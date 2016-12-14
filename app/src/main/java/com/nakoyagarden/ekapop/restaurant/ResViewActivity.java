package com.nakoyagarden.ekapop.restaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResViewActivity extends AppCompatActivity {
    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jarrR;
    private RestaurantControl rs;
    ListView lvResView;
    Button btnResAdd;
    Boolean pageLoad=false;
    public ArrayList<Restaurant> lRes = new ArrayList<Restaurant>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_view);
        pageLoad = true;
        lvResView = (ListView)findViewById(R.id.lvRvView);
        lvResView.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        btnResAdd = (Button)findViewById(R.id.btnRvAdd);
        btnResAdd.setText(getResources().getString(R.string.add)+getResources().getString(R.string.restaurant));
        btnResAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rs.resID ="";
                Intent s1 = new Intent(view.getContext(), ResAddActivity.class);
                s1.putExtra("RestaurantControl",rs);
                startActivityForResult(s1, 0);
            }
        });
        lvResView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    JSONObject catObj = (JSONObject) jarrR.get(i);
                    //String ID = catObj.getString("foods_id");
                    rs.resID = catObj.getString("res_id");
                }catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Intent s1 = new Intent(view.getContext(), ResAddActivity.class);
                s1.putExtra("RestaurantControl",rs);
                startActivityForResult(s1, 0);
            }
        });

        pageLoad=false;
    }
    @Override
    protected void onResume() {
        if(!pageLoad){
            super.onResume();
            new retrieveRes().execute();
            setLvRes();
        }
    }
    class retrieveRes extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
            //rs.jarrF = jarrF.toString();
            //} catch (JSONException e) {
            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setLvRes();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    private void setLvRes(){
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoods,params);
            //jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
            if(jarrR!=null){
                //jarrF =  new JSONArray(rs.jarrF);
                arrayList = new ArrayList<String>();
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                lRes.clear();
                for (int i = 0; i < jarrR.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrR.get(i);
                    Restaurant a = new Restaurant();
                    a.ID = catObj.getString("res_id");
                    a.Code = catObj.getString("res_code");
                    a.Name = rs.StringNull(catObj.getString("res_name")).trim();
                    a.Remark = rs.StringNull(catObj.getString("remark")).trim();
                    a.Active = catObj.getString("active");
                    a.RH1 = rs.StringNull(catObj.getString("receipt_header1")).trim();
                    a.RH2 = rs.StringNull(catObj.getString("receipt_header2")).trim();
                    a.RF1 = rs.StringNull(catObj.getString("receipt_footer1")).trim();
                    a.RF2 = rs.StringNull(catObj.getString("receipt_footer2")).trim();
                    //a.AreaID = catObj.getString("area_id");
                    //a.Sort1 = catObj.getString("sort1");
                    lRes.add(a);
                    //arrayList.add(f.Code+" "+f.Name+" "+f.Price+" "+f.Remark+" ร้าน "+rs.getResToName(f.ResId,"id")+" ประเภท "+rs.getFoodsTypeToName(f.TypeId,"id")+" สถานะ "+f.StatusFoods+" เครื่องพิมพ์ "+f.PrinterName);
                    arrayList.add(a.Code+" "+a.Name+" "+a.Remark+" "+a.RH1+" "+a.RH2+" "+a.RF1+" "+a.RF2);
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
                //lvAvView = (ListView)findViewById(R.id.lvFoods);
                lvResView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
