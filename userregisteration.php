<?php
   $con = mysqli_connect("localhost", "smg6135", "iamagod1026","smg6135");

    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];
    $userGender = $_POST["userGender"];
    $userEmail = $_POST["userEmail"];
    $userPoint = $_POST["userPoint"];
    
    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssss", $userID, $userPassword, $userGender, $userEmail, $userPoint);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;
    
    echo json_encode($response);
?>