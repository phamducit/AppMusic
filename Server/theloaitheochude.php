<?php
	require "connect.php";
	
	class Theloai{
		function Theloai($idtheloai, $idchude, $tentheloai, $hinhtheloai){
			$this->idtheloai = $idtheloai;
			$this->idkeychude = $idchude;
			$this->tentheloai = $tentheloai;
			$this->hinhtheloai = $hinhtheloai;
		}
	}
	$arraytheloai = array();
	$idchude = $_POST['idchude'];
	$query = "SELECT * FROM theloai WHERE idChuDe = '$idchude' ";
	$data = mysqli_query($con,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		 array_push($arraytheloai, new Theloai($row['idTheLoai'],
											$row['idChuDe'],
											$row['TenTheLoai'],
											$row['HinhTheLoai'])); 
	}
	echo json_encode($arraytheloai);
?>