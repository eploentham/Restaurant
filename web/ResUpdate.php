<?php
include "classes.php";

$objConnect = mysql_connect("localhost","root","Ekartc2c5");
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$res = new Restaurant();
//$footy = new FoodsType();
//$ord = new Order();
$res->id="res_id";
$res->code="res_code";
$res->name="res_name";
$res->active="active";
//$res->sort1="sort1";
$res->remark="remark";
//$res->areaid="area_id";
$res->datecreate="date_create";

$res->table="b_restaurant";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Update ".$res->table." Set "
//.$foo->id.","
.$res->code."='".$_POST['Code']."',"
.$res->name."='".$_POST['Name']."',"
.$res->active."='".$_POST['Active']."',"
//.$res->sort1."='".$_POST['Sort1']."',"
.$res->remark."='".$_POST['Remark']."' "
."Where ".$res->id." ='".$_POST['ID']."'";
//.$foo->datecreate

//" value (UUID(),'".$_POST['Code']."','".$_POST['Name']."','".$_POST['Active']."','".$_POST['TypeId']."','".$_POST['Remark']."','"
//.$_POST['ResId']."','".$_POST['StatusFoods']."','".$_POST['PrinterName']."','".$_POST['ResCode']."','".$_POST['Price']."',NOW())";
$objQuery = mysql_query($sql);
$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "update table success";
array_push($resultArray,$response);
echo json_encode($resultArray);


?>