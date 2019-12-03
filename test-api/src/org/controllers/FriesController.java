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

public class FriesController extends AbstractController{

    private Stage stage;
    private User user;
    private Database db = new Database();

    public FriesController(Stage stage, String fxml, User user) {
        Utils.loadFXML(this, this, fxml);
        this.user = user;
        this.stage = stage;
        ToggleGroup toggleGroup = new ToggleGroup();
        small.setToggleGroup(toggleGroup);
        large.setToggleGroup(toggleGroup);
    }

    @FXML
    private RadioButton small, large;

    @FXML
    private void addToCart(ActionEvent event) {
        boolean smallFry = small.isSelected();
        boolean largeFry = large.isSelected();
        List<String> items = new ArrayList<>();

        if (smallFry) {
            items = getSmallFry(items);
        } else if (largeFry) {
            items = getLargeFry(items);
        }

        if (!items.isEmpty()) {
            user.addItem(items.get(0), items.get(1));
            stage.close();
        }
    }

    private List<String> getSmallFry(List<String> items) {
        boolean inStock;
        String smallFry = "Small Fry";

        inStock = db.checkInventory(smallFry);

        if (inStock) {
            items = db.getItem(smallFry);
        } else {
            notInStock();
        }

        return items;
    }

    private List<String> getLargeFry(List<String> items) {
        boolean inStock;
        String largeFry = "Large Fry";

        inStock = db.checkInventory(largeFry);

        if (inStock) {
            items = db.getItem(largeFry);
        } else {
            notInStock();
        }
        return items;
    }

    private void notInStock() {
        Stage error = new Stage();
        error.setTitle("Item not in stock");
        ErrorController controller = new ErrorController("/org/fxmls/fxmls/notInStock.fxml");
        error.setScene(new Scene(controller));
        error.show();
    }

}
