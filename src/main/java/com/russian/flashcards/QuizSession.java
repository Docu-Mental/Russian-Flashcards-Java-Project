package com.russian.flashcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class QuizSession {

    // Private fields to hold the filtered word list, number of words, direction and word type
    private ArrayList<Word> words;
    private int numWords;
    private int direction;
    private String wordType;

    // Private fields to track the score and percentage
    private int score;
    private int percentage;

    // Private Scanner field for reading user answers
    private Scanner myScanner = new Scanner(System.in);

    // Constructor that accepts the filtered word list, number of words, direction and word type
    public QuizSession(ArrayList<Word> words, int numWords, int direction, String wordType) {
        this.words = words;
        this.numWords = numWords;
        this.direction = direction;
        this.wordType = wordType;
    }

    public void start() {

        // Shuffle the word list and take the first numWords items
        if (numWords > words.size()) {
            numWords = words.size();
            System.out.println("Only " + numWords + " words available. Adjusting quiz size.");
        }
        Collections.shuffle(words);
        List<Word> selectedWords = words.subList(0, numWords);

        // Loop through selected words
        for (int i = 0; i < selectedWords.size(); i++) {
            Word word = selectedWords.get(i);

            // Display progress
            System.out.println("\nWord " + (i + 1) + " of " + numWords);
            System.out.println("--------------");

            // Display question based on direction
            if (direction == 1) {
                System.out.println(word.getRussian());
            } else {
                System.out.println(word.getEnglish());
            }

            // Read user's answer
            System.out.print("Your answer: ");
            String answer = myScanner.nextLine();

            // Check answer
            String correctAnswer = direction == 1 ? word.getEnglish() : word.getRussian();
            if (answer.equalsIgnoreCase(correctAnswer)) {
                System.out.println("✓ Correct!");
                score++;
            } else {
                System.out.println("✗ Wrong. The answer was: " + correctAnswer);
            }
        }

        // Calculate and display final score
        percentage = (score * 100) / numWords;
        System.out.println("\nQuiz complete!");
        System.out.println("Score: " + score + "/" + numWords + " (" + percentage + "%)");

        checkHighScore();
    }

    // Check if the score qualifies as a high score and save it
    private void checkHighScore() {
        boolean isHighScore = HighScores.addScore(percentage, direction, wordType);
        if (isHighScore) {
            System.out.println("New high score! 🎉");
        }
    }
}