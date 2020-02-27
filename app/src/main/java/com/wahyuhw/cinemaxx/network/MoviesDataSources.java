package com.wahyuhw.cinemaxx.network;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.wahyuhw.cinemaxx.entity.MoviesResponses;

import static com.wahyuhw.cinemaxx.BuildConfig.BASE_URL_TMDB_MOVIE;
import static com.wahyuhw.cinemaxx.BuildConfig.TMDB_API_KEY;

public class MoviesDataSources {
    public static final String URL_MOVIE = BASE_URL_TMDB_MOVIE + "popular?api_key=" +TMDB_API_KEY+ "&language=en-US&page=1";
    public static final String URL_MOVIE_NOW_PLAYING = BASE_URL_TMDB_MOVIE + "now_playing?api_key=" +TMDB_API_KEY+ "&language=en-US&page=1";

    public void getMovies(String movieEndpoint, final MovieDataSourcesCallback callback) {
        AndroidNetworking.get(movieEndpoint)
                .addPathParameter("apiKey", TMDB_API_KEY)
                .setTag(MoviesDataSources.class)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsObject(MoviesResponses.class, new ParsedRequestListener<MoviesResponses>() {
                    @Override
                    public void onResponse(MoviesResponses response) {
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
