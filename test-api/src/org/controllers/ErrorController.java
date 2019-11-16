package org.controllers;


import javafx.scene.layout.Pane;
import org.utils.Utils;

public class ErrorController extends Pane {

    public ErrorController(String fxml) {
        Utils.loadFXML(this, this, fxml);
    }
}
