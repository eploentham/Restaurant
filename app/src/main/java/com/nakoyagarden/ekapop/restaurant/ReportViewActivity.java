package com.nakoyagarden.ekapop.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReportViewActivity extends AppCompatActivity {
    private RestaurantControl rs;
    DatabaseSQLi daS;
    private int year,textSize=20;
    private int month;
    private int day;

    ListView list1;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList, alCode, alName, alPrice, alQty, aAmt;

    JSONArray jarr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view);

        list1 = (ListView)findViewById(R.id.list1);

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);

        Foods foo = new Foods();
        Bill bi = new Bill();
        BillDetail bid = new BillDetail();
        if(rs.flagReport.equals("DailyGroupByFoods")){
            try{
                alCode = new ArrayList<String>();
                alName = new ArrayList<String>();
                alPrice = new ArrayList<String>();
                alQty = new ArrayList<String>();
                aAmt = new ArrayList<String>();
                jarr = new JSONArray(rs.jarrR);
                for (int i = 0; i < jarr.length(); i++) {
                    JSONObject catObj = (JSONObject) jarr.get(i);
                    alCode.add(catObj.getString(bid.dbFoodsCode));
                    alName.add(catObj.getString(bid.dbFoodsName));
                    alPrice.add(catObj.getString(bid.dbPrice));
                    alQty.add(catObj.getString(bid.dbQty));
                    aAmt.add(catObj.getString(bid.dbAmt));
                }
                ListViewReportDailyFoods lva = new ListViewReportDailyFoods(getApplicationContext(),alCode,alName,alPrice,alQty,aAmt);
                list1.setAdapter(lva);
            }catch (JSONException e) {
                Log.e("ReportViewActivity", "Error parsing data " + e.toString());
            }
        }
    }
}
