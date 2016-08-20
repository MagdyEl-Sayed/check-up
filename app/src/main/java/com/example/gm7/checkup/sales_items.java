package com.example.gm7.checkup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.askerlap.emadahmed.checkup.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class sales_items extends AppCompatActivity {
private DBSalesItems SalesHelper;
    private DBShopsHelper ShopsHelper;
    private LoginDataBaseAdapter loginHelper;
    private EditText itemName,itemType,itemPrice,shopName,shopPhone,shopAddress;
    private android.os.Handler customHandler = new android.os.Handler();
    private Handler handler;
    private Runnable runnable;
    String strdate1;
    private static final String FORMAT = "%02d:%02d:%02d";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_items);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        customHandler.postDelayed(updateTimerThread, 0);
        SalesHelper=new DBSalesItems(this);
        ShopsHelper=new DBShopsHelper(this);
        loginHelper=new LoginDataBaseAdapter(this);
        loginHelper.open();
        shopName=(EditText)findViewById(R.id.ed_shopname);
        shopAddress=(EditText)findViewById(R.id.ed_shopaddress);
        shopPhone=(EditText)findViewById(R.id.ed_shopPhone);
        itemName=(EditText)findViewById(R.id.ed_itemname);
        itemType=(EditText)findViewById(R.id.ed_itemtype);
        itemPrice=(EditText)findViewById(R.id.ed_itemprice);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String iName=itemName.getText().toString(),iType= itemType.getText().toString(),sName= shopName.getText().toString(),sAddress=shopAddress.getText().toString();
                int sPhone=Integer.parseInt(shopPhone.getText().toString()); double iPrice=Double.parseDouble(itemPrice.getText().toString());
                if(iName.isEmpty()|| iType.isEmpty()||
                       sName.isEmpty()||sAddress.isEmpty()||(sPhone+"").isEmpty()){
                    Snackbar.make(view,getResources().getString(R.string.add_item_error),Snackbar.LENGTH_LONG).setAction("Action",null)
                            .show();
                } else {
                    ShopsHelper.getWritableDatabase();
                    SalesHelper.getWritableDatabase();
                   SalesHelper.insertEntryItems(loginHelper.getUserName(),iPrice,iName,iType,sName,strdate1);
                    if(!ShopsHelper.getshopName().contains(sName)){
                    ShopsHelper.insertShop(loginHelper.getUserName(),sName,sPhone,sAddress);}
                    final ProgressDialog progressDialog = new ProgressDialog(sales_items.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage(getResources().getString(R.string.auth));
                    progressDialog.show();
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {

                                    progressDialog.dismiss();
                                }
                            },  1000);

                    finish();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            ///
            Calendar c1 = Calendar.getInstance();

            SimpleDateFormat sdf1 = new SimpleDateFormat("d MMM yyyy");
             strdate1 = sdf1.format(c1.getTime());

            customHandler.postDelayed(this, 0);
        }

    };
}
