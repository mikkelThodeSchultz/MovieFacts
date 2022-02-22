package com.example.moviefacts.controllers;

import com.example.moviefacts.models.Movie;
import com.example.moviefacts.services.FileReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MainController {
    FileReader fr = new FileReader();

    @GetMapping("/")
    public String welcomeMessage() {
        return "Welcome to the Movie Fact site where you can get all the " +
                "information you want about all the best movies out there!";

    }

    @GetMapping("/getFirst")
    public String getFirst() {
        return fr.getFirstMovie();
    }


    @GetMapping("/getRandom")
    public String getRandom() {
        return fr.getRandom();
    }

    @GetMapping("/getTenSortByPopularity")
    public String getTenSortByPopularity(){

        return fr.getTenSortByPopularity();
    }

    @GetMapping("/howManyWonAnAward")
    public String getHowManyWonAnAward(){
        return fr.getHowManyWonAnAward();
    }
    //TODO virker slet ikke
    @GetMapping("/filter")
    public String filter(@RequestParam String x, int n){
        return fr.filter(x, n);
    }
}