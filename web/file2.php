//file2.php
<?php
   include ("file1.php");

   class ClassB
   {

     function __construct()
     {
     }

     function callA()
     {
       $classA = new ClassA();
       $name = $classA->getName();
       echo $name;    //Prints John
     }
   }


   class ConnectDB 
{
  $foo = new Foods();
  $footy = new FoodsType();
  $ord = new Order();
  $foo->id="foods_id";
  $foo->code="foods_code";
  $foo->name="foods_name";
  $foo->active="active";
  $foo->foodstypeid="foods_type_id";
  $foo->remark="remark";
  $foo->table="b_foods";

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
            self::$conn = new mysqli('localhost','root','','esernrodded');
        }

        // If connection was not successful, handle the error
        if(self::$conn === false) {
            // Handle error - notify administrator, log to a file, show an error screen, etc.
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
    function query($query) {
        // Connect to the database
        $conn = $this -> connect();

        // Query the database
        $result = $conn -> query($query);

        return $result;
    }
    /**
     * Fetch rows from the database (SELECT query)
     *
     * @param $query The query string
     * @return bool False on failure / array Database rows on success
     */
    function select($query) {
        $rows = array();
        $result = $this -> query($query);
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
      $conn = $this -> connect();
      $sql = "Insert into ".$foo->table."(".$foo->id.",".$foo->code.",".$foo->name.",".$foo->active.",".$foo->foodstypeid.",".$foo->remark.")".
      " value (".$f->id."','".$f->code."','".$f->name."','1','".$f->active."','".$f->foodstypeid."','".$f->remark."')";
      $conn->query($sql);
    }

}



   $classb = new ClassB();
   $classb->callA();
?>