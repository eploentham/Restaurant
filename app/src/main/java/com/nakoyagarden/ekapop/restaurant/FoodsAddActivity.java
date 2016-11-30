package com.nakoyagarden.ekapop.restaurant;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    TextView lbFoodsCode, lbFoodsName, lbFoodsPrice, lbFoodsRemark, lbResCode, lbPrinterName, lbActive,lbFaFoodsType;

    EditText txtFoodsCode, txtFoodsName, txtFoodsPrice, txtFoodsRemark;
    Spinner cboRes, cboFaFoodsType,cboFaPrinter;
    Switch chkFoodsActive;
    Button btnFoodsSave;

    private RestaurantControl rs;
    private Foods foo;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_add);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");

        lbFoodsCode = (TextView)findViewById(R.id.lbFoodsCode);
        lbFoodsName = (TextView)findViewById(R.id.lbFoodsName);
        lbFoodsPrice = (TextView)findViewById(R.id.lbFoodsPrice);
        lbFoodsRemark = (TextView)findViewById(R.id.lbFoodsRemark);
        lbResCode = (TextView)findViewById(R.id.lbResCode);
        lbPrinterName = (TextView)findViewById(R.id.lbFaPrinter);
        lbFaFoodsType = (TextView)findViewById(R.id.lbFaFoodsType);
        lbActive = (TextView)findViewById(R.id.lbActive);
        txtFoodsCode = (EditText) findViewById(R.id.txtFoodsCode);
        txtFoodsName = (EditText)findViewById(R.id.txtFoodsName);
        txtFoodsPrice = (EditText)findViewById(R.id.txtFoodsPrice);
        txtFoodsRemark = (EditText)findViewById(R.id.txtFoodsRemark);
        cboFaPrinter = (Spinner) findViewById(R.id.cboFaPrinter);
        cboRes = (Spinner)findViewById(R.id.cboFoodsRes);
        cboFaFoodsType = (Spinner) findViewById(R.id.cboFaFoodsType);
        chkFoodsActive = (Switch) findViewById(R.id.chkFoodsActive);
        btnFoodsSave = (Button) findViewById(R.id.btnFoodsSave);

        lbFoodsCode.setText(R.string.code);
        lbFoodsName.setText(R.string.name);
        lbFoodsPrice.setText(R.string.price);
        lbFoodsRemark.setText(R.string.remark);
        lbResCode.setText(R.string.restaurant);
        lbPrinterName.setText(R.string.printername);
        lbFaFoodsType.setText(R.string.foodstype);
        lbActive.setText(R.string.active);
        //chkFoodsActive.setText(R.string.activeon);
        chkFoodsActive.setChecked(false);
        chkFoodsActive.setChecked(true);
        txtFoodsCode.setEnabled(false);
        txtFoodsName.setSelected(true);
        chkFoodsActive.setText(R.string.activeon);

        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboRes);
        cboRes.setAdapter(adaArea);
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
                new insertFoods().execute();
            }
        });

        chkFoodsActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkFoodsActive.setText(R.string.activeon);
                }else{
                    chkFoodsActive.setText(R.string.activeoff);
                }
            }
        });
        new retrieveFoods().execute();
        setTheme();
        //setControl();
        //txtFoodsCode.setText(rs.fooID);
    }
    private void setControl(){
        //foo = new Foods();
        if(foo!=null){
            txtFoodsCode.setText(foo.Code);
            txtFoodsName.setText(foo.Name);
            txtFoodsRemark.setText(foo.Remark);
            txtFoodsPrice.setText(foo.Price);
            for(int i=0;i<cboFaFoodsType.getCount();i++){
                if(cboFaFoodsType.getItemAtPosition(i).equals(rs.getFoodsTypeToName(foo.TypeId,"id"))){
                    cboFaFoodsType.setSelection(i);
                }
            }
            for(int i=0;i<cboRes.getCount();i++){
                if(cboRes.getItemAtPosition(i).equals(rs.getResToName(foo.ResCode,"code"))){
                    cboRes.setSelection(i);
                }
            }
        }
    }
    private void getFoods(){
        String resName = cboRes.getSelectedItem().toString();
        String foodsTypeName = cboFaFoodsType.getSelectedItem().toString();
        foo = new Foods();
        foo.ID = rs.fooID;
        foo.ResCode = rs.getRes(resName, "code");
        foo.Name = txtFoodsName.getText().toString();
        foo.ResId = rs.getRes(resName, "id");
        foo.Remark = txtFoodsRemark.getText().toString();
        foo.Code = txtFoodsCode.getText().toString();
        foo.Active = "1";
        foo.Price = txtFoodsPrice.getText().toString();
        foo.StatusFoods = "1";
        foo.TypeId=rs.getFoodsType(foodsTypeName,"id");
        foo.PrinterName = "";
        //foo.ResId="";
    }
    private void setTheme(){
        lbFoodsCode.setTextSize(textSize);
        lbFoodsName.setTextSize(textSize);
        lbFoodsPrice.setTextSize(textSize);
        lbFoodsRemark.setTextSize(textSize);
        lbResCode.setTextSize(textSize);
        lbPrinterName.setTextSize(textSize);
        lbActive.setTextSize(textSize);
        lbFaFoodsType.setTextSize(textSize);
        txtFoodsCode.setTextSize(textSize);
        txtFoodsPrice.setTextSize(textSize);
        txtFoodsRemark.setTextSize(textSize);
        //btnFoodsSave.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //btnFoodsSave.setThe(getResources().getColor(R.color.colorPrimary),getTheme());
        //txt.setTextSize(textSize);
    }
    class insertFoods extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("ID", foo.ID));
                params.add(new BasicNameValuePair("Code", foo.Code));
                params.add(new BasicNameValuePair("Name", foo.Name));
                params.add(new BasicNameValuePair("Remark", foo.Remark));
                params.add(new BasicNameValuePair("ResCode", foo.ResCode));
                params.add(new BasicNameValuePair("Active", foo.Active));
                params.add(new BasicNameValuePair("Price", foo.Price));
                params.add(new BasicNameValuePair("PrinterName", foo.PrinterName));
                params.add(new BasicNameValuePair("ResId", foo.ResId));
                params.add(new BasicNameValuePair("StatusFoods", foo.StatusFoods));
                params.add(new BasicNameValuePair("TypeId", foo.TypeId));
                if(foo.ID.equals("")){
                    jarr = jsonparser.getJSONFromUrl(rs.hostFoodsInsert,params);
                }else{
                    jarr = jsonparser.getJSONFromUrl(rs.hostFoodsUpdate,params);
                }

                if(jarr!=null){
                    //rs.sCboArea.clear();
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    //for (int i = 0; i < jarr.length(); i++) {
                        JSONObject catObj = (JSONObject) jarr.get(0);
                        //rs.sCboArea.add(catObj.getString("name"));
                    //}
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
            try{
                JSONObject catObj = (JSONObject) jarr.get(0);
                txtFoodsCode.setText(catObj.getString("foods_code"));
                foo.ID = catObj.getString("foods_id");
                btnFoodsSave.setEnabled(true);
                //btnFoodsSave.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    class retrieveFoods extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("foods_id", rs.fooID));

                jarr = jsonparser.getJSONFromUrl(rs.hostSelectFoodsByID,params);
                if((jarr!=null) && (!jarr.equals("[]"))){
                    JSONObject catObj = (JSONObject) jarr.get(0);
                    foo = new Foods();
                    foo.ID = catObj.getString("foods_id");
                    foo.Code = catObj.getString("foods_code");
                    foo.Name = rs.StringNull(catObj.getString("foods_name"));
                    foo.Remark = rs.StringNull(catObj.getString("remark"));
                    foo.ResCode = catObj.getString("res_code");
                    foo.Price = catObj.getString("foods_price");
                    foo.PrinterName = rs.StringNull(catObj.getString("printer_name"));
                    foo.Active = catObj.getString("active");
                    foo.ResId = catObj.getString("res_id");
                    foo.StatusFoods = catObj.getString("status_foods");
                    foo.TypeId = catObj.getString("foods_type_id");
                        //rs.sCboArea.add(catObj.getString("name"));
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
            jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoods,new ArrayList<NameValuePair>());
            rs.jarrF = jarrF.toString();
            //jarrF = jsonparser.getJSONFromUrl(rs.hostGetRes,params);

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
}
