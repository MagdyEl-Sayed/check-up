package com.example.gm7.checkup;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
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

public class Shops extends AppCompatActivity {
private ListView list_layout;
    static final String[] Android =
            new String[] { "item1", "item2", "item3", "item4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
      //  setContentView(R.layout.activity_shops);
        list_layout=(ListView)findViewById(R.id.activity_shops);
        list_layout.setAdapter(new ListAct(this,Android));

//        setListAdapter(new ListAct(this, Android));


    }



}
