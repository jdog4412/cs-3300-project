package org;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controllers.LoginController;

public class Main extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) {
        LoginController login = new LoginController(stage, "..\\fxmls\\login.fxml");
        Scene scene = new Scene( login, 800, 400);

        stage.setTitle("Clyde's Pickup - Login");

        stage.setScene(scene);

        stage.show();
    }
}
