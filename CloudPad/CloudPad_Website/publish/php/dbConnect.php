<?php
	/*
		author: Belal Khan 
		website: http://www.simplifiedcoding.net
		
		My Database is androiddb 
		you need to change the database name rest the things are default if you are using wamp or xampp server
		You may need to change the host user name or password if you have changed the defaults in your server
	*/
	
	define('HOST','athena01.fhict.local');
	define('USER','dbi324201');
	define('PASS','s8SXuJPyVN');
	define('DB','dbi324201');
	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Kon geen verbinding maken met de server.\r\nProbeer het later nog eens.');