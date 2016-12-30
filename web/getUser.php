<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
$objQuery = mysql_query("Select * From b_user Where active = '1'");
$intNumRows = mysql_num_rows($objQuery);
while($row = mysql_fetch_array($objQuery)){
	//$arrCol = array();
	$tmp = array();
	$tmp["user_id"] = $row["user_id"];
    $tmp["user_login"] = $row["user_login"];
    $tmp["user_name"] = $row["user_name"];
    $tmp["remark"] = $row["remark"];
    $tmp["active"] = $row["active"];
    $tmp["password1"] = $row["password1"];
    $tmp["sort1"] = $row["sort1"];
    $tmp["privilege"] = $row["privilege"];
    $tmp["permission_void_bill"] = $row["permission_void_bill"];
    $tmp["permission_void_closeday"] = $row["permission_void_closeday"];
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