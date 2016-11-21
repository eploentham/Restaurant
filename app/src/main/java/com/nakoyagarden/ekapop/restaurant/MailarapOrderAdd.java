package com.nakoyagarden.ekapop.restaurant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.epson.epos2.Epos2Exception;
import com.epson.epos2.printer.Printer;
import com.epson.epos2.printer.PrinterStatusInfo;
import com.epson.epos2.printer.ReceiveListener;

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
public class MailarapOrderAdd  extends Activity implements ReceiveListener {
    Spinner cboTable, cboArea;
    TextView lbMFoodsname,lbMFoodsRemark, lbMQty, lbMToGo;
    EditText txtMFoodsCode, txtMFoodsRemark;
    Button btnMailarapAdd, btnMSave;
    RadioButton chkMToGo, chkMInRes;
//    SwipeListView sv;
    com.hrules.horizontalnumberpicker.HorizontalNumberPicker txtMQty;

    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    JSONArray jarr;
    public RestaurantControl rs;
    private PrintOrderEpson pOE;
    public ArrayList<String> sCboTable = new ArrayList<String>();
    public ArrayList<String> sCboArea = new ArrayList<String>();
    public ArrayList<Order> lorder = new ArrayList<Order>();
    public ArrayList<Foods> lFoo = new ArrayList<Foods>();
    ListView lvMOrder;
    private ArrayAdapter<String> acboMFoods, alvMOrder, alvMFoods;
    private ArrayList<String> arrayList1, arrayFoods, arraycboMFoods, alRemark, alCode, alName, alPrice, alQty;
    JSONArray jarrT;
    Foods foo = new Foods();
    String ab, Code="";
    Integer row1=0;
    Boolean pageLoad=false, pageLoadFromlvmClick=false, pageSearchFoods=true,flagDel=false;
    String lotID="";
    int textSize=20,textSize1=18, row,rowDel=0;

//    private Printer mPrinter = null;
//    private Context mContext = null;
//    MainActivity ma;

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

        GridLayout linearLayout = (GridLayout) findViewById(R.id.layoutMailarapOrder);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));

        cboTable = (Spinner)findViewById(R.id.cboMailarapTable);
        cboArea = (Spinner)findViewById(R.id.cboMailarapArea);
        //cboMFoods = (Spinner) findViewById(R.id.cboMFoods);
        lbMFoodsname = (TextView)findViewById(R.id.lbMFoodsName1);
        lbMFoodsRemark = (TextView)findViewById(R.id.lbFoodsRemark);
        lbMQty = (TextView)findViewById(R.id.lbMQty);
        txtMFoodsCode = (EditText) findViewById(R.id.txtMFoodsCode);
        txtMFoodsRemark = (EditText)findViewById(R.id.txtMFoodsRemark);
        txtMQty = (com.hrules.horizontalnumberpicker.HorizontalNumberPicker)findViewById(R.id.txtMQty);
        lvMOrder = (ListView)findViewById(R.id.lvMOrder);
        chkMToGo = (RadioButton) findViewById(R.id.chkMToGo);
        chkMInRes = (RadioButton) findViewById(R.id.chkMInRes);
        lbMToGo = (TextView) findViewById(R.id.lbMToGo);

        lbMQty.setText(R.string.code);
        lbMFoodsRemark.setText(R.string.remark);
        chkMInRes.setText(R.string.inres);
        chkMToGo.setText(R.string.togo);
        lbMToGo.setText(R.string.active);



        btnMailarapAdd = (Button)findViewById(R.id.btnMailarapAdd);
        btnMSave = (Button)findViewById(R.id.btnMSave);
        btnMSave.setText(R.string.save);
        btnMailarapAdd.setText(getResources().getString(R.string.order1)+" "+getResources().getString(R.string.foods));
        btnMailarapAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //arrayList.add("55");
                if(setOrder()){
                    setlvOrder();
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
        btnMSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lotID = UUID.randomUUID().toString();
                String areacode = rs.getArea(cboArea.getSelectedItem().toString(),"code");
                String tablecode = rs.getTable(cboTable.getSelectedItem().toString(),"code");
//                ItemData itemView = (ItemData) cboTable.getSelectedItem();
//                itemView.text
//                String tablecode = rs.getTable(itemView.text,"code");
                String[] prn = new String[lorder.size()];
                for(int i=0;i<lorder.size();i++){
                    Order ord = (Order)lorder.get(i);
                    new insertOrder().execute(String.valueOf(i+1),lotID, areacode,tablecode,ord.Qty, ord.FoodsCode,
                            ord.FoodsName,ord.Remark,ord.ResCode, ord.Price, ord.PrinterName, ord.FoodsId, ord.StatusToGo);
//                    pOE.runPrintReceiptSequenceEpson(cboArea.getSelectedItem().toString(),cboTable.getSelectedItem().toString(), ord.FoodsName,ord.Qty,ord.Remark);
                    if(ord.Remark.equals("")) ord.Remark="-";
                    prn[i] = ord.FoodsName+";"+ord.Qty+";"+ord.Remark+";";
                }
                pOE = new PrintOrderEpson(MailarapOrderAdd.this);
                pOE.runPrintReceiptSequenceEpson(cboArea.getSelectedItem().toString(),cboTable.getSelectedItem().toString(), prn);
                pOE = null;

                AlertDialog.Builder builder1 = new AlertDialog.Builder(MailarapOrderAdd.this);
                builder1.setMessage("บันทึกข้อมูลเรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnMSave.setEnabled(false);
                    }
                }).create().show();
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
        txtMFoodsCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageLoad=true;
                pageSearchFoods=true;
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
                        txtMFoodsCode.setText(foo1.Code);
                        lbMFoodsname.setText(foo1.Name);
                        foo.ID = foo1.ID;
                    }else{
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
                    pageSearchFoods = false;
                    pageLoad=false;
                    pageLoadFromlvmClick=false;
                }
            }
        });

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        ArrayAdapter<String> adaTable = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboTable);
        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboArea);
        alvMOrder = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList1);
        alvMFoods = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayFoods);

        lvMOrder.setAdapter(alvMOrder);

        cboArea.setAdapter(adaArea);
        //alvMFoods = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lvMFoods);
        //adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        //lvFoods = (ListView)findViewById(R.id.lvFoods);
        //lvFoods.setAdapter(adapter);
        new retrieveFoods().execute();
        setTheme();
        //lvMFoods.setVisibility(View.INVISIBLE);
        chkMInRes.setChecked(true);
        lvMOrder.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext(), lvMOrder));

        ArrayList<ItemData> list=new ArrayList<>();
        for(int i=0;i<rs.sTable.size();i++){
            String[] aa = rs.sTable.get(i).split("@");
            if(aa[3].equals("1")){
                list.add(new ItemData(aa[2],R.drawable.idel_red));
            }else{
                list.add(new ItemData(aa[2],R.drawable.idel_blue));
            }
        }
        SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.spinner_layout,R.id.cbotxt,list);
        cboTable.setAdapter(adapter);
        pageLoad=false;
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
    private void setTheme(){
        lbMFoodsname.setTextSize(textSize);
        lbMFoodsRemark.setTextSize(textSize);
        lbMQty.setTextSize(textSize);
        txtMFoodsCode.setTextSize(textSize);
        txtMFoodsRemark.setTextSize(textSize);
        lbMToGo.setTextSize(textSize);
        chkMToGo.setTextSize(textSize);
        chkMInRes.setTextSize(textSize);
        alvMOrder = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList1);

    }

    private void setLFoods(){
        lFoo.clear();
        try {
            for (int i = 0; i < jarrF.length(); i++) {
                JSONObject catObj = (JSONObject) jarrF.get(i);
                Foods f = new Foods();
                f.ID = catObj.getString("foods_id");
                f.Code = catObj.getString("foods_code");
                f.Name = catObj.getString("foods_name");
                f.Remark = catObj.getString("remark");
                f.ResCode = catObj.getString("res_code");
                f.Price = catObj.getString("foods_price");
                f.PrinterName = catObj.getString("printer_name");
                f.Active = catObj.getString("active");
                f.ResId = catObj.getString("res_id");
                f.StatusFoods = catObj.getString("status_foods");
                f.TypeId = catObj.getString("foods_type_id");
                lFoo.add(f);
                arrayFoods.add(f.Code + " " + f.Name + " " + f.Price + " " + f.Remark + " " + f.PrinterName);
                arraycboMFoods.add(f.Code + " " + f.Name + " " + f.Price + " " + f.Remark + " " + f.PrinterName);
            }
            //adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        } catch (JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void setlvFoods(){
        if(!pageLoadFromlvmClick){
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
        setCboMFoods();
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
            //List<NameValuePair> params = new ArrayList<NameValuePair>();
            //params.add(new BasicNameValuePair("Code",Code));
            jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoods,new ArrayList<NameValuePair>());

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
            params.add(new BasicNameValuePair("Code",Code));
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
            foo.ID = catObj.getString("foods_id");
            foo.Code = catObj.getString("foods_code");
            foo.Name = catObj.getString("foods_name");
            foo.Remark = catObj.getString("remark");
            foo.ResCode = catObj.getString("res_code");
            foo.Price = catObj.getString("foods_price");
            foo.PrinterName = catObj.getString("printer_name");
            foo.Active = catObj.getString("active");
            foo.ResId = catObj.getString("res_id");
            foo.StatusFoods = catObj.getString("status_foods");
            foo.TypeId = catObj.getString("foods_type_id");
        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
                params.add(new BasicNameValuePair("lot_id", arg0[1]));
                params.add(new BasicNameValuePair("foods_code", arg0[5]));
                params.add(new BasicNameValuePair("foods_name", arg0[6]));
                params.add(new BasicNameValuePair("foods_id", arg0[11]));
                params.add(new BasicNameValuePair("row1", arg0[0]));
                params.add(new BasicNameValuePair("Active", "1"));
                params.add(new BasicNameValuePair("res_code", arg0[8]));
                params.add(new BasicNameValuePair("table_code", arg0[3]));
                params.add(new BasicNameValuePair("area_code", arg0[2]));
                params.add(new BasicNameValuePair("remark", arg0[7]));
                params.add(new BasicNameValuePair("price", arg0[9]));
                params.add(new BasicNameValuePair("qty", arg0[4]));
                params.add(new BasicNameValuePair("printer_name", arg0[10]));
                params.add(new BasicNameValuePair("status_foods_1", ""));
                params.add(new BasicNameValuePair("status_foods_2", ""));
                params.add(new BasicNameValuePair("status_foods_3", ""));
                params.add(new BasicNameValuePair("status_to_go", arg0[12]));

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
    public class retrieveTable extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                jarrT = jsonparser.getJSONFromUrl(rs.hostGetTable,params);
                if(jarrT!=null){
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    rs.sCboTable.clear();
                    rs.sTable.clear();
                    for (int i = 0; i < jarrT.length(); i++) {
                        JSONObject catObj = (JSONObject) jarrT.get(i);
                        rs.sCboTable.add(catObj.getString("table_name"));
                        rs.sTable.add(catObj.getString("table_id")+"@"+catObj.getString("table_code")+"@"+catObj.getString("table_name")+"@"+catObj.getString("status_use"));
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
//            TextView fLine = (TextView) rowView.findViewById(R.id.fLine);
//            TextView sLine = (TextView) rowView.findViewById(R.id.sLine);
//            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
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
