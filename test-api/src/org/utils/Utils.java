package org.utils;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Utils {

    public static void loadFXML(Object controller, Object root, String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(Utils.class.getResource(fxml));
        fxmlLoader.setRoot(root);
        fxmlLoader.setController(controller);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
