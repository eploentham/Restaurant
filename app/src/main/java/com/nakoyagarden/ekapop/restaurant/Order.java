package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 8/23/2016.
 */
public class Order extends Database{
    public String ID="", LotId="", FoodsId="", FoodsCode="", FoodsName="", OrderDate="", Price="", Qty="", Remark="",ResCode="", DeviceId="", AreaCode="", StatusFoods1="", StatusFoods2="";
    public String StatusFoods3="", StatusBill="", BillCheckDate="", StatusCook="", CookReceiveDate="", CookFinishDate="", Active="", VoidDate="", StatusVoid="", row1="", PrinterName="";
    public String StatusToGo="", FlagVoid ="", ClosedayId="", StatusCloseday="", OrderUser="", TableCode="", TableId="";
    public String Amt="", CntCust="";


    public String dbID="order_id", dbLotId="lot_id", dbFoodsId="foods_id", dbFoodsCode="foods_code", dbFoodsName="foods_name", dbOrderDate="order_date", dbPrice="price", dbQty="qty", dbRemark="remark";
    public String dbResCode="res_code", dbAreaCode="area_code", dbStatusFoods1="status_foods_1", dbStatusFoods2="status_foods_2";
    public String dbStatusFoods3="status_foods_3", dbStatusBill="status_bill", dbBillCheckDate="bill_check_date", dbStatusCook="status_cook", dbCookReceiveDate="cook_receive_date";
    public String dbCookFinishDate="cook_finish_date", dbActive="active", dbVoidDate="void_date", dbrow1="row1", dbPrinterName="printer_name";
    public String dbStatusToGo="status_to_go", dbFlagVoid ="flag_void", dbOrderUser="order_user", dbTableCode="table_code", dbTableId="table_id";
    public String dbAmt="amount", dbCntCust="cnt_cust", dbStatusVoid="status_void", dbClosedayId="closeday_id", dbStatusCloseday="status_closeday";

    public String cOrderMySQL=creaT+" '"+dbNameD+"'.'"+tbNameOrder+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbLotId+"' "+varc+"  NULL "
            +", '"+dbrow1+"' "+varc+"  NULL "
            +", '"+dbFoodsId+"' "+varc+"  NULL "
            +", '"+dbFoodsCode+"' "+varc+"  NULL "
            +", '"+dbFoodsName+"' "+varc+"  NULL "
            +", '"+dbOrderDate+"' "+varc+"  NULL "
            +", '"+dbPrice+"' "+deci+"  NULL "
            +", '"+dbQty+"' "+deci+"  NULL "
            +", '"+dbAmt+"' "+deci+"  NULL "
            +", '"+dbRemark+"' "+varc+"  NULL "
            +", '"+dbResCode+"' "+varc+"  NULL "
            +", '"+dbAreaCode+"' "+varc+"  NULL "
            +", '"+dbTableCode+"' "+varc+"  NULL "
            +", '"+dbStatusFoods1+"' "+varc+"  NULL "
            +", '"+dbStatusFoods2+"' "+varc+"  NULL "
            +", '"+dbStatusFoods3+"' "+varc+"  NULL "
            +", '"+dbStatusBill+"' "+varc+"  NULL "
            +", '"+dbBillCheckDate+"' "+varc+"  NULL "
            +", '"+dbStatusCook+"' "+varc+"  NULL "
            +", '"+dbCookReceiveDate+"' "+varc+"  NULL "
            +", '"+dbCookFinishDate+"' "+varc+"  NULL "
            +", '"+dbActive+"' "+varc+"  NULL "
            +", '"+dbVoidDate+"' "+varc+"  NULL "
            +", '"+dbStatusVoid+"' "+varc+"  NULL "
            +", '"+dbPrinterName+"' "+varc+"  NULL "
            +", '"+dbStatusToGo+"' "+varc+"  NULL "
            +", '"+dbFlagVoid+"' "+varc+"  NULL "
            +", '"+dbOrderUser+"' "+varc+"  NULL "
            +", '"+dbTableId+"' "+varc+"  NULL "
            +", '"+dbCntCust+"' "+varc+"  NULL "
            +", '"+dbClosedayId+"' "+varc+"  NULL "
            +", '"+dbStatusCloseday+"' "+varc+"  NULL "
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cOrderSQLi=creaT+" "+tbNameOrder+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbLotId+" "+tex+"  NULL "
            +", "+dbrow1+" "+tex+"  NULL "
            +", "+dbFoodsId+" "+tex+"  NULL "
            +", "+dbFoodsCode+" "+tex+"  NULL "
            +", "+dbFoodsName+" "+tex+"  NULL "
            +", "+dbOrderDate+" "+tex+"  NULL "
            +", "+dbPrice+" "+rea+"  NULL "
            +", "+dbQty+" "+rea+"  NULL "
            +", "+dbAmt+" "+rea+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +", "+dbResCode+" "+tex+"  NULL "
            +", "+dbAreaCode+" "+tex+"  NULL "
            +", "+dbTableCode+" "+tex+"  NULL "
            +", "+dbStatusFoods1+" "+tex+"  NULL "
            +", "+dbStatusFoods2+" "+tex+"  NULL "
            +", "+dbStatusFoods3+" "+tex+"  NULL "
            +", "+dbStatusBill+" "+tex+"  NULL "
            +", "+dbBillCheckDate+" "+tex+"  NULL "
            +", "+dbStatusCook+" "+tex+"  NULL "
            +", "+dbCookReceiveDate+" "+tex+"  NULL "
            +", "+dbCookFinishDate+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbVoidDate+" "+tex+"  NULL "
            +", "+dbStatusVoid+" "+tex+"  NULL "
            +", "+dbPrinterName+" "+tex+"  NULL "
            +", "+dbStatusToGo+" "+tex+"  NULL "
            +", "+dbFlagVoid+" "+tex+"  NULL "
            +", "+dbOrderUser+" "+tex+"  NULL "
            +", "+dbTableId+" "+tex+"  NULL "
            +", "+dbCntCust+" "+tex+"  NULL "
            +", "+dbClosedayId+" "+tex+"  NULL "
            +", "+dbStatusCloseday+" "+tex+"  NULL "
            +"); ";
    public String cDropOrder="DROP TABLE IF EXISTS "+tbNameOrder+" ;";
}
