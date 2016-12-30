package com.nakoyagarden.ekapop.restaurant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    public DatabaseSQLi(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        c= context;
    }
    public void onCreate(SQLiteDatabase db) {
        creSQLi = "";
        createDatabase();
        String cAr="";
        cAr=da.creaT+" "+da.tbNameRes+" "
                +"( "+ar.dbID+"' "+da.tex+" PRIMARY KEY "
                +", "+ar.dbCode+"' "+da.tex+"  NULL "
                +", '"+ar.dbName+"' "+da.tex+"  NULL "
                +", '"+ar.dbRemark+"' "+da.tex+"  NULL "
                +", '"+ar.dbActive+"' "+da.tex+"  NULL "
                +", '"+ar.dbSort1+"' "+da.tex+"  NULL "
                +") ";
        Log.d("onCreate ",ar.cAreaSQLi);
        db.execSQL(ar.cDropArea);
        db.execSQL(ar.cAreaSQLi);
        Log.d("onCreate ",ta.cTableSQLi);
        db.execSQL(ta.cDropTable);
        db.execSQL(ta.cTableSQLi);
        db.execSQL("Insert Into b_table (table_id, table_name, active) Values(lower(hex(randomblob(16))), 'โต๊ะ 1','1');");
        db.execSQL("Insert Into b_table (table_id, table_name, active) Values(lower(hex(randomblob(16))), 'โต๊ะ 2','1');");
        Log.d("onCreate ",res.cResSQLi);
        db.execSQL(res.cDropRes);
        db.execSQL(res.cResSQLi);
        Log.d("onCreate ",us.cUserSQLi);
        db.execSQL(us.cDropUser);
        db.execSQL(us.cUserSQLi);
        Log.d("onCreate ",foo.cFoodsSQLi);
        db.execSQL(foo.cDropFoods);
        db.execSQL(foo.cFoodsSQLi);
        Log.d("onCreate ",ft.cFoodsTypeSQLi);
        db.execSQL(ft.cDropFoodsType);
        db.execSQL(ft.cFoodsTypeSQLi);
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
        Cursor c = db.rawQuery("SELECT column1,column2,column3 FROM table ", null);
        if(c.moveToFirst()){
            do{
                //assing values
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                String column3 = c.getString(2);
                //Do something Here with values

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return  jarr;
    }
}
