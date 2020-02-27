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
import com.wahyuhw.cinemaxx.activity.TvShowsDetailActivity;
import com.wahyuhw.cinemaxx.entity.TvShowsItem;

import java.util.ArrayList;

public class GridTvShowsAdapter extends RecyclerView.Adapter<GridTvShowsAdapter.Holder> {
    private ArrayList<TvShowsItem> listTv;

    public GridTvShowsAdapter(ArrayList<TvShowsItem> items) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_movies, parent, false);
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

        public Holder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.img_item_photo);

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
