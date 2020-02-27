package com.wahyuhw.cinemaxx.base;

import androidx.fragment.app.Fragment;

import com.wahyuhw.cinemaxx.network.TvShowsDataSources;

public class BaseFragmentTvShows extends Fragment {
    public static final String KEY_TV = "tv";

    public TvShowsDataSources getTvShowsDataSources() {
        return new TvShowsDataSources();
    }
}
