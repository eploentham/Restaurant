<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_GET['userdb'],$_GET['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
$date1 = $_GET['closeday_date']." 00:00:00";
$date2 = $_GET['closeday_date']." 23:59:59";
//$objQuery = mysql_query("Select * From t_bill Where active = '1' and bill_code = '".$_GET['bill_code']."' ");
$wheredate=" and closeday_date >= '".$date1."' and closeday_date <= '".$date2."'";
$sql = "Select * From t_closeday Where res_id = '".$_GET['res_id']."' and active ='1'  ".$wheredate;
//echo $sql;
//rerturn;
$obj = mysql_query($sql);
$intNumRows = mysql_num_rows($obj);
//echo "intNumField=".$intNumField;
//return;
$tmp = array();
if($intNumRows==0){
    
    echo "cccccccccccc ".$sql;
    //return;
    $wheredate=" and bill_date >= '".$date1."' and bill_date <= '".$date2."'";
    $sql = "Select sum(amount) as amt, sum(discount) as discount, sum(total) as total, sum(service_charge) as sc, sum(vat) as vat, sum(nettotal) as nettotal, count(1) as cnt_bill From t_bill Where status_closeday <> '1' ".$wheredate." and active = '1'";
    $objQuery = mysql_query($sql);
    $intNumRows = mysql_num_rows($objQuery);
    while($row = mysql_fetch_array($objQuery)){
        //$arrCol = array();
        

        $tmp["closeday_id"] = "";
        $tmp["amt"] = $row["amt"];
        $tmp["discount"] = $row["discount"];
        $tmp["total"] = $row["total"];
        $tmp["sc"] = $row["sc"];
        $tmp["vat"] = $row["vat"];
        $tmp["nettotal"] = $row["nettotal"];
        $tmp["cnt_bill"] = $row["cnt_bill"];

        $tmp["remark"] = "";
        //$tmp["cnt_bill"] = "";
        $tmp["cnt_order"] = "";
        $tmp["amt_order"] = "";
        $tmp["cash_receive1"] = "";
        $tmp["cash_receive2"] = "";
        $tmp["cash_receive3"] = "";
        $tmp["cash_draw1"] = "";
        $tmp["cash_draw2"] = "";
        $tmp["cash_draw3"] = "";
        $tmp["cash_receive1_remark"] = "";
        $tmp["cash_receive2_remark"] = "";
        $tmp["cash_receive3_remark"] = "";
        $tmp["closeday_user"] = "";
        
        array_push($resultArray,$tmp);
    }
    $wheredate=" and order_date >= '".$date1."' and order_date <= '".$date2."'";
    $sql = "Select count(1) as cnt_order, sum(price*qty) as amt_order From t_order Where status_closeday <> '1' ".$wheredate." and active = '1'";
    $objQuery = mysql_query($sql);
    $intNumRows = mysql_num_rows($objQuery);
    while($row = mysql_fetch_array($objQuery)){
        $tmp["cnt_order"] = $row["cnt_order"];
        $tmp["amt_order"] = $row["amt_order"];
    }
}else{
    //return;
    //$sql = "Select * From t_closeday Where ";
    $intNumRows = mysql_num_rows($obj);
    echo "bbbbbbbbbbbb ".$intNumRows;
    while($row = mysql_fetch_array($obj)){
        //$arrCol = array();
        //$tmp = array();
        echo "bbbbbbbbbbbb 0000000";
        $tmp["closeday_id"] = $row["closeday_id"];
        $tmp["closeday_date"] = $row["closeday_date"];
        $tmp["res_id"] = $row["res_id"];
        $tmp["amt"] = $row["amount"];
        $tmp["discount"] = $row["discount"];
        $tmp["total"] = $row["total"];
        $tmp["sc"] = $row["service_charge"];
        $tmp["vat"] = $row["vat"];
        $tmp["nettotal"] = $row["nettotal"];
        $tmp["remark"] = $row["remark"];
        $tmp["cnt_bill"] = $row["cnt_bill"];
        $tmp["cnt_order"] = $row["cnt_order"];
        $tmp["amt_order"] = $row["amount_order"];
        $tmp["cash_receive1"] = $row["cash_receive1"];
        $tmp["cash_receive2"] = $row["cash_receive2"];
        $tmp["cash_receive3"] = $row["cash_receive3"];
        $tmp["cash_draw1"] = $row["cash_draw1"];
        $tmp["cash_draw2"] = $row["cash_draw2"];
        $tmp["cash_draw3"] = $row["cash_draw3"];
        $tmp["cash_receive1_remark"] = $row["cash_receive1_remark"];
        $tmp["cash_receive2_remark"] = $row["cash_receive2_remark"];
        $tmp["cash_receive3_remark"] = $row["cash_receive3_remark"];
        $tmp["closeday_user"] = $row["closeday_user"];
		echo "bbbbbbbbbbbb 111111";
        array_push($resultArray,$tmp);
    }
}

mysql_close($objConnect);

header('Content-Type: application/json');
echo json_encode($resultArray);

	//$pid = $_POST['order_id'];
	//$name = $_POST['foods_code'];
	//$price = $_POST['price'];
	//$description = $_POST['description'];
	//$conn->getArea();

?>
