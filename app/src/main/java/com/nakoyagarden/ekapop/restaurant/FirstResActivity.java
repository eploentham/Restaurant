package com.nakoyagarden.ekapop.restaurant;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class FirstResActivity extends AppCompatActivity {
    TextView lbFrFristRes, lbFrResName, lbFrUserDB, lbFrPasswordDB, lbFrWebDirectory, lbFrDatabaseIP, lbFrHostID;
    EditText txtFrResName, txtFrUserDB, txtFrPasswordDB, txtFrWebDirectory, txtFrDatabaseIP, txtFrHostID;
    RadioButton chkFrStandalone, chkFrHaveServer, chkFrInternet;
    Button btnFrCreate;

    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_res);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");

        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);

        lbFrFristRes = (TextView)findViewById(R.id.lbFrFristRes);
        lbFrResName = (TextView)findViewById(R.id.lbFrResName);
        lbFrHostID = (TextView)findViewById(R.id.lbFrHostID);
        txtFrResName = (EditText) findViewById(R.id.txtFrResName);
        txtFrHostID = (EditText) findViewById(R.id.txtFrHostID);
        chkFrStandalone = (RadioButton) findViewById(R.id.chkFrStandalone);
        chkFrHaveServer = (RadioButton)findViewById(R.id.chkFrHaveServer);
        chkFrInternet = (RadioButton) findViewById(R.id.chkFrInternet);
        btnFrCreate = (Button) findViewById(R.id.btnFrCreate);
        lbFrUserDB = (TextView) findViewById(R.id.lbFrUserDB);
        lbFrPasswordDB = (TextView) findViewById(R.id.lbFrPasswordDB);
        lbFrWebDirectory = (TextView) findViewById(R.id.lbFrWebDirectory);
        lbFrDatabaseIP = (TextView) findViewById(R.id.lbFrDatabaseIP);
        txtFrUserDB = (EditText) findViewById(R.id.txtFrUserDB);
        txtFrPasswordDB = (EditText) findViewById(R.id.txtFrPasswordDB);
        txtFrWebDirectory = (EditText) findViewById(R.id.txtFrWebDirectory);
        txtFrDatabaseIP = (EditText) findViewById(R.id.txtFrDatabaseIP);


        lbFrFristRes.setText(R.string.lbFrFristRes);
        lbFrDatabaseIP.setText(R.string.hostIP);
        lbFrUserDB.setText(R.string.user);
        lbFrPasswordDB.setText(R.string.password);
        lbFrWebDirectory.setText(R.string.lbFrWebDirectory);
        lbFrResName.setText(getResources().getString(R.string.name)+getResources().getString(R.string.restaurant));
        chkFrStandalone.setText(R.string.chkFrStandalone);
        chkFrHaveServer.setText(R.string.chkFrHaveServer);
        chkFrInternet.setText(R.string.chkFrInternet);
        btnFrCreate.setText(R.string.btnFrCreate);

        chkFrHaveServer.setChecked(false);
        chkFrInternet.setChecked(false);
        chkFrStandalone.setChecked(false);

        chkFrHaveServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChkFrServer();
            }
        });
        chkFrInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChkFrInternet();
            }
        });
        chkFrStandalone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChkFrStandalone();
            }
        });
        btnFrCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                saveText();
                String hostID = UUID.randomUUID().toString();
                txtFrHostID.setText(hostID);
                if(chkFrStandalone.isChecked()){
                    SQLiteDatabase mDb;
                    DatabaseSQLi das = new DatabaseSQLi(FirstResActivity.this);
                    mDb = das.getWritableDatabase();
//                    das.onUpgrade(mDb,1,1);
                }
                saveText();
                btnFrCreate.setEnabled(false);
            }
        });
        getText();
    }
    private void setChkFrStandalone(){
        txtFrDatabaseIP.setVisibility(View.INVISIBLE);
        txtFrWebDirectory.setVisibility(View.INVISIBLE);
        txtFrPasswordDB.setVisibility(View.INVISIBLE);
        txtFrUserDB.setVisibility(View.INVISIBLE);
        lbFrDatabaseIP.setVisibility(View.INVISIBLE);
        lbFrUserDB.setVisibility(View.INVISIBLE);
        lbFrPasswordDB.setVisibility(View.INVISIBLE);
        lbFrWebDirectory.setVisibility(View.INVISIBLE);
    }
    private void setChkFrServer(){
        txtFrDatabaseIP.setVisibility(View.VISIBLE);
        txtFrWebDirectory.setVisibility(View.VISIBLE);
        txtFrPasswordDB.setVisibility(View.VISIBLE);
        txtFrUserDB.setVisibility(View.VISIBLE);
        lbFrDatabaseIP.setVisibility(View.VISIBLE);
        lbFrUserDB.setVisibility(View.VISIBLE);
        lbFrPasswordDB.setVisibility(View.VISIBLE);
        lbFrWebDirectory.setVisibility(View.VISIBLE);
    }
    private void setChkFrInternet(){
        txtFrDatabaseIP.setVisibility(View.VISIBLE);
        txtFrWebDirectory.setVisibility(View.INVISIBLE);
        txtFrPasswordDB.setVisibility(View.INVISIBLE);
        txtFrUserDB.setVisibility(View.INVISIBLE);
        lbFrDatabaseIP.setVisibility(View.VISIBLE);
        lbFrUserDB.setVisibility(View.INVISIBLE);
        lbFrPasswordDB.setVisibility(View.INVISIBLE);
        lbFrWebDirectory.setVisibility(View.INVISIBLE);
    }
    private void saveText(){
        FileOutputStream outputStream;
        String userDB="",passwordDB="",webDirectory="",printCloseDay="", port="", posID="",taxID="", printer="",AccessMethod="";

        if(chkFrStandalone.isChecked()) {
            userDB = "";
            passwordDB = "";
            webDirectory = "";
            AccessMethod="Standalone";
        }else if (chkFrInternet.isChecked()){
            userDB = "";
            passwordDB = "";
            webDirectory = "";
            AccessMethod="Internet";
        }else{
            userDB=txtFrUserDB.getText().toString().trim();
            passwordDB=txtFrPasswordDB.getText().toString().trim();
            webDirectory=txtFrWebDirectory.getText().toString().trim();
            AccessMethod="Server";
        }
        String string = "host="+lbFrDatabaseIP.getText().toString().trim()+";\n"
                +"printer="+printer+";\n"
                +"PosID="+posID+";\n"
                +"TaxID="+taxID+";\n"
                +"PortNumber="+port+";\n"
                +"WebDirectory="+webDirectory+";\n"
                +"UserDB="+userDB+";\n"
                +"PasswordDB="+passwordDB+";\n"
                +"TextSize=16;\n"
                +"PrintOrder=Off;\n"
                +"PrintBill=Off;\n"
                +"PrintCloseDay=Off;\n"
                +"HostID="+txtFrHostID.getText().toString().trim()+";\n"
                +"AccessMethod="+AccessMethod+";\n";
        try {
            Log.d("saveText() string ",string);
            File file =getFileStreamPath("initial.cnf");
            outputStream = openFileOutput("initial.cnf", Context.MODE_PRIVATE);
//            outputStream = openFileOutput(file.getPath(), Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            rs.pageLoad=true;
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getText(){
        try {
            File file =getFileStreamPath("initial.cnf");
            final int READ_BLOCK_SIZE = 100;
            FileInputStream fileIn=openFileInput("initial.cnf");
//            FileInputStream fileIn=openFileInput(file.getPath());
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            String[] p = s.split(";");
            if(p.length>0){
                Log.d("getText() length ",String.valueOf(p.length));
//                btnFrCreate.setEnabled(false);

                String hostID = p[12].length()>0 ? p[12].replace("HostID=","").replace("\n",""):"";
                String AccessMethod = p[13].length()>0 ? p[13].replace("AccessMethod=","").replace("\n",""):"";
                txtFrHostID.setText(hostID);
                if(AccessMethod.equals("Standalone")){
                    chkFrStandalone.setChecked(true);
                }else if(AccessMethod.equals("Internet")){
                    chkFrInternet.setChecked(true);
                }else if(AccessMethod.equals("Server")){
                    chkFrHaveServer.setChecked(true);
                }
            }
            fileIn.close();
            rs.refresh();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("getText() ",e.getMessage());
        }
    }
}
