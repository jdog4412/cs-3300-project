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

public class SandwichesController extends AbstractController {

    private Stage stage;
    private User user;
    private Database db = new Database();

    @FXML
    private Button blta, classicChicken, fishTacos, grilledCheese;

    public SandwichesController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
        this.user = user;
    }

    @FXML
    private void mainMenu(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.MAIN_MENU, user);
    }

    @FXML
    private void myCart(ActionEvent event) {
        SceneController.switchScenes(stage, SceneController.CART, user);
    }

    @FXML
    private void addToCart(ActionEvent event) {
        Object button = event.getSource();
        List<String> items = new ArrayList<>();

        if (button.equals(blta)) {
            items = getBlta(items);
        } else if (button.equals(classicChicken)) {
            items = getClassicChicken(items);
        } else if (button.equals(fishTacos)) {
            items = getFishTacos(items);
        } else if (button.equals(grilledCheese)) {
            items = getGrilledCheese(items);
        }

        user.addItem(items.get(0), items.get(1));
        Toast.makeText(stage, "Item successfully added", 2000, 500, 500);

    }

    private List<String> getBlta(List<String> items) {
        boolean inStock;
        String BLTA = "BLTA Sandwich";

        inStock = db.checkInventory(BLTA);
        if (inStock) {
            items = db.getItem(BLTA);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getClassicChicken(List<String> items) {
        boolean inStock;
        String chicken = "Classic Chicken Sandwich";

        inStock = db.checkInventory(chicken);
        if (inStock) {
            items = db.getItem(chicken);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getFishTacos(List<String> items) {
        boolean inStock;
        String tacos = "Ahi Fish Tacos";

        inStock = db.checkInventory(tacos);
        if (inStock) {
            items = db.getItem(tacos);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getGrilledCheese(List<String> items) {
        boolean inStock;
        String grChz = "BYO Grilled Cheese";

        inStock = db.checkInventory(grChz);
        if (inStock) {
            items = db.getItem(grChz);
        } else {
            notInStock();
        }
        return items;
    }

    private void notInStock() {
        Stage error = new Stage();
        error.setTitle("Item not in stock");
        ErrorController controller = new ErrorController("../notInStock.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }


}
