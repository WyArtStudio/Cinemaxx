package com.wahyuhw.cinemaxx.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wahyuhw.cinemaxx.R;
import com.wahyuhw.cinemaxx.activity.MoviesDetailActivity;
import com.wahyuhw.cinemaxx.entity.MoviesItem;

import java.util.ArrayList;

public class GridMoviesAdapter extends RecyclerView.Adapter<GridMoviesAdapter.MovieHolder> {
    private ArrayList<MoviesItem> listMovies;

    public GridMoviesAdapter(ArrayList<MoviesItem> items) {
        this.listMovies = items;
    }

    public void setListMovies(ArrayList<MoviesItem> items) {
        this.listMovies = new ArrayList<>();
        this.listMovies.addAll(items);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_movies, parent, false);
        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int i) {
        holder.bind(listMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPoster;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.img_item_photo);

            itemView.setOnClickListener(this);
        }

        void bind(MoviesItem movie) {
            String url_image = "https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.getPosterPath();

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPoster);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            MoviesItem movie = listMovies.get(position);

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), MoviesDetailActivity.class);
            moveWithObjectIntent.putExtra(MoviesDetailActivity.EXTRA_MOVIE, movie);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }
}
