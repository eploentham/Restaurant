package com.nakoyagarden.ekapop.restaurant;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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
    private PrintBillEpson pBE;

    Button btnBaSave;
    TextView lbCaAmt,lbCaCloseDayDate, lbCaDiscount, lbCaTotal, lbCaSC, lbCaVat, lbCaNetTotal, lbCaRemark, lbCaCashReceive1, lbCaCashReceive2, lbCaCashReceive3, lbCaCashDraw1;
    TextView lbCaCashDraw2, lbCaCashDraw3,lbCaCntBill, lbCaCntOrder;

    TextView txtCaAmt, txtCaDiscount, txtCaTotal, txtCaSC, txtCaVat, txtCaNetTotal;
    EditText txtCaRemark, txtCaCashReceive1, txtCaCashReceive2, txtCaCashReceive3, txtCaCashDraw1;
    EditText txtCaCashDraw2, txtCaCashDraw3, txtCaCashReceive1Remark, txtCaCashReceive2Remark, txtCaCashReceive3Remark, txtCaCashDraw1Remark, txtCaCashDraw2Remark;
    EditText txtCaCashDraw3Remark, txtCaUserPassword;
    Spinner cboCaRes;
    DatePicker dpCloseDayDate;

    String ID="";

    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jaCa;

    ProgressDialog pd;
    Bill bill;
    CloseDay cd;
    private int year,textSize=20;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 0;
    LocalActivityManager mLocalActivityManager;
    Boolean pageLoad=Boolean.FALSE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_day_add);
        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");

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
        btnBaSave = (Button) findViewById(R.id.btnCaSave);

        lbCaAmt.setText(R.string.amt);
//        lbCaCntBill.setText(R.string.discount);
        lbCaDiscount.setText(R.string.discount);
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
        lbCaCloseDayDate.setText("ไม่ระบุวัยที่");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
//        lbCaCloseDayDate.setText(currentDateandTime);
//        final Calendar c = Calendar.getInstance();
        Date dt = new Date();
        year = dt.getYear()+1900;
        month = dt.getMonth();
        day = dt.getDate();

        ArrayAdapter<String> adaRes = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboRes);
        cboCaRes.setAdapter(adaRes);
//        setTheme();
        lbCaCloseDayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageLoad=true;
                showDialog(DATE_DIALOG_ID);
            }
        });
        btnBaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        new retrieveCloseDayInsert().execute();
                    }

                }
            }
        });
        String resid = rs.getRes(cboCaRes.getSelectedItem().toString(),"id");
        setControlNewCloseDay();
        new retrieveCloseDay().execute(year+"-"+(month+1)+"-"+day,resid);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
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
//            dpCloseDayDate.init(year, month, day, null);
            // set selected date into textview
            lbCaCloseDayDate.setText(new StringBuilder().append(day).append("-").append(month + 1).append("-").append(year).append(" "));
            String resid = rs.getRes(cboCaRes.getSelectedItem().toString(),"id");
            setControlNewCloseDay();
            if(pageLoad) new retrieveCloseDay().execute(year+"-"+(month+1)+"-"+day, resid);
            pageLoad =false;
            // set selected date into datepicker also
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
        lbCaNetTotal.setTextSize(textSize);
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
        txtCaNetTotal.setTextSize(textSize);
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
//        lbCaAmt.setTextSize(textSize);
//        lbCaAmt.setTextSize(textSize);
//        lbCaAmt.setTextSize(textSize);
    }
    private void setCloseDay(){
        cd = new CloseDay();
        cd.Active="";
        cd.Amt = txtCaAmt.getText().toString();
        cd.AmtOrder = "0.0";
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
        cd.CloseDayDate = year+""+(month+1)+""+day;
        cd.CloseDayUser = txtCaUserPassword.getText().toString();
        cd.Cnt = rs.chkNumber(lbCaCntBill.getText().toString().replace("จำนวนบิล",""));
        cd.CntOrder = rs.chkNumber(lbCaCntOrder.getText().toString());
        cd.CntBill = rs.chkNumber(lbCaCntBill.getText().toString().replace("จำนวนบิล",""));
        cd.Discount = txtCaDiscount.getText().toString();
        cd.ID = ID;
        cd.NetTotal = txtCaNetTotal.getText().toString();
        cd.Remark = txtCaRemark.getText().toString();
        cd.ResId = rs.getRes(cboCaRes.getSelectedItem().toString(),"id");
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

//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            try {
                if(jaCa!=null){
                    JSONObject catObj = (JSONObject) jaCa.get(0);
                    bill = new Bill();
                    ID = catObj.getString("closeday_id");
                    if(!ID.equals("")){
                        txtCaAmt.setText(catObj.getString("amt"));
                        txtCaDiscount.setText(catObj.getString("discount"));
                        txtCaTotal.setText(catObj.getString("total"));
                        txtCaVat.setText(catObj.getString("vat"));
                        txtCaNetTotal.setText(catObj.getString("nettotal"));
                        txtCaSC.setText(catObj.getString("sc"));
                        lbCaCntBill.setText("จำนวนบิล "+catObj.getString("cnt_bill"));
                        lbCaCntOrder.setText("จำนวนบิล "+catObj.getString("cnt_order"));
                        txtCaRemark.setText(catObj.getString("remark"));
                        txtCaCashReceive1.setText(catObj.getString("cash_receive1"));
                        txtCaCashReceive2.setText(catObj.getString("cash_receive2"));
                        txtCaCashReceive3.setText(catObj.getString("cash_receive3"));
                        txtCaCashDraw1.setText(catObj.getString("cash_draw1"));
                        txtCaCashDraw2.setText(catObj.getString("cash_draw2"));
                        txtCaCashDraw3.setText(catObj.getString("cash_draw3"));
                        txtCaCashReceive1Remark.setText(catObj.getString("cash_receive1_remark"));
                        txtCaCashReceive2Remark.setText(catObj.getString("cash_receive2_remark"));
                        txtCaCashReceive3Remark.setText(catObj.getString("cash_receive3_remark"));
                        txtCaCashDraw1Remark.setText(catObj.getString("cash_draw1_remark"));
                        txtCaCashDraw2Remark.setText(catObj.getString("cash_draw2_remark"));
                        txtCaCashDraw3Remark.setText(catObj.getString("cash_draw3_remark"));
                        txtCaUserPassword.setText("");
                    }else{
                        txtCaAmt.setText(catObj.getString("amt"));
                        txtCaDiscount.setText(catObj.getString("discount"));
                        txtCaTotal.setText(catObj.getString("total"));
                        txtCaVat.setText(catObj.getString("vat"));
                        txtCaNetTotal.setText(catObj.getString("nettotal"));
                        txtCaSC.setText(catObj.getString("sc"));
                        lbCaCntBill.setText("จำนวนบิล "+catObj.getString("cnt_bill"));
                    }
//                    bill.SC = catObj.getString("sc");
                }
            } catch (JSONException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            setControlNewCloseDay();
//            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
//            pd = new ProgressDialog(CloseDayAddActivity.this);
//            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            pd.setTitle("Loading...");
//            pd.setMessage("Loading images...");
//            pd.setCancelable(false);
//            pd.setIndeterminate(false);
//            pd.setMax(100);
//            pd.setProgress(0);
//            pd.show();
        }
    }
    class retrieveCloseDayInsert extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {+
            String id = UUID.randomUUID().toString();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair("closeday_id", id));
            params.add(new BasicNameValuePair("closeday_date", cd.CloseDayDate));
            params.add(new BasicNameValuePair("res_id", cd.ResId));
            params.add(new BasicNameValuePair("amount", cd.Amt));
            params.add(new BasicNameValuePair("discount", cd.Discount));
            params.add(new BasicNameValuePair("total", cd.Total));
            params.add(new BasicNameValuePair("sc", cd.SC));
            params.add(new BasicNameValuePair("vat", cd.Vat));
            params.add(new BasicNameValuePair("nettotal", cd.NetTotal));
            params.add(new BasicNameValuePair("remark", cd.Remark));
            params.add(new BasicNameValuePair("active", cd.Active));
            params.add(new BasicNameValuePair("status_void", cd.StatusVoid));
            params.add(new BasicNameValuePair("void_date", cd.VoidDate));
            params.add(new BasicNameValuePair("void_user", cd.VoidUser));
            params.add(new BasicNameValuePair("cnt_bill", cd.CntBill));
            params.add(new BasicNameValuePair("cnt_order", cd.CntOrder));
            params.add(new BasicNameValuePair("amount_order", cd.AmtOrder));
            params.add(new BasicNameValuePair("bill_amount", cd.Amt));
            params.add(new BasicNameValuePair("closeday_user", cd.CloseDayUser));
            params.add(new BasicNameValuePair("cash_receive1", cd.CashR1));
            params.add(new BasicNameValuePair("cash_receive2", cd.CashR2));
            params.add(new BasicNameValuePair("cash_receive3", cd.CashR3));
            params.add(new BasicNameValuePair("cash_draw1", cd.CashD1));
            params.add(new BasicNameValuePair("cash_draw2", cd.CashD2));
            params.add(new BasicNameValuePair("cash_draw3", cd.CashD3));
            params.add(new BasicNameValuePair("cash_receive1_remark", cd.CashR1Remark));
            params.add(new BasicNameValuePair("cash_receive2_remark", cd.CashR2Remark));
            params.add(new BasicNameValuePair("cash_receive3_remark", cd.CashR3Remark));
            params.add(new BasicNameValuePair("cash_draw1_remark", cd.CashD1Remark));
            params.add(new BasicNameValuePair("cash_draw2_remark", cd.CashD2Remark));
            params.add(new BasicNameValuePair("cash_draw3_remark", cd.CashD3Remark));
            jaCa = jsonparser.getJSONFromUrl(rs.hostCloseDayInsert,params);

//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            try {
                if(jaCa!=null){
                    JSONObject catObj = (JSONObject) jaCa.get(0);
                    bill = new Bill();
                    ID = catObj.getString("closeday_id");
                    if(!ID.equals("")){
                        txtCaAmt.setText(catObj.getString("amt"));
                        txtCaDiscount.setText(catObj.getString("discount"));
                        txtCaTotal.setText(catObj.getString("total"));
                        txtCaVat.setText(catObj.getString("vat"));
                        txtCaNetTotal.setText(catObj.getString("nettotal"));
                        txtCaSC.setText(catObj.getString("sc"));
                        lbCaCntBill.setText("จำนวนบิล "+catObj.getString("cnt_bill"));
                        lbCaCntOrder.setText("จำนวนบิล "+catObj.getString("cnt_order"));
                        txtCaRemark.setText(catObj.getString("remark"));
                        txtCaCashReceive1.setText(catObj.getString("cash_receive1"));
                        txtCaCashReceive2.setText(catObj.getString("cash_receive2"));
                        txtCaCashReceive3.setText(catObj.getString("cash_receive3"));
                        txtCaCashDraw1.setText(catObj.getString("cash_draw1"));
                        txtCaCashDraw2.setText(catObj.getString("cash_draw2"));
                        txtCaCashDraw3.setText(catObj.getString("cash_draw3"));
                        txtCaCashReceive1Remark.setText(catObj.getString("cash_receive1_remark"));
                        txtCaCashReceive2Remark.setText(catObj.getString("cash_receive2_remark"));
                        txtCaCashReceive3Remark.setText(catObj.getString("cash_receive3_remark"));
                        txtCaCashDraw1Remark.setText(catObj.getString("cash_draw1_remark"));
                        txtCaCashDraw2Remark.setText(catObj.getString("cash_draw2_remark"));
                        txtCaCashDraw3Remark.setText(catObj.getString("cash_draw3_remark"));
                        txtCaUserPassword.setText("");
                    }else{
                        txtCaAmt.setText(catObj.getString("amt"));
                        txtCaDiscount.setText(catObj.getString("discount"));
                        txtCaTotal.setText(catObj.getString("total"));
                        txtCaVat.setText(catObj.getString("vat"));
                        txtCaNetTotal.setText(catObj.getString("nettotal"));
                        txtCaSC.setText(catObj.getString("sc"));
                        lbCaCntBill.setText("จำนวนบิล "+catObj.getString("cnt_bill"));
                    }
//                    bill.SC = catObj.getString("sc");
                }
            } catch (JSONException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            setControlNewCloseDay();
//            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
//            pd = new ProgressDialog(CloseDayAddActivity.this);
//            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            pd.setTitle("Loading...");
//            pd.setMessage("Loading images...");
//            pd.setCancelable(false);
//            pd.setIndeterminate(false);
//            pd.setMax(100);
//            pd.setProgress(0);
//            pd.show();
        }
    }
}
