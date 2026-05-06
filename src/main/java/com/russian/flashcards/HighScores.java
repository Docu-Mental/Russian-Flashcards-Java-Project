package com.russian.flashcards;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class HighScores {

    // Path to the high scores file
    private static final String FILE_PATH = System.getProperty("user.home") + File.separator +
            ".russianflashcards" + File.separator + "highscores.txt";

    private static final int MAX_SCORES = 10;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    // Static nested class representing a single high score entry
    static class HighScore {
        int percentage;
        String direction;
        String wordType;
        String dateTime;

        public HighScore(int percentage, String direction, String wordType, String dateTime) {
            this.percentage = percentage;
            this.direction = direction;
            this.wordType = wordType;
            this.dateTime = dateTime;
        }
    }

    // Read high scores from file
    public static ArrayList<HighScore> readHighScores() {
        ArrayList<HighScore> scores = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return scores;
        }

        try (Scanner myScanner = new Scanner(file, "UTF-8")) {
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                String[] values = line.split(",");
                if (values.length == 4) {
                    scores.add(new HighScore(
                            Integer.parseInt(values[0]),
                            values[1],
                            values[2],
                            values[3]
                    ));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read high scores file.");
        }

        return scores;
    }

    // Add a new score and save top 10
    public static boolean addScore(int percentage, int direction, String wordType) {
        ArrayList<HighScore> scores = readHighScores();

        String directionStr = direction == 1 ? "Russian → English" : "English → Russian";
        String dateTime = LocalDateTime.now().format(FORMATTER);

        HighScore newScore = new HighScore(percentage, directionStr, wordType, dateTime);
        scores.add(newScore);

        // Sort by percentage descending
        scores.sort(Comparator.comparingInt((HighScore s) -> s.percentage).reversed());

        // Keep only top 10
        if (scores.size() > MAX_SCORES) {
            scores = new ArrayList<>(scores.subList(0, MAX_SCORES));
        }

        // Check if new score made it into top 10
        boolean isHighScore = scores.contains(newScore);

        // Create file if it doesn't exist
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Could not create high scores file.");
            return false;
        }

        // Write back to file
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, false))) {
            for (HighScore score : scores) {
                writer.println(score.percentage + "," + score.direction + "," + score.wordType + "," + score.dateTime);
            }
        } catch (IOException e) {
            System.out.println("Could not save high score.");
        }

        return isHighScore;
    }

    // Display top 10 high scores
    public static void display() {
        ArrayList<HighScore> scores = readHighScores();

        System.out.println("\nHigh Scores");
        System.out.println("-----------------------------------");

        if (scores.isEmpty()) {
            System.out.println("No high scores yet!");
            return;
        }

        for (int i = 0; i < scores.size(); i++) {
            HighScore score = scores.get(i);
            System.out.println((i + 1) + ". " + score.percentage + "% | " +
                    score.direction + " | " + score.wordType + " | " + score.dateTime);
        }
    }
}