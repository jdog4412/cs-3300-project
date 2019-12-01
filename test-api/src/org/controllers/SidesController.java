package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.User;
import org.utils.Database;
import org.utils.Toast;
import org.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SidesController extends AbstractController {

    private User user;
    private Stage stage;
    private Database db = new Database();

    public SidesController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
        this.user = user;
    }

    @FXML
    private Button onionRings, sideSalad;

    @FXML
    private void addToCart(ActionEvent event) {
        Object button = event.getSource();
        List<String> items = new ArrayList<>();

        if (button.equals(onionRings)) {
            items = getOnionRings(items);
        } else if (button.equals(sideSalad)) {
            items = getSideSalad(items);
        }

        if (!items.isEmpty()) {
            user.addItem(items.get(0), items.get(1));
            Toast.makeText(stage, "Item successfully added", 2000, 500, 500);
        }
    }

    @FXML
    private void addFries(ActionEvent event) {
        Stage stage = new Stage();
        FriesController controller = new FriesController(stage, SceneController.FRIES, user);
        stage.setScene(new Scene(controller));
        stage.show();
    }

    @FXML
    private void addSoup(ActionEvent event) {
        Stage stage = new Stage();
        SoupController controller = new SoupController(stage, SceneController.SOUP, user);
        stage.setScene(new Scene(controller));
        stage.show();
    }

    @FXML
    private void myCart(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.CART, user);
    }

    @FXML
    private void mainMenu(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.MAIN_MENU, user);
    }

    private List<String> getOnionRings(List<String> items) {
        boolean inStock;
        String onionRings = "Small Onion Rings";

        inStock = db.checkInventory(onionRings);

        if (inStock) {
            items = db.getItem(onionRings);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getSideSalad(List<String> items) {
        boolean inStock;
        String sideSalad = "Side Salad";

        inStock = db.checkInventory(sideSalad);

        if (inStock) {
            items = db.getItem(sideSalad);
        } else {
            notInStock();
        }
        return items;
    }

    private void notInStock() {
        Stage error = new Stage();
        error.setTitle("Item not in stock");
        ErrorController controller = new ErrorController("../fxmls/notInStock.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }

}
