package com.russian.flashcards;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        System.setErr(new java.io.PrintStream(System.err, true, java.nio.charset.StandardCharsets.UTF_8));

        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Word> words = WordLoader.loadWords();

        if (words == null) {
            System.out.println("Exiting program.");
            return;
        }

        Menu menu = new Menu(words);
        menu.show();
    }
}