package org.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.User;
import org.utils.Utils;

import java.util.Map;

public class CartController extends AbstractController {

    private User user;
    private Stage stage;

    public CartController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
        this.user = user;
        populateField(listView, textField, user);
    }

    @FXML
    private TextField textField = new TextField();

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
    private void checkout(ActionEvent event) {
        if (!user.getCartWithPrice().isEmpty() || !user.getCartWithQuantity().isEmpty()) {
            SceneController.switchScenes(stage, SceneController.CHECKOUT, user);
        } else {
            emptyCart();
        }
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

    private void emptyCart() {
        Stage error = new Stage();
        error.setTitle("Empty Cart");
        ErrorController controller = new ErrorController("../fxmls/emptyCart.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }

}
