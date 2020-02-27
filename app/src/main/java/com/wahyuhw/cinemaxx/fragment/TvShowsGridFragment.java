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
import com.wahyuhw.cinemaxx.adapter.GridTvShowsAdapter;
import com.wahyuhw.cinemaxx.base.BaseFragmentTvShows;
import com.wahyuhw.cinemaxx.entity.TvShowsItem;
import com.wahyuhw.cinemaxx.entity.TvShowsResponses;
import com.wahyuhw.cinemaxx.network.TvDataSourcesCallback;
import com.wahyuhw.cinemaxx.network.TvShowsDataSources;

import java.util.ArrayList;

public class TvShowsGridFragment extends BaseFragmentTvShows implements TvDataSourcesCallback {
    private ArrayList<TvShowsItem> tv = new ArrayList<>();
    private GridTvShowsAdapter tvAdapter;
    private ProgressBar progressBar;

    public TvShowsGridFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvAdapter = new GridTvShowsAdapter(tv);

        RecyclerView movieList = view.findViewById(R.id.rv_category);
        movieList.setHasFixedSize(true);
        movieList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        movieList.setAdapter(tvAdapter);
        progressBar = view.findViewById(R.id.progressBar);


        if (savedInstanceState == null) {
            showLoading(true);
            getTvShowsDataSources().getTv(TvShowsDataSources.URL_TV, this);
        } else {
            tv = savedInstanceState.getParcelableArrayList(KEY_TV);
            tvAdapter.setListTv(tv);
        }
    }
    @Override
    public void onSuccess(TvShowsResponses tvResponse) {
        tv = tvResponse.getResults();
        tvAdapter.setListTv(tv);
        showLoading(false);
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(getContext() , error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_TV, tv);
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
