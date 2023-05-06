package com.delts.shipitfixit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.delts.shipitfixit.R;
import com.delts.shipitfixit.models.Shop;

import java.util.ArrayList;

public class ShopDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Shop.db";
    public static final String TABLE_SHOP = "Shop";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_LOCATION = "location";
    public static final String COL_IMAGE_SOURCE = "image_source";
    public ShopDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE = "CREATE TABLE " + TABLE_SHOP + "("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NAME + " TEXT NOT NULL, "
                    + COL_LOCATION + " TEXT NOT NULL, "
                    + COL_IMAGE_SOURCE +  " INTEGER NOT NULL)";

        String INSERT_INTO = "INSERT INTO " + TABLE_SHOP + " (" + COL_NAME + ", " + COL_LOCATION + ", " + COL_IMAGE_SOURCE + ") ";
            db.execSQL(CREATE_TABLE);
            db.execSQL(INSERT_INTO + "VALUES ('Samsung', 'Taytay, Rizal', " + R.drawable.samsung_logo + ")");
            db.execSQL(INSERT_INTO + "VALUES ('Infinix', 'Binangonan, Rizal', " + R.drawable.infinix_logo + ")");
            db.execSQL(INSERT_INTO + "VALUES ('OPPO', 'Angono, Rizal', " + R.drawable.oppo_logo + ")");
            db.execSQL(INSERT_INTO + "VALUES ('Huawei', 'Brookside', " + R.drawable.huawei_logo + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ON_UPGRADE = "DROP TABLE IF EXISTS " + TABLE_SHOP;
        db.execSQL(ON_UPGRADE);
    }

    public boolean insertShop(String name, String location, int imageResource){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_NAME, name);
        cv.put(COL_LOCATION, location);
        cv.put(COL_IMAGE_SOURCE, imageResource);
        long result = db.insert(TABLE_SHOP, null, cv);

        db.close();

        return result != -1;
    }


    public ArrayList<Shop> retrieveAllShops() {
        String SELECT_QUERY = "SELECT * FROM " + TABLE_SHOP;
        ArrayList<Shop> shops = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SELECT_QUERY, null);

        while (c.moveToNext()) {
            Shop shop = new Shop();
            shop.setName(c.getString(c.getColumnIndexOrThrow(COL_NAME)));
            shop.setLocation(c.getString(c.getColumnIndexOrThrow(COL_LOCATION)));
            shop.setImage(c.getInt(c.getColumnIndexOrThrow(COL_IMAGE_SOURCE)));
            shops.add(shop);
        }
        return shops;
    }

    public ArrayList<Shop> retrieveShopByName(String name) {
        String SELECT_QUERY = "SELECT * FROM " + TABLE_SHOP + " WHERE " + COL_NAME
                + " LIKE '%' || ? || '%'";
        ArrayList<Shop> shops = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(SELECT_QUERY, new String[] {name});

        while (c.moveToNext()) {
            Shop shop = new Shop();
            shop.setId(c.getInt(c.getColumnIndexOrThrow(COL_ID)));
            shop.setName(c.getString(c.getColumnIndexOrThrow(COL_NAME)));
            shop.setLocation(c.getString(c.getColumnIndexOrThrow(COL_LOCATION)));
            shop.setImage(c.getInt(c.getColumnIndexOrThrow(COL_IMAGE_SOURCE)));
            shops.add(shop);
        }
        return shops;
    }
}
