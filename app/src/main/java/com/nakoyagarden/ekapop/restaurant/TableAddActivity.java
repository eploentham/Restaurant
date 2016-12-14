package com.nakoyagarden.ekapop.restaurant;

import android.content.Intent;
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

public class TableAddActivity extends AppCompatActivity {
    TextView lbTaCode, lbTaName, lbTaArea, lbTaRemark, lbTaActive,lbTaSort1;
    EditText txtTaCode, txtTaName, txtTaRemark, txtTaSort1;
    Spinner cboTaArea;
    Button btnTaSave;
    Switch chkTaActive;

    Table ta;
    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_add);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        lbTaCode = (TextView)findViewById(R.id.lbTaCode);
        lbTaName = (TextView)findViewById(R.id.lbTaName);
        lbTaArea = (TextView)findViewById(R.id.lbTaArea);
        lbTaRemark = (TextView)findViewById(R.id.lbTaRemark);
        lbTaActive = (TextView)findViewById(R.id.lbTaActive);
        lbTaSort1 = (TextView)findViewById(R.id.lbTaSort1);
        txtTaCode = (EditText)findViewById(R.id.txtTaCode);
        txtTaName = (EditText)findViewById(R.id.txtTaName);
        txtTaRemark = (EditText)findViewById(R.id.txtTaRemark);
        txtTaSort1 = (EditText)findViewById(R.id.txtTaSort1);
        btnTaSave = (Button)findViewById(R.id.btnTaSave);
        cboTaArea = (Spinner)findViewById(R.id.cboTaArea);
        chkTaActive = (Switch) findViewById(R.id.chkTaActive);

        lbTaCode.setText(R.string.code);
        lbTaName.setText(R.string.name);
        lbTaArea.setText(R.string.area);
        lbTaRemark.setText(R.string.remark);
        lbTaActive.setText(R.string.active);
        lbTaSort1.setText(R.string.sort);
        btnTaSave.setText(R.string.save);

        btnTaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTable();
                new insertTable().execute();
            }
        });
        chkTaActive.setText(R.string.activeon);
        chkTaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkTaActive.setText(R.string.activeon);
                }else{
                    chkTaActive.setText(R.string.activeoff);
                }
            }
        });
        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboArea);
        cboTaArea.setAdapter(adaArea);
        new retrieveTable().execute();
        setTheme();
    }
    private void setTheme(){
        lbTaCode.setTextSize(textSize);
        lbTaName.setTextSize(textSize);
        lbTaRemark.setTextSize(textSize);
        lbTaSort1.setTextSize(textSize);
        lbTaActive.setTextSize(textSize);
        txtTaCode.setTextSize(textSize);
        txtTaName.setTextSize(textSize);
        txtTaRemark.setTextSize(textSize);
        txtTaSort1.setTextSize(textSize);
    }
    private void setControl(){
        if(ta!=null){
            txtTaCode.setText(ta.Code);
            txtTaName.setText(ta.Name);
            txtTaRemark.setText(ta.Remark);
            txtTaSort1.setText(ta.Sort1);
            for(int i=0;i<cboTaArea.getCount();i++){
                if(cboTaArea.getItemAtPosition(i).equals(rs.getAreaToName(ta.AreaID,"id"))){
                    cboTaArea.setSelection(i);
                }
            }
            if(ta.Active.equals("1")){
                chkTaActive.setChecked(true);
            }else{
                chkTaActive.setChecked(false);
            }
        }
    }
    private void getTable(){
        ta = new Table();
        ta.ID = rs.taID;
        ta.Sort1=txtTaSort1.getText().toString().trim();
        ta.Active="1";
        ta.Code=txtTaCode.getText().toString().trim();
        ta.Name=txtTaName.getText().toString().trim();
        ta.Remark=txtTaRemark.getText().toString().trim();
        if(chkTaActive.isChecked()){
            ta.Active="1";
        }else{
            ta.Active="3";
        }
    }
    class insertTable extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                params.add(new BasicNameValuePair("ID", ta.ID));
                params.add(new BasicNameValuePair("Code", ta.Code));
                params.add(new BasicNameValuePair("Name", ta.Name));
                params.add(new BasicNameValuePair("Remark", ta.Remark));
                params.add(new BasicNameValuePair("Active", ta.Active));
                params.add(new BasicNameValuePair("Sort1", ta.Sort1));
                params.add(new BasicNameValuePair("AreaID", ta.AreaID));
                if(ta.ID.equals("")){
                    jarr = jsonparser.getJSONFromUrl(rs.hostTableInsert,params);
                }else{
                    jarr = jsonparser.getJSONFromUrl(rs.hostTableUpdate,params);
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
    class retrieveTable extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("table_id", rs.taID));

                jarr = jsonparser.getJSONFromUrl(rs.hostTableSelectByID,params);
                if((jarr!=null) && (!jarr.equals("[]"))){

                    JSONObject catObj = (JSONObject) jarr.get(0);
                    ta = new Table();
                    ta.ID = catObj.getString("table_id");
                    ta.Code = catObj.getString("table_code");
                    ta.Name = rs.StringNull(catObj.getString("table_name")).trim();
                    ta.Remark = rs.StringNull(catObj.getString("remark")).trim();
                    ta.Sort1 = catObj.getString("sort1");
                    ta.Active = rs.StringNull(catObj.getString("active")).trim();
                    ta.AreaID = rs.StringNull(catObj.getString("area_id")).trim();
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
