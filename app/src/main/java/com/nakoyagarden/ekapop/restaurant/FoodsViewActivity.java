package com.nakoyagarden.ekapop.restaurant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    ProgressDialog pd;
    DatabaseSQLi daS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_view);
        pageLoad = true;

        lvFoods = (ListView)findViewById(R.id.lvFoods);
        lvFoods.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));
        //GridLayout linearLayout = (GridLayout) findViewById(R.genid.ac//;
        //linearLayout.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");

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
        if(rs.AccessMode.equals("Standalone")) {
            if(!pageLoad) {
                super.onResume();
                jarrF = daS.FoodsSelectAll();
                setLvFoods();
            }
        }else if(rs.AccessMode.equals("Internet")){
            if(!pageLoad){
                super.onResume();
                new retrieveFoods().execute();
            }
        }else{
            if(!pageLoad){
                super.onResume();
                new retrieveFoods().execute();
            }
        }
    ////    setLvFoods();
    }
    class retrieveFoods extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoods,params);
//            rs.jarrR = jarrR.toString();
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
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(FoodsViewActivity.this);
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
    private void setLvFoods(){
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //jarrR = jsonparser.getJSONFromUrl(rs.hostSelectFoods,params);
            //jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
            if(jarrF!=null){
//                jarrR =  new JSONArray(rs.jarrR);
                arrayList = new ArrayList<String>();
                Foods f = new Foods();
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                lFoo.clear();
                for (int i = 0; i < jarrF.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrF.get(i);
                    f = new Foods();
                    f.ID = catObj.getString(f.dbID);
                    f.Code = catObj.getString(f.dbCode);
                    f.Name = catObj.getString(f.dbName);
                    f.Remark = catObj.getString(f.dbRemark);
                    f.ResCode = catObj.getString(f.dbResCode);
                    f.Price = catObj.getString(f.dbPrice);
                    f.PrinterName = catObj.getString(f.dbPrinterName);
                    f.Active = catObj.getString(f.dbActive);
                    f.ResId = catObj.getString(f.dbResId);
                    f.StatusFoods = catObj.getString(f.dbStatusFoods);
                    f.TypeId = catObj.getString(f.dbTypeId);
                    lFoo.add(f);
                    //arrayList.add(f.Code+" "+f.Name+" "+f.Price+" "+f.Remark+" ร้าน "+rs.getResToName(f.ResId,"genid")+" ประเภท "+rs.getFoodsTypeToName(f.TypeId,"genid")+" สถานะ "+f.StatusFoods+" เครื่องพิมพ์ "+f.PrinterName);
                    arrayList.add(f.Code+" "+f.Name+" "+getResources().getString(R.string.price)+" "+f.Price+" "+getResources().getString(R.string.remark)+" "+f.Remark+" ร้าน "+rs.getResToName(f.ResId,"genid")+" ประเภท "+rs.getFoodsTypeToName(f.TypeId,"genid")+" สถานะ "+f.StatusFoods+" เครื่องพิมพ์ "+f.PrinterName);
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
            Log.e("setLvFoods ",e.getMessage());
        }

    }
}
