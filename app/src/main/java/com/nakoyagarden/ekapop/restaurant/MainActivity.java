package com.nakoyagarden.ekapop.restaurant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jarrA, jarrT, jarrR, jarrF,jarrU;
    //Button btnMInt;
    ImageButton btnMBill, btnMOrderV,btnCookV,btnOrderA, btnMCloseDay, btnMInt;
    TextView lbMMessage;
    public RestaurantControl rs;
    ProgressDialog pd;
    Boolean fileExit=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCookV = (ImageButton)findViewById(R.id.btnCookView);
        btnOrderA = (ImageButton)findViewById(R.id.btnOrderAdd);

//        btnMFt = (Button)findViewById(R.id.btnMFoodsType);
//        btnMTable = (Button)findViewById(R.id.btnMTable);
//        btnMArea = (Button)findViewById(R.id.btnMArea);
        lbMMessage = (TextView)findViewById(R.id.lbMMessage);
        btnMBill = (ImageButton)findViewById(R.id.btnMBill);
        //btnMOrderV = (ImageButton)findViewById(R.id.btnMOrderView);
        btnMCloseDay = (ImageButton)findViewById(R.id.btnMCloseDay);
//        btnMFt.setText(getResources().getString(R.string.add)+getResources().getString(R.string.type)+getResources().getString(R.string.foods));
//        btnMTable.setText(getResources().getString(R.string.add)+getResources().getString(R.string.table));
//        btnMArea.setText(getResources().getString(R.string.add)+getResources().getString(R.string.area));
//        btnMRes.setText(getResources().getString(R.string.add)+getResources().getString(R.string.restaurant));
        btnMInt = (ImageButton)findViewById(R.id.btnMInt);
        //btnMBill.setText(R.string.billcheck);

        btnOrderA.setBackgroundResource(R.mipmap.menu_icon1);
        btnCookV.setBackgroundResource(R.mipmap.menu_cook);
        btnMBill.setBackgroundResource(R.mipmap.menu_bill);
//        btnMOrderV.setBackgroundResource(R.mipmap.menu_bill);
        btnMCloseDay.setBackgroundResource(R.mipmap.menu_closeday);
        btnMInt.setBackgroundResource(R.mipmap.menu_int);
        lbMMessage.setVisibility(View.INVISIBLE);
        //btnMFoodsType.setText(Re);
        rs = new RestaurantControl();
        rs.pageLoad=false;
        btnMInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), InitailActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });

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
//                txtIaHost.setText(p[0].replace("host=",""));
                //txtIaPrint.setText(p[1].replace("printer=",""));
                rs.hostPORT =  p[4].replace("PortNumber=","").replace("\n","");
                rs.hostWebDirectory =p[5].replace("WebDirectory=","").replace("\n","");
                rs.UserDB =p[6].replace("UserDB=","").replace("\n","");
                rs.PasswordDB =p[7].replace("PasswordDB=","").replace("\n","");
//                txtIaTaxID.setText(p[3].replace("TaxID=",""));
//                txtIaPortID.setText(p[4].replace("PortNumber=",""));
//                txtIaWebDirectory.setText(p[5].replace("WebDirectory=",""));
            }
            fileIn.close();
            rs.refresh();
//            }
        } catch (Exception e) {
            e.printStackTrace();
//            fileErr=true;
        }
//        if(fileErr)
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
//        btnMOrderV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivityForResult(new Intent(view.getContext(), OrderViewActivity.class).putExtra("RestaurantControl",rs), 0);
//            }
//        });
        btnMCloseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), CloseDayAddActivity.class).putExtra("RestaurantControl",rs), 0);
            }

        });

        ArrayList<ItemData> list=new ArrayList<>();
        list.add(new ItemData("Khr",R.drawable.idel_blue));
        list.add(new ItemData("Usd",R.drawable.idel_red));
        list.add(new ItemData("Jpy",R.drawable.menu_icon));
        list.add(new ItemData("Aud",R.drawable.menu_icon));

//        String txt = rs.hostIP;
//        txt = txt.replace("http://","").trim();

        if(!rs.hostIP.equals("")) {
            new chkServerReachable().execute();
        }

//        pageLoad=false;
//        Spinner sp=(Spinner)findViewById(R.id.spinner);
//        SpinnerAdapter adapter=new SpinnerAdapter(this,
//                R.layout.spinner_layout,R.id.cbotxt,list);
//        sp.setAdapter(adapter);
    }
//    @Override
//    protected void onResume(){
//        if(!rs.hostIP.equals("") && rs.pageLoad) {
//            new retrieveArea().execute();
//            new retrieveTable().execute();
//            new retrieveRes().execute();
//            new retrieveFoods().execute();
//            new retrievePrinterName().execute();
//            new retrieveFoodsType().execute();
//        }
//    }
    class retrieveArea extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
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

        }
    }
    public class retrieveTable extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
//                if(!rs.hostIP.equals("")) {
//
//                }
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
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
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
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
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
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
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
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                jarrR = jsonparser.getJSONFromUrl(rs.hostGetFoodsType,params);
                if(jarrR!=null){
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    rs.sCboFoodsType.clear();
                    rs.sFoodsType.clear();
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
    class retrieveUser extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                jarrU = jsonparser.getJSONFromUrl(rs.hostGetUser,params);
                if(jarrU!=null){
                    rs.sCboUser.clear();
                    rs.sUser.clear();
                    for (int i = 0; i < jarrU.length(); i++) {
                        JSONObject catObj = (JSONObject) jarrU.get(i);
                        rs.sCboUser.add(catObj.getString("user_name"));
                        rs.sUser.add(catObj.getString("user_id")+"@"+catObj.getString("user_login")+"@"+catObj.getString("user_name")+"@"+
                                catObj.getString("password")+"@"+catObj.getString("privilege")+"@"+catObj.getString("remark"));
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
    class chkServerReachable extends AsyncTask<String,String,String>{
        Boolean chk = false;
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            Socket socket;
//            final String host = "10.0.1.51";
            final int port = 80;
            final int timeout = 20000;   // 10 seconds

            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(rs.hostIP, port), timeout);
                chk=true;
            }
            catch (UnknownHostException uhe) {
                chk = false;
                Log.e("ServerSock", "I couldn't resolve the host you've provided!");
                pd.dismiss();
                ab = "ServerSock I couldn't resolve the host you've provided!";
            }
            catch (SocketTimeoutException ste) {
                chk = false;
                Log.e("ServerSock", "After a reasonable amount of time, I'm not able to connect, Server is probably down!");
                pd.dismiss();
                ab = "ServerSock After a reasonable amount of time, I'm not able to connect, Server is probably down!" ;
            }
            catch (IOException ioe) {
                chk = false;
                Log.e("ServerSock", "Hmmm... Sudden disconnection, probably you should start again!");
                pd.dismiss();
                ab = "ServerSock Hmmm... Sudden disconnection, probably you should start again!";
            }
            return ab+ rs.hostIP;
        }
        @Override
        protected void onPostExecute(String ab){
//            chk=true;
            if(chk){
                new retrieveArea().execute();
                new retrieveTable().execute();
                new retrieveRes().execute();
                new retrieveFoods().execute();
                new retrievePrinterName().execute();
                new retrieveFoodsType().execute();
                new retrieveUser().execute();
            }else{
                lbMMessage.setVisibility(View.VISIBLE);
                lbMMessage.setText(ab);
            }
            pd.dismiss();

        }
        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(MainActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Loading...");
            pd.setMessage("Loading information...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();

        }
    }
}
