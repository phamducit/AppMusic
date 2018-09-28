<?php
	require "connect.php";

	$query = "SELECT * FROM playlist";
	$data = mysqli_query($con,$query);

	class Danhsachplaylist{
		function Danhsachplaylist($idplaylist,$ten,$hinh,$icon){
			$this->idplaylist = $idplaylist;
			$this->ten = $ten;
			$this->hinh = $hinh;
			$this->icon = $icon;
		}
	}
	$arrayplaylist = array();
	while($row = mysqli_fetch_assoc($data)){
		array_push($arrayplaylist, new Danhsachplaylist(
			$row['idPlayList'],
			$row['Ten'],
			$row['Hinhnen'],
			$row['Hinhicon']));
	}
	echo json_encode($arrayplaylist);
?>