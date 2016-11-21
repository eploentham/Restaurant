<?php
include "classes.php";

$objConnect = mysql_connect("localhost","root","Ekartc2c5");
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$ft = new FoodsType();
//$footy = new FoodsType();
//$ord = new Order();
$ft->id="foods_type_id";
$ft->code="foods_type_code";
$ft->name="foods_type_name";
$ft->active="active";
$ft->remark="remark";
$ft->sort1="sort1";
//$ta->areaid="area_id";
$ft->datecreate="date_create";

$ft->table="b_foods_type";

$cnt="0";
$sql = "Select count(1) as cnt From b_foods_type ";
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
$sql = "Insert into ".$ft->table."(".$ft->id.",".$ft->code.",".$ft->name.",".$ft->active.",".$ft->sort1.",".$ft->remark.",".$ft->datecreate.")".
" value (UUID(),'".$Code."','".$_POST['Name']."','".$_POST['Active']."','".$_POST['Sort1']."','".$_POST['Remark']."',NOW())";
$objQuery = mysql_query($sql);

mysql_close($objConnect);

$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "insert FoodsType success";
array_push($resultArray,$response);
echo json_encode($resultArray);


?>