package com.example.moviefacts.models;

public class Movie {

    private String title;
    private int length;
    private String genre;
    private boolean wonAnAward;
    private int popularity;
    private String booleanAsString;

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPopularity() {
        return popularity;
    }

    public boolean isWonAnAward() {
        return wonAnAward;
    }

    public Movie(String title, int length, String genre, boolean wonAnAward, int popularity) {
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.wonAnAward = wonAnAward;
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        if (wonAnAward){
            booleanAsString = "Yes";
        } else booleanAsString = "No";
        return "Title: " + title + "<br>Length: " + length + "\nGenre: " + genre + "\nAward winning: " + booleanAsString + "\nPopularity score: " + popularity;
    }
}
