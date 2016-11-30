package com.nakoyagarden.ekapop.restaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResAddActivity extends AppCompatActivity {
    TextView lbRaCode, lbRaName, lbRaRemark, lbRaSort1, lbRaActive, lbRaDefaultRes, lbRaRH1, lbRaRH2, lbRaRF1, lbRaRF2, lbRaTaxID, lbRaBillCode;
    EditText txtRaCode, txtRaName, txtRaRemark, txtRaSort1, txtRaRH1, txtRaRH2,  txtRaRF1, txtRaRF2,  txtRaTaxID, txtRaBillCode;
    Switch chkRaActive;
    Button btnRaSave;
    CheckBox chkRaDefaultRes;

    Restaurant res;

    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_add);
        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");

        lbRaCode = (TextView)findViewById(R.id.lbRaCode);
        lbRaName = (TextView)findViewById(R.id.lbRaName);
        lbRaRemark = (TextView)findViewById(R.id.lbRaRemark);
        lbRaActive = (TextView)findViewById(R.id.lbRaActive);
        lbRaSort1 = (TextView)findViewById(R.id.lbRaSort1);
        lbRaDefaultRes = (TextView)findViewById(R.id.lbRaDefaultRes);
        lbRaRH1 = (TextView)findViewById(R.id.lbRaRH1);
        lbRaRH2 = (TextView)findViewById(R.id.lbRaRH2);
        lbRaRF2 = (TextView)findViewById(R.id.lbRaRF2);
        lbRaRF1 = (TextView)findViewById(R.id.lbRaRF1);
        lbRaTaxID = (TextView)findViewById(R.id.lbRaTaxID);
        lbRaBillCode = (TextView)findViewById(R.id.lbRaBillCode);
        chkRaDefaultRes = (CheckBox)findViewById(R.id.chkRaDefaultRes);
//        lbRaSort1 = (TextView)findViewById(R.id.lbRaSort1);

        txtRaCode = (EditText)findViewById(R.id.txtRaCode);
        txtRaName = (EditText)findViewById(R.id.txtRaName);
        txtRaRemark = (EditText)findViewById(R.id.txtRaRemark);
        txtRaSort1 = (EditText)findViewById(R.id.txtRaSort1);
        txtRaRH1 = (EditText)findViewById(R.id.txtRaRH1);
        chkRaDefaultRes = (CheckBox)findViewById(R.id.chkRaDefaultRes);
        txtRaRH2 = (EditText)findViewById(R.id.txtRaRH2);
        txtRaRF1 = (EditText)findViewById(R.id.txtRaRF1);
        txtRaRF2 = (EditText)findViewById(R.id.txtRaRF2);
        txtRaTaxID = (EditText)findViewById(R.id.txtRaTaxID);
        txtRaBillCode = (EditText)findViewById(R.id.txtRaBillCode);
//        txtRaSort1 = (EditText)findViewById(R.id.txtRaSort1);

        btnRaSave = (Button)findViewById(R.id.btnRaSave);
        chkRaActive = (Switch) findViewById(R.id.chkRaActive);

        lbRaCode.setText(R.string.code);
        lbRaName.setText(R.string.name);
        lbRaRemark.setText(R.string.remark);
        lbRaActive.setText(R.string.active);
        lbRaSort1.setText(R.string.sort);
        lbRaDefaultRes.setText(R.string.RaDefaultRes);
        lbRaRH1.setText(R.string.RaRH1);
        lbRaRH2.setText(R.string.RaRH2);
        lbRaRF2.setText(R.string.RaRF1);
        lbRaRF1.setText(R.string.RaRF2);
        lbRaTaxID.setText(R.string.RaTaxID);
        lbRaBillCode.setText(R.string.RaBillCode);
        chkRaDefaultRes.setText("เป็นร้านคิดเงิน");

        txtRaBillCode.setFocusable(false);

        btnRaSave.setText(R.string.save);
        txtRaCode.setEnabled(false);
        chkRaActive.setText(R.string.activeon);
        chkRaActive.setChecked(true);
        chkRaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                chkRaActive.setText(R.string.activeon);
            }else{
                chkRaActive.setText(R.string.activeoff);
            }
            }
        });
        btnRaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRes();
                new insertRes().execute();
            }
        });
        new retrieveRes().execute();
        setTheme();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
    private void setTheme(){
        lbRaCode.setTextSize(textSize);
        lbRaName.setTextSize(textSize);
        lbRaRemark.setTextSize(textSize);
        lbRaSort1.setTextSize(textSize);
        lbRaActive.setTextSize(textSize);
        txtRaCode.setTextSize(textSize);
        txtRaName.setTextSize(textSize);
        txtRaRemark.setTextSize(textSize);
        txtRaSort1.setTextSize(textSize);
        lbRaDefaultRes.setTextSize(textSize);
        lbRaRH1.setTextSize(textSize);
        lbRaRH2.setTextSize(textSize);
        lbRaRF2.setTextSize(textSize);
        lbRaRF1.setTextSize(textSize);
        lbRaTaxID.setTextSize(textSize);
        lbRaBillCode.setTextSize(textSize);
        chkRaDefaultRes.setTextSize(textSize);
        txtRaRH1.setTextSize(textSize);
        txtRaRH2.setTextSize(textSize);
        txtRaRF1.setTextSize(textSize);
        txtRaRF2.setTextSize(textSize);
        txtRaTaxID.setTextSize(textSize);
        txtRaBillCode.setTextSize(textSize);
        chkRaActive.setTextSize(textSize);
    }
    private void setControl(){
        if(res!=null){
            txtRaCode.setText(res.Code);
            txtRaName.setText(res.Name);
            txtRaRemark.setText(res.Remark);
            txtRaSort1.setText(res.Sort1);
            txtRaRH1.setText(res.RH1);
            txtRaRH2.setText(res.RH2);
            txtRaRF1.setText(res.RF1);
            txtRaRF2.setText(res.RF2);
            txtRaBillCode.setText(res.BillCode);
            txtRaTaxID.setText(res.TaxID);
//            txtRaTaxID.setText(res.TaxID);
            if(res.DefaultRes.equals("1")){
                chkRaDefaultRes.setChecked(true);
                chkRaDefaultRes.setText("เป็นร้านคิดเงิน");
            }else{
                chkRaDefaultRes.setChecked(false);
                chkRaDefaultRes.setText("เป็นร้านคิดเงิน");
            }
            if(res.Active.equals("1")){
                chkRaActive.setChecked(true);
            }else{
                chkRaActive.setChecked(false);
            }
        }
    }
    private void getRes(){
        res = new Restaurant();
        res.ID = rs.resID;
        //res.Sort1=txtRaSort1.getText().toString();
        if(chkRaActive.isChecked()){
            res.Active="1";
        }else{
            res.Active="3";
        }
        res.Code=txtRaCode.getText().toString();
        res.Name=txtRaName.getText().toString().trim();
        res.Remark=txtRaRemark.getText().toString().trim();
        res.RH1 = txtRaRH1.getText().toString().trim();
        res.RH2 = txtRaRH2.getText().toString().trim();
        res.RF1 = txtRaRF1.getText().toString().trim();
        res.RF2 = txtRaRF2.getText().toString().trim();
        res.TaxID = txtRaTaxID.getText().toString().trim();
        res.BillCode = txtRaBillCode.getText().toString().trim();
        res.Sort1 = txtRaSort1.getText().toString().trim();
        if(chkRaDefaultRes.isChecked()){
            res.DefaultRes="1";
        }else{
            res.DefaultRes="0";
        }
    }
    class insertRes extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("ID", res.ID));
                params.add(new BasicNameValuePair("Code", res.Code));
                params.add(new BasicNameValuePair("Name", res.Name));
                params.add(new BasicNameValuePair("Remark", res.Remark));
                params.add(new BasicNameValuePair("Active", res.Active));
                params.add(new BasicNameValuePair("Sort1", res.Sort1));
                params.add(new BasicNameValuePair("receipt_header1", res.RH1));
                params.add(new BasicNameValuePair("receipt_header2", res.RH2));
                params.add(new BasicNameValuePair("receipt_footer1", res.RF1));
                params.add(new BasicNameValuePair("receipt_footer2", res.RF2));
                params.add(new BasicNameValuePair("tax_id", res.TaxID));
                params.add(new BasicNameValuePair("default_res", res.DefaultRes));
                if(res.ID.equals("")){
                    jarr = jsonparser.getJSONFromUrl(rs.hostResInsert,params);
                }else{
                    jarr = jsonparser.getJSONFromUrl(rs.hostResUpdate,params);
                }
                if(jarr!=null){
                    JSONObject catObj = (JSONObject) jarr.get(0);
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
    class retrieveRes extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("res_id", rs.resID));

                jarr = jsonparser.getJSONFromUrl(rs.hostResSelectByID,params);
                if((jarr!=null) && (!jarr.equals("[]"))){

                    JSONObject catObj = (JSONObject) jarr.get(0);
                    res = new Restaurant();
                    res.ID = catObj.getString("res_id");
                    res.Code = catObj.getString("res_code");
                    res.Name = rs.StringNull(catObj.getString("res_name")).trim();
                    res.Remark = rs.StringNull(catObj.getString("remark")).trim();
                    res.Sort1 = rs.StringNull(catObj.getString("sort1")).trim();
                    res.Active = catObj.getString("active").trim();
                    res.RH1 = rs.StringNull(catObj.getString("receipt_header1")).trim();
                    res.RH2 = rs.StringNull(catObj.getString("receipt_header2")).trim();
                    res.RF1 = rs.StringNull(catObj.getString("receipt_footer1")).trim();
                    res.RF2 = rs.StringNull(catObj.getString("receipt_footer2")).trim();
                    res.BillCode = rs.StringNull(catObj.getString("bill_code")).trim();
                    res.TaxID = rs.StringNull(catObj.getString("tax_id")).trim();
                    res.DefaultRes = rs.StringNull(catObj.getString("default_res")).trim();
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
            setControl();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
}
