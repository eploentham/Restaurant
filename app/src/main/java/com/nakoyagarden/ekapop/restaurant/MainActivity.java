package com.nakoyagarden.ekapop.restaurant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jarrA, jarrT, jarrR, jarrF;
    Button btnMInt;
    ImageButton btnMBill, btnMOrderV,btnCookV,btnOrderA;
    public RestaurantControl rs;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCookV = (ImageButton)findViewById(R.id.btnCookView);
        btnOrderA = (ImageButton)findViewById(R.id.btnOrderAdd);

//        btnMFt = (Button)findViewById(R.id.btnMFoodsType);
//        btnMTable = (Button)findViewById(R.id.btnMTable);
//        btnMArea = (Button)findViewById(R.id.btnMArea);
//        btnMRes = (Button)findViewById(R.id.btnMRes);
        btnMBill = (ImageButton)findViewById(R.id.btnMBill);
        btnMOrderV = (ImageButton)findViewById(R.id.btnMOrderView);
//        btnMFt.setText(getResources().getString(R.string.add)+getResources().getString(R.string.type)+getResources().getString(R.string.foods));
//        btnMTable.setText(getResources().getString(R.string.add)+getResources().getString(R.string.table));
//        btnMArea.setText(getResources().getString(R.string.add)+getResources().getString(R.string.area));
//        btnMRes.setText(getResources().getString(R.string.add)+getResources().getString(R.string.restaurant));
        btnMInt = (Button)findViewById(R.id.btnMInt);
        //btnMBill.setText(R.string.billcheck);

        btnOrderA.setBackgroundResource(R.mipmap.menu_icon1);
        btnCookV.setBackgroundResource(R.mipmap.menu_cook);
        btnMBill.setBackgroundResource(R.mipmap.menu_bill);
        btnMOrderV.setBackgroundResource(R.mipmap.menu_bill);
        //btnMFoodsType.setText(Re);
        rs = new RestaurantControl();
        try {
            final int READ_BLOCK_SIZE = 100;
            FileInputStream fileIn=openFileInput("initial.cnf");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
//                    txtIaHost.setText(s);
            String[] p = s.split(";");
            if(p.length>0){
                rs.hostIP = p[0].replace("host=","");
//                        txtIaPrint.setText(p[1].replace("printer=",""));
//                        rs.hostIP = s;
            }
            rs.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //btnCookV.setText(R.string.kitchen);
        //btnOrderA.setText("");

        btnOrderA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent s1 = new Intent(view.getContext(), EsernOrderAddActivity.class);
                rs.refresh();
                startActivityForResult(new Intent(view.getContext(), OrderAddActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnCookV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_delete_product="http://10.0.1.25:80/restaurant/testjson.php";
                //JSONObject json = jsonParser.makeHttpRequest(url_delete_product, "POST", params);

            }
        });

        btnMBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), BillAddActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnMOrderV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), OrderViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnMInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), InitailActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        ArrayList<ItemData> list=new ArrayList<>();
        list.add(new ItemData("Khr",R.drawable.idel_blue));
        list.add(new ItemData("Usd",R.drawable.idel_red));
        list.add(new ItemData("Jpy",R.drawable.menu_icon));
        list.add(new ItemData("Aud",R.drawable.menu_icon));

        new retrieveArea().execute();
        new retrieveTable().execute();
        new retrieveRes().execute();
        new retrieveFoods().execute();
        new retrievePrinterName().execute();
        new retrieveFoodsType().execute();

//        Spinner sp=(Spinner)findViewById(R.id.spinner);
//        SpinnerAdapter adapter=new SpinnerAdapter(this,
//                R.layout.spinner_layout,R.id.cbotxt,list);
//        sp.setAdapter(adapter);
    }
    class retrieveArea extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                jarrA = jsonparser.getJSONFromUrl(rs.hostGetArea,params);
                if(jarrA!=null){
                    rs.sCboArea.clear();
                    rs.sArea.clear();
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    for (int i = 0; i < jarrA.length(); i++) {
                        JSONObject catObj = (JSONObject) jarrA.get(i);
                        rs.sCboArea.add(catObj.getString("area_name"));
                        rs.sArea.add(catObj.getString("area_id")+"@"+catObj.getString("area_code")+"@"+catObj.getString("area_name"));
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
            pd = new ProgressDialog(MainActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Loading...");
            pd.setMessage("Loading images...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();
        }
    }
    public class retrieveTable extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                jarrT = jsonparser.getJSONFromUrl(rs.hostGetTable,params);
                if(jarrT!=null){
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    rs.sCboTable.clear();
                    rs.sTable.clear();
                    for (int i = 0; i < jarrT.length(); i++) {
                        JSONObject catObj = (JSONObject) jarrT.get(i);
                        rs.sCboTable.add(catObj.getString("table_name"));
                        rs.sTable.add(catObj.getString("table_id")+"@"+catObj.getString("table_code")+"@"+catObj.getString("table_name")+"@"+catObj.getString("status_use"));
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){

        }
        @Override
        protected void onPreExecute() {

        }
    }
    class retrieveRes extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
                if(jarrR!=null){
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    rs.sCboRes.clear();
                    rs.sRes.clear();
                    for (int i = 0; i < jarrR.length(); i++) {
                        JSONObject catObj = (JSONObject) jarrR.get(i);
                        rs.sCboRes.add(catObj.getString("res_name"));
                        rs.sRes.add(catObj.getString("res_id")+"@"+catObj.getString("res_code")+"@"+catObj.getString("res_name"));
                        if(catObj.getString("default_res").equals("1")){
                            rs.ResName = catObj.getString("res_name");
                            rs.ReceiptH1 = catObj.getString("receipt_header1");
                            rs.ReceiptH2 = catObj.getString("receipt_header2");
                            rs.ReceiptF1 = catObj.getString("receipt_footer1");
                            rs.ReceiptF2 = catObj.getString("receipt_footer2");
                        }
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){

        }
        @Override
        protected void onPreExecute() {

        }
    }

    /**
     * ส่งobject ไปทาง RestaurantControl แล้วError
     * เลยเปลี่ยนเป็นส่งไปทาง String แทน
     */
    public class retrieveFoods extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                jarrF = jsonparser.getJSONFromUrl(rs.hostGetFoods,new ArrayList<NameValuePair>());
                rs.jarrF = jarrF.toString();
                //jarrF = jsonparser.getJSONFromUrl(rs.hostGetRes,params);

            //} catch (JSONException e) {
                // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;

        }
        @Override
        protected void onPreExecute() {

        }
    }
    class retrievePrinterName extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                jarrR = jsonparser.getJSONFromUrl(rs.hostGetPrinterName,params);
                if(jarrR!=null){
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    rs.sCboPrinter.clear();
                    for (int i = 0; i < jarrR.length(); i++) {
                        JSONObject catObj = (JSONObject) jarrR.get(i);
                        rs.sCboPrinter.add(catObj.getString("printer_name"));
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){

        }
        @Override
        protected void onPreExecute() {

        }
    }
    class retrieveFoodsType extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                jarrR = jsonparser.getJSONFromUrl(rs.hostGetFoodsType,params);
                if(jarrR!=null){
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    rs.sCboFoodsType.clear();
                    for (int i = 0; i < jarrR.length(); i++) {
                        JSONObject catObj = (JSONObject) jarrR.get(i);
                        rs.sCboFoodsType.add(catObj.getString("foods_type_name"));
                        rs.sFoodsType.add(catObj.getString("foods_type_id")+"@"+catObj.getString("foods_type_code")+"@"+catObj.getString("foods_type_name"));
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {

        }
    }
}
