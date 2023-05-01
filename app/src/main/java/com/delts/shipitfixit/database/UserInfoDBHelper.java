package com.delts.shipitfixit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.delts.shipitfixit.models.User;
import com.delts.shipitfixit.models.UserInfo;

import java.util.ArrayList;

public class UserInfoDBHelper extends SQLiteOpenHelper {
    public UserInfoDBHelper(@Nullable Context context) {
        super(context, Entries.UserEntry.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER_INFO = "CREATE TABLE " + Entries.UserEntry.TABLE_USER_INFO + "("
                + Entries.UserEntry.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Entries.UserEntry.COLUMN_USERNAME + " TEXT NOT NULL, "
                + Entries.UserEntry.COLUMN_FIRSTNAME + " TEXT NOT NULL, "
                + Entries.UserEntry.COLUMN_LASTNAME + " TEXT NOT NULL, "
                + Entries.UserEntry.COLUMN_BIRTHDAY + " DATE NOT NULL, "
                + Entries.UserEntry.COLUMN_AGE + " INTEGER NOT NULL, "
                + Entries.UserEntry.COLUMN_GENDER + " TEXT NOT NULL, "
                + Entries.UserEntry.COLUMN_ADDRESS + " TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_USER_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ON_UPGRADE = "DROP TABLE IF EXISTS " + Entries.UserEntry.TABLE_USER_INFO;
        db.execSQL(ON_UPGRADE);
    }

    //returns true if insertion is successful
    public boolean insertUserInfo(String username, String firstname, String lastname, String birthday,
                                  int age, String gender, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Entries.UserEntry.COLUMN_USERNAME, username);
        cv.put(Entries.UserEntry.COLUMN_FIRSTNAME, firstname);
        cv.put(Entries.UserEntry.COLUMN_LASTNAME, lastname);
        cv.put(Entries.UserEntry.COLUMN_BIRTHDAY, birthday);
        cv.put(Entries.UserEntry.COLUMN_AGE, age);
        cv.put(Entries.UserEntry.COLUMN_GENDER, gender);
        cv.put(Entries.UserEntry.COLUMN_ADDRESS, address);

        long insertSuccess = db.insert(Entries.UserEntry.TABLE_USER_INFO, null, cv);

        db.close();
        return insertSuccess != -1;
    }

    //returns an ArrayList of UserInfo using username
    public ArrayList<UserInfo> getUserInfoArrayList (String username){
        String SELECT_QUERY = "SELECT * FROM " + Entries.UserEntry.TABLE_USER_INFO + " WHERE "
                + Entries.UserEntry.COLUMN_USERNAME + " = ?";

        ArrayList<UserInfo> userInfos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, new String[] {username});

        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                UserInfo userInfo = new UserInfo();
                userInfo.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(Entries.UserEntry.COLUMN_USERNAME)));
                userInfo.setFirstname(cursor.getString(cursor.getColumnIndexOrThrow(Entries.UserEntry.COLUMN_FIRSTNAME)));
                userInfo.setLastname(cursor.getString(cursor.getColumnIndexOrThrow(Entries.UserEntry.COLUMN_LASTNAME)));
                userInfo.setBirthday(cursor.getString(cursor.getColumnIndexOrThrow(Entries.UserEntry.COLUMN_BIRTHDAY)));
                userInfo.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(Entries.UserEntry.COLUMN_AGE)));
                userInfo.setGender(cursor.getString(cursor.getColumnIndexOrThrow(Entries.UserEntry.COLUMN_GENDER)));
                userInfo.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(Entries.UserEntry.COLUMN_ADDRESS)));
                userInfos.add(userInfo);
            }
        }
        db.close();
        cursor.close();
        return userInfos;
    }
}
