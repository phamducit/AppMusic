<?php
	require "connect.php";
	class Album{
		function Album($idalbum,$tenalbum,$tencasialbum,$hinhalbum){
			$this->idalbum = $idalbum;
			$this->tenalbum = $tenalbum;
			$this->tencasialbum = $tencasialbum;
			$this->hinhalbum = $hinhalbum;
		}
	}

	$arrayalbum = array();
	$query = "SELECT * FROM album";
	$data = mysqli_query($con,$query);
	while ($row = mysqli_fetch_assoc($data)){
		array_push($arrayalbum, new Album(
			$row['idAlbum']
			,$row['TenAlbum']
			,$row['TenCaSiAlbum']
			,$row['HinhAlbum']));
	}
	echo json_encode($arrayalbum);
?>