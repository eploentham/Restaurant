<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
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
    echo $code1."\n";

    //$billCode = $year.$month.$code;
    $tmp = array();
    $tmp["bill_code"] = $code1;
    array_push($resultArray,$tmp);
}
mysql_query("update b_restaurant set bill_code = '".$code."' Where res_id  = '".$res_id."'");
mysql_close($objConnect);
	//$pid = $_POST['order_id'];
	//$name = $_POST['foods_code'];
	//$price = $_POST['price'];
	//$description = $_POST['description'];
	//$conn->getArea();
header('Content-Type: application/json');
echo json_encode($resultArray);
?>