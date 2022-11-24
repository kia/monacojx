package de.softquadrat.monacojx;

import com.teamdev.jxbrowser.js.JsAccessible;

public final class PasteAction extends AbstractEditorAction {

    public PasteAction() {
        setLabel("Paste2");
        setName("Paste2");
        setActionId("editor.action.clipboardPasteAction");
        setContextMenuOrder("3");
        setContextMenuGroupId("9_cutcopypaste");
        setVisibleOnReadonly(false);
        setKeyBindings("monaco.KeyMod.ShiftCmd & monaco.KeyCode.Insert");
        setRunScript("console.log('paste Action!');" +
                "let position = editor.getPosition();" +
                "console.log('position' + position);" +
                "let newPosition = window.clipboardBridge.paste(editor.getSelection(), position);" +
                "editor.setPosition(newPosition);" +
                "editor.focus();"
        );
    }

    @Override
    @JsAccessible
    public void action() {
        // empty
        System.out.println("action");
    }
}
