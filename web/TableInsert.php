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
$ta->remark="remark";
$ta->sort1="sort1";
$ta->areaid="area_id";
$ta->datecreate="date_create";

$ta->table="b_table";

$cnt="0";
$sql = "Select count(1) as cnt From b_table ";
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
$sql = "Insert into ".$ta->table."(".$ta->id.",".$ta->code.",".$ta->name.",".$ta->active.",".$ta->sort1.",".$ta->remark.",".$ta->datecreate.",".$ta->areaid.")".
" value (UUID(),'".$Code."','".$_POST['Name']."','".$_POST['Active']."','".$_POST['Sort1']."','".$_POST['Remark']."',NOW(),'".$_POST['AreaID']."')";
$objQuery = mysql_query($sql);
$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "insert Table success";
array_push($resultArray,$response);
echo json_encode($resultArray);


?>