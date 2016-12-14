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
$us->password="password";
$us->privilege="privilege";

$us->usble="b_user";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Update ".$us->table." Set "
//.$foo->id.","
.$us->userlogin."='".$_POST['user_login']."',"
.$us->username."='".$_POST['user_name']."',"
//.$us->active."='".$_POST['Active']."',"
.$us->sort1."='".$_POST['sort1']."',"
.$us->remark."='".$_POST['remark']."', "
.$us->password."='".$_POST['password']."', "
.$us->privilege."='".$_POST['privilege']."' "
."Where ".$us->id." ='".$_POST['ID']."'";
//.$foo->datecreate

//" value (UUID(),'".$_POST['Code']."','".$_POST['Name']."','".$_POST['Active']."','".$_POST['TypeId']."','".$_POST['Remark']."','"
//.$_POST['ResId']."','".$_POST['StatusFoods']."','".$_POST['PrinterName']."','".$_POST['ResCode']."','".$_POST['Price']."',NOW())";
$objQuery = mysql_query($sql);
$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "update user success";
array_push($resultArray,$response);
echo json_encode($resultArray);


?>