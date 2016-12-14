<?php
include "classes.php";

$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$foo = new Foods();
//$footy = new FoodsType();
//$ord = new Order();
$foo->id="foods_id";
$foo->code="foods_code";
$foo->name="foods_name";
$foo->active="active";
$foo->foodstypeid="foods_type_id";
$foo->remark="remark";
$foo->resid="res_id";
$foo->statusfoods="status_foods";
$foo->printername="printer_name";
$foo->rescode="res_code";
$foo->price="foods_price";
$foo->datecreate="date_create";

$foo->table="b_foods";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Update ".$foo->table." Set "
//.$foo->id.","
.$foo->code."='".$_POST['Code']."',"
.$foo->name."='".$_POST['Name']."',"
.$foo->active."='".$_POST['Active']."',"
.$foo->foodstypeid."='".$_POST['TypeId']."',"
.$foo->remark."='".$_POST['Remark']."',"
.$foo->resid."='".$_POST['ResId']."',"
.$foo->statusfoods."='".$_POST['StatusFoods']."',"
.$foo->printername."='".$_POST['PrinterName']."',"
.$foo->rescode."='".$_POST['ResCode']."',"
.$foo->price."='".$_POST['Price']."' "
."Where ".$foo->id." ='".$_POST['ID']."'";
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