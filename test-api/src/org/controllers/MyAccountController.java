package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.User;
import org.utils.Database;
import org.utils.Toast;
import org.utils.Utils;

public class MyAccountController extends AbstractController {

    private Stage stage;
    private User user;
    private Database db = new Database();

    public MyAccountController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.user = user;
        this.stage = stage;
        populateData();
    }

    @FXML
    private Text firstName, lastName, email, username, phoneNumber;

    @FXML
    private void mainMenu(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.MAIN_MENU, user);
    }

    @FXML
    private void myCart(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.CART, user);
    }

    @FXML
    private void logOut(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.LOGIN, null);
        Toast.makeText(stage, "Successfully Logged Out", 2000, 500, 500);
    }

    @FXML
    private void edit(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.ACCOUNT_EDIT, user);
    }

    private void populateData() {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        phoneNumber.setText(user.getPhoneNumber());
    }

}
