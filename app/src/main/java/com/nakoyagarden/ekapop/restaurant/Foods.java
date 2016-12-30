package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 8/23/2016.
 */
public class Foods extends Database{
    public String ID="", Code="", Name="", Price="", Active="",TypeId="", Remark="", ResId="", StatusFoods="", PrinterName="", ResCode="";

    public String dbID="foods_id", dbCode="foods_code", dbName="foods_name", dbPrice="foods_price", dbActive="active",dbTypeId="foods_type_id";
    public String dbRemark="remark", dbResId="res_id", dbStatusFoods="status_foods", dbPrinterName="printer_name",dbResCode="res_code";

    public String cFoodsMySQL=creaT+" '"+dbNameD+"'.'"+tbNameFoods+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbCode+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbName+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrice+"' "+deci+"  NULL "+ defMySQL_v
            +", '"+dbActive+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbTypeId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRemark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbResId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbStatusFoods+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrinterName+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbResCode+"' "+varc+"  NULL "+ defMySQL_v
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cFoodsSQLi=creaT+" "+tbNameFoods+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbCode+" "+tex+"  NULL "
            +", "+dbName+" "+tex+"  NULL "
            +", "+dbPrice+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbTypeId+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +", "+dbResId+" "+tex+"  NULL "
            +", "+dbStatusFoods+" "+tex+"  NULL "
            +", "+dbPrinterName+" "+tex+"  NULL "
            +", "+dbResCode+" "+tex+"  NULL "
            +"); ";
    public String cDropFoods="DROP TABLE IF EXISTS "+tbNameFoods+" ;";
}
