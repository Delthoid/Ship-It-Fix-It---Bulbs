package com.delts.shipitfixit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsersDatabaseHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "Users.db";
    public static final int DB_VERSION = 1;

    //Queries
    private static final String SQL_CREATE_DB =
            "CREATE TABLE " + Entries.UserEntry.TABLE_NAME + "(" +
            Entries.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Entries.UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
            Entries.UserEntry.COLUMN_NAME_PASSWORD + " TEXT)";

    public UsersDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String onUpgrade = "DROP TABLE IF EXISTS " + Entries.UserEntry.TABLE_NAME;
        db.execSQL(onUpgrade);
    }

    //returns true if is already existing
    public boolean checkUsernameIfExist(String username){
        String QUERY = "SELECT " + Entries.UserEntry.COLUMN_NAME_USERNAME
                + " FROM " + Entries.UserEntry.TABLE_NAME
                + " WHERE " + Entries.UserEntry.COLUMN_NAME_USERNAME + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY, new String[] {username});

        boolean success = cursor.moveToNext() ? true : false;
        db.close();
        cursor.close();
        return success;
    }

    //inserting account here return false if failed
    public boolean insertAccount(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Entries.UserEntry.COLUMN_NAME_USERNAME, username);
        cv.put(Entries.UserEntry.COLUMN_NAME_PASSWORD, password);
        long result = db.insert(Entries.UserEntry.TABLE_NAME, null, cv);

        boolean success = result != -1 ? true : false;
        db.close();
        return success;
    }

    //returns true if username and password matches together
    public boolean checkLoginSuccess(String username, String password){
        String QUERY = "SELECT " + Entries.UserEntry.COLUMN_NAME_USERNAME + ", "
                + Entries.UserEntry.COLUMN_NAME_PASSWORD + " FROM " + Entries.UserEntry.TABLE_NAME
                + " WHERE " + Entries.UserEntry.COLUMN_NAME_USERNAME + " = ? AND "
                + Entries.UserEntry.COLUMN_NAME_PASSWORD + " = ? ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY, new String[] {username, password});

        boolean success = cursor.moveToNext() ? true : false;

        cursor.close();
        db.close();

        return success;
    }
}