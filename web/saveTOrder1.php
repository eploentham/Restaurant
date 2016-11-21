<?php
include "connectdb.inc.php";

$conn = new ConnectDB();

if (isset($_POST['order_id']) && isset($_POST['foods_code'])) {
	//$pid = $_POST['order_id'];
	//$name = $_POST['foods_code'];
	//$price = $_POST['price'];
	//$description = $_POST['description'];
	$conn->insertOrder();
}


?>