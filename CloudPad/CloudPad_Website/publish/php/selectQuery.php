<?php 
	$query = $_GET['query'];
	require_once('dbConnect.php');
	
	$sql = "$query";
	$result = mysqli_query($con,$sql);
	
	$json = mysqli_fetch_all ($result, MYSQLI_ASSOC);
	echo json_encode($json);
	
	mysqli_close($con);
?>