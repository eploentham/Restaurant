package com.nakoyagarden.ekapop.restaurant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by ekapop on 12/29/2016.
 */

public class DatabaseSQLi extends SQLiteOpenHelper {
    private static final String DB_NAME = "restaurant";
    private static final int DB_VERSION = 1;
    private String creSQLi="";
    Database da = new Database();
    Restaurant res = new Restaurant();
    Area ar = new Area();
    Table ta = new Table();
    Foods foo = new Foods();
    FoodsType ft = new FoodsType();
    User us = new User();
    Order or = new Order();
    Bill bi = new Bill();
    BillDetail bid = new BillDetail();
    CloseDay cd = new CloseDay();

    Context c;
    SQLiteDatabase mDb;
    String hostID="";
    String gendate ="strftime('%Y-%m-%d %H:%M:%S','now')";
    String genid = "lower(hex(randomblob(16)))";

    public DatabaseSQLi(Context context, String hostID) {
        super(context, DB_NAME, null, DB_VERSION);
        c= context;
        this.hostID = hostID;
    }
    public void onCreate(SQLiteDatabase db) {
        creSQLi = "";
        createDatabase();
        String cAr="";
        String resID = UUID.randomUUID().toString();
        String typeID1 = UUID.randomUUID().toString();
        String typeID2 = UUID.randomUUID().toString();
        String arID = UUID.randomUUID().toString();

        cAr=da.creaT+" "+da.tbNameRes+" "
                +"( "+ar.dbID+"' "+da.tex+" PRIMARY KEY "
                +", "+ar.dbCode+"' "+da.tex+"  NULL "
                +", '"+ar.dbName+"' "+da.tex+"  NULL "
                +", '"+ar.dbRemark+"' "+da.tex+"  NULL "
                +", '"+ar.dbActive+"' "+da.tex+"  NULL "
                +", '"+ar.dbSort1+"' "+da.tex+"  NULL "
                +") ";
        try{
            Log.d("onCreate ",ar.cAreaSQLi);
            db.execSQL(ar.cDropArea);
            db.execSQL(ar.cAreaSQLi);
            db.execSQL("Insert Into "+da.tbNameArea+" ("+ar.dbID+","+ar.dbCode+", "+ar.dbName+", "+ar.dbActive+", "+ar.dbDateCreate+","+ar.dbHostId+") Values('"+arID+"','01', 'ในร้าน','1',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",ta.cTableSQLi);
            db.execSQL(ta.cDropTable);
            db.execSQL(ta.cTableSQLi);
            db.execSQL("Insert Into "+da.tbNameTable+" ("+ta.dbID+", "+ta.dbCode+", "+ta.dbName+", "+ta.dbActive+","
                    +ta.dbStatusUse+","+ta.dbAreaID+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'100', 'โต๊ะ 1','1','0','"+arID+"',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameTable+" ("+ta.dbID+", "+ta.dbCode+", "+ta.dbName+", "+ta.dbActive+","
                    +ta.dbStatusUse+","+ta.dbAreaID+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'101', 'โต๊ะ 2','1','0','"+arID+"',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",res.cResSQLi);
            db.execSQL(res.cDropRes);
            db.execSQL(res.cResSQLi);
            db.execSQL("Insert Into "+da.tbNameRes+" ("+res.dbID+", "+res.dbCode+", "+res.dbName+", "+res.dbActive+","
                    +res.dbDefaultRes+","+res.dbRH1+","+res.dbRH2+","+res.dbRF1+","
                    +res.dbRF2+","+res.dbBillCode+","+res.dbTaxID+", "+ar.dbDateCreate+","+ar.dbHostId+") Values('"+resID+"','01', 'ร้านอาหาร','1','1','','','','','','',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",us.cUserSQLi);
            db.execSQL(us.cDropUser);
            db.execSQL(us.cUserSQLi);
            db.execSQL("Insert Into "+da.tbNameUser+" ("+us.dbID+", "+us.dbLogin+", "+us.dbName+", "+us.dbActive+","
                    +us.dbPrivilege+","+us.dbVoidBill+","+us.dbVoidCloseday+","+us.dbPassword1+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'admin', 'admin','1','1','1','1','1618',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",foo.cFoodsSQLi);
            db.execSQL(foo.cDropFoods);
            db.execSQL(foo.cFoodsSQLi);
            db.execSQL("Insert Into "+da.tbNameFoods+" ("+foo.dbID+", "+foo.dbCode+", "+foo.dbName+", "+foo.dbPrice+", "
                    +foo.dbActive+", "+foo.dbTypeId+", "+foo.dbResId+", "+foo.dbResCode+", "
                    +foo.dbStatusFoods+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'0101', 'อาหาร1',45,'1','"+typeID1+"','"+resID+"','10','1',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameFoods+" ("+foo.dbID+", "+foo.dbCode+", "+foo.dbName+", "+foo.dbPrice+", "
                    +foo.dbActive+", "+foo.dbTypeId+", "+foo.dbResId+", "+foo.dbResCode+", "
                    +foo.dbStatusFoods+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'0102', 'อาหาร2',45,'1','"+typeID1+"','"+resID+"','10','1',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameFoods+" ("+foo.dbID+", "+foo.dbCode+", "+foo.dbName+", "+foo.dbPrice+", "
                    +foo.dbActive+", "+foo.dbTypeId+", "+foo.dbResId+", "+foo.dbResCode+", "
                    +foo.dbStatusFoods+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'0201', 'อาหาร3',50,'1','"+typeID2+"','"+resID+"','10','1',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameFoods+" ("+foo.dbID+", "+foo.dbCode+", "+foo.dbName+", "+foo.dbPrice+", "
                    +foo.dbActive+", "+foo.dbTypeId+", "+foo.dbResId+", "+foo.dbResCode+", "
                    +foo.dbStatusFoods+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'0202', 'อาหาร4',50,'1','"+typeID2+"','"+resID+"','10','1',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",ft.cFoodsTypeSQLi);
            db.execSQL(ft.cDropFoodsType);
            db.execSQL(ft.cFoodsTypeSQLi);
            db.execSQL("Insert Into "+da.tbNameFoodsType+" ("+ft.dbID+", "+ft.dbCode+", "+ft.dbName+", "+ft.dbActive+", "+ar.dbDateCreate+","+ar.dbHostId+") Values('"+typeID1+"','01', 'ประเภทอาหาร1','1',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameFoodsType+" ("+ft.dbID+", "+ft.dbCode+", "+ft.dbName+", "+ft.dbActive+", "+ar.dbDateCreate+","+ar.dbHostId+") Values('"+typeID2+"','02', 'ประเภทอาหาร2','1',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",bi.cBillSQLi);
            db.execSQL(bi.cDropBill);
            db.execSQL(bi.cBillSQLi);

            Log.d("onCreate ",bid.cBillDetailSQLi);
            db.execSQL(bid.cDropBillDetail);
            db.execSQL(bid.cBillDetailSQLi);

            Log.d("onCreate ",or.cOrderSQLi);
            db.execSQL(or.cDropOrder);
            db.execSQL(or.cOrderSQLi);


            Log.d("onCreate ",cd.cClosedaySQLi);
            db.execSQL(cd.cDropCloseday);
            db.execSQL(cd.cClosedaySQLi);
        }catch(Exception e){
            Log.e("onCreate ",e.getMessage());
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }
    public void createDatabase() {
        String url = "/data/data/" + c.getPackageName() + "/databases/"+da.dbNameD;
        File f = new File(url);
        Log.d("createDatabase ",url);
        if(!f.exists()) {
            try {
//                mHelper = new MyDbHelper(this);
                mDb = getWritableDatabase();
                mDb.close();
//                mHelper.close();
                InputStream in = c.getAssets().open(da.dbNameD);
                OutputStream out = new FileOutputStream(url);
                byte[] buffer = new byte[in.available()];
                in.read(buffer);
                out.write(buffer, 0, buffer.length);
                in.close();
                out.close();
                Log.d("createDatabase ","OK");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e("createDatabase ",e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("createDatabase ",e.getMessage());
            }
        }
    }
    public JSONArray TableSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameTable+" Where "+ta.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
                jarr.put(getJsonObjectTable(c));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray TableSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameTable+" Where "+ta.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
                //Do something Here with values
                jarr.put(getJsonObjectTable(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    private JSONObject getJsonObjectTable(Cursor c){
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(ta.dbID, c.getString(c.getColumnIndex(ta.dbID)));
            jsonObj.put(ta.dbAreaID, chkNull(c.getString(c.getColumnIndex(ta.dbAreaID)))?"":c.getString(c.getColumnIndex(ta.dbAreaID)));
            jsonObj.put(ta.dbCode, chkNull(c.getString(c.getColumnIndex(ta.dbCode)))?"":c.getString(c.getColumnIndex(ta.dbCode)));
            jsonObj.put(ta.dbName, chkNull(c.getString(c.getColumnIndex(ta.dbName)))?"":c.getString(c.getColumnIndex(ta.dbName)));
            jsonObj.put(ta.dbActive, chkNull(c.getString(c.getColumnIndex(ta.dbActive)))?"":c.getString(c.getColumnIndex(ta.dbActive)));
            jsonObj.put(ta.dbRemark, chkNull(c.getString(c.getColumnIndex(ta.dbRemark)))?"":c.getString(c.getColumnIndex(ta.dbRemark)));
            jsonObj.put(ta.dbSort1, chkNull(c.getString(c.getColumnIndex(ta.dbSort1)))?"":c.getString(c.getColumnIndex(ta.dbSort1)));
            jsonObj.put(ta.dbDateCreate, chkNull(c.getString(c.getColumnIndex(ta.dbDateCreate)))?"":c.getString(c.getColumnIndex(ta.dbDateCreate)));
            jsonObj.put(ta.dbDateModi, chkNull(c.getString(c.getColumnIndex(ta.dbDateModi)))?"":c.getString(c.getColumnIndex(ta.dbDateModi)));
            jsonObj.put(ta.dbStatusUse, chkNull(c.getString(c.getColumnIndex(ta.dbStatusUse)))?"":c.getString(c.getColumnIndex(ta.dbStatusUse)));
            jsonObj.put(ta.dbDateUse, chkNull(c.getString(c.getColumnIndex(ta.dbDateUse)))?"":c.getString(c.getColumnIndex(ta.dbDateUse)));
            jsonObj.put(ta.dbHostId, chkNull(c.getString(c.getColumnIndex(ta.dbHostId)))?"":c.getString(c.getColumnIndex(ta.dbHostId)));
            jsonObj.put(ta.dbBranchId, chkNull(c.getString(c.getColumnIndex(ta.dbBranchId)))?"":c.getString(c.getColumnIndex(ta.dbBranchId)));
            jsonObj.put(ta.dbDeviceId, chkNull(c.getString(c.getColumnIndex(ta.dbDeviceId)))?"":c.getString(c.getColumnIndex(ta.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TableSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("TableSelectAll ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray AreaSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameArea+" Where "+ar.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectArea(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectArea(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }

    @NonNull
    private JSONObject getJsonObjectArea(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(ar.dbID, c.getString(c.getColumnIndex(ar.dbID)));
            jsonObj.put(ar.dbCode, chkNull(c.getString(c.getColumnIndex(ar.dbCode)))?"":c.getString(c.getColumnIndex(ar.dbCode)));
            jsonObj.put(ar.dbName, chkNull(c.getString(c.getColumnIndex(ar.dbName)))?"":c.getString(c.getColumnIndex(ar.dbName)));
            jsonObj.put(ar.dbActive, chkNull(c.getString(c.getColumnIndex(ar.dbActive)))?"":c.getString(c.getColumnIndex(ar.dbActive)));
            jsonObj.put(ar.dbRemark, chkNull(c.getString(c.getColumnIndex(ar.dbRemark)))?"":c.getString(c.getColumnIndex(ar.dbRemark)));
            jsonObj.put(ar.dbSort1, chkNull(c.getString(c.getColumnIndex(ar.dbSort1)))?"":c.getString(c.getColumnIndex(ar.dbSort1)));
            jsonObj.put(ar.dbDateCreate, chkNull(c.getString(c.getColumnIndex(ar.dbDateCreate)))?"":c.getString(c.getColumnIndex(ar.dbDateCreate)));
            jsonObj.put(ar.dbDateModi, chkNull(c.getString(c.getColumnIndex(ar.dbDateModi)))?"":c.getString(c.getColumnIndex(ar.dbDateModi)));
            jsonObj.put(ar.dbHostId, chkNull(c.getString(c.getColumnIndex(ar.dbHostId)))?"":c.getString(c.getColumnIndex(ar.dbHostId)));
            jsonObj.put(ar.dbBranchId, chkNull(c.getString(c.getColumnIndex(ar.dbBranchId)))?"":c.getString(c.getColumnIndex(ar.dbBranchId)));
            jsonObj.put(ar.dbDeviceId, chkNull(c.getString(c.getColumnIndex(ar.dbDeviceId)))?"":c.getString(c.getColumnIndex(ar.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("AreaSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("AreaSelectAll ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray AreaSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameArea+" Where "+ar.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = ;
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectArea(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray ResSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameRes+" Where "+res.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectRes(c);
                jarr.put(getJsonObjectRes(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }

    @NonNull
    private JSONObject getJsonObjectRes(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(res.dbID, c.getString(c.getColumnIndex(res.dbID)));
            jsonObj.put(res.dbCode, chkNull(c.getString(c.getColumnIndex(res.dbCode)))?"":c.getString(c.getColumnIndex(res.dbCode)));
            jsonObj.put(res.dbName, chkNull(c.getString(c.getColumnIndex(res.dbName)))?"":c.getString(c.getColumnIndex(res.dbName)));
            jsonObj.put(res.dbActive, chkNull(c.getString(c.getColumnIndex(res.dbActive)))?"":c.getString(c.getColumnIndex(res.dbActive)));
            jsonObj.put(res.dbRemark, chkNull(c.getString(c.getColumnIndex(res.dbRemark)))?"":c.getString(c.getColumnIndex(res.dbRemark)));
            jsonObj.put(res.dbDefaultRes, chkNull(c.getString(c.getColumnIndex(res.dbDefaultRes)))?"":c.getString(c.getColumnIndex(res.dbDefaultRes)));
            jsonObj.put(res.dbRF1, chkNull(c.getString(c.getColumnIndex(res.dbRF1)))?"":c.getString(c.getColumnIndex(res.dbRF1)));
            jsonObj.put(res.dbRF2, chkNull(c.getString(c.getColumnIndex(res.dbRF2)))?"":c.getString(c.getColumnIndex(res.dbRF2)));
            jsonObj.put(res.dbRH1, chkNull(c.getString(c.getColumnIndex(res.dbRH1)))?"":c.getString(c.getColumnIndex(res.dbRH1)));
            jsonObj.put(res.dbRH2, chkNull(c.getString(c.getColumnIndex(res.dbRH2)))?"":c.getString(c.getColumnIndex(res.dbRH2)));
            jsonObj.put(res.dbBillCode, chkNull(c.getString(c.getColumnIndex(res.dbBillCode)))?"":c.getString(c.getColumnIndex(res.dbBillCode)));
            jsonObj.put(res.dbTaxID, chkNull(c.getString(c.getColumnIndex(res.dbTaxID)))?"":c.getString(c.getColumnIndex(res.dbTaxID)));
            jsonObj.put(res.dbPrinterOrder1, chkNull(c.getString(c.getColumnIndex(res.dbPrinterOrder1)))?"":c.getString(c.getColumnIndex(res.dbPrinterOrder1)));
            jsonObj.put(res.dbPrinterOrder2, chkNull(c.getString(c.getColumnIndex(res.dbPrinterOrder2)))?"":c.getString(c.getColumnIndex(res.dbPrinterOrder2)));
            jsonObj.put(res.dbPrinterOrder3, chkNull(c.getString(c.getColumnIndex(res.dbPrinterOrder3)))?"":c.getString(c.getColumnIndex(res.dbPrinterOrder3)));
            jsonObj.put(res.dbPrinterWaterBar1, chkNull(c.getString(c.getColumnIndex(res.dbPrinterWaterBar1)))?"":c.getString(c.getColumnIndex(res.dbPrinterWaterBar1)));
            jsonObj.put(res.dbPrinterWaterBar2, chkNull(c.getString(c.getColumnIndex(res.dbPrinterWaterBar2)))?"":c.getString(c.getColumnIndex(res.dbPrinterWaterBar2)));
            jsonObj.put(res.dbPrinterWaterBar3, chkNull(c.getString(c.getColumnIndex(res.dbPrinterWaterBar3)))?"":c.getString(c.getColumnIndex(res.dbPrinterWaterBar3)));
            jsonObj.put(res.dbSort1, chkNull(c.getString(c.getColumnIndex(res.dbSort1)))?"":c.getString(c.getColumnIndex(res.dbSort1)));
            jsonObj.put(res.dbDateCreate, chkNull(c.getString(c.getColumnIndex(res.dbDateCreate)))?"":c.getString(c.getColumnIndex(res.dbDateCreate)));
            jsonObj.put(res.dbDateModi, chkNull(c.getString(c.getColumnIndex(res.dbDateModi)))?"":c.getString(c.getColumnIndex(res.dbDateModi)));
            jsonObj.put(res.dbHostId, chkNull(c.getString(c.getColumnIndex(res.dbHostId)))?"":c.getString(c.getColumnIndex(res.dbHostId)));
            jsonObj.put(res.dbBranchId, chkNull(c.getString(c.getColumnIndex(res.dbBranchId)))?"":c.getString(c.getColumnIndex(res.dbBranchId)));
            jsonObj.put(res.dbDeviceId, chkNull(c.getString(c.getColumnIndex(res.dbDeviceId)))?"":c.getString(c.getColumnIndex(res.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ResSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("ResSelectAll ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray ResSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameRes+" Where "+res.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectRes(c);
                jarr.put(getJsonObjectRes(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray UserSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameUser+" Where "+us.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectUser(c);
                jarr.put(getJsonObjectUser(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }

    @NonNull
    private JSONObject getJsonObjectUser(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(us.dbID, c.getString(c.getColumnIndex(us.dbID)));
            jsonObj.put(us.dbLogin, chkNull(c.getString(c.getColumnIndex(us.dbLogin)))?"":c.getString(c.getColumnIndex(us.dbLogin)));
            jsonObj.put(us.dbName, chkNull(c.getString(c.getColumnIndex(us.dbName)))?"":c.getString(c.getColumnIndex(us.dbName)));
            jsonObj.put(us.dbActive, chkNull(c.getString(c.getColumnIndex(us.dbActive)))?"":c.getString(c.getColumnIndex(us.dbActive)));
            jsonObj.put(us.dbPassword1, chkNull(c.getString(c.getColumnIndex(us.dbPassword1)))?"":c.getString(c.getColumnIndex(us.dbPassword1)));
            jsonObj.put(us.dbPrivilege, chkNull(c.getString(c.getColumnIndex(us.dbPrivilege)))?"":c.getString(c.getColumnIndex(us.dbPrivilege)));
            jsonObj.put(us.dbRemark, chkNull(c.getString(c.getColumnIndex(us.dbRemark)))?"":c.getString(c.getColumnIndex(us.dbRemark)));
            jsonObj.put(us.dbSort1, chkNull(c.getString(c.getColumnIndex(us.dbSort1)))?"":c.getString(c.getColumnIndex(us.dbSort1)));
            jsonObj.put(us.dbVoidBill, chkNull(c.getString(c.getColumnIndex(us.dbVoidBill)))?"":c.getString(c.getColumnIndex(us.dbVoidBill)));
            jsonObj.put(us.dbVoidCloseday, chkNull(c.getString(c.getColumnIndex(us.dbVoidCloseday)))?"":c.getString(c.getColumnIndex(us.dbVoidCloseday)));
            jsonObj.put(us.dbDateCreate, chkNull(c.getString(c.getColumnIndex(us.dbDateCreate)))?"":c.getString(c.getColumnIndex(us.dbDateCreate)));
            jsonObj.put(us.dbDateModi, chkNull(c.getString(c.getColumnIndex(us.dbDateModi)))?"":c.getString(c.getColumnIndex(us.dbDateModi)));
            jsonObj.put(us.dbHostId, chkNull(c.getString(c.getColumnIndex(us.dbHostId)))?"":c.getString(c.getColumnIndex(us.dbHostId)));
            jsonObj.put(us.dbBranchId, chkNull(c.getString(c.getColumnIndex(us.dbBranchId)))?"":c.getString(c.getColumnIndex(us.dbBranchId)));
            jsonObj.put(us.dbDeviceId, chkNull(c.getString(c.getColumnIndex(us.dbDeviceId)))?"":c.getString(c.getColumnIndex(us.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ResSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("ResSelectAll ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray UserSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameUser+" Where "+us.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectUser(c);
                jarr.put(getJsonObjectUser(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray FoodsTypeSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoodsType+" Where "+ft.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoodsType(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoodsType(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }

    @NonNull
    private JSONObject getJsonObjectFoodsType(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(ft.dbID, c.getString(c.getColumnIndex(ft.dbID)));
            jsonObj.put(ft.dbCode, chkNull(c.getString(c.getColumnIndex(ft.dbCode)))?"":c.getString(c.getColumnIndex(ft.dbCode)));
            jsonObj.put(ft.dbName, chkNull(c.getString(c.getColumnIndex(ft.dbName)))?"":c.getString(c.getColumnIndex(ft.dbName)));
            jsonObj.put(ft.dbActive, chkNull(c.getString(c.getColumnIndex(ft.dbActive)))?"":c.getString(c.getColumnIndex(ft.dbActive)));
            jsonObj.put(ft.dbRemark, chkNull(c.getString(c.getColumnIndex(ft.dbRemark)))?"":c.getString(c.getColumnIndex(ft.dbRemark)));
            jsonObj.put(ft.dbSort1, chkNull(c.getString(c.getColumnIndex(ft.dbSort1)))?"":c.getString(c.getColumnIndex(ft.dbSort1)));
            jsonObj.put(ft.dbDateCreate, chkNull(c.getString(c.getColumnIndex(ft.dbDateCreate)))?"":c.getString(c.getColumnIndex(ft.dbDateCreate)));
            jsonObj.put(ft.dbDateModi, chkNull(c.getString(c.getColumnIndex(ft.dbDateModi)))?"":c.getString(c.getColumnIndex(ft.dbDateModi)));
            jsonObj.put(ft.dbHostId, chkNull(c.getString(c.getColumnIndex(ft.dbHostId)))?"":c.getString(c.getColumnIndex(ft.dbHostId)));
            jsonObj.put(ft.dbBranchId, chkNull(c.getString(c.getColumnIndex(ft.dbBranchId)))?"":c.getString(c.getColumnIndex(ft.dbBranchId)));
            jsonObj.put(ft.dbDeviceId, chkNull(c.getString(c.getColumnIndex(ft.dbDeviceId)))?"":c.getString(c.getColumnIndex(ft.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("AreaSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("AreaSelectAll ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray FoodsTypeSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoodsType+" Where "+ft.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoodsType(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoodsType(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray FoodsSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoods+" Where "+foo.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoods(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoods(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }

    @NonNull
    private JSONObject getJsonObjectFoods(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(foo.dbID, c.getString(c.getColumnIndex(foo.dbID)));
            jsonObj.put(foo.dbCode, chkNull(c.getString(c.getColumnIndex(foo.dbCode)))?"":c.getString(c.getColumnIndex(foo.dbCode)));
            jsonObj.put(foo.dbName, chkNull(c.getString(c.getColumnIndex(foo.dbName)))?"":c.getString(c.getColumnIndex(foo.dbName)));
            jsonObj.put(foo.dbPrice, c.getDouble(c.getColumnIndex(foo.dbPrice)));
            jsonObj.put(foo.dbActive, chkNull(c.getString(c.getColumnIndex(foo.dbActive)))?"":c.getString(c.getColumnIndex(foo.dbActive)));
            jsonObj.put(foo.dbTypeId, chkNull(c.getString(c.getColumnIndex(foo.dbTypeId)))?"":c.getString(c.getColumnIndex(foo.dbTypeId)));
            jsonObj.put(foo.dbRemark, chkNull(c.getString(c.getColumnIndex(foo.dbRemark)))?"":c.getString(c.getColumnIndex(foo.dbRemark)));
            jsonObj.put(foo.dbResId, chkNull(c.getString(c.getColumnIndex(foo.dbResId)))?"":c.getString(c.getColumnIndex(foo.dbResId)));
            jsonObj.put(foo.dbResCode, chkNull(c.getString(c.getColumnIndex(foo.dbResCode)))?"":c.getString(c.getColumnIndex(foo.dbResCode)));
            jsonObj.put(foo.dbStatusFoods, chkNull(c.getString(c.getColumnIndex(foo.dbStatusFoods)))?"":c.getString(c.getColumnIndex(foo.dbStatusFoods)));
            jsonObj.put(foo.dbPrinterName, chkNull(c.getString(c.getColumnIndex(foo.dbPrinterName)))?"":c.getString(c.getColumnIndex(foo.dbPrinterName)));
            jsonObj.put(foo.dbSort1, chkNull(c.getString(c.getColumnIndex(foo.dbSort1)))?"":c.getString(c.getColumnIndex(foo.dbSort1)));
            jsonObj.put(foo.dbDateCreate, chkNull(c.getString(c.getColumnIndex(foo.dbDateCreate)))?"":c.getString(c.getColumnIndex(foo.dbDateCreate)));
            jsonObj.put(foo.dbDateModi, chkNull(c.getString(c.getColumnIndex(foo.dbDateModi)))?"":c.getString(c.getColumnIndex(foo.dbDateModi)));
            jsonObj.put(foo.dbHostId, chkNull(c.getString(c.getColumnIndex(foo.dbHostId)))?"":c.getString(c.getColumnIndex(foo.dbHostId)));
            jsonObj.put(foo.dbBranchId, chkNull(c.getString(c.getColumnIndex(foo.dbBranchId)))?"":c.getString(c.getColumnIndex(foo.dbBranchId)));
            jsonObj.put(foo.dbDeviceId, chkNull(c.getString(c.getColumnIndex(foo.dbDeviceId)))?"":c.getString(c.getColumnIndex(foo.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("AreaSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("AreaSelectAll ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray FoodsSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoods+" Where "+foo.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
                jarr.put(getJsonObjectFoods(c));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    private Boolean chkNull(String c){
        if(c==null){
            return true;
        }else{
            return false;
        }
    }
    public JSONArray OrderInsert(String row1,String lotID,String areacode,String tablecode,String Qty,String FoodsCode,
                            String FoodsName,String Remark,String ResCode,String Price,String PrinterName,String FoodsId,String StatusToGo,String user,String tableid,String CntCust){
        String sql="",err="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try {//strftime('%Y-%m-%d %H:%M:%S','now')
            sql = "Insert into "+da.tbNameOrder+"("+or.dbID+","+or.dbFoodsCode+","+or.dbOrderDate+","+or.dbPrice+","+or.dbQty+","+or.dbLotId+","+or.dbResCode+","+or.dbTableCode+","
                    +or.dbAreaCode+","+or.dbStatusFoods1+","+or.dbStatusFoods2+","+or.dbStatusFoods3+","+or.dbActive+","+or.dbStatusVoid+","+or.dbStatusBill+","+or.dbStatusCook+","+or.dbDateCreate+","
                    +or.dbPrinterName+","+or.dbRemark+","+or.dbrow1+","+or.dbFoodsId+","+or.dbFoodsName+","+or.dbStatusToGo+","
                    +or.dbOrderUser+","+or.dbStatusCloseday+","+or.dbClosedayId+","+or.dbCntCust+","+or.dbBillId+")"
                    +" Values(lower(hex(randomblob(16))),'"+FoodsCode+"',"+gendate+",'"+Price+"','"+Qty+"','"+lotID+"','"+ResCode+"','"+tablecode+"','"
                    +areacode+"','','','','1','0','0','0',"+gendate+",'"
                    +PrinterName+"','"+Remark+"','"+row1+"','"+FoodsId+"','"+FoodsName+"','"+StatusToGo+"','"
                    +user+"','0','','"+CntCust+"','')";
            db.execSQL(sql);
            sql = "Update "+da.tbNameTable+" Set "+ta.dbStatusUse+" ='1' Where "+ta.dbID+" ='"+tableid+"'";
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("OrderInsert ",e.getMessage());
            err = e.getMessage();
        }

        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Order success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("OrderInsert ",e.getMessage());
        }

//        try {
//
//        }catch (Exception e){
//            Log.e("OrderInsert ",e.getMessage());
//        }

//        $response = array();
//        $resultArray = array();
//        $response["success"] = $ok;
//        $response["message"] = "insert Order success";
//        $response["sql"] = $sql;
//        $response["error"] = $err;
//        array_push($resultArray,$response);
        return  jarr;
    }
    public JSONArray AreaInsert(String id, String code, String name, String remark, String sort1){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameArea+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("AreaInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("AreaInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameArea+"("+ar.dbID+","+ar.dbCode+","+ar.dbName+","+ar.dbRemark+","+ar.dbSort1+","+ar.dbDateCreate+","+ar.dbActive+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+remark+"','"+sort1+"',"+ gendate +",'1')";
            }else{
                sql="Update "+da.tbNameArea +" "
                +"Set "+ar.dbCode+"='"+code+"' "
                +","+ar.dbName+"='"+name+"' "
                +","+ar.dbRemark+"='"+remark+"' "
                +","+ar.dbSort1+"='"+sort1+"' "
                +" Where "+ar.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("AreaInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Area success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("AreaInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray FoodsInsert(String id, String code, String name, String price, String typeid, String remark
            , String resid, String rescode, String statusfoods, String printname, String sort1, String hostid, String branchid){
        String sql="",err="", code1="",foodsCode="", cnt="",id1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                sql = "Select bft.foods_type_code, count(1) as cnt From b_foods bf inner join b_foods_type bft on bf.foods_type_id = bft.foods_type_id Where bf.foods_type_id = '"+typeid+"'";
                Cursor c = db.rawQuery(sql, null);
                if(c.moveToFirst()){
                    do{
                        Log.d("FoodsInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        foodsCode = c.getString(c.getColumnIndex("foods_type_code"));
                        cnt = String.valueOf(c.getInt(c.getColumnIndex("cnt"))+1);
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("FoodsInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
                foodsCode=foodsCode+code1;
                sql = "Insert into "+foo.tbNameFoods+"("+foo.dbID+","+foo.dbCode+","+foo.dbName+","+foo.dbActive+","+foo.dbTypeId+","+foo.dbRemark+","+foo.dbResId+","+foo.dbStatusFoods+","
                    +foo.dbPrinterName+","+foo.dbResCode+","+foo.dbPrice+","+foo.dbDateCreate+")"
                    +" Values ("+genid+",'"+code+"','"+foodsCode+"','1','"+typeid+"','"+remark+"','"
                    +resid+"','"+statusfoods+"','"+printname+"','"+rescode+"',"+price+","+gendate+")";
            }else{
                foodsCode = code;
                sql="Update "+foo.tbNameFoods+" "
                +"Set "+foo.dbCode+" ='"+code+"'"
                +","+foo.dbName+"='"+name+"'"
                +","+foo.dbTypeId+"='"+typeid+"'"
                +","+foo.dbRemark+"='"+remark+"'"
                +","+foo.dbResId+"='"+resid+"'"
                +","+foo.dbStatusFoods+"='"+statusfoods+"'"
                +","+foo.dbPrinterName+"='"+printname+"'"
                +","+foo.dbResCode+"='"+rescode+"'"
                +","+foo.dbPrice+"="+price+" "
                +"Where "+foo.dbID+"='"+id+"'";
            }
            db.execSQL(sql);

            Cursor c = db.rawQuery("Select "+foo.dbID+" From "+da.tbNameFoods+" Where "+foo.dbCode+"='"+foodsCode+"'", null);
            if(c.moveToFirst()){
                do{
//                    Log.d("FoodsInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                    id1 = c.getString(c.getColumnIndex(foo.dbID));
//                    Log.d("FoodsInsert code1 ",code1);
                }while(c.moveToNext());
            }
            c.close();
            db.close();
            Log.d("FoodsInsert ","OK");
        }catch (Exception e){
            Log.e("FoodsInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Foods success");
            jsonObj.put("foods_id",id1);
            jsonObj.put("foods_code",foodsCode);
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("FoodsInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray FoodsTypeInsert(String id, String code, String name, String remark, String sort1 ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameFoodsType+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("FTInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("FoodsTypeInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameFoodsType+"("+ft.dbID+","+ft.dbCode+","+ft.dbName+","+ft.dbRemark+","+ft.dbSort1+","+ft.dbDateCreate+","+ft.dbActive+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+remark+"','"+sort1+"',"+ gendate +",'1')";
            }else{
                sql="Update "+da.tbNameFoodsType +" "
                        +"Set "+ft.dbCode+"='"+code+"' "
                        +","+ft.dbName+"='"+name+"' "
                        +","+ft.dbRemark+"='"+remark+"' "
                        +","+ft.dbSort1+"='"+sort1+"' "
                        +" Where "+ft.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("FoodsTypeInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert FoodsType success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("FoodsTypeInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray ResInsert(String id, String code, String name, String remark, String sort1 ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameRes+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("ResInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("ResInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameRes+"("+res.dbID+","+res.dbCode+","+res.dbName+","+res.dbRemark+","+res.dbSort1+","+res.dbDateCreate+","+res.dbActive+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+remark+"','"+sort1+"',"+ gendate +",'1')";
            }else{
                sql="Update "+da.tbNameRes +" "
                        +"Set "+res.dbCode+"='"+code+"' "
                        +","+res.dbName+"='"+name+"' "
                        +","+res.dbRemark+"='"+remark+"' "
                        +","+res.dbSort1+"='"+sort1+"' "
                        +" Where "+res.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("ResInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Res success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("ResInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray TableInsert(String id, String code, String name,String arid, String remark, String sort1, String statususe, String statustogo, String dateuse ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameTable+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("TableInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("TableInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameTable+"("+ta.dbID+","+ta.dbCode+","+ta.dbName+","+ta.dbRemark+","
                        +ta.dbSort1+","+ta.dbDateCreate+","+ta.dbActive+","+ta.dbStatusUse+","
                        +ta.dbStatusToGo+","+ta.dbDateUse+","+ta.dbStatusUse+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+remark+"','"
                        +sort1+"',"+ gendate +",'1','','"
                        +statustogo+"','','0')";
            }else{
                sql="Update "+da.tbNameRes +" "
                        +"Set "+ta.dbCode+"='"+code+"' "
                        +","+ta.dbName+"='"+name+"' "
                        +","+ta.dbRemark+"='"+remark+"' "
                        +","+ta.dbSort1+"='"+sort1+"' "
//                        +","+ta.dbStatusUse+"='"+statususe+"' "
                        +","+ta.dbStatusToGo+"='"+statustogo+"' "
//                        +","+ta.dbDateUse+"='"+dateuse+"' "
                        +" Where "+ta.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("TableInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Table success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("TableInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray UserInsert(String id, String login, String name,String password, String privilege, String remark, String sort1, String voidbill, String voidcloseday ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameUser+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("UserInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "000"+cnt;
                        code1 = code1.substring(code1.length()-3);
                        Log.d("UserInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameUser+"("+us.dbID+","+us.dbLogin+","+us.dbName+","+us.dbPassword1+","
                        +us.dbPrivilege+","+us.dbRemark+","+us.dbDateCreate+","+us.dbActive+","
                        +us.dbSort1+","+us.VoidBill+","+us.VoidCloseday+") "
                        +"Values ("+this.genid +",'"+login+"','"+name+"','"+password+"','"
                        +privilege+"',"+ remark+"',"+gendate+",'1','"
                        +sort1+"','"+voidbill+"','"+voidcloseday+"')";
            }else{
                sql="Update "+da.tbNameUser +" "
                        +"Set "+us.dbName+"='"+name+"' "
                        +","+us.dbPassword1+"='"+password+"' "
                        +","+us.dbPrivilege+"='"+privilege+"' "
                        +","+us.dbRemark+"='"+remark+"' "
                        +","+us.dbSort1+"='"+sort1+"' "
                        +","+us.VoidBill+"='"+voidbill+"' "
                        +","+us.VoidCloseday+"='"+voidcloseday+"' "
                        +" Where "+us.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("UserInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert User success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("UserInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray OrderByTableCode(String tablecode){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            Cursor c = db.rawQuery("Select * From "+da.tbNameOrder+" Where "+or.dbTableCode+" = '"+tablecode+"' and "+or.dbActive+"='1' and "+or.dbStatusBill+"<> '2' Order By "+or.dbLotId+", "+or.dbrow1, null);
            if(c.moveToFirst()){
                do{
                    jarr.put(getJsonObjectOrder(c));
                }while(c.moveToNext());
            }
            c.close();
        }catch (Exception e){
            Log.e("OrderByTableCode ",e.getMessage());
        }
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    private JSONObject getJsonObjectOrder(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(or.dbID, c.getString(c.getColumnIndex(or.dbID)));
            jsonObj.put(or.dbLotId, chkNull(c.getString(c.getColumnIndex(or.dbLotId)))?"":c.getString(c.getColumnIndex(or.dbLotId)));
            jsonObj.put(or.dbrow1, chkNull(c.getString(c.getColumnIndex(or.dbrow1)))?"":c.getString(c.getColumnIndex(or.dbrow1)));
            jsonObj.put(or.dbFoodsId, c.getDouble(c.getColumnIndex(or.dbFoodsId)));
            jsonObj.put(or.dbFoodsCode, chkNull(c.getString(c.getColumnIndex(or.dbFoodsCode)))?"":c.getString(c.getColumnIndex(or.dbFoodsCode)));
            jsonObj.put(or.dbOrderDate, chkNull(c.getString(c.getColumnIndex(or.dbOrderDate)))?"":c.getString(c.getColumnIndex(or.dbOrderDate)));
            jsonObj.put(or.dbPrice, chkNull(c.getString(c.getColumnIndex(or.dbPrice)))?"":c.getString(c.getColumnIndex(or.dbPrice)));
            jsonObj.put(or.dbQty, chkNull(c.getString(c.getColumnIndex(or.dbQty)))?"":c.getString(c.getColumnIndex(or.dbQty)));
            jsonObj.put(or.dbRemark, chkNull(c.getString(c.getColumnIndex(or.dbRemark)))?"":c.getString(c.getColumnIndex(or.dbRemark)));
            jsonObj.put(or.dbTableCode, chkNull(c.getString(c.getColumnIndex(or.dbTableCode)))?"":c.getString(c.getColumnIndex(or.dbTableCode)));
            jsonObj.put(or.dbResCode, chkNull(c.getString(c.getColumnIndex(or.dbResCode)))?"":c.getString(c.getColumnIndex(or.dbResCode)));
            jsonObj.put(or.dbAreaCode, chkNull(c.getString(c.getColumnIndex(or.dbAreaCode)))?"":c.getString(c.getColumnIndex(or.dbAreaCode)));
            jsonObj.put(or.dbStatusFoods1, chkNull(c.getString(c.getColumnIndex(or.dbStatusFoods1)))?"":c.getString(c.getColumnIndex(or.dbStatusFoods1)));
            jsonObj.put(or.dbStatusFoods2, chkNull(c.getString(c.getColumnIndex(or.dbStatusFoods2)))?"":c.getString(c.getColumnIndex(or.dbStatusFoods2)));
            jsonObj.put(or.dbStatusFoods3, chkNull(c.getString(c.getColumnIndex(or.dbStatusFoods3)))?"":c.getString(c.getColumnIndex(or.dbStatusFoods3)));
            jsonObj.put(or.dbStatusBill, chkNull(c.getString(c.getColumnIndex(or.dbStatusBill)))?"":c.getString(c.getColumnIndex(or.dbStatusBill)));
            jsonObj.put(or.dbBillCheckDate, chkNull(c.getString(c.getColumnIndex(or.dbBillCheckDate)))?"":c.getString(c.getColumnIndex(or.dbBillCheckDate)));
            jsonObj.put(or.dbStatusCook, chkNull(c.getString(c.getColumnIndex(or.dbStatusCook)))?"":c.getString(c.getColumnIndex(or.dbStatusCook)));
            jsonObj.put(or.dbCookReceiveDate, chkNull(c.getString(c.getColumnIndex(or.dbCookReceiveDate)))?"":c.getString(c.getColumnIndex(or.dbCookReceiveDate)));
            jsonObj.put(or.dbCookFinishDate, chkNull(c.getString(c.getColumnIndex(or.dbCookFinishDate)))?"":c.getString(c.getColumnIndex(or.dbCookFinishDate)));
            jsonObj.put(or.dbActive, chkNull(c.getString(c.getColumnIndex(or.dbActive)))?"":c.getString(c.getColumnIndex(or.dbActive)));
            jsonObj.put(or.dbVoidDate, chkNull(c.getString(c.getColumnIndex(or.dbVoidDate)))?"":c.getString(c.getColumnIndex(or.dbVoidDate)));
            jsonObj.put(or.dbStatusVoid, chkNull(c.getString(c.getColumnIndex(or.dbStatusVoid)))?"":c.getString(c.getColumnIndex(or.dbStatusVoid)));
            jsonObj.put(or.dbPrinterName, chkNull(c.getString(c.getColumnIndex(or.dbPrinterName)))?"":c.getString(c.getColumnIndex(or.dbPrinterName)));
            jsonObj.put(or.dbDateCreate, chkNull(c.getString(c.getColumnIndex(or.dbDateCreate)))?"":c.getString(c.getColumnIndex(or.dbDateCreate)));
            jsonObj.put(or.dbFoodsName, chkNull(c.getString(c.getColumnIndex(or.dbFoodsName)))?"":c.getString(c.getColumnIndex(or.dbFoodsName)));
            jsonObj.put(or.dbBillId, chkNull(c.getString(c.getColumnIndex(or.dbBillId)))?"":c.getString(c.getColumnIndex(or.dbBillId)));
            jsonObj.put(or.dbOrderUser, chkNull(c.getString(c.getColumnIndex(or.dbOrderUser)))?"":c.getString(c.getColumnIndex(or.dbOrderUser)));
            jsonObj.put(or.dbStatusCloseday, chkNull(c.getString(c.getColumnIndex(or.dbStatusCloseday)))?"":c.getString(c.getColumnIndex(or.dbStatusCloseday)));
            jsonObj.put(or.dbClosedayId, chkNull(c.getString(c.getColumnIndex(or.dbClosedayId)))?"":c.getString(c.getColumnIndex(or.dbClosedayId)));
            jsonObj.put(or.dbCntCust, chkNull(c.getString(c.getColumnIndex(or.dbCntCust)))?"":c.getString(c.getColumnIndex(or.dbCntCust)));
            jsonObj.put(or.dbStatusToGo, chkNull(c.getString(c.getColumnIndex(or.dbStatusToGo)))?"":c.getString(c.getColumnIndex(or.dbStatusToGo)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectOrder ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectOrder ",e.getMessage());
        }
        return jsonObj;
    }
    public JSONArray BillInsert(String tableid,String areaid,String deviceid,String discount,String amt,String  sc,
            String vat,String total,String nettotal,String billID, String cashreceive,String cashton,String user, String remark){
        String sql="",err="", code1="",resID="", code="",year="",month="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            Cursor c = db.rawQuery("Select ifnull("+res.dbBillCode+",'000000000') as "+res.dbBillCode+" From "+da.tbNameRes+" Where "+res.dbDefaultRes+" = '1' and "+res.dbBillMonth+" = strftime('%m','now') ", null);
            if(!c.moveToFirst()){
                db.execSQL("update "+da.tbNameRes+" set "+res.dbBillMonth+" = strftime('%m','now'), "+res.dbBillCode+" = NULL where "+res.dbDefaultRes+" = '1'");
            }
            c.close();
//            c = db.rawQuery("Select "+res.dbID+", strftime('%m','now') as month, strftime('%Y','now') as year, ifnull("+res.dbBillCode+",'000000000') as "+res.dbBillCode
//                    +" From "+da.tbNameRes+" Where "+res.dbDefaultRes+" = '1' and "+res.dbBillMonth+" = strftime('%m','now')",null);
            c = db.rawQuery("Select "+res.dbID+", strftime('%m','now') as month, strftime('%Y','now') as year, ifnull("+res.dbBillCode+",'000000000') as "+res.dbBillCode
                    +" From "+da.tbNameRes+" Where "+res.dbDefaultRes+" = '1' ",null);
            if(c.moveToFirst()){
                do{
                    resID = c.getString(c.getColumnIndex("res_id"));
                    code = "0000"+String.valueOf(Integer.parseInt(c.getString(c.getColumnIndex("bill_code")))+1);
                    code = code.substring(code.length()-5);
                    year = String.valueOf(c.getInt(c.getColumnIndex("year"))+543);
                    year = year.substring(year.length()-2);
                    month = c.getString(c.getColumnIndex("month"));
                    code1 = year+month+code;
                    Log.d("BillInsert code1 ",code1);
                }while(c.moveToNext());
            }
            c.close();
            db.execSQL("update "+da.tbNameRes+" set "+res.dbBillCode+" = '"+code+"' Where "+res.dbID+"  = '"+resID+"'");
            sql = "Insert into "+da.tbNameBill+"("+bi.dbID+","+bi.dbBillDate+","+bi.dbDateCreate+","+bi.dbRemark+","+bi.dbStatusVoid+","+bi.dbTableId+","+bi.dbResId+","
                    +bi.dbAreaId+","+bi.dbDeviceId+","+bi.dbAmt+","+bi.dbDiscount+","+bi.dbActive+","+bi.dbSC+","+bi.dbVat+","+bi.dbTotal+","+bi.dbNetTotal+","
                    +bi.dbCode+","+bi.dbBillUser+","+bi.dbCashReceive+","+bi.dbCashTon+","+bi.dbStatusCloseday+","+bi.dbClosedayId+")"
                    +" Values ('"+billID+"',"+gendate+","+gendate+",'"+remark+"','0','"+tableid+"','"+resID+"','"
                    +areaid+"','"+deviceid+"',"+amt+","+discount+",'1',"+sc+","+vat+","+total+","+nettotal+",'"
                    +code1+"','"+user+"',"+cashreceive+","+cashton+",'0','')";
            db.execSQL(sql);
            db.execSQL("Update "+da.tbNameTable+" Set "+ta.dbStatusUse+" ='0' Where table_id ='"+tableid+"'");
        }catch (Exception e){
            Log.e("BillInsert 1 ",e.getMessage());
            err=e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Bill success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("BillInsert 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray BillDetailInsert(String billID,String LotId,String Qty,String FoodsCode,String FoodsName,
            String FoodsId,String Price,String Amt,String orderID,String row1,String FlagVoid){
        String sql="",err="", code1="",resID="", code="",year="",month="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(FlagVoid.equals("1")){
                sql = "Update "+da.tbNameOrder+" Set "+or.dbStatusVoid+" ='3', "+or.dbVoidDate+" = "+gendate+" Where "+or.dbID+" ='"+orderID+"'";
                db.execSQL(sql);
            }else{
                sql = "Insert into "+da.tbNameBillDetail+"("+bid.dbID+","+bid.dbDateCreate+","+bid.dbBillId+","+bid.dbORderId+","+bid.dbStatusVoid+","+bid.dbRow1+","+bid.dbFoodsId+","+bid.dbFoodsCode+","
                        +bid.dbPrice+","+bid.dbQty+","+bid.dbAmt+","+bid.dbActive+")"
                        +" Values ("+genid+","+gendate+",'"+billID+"','"+orderID+"','0','"+row1+"','"+FoodsId+"','"+FoodsCode+"',"
                        +Price+","+Qty+","+Amt+",'1')";
                db.execSQL(sql);
            }
        }catch (Exception e){
            Log.e("BillDetailInsert 1 ",e.getMessage());
            err=e.getMessage();
        }
        db.execSQL("Update "+da.tbNameOrder+" Set "+or.dbStatusBill+" ='2', "+or.dbBillId+" = '"+billID+"', "+or.dbBillCheckDate+" = "+gendate+" Where "+or.dbID+" ='"+orderID+"'");
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert BillDetail success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("BillDetailInsert 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray BillByCloseday(String resid, String closedaydate){
        String sql="",err="", date1="",date2="", wheredate="",year="",month="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c,c1,c2;
        try{
            Log.d("BillByCloseday ","ok");
            date1 = closedaydate+" 00:00:00";
            date2 = closedaydate+" 23:59:59";
            wheredate=" and "+cd.dbCloseDayDate+" >= '"+date1+"' and "+cd.dbCloseDayDate+" <= '"+date2+"'";
            sql = "Select * From t_closeday Where res_id = '"+resid+"' and active ='1'  "+wheredate;
//            sql = "Select sum(amount) amount amt, sum(discount) as discount, sum(total) as total, sum(service_charge) as service_charge, sum(vat) as vat, sum(nettotal) as nettotal, count(1) as cnt_bill "
//                +"From "+da.tbNameBill+" Where "+bi.dbStatusCloseday+" <> '1' "+wheredate+" and "+bi.dbActive+" = '1'";
            c = db.rawQuery(sql,null);
            if(!c.moveToFirst()){
                jsonObj = getJsonObjectCloseday(null);
                wheredate=" and bill_date >= '"+date1+"' and bill_date <= '"+date2+"'";
//                sql = "Select count(1) as cnt_order, sum(price*qty) as amt_order From t_order Where status_closeday <> '1' "+wheredate+" and active = '1'";
                sql = "Select sum(amount) as amount, sum(discount) as discount, sum(total) as total, sum(service_charge) as service_charge, sum(vat) as vat, sum(nettotal) as nettotal, count(1) as cnt_bill "
                    +"From "+da.tbNameBill+" Where "+bi.dbStatusCloseday+" <> '1' "+wheredate+" and "+bi.dbActive+" = '1'";
                c1 = db.rawQuery(sql,null);
                if(c1.moveToFirst()){
                    do {
                        jsonObj.put(cd.dbAmt, c1.getString(c1.getColumnIndex(cd.dbAmt)));
                        jsonObj.put(cd.dbDiscount, c1.getString(c1.getColumnIndex(cd.dbDiscount)));
                        jsonObj.put(cd.dbTotal, c1.getString(c1.getColumnIndex(cd.dbTotal)));
                        jsonObj.put(cd.dbSC, c1.getString(c1.getColumnIndex(cd.dbSC)));
                        jsonObj.put(cd.dbVat, c1.getString(c1.getColumnIndex(cd.dbVat)));
                        jsonObj.put(cd.dbNetTotal, c1.getString(c1.getColumnIndex(cd.dbNetTotal)));
                        jsonObj.put(cd.dbCntBill, c1.getString(c1.getColumnIndex(cd.dbCntBill)));
                    }while(c1.moveToNext());
                }
                c1.close();
                wheredate=" and order_date >= '"+date1+"' and order_date <= '"+date2+"'";
                sql = "Select count(1) as cnt_order, sum(price*qty) as amount_order From t_order Where status_closeday <> '1' "+wheredate+" and active = '1'";
                c2 = db.rawQuery(sql,null);
                if(c2.moveToFirst()){
                    do {
                        jsonObj.put(cd.dbCntOrder, c2.getString(c2.getColumnIndex(cd.dbCntOrder)));
                        jsonObj.put(cd.dbAmtOrder, c2.getString(c2.getColumnIndex(cd.dbAmtOrder)));
                    }while(c2.moveToNext());
                }
                c2.close();
                jarr.put(jsonObj);
            }else{
                do {
//                    jsonObj = getJsonObjectCloseday(c);
                    jarr.put(getJsonObjectCloseday(c));
                }while(c.moveToNext());

            }
            c.close();
        }catch (JSONException e){
            Log.e("BillByCloseday 1 ",e.getMessage());
            err=e.getMessage();
        }catch (Exception e1) {
            Log.e("BillByCloseday 1 ",e1.getMessage());
            err=e1.getMessage();
        }
//        try{
//            jsonObj = new JSONObject();
//            jsonObj.put("success", "1");
//            jsonObj.put("message", "insert BillDetail success");
//            jsonObj.put("sql", sql);
//            jsonObj.put("error", err);
//            jarr.put(jsonObj);
//        }catch (JSONException e) {
//            Log.e("BillByCloseday 2 ",e.getMessage());
//            err=e.getMessage();
//        }
        return jarr;
    }
    private JSONObject getJsonObjectCloseday(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try{
            if(c==null){
                jsonObj.put(cd.dbID, "");
                jsonObj.put(cd.dbCloseDayDate, "");
                jsonObj.put(cd.dbResId, "");
                jsonObj.put(cd.dbAmt, "");
                jsonObj.put(cd.dbDiscount, "");
                jsonObj.put(cd.dbTotal, "");
                jsonObj.put(cd.dbSC, "");
                jsonObj.put(cd.dbVat, "");
                jsonObj.put(cd.dbNetTotal, "");
                jsonObj.put(cd.dbRemark, "");
                jsonObj.put(cd.dbActive, "");
                jsonObj.put(cd.dbStatusVoid, "");
                jsonObj.put(cd.dbVoidDate, "");
                jsonObj.put(cd.dbVoidUser, "");
                jsonObj.put(cd.dbCntBill, "");
                jsonObj.put(cd.dbAmtBill, "");
                jsonObj.put(cd.dbCntOrder, "");
                jsonObj.put(cd.dbAmtOrder, "");
                jsonObj.put(cd.dbCloseDayUser, "");
                jsonObj.put(cd.dbCashR1, "");
                jsonObj.put(cd.dbCashR2, "");
                jsonObj.put(cd.dbCashR3, "");
                jsonObj.put(cd.dbCashR1Remark, "");
                jsonObj.put(cd.dbCashR2Remark, "");
                jsonObj.put(cd.dbCashR3Remark, "");
                jsonObj.put(cd.dbCashD1, "");
                jsonObj.put(cd.dbCashD2, "");
                jsonObj.put(cd.dbCashD3, "");
                jsonObj.put(cd.dbCashD1Remark, "");
                jsonObj.put(cd.dbCashD2Remark, "");
                jsonObj.put(cd.dbCashD3Remark, "");
                jsonObj.put(cd.dbHostId, "");
                jsonObj.put(cd.dbBranchId, "");
                jsonObj.put(cd.dbDeviceId, "");
                jsonObj.put(cd.dbWeather, "");
            }else{
                jsonObj.put(cd.dbID, c.getString(c.getColumnIndex(cd.dbID)));
                jsonObj.put(cd.dbCloseDayDate, c.getString(c.getColumnIndex(cd.dbCloseDayDate)));
                jsonObj.put(cd.dbResId, c.getString(c.getColumnIndex(cd.dbResId)));
                jsonObj.put(cd.dbAmt, c.getString(c.getColumnIndex(cd.dbAmt)));
                jsonObj.put(cd.dbDiscount, c.getString(c.getColumnIndex(cd.dbDiscount)));
                jsonObj.put(cd.dbTotal, c.getString(c.getColumnIndex(cd.dbTotal)));
                jsonObj.put(cd.dbSC, c.getString(c.getColumnIndex(cd.dbSC)));
                jsonObj.put(cd.dbVat, c.getString(c.getColumnIndex(cd.dbVat)));
                jsonObj.put(cd.dbNetTotal, c.getString(c.getColumnIndex(cd.dbNetTotal)));
                jsonObj.put(cd.dbRemark, c.getString(c.getColumnIndex(cd.dbRemark)));
                jsonObj.put(cd.dbActive, c.getString(c.getColumnIndex(cd.dbActive)));
                jsonObj.put(cd.dbStatusVoid, c.getString(c.getColumnIndex(cd.dbStatusVoid)));
                jsonObj.put(cd.dbVoidDate, c.getString(c.getColumnIndex(cd.dbVoidDate)));
                jsonObj.put(cd.dbVoidUser, c.getString(c.getColumnIndex(cd.dbVoidUser)));
                jsonObj.put(cd.dbCntBill, c.getString(c.getColumnIndex(cd.dbCntBill)));
                jsonObj.put(cd.dbAmtBill, c.getString(c.getColumnIndex(cd.dbAmtBill)));
                jsonObj.put(cd.dbCntOrder, c.getString(c.getColumnIndex(cd.dbCntOrder)));
                jsonObj.put(cd.dbAmtOrder, c.getString(c.getColumnIndex(cd.dbAmtOrder)));
                jsonObj.put(cd.dbCloseDayUser, c.getString(c.getColumnIndex(cd.dbCloseDayUser)));
                jsonObj.put(cd.dbCashR1, c.getString(c.getColumnIndex(cd.dbCashR1)));
                jsonObj.put(cd.dbCashR2, c.getString(c.getColumnIndex(cd.dbCashR2)));
                jsonObj.put(cd.dbCashR3, c.getString(c.getColumnIndex(cd.dbCashR3)));
                jsonObj.put(cd.dbCashR1Remark, c.getString(c.getColumnIndex(cd.dbCashR1Remark)));
                jsonObj.put(cd.dbCashR2Remark, c.getString(c.getColumnIndex(cd.dbCashR2Remark)));
                jsonObj.put(cd.dbCashR3Remark, c.getString(c.getColumnIndex(cd.dbCashR3Remark)));
                jsonObj.put(cd.dbCashD1, c.getString(c.getColumnIndex(cd.dbCashD1)));
                jsonObj.put(cd.dbCashD2, c.getString(c.getColumnIndex(cd.dbCashD2)));
                jsonObj.put(cd.dbCashD3, c.getString(c.getColumnIndex(cd.dbCashD3)));
                jsonObj.put(cd.dbCashD1Remark, c.getString(c.getColumnIndex(cd.dbCashD1Remark)));
                jsonObj.put(cd.dbCashD2Remark, c.getString(c.getColumnIndex(cd.dbCashD2Remark)));
                jsonObj.put(cd.dbCashD3Remark, c.getString(c.getColumnIndex(cd.dbCashD3Remark)));
                jsonObj.put(cd.dbHostId, c.getString(c.getColumnIndex(cd.dbHostId)));
                jsonObj.put(cd.dbBranchId, c.getString(c.getColumnIndex(cd.dbBranchId)));
                jsonObj.put(cd.dbDeviceId, c.getString(c.getColumnIndex(cd.dbDeviceId)));
                jsonObj.put(cd.dbWeather, c.getString(c.getColumnIndex(cd.dbWeather)));
            }
        }catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectCloseday1 ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectCloseday2 ",e.getMessage());
        }
        return jsonObj;
    }
    public JSONArray ClosedayInsert(String closedate){
        String sql="",err="", code1="",resID="", code="",year="",month="",date1="",date2="", wheredate="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            date1 = closedate+" 00:00:00";
            date2 = closedate+" 23:59:59";
            $sql1 = "Insert into "da.tbNameCloseday+"("+cd.dbID+","+cd.dbCloseDayDate+","+cd.dbResId+","+cd.dbAmt+","+cd.dbDiscount+","+cd.dbTotal+","+cd.dbSC+","
                    +cd.dbVat+","+cd.dbNetTotal+","+cd.dbRemark+","+cd.dbActive+","+cd.dbStatusVoid+","+cd.dbVoidDate+","+cd.dbVoidUser+","+cd.dbCntBill+","+cd.dbAmtBill+","
                    +cd.dbCntOrder+","+cd.dbAmtOrder+","+cd.CloseDayUser+","+cd.dbCashR1+","+cd.dbCashR2+","+cd.dbCashR3+","+cd.dbCashD1+","+cd.dbCashD2+","+cd.dbCashD3+","
                    +cd.dbCashR1Remark+","+cd.dbCashR2Remark+","+cd.dbCashR3Remark+","+cd.dbCashD1Remark+","+cd.dbCashD2Remark+","+cd.dbCashD3Remark+","+cd.dbWeather+")"
                    +" Values ('".$_POST['closeday_id']."',now(),'".$_POST['res_id']."',".$_POST['amount'].",".$_POST['discount'].",".$_POST['total'].",".$_POST['sc'].","
                    .$_POST['vat'].",".$_POST['nettotal'].",'".$_POST['remark']."','1','0','','',".$_POST['cnt_bill'].",".$_POST['bill_amount'].","
                    .$_POST['cnt_order'].",".$_POST['amount_order'].",'".$_POST['closeday_user']."',".$_POST['cash_receive1'].",".$_POST['cash_receive2'].",".$_POST['cash_receive3'].",".$_POST['cash_draw1'].",".$_POST['cash_draw2'].",".$_POST['cash_draw3'].",'"
                    .$_POST['cash_receive1_remark']."','".$_POST['cash_receive2_remark']."','".$_POST['cash_receive3_remark']."','".$_POST['cash_draw1_remark']."','".$_POST['cash_draw2_remark']."','".$_POST['cash_draw3_remark']."','".$_POST['weather']."')";
        }catch (Exception e1) {

        }
        return jarr;
    }
}
