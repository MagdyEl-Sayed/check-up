package com.example.gm7.checkup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by root on 24/07/16.
 */

public class DBShopsHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="userShopping";
    private static final String SHOP_TABLE= "shop";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_SHOP_TABLE="create table "+SHOP_TABLE+"("+"shop_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_name TEXT NOT NULL ,"+
            "shop_name text NOT NULL ," +
            "shop_phone INTEGER NOT NULL ,"+
            "shop_address text NOt NULL ,"+
            "shop_last_visit text "+
                        ");";
    public DBShopsHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SHOP_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SHOP_TABLE);
        onCreate(db);
    }
    public boolean  insertShop( String userName, String shop_name, int shop_phone, String shop_address, String shop_last_visit) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("user_name",userName);
        newValues.put("shop_name",shop_name);
        newValues.put("shop_phone",shop_phone);
        newValues.put("shop_address",shop_address);
        newValues.put("shop_last_visit",shop_last_visit);
        db.insert(SHOP_TABLE, null, newValues);
        return true;
    }
    public int deleteShop(String shop_name) {
        SQLiteDatabase db=this.getWritableDatabase();
        String where = "shop_name=?";
        int numberOFEntriesDeleted = db.delete("shop", where, new String[]{shop_name});
        return numberOFEntriesDeleted;
    }
    public boolean updateShopLastVisit(String shop_last_visit) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("shop_last_visit",shop_last_visit);
        String where = "shop_last_visit = ?";
        db.update("shop", updatedValues, where, new String[]{shop_last_visit});
        return true;
    }
public ArrayList<String > getShopAddress( String userName){
    SQLiteDatabase db=this.getWritableDatabase();

    ArrayList<String>shopAddresses=new ArrayList<String>();
    Cursor cursor=db.rawQuery("SELECT * FROM "+SHOP_TABLE+ " where user_name = '"+ userName+"'",null);
    cursor.moveToFirst();
    while(!cursor.isAfterLast()){
        shopAddresses.add(cursor.getString(cursor.getColumnIndex("shop_address")));
        cursor.moveToNext();
    }
    return shopAddresses;
}
    public ArrayList<String> getShopPhones(){
        SQLiteDatabase db=this.getWritableDatabase();

        ArrayList<String>shopphones=new ArrayList<String>();
        Cursor cursor=db.rawQuery("SELECT  * FROM "+SHOP_TABLE ,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            shopphones.add(cursor.getString(cursor.getColumnIndex("shop_phone")));
            cursor.moveToNext();
        }
        return shopphones;
    }
    public ArrayList<String> getShopNames(){
        SQLiteDatabase db=this.getWritableDatabase();

        ArrayList<String>shopNames=new ArrayList<String>();
        Cursor cursor=db.rawQuery("SELECT  * FROM "+SHOP_TABLE ,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            shopNames.add(cursor.getString(cursor.getColumnIndex("shop_name")));
            cursor.moveToNext();
        }
        return shopNames;    }
    public ArrayList<String> getLasttVisit(){
        SQLiteDatabase db=this.getWritableDatabase();

        ArrayList<String>shopNames=new ArrayList<String>();
        Cursor cursor=db.rawQuery("SELECT  * FROM "+SHOP_TABLE ,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            shopNames.add(cursor.getString(cursor.getColumnIndex("shop_last_visit")));
            cursor.moveToNext();
        }
        return shopNames;    }
}
