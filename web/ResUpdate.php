<?php
include "classes.php";

$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$res = new Restaurant();
//$footy = new FoodsType();
//$ord = new Order();
$res->id="res_id";
$res->code="res_code";
$res->name="res_name";
$res->active="active";
$res->sort1="sort1";
$res->remark="remark";
//$res->areaid="area_id";
$res->datecreate="date_create";
$res->rh1="receipt_header1";
$res->rh2="receipt_header2";
$res->rf1="receipt_footer1";
$res->rf2="receipt_footer2";
$res->taxid="tax_id";
$res->defaultres="default_res";
$res->billcode="bill_code";
$res->prnf1="printer_foods1";
$res->prnf2="printer_foods2";
$res->prnf3="printer_foods3";
$res->prnw1="printer_waterbar1";
$res->prnw2="printer_waterbar2";
$res->prnw3="printer_waterbar3";
$res->table="b_restaurant";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Update ".$res->table." Set "
//.$foo->id.","
.$res->code."='".$_POST['res_code']."',"
.$res->name."='".$_POST['res_name']."',"
.$res->active."='".$_POST['active']."',"
.$res->sort1."='".$_POST['sort1']."',"
.$res->rf2."='".$_POST['receipt_footer2']."',"
.$res->rh1."='".$_POST['receipt_header1']."',"
.$res->rh2."='".$_POST['receipt_header2']."',"
.$res->rf1."='".$_POST['receipt_footer1']."',"
.$res->taxid."='".$_POST['tax_id']."',"
.$res->prnf1."='".$_POST['printer_foods1']."',"
.$res->prnf2."='".$_POST['printer_foods2']."',"
.$res->prnf3."='".$_POST['printer_foods3']."',"
.$res->prnw1."='".$_POST['printer_waterbar1']."',"
.$res->prnw2."='".$_POST['printer_waterbar2']."',"
.$res->prnw3."='".$_POST['printer_waterbar3']."',"
.$res->defaultres."='".$_POST['default_res']."',"
.$res->remark."='".$_POST['remark']."' "

."Where ".$res->id." ='".$_POST['res_id']."'";
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
$response["message"] = "update Restaurant success";
$response["sql"] = $sql;
$response["error"] = $err;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>