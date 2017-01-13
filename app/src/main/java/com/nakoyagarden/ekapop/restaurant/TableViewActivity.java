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

public class TableViewActivity extends AppCompatActivity {
    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jarrT;
    private RestaurantControl rs;
    ListView lvTvView;
    Button btnTvAdd;
    Boolean pageLoad=false;
    public ArrayList<Table> lTa = new ArrayList<Table>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    ProgressDialog pd;
    DatabaseSQLi daS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);
        pageLoad = true;

        lvTvView = (ListView)findViewById(R.id.lvTvView);
        lvTvView.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");

        btnTvAdd = (Button)findViewById(R.id.btnTvAdd);

        btnTvAdd.setText(getResources().getString(R.string.add)+getResources().getString(R.string.table));
        btnTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rs.taID ="";
                Intent s1 = new Intent(view.getContext(), TableAddActivity.class);
                s1.putExtra("RestaurantControl",rs);
                startActivityForResult(s1, 0);
            }
        });
        lvTvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    JSONObject catObj = (JSONObject) jarrT.get(i);
                    //String ID = catObj.getString("foods_id");
                    rs.taID = catObj.getString("table_id");
                }catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Intent s1 = new Intent(view.getContext(), TableAddActivity.class);
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
                jarrT = daS.TableSelectAll();
                setLvTable();
            }
        }else if(rs.AccessMode.equals("Internet")){
            if(!pageLoad){
                super.onResume();
                new retrieveTable().execute();
            }
        }else{
            if(!pageLoad){
                super.onResume();
                new retrieveTable().execute();
            }
        }
        ////    setLvFoods();
    }
    class retrieveTable extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrT = jsonparser.getJSONFromUrl(rs.hostGetTable,params);
            //rs.jarrR = jarrT.toString();
            //} catch (JSONException e) {
            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setLvTable();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(TableViewActivity.this);
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
    private void setLvTable(){
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //jarrR = jsonparser.getJSONFromUrl(rs.hostSelectFoods,params);
            //jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
            if(jarrT!=null){
                //jarrT =  new JSONArray(rs.jarrR);
                arrayList = new ArrayList<String>();
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                lTa.clear();
                for (int i = 0; i < jarrT.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrT.get(i);
                    Table ta = new Table();
                    ta.ID = catObj.getString(ta.dbID);
                    ta.Code = catObj.getString(ta.dbCode);
                    ta.Name = catObj.getString(ta.dbName);
                    ta.Remark = catObj.getString(ta.dbRemark);
                    ta.Active = catObj.getString(ta.dbActive);
                    ta.AreaID = catObj.getString(ta.dbAreaID);
                    ta.Sort1 = catObj.getString(ta.dbSort1);
                    lTa.add(ta);
                    //arrayList.add(f.Code+" "+f.Name+" "+f.Price+" "+f.Remark+" ร้าน "+rs.getResToName(f.ResId,"genid")+" ประเภท "+rs.getFoodsTypeToName(f.TypeId,"genid")+" สถานะ "+f.StatusFoods+" เครื่องพิมพ์ "+f.PrinterName);
                    arrayList.add(ta.Code+" "+ta.Name+""+rs.getAreaToName(ta.AreaID,"genid")+" "+ta.Remark);
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
                lvTvView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
