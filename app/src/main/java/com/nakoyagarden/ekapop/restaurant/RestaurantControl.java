package com.nakoyagarden.ekapop.restaurant;

import android.app.Application;
import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Created by ekapop on 8/16/2016.
 */
public class RestaurantControl  extends Application implements Serializable {
    //JsonParser jsonparser = new JsonParser();
    public ArrayList<String> sCboTable = new ArrayList<String>();
    public ArrayList<String> sCboArea = new ArrayList<String>();
    public ArrayList<String> sCboRes = new ArrayList<String>();
    public ArrayList<String> sCboPrinter = new ArrayList<String>();
    public ArrayList<String> sCboFoodsType = new ArrayList<String>();

    public ArrayList<String> sTable = new ArrayList<String>();
    public ArrayList<String> sArea = new ArrayList<String>();
    public ArrayList<String> sRes = new ArrayList<String>();
    //public ArrayList<String> sCboPrinter = new ArrayList<String>();
    public ArrayList<String> sFoodsType = new ArrayList<String>();

    public String ResName="", ReceiptH1="", ReceiptH2="", ReceiptF1="", ReceiptF2="";

    public String hostIP="10.0.1.80";
    public String fooID="", ordID="", ordLotID="", arID="", taID ="",resID="", ftID="";
    public String hostSaveOrder="http://"+hostIP+":80/restaurant/saveTOrder.php";

    public String hostGetArea="http://"+hostIP+":80/restaurant/getArea.php";
    public String hostGetTable="http://"+hostIP+":80/restaurant/getTable.php";
    public String hostGetRes="http://"+hostIP+":80/restaurant/getRes.php";
    public String hostGetFoodsType ="http://"+hostIP+":80/restaurant/getFoodsType.php";
    public String hostGetPrinterName ="http://"+hostIP+":80/restaurant/getPrinterName.php";
    public String hostGetFoods ="http://"+hostIP+":80/restaurant/getFoods.php";

    public String hostOrderByTableCode ="http://"+hostIP+":80/restaurant/OrderByTableCode.php";
    public String hostBillByTableId ="http://"+hostIP+":80/restaurant/BillByTableId.php";
    public String hostBillByBillCode ="http://"+hostIP+":80/restaurant/BillByBillCode.php";
    public String hostBillDetailByBillId ="http://"+hostIP+":80/restaurant/BillByBillCode.php";

    public String hostBillInsert ="http://"+hostIP+":80/restaurant/BillInsert.php";
    public String hostBillUpdate ="http://"+hostIP+":80/restaurant/BillUpdate.php";
    public String hostBillDetailInsert ="http://"+hostIP+":80/restaurant/BillDetailInsert.php";
    public String hostBillDetailUpdate ="http://"+hostIP+":80/restaurant/BillDetailUpdate.php";
    public String hostBillVoid ="http://"+hostIP+":80/restaurant/BillVoid.php";

    public String hostFoodsInsert ="http://"+hostIP+":80/restaurant/FoodsInsert.php";
    public String hostFoodsUpdate ="http://"+hostIP+":80/restaurant/FoodsUpdate.php";
    public String hostFoodsSearch ="http://"+hostIP+":80/restaurant/FoodsSearch.php";
    public String hostAreaInsert ="http://"+hostIP+":80/restaurant/AreaInsert.php";
    public String hostAreaUpdate ="http://"+hostIP+":80/restaurant/AreaUpdate.php";
    public String hostAreaSelectByID ="http://"+hostIP+":80/restaurant/AreaSelectByID.php";
    public String hostTableInsert ="http://"+hostIP+":80/restaurant/TableInsert.php";
    public String hostTableUpdate ="http://"+hostIP+":80/restaurant/TableUpdate.php";
    public String hostTableSelectByID ="http://"+hostIP+":80/restaurant/TableSelectByID.php";
    public String hostResInsert ="http://"+hostIP+":80/restaurant/ResInsert.php";
    public String hostResUpdate ="http://"+hostIP+":80/restaurant/ResUpdate.php";
    public String hostResSelectByID ="http://"+hostIP+":80/restaurant/ResSelectByID.php";
    public String hostFoodsTypeInsert ="http://"+hostIP+":80/restaurant/FoodsTypeInsert.php";
    public String hostFoodsTypeUpdate ="http://"+hostIP+":80/restaurant/FoodsTypeUpdate.php";
    public String hostFoodsTypeSelectByID ="http://"+hostIP+":80/restaurant/FoodsTypeSelectByID.php";
    public String hostSelectFoods="http://"+hostIP+":80/restaurant/selectFoods.php";
    public String hostSelectFoodsByID="http://"+hostIP+":80/restaurant/selectFoodsByID.php";
    public String hostSelectFoodsByCode="http://"+hostIP+":80/restaurant/selectFoodsByCode.php";

    public String discount="0.0", SC="0.0", vat="0.0";

    public ArrayList<Foods> lFoo = new ArrayList<Foods>();
    public String jarrA, jarrT, jarrR, jarrF;
    //JSONArray jarrF1;
    //public List<Ta> lFoo = new ArrayList<Foods>();
    public RestaurantControl(){
        setCboTable();
        refresh();
    }
    public void refresh(){
        hostSaveOrder="http://"+hostIP+":80/restaurant/saveTOrder.php";

        hostGetArea="http://"+hostIP+":80/restaurant/getArea.php";
        hostGetTable="http://"+hostIP+":80/restaurant/getTable.php";
        hostGetRes="http://"+hostIP+":80/restaurant/getRes.php";
        hostGetFoodsType ="http://"+hostIP+":80/restaurant/getFoodsType.php";
        hostGetPrinterName ="http://"+hostIP+":80/restaurant/getPrinterName.php";
        hostGetFoods ="http://"+hostIP+":80/restaurant/getFoods.php";

        hostOrderByTableCode ="http://"+hostIP+":80/restaurant/OrderByTableCode.php";
        hostBillByTableId ="http://"+hostIP+":80/restaurant/BillByTableId.php";
        hostBillByBillCode ="http://"+hostIP+":80/restaurant/BillByBillCode.php";
        hostBillDetailByBillId ="http://"+hostIP+":80/restaurant/BillDetailByBillId.php";

        hostBillInsert ="http://"+hostIP+":80/restaurant/BillInsert.php";
        hostBillUpdate ="http://"+hostIP+":80/restaurant/BillUpdate.php";
        hostBillDetailInsert ="http://"+hostIP+":80/restaurant/BillDetailInsert.php";
        hostBillDetailUpdate ="http://"+hostIP+":80/restaurant/BillDetailUpdate.php";
        hostBillVoid ="http://"+hostIP+":80/restaurant/BillVoid.php";

        hostFoodsInsert ="http://"+hostIP+":80/restaurant/FoodsInsert.php";
        hostFoodsUpdate ="http://"+hostIP+":80/restaurant/FoodsUpdate.php";
        hostFoodsSearch ="http://"+hostIP+":80/restaurant/FoodsSearch.php";
        hostAreaInsert ="http://"+hostIP+":80/restaurant/AreaInsert.php";
        hostAreaUpdate ="http://"+hostIP+":80/restaurant/AreaUpdate.php";
        hostAreaSelectByID ="http://"+hostIP+":80/restaurant/AreaSelectByID.php";
        hostTableInsert ="http://"+hostIP+":80/restaurant/TableInsert.php";
        hostTableUpdate ="http://"+hostIP+":80/restaurant/TableUpdate.php";
        hostTableSelectByID ="http://"+hostIP+":80/restaurant/TableSelectByID.php";
        hostResInsert ="http://"+hostIP+":80/restaurant/ResInsert.php";
        hostResUpdate ="http://"+hostIP+":80/restaurant/ResUpdate.php";
        hostResSelectByID ="http://"+hostIP+":80/restaurant/ResSelectByID.php";
        hostFoodsTypeInsert ="http://"+hostIP+":80/restaurant/FoodsTypeInsert.php";
        hostFoodsTypeUpdate ="http://"+hostIP+":80/restaurant/FoodsTypeUpdate.php";
        hostFoodsTypeSelectByID ="http://"+hostIP+":80/restaurant/FoodsTypeSelectByID.php";
        hostSelectFoods="http://"+hostIP+":80/restaurant/selectFoods.php";
        hostSelectFoodsByID="http://"+hostIP+":80/restaurant/selectFoodsByID.php";
        hostSelectFoodsByCode="http://"+hostIP+":80/restaurant/selectFoodsByCode.php";
    }
    private void setCboTable(){
        //sCboTable.add("โต๊ะ 1");
        //sCboTable.add("โต๊ะ 2");
        //sCboTable.add("โต๊ะ 3");
        //sCboTable.add("โต๊ะ 4");
        //sCboTable.add("โต๊ะ 5");
        //sCboTable.add("โต๊ะ 6");
        //sCboTable.add("โต๊ะ 7");
        //sCboTable.add("โต๊ะ 8");
        //sCboTable.add("โต๊ะ 9");
        //sCboTable.add("โต๊ะ 10");
        //sCboTable.add("โต๊ะ 11");

        //sCboArea.add("ในร้าน");
        //sCboArea.add("ฟุตบาท");
        //sCboArea.add("ในสวน");
    }
    public String getArea(String areaName, String flag){
        String ab="";
        for(int i=0;i<sArea.size();i++){
            String[] aa = sArea.get(i).split("@");
            if(areaName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getAreaToName(String area, String flag){
        String ab="";
        for(int i=0;i<sArea.size();i++){
            String[] aa = sArea.get(i).split("@");
            if(flag.equals("code")){
                if(area.equals(aa[1])){
                    ab = aa[2];
                }
            }else{
                if(area.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
    public String getTable(String tableName, String flag){
        String ab="";
        for(int i=0;i<sTable.size();i++){
            String[] aa = sTable.get(i).split("@");
            if(tableName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getTableToName(String table, String flag){
        String ab="";
        for(int i=0;i<sTable.size();i++){
            String[] aa = sTable.get(i).split("@");
            if(flag.equals("code")){
                if(table.equals(aa[1])){
                    ab = aa[2];
                }
            }else{
                if(table.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
    public String getFoodsType(String foodsTypeName, String flag){
        String ab="";
        for(int i=0;i<sFoodsType.size();i++){
            String[] aa = sFoodsType.get(i).split("@");
            if(foodsTypeName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getFoodsTypeToName(String foodsType, String flag){
        String ab="";
        for(int i=0;i<sFoodsType.size();i++){
            String[] aa = sFoodsType.get(i).split("@");
            if(flag.equals("code")){
                if(foodsType.equals(aa[1])){
                    ab = aa[2];
                }
            }else{
                if(foodsType.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
    public String getRes(String resName, String flag){
        String ab="";
        for(int i=0;i<sRes.size();i++){
            String[] aa = sRes.get(i).split("@");
            if(resName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getResToName(String res, String flag){
        String ab="";
        for(int i=0;i<sRes.size();i++){
            String[] aa = sRes.get(i).split("@");
            if(flag.equals("code")){
                if(res.equals(aa[1])){
                    ab = aa[2];
                }
            }else{
                if(res.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
    public String getPrinterName(String printerName, String flag){
        String ab="";
        for(int i=0;i<sRes.size();i++){
            String[] aa = sRes.get(i).split("@");
            if(printerName.equals(aa[2])){
                if(flag.equals("ip")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getPrinterNameToName(String printerName, String flag){
        String ab="";
        for(int i=0;i<sRes.size();i++){
            String[] aa = sRes.get(i).split("@");
            if(flag.equals("ip")){
                if(printerName.equals(aa[1])){
                    ab = aa[2];
                }
            }else{
                if(printerName.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
    public void setInitial(FileOutputStream ot,String node, String data) {
        String xmlFile = "initial.xml";
        String userNAme = "username";
        String password = "password";
        try {
//            xmlFile = getResources().getAssets()+"initial.xml";
            FileOutputStream fos = new FileOutputStream("com.nakoyagarden.ekapop.restaurant.assets/initial.xml");
//            ot = getApplicationContext().openFileOutput(xmlFile, Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "Restaurant");
            xmlSerializer.startTag(null, node);
            xmlSerializer.text(data);
            xmlSerializer.endTag(null, node);
            xmlSerializer.endTag(null, "Restaurant");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            ot.write(dataWrite.getBytes());
            ot.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String getInitial(InputStream is,String node){
//        public List<Employee> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        String aa="", text="";
        try {
//            InputStream is = getAssets().open("initial.xml");
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("employee")) {
                            // create a new instance of employee
//                                employee = new Employee();
                        }
                        break;

                    case XmlPullParser.TEXT:
                            text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(node)) {
                            aa = text;
                            // add employee object to list
//                                employees.add(employee);
                        } else if (tagname.equalsIgnoreCase("name")) {
//                                employee.setName(text);
                        } else if (tagname.equalsIgnoreCase("id")) {
//                                employee.setId(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("department")) {
//                                employee.setDepartment(text);
                        } else if (tagname.equalsIgnoreCase("email")) {
//                                employee.setEmail(text);
                        } else if (tagname.equalsIgnoreCase("type")) {
//                                employee.setType(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aa;
    }
    public void setHostName(String hostname){
        String fileName = "initial.cnf";
        FileOutputStream outputStream;
//        String string = hostname;
        try {
            outputStream = openFileOutput("initial.cnf", Context.MODE_PRIVATE);
            outputStream.write(hostname.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static String getHostName (String filename) throws Exception {
//        try {
//            InputStream is = getAssets().open(filename);
//            int size = is.available();
//            byte buffer[] = new byte[size];
//            is.read(buffer);
//            is.close();
//            return new String(buffer);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "" ;
//        }
//    }
}
