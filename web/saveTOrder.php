
<?php
include "classes.php";

$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$or = new Order();
//$footy = new FoodsType();
//$ord = new Order();
$or->id="order_id";
$or->foodsid="foods_id";
$or->foodscode="foods_code";
$or->orderdate="order_date";
$or->price="price";
$or->qty="qty";
$or->remark="remark";
$or->lotid="lot_id";
$or->rescode="res_code";
$or->tablecode="table_code";
$or->deviceid="device_id";
$or->hostid="host_id";
$or->areacode="area_code";
$or->statusfoods1="status_foods_1";
$or->statusfoods2="status_foods_2";
$or->statusfoods3="status_foods_3";
$or->statusbill="status_bill";
$or->billcheckdate="bill_check_date";
$or->statuscook="status_cook";
$or->cookreceivedate="cook_receive_date";
$or->cookfinishdate="cook_finish_date";
$or->statusvoid="status_void";
$or->voiddate="void_date";
$or->active="active";
$or->datecreate="date_create";
$or->printername="printer_name";
$or->row1="row1";
$or->foodsname="foods_name";
$or->statustogo="status_to_go";
$or->orderuser="order_user";
$or->statuscloseday="status_closeday";
$or->closedayid="closeday_id";
$or->cntcust="cnt_cust";

$or->table="t_order";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodscode.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->lotid.",".$or->rescode.",".$or->tablecode.","
.$or->areacode.",".$or->statusfoods1.",".$or->statusfoods2.",".$or->statusfoods3.",".$or->active.",".$or->statusvoid.",".$or->statusbill.",".$or->statuscook.",".$or->datecreate.","
.$or->printername.",".$or->remark.",".$or->row1.",".$or->foodsid.",".$or->foodsname.",".$or->statustogo.","
.$or->orderuser.",".$or->statuscloseday.",".$or->closedayid.",".$or->cntcust.",".$or->deviceid.",".$or->hostid.")".
" value (UUID(),'".$_POST['foods_code']."',now(),'".$_POST['price']."','".$_POST['qty']."','".$_POST['lot_id']."','".$_POST['res_code']."','".$_POST['table_code']."','"
.$_POST['area_code']."','".$_POST['status_foods_1']."','".$_POST['status_foods_2']."','".$_POST['status_foods_3']."','1','0','0','0',NOW(),'"
.$_POST['printer_name']."','".$_POST['remark']."','".$_POST['row1']."','".$_POST['foods_id']."','".$_POST['foods_name']."','".$_POST['status_to_go']."','"
.$_POST['order_user']."','0','','".$_POST['cnt_cust']."','".$_POST['device_id']."','".$_POST['host_id']."')";
$objQuery = mysql_query($sql);
$ok="";
$err="";
if(!$objQuery){
	$ok="0";
	$err= mysql_error();
}else{
	$ok="1";
}
$sql = "Update b_table Set status_use ='1' Where table_id ='".$_POST['table_id']."'";
$objQuery = mysql_query($sql);

$response = array();
$resultArray = array();
$response["success"] = $ok;
$response["message"] = "insert Order success";
$response["sql"] = $sql;
$response["error"] = $err;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>
