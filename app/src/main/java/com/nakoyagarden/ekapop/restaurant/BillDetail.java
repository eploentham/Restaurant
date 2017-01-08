package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 9/14/2016.
 */
public class BillDetail extends Database{
    public String ID="", BillId="", ORderId="", StatusVoid="", Row1="", FoodsId="", FoodsCode="", Price="", Qty="", Amt="",Active="", LotID="",Remark="", BillCode="",TableId="", FoodsName="";

    public String dbID="bill_detail_id", dbBillId="bill_id", dbORderId="order_id", dbStatusVoid="status_id", dbRow1="row1", dbFoodsId="foods_id", dbFoodsCode="foods_code", dbPrice="price", dbQty="qty", dbAmt="amount";
    public String dbFlagVoid="flag_void", dbActive="active", dbLotID="lot_id",dbRemark="remark", dbBillCode="bill_code", dbTableId="table_id", dbFoodsName="foods_name";

    public String cBillDetailMySQL=creaT+" '"+dbNameD+"'.'"+tbNameBillDetail+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbBillId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRow1+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbORderId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbStatusVoid+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbFoodsId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbFoodsCode+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrice+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbQty+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbAmt+"' "+deci+"  NULL "+ defMySQL_d
            +", '"+dbActive+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbLotID+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRemark+"' "+varc+"  NULL "+ defMySQL_v
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cBillDetailSQLi=creaT+" "+tbNameBillDetail+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbBillId+" "+tex+"  NULL "
            +", "+dbRow1+" "+tex+"  NULL "
            +", "+dbORderId+" "+tex+"  NULL "
            +", "+dbStatusVoid+" "+tex+"  NULL "
            +", "+dbFoodsId+" "+tex+"  NULL "
            +", "+dbFoodsCode+" "+tex+"  NULL "
            +", "+dbPrice+" "+rea+"  NULL "
            +", "+dbQty+" "+rea+"  NULL "
            +", "+dbAmt+" "+rea+"  NULL "
            +", "+dbDateCreate+" "+tex+"  NULL "
            +", "+dbDateModi+" "+tex+"  NULL "
            +", "+dbHostId+" "+tex+"  NULL "
            +", "+dbBranchId+" "+tex+"  NULL "
            +", "+dbDeviceId+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbLotID+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +"); ";
    public String cDropBillDetail="DROP TABLE IF EXISTS "+tbNameBillDetail+" ;";
}
