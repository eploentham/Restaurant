<?php
include "classes.php";

$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
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
.$ft->code."='".$_POST['foods_type_code']."',"
.$ft->name."='".$_POST['foods_type_name']."',"
.$ft->active."='".$_POST['active']."',"
.$ft->sort1."='".$_POST['sort1']."',"
.$ft->remark."='".$_POST['remark']."' "
//.$ft->areaid."='".$_POST['AreaID']."' "
."Where ".$ft->id." ='".$_POST['foods_type_id']."'";
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
$response["success"] = 1;
$response["message"] = "update FoodsType success";
$response["error"] = $err;
$response["sql"] = $sql;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>