package Typinggame;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedReader;

public class FileLoader {

    public static File getLastModified(String dir) {
        File directory = new File(dir);
        File[] files = directory.listFiles(File::isFile);

        File chosenFile = null;

        if (files != null) {
            for (File file : files) {

                chosenFile = file;

            }
        }

        return chosenFile;
    }

    public static int[] sumUpNumbers(String dir) {
        File directory = new File(dir);
        File[] files = directory.listFiles(File::isFile);
        int[] returnArray = new int[4];
        String[] arr;
        int counter = 0;
        if (files != null) {
            for (File file : files) {
                counter++;
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String st;
                    while ((st = br.readLine()) != null) {
                        arr = st.split(";");
                        returnArray[0] += Integer.parseInt(arr[0]);
                        returnArray[1] += Integer.parseInt(arr[1]);
                        returnArray[2] += Integer.parseInt(arr[2]);
                    }

                } catch (IOException ignored) {
                }
            }
        }
        returnArray[3] = counter;
        return returnArray;
    }

}
