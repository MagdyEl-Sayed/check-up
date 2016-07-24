package com.example.gm7.checkup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 24/07/16.
 */

public class DB_shoppingHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="userShopping";
    private static final String SHOP_TABEL= "shop";
    private static final int DATABASE_VERSION=1;
    public SQLiteDatabase db;
    private static final String CREATE_SHOP_TABLE="create table "+SHOP_TABEL+"("+"shop_id INTEGER PRIMARY KEY AUTOINCREMENT," +

            "user_name TEXT NOT NULL ,"+
            "shop_name text NOT NULL ," +
            "shop_phone INTEGER NOT NULL ,"+
            "shop_address text NOt NULL ,"+
            "shop_last_visit text "+
                        ");";


    public DB_shoppingHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SHOP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SHOP_TABEL);
        onCreate(db);


    }
    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }
//
    public boolean  insertEntry(String userName, String shop_name, int shop_phone, String shop_address, String shop_last_visit) {
        ContentValues newValues = new ContentValues();
        newValues.put("user_name",userName);
        newValues.put("shop_name",shop_name);
        newValues.put("shop_phone",shop_phone);
        newValues.put("shop_address",shop_address);
        newValues.put("shop_last_visit",shop_last_visit);

        db.insert("shop", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
        return true;
    }

    public int deleteEntry(String shop_name) {
        String where = "shop_name=?";
        int numberOFEntriesDeleted = db.delete("shop", where, new String[]{shop_name});
        return numberOFEntriesDeleted;
    }


    public boolean updateEntry(String shop_last_visit) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("shop_last_visit",shop_last_visit);
        String where = "shop_last_visit = ?";
        db.update("shop", updatedValues, where, new String[]{shop_last_visit});
        return true;
    }


}
