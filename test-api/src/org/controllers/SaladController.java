package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.User;
import org.utils.Database;
import org.utils.Toast;
import org.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SaladController extends AbstractController {

    private User user;
    private Stage stage;
    private Database db = new Database();

    public SaladController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
        this.user = user;
    }

    @FXML
    private Button houseSalad, caesarSalad, ahiSalad;

    @FXML
    private void addToCart(ActionEvent event) {
        Object button = event.getSource();
        List<String> items = new ArrayList<>();

        if (button.equals(houseSalad)) {
            items = getHouse(items);
        } else if (button.equals(caesarSalad)) {
            items = getCaesar(items);
        } else if (button.equals(ahiSalad)) {
            items = getAhi(items);
        }

        if (!items.isEmpty()) {
            user.addItem(items.get(0), items.get(1));
            Toast.makeText(stage, "Item successfully added", 2000, 500, 500);
        }

    }

    @FXML
    private void myCart(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.CART, user);
    }

    @FXML
    private void mainMenu(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.MAIN_MENU, user);
    }

    private List<String> getHouse (List<String> items) {
        boolean inStock;
        String houseSalad = "House Salad";

        inStock = db.checkInventory(houseSalad);

        if (inStock) {
            items = db.getItem(houseSalad);
        } else {
            notInStock();
        }

        return items;
    }

    private List<String> getCaesar (List<String> items) {
        boolean inStock;
        String caesarSalad = "Caesar Salad";

        inStock = db.checkInventory(caesarSalad);

        if (inStock) {
            items = db.getItem(caesarSalad);
        } else {
            notInStock();
        }

        return items;
    }

    private List<String> getAhi (List<String> items) {
        boolean inStock;
        String ahiSalad = "Ahi Salad";

        inStock = db.checkInventory(ahiSalad);

        if (inStock) {
            items = db.getItem(ahiSalad);
        } else {
            notInStock();
        }

        return items;
    }

    private void notInStock() {
        Stage error = new Stage();
        error.setTitle("Item not in stock");
        ErrorController controller = new ErrorController("/org/fxmls/notInStock.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }

}
