package de.softquadrat.monacojx;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.event.ConsoleMessageReceived;
import com.teamdev.jxbrowser.js.JsObject;
import com.teamdev.jxbrowser.view.javafx.BrowserView;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


public class MonacoEditor {

    private final static String EDITOR_HTML_RESOURCE_LOCATION = "/de/softquadrat/monacojx/monaco-editor/index.html";
    private final Browser browser;
    private final BrowserView browserView;
    private boolean readOnly = false;

    public MonacoEditor(Browser browser, BrowserView browserView) {
        this.browser = browser;
        this.browserView = browserView;
    }

    public void init() {
        String url = MonacoEditor.class.getResource(EDITOR_HTML_RESOURCE_LOCATION).toExternalForm();
        browser.navigation().loadUrlAndWait(url);

        Document document = new Document(this);
        SystemClipboardWrapper systemClipboard = new SystemClipboardWrapper();
        ClipboardBridge clipboardBridge = new ClipboardBridge(document, systemClipboard);

        JsObject window = executeJavaScript("window");
        window.putProperty("clipboardBridge", clipboardBridge);

        addContextMenuAction(new PasteAction());

        browser.on(ConsoleMessageReceived.class, event -> {
            String message = event.consoleMessage().message();
            System.out.println(message);
        });

    }

    public BrowserView getBrowserView() {
        return browserView;
    }

    public JsObject updateText(String text) {
        boolean accessClipboard = browser.settings().isAllowJavaScriptAccessClipboard();
        String script = "updateText('%s')".formatted(text + " - " + accessClipboard);
        return executeJavaScript(script);
    }

    public JsObject executeJavaScript(String script) {
        AtomicReference<JsObject> jsObject = new AtomicReference<>();
        browser.mainFrame().ifPresent(frame -> jsObject.set(frame.executeJavaScript(script)));
        return jsObject.get();
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadonly(boolean readOnly) {
        this.readOnly = readOnly;
        setOption("readOnly", readOnly);
    }

    public void setOption(String optionName, Object value) {
        String script = String.format("editorView.updateOptions({ " + optionName + ": %s })", value);
        executeJavaScript(script);
    }

    public void addContextMenuAction(AbstractEditorAction action) {
        String precondition = "null";
        if(!action.isVisibleOnReadonly() && readOnly) {
            precondition = "\"false\"";
        }
        JsObject window = executeJavaScript("window");
        String actionName = action.getName();
        String keyBindings = Arrays.stream(action.getKeyBindings()).collect(Collectors.joining(","));
        window.putProperty(actionName, action);
        String contextMenuOrder = "";
        if (action.getContextMenuOrder() != null && !action.getContextMenuOrder().isEmpty()) {
            contextMenuOrder = "contextMenuOrder: " + action.getContextMenuOrder() + ",\n";
        }

        executeJavaScript(
                "editorView.addAction({" +
                    "id: '" + action.getActionId() + actionName + "'," +
                    "label: '" + action.getLabel() + "'," +
                    "contextMenuGroupId: '" + action.getContextMenuGroupId() + "'," +
                    "precondition: " + precondition + "," +
                    "keybindings: [" + keyBindings + "]," +
                    contextMenuOrder +
                    "run: (editor) => {" +
                        "console.log('editor:' + editor);" +
                        actionName + ".action();" +
                        action.getRunScript() +"}" +
                "});"
        );

    }

    public String getText() {
        return executeJavaScript("editorView").call("getValue");
    }
}
