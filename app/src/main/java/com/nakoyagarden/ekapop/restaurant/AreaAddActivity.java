package com.nakoyagarden.ekapop.restaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_add);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
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
            new insertArea().execute();
            }
        });
        chkAaActive.setText(R.string.activeon);
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
        new retrieveArea().execute();
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
        if(ar!=null){
            txtAaCode.setText(ar.Code);
            txtAaName.setText(ar.Name);
            txtAaRemark.setText(ar.Remark);
            txtAaSort1.setText(ar.Sort1);
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
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                params.add(new BasicNameValuePair("ID", ar.ID));
                params.add(new BasicNameValuePair("Code", ar.Code));
                params.add(new BasicNameValuePair("Name", ar.Name));
                params.add(new BasicNameValuePair("Remark", ar.Remark));
                params.add(new BasicNameValuePair("Active", ar.Active));
                params.add(new BasicNameValuePair("Sort1", ar.Sort1));
                if(ar.ID.equals("")){
                    jarr = jsonparser.getJSONFromUrl(rs.hostAreaInsert,params);
                }else{
                    jarr = jsonparser.getJSONFromUrl(rs.hostAreaUpdate,params);
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
    class retrieveArea extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                params.add(new BasicNameValuePair("area_id", rs.arID));

                jarr = jsonparser.getJSONFromUrl(rs.hostAreaSelectByID,params);
                if((jarr!=null) && (!jarr.equals("[]"))){
                    JSONObject catObj = (JSONObject) jarr.get(0);
                    ar = new Area();
                    ar.ID = catObj.getString("area_id");
                    ar.Code = catObj.getString("area_code");
                    ar.Name = rs.StringNull(catObj.getString("area_name"));
                    ar.Remark = rs.StringNull(catObj.getString("remark"));
                    ar.Sort1 = catObj.getString("sort1");
                    ar.Active = catObj.getString("active");
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
