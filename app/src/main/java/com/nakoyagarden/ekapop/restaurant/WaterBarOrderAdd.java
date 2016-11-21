package com.nakoyagarden.ekapop.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.hrules.horizontalnumberpicker.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ekapop on 8/16/2016.
 */
public class WaterBarOrderAdd extends Activity {
    Spinner cboTable, cboArea;
    JsonParser jsonparser = new JsonParser();
    JSONObject jobj = null;
    public RestaurantControl rs;
    public ArrayList<String> sCboTable = new ArrayList<String>();
    public ArrayList<String> sCboArea = new ArrayList<String>();
    JSONArray jarr;
    Button btnSave;
    CheckBox chkCoke, chkPapsi, chkWaterOrange, chkWaterRed, chkSoda, chkBeerSingha, chkBeerLeo, chkBeerChiang, chkIceGlass, chkIceJar;
    com.hrules.horizontalnumberpicker.HorizontalNumberPicker txtCoke, txtPapsi, txtWaterOrange, txtWaterRed, txtSoda, txtBeerSingha, txtBeerLeo, txtBeerChiang, txtIceGlass, txtIceJar;
    int textSize=20,textSize1=18, row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_waterbar_order);

        GridLayout linearLayout = (GridLayout) findViewById(R.id.layoutWaterBarOrder);
        //linearLayout.setBackgroundColor(Color.parseColor("#3982d7"));
        linearLayout.setBackgroundColor(getResources().getColor(R.color.BackScreenWaterBar));
        cboTable = (Spinner)findViewById(R.id.cboWaterBarTable);
        cboArea = (Spinner)findViewById(R.id.cboWaterBarArea);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        ArrayAdapter<String> adaTable = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboTable);
        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboArea);
        cboTable.setAdapter(adaTable);
        cboArea.setAdapter(adaArea);

        chkCoke = (CheckBox)findViewById(R.id.chkCoke);
        chkPapsi = (CheckBox)findViewById(R.id.chkPapsi);
        chkWaterOrange = (CheckBox)findViewById(R.id.chkWaterOrange);
        chkWaterRed = (CheckBox)findViewById(R.id.chkWaterRed);
        chkSoda = (CheckBox)findViewById(R.id.chkSoda);
        chkBeerSingha = (CheckBox)findViewById(R.id.chkBeerSingha);
        chkBeerLeo = (CheckBox)findViewById(R.id.chkBeerLeo);
        chkBeerChiang = (CheckBox)findViewById(R.id.chkBeerChiang);
        chkIceGlass = (CheckBox)findViewById(R.id.chkIceGlass);
        chkIceJar = (CheckBox)findViewById(R.id.chkIceJar);

        chkCoke.setText(R.string.coke);
        chkPapsi.setText(R.string.papsi);
        chkWaterOrange.setText(R.string.waterorange);
        chkWaterRed.setText(R.string.waterred);
        chkSoda.setText(R.string.soda);
        chkBeerSingha.setText(R.string.beersingha);
        chkBeerLeo.setText(R.string.beerleo);
        chkBeerChiang.setText(R.string.beerchiang);
        chkIceGlass.setText(R.string.iceglass);
        chkIceJar.setText(R.string.icejar);
        setTheme();
    }
    private void setTheme(){
        chkCoke.setTextSize(textSize1);
        chkPapsi.setTextSize(textSize1);
        chkWaterOrange.setTextSize(textSize1);
        chkWaterRed.setTextSize(textSize1);
        chkSoda.setTextSize(textSize1);
        chkBeerSingha.setTextSize(textSize1);
        chkBeerLeo.setTextSize(textSize1);
        chkBeerChiang.setTextSize(textSize1);
        chkIceGlass.setTextSize(textSize1);
        chkIceJar.setTextSize(textSize1);
        //chkCoke.setTextColor(getResources().getColor(R.color.Red));
    }
}
