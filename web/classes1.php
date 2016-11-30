<?php

/**

*/
class Foods
{
	var $id;
	var $code;
	var $name;
	var $active;
	var $foodstypeid;
	var $remark;
	var $resid;
	var $statusfoods;
	var $printername;
	var $rescode;
	var $price;
	var $datecreate;
	var $datemodi;
	var $table;
	var $pkfield;
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
	var $id,$code,$typename,$active,$remark,$table,$pkfield;
	function __construct()
	{
		# code...
		$id="";
		$code="";
		$typename="";
		$active="";
		$remark="";
		$sort1="";
		$table="";
		$pkfield="";
	}
}
/**
* 
*/
class Order 
{
	var $id, $foodsid, $orderdate, $price, $qty, $remark, $lotid, $rescode, $tablecode, $deviceid, $areacode, $active, $statusfoods1,$foodscode,$foodsname;
	var $statusfoods2,$statusfoods3,$statusbill,$billcheckdate,$statuscook,$cookreceivedate,$cookfinishdate,$statusvoid,$voiddate,$table,$pkfield,$statustogo,$datecreate,$datemodi,$closedayid,$statuscloseday;
	function __construct()
	{
		# code...
		$id="";
		$foodsid="";
		$orderdate="";
		$price="";
		$qty="";
		$remark="";
		$lotid="";
		$rescode="";
		$tablecode="";
		$deviceid="";
		$areacode="";
		$active="";
		$statusfoods1="";
		$statusfoods2="";
		$statusfoods3="";
		$statusbill="";
		$billcheckdate="";
		$statuscook="";
		$cookreceivedate="";
		$cookfinishdate="";
		$statusvoid="";
		$voiddate="";
		$statustogo="";
		$foodsname="";
		$foodscode="";
		$statustogo="";
		$datecreate="";
		$datemodi="";
		$statuscloseday="";
		$closedayid="";

		$table="";
		$pkfield="";
	}
}
class Area 
{
	var $id,$code,$name,$sort1,$active,$remark,$table,$pkfield;
	function __construct()
	{
		# code...
		$id="";
		$code="";
		$sort1="";
		$name="";
		$active="";
		$remark="";
		$datecreate="";
		$table="";
		$pkfield="";
	}
}
class Table1 
{
	var $id,$code,$name,$sort1,$active,$remark, $areaid,$table,$pkfield;
	function __construct()
	{
		# code...
		$id="";
		$code="";
		$sort1="";
		$name="";
		$active="";
		$remark="";
		$datecreate="";
		$areaid="";
		$statususe="";
		$dateuse="";
		$table="";
		$pkfield="";
	}
}
class Restaurant 
{
	var $id,$code,$name,$sort1,$active,$remark,$table,$pkfield;
	function __construct()
	{
		# code...
		$id="";
		$code="";
		$sort1="";
		$name="";
		$active="";
		$remark="";
		$datecreate="";
		
		$table="";
		$pkfield="";
	}
}
class Bill
{
	var $id, $billdate, $lotid, $active, $remark, $statusvoid, $voiddate, $tableid, $resid, $areaid, $deviceid, $amt, $discount, $servicecharge, $vat, $total, $nettotal,$datecreate,$datemodi,$billcode,$statusbill,$billuser,$closedayid,$statuscloseday;
	function __construct(){
		$id="";
		$billdate="";
		$lotid="";
		$active="";
		$remark="";
		$statusvoid="";
		$voiddate="";
		$tableid="";
		$resid="";
		$areaid="";
		$deviceid="";
		$amt="";
		$discount="";
		$servicecharge="";
		$vat="";
		$total="";
		$nettotal="";
		$datecreate="";
		$datemodi="";
		$billcode="";
		$statusbill="";
		$statuscloseday="";
		$closedayid="";
		$billuser="";
	}
}
class BillDetail{
	var $id,$billid,$orderid,$statusvoid,$row1,$foodsid,$foodscode,$price,$qty,$amt,$datecreate,$datemodi,$lotid;
	function __construct(){
		$id="";
		$billid="";
		$orderid="";
		$statusvoid="";
		$row1="";
		$foodsid="";
		$foodscode="";
		$price="";
		$qty="";
		$amt="";
		$datecreate="";
		$datemodi="";
		$lotid="";
		$active="";
	}
}
?>
