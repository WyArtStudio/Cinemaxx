package com.wahyuhw.cinemaxx.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;
import com.wahyuhw.cinemaxx.R;
import com.wahyuhw.cinemaxx.db.FavoriteHelper;
import com.wahyuhw.cinemaxx.entity.TvShowsItem;

import java.util.Objects;
public class TvShowsDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOWS = "extra_tv_shows";
    private FavoriteHelper favoriteHelper;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows_detail);
        final TextView tvTitle = findViewById(R.id.tv_item_ts_title);
        final TextView tvScore = findViewById(R.id.tv_item_ts_userScore);
        final TextView tvOverview = findViewById(R.id.tv_item_ts_overview);
        final TextView tvLanguage = findViewById(R.id.tv_item_ts_language);
        final TextView tvVote_Count = findViewById(R.id.tv_item_ts_runtime);
        final TextView tvRelease = findViewById(R.id.tv_item_ts_dateReleased);
        final ImageView imagePoster = findViewById(R.id.img_item_ts_photo);
        progressBar = findViewById(R.id.progress_movies_detail);
        progressBar.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) { }

                handler.post(new Runnable() {
                    public void run() {
                        TvShowsItem tvShow = getIntent().getParcelableExtra(EXTRA_TVSHOWS);

                        assert tvShow != null;
                        String vote_average = Double.toString(tvShow.getVoteAverage());
                        String url_image = "https://image.tmdb.org/t/p/w500" + tvShow.getPosterPath();

                        tvTitle.setText(tvShow.getTitle());
                        tvRelease.setText(tvShow.getReleaseDate());
                        tvScore.setText(vote_average);
                        tvVote_Count.setText(tvShow.getVote_count());
                        tvOverview.setText(tvShow.getOverview());
                        tvLanguage.setText(tvShow.getOriginal_language());
                        Glide.with(TvShowsDetailActivity.this)
                                .load(url_image)
                                .placeholder(R.color.colorAccent)
                                .into(imagePoster);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        MaterialFavoriteButton materialFavoriteButton = findViewById(R.id.favorite_button);

        final TvShowsItem tvShow = getIntent().getParcelableExtra(EXTRA_TVSHOWS);

        if (favoriteHelper.isExist2(tvShow)){
            materialFavoriteButton.setFavorite(true);
            materialFavoriteButton.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite) {
                                if (!favoriteHelper.isExist2(tvShow)) {
                                    long result = favoriteHelper.insertFavorite2(tvShow);
                                    if (result > 0) {
                                        Snackbar.make(buttonView, getString(R.string.added_to_fav),
                                                Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                favoriteHelper.deleteFavorite2(tvShow.getId());
                                Snackbar.make(buttonView, getString(R.string.removed_from_fav),
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite) {
                        if (!favoriteHelper.isExist2(tvShow)) {
                            long result = favoriteHelper.insertFavorite2(tvShow);
                            if (result > 0) {
                                Snackbar.make(buttonView, getString(R.string.added_to_fav),
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        int movie_id = Objects.requireNonNull(getIntent().getExtras()).getInt("id");
                        favoriteHelper.deleteFavorite(movie_id);
                        Snackbar.make(buttonView, getString(R.string.removed_from_fav),
                                Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
