package com.russian.flashcards;

import java.io.File;                  // Import the File class
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class WordLoader {

    static ArrayList<Word> loadWords() {

        File myFileObj = new File("words.csv");
        System.out.println(" ");
        if (!myFileObj.exists()) {
            System.out.println("words.csv is missing. It should be in the same folder as the JAR file.");
            return null;
        }

        else {
            System.out.println("Loading words.csv ...");
            ArrayList<Word> wordArrayList = new ArrayList<Word>();

            try (Scanner myReader = new Scanner(myFileObj, "UTF-8")) {
                myReader.nextLine();

                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    String[] values = line.split(",");
                    Word word = new Word(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4], values[5]);
                    wordArrayList.add(word);
                }
                return wordArrayList;
            }
            catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return null;
        }
    }
}