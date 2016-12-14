package com.nakoyagarden.ekapop.restaurant;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
    Switch chkUaActive;

    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;

    User us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        lbUaLogin = (TextView)findViewById(R.id.lbUaLogin);
        lbUaName = (TextView)findViewById(R.id.lbUaName);
        lbUaPassword = (TextView)findViewById(R.id.lbUaPassword);
        lbUaRemark = (TextView)findViewById(R.id.lbUaRemark);
        lbUaPrivilege = (TextView)findViewById(R.id.lbUaPrivilege);
        lbUaActive = (TextView)findViewById(R.id.lbUaActive);
        chkUaActive = (Switch) findViewById(R.id.chkUaActive);

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
        if(us!=null){
            txtUaLogin.setText(us.Login);
            txtUaName.setText(us.Name);
            txtUaPassword.setText(us.Passoword1);
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
        }
    }
    class retrieveUser extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                params.add(new BasicNameValuePair("user_id", rs.UsID));

                jarr = jsonparser.getJSONFromUrl(rs.hostUserSelectByID,params);
                if((jarr!=null) && (!jarr.equals("[]"))){

                    JSONObject catObj = (JSONObject) jarr.get(0);
                    us = new User();
                    us.ID = catObj.getString("user_id");
                    us.Login = catObj.getString("user_login");
                    us.Name = rs.StringNull(catObj.getString("user_name")).trim();
                    us.Remark = rs.StringNull(catObj.getString("remark")).trim();
                    us.Sort1 = catObj.getString("sort1");
                    us.Active = rs.StringNull(catObj.getString("active")).trim();

                    us.Privilege = rs.StringNull(catObj.getString("privilege")).trim();
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
