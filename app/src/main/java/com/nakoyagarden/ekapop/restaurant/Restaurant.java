package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 9/2/2016.
 */
public class Restaurant extends Database{
    public String ID="", Code="", Name="", Remark="", Active="", Sort1="",TaxID="", PrinterOrder1="", PrinterOrder2="", PrinterOrder3, PrinterWaterBar1="", PrinterWaterBar2="", PrinterWaterBar3="";
    public String RH1="", RH2="",RF1="",RF2="",BillCode="",DefaultRes="";

    public String dbID="res_id", dbCode="res_code", dbName="res_name", dbRemark="remark", dbActive="active", dbSort1="sort1",dbTaxID="tax_id";
    public String dbPrinterOrder1="printer_foods1", dbPrinterOrder2="printer_foods2", dbPrinterOrder3="printer_foods3", dbPrinterWaterBar1="printer_waterbar1", dbPrinterWaterBar2="printer_waterbar2", dbPrinterWaterBar3="printer_waterbar3";
    public String dbRH1="receipt_header1", dbRH2="receipt_header2",dbRF1="receipt_footer1",dbRF2="receipt_footer2",dbBillCode="bill_code",dbDefaultRes="default_res", dbBillMonth="bill_month";

    public String cResMySQL=creaT+" '"+dbNameD+"'.'"+tbNameRes+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbCode+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbName+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRemark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbActive+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbTaxID+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrinterOrder1+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrinterOrder2+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrinterOrder3+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrinterWaterBar1+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrinterWaterBar2+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrinterWaterBar3+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRH1+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRH2+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRF1+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRF2+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbBillCode+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbDefaultRes+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbBillMonth+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbSort1+"' "+varc+"  NULL "+ defMySQL_v
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cResSQLi=creaT+" "+tbNameRes+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbCode+" "+tex+"  NULL "
            +", "+dbName+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbTaxID+" "+tex+"  NULL "
            +", "+dbPrinterOrder1+" "+tex+"  NULL "
            +", "+dbPrinterOrder2+" "+tex+"  NULL "
            +", "+dbPrinterOrder3+" "+tex+"  NULL "
            +", "+dbPrinterWaterBar1+" "+tex+"  NULL "
            +", "+dbPrinterWaterBar2+" "+tex+"  NULL "
            +", "+dbPrinterWaterBar3+" "+tex+"  NULL "
            +", "+dbRH1+" "+tex+"  NULL "
            +", "+dbRH2+" "+tex+"  NULL "
            +", "+dbRF1+" "+tex+"  NULL "
            +", "+dbRF2+" "+tex+"  NULL "
            +", "+dbBillCode+" "+tex+"  NULL "
            +", "+dbDefaultRes+" "+tex+"  NULL "
            +", "+dbBillMonth+" "+tex+"  NULL "
            +", "+dbSort1+" "+tex+"  NULL "
            +"); ";
    public String cDropRes="DROP TABLE IF EXISTS "+tbNameRes+" ;";
}
