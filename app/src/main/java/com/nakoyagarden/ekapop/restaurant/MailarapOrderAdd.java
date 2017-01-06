package com.nakoyagarden.ekapop.restaurant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.epson.epos2.printer.Printer;
import com.epson.epos2.printer.PrinterStatusInfo;
import com.epson.epos2.printer.ReceiveListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ekapop on 8/16/2016.
 */
public class MailarapOrderAdd  extends Activity implements ReceiveListener {
    Spinner cboTable, cboArea;
    TextView lbMFoodsname,lbMFoodsRemark, lbMQty, lbMToGo;
    EditText txtMFoodsCode, txtMFoodsRemark, txtMPassword;
    Button btnMailarapAdd, btnMSave, btnMClear;
    RadioButton chkMToGo, chkMInRes;
//    SwipeListView sv;
    com.hrules.horizontalnumberpicker.HorizontalNumberPicker txtMQty, txtMCntCust;

    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    JSONArray jarr;
    public RestaurantControl rs;
    private PrintOrderEpson pOE;
    public ArrayList<String> sCboTable = new ArrayList<String>();
    public ArrayList<String> sCboArea = new ArrayList<String>();
    public ArrayList<Order> lorder = new ArrayList<Order>();
    public ArrayList<String> table1 = new ArrayList<String>();
    public ArrayList<Foods> lFoo = new ArrayList<Foods>();
    ListView lvMOrder;
    private ArrayAdapter<String> acboMFoods, alvMOrder, alvMFoods;
    private ArrayList<String> arrayList1, arrayFoods, arraycboMFoods, alRemark, alCode, alName, alPrice, alQty;
    JSONArray jarrT;
    Foods foo = new Foods();
    Order or = new Order();
    Table ta = new Table();
    String ab, Code="";
    Integer row1=0;
    Boolean pageLoad=false, pageLoadFromlvmClick=false, pageSearchFoods=true,flagDel=false;
    String lotID="";
    int textSize=20,textSize1=18, row=0,rowDel=0, insertErr=0, insertSucc=0;
    ArrayList<ItemData> listTable=new ArrayList<>();
//    private Printer mPrinter = null;
//    private Context mContext = null;
//    MainActivity ma;
    DatabaseSQLi daS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mailarap_order);
        pageLoad=true;
//        mContext = this;

//        new retrieveTable().execute();
//        new MainActivity().retrieveTable.execute();
//        ma.retrieveTable().execute();
        //arrayList = new ArrayList<String>();
        arrayList1 = new ArrayList<String>();
        arrayFoods = new ArrayList<String>();
        alRemark = new ArrayList<String>();
        alCode = new ArrayList<String>();
        alName = new ArrayList<String>();
        alPrice = new ArrayList<String>();
        alQty = new ArrayList<String>();
        arraycboMFoods = new ArrayList<String>();

        RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.layout_mailarap_order);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));

        cboTable = (Spinner)findViewById(R.id.cboMailarapTable);
        cboArea = (Spinner)findViewById(R.id.cboMailarapArea);
        //cboMFoods = (Spinner) findViewById(R.genid.cboMFoods);
        lbMFoodsname = (TextView)findViewById(R.id.lbMFoodsName1);
        lbMFoodsRemark = (TextView)findViewById(R.id.lbFoodsRemark);
        lbMQty = (TextView)findViewById(R.id.lbMQty);
        txtMFoodsCode = (EditText) findViewById(R.id.txtMFoodsCode);
        txtMFoodsRemark = (EditText)findViewById(R.id.txtMFoodsRemark);
        txtMPassword = (EditText)findViewById(R.id.txtMPassword);
        txtMQty = (com.hrules.horizontalnumberpicker.HorizontalNumberPicker)findViewById(R.id.txtMQty);
        txtMCntCust = (com.hrules.horizontalnumberpicker.HorizontalNumberPicker)findViewById(R.id.txtMCntCust);
        lvMOrder = (ListView)findViewById(R.id.lvMOrder);
        chkMToGo = (RadioButton) findViewById(R.id.chkMToGo);
        chkMInRes = (RadioButton) findViewById(R.id.chkMInRes);
//        lbMToGo = (TextView) findViewById(R.genid.lbMToGo);

        lbMQty.setText(R.string.qty);
        lbMFoodsRemark.setText(R.string.remark);
        chkMInRes.setText(R.string.inres);
        chkMToGo.setText(R.string.togo);
//        lbMToGo.setText(R.string.active);




        btnMailarapAdd = (Button)findViewById(R.id.btnMailarapAdd);
        btnMSave = (Button)findViewById(R.id.btnMSave);
        btnMClear = (Button)findViewById(R.id.btnMClear);
        btnMSave.setText(R.string.save);
        btnMClear.setText("สั่งใหม่");
//        btnMClear.setVisibility(View.INVISIBLE);
        btnMailarapAdd.setText(getResources().getString(R.string.order1)+" "+getResources().getString(R.string.foods));
        btnMailarapAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageSearchFoods = false;
                //arrayList.add("55");
                if(setOrder()){
                    setlvOrder();
                    setShowBtnSave(true);
                }else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MailarapOrderAdd.this);
                    builder1.setMessage("Write your message here.");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Whatever...
                        }
                    }).create().show();
                }
            }
        });
        lbMFoodsname.setText(R.string.search);
        lbMFoodsname.setBackgroundColor(getResources().getColor(R.color.BackScreenSearch));
        //lbMFoodsname.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        lbMFoodsname.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        //        Code =  txtMFoodsCode.getText().toString();
        //        new retrieveFoodsByCode().execute();
        //    }
        //});
        //cboMFoods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        //   @Override
        //    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //       if(!pageLoad){
        //            //txtMFoodsRemark.setText("adsfasdf");
        //            //txtMFoodsRemark.setText(cboMFoods.getItemAtPosition(i).toString());
        //            Foods foo1 = (Foods)lFoo.get(i);
        //            txtMFoodsCode.setText(foo1.Code);
        //            lbMFoodsname.setText(foo1.Name);
        //            foo.ID = foo1.ID;
        //            //String aa = lbMFoodsname.getText().toString();
        //        }
        //        pageLoad=false;
        //    }
        //    @Override
        //    public void onNothingSelected(AdapterView<?> adapterView) {
//
        //    }
        //});
        btnMClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row=0;
                insertErr=0;
                insertSucc=0;
                btnMSave.setEnabled(true);
                txtMFoodsCode.setEnabled(true);
                btnMailarapAdd.setEnabled(true);




                lbMFoodsname.setFocusable(true);
                lorder.clear();
                txtMFoodsCode.setText("");
                txtMPassword.setText("");
                lbMFoodsname.setText("");
                lbMFoodsRemark.setText("");
                setlvOrder();

                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            }
        });
        btnMSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtMPassword.getText().toString().equals("")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MailarapOrderAdd.this);
                    builder1.setMessage("Password ไม่ได้ป้อน");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            txtMPassword.setSelection(0,txtMPassword.getText().length());
                            txtMPassword.setFocusable(true);
                        }
                    }).create().show();
                }else{
                    if(rs.chkPasswordOrder(txtMPassword.getText().toString())){
                        String lotID = UUID.randomUUID().toString();
                        String areacode = rs.getArea(cboArea.getSelectedItem().toString(),"code");
                        String tablecode = rs.getTable(cboTable.getSelectedItem().toString(),"code");
                        String tableid = rs.getTable(cboTable.getSelectedItem().toString(),"genid");
//                ItemData itemView = (ItemData) cboTable.getSelectedItem();
//                itemView.text
//                String tablecode = rs.getTable(itemView.text,"code");
                        String[] prn = new String[lorder.size()];
                        String user = rs.chkUserByPassword(txtMPassword.getText().toString());
                        row = lorder.size();
                        for(int i=0;i<lorder.size();i++){
                            Order ord = (Order)lorder.get(i);
                            if(rs.AccessMode.equals("Standalone")) {
                                jarr = daS.OrderInsert(String.valueOf(i+1),lotID, areacode,tablecode,ord.Qty, ord.FoodsCode,
                                        ord.FoodsName,ord.Remark,ord.ResCode, ord.Price, ord.PrinterName, ord.FoodsId, ord.StatusToGo,user,tableid, String.valueOf(txtMCntCust.getValue()));
                                chkInsert();
                            }else if(rs.AccessMode.equals("Internet")){
                                new insertOrder().execute(String.valueOf(i+1),lotID, areacode,tablecode,ord.Qty, ord.FoodsCode,
                                        ord.FoodsName,ord.Remark,ord.ResCode, ord.Price, ord.PrinterName, ord.FoodsId, ord.StatusToGo,user,tableid, String.valueOf(txtMCntCust.getValue()));
                            }else{
                                new insertOrder().execute(String.valueOf(i+1),lotID, areacode,tablecode,ord.Qty, ord.FoodsCode,
                                        ord.FoodsName,ord.Remark,ord.ResCode, ord.Price, ord.PrinterName, ord.FoodsId, ord.StatusToGo,user,tableid, String.valueOf(txtMCntCust.getValue()));
                            }

//                    pOE.runPrintReceiptSequenceEpson(cboArea.getSelectedItem().toString(),cboTable.getSelectedItem().toString(), ord.FoodsName,ord.Qty,ord.Remark);
                            if(ord.Remark.equals("")) ord.Remark="-";
                            prn[i] = ord.FoodsName+";"+ord.Qty+";"+ord.Remark+";";
                        }
                        if(rs.PrnO.equals("ON")){
                            pOE = new PrintOrderEpson(MailarapOrderAdd.this);
                            pOE.runPrintReceiptSequenceEpson(cboArea.getSelectedItem().toString(),cboTable.getSelectedItem().toString(), prn);
                            pOE = null;
                        }
                        setCboTable(tablecode);
                        setTable(tablecode);

                    }else{
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MailarapOrderAdd.this);
                        builder1.setMessage("Password ไม่ถูกต้อง");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtMPassword.setSelection(0,txtMPassword.getText().length());
                                txtMPassword.setFocusable(true);
                            }
                        }).create().show();
                    }
                }



















            }
        });
        txtMFoodsCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pageLoad=true;
                setlvFoods();
                //if(txtMFoodsCode.getText().length()>2){
                //    setlvFoods();
                    //lvMFoods.setVisibility(View.VISIBLE);
                    //lvMOrder.setVisibility(View.INVISIBLE);
                //}else{

                    //lvMFoods.setVisibility(View.INVISIBLE);
                    //lvMOrder.setVisibility(View.VISIBLE);
                //}
                pageLoad=false;
            }
        });
//        txtMFoodsCode.setOnClickListener(new View.OnClickListener() {
        lbMFoodsname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                pageLoad=true;
                pageSearchFoods=true;
                setShowBtnSave(false);
                txtMFoodsCode.setText("");
                setlvFoods();
            }
        });
        lvMOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(flagDel) return;

                if(!pageLoad){
                    pageLoad=true;
                    pageLoadFromlvmClick=true;
                    //foo = (Foods)lFoo.get(i);
                    //txtMFoodsCode.setText(foo.Code);
                    //lbMFoodsname.setText(foo.Name);
                    //String text = lvMOrder.getItemAtPosition(i).toString();
                    //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                    if(pageSearchFoods){
                        String[] txt = ((TextView)view).getText().toString().split(" ");
                        Foods foo1 = getFoods(txt[0].trim());
                        foo = lFoo.get(i);
                        txtMFoodsCode.setText(foo1.Code);
                        lbMFoodsname.setText(foo1.Name);
                        foo.ID = foo1.ID;
//                        foo.Name = foo1.Name;
                    }else{
                        if(!btnMSave.isEnabled()){
                            return;
                        }
                        flagDel = true;
                        rowDel = i;
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MailarapOrderAdd.this);
                        builder1.setMessage("ต้องการเยกเลิกรายการนี้.\n\nลำดับ "+(rowDel+1)+" รหัส "+txtMFoodsCode.getText().toString()+" "+ lbMFoodsname.getText().toString()+"\n");
                        builder1.setCancelable(true);
                        builder1.setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                                flagDel=false;
//                                Toast.makeText(MailarapOrderAdd.this,"You clicked no button",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                                flagDel = false;
                            }
                        });
                        builder1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                                lorder.remove(rowDel);
                                setlvOrder();
//                                flagDel=true;
                                Toast.makeText(MailarapOrderAdd.this,"ลบข้อมูลเรียบร้อย",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                                flagDel = false;
                            }
                        }).create().show();
                    }
//                    pageSearchFoods = false;
                    pageLoad=false;
                    pageLoadFromlvmClick=false;
                }
            }
        });

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);
        ArrayAdapter<String> adaTable = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboTable);
        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboArea);
        alvMOrder = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList1);
        alvMFoods = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayFoods);

        lvMOrder.setAdapter(alvMOrder);

        cboArea.setAdapter(adaArea);
        //alvMFoods = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lvMFoods);
        //adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        //lvFoods = (ListView)findViewById(R.genid.lvFoods);
        //lvFoods.setAdapter(adapter);
        daS = new DatabaseSQLi(this,"");
        if(rs.AccessMode.equals("Standalone")) {
            jarrF = daS.FoodsSelectAll();
            setControl();
        }else if(rs.AccessMode.equals("Internet")){
            new retrieveFoods().execute();
        }else{
            new retrieveFoods().execute();
        }

        setTheme();
        //lvMFoods.setVisibility(View.INVISIBLE);
        chkMInRes.setChecked(true);
        lvMOrder.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext(), lvMOrder));

        setCboTable("");
        getTable();
        pageLoad=false;
        txtMQty.setValue(1);
        txtMCntCust.setValue(1);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public Foods getFoods(String code){
        Foods foo1 = new Foods();
        for(int i=0;i<lFoo.size();i++){
            foo1 = lFoo.get(i);
            if(code.equals(foo1.Code)){
                txtMFoodsCode.setText(foo1.Code);
                //lbMFoodsname.setText(foo1.Name);
                foo.ID = foo1.ID;
                return foo1;
            }
        }
        return foo1;
    }
    private void getTable(){
        try {
            table1.clear();
            File file =getFileStreamPath("table.cnf");
            final int READ_BLOCK_SIZE = 100;
            FileInputStream fileIn=openFileInput("table.cnf");
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
                for(int i=0;i<p.length;i++){
                    table1.add(p[i].replace("\n",""));
                }
            }
            fileIn.close();
            rs.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setTable(String id){
        String table="";
        for(int i=0;i<table1.size();i++) {
            String[] bb = table1.get(i).split("=");
            if(bb[0].length()==0) continue;
            if(id.equals(bb[0])){
                table +=bb[0]+"=1;\n";
            }else{
                table +=bb[0]+"="+bb[1]+";\n";
            }
        }
        try {
            FileOutputStream outputStream;
//                    File file =getFileStreamPath("table.cnf");
            outputStream = openFileOutput("table.cnf", Context.MODE_PRIVATE);
//            outputStream = openFileOutput(file.getPath(), Context.MODE_PRIVATE);
            outputStream.write(table.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setCboTable(String id){
        Log.i("rs.sTable.size()",String.valueOf(rs.sTable.size()));
        if(rs.sTable==null) return;
        if(rs.sTable.size()==0) return;
        listTable.clear();
        getTable();
        String table="";

        for(int i=0;i<table1.size();i++){
            String[] aa = table1.get(i).split("=");
            if(aa[0].length()==0) continue;
            String[] bb = rs.sTable.get(i).split("@");
//            table +=aa[1]+"="+aa[3]+";\n";
            if(id.equals(aa[0])){
//                aa[3] = "1";
                listTable.add(new ItemData(bb[2],R.drawable.idel_red));
            }else{
                if(aa[1].equals("1")){
                    listTable.add(new ItemData(bb[2],R.drawable.idel_red));
                }else{
                    listTable.add(new ItemData(bb[2],R.drawable.idel_blue));
                }
            }
        }
        SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.spinner_layout,R.id.cbotxt,listTable);
        cboTable.setAdapter(adapter);
    }
    private void setTheme(){
        lbMFoodsname.setTextSize(textSize);
        lbMFoodsRemark.setTextSize(textSize);
        lbMQty.setTextSize(textSize);
        txtMFoodsCode.setTextSize(textSize);
        txtMFoodsRemark.setTextSize(textSize);
        txtMPassword.setTextSize(textSize);
        btnMSave.setTextSize(textSize);
        btnMClear.setTextSize(textSize);
        btnMailarapAdd.setTextSize(textSize);
        lbMQty.setTextSize(textSize);



//        lbMToGo.setTextSize(textSize);
//        chkMToGo.setTextSize(textSize);
//        chkMInRes.setTextSize(textSize);
        alvMOrder = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList1);
    }
    private void setShowBtnSave(Boolean chk){
        if(chk){
            txtMPassword.setVisibility(View.VISIBLE);
            btnMSave.setVisibility(View.VISIBLE);
        }else{
            txtMPassword.setVisibility(View.INVISIBLE);
            btnMSave.setVisibility(View.INVISIBLE);
        }
    }

    private void setLFoods(){
        lFoo.clear();
        try {
            for (int i = 0; i < jarrF.length(); i++) {
                JSONObject catObj = (JSONObject) jarrF.get(i);
                Foods f = new Foods();
                f.ID = catObj.getString(f.dbID);
                f.Code = catObj.getString(f.dbCode);
                f.Name = catObj.getString(f.dbName);
                f.Remark = catObj.getString(f.dbRemark);
                f.ResCode = catObj.getString(f.dbResCode);
                f.Price = catObj.getString(f.dbPrice);
                f.PrinterName = catObj.getString(f.dbPrinterName);
                f.Active = catObj.getString(f.dbActive);
                f.ResId = catObj.getString(f.dbResId);
                f.StatusFoods = catObj.getString(f.dbStatusFoods);
                f.TypeId = catObj.getString(f.dbTypeId);
                lFoo.add(f);
                arrayFoods.add(f.Code + " " + f.Name + " " + f.Price + " " + f.Remark + " " + f.PrinterName);
                arraycboMFoods.add(f.Code + " " + f.Name + " " + f.Price + " " + f.Remark + " " + f.PrinterName);
            }
            //adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        } catch (JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setLFoods ",e.getMessage());
        }
    }
    private void setlvFoods(){
        Log.d("setlvFoods ","pageLoadFromlvmClick "+pageLoadFromlvmClick);
        if(!pageLoadFromlvmClick){
            Log.d("lFoo ",String.valueOf(lFoo.size()));
            lvMOrder.setBackgroundColor(getResources().getColor(R.color.BackScreenSearch));
            arrayFoods.clear();
            for(int i=0;i<lFoo.size();i++){
                foo = (Foods)lFoo.get(i);
                if(foo.Code.indexOf(txtMFoodsCode.getText().toString())>=0){
                    arrayFoods.add(foo.Code + " " + foo.Name + " " + foo.Price + " " + foo.Remark + " " + foo.PrinterName);
                }
            }
            alvMFoods = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayFoods){
                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    // Get the current item from ListView
                    View view = super.getView(position,convertView,parent);
                    // Get the Layout Parameters for ListView Current Item View
                    ViewGroup.LayoutParams params = view.getLayoutParams();
                    // Set the height of the Item View
                    params.height = 40;
                    view.setLayoutParams(params);
                    TextView textView = (TextView) super.getView(position, convertView, parent);
                    textView.setTextSize(textSize);
                    textView.setHeight(40);
                    textView.setTextColor(Color.RED);
                    return textView;
                }
            };
            lvMOrder.setAdapter(alvMFoods);
        }
    }
    private void setCboMFoods(){
        //acboMFoods = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,arraycboMFoods);
        //cboMFoods.setAdapter(acboMFoods);
    }
    private void setControl(){
        pageLoad=true;
        setLFoods();
//        setCboMFoods();
        //pageLoad=false;
    }
    private void setlvOrder(){
        lvMOrder.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));
        arrayList1.clear();
        alRemark.clear();
        alCode.clear();
        alName.clear();
        alPrice.clear();
        alQty.clear();
        for(int i=0;i<lorder.size();i++){
            Order o = new Order();
            o = (Order)lorder.get(i);
            //arrayList1.add((i+1)+" "+o.FoodsCode+" "+o.FoodsName+" "+getResources().getString(R.string.price)+"("+o.Price+") "+getResources().getString(R.string.qty)+"("+o.Qty+") ");
            alCode.add(o.FoodsCode);
            alName.add(o.FoodsName);
            alPrice.add(o.Price);
            alQty.add(o.Qty);
            if(o.Remark.equals("")){
                alRemark.add("-");
            }else{
                alRemark.add(o.Remark);
            }

        }
//        alvMOrder = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_layout, arrayList1){
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent){
//                // Get the current item from ListView
//                View view = super.getView(position,convertView,parent);
//                // Get the Layout Parameters for ListView Current Item View
//                ViewGroup.LayoutParams params = view.getLayoutParams();
//                //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                // Set the height of the Item View
//                params.height = 40;
//                view.setLayoutParams(params);
//                TextView textView = (TextView) super.getView(position, convertView, parent);
//                textView.setTextSize(textSize);
//                textView.setHeight(40);
//                return textView;
//            }
//        };
//
//        lvMOrder.setAdapter(alvMOrder);

        ListViewOrderAdapter lva = new ListViewOrderAdapter(getApplicationContext(),alCode,alName,alPrice,alQty,alRemark);
        lvMOrder.setAdapter(lva);
    }
    private Boolean setOrder(){
        if(txtMQty.getValue()<=0){
            return false;
        }
        row1++;
        //String lotID = UUID.randomUUID().toString();

        Order ord = new Order();
        ord.ID="";
        ord.LotId=lotID;
        ord.Active="1";
        ord.FoodsCode = txtMFoodsCode.getText().toString();
        ord.FoodsName = lbMFoodsname.getText().toString();
        ord.Qty = String.valueOf(txtMQty.getValue());
        ord.row1 = row1.toString();
        ord.FoodsId = foo.ID;
        ord.ResCode = foo.ResCode;
        ord.PrinterName = foo.PrinterName;
        ord.Remark = txtMFoodsRemark.getText().toString();
        ord.Price = foo.Price;
        if(chkMInRes.isChecked()){
            ord.StatusToGo ="1";
        }else{
            ord.StatusToGo ="2";
        }
        //?chkMInRes.isChecked()==true, ord.StatusToGo = "";
        lorder.add(ord);
        return true;
    }

    @Override
    public void onPtrReceive(Printer printer, int i, PrinterStatusInfo printerStatusInfo, String s) {

    }

    class retrieveFoods extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //params.add(new BasicNameValuePair("Code",Code));
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoods,params);

            //rs.jarrF = jarrF.toString();
            //} catch (JSONException e) {
            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setControl();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    class retrieveFoodsByCode extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(foo.dbCode,Code));
            //jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoodsByCode,params);
            jarrF = jsonparser.getJSONFromUrl(rs.hostFoodsSearch,params);
            //rs.jarrF = jarrF.toString();
            //} catch (JSONException e) {
            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setFoods();
            //lbMFoodsname.setText(foo.Name);
            lbMFoodsname.setBackgroundColor(getResources().getColor(R.color.BackScreenSearchOK));
        }
        @Override
        protected void onPreExecute() {

        }
    }
    private void setFoods(){
        try{
            JSONObject catObj = (JSONObject) jarrF.get(0);
            foo.ID = catObj.getString(foo.dbID);
            foo.Code = catObj.getString(foo.dbCode);
            foo.Name = catObj.getString(foo.dbName);
            foo.Remark = catObj.getString(foo.dbRemark);
            foo.ResCode = catObj.getString(foo.dbResCode);
            foo.Price = catObj.getString(foo.dbPrice);
            foo.PrinterName = catObj.getString(foo.dbPrinterName);
            foo.Active = catObj.getString(foo.dbActive);
            foo.ResId = catObj.getString(foo.dbResId);
            foo.StatusFoods = catObj.getString(foo.dbStatusFoods);
            foo.TypeId = catObj.getString(foo.dbTypeId);
        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("MailarapOrderAdd",e.getMessage());
        }
    }
    class insertOrder extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                //new insertOrder().execute(String.valueOf(i),lotID, areacode,tablecode,String.valueOf(txtMQty.getValue()), txtMFoodsCode.getText().toString(),
                //        lbMFoodsname.getText().toString(),txtMFoodsRemark.getText().toString(),ord.ResCode, ord.Price, ord.PrinterName);
                //int row = Integer.parseInt(arg0[0]);
                //Order ord = (Order)lorder.get(row);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));

                params.add(new BasicNameValuePair(or.dbLotId, arg0[1]));
                params.add(new BasicNameValuePair(or.dbFoodsCode, arg0[5]));
                params.add(new BasicNameValuePair(or.dbFoodsName, arg0[6]));
                params.add(new BasicNameValuePair(or.dbFoodsId, arg0[11]));
                params.add(new BasicNameValuePair(or.dbrow1, arg0[0]));
                params.add(new BasicNameValuePair(or.dbActive, "1"));
                params.add(new BasicNameValuePair(or.dbResCode, arg0[8]));
                params.add(new BasicNameValuePair(or.dbTableCode, arg0[3]));
                params.add(new BasicNameValuePair(or.dbAreaCode, arg0[2]));
                params.add(new BasicNameValuePair(or.dbRemark, arg0[7]));
                params.add(new BasicNameValuePair(or.dbPrice, arg0[9]));
                params.add(new BasicNameValuePair(or.dbQty, arg0[4]));
                params.add(new BasicNameValuePair(or.dbPrinterName, arg0[10]));
                params.add(new BasicNameValuePair(or.dbStatusFoods1, ""));
                params.add(new BasicNameValuePair(or.dbStatusFoods2, ""));
                params.add(new BasicNameValuePair(or.dbStatusFoods3, ""));
                params.add(new BasicNameValuePair(or.dbStatusToGo, arg0[12]));
                params.add(new BasicNameValuePair(or.dbOrderUser, arg0[13]));
                params.add(new BasicNameValuePair(or.dbTableId, arg0[14]));
                params.add(new BasicNameValuePair(or.dbCntCust, arg0[15].equals("")?"1":arg0[15]));

                jarr = jsonparser.getJSONFromUrl(rs.hostSaveOrder, params);

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
            return arg0[8]+";"+arg0[3]+";"+arg0[6]+";"+arg0[4]+";"+arg0[7];
        }
        @Override
        protected void onPostExecute(String ab1){
            String[] aaa = ab1.split(";");
            chkInsert();

            //new retrieveFoods1().execute();
//            if(aaa.length>=5){
//                runPrintReceiptSequenceEpson(rs.getAreaToName(aaa[0],"code"),rs.getTableToName(aaa[1],"code"), aaa[2],aaa[3],aaa[4]);
//            }else{
//                runPrintReceiptSequenceEpson(rs.getAreaToName(aaa[0],"code"),rs.getTableToName(aaa[1],"code"), aaa[2],aaa[3],"");
//            }

//            pOE.runPrintReceiptSequenceEpson(cboArea.getSelectedItem().toString(),cboTable.getSelectedItem().toString(), arg0[6],arg0[4],arg0[7]);
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    private void chkInsert(){
        for (int i = 0; i < jarr.length(); i++) {
            try {
                JSONObject catObj = (JSONObject) jarr.get(i);
                Log.d("sql",catObj.getString("sql"));
                if(!catObj.getString("success").equals("1")){
                    insertErr++;
                }else{
                    insertSucc++;
                }
            } catch (JSONException e) {

            }
        }
        if((insertSucc+insertErr)==row){

            AlertDialog.Builder builder1 = new AlertDialog.Builder(MailarapOrderAdd.this);
            builder1.setMessage("บันทึกข้อมูลทั้งหมด"+(insertSucc+insertErr)+"รายการ  เรียบร้อย");
            builder1.setCancelable(true);
            builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    lvMOrder.setBackgroundColor(Color.GRAY);
                    btnMSave.setEnabled(false);
                    txtMFoodsCode.setEnabled(false);
                    txtMFoodsCode.setText("");
                    txtMPassword.setText("");
                    lbMFoodsname.setText("");
                    lbMFoodsRemark.setText("");
                    btnMClear.setVisibility(View.VISIBLE);
                    btnMailarapAdd.setEnabled(false);
                    lorder.clear();
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                }
            }).create().show();
        }
    }
    public class retrieveTable extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                jarrT = jsonparser.getJSONFromUrl(rs.hostGetTable,params);
                if(jarrT!=null){
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    rs.sCboTable.clear();
                    rs.sTable.clear();
                    for (int i = 0; i < jarrT.length(); i++) {
                        JSONObject catObj = (JSONObject) jarrT.get(i);
                        rs.sCboTable.add(catObj.getString(ta.dbName));
                        rs.sTable.add(catObj.getString(ta.dbID)+"@"+catObj.getString(ta.dbCode)+"@"+catObj.getString(ta.dbName)+"@"+catObj.getString(ta.dbStatusUse));
                    }
                }
            } catch (JSONException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){

        }
        @Override
        protected void onPreExecute() {

        }
    }

//    public class ListArrayAdapter extends ArrayAdapter<String> {
//        private final Context context;
////        private final String[] values;
//        private final ArrayList<String> values;
//
//        public ListArrayAdapter(Context context, ArrayList<String> values) {
//            super(context, -1, values);
//            this.context = context;
//            this.values = values;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View rowView = inflater.inflate(R.layout.listview_layout, parent, false);
//            TextView fLine = (TextView) rowView.findViewById(R.genid.fLine);
//            TextView sLine = (TextView) rowView.findViewById(R.genid.sLine);
//            ImageView imageView = (ImageView) rowView.findViewById(R.genid.icon);
////            fLine.setText(values[position]);
//            fLine.setText(values.get(position));
//            // change the icon for Windows and iPhone
////            String s = values[position];
//            String s = values.get(position);
//            if (s.startsWith("iPhone")) {
//                imageView.setImageResource(R.drawable.idel_blue);
//            } else {
//                imageView.setImageResource(R.drawable.idel_red);
//            }
//
//            return rowView;
//        }
//    }
}
