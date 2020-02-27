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

public class FavoriteTvShowsAdapter extends RecyclerView.Adapter<FavoriteTvShowsAdapter.FavoriteViewHolder> {
    private ArrayList<TvShowsItem> listTvShow = new ArrayList<>();

    public ArrayList<TvShowsItem> getListTvShow() {
        return listTvShow;
    }

    public void setListTvShow(ArrayList<TvShowsItem> listTvShow){

        if (listTvShow.size() > 0){
            this.listTvShow.clear();
        }
        this.listTvShow = new ArrayList<>();
        this.listTvShow.addAll(listTvShow);

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

        holder.bind(listTvShow.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.itemView.getContext(), TvShowsDetailActivity.class);

                TvShowsItem tvShow = new TvShowsItem();
                tvShow.setId(listTvShow.get(position).getId());
                tvShow.setTitle(listTvShow.get(position).getTitle());
                tvShow.setOverview(listTvShow.get(position).getOverview());
                tvShow.setVote_count(listTvShow.get(position).getVote_count());
                tvShow.setVoteAverage(listTvShow.get(position).getVoteAverage());
                tvShow.setOriginal_language(listTvShow.get(position).getOriginal_language());
                tvShow.setPosterPath(listTvShow.get(position).getPosterPath());
                tvShow.setBackdropPath(listTvShow.get(position).getBackdropPath());
                tvShow.setReleaseDate(listTvShow.get(position).getReleaseDate());

                intent.putExtra(TvShowsDetailActivity.EXTRA_TVSHOWS, tvShow);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listTvShow.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPoster;

        FavoriteViewHolder(View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.img_item_photo);
        }

        void bind(TvShowsItem tvShow) {
            String url_image = "https://image.tmdb.org/t/p/w300_and_h450_bestv2" + tvShow.getPosterPath();

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPoster);
        }
    }
}
