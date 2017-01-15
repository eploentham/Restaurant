package com.nakoyagarden.ekapop.restaurant;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nakoyagarden.ekapop.restaurant.R;

import java.util.ArrayList;

/**
 * Created by ekapop on 1/15/2017.
 */

public class ListViewReportDailyFoods extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> code;
    private final ArrayList<String> name;
    private final ArrayList<String> price;
    private final ArrayList<String> qty;
    private final ArrayList<String> amt;
    public ListViewReportDailyFoods(Context context, ArrayList<String> code, ArrayList<String> name, ArrayList<String> price, ArrayList<String> qty, ArrayList<String> amt) {
        super(context, -1, code);
        this.context = context;
        this.code = code;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.amt = amt;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_report_daily_foods_layout, parent, false);
        TextView dfRow1 = (TextView) rowView.findViewById(R.id.dfRow1);
        TextView dfFoodsCode = (TextView) rowView.findViewById(R.id.dfFoodsCode);
        TextView dfFoodsName = (TextView) rowView.findViewById(R.id.dfFoodsName);
        TextView dfPrice = (TextView) rowView.findViewById(R.id.dfPrice);
        TextView dfQty = (TextView) rowView.findViewById(R.id.dfQty);
        TextView dfAmt = (TextView) rowView.findViewById(R.id.dfAmt);

        dfRow1.setTextSize(22);
        dfFoodsCode.setTextSize(22);
        dfFoodsName.setTextSize(22);
        dfPrice.setTextSize(22);
        dfQty.setTextSize(26);
        dfAmt.setTextSize(14);

        dfFoodsCode.setTextColor(Color.BLACK);
        dfFoodsName.setTextColor(Color.BLACK);
        dfPrice.setTextColor(Color.BLACK);
        dfQty.setTextColor(Color.BLACK);
        dfAmt.setTextColor(Color.BLACK);

        dfRow1.setText(String.valueOf(position+1));
        dfFoodsCode.setText(code.get(position));
        dfFoodsName.setText(name.get(position));
        dfPrice.setText(price.get(position));
        dfQty.setText(qty.get(position));
        dfAmt.setText(amt.get(position));

        return rowView;
    }
}
