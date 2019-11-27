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

public class BurgersController extends AbstractController {

    private Stage stage;
    private Database db = new Database();
    private User user;

    public BurgersController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
        this.user = user;
    }

    @FXML
    private Button original, abc, mushavcswiss, blackbean;

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

        if (button.equals(original)) {
            items = getOriginal(items);
        } else if (button.equals(abc)) {
            items = getABC(items);
        } else if (button.equals(mushavcswiss)) {
            items = getMushAvcSwiss(items);
        } else if (button.equals(blackbean)) {
            items = getBlackBean(items);
        }

        user.addItem(items.get(0), items.get(1));
        Toast.makeText(stage, "Item successfully added", 2000, 500, 500);

    }

    private List<String> getOriginal(List<String> items) {
        boolean inStock;
        String original = "Clydes Original";

        inStock = db.checkInventory(original);
        if (inStock) {
            items = db.getItem(original);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getABC(List<String> items) {
        boolean inStock;
        String abc = "A.B.C Burger";

        inStock = db.checkInventory(abc);
        if (inStock) {
            items = db.getItem(abc);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getMushAvcSwiss(List<String> items) {
        boolean inStock;
        String mushavcswiss = "Mushroom Avocado Swiss Burger";

        inStock = db.checkInventory(mushavcswiss);
        if (inStock) {
            items = db.getItem(mushavcswiss);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getBlackBean(List<String> items) {
        boolean inStock;
        String blackbean = "Black Bean Burger";

        inStock = db.checkInventory(blackbean);
        if (inStock) {
            items = db.getItem(blackbean);
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
