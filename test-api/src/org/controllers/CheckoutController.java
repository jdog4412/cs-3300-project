package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.User;
import org.utils.Database;
import org.utils.Utils;
import org.utils.WorkIndicatorDialog;

import java.util.Map;

public class CheckoutController extends AbstractController{

    private Stage stage;
    private User user;
    private Database db = new Database();

    public CheckoutController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
        this.user = user;
        populateField(listView, textField, user);
        populateUserFields();
    }

    @FXML
    private TextField textField, firstName, lastName, address, creditCard, expire, cvc;

    @FXML
    private CheckBox saveCard;

    @FXML
    private ListView listView = new ListView();

    @FXML
    private void myAccount(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.ACCOUNT, user);
    }

    @FXML
    private void mainMenu(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.MAIN_MENU, user);
    }

    @FXML
    private void confirm(ActionEvent event) {
        WorkIndicatorDialog<Void, Void> confirmDialog = new WorkIndicatorDialog<>(stage.getOwner(), "Sending information...");
        if (!areFieldsEmpty()) {
            confirmDialog.addTaskEndNotification(ignore -> {
                SceneController.switchScenes(stage, SceneController.CONFIRM, null);
            });
            confirmDialog.execute(null, (ignore1, ignore2) -> {
                if (saveCard.isSelected()) {
                    db.deleteUser(user.getUsername());
                    db.setUser(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(),
                            creditCard.getText(), user.getPhoneNumber());
                }
                return ignore1;
            });

        } else {
            emptyFields();
        }
        //TODO: Create Clyde's API
    }

    private void populateField(ListView listView, TextField textField, User user) {
        Map<String, String> cart = user.getCartWithPrice();
        double total = 0.00;

        for (Map.Entry mapElement : cart.entrySet()) {
            String item = (String) mapElement.getKey();
            int quantity = user.getCartWithQuantity().get(item);
            String value = (String) mapElement.getValue();
            String full = String.format("(x%s) %s", quantity, item);

            listView.getItems().add(full);
            total = total + (Double.parseDouble(value) * quantity);

        }
        textField.clear();
        textField.setText("$ " + total);

    }

    private void populateUserFields() {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        address.setText(user.getEmail());
    }

    private boolean areFieldsEmpty() {
        return firstName.getText().isEmpty() || lastName.getText().isEmpty() || address.getText().isEmpty()
                || creditCard.getText().isEmpty() || expire.getText().isEmpty() || cvc.getText().isEmpty();
    }

    private void emptyFields() {
        Stage error = new Stage();
        error.setTitle("Fields are empty");
        ErrorController controller = new ErrorController("../fxmls/emptyFields.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }

}
