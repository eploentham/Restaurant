<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
$objQuery = mysql_query("Select * From b_foods Where active = '1'");
//$intNumRows = mysql_num_rows($objQuery);
while($row = mysql_fetch_array($objQuery)){
	//$arrCol = array();
	$tmp = array();
    $tmp["foods_id"] = $row["foods_id"];
    $tmp["foods_code"] = $row["foods_code"];
    $tmp["foods_name"] = $row["foods_name"];
    $tmp["foods_price"] = $row["foods_price"];
    $tmp["active"] = $row["active"];
    $tmp["foods_type_id"] = $row["foods_type_id"];
    $tmp["remark"] = $row["remark"];
    $tmp["res_id"] = $row["res_id"];
    $tmp["res_code"] = $row["res_code"];
    $tmp["status_foods"] = $row["status_foods"];
    $tmp["printer_name"] = $row["printer_name"];
	//for($i=0;$i<$intNumField;$i++)
	//{
	//	$arrCol[mysql_field_name($objQuery,$i)] = $obResult[$i];
	//}
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