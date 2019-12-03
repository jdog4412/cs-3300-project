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

public class DrinksController extends AbstractController{

    private Stage stage;
    private User user;
    private Database db = new Database();

    public DrinksController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
        this.user = user;
    }

    @FXML
    private Button iceTea, lemonade, coffee, hotTea, soda;

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

        if (button.equals(iceTea)) {
            items = getIceTea(items);
        } else if (button.equals(lemonade)) {
            items = getLemonade(items);
        } else if (button.equals(coffee)) {
            items = getCoffee(items);
        } else if (button.equals(hotTea)) {
            items = getHotTea(items);
        } else if (button.equals(soda)) {
            items = getSoda(items);
        }

        user.addItem(items.get(0), items.get(1));
        Toast.makeText(stage, "Item successfully added", 2000, 500, 500);

    }

    private List<String> getIceTea(List<String> items) {
        boolean inStock;
        String iceTea = "Iced Tea";

        inStock = db.checkInventory(iceTea);
        if (inStock) {
            items = db.getItem(iceTea);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getLemonade(List<String> items) {
        boolean inStock;
        String lemonade = "Lemonade";

        inStock = db.checkInventory(lemonade);
        if (inStock) {
            items = db.getItem(lemonade);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getCoffee(List<String> items) {
        boolean inStock;
        String coffee = "Coffee";

        inStock = db.checkInventory(coffee);
        if (inStock) {
            items = db.getItem(coffee);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getHotTea(List<String> items) {
        boolean inStock;
        String hotTea = "Hot Tea";

        inStock = db.checkInventory(hotTea);
        if (inStock) {
            items = db.getItem(hotTea);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getSoda(List<String> items) {
        boolean inStock;
        String soda = "Soda";

        inStock = db.checkInventory(soda);
        if (inStock) {
            items = db.getItem(soda);
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
