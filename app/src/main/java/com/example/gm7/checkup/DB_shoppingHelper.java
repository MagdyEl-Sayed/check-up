package com.example.gm7.checkup;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 24/07/16.
 */

public class DB_shoppingHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="userShopping";
    private static final String SHOP_TABEL= "shop";
    private static final int DATABASE_VERSION=1;
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
    public boolean insertUserName(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("user_name",name);
        db.insert(SHOP_TABEL,null, values);
        return true;
    }
}
