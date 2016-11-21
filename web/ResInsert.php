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
$res->remark="remark";
$res->sort1="sort1";
//$ta->areaid="area_id";
$res->datecreate="date_create";

$res->table="b_restaurant";

$cnt="0";
$sql = "Select count(1) as cnt From b_restaurant ";
$objQuery = mysql_query($sql);
$intNumField = mysql_num_fields($objQuery);
while($row = mysql_fetch_array($objQuery)){
	$cnt = "00".strval(intval($row["cnt"])+1);
	$cnt = substr($cnt,strlen($cnt)-2);
}
$Code=strval($cnt);


//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Insert into ".$res->table."(".$res->id.",".$res->code.",".$res->name.",".$res->active.",".$res->sort1.",".$res->remark.",".$res->datecreate.")".
" value (UUID(),'".$Code."','".$_POST['Name']."','".$_POST['Active']."','".$_POST['Sort1']."','".$_POST['Remark']."',NOW())";
$objQuery = mysql_query($sql);
$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "insert Restaurant success";
array_push($resultArray,$response);
echo json_encode($resultArray);


?>