package com.example.gm7.checkup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.askerlap.emadahmed.checkup.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
int counter=0;
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    LoginDataBaseAdapter loginHelper;
    DBShopsHelper shopsHelper;
    DBSalesItems salesHelper;
    private Button billDetails,shopDetails,sales_items;

    //Fuck u _!_
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_menu   );
        loginHelper=new LoginDataBaseAdapter(this);
        shopsHelper=new DBShopsHelper(this);
        salesHelper=new DBSalesItems(this);
        loginHelper.open();
        viewFlipper = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper1);
        fade_in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);
//sets auto flipping
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();
        billDetails=(Button) findViewById(R.id.btn_report);
        shopDetails=(Button)findViewById(R.id.btn_shopping);
        sales_items=(Button)findViewById(R.id.btn_salesItem);
        sales_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,sales_items.class));
            }
        });
        billDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,BillDetails.class));
            }
        });
        shopDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShopDetails.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main__menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_aboutapp){
            startActivity(new Intent(MainActivity.this,About_app.class));
        } else if(id==R.id.action_logout){
            loginHelper.updateFlag("false",loginHelper.getUserName());
            startActivity(new Intent(MainActivity.this,Login.class));
        } else if (id==R.id.action_delete_data){
            int size=shopsHelper.getshopName().size();
            ArrayList<String> Names=shopsHelper.getshopName();
            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {

                            progressDialog.dismiss();
                        }
                    }, 3000);
            for(int i=0;i<size;i++){
                shopsHelper.deleteDate(Names.get(i));
                salesHelper.deleteDate(Names.get(i));
            }
            return true;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
counter++;
    }

}
