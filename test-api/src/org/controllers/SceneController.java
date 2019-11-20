package org.controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.User;

public class SceneController {

    public static final String MAIN_MENU = "../fxmls/mainMenu.fxml";
    public static final String SIGN_UP = "../fxmls/signUp.fxml";
    public static final String LOGIN = "../fxmls/login.fxml";
    public static final String ACCOUNT = "../fxmls/account.fxml";
    public static final String CART = "../fxmls/cart.fxml";
    public static final String BURGERS = "../fxmls/burgers.fxml";
    public static final String SANDWICHES = "../fxmls/sandwiches.fxml";
    public static final String DRINKS = "../fxmls/drinks.fxml";
    public static final String FRIESNSIDES = "../fxmls/friesAndSides.fxml";
    public static final String ALCOHOL = "../fxmls/alcohol.fxml";
    public static final String SALADS = "../fxmls/salads.fxml";


    public static void switchScenes(Stage stage, String fxml, User user) {
        AbstractController controller = null;
        switch (fxml) {
            case MAIN_MENU:
                controller = new MainMenuController(stage, fxml, user);
                break;
            case SIGN_UP:
                controller = new SignUpController(stage, fxml);
                break;
            case LOGIN:
                controller = new LoginController(stage, fxml);
        }
        stage.setScene(new Scene(controller));
    }
}
