package com.wahyuhw.cinemaxx.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_FAV = "movie";
    static String TABLE_FAV2 = "tv";

    static final class FavoriteColumns implements BaseColumns {
        static String TITLE = "title";
        static String DESCRIPTION = "overview";
        static String DATE = "release_date";
        static String BACKGROUND = "backdrop_path";
        static String POSTER = "poster_path";
        static String LANGUAGE = "original_language";
        static String VOTE = "vote_count";
        static String AVERAGE = "vote_average";
    }
}
