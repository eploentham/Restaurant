
<?php
include "classes.php";
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$sql = "Update t_closeday Set active ='3', void_date = now(), void_user = '".$_POST['void_user']."', void_remark = '".$_POST['void_remark']."', status_void = '1' Where closeday_id = '".$_POST['closeday_id']."'" ;
$objQuery = mysql_query($sql);
$ok="";
$err="";
if(!$objQuery){
    $ok="0";
    $err= mysql_error();
}else{
    $ok="1";
}

$sql = "Insert Into t_closeday_void select UUID(),'".$_POST['closeday_id']."', bill_id, now() from t_bill Where closeday_id = '".$_POST['closeday_id']."'";
$objQuery = mysql_query($sql);

//$wheredate=" and bill_date >= '".$date1."' and bill_date <= '".$date2."'";
$sql1 = "Update t_bill Set status_closeday ='0', closeday_id = '' Where closeday_id = '".$_POST['closeday_id']."'";
$objQuery = mysql_query($sql1);
if(!$objQuery){
    $ok="0";
    $err1= mysql_error();
}else{
    $ok="1";
}
//$wheredate=" and order_date >= '".$date1."' and order_date <= '".$date2."'";
$sql2 = "Update t_order Set status_closeday ='0', closeday_id = '' Where closeday_id = '".$_POST['closeday_id']."'";
$objQuery = mysql_query($sql2);
if(!$objQuery){
    $ok="0";
    $err2= mysql_error();
}else{
    $ok="1";
}
mysql_close($objConnect);

$response = array();
$resultArray = array();
$response["success"] = $ok;
$response["message"] = "Void Closeday success";
$response["sql"] = $sql;
$response["error"] = $err;
$response["error1"] = $err1;
$response["error2"] = $err2;
//$response["close"] = $code1;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>
