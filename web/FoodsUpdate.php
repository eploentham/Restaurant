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
.$foo->code."='".$_POST['foods_code']."',"
.$foo->name."='".$_POST['foods_name']."',"
.$foo->active."='".$_POST['active']."',"
.$foo->foodstypeid."='".$_POST['foods_type_id']."',"
.$foo->remark."='".$_POST['remark']."',"
.$foo->resid."='".$_POST['res_id']."',"
.$foo->statusfoods."='".$_POST['status_foods']."',"
.$foo->printername."='".$_POST['printer_name']."',"
.$foo->rescode."='".$_POST['res_code']."',"
.$foo->price."='".$_POST['foods_price']."' "
."Where ".$foo->id." ='".$_POST['foods_id']."'";
//.$foo->datecreate

//" value (UUID(),'".$_POST['Code']."','".$_POST['Name']."','".$_POST['Active']."','".$_POST['TypeId']."','".$_POST['Remark']."','"
//.$_POST['ResId']."','".$_POST['StatusFoods']."','".$_POST['PrinterName']."','".$_POST['ResCode']."','".$_POST['Price']."',NOW())";
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
$response["message"] = "update Foods success";
$response["foods_id"] = $_POST['foods_id'];
$response["foods_code"] = $_POST['foods_name'];
$response["error"] = $err;
$response["sql"] = $sql;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>