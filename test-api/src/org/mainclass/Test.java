package org.mainclass;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test extends Application {
    public static void main(String[] args) {
        Database database = new Database();

        database.deleteUserNameAndPassword("SHERRY", "password");

        database.setUserNameAndPassword("SHERRY", "password");
        if(database.checkUsernameAndPassword("SHERRY", "password")) {
            System.out.println("This test worked!");
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Title of Window");

        Button button = new Button("Click me");

        StackPane layout = new StackPane();

        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 250);

        stage.setScene(scene);
        stage.show();
    }
}
