package com.wahyuhw.cinemaxx.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wahyuhw.cinemaxx.R;
import com.wahyuhw.cinemaxx.activity.MoviesDetailActivity;
import com.wahyuhw.cinemaxx.entity.MoviesItem;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> {
    private ArrayList<MoviesItem> listMovies;

    public MoviesAdapter(ArrayList<MoviesItem> items) {
        this.listMovies = items;
    }

    public void setListMovies(ArrayList<MoviesItem> items) {
        this.listMovies = new ArrayList<>();
        this.listMovies.addAll(items);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movies, parent, false);
        return new MoviesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int i) {
        holder.bind(listMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class MoviesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPoster;
        TextView tvTitle, tvRelease, tvPolling, tvPeople_Vote;

        public MoviesHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvRelease = itemView.findViewById(R.id.tv_item_dateReleased);
            tvPolling = itemView.findViewById(R.id.tv_item_userScore);
            tvPeople_Vote = itemView.findViewById(R.id.tv_item_vote);

            itemView.setOnClickListener(this);
        }

        void bind(MoviesItem movie) {
            String vote_average = Double.toString(movie.getVoteAverage());
            String url_image = "https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.getPosterPath();

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPoster);

            tvTitle.setText(movie.getTitle());
            tvRelease.setText(movie.getReleaseDate().substring(0, 4));
            tvPolling.setText(vote_average);
            tvPeople_Vote.setText(movie.getVote_count());

            String title = checkTextIfNull(movie.getTitle());
            if (title.length() > 30) {
                tvTitle.setText(String.format("%s...", title.substring(0, 29)));
            } else {
                tvTitle.setText(checkTextIfNull(movie.getTitle()));
            }
        }

        String checkTextIfNull(String text) {
            if (text != null && !text.isEmpty()) {
                return text;
            } else {
                return "-";
            }
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
