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
class User 
{
	var $id,$userlogin,$username,$password,$privilege,$active,$remark, $permissionvoidbill, $permissionvoidcloseday,$table,$pkfield;
	function __construct()
	{
		# code...
		$id="";
		$userlogin="";
		$username="";
		$active="";
		$password="";
		$privilege="";
		$remark="";
		$sort1="";
		$permissionvoidbill="";
		$permissionvoidcloseday="";

		$table="";
		$pkfield="";
	}
}
/**
* 
*/
class Order 
{
	var $id, $foodsid, $orderdate, $price, $qty, $remark, $lotid, $rescode, $tablecode, $deviceid, $areacode, $active, $statusfoods1,$foodscode,$foodsname,$cntcust;
	var $statusfoods2,$statusfoods3,$statusbill,$billcheckdate,$statuscook,$cookreceivedate,$cookfinishdate,$statusvoid,$voiddate,$table,$pkfield,$statustogo,$datecreate,$datemodi;
	var $hostid;
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
		$hostid="";
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
		$cntcust="";

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
	var $id,$code,$name,$sort1,$active,$remark,$rh1,$rh2,$rf1,$rf2,$billcode,$taxid,$defaultres,$prnf1,$prnf2,$prnf3,$prnw1,$prnw2,$prnw3,$table,$pkfield;
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
		$rh1="";
		$rh2="";
		$rf1="";
		$rf2="";
		$billcode="";
		$taxid="";
		$defaultres="";
		$prnf1="";
		$prnf2="";
		$prnf3="";
		$prnw1="";
		$prnw2="";
		$prnw3="";
		
		$table="";
		$pkfield="";
	}
}
class Bill
{
	var $id, $billdate, $lotid, $active, $remark, $statusvoid, $voiddate, $tableid, $resid, $areaid, $deviceid, $amt, $discount, $servicecharge, $vat, $total, $nettotal,$datecreate,$datemodi,$billcode,$billuser, $statuscloseday,$closedayid,$hostid,$branchid, $cashreceive,$cashton;
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
		$billuser="";
		$statuscloseday="";
		$closedayid="";
		$hostid="";
		$branchid="";
		$cashreceive="";
		$cashton="";
		$billuser="";
		//$weather="";
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
class Closeday{
	var $id, $closeday_date, $res_id, $amount, $discount, $total, $service_charge, $vat, $nettotal, $remark, $active, $status_void, $void_date, $void_user, $cnt_bill, $bill_amount, $cnt_order, $amount_order, $closeday_user;
	var $cash_receive1, $cash_receive2, $cash_receive3, $cash_draw1, $cash_draw2, $cash_draw3, $cash_receive1_remark, $cash_receive2_remark, $cash_receive3_remark, $cash_draw1_remark, $cash_draw2_remark, $cash_draw3_remark, $weather;
}
?>