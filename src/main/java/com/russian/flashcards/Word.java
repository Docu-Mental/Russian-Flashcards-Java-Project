package com.russian.flashcards;

public class Word {

    private int id;
    private String russian;
    private String type;
    private String gender;
    private String aspect;
    private String english;

    public Word(int id, String russian, String type, String gender, String aspect, String english) {
        this.id = id;
        this.russian = russian;
        this.type = type;
        this.gender = gender;
        this.aspect = aspect;
        this.english = english;
    }

    public int getId() {
        return id;
    }

    public String getRussian() {
        return russian;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public String getAspect() {
        return aspect;
    }

    public String getEnglish() {
        return english;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", russian='" + russian + '\'' +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                ", aspect='" + aspect + '\'' +
                ", english='" + english + '\'' +
                '}';
    }
}
