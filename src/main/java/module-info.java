module AP2{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    opens model to com.google.gson;
    opens controller to com.google.gson;
    exports view;
    exports model;
    opens view to javafx.fxml;

}