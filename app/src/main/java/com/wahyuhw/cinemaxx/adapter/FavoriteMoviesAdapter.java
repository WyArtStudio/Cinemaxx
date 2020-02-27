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

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.FavoriteViewHolder> {
    private ArrayList<MoviesItem> listMovies = new ArrayList<>();

    public ArrayList<MoviesItem> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<MoviesItem> listMovies) {
        if (listMovies.size() > 0){
            this.listMovies.clear();
        }
        this.listMovies = new ArrayList<>();
        this.listMovies.addAll(listMovies);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_movies, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteViewHolder holder, final int position) {

        holder.bind(listMovies.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.itemView.getContext(), MoviesDetailActivity.class);

                MoviesItem movie1 = new MoviesItem();
                movie1.setId(listMovies.get(position).getId());
                movie1.setTitle(listMovies.get(position).getTitle());
                movie1.setOverview(listMovies.get(position).getOverview());
                movie1.setVote_count(listMovies.get(position).getVote_count());
                movie1.setVoteAverage(listMovies.get(position).getVoteAverage());
                movie1.setOriginal_language(listMovies.get(position).getOriginal_language());
                movie1.setPosterPath(listMovies.get(position).getPosterPath());
                movie1.setBackdropPath(listMovies.get(position).getBackdropPath());
                movie1.setReleaseDate(listMovies.get(position).getReleaseDate());

                intent.putExtra(MoviesDetailActivity.EXTRA_MOVIE, movie1);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPoster;

        FavoriteViewHolder(View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.img_item_photo);
        }

        void bind(MoviesItem movie) {
            String url_image = "https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.getPosterPath();

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPoster);
        }
    }
}
