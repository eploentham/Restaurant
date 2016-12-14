<html>
<head>
<title>e-sern rod ded.Com</title>
</head>
<body>
<?php
 $conn = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
 if($conn )
 {
  echo "เชื่อมต่อฐานข้อมูลเรียบร้อย.";
 }
 else
 {
  echo "ไม่สามารถเชื่อมต่อฐานข้อมูลได้.";
 }

 mysql_close($conn );
?>
</body>
</html>