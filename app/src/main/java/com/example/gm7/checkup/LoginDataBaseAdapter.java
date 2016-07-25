package com.example.gm7.checkup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static android.R.attr.name;

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "shop";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table " + "SHOP" +
            "( " + "ID" + " integer primary key autoincrement," +
            "USERNAME  text,EMAIL text,PASSWORD text,PHONE text,ADDRESS text,LAST_VISIT text); ";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String userName, String password, String email) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("EMAIL", email);
        newValues.put("PASSWORD", password);
        newValues.put("PHONE", "01015639329");
        newValues.put("ADDRESS","Kafr ali");
        newValues.put("LAST_VISIT","10am");

        // Insert the row into your table
        db.insert("SHOP", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String UserName) {
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("SHOP", where, new String[]{UserName});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query("SHOP", null, "USERNAME=? ", new String[]{userName}, null, null, null);
        //Cursor cursor=db.query("LOGIN", null, "EMAIL=?", new String[]{email}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public String getSinlgeEnt(String userName) {
        Cursor cursor = db.query("SHOP", null, "EMAIL=? ", new String[]{userName}, null, null, null);

        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void updateEntry(String userName, String password) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("SHOP", updatedValues, where, new String[]{userName});
    }

    //
    public String check(String username) {

        Cursor cursor = db.query("SHOP", null, "EMAIL=? ", new String[]{username}, null, null, null);
        //Cursor cursor=db.query("LOGIN", null, "EMAIL=?", new String[]{email}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("EMAIL"));
        cursor.close();
        return password;

    }

    //
    public String recoverypass(String email) {

        Cursor cursor = db.query("SHOP", null, "EMAIL=? ", new String[]{email}, null, null, null);
        //Cursor cursor=db.query("LOGIN", null, "EMAIL=?", new String[]{email}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;

    }
    //Method to get UserName
    public String getUserName( ){
            String password=null;

            Cursor cursor = db.rawQuery("select USERNAME from " + DATABASE_NAME, null);
            cursor.moveToFirst();

            if(cursor.moveToFirst()){
                password=cursor.getString(cursor.getColumnIndex("USERNAME"));
            }
            return password;
        }
}

