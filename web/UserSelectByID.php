<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
$objQuery = mysql_query("Select * From b_user Where user_id = '".$_POST['user_id']."'");
$intNumField = mysql_num_fields($objQuery);
while($row = mysql_fetch_array($objQuery)){
	//$arrCol = array();
	$tmp = array();
    $tmp["user_id"] = $row["user_id"];
    $tmp["user_login"] = $row["user_login"];
    $tmp["user_name"] = $row["user_name"];
    $tmp["active"] = $row["active"];
    $tmp["sort1"] = $row["sort1"];
    $tmp["remark"] = $row["remark"];
    $tmp["password"] = $row["password"];
    $tmp["privilege"] = $row["privilege"];
   
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