module desktop.team4_desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens desktop.team4_desktop to javafx.fxml;
    exports desktop.team4_desktop;
}