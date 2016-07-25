package com.example.gm7.checkup;

import android.app.Activity;
import android.app.ListActivity;
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
import android.widget.Toast;

import com.askerlap.emadahmed.checkup.R;

import java.util.ArrayList;

public class Shops extends ListActivity {

    static final String[] Android =
            new String[] { "item1", "item2", "item3", "item4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_shops);
        setListAdapter(new ListAct(this, Android));


    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //get selected items
        String selectedValue = (String) getListAdapter().getItem(position);
        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
    }


}
