package com.example.gm7.checkup;

/**
 * Created by GM7 on 25/07/2016.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.askerlap.emadahmed.checkup.R;

public class ListAct extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public ListAct(Context context, String[] values) {
        super(context, R.layout.activity_shops, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_shops, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values[position]);

        // Change icon based on name
        String s = values[position];

        System.out.println(s);

        if (s.equals("item1")) {
            imageView.setImageResource(R.drawable.back_app);
        }

        return rowView;
    }
}
