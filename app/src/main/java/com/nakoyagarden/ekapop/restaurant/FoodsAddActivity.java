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

public class FoodsAddActivity extends AppCompatActivity {

    TextView lbFaFoodsCode, lbFaFoodsName, lbFaFoodsPrice, lbFaFoodsRemark, lbFaResCode, lbPrinterName, lbFooActive,lbFaFoodsType;

    EditText txtFaFoodsCode, txtFaFoodsName, txtFaFoodsPrice, txtFaFoodsRemark, txtFooPasswordVoid;
    Spinner cboFaRes, cboFaFoodsType,cboFaPrinter;
    Switch chkFoodsActive;
    Button btnFoodsSave, btnFooVoid;

    private RestaurantControl rs;
    private Foods foo;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;
    DatabaseSQLi daS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_add);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);
        foo = new Foods();

        lbFaFoodsCode = (TextView)findViewById(R.id.lbFaFoodsCode);
        lbFaFoodsName = (TextView)findViewById(R.id.lbFaFoodsName);
        lbFaFoodsPrice = (TextView)findViewById(R.id.lbFaFoodsPrice);
        lbFaFoodsRemark = (TextView)findViewById(R.id.lbFaFoodsRemark);
        lbFaResCode = (TextView)findViewById(R.id.lbFaResCode);
        lbPrinterName = (TextView)findViewById(R.id.lbFaPrinter);
        lbFaFoodsType = (TextView)findViewById(R.id.lbFaFoodsType);
        lbFooActive = (TextView)findViewById(R.id.lbFooActive);
        txtFaFoodsCode = (EditText) findViewById(R.id.txtFaFoodsCode);
        txtFaFoodsName = (EditText)findViewById(R.id.txtFaFoodsName);
        txtFaFoodsPrice = (EditText)findViewById(R.id.txtFaFoodsPrice);
        txtFaFoodsRemark = (EditText)findViewById(R.id.txtFaFoodsRemark);
        txtFooPasswordVoid = (EditText)findViewById(R.id.txtFooPasswordVoid);
        cboFaPrinter = (Spinner) findViewById(R.id.cboFaPrinter);
        cboFaRes = (Spinner)findViewById(R.id.cboFaFoodsRes);
        cboFaFoodsType = (Spinner) findViewById(R.id.cboFaFoodsType);
        chkFoodsActive = (Switch) findViewById(R.id.chkFoodsActive);
        btnFoodsSave = (Button) findViewById(R.id.btnFoodsSave);
        btnFooVoid = (Button) findViewById(R.id.btnFooVoid);

        lbFaFoodsCode.setText(R.string.code);
        lbFaFoodsName.setText(R.string.name);
        lbFaFoodsPrice.setText(R.string.price);
        lbFaFoodsRemark.setText(R.string.remark);
        lbFaResCode.setText(R.string.restaurant);
        lbPrinterName.setText(R.string.printername);
        lbFaFoodsType.setText(R.string.foodstype);
        lbFooActive.setText(R.string.active);
        //chkFoodsActive.setText(R.string.activeon);
        chkFoodsActive.setChecked(false);
        chkFoodsActive.setChecked(true);
        txtFaFoodsCode.setEnabled(false);
        txtFaFoodsName.setSelected(true);
        chkFoodsActive.setText(R.string.activeon);
        btnFooVoid.setText(R.string.void1);
        btnFooVoid.setVisibility(View.INVISIBLE);
        txtFooPasswordVoid.setVisibility(View.INVISIBLE);

        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboRes);
        cboFaRes.setAdapter(adaArea);
        ArrayAdapter<String> adaPrinter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboPrinter);
        cboFaPrinter.setAdapter(adaPrinter);
        ArrayAdapter<String> adaFoodsType = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboFoodsType);
        cboFaFoodsType.setAdapter(adaFoodsType);

        btnFoodsSave.setText(R.string.save);
        btnFoodsSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btnFoodsSave.setBackgroundColor(getResources().getColor(R.color.Red));
                btnFoodsSave.setEnabled(false);
                getFoods();
                if(rs.AccessMode.equals("Standalone")) {
                    getFoods();
                    jarrF = daS.FoodsInsert(foo.ID, foo.Code, foo.Name,foo.Price,foo.TypeId, foo.Remark,foo.ResId,foo.ResCode,foo.StatusFoods,foo.PrinterName,foo.Sort1,"","");
                    getFoodsInsert();
                }else if(rs.AccessMode.equals("Internet")){
                    new insertFoods().execute();
                }else{
                    new insertFoods().execute();
                }
            }
        });

        chkFoodsActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkFoodsActive.setText(R.string.activeon);
                    btnFooVoid.setVisibility(View.INVISIBLE);
                }else{
                    chkFoodsActive.setText(R.string.activeoff);
                    btnFooVoid.setVisibility(View.VISIBLE);
                }
            }
        });
        if(rs.AccessMode.equals("Standalone")) {
            jarrF = daS.FoodsSelectById(rs.fooID);
            setControl();
        }else if(rs.AccessMode.equals("Internet")){
            new retrieveFoods().execute();
        }else{
            new retrieveFoods().execute();
        }
        btnFooVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnFooVoid.getText().toString().equals(getResources().getString(R.string.void1confrim))){
                    if(txtFooPasswordVoid.getText().toString().equals("")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsAddActivity.this);
                        builder1.setMessage("Password ไม่ได้ป้อน");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtFooPasswordVoid.setSelection(0,txtFooPasswordVoid.getText().length());
                                txtFooPasswordVoid.setFocusable(true);
                            }
                        }).create().show();
                    }else{
                        if(rs.chkPasswordVoid(txtFooPasswordVoid.getText().toString())){
//                            String tableid = rs.getTable(cboBvTable.getSelectedItem().toString(),"genid");
                            if(rs.AccessMode.equals("Standalone")) {
                                jarr = daS.FoodsVoid(rs.chkUserByPassword(txtFooPasswordVoid.getText().toString()), foo.ID);
                                getFoodsVoid();
                            }else if(rs.AccessMode.equals("Internet")){
                                new FoodsVoid().execute(rs.chkUserByPassword(txtFooPasswordVoid.getText().toString()), foo.ID);
                            }else{
                                new FoodsVoid().execute(rs.chkUserByPassword(txtFooPasswordVoid.getText().toString()), foo.ID);
                            }

                        }else{
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsAddActivity.this);
                            builder1.setMessage("Password ไม่ถูกต้อง");
                            builder1.setCancelable(true);
                            builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txtFooPasswordVoid.setSelection(0,txtFooPasswordVoid.getText().length());
                                    txtFooPasswordVoid.setFocusable(true);
                                }
                            }).create().show();
                        }
                    }


                    if(rs.AccessMode.equals("Standalone")) {

                    }else if(rs.AccessMode.equals("Internet")){

                    }else{

                    }
                }else{
                    if(!chkFoodsActive.isChecked()){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsAddActivity.this);
                        builder1.setMessage("ต้องการเยกเลิกรายการนี้.\n\nลำดับ "+" รหัส "+ txtFaFoodsCode.getText().toString()+" "+ txtFaFoodsName.getText().toString()+"\n");
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
                                txtFooPasswordVoid.setVisibility(View.VISIBLE);
                                btnFooVoid.setText(R.string.void1confrim);
                                txtFooPasswordVoid.setSelection(0,txtFooPasswordVoid.getText().length());
                                txtFooPasswordVoid.setFocusable(true);

                            }
                        }).create().show();

                    }
                }
            }
        });
//        new retrieveFoodsType().execute();
        if(rs.fooID.equals("")) chkFoodsActive.setChecked(true);
        setTheme();
        //setControl();
        //txtFaFoodsCode.setText(rs.fooID);
    }
    private void setControl(){
        //foo = new Foods();
        try {
            foo = new Foods();
            if((jarrF!=null) && (!jarrF.equals("[]"))){
                JSONObject catObj = (JSONObject) jarrF.get(0);

                foo.ID = catObj.getString(foo.dbID);
                foo.Code = catObj.getString(foo.dbCode);
                foo.Name = rs.StringNull(catObj.getString(foo.dbName));
                foo.Remark = rs.StringNull(catObj.getString(foo.dbRemark));
                foo.ResCode = catObj.getString(foo.dbResCode);
                foo.Price = catObj.getString(foo.dbPrice);
                foo.PrinterName = rs.StringNull(catObj.getString(foo.dbPrinterName));
                foo.Active = catObj.getString(foo.dbActive);
                foo.ResId = catObj.getString(foo.dbResId);
                foo.StatusFoods = catObj.getString(foo.dbStatusFoods);
                foo.TypeId = catObj.getString(foo.dbTypeId);
                //rs.sCboArea.add(catObj.getString("name"));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setControl ",e.getMessage());
        }
        if(foo!=null){
            txtFaFoodsCode.setText(foo.Code);
            txtFaFoodsName.setText(foo.Name);
            txtFaFoodsRemark.setText(foo.Remark);
            txtFaFoodsPrice.setText(foo.Price);
            for(int i=0;i<cboFaFoodsType.getCount();i++){
                if(cboFaFoodsType.getItemAtPosition(i).equals(rs.getFoodsTypeToName(foo.TypeId,"genid"))){
                    cboFaFoodsType.setSelection(i);
                }
            }
            for(int i = 0; i< cboFaRes.getCount(); i++){
                if(cboFaRes.getItemAtPosition(i).equals(rs.getResToName(foo.ResCode,"code"))){
                    cboFaRes.setSelection(i);
                }
            }
        }
    }
    private void getFoods(){
        String resName = cboFaRes.getSelectedItem().toString();
        String foodsTypeName = cboFaFoodsType.getSelectedItem().toString();
        foo = new Foods();
        foo.ID = rs.fooID;
        foo.ResCode = rs.getRes(resName, "code");
        foo.Name = txtFaFoodsName.getText().toString();
        foo.ResId = rs.getRes(resName, "genid");
        foo.Remark = txtFaFoodsRemark.getText().toString();
        foo.Code = txtFaFoodsCode.getText().toString();
        foo.Active = "1";
        foo.Price = txtFaFoodsPrice.getText().toString();
        foo.StatusFoods = "1";
        foo.TypeId=rs.getFoodsType(foodsTypeName,"genid");
        foo.PrinterName = "";
        //foo.ResId="";
    }
    private void setTheme(){
        lbFaFoodsCode.setTextSize(textSize);
        lbFaFoodsName.setTextSize(textSize);
        lbFaFoodsPrice.setTextSize(textSize);
        lbFaFoodsRemark.setTextSize(textSize);
        lbFaResCode.setTextSize(textSize);
        lbPrinterName.setTextSize(textSize);
        lbFooActive.setTextSize(textSize);
        lbFaFoodsType.setTextSize(textSize);
        txtFaFoodsCode.setTextSize(textSize);
        txtFaFoodsPrice.setTextSize(textSize);
        txtFaFoodsRemark.setTextSize(textSize);
        //btnFoodsSave.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //btnFoodsSave.setThe(getResources().getColor(R.color.colorPrimary),getTheme());
        //txt.setTextSize(textSize);
    }
    class insertFoods extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));

            params.add(new BasicNameValuePair(foo.dbID, foo.ID));
            params.add(new BasicNameValuePair(foo.dbCode, foo.Code));
            params.add(new BasicNameValuePair(foo.dbName, foo.Name));
            params.add(new BasicNameValuePair(foo.dbRemark, foo.Remark));
            params.add(new BasicNameValuePair(foo.dbResCode, foo.ResCode));
            params.add(new BasicNameValuePair(foo.dbActive, foo.Active));
            params.add(new BasicNameValuePair(foo.dbPrice, foo.Price));
            params.add(new BasicNameValuePair(foo.dbPrinterName, foo.PrinterName));
            params.add(new BasicNameValuePair(foo.dbResId, foo.ResId));
            params.add(new BasicNameValuePair(foo.dbStatusFoods, foo.StatusFoods));
            params.add(new BasicNameValuePair(foo.dbTypeId, foo.TypeId));
            if(foo.ID.equals("")){
                jarrF = jsonparser.getJSONFromUrl(rs.hostFoodsInsert,params);
            }else{
                jarrF = jsonparser.getJSONFromUrl(rs.hostFoodsUpdate,params);
            }

//                if(jarr!=null){
//                    //rs.sCboArea.clear();
//                    //JSONArray categories = jobj.getJSONArray("area");
//                    //JSONArray json = new JSONArray(jobj);
//                    //for (int i = 0; i < jarr.length(); i++) {
//                        JSONObject catObj = (JSONObject) jarr.get(0);
//                        //rs.sCboArea.add(catObj.getString("name"));
//                    //}
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
            //new retrieveFoods1().execute();
            getFoodsInsert();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    private void getFoodsInsert(){
        try{
            Log.d("getFoodsInsert ",String.valueOf(jarr.length()));
            if((jarrF!=null) && (!jarrF.equals("[]")) & jarrF.length()>0){
                JSONObject catObj = (JSONObject) jarrF.get(0);
                txtFaFoodsCode.setText(catObj.getString(foo.dbCode));
                foo.ID = catObj.getString(foo.dbID);
                btnFoodsSave.setEnabled(true);

                Log.d("sql",catObj.getString("sql"));
                if(catObj.getString("success").equals("1")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsAddActivity.this);
                    builder1.setMessage("บันทึกข้อมูล  เรียบร้อย");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btnFoodsSave.setEnabled(false);
                        }
                    }).create().show();
                }
            }else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsAddActivity.this);
                builder1.setMessage("บันทึกข้อมูล  ไม่เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnFoodsSave.setEnabled(false);
                    }
                }).create().show();
            }
            //btnFoodsSave.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("getFoodsInsert ",e.getMessage());
        }
    }
    private void getFoodsVoid(){
        try{
            Log.d("getFoodsVoid ",String.valueOf(jarr.length()));
            if((jarr!=null) && (!jarr.equals("[]")) & jarr.length()>0){
                JSONObject catObj = (JSONObject) jarr.get(0);
//                txtFaFoodsCode.setText(catObj.getString(foo.dbCode));
//                foo.ID = catObj.getString(foo.dbID);
//                btnFoodsSave.setEnabled(true);

                Log.d("sql",catObj.getString("sql"));
                if(catObj.getString("status").equals("1")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsAddActivity.this);
                    builder1.setMessage("ยกเลิกข้อมูล  เรียบร้อย");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btnFoodsSave.setEnabled(false);
                        }
                    }).create().show();
                }
            }else{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsAddActivity.this);
                builder1.setMessage("ยกเลิกข้อมูล  ไม่เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnFoodsSave.setEnabled(false);
                    }
                }).create().show();
            }
            //btnFoodsSave.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("getFoodsVoid ",e.getMessage());
        }
    }
    class retrieveFoods extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(foo.dbID, rs.fooID));

            jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoodsByID,params);
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
    /**
     * ส่งobject ไปทาง RestaurantControl แล้วError
     * เลยเปลี่ยนเป็นส่งไปทาง String แทน
     */
    public class retrieveFoods1 extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoods,new ArrayList<NameValuePair>());
            rs.jarrF = jarrF.toString();
            //jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);

            //} catch (JSONException e) {
            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;

        }
        @Override
        protected void onPreExecute() {

        }
    }
    class FoodsVoid extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(foo.dbID, arg0[1]));
            params.add(new BasicNameValuePair(foo.dbVoidUser, arg0[0]));

            jarr = jsonparser.getJSONFromUrl(rs.hostFoodsVoid,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getFoodsVoid();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
}
