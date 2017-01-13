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
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodsTypeAddActivity extends AppCompatActivity {
    TextView lbFtaCode, lbFtaName, lbFtaRemark, lbFtaSort1, lbFtaActive;
    EditText txtFtaCode, txtFtaName, txtFtaRemark, txtFtaSort1, txtFtaPasswordVoid;
    Switch chkFtaActive;
    Button btnFtaSave, btnFtaVoid;

    FoodsType ft = new FoodsType();

    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;
    DatabaseSQLi daS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_type_add);
        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        ft = new FoodsType();

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
        txtFtaPasswordVoid = (EditText)findViewById(R.id.txtFtaPasswordVoid);
        btnFtaSave = (Button)findViewById(R.id.btnFtaSave);
        btnFtaVoid = (Button)findViewById(R.id.btnFtaVoid);
        chkFtaActive = (Switch)findViewById(R.id.chkFtaActive);

        lbFtaCode.setText(R.string.code);
        lbFtaName.setText(R.string.name);
        lbFtaRemark.setText(R.string.remark);
        lbFtaActive.setText(R.string.active);
        lbFtaSort1.setText(R.string.sort);
        btnFtaSave.setText(R.string.save);
        btnFtaVoid.setText(R.string.void1);
        btnFtaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rs.AccessMode.equals("Standalone")) {
                    getFoodsType();
                    jarr = daS.FoodsTypeInsert(ft.ID,ft.Code,ft.Name,ft.Remark,ft.Sort1);
                    getFoodsTypeInsert();
                }else if(rs.AccessMode.equals("Internet")){
                    getFoodsType();
                    new insertFoodsType().execute();
                }else{
                    getFoodsType();
                    new insertFoodsType().execute();
                }

            }
        });
        btnFtaVoid.setVisibility(View.INVISIBLE);
        txtFtaPasswordVoid.setVisibility(View.INVISIBLE);
        chkFtaActive.setText(R.string.activeon);
        chkFtaActive.setChecked(true);
        txtFtaCode.setEnabled(false);
        chkFtaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkFtaActive.setText(R.string.activeon);
                    btnFtaVoid.setVisibility(View.INVISIBLE);
                }else{
                    chkFtaActive.setText(R.string.activeoff);
                    btnFtaVoid.setVisibility(View.VISIBLE);
                }
            }
        });
        if(rs.AccessMode.equals("Standalone")) {
            jarrF = daS.FoodsTypeSelectById(rs.ftID);
            setControl();
        }else if(rs.AccessMode.equals("Internet")){
            new retrieveFoodsType().execute();
        }else{
            new retrieveFoodsType().execute();
        }
        btnFtaVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnFtaVoid.getText().toString().equals(getResources().getString(R.string.void1confrim))){
                    if(txtFtaPasswordVoid.getText().toString().equals("")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsTypeAddActivity.this);
                        builder1.setMessage("Password ไม่ได้ป้อน");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtFtaPasswordVoid.setSelection(0,txtFtaPasswordVoid.getText().length());
                                txtFtaPasswordVoid.setFocusable(true);
                            }
                        }).create().show();
                    }else{
                        if(rs.chkPasswordVoid(txtFtaPasswordVoid.getText().toString())){
//                            String tableid = rs.getTable(cboBvTable.getSelectedItem().toString(),"genid");
                            if(rs.AccessMode.equals("Standalone")) {
                                jarr = daS.FoodsTypeVoid(rs.chkUserByPassword(txtFtaPasswordVoid.getText().toString()), ft.ID);
                                getFoodsTypeVoid();
                            }else if(rs.AccessMode.equals("Internet")){
                                new FoodsTypeVoid().execute(rs.chkUserByPassword(txtFtaPasswordVoid.getText().toString()), ft.ID);
                            }else{
                                new FoodsTypeVoid().execute(rs.chkUserByPassword(txtFtaPasswordVoid.getText().toString()), ft.ID);
                            }

                        }else{
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsTypeAddActivity.this);
                            builder1.setMessage("Password ไม่ถูกต้อง");
                            builder1.setCancelable(true);
                            builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txtFtaPasswordVoid.setSelection(0,txtFtaPasswordVoid.getText().length());
                                    txtFtaPasswordVoid.setFocusable(true);
                                }
                            }).create().show();
                        }
                    }


                    if(rs.AccessMode.equals("Standalone")) {

                    }else if(rs.AccessMode.equals("Internet")){

                    }else{

                    }
                }else{
                    if(!chkFtaActive.isChecked()){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsTypeAddActivity.this);
                        builder1.setMessage("ต้องการเยกเลิกรายการนี้.\n\nลำดับ "+" รหัส "+txtFtaCode.getText().toString()+" "+ txtFtaName.getText().toString()+"\n");
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
                                txtFtaPasswordVoid.setVisibility(View.VISIBLE);
                                btnFtaVoid.setText(R.string.void1confrim);
                                txtFtaPasswordVoid.setSelection(0,txtFtaPasswordVoid.getText().length());
                                txtFtaPasswordVoid.setFocusable(true);

                            }
                        }).create().show();

                    }
                }
            }
        });
//        new retrieveFoodsType().execute();
        if(rs.ftID.equals("")) chkFtaActive.setChecked(true);
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
        try {
            ft = new FoodsType();
            if((jarrF!=null) && (!jarrF.equals("[]")) & jarrF.length()>0){
                JSONObject catObj = (JSONObject) jarrF.get(0);
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
            Log.e("setControl ",e.getMessage());
        }
        if(ft!=null){
            txtFtaCode.setText(ft.Code);
            txtFtaName.setText(ft.Name);
            txtFtaRemark.setText(ft.Remark);
            txtFtaSort1.setText(ft.Sort1);
            if(ft.Active.equals("1")){
                chkFtaActive.setChecked(true);
            }else{
                chkFtaActive.setChecked(false);
            }
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
            getFoodsTypeInsert();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    private void getFoodsTypeInsert(){
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
            Log.e("getFoodsTypeInsert ",e.getMessage());
        }
    }
    private void getFoodsTypeVoid(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("sql",catObj.getString("sql"));
            if(catObj.getString("status").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsTypeAddActivity.this);
                builder1.setMessage("ยกเลิกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnFtaSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {
            Log.e("getFoodsTypeInsert ",e.getMessage());
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

            jarrF = jsonparser.getJSONFromUrl(rs.hostFoodsTypeSelectByID,params);
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
    class FoodsTypeVoid extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(ft.dbID, arg0[1]));
            params.add(new BasicNameValuePair(ft.dbVoidUser, arg0[0]));
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));

            jarr = jsonparser.getJSONFromUrl(rs.hostFoodsTypeVoid,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getFoodsTypeVoid();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
}
