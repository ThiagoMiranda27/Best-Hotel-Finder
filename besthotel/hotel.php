<?php


	$host = 'localhost';
	$user = 'root';
	$pwd = '';
	$db = 'besthotel';

	$conn = mysqli_connect($host, $user, $pwd, $db);
    	


	$response = array();
	$sql_query = "select * from hotel";
	
	$result = mysqli_query($conn, $sql_query);
	
	if (mysqli_num_rows($result) > 0 ){
		$response['success'] = 1;
		$hotel = array();
		while($row = mysqli_fetch_assoc($result))
		{
			# code ...
			array_push($hotel, $row);
		}
		$response['hotel'] = $hotel;
	}
	else{
		$response['sucess'] = 0;
		$response['message'] = 'No data';
	}

    echo json_encode($response);
    mysqli_close($conn);


?>
