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

public class FoodsTypeAddActivity extends AppCompatActivity {
    TextView lbFtaCode, lbFtaName, lbFtaRemark, lbFtaSort1, lbFtaActive;
    EditText txtFtaCode, txtFtaName, txtFtaRemark, txtFtaSort1;
    Switch chkFtaActive;
    Button btnFtaSave;

    FoodsType ft = new FoodsType();

    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_type_add);
        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);

        lbFtaCode = (TextView)findViewById(R.id.lbFtaCode);
        lbFtaName = (TextView)findViewById(R.id.lbFtaName);
        lbFtaRemark = (TextView)findViewById(R.id.lbFtaRemark);
        lbFtaActive = (TextView)findViewById(R.id.lbFtaActive);
        lbFtaSort1 = (TextView)findViewById(R.id.lbFtaSort1);
        txtFtaCode = (EditText)findViewById(R.id.txtFtaCode);
        txtFtaName = (EditText)findViewById(R.id.txtFtaName);
        txtFtaRemark = (EditText)findViewById(R.id.txtFtaRemark);
        txtFtaSort1 = (EditText)findViewById(R.id.txtFtaSort1);
        btnFtaSave = (Button)findViewById(R.id.btnFtaSave);
        chkFtaActive = (Switch)findViewById(R.id.chkFtaActive);

        lbFtaCode.setText(R.string.code);
        lbFtaName.setText(R.string.name);
        lbFtaRemark.setText(R.string.remark);
        lbFtaActive.setText(R.string.active);
        lbFtaSort1.setText(R.string.sort);
        btnFtaSave.setText(R.string.save);
        btnFtaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFoodsType();
                new insertFoodsType().execute();
            }
        });
        chkFtaActive.setText(R.string.activeon);
        chkFtaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkFtaActive.setText(R.string.activeon);
                }else{
                    chkFtaActive.setText(R.string.activeoff);
                }
            }
        });
        new retrieveFoodsType().execute();
        setTheme();
    }
    private void setTheme(){
        lbFtaCode.setTextSize(textSize);
        lbFtaName.setTextSize(textSize);
        lbFtaRemark.setTextSize(textSize);
        lbFtaSort1.setTextSize(textSize);
        lbFtaActive.setTextSize(textSize);
        txtFtaCode.setTextSize(textSize);
        txtFtaName.setTextSize(textSize);
        txtFtaRemark.setTextSize(textSize);
        txtFtaSort1.setTextSize(textSize);
    }
    private void setControl(){
        if(ft!=null){
            txtFtaCode.setText(ft.Code);
            txtFtaName.setText(ft.Name);
            txtFtaRemark.setText(ft.Remark);
            txtFtaSort1.setText(ft.Sort1);
        }
    }
    private void getFoodsType(){
        ft = new FoodsType();
        ft.ID = rs.ftID;
        //res.Sort1=txtRaSort1.getText().toString();
        ft.Active="1";
        ft.Code=txtFtaCode.getText().toString();
        ft.Name=txtFtaName.getText().toString();
        ft.Remark=txtFtaRemark.getText().toString();
    }
    class insertFoodsType extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(ft.dbID, ft.ID));
            params.add(new BasicNameValuePair(ft.dbCode, ft.Code));
            params.add(new BasicNameValuePair(ft.dbName, ft.Name));
            params.add(new BasicNameValuePair(ft.dbRemark, ft.Remark));
            params.add(new BasicNameValuePair(ft.dbActive, ft.Active));
            params.add(new BasicNameValuePair(ft.dbSort1, ft.Sort1));
            if(ft.ID.equals("")){
                jarr = jsonparser.getJSONFromUrl(rs.hostFoodsTypeInsert,params);
            }else{
                jarr = jsonparser.getJSONFromUrl(rs.hostFoodsTypeUpdate,params);
            }
//            if(jarr!=null){
//                JSONObject catObj = (JSONObject) jarr.get(0);
//            }
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
                JSONObject catObj = (JSONObject) jarr.get(0);
                Log.d("sql",catObj.getString("sql"));
                if(catObj.getString("success").equals("1")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsTypeAddActivity.this);
                    builder1.setMessage("บันทึกข้อมูล  เรียบร้อย");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btnFtaSave.setEnabled(false);
                        }
                    }).create().show();
                }
            } catch (JSONException e) {

            }
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    class retrieveFoodsType extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(ft.dbID, rs.ftID));
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));

            jarr = jsonparser.getJSONFromUrl(rs.hostFoodsTypeSelectByID,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            try {
                ft = new FoodsType();
                if((jarr!=null) && (!jarr.equals("[]")) & jarr.length()>0){
                    JSONObject catObj = (JSONObject) jarr.get(0);
                    ft.ID = catObj.getString(ft.dbID);
                    ft.Code = catObj.getString(ft.dbCode);
                    ft.Name = rs.StringNull(catObj.getString(ft.dbName));
                    ft.Remark = rs.StringNull(catObj.getString(ft.dbRemark));
                    ft.Sort1 = catObj.getString(ft.dbSort1);
                    ft.Active = catObj.getString(ft.dbActive);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            setControl();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
}
