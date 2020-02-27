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
import com.wahyuhw.cinemaxx.adapter.FavoriteMoviesAdapter;
import com.wahyuhw.cinemaxx.base.BaseFragmentMovies;
import com.wahyuhw.cinemaxx.db.FavoriteHelper;
import com.wahyuhw.cinemaxx.entity.MoviesItem;

import java.util.ArrayList;

public class FavoriteMoviesFragment extends BaseFragmentMovies {
    private ArrayList<MoviesItem> movies = new ArrayList<>();
    private FavoriteMoviesAdapter moviesAdapter;
    private FavoriteHelper favoriteHelper;
    private ProgressBar progressBar;
    private Bundle saveState;

    public FavoriteMoviesFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesAdapter = new FavoriteMoviesAdapter();

        RecyclerView movieList = view.findViewById(R.id.rv_category);
        movieList.setHasFixedSize(true);
        movieList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        movieList.setAdapter(moviesAdapter);

        progressBar = view.findViewById(R.id.progressBar);
    }

    @Override
    public void onStart() {
        progressBar.setVisibility(View.VISIBLE);
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();

        if (saveState == null) {
            movies.clear();
            movies.addAll(favoriteHelper.getAllFavorites());
            if (movies != null) {
                moviesAdapter.setListMovies(movies);
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.empty_data), Toast.LENGTH_SHORT).show();
            }
        } else {
            ArrayList<MoviesItem> list = saveState.getParcelableArrayList(KEY_MOVIES);
            if (list != null) {
                moviesAdapter.setListMovies(list);
            }
        }
        super.onStart();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES, moviesAdapter.getListMovies());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }

}
