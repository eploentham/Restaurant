package com.nakoyagarden.ekapop.restaurant;

import android.app.ProgressDialog;
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
    ProgressDialog pd;
    DatabaseSQLi daS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_view);
        pageLoad = true;
        lvResView = (ListView)findViewById(R.id.lvRvView);
        lvResView.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");

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
        if(rs.AccessMode.equals("Standalone")) {
            if(!pageLoad) {
                super.onResume();
                jarrR = daS.ResSelectAll();
                setLvRes();
            }
        }else if(rs.AccessMode.equals("Internet")){
            if(!pageLoad){
                super.onResume();
                new retrieveRes().execute();
                //setLvRes();
            }
        }else{
            if(!pageLoad){
                super.onResume();
                new retrieveRes().execute();
                //setLvRes();
            }
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
            //rs.jarrR = jarrR.toString();
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
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(ResViewActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Loading...");
            pd.setMessage("Loading Bill ...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();
        }
    }
    private void setLvRes(){
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //jarrR = jsonparser.getJSONFromUrl(rs.hostSelectFoods,params);
            //jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
            if(jarrR!=null){
                //jarrR =  new JSONArray(rs.jarrR);
                arrayList = new ArrayList<String>();

                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                lRes.clear();
                for (int i = 0; i < jarrR.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrR.get(i);
                    Restaurant a = new Restaurant();
                    a.ID = catObj.getString(a.dbID);
                    a.Code = catObj.getString(a.dbCode);
                    a.Name = rs.StringNull(catObj.getString(a.dbName)).trim();
                    a.Remark = rs.StringNull(catObj.getString(a.dbRemark)).trim();
                    a.Active = catObj.getString(a.dbActive);
                    a.RH1 = rs.StringNull(catObj.getString(a.dbRH1)).trim();
                    a.RH2 = rs.StringNull(catObj.getString(a.dbRH2)).trim();
                    a.RF1 = rs.StringNull(catObj.getString(a.dbRF1)).trim();
                    a.RF2 = rs.StringNull(catObj.getString(a.dbRF2)).trim();
                    //a.AreaID = catObj.getString("area_id");
                    //a.Sort1 = catObj.getString("sort1");
                    lRes.add(a);
                    //arrayList.add(f.Code+" "+f.Name+" "+f.Price+" "+f.Remark+" ร้าน "+rs.getResToName(f.ResId,"genid")+" ประเภท "+rs.getFoodsTypeToName(f.TypeId,"genid")+" สถานะ "+f.StatusFoods+" เครื่องพิมพ์ "+f.PrinterName);
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
                //lvAvView = (ListView)findViewById(R.genid.lvFoods);
                lvResView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
