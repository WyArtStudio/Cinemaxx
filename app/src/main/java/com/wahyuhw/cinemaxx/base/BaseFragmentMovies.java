package com.wahyuhw.cinemaxx.base;

import androidx.fragment.app.Fragment;

import com.wahyuhw.cinemaxx.network.MoviesDataSources;

public class BaseFragmentMovies extends Fragment {
    public static final String KEY_MOVIES = "movies";

    public MoviesDataSources getMoviesDataSources() {
        return new MoviesDataSources();
    }
}
