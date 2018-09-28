<?php
	require "connect.php";

	$query = "SELECT DISTINCT * FROM playlist ORDER BY rand(" .date("Ymd") . ") LIMIT 3";
	class PlaylistToday{
		function PlaylistToday($idplaylist,$ten,$hinh,$icon){
			$this->idplaylist = $idplaylist;
			$this->ten = $ten;
			$this->hinh = $hinh;
			$this->icon = $icon;
		}
	}
	$arrayplaylisttoday = array();
	$data = mysqli_query($con,$query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($arrayplaylisttoday, new PlaylistToday(
			$row['idPlayList']
			,$row['Ten']
			,$row['Hinhnen']
			,$row['Hinhicon']));
	}
	echo json_encode($arrayplaylisttoday);
?>