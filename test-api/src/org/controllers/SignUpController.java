package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.User;
import org.utils.Database;
import org.utils.Utils;
import org.utils.WorkIndicatorDialog;

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

        WorkIndicatorDialog<Void, User> signUpDialog = new WorkIndicatorDialog<>(stage.getOwner(), "Creating new User...");

        if (!areFieldsEmpty()) {

                signUpDialog.addTaskEndNotification(user -> {
                    if (user != null) {
                        SceneController.switchScenes(stage, SceneController.MAIN_MENU, user);
                    } else {
                        passwordsDontMatch();
                    }
                });

            signUpDialog.execute(null, (ignore1, ignore2) -> {
                if (passwordField.getText().equals(confirmPasswordField.getText())) {
                    db.setUser(usernameField.getText(), passwordField.getText(), firstNameField.getText(),
                            lastNameField.getText(), emailField.getText(), " ", phoneNumberField.getText());
                    return new User(usernameField.getText());
                }
                return null;
            });
        } else {
            emptyFields();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.LOGIN, null);
    }

    private void emptyFields() {
        Stage error = new Stage();
        error.setTitle("Fields are empty");
        ErrorController controller = new ErrorController("/org/fxmls/emptyFields.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }

    private void passwordsDontMatch() {
        Stage error = new Stage();
        error.setTitle("Passwords do not match");
        ErrorController controller = new ErrorController("/org/fxmls/passwordsDontMatch.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }

    private boolean areFieldsEmpty() {
        return firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || phoneNumberField.getText().isEmpty() || usernameField.getText().isEmpty() ||
                passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty();
    }

}
