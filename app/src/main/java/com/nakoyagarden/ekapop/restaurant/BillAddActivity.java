package com.nakoyagarden.ekapop.restaurant;

import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BillAddActivity extends AppCompatActivity {
    Spinner cboBaTable, cboBaArea;
    TextView lbBaAmt, lbBaAmt1, lbBaDiscount, lbBaDiscount1, lbBaTotal, lbBaTotal1, lbBaVat, lbBaVat1, lbBaSC, lbBaSC1, lbBaNetTotal, lbBaNetTotal1, lbBaCashReceive, lbBaCashTon;
    ListView lvBaAdd;
    RadioButton chkBaToGo, chkBaInRes;
    Button btnBaSave;
    EditText txtBaUserPassword, txtBaCashReceive, txtBaCashTon;

    JsonParser jsonparser = new JsonParser();
    JSONArray jarrBa;
    JSONArray jarr;
    public RestaurantControl rs;
    private PrintBillEpson pBE;
    public ArrayList<String> sCboTable = new ArrayList<String>();
    public ArrayList<String> sCboArea = new ArrayList<String>();
    public ArrayList<String> arrayList = new ArrayList<String>();
    public ArrayList<Order> lOrderT = new ArrayList<Order>();
    public ArrayList<Foods> lFoo = new ArrayList<Foods>();
    String[] prn ;
    Integer row1=0, rowDel=0;
    String lotID="";
    int textSize=20,textSize1=18, row;
    String ab;
    private ArrayAdapter<String> alvBaOrder;
    LocalActivityManager mLocalActivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_add);
        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);

        cboBaTable = (Spinner)findViewById(R.id.cboBaTable);
        cboBaArea = (Spinner)findViewById(R.id.cboBaArea);
        lbBaAmt = (TextView) findViewById(R.id.lbBaAmt);
        lbBaAmt1 = (TextView)findViewById(R.id.lbBaAmt1);
        lbBaDiscount = (TextView)findViewById(R.id.lbBaDiscount);
        lbBaDiscount1 = (TextView)findViewById(R.id.lbBaDiscount1);
        lbBaTotal = (TextView)findViewById(R.id.lbBaTotal);
        lbBaTotal1 = (TextView)findViewById(R.id.lbBaTotal1);
        lbBaVat = (TextView)findViewById(R.id.lbBaVat);
        lbBaVat1 = (TextView)findViewById(R.id.lbBaVat1);
        lbBaSC = (TextView)findViewById(R.id.lbBaSC);
        lbBaSC1 = (TextView)findViewById(R.id.lbBaSC1);
        lbBaNetTotal = (TextView)findViewById(R.id.lbBaNetTotal);
        lbBaNetTotal1 = (TextView)findViewById(R.id.lbBaNetTotal1);
        lvBaAdd = (ListView)findViewById(R.id.lvBaAdd);
        chkBaToGo = (RadioButton) findViewById(R.id.chkBaToGo);
        chkBaInRes = (RadioButton)findViewById(R.id.chkBaInRes);
        btnBaSave = (Button) findViewById(R.id.btnBaSave);
        txtBaUserPassword = (EditText) findViewById(R.id.txtBaUserPassword);
        lbBaCashReceive = (TextView)findViewById(R.id.lbBaCashReceive);
        lbBaCashTon = (TextView)findViewById(R.id.lbBaCashTon);
        txtBaCashReceive = (EditText) findViewById(R.id.txtBaCashReceive);
        txtBaCashTon = (EditText) findViewById(R.id.txtBaCashTon);

        chkBaInRes.setText(R.string.inres);
        chkBaToGo.setText(R.string.togo);
        lbBaAmt.setText(R.string.amt);
        lbBaDiscount.setText(R.string.discount);
        lbBaTotal.setText(R.string.total);
        lbBaSC.setText(R.string.sc);
        lbBaNetTotal.setText(R.string.nettotal);
        lbBaVat.setText(R.string.vat);
        btnBaSave.setText(R.string.billcheck);
        lbBaCashTon.setText(R.string.cashton);
        lbBaCashReceive.setText(R.string.cashreceive);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        ArrayAdapter<String> adaTable = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboTable);
        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboArea);
        cboBaTable.setAdapter(adaTable);
        cboBaArea.setAdapter(adaArea);

        cboBaTable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tablecode = rs.getTable(cboBaTable.getSelectedItem().toString(),"code");
                String areacode = rs.getArea(cboBaArea.getSelectedItem().toString(),"code");
                new retrieveOrderByTable().execute(areacode,tablecode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnBaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtBaCashReceive.getText().toString().equals("")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(BillAddActivity.this);
                    builder1.setMessage("รับเงิน ไม่ได้ป้อน");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            txtBaCashReceive.setSelection(0,txtBaCashReceive.getText().length());
                            txtBaCashReceive.setFocusable(true);
                        }
                    }).create().show();
                    return;
                }
                if(txtBaUserPassword.getText().toString().equals("")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(BillAddActivity.this);
                    builder1.setMessage("Password ไม่ได้ป้อน");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            txtBaUserPassword.setSelection(0,txtBaUserPassword.getText().length());
                            txtBaUserPassword.setFocusable(true);
                        }
                    }).create().show();
                }else{
                    if(rs.chkPasswordBill(txtBaUserPassword.getText().toString())){
                        String tableid = rs.getTable(cboBaTable.getSelectedItem().toString(),"id");
                        String areaid = rs.getArea(cboBaArea.getSelectedItem().toString(),"id");
                        String deviceid = "";
                        String user = rs.chkUserByPassword(txtBaUserPassword.getText().toString());
                        String billID = UUID.randomUUID().toString();

                        new insertBill().execute(tableid,areaid, deviceid,lbBaDiscount1.getText().toString(),lbBaAmt1.getText().toString(), lbBaSC1.getText().toString(),
                                lbBaVat1.getText().toString(),lbBaTotal1.getText().toString(),lbBaNetTotal1.getText().toString(),billID,
                                txtBaCashReceive.getText().toString(),txtBaCashTon.getText().toString(),user);
                        for(int i=0;i<lOrderT.size();i++){

                            Order ord = (Order)lOrderT.get(i);
                            new insertBillDetail().execute(billID,ord.LotId,ord.Qty, ord.FoodsCode, ord.FoodsName,ord.FoodsId,ord.Price, ord.Amt, ord.ID,String.valueOf(i+1),ord.FlagVoid);
                        }
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(BillAddActivity.this);
                        builder1.setMessage("บันทึกข้อมูลเรียบร้อย");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btnBaSave.setEnabled(false);
                            }
                        }).create().show();
                    }
                }
            }
        });
        lvBaAdd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(int k=0;k<adapterView.getCount();k++){
                    if(k!=i) adapterView.getChildAt(k).setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));
                }
                //adapterView.getChildAt(i).getBackground().
                adapterView.getChildAt(i).setBackgroundColor(Color.GRAY);
            }
        });
        lvBaAdd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String[] txt = ((TextView)view).getText().toString().split(" ");
                rowDel = i;
                Order o = lOrderT.get(i);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(BillAddActivity.this);
                builder1.setMessage("ต้องการเยกเลิกรายการนี้.\n\nลำดับ "+(i+1)+" รหัส "+o.FoodsCode+" "+ o.FoodsName+"\n");
                builder1.setCancelable(true);
                builder1.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Whatever...
                        lOrderT.get(rowDel).FlagVoid = "1";
                        calOrder();
//                                flagDel=true;
                        Toast.makeText(BillAddActivity.this,"ลบข้อมูลเรียบร้อย",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
//                        flagDel = false;
                    }
                }).create().show();
                return false;
            }
        });
//        setTheme();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    private void calOrder(){
        Double amt=0.0, total=0.0;
        for(int i=0;i<lOrderT.size();i++){
            Order o = lOrderT.get(i);
            if(!o.FlagVoid.equals("1")){
                amt = Double.parseDouble(o.Price)*Double.parseDouble(o.Qty);
                total += amt;
            }
        }
        lbBaAmt1.setText(total.toString());
        lbBaDiscount1.setText(rs.discount);
        lbBaTotal1.setText(String.valueOf(total - Double.parseDouble(rs.discount)));
        lbBaSC1.setText(rs.SC);
        lbBaVat1.setText(rs.vat);
        lbBaNetTotal1.setText(String.valueOf(Double.parseDouble(lbBaTotal1.getText().toString()) + Double.parseDouble(lbBaSC1.getText().toString())+Double.parseDouble(lbBaVat1.getText().toString())));
    }
    private void setTheme(){
        lbBaAmt.setTextSize(textSize);
        lbBaAmt1.setTextSize(textSize);
        lbBaDiscount.setTextSize(textSize);
        lbBaDiscount1.setTextSize(textSize);
        lbBaTotal.setTextSize(textSize);
        lbBaTotal1.setTextSize(textSize);
        lbBaVat.setTextSize(textSize);
        lbBaVat1.setTextSize(textSize);
        lbBaSC.setTextSize(textSize);
        lbBaSC1.setTextSize(textSize);
        lbBaNetTotal.setTextSize(textSize);
        lbBaNetTotal1.setTextSize(textSize);
        chkBaInRes.setTextSize(textSize);
        chkBaToGo.setTextSize(textSize);
        //alvMOrder = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList1);
    }
    private void setControl(){
        lbBaAmt1.setText("0.00");
        lbBaDiscount1.setText("0.00");
        lbBaTotal1.setText("0.00");
        lbBaSC1.setText("0.00");
        lbBaVat1.setText("0.00");
        lbBaNetTotal1.setText("0.00");
        setlvOrder();
    }
    private void setlvOrder(){
        lvBaAdd.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));
        lOrderT.clear();
        arrayList.clear();
        Double amt=0.0, total=0.0;
        prn = new String[jarrBa.length()];
        for(int i=0;i<jarrBa.length();i++){
            try {
                JSONObject catObj = (JSONObject) jarrBa.get(i);
                Order o = new Order();
                //o = (Order)lOrderLot.get(i);
                o.ID = catObj.getString("order_id");
                o.LotId = catObj.getString("lot_id");
                o.FoodsCode = catObj.getString("foods_code");
                o.FoodsName = catObj.getString("foods_name");
                o.FoodsId = catObj.getString("foods_id");
                o.Price = catObj.getString("price");
                o.Qty = catObj.getString("qty");
                o.row1 = catObj.getString("row1");
                o.StatusCook = catObj.getString("status_cook");
                o.FoodsName = catObj.getString("foods_name");
                o.StatusToGo = catObj.getString("status_to_go");
                amt = Double.parseDouble(o.Price)*Double.parseDouble(o.Qty);
                o.Amt = amt.toString();
                total += amt;
                String togo = o.StatusToGo;
                if(i==0){
                    if(o.StatusToGo.equals("1")){
                        chkBaInRes.setChecked(true);
                    }else{
                        chkBaToGo.setChecked(true);
                    }
                }
                if(togo.equals("1")){
                    togo = getResources().getString(R.string.inres);
                }else{
                    togo=getResources().getString(R.string.togo);
                }
                prn[i] = o.FoodsName+";"+o.Qty+";"+o.Remark;
                arrayList.add(o.row1+" "+o.FoodsCode+" "+o.FoodsName+" "+getResources().getString(R.string.price)+"("+o.Price+") "+
                        getResources().getString(R.string.qty)+"("+o.Qty+") = "+ amt.toString()+o.Remark+togo);
                lOrderT.add(o);
            }catch (JSONException e){
                e.printStackTrace();
            }
            lbBaAmt1.setText(total.toString());
            lbBaDiscount1.setText(rs.discount);
            lbBaTotal1.setText(String.valueOf(total - Double.parseDouble(rs.discount)));
            lbBaSC1.setText(rs.SC);
            lbBaVat1.setText(rs.vat);
            lbBaNetTotal1.setText(String.valueOf(Double.parseDouble(lbBaTotal1.getText().toString()) + Double.parseDouble(lbBaSC1.getText().toString())+Double.parseDouble(lbBaVat1.getText().toString())));
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
        lvBaAdd.setAdapter(alvBaOrder);
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
            setControl();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    class insertBill extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                //new insertOrder().execute(String.valueOf(i),lotID, areacode,tablecode,String.valueOf(txtMQty.getValue()), txtMFoodsCode.getText().toString(),
                //        lbMFoodsname.getText().toString(),txtMFoodsRemark.getText().toString(),ord.ResCode, ord.Price, ord.PrinterName);
                //int row = Integer.parseInt(arg0[0]);
                //Order ord = (Order)lorder.get(row);
                //String billID = UUID.randomUUID().toString();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                params.add(new BasicNameValuePair("bill_id", arg0[9]));
                params.add(new BasicNameValuePair("table_id", arg0[0]));
                params.add(new BasicNameValuePair("area_id", arg0[1]));
                params.add(new BasicNameValuePair("device_id", arg0[2]));
                params.add(new BasicNameValuePair("amt", arg0[4]));
                params.add(new BasicNameValuePair("service_charge", arg0[5]));
                params.add(new BasicNameValuePair("vat", arg0[6]));
                params.add(new BasicNameValuePair("total", arg0[7]));
                params.add(new BasicNameValuePair("nettotal", arg0[8]));
                params.add(new BasicNameValuePair("remark", ""));
                params.add(new BasicNameValuePair("res_id", ""));
                params.add(new BasicNameValuePair("discount", arg0[3]));
                params.add(new BasicNameValuePair("cash_receive", arg0[10].equals("")?"0.0":arg0[10]));
                params.add(new BasicNameValuePair("cash_ton", arg0[11].equals("")?"0.0":arg0[11]));
                params.add(new BasicNameValuePair("bill_user", arg0[12]));

                jarr = jsonparser.getJSONFromUrl(rs.hostBillInsert, params);

                if(jarr!=null){
                    //rs.sCboArea.clear();
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    //for (int i = 0; i < jarr.length(); i++) {
                    JSONObject catObj = (JSONObject) jarr.get(0);
                    ab = catObj.getString("bill_code");
                    //rs.sCboArea.add(catObj.getString("name"));
                    //}
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
            //new retrieveFoods1().execute();
            if(rs.PrnB.equals("ON")){
                pBE = new PrintBillEpson(BillAddActivity.this);
                pBE.runPrintReceiptSequenceEpson(getResources(), ab, rs.ResName, rs.ReceiptH1,rs.ReceiptH2,rs.ReceiptF1,rs.ReceiptF2, cboBaArea.getSelectedItem().toString(),cboBaTable.getSelectedItem().toString(), prn,lbBaAmt1.getText().toString(),
                        lbBaDiscount1.getText().toString(),lbBaTotal1.getText().toString(), lbBaSC1.getText().toString(),lbBaVat1.getText().toString(),lbBaNetTotal1.getText().toString());
                pBE = null;
            }

        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    class insertBillDetail extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                //new insertOrder().execute(String.valueOf(i),lotID, areacode,tablecode,String.valueOf(txtMQty.getValue()), txtMFoodsCode.getText().toString(),
                //        lbMFoodsname.getText().toString(),txtMFoodsRemark.getText().toString(),ord.ResCode, ord.Price, ord.PrinterName);
                //int row = Integer.parseInt(arg0[0]);
                //Order ord = (Order)lorder.get(row);
                //String billID = UUID.randomUUID().toString();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                params.add(new BasicNameValuePair("bill_id", arg0[0]));
                params.add(new BasicNameValuePair("lot_id", arg0[1]));
                params.add(new BasicNameValuePair("qty", arg0[2]));
                params.add(new BasicNameValuePair("foods_code", arg0[3]));
                params.add(new BasicNameValuePair("foods_name", arg0[4]));
                params.add(new BasicNameValuePair("foods_id", arg0[5]));
                params.add(new BasicNameValuePair("price", arg0[6]));
                params.add(new BasicNameValuePair("amount", arg0[7]));
                params.add(new BasicNameValuePair("order_id", arg0[8]));
                params.add(new BasicNameValuePair("row1", arg0[9]));
                params.add(new BasicNameValuePair("flag_void", arg0[10]));
                //params.add(new BasicNameValuePair("discount", arg0[3]));

                jarr = jsonparser.getJSONFromUrl(rs.hostBillDetailInsert, params);

                if(jarr!=null){
                    //rs.sCboArea.clear();
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    //for (int i = 0; i < jarr.length(); i++) {
                    JSONObject catObj = (JSONObject) jarr.get(0);
                    //rs.sCboArea.add(catObj.getString("name"));
                    //}
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
            //new retrieveFoods1().execute();

        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    class updateOrder extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                //new insertOrder().execute(String.valueOf(i),lotID, areacode,tablecode,String.valueOf(txtMQty.getValue()), txtMFoodsCode.getText().toString(),
                //        lbMFoodsname.getText().toString(),txtMFoodsRemark.getText().toString(),ord.ResCode, ord.Price, ord.PrinterName);
                //int row = Integer.parseInt(arg0[0]);
                //Order ord = (Order)lorder.get(row);
                //String billID = UUID.randomUUID().toString();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                params.add(new BasicNameValuePair("bill_id", arg0[0]));
                params.add(new BasicNameValuePair("lot_id", arg0[1]));
                params.add(new BasicNameValuePair("qty", arg0[2]));
                params.add(new BasicNameValuePair("foods_code", arg0[3]));
                params.add(new BasicNameValuePair("foods_name", arg0[4]));
                params.add(new BasicNameValuePair("foods_id", arg0[5]));
                params.add(new BasicNameValuePair("price", arg0[6]));
                params.add(new BasicNameValuePair("amount", arg0[7]));
                params.add(new BasicNameValuePair("order_id", arg0[8]));
                params.add(new BasicNameValuePair("row1", arg0[9]));
                //params.add(new BasicNameValuePair("res_id", ""));
                //params.add(new BasicNameValuePair("discount", arg0[3]));

                jarr = jsonparser.getJSONFromUrl(rs.hostBillDetailInsert, params);

                if(jarr!=null){
                    //rs.sCboArea.clear();
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    //for (int i = 0; i < jarr.length(); i++) {
                    JSONObject catObj = (JSONObject) jarr.get(0);
                    //rs.sCboArea.add(catObj.getString("name"));
                    //}
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
            //new retrieveFoods1().execute();

        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
}
