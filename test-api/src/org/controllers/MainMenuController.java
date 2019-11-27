package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.User;
import org.utils.Utils;


public class MainMenuController extends AbstractController {

    private Stage stage;
    private User user;

    public MainMenuController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
        this.user = user;
    }

    @FXML
    private void goToAccount(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.ACCOUNT, user);
    }

    @FXML
    private void goToCart(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.CART, user);
    }

    @FXML
    private void goToBurgers(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.BURGERS, user);
    }

    @FXML
    private void goToSandwiches(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.SANDWICHES, user);
    }

    @FXML
    private void goToSalads(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.SALADS, user);
    }

    @FXML
    private void goToFriesAndSides(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.FRIESNSIDES, user);
    }

    @FXML
    private void goToDrinks(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.DRINKS, user);
    }

    @FXML
    private void goToAlcohol(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.BEER, user);
    }
}
