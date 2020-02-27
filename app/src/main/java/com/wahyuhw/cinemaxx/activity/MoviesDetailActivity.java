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
import com.wahyuhw.cinemaxx.entity.MoviesItem;

import java.util.Objects;

public class MoviesDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private FavoriteHelper favoriteHelper;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        final TextView tvTitle = findViewById(R.id.tv_item_title);
        final TextView tvVoteAverage = findViewById(R.id.tv_item_userScore);
        final TextView tvOverview = findViewById(R.id.tv_item_overview);
        final TextView tvLanguage = findViewById(R.id.tv_item_language);
        final ImageView imagePoster = findViewById(R.id.img_item_photo);
        final TextView tvVoteCount = findViewById(R.id.tv_item_vote);
        final TextView tvRelease = findViewById(R.id.tv_item_dateReleased);
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
                        MoviesItem movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

                        assert movie != null;
                        String vote_average = Double.toString(movie.getVoteAverage());
                        String url_image = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();

                        tvTitle.setText(movie.getTitle());
                        tvRelease.setText(movie.getReleaseDate());
                        tvVoteAverage.setText(vote_average);
                        tvVoteCount.setText(movie.getVote_count());
                        tvOverview.setText(movie.getOverview());
                        tvLanguage.setText(movie.getOriginal_language());
                        Glide.with(MoviesDetailActivity.this)
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

        final MoviesItem movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (favoriteHelper.isExist(movie)){
            materialFavoriteButton.setFavorite(true);
            materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite) {
                        if (!favoriteHelper.isExist(movie)) {
                            long result = favoriteHelper.insertFavorite(movie);
                            if (result > 0) {
                                Snackbar.make(buttonView, getString(R.string.added_to_fav),
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        favoriteHelper.deleteFavorite(movie.getId());
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
                        if (!favoriteHelper.isExist(movie)) {
                            long result = favoriteHelper.insertFavorite(movie);
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
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
