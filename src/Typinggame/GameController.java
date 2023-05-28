package Typinggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GameController implements Initializable {

    private int timer = 6000;
    private int NextLevel = 1;
    private int life = 3;
    private int max = 100;
    private double coeff = Math.pow(0.9, 1);
    private double End = 3.9 * coeff;
    private double startTime = 0;
    private double coeff2 = Math.pow(0.9, 100);
    private double speed = 3.9 * coeff2;

    private int CompteurOfWords = 0;
    private int first = 1;

    private File dataToFile;

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    @FXML
    public Text seconds, wordMinute, accuracy, InitialWords, NextWords;

    @FXML
    Button AnotherOne;

    @FXML
    public TextField userWord;
    @FXML
            public ImageView wrong,correct;
    ArrayList<String> words = new ArrayList<>();

    // add words to array list
    public void ListWordsFromFiles() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("wordsList"));
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toMainMenu(ActionEvent ex) throws IOException {
        Main m = new Main();
        m.changeScene("MainGameWindow.fxml");
    }

    private int InPocket = 1;
    private boolean AnotherLife = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AnotherOne.setVisible(false);
        seconds.setText("6000");
        AnotherOne.setDisable(true);

        ListWordsFromFiles();
        Collections.shuffle(words);
        InitialWords.setText(words.get(CompteurOfWords));

        NextWords.setText("NextLevel : " + NextLevel + "+");
        CompteurOfWords++;

        SimpleDateFormat formatter ;
        formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

        Date date=new Date();
        String nameoffile = formatter.format(date).strip();
         dataToFile = new File("src/" + nameoffile + ".txt");
    }
    Runnable r = new Runnable() {
        @Override
        public void run() {
            startTime = startTime + speed;
            System.out.println(startTime);
            if (startTime == End) {
                System.out.println("5 SEC passed");
                CompteurOfWords++;
                InitialWords.setText(words.get(CompteurOfWords));

            }

            if (timer > -1 || life != -1) {

                seconds.setText(String.valueOf(timer));
                wordMinute.setText(String.valueOf(life));
                timer -= 1;
            }

            else {
                if (timer == -1 || life == -1) {
                    userWord.setDisable(true);
                    userWord.setText("Game over");

                    try {
                        FileWriter myWriter = new FileWriter(dataToFile);
                        myWriter.write(countAll + ";");
                        myWriter.write(counter + ";");
                        myWriter.write(String.valueOf(countAll - counter));
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (timer == -4) {
                    AnotherOne.setVisible(true);
                    AnotherOne.setDisable(false);
                    executor.shutdown();
                }

                timer -= 1;
            }
        }
    };

    Runnable fadeCorrect = new Runnable() {
        @Override
        public void run() {
            correct.setOpacity(0);
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(50);
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(100);
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            correct.setOpacity(0);

        }
    };

    Runnable fadeWrong = new Runnable() {
        @Override
        public void run() {
            wrong.setOpacity(0);
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(50);
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(100);
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wrong.setOpacity(0);
        }
    };

    private int countAll = 0;
    private int counter = 0;

    public void startGame(KeyEvent ke) {

        // only gets called once
        if (first == 1) {
            first = 0;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
        }

        if (ke.getCode() == KeyCode.ENTER) {

            String s = userWord.getText();
            String real = InitialWords.getText();
            countAll++;

            if (s.equals(real)) {
                if (AnotherLife)
                    life = life + 1;
                counter++;
                if (counter % 5 == 0)
                    NextLevel = NextLevel + 1;
                speed = 3.9 * Math.pow(0.9, NextLevel);
                NextWords.setText("NextLevel : " + NextLevel);

                wordMinute.setText(String.valueOf(life));
                CompteurOfWords++;
                Thread t = new Thread(fadeCorrect);
                t.start();

            } else {
                life = life - 1;
                if (life != -1) {
                    Thread t = new Thread(fadeWrong);
                    t.start();
                } else
                    timer = -1;
            }
            userWord.setText("");
            accuracy.setText(String.valueOf(Math.round((counter * 1.0 / countAll) * 100)));
            InitialWords.setText(words.get(CompteurOfWords));
            CompteurOfWords++;
            int randomNum = ThreadLocalRandom.current().nextInt(0, 4);

            if (randomNum == InPocket) {
                InitialWords.setFill(Color.BLUE);
                AnotherLife = true;

            } else {
                InitialWords.setFill(Color.GREEN);
                AnotherLife = false;

            }

            System.out.println(randomNum);
        }

    }
}
