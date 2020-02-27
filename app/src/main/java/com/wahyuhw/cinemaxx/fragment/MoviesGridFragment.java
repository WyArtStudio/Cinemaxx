package com.wahyuhw.cinemaxx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wahyuhw.cinemaxx.R;
import com.wahyuhw.cinemaxx.adapter.GridMoviesAdapter;
import com.wahyuhw.cinemaxx.base.BaseFragmentMovies;
import com.wahyuhw.cinemaxx.entity.MoviesItem;
import com.wahyuhw.cinemaxx.entity.MoviesResponses;
import com.wahyuhw.cinemaxx.network.MovieDataSourcesCallback;
import com.wahyuhw.cinemaxx.network.MoviesDataSources;

import java.util.ArrayList;

public class MoviesGridFragment extends BaseFragmentMovies implements MovieDataSourcesCallback {
    private ArrayList<MoviesItem> movies = new ArrayList<>();
    private GridMoviesAdapter moviesAdapter;
    private ProgressBar progressBar;

    public MoviesGridFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesAdapter = new GridMoviesAdapter(movies);

        RecyclerView movieList = view.findViewById(R.id.rv_category);
        movieList.setHasFixedSize(true);
        movieList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        movieList.setAdapter(moviesAdapter);
        progressBar = view.findViewById(R.id.progressBar);


        if (savedInstanceState == null) {
            showLoading(true);
            getMoviesDataSources().getMovies(MoviesDataSources.URL_MOVIE, this);
        } else {
            movies = savedInstanceState.getParcelableArrayList(KEY_MOVIES);
            moviesAdapter.setListMovies(movies);
        }
    }
    @Override
    public void onSuccess(MoviesResponses movieResponse) {
        movies = movieResponse.getResults();
        moviesAdapter.setListMovies(movies);
        showLoading(false);
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(getContext() , error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES, movies);
        super.onSaveInstanceState(outState);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
