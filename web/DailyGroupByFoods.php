<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
$date1 = $_POST['date_start']." 00:00:00";
$date2 = $_POST['date_end']." 23:59:59";
$wheredate=" and bi.bill_date >= '".$date1."' and bi.bill_date <= '".$date2."'";

//$wheredate=""; 
$sql="Select bid.foods_code, foo.foods_name, sum(bid.price) as price, sum(bid.qty) as qty, sum(bid.amount) as amount "
	."From t_bill_detail bid "
	."Left join b_foods foo on foo.foods_id = bid.foods_id "
	."Left join t_bill bi on bi.bill_id = bid.bill_id "
	."Where bid.active = '1' and bi.active= '1' ".$wheredate." ";
$objQuery = mysql_query($sql);
$intNumRows = mysql_num_rows($objQuery);
while($row = mysql_fetch_array($objQuery)){
	//$arrCol = array();
	$tmp = array();
	$tmp["foods_code"] = $row["foods_code"];
    $tmp["foods_name"] = $row["foods_name"];
    $tmp["price"] = $row["price"];
    $tmp["qty"] = $row["qty"];
    $tmp["amount"] = $row["amount"];
    //$tmp["sort1"] = $row["sort1"];
	//for($i=0;$i<$intNumField;$i++)
	//{
	//	$arrCol[mysql_field_name($objQuery,$i)] = $obResult[$i];
	//}
	array_push($resultArray,$tmp);
}
mysql_close($objConnect);
	//$pid = $_POST['order_id'];
	//$name = $_POST['foods_code'];
	//$price = $_POST['price'];
	//$description = $_POST['description'];
	//$conn->getArea();
header('Content-Type: application/json');
echo json_encode($resultArray);
?>