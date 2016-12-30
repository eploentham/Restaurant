package com.nakoyagarden.ekapop.restaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserAddActivity extends AppCompatActivity {
    TextView lbUaLogin, lbUaName, lbUaPassword, lbUaRemark, lbUaPrivilege, lbUaActive;
    EditText txtUaLogin, txtUaName, txtUaPassword, txtUaRemark;
    Spinner cboUaPrivilege;
    Switch chkUaActive,chkUaPermissionVoidBill,chkUaPermissionVoidCloseday;
    Button btnUaSave;

    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;

    User us = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);
        lbUaLogin = (TextView)findViewById(R.id.lbUaLogin);
        lbUaName = (TextView)findViewById(R.id.lbUaName);
        lbUaPassword = (TextView)findViewById(R.id.lbUaPassword);
        lbUaRemark = (TextView)findViewById(R.id.lbUaRemark);
        lbUaPrivilege = (TextView)findViewById(R.id.lbUaPrivilege);
        lbUaActive = (TextView)findViewById(R.id.lbUaActive);
        chkUaActive = (Switch) findViewById(R.id.chkUaActive);
        chkUaPermissionVoidBill = (Switch) findViewById(R.id.chkUaPermissionVoidBill);
        chkUaPermissionVoidCloseday = (Switch) findViewById(R.id.chkUaPermissionVoidCloseday);
        btnUaSave = (Button) findViewById(R.id.btnUaSave);

        txtUaLogin = (EditText) findViewById(R.id.txtUaLogin);
        txtUaName = (EditText)findViewById(R.id.txtUaName);
        txtUaPassword = (EditText)findViewById(R.id.txtUaPassword);
        txtUaRemark = (EditText)findViewById(R.id.txtUaRemark);

        cboUaPrivilege = (Spinner)findViewById(R.id.cboUaPrivilege);

        lbUaLogin.setText(R.string.login);
        lbUaName.setText(R.string.user);
        lbUaPassword.setText(R.string.password);
        lbUaRemark.setText(R.string.remark);
        lbUaPrivilege.setText(R.string.privilege);
        lbUaActive.setText(R.string.active);
        chkUaActive.setText(R.string.activeon);
        btnUaSave.setText(R.string.save);

        chkUaPermissionVoidBill.setText(R.string.chkUaPermissionVoidBillOff);
        chkUaPermissionVoidCloseday.setText(R.string.chkUaPermissionVoidClosedayOff);
        chkUaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkUaActive.setText(R.string.activeon);
                }else{
                    chkUaActive.setText(R.string.activeoff);
                }
            }
        });
        chkUaPermissionVoidBill.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkUaPermissionVoidBill.setText(R.string.chkUaPermissionVoidBillOn);
                }else{
                    chkUaPermissionVoidBill.setText(R.string.chkUaPermissionVoidBillOff);
                }
            }
        });
        chkUaPermissionVoidCloseday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkUaPermissionVoidCloseday.setText(R.string.chkUaPermissionVoidClosedayOn);
                }else{
                    chkUaPermissionVoidCloseday.setText(R.string.chkUaPermissionVoidClosedayOff);
                }
            }
        });
        btnUaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUser();
                new insertUser().execute();
            }
        });
        ArrayAdapter<String> adaUser = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboPrivilege);
        cboUaPrivilege.setAdapter(adaUser);
        new retrieveUser().execute();
        setTheme();
    }
    private void setTheme(){
        lbUaLogin.setTextSize(textSize);
        lbUaName.setTextSize(textSize);
        lbUaPassword.setTextSize(textSize);
        lbUaRemark.setTextSize(textSize);
        lbUaPrivilege.setTextSize(textSize);
        txtUaLogin.setTextSize(textSize);
        txtUaName.setTextSize(textSize);
        txtUaPassword.setTextSize(textSize);
        txtUaRemark.setTextSize(textSize);
        lbUaActive.setTextSize(textSize);
    }
    private void setControl(){
        Log.d("setControl ","OK");
        if((jarr!=null) && (!jarr.equals("[]"))){
            try {
                JSONObject catObj = (JSONObject) jarr.get(0);
                Log.d("retrieveUser", catObj.getString(us.dbID));
                us = new User();
                us.ID = catObj.getString(us.dbID);
                us.Login = catObj.getString(us.dbLogin);
                us.Name = rs.StringNull(catObj.getString(us.dbName)).trim();
                us.Remark = rs.StringNull(catObj.getString(us.dbRemark)).trim();
                us.Sort1 = catObj.getString(us.dbSort1);
                us.Active = rs.StringNull(catObj.getString(us.dbActive)).trim();
                us.VoidBill =rs.StringNull(catObj.getString(us.dbVoidBill)).trim();
                us.VoidCloseday =rs.StringNull(catObj.getString(us.dbVoidCloseday)).trim();
                us.Privilege = rs.StringNull(catObj.getString(us.dbPrivilege)).trim();
                us.Password1 = rs.StringNull(catObj.getString(us.dbPassword1)).trim();
            }catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(us!=null){
            txtUaLogin.setText(us.Login);
            txtUaName.setText(us.Name);
            txtUaPassword.setText(us.Password1);
            txtUaRemark.setText(us.Remark);
            for(int i=0;i<cboUaPrivilege.getCount();i++){
                if(cboUaPrivilege.getItemAtPosition(i).equals("1")){
                    cboUaPrivilege.setSelection(i);
                }
            }
            if(us.Privilege.equals("1")){//All
                cboUaPrivilege.setSelection(0);
            }else if(us.Privilege.equals("2")){//Order
                cboUaPrivilege.setSelection(1);
            }else if(us.Privilege.equals("3")){//Bill
                cboUaPrivilege.setSelection(2);
            }else if(us.Privilege.equals("4")){//Closeday
                cboUaPrivilege.setSelection(3);
            }
            if(us.Active.equals("1")){
                chkUaActive.setChecked(true);
            }else{
                chkUaActive.setChecked(false);
            }
            if(us.VoidBill.equals("1")){
                chkUaPermissionVoidBill.setChecked(true);
            }else{
                chkUaPermissionVoidBill.setChecked(false);
            }
            if(us.VoidCloseday.equals("1")){
                chkUaPermissionVoidCloseday.setChecked(true);
            }else{
                chkUaPermissionVoidCloseday.setChecked(false);
            }
        }
    }
    private void getUser(){
        us.Login = txtUaLogin.getText().toString().trim();
        us.Name = txtUaName.getText().toString().trim();
        us.Remark = txtUaRemark.getText().toString().trim();
//        us.Sort1 = catObj.getString("sort1");
        us.Active = chkUaActive.isChecked()?"1":"3";
        us.VoidBill =chkUaPermissionVoidBill.isChecked()?"1":"0";
        us.VoidCloseday =chkUaPermissionVoidCloseday.isChecked()?"1":"0";
        if(cboUaPrivilege.getSelectedItemPosition()==0){
            us.Privilege = "1";
        }else if(cboUaPrivilege.getSelectedItemPosition()==1){
            us.Privilege = "2";
        }else if(cboUaPrivilege.getSelectedItemPosition()==2){
            us.Privilege = "3";
        }else if(cboUaPrivilege.getSelectedItemPosition()==3){
            us.Privilege = "4";
        }
//        us.Privilege = rs.StringNull(catObj.getString("privilege")).trim();
        us.Password1 = txtUaPassword.getText().toString().trim();
    }
    class retrieveUser extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                params.add(new BasicNameValuePair(us.dbID, rs.UsID));

                jarr = jsonparser.getJSONFromUrl(rs.hostUserSelectByID,params);

//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
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
    class insertUser extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(us.dbID, us.ID));
            params.add(new BasicNameValuePair(us.dbLogin, us.Login));
            params.add(new BasicNameValuePair(us.dbName, us.Name));
            params.add(new BasicNameValuePair(us.dbSort1, ""));
            params.add(new BasicNameValuePair(us.dbRemark, us.Remark));
            params.add(new BasicNameValuePair(us.dbPassword1, us.Password1));
            params.add(new BasicNameValuePair(us.dbPrivilege, us.Privilege));
            params.add(new BasicNameValuePair(us.dbVoidBill, us.VoidBill));
            params.add(new BasicNameValuePair(us.dbVoidCloseday, us.VoidCloseday));

            if(us.ID.equals("")){
                jarr = jsonparser.getJSONFromUrl(rs.hostUserInsert,params);
            }else{
                jarr = jsonparser.getJSONFromUrl(rs.hostUserUpdate,params);
            }
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
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(UserAddActivity.this);
                    builder1.setMessage("บันทึกข้อมูล  เรียบร้อย");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btnUaSave.setEnabled(false);
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
}
