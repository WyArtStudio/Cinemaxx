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
import com.wahyuhw.cinemaxx.activity.TvShowsDetailActivity;
import com.wahyuhw.cinemaxx.entity.TvShowsItem;

import java.util.ArrayList;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.Holder> {
    private ArrayList<TvShowsItem> listTv;

    public TvShowsAdapter(ArrayList<TvShowsItem> items) {
        this.listTv = items;
    }

    public void setListTv(ArrayList<TvShowsItem> items) {
        this.listTv = new ArrayList<>();
        this.listTv.addAll(items);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movies, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.bind(listTv.get(i));

    }

    @Override
    public int getItemCount() {
        return listTv.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPoster;
        TextView tvTitle, tvRelease, tvPolling, tvPeople_Vote;

        public Holder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvRelease = itemView.findViewById(R.id.tv_item_dateReleased);
            tvPolling = itemView.findViewById(R.id.tv_item_userScore);
            tvPeople_Vote = itemView.findViewById(R.id.tv_item_vote);

            itemView.setOnClickListener(this);
        }

        void bind(TvShowsItem tvItem) {
            String vote_average = Double.toString(tvItem.getVoteAverage());
            String url_image = "https://image.tmdb.org/t/p/w300_and_h450_bestv2" + tvItem.getPosterPath();

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPoster);

            tvTitle.setText(tvItem.getTitle());
            tvRelease.setText(tvItem.getReleaseDate().substring(0, 4));
            tvPolling.setText(vote_average);
            tvPeople_Vote.setText(tvItem.getVote_count());

            String title = checkTextIfNull(tvItem.getTitle());
            if (title.length() > 30) {
                tvTitle.setText(String.format("%s...", title.substring(0, 29)));
            } else {
                tvTitle.setText(checkTextIfNull(tvItem.getTitle()));
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
            TvShowsItem tv = listTv.get(position);

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), TvShowsDetailActivity.class);
            moveWithObjectIntent.putExtra(TvShowsDetailActivity.EXTRA_TVSHOWS, tv);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }
}
