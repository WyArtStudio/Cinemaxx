package com.wahyuhw.cinemaxx.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesResponses {
    @SerializedName("results")
    private ArrayList<MoviesItem> results;

    public ArrayList<MoviesItem> getResults(){
        return results;
    }
}
