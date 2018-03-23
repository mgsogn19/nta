<?php
    $con = mysqli_connect("nane, pass, dbname");
    $userID = $_GET["userID"];
    $result = mysqli_query($con, "SELECT * FROM USER WHERE userID like '$userID';");
    $response = array();
    
    while($row = mysqli_fetch_array($result)){
        array_push($response, array("userPoint" => $row[4]));
    }
    
    echo json_encode(array("response" => $response));
    mysqli_close($con);
?>
