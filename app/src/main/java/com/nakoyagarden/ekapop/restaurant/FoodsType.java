package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 9/6/2016.
 */
public class FoodsType extends Database{
    public String ID="", Code="", Name="", Remark="", Active="", Sort1="", VoidDate="", VoidUser="";
    public String dbID="foods_type_id", dbCode="foods_type_code", dbName="foods_type_name", dbRemark="remark", dbActive="active", dbSort1="sort1", dbVoidDate="void_date", dbVoidUser="void_user";

    public String cFoodsTypeMySQL=creaT+" '"+dbNameD+"'.'"+tbNameFoodsType+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbCode+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbName+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRemark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbActive+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbSort1+"' "+varc+"  NULL "+ defMySQL_v
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cFoodsTypeSQLi=creaT+" "+tbNameFoodsType+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbCode+" "+tex+"  NULL "
            +", "+dbName+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbSort1+" "+tex+"  NULL "
            +", "+dbDateCreate+" "+tex+"  NULL "
            +", "+dbDateModi+" "+tex+"  NULL "
            +", "+dbHostId+" "+tex+"  NULL "
            +", "+dbBranchId+" "+tex+"  NULL "
            +", "+dbDeviceId+" "+tex+"  NULL "
            +", "+dbVoidDate+" "+tex+"  NULL "
            +", "+dbVoidUser+" "+tex+"  NULL "
            +"); ";
    public String cDropFoodsType="DROP TABLE IF EXISTS "+tbNameFoodsType+" ;";
}
