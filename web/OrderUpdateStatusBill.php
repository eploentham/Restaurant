<?php
include "classes.php";

$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$sql = "Update t_order Set status_bill ='".$_POST['status_bill']."' Where order_id ='".$_POST['order_id']."'";
//.$foo->datecreate

//" value (UUID(),'".$_POST['Code']."','".$_POST['Name']."','".$_POST['Active']."','".$_POST['TypeId']."','".$_POST['Remark']."','"
//.$_POST['ResId']."','".$_POST['StatusFoods']."','".$_POST['PrinterName']."','".$_POST['ResCode']."','".$_POST['Price']."',NOW())";
$objQuery = mysql_query($sql);
$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "update Foods success";
array_push($resultArray,$response);
echo json_encode($resultArray);


?>