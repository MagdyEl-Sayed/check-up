package com.example.gm7.checkup;

/**
 * Created by GM7 on 25/07/2016.
 */


import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.askerlap.emadahmed.checkup.R;

import java.util.ArrayList;

public class ListAct extends BaseAdapter{
    private final Context context;
    private ArrayList<String> Addressvalues,PhonesValues,namesValues,lastvisitValue;


    public ListAct(Context context, ArrayList<String> values,ArrayList<String> values2,ArrayList<String> values3 ,ArrayList<String> value4) {

        this.context = context;
        this.Addressvalues = values;
        this.PhonesValues=values2;
        this.namesValues=values3;
        this.lastvisitValue=value4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView name,address,phone,lastVisit,bill;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.custom_listview, parent, false);
        name=(TextView)rowView.findViewById(R.id.tv_shopName);
        address=(TextView)rowView.findViewById(R.id.tv_shopAddress);
        phone=(TextView)rowView.findViewById(R.id.tv_ShopPhone);
        lastVisit=(TextView)rowView.findViewById(R.id.tv_lastVisit);
        bill=(TextView)rowView.findViewById(R.id.tv_totalBill);
        name.setText(context.getResources().getString(R.string.shopName)+" "+namesValues.get(position));
        phone.setText(context.getResources().getString(R.string.shopPhone)+" "+PhonesValues.get(position)) ;
        address.setText(context.getResources().getString(R.string.shopAddress)+" "+Addressvalues.get(position));
        lastVisit.setText(context.getResources().getString(R.string.lastVisit)+" "+lastvisitValue.get(position));
        bill.setText(R.string.totalBill);

        return rowView;
    }

    @Override
    public int getCount() {
        return PhonesValues.size();

    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
