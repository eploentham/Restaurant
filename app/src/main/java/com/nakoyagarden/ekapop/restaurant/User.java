package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 12/1/2016.
 */

public class User extends Database{
    public String ID="", Login="", Name="", Password1 ="",Active="", Remark="", Privilege="", Sort1="", VoidBill="",VoidCloseday="";

    public String dbID="user_id", dbLogin="user_login", dbName="user_name", dbPassword1="password1",dbActive="active", dbRemark="remark";
    public String dbPrivilege="privilege", dbSort1="sort1", dbVoidBill="permission_void_bill",dbVoidCloseday="permission_void_closeday";

    public String cUserMySQL=creaT+" '"+dbNameD+"'.'"+tbNameUser+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbLogin+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbName+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRemark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbActive+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPassword1+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrivilege+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbSort1+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbVoidBill+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbVoidCloseday+"' "+varc+"  NULL "+ defMySQL_v
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cUserSQLi=creaT+" "+tbNameUser+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbLogin+" "+tex+"  NULL "
            +", "+dbName+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbPassword1+" "+tex+"  NULL "
            +", "+dbPrivilege+" "+tex+"  NULL "
            +", "+dbSort1+" "+tex+"  NULL "
            +", "+dbVoidBill+" "+tex+"  NULL "
            +", "+dbVoidCloseday+" "+tex+"  NULL "
            +"); ";
    public String cDropUser="DROP TABLE IF EXISTS "+tbNameArea+" ;";
}
