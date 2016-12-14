<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
$objQuery = mysql_query("Select * From t_bill Where active = '1' and table_id like '".$_POST['table_id']."' and bill_date >= '".$_POST['bill_date1']."' and bill_date <= '".$_POST['bill_date2']."' Order By bill_date");
$intNumField = mysql_num_fields($objQuery);
while($row = mysql_fetch_array($objQuery)){
	//$arrCol = array();
	$tmp = array();
    $tmp["bill_id"] = $row["bill_id"];
    $tmp["bill_code"] = $row["bill_code"];
    $tmp["bill_date"] = $row["bill_date"];
    $tmp["lot_id"] = $row["lot_id"];
    $tmp["active"] = $row["active"];
    $tmp["remark"] = $row["remark"];
    $tmp["status_void"] = $row["status_void"];
    $tmp["void_date"] = $row["void_date"];
    $tmp["table_id"] = $row["table_id"];
    $tmp["res_id"] = $row["res_id"];
    $tmp["area_id"] = $row["area_id"];
    $tmp["device_id"] = $row["device_id"];
    $tmp["amount"] = $row["amount"];
    $tmp["discount"] = $row["discount"];
    $tmp["service_charge"] = $row["service_charge"];
    $tmp["vat"] = $row["vat"];
    $tmp["total"] = $row["total"];
    $tmp["nettotal"] = $row["nettotal"];
    $tmp["date_create"] = $row["date_create"];
    $tmp["date_modi"] = $row["date_modi"];    
	array_push($resultArray,$tmp);
}
mysql_close($objConnect);
	//$pid = $_POST['order_id'];
	//$name = $_POST['foods_code'];
	//$price = $_POST['price'];
	//$description = $_POST['description'];
	//$conn->getArea();
header('Content-Type: application/json');
echo json_encode($resultArray);
?>