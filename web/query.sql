Select sum(amount) as amt, sum(discount) as discount, sum(total) as total, sum(service_charge) as sc, sum(vat) as vat, sum(nettotal) as nettotal, count(1) as cnt_bill 
From t_bill 
Where status_closeday <> '1'  and bill_date >= '2016-12-21 00:00:00' and bill_date <= '2016-12-21 23:59:59' and active = '1'



Select * From t_closeday Where res_id = 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8' and active ='1'   and closeday_date >= '2016-12-21 00:00:00' and closeday_date <= '2016-12-21 23:59:59'



Select * From b_table Where table_id = '6e7f4e6b-5a1c-11e6-99a1-c03fd5b6f2e8'





http://localhost/restaurant/BillByCloseDay.php?userdb=root&passworddb=&closeday_date=2016-12-21&res_id=beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8
http://localhost/restaurant/BillByCloseDay2.php?userdb=root&passworddb=&closeday_date=2016-12-21&res_id=beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8
http://ec2-35-166-120-55.us-west-2.compute.amazonaws.com/restaurant/BillByCloseDay2.php?closeday_date=2017-01-10&res_id=beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8&userdb=root&passworddb=Ekartc2c5





Insert into t_bill(bill_id,bill_date,date_create,remark
	,status_void,table_id,res_id,area_id
	,device_id,amount,discount,active
	,service_charge,vat,total,nettotal
	,bill_code,bill_user,cash_receive,cash_ton
	,status_closeday,closeday_id) 
	value ('1654ba17-8329-492e-880e-92385b150989',now(),now(),''
	,'0','dd9f4e7d-5a1b-11e6-99a1-c03fd5b6f2e8','','a01f92e1-5926-11e6-bfd0-c03fd5b6f2e8'
	,'',,0.0,'1',0.0,0.0,100.0,100.0,'600100003','',100.00,0.0,'0','')
01-10 14:56:01.703 19784-19890/com.nakoyagarden.ekapop.restaurant I/Syste


 Insert into t_closeday(closeday_id,closeday_date,res_id,amount,discount,total
 	,service_charge,vat,nettotal,remark,active
 	,status_void,void_date,void_user,cnt_bill,bill_amount
 	,cnt_order,amount_order,closeday_user,cash_receive1
 	,cash_receive2,cash_receive3,cash_draw1,cash_draw2,cash_draw3,cash_receive1_remark
 	,cash_receive2_remark,cash_receive3_remark,cash_draw1_remark,cash_draw2_remark
 	,cash_draw3_remark,weather) 
 value ('1be2e98c-7bc3-4127-9d1c-1b4cfdc4d4eb',now(),'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8',100.00,0.00
 	,100.00,0.00,0.00,100.00,''
 	,'1','0','','',1.00
 	,100.00,จำนวนบิล 0.00,0.0,'447e331c-ad32-11e6-96c5-02004c4f4f50',0.00,0.00,0.00,0.00,0.00,0.00,'','','','','','','')


