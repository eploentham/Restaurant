
<?php
include "classes.php";

$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");


$objQuery = mysql_query("Select month(now()) as month, year(now()) as year, ifnull(bill_code,'000000000') as bill_code From b_restaurant Where default_res = '1' and bill_month = month(now()) ");
$intNumRow = mysql_num_rows($objQuery);
//echo $intNumField;
if($intNumRow==0){
    mysql_query("update b_restaurant set bill_month = date_format(now(),'%m'), bill_code = null where default_res = '1' ");
}
$res_id = "";
$code = "";
//$objQuery = mysql_query("Select res_id, date_format(now(),'%m') as month, year(now()) as year, ifnull(bill_code,'000000000') as bill_code From b_restaurant Where default_res = '1' and bill_month = month(now()) ");
$objQuery = mysql_query("Select res_id, date_format(now(),'%m') as month, year(now()) as year, ifnull(bill_code,'000000000') as bill_code From b_restaurant Where default_res = '1' ");
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
$bi->billuser="bill_user";
$bi->statuscloseday="status_closeday";
$bi->closedayid="closeday_id";
$bi->hostid="host_id";
$bi->branchid="branch_id";
$bi->cashreceive="cash_receive";
$bi->cashton="cash_ton";


$bi->table="t_bill";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Insert into ".$bi->table."(".$bi->id.",".$bi->billdate.",".$bi->datecreate.",".$bi->remark.",".$bi->statusvoid.",".$bi->tableid.",".$bi->resid.","
.$bi->areaid.",".$bi->deviceid.",".$bi->amt.",".$bi->discount.",".$bi->active.",".$bi->servicecharge.",".$bi->vat.",".$bi->total.",".$bi->nettotal.","
.$bi->billcode.",".$bi->billuser.",".$bi->cashreceive.",".$bi->cashton.",".$bi->statuscloseday.",".$bi->closedayid.",".$bi->hostid.")".
" value ('".$_POST['bill_id']."',now(),now(),'".$_POST['remark']."','0','".$_POST['table_id']."','".$_POST['res_id']."','"
.$_POST['area_id']."','".$_POST['device_id']."',".$_POST['amount'].",".$_POST['discount'].",'1',".$_POST['service_charge'].",".$_POST['vat'].",".$_POST['total'].",".$_POST['nettotal'].",'"
.$code1."','".$_POST['billuser']."',".$_POST['cash_receive'].",".$_POST['cash_ton'].",'0','','".$_POST['host_id']."')";
$objQuery = mysql_query($sql);
$ok="";
$err="";
if(!$objQuery){
    $ok="0";
    $err= mysql_error();
}else{
    $ok="1";
}

$sql1 = "Update b_table Set status_use ='0' Where table_id ='".$_POST['table_id']."'";
$objQuery = mysql_query($sql1);

mysql_close($objConnect);

$response = array();
$resultArray = array();
$response["success"] = $ok;
$response["message"] = "insert Bill success";
$response["sql"] = $sql;
$response["bill_code"] = $code1;
$response["error"] = $err;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>