package com.nakoyagarden.ekapop.restaurant;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderViewActivity extends AppCompatActivity {
    Spinner cboOvTable, cboOvArea;
    ListView lvOvOrder;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();

    public RestaurantControl rs;

    public ArrayList<String> sCboTable = new ArrayList<String>();
    public ArrayList<String> sCboArea = new ArrayList<String>();
    public ArrayList<Order> lOrder = new ArrayList<Order>();
    public ArrayList<String> arrayList = new ArrayList<String>();

    private ArrayAdapter<String> alvOvOrder, alvMOrder;

    Integer row1=0;
    String tableCode="";
    int textSize=20,textSize1=18;
    String ab, Code="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);

        cboOvTable = (Spinner)findViewById(R.id.cboOvTable);
        cboOvArea = (Spinner)findViewById(R.id.cboOvArea);
        lvOvOrder = (ListView)findViewById(R.id.lvOvOrder);
//        Intent intent = getIntent();
        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboArea);
        cboOvArea.setAdapter(adaArea);
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
        cboOvTable.setAdapter(adapter);
        cboOvTable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ItemData itemView = (ItemData) cboOvTable.getSelectedItem();
//                itemView.text
//                String tablecode = rs.getTable(itemView.text,"code");
                String tablecode = rs.getTable(cboOvTable.getSelectedItem().toString(),"code");
                String areacode = rs.getArea(cboOvTable.getSelectedItem().toString(),"code");
                if(!tablecode.equals("")){
                    new retrieveOrderByTableCode().execute(tablecode);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        lvOvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
    private void setControl(){
        setlvOrder();
    }
    private void setlvOrder(){
        lvOvOrder.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));
        lOrder.clear();
        arrayList.clear();
        Double amt=0.0, total=0.0;
        for(int i=0;i<jarr.length();i++){
            try {
                JSONObject catObj = (JSONObject) jarr.get(i);
                Order o = new Order();
                //o = (Order)lOrderLot.get(i);
                o.ID = catObj.getString("order_id");
                o.LotId = catObj.getString("lot_id");
                o.FoodsCode = catObj.getString("foods_code");
                o.FoodsName = catObj.getString("foods_name");
                o.Price = catObj.getString("price");
                o.Qty = catObj.getString("qty");
                o.row1 = catObj.getString("row1");
                o.StatusCook = catObj.getString("status_cook");
                o.FoodsName = catObj.getString("foods_name");
                o.StatusToGo = catObj.getString("status_to_go");
                amt = Double.parseDouble(o.Price)*Double.parseDouble(o.Qty);
                o.Amt = amt.toString();
                total += amt;
                String togo = o.StatusToGo;
                if(i==0){
                    if(o.StatusToGo.equals("1")){
//                        chkBaInRes.setChecked(true);
                    }else{
//                        chkBaToGo.setChecked(true);

                    }
                }
                if(togo.equals("1")){
                    togo = getResources().getString(R.string.inres);
                }else{
                    togo=getResources().getString(R.string.togo);
                }
                arrayList.add(o.row1+" "+o.FoodsCode+" "+o.FoodsName+" "+getResources().getString(R.string.price)+"("+o.Price+") "+
                        getResources().getString(R.string.qty)+"("+o.Qty+") = "+ amt.toString()+o.Remark+togo);
                lOrder.add(o);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        alvOvOrder = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList){
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
                return textView;
            }
        };
        lvOvOrder.setAdapter(alvOvOrder);
    }
    class retrieveOrderByTableCode extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair("table_code", arg0[0]));
            //params.add(new BasicNameValuePair("table_code",tableCode));
            jarr = jsonparser.getJSONFromUrl(rs.hostOrderByTableCode,params);

            //rs.jarrR = jarrR.toString();
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
}
