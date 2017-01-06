<?php
include "classes.php";

$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$us = new User();
//$footy = new FoodsType();
//$ord = new Order();
$us->id="user_id";
$us->userlogin="user_login";
$us->username="user_name";
$us->active="active";
$us->remark="remark";
$us->sort1="sort1";
$us->password="password1";
$us->privilege="privilege";
$us->permissionvoidbill="permission_void_bill";
$us->permissionvoidcloseday="permission_void_closeday";

$us->table="b_user";

$cnt="0";
$sql = "Select count(1) as cnt From b_user ";
$objQuery = mysql_query($sql);
$intNumField = mysql_num_fields($objQuery);
while($row = mysql_fetch_array($objQuery)){
	$cnt = "000".strval(intval($row["cnt"])+1);
	$cnt = substr($cnt,strlen($cnt)-3);
}
$Code=strval($cnt);


//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Insert into ".$us->table."(".$us->id.",".$us->userlogin.",".$us->username.",".$us->active.",".$us->sort1.",".$us->remark.",date_create,".$us->password.",".$us->privilege.",".$us->permissionvoidbill.",".$us->permissionvoidcloseday.")".
" value (UUID(),'".$Code."','".$_POST['user_name']."','1','".$_POST['sort1']."','".$_POST['remark']."',NOW(),'".$_POST['password']."','".$_POST['privilege']."','".$_POST['permission_void_bill']."','".$_POST['permission_void_closeday']."')";
$objQuery = mysql_query($sql);
$ok="";
$err="";
if(!$objQuery){
    $ok="0";
    $err= mysql_error();
}else{
    $ok="1";
}
$response = array();
$resultArray = array();
$response["success"] = $ok;
$response["message"] = "insert User success";
$response["sql"] = $sql;
$response["error"] = $err;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>