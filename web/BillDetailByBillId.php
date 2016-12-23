<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
//$objQuery = mysql_query("Select * From t_bill Where active = '1' and bill_code = '".$_POST['bill_code']."' ");
$objQuery = mysql_query("Select t_bill_detail.*, b_foods.foods_name, t_bill.bill_code, t_bill.table_id  "
    ."From t_bill_detail left join b_foods on t_bill_detail.foods_id = b_foods.foods_id "
    ." left join t_bill on t_bill.bill_id = t_bill_detail.bill_id "
    ."Where t_bill_detail.active = '1' and t_bill_detail.bill_id = '".$_POST['bill_id']."' Order By t_bill_detail.row1");
$intNumField = mysql_num_fields($objQuery);
while($row = mysql_fetch_array($objQuery)){
	//$arrCol = array();
	$tmp = array();
    $tmp["bill_detail_id"] = $row["bill_detail_id"];
    $tmp["bill_id"] = $row["bill_id"];
    $tmp["order_id"] = $row["order_id"];
    $tmp["lot_id"] = $row["lot_id"];
    $tmp["status_void"] = $row["status_void"];
    $tmp["row1"] = $row["row1"];
    $tmp["foods_id"] = $row["foods_id"];
    $tmp["foods_code"] = $row["foods_code"];
    $tmp["price"] = $row["price"];
    $tmp["qty"] = $row["qty"];
    $tmp["amount"] = $row["amount"];
    $tmp["date_create"] = $row["date_create"];
    $tmp["date_modi"] = $row["date_modi"];
    $tmp["active"] = $row["active"];
    $tmp["foods_name"] = $row["foods_name"];
    $tmp["remark"] = $row["remark"];
    $tmp["bill_code"] = $row["bill_code"];
    $tmp["table_id"] = $row["table_id"];

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