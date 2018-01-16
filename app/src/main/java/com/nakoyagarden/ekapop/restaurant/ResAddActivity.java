package com.nakoyagarden.ekapop.restaurant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Log;
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

public class ResAddActivity extends Activity {
    TextView lbRaCode, lbRaName, lbRaRemark, lbRaSort1, lbRaActive, lbRaDefaultRes, lbRaRH1, lbRaRH2, lbRaRF1, lbRaRF2, lbRaTaxID, lbRaBillCode;
    EditText txtRaCode, txtRaName, txtRaRemark, txtRaSort1, txtRaRH1, txtRaRH2,  txtRaRF1, txtRaRF2,  txtRaTaxID, txtRaBillCode,txtRaPasswordVoid;
    Switch chkRaActive;
    Button btnRaSave,btnRaVoid;
    CheckBox chkRaDefaultRes;

    Restaurant res = new Restaurant();

    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrR;
    String ab;
    int textSize=20,textSize1=18, row;
    DatabaseSQLi daS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_add);
        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        res = new Restaurant();

        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);
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
//        lbRaSort1 = (TextView)findViewById(R.genid.lbRaSort1);

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
        txtRaPasswordVoid = (EditText)findViewById(R.id.txtRaPasswordVoid);

        btnRaSave = (Button)findViewById(R.id.btnRaSave);
        btnRaVoid = (Button)findViewById(R.id.btnRaVoid);
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
        btnRaVoid.setText(R.string.void1);
        btnRaVoid.setVisibility(View.INVISIBLE);
        txtRaPasswordVoid.setVisibility(View.INVISIBLE);
        txtRaBillCode.setFocusable(false);

        btnRaSave.setText(R.string.save);
        txtRaCode.setEnabled(false);
        chkRaActive.setText(R.string.activeon);
        chkRaActive.setChecked(true);
        txtRaCode.setEnabled(false);
        chkRaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                chkRaActive.setText(R.string.activeon);
                btnRaVoid.setVisibility(View.INVISIBLE);
            }else{
                chkRaActive.setText(R.string.activeoff);
                btnRaVoid.setVisibility(View.VISIBLE);
            }
            }
        });
        btnRaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rs.AccessMode.equals("Standalone")) {
                    getRes();
                    jarrR = daS.ResInsert(res.ID,res.Code,res.Name,res.Remark,res.Sort1);
                    getResInsert();
                }else if(rs.AccessMode.equals("Internet")){
                    getRes();
                    new insertRes().execute();
                }else{
                    getRes();
                    new insertRes().execute();
                }
            }
        });
        if(rs.AccessMode.equals("Standalone")) {
            jarr = daS.ResSelectById(rs.resID);
            setControl();
        }else if(rs.AccessMode.equals("Internet")){
            new retrieveRes().execute();
        }else{
            new retrieveRes().execute();
        }
//        new retrieveRes().execute();
        btnRaVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnRaVoid.getText().toString().equals(getResources().getString(R.string.void1confrim))){
                    if(txtRaPasswordVoid.getText().toString().equals("")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ResAddActivity.this);
                        builder1.setMessage("Password ไม่ได้ป้อน");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtRaPasswordVoid.setSelection(0,txtRaPasswordVoid.getText().length());
                                txtRaPasswordVoid.setFocusable(true);
                            }
                        }).create().show();
                    }else{
                        if(rs.chkPasswordVoid(txtRaPasswordVoid.getText().toString())){
//                            String tableid = rs.getTable(cboBvTable.getSelectedItem().toString(),"genid");
                            if(rs.AccessMode.equals("Standalone")) {
                                jarr = daS.ResVoid(rs.chkUserByPassword(txtRaPasswordVoid.getText().toString()), res.ID);
                                getResVoid();
                            }else if(rs.AccessMode.equals("Internet")){
                                new ResVoid().execute(rs.chkUserByPassword(txtRaPasswordVoid.getText().toString()), res.ID);
                            }else{
                                new ResVoid().execute(rs.chkUserByPassword(txtRaPasswordVoid.getText().toString()), res.ID);
                            }

                        }else{
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(ResAddActivity.this);
                            builder1.setMessage("Password ไม่ถูกต้อง");
                            builder1.setCancelable(true);
                            builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txtRaPasswordVoid.setSelection(0,txtRaPasswordVoid.getText().length());
                                    txtRaPasswordVoid.setFocusable(true);
                                }
                            }).create().show();
                        }
                    }


                    if(rs.AccessMode.equals("Standalone")) {

                    }else if(rs.AccessMode.equals("Internet")){

                    }else{

                    }
                }else{
                    if(!chkRaActive.isChecked()){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(ResAddActivity.this);
                        builder1.setMessage("ต้องการเยกเลิกรายการนี้.\n\nลำดับ "+" รหัส "+txtRaCode.getText().toString()+" "+ txtRaName.getText().toString()+"\n");
                        builder1.setCancelable(true);
                        builder1.setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                                flagDel=false;
//                                Toast.makeText(MailarapOrderAdd.this,"You clicked no button",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });
                        builder1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                                txtRaPasswordVoid.setVisibility(View.VISIBLE);
                                btnRaVoid.setText(R.string.void1confrim);
                                txtRaPasswordVoid.setSelection(0,txtRaPasswordVoid.getText().length());
                                txtRaPasswordVoid.setFocusable(true);

                            }
                        }).create().show();

                    }
                }
            }
        });
//        new retrieveFoodsType().execute();
        if(rs.resID.equals("")) chkRaActive.setChecked(true);
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
        try {
            res = new Restaurant();
            if((jarr!=null) && (!jarr.equals("[]")) && jarr.length()>0){
                JSONObject catObj = (JSONObject) jarr.get(0);
                res.ID = catObj.getString(res.dbID);
                res.Code = catObj.getString(res.dbCode);
                res.Name = rs.StringNull(catObj.getString(res.dbName)).trim();
                res.Remark = rs.StringNull(catObj.getString(res.dbRemark)).trim();
                res.Sort1 = rs.StringNull(catObj.getString(res.dbSort1)).trim();
                res.Active = catObj.getString(res.dbActive).trim();
                res.RH1 = rs.StringNull(catObj.getString(res.dbRH1)).trim();
                res.RH2 = rs.StringNull(catObj.getString(res.dbRH2)).trim();
                res.RF1 = rs.StringNull(catObj.getString(res.dbRF1)).trim();
                res.RF2 = rs.StringNull(catObj.getString(res.dbRF2)).trim();
                res.BillCode = rs.StringNull(catObj.getString(res.dbBillCode)).trim();
                res.TaxID = rs.StringNull(catObj.getString(res.dbTaxID)).trim();
                res.DefaultRes = rs.StringNull(catObj.getString(res.dbDefaultRes)).trim();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(res.dbID, res.ID));
            params.add(new BasicNameValuePair(res.dbCode, res.Code));
            params.add(new BasicNameValuePair(res.dbName, res.Name));
            params.add(new BasicNameValuePair(res.dbRemark, res.Remark));
            params.add(new BasicNameValuePair(res.dbActive, res.Active));
            params.add(new BasicNameValuePair(res.dbSort1, res.Sort1));
            params.add(new BasicNameValuePair(res.dbRH1, res.RH1));
            params.add(new BasicNameValuePair(res.dbRH2, res.RH2));
            params.add(new BasicNameValuePair(res.dbRF1, res.RF1));
            params.add(new BasicNameValuePair(res.dbRF2, res.RF2));
            params.add(new BasicNameValuePair(res.dbTaxID, res.TaxID));
            params.add(new BasicNameValuePair(res.dbDefaultRes, res.DefaultRes));
            if(res.ID.equals("")){
                jarrR = jsonparser.getJSONFromUrl(rs.hostResInsert,params);
            }else{
                jarrR = jsonparser.getJSONFromUrl(rs.hostResUpdate,params);
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
            getResInsert();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    private void getResInsert(){
        try {
            if((jarrR !=null) && (!jarrR.equals("[]")) & jarrR.length()>0){
                JSONObject catObj = (JSONObject) jarrR.get(0);
                Log.d("sql",catObj.getString("sql"));
                if(catObj.getString("success").equals("1")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ResAddActivity.this);
                    builder1.setMessage("บันทึกข้อมูล  เรียบร้อย");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btnRaSave.setEnabled(false);
                        }
                    }).create().show();
                }
            }else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ResAddActivity.this);
                builder1.setMessage("บันทึกข้อมูล  ไม่เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnRaSave.setEnabled(false);
                    }
                }).create().show();
            }

        } catch (JSONException e) {
            Log.e("getResInsert ",e.getMessage());
        }
    }
    private void getResVoid(){
        try {
            if((jarr!=null) && (!jarr.equals("[]")) & jarr.length()>0){
                JSONObject catObj = (JSONObject) jarr.get(0);
                Log.d("sql",catObj.getString("sql"));
                if(catObj.getString("status").equals("1")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ResAddActivity.this);
                    builder1.setMessage("ยกเลิกข้อมูล  เรียบร้อย");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btnRaSave.setEnabled(false);
                        }
                    }).create().show();
                }
            }else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ResAddActivity.this);
                builder1.setMessage("ยกเลิกข้อมูล  ไม่เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnRaSave.setEnabled(false);
                    }
                }).create().show();
            }

        } catch (JSONException e) {
            Log.e("getResVoid ",e.getMessage());
        }
    }
    class retrieveRes extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(res.dbID, rs.resID));

            jarr = jsonparser.getJSONFromUrl(rs.hostResSelectByID,params);
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
    class ResVoid extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(res.dbID,arg0[1]));
            params.add(new BasicNameValuePair(res.dbVoidUser, arg0[0]));

            jarr = jsonparser.getJSONFromUrl(rs.hostResVoid,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getResVoid();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
}
