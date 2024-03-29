package org;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controllers.LoginController;
import org.controllers.SceneController;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

            LoginController login = new LoginController(stage, SceneController.LOGIN);
            Scene scene = new Scene(login);

            stage.setTitle("Clyde's Pickup");

            stage.setScene(scene);

            stage.setResizable(false);

            stage.show();
    }

}
