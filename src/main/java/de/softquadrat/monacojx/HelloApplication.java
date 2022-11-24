package de.softquadrat.monacojx;

import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MonacoEditor monacoEditor =  new MonacoEditorFactoryImpl().newInstance();
        monacoEditor.init();
        BrowserView browserView = monacoEditor.getBrowserView();

        Scene scene = new Scene(new BorderPane(browserView), 700, 500);
        stage.setTitle("JavaFx BrowserView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}