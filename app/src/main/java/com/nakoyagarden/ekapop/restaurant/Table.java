package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 9/1/2016.
 */
public class Table extends  Database{
    public String ID="", Code="", Name="", Remark="", Active="", Sort1="", AreaID="", StatusUse="", DateUse="", StatusToGo="";
    public String dbID="table_id", dbCode="table_code", dbName="table_name", dbRemark="remark", dbActive="active", dbSort1="sort1", dbAreaID="area_id", dbStatusUse="status_use", dbDateUse="date_use", dbStatusToGo="status_togo";

    public String cTableMySQL=creaT+" '"+dbNameD+"'.'"+tbNameTable+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbCode+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbName+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRemark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbActive+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbSort1+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbAreaID+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbStatusUse+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbDateUse+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbStatusToGo+"' "+varc+"  NULL "+ defMySQL_v
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cTableSQLi=creaT+" "+tbNameTable+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbCode+" "+tex+"  NULL "
            +", "+dbName+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbSort1+" "+tex+"  NULL "
            +", "+dbAreaID+" "+tex+"  NULL "
            +", "+dbStatusUse+" "+tex+"  NULL "
            +", "+dbDateUse+" "+tex+"  NULL "
            +", "+dbDateCreate+" "+tex+"  NULL "
            +", "+dbDateModi+" "+tex+"  NULL "
            +", "+dbHostId+" "+tex+"  NULL "
            +", "+dbBranchId+" "+tex+"  NULL "
            +", "+dbDeviceId+" "+tex+"  NULL "
            +", "+dbStatusToGo+" "+tex+"  NULL "
            +"); ";
    public String cDropTable="DROP TABLE IF EXISTS "+tbNameTable+" ;";

}
