package com.wahyuhw.cinemaxx.network;

import com.wahyuhw.cinemaxx.entity.MoviesResponses;

public interface MovieDataSourcesCallback {

    void onSuccess(MoviesResponses moviesResponses);

    void onFailed(String error);
}
