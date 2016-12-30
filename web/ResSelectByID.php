<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
$objQuery = mysql_query("Select * From b_restaurant Where res_id = '".$_POST['res_id']."'");
$intNumRows = mysql_num_rows($objQuery);
while($row = mysql_fetch_array($objQuery)){
	//$arrCol = array();
	$tmp = array();
    $tmp["res_id"] = $row["res_id"];
    $tmp["res_code"] = $row["res_code"];
    $tmp["res_name"] = $row["res_name"];
    $tmp["active"] = $row["active"];
    $tmp["sort1"] = $row["sort1"];
    $tmp["remark"] = $row["remark"];
    $tmp["default_res"] = $row["default_res"];
    $tmp["receipt_header1"] = $row["receipt_header1"];
    $tmp["receipt_header2"] = $row["receipt_header2"];
    $tmp["receipt_footer1"] = $row["receipt_footer1"];
    $tmp["receipt_footer2"] = $row["receipt_footer2"];
    $tmp["bill_code"] = $row["bill_code"];
    $tmp["tax_id"] = $row["tax_id"];
    //$tmp["area_id"] = $row["area_id"];
   
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
