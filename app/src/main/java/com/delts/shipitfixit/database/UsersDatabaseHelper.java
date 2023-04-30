package com.delts.shipitfixit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsersDatabaseHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "Users.db";
    public static final int DB_VERSION = 1;

    //Queries
    private static final String SQL_CREATE_DB = "" +
            "CREATE TABLE " + Entries.UserEntry.TABLE_NAME + "(" +
            Entries.UserEntry._ID + " INTEGER PRIMARY KEY, " +
            Entries.UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
            Entries.UserEntry.COLUMN_NAME_PASSWORD + " TEXT)";

    public UsersDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}