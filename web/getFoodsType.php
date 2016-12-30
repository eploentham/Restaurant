<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
$objQuery = mysql_query("Select * From b_foods_type Where active = '1'");
$intNumRows = mysql_num_rows($objQuery);
while($row = mysql_fetch_array($objQuery)){
	//$arrCol = array();
	$tmp = array();
    $tmp["foods_type_id"] = $row["foods_type_id"];
    $tmp["foods_type_code"] = $row["foods_type_code"];
    $tmp["active"] = $row["active"];
    $tmp["foods_type_name"] = $row["foods_type_name"];
    $tmp["remark"] = $row["remark"];
    $tmp["sort1"] = $row["sort1"];
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