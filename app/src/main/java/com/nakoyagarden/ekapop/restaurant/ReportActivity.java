package com.nakoyagarden.ekapop.restaurant;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.print.pdf.PrintedPdfDocument;
import android.provider.DocumentsContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class ReportActivity extends AppCompatActivity {
    RadioButton chkRYearly,chkRMonthly,chkRDaily;
    RadioButton chkDailyGroupByFoods,chkDailyGroupByFoodsType,chkDailyGroupByCloseday,chkDailyGroupByHour;
    RadioButton chkMonthlyGroupByFoods,chkMonthlyGroupByFoodsType,chkMonthlyGroupByCloseday,chkMonthlyGroupByHour;
    RadioButton chkYearlyGroupByFoods,chkYearlyGroupByFoodsType,chkYearlyGroupByCloseday,chkYearlyGroupByHour;

    RadioGroup chkRGYearly,chkRGMonthly,chkRGDaily;
    TextView lbRDailyDateStart,lbRDailyDateEnd,txtRDailyDateStart,txtRDailyDateEnd,lbRMonthlyStart,lbRMonthlyEnd,lbRYearlyStart,lbRYearlyEnd;
    Spinner cboMonthlyStart,cboMonthlyEnd,cboYearlyStart,cboYearlyEnd,cboMonthlyYearStart,cboMonthlyYearEnd;
    LinearLayout layoutDaily, layoutMonthly, layoutYearly;

    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();

    Button btnRPrint;

    private RestaurantControl rs;
    DatabaseSQLi daS;

    static final int DATE_DIALOG_ID = 0;
    static final int DATE1_DIALOG_ID = 1;
    private int year,textSize=20;
    private int month;
    private int day;

    String flagtxtDate="";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);
        rs.setCboMOnth(getBaseContext());

        chkRDaily = (RadioButton) findViewById(R.id.chkRDaily);
        chkRMonthly = (RadioButton) findViewById(R.id.chkRMonthly);
        chkRYearly = (RadioButton) findViewById(R.id.chkRYearly);
        btnRPrint = (Button) findViewById(R.id.btnRPrint);

        chkDailyGroupByFoods = (RadioButton) findViewById(R.id.chkDailyGroupByFoods);
        chkDailyGroupByFoodsType = (RadioButton) findViewById(R.id.chkDailyGroupByFoodsType);
        chkDailyGroupByCloseday = (RadioButton) findViewById(R.id.chkDailyGroupByCloseday);
        chkDailyGroupByHour = (RadioButton) findViewById(R.id.chkDailyGroupByHour);

        chkMonthlyGroupByFoods = (RadioButton) findViewById(R.id.chkMonthlyGroupByFoods);
        chkMonthlyGroupByFoodsType = (RadioButton) findViewById(R.id.chkMonthlyGroupByFoodsType);
        chkMonthlyGroupByCloseday = (RadioButton) findViewById(R.id.chkMonthlyGroupByCloseday);
        chkMonthlyGroupByHour = (RadioButton) findViewById(R.id.chkMonthlyGroupByHour);

        chkYearlyGroupByFoods = (RadioButton) findViewById(R.id.chkYearlyGroupByFoods);
        chkYearlyGroupByFoodsType = (RadioButton) findViewById(R.id.chkYearlyGroupByFoodsType);
        chkYearlyGroupByCloseday = (RadioButton) findViewById(R.id.chkYearlyGroupByCloseday);
        chkYearlyGroupByHour = (RadioButton) findViewById(R.id.chkYearlyGroupByHour);

        chkRGYearly = (RadioGroup) findViewById(R.id.chkRGYearly);
        chkRGMonthly = (RadioGroup) findViewById(R.id.chkRGMonthly);
        chkRGDaily = (RadioGroup) findViewById(R.id.chkRGDaily);
        lbRDailyDateStart = (TextView) findViewById(R.id.lbRDailyDateStart);
        lbRDailyDateEnd = (TextView) findViewById(R.id.lbRDailyDateEnd);
        txtRDailyDateStart = (TextView) findViewById(R.id.txtRDailyDateStart);
        txtRDailyDateEnd = (TextView) findViewById(R.id.txtRDailyDateEnd);
        lbRMonthlyStart = (TextView) findViewById(R.id.lbRMonthlyStart);
        lbRMonthlyEnd = (TextView) findViewById(R.id.lbRMonthlyEnd);
        lbRYearlyStart = (TextView) findViewById(R.id.lbRYearlyStart);
        lbRYearlyEnd = (TextView) findViewById(R.id.lbRYearlyEnd);
        cboMonthlyStart = (Spinner) findViewById(R.id.cboMonthlyStart);
        cboMonthlyEnd = (Spinner) findViewById(R.id.cboMonthlyEnd);
        cboMonthlyYearEnd = (Spinner) findViewById(R.id.cboMonthlyYearEnd);
        cboMonthlyYearStart = (Spinner) findViewById(R.id.cboMonthlyYearStart);
        cboYearlyStart = (Spinner) findViewById(R.id.cboYearlyStart);
        cboYearlyEnd = (Spinner) findViewById(R.id.cboYearlyEnd);

        layoutDaily = (LinearLayout) findViewById(R.id.layoutDaily);
        layoutMonthly = (LinearLayout) findViewById(R.id.layoutMonthly);
        layoutYearly = (LinearLayout) findViewById(R.id.layoutYearly);

        chkRDaily.setText(R.string.chkRDaily);
        chkRMonthly.setText(R.string.chkRMonthly);
        chkRYearly.setText(R.string.chkRYearly);

        chkDailyGroupByFoods.setText(R.string.chkDailyGroupByFoods);
        chkDailyGroupByFoodsType.setText(R.string.chkDailyGroupByFoodsType);
        chkDailyGroupByCloseday.setText(R.string.chkDailyGroupByCloseday);
        chkDailyGroupByHour.setText(R.string.chkDailyGroupByHour);

        chkMonthlyGroupByFoods.setText(R.string.chkMonthlyGroupByFoods);
        chkMonthlyGroupByFoodsType.setText(R.string.chkMonthlyGroupByFoodsType);
        chkMonthlyGroupByCloseday.setText(R.string.chkMonthlyGroupByCloseday);
        chkMonthlyGroupByHour.setText(R.string.chkMonthlyGroupByHour);

        chkYearlyGroupByFoods.setText(R.string.chkYearlyGroupByFoods);
        chkYearlyGroupByFoodsType.setText(R.string.chkYearlyGroupByFoodsType);
        chkYearlyGroupByCloseday.setText(R.string.chkYearlyGroupByCloseday);
        chkYearlyGroupByHour.setText(R.string.chkYearlyGroupByHour);

        lbRDailyDateStart.setText(R.string.lbRDailyDateStart);
        lbRDailyDateEnd.setText(R.string.lbRDailyDateEnd);
        lbRMonthlyStart.setText(R.string.lbRMonthlyStart);
        lbRMonthlyEnd.setText(R.string.lbRMonthlyEnd);
        lbRYearlyStart.setText(R.string.lbRYearlyStart);
        lbRYearlyEnd.setText(R.string.lbRYearlyEnd);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime = sdf.format(new Date());
        txtRDailyDateStart.setText(currentDateandTime);
        txtRDailyDateEnd.setText(currentDateandTime);

        cboMonthlyStart.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboMonth));
        cboMonthlyEnd.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboMonth));
        cboMonthlyYearEnd.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboYear));
        cboMonthlyYearStart.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboYear));
        cboYearlyStart.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboYear));
        cboYearlyEnd.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboYear));

        chkRGDaily.setVisibility(View.INVISIBLE);
        chkRGMonthly.setVisibility(View.INVISIBLE);
        chkRGYearly.setVisibility(View.INVISIBLE);
        layoutDaily.setVisibility(View.INVISIBLE);
        layoutMonthly.setVisibility(View.INVISIBLE);
        layoutYearly.setVisibility(View.INVISIBLE);
        Date dt = new Date();
        year = dt.getYear()+1900;
        month = dt.getMonth();
        day = dt.getDate();

        chkRDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChkReportDailyShow();
            }
        });
        chkRMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChkReportMonthlyShow();
            }
        });
        chkRYearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChkReportYearlyShow();
            }
        });
        lbRDailyDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        lbRDailyDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE1_DIALOG_ID);
            }
        });
        btnRPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkDailyGroupByFoods.isChecked()){
                    String dateStart="", dateEnd="";
                    dateStart = txtRDailyDateStart.getText().toString();
                    dateEnd = txtRDailyDateEnd.getText().toString();
                    dateStart = dateStart.substring(dateStart.length()-4)+"-"+dateStart.substring(3,5)+"-"+dateStart.substring(0,2);
                    dateEnd = dateEnd.substring(dateEnd.length()-4)+"-"+dateEnd.substring(3,5)+"-"+dateEnd.substring(0,2);
                    new retrieveDailyGroupByFoods().execute(dateStart,dateEnd);
                }else if(chkDailyGroupByFoodsType.isChecked()){

                }
            }
        });
        txtRDailyDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        txtRDailyDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE1_DIALOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                flagtxtDate = "txtRDailyDateStart";
                // set gendate picker as current gendate
                return new DatePickerDialog(this, datePickerListener,  year, month,day);
            case DATE1_DIALOG_ID:
                // set gendate picker as current gendate
                flagtxtDate = "txtRDailyDateEnd";
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
            if(flagtxtDate.equals("txtRDailyDateStart")){
                txtRDailyDateStart.setText(day1+"-"+month1+"-"+year);
            }else if(flagtxtDate.equals("txtRDailyDateEnd")){
                txtRDailyDateEnd.setText(day1+"-"+month1+"-"+year);
            }
//            return day1+""+month1+""+year;
        }
    };

    private void setChkReportDailyShow(){
        chkRGDaily.setVisibility(View.VISIBLE);
        chkRGMonthly.setVisibility(View.INVISIBLE);
        chkRGYearly.setVisibility(View.INVISIBLE);
        layoutDaily.setVisibility(View.VISIBLE);
        layoutMonthly.setVisibility(View.INVISIBLE);
        layoutYearly.setVisibility(View.INVISIBLE);
    }
    private void setChkReportMonthlyShow(){
        chkRGDaily.setVisibility(View.INVISIBLE);
        chkRGMonthly.setVisibility(View.VISIBLE);
        chkRGYearly.setVisibility(View.INVISIBLE);
        layoutDaily.setVisibility(View.INVISIBLE);
        layoutMonthly.setVisibility(View.VISIBLE);
        layoutYearly.setVisibility(View.INVISIBLE);
    }
    private void setChkReportYearlyShow(){
        chkRGDaily.setVisibility(View.INVISIBLE);
        chkRGMonthly.setVisibility(View.INVISIBLE);
        chkRGYearly.setVisibility(View.VISIBLE);
        layoutDaily.setVisibility(View.INVISIBLE);
        layoutMonthly.setVisibility(View.INVISIBLE);
        layoutYearly.setVisibility(View.VISIBLE);
    }
    class retrieveDailyGroupByFoods extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            Log.d("DailyGroupByFoods ", arg0[0]+" "+arg0[1]);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair("date_start", arg0[0]));
            params.add(new BasicNameValuePair("date_end", arg0[1]));

            jarr = jsonparser.getJSONFromUrl(rs.hostDailyGroupByFoods,params);
            return "";
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            pd.dismiss();
            setControl();
        }
        @Override
        protected void onPreExecute() {
            String aaa = "";
            pd = new ProgressDialog(ReportActivity.this);
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
    private void setControl(){
        try {
//            ar = new Area();
            if((jarr!=null) && (!jarr.equals("[]"))){
                JSONObject catObj = (JSONObject) jarr.get(0);
                rs.flagReport = "DailyGroupByFoods";
                rs.jarrR = jarr.toString();
                startActivityForResult(new Intent(this, ReportViewActivity.class).putExtra("RestaurantControl",rs), 0);


            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
