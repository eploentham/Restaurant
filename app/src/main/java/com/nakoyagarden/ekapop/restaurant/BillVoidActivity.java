package com.nakoyagarden.ekapop.restaurant;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BillVoidActivity extends AppCompatActivity {
    Spinner cboBvTable, cboBvArea;
    ListView lvBvBill;
    RadioButton chkBvToGo, chkBvInRes;
    Button btnBvVoid, btnBvSearch;
    EditText txtBvBillCode, txtBvPassword;

    public RestaurantControl rs;
    Bill bill;
    public ArrayList<Bill> lBill = new ArrayList<Bill>();
    public ArrayList<Order> lOrder = new ArrayList<Order>();

    public ArrayList<String> sCboTable = new ArrayList<String>();
    public ArrayList<String> sCboArea = new ArrayList<String>();
    public ArrayList<String> arrayList = new ArrayList<String>();
    public ArrayList<String> table1 = new ArrayList<String>();

    private ArrayAdapter<String> alvBvBill;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrBv, jarrOv, jarrBv1;
    ProgressDialog pd;

    int textSize=20;
    String ab;
    Boolean pageLoad=false, pageTxtSearch =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_void);
        pageLoad=true;

        cboBvTable = (Spinner)findViewById(R.id.cboBvTable);
        cboBvArea = (Spinner)findViewById(R.id.cboBvArea);
        lvBvBill = (ListView)findViewById(R.id.lvBvBill);
        chkBvToGo = (RadioButton) findViewById(R.id.chkBvToGo);
        chkBvInRes = (RadioButton)findViewById(R.id.chkBvInRes);
        btnBvVoid = (Button) findViewById(R.id.btnBvVoid);
        btnBvSearch = (Button)findViewById(R.id.btnBvSearch);
        txtBvBillCode = (EditText) findViewById(R.id.txtBvBillCode);
        txtBvPassword = (EditText)findViewById(R.id.txtBvPassword);

        chkBvInRes.setText(R.string.inres);
        chkBvToGo.setText(R.string.togo);
        btnBvSearch.setText(R.string.search);
        btnBvVoid.setText(R.string.void1);
        txtBvBillCode.setText(R.string.txtBvBillCode);
        txtBvPassword.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");

        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboArea);
        cboBvArea.setAdapter(adaArea);
        ArrayAdapter<String> adaTable = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboTable);
        cboBvTable.setAdapter(adaTable);
        cboBvTable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!pageLoad){
                    String tableid = rs.getTable(cboBvTable.getSelectedItem().toString(),"id");
                    String areacode = rs.getArea(cboBvArea.getSelectedItem().toString(),"code");
                    new retrieveOrderByTable().execute(tableid);
//                    pageLoad=true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnBvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                txtBvBillCode.setFocusable(false);
                new retrieveOrderByBillCode().execute(txtBvBillCode.getText().toString());
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }
        });
        btnBvVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnBvVoid.getText().toString().equals(getResources().getString(R.string.void1))){
//                    Log.d("btnBvVoid onClick", jobj.toString());
                    txtBvPassword.setVisibility(View.VISIBLE);
                    btnBvVoid.setText(R.string.void1confrim);
                }else if(btnBvVoid.getText().toString().equals(getResources().getString(R.string.void1confrim))){
//                    Log.d("btnBvVoid onClick", jobj.toString());
                    if(txtBvPassword.getText().toString().equals("")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(BillVoidActivity.this);
                        builder1.setMessage("Password ไม่ได้ป้อน");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtBvPassword.setSelection(0,txtBvPassword.getText().length());
                                txtBvPassword.setFocusable(true);
                            }
                        }).create().show();
                    }else{
                        if(rs.chkPasswordVoid(txtBvPassword.getText().toString())){
//                            String tableid = rs.getTable(cboBvTable.getSelectedItem().toString(),"id");
                            new retrieveBillVoid().execute(rs.chkUserByPassword(txtBvPassword.getText().toString()), bill.ID);
                            setTable(rs.getTableToName(bill.TableId,"idtocode"));
                        }else{
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(BillVoidActivity.this);
                            builder1.setMessage("Password ไม่ถูกต้อง");
                            builder1.setCancelable(true);
                            builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txtBvPassword.setSelection(0,txtBvPassword.getText().length());
                                    txtBvPassword.setFocusable(true);
                                }
                            }).create().show();
                        }
                    }
                }
            }
        });
        txtBvBillCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtBvBillCode.getText().toString().contains(getResources().getString(R.string.txtBvBillCode))){
                    txtBvBillCode.setText("");
                }
            }
        });
//        txtBvBillCode.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if(txtBvBillCode.getText().toString().length()==10){
//                    pageTxtSearch =true;
//                    new retrieveOrderByBillId().execute(txtBvBillCode.getText().toString());
//                }
//
//            }
//        });
        lvBvBill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(pageLoad){
                    String[] txt = ((TextView)view).getText().toString().split(" ");
//                bill = new Bill();
                    bill = lBill.get(i);
                    txtBvBillCode.setText(txt[1]);
                    new retrieveOrderByBillId().execute(bill.ID);
                }
            }
        });
        pageLoad=false;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    private void getTable(){
        try {
            table1.clear();
            File file =getFileStreamPath("table.cnf");
            final int READ_BLOCK_SIZE = 100;
            FileInputStream fileIn=openFileInput("table.cnf");
//            FileInputStream fileIn=openFileInput(file.getPath());
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
            String[] p = s.split(";");
            if(p.length>0){
                for(int i=0;i<p.length;i++){
                    table1.add(p[i].replace("\n",""));
                }
            }
            fileIn.close();
            rs.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setTable(String id){
        String table="";
        Log.d("setTable ", id);
        getTable();
        for(int i=0;i<table1.size();i++) {
            String[] bb = table1.get(i).split("=");
            if(bb[0].length()==0) continue;
            if(id.equals(bb[0])){
                Log.d("setTable", " OK");
                table +=bb[0]+"=1;\n";
            }else{
                table +=bb[0]+"="+bb[1]+";\n";
            }
        }
        try {
            FileOutputStream outputStream;
//                    File file =getFileStreamPath("table.cnf");
            outputStream = openFileOutput("table.cnf", Context.MODE_PRIVATE);
//            outputStream = openFileOutput(file.getPath(), Context.MODE_PRIVATE);
            outputStream.write(table.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    class retrieveOrderByTable extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf .format(new Date());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 1);
            String output = sdf.format(c.getTime());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair("table_id", arg0[0]));
            params.add(new BasicNameValuePair("bill_date1",currentDate));
            params.add(new BasicNameValuePair("bill_date2",output));
            jarrBv = jsonparser.getJSONFromUrl(rs.hostBillByTableId,params);
            Log.d("currentDate",currentDate);
            Log.d("output",output);

            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setlvBill();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
            pd = new ProgressDialog(BillVoidActivity.this);
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
    class retrieveOrderByBillId extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            Log.d("retrieveOrderByBillId ", arg0[0]);
            //try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String currentDate = sdf .format(new Date());
//            Calendar c = Calendar.getInstance();
//            c.add(Calendar.DATE, 1);
//            String output = sdf.format(c.getTime());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair("bill_id", arg0[0]));
//            params.add(new BasicNameValuePair("bill_date1",currentDate));
//            params.add(new BasicNameValuePair("bill_date2",output));
            jarrOv = jsonparser.getJSONFromUrl(rs.hostBillDetailByBillId,params);

            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setlvBillShowOrder();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
            pd = new ProgressDialog(BillVoidActivity.this);
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
    class retrieveOrderByBillCode extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String currentDate = sdf .format(new Date());
//            Calendar c = Calendar.getInstance();
//            c.add(Calendar.DATE, 1);
//            String output = sdf.format(c.getTime());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair("bill_code", arg0[0]));
//            params.add(new BasicNameValuePair("bill_date1",currentDate));
//            params.add(new BasicNameValuePair("bill_date2",output));
            Log.d("bill_code",arg0[0]);
            jarrOv = jsonparser.getJSONFromUrl(rs.hostBillDetailByBillCode,params);

            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setlvBillShowOrder();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
            pd = new ProgressDialog(BillVoidActivity.this);
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
    class retrieveBillVoid extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            Log.d("retrieveBillVoid", arg0[1]);
            //try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String currentDate = sdf .format(new Date());
//            Calendar c = Calendar.getInstance();
//            c.add(Calendar.DATE, 1);
//            String output = sdf.format(c.getTime());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair("void_user", arg0[0]));
            params.add(new BasicNameValuePair("bill_id",arg0[1]));
//            params.add(new BasicNameValuePair("bill_date2",output));
            jarrBv1 = jsonparser.getJSONFromUrl(rs.hostBillVoid,params);

            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
//            setlvBill();
            pd.dismiss();
            for(int i=0;i<jarrBv1.length();i++){
                try {
                    JSONObject catObj = (JSONObject) jarrBv1.get(i);
                    Log.d("retrieveBillVoid", catObj.getString("status"));
                    if(catObj.getString("status").equals("3")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(BillVoidActivity.this);
                        builder1.setMessage("Password ไม่๔ุกต้อง");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtBvPassword.setSelection(0,txtBvPassword.getText().length());
                                txtBvPassword.setFocusable(true);
                            }
                        }).create().show();
                    }else if(catObj.getString("status").equals("1")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(BillVoidActivity.this);
                        builder1.setMessage("ทำการยกเลิก เรียบร้อย");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtBvPassword.setSelection(0,txtBvPassword.getText().length());
                                txtBvPassword.setFocusable(false);
                            }
                        }).create().show();
                    }else{
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(BillVoidActivity.this);
                        builder1.setMessage("ทำการยกเลิกไม่สำเร็จ");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtBvPassword.setSelection(0,txtBvPassword.getText().length());
                                txtBvPassword.setFocusable(true);
                            }
                        }).create().show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
            pd = new ProgressDialog(BillVoidActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Loading...");
            pd.setMessage("Loading Void Bill...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();
        }
    }

    private void setlvBill(){
        lvBvBill.setBackgroundColor(getResources().getColor(R.color.BackScreenSearch));
//        lvMOrder.setBackgroundColor(getResources().getColor(R.color.BackScreenSearch));
        lBill.clear();
        arrayList.clear();
        Double amt=0.0, total=0.0;
        if(jarrBv==null) return;;
        for(int i=0;i<jarrBv.length();i++){
            try {
                JSONObject catObj = (JSONObject) jarrBv.get(i);
                Bill o = new Bill();
                //o = (Order)lOrderLot.get(i);
                o.ID = catObj.getString("bill_id");
//                o.BillCode = catObj.getString("bill_code");
                o.Code = catObj.getString("bill_code");
                o.NetTotal = catObj.getString("nettotal");

                arrayList.add((i+1)+" "+o.Code+" "+o.NetTotal);
                lBill.add(o);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        alvBvBill = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);
                // Get the Layout Parameters for ListView Current Item View
                ViewGroup.LayoutParams params = view.getLayoutParams();
                // Set the height of the Item View
                params.height = 40;
                view.setLayoutParams(params);
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextSize(textSize);
                textView.setHeight(40);
                textView.setTextColor(Color.RED);
                return textView;
            }
        };
        lvBvBill.setAdapter(alvBvBill);
    }
    private void setlvBillShowOrder(){
        pageLoad=true;
        lvBvBill.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));
//        lvMOrder.setBackgroundColor(getResources().getColor(R.color.BackScreenSearch));
        lOrder.clear();
        arrayList.clear();
        Double amt=0.0, total=0.0;
        Log.d("jarrOv.length()",String.valueOf(jarrOv.length()));
        for(int i=0;i<jarrOv.length();i++){
            try {

                JSONObject catObj = (JSONObject) jarrOv.get(i);
                Order o = new Order();
                bill = new Bill();
                bill.ID = catObj.getString("bill_id");
                bill.Code = catObj.getString("bill_code");
                bill.TableId = catObj.getString("table_id");
                //o = (Order)lOrderLot.get(i);

                o.ID = catObj.getString("bill_id");
                o.FoodsCode = catObj.getString("foods_code");
                o.FoodsName = catObj.getString("foods_name");
                o.Qty = catObj.getString("qty");
                o.Amt = catObj.getString("amount");
                o.Remark = catObj.getString("remark");
                o.row1 = catObj.getString("row1");

                arrayList.add(o.row1+" "+o.FoodsCode+" "+o.FoodsName+" "+o.Qty+" "+o.Amt+" "+o.Remark);
                lOrder.add(o);
            }catch (JSONException e){
                e.printStackTrace();
                Log.e("setlvBillShowOrder",e.getMessage());
            }
        }
        alvBvBill = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);
                // Get the Layout Parameters for ListView Current Item View
                ViewGroup.LayoutParams params = view.getLayoutParams();
                // Set the height of the Item View
                params.height = 40;
                view.setLayoutParams(params);
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextSize(textSize);
                textView.setHeight(40);
                return textView;
            }
        };
        lvBvBill.setAdapter(alvBvBill);
        pageLoad=false;
    }
}
