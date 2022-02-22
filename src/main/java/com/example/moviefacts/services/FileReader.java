package com.example.moviefacts.services;

import com.example.moviefacts.models.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {

    private File file = new File("src/main/java/com/example/moviefacts/repositories/imdb-data.csv");
    private Scanner sc;
    private Random rn;
    private ArrayList<String> listOfMovies = new ArrayList<>();


    public String getFirstMovie() {
        String firstMovie = "";
        String metaData;
        {
            try {
                sc = new Scanner(file);
                metaData = sc.nextLine();
                firstMovie = sc.nextLine();
            } catch (FileNotFoundException e) {
                System.out.println("File could not be found");
                e.printStackTrace();
            }
        }

        return getTitle(firstMovie);
    }

    public String getRandom() {
        rn = new Random();
        String randomMovie = "";
        addMoviesToArraylist();
        randomMovie = listOfMovies.get(rn.nextInt(listOfMovies.size()));
        listOfMovies.clear();
        return getTitle(randomMovie);
    }
    // TODO kald denne metode i den andre metoder for at mindske koden.
    private void addMoviesToArraylist() {
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                listOfMovies.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        listOfMovies.remove(0);
    }

    public String getTitle(String movie) {
        int first = movie.indexOf(";");
        int second = movie.indexOf(";", first + 1);
        int third = movie.indexOf(";", second + 1);
        movie = movie.substring(second, third);
        movie = movie.substring(1);
        return movie;
    }

    public String getGenre(String movie) {
        int first = movie.indexOf(";");
        int second = movie.indexOf(";", first + 1);
        int third = movie.indexOf(";", second + 1);
        int fourth = movie.indexOf(";", third + 1);
        movie = movie.substring(third, fourth);
        movie = movie.substring(1);
        return movie;
    }

    public int getLength(String movie) {
        int first = movie.indexOf(";");
        int second = movie.indexOf(";", first + 1);
        movie = movie.substring(first, second);
        movie = movie.substring(1);
        return Integer.parseInt(movie);
    }

    public int getPopularity(String movie) {
        int first = movie.indexOf(";");
        int second = movie.indexOf(";", first + 1);
        int third = movie.indexOf(";", second + 1);
        int fourth = movie.indexOf(";", third + 1);
        int fifth = movie.indexOf(";", fourth + 1);
        movie = movie.substring(fourth, fifth);
        movie = movie.substring(1);
        return Integer.parseInt(movie);
    }

    public boolean getWonAnAward(String movie) {
        return movie.endsWith("Yes");
    }

    public String getTenSortByPopularity() {
        addMoviesToArraylist();
        ArrayList<Movie> listOfMovieObjects = new ArrayList<>();
        rn = new Random();
        for (int i = 0; i < 9; i++) {
            String randomMovie = listOfMovies.get(rn.nextInt(listOfMovies.size()));
            String title = getTitle(randomMovie);
            int length = getLength(randomMovie);
            String genre = getGenre(randomMovie);
            boolean wonAnAward = getWonAnAward(randomMovie);
            int popularity = getPopularity(randomMovie);
            Movie movie = new Movie(title, length, genre, wonAnAward, popularity);
            listOfMovieObjects.add(movie);
        }
        listOfMovies.clear();
        Collections.sort(listOfMovieObjects, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.getPopularity() - o1.getPopularity();
            }
        });

        // TODO Jeg aner ikke hvordan jeg får den her streng til at se godt ud når den ryger ud på browseren.
        return listOfMovieObjects.toString();

    }


    public String getHowManyWonAnAward() {
        addMoviesToArraylist();
        ArrayList<Movie> listOfMovieObjects = new ArrayList<>();

        for (int i = 0; i < listOfMovies.size(); i++) {
            String randomMovie = listOfMovies.get(i);
            String title = getTitle(randomMovie);
            int length = getLength(randomMovie);
            String genre = getGenre(randomMovie);
            boolean wonAnAward = getWonAnAward(randomMovie);
            int popularity = getPopularity(randomMovie);
            Movie movie = new Movie(title, length, genre, wonAnAward, popularity);
            if (movie.isWonAnAward()) {
                listOfMovieObjects.add(movie);
            }
        }
        listOfMovies.clear();

        // TODO Jeg aner ikke hvordan jeg får den her streng til at se godt ud når den ryger ud på browseren.
        return listOfMovieObjects.toString();

    }

    public ArrayList<Movie> stringsAsListOfObjects() {
        ArrayList<Movie> listOfMovieObjects = new ArrayList<>();

        for (int i = 0; i < listOfMovies.size(); i++) {
            String randomMovie = listOfMovies.get(i);
            String title = getTitle(randomMovie);
            int length = getLength(randomMovie);
            String genre = getGenre(randomMovie);
            boolean wonAnAward = getWonAnAward(randomMovie);
            int popularity = getPopularity(randomMovie);
            Movie movie = new Movie(title, length, genre, wonAnAward, popularity);
            listOfMovieObjects.add(movie);

        }
        return listOfMovieObjects;
    }
    // TODO virker slet ikke
    public String filter(String x, int n) {
    addMoviesToArraylist();
    stringsAsListOfObjects();
    String returnString = "";

        for (int i = 0; i < stringsAsListOfObjects().size(); i++) {
            if (stringsAsListOfObjects().get(i).getTitle().contains(x)){
                returnString = returnString + stringsAsListOfObjects().get(i).getTitle().replaceAll("x", "");

            }
        }
        return returnString;
    }
}

