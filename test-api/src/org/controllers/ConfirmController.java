package org.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.User;
import org.utils.Utils;

import java.util.Random;

public class ConfirmController extends AbstractController {

    private Stage stage;

    @FXML
    Text orderNum, time;

    public ConfirmController(Stage stage, String fxml) {
        Utils.loadFXML(this, this, fxml);
        this.stage = stage;
        generateNumberAndTime(orderNum, time);
    }

    private void generateNumberAndTime(Text orderNum, Text time) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(999999999);
        int randomTime = rand.nextInt((55 - 5) + 1) + 5;
        orderNum.setText("# " + randomNumber);
        time.setText(randomTime + " Minutes");



    }
}
