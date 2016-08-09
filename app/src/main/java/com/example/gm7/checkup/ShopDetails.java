package com.example.gm7.checkup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.askerlap.emadahmed.checkup.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ShopDetails extends AppCompatActivity {
public ListView list;
    DBSalesItems salesHelper;
    DBShopsHelper shopsHelper;
    private  ArrayList<String> names,shopNames,Addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        list=(ListView)findViewById(R.id.activity_shop_details);
        salesHelper=new DBSalesItems(this);
        shopsHelper=new DBShopsHelper(this);
        shopNames=new ArrayList<>();
        names=new ArrayList<>();
        Addresses=new ArrayList<>();
        shopNames=shopsHelper.getShopPhones();
        Addresses=shopsHelper.getshopAddress();
        names=shopsHelper.getshopName();
        list.setAdapter(new customAdapter(this,shopNames,names,Addresses));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shop_details_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_search){
            Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class customAdapter extends BaseAdapter{
        private final Context mcontext;
        private ArrayList<String> shopNames,NAMES,shopAddress;
        customAdapter(Context context,ArrayList<String> shopNames,ArrayList<String>names,ArrayList<String>Address){
            this.mcontext=context;
            this.NAMES=names;
            this.shopAddress=Address;
            this.shopNames=shopNames;
        }

        @Override
        public int getCount() {
            int size=shopNames.size();

            return size ;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) mcontext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView=inflater.inflate(R.layout.custom_shop_layout,viewGroup,false);
            final TextView shopName,Shop_phone,Shop_Address;
            shopName=(TextView)myView.findViewById(R.id.txt_cust_shopname);
            Shop_phone=(TextView)myView.findViewById(R.id.txt_cust_shopphone);
            Shop_Address=(TextView)myView.findViewById(R.id.txt_cust_test);
            Shop_Address.setText(shopAddress.get(i));
            Shop_phone.setText("+20"+shopNames.get(i));
           shopName.setText(NAMES.get(i));
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    call(Shop_phone.getText().toString());
                }
            });
            return myView;
        }
        private void call(String number) {
            Intent in=new Intent(Intent.ACTION_DIAL,Uri.parse("tel: "+ number));
            try{
                startActivity(in);
            }

            catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(),"Can't establish the call",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
