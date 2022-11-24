module de.softquadrat.monacojx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jxbrowser;
    requires jxbrowser.javafx;


    opens de.softquadrat.monacojx to javafx.fxml;
    exports de.softquadrat.monacojx;
}