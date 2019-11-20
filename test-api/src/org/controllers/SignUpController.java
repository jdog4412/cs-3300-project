package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.User;
import org.utils.Database;
import org.utils.Utils;

public class SignUpController extends AbstractController {

    private Stage stage;
    private Database db = new Database();

    @FXML
    private TextField firstNameField, lastNameField, emailField, phoneNumberField, usernameField, passwordField, confirmPasswordField;

    public SignUpController(Stage stage, String fxml) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
    }

    @FXML
    private void signup(ActionEvent event) {
        if (passwordField.getText().equals(confirmPasswordField.getText())) {
            db.setUser(usernameField.getText(), passwordField.getText(), firstNameField.getText(),
                    lastNameField.getText(), emailField.getText(), " ", phoneNumberField.getText());
            User user = new User(usernameField.getText());
            SceneController.switchScenes(stage, "../fxmls/mainMenu.fxml", user);
        } else {
            passwordsDontMatch();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        SceneController.switchScenes(stage, "../fxmls/login.fxml", null);
    }


    private void passwordsDontMatch() {
        Stage error = new Stage();
        error.setTitle("Passwords do not match");
        ErrorController controller = new ErrorController("../fxmls/passwordsDontMatch.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }


}
