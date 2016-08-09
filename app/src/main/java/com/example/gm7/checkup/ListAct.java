package com.example.gm7.checkup;

/**
 * Created by GM7 on 25/07/2016.
 */


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askerlap.emadahmed.checkup.R;

import java.util.ArrayList;

public class ListAct extends BaseAdapter{
    public  static int SIZE;
    private final Context context;
    private ArrayList<String> Addressvalues,PhonesValues,namesValues,DateValue,itemNamesValue;
    private DBSalesItems salesHelper;

    public ListAct(Context context, ArrayList<String> addressValues,ArrayList<String> PhoneValues
            ,ArrayList<String> dateValues,ArrayList<String>shopnameNalues,ArrayList<String> itemnameValues) {

        this.context = context;
        this.Addressvalues = addressValues;
        this.PhonesValues=PhoneValues;
         this.DateValue=dateValues;
         this.namesValues=shopnameNalues;
        this.itemNamesValue=itemnameValues;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        salesHelper=new DBSalesItems(context);
        namesValues=new DBShopsHelper(context).getShopNames(DateValue.get(position));
       itemNamesValue= salesHelper.getItems(namesValues.get(position-1));
        SIZE=itemNamesValue.size();
        View rowView = inflater.inflate(R.layout.dynamic_custom_layout, parent, false);
        LinearLayout parentLayout=(LinearLayout)rowView.findViewById(R.id.parent_layout);
        LinearLayout [] childsLayout=new LinearLayout[PhonesValues.size()];
        LinearLayout [] ItemDetailsLayout =new LinearLayout[PhonesValues.size()];
        TextView [] fixedView=new TextView[3];
        TextView itemsDetails[] =new TextView[itemNamesValue.size()];
        for( int i=0 ;i<3;i++){
            fixedView[i]=new TextView(context);
            fixedView[i].setTextSize(17);
            fixedView[i].setTextColor(Color.BLACK);
        }
        fixedView[0].setGravity(Gravity.CENTER);
        fixedView[0].setPadding(0,10,0,0);
        fixedView[0].setText(new DBShopsHelper(context).getShopNames(DateValue.get(position)).toString());
        fixedView[1].setPadding(10,0,0,0);
        fixedView[1].setText(context.getResources().getString(R.string.bill_date)+DateValue.get(position).substring(6));
        childsLayout[position]=new LinearLayout(context);
        childsLayout[position].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        childsLayout[position].setOrientation(LinearLayout.VERTICAL);
        parentLayout.addView(fixedView[0]);
        childsLayout[position].addView(fixedView[1]);
        ///////////////////////////////////////////////////////////
        ItemDetailsLayout[position]=new LinearLayout(context);
        ItemDetailsLayout[position].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ItemDetailsLayout[position].setOrientation(LinearLayout.HORIZONTAL);
        for(int i=0;i<itemNamesValue.size();i++){
            itemsDetails[i]=new TextView(context);
            itemsDetails[i].setTextSize(17);
            itemsDetails[i].setTextColor(Color.BLACK);
            itemsDetails[i].setText(itemNamesValue.get(i));
            ItemDetailsLayout[position].addView(itemsDetails[i]);

        }
        //////////////////////////////////////////////////////////
        parentLayout.addView(childsLayout[position]);
parentLayout.addView(ItemDetailsLayout[position]);

//        name=(TextView)rowView.findViewById(R.id.tv_shopName);
//        address=(TextView)rowView.findViewById(R.id.tv_shopAddress);
//        phone=(TextView)rowView.findViewById(R.id.tv_ShopPhone);
//        lastVisit=(TextView)rowView.findViewById(R.id.tv_lastVisit);
//        bill=(TextView)rowView.findViewById(R.id.tv_totalBill);
//        name.setText(context.getResources().getString(R.string.shopName)+" "+namesValues.get(position));
//        phone.setText(context.getResources().getString(R.string.shopPhone)+" "+PhonesValues.get(position)) ;
//        address.setText(context.getResources().getString(R.string.shopAddress)+" "+Addressvalues.get(position));
//        lastVisit.setText(context.getResources().getString(R.string.lastVisit)+" "+lastvisitValue.get(position));
//        bill.setText(R.string.totalBill);

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
