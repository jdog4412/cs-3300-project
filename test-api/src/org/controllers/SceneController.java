package org.controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.User;

public class SceneController {

    public static final String MAIN_MENU = "../fxmls/mainMenu.fxml";
    public static final String SIGN_UP = "../fxmls/signUp.fxml";
    public static final String LOGIN = "../fxmls/login.fxml";
    public static final String ACCOUNT = "../fxmls/myAccount.fxml";
    public static final String ACCOUNT_EDIT = "../fxmls/myAccountEdit.fxml";
    public static final String CART = "../fxmls/cart.fxml";
    public static final String BURGERS = "../fxmls/burgersScene.fxml";
    public static final String SANDWICHES = "../fxmls/sandwichesScene.fxml";
    public static final String DRINKS = "../fxmls/drinks.fxml";
    public static final String FRIESNSIDES = "../fxmls/friesAndSides.fxml";
    public static final String BEER = "../fxmls/beer.fxml";
    public static final String SALADS = "../fxmls/salads.fxml";
    public static final String CONFIRM = "../fxmls/confirmPage.fxml";
    public static final String CHECKOUT = "../fxmls/checkout.fxml";


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
                break;
            case ACCOUNT:
                controller = new MyAccountController(stage, fxml, user);
                break;
            case ACCOUNT_EDIT:
                controller = new MyAccountControllerEdit(stage, fxml, user);
                break;
            case CART:
                controller = new CartController(stage, fxml, user);
                break;
            case BURGERS:
                controller = new BurgersController(stage, fxml, user);
                break;
            case SANDWICHES:
                controller = new SandwichesController(stage, fxml, user);
                break;
            case BEER:
                controller = new BeerController(stage, fxml, user);
                break;
            case DRINKS:
                controller = new DrinksController(stage, fxml, user);
                break;
            case CONFIRM:
                controller = new ConfirmController(stage, fxml);
        }
        stage.setScene(new Scene(controller));
    }
}
