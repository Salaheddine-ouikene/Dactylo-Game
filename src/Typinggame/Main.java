package Typinggame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


import java.io.IOException;

public class Main extends Application {

    private static Stage Stages;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stages = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("MainGameWindow.fxml"));
        primaryStage.setTitle("Type Practice");
        //primaryStage.getIcons().add(new Image("images/game.png"));
        primaryStage.setScene(new Scene(root, 800, 550));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Stages.getScene().setRoot(pane);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
