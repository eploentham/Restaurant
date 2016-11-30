
<?php
include "classes.php";

$objConnect = mysql_connect("localhost","root","Ekartc2c5");
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");



$objQuery = mysql_query("Select month(now()) as month, year(now()) as year, ifnull(bill_code,'000000000') as bill_code From b_restaurant Where default_res = '1' and bill_month = month(now()) ");
$intNumField = mysql_num_fields($objQuery);
//echo $intNumField;
if($intNumField==0){
    mysql_query("update b_restaurant set bill_month = date_format(now(),'%m'), bill_code = null where default_res = '1' ");
}
$res_id = "";
$code = "";
$objQuery = mysql_query("Select res_id, date_format(now(),'%m') as month, year(now()) as year, ifnull(bill_code,'000000000') as bill_code From b_restaurant Where default_res = '1' and bill_month = month(now()) ");
while($row = mysql_fetch_array($objQuery)){
    //$arrCol = a
    $res_id = $row["res_id"];
    //$month = substr("00".$row["month"],2);
    //$year = substr(intval($row["year"])+543,2);
    $code = "0000".strval(intval($row["bill_code"])+1);
    //echo $month."\n";
    //echo $year."\n";
    //echo $code."\n";
    //echo $year.$month.$code."\n";
    $code1 = substr(intval($row["year"])+543,2).substr("00".$row["month"],2)."0000".strval(intval($row["bill_code"])+1);
    //echo $code1."\n";

    //$billCode = $year.$month.$code;
    //$tmp = array();
    //$tmp["bill_code"] = $code1;
    //array_push($resultArray,$tmp);
}
mysql_query("update b_restaurant set bill_code = '".$code."' Where res_id  = '".$res_id."'");





$bi = new Bill();
//$footy = new FoodsType();
//$ord = new Order();
$bi->id="bill_id";
$bi->billdate="bill_date";
$bi->lotid="lot_id";
$bi->active="active";
$bi->remark="remark";
$bi->statusvoid="status_void";
$bi->voiddate="void_date";
$bi->tableid="table_id";
$bi->resid="res_id";
$bi->areaid="area_id";
$bi->deviceid="device_id";
$bi->amt="amount";
$bi->discount="discount";
$bi->servicecharge="service_charge";
$bi->vat="vat";
$bi->total="total";
$bi->nettotal="nettotal";
$bi->datecreate="date_create";
$bi->billcode = "bill_code";
$bi->statusbill = "status_bill";
$bi->billuser="bill_user":
$bi->statuscloseday="status_closeday";
$bi->closedayid="closeday_id";

$bi->table="t_bill";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Insert into ".$bi->table."(".$bi->id.",".$bi->billdate.",".$bi->datecreate.",".$bi->remark.",".$bi->statusvoid.",".$bi->tableid.",".$bi->resid.","
.$bi->areaid.",".$bi->deviceid.",".$bi->amt.",".$bi->discount.",".$bi->active.",".$bi->servicecharge.",".$bi->vat.",".$bi->total.",".$bi->nettotal.","
.$bi->billcode.",".$bi->statusbill.",".$bi->closedayid.",".$bi->statuscloseday.",".$bi->billuser.")".
" value ('".$_POST['bill_id']."',now(),now(),'".$_POST['remark']."','0','".$_POST['table_id']."','".$_POST['res_id']."','"
.$_POST['area_id']."','".$_POST['device_id']."',".$_POST['amt'].",".$_POST['discount'].",'1',".$_POST['service_charge'].",".$_POST['vat'].",".$_POST['total'].",".$_POST['nettotal'].",'"
.$code1."','0','','0','".$_POST['bill_user']."')";
$objQuery = mysql_query($sql);

$sql = "Update b_table Set status_use ='0' Where table_id ='".$_POST['table_id']."'";
$objQuery = mysql_query($sql);

mysql_close($objConnect);

$response = array();
$resultArray = array();
$response["success"] = 1;
$response["message"] = "insert Bill success";
$response["sql"] = $sql;
$response["bill_code"] = $code1;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>
