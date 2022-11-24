package de.softquadrat.monacojx;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.deps.org.checkerframework.checker.nullness.Opt;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.frame.EditorCommand;
import com.teamdev.jxbrowser.frame.Frame;
import com.teamdev.jxbrowser.js.JsObject;
import com.teamdev.jxbrowser.navigation.Navigation;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MonacoEditorTest {

    @Mock
    private Browser browser;

    @Mock
    private BrowserView browserView;

    @InjectMocks
    private MonacoEditor monacoEditor;

    @Before
    public void init() {
        assertNotNull(monacoEditor);
    }

    @Test
    public void updateText() {
        // given
        Frame frame = Mockito.mock(Frame.class);
        JsObject jsObject = mock(JsObject.class);
        when(frame.executeJavaScript("updateText('update text')")).thenReturn(jsObject);
        Optional<Frame> mainFrameOptional = Optional.of(frame);
        when(browser.mainFrame()).thenReturn(mainFrameOptional);
        // when
        monacoEditor.updateText("update text");
        // then
        verify(frame).executeJavaScript("updateText('update text')");


    }
}