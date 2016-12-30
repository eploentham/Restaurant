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
$res->rh1="receipt_header1";
$res->rh2="receipt_header2";
$res->rf1="receipt_footer1";
$res->rf2="receipt_footer2";
$res->taxid="tax_id";
$res->defaultres="default_res";
$res->billcode="bill_code";
$res->datecreate="date_create";
$res->prnf1="printer_foods1";
$res->prnf2="printer_foods2";
$res->prnf3="printer_foods3";
$res->prnw1="printer_waterbar1";
$res->prnw2="printer_waterbar2";
$res->prnw3="printer_waterbar3";

$res->table="b_restaurant";

$cnt="0";
$sql = "Select count(1) as cnt From b_restaurant ";
$objQuery = mysql_query($sql);
$intNumRow = mysql_num_rows($objQuery);
while($row = mysql_fetch_array($objQuery)){
	$cnt = "00".strval(intval($row["cnt"])+1);
	$cnt = substr($cnt,strlen($cnt)-2);
}
$Code=strval($cnt);


//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Insert into ".$res->table."(".$res->id.",".$res->code.",".$res->name.",".$res->active.",".$res->sort1.",".
	$res->remark.",".$res->datecreate.",".$res->rh1.",".$res->rh2.",".$res->rf1.",".$res->rf2.",".
	$res->defaultres.",".$res->billcode.",".$res->taxid.",".
	$res->prnf1.",".$res->prnf2.",".$res->prnf3.",".$res->prnw1.",".$res->prnw2.",".$res->prnw3.")".
	" value (UUID(),'".$Code."','".$_POST['res_name']."','".$_POST['active']."','".$_POST['sort1']."','".
	$_POST['remark']."',NOW(),'".$_POST['receipt_header1']."','".$_POST['receipt_header2']."','".$_POST['receipt_footer1']."','".$_POST['receipt_footer2']."','".
	$_POST['default_res']."','".$_POST['bill_code']."','".$_POST['tax_id']."','".
	$_POST['printer_foods1']."','".$_POST['printer_foods2']."','".$_POST['printer_foods3']."','".$_POST['printer_waterbar1']."','".$_POST['printer_waterbar2']."','".$_POST['printer_waterbar3']."')";
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
$response["message"] = "insert Restaurant success";
$response["sql"] = $sql;
$response["error"] = $err;
array_push($resultArray,$response);
echo json_encode($resultArray);

?>