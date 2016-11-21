<?php

include "connectdb.inc.php";

//include "classes.inc.php";
$conn = new ConnectDB();
$foo = new Foods;

$foo->id="22222";
$foo->code="2222";
$foo->name="3333";
$foo->active="1";
$foo->foodstypeid="4444";
$foo->remark="55555";
$conn->insertFoods($foo);
//echo "<html><head></head><body>77777777777777777777</body></html>";
// required field is missing\
// array for JSON response
$response = array();
$response["success"] = 0;
$response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
echo json_encode($response);
?>