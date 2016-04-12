<?php
		$query = $_GET['query'];

		require_once('dbConnect.php');
		
		$sql = "$query";
		
		if(mysqli_query($con,$sql)){
			echo '1';
		}else{
			echo '0';
		}
		
		mysqli_close($con);
?>