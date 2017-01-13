package com.nakoyagarden.ekapop.restaurant;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CloseDayAddActivity extends AppCompatActivity {
    public RestaurantControl rs;
    private PrintCloseDayEpson pBE;

    Button btnCaSave, btnCaVoid;
    TextView lbCaAmt,lbCaCloseDayDate, lbCaDiscount, lbCaTotal, lbCaSC, lbCaVat, lbCaNetTotal, lbCaRemark, lbCaCashReceive1, lbCaCashReceive2, lbCaCashReceive3, lbCaCashDraw1;
    TextView lbCaCashDraw2, lbCaCashDraw3,lbCaCntBill, lbCaCntOrder;

    TextView txtCaAmt, txtCaDiscount, txtCaTotal, txtCaSC, txtCaVat, txtCaNetTotal;
    EditText txtCaRemark, txtCaCashReceive1, txtCaCashReceive2, txtCaCashReceive3, txtCaCashDraw1;
    EditText txtCaCashDraw2, txtCaCashDraw3, txtCaCashReceive1Remark, txtCaCashReceive2Remark, txtCaCashReceive3Remark, txtCaCashDraw1Remark, txtCaCashDraw2Remark;
    EditText txtCaCashDraw3Remark, txtCaUserPassword,txtCaPasswordVoid;
    Spinner cboCaRes;
    DatePicker dpCloseDayDate;
    LinearLayout layout16, layout17;
    Switch chkCaActive;

//    String ID="";

    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jaCa, jarr;

    ProgressDialog pd;
    Bill bill;
    CloseDay cd = new CloseDay();
    private int year,textSize=20;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 0;
    LocalActivityManager mLocalActivityManager;
    Boolean pageLoad=Boolean.FALSE;
    DatabaseSQLi daS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_day_add);
        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        pd = new ProgressDialog(CloseDayAddActivity.this);
        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);

        cboCaRes = (Spinner)findViewById(R.id.cboCaRes);
        lbCaAmt = (TextView) findViewById(R.id.lbCaAmt);
        lbCaCntBill = (TextView) findViewById(R.id.lbCaCntBill);
        lbCaCloseDayDate = (TextView) findViewById(R.id.lbCaCloseDayDate);
        lbCaDiscount = (TextView) findViewById(R.id.lbCaDiscount);
        lbCaTotal = (TextView) findViewById(R.id.lbCaTotal);
        lbCaSC = (TextView) findViewById(R.id.lbCaSC);
        lbCaVat = (TextView) findViewById(R.id.lbCaVat);
        lbCaNetTotal = (TextView) findViewById(R.id.lbCaNetTotal);
        lbCaRemark = (TextView) findViewById(R.id.lbCaRemark);
        lbCaCashReceive1 = (TextView) findViewById(R.id.lbCaCashReceive1);
        lbCaCashReceive2 = (TextView) findViewById(R.id.lbCaCashReceive2);
        lbCaCashReceive3 = (TextView) findViewById(R.id.lbCaCashReceive3);
        lbCaCashDraw1 = (TextView) findViewById(R.id.lbCaCashDraw1);
        lbCaCashDraw2 = (TextView) findViewById(R.id.lbCaCashDraw2);
        lbCaCashDraw3 = (TextView) findViewById(R.id.lbCaCashDraw3);
        lbCaCntOrder = (TextView) findViewById(R.id.lbCaCntOrder);
        txtCaAmt = (TextView) findViewById(R.id.txtCaAmt);
        txtCaDiscount = (TextView) findViewById(R.id.txtCaDiscount);
        txtCaTotal = (TextView) findViewById(R.id.txtCaTotal);
        txtCaSC = (TextView) findViewById(R.id.txtCaSC);
        txtCaVat = (TextView) findViewById(R.id.txtCaVat);
        txtCaNetTotal = (TextView) findViewById(R.id.txtCaNetTotal);
        txtCaRemark = (EditText) findViewById(R.id.txtCaRemark);
        txtCaCashReceive1 = (EditText) findViewById(R.id.txtCaCashReceive1);
        txtCaCashReceive2 = (EditText) findViewById(R.id.txtCaCashReceive2);
        txtCaCashReceive3 = (EditText) findViewById(R.id.txtCaCashReceive3);
        txtCaCashDraw1 = (EditText) findViewById(R.id.txtCaCashDraw1);
        txtCaCashDraw2 = (EditText) findViewById(R.id.txtCaCashDraw2);
        txtCaCashDraw3 = (EditText) findViewById(R.id.txtCaCashDraw3);
        txtCaCashReceive1Remark = (EditText) findViewById(R.id.txtCaCashReceive1Remark);
        txtCaCashReceive2Remark = (EditText) findViewById(R.id.txtCaCashReceive2Remark);
        txtCaCashReceive3Remark = (EditText) findViewById(R.id.txtCaCashReceive3Remark);
        txtCaCashDraw1Remark = (EditText) findViewById(R.id.txtCaCashDraw1Remark);
        txtCaCashDraw2Remark = (EditText) findViewById(R.id.txtCaCashDraw2Remark);
        txtCaCashDraw3Remark = (EditText) findViewById(R.id.txtCaCashDraw3Remark);
        txtCaUserPassword = (EditText) findViewById(R.id.txtCaUserPassword);
        txtCaPasswordVoid = (EditText) findViewById(R.id.txtCaPasswordVoid);
        btnCaSave = (Button) findViewById(R.id.btnCaSave);
        btnCaVoid = (Button) findViewById(R.id.btnCaVoid);
        layout16 = (LinearLayout) findViewById(R.id.layout16);
        layout17 = (LinearLayout) findViewById(R.id.layout17);
        chkCaActive = (Switch) findViewById(R.id.chkCaActive);

        lbCaAmt.setText(R.string.amt);
//        lbCaCntBill.setText(R.string.discount);
        lbCaDiscount.setText(R.string.discount);
        txtCaDiscount.setText(R.string.zero);
        txtCaNetTotal.setText(R.string.zero);
        txtCaVat.setText(R.string.zero);
        txtCaSC.setText(R.string.zero);
        txtCaTotal.setText(R.string.zero);
        lbCaTotal.setText(R.string.total);
        lbCaSC.setText(R.string.sc);
        lbCaVat.setText(R.string.vat);
        lbCaNetTotal.setText(R.string.nettotal);
        lbCaRemark.setText(R.string.remark);
        lbCaCashReceive1.setText(R.string.CashReceive1);
        lbCaCashReceive2.setText(R.string.CashReceive2);
        lbCaCashReceive3.setText(R.string.CashReceive3);
        lbCaCashDraw1.setText(R.string.CashDraw1);
        lbCaCashDraw2.setText(R.string.CashDraw2);
        lbCaCashDraw3.setText(R.string.CashDraw3);
        lbCaCloseDayDate.setText("ระบุวันที่");
        btnCaSave.setText(R.string.save);
        btnCaVoid.setText(R.string.void1);
        chkCaActive.setChecked(true);
        lbCaCntBill.setText(R.string.lbCaCntBill);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
//        lbCaCloseDayDate.setText(currentDateandTime);
//        final Calendar c = Calendar.getInstance();
        Date dt = new Date();
        year = dt.getYear()+1900;
        month = dt.getMonth();
        day = dt.getDate();
        chkCaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkCaActive.setText(R.string.activeon);
                    btnCaVoid.setVisibility(View.INVISIBLE);
                    txtCaPasswordVoid.setVisibility(View.INVISIBLE);
                }else{
                    chkCaActive.setText(R.string.activeoff);
                    btnCaVoid.setVisibility(View.VISIBLE);
                    txtCaPasswordVoid.setVisibility(View.INVISIBLE);
                }
            }
        });
        ArrayAdapter<String> adaRes = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboRes);
        cboCaRes.setAdapter(adaRes);
        setTheme();
        lbCaCloseDayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageLoad=true;
                showDialog(DATE_DIALOG_ID);
            }
        });
        btnCaVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnCaVoid.getText().toString().equals(getResources().getString(R.string.void1))){
                    txtCaPasswordVoid.setVisibility(View.VISIBLE);
                    btnCaVoid.setText(R.string.void1confrim);
                }else if(btnCaVoid.getText().toString().equals(getResources().getString(R.string.void1confrim))){
                    if(txtCaPasswordVoid.getText().toString().equals("")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(CloseDayAddActivity.this);
                        builder1.setMessage("Password ไม่ได้ป้อน");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtCaPasswordVoid.setSelection(0,txtCaPasswordVoid.getText().length());
                                txtCaPasswordVoid.setFocusable(true);
                            }
                        }).create().show();
                    }else{
                        if(rs.chkPasswordVoid(txtCaPasswordVoid.getText().toString())){
//                            String user = rs.chkUserByPassword(txtCaUserPassword.getText().toString());
                            if(rs.AccessMode.equals("Standalone")) {
                                Log.d("cd.ID ",cd.ID);
                                jarr = daS.ClosedayVoid(rs.chkUserByPassword(txtCaPasswordVoid.getText().toString()), cd.ID);
                                getClosedayVoid();
                            }else if(rs.AccessMode.equals("Internet")){
                                new CloseDayVoid().execute(cd.ID,rs.chkUserByPassword(txtCaUserPassword.getText().toString()));
                            }else{
                                new CloseDayVoid().execute(cd.ID,rs.chkUserByPassword(txtCaUserPassword.getText().toString()));
                            }
                        }
                    }
                }
            }
        });
        btnCaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnCaSave.getText().toString().equals(getResources().getString(R.string.save))){
                    txtCaUserPassword.setVisibility(View.VISIBLE);
                    btnCaSave.setText(R.string.closedayconfirm);
                }else if(btnCaSave.getText().toString().equals(getResources().getString(R.string.closedayconfirm))){
                    if(txtCaUserPassword.getText().toString().equals("")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(CloseDayAddActivity.this);
                        builder1.setMessage("Password ไม่ได้ป้อน");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtCaUserPassword.setSelection(0,txtCaUserPassword.getText().length());
                                txtCaUserPassword.setFocusable(true);
                            }
                        }).create().show();
                    }else{
                        if(rs.chkPasswordCloseDay(txtCaUserPassword.getText().toString())){
                            setCloseDay();
                            if(rs.AccessMode.equals("Standalone")) {
                                Log.d("cd.ID ",cd.ID);
                                jaCa = daS.ClosedayInsert(cd.ID,cd.CloseDayDate, cd.ResId,cd.Amt,cd.Discount,cd.Total,cd.SC,cd.Vat,cd.NetTotal,cd.Remark,cd.CntBill
                                        ,cd.Amt,cd.CntOrder,cd.AmtOrder,cd.CloseDayUser,cd.CashR1,cd.CashR2,cd.CashR3,cd.CashD1,cd.CashD2,cd.CashD3
                                        ,cd.CashR1Remark,cd.CashR2Remark,cd.CashR3Remark,cd.CashD1Remark,cd.CashD2Remark,cd.CashD3Remark,cd.Weather);
                                getClosedayInsert();
                            }else if(rs.AccessMode.equals("Internet")){
                                new retrieveCloseDayInsert().execute();
                            }else{
                                new retrieveCloseDayInsert().execute();
                            }
                        }
                    }
                }
            }
        });
        txtCaUserPassword.setVisibility(View.INVISIBLE);
        layout17.setVisibility(View.INVISIBLE);
        setControlNewCloseDay();

        if(cboCaRes.getChildCount()>0){
            String resid = rs.getRes(cboCaRes.getSelectedItem().toString(),"id");
            String month1="", day1="";
            month1 = "00"+(month+1);
            month1 = month1.substring(month1.length()-2);
            day1 = "00"+day;
            day1 = day1.substring(day1.length()-2);
            if(rs.AccessMode.equals("Standalone")) {
                jaCa = daS.BillByCloseday(resid,year+"-"+month1+"-"+day1);
                getCloseday();
            }else if(rs.AccessMode.equals("Internet")){
                new retrieveCloseDay().execute(year+"-"+month1+"-"+day1,resid);
            }else{
                new retrieveCloseDay().execute(year+"-"+month1+"-"+day1,resid);
            }
        }
        chkCaActive.setChecked(false);
        txtCaPasswordVoid.setVisibility(View.INVISIBLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set gendate picker as current gendate
                return new DatePickerDialog(this, datePickerListener,  year, month,day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            String month1="", day1="";
            month1 = "00"+(month+1);
            month1 = month1.substring(month1.length()-2);
            day1 = "00"+day;
            day1 = day1.substring(day1.length()-2);
//            dpCloseDayDate.init(year, month, day, null);
            // set selected gendate into textview
            lbCaCloseDayDate.setText(new StringBuilder().append(day).append("-").append(month + 1).append("-").append(year).append(" "));
            String resid = rs.getRes(cboCaRes.getSelectedItem().toString(),"genid");
            setControlNewCloseDay();
            if(rs.AccessMode.equals("Standalone")) {
                jaCa = daS.BillByCloseday(resid,year+"-"+month1+"-"+day1);
                getCloseday();
            }else if(rs.AccessMode.equals("Internet")){
                if(pageLoad) new retrieveCloseDay().execute(year+"-"+month1+"-"+day1, resid);
            }else{
                if(pageLoad) new retrieveCloseDay().execute(year+"-"+month1+"-"+day1, resid);
            }

            pageLoad =false;
            // set selected gendate into datepicker also
        }
    };
    private void setControlNewCloseDay(){
        try{
            txtCaAmt.setText("");
            txtCaDiscount.setText("");
            txtCaRemark.setText("");
            txtCaCashReceive1.setText("");
            txtCaCashReceive2.setText("");
            txtCaCashReceive3.setText("");
            txtCaCashDraw1.setText("");
            txtCaCashDraw2.setText("");
            txtCaCashDraw3.setText("");
            txtCaCashReceive1Remark.setText("");
            txtCaCashReceive2Remark.setText("");
            txtCaCashReceive3Remark.setText("");
            txtCaCashDraw1Remark.setText("");
            txtCaCashDraw2Remark.setText("");
            txtCaCashDraw3Remark.setText("");
            txtCaUserPassword.setText("");
            lbCaCntOrder.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void setTheme(){
        lbCaAmt.setTextSize(textSize);
        lbCaCloseDayDate.setTextSize(textSize);
        lbCaDiscount.setTextSize(textSize);
        lbCaTotal.setTextSize(textSize);
        lbCaSC.setTextSize(textSize);
        lbCaVat.setTextSize(textSize);
        lbCaNetTotal.setTextSize(textSize+2);
        lbCaRemark.setTextSize(textSize);
        lbCaCashReceive1.setTextSize(textSize);
        lbCaCashReceive2.setTextSize(textSize);
        lbCaCashReceive3.setTextSize(textSize);
        lbCaCashDraw1.setTextSize(textSize);
        lbCaCashDraw2.setTextSize(textSize);
        lbCaCashDraw3.setTextSize(textSize);
        lbCaCntBill.setTextSize(textSize);
        lbCaCntOrder.setTextSize(textSize);

        txtCaAmt.setTextSize(textSize);
        txtCaDiscount.setTextSize(textSize);
        txtCaTotal.setTextSize(textSize);
        txtCaSC.setTextSize(textSize);
        txtCaVat.setTextSize(textSize);
        txtCaNetTotal.setTextSize(textSize+2);
        txtCaRemark.setTextSize(textSize);
        txtCaCashReceive1.setTextSize(textSize);
        txtCaCashReceive2.setTextSize(textSize);
        txtCaCashReceive3.setTextSize(textSize);
        txtCaCashDraw1.setTextSize(textSize);
        txtCaCashDraw2.setTextSize(textSize);
        txtCaCashDraw3.setTextSize(textSize);
        txtCaCashReceive1Remark.setTextSize(textSize);
        txtCaCashReceive2Remark.setTextSize(textSize);
        txtCaCashReceive3Remark.setTextSize(textSize);
        txtCaCashDraw1Remark.setTextSize(textSize);
        txtCaCashDraw2Remark.setTextSize(textSize);
        txtCaCashDraw3Remark.setTextSize(textSize);
        txtCaUserPassword.setTextSize(textSize);

        lbCaAmt.setBackgroundColor(getResources().getColor(R.color.amt));
        txtCaAmt.setBackgroundColor(getResources().getColor(R.color.amt));
        lbCaTotal.setBackgroundColor(getResources().getColor(R.color.total));
        txtCaTotal.setBackgroundColor(getResources().getColor(R.color.total));
        lbCaNetTotal.setBackgroundColor(getResources().getColor(R.color.nettotal));
        txtCaNetTotal.setBackgroundColor(getResources().getColor(R.color.nettotal));
        lbCaCntBill.setBackgroundColor(getResources().getColor(R.color.closedayamt));
        lbCaCntOrder.setBackgroundColor(getResources().getColor(R.color.closedayamt));
        lbCaNetTotal.setTextColor(Color.WHITE);
        txtCaNetTotal.setTextColor(Color.WHITE);
//        lbCaAmt.setTextSize(textSize);
//        lbCaAmt.setTextSize(textSize);
//        lbCaAmt.setTextSize(textSize);
    }
    private void setCloseDay(){
        String id = UUID.randomUUID().toString();
        String month1="", day1="";
        month1 = "00"+(month+1);
        month1 = month1.substring(month1.length()-2);
        day1 = "00"+day;
        day1 = day1.substring(day1.length()-2);
        cd = new CloseDay();
        cd.Active="";
        cd.Amt = txtCaAmt.getText().toString();
        cd.AmtOrder = "0.0";
        cd.ID = id;
        cd.CashD1 = rs.chkNumber(txtCaCashDraw1.getText().toString());
        cd.CashD1Remark = txtCaCashDraw1Remark.getText().toString();
        cd.CashD2 = rs.chkNumber(txtCaCashDraw2.getText().toString());
        cd.CashD2Remark = txtCaCashDraw2Remark.getText().toString();
        cd.CashD3 = rs.chkNumber(txtCaCashDraw3.getText().toString());
        cd.CashD3Remark = txtCaCashDraw3Remark.getText().toString();
        cd.CashR1 = rs.chkNumber(txtCaCashReceive1.getText().toString());
        cd.CashR1Remark = txtCaCashReceive1Remark.getText().toString();
        cd.CashR2 = rs.chkNumber(txtCaCashReceive2.getText().toString());
        cd.CashR2Remark = txtCaCashReceive2Remark.getText().toString();
        cd.CashR3 = rs.chkNumber(txtCaCashReceive3.getText().toString());
        cd.CashR3Remark = txtCaCashReceive3Remark.getText().toString();
        cd.CloseDayDate = year+"-"+month1+"-"+day1;
        cd.CloseDayUser = rs.chkUserByPassword(txtCaUserPassword.getText().toString());
        cd.Cnt = rs.chkNumber(lbCaCntBill.getText().toString().replace("จำนวนบิล",""));
        cd.CntOrder = rs.chkNumber(lbCaCntOrder.getText().toString().replace("จำนวนบิล",""));
        cd.CntBill = rs.chkNumber(lbCaCntBill.getText().toString().replace("จำนวนบิล",""));
        cd.Discount = txtCaDiscount.getText().toString();
//        cd.ID = ID;
        cd.NetTotal = txtCaNetTotal.getText().toString();
        cd.Remark = txtCaRemark.getText().toString();
        cd.ResId = rs.getRes(cboCaRes.getSelectedItem().toString(),"genid");
        cd.SC = txtCaSC.getText().toString();
        cd.StatusVoid = "0";
        cd.Total = txtCaTotal.getText().toString();
        cd.Vat = txtCaVat.getText().toString();
        cd.VoidDate="";
        cd.VoidUser="";
    }
    class retrieveCloseDay extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {+
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair("closeday_date", arg0[0]));
            params.add(new BasicNameValuePair("res_id", arg0[1]));
            jaCa = jsonparser.getJSONFromUrl(rs.hostBillCloseDay,params);
            Log.d("closeday_date",arg0[0]);
            Log.d("res_id",arg0[1]);
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getCloseday();
//            setControlNewCloseDay();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {

            String aaa = ab;

            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Loading...");
            pd.setMessage("Loading Close day...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();
        }
    }
    private void getCloseday(){
        try {
            if(jaCa!=null){
                Log.d("jaCa",jaCa.toString());
                JSONObject catObj = (JSONObject) jaCa.get(0);
                bill = new Bill();
                cd.ID = catObj.getString(cd.dbID);
                if(!cd.ID.equals("")){
                    txtCaAmt.setText(catObj.getString(cd.dbAmt));
                    txtCaDiscount.setText(catObj.getString(cd.dbDiscount));
                    txtCaTotal.setText(catObj.getString(cd.dbTotal));
                    txtCaVat.setText(catObj.getString(cd.dbVat));
                    txtCaNetTotal.setText(catObj.getString(cd.dbNetTotal));
                    txtCaSC.setText(catObj.getString(cd.dbSC));
                    lbCaCntBill.setText("จำนวนบิล "+catObj.getString(cd.dbCntBill));
                    lbCaCntOrder.setText("จำนวนรายการ "+catObj.getString(cd.dbCntOrder));
                    txtCaRemark.setText(catObj.getString(cd.dbRemark));
                    txtCaCashReceive1.setText(catObj.getString(cd.dbCashR1));
                    txtCaCashReceive2.setText(catObj.getString(cd.dbCashR2));
                    txtCaCashReceive3.setText(catObj.getString(cd.dbCashR3));
                    txtCaCashDraw1.setText(catObj.getString(cd.dbCashD1));
                    txtCaCashDraw2.setText(catObj.getString(cd.dbCashD2));
                    txtCaCashDraw3.setText(catObj.getString(cd.dbCashD3));
                    txtCaCashReceive1Remark.setText(catObj.getString(cd.dbCashR1Remark));
                    txtCaCashReceive2Remark.setText(catObj.getString(cd.dbCashR2Remark));
                    txtCaCashReceive3Remark.setText(catObj.getString(cd.dbCashR3Remark));
                    txtCaCashDraw1Remark.setText(catObj.getString(cd.dbCashD1Remark));
                    txtCaCashDraw2Remark.setText(catObj.getString(cd.dbCashD2Remark));
                    txtCaCashDraw3Remark.setText(catObj.getString(cd.dbCashD3Remark));
                    txtCaUserPassword.setText("");
                    layout17.setVisibility(View.VISIBLE);
                    layout16.setVisibility(View.INVISIBLE);
                    chkCaActive.setChecked(true);
                }else{
                    txtCaAmt.setText(catObj.getString(bill.dbAmt));
                    txtCaDiscount.setText(catObj.getString(bill.dbDiscount));
                    txtCaTotal.setText(catObj.getString(bill.dbTotal));
                    txtCaVat.setText(catObj.getString(bill.dbVat));
                    txtCaNetTotal.setText(catObj.getString(bill.dbNetTotal));
                    txtCaSC.setText(catObj.getString(bill.dbSC));
                    lbCaCntBill.setText("จำนวนบิล "+catObj.getString("cnt_bill"));
                    layout17.setVisibility(View.INVISIBLE);
                    layout16.setVisibility(View.VISIBLE);
                    chkCaActive.setChecked(false);
                }
//                    bill.SC = catObj.getString("sc");
            }
        } catch (JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("retrieveCloseDay 1 ",e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            Log.e("retrieveCloseDay 2 ",e.getMessage());
        }
    }
    class retrieveCloseDayInsert extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {+
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(cd.dbID, cd.ID));
            params.add(new BasicNameValuePair(cd.dbCloseDayDate, cd.CloseDayDate));
            params.add(new BasicNameValuePair(cd.dbResId, cd.ResId));
            params.add(new BasicNameValuePair(cd.dbAmt, cd.Amt));
            params.add(new BasicNameValuePair(cd.dbDiscount, cd.Discount));
            params.add(new BasicNameValuePair(cd.dbTotal, cd.Total));
            params.add(new BasicNameValuePair(cd.dbSC, cd.SC));
            params.add(new BasicNameValuePair(cd.dbVat, cd.Vat));
            params.add(new BasicNameValuePair(cd.dbNetTotal, cd.NetTotal));
            params.add(new BasicNameValuePair(cd.dbRemark, cd.Remark));
            params.add(new BasicNameValuePair(cd.dbActive, cd.Active));
            params.add(new BasicNameValuePair(cd.dbStatusVoid, cd.StatusVoid));
            params.add(new BasicNameValuePair(cd.dbVoidDate, cd.VoidDate));
            params.add(new BasicNameValuePair(cd.dbVoidUser, cd.VoidUser));
            params.add(new BasicNameValuePair(cd.dbCntBill, cd.CntBill));
            params.add(new BasicNameValuePair(cd.dbCntOrder, cd.CntOrder));
            params.add(new BasicNameValuePair(cd.dbAmtOrder, cd.AmtOrder));
            params.add(new BasicNameValuePair(cd.dbAmtBill, cd.Amt));
            params.add(new BasicNameValuePair(cd.dbCloseDayUser, cd.CloseDayUser));
            params.add(new BasicNameValuePair(cd.dbCashR1, cd.CashR1));
            params.add(new BasicNameValuePair(cd.dbCashR2, cd.CashR2));
            params.add(new BasicNameValuePair(cd.dbCashR3, cd.CashR3));
            params.add(new BasicNameValuePair(cd.dbCashD1, cd.CashD1));
            params.add(new BasicNameValuePair(cd.dbCashD2, cd.CashD2));
            params.add(new BasicNameValuePair(cd.dbCashD3, cd.CashD3));
            params.add(new BasicNameValuePair(cd.dbCashR1Remark, cd.CashR1Remark));
            params.add(new BasicNameValuePair(cd.dbCashR2Remark, cd.CashR2Remark));
            params.add(new BasicNameValuePair(cd.dbCashR3Remark, cd.CashR3Remark));
            params.add(new BasicNameValuePair(cd.dbCashD1Remark, cd.CashD1Remark));
            params.add(new BasicNameValuePair(cd.dbCashD2Remark, cd.CashD2Remark));
            params.add(new BasicNameValuePair(cd.dbCashD3Remark, cd.CashD3Remark));
            jaCa = jsonparser.getJSONFromUrl(rs.hostCloseDayInsert,params);

//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return ab;
        }//50663
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getClosedayInsert();
//            setControlNewCloseDay();
//            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
//            pd = new ProgressDialog(CloseDayAddActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Save...");
            pd.setMessage("Save Close Day...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();
        }
    }
    private void getClosedayInsert(){
        try {
            if(jaCa!=null){
                JSONObject catObj = (JSONObject) jaCa.get(0);

                Log.d("sql",catObj.getString("sql"));
                if(catObj.getString("success").equals("1")){
                    pd.dismiss();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(CloseDayAddActivity.this);
                    builder1.setMessage("บันทึกข้อมูล  เรียบร้อย")
                    ;
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btnCaSave.setEnabled(false);
                        }
                    }).create().show();
                    txtCaUserPassword.setText("");
                }
                if(rs.PrnC.equals("ON")){
                    pBE = new PrintCloseDayEpson(CloseDayAddActivity.this);
//                        pBE.runPrintCloseDayEpson(getResources(), ab, rs.ResName, rs.ReceiptH1,rs.ReceiptH2,rs.ReceiptF1,rs.ReceiptF2, cboBaArea.getSelectedItem().toString(),cboBaTable.getSelectedItem().toString(), prn,lbBaAmt1.getText().toString(),
//                                lbBaDiscount1.getText().toString(),lbBaTotal1.getText().toString(), lbBaSC1.getText().toString(),lbBaVat1.getText().toString(),lbBaNetTotal1.getText().toString());
                    pBE = null;
                }
            }
        } catch (JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    class CloseDayVoid extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {+
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(cd.dbID, arg0[0]));
            params.add(new BasicNameValuePair(cd.dbVoidRemark, ""));
            jaCa = jsonparser.getJSONFromUrl(rs.hostCloseDayVoid,params);
            Log.d("CloseDayVoid cdid",arg0[0]);
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getClosedayVoid();
//            setControlNewCloseDay();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {

            String aaa = ab;
            pd = new ProgressDialog(CloseDayAddActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Loading...");
            pd.setMessage("Loading Close day...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();
        }
    }
    private void getClosedayVoid(){
        try {
            if(jarr!=null){
                Log.d("jarr",jarr.toString());
                JSONObject catObj = (JSONObject) jarr.get(0);
                if(catObj.getString("success").equals("1")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(CloseDayAddActivity.this);
                    builder1.setMessage("ยกเลิกข้อมูล  เรียบร้อย");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btnCaVoid.setEnabled(false);
                        }
                    }).create().show();
                }
//                    ID = catObj.getString("closeday_id");
//                    bill.SC = catObj.getString("sc");
            }
        } catch (JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("retrieveCloseDay",e.getMessage());
        }
    }
}
