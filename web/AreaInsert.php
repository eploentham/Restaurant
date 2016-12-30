<?php
include "classes.php";

$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$ar = new Area();
//$footy = new FoodsType();
//$ord = new Order();
$ar->id="area_id";
$ar->code="area_code";
$ar->name="area_name";
$ar->active="active";
$ar->remark="remark";
$ar->sort1="sort1";
$ar->datecreate="date_create";

$ar->table="b_area";

$cnt="0";
$sql = "Select count(1) as cnt From b_area ";
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
$sql = "Insert into ".$ar->table."(".$ar->id.",".$ar->code.",".$ar->name.",".$ar->active.",".$ar->sort1.",".$ar->remark.",".$ar->datecreate.")".
" value (UUID(),'".$Code."','".$_POST['area_name']."','".$_POST['active']."','".$_POST['sort1']."','".$_POST['remark']."',NOW())";
$objQuery = mysql_query($sql);
$ok="";
$err="";
if(!$objQuery){
    $ok="0";
    $err= mysql_error();
}else{
    $ok="1";
}
mysql_close($objConnect);

$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "insert Order success";
$response["error"] = $err;
$response["sql"] = $sql;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>