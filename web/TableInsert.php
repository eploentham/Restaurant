<?php
include "classes.php";

$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$ta = new Table1();
//$footy = new FoodsType();
//$ord = new Order();
$ta->id="table_id";
$ta->code="table_code";
$ta->name="table_name";
$ta->active="active";
$ta->remark="remark";
$ta->sort1="sort1";
$ta->areaid="area_id";
$ta->datecreate="date_create";

$ta->table="b_table";

$cnt="0";
$sql = "Select count(1) as cnt From b_table ";
$objQuery = mysql_query($sql);
$intNumRows = mysql_num_rows($objQuery);
while($row = mysql_fetch_array($objQuery)){
	$cnt = "00".strval(intval($row["cnt"])+1);
	$cnt = substr($cnt,strlen($cnt)-2);
}
$Code=strval($cnt);


//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Insert into ".$ta->table."(".$ta->id.",".$ta->code.",".$ta->name.",".$ta->active.",".$ta->sort1.",".$ta->remark.",".$ta->datecreate.",".$ta->areaid.")".
" value (UUID(),'".$Code."','".$_POST['table_name']."','".$_POST['active']."','".$_POST['sort1']."','".$_POST['remark']."',NOW(),'".$_POST['area_id']."')";
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
$response["message"] = "insert Table success";
$response["error"] = $err;
$response["sql"] = $sql;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>