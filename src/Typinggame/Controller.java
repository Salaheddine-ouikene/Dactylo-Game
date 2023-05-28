package Typinggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    private Label TimeText;
    @FXML
    private Label UserNameLabel;

    @FXML
    private Text finalScore;
    @FXML
    private Text WordTemp;
    @FXML
    private Text wrong;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File newFile = new File("username.txt");
        if (newFile.length() != 0) {
            Scanner reader = null;
            try {
                reader = new Scanner(newFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String data = reader.nextLine();
            UserNameLabel.setText("Welcome, " + data);

        }

        ;
        Locale locale = new Locale("en");
        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        String strDay = formatter.format(new Date());

        TimeText.setText("Today is " + strDay);

        // we need to display data
        int[] data = FileLoader.sumUpNumbers("src/data");
        String temp0 = String.valueOf(data[0]);
        int temp1 = data[1];
        int temp3 = data[3];
        String temp2 = String.valueOf(data[2]);
        String TextfinalwordTemp = String.valueOf(Math.round(temp1 * 1.0 / temp3));
        finalScore.setText(temp0);
        WordTemp.setText(TextfinalwordTemp);
        wrong.setText(temp2);
    }

    public void PlayNow(ActionEvent ddd) throws IOException {
        Main m = new Main();

        File newFile = new File("username.txt");
        if (newFile.length() != 0) {
            m.changeScene("game.fxml");

        } else {
            try {
                m.changeScene("popup.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
