
<?php
include "classes.php";
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");

$date1 = $_POST['closeday_date']." 00:00:00";
$date2 = $_POST['closeday_date']." 23:59:59";

$cd = new Closeday();
//$footy = new FoodsType();
//$ord = new Order();
$cd->id="closeday_id";
$cd->closeday_date="closeday_date";
$cd->res_id="res_id";
$cd->amount="amount";
$cd->discount="discount";
$cd->total="total";
$cd->service_charge="service_charge";
$cd->vat="vat";
$cd->nettotal="nettotal";
$cd->remark="remark";
$cd->active="active";
$cd->status_void="status_void";
$cd->void_date="void_date";
$cd->void_user="void_user";
$cd->cnt_bill="cnt_bill";
$cd->bill_amount="bill_amount";
$cd->cnt_order="cnt_order";
$cd->amount_order="amount_order";
$cd->closeday_user = "closeday_user";
$cd->cash_receive1 = "cash_receive1";
$cd->cash_receive2 = "cash_receive2";
$cd->cash_receive3 = "cash_receive3";
$cd->cash_draw1 = "cash_draw1";
$cd->cash_draw2 = "cash_draw2";
$cd->cash_draw3 = "cash_draw3";
$cd->cash_receive1_remark = "cash_receive1_remark";
$cd->cash_receive2_remark = "cash_receive2_remark";
$cd->cash_receive3_remark = "cash_receive3_remark";
$cd->cash_draw1_remark = "cash_draw1_remark";
$cd->cash_draw2_remark = "cash_draw2_remark";
$cd->cash_draw3_remark = "cash_draw3_remark";
$cd->weather="weather";

$cd->table="t_closeday";

//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
//$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
//" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
$sql = "Insert into ".$cd->table."(".$cd->id.",".$cd->closeday_date.",".$cd->res_id.",".$cd->amount.",".$cd->discount.",".$cd->total.",".$cd->service_charge.","
.$cd->vat.",".$cd->nettotal.",".$cd->remark.",".$cd->active.",".$cd->status_void.",".$cd->void_date.",".$cd->void_user.",".$cd->cnt_bill.",".$cd->bill_amount.","
.$cd->cnt_order.",".$cd->amount_order.",".$cd->closeday_user.",".$cd->cash_receive1.",".$cd->cash_receive2.",".$cd->cash_receive3.",".$cd->cash_draw1.",".$cd->cash_draw2.",".$cd->cash_draw3.","
.$cd->cash_receive1_remark.",".$cd->cash_receive2_remark.",".$cd->cash_receive3_remark.",".$cd->cash_draw1_remark.",".$cd->cash_draw2_remark.",".$cd->cash_draw3_remark.",".$cd->weather.")".
" value ('".$_POST['closeday_id']."',now(),'".$_POST['res_id']."',".$_POST['amount'].",".$_POST['discount'].",".$_POST['total'].",".$_POST['service_charge'].","
.$_POST['vat'].",".$_POST['nettotal'].",'".$_POST['remark']."','1','0','','',".$_POST['cnt_bill'].",".$_POST['bill_amount'].","
.$_POST['cnt_order'].",".$_POST['amount_order'].",'".$_POST['closeday_user']."',".$_POST['cash_receive1'].",".$_POST['cash_receive2'].",".$_POST['cash_receive3'].",".$_POST['cash_draw1'].",".$_POST['cash_draw2'].",".$_POST['cash_draw3'].",'"
.$_POST['cash_receive1_remark']."','".$_POST['cash_receive2_remark']."','".$_POST['cash_receive3_remark']."','".$_POST['cash_draw1_remark']."','".$_POST['cash_draw2_remark']."','".$_POST['cash_draw3_remark']."','".$_POST['weather']."')";
$objQuery = mysql_query($sql);
$ok="";
$err="";
if(!$objQuery){
    $ok="0";
    $err= mysql_error();
}else{
    $ok="1";
}

$wheredate=" and bill_date >= '".$date1."' and bill_date <= '".$date2."'";
$sql1 = "Update t_bill Set status_closeday ='1', closeday_id = '".$_POST['closeday_id']."' Where status_closeday ='0' ".$wheredate ;
$objQuery = mysql_query($sql1);

$wheredate=" and order_date >= '".$date1."' and order_date <= '".$date2."'";
$sql2 = "Update t_order Set status_closeday ='1', closeday_id = '".$_POST['closeday_id']."' Where status_closeday ='0' ".$wheredate ;
$objQuery = mysql_query($sql2);

mysql_close($objConnect);

$response = array();
$resultArray = array();
$response["success"] = $ok;
$response["message"] = "insert Closeday success";
$response["sql"] = $sql;
$response["error"] = $err;
//$response["close"] = $code1;
array_push($resultArray,$response);
echo json_encode($resultArray);


?>
