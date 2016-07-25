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

import com.askerlap.emadahmed.checkup.R;

public class ListAct extends BaseAdapter{
    private final Context context;
    private final String[] values;

    public ListAct(Context context, String[] values) {

        this.context = context;
        this.values = values;
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
        name.setText(R.string.shopName);
        phone.setText(R.string.shopPhone);
        address.setText(R.string.shopAddress);
        lastVisit.setText(R.string.lastVisit);
        bill.setText(R.string.totalBill);

        return rowView;
    }

    @Override
    public int getCount() {
        return values.length;
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
