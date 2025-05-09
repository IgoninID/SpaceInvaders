module com.classig.gamespace {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.classig.gamespace to javafx.fxml;
    exports com.classig.gamespace;
}