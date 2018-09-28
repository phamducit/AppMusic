<?php
	require "connect.php";
	
	class Baihat{
		function Baihat($idbaihat,$tenbaihat,$hinhbaihat,$casi,$linkbaihat,$luotthich){
			$this->idbaihat = $idbaihat;
			$this->tenbaihat = $tenbaihat;
			$this->hinhbaihat = $hinhbaihat;
			$this->casi = $casi;
			$this->linkbaihat = $linkbaihat;
			$this->luotthich = $luotthich;
		}
	}
	$arraydanhsachbaihat = array();

	if(isset($_POST['idalbum'])){
		$idalbum = $_POST['idalbum'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idalbum',idAlbum)";
	}
	
	if(isset($_POST['idtheloai'])){
		$idtheloai = $_POST['idtheloai'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idtheloai', idTheLoai)";
	}

	if(isset($_POST['idplaylist'])){
		$idplaylist = $_POST['idplaylist'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idplaylist',idPlayList)";
	}

	if(isset($_POST['idquangcao'])){
		$idquangcao = $_POST['idquangcao'];
		$queryquangcao = "SELECT * FROM quangcao WHERE id = '$idquangcao'";
		$dataquangcao = mysqli_query($con,$queryquangcao);
		$rowquangcao = mysqli_fetch_assoc($dataquangcao);
		$idbaihat = $rowquangcao['idbaihat'];
		$query = "SELECT * FROM baihat WHERE idBaiHat = '$idbaihat'";
	}


	$data = mysqli_query($con,$query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($arraydanhsachbaihat, new Baihat(
			 $row['idBaiHat']
			,$row['TenBaiHat']
			,$row['HinhBaiHat']
			,$row['Casi']
			,$row['LinkBaiHat']
			,$row['LuotThich']));
	}
	echo json_encode($arraydanhsachbaihat);
?>