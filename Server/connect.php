<?php
	$hostname = "localhost";
	$username = "id6979396_minhducit";
	$password = "duckhopro";
	$databasename = "id6979396_mp3zing";

	$con = mysqli_connect($hostname,$username,$password,$databasename);
	mysqli_query($con,"SET NAME 'utf8'");
?>