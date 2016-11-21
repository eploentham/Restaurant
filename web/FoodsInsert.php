<?php
include "classes.php";

$objConnect = mysql_connect("localhost","root","Ekartc2c5");
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


$foodsCode="";
$foodsId="";
$foodsTypeCode="";
$cnt="01";
$sql = "Select bft.foods_type_code, count(1) as cnt From b_foods bf inner join b_foods_type bft on bf.foods_type_id = bft.foods_type_id Where bf.foods_type_id = '".$_POST['TypeId']."'";
$objQuery = mysql_query($sql);
$intNumField = mysql_num_fields($objQuery);
while($row = mysql_fetch_array($objQuery)){
	$foodsTypeCode = trim($row["foods_type_code"]);
	if(empty($foodsTypeCode)){
		$sql="Select foods_type_code From b_foods_type Where foods_type_id = '".$_POST['TypeId']."'";
		$objQuery1 = mysql_query($sql);
		while($row1 = mysql_fetch_array($objQuery1)){
			$foodsTypeCode = $row1["foods_type_code"];
		}
	}
	$cnt = "00".strval(intval($row["cnt"])+1);
	$cnt = substr($cnt,strlen($cnt)-2);
}
$foodsCode=strval($foodsTypeCode).strval($cnt);


//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.",".$foo->resid.",".$foo->statusfoods.","
.$foo->printername.",".$foo->rescode.",".$foo->price.",".$foo->datecreate.")".
" value (UUID(),'".$foodsCode."','".$_POST['Name']."','".$_POST['Active']."','".$_POST['TypeId']."','".$_POST['Remark']."','"
.$_POST['ResId']."','".$_POST['StatusFoods']."','".$_POST['PrinterName']."','".$_POST['ResCode']."','".$_POST['Price']."',NOW())";
$objQuery = mysql_query($sql);
$sql = "Select foods_id From b_foods Where foods_code = '".$foodsCode."'";
$objQuery = mysql_query($sql);
while($row = mysql_fetch_array($objQuery)){
	$foodsId = trim($row["foods_id"]);
}

mysql_close($objConnect);

$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "insert Order success";
$response["foods_id"] = $foodsId;
$response["foods_code"] = $foodsCode;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>