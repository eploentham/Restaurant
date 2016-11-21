package com.nakoyagarden.ekapop.restaurant;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodsViewActivity extends AppCompatActivity {
    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jarrF;
    private RestaurantControl rs;
    public ArrayList<Foods> lFoo = new ArrayList<Foods>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    Boolean pageLoad=false;
    ListView lvFoods;
    Button btnFoodsA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_view);
        pageLoad = true;

        lvFoods = (ListView)findViewById(R.id.lvFoods);
        lvFoods.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));
        //GridLayout linearLayout = (GridLayout) findViewById(R.id.ac//;
        //linearLayout.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");

        btnFoodsA = (Button)findViewById(R.id.btnFoodsAdd);

        btnFoodsA.setText(getResources().getString(R.string.add)+getResources().getString(R.string.desc)+getResources().getString(R.string.foods));

        btnFoodsA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rs.fooID="";
                Intent s1 = new Intent(view.getContext(), FoodsAddActivity.class);
                s1.putExtra("RestaurantControl",rs);
                startActivityForResult(s1, 0);
            }
        });
        lvFoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //RelativeLayout relativeLayout = (RelativeLayout) view.getParent();
                //TextView textView = (TextView) relativeLayout.getChildAt(0);
                //ImageView imaegView = (ImageView) relativeLayout.getChildAt(1);
                //textView.setTextColor(Color.RED);
                try{
                    JSONObject catObj = (JSONObject) jarrF.get(i);
                    //String ID = catObj.getString("foods_id");
                    rs.fooID = catObj.getString("foods_id");
                }catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Intent s1 = new Intent(view.getContext(), FoodsAddActivity.class);
                s1.putExtra("RestaurantControl",rs);
                startActivityForResult(s1, 0);
            }
        });
        //new retrieveFoods().execute();
        //setLvFoods();
        pageLoad=false;
    }
    @Override
    protected void onResume() {
        if(!pageLoad){
            super.onResume();
            new retrieveFoods().execute();
        }
    ////    setLvFoods();
    }
    class retrieveFoods extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoods,new ArrayList<NameValuePair>());
                rs.jarrF = jarrF.toString();
            //} catch (JSONException e) {
                // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setLvFoods();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    private void setLvFoods(){
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoods,params);
            //jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
            if(rs.jarrF!=null){
                jarrF =  new JSONArray(rs.jarrF);
                arrayList = new ArrayList<String>();
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                lFoo.clear();
                for (int i = 0; i < jarrF.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrF.get(i);
                    Foods f = new Foods();
                    f.ID = catObj.getString("foods_id");
                    f.Code = catObj.getString("foods_code");
                    f.Name = catObj.getString("foods_name");
                    f.Remark = catObj.getString("remark");
                    f.ResCode = catObj.getString("res_code");
                    f.Price = catObj.getString("foods_price");
                    f.PrinterName = catObj.getString("printer_name");
                    f.Active = catObj.getString("active");
                    f.ResId = catObj.getString("res_id");
                    f.StatusFoods = catObj.getString("status_foods");
                    f.TypeId = catObj.getString("foods_type_id");
                    lFoo.add(f);
                    //arrayList.add(f.Code+" "+f.Name+" "+f.Price+" "+f.Remark+" ร้าน "+rs.getResToName(f.ResId,"id")+" ประเภท "+rs.getFoodsTypeToName(f.TypeId,"id")+" สถานะ "+f.StatusFoods+" เครื่องพิมพ์ "+f.PrinterName);
                    arrayList.add(f.Code+" "+f.Name+" "+getResources().getString(R.string.price)+" "+f.Price+" "+getResources().getString(R.string.remark)+" "+f.Remark+" ร้าน "+rs.getResToName(f.ResId,"id")+" ประเภท "+rs.getFoodsTypeToName(f.TypeId,"id")+" สถานะ "+f.StatusFoods+" เครื่องพิมพ์ "+f.PrinterName);
                }
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        // Get the current item from ListView
                        View view = super.getView(position,convertView,parent);

                        // Get the Layout Parameters for ListView Current Item View
                        ViewGroup.LayoutParams params = view.getLayoutParams();

                        // Set the height of the Item View
                        params.height = 60;
                        view.setLayoutParams(params);

                        return view;
                    }
                };
                lvFoods = (ListView)findViewById(R.id.lvFoods);
                lvFoods.setAdapter(adapter);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
