package com.wahyuhw.cinemaxx.network;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.wahyuhw.cinemaxx.entity.TvShowsResponses;

import static com.wahyuhw.cinemaxx.BuildConfig.BASE_URL_TMDB_TV;
import static com.wahyuhw.cinemaxx.BuildConfig.TMDB_API_KEY;

public class TvShowsDataSources {
    public static final String URL_TV = BASE_URL_TMDB_TV + "popular?api_key=" +TMDB_API_KEY+ "&language=en-US&page=1";
    public static final String URL_TV_AIRING_TODAY = BASE_URL_TMDB_TV + "airing_today?api_key=" +TMDB_API_KEY+ "&language=en-US&page=1";

    public void getTv(String tvEndpoint, final TvDataSourcesCallback callback) {
        AndroidNetworking.get(tvEndpoint)
                .addPathParameter("apiKey", TMDB_API_KEY)
                .setTag(TvShowsDataSources.class)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsObject(TvShowsResponses.class, new ParsedRequestListener<TvShowsResponses>() {
                    @Override
                    public void onResponse(TvShowsResponses response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ERROR", "onError: ", anError);
                        callback.onFailed("Terjadi kesalahan saat menghubungi server");
                    }
                });
    }
}
