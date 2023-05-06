package com.delts.shipitfixit.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.delts.shipitfixit.models.ShopService;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopServicesDBHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "ShopServices.db";
    static final String TABLE_SHOP_SERVICES = "ShopServices";
    static final String COL_ID = "id";
    static final String COL_SHOP_ID = "shop_id";
    static final String COL_IMAGE_RESOURCE_ID = "image_resource_id";
    static final String COL_SERVICE_NAME = "service_name";
    static final String COL_SERVICE_DESCRIPTION = "service_description";
    static final String COL_ESTIMATED_PRICE = "estimated_price";


    public ShopServicesDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_SHOP_SERVICES + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_SHOP_ID + " INTEGER NOT NULL, "
                + COL_IMAGE_RESOURCE_ID + " INTEGER NOT NULL, "
                + COL_SERVICE_NAME + " TEXT NOT NULL, "
                + COL_SERVICE_DESCRIPTION + " TEXT NOT NULL, "
                + COL_ESTIMATED_PRICE + " REAL NOT NULL)";

        db.execSQL(CREATE_TABLE);

        //Initialize services for shops
        //Test service names and descriptions
        String[] serviceNames = {"LCD Repair", "Reformat", "Sirain nang tuluyan", "Upgrade", "Battery Repair"};

        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<Double> prices = new ArrayList<>();

        descriptions.add("Repair your smarphones laptop, and even smartwatches LCD, \n Ayusin namin dahil sa katangahan mo.");
        descriptions.add("may virus ba gadget mo dahil sa dolb? Bobo, i-reformat na natin yan");
        descriptions.add("Walang kwenta na ba phone mo? Sirain na natin nang tuluyan HAHA libre lang");
        descriptions.add("Upgrade Storage");
        descriptions.add("Fix your android, iphone, tablets, and laptop battery. We use high quality and reliable batteries");

        prices.add(575.0);
        prices.add(150.50);
        prices.add(0.0);
        prices.add(2500.0);
        prices.add(1500.0);

        //4 Shops
        for (int i = 0; i < 4; i++) {
            //5 services for each shops HAHAHA
            for (int j = 0; j < 5; j++) {
                String INSERT_INTO = "INSERT INTO " + TABLE_SHOP_SERVICES + " (" + COL_SHOP_ID + ", " + COL_IMAGE_RESOURCE_ID + ", " + COL_SERVICE_NAME + ", "+ COL_SERVICE_DESCRIPTION + ", " + COL_ESTIMATED_PRICE + ") ";
                String VALUES = "VALUES (" + (i + 1) + ", " + 1 + ", '" + serviceNames[j] + "', '" + descriptions.get(j) + "', " + prices.get(j) + ")";
                db.execSQL(INSERT_INTO + VALUES);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String ON_UPGRADE = "DROP TABLE IF EXISTS " + TABLE_SHOP_SERVICES;
        db.execSQL(ON_UPGRADE);
    }

    public void test() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SHOP_SERVICES + ";", null);
        ArrayList<String> a = new ArrayList<>();
        while(c.moveToNext()) {
            a.add(c.getString(c.getColumnIndexOrThrow(COL_SERVICE_NAME)));
        }
        db.close();
    }

    public HashMap<Integer, ShopService> shopServicesByShopId(int shopId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SHOP_SERVICES + " WHERE " + COL_SHOP_ID + " = ?;", new String[]{String.valueOf(shopId)});
        HashMap<Integer, ShopService> services = new HashMap<>();

        while (c.moveToNext()) {
            ShopService service = new ShopService();
            service.setId(c.getInt(c.getColumnIndexOrThrow(COL_ID)));
            service.setShopId(shopId);
            service.setImageResourceId(c.getInt(c.getColumnIndexOrThrow(COL_IMAGE_RESOURCE_ID)));
            service.setServiceName(c.getString(c.getColumnIndexOrThrow(COL_SERVICE_NAME)));
            service.setServiceDescription(c.getString(c.getColumnIndexOrThrow(COL_SERVICE_DESCRIPTION)));
            service.setEstimatedPrice(c.getDouble(c.getColumnIndexOrThrow(COL_ESTIMATED_PRICE)));
            services.put(service.getId(), service);
        }

        return services;
    }
}
