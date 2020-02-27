package com.wahyuhw.cinemaxx.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbfavourite";
    private static final int DATABASE_VERSION = 3;
    private static final String SQL_CREATE_TABLE_FAV = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_FAV,
            DatabaseContract.FavoriteColumns._ID,
            DatabaseContract.FavoriteColumns.TITLE,
            DatabaseContract.FavoriteColumns.DESCRIPTION,
            DatabaseContract.FavoriteColumns.DATE,
            DatabaseContract.FavoriteColumns.BACKGROUND,
            DatabaseContract.FavoriteColumns.POSTER,
            DatabaseContract.FavoriteColumns.LANGUAGE,
            DatabaseContract.FavoriteColumns.VOTE,
            DatabaseContract.FavoriteColumns.AVERAGE
    );

    private static final String SQL_CREATE_TABLE_FAV2 = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_FAV2,
            DatabaseContract.FavoriteColumns._ID,
            DatabaseContract.FavoriteColumns.TITLE,
            DatabaseContract.FavoriteColumns.DESCRIPTION,
            DatabaseContract.FavoriteColumns.DATE,
            DatabaseContract.FavoriteColumns.BACKGROUND,
            DatabaseContract.FavoriteColumns.POSTER,
            DatabaseContract.FavoriteColumns.LANGUAGE,
            DatabaseContract.FavoriteColumns.VOTE,
            DatabaseContract.FavoriteColumns.AVERAGE
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAV);
        db.execSQL(SQL_CREATE_TABLE_FAV2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAV);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAV2);
        onCreate(db);
    }
}
