package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 12/27/2016.
 */

public class Database {
    public String dbNameD="restaurant",tbNameArea="b_area", tbNameFoods="b_foods",tbNameFoodsType="b_foods_type", tbNamePrinterName="b_printername", tbNameRes="b_restaurant";
    public String tbNameTable="b_table",tbNameUser="b_user", tbNameBill="t_bill",tbNameBillDetail="t_bill_detail", tbNameCloseday="t_closeday",tbNameOrder="t_order";
    public String tbNameTableChange="t_tablechange";

    public String dbDateCreate="date_create", dbDateModi="date_modi", dbHostId="host_id", dbBranchId="branch_id", dbDeviceId="device_id";

    public String varc=" varchar(255)  ";
    public String deci=" DECIMAL(17,2)  ";
    public String tex=" TEXT  ";
    public String rea=" REAL  ";

    public String alterT="ALTER TABLE ", creaT="CREATE TABLE ";
    public String defMySQL_v = "DEFAULT ''", defMySQL_d ="DEFAULT 0", defSQLi_S="";
    //ALTER TABLE `b_user` ADD `ttttt` VARCHAR(255) NULL AFTER `permission_void_closeday`;
}
