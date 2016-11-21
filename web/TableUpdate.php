<?php
include "classes.php";

$objConnect = mysql_connect("localhost","root","Ekartc2c5");
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$ta = new Table1();
//$footy = new FoodsType();
//$ord = new Order();
$ta->id="table_id";
$ta->code="table_code";
$ta->name="table_name";
$ta->active="active";
$ta->sort1="sort1";
$ta->remark="remark";
$ta->areaid="area_id";
$ta->datecreate="date_create";

$ta->table="b_table";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Update ".$ta->table." Set "
//.$foo->id.","
.$ta->code."='".$_POST['Code']."',"
.$ta->name."='".$_POST['Name']."',"
.$ta->active."='".$_POST['Active']."',"
.$ta->sort1."='".$_POST['Sort1']."',"
.$ta->remark."='".$_POST['Remark']."', "
.$ta->areaid."='".$_POST['AreaID']."' "
."Where ".$ta->id." ='".$_POST['ID']."'";
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