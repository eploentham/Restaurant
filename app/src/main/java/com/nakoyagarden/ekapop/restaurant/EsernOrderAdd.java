package com.nakoyagarden.ekapop.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hrules.horizontalnumberpicker.*;
import com.hrules.horizontalnumberpicker.HorizontalNumberPicker;
import com.hrules.horizontalnumberpicker.HorizontalNumberPickerListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ekapop on 8/16/2016.
 */
public class EsernOrderAdd  extends Activity implements HorizontalNumberPickerListener {
    Spinner cboTable, cboArea;
    TextView lbDate, lbMinced, lbFall, lbKoy, lbBoilZaa, lbThickSoup,lbPapazaSalad,lbGrillPorkNeck,lbWeepingTiger,lbGrillRibBeef,lbStickyRice,lbBambooShootSalad,lbBurnBeef;
    TextView lbMessage;
    com.hrules.horizontalnumberpicker.HorizontalNumberPicker txtQtyMinced,txtQtyFall,txtQtyKoy,txtQtyBoilZaa,txtQtyThickSoup,txtQtyPapazaSalad,txtQtyGrillPorkNeck,txtQtyWeepingTiger,txtQtyGrillRibBeef,txtQtyStickyRice,txtQtyBambooShootSalad,txtQtyBurnBeef;
    EditText txtRemarkMinced;
    RadioButton chkMincedPork, chkMincedBeef, chkMincedCatFish;
    RadioButton chkFallPork, chkFallBeef, chkFallBitter, chkFallSour;
    RadioButton chkKoy, chkKoyKoo, chkKoyBitter, chkKoySour;
    RadioButton chkBoilZaaBonePork, chkBoilZaaBeef, chkBoilZaaBitter, chkBoilZaaSour;
    RadioButton chkThickSoupPork, chkThickSoupBeef;
    RadioButton chkPapazaSaladThai,chkPapazaSaladCrab, chkPapazaSaladCrabPickledFish;
    Button btnSave;

    JsonParser jsonparser = new JsonParser();
    JSONObject jobj = null;
    String ab, qtyMinced, qtyFall, qtyKoy, qtyBoilZaa, qtyThickSoup, qtyPapazaSalad, qtyGrillPorkNeck, qtyWeepingTiger, qtyGrillRibBeef, qtyStickRice, qtyBambooShootSalad, qtyBurnBeef;
    int textSize=20,textSize1=18, row;

    public RestaurantControl rs;
    public ArrayList<String> sCboTable = new ArrayList<String>();
    public ArrayList<String> sCboArea = new ArrayList<String>();
    JSONArray jarr;
    //private ArrayList<Locale.Category> categoriesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_esern_order);

        GridLayout linearLayout = (GridLayout) findViewById(R.id.layoutEsernOrder);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.BackScreenEsern));

        Integer min=9;
        String txt="";
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_esern_order_add);
        //getSupportActionBar().hide();

        //RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.layoutEsern);
        //linearLayout.setBackgroundColor(Color.parseColor("#f62355"));

        cboTable = (Spinner)findViewById(R.id.cboTable);
        cboArea = (Spinner)findViewById(R.id.cboArea);

        lbDate = (TextView) findViewById(R.id.lbDate);
        lbMinced = (TextView) findViewById(R.id.lbMinced);
        lbFall = (TextView) findViewById(R.id.lbFall);
        lbKoy = (TextView) findViewById(R.id.lbKoy);
        lbBoilZaa = (TextView) findViewById(R.id.lbBoilZaa);
        lbThickSoup = (TextView) findViewById(R.id.lbThickSoup);
        lbPapazaSalad = (TextView) findViewById(R.id.lbPapazaSalad);
        lbGrillPorkNeck = (TextView) findViewById(R.id.lbGrillPorkNeck);
        lbWeepingTiger = (TextView) findViewById(R.id.lbWeepingTiger);
        lbGrillRibBeef = (TextView) findViewById(R.id.lbGrillRibBeef);
        lbStickyRice = (TextView) findViewById(R.id.lbStickyRice);
        lbBambooShootSalad = (TextView) findViewById(R.id.lbBambooShootSalad);
        lbBurnBeef = (TextView) findViewById(R.id.lbBurnBeef);
        lbMessage = (TextView) findViewById(R.id.lbMessage);

        txtRemarkMinced = (EditText) findViewById(R.id.txtRemarkMinced);
        btnSave = (Button) findViewById(R.id.btnSave);

        txtQtyMinced = (HorizontalNumberPicker) findViewById(R.id.txtQtyMinced);
        txtQtyMinced.setMaxValue(min);
        txtQtyMinced.setMinValue(0);
        //txtQtyMinced.getButtonMinusView().set
        txtQtyMinced.setListener(this);

        txtQtyFall = (HorizontalNumberPicker) findViewById(R.id.txtQtyFall);
        txtQtyFall.setMaxValue(min);
        txtQtyFall.setMinValue(0);
        txtQtyKoy = (HorizontalNumberPicker) findViewById(R.id.txtQtyKoy);
        txtQtyKoy.setMaxValue(min);
        txtQtyKoy.setMinValue(0);
        txtQtyBoilZaa = (HorizontalNumberPicker) findViewById(R.id.txtQtyBoilZaa);
        txtQtyBoilZaa.setMaxValue(min);
        txtQtyBoilZaa.setMinValue(0);
        txtQtyThickSoup = (HorizontalNumberPicker) findViewById(R.id.txtQtyThickSoup);
        txtQtyThickSoup.setMaxValue(min);
        txtQtyThickSoup.setMinValue(0);
        txtQtyPapazaSalad = (HorizontalNumberPicker) findViewById(R.id.txtQtyPapazaSalad);
        txtQtyPapazaSalad.setMaxValue(min);
        txtQtyPapazaSalad.setMinValue(0);
        txtQtyGrillPorkNeck = (HorizontalNumberPicker) findViewById(R.id.txtQtyGrillPorkNeck);
        txtQtyGrillPorkNeck.setMaxValue(min);
        txtQtyGrillPorkNeck.setMinValue(0);
        txtQtyWeepingTiger = (HorizontalNumberPicker) findViewById(R.id.txtQtyWeepingTiger);
        txtQtyWeepingTiger.setMaxValue(min);
        txtQtyWeepingTiger.setMinValue(0);
        txtQtyGrillRibBeef = (HorizontalNumberPicker) findViewById(R.id.txtQtyGrillRibBeef);
        txtQtyGrillRibBeef.setMaxValue(min);
        txtQtyGrillRibBeef.setMinValue(0);
        txtQtyStickyRice = (HorizontalNumberPicker) findViewById(R.id.txtQtyStickyRice);
        txtQtyStickyRice.setMaxValue(min);
        txtQtyStickyRice.setMinValue(0);
        txtQtyStickyRice = (HorizontalNumberPicker) findViewById(R.id.txtQtyStickyRice);
        txtQtyStickyRice.setMaxValue(min);
        txtQtyStickyRice.setMinValue(0);
        txtQtyBambooShootSalad = (HorizontalNumberPicker) findViewById(R.id.txtQtyBambooShootSalad);
        txtQtyBambooShootSalad.setMaxValue(min);
        txtQtyBambooShootSalad.setMinValue(0);
        txtQtyBurnBeef = (HorizontalNumberPicker) findViewById(R.id.txtQtyBurnBeef);
        txtQtyBurnBeef.setMaxValue(min);
        txtQtyBurnBeef.setMinValue(0);

        chkMincedPork = (RadioButton)findViewById(R.id.chkMincedPork);
        chkMincedBeef = (RadioButton)findViewById(R.id.chkMincedBeef);
        chkMincedCatFish = (RadioButton)findViewById(R.id.chkMincedCatFish);

        chkFallPork = (RadioButton)findViewById(R.id.chkFallPork);
        chkFallBeef = (RadioButton)findViewById(R.id.chkFallBeef);
        chkFallBitter = (RadioButton)findViewById(R.id.chkFallBitter);
        chkFallSour = (RadioButton)findViewById(R.id.chkFallSour);

        chkKoy = (RadioButton)findViewById(R.id.chkKoy);
        chkKoyKoo = (RadioButton)findViewById(R.id.chkKoyKoo);
        chkKoyBitter = (RadioButton)findViewById(R.id.chkKoyBitter);
        chkKoySour = (RadioButton)findViewById(R.id.chkKoySour);

        chkBoilZaaBonePork = (RadioButton)findViewById(R.id.chkBoilZaaBonePork);
        chkBoilZaaBeef = (RadioButton)findViewById(R.id.chkBoilZaaBeef);
        chkBoilZaaBitter = (RadioButton)findViewById(R.id.chkBoilZaaBitter);
        chkBoilZaaSour = (RadioButton)findViewById(R.id.chkBoilZaaSour);

        chkThickSoupPork = (RadioButton)findViewById(R.id.chkThickSoupPork);
        chkThickSoupBeef = (RadioButton)findViewById(R.id.chkThickSoupBeef);

        chkPapazaSaladThai = (RadioButton)findViewById(R.id.chkPapazaSaladThai);
        chkPapazaSaladCrab = (RadioButton)findViewById(R.id.chkPapazaSaladCrab);
        chkPapazaSaladCrabPickledFish = (RadioButton)findViewById(R.id.chkPapazaSaladCrabPickledFish);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String currentDateandTime = sdf .format(new Date());
        lbDate.setText(currentDateandTime);
        lbMinced.setText(R.string.minced);
        lbFall.setText(R.string.fall);
        lbKoy.setText(R.string.koy);
        //txt = R.string.boil+" "+R.string.zaa;
        lbBoilZaa.setText(getResources().getString(R.string.boil)+getResources().getString(R.string.zaa));
        lbThickSoup.setText(R.string.thicksoup);
        lbPapazaSalad.setText(R.string.papaya_salad);
        lbGrillPorkNeck.setText(getResources().getString(R.string.neck)+getResources().getString(R.string.pork)+getResources().getString(R.string.gril));
        lbWeepingTiger.setText(R.string.weepingtiger);
        lbGrillRibBeef.setText(getResources().getString(R.string.rib)+getResources().getString(R.string.beef)+getResources().getString(R.string.gril));
        lbStickyRice.setText(R.string.stickyrice);
        lbBambooShootSalad.setText(R.string.bamboo_shoot_salad);
        lbBurnBeef.setText(getResources().getString(R.string.beef)+getResources().getString(R.string.burn));
        lbMessage.setText("");

        setTheme();

        chkMincedPork.setText(R.string.pork);
        chkMincedBeef.setText(R.string.beef);
        chkMincedCatFish.setText(R.string.catfish);

        chkFallPork.setText(R.string.pork);
        chkFallBeef.setText(R.string.beef);
        chkFallBitter.setText(R.string.bitter);
        chkFallSour.setText(R.string.sour);

        chkKoy.setText(R.string.koy);
        chkKoyKoo.setText(R.string.koo);
        chkKoyBitter.setText(R.string.bitter);
        chkKoySour.setText(R.string.sour);

        //txt = R.string.bone+" "+R.string.pork;
        chkBoilZaaBonePork.setText(getResources().getString(R.string.bone)+getResources().getString(R.string.pork));
        chkBoilZaaBeef.setText(R.string.beef);
        chkBoilZaaBitter.setText(R.string.bitter);
        chkBoilZaaSour.setText(R.string.sour);

        chkThickSoupPork.setText(getResources().getString(R.string.thicksoup)+getResources().getString(R.string.pork));
        chkThickSoupBeef.setText(getResources().getString(R.string.thicksoup)+getResources().getString(R.string.beef));

        chkPapazaSaladThai.setText(getResources().getString(R.string.thai));
        chkPapazaSaladCrab.setText(getResources().getString(R.string.crab));
        chkPapazaSaladCrabPickledFish.setText(getResources().getString(R.string.crab)+getResources().getString(R.string.pickledfish));

        btnSave.setText(R.string.save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txtQtyMinced.getValue();
                saveOrder();
            }
        });

        //setCboTable();
        //rs = new RestaurantControl();
        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        ArrayAdapter<String> adaTable = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboTable);
        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboArea);
        cboTable.setAdapter(adaTable);
        cboArea.setAdapter(adaArea);

        //new retrieveArea().execute();
    }
    @Override
    public void onHorizontalNumberPickerChanged(HorizontalNumberPicker horizontalNumberPicker, int value) {
        switch (horizontalNumberPicker.getId()) {
            case R.id.txtQtyMinced:
                //txtRemarkMinced.setText(String.valueOf(value));
                break;
            case R.id.txtQtyFall:
                //DebugLog.d("horizontal_number_picker2 current value: " + value);
                break;

            case R.id.txtQtyKoy:
                //DebugLog.d("horizontal_number_picker3 current value: " + value);
                break;
        }
    }
    private void setTheme(){
        lbMinced.setTextColor(Color.BLACK);
        lbMinced.setTextColor(getResources().getColor(R.color.Black));
        lbFall.setTextColor(getResources().getColor(R.color.Black));
        lbKoy.setTextColor(getResources().getColor(R.color.Black));
        lbBoilZaa.setTextColor(getResources().getColor(R.color.Black));
        lbThickSoup.setTextColor(getResources().getColor(R.color.Black));
        lbPapazaSalad.setTextColor(getResources().getColor(R.color.Black));
        lbGrillPorkNeck.setTextColor(getResources().getColor(R.color.Black));
        lbWeepingTiger.setTextColor(getResources().getColor(R.color.Black));
        lbGrillRibBeef.setTextColor(getResources().getColor(R.color.Black));
        lbStickyRice.setTextColor(getResources().getColor(R.color.Black));
        lbBambooShootSalad.setTextColor(getResources().getColor(R.color.Black));
        lbBurnBeef.setTextColor(getResources().getColor(R.color.Black));

        //lbMessage.setText(Float.toString(lbMinced.getTextSize()));
        lbMinced.setTextSize(textSize);
        lbFall.setTextSize(textSize);
        lbKoy.setTextSize(textSize);
        lbBoilZaa.setTextSize(textSize);
        lbThickSoup.setTextSize(textSize);
        lbPapazaSalad.setTextSize(textSize);
        lbGrillPorkNeck.setTextSize(textSize);
        lbWeepingTiger.setTextSize(textSize);
        lbGrillRibBeef.setTextSize(textSize);
        lbStickyRice.setTextSize(textSize);
        lbBambooShootSalad.setTextSize(textSize);
        lbBurnBeef.setTextSize(textSize);



        chkMincedPork.setTextSize(textSize1);
        chkMincedBeef.setTextSize(textSize1);
        chkMincedCatFish.setTextSize(textSize1);
        chkFallPork.setTextSize(textSize1);
        chkFallBeef.setTextSize(textSize1);
        chkFallBitter.setTextSize(textSize1);
        chkFallSour.setTextSize(textSize1);
        chkFallBitter.setTextColor(getResources().getColor(R.color.Red));
        chkFallSour.setTextColor(getResources().getColor(R.color.Red));
        chkKoy.setTextSize(textSize1);
        chkKoyKoo.setTextSize(textSize1);
        chkKoyBitter.setTextSize(textSize1);
        chkKoySour.setTextSize(textSize1);
        chkKoyBitter.setTextColor(getResources().getColor(R.color.Red));
        chkKoySour.setTextColor(getResources().getColor(R.color.Red));
        chkBoilZaaBonePork.setTextSize(textSize1);
        chkBoilZaaBeef.setTextSize(textSize1);
        chkBoilZaaBitter.setTextSize(textSize1);
        chkBoilZaaSour.setTextSize(textSize1);
        chkBoilZaaBitter.setTextColor(getResources().getColor(R.color.Red));
        chkBoilZaaSour.setTextColor(getResources().getColor(R.color.Red));
        chkThickSoupPork.setTextSize(textSize1);
        chkThickSoupPork.setTextSize(textSize1);
        chkPapazaSaladThai.setTextSize(textSize1);
        chkPapazaSaladCrab.setTextSize(textSize1);
        chkPapazaSaladCrabPickledFish.setTextSize(textSize1);

    }
    private void saveOrder(){
        row=0;
        String lotID = UUID.randomUUID().toString();
        String areacode = cboArea.getSelectedItem().toString();
        String tablecode = cboTable.getSelectedItem().toString();

        for(int i=0;i<=12;i++){
            row=i;
            RadioGroup rg;
            RadioButton rb1, rb2,rb3;
            switch (i) {
                case 0:
                    if(txtQtyMinced.getValue()>0){
                        rg = (RadioGroup) findViewById(R.id.chkGMinced);
                        int selMinced = rg.getCheckedRadioButtonId();
                        rb1 = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                        new retrieveData().execute(String.valueOf(i),lotID, "100", "10", tablecode, areacode,String.valueOf(txtQtyMinced.getValue()),rb1.getText().toString(),"");
                    }
                    break;
                case 1:
                    if(txtQtyFall.getValue()>0){
                        rg = (RadioGroup) findViewById(R.id.chkGFall);
                        int selFall = rg.getCheckedRadioButtonId();
                        rb1 = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                        rg = (RadioGroup) findViewById(R.id.chkGFallBitter);
                        int selFallBitter = rg.getCheckedRadioButtonId();
                        rb2 = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                        new retrieveData().execute(String.valueOf(i),lotID, "101", "10", tablecode, areacode,String.valueOf(txtQtyFall.getValue()),rb1.getText().toString(), rb2.getText().toString());
                    }
                    break;
                case 2:
                    if(txtQtyKoy.getValue()>0){
                        rg = (RadioGroup) findViewById(R.id.chkGKoy);
                        int selKoy = rg.getCheckedRadioButtonId();
                        rb1 = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                        rg = (RadioGroup) findViewById(R.id.chkGKoyBitter);
                        int selKoyBitter = rg.getCheckedRadioButtonId();
                        rb2 = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                        new retrieveData().execute(String.valueOf(i),lotID, "102", "10", tablecode, areacode,String.valueOf(txtQtyKoy.getValue()),rb1.getText().toString(), rb2.getText().toString());
                    }
                    break;
                case 3:
                    if(txtQtyBoilZaa.getValue()>0){
                        rg = (RadioGroup) findViewById(R.id.chkGBoilZaa);
                        int selBoilZaa = rg.getCheckedRadioButtonId();
                        rb1 = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                        rg = (RadioGroup) findViewById(R.id.chkGBoilZaaBitter);
                        int selBoilZaaBitter = rg.getCheckedRadioButtonId();
                        rb2 = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                        new retrieveData().execute(String.valueOf(i),lotID, "103", "10", tablecode, areacode,String.valueOf(txtQtyBoilZaa.getValue()),rb1.getText().toString(), rb2.getText().toString());
                    }
                    break;
                case 4:
                    if(txtQtyThickSoup.getValue()>0){
                        rg = (RadioGroup) findViewById(R.id.chkGThickSoupPork);
                        int selBoilZaa = rg.getCheckedRadioButtonId();
                        rb1 = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                        new retrieveData().execute(String.valueOf(i),lotID, "104", "10", tablecode, areacode,String.valueOf(txtQtyThickSoup.getValue()),rb1.getText().toString(),"");
                    }
                    break;
                case 5:
                    if(txtQtyPapazaSalad.getValue()>0){
                        rg = (RadioGroup) findViewById(R.id.chkGPapazaSalad);
                        int selPapazaSalad = rg.getCheckedRadioButtonId();
                        rb1 = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                        new retrieveData().execute(String.valueOf(i),lotID, "105", "10", tablecode, areacode,String.valueOf(txtQtyPapazaSalad.getValue()),rb1.getText().toString(),"");
                    }
                    break;
                case 6:
                    if(txtQtyGrillPorkNeck.getValue()>0){
                        new retrieveData().execute(String.valueOf(i),lotID, "106", "10", tablecode, areacode,String.valueOf(txtQtyGrillPorkNeck.getValue()),"","");
                    }
                    break;
                case 7:
                    if(txtQtyWeepingTiger.getValue()>0){
                        new retrieveData().execute(String.valueOf(i),lotID, "107", "10", tablecode, areacode,String.valueOf(txtQtyWeepingTiger.getValue()),"","");
                    }
                    break;
                case 8:
                    if(txtQtyGrillRibBeef.getValue()>0){
                        new retrieveData().execute(String.valueOf(i),lotID, "108", "10", tablecode, areacode,String.valueOf(txtQtyGrillRibBeef.getValue()),"","");
                    }
                    break;
                case 9:
                    if(txtQtyStickyRice.getValue()>0){
                        new retrieveData().execute(String.valueOf(i),lotID, "109", "10", tablecode, areacode,String.valueOf(txtQtyStickyRice.getValue()),"","");
                    }
                    break;
                case 10:
                    if(txtQtyBambooShootSalad.getValue()>0){
                        new retrieveData().execute(String.valueOf(i),lotID, "110", "10", tablecode, areacode,String.valueOf(txtQtyBambooShootSalad.getValue()),"","");
                    }
                    break;
                case 11:
                    if(txtQtyBurnBeef.getValue()>0){
                        new retrieveData().execute(String.valueOf(i),lotID, "111", "10", tablecode, areacode,String.valueOf(txtQtyBurnBeef.getValue()),"","");
                    }
                    break;
            }
            //new retrievedata().execute(String.valueOf(i));
        }
        //jobj = jsonparser.getJSONFromUrl(urlOrder, params);

        // check your log for json response
        //Log.d("Login attempt", jobj.toString());

        //try {
        //    ab = jobj.getString("key");
        //} catch (JSONException e) {
        //    // TODO Auto-generated catch block
        //    e.printStackTrace();
        //}
    }
    class retrieveData extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            //lbMessage = (TextView) findViewById(R.id.lbMessage);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //String uniqueID = UUID.randomUUID().toString();
            //row = Integer.parseInt(arg0[0]);
            //params.add(new BasicNameValuePair("order_id", uniqueID));
            params.add(new BasicNameValuePair("lot_id", arg0[1]));
            params.add(new BasicNameValuePair("foods_code", arg0[2]));
            params.add(new BasicNameValuePair("res_code", arg0[3]));
            params.add(new BasicNameValuePair("table_code", arg0[4]));
            params.add(new BasicNameValuePair("area_code", arg0[5]));
            params.add(new BasicNameValuePair("qty", arg0[6]));
            params.add(new BasicNameValuePair("status_foods_1", arg0[7]));
            params.add(new BasicNameValuePair("status_foods_2", arg0[8]));
            params.add(new BasicNameValuePair("status_foods_3", ""));
            params.add(new BasicNameValuePair("foods_name", "555"));
            params.add(new BasicNameValuePair("remark", "666"));
            params.add(new BasicNameValuePair("price", "22"));
            params.add(new BasicNameValuePair("row1", arg0[0]));
            jarr = jsonparser.getJSONFromUrl(rs.hostSaveOrder, params);

            // check your log for json response
            //Log.d("Login attempt", jobj.toString());

            try {
                JSONObject obj = (JSONObject) jarr.get(0);
                ab = obj.getString("success");
                if (ab.equals("1")) {
                    //lbMessage.setText("รับOrderเรียบร้อย");
                    ab = "รับOrderเรียบร้อย";
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ab;
        }

        @Override
        protected void onPostExecute(String ab) {
            lbMessage.setText(ab);
        }

        @Override
        protected void onPreExecute() {
            lbMessage.setText("กำลังส่งข้อมูล ...");
            //qtyMinced = String.valueOf(txtQtyMinced.getValue());
            //pass = password.getText().toString();
            //switch (row) {
            //    case 0:
            //        qtyMinced = String.valueOf(txtQtyMinced.getValue());
            //       break;
            //    case 1:
            //        qtyFall = String.valueOf(txtQtyFall.getValue());
            //        break;
            //    case 2:
            //        qtyKoy= String.valueOf(txtQtyKoy.getValue());
            //        break;
            //    case 3:
            //        qtyBoilZaa = String.valueOf(txtQtyBoilZaa.getValue());
            //        break;
            //    case 4:
            //        qtyThickSoup = String.valueOf(txtQtyThickSoup.getValue());
            //        break;
            //    case 5:
            //        qtyPapazaSalad = String.valueOf(txtQtyPapazaSalad.getValue());
            //        break;
            //    case 6:
            //        qtyGrillPorkNeck = String.valueOf(txtQtyGrillPorkNeck.getValue());
            //        break;
            //    case 7:
            //        qtyWeepingTiger = String.valueOf(txtQtyWeepingTiger.getValue());
            //        break;
            //    case 8:
            //        qtyGrillRibBeef = String.valueOf(txtQtyGrillRibBeef.getValue());
            //        break;
            //    case 9:
            //        qtyStickRice = String.valueOf(txtQtyStickyRice.getValue());
            //        break;
            //    case 10:
            //        qtyBambooShootSalad = String.valueOf(txtQtyBambooShootSalad.getValue());
            //        break;
            //    case 11:
            //        qtyBurnBeef = String.valueOf(txtQtyBurnBeef.getValue());
            //        break;
            //}
        }
    }
}
