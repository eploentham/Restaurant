package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 9/14/2016.
 */
public class Bill extends Database{
    public String ID="", Code="", BillDate="", LotID="", Active="", Remark="", StatusVoid="", VoidDate="", TableId="", ResId="", AreaId="", DeviceId="", Amt="", Discount="", SC="";
    public String Vat="", Total="", NetTotal="", Cnt="", CashReceive="", CashTon="", VoidUser="", BillUser="", ClosedayId="";

    public String dbID="bill_id", dbCode="bill_code", dbBillDate="bill_date", dbLotID="lot_id", dbActive="active", dbRemark="remark", dbStatusVoid="status_void";
    public String dbVoidDate="void_date", dbTableId="table_id", dbResId="res_id", dbAreaId="area_id", dbAmt="amount", dbDiscount="discount", dbSC="service_charge";
    public String dbVat="vat", dbTotal="total", dbNetTotal="nettotal", dbCnt="", dbCashReceive="cash_receive", dbCashTon="cash_ton", dbStatusCloseday="status_closeday";
    public String dbVoidUser="void_user", dbBillUser="bill_user", dbClosedayId="closeday_id";

    public String cBillMySQL=creaT+" '"+dbNameD+"'.'"+tbNameBill+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbCode+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbBillDate+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbLotID+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbActive+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRemark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbStatusVoid+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbVoidDate+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbTableId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbResId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbAreaId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbAmt+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbDiscount+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbSC+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbVat+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbTotal+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbNetTotal+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCashReceive+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCashTon+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbStatusCloseday+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbVoidUser+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbBillUser+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbClosedayId+"' "+varc+"  NULL "+ defMySQL_v
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cBillSQLi=creaT+" "+tbNameBill+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbCode+" "+tex+"  NULL "
            +", "+dbBillDate+" "+tex+"  NULL "
            +", "+dbLotID+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +", "+dbStatusVoid+" "+tex+"  NULL "
            +", "+dbVoidDate+" "+tex+"  NULL "
            +", "+dbTableId+" "+tex+"  NULL "
            +", "+dbResId+" "+tex+"  NULL "
            +", "+dbAreaId+" "+tex+"  NULL "
            +", "+dbAmt+" "+rea+"  NULL "
            +", "+dbDiscount+" "+rea+"  NULL "
            +", "+dbSC+" "+rea+"  NULL "
            +", "+dbVat+" "+rea+"  NULL "
            +", "+dbTotal+" "+rea+"  NULL "
            +", "+dbNetTotal+" "+rea+"  NULL "
            +", "+dbCashReceive+" "+rea+"  NULL "
            +", "+dbCashTon+" "+rea+"  NULL "
            +", "+dbStatusCloseday+" "+tex+"  NULL "
            +", "+dbVoidUser+" "+tex+"  NULL "
            +", "+dbBillUser+" "+tex+"  NULL "
            +", "+dbClosedayId+" "+tex+"  NULL "
            +", "+dbDateCreate+" "+tex+"  NULL "
            +", "+dbDateModi+" "+tex+"  NULL "
            +", "+dbHostId+" "+tex+"  NULL "
            +", "+dbBranchId+" "+tex+"  NULL "
            +", "+dbDeviceId+" "+tex+"  NULL "
            +"); ";
    public String cDropBill="DROP TABLE IF EXISTS "+tbNameBill+" ;";

}
