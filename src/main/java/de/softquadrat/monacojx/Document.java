package de.softquadrat.monacojx;

import com.teamdev.jxbrowser.browser.Browser;

public class Document {


    private final MonacoEditor monacoEditor;

    public Document(MonacoEditor monacoEditor) {
        this.monacoEditor = monacoEditor;
    }


    public String getText() {
        return monacoEditor.getText();
    }

    public void updateText(String text) {
        monacoEditor.updateText(text);
    }
}
