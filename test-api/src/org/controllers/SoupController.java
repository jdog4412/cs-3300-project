package org.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.User;
import org.utils.Database;
import org.utils.Toast;
import org.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SoupController extends AbstractController{

    private Stage stage;
    private User user;
    private Database db = new Database();

    public SoupController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.user = user;
        this.stage = stage;
        ToggleGroup toggleGroup = new ToggleGroup();
        cup.setToggleGroup(toggleGroup);
        bowl.setToggleGroup(toggleGroup);
    }

    @FXML
    private RadioButton cup, bowl;

    @FXML
    private void addToCart(ActionEvent event) {
        boolean cupSoup = cup.isSelected();
        boolean bowlSoup = bowl.isSelected();
        List<String> items = new ArrayList<>();

        if (cupSoup) {
            items = getCup(items);
        } else if (bowlSoup) {
            items = getBowl(items);
        }

        if (!items.isEmpty()) {
            user.addItem(items.get(0), items.get(1));
            Toast.makeText(stage, "Item successfully added", 2000, 500, 500);
            stage.close();
        }
    }

    private List<String> getCup(List<String> items) {
        boolean inStock;
        String cup = "Cup of Soup";

        inStock = db.checkInventory(cup);

        if (inStock) {
            items = db.getItem(cup);
        } else {
            notInStock();
        }

        return items;
    }

    private List<String> getBowl(List<String> items) {
        boolean inStock;
        String bowl = "Bowl of Soup";

        inStock = db.checkInventory(bowl);

        if (inStock) {
            items = db.getItem(bowl);
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
