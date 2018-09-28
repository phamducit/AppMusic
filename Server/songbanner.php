<?php
	require "connect.php";
	$query = "SELECT quangcao.id, quangcao.Hinhanh, quangcao.Noidung, quangcao.idbaihat, baihat.TenBaiHat, baihat.HinhBaiHat FROM `baihat` INNER JOIN quangcao ON quangcao.idbaihat = baihat.idBaiHat WHERE quangcao.idbaihat = baihat.idBaiHat";
	$data = mysqli_query($con,$query);
	
	class Quangcao{
		function QuangCao($id,$Hinhanh,$Noidung,$idbaihat,$TenBaiHat,$HinhBaiHat){
			$this->id = $id;
			$this->Hinhanh = $Hinhanh;
			$this->Noidung = $Noidung;
			$this->idbaihat = $idbaihat;
			$this->TenBaiHat = $TenBaiHat;
			$this->HinhBaiHat = $HinhBaiHat;
		}
	}
	$mangquangcao = array();
	while($row = mysqli_fetch_assoc($data)){
		array_push($mangquangcao, new Quangcao($row['id'],
			$row['Hinhanh'],
			$row['Noidung'],
			$row['idbaihat'],
			$row['TenBaiHat'],
			$row['HinhBaiHat']));
	}
	echo json_encode($mangquangcao);
?>