package com.nakoyagarden.ekapop.restaurant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserViewActivity extends AppCompatActivity {
    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jarrU;
    private RestaurantControl rs;
    ListView lvUvView;
    Button btnUvAdd;
    Boolean pageLoad=false;
    public ArrayList<User> lUa = new ArrayList<User>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        pageLoad = true;

        lvUvView = (ListView)findViewById(R.id.lvUvView);
        lvUvView.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        btnUvAdd = (Button)findViewById(R.id.btnUvAdd);

        btnUvAdd.setText(getResources().getString(R.string.add)+getResources().getString(R.string.table));
        btnUvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rs.UsID ="";
                Intent s1 = new Intent(view.getContext(), UserAddActivity.class);
                s1.putExtra("RestaurantControl",rs);
                startActivityForResult(s1, 0);
            }
        });
        lvUvView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    JSONObject catObj = (JSONObject) jarrU.get(i);
                    //String ID = catObj.getString("foods_id");
                    rs.UsID = catObj.getString("user_id");
                }catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Intent s1 = new Intent(view.getContext(), UserAddActivity.class);
                s1.putExtra("RestaurantControl",rs);
                startActivityForResult(s1, 0);
            }
        });
        pageLoad=false;
    }
    @Override
    protected void onResume() {
        if(!pageLoad){
            super.onResume();
            new retrieveUser().execute();
        }
        ////    setLvFoods();
    }
    class retrieveUser extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrU = jsonparser.getJSONFromUrl(rs.hostGetUser,new ArrayList<NameValuePair>());
            //rs.jarrF = jarrT.toString();
            //} catch (JSONException e) {
            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setLvTable();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(UserViewActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Loading...");
            pd.setMessage("Loading Bill ...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();
        }
    }
    private void setLvTable(){
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //jarrF = jsonparser.getJSONFromUrl(rs.hostSelectFoods,params);
            //jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
            if(jarrU!=null){
                //jarrT =  new JSONArray(rs.jarrF);
                arrayList = new ArrayList<String>();
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                lUa.clear();
                for (int i = 0; i < jarrU.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrU.get(i);
                    User a = new User();
                    a.ID = catObj.getString("user_id");
                    a.Login = catObj.getString("user_login");
                    a.Name = catObj.getString("user_name");
                    a.Passoword1 = catObj.getString("password");
                    a.Privilege = catObj.getString("privilege");
                    a.Remark = catObj.getString("remark");
                    a.Sort1 = catObj.getString("sort1");
                    lUa.add(a);
                    //arrayList.add(f.Code+" "+f.Name+" "+f.Price+" "+f.Remark+" ร้าน "+rs.getResToName(f.ResId,"id")+" ประเภท "+rs.getFoodsTypeToName(f.TypeId,"id")+" สถานะ "+f.StatusFoods+" เครื่องพิมพ์ "+f.PrinterName);
                    arrayList.add(a.Login+" "+a.Name+""+a.Privilege+" "+a.Remark);
                }

                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        // Get the current item from ListView
                        View view = super.getView(position,convertView,parent);

                        // Get the Layout Parameters for ListView Current Item View
                        ViewGroup.LayoutParams params = view.getLayoutParams();

                        // Set the height of the Item View
                        params.height = 60;
                        view.setLayoutParams(params);

                        return view;
                    }
                };
                //lvAvView = (ListView)findViewById(R.id.lvFoods);
                lvUvView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
