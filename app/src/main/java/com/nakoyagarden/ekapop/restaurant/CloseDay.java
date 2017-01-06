package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 11/23/2016.
 */

public class CloseDay extends Database{
    public String ID="", CloseDayDate="", Active="", Remark="", StatusVoid="", VoidDate="", ResId="", Amt="", Discount="", SC="";
    public String Vat="", Total="", NetTotal="", Cnt="", VoidUser="", CntBill="", CntOrder="", AmtOrder="", CloseDayUser="", CashR1="", CashR2="", CashR3="", CashR1Remark ="", CashR2Remark="";
    public String  CashR3Remark="", CashD1="", CashD2="", CashD3="", CashD1Remark="", CashD2Remark="", CashD3Remark="",Weather="";

    public String dbID="closeday_id", dbCloseDayDate="closeday_date", dbActive="active", dbRemark="remark", dbStatusVoid="status_void", dbVoidDate="void_date", dbResId="res_id", dbAmt="amount", dbDiscount="discount", dbSC="service_charge";
    public String dbVat="vat", dbTotal="total", dbNetTotal="nettotal", dbCnt="", dbVoidUser="void_user", dbCntBill="cnt_bill", dbCntOrder="cnt_order", dbAmtOrder="amount_order", dbCloseDayUser="closeday_user";
    public String dbCashR1="cash_receive1", dbCashR2="cash_receive2", dbCashR3="cash_receive3", dbCashR1Remark ="cash_receive1_remark", dbCashR2Remark="cash_receive2_remark";
    public String dbCashR3Remark="cash_receive3_remark", dbCashD1="cash_draw1", dbCashD2="cash_draw2", dbCashD3="cash_draw3", dbCashD1Remark="cash_draw1_remark", dbCashD2Remark="cash_draw2_remark", dbCashD3Remark="cash_draw3_remark";
    public String dbAmtBill="bill_amount", dbWeather="weather";

    public String cClosedayMySQL=creaT+" '"+dbNameD+"'.'"+tbNameCloseday+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbCloseDayDate+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbActive+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRemark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbStatusVoid+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbVoidDate+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbVoidUser+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbResId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbCloseDayUser+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbAmt+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbDiscount+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbSC+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbVat+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbTotal+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbNetTotal+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCntBill+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCntOrder+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbAmtOrder+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbAmtBill+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCashR1+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCashR2+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCashR3+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCashR1Remark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbCashR2Remark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbCashR3Remark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbCashD1+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCashD2+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCashD3+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbCashD1Remark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbCashD2Remark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbCashD3Remark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbWeather+"' "+varc+"  NULL "+ defMySQL_v
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cClosedaySQLi=creaT+" "+tbNameCloseday+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbCloseDayDate+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +", "+dbStatusVoid+" "+tex+"  NULL "
            +", "+dbVoidDate+" "+tex+"  NULL "
            +", "+dbVoidUser+" "+tex+"  NULL "
            +", "+dbResId+" "+tex+"  NULL "
            +", "+dbCloseDayUser+" "+tex+"  NULL "
//            +", "+dbResId+" "+tex+"  NULL "
            +", "+dbAmt+" "+rea+"  NULL "
            +", "+dbDiscount+" "+rea+"  NULL "
            +", "+dbSC+" "+rea+"  NULL "
            +", "+dbVat+" "+rea+"  NULL "
            +", "+dbTotal+" "+rea+"  NULL "
//            +", "+dbTotal+" "+rea+"  NULL "
            +", "+dbNetTotal+" "+rea+"  NULL "
            +", "+dbCntBill+" "+rea+"  NULL "
            +", "+dbCntOrder+" "+rea+"  NULL "
            +", "+dbAmtOrder+" "+rea+"  NULL "
            +", "+dbAmtBill+" "+rea+"  NULL "
            +", "+dbCashR1+" "+rea+"  NULL "
            +", "+dbCashR2+" "+rea+"  NULL "
            +", "+dbCashR3+" "+rea+"  NULL "
            +", "+dbCashR1Remark+" "+tex+"  NULL "
            +", "+dbCashR2Remark+" "+tex+"  NULL "
            +", "+dbCashR3Remark+" "+tex+"  NULL "
            +", "+dbCashD1+" "+rea+"  NULL "
            +", "+dbCashD2+" "+rea+"  NULL "
            +", "+dbCashD3+" "+rea+"  NULL "
            +", "+dbCashD1Remark+" "+tex+"  NULL "
            +", "+dbCashD2Remark+" "+tex+"  NULL "
            +", "+dbCashD3Remark+" "+tex+"  NULL "
            +", "+dbWeather+" "+tex+"  NULL "
            +", "+dbDateCreate+" "+tex+"  NULL "
            +", "+dbDateModi+" "+tex+"  NULL "
            +", "+dbHostId+" "+tex+"  NULL "
            +", "+dbBranchId+" "+tex+"  NULL "
            +", "+dbDeviceId+" "+tex+"  NULL "
            +"); ";
    public String cDropCloseday="DROP TABLE IF EXISTS "+tbNameCloseday+" ;";
}
