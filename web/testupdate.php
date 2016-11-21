<?php
 
/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */
 
// array for JSON response
$response = array();
 include "classes.php";
// check for required fields
if (isset($_POST['foods_id']) && isset($_POST['foods_code'])) {
 
    $pid = $_POST['pid'];
    $name = $_POST['name'];
    $price = $_POST['price'];
    $description = $_POST['description'];
 
    // include db connect class
    require_once __DIR__ . 'db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    //$result = mysql_query("UPDATE products SET name = '$name', price = '$price', description = '$description' WHERE pid = $pid");
 

    $foo = new Foods();
    //$footy = new FoodsType();
    //$ord = new Order();
    $foo->id="foods_id";
    $foo->code="foods_code";
    $foo->name="foods_name";
    $foo->active="active";
    $foo->foodstypeid="foods_type_id";
    $foo->remark="remark";
    $foo->table="b_foods";
    //$conn = $this -> connect();
    $sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
        " value ('".$_POST['foods_id']."','".$_POST['foods_code']."','".$_POST['foods_name']."','1','".$_POST['foods_type_id']."','".$_POST['remark']."')";
    $result = mysql_query($sql);
    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
 
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>