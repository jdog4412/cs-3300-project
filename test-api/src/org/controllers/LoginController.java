package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.utils.Database;
import org.utils.Utils;


public class LoginController extends AnchorPane {

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
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean login = false;

        if(!username.equals("") && !password.equals(""))  {
            login = db.checkUsernameAndPassword(username, password);

            if (login) {
                SceneController.switchScenes(stage, "../fxmls/mainMenu.fxml");
            } else {
                invalidLogin();
            }
        }
    }

    @FXML
    private void signup(ActionEvent event) {
        SceneController.switchScenes(stage, "../fxmls/signup.fxml");
    }

    private void invalidLogin() {
        Stage error = new Stage();
        error.setTitle("Invalid Login");
        ErrorController controller = new ErrorController("../fxmls/invalidLogin.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }
}
