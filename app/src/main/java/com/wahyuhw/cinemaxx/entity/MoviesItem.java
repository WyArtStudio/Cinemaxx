package com.wahyuhw.cinemaxx.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class MoviesItem implements Parcelable {

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("vote_count")
    private String vote_count;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("overview")
    private String overview;

    @SerializedName("id")
    private int id;

    MoviesItem(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readDouble();
        vote_count = in.readString();
        original_language = in.readString();
        overview = in.readString();
        id = in.readInt();
    }

    public MoviesItem() {
    }

    public static final Parcelable.Creator<MoviesItem> CREATOR = new Parcelable.Creator<MoviesItem>() {
        @Override
        public MoviesItem createFromParcel(Parcel in) {
            return new MoviesItem(in);
        }

        @Override
        public MoviesItem[] newArray(int size) {
            return new MoviesItem[size];
        }
    };

    public String getVote_count() {
        return vote_count;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeString(releaseDate);
        dest.writeDouble(voteAverage);
        dest.writeString(vote_count);
        dest.writeString(original_language);
        dest.writeString(overview);
        dest.writeInt(id);
    }
}
