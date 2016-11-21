<?php
include "classes.php";

$objConnect = mysql_connect("localhost","root","Ekartc2c5");
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$ar = new Area();
//$footy = new FoodsType();
//$ord = new Order();
$ar->id="area_id";
$ar->code="area_code";
$ar->name="area_name";
$ar->active="active";
$ar->sort1="sort1";
$ar->remark="remark";
$ar->datecreate="date_create";

$ar->table="b_area";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Update ".$ar->table." Set "
//.$foo->id.","
.$ar->code."='".$_POST['Code']."',"
.$ar->name."='".$_POST['Name']."',"
.$ar->active."='".$_POST['Active']."',"
.$ar->sort1."='".$_POST['Sort1']."',"
.$ar->remark."='".$_POST['Remark']."' "
."Where ".$ar->id." ='".$_POST['ID']."'";
//.$foo->datecreate

//" value (UUID(),'".$_POST['Code']."','".$_POST['Name']."','".$_POST['Active']."','".$_POST['TypeId']."','".$_POST['Remark']."','"
//.$_POST['ResId']."','".$_POST['StatusFoods']."','".$_POST['PrinterName']."','".$_POST['ResCode']."','".$_POST['Price']."',NOW())";
$objQuery = mysql_query($sql);

mysql_close($objConnect);

$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "update area success";
array_push($resultArray,$response);
echo json_encode($resultArray);


?>