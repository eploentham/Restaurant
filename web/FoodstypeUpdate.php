<?php
include "classes.php";

$objConnect = mysql_connect("localhost","root","");
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$ft = new FoodsType();
//$footy = new FoodsType();
//$ord = new Order();
$ft->id="foods_type_id";
$ft->code="foods_type_code";
$ft->name="foods_type_name";
$ft->active="active";
$ft->sort1="sort1";
$ft->remark="remark";
//$ta->areaid="area_id";
$ft->datecreate="date_create";

$ft->table="b_foods_type";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Update ".$ft->table." Set "
//.$foo->id.","
.$ft->code."='".$_POST['Code']."',"
.$ft->name."='".$_POST['Name']."',"
.$ft->active."='".$_POST['Active']."',"
.$ft->sort1."='".$_POST['Sort1']."',"
.$ft->remark."='".$_POST['Remark']."' "
//.$ft->areaid."='".$_POST['AreaID']."' "
."Where ".$ft->id." ='".$_POST['ID']."'";
//.$foo->datecreate

//" value (UUID(),'".$_POST['Code']."','".$_POST['Name']."','".$_POST['Active']."','".$_POST['TypeId']."','".$_POST['Remark']."','"
//.$_POST['ResId']."','".$_POST['StatusFoods']."','".$_POST['PrinterName']."','".$_POST['ResCode']."','".$_POST['Price']."',NOW())";
$objQuery = mysql_query($sql);
$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "update FoodsType success";
array_push($resultArray,$response);
echo json_encode($resultArray);


?>