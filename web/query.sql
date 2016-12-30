Select sum(amount) as amt, sum(discount) as discount, sum(total) as total, sum(service_charge) as sc, sum(vat) as vat, sum(nettotal) as nettotal, count(1) as cnt_bill 
From t_bill 
Where status_closeday <> '1'  and bill_date >= '2016-12-21 00:00:00' and bill_date <= '2016-12-21 23:59:59' and active = '1'



Select * From t_closeday Where res_id = 'beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8' and active ='1'   and closeday_date >= '2016-12-21 00:00:00' and closeday_date <= '2016-12-21 23:59:59'



Select * From b_table Where table_id = '6e7f4e6b-5a1c-11e6-99a1-c03fd5b6f2e8'





http://localhost/restaurant/BillByCloseDay.php?userdb=root&passworddb=&closeday_date=2016-12-21&res_id=beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8
http://localhost/restaurant/BillByCloseDay2.php?userdb=root&passworddb=&closeday_date=2016-12-21&res_id=beefcdc0-5a1d-11e6-99a1-c03fd5b6f2e8

