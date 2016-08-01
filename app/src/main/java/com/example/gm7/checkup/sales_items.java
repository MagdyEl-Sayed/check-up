package com.example.gm7.checkup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.askerlap.emadahmed.checkup.R;

public class sales_items extends AppCompatActivity {
private DBSalesItems SalesHelper;
    private DBShopsHelper ShopsHelper;
    private EditText itemName,itemType,itemPrice,shopName,shopPhone,shopAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SalesHelper=new DBSalesItems(this);
        ShopsHelper=new DBShopsHelper(this);
        shopName=(EditText)findViewById(R.id.ed_shopname);
        shopAddress=(EditText)findViewById(R.id.ed_shopaddress);
        shopPhone=(EditText)findViewById(R.id.ed_shopPhone);
        itemName=(EditText)findViewById(R.id.ed_itemname);
        itemPrice=(EditText)findViewById(R.id.ed_itemprice);
        shopAddress=(EditText)findViewById(R.id.ed_shopname);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemPrice.getText().toString().isEmpty()|| itemName.getText().toString().isEmpty()||
                        itemType.getText().toString().isEmpty()|| shopName.getText().toString().isEmpty()||shopPhone.getText().toString().isEmpty()){
                    Snackbar.make(view,"please fill all boxes above ",Snackbar.LENGTH_LONG).setAction("Action",null)
                            .show();
                } else {
                    ShopsHelper.getWritableDatabase();
                    SalesHelper.getWritableDatabase();
                    SalesHelper.insertEntryItems(Double.parseDouble(itemPrice.getText().toString()), itemName.getText().toString(), "sex",
                            shopName.getText().toString());
                    ShopsHelper.insertShop(shopName.getText().toString(), Integer.parseInt(shopPhone.getText().toString()),shopAddress.getText().toString(),null);

                    try {
                        Thread.sleep(1000);
                        Snackbar.make(view, "Your Money now are safe to track", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
