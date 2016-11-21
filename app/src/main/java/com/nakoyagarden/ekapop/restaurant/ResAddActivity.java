package com.nakoyagarden.ekapop.restaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    TextView lbRaCode, lbRaName, lbRaRemark, lbRaSort1, lbRaActive;
    EditText txtRaCode, txtRaName, txtRaRemark, txtRaSort1;
    Switch chkRaActive;
    Button btnRaSave;

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
        txtRaCode = (EditText)findViewById(R.id.txtRaCode);
        txtRaName = (EditText)findViewById(R.id.txtRaName);
        txtRaRemark = (EditText)findViewById(R.id.txtRaRemark);
        txtRaSort1 = (EditText)findViewById(R.id.txtRaSort1);
        btnRaSave = (Button)findViewById(R.id.btnRaSave);

        lbRaCode.setText(R.string.code);
        lbRaName.setText(R.string.name);
        lbRaRemark.setText(R.string.remark);
        lbRaActive.setText(R.string.active);
        lbRaSort1.setText(R.string.sort);
        btnRaSave.setText(R.string.save);
        btnRaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRes();
                new insertRes().execute();
            }
        });
        new retrieveRes().execute();
        setTheme();
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
    }
    private void setControl(){
        if(res!=null){
            txtRaCode.setText(res.Code);
            txtRaName.setText(res.Name);
            txtRaRemark.setText(res.Remark);
            txtRaSort1.setText(res.Sort1);
        }
    }
    private void getRes(){
        res = new Restaurant();
        res.ID = rs.resID;
        //res.Sort1=txtRaSort1.getText().toString();
        res.Active="1";
        res.Code=txtRaCode.getText().toString();
        res.Name=txtRaName.getText().toString();
        res.Remark=txtRaRemark.getText().toString();
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
                //params.add(new BasicNameValuePair("Sort1", res.Sort1));
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
                    res.Name = catObj.getString("res_name");
                    res.Remark = catObj.getString("remark");
                    //res.Sort1 = catObj.getString("sort1");
                    res.Active = catObj.getString("active");
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
