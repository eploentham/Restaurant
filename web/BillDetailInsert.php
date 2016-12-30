
<?php
include "classes.php";

$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$bid = new BillDetail();
//$footy = new FoodsType();
//$ord = new Order();
$bid->id="bill_detail_id";
$bid->billid="bill_id";
$bid->orderid="order_id";
$bid->statusvoid="status_void";
$bid->row1="row1";
$bid->foodsid="foods_id";
$bid->foodscode="foods_code";
$bid->price="price";
$bid->qty="qty";
$bid->amt="amount";
$bid->datecreate="date_create";
$bid->datemodi="date_modi";
$bid->active="active";

$bid->table="t_bill_detail";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";

if($_POST['flag_void']==="1"){
	$sql = "Update t_order Set status_void ='3', void_date = now() Where order_id ='".$_POST['order_id']."'";
	$objQuery = mysql_query($sql);
	$ok="";
	$err="";
	if(!$objQuery){
	    $ok="0";
	    $err= mysql_error();
	}else{
	    $ok="1";
	}
}else{
	$sql = "Insert into ".$bid->table."(".$bid->id.",".$bid->datecreate.",".$bid->billid.",".$bid->orderid.",".$bid->statusvoid.",".$bid->row1.",".$bid->foodsid.",".$bid->foodscode.","
		.$bid->price.",".$bid->qty.",".$bid->amt.",".$bid->active.")".
		" value (UUID(),now(),'".$_POST['bill_id']."','".$_POST['order_id']."','0','".$_POST['row1']."','".$_POST['foods_id']."','".$_POST['foods_code']."',"
		.$_POST['price'].",".$_POST['qty'].",".$_POST['amount'].",'1')";
	$objQuery = mysql_query($sql);	
	$ok="";
	$err="";
	if(!$objQuery){
	    $ok="0";
	    $err= mysql_error();
	}else{
	    $ok="1";
	}
}

$sql = "Update t_order Set status_bill ='2', bill_id = '".$_POST['bill_id']."', bill_check_date = now() Where order_id ='".$_POST['order_id']."'";
$objQuery = mysql_query($sql);



mysql_close($objConnect);

$response = array();
$resultArray = array();
$response["success"] = $ok;
$response["message"] = "insert Bill Detail success".$sql;
$response["error"] = $err;
$response["sql"] = $sql;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>