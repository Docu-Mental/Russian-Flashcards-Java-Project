package com.russian.flashcards;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    // Private field to hold the list of Word objects
    private ArrayList<Word> words;

    // Private Scanner field for reading user input
    private Scanner myScanner = new Scanner(System.in);

    // Constructor that accepts an ArrayList of Word objects as a parameter
    // and assigns it to the instance field
    public Menu(ArrayList<Word> words) {
        this.words = words;
    }

    // Public instance method that displays the welcome screen
    // and handles user navigation
    public void show() {

        String welcome = """
                
                Welcome to Russian Flashcard Quiz
                -----------------------------------
                1. Start Quiz (default)
                2. View High Scores
                3. Quit""";

        System.out.println(welcome);
        System.out.print("Enter your choice: ");

        String choice = myScanner.nextLine();

        // Apply default if user just presses Enter
        if (choice.isEmpty()) {
            choice = "1";
        }

        switch (choice) {
            case "1" -> startQuiz();
            case "2" -> viewHighScores();
            case "3" -> System.out.println("Goodbye!");
            default -> {
                System.out.println("Invalid choice. Please try again.");
                show();
            }
        }
    }

    // Helper method to read a valid integer input from the user
    // Returns the default value if the user just presses Enter
    private int readIntInput(String prompt, int defaultValue, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = myScanner.nextLine();

            if (input.isEmpty()) {
                return defaultValue;
            }

            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void startQuiz() {

        // Ask how many words with validation
        System.out.println(" ");
        int numWords = readIntInput("How many words? (max 1000): ", 10, 1, 1000);

        // Ask quiz direction with validation
        System.out.println(" ");
        System.out.println("Quiz direction?");
        System.out.println("1. Russian → English (default)");
        System.out.println("2. English → Russian");
        int direction = readIntInput("Enter your choice: ", 1, 1, 2);

        // Ask word type with validation
        System.out.println("");
        System.out.println("Word type?");
        System.out.println("1. All words (default)");
        System.out.println("2. Nouns");
        System.out.println("3. Verbs");
        System.out.println("4. Adjectives");
        System.out.println("5. Adverbs");
        System.out.println("6. Prepositions");
        System.out.println("7. Conjunctions");
        int wordType = readIntInput("Enter your choice: ", 1, 1, 7);

        // Map word type number to string
        String[] types = {"", "All", "Noun", "Verb", "Adjective", "Adverb", "Preposition", "Conjunction"};
        String wordTypeStr = types[wordType];

        // Filter words by type
        ArrayList<Word> filteredWords = new ArrayList<>(words);
        if (wordType != 1) {
            filteredWords.removeIf(w -> !w.getType().equalsIgnoreCase(wordTypeStr));
        }

        // Pass to QuizSession
        QuizSession session = new QuizSession(filteredWords, numWords, direction, wordTypeStr);
        session.start();
        show();
    }

    // Display high scores and return to main menu
    private void viewHighScores() {
        HighScores.display();
        show();
    }
}