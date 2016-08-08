package com.example.gm7.checkup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.askerlap.emadahmed.checkup.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ShopDetails extends AppCompatActivity {
private ListView list;
    DBSalesItems salesHelper;
    DBShopsHelper shopsHelper;
    private  ArrayList<String> shopNames;
    private ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        list=(ListView)findViewById(R.id.activity_shop_details);
        salesHelper=new DBSalesItems(this);
        shopsHelper=new DBShopsHelper(this);
        shopNames=new ArrayList<>();
        names=new ArrayList<>();
        shopNames=shopsHelper.getShopPhones();
        names=shopsHelper.getshopName();
        list.setAdapter(new customAdapter(this,shopNames,names));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shop_details_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    class customAdapter extends BaseAdapter{
        private final Context mcontext;
        private ArrayList<String> shopNames,NAMES;
        customAdapter(Context context,ArrayList<String> shopNames,ArrayList<String>names){
            this.mcontext=context;
            this.NAMES=names;
            this.shopNames=shopNames;
        }

        @Override
        public int getCount() {
            return shopNames.size();
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
            TextView shopName,Shop_phone;
            shopName=(TextView)myView.findViewById(R.id.txt_cust_shopname);
            Shop_phone=(TextView)myView.findViewById(R.id.txt_cust_shopphone);

            Shop_phone.setText("+20"+shopNames.get(i));
           shopName.setText(NAMES.get(i));
            return myView;
        }
    }
}
