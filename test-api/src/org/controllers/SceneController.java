package org.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

    public static void switchScenes(Stage stage, String fxml) {
        switch(fxml) {
            case "../fxmls/mainMenu.fxml":
//                MainMenuController controller = new MainMenuController(stage, fxml);
                break;
        }
        //stage.setScene(new Scene(controller));
    }
}
