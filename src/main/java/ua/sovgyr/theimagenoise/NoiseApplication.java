package ua.sovgyr.theimagenoise;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ua.sovgyr.theimagenoise.components.BaseContext;
import ua.sovgyr.theimagenoise.controllers.MainController;

/**
 * Created by dimdron on 13.11.14.
 */
public class NoiseApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/window_main.fxml"));
        Parent parent = loader.load();
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

}
