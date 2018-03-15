<?php
    $con = mysql_connect("localhost", "smg6135", "iamagod1026");
    mysql_select_db("smg6135", $con);
    $mysql_query = "SELECT userPoint FROM USER WHERE userID like 'smg6135'";
    $result = mysqli_query($con, $mysql_query);
    
    echo ($result);
    mysqli_close($con);
?>