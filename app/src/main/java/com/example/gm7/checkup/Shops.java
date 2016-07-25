package com.example.gm7.checkup;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.askerlap.emadahmed.checkup.R;

import java.util.ArrayList;

public class Shops extends AppCompatActivity {
    private ListView layout_list;
    ArrayList<String> shopNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        shopNames=new ArrayList<String>();
        layout_list=(ListView)findViewById(R.id.activity_shops);
        layout_list.setAdapter(new myAdapter(this ));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class myAdapter extends BaseAdapter {
        Context context;
        LayoutInflater mInflater;

        myAdapter(Context context ){
            this.context=context;

            mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View cust_view;
            TextView txt;
            cust_view=mInflater.inflate(R.layout.custom_listview,null);
            txt=(TextView)cust_view.findViewById(R.id.txt1);
            return view;
        }
    }
}
