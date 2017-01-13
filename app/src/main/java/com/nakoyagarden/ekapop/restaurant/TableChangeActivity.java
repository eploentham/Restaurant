package com.nakoyagarden.ekapop.restaurant;

import android.app.AlertDialog;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TableChangeActivity extends Activity {
    Spinner cboTcTableFrom, cboTcAreaFrom,cboTcTableTo, cboTcAreaTo;
    ListView lvTcOrderFrom, lvTcOrderTo;
    Button btnTcSave;
    EditText txtTcPassword;

    public ArrayList<String> table1 = new ArrayList<String>();
    private ArrayAdapter<String> alvMOrder;
    private ArrayList<String> arrayList= new ArrayList<String>();
    public ArrayList<Order> lOrderT = new ArrayList<Order>();
    ArrayList<ItemData> listTable=new ArrayList<>();
    private ArrayAdapter<String> alvBaOrder;

    JSONArray jarrBa;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();

    int textSize=20;

    public RestaurantControl rs;
    DatabaseSQLi daS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_change);

        cboTcTableFrom = (Spinner)findViewById(R.id.cboTcTableFrom);
        cboTcAreaFrom = (Spinner)findViewById(R.id.cboTcAreaFrom);
        cboTcTableTo = (Spinner)findViewById(R.id.cboTcTableTo);
        cboTcAreaTo = (Spinner)findViewById(R.id.cboTcAreaTo);
        lvTcOrderFrom = (ListView)findViewById(R.id.lvTcOrderFrom);
        lvTcOrderTo = (ListView)findViewById(R.id.lvTcOrderTo);
        btnTcSave = (Button) findViewById(R.id.btnTcSave);
        txtTcPassword = (EditText) findViewById(R.id.txtTcPassword);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);

        btnTcSave.setText(R.string.btnMTChange);
        txtTcPassword.setVisibility(View.INVISIBLE);
        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboArea);
        cboTcAreaFrom.setAdapter(adaArea);
        cboTcAreaTo.setAdapter(adaArea);

        daS = new DatabaseSQLi(this,"");
        if(rs.AccessMode.equals("Standalone")){

        }else if(rs.AccessMode.equals("Internet")){

        }else{

        }
        cboTcTableFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tablecode = rs.getTable(cboTcTableFrom.getSelectedItem().toString(),"code");
                String areacode = rs.getArea(cboTcAreaFrom.getSelectedItem().toString(),"code");
                if(rs.AccessMode.equals("Standalone")) {
                    jarrBa = daS.OrderByTableCode(tablecode);
                    setlvOrder();
                }else if(rs.AccessMode.equals("Internet")){
                    new retrieveOrderByTable().execute(areacode,tablecode);
                }else{
                    new retrieveOrderByTable().execute(areacode,tablecode);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnTcSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btnTcSave ","onClick");
                if(btnTcSave.getText().toString().equals(getResources().getString(R.string.btnMTChange))){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(TableChangeActivity.this);
                    builder1.setMessage("ต้องการ"+getResources().getString(R.string.btnMTChange)+".\n\n "+" "+cboTcTableFrom.getSelectedItem().toString()+" -> "+ cboTcAreaFrom.getSelectedItem().toString()+"\n");
                    builder1.setCancelable(true);
                    builder1.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                                flagDel=false;
//                                Toast.makeText(MailarapOrderAdd.this,"You clicked no button",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });
                    builder1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Whatever...
                            txtTcPassword.setVisibility(View.VISIBLE);
                            btnTcSave.setText(R.string.btnTcSave);
                            txtTcPassword.setSelection(0,txtTcPassword.getText().length());
                            txtTcPassword.setFocusable(true);

                        }
                    }).create().show();
                }else if(btnTcSave.getText().toString().equals(getResources().getString(R.string.btnTcSave))){
                    if(txtTcPassword.getText().toString().equals("")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(TableChangeActivity.this);
                        builder1.setMessage("Password ไม่ได้ป้อน");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtTcPassword.setSelection(0,txtTcPassword.getText().length());
                                txtTcPassword.setFocusable(true);
                            }
                        }).create().show();
                    }else{
                        if(rs.chkPasswordBill(txtTcPassword.getText().toString())){
                            Log.d("btnTcSave ","if(rs.chkPasswordBill(txtTcPassword.getText().toString()))");
                            String tableidFrom = rs.getTable(cboTcTableFrom.getSelectedItem().toString(),"id");
                            String tablecodeFrom = rs.getTable(cboTcTableFrom.getSelectedItem().toString(),"code");
                            String tableidTo = rs.getTable(cboTcTableTo.getSelectedItem().toString(),"id");
                            String tablecodeTo = rs.getTable(cboTcTableTo.getSelectedItem().toString(),"code");
                            String areaidfrom = rs.getArea(cboTcAreaFrom.getSelectedItem().toString(),"id");
                            String areaidto = rs.getArea(cboTcAreaTo.getSelectedItem().toString(),"id");
                            String deviceid = "";
                            String user = rs.chkUserByPassword(txtTcPassword.getText().toString());
                            String tcID = UUID.randomUUID().toString();
                            if(rs.AccessMode.equals("Standalone")) {
                                jarr = daS.TableChangeInsert(tcID, tableidFrom,tableidTo,user,"","",tablecodeTo,tablecodeFrom);
                                getTableChangeInsert();
                                setTable(tablecodeTo,tablecodeFrom);
                                setCboTable(tablecodeTo);
                            }else if(rs.AccessMode.equals("Internet")){
                                new insertTableChange().execute(tcID, tableidFrom,tableidTo,user,"","",tablecodeTo,tablecodeFrom);
                                setTable(tablecodeTo,tablecodeFrom);
                                setCboTable(tablecodeTo);
                            }else{
                                new insertTableChange().execute(tcID, tableidFrom,tableidTo,user,"","",tablecodeTo,tablecodeFrom);
                                setTable(tablecodeTo,tablecodeFrom);
                                setCboTable(tablecodeTo);
                            }
                        }
                    }
                }
            }
        });
        setTheme();
        setCboTable("");
//        alvMOrder = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList1);
//        lvTcOrderFrom.setAdapter(alvMOrder);
//        lvTcOrderTo.setAdapter(alvMOrder);
        lvTcOrderFrom.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext(), lvTcOrderFrom));
        lvTcOrderTo.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext(), lvTcOrderTo));
    }
    private void setTheme(){

    }
    private void setlvOrder(){
        lvTcOrderFrom.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));
        lOrderT.clear();
        arrayList.clear();
        Double amt=0.0, total=0.0;
        if(jarrBa==null) return;
//        prn = new String[jarrBa.length()];
        for(int i=0;i<jarrBa.length();i++){
            try {
                JSONObject catObj = (JSONObject) jarrBa.get(i);
                Order o = new Order();
                //o = (Order)lOrderLot.get(i);
                o.ID = catObj.getString(o.dbID);
                o.LotId = catObj.getString(o.dbLotId);
                o.FoodsCode = catObj.getString(o.dbFoodsCode);
                o.FoodsName = catObj.getString(o.dbFoodsName);
                o.FoodsId = catObj.getString(o.dbFoodsId);
                o.Price = catObj.getString(o.dbPrice);
                o.Qty = catObj.getString(o.dbQty);
                o.row1 = catObj.getString(o.dbrow1);
                o.StatusCook = catObj.getString(o.dbStatusCook);
                o.FoodsName = catObj.getString(o.dbFoodsName);
                o.StatusToGo = catObj.getString(o.dbStatusToGo);
                o.FlagVoid = "0";
                arrayList.add(o.row1+" "+o.FoodsCode+" "+o.FoodsName+" "+getResources().getString(R.string.price)+"("+o.Price+") "+
                        getResources().getString(R.string.qty)+"("+o.Qty+") = "+ amt.toString());
            }catch (JSONException e){
                e.printStackTrace();
                Log.e("setlvOrder ",e.getMessage());
            }
        }
        alvBaOrder = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList){
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
        lvTcOrderFrom.setAdapter(alvBaOrder);
    }
    private void setCboTable(String id){
        Log.i("rs.sTable.size()",String.valueOf(rs.sTable.size()));
        if(rs.sTable==null) return;
        if(rs.sTable.size()==0) return;
        listTable.clear();
        getTable();
        String table="";

        for(int i=0;i<table1.size();i++){
            String[] aa = table1.get(i).split("=");
            if(aa[0].length()==0) continue;
            String[] bb = rs.sTable.get(i).split("@");
//            table +=aa[1]+"="+aa[3]+";\n";
            if(id.equals(aa[0])){
//                aa[3] = "1";
                listTable.add(new ItemData(bb[2],R.drawable.idel_red));
            }else{
                if(aa[1].equals("1")){
                    listTable.add(new ItemData(bb[2],R.drawable.idel_red));
                }else{
                    listTable.add(new ItemData(bb[2],R.drawable.idel_blue));
                }
            }
        }
        SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.spinner_layout,R.id.cbotxt,listTable);
        cboTcTableTo.setAdapter(adapter);
        cboTcTableFrom.setAdapter(adapter);
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
    private void setTable(String idto, String idfrom){
        String table="";
        for(int i=0;i<table1.size();i++) {
            String[] bb = table1.get(i).split("=");
            if(bb[0].length()==0) continue;
            if(idto.equals(bb[0])){
                table +=bb[0]+"=1;\n";
            }else if(idfrom.equals(bb[0])){
                table +=bb[0]+"=0;\n";
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
    private void getTableChangeInsert(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("getTableChangeInsert",catObj.getString("sql"));
            if(catObj.getString("success").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(TableChangeActivity.this);
                builder1.setMessage("บันทึกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnTcSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {

        }
    }
    class retrieveOrderByTable extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair("table_code", arg0[1]));
            //params.add(new BasicNameValuePair("table_code",tableCode));
            jarrBa = jsonparser.getJSONFromUrl(rs.hostOrderByTableCode,params);

            //rs.jarrR = jarrR.toString();
            //} catch (JSONException e) {
            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return "";
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setlvOrder();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    class insertTableChange extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            Log.d("insertTableChange", "OK");
//            try {
//            tcID, tableidFrom,tableidTo,user,"","",tablecodeTo,tablecodeFrom
            TableChange tc = new TableChange();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(tc.dbID, arg0[0]));
            params.add(new BasicNameValuePair(tc.dbTableIdFrom, arg0[1]));
            params.add(new BasicNameValuePair(tc.dbTableIdTo, arg0[2]));
            params.add(new BasicNameValuePair(tc.dbTableChangeUser, arg0[3]));
            params.add(new BasicNameValuePair(tc.dbTableCodeTo, arg0[6]));
            params.add(new BasicNameValuePair(tc.dbTableCodeFrom, arg0[7]));
            jarr = jsonparser.getJSONFromUrl(rs.hostTableChange,params);
            Log.d(tc.dbTableCodeTo, arg0[6]);
            Log.d(tc.dbTableCodeFrom, arg0[7]);

//                if(jarr!=null){
//                    JSONObject catObj = (JSONObject) jarr.get(0);
//                }
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return "";
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getTableChangeInsert();
        }
        @Override
        protected void onPreExecute() {
            String aaa = "";
        }
    }
}
