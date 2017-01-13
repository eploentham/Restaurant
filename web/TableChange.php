<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");
    $sql = "Insert into t_tablechange (tablechange_id,table_id_from,table_id_to,tablechange_user,host_id,device_id,date_create,active) "                    
            ."Values ('".$_POST['tablechange_id']."','".$_POST['table_id_from']."','".$_POST['table_id_to']."','".$_POST['tablechange_user']."','".$_POST['host_id']."','".$_POST['device_id']."',now(),'1')";
    mysql_query($sql);

    $sql1 = "Update t_order Set table_id ='".$_POST['table_id_to']."' "
                    .",table_code='".$_POST['table_code_to']."' "
                    .",tablechange_id='".$_POST['tablechange_id']."' "
                    ." Where table_code = '".$_POST['table_code_from']."' and active='1' and status_bill= '0'";
    mysql_query($sql1);
    $ok="";
    $err="";
    if(!$objQuery){
        $ok="0";
        $err= mysql_error();
    }else{
        $ok="1";
    }
            
    $sql="Update b_table Set status_use='1' "
            +"Where table_id='".$_POST['table_id_to']."'";
    mysql_query($sql);

    $sql="Update b_table Set status_use='0' "
            +"Where table_id='".$_POST['table_id_from']."'";
    mysql_query($sql);
   
    
    //$sql = "Update t_order Set status_bill ='2', bill_id = ".$_POST['bill_id']." Where order_id ='".$_POST['order_id']."'";
    //$objQuery = mysql_query("Update t_order Set status_bill ='1' Where bill_id ='".$_POST['bill_id']."'");
    $tmp = array();
    $tmp["status"] = $ok;
    $tmp["message"] = "ย้ายโต๊ะ เรียบร้อย";
    $tmp["sql"] = $sql1;
    $tmp["error"] = $err;

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