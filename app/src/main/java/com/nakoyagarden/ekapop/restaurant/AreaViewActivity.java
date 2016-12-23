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

public class AreaViewActivity extends AppCompatActivity {
    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jarrR;
    private RestaurantControl rs;
    ListView lvAvView;
    Button btnAvAdd;
    Boolean pageLoad=false;
    public ArrayList<Area> lArea = new ArrayList<Area>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_view);
        pageLoad = true;

        lvAvView = (ListView)findViewById(R.id.lvAvView);
        lvAvView.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");

        btnAvAdd = (Button)findViewById(R.id.btnAvAdd);

        btnAvAdd.setText(getResources().getString(R.string.add)+getResources().getString(R.string.area));
        btnAvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rs.arID="";
                Intent s1 = new Intent(view.getContext(), AreaAddActivity.class);
                s1.putExtra("RestaurantControl",rs);
                startActivityForResult(s1, 0);
            }
        });
        lvAvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //RelativeLayout relativeLayout = (RelativeLayout) view.getParent();
                //TextView textView = (TextView) relativeLayout.getChildAt(0);
                //ImageView imaegView = (ImageView) relativeLayout.getChildAt(1);
                //textView.setTextColor(Color.RED);
                try{
                    JSONObject catObj = (JSONObject) jarrR.get(i);
                    //String ID = catObj.getString("foods_id");
                    rs.arID = catObj.getString("area_id");
                }catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Intent s1 = new Intent(view.getContext(), AreaAddActivity.class);
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
            new retrieveArea().execute();
        }
        ////    setLvFoods();
    }
    class retrieveArea extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrR = jsonparser.getJSONFromUrl(rs.hostGetArea,new ArrayList<NameValuePair>());
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
            setLvArea();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(AreaViewActivity.this);
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
    private void setLvArea(){
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoods,params);
            //jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
            if(jarrR!=null){
                //jarrF =  new JSONArray(rs.jarrR);
                arrayList = new ArrayList<String>();
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                lArea.clear();
                for (int i = 0; i < jarrR.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrR.get(i);
                    Area a = new Area();
                    a.ID = catObj.getString("area_id");
                    a.Code = catObj.getString("area_code");
                    a.Name = catObj.getString("area_name");
                    a.Remark = catObj.getString("remark");
                    a.Active = catObj.getString("active");
                    a.Sort1 = catObj.getString("sort1");
                    lArea.add(a);
                    //arrayList.add(f.Code+" "+f.Name+" "+f.Price+" "+f.Remark+" ร้าน "+rs.getResToName(f.ResId,"id")+" ประเภท "+rs.getFoodsTypeToName(f.TypeId,"id")+" สถานะ "+f.StatusFoods+" เครื่องพิมพ์ "+f.PrinterName);
                    arrayList.add(a.Code+" "+a.Name+" "+a.Remark);
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
                lvAvView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
