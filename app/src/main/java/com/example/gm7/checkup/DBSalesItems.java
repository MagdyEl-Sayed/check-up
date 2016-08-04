package com.example.gm7.checkup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
            "user_name text not null ,"+
            "item_name VARCHAR(15) NOT NULL ," +
            "shop_name VARCHAR(15) NOT NULL ,"+
            "date Date  ,"+
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
        String upgradeQuery = "ALTER TABLE items ADD COLUMN date TEXT NOT NULL";
        if (oldVersion == 1 && newVersion == 2)
            db.execSQL(upgradeQuery);
        onCreate(db);
    }

    public boolean  insertEntryItems(String userName,double item_price, String item_name,String item_type,String shopName,String date) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("user_name",userName);
        newValues.put("item_price",item_price);
        newValues.put("item_name",item_name);
        newValues.put("item_type",item_type);
        newValues.put("shop_name",shopName);
        newValues.put("date",date);

         db.insert(ITEM_TABLE, null, newValues);
        return true;
    }

    public int deleteEntry(String item_name) {
        SQLiteDatabase db=this.getWritableDatabase();
        String where = "item_name=?";
        int numberOFEntriesDeleted = db.delete(ITEM_TABLE, where, new String[]{item_name});
        return numberOFEntriesDeleted;
    }
    public String getItemDate(){
        String password=null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select date from " + ITEM_TABLE , null);
        cursor.moveToFirst();

        if(cursor.moveToFirst()){
            password=cursor.getString(cursor.getColumnIndex("date"));
        }
        return password;
    }


}
