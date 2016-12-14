package com.nakoyagarden.ekapop.restaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private ArrayAdapter<String> alvBvBill;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrBv, jarrOv, jarrBv1;

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
                    String tablecode = rs.getTable(cboBvTable.getSelectedItem().toString(),"id");
                    String areacode = rs.getArea(cboBvArea.getSelectedItem().toString(),"code");
                    new retrieveOrderByTable().execute(tablecode);
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
                new retrieveOrderByBillId().execute(txtBvBillCode.getText().toString());
            }
        });
        btnBvVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        new retrieveBillVoid().execute(txtBvPassword.getText().toString(), bill.ID);
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
        });
        txtBvBillCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(txtBvBillCode.getText().toString().length()==9){
                    pageTxtSearch =true;
                    new retrieveOrderByBillId().execute(txtBvBillCode.getText().toString());
                }

            }
        });
        lvBvBill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if(pageTxtSearch){
//                    Bill b = new Bill();
                String[] txt = ((TextView)view).getText().toString().split(" ");
//                bill = new Bill();
                bill = lBill.get(i);
                txtBvBillCode.setText(txt[1]);
                new retrieveOrderByBillId().execute(bill.ID);
//                }else{

//                }
            }
        });
        pageLoad=false;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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

            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setlvBill();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    class retrieveOrderByBillId extends AsyncTask<String,String,String> {
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
        }
        @Override
        protected void onPreExecute() {


        }
    }
    class retrieveBillVoid extends AsyncTask<String,String,String> {
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
            params.add(new BasicNameValuePair("password", arg0[0]));
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
            for(int i=0;i<jarrBv1.length();i++){
                try {
                    JSONObject catObj = (JSONObject) jarrBv1.get(i);
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
                                txtBvPassword.setFocusable(true);
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
        lvBvBill.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));
//        lvMOrder.setBackgroundColor(getResources().getColor(R.color.BackScreenSearch));
        lOrder.clear();
        arrayList.clear();
        Double amt=0.0, total=0.0;
        for(int i=0;i<jarrOv.length();i++){
            try {
                JSONObject catObj = (JSONObject) jarrOv.get(i);
                Order o = new Order();
                //o = (Order)lOrderLot.get(i);
                o.ID = catObj.getString("bill_id");
                o.FoodsCode = catObj.getString("foods_code");
                o.FoodsName = catObj.getString("foods_name");
                o.Qty = catObj.getString("qty");
                o.Amt = catObj.getString("amount");
                o.Remark = catObj.getString("remark");

                arrayList.add((i+1)+" "+o.FoodsCode+" "+o.FoodsName+" "+o.Qty+" "+o.Amt+" "+o.Remark);
                lOrder.add(o);
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
                return textView;
            }
        };
        lvBvBill.setAdapter(alvBvBill);
    }
}
