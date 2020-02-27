package com.wahyuhw.cinemaxx.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wahyuhw.cinemaxx.entity.MoviesItem;
import com.wahyuhw.cinemaxx.entity.TvShowsItem;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.wahyuhw.cinemaxx.db.DatabaseContract.FavoriteColumns.AVERAGE;
import static com.wahyuhw.cinemaxx.db.DatabaseContract.FavoriteColumns.BACKGROUND;
import static com.wahyuhw.cinemaxx.db.DatabaseContract.FavoriteColumns.DATE;
import static com.wahyuhw.cinemaxx.db.DatabaseContract.FavoriteColumns.DESCRIPTION;
import static com.wahyuhw.cinemaxx.db.DatabaseContract.FavoriteColumns.LANGUAGE;
import static com.wahyuhw.cinemaxx.db.DatabaseContract.FavoriteColumns.POSTER;
import static com.wahyuhw.cinemaxx.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.wahyuhw.cinemaxx.db.DatabaseContract.FavoriteColumns.VOTE;
import static com.wahyuhw.cinemaxx.db.DatabaseContract.TABLE_FAV;
import static com.wahyuhw.cinemaxx.db.DatabaseContract.TABLE_FAV2;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = TABLE_FAV;
    private static final String DATABASE_TABLE2 = TABLE_FAV2;
    private static DatabaseHelper databaseHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<MoviesItem> getAllFavorites() {
        ArrayList<MoviesItem> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        MoviesItem movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new MoviesItem();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                movie.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(BACKGROUND)));
                movie.setOriginal_language(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                movie.setVote_count(cursor.getString(cursor.getColumnIndexOrThrow(VOTE)));
                movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(AVERAGE)));
                arrayList.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean isExist(MoviesItem movie) {
        database = databaseHelper.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_FAV + " WHERE " + _ID + "=" + movie.getId();

        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long insertFavorite(MoviesItem movie) {
        ContentValues args = new ContentValues();
        args.put(_ID, movie.getId());
        args.put(TITLE, movie.getTitle());
        args.put(DESCRIPTION, movie.getOverview());
        args.put(DATE, movie.getReleaseDate());
        args.put(POSTER, movie.getPosterPath());
        args.put(BACKGROUND, movie.getBackdropPath());
        args.put(LANGUAGE, movie.getOriginal_language());
        args.put(VOTE, movie.getVote_count());
        args.put(AVERAGE, movie.getVoteAverage());
        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteFavorite(int id) {
        return database.delete(TABLE_FAV, _ID + " = '" + id + "'", null);
    }

    public ArrayList<TvShowsItem> getAllFavorites2() {
        ArrayList<TvShowsItem> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE2,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        TvShowsItem tvShow;
        if (cursor.getCount() > 0) {
            do {
                tvShow = new TvShowsItem();
                tvShow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                tvShow.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                tvShow.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                tvShow.setVote_count(cursor.getString(cursor.getColumnIndexOrThrow(VOTE)));
                tvShow.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                tvShow.setOriginal_language(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                tvShow.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(AVERAGE)));
                tvShow.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                tvShow.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(BACKGROUND)));
                arrayList.add(tvShow);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean isExist2(TvShowsItem tvShow) {
        database = databaseHelper.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_FAV2 + " WHERE " + _ID + "=" + tvShow.getId();

        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long insertFavorite2(TvShowsItem tvShow) {
        ContentValues args = new ContentValues();
        args.put(_ID, tvShow.getId());
        args.put(TITLE, tvShow.getTitle());
        args.put(DESCRIPTION, tvShow.getOverview());
        args.put(VOTE, tvShow.getVote_count());
        args.put(DATE, tvShow.getReleaseDate());
        args.put(LANGUAGE, tvShow.getOriginal_language());
        args.put(AVERAGE, tvShow.getVoteAverage());
        args.put(POSTER, tvShow.getPosterPath());
        args.put(BACKGROUND, tvShow.getBackdropPath());
        return database.insert(DATABASE_TABLE2, null, args);
    }

    public int deleteFavorite2(int id) {
        return database.delete(TABLE_FAV2, _ID + " = '" + id + "'", null);
    }
}
