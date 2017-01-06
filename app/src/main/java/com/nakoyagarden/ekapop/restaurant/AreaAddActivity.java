package com.nakoyagarden.ekapop.restaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class AreaAddActivity extends AppCompatActivity {
    TextView lbAaCode, lbAaName, lbAaRemark, lbAaSort1, lbAaActive;
    EditText txtAaCode, txtAaName, txtAaRemark, txtAaSort1;
    Switch chkAaActive;
    Button btnAaSave;

    Area ar;

    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
//    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;
    DatabaseSQLi daS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_add);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");

        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);
        lbAaCode = (TextView)findViewById(R.id.lbAaCode);
        lbAaName = (TextView)findViewById(R.id.lbAaName);
        lbAaRemark = (TextView)findViewById(R.id.lbAaRemark);
        lbAaSort1 = (TextView)findViewById(R.id.lbAaSort1);
        lbAaActive = (TextView)findViewById(R.id.lbAaActive);
        txtAaCode = (EditText) findViewById(R.id.txtAaCode);
        txtAaName = (EditText) findViewById(R.id.txtAaName);
        txtAaRemark = (EditText) findViewById(R.id.txtAaRemark);
        txtAaSort1 = (EditText) findViewById(R.id.txtAaSort1);
        chkAaActive = (Switch) findViewById(R.id.chkAaActive);
        btnAaSave = (Button) findViewById(R.id.btnAaSave);

        lbAaCode.setText(R.string.code);
        lbAaName.setText(R.string.name);
        lbAaRemark.setText(R.string.remark);
        lbAaSort1.setText(R.string.sort);
        lbAaActive.setText(R.string.active);

        btnAaSave.setText(R.string.save);
        btnAaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getArea();
                if(rs.AccessMode.equals("Standalone")) {
                    getArea();
                    jarr = daS.AreaInsert(ar.ID, ar.Code, ar.Name,ar.Remark, ar.Sort1);
                    getAreaInsert();
                }else if(rs.AccessMode.equals("Internet")){
                    new insertArea().execute();
                }else{
                    new insertArea().execute();
                }

            }
        });
        chkAaActive.setText(R.string.activeon);
        chkAaActive.setChecked(true);
        txtAaCode.setEnabled(false);
        chkAaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                chkAaActive.setText(R.string.activeon);
            }else{
                chkAaActive.setText(R.string.activeoff);
            }
            }
        });
        if(rs.AccessMode.equals("Standalone")) {
            jarr = daS.AreaSelectById(rs.arID);
            setControl();
        }else if(rs.AccessMode.equals("Internet")){
            new retrieveArea().execute();
        }else{
            new retrieveArea().execute();
        }

        setTheme();
    }
    private void setTheme(){
        lbAaCode.setTextSize(textSize);
        lbAaName.setTextSize(textSize);
        lbAaRemark.setTextSize(textSize);
        lbAaSort1.setTextSize(textSize);
        lbAaActive.setTextSize(textSize);
        txtAaCode.setTextSize(textSize);
        txtAaName.setTextSize(textSize);
        txtAaRemark.setTextSize(textSize);
        txtAaSort1.setTextSize(textSize);
    }
    private void setControl(){
        try {
            ar = new Area();
            if((jarr!=null) && (!jarr.equals("[]"))){
                JSONObject catObj = (JSONObject) jarr.get(0);
                ar.ID = catObj.getString(ar.dbID);
                ar.Code = catObj.getString(ar.dbCode);
                ar.Name = rs.StringNull(catObj.getString(ar.dbName));
                ar.Remark = rs.StringNull(catObj.getString(ar.dbRemark));
                ar.Sort1 = catObj.getString("sort1");
                ar.Active = catObj.getString("active");

                txtAaCode.setText(ar.Code);
                txtAaName.setText(ar.Name);
                txtAaRemark.setText(ar.Remark);
                txtAaSort1.setText(ar.Sort1);
                if(ar.Active.equals("1")){
                    chkAaActive.setChecked(true);
                }else{
                    chkAaActive.setChecked(false);
                }
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void getArea(){
        ar = new Area();
        ar.ID = rs.arID;
        ar.Sort1=txtAaSort1.getText().toString();
        ar.Active="1";
        ar.Code=txtAaCode.getText().toString();
        ar.Name=txtAaName.getText().toString();
        ar.Remark=txtAaRemark.getText().toString();

    }
    class insertArea extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(ar.dbID, ar.ID));
            params.add(new BasicNameValuePair(ar.dbCode, ar.Code));
            params.add(new BasicNameValuePair(ar.dbName, ar.Name));
            params.add(new BasicNameValuePair(ar.dbRemark, ar.Remark));
            params.add(new BasicNameValuePair(ar.dbActive, ar.Active));
            params.add(new BasicNameValuePair(ar.dbSort1, ar.Sort1));
            if(ar.ID.equals("")){
                jarr = jsonparser.getJSONFromUrl(rs.hostAreaInsert,params);
            }else{
                jarr = jsonparser.getJSONFromUrl(rs.hostAreaUpdate,params);
            }

//                if(jarr!=null){
//                    JSONObject catObj = (JSONObject) jarr.get(0);
//                }
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getAreaInsert();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    private void getAreaInsert(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("sql",catObj.getString("sql"));
            if(catObj.getString("success").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AreaAddActivity.this);
                builder1.setMessage("บันทึกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnAaSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {
            Log.e("sql",e.getMessage());
        }
    }
    class retrieveArea extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(ar.dbID, rs.arID));

            jarr = jsonparser.getJSONFromUrl(rs.hostAreaSelectByID,params);
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
