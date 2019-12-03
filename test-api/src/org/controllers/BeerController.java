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

public class BeerController extends AbstractController{

    private Stage stage;
    private User user;
    private Database db = new Database();

    @FXML
    private Button beehive, laughingLab, titan, colette, claymore, glider, cherry, coors, blueMoon, milkStout;

    public BeerController(Stage stage, String fxml, User user) {
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

        if (button.equals(beehive)) {
            items = getBeehive(items);
        } else if (button.equals(laughingLab)) {
            items = getLaughingLab(items);
        } else if (button.equals(titan)) {
            items = getTitan(items);
        } else if (button.equals(colette)) {
            items = getColette(items);
        } else if (button.equals(claymore)) {
            items = getClaymore(items);
        } else if (button.equals(glider)) {
            items = getGlider(items);
        } else if (button.equals(cherry)) {
            items = getCherry(items);
        } else if (button.equals(coors)) {
            items = getCoors(items);
        } else if (button.equals(blueMoon)) {
            items = getBlueMoon(items);
        } else if (button.equals(milkStout)) {
            items = getMilkStout(items);
        }

        user.addItem(items.get(0), items.get(1));
        Toast.makeText(stage, "Item successfully added", 2000, 500, 500);

    }

    private List<String> getBeehive(List<String> items) {
        boolean inStock;
        String beehive = "Beehive";

        inStock = db.checkInventory(beehive);
        if (inStock) {
            items = db.getItem(beehive);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getLaughingLab(List<String> items) {
        boolean inStock;
        String laughingLab = "Laughing Lab";

        inStock = db.checkInventory(laughingLab);
        if (inStock) {
            items = db.getItem(laughingLab);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getTitan(List<String> items) {
        boolean inStock;
        String titan = "Titan";

        inStock = db.checkInventory(titan);
        if (inStock) {
            items = db.getItem(titan);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getColette(List<String> items) {
        boolean inStock;
        String colette = "Colette";

        inStock = db.checkInventory(colette);
        if (inStock) {
            items = db.getItem(colette);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getClaymore(List<String> items) {
        boolean inStock;
        String claymore = "Claymore";

        inStock = db.checkInventory(claymore);
        if (inStock) {
            items = db.getItem(claymore);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getGlider(List<String> items) {
        boolean inStock;
        String glider = "Glider Cider";

        inStock = db.checkInventory(glider);
        if (inStock) {
            items = db.getItem(glider);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getCherry(List<String> items) {
        boolean inStock;
        String cherry = "Cherry Glider";

        inStock = db.checkInventory(cherry);
        if (inStock) {
            items = db.getItem(cherry);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getCoors(List<String> items) {
        boolean inStock;
        String coors = "Coors";

        inStock = db.checkInventory(coors);
        if (inStock) {
            items = db.getItem(coors);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getBlueMoon(List<String> items) {
        boolean inStock;
        String blueMoon = "Blue Moon";

        inStock = db.checkInventory(blueMoon);
        if (inStock) {
            items = db.getItem(blueMoon);
        } else {
            notInStock();
        }
        return items;
    }

    private List<String> getMilkStout(List<String> items) {
        boolean inStock;
        String milkStout = "Milk Stout Nitro";

        inStock = db.checkInventory(milkStout);
        if (inStock) {
            items = db.getItem(milkStout);
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
