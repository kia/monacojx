package de.softquadrat.monacojx;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.permission.callback.RequestPermissionCallback;
import com.teamdev.jxbrowser.view.javafx.BrowserView;

import static com.teamdev.jxbrowser.permission.PermissionType.CLIPBOARD_READ_WRITE;

public class MonacoEditorFactoryImpl implements MonacoEditorFactory {

    public MonacoEditor newInstance() {
        Engine engine = Engine.newInstance(RenderingMode.HARDWARE_ACCELERATED);
        engine.permissions().set(RequestPermissionCallback.class, (params, tell) -> {
            if (params.permissionType() == CLIPBOARD_READ_WRITE) {
                tell.grant();
            } else {
                tell.deny();
            }
        });
        Browser browser = engine.newBrowser();
        browser.settings().allowJavaScriptAccessClipboard();
        BrowserView browserView = BrowserView.newInstance(browser);

        MonacoEditor monacoEditor = new MonacoEditor(browser, browserView);

        return monacoEditor;
    }
}
