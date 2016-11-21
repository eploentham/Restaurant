<?php

class ClassA
{
   private $name = 'John33333';

   function getName()
   {
     return $this->name;
   }   
}
class Foods
{
	var $id;
	var $code;
	var $name;
	var $active;
	var $foodstypeid;
	var $remark;
	function __construct()
	{
		# code...
		$id="";
		$code="";
		$name="";
		$active="";
		$foodstypeid="";
		$remark="";
		$table="";
		$pkfield="";
	}
}


/**
* ประเภทอาหาร เช่น อาหาร เครื่องดื่ม 
*/
class FoodsType 
{
	var $id,$code,$typename,$active,$remark;
	function __construct()
	{
		# code...
		$id="";
		$code="";
		$typename="";
		$active="";
		$remark="";
		$table="";
		$pkfield="";
	}
}
/**
* 
*/
class Order 
{
	var $id, $foodsid, $orderdate, $price,$qty,$remark;
	function __construct()
	{
		# code...
		$id="";
		$foodsid="";
		$orderdate="";
		$price="";
		$qty="";
		$remark="";
		$table="";
		$pkfield="";
	}
}
?>