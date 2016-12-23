
<?php
include "classes.php";
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$sql = "Update t_closeday Set active ='3', void_date = now(), void_user = '".$_POST['void_user']."' Where closeday_id = '".$_POST['closeday_id']."'" ;
$objQuery = mysql_query($sql);


mysql_close($objConnect);

$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "insert Closeday success";
$response["sql"] = $sql;
//$response["close"] = $code1;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>
