package com.wahyuhw.cinemaxx.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvShowsResponses {
    @SerializedName("results")
    private ArrayList<TvShowsItem> results;

    public ArrayList<TvShowsItem> getResults(){
        return results;
    }
}
