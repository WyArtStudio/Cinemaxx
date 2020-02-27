package com.wahyuhw.cinemaxx.network;


import com.wahyuhw.cinemaxx.entity.TvShowsResponses;

public interface TvDataSourcesCallback {

    void onSuccess(TvShowsResponses tvShowsResponses);

    void onFailed(String error);
}
