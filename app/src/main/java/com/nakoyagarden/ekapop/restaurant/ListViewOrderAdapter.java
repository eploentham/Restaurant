package com.nakoyagarden.ekapop.restaurant;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nakoyagarden.ekapop.restaurant.R;

import java.util.ArrayList;

/**
 * Created by ekapop on 9/20/2016.
 */
public class ListViewOrderAdapter extends ArrayAdapter<String> {
    private final Context context;
    //        private final String[] values;
    private final ArrayList<String> code;
    private final ArrayList<String> name;
    private final ArrayList<String> price;
    private final ArrayList<String> qty;
    private final ArrayList<String> remark;

    public ListViewOrderAdapter(Context context, ArrayList<String> code, ArrayList<String> name, ArrayList<String> price, ArrayList<String> qty, ArrayList<String> remark) {
        super(context, -1, code);
        this.context = context;
        this.code = code;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.remark = remark;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_order_layout, parent, false);
        TextView fRow = (TextView) rowView.findViewById(R.id.fRow);
        TextView fCode = (TextView) rowView.findViewById(R.id.fCode);
        TextView fName = (TextView) rowView.findViewById(R.id.fName);
        TextView fPrice = (TextView) rowView.findViewById(R.id.fPrice);
        TextView fQty = (TextView) rowView.findViewById(R.id.fQty);
        TextView fRes = (TextView) rowView.findViewById(R.id.fRes);
        TextView fRemark = (TextView) rowView.findViewById(R.id.fRemark);

        fRow.setTextSize(30);
        fCode.setTextSize(22);
        fName.setTextSize(22);
        fPrice.setTextSize(22);
        fQty.setTextSize(26);
        fRemark.setTextSize(14);
        fRes.setTextSize(14);
        fCode.setTextColor(Color.BLACK);
        fName.setTextColor(Color.BLACK);
        fPrice.setTextColor(Color.BLACK);
        fQty.setTextColor(Color.BLACK);
        fRemark.setTextColor(Color.BLACK);

        fRow.setText(String.valueOf(position+1));
        fCode.setText(code.get(position));
        fName.setText(name.get(position));
        fPrice.setText(price.get(position));
        fQty.setText(qty.get(position));
        if(remark.size()>position){
            fRemark.setText(remark.get(position));
        }
        return rowView;
    }
}
