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


public class LoginController extends AbstractController {

    private Stage stage;
    private Database db = new Database();

    @FXML
   private TextField passwordField, usernameField;

    public LoginController(Stage stage, String fxml) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
    }

    @FXML
    private void login(ActionEvent event) {
        WorkIndicatorDialog<Void, Boolean> loginDialog = new WorkIndicatorDialog<>(stage.getOwner(), "Logging in...");
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(!username.equals("") && !password.equals(""))  {

            loginDialog.addTaskEndNotification(c -> {
                if(c) {
                    User user = new User(username);
                    SceneController.switchScenes(stage, SceneController.MAIN_MENU, user);
                } else {
                    invalidLogin();
                }
            });

            loginDialog.execute(null, (ignore, c) -> db.checkUsernameAndPassword(username, password));

        }
    }

    @FXML
    private void signup(ActionEvent event) {
        SceneController.switchScenes(stage, "../fxmls/signUp.fxml", null);
    }

    private void invalidLogin() {
        usernameField.clear();
        passwordField.clear();
        Stage error = new Stage();
        error.setTitle("Invalid Login");
        ErrorController controller = new ErrorController("../fxmls/invalidLogin.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }
}
