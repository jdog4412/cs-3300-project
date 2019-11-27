package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
        SceneController.switchScenes(stage, SceneController.CHECKOUT, user);
    }

    private void populateField(ListView listView, TextField textField, User user) {
        Map<String, String> cart = user.getCart();
        double total = 0.0;

        for (Map.Entry mapElement : cart.entrySet()) {
            String item = (String) mapElement.getKey();
            String value = (String) mapElement.getValue();
            String full = item + "\t\t\t\t\t" + value;

            listView.getItems().add(full);
            total = total + Double.parseDouble(value);

        }
        textField.clear();
        textField.setText(Double.toString(total));

    }

}
