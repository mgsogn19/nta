<?php
    $con = mysqli_connect("localhost", "smg6135", "iamagod1026", "smg6135");
    $userID = $_GET["userID"];
    $result = mysqli_query($con, "SELECT * FROM USER WHERE userID like '$userID';");
    $response = array();
    
    while($row = mysqli_fetch_array($result)){
        array_push($response, array("userPoint" => $row[4]));
    }
    
    echo json_encode(array("response" => $response));
    mysqli_close($con);
?>
