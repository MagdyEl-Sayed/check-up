package com.example.gm7.checkup;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GM7 on 27/07/2016.
 */

public class DBSalesItems extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="sales_items";
    private static final String ITEM_TABLE= "items";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_ITEM_TABLE ="create table "+ITEM_TABLE+"("+
            "item_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "item_price DECIMAL(19,5) NOT NULL ,"+
            "item_name VARCHAR(15) NOT NULL ," +
            "shop_name VARCHAR(15) NOT NULL ,"+
            "item_type VARCHAR(15) NOT NULL );";
    public DBSalesItems(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
        onCreate(db);
    }

    public boolean  insertEntryItems(double item_price, String item_name,String item_type,String shopName) {
        SQLiteDatabase db=this.getWritableDatabase();
        db=this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("item_price",item_price);
        newValues.put("item_name",item_name);
        newValues.put("item_type",item_type);
        newValues.put("shop_name",shopName);
         db.insert(ITEM_TABLE, null, newValues);
        return true;
    }

    public int deleteEntry(String item_name) {
        SQLiteDatabase db=this.getWritableDatabase();
        String where = "item_name=?";
        int numberOFEntriesDeleted = db.delete(ITEM_TABLE, where, new String[]{item_name});
        return numberOFEntriesDeleted;
    }


}
