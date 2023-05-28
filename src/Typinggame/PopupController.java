package Typinggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class PopupController {

    @FXML
    private TextField username;

    public void submit(ActionEvent ex) throws IOException {
        String name = username.getText();
        FileWriter File = new FileWriter("username.txt");
        File.write(name);
        File.close();

        Main m = (new Main());
        m.changeScene("MainGameWindow.fxml");


    }

}
