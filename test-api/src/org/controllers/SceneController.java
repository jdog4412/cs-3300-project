package org.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.User;


public class SceneController {

    public static final String MAIN_MENU = "/org/fxmls/mainMenu.fxml";
    public static final String SIGN_UP = "/org/fxmls/signUp.fxml";
    public static final String LOGIN = "/org/fxmls/login.fxml";
    public static final String ACCOUNT = "/org/fxmls/myAccount.fxml";
    public static final String ACCOUNT_EDIT = "/org/fxmls/myAccountEdit.fxml";
    public static final String CART = "/org/fxmls/cart.fxml";
    public static final String BURGERS = "/org/fxmls/burgersScene.fxml";
    public static final String SANDWICHES = "/org/fxmls/sandwichesScene.fxml";
    public static final String DRINKS = "/org/fxmls/drinks.fxml";
    public static final String FRIESNSIDES = "/org/fxmls/sidesScene.fxml";
    public static final String BEER = "/org/fxmls/beer.fxml";
    public static final String SALADS = "/org/fxmls/saladScene.fxml";
    public static final String CONFIRM = "/org/fxmls/confirmPage.fxml";
    public static final String CHECKOUT = "/org/fxmls/checkout.fxml";
    public static final String FRIES = "/org/fxmls/fries.fxml";
    public static final String SOUP = "/org/fxmls/soup.fxml";



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
                break;
            case SALADS:
                controller = new SaladController(stage, fxml, user);
                break;
            case FRIESNSIDES:
                controller = new SidesController(stage, fxml, user);
                break;
            case CHECKOUT:
                controller = new CheckoutController(stage, fxml, user);
                break;
        }
        stage.setScene(new Scene(controller));
    }
}
