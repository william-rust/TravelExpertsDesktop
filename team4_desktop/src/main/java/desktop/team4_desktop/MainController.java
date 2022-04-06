package desktop.team4_desktop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane apBackground;

    @FXML
    void initialize() {
        assert apBackground != null : "fx:id=\"apBackground\" was not injected: check your FXML file 'main-view.fxml'.";

    }

}
