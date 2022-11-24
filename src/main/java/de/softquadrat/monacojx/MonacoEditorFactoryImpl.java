package de.softquadrat.monacojx;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.javafx.BrowserView;

public class MonacoEditorFactoryImpl implements MonacoEditorFactory {

    public MonacoEditor newInstance() {
        Engine engine = Engine.newInstance(RenderingMode.HARDWARE_ACCELERATED);
        Browser browser = engine.newBrowser();
        browser.settings().allowJavaScriptAccessClipboard();
        BrowserView browserView = BrowserView.newInstance(browser);

        MonacoEditor monacoEditor = new MonacoEditor(browser, browserView);

        return monacoEditor;
    }
}
