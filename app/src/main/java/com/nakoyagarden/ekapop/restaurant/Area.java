package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 9/1/2016.
 */
public class Area extends Database{
    public String ID="", Code="", Name="", Remark="", Active="", Sort1="", VoidDate="", VoidUser="";
    public String dbID="area_id", dbCode="area_code", dbName="area_name", dbRemark="remark", dbVoidDate="void_date", dbVoidUser="void_user";
    public String dbActive="active", dbSort1="sort1";

    //ALTER TABLE `b_user` ADD `ttttt` VARCHAR(255) NULL AFTER `permission_void_closeday`;
    /**
     * CREATE TABLE `restaurant`.`test`
     * ( `aa` VARCHAR(255) NOT NULL
     * , `bb` VARCHAR(255) NULL
     * , `cc` DECIMAL(17,2) NULL
     * , `dd` DECIMAL(17,2) NULL
     * , PRIMARY KEY (`aa`))
     * ENGINE = MyISAM
     * CHARACTER SET utf8 COLLATE utf8_bin
     * COMMENT = 'mmmmmmm';
     */

    public String cArea=creaT+" '"+dbNameD+"'.'"+tbNameArea+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbCode+"' "+varc+"  NULL "
            +", '"+dbName+"' "+varc+"  NULL "
            +", '"+dbRemark+"' "+varc+"  NULL "
            +", '"+dbActive+"' "+varc+"  NULL "
            +", '"+dbSort1+"' "+varc+"  NULL "
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cAreaSQLi=creaT+" "+tbNameArea+" "
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
    public String cDropArea="DROP TABLE IF EXISTS "+tbNameArea+" ;";
}
