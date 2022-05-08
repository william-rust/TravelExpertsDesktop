package desktop.team4_desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600); // matched width and height of main-view.fxml
        stage.setTitle("Travel Experts");
        stage.setScene(scene);
        stage.show();
//        stage.setResizable(false);
    }


    public static void main(String[] args) {
        launch();
    }
}