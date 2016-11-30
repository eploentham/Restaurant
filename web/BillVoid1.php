<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost","root","Ekartc2c5");
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
//$objQuery = mysql_query("Select * From t_bill Where active = '1' and bill_code = '".$_POST['bill_code']."' ");
$objQuery = mysql_query("Select password1 From b_user Where password1 = '".$_POST['password']."' ");
$intNumRows = mysql_num_rows($objQuery);

if($intNumRows==0){
    $tmp = array();
    $tmp["status"] = "3";
    $tmp["message"] = "Password ไม่ถูกต้อง";
    
}else{
    $objQuery = mysql_query("update t_bill Set active = '1', status_void = '1' Where bill_id = '".$_POST['bill_id']."' ");
    //$sql = "Update t_order Set status_bill ='2', bill_id = ".$_POST['bill_id']." Where order_id ='".$_POST['order_id']."'";
    $objQuery = mysql_query("Update t_order Set status_bill ='1' Where bill_id ='".$_POST['bill_id']."'");
    $tmp = array();
    $tmp["status"] = "1";
    $tmp["message"] = "ยกเลิกเรียบร้อย";
}

array_push($resultArray,$tmp);
header('Content-Type: application/json');
echo json_encode($resultArray);


mysql_close($objConnect);
	//$pid = $_POST['order_id'];
	//$name = $_POST['foods_code'];
	//$price = $_POST['price'];
	//$description = $_POST['description'];
	//$conn->getArea();

?>