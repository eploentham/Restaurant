package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 1/9/2017.
 */

public class TableChange extends Database{
    public String ID="", TableIdFrom="",TableIdTo="", TableChangeDate="", TableChangeUser="", Active="", DateCreate="", DateModi="";
    public String HostId="", BranchId="", DeviceId="", VoidUser="", VoidDate="";

    public String dbID="tablechange_id", dbTableIdFrom="table_id_from",dbTableIdTo="table_id_to", dbTableChangeDate="tablechange_date";
    public String dbTableChangeUser="tablechange_user", dbActive="active", dbDateCreate="date_create", dbDateModi="date_modi";
    public String dbHostId="host_id", dbBranchId="branch_id", dbDeviceId="device_id", dbVoidUser="void_user", dbVoidDate="void_date";
    public String dbTableCodeTo="table_code_to", dbTableCodeFrom="table_code_from";

    public String cTableChangeSQLi=creaT+" "+tbNameTableChange+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbTableIdFrom+" "+tex+"  NULL "
            +", "+dbTableIdTo+" "+tex+"  NULL "
            +", "+dbTableChangeDate+" "+tex+"  NULL "
            +", "+dbTableChangeUser+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbDateCreate+" "+tex+"  NULL "
            +", "+dbDateModi+" "+tex+"  NULL "
            +", "+dbHostId+" "+tex+"  NULL "
            +", "+dbBranchId+" "+tex+"  NULL "
            +", "+dbDeviceId+" "+tex+"  NULL "
            +", "+dbVoidDate+" "+tex+"  NULL "
            +", "+dbVoidUser+" "+tex+"  NULL "
            +"); ";
    public String cDropTableChange="DROP TABLE IF EXISTS "+tbNameTableChange+" ;";
}
