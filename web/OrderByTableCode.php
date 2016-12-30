<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
$objQuery = mysql_query("Select * From t_order Where active = '1' and table_code like '".$_POST['table_code']."' and status_bill <> '2' Order By lot_id,row1");
$intNumRows = mysql_num_rows($objQuery);
while($row = mysql_fetch_array($objQuery)){
	//$arrCol = array();
	$tmp = array();
    $tmp["order_id"] = $row["order_id"];
    $tmp["lot_id"] = $row["lot_id"];
    $tmp["row1"] = $row["row1"];
    $tmp["foods_id"] = $row["foods_id"];
    $tmp["foods_code"] = $row["foods_code"];
    $tmp["foods_name"] = $row["foods_name"];
    $tmp["order_date"] = $row["order_date"];
    $tmp["price"] = $row["price"];
    $tmp["qty"] = $row["qty"];
    $tmp["remark"] = $row["remark"];
    $tmp["table_code"] = $row["table_code"];
    $tmp["device_id"] = $row["device_id"];
    $tmp["res_code"] = $row["res_code"];
    $tmp["area_code"] = $row["area_code"];
    $tmp["status_foods_1"] = $row["status_foods_1"];
    $tmp["status_foods_2"] = $row["status_foods_2"];
    $tmp["status_foods_3"] = $row["status_foods_3"];
    $tmp["status_bill"] = $row["status_bill"];
    $tmp["bill_check_date"] = $row["bill_check_date"];
    $tmp["status_cook"] = $row["status_cook"];
    $tmp["cook_receive_date"] = $row["cook_receive_date"];
    $tmp["cook_finish_date"] = $row["cook_finish_date"];
    $tmp["active"] = $row["active"];
    $tmp["void_date"] = $row["void_date"];
    $tmp["status_void"] = $row["status_void"];
    $tmp["date_create"] = $row["date_create"];
    $tmp["date_modi"] = $row["date_modi"];
    $tmp["status_to_go"] = $row["status_to_go"];
    //$tmp["printer_name"] = $row["printer_name"];
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