<?php
/**
* 
*/
include "classes.php";
//require('classes.inc.php');
class ConnectDB 
{
	//$foo = new Foods();
	//$footy = new FoodsType();
	//$ord = new Order();
	//$foo->id="foods_id";
	//$foo->code="foods_code";
	//$foo->name="foods_name";
	//$foo->active="active";
	//$foo->foodstypeid="foods_type_id";
	//$foo->remark="remark";
	//$foo->table="b_foods";

	static $conn;


	/**
     * Connect to the database
     * 
     * @return bool false on failure / mysqli MySQLi object instance on success
     */
    function connect() {    
        // Try and connect to the database
        if(!isset(self::$conn)) {
            // Load configuration as an array. Use the actual location of your configuration file
            //$config = parse_ini_file('./config.ini'); 
            //self::$connection = new mysqli('localhost',$config['root'],$config[''],$config['esernrodded']);
            self::$conn = new mysqli('localhost','root','','restaurant');
            mysqli_query("SET NAMES UTF8");
        }

        // If connection was not successful, handle the error
        if(self::$conn === false) {
            // Handle error - notify administrator, log to a file, show an error screen, etc.
            echo "error connect database";
            return false;
        }
        return self::$conn;
    }
    /**
     * Query the database
     *
     * @param $query The query string
     * @return mixed The result of the mysqli::query() function
     */
    function query1($sql) {
        // Connect to the database
        $conn = $this -> connect();
        //echo $sql;
        // Query the database
        $result = $conn -> query($sql);
        
        return $result;
    }
    /**
     * Fetch rows from the database (SELECT query)
     *
     * @param $query The query string
     * @return bool False on failure / array Database rows on success
     */
    function select($sql) {
        $rows = array();
        $result = $this -> query($sql);
        if($result === false) {
            return false;
        }
        while ($row = $result -> fetch_assoc()) {
            $rows[] = $row;
        }
        return $rows;
    }
    /**
     * Fetch the last error from the database
     * 
     * @return string Database error message
     */
    function error() {
        $conn = $this -> connect();
        return $conn -> error;
    }
    /**
     * Quote and escape value for use in a database query
     *
     * @param string $value The value to be quoted and escaped
     * @return string The quoted and escaped string
     */
    function quote($value) {
        $connection = $this -> connect();
        return "'" . $conn -> real_escape_string($value) . "'";
    }

    function insertFoods(Foods $f){
        //echo "aaaa";
        //return;
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
        	$conn = $this -> connect();
        	//$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
        	//" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
            $sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
            " value ('".$_POST['foods_id']."','".$_POST['foods_code']."','".$_POST['foods_name']."','1','".$_POST['foods_type_id']."','".$_POST['remark']."')";
    	$this -> query1($sql);
    }
    function insertOrder(){
        //echo "aaaa";
        //return;
        $or = new Order();
        //$footy = new FoodsType();
        //$ord = new Order();
        $or->id="order_id";
        $or->foodsid="foods_code";
        $or->orderdate="order_date";
        $or->price="price";
        $or->qty="qty";
        $or->remark="remark";
        $or->lotid="lot_id";
        $or->rescode="res_code";
        $or->tablecode="table_code";
        $or->deviceid="device_id";
        $or->areacode="area_code";

        $or->table="t_order";
        $conn = $this -> connect();
        //$sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
        //" value ('".$f->id."','".$f->code."','".$f->name."','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
        //$sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->remark.")".
        //" value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['remark']."')";
        $sql = "Insert into ".$or->table."(".$or->id.",".$or->foodsid.",".$or->orderdate.",".$or->price.",".$or->qty.",".$or->lotid.",".$or->rescode.",".$or->tablecode.",".$or->areacode.")".
        " value ('".$_POST['order_id']."','".$_POST['foods_code']."',now(),'1','".$_POST['qty']."','".$_POST['lot_id']."','".$_POST['res_code']."','".$_POST['table_code']."','".$_POST['area_code']."')";
        $this -> query1($sql);
        $response = array();
        $response["success"] = 1;
        $response["message"] = "insert Order success";
        echo json_encode($response);
    }
    function getArea(){
        $conn = $this -> connect();
        // array for json response
        $response = array();
        //$response["area"] = array();
         
        // Mysql select query
        $result = mysql_query("SELECT * FROM b_area Where active = '1'");
         
        while($row = mysql_fetch_array($result)){
            // temporary array to create single category
            $tmp = array();
            $tmp["code"] = $row["area_code"];
            $tmp["name"] = $row["area_name"];
             
            // push category to final json array
            array_push($response, $tmp);
        }
         
        // keeping response header to json
        header('Content-Type: application/json');
        //response = $this -> query($sql);
        // echoing json result
        echo json_encode($response);
    }
}
?>
