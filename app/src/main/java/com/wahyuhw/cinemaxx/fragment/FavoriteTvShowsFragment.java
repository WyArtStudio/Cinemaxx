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
import com.wahyuhw.cinemaxx.adapter.FavoriteTvShowsAdapter;
import com.wahyuhw.cinemaxx.base.BaseFragmentTvShows;
import com.wahyuhw.cinemaxx.db.FavoriteHelper;
import com.wahyuhw.cinemaxx.entity.TvShowsItem;

import java.util.ArrayList;

public class FavoriteTvShowsFragment extends BaseFragmentTvShows {
    private ArrayList<TvShowsItem> tv = new ArrayList<>();
    private FavoriteTvShowsAdapter tvAdapter;
    private FavoriteHelper favoriteHelper;
    private ProgressBar progressBar;
    private Bundle saveState;

    public FavoriteTvShowsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvAdapter = new FavoriteTvShowsAdapter();

        RecyclerView movieList = view.findViewById(R.id.rv_category);
        movieList.setHasFixedSize(true);
        movieList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        movieList.setAdapter(tvAdapter);

        progressBar = view.findViewById(R.id.progressBar);
    }

    @Override
    public void onStart() {
        progressBar.setVisibility(View.VISIBLE);
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();

        if (saveState == null) {
            tv.clear();
            tv.addAll(favoriteHelper.getAllFavorites2());
            if (tv != null) {
                tvAdapter.setListTvShow(tv);
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.empty_data), Toast.LENGTH_SHORT).show();
            }
        } else {
            ArrayList<TvShowsItem> list = saveState.getParcelableArrayList(KEY_TV);
            if (list != null) {
                tvAdapter.setListTvShow(list);
            }
        }
        super.onStart();
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_TV, tvAdapter.getListTvShow());
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}
