package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.User;
import org.utils.Database;
import org.utils.Utils;
import org.utils.WorkIndicatorDialog;

public class MyAccountControllerEdit extends AbstractController {

    private Stage stage;
    private User user;
    private Database db = new Database();

    public MyAccountControllerEdit(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
        this.user = user;

    }

    @FXML
    private TextField firstName, lastName, email, username, phoneNumber;

    @FXML
    private PasswordField newPassword;

    @FXML
    private void saveInfo(ActionEvent event) {
        WorkIndicatorDialog<Void, User> editDialog = new WorkIndicatorDialog<>(stage.getOwner(), "Updating information...");

        if (!areFieldsEmpty()) {

            editDialog.addTaskEndNotification(user -> {
                SceneController.switchScenes(stage, SceneController.ACCOUNT, user);
            });

            editDialog.execute(null, (ignore1, status) -> {
                String card = user.getCard();
                db.deleteUser(user.getUsername());
                db.setUser(username.getText(), newPassword.getText(), firstName.getText(),
                        lastName.getText(), email.getText(), card, phoneNumber.getText());
                status.accept("Information saved, returning to Account Information...");
                return new User(username.getText());
            });



        } else {
            emptyFields();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.ACCOUNT, user);
    }

    private boolean areFieldsEmpty() {
        return firstName.getText().isEmpty() || lastName.getText().isEmpty() ||
                email.getText().isEmpty() || phoneNumber.getText().isEmpty() || username.getText().isEmpty() ||
                newPassword.getText().isEmpty();
    }

    private void emptyFields() {
        Stage error = new Stage();
        error.setTitle("Fields are empty");
        ErrorController controller = new ErrorController("/org/fxmls/emptyFields.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }

}
