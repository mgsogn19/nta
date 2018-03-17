package com.example.user.nta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView idtext = (TextView) findViewById(R.id.userID);
        final TextView point = (TextView) findViewById(R.id.point);

        Intent intent = getIntent();
        String userid = intent.getStringExtra("userID");
        idtext.setText(userid);



        Response.Listener<String> listener = new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray arr = new JSONArray("response");

                    point.setText(arr.getJSONObject(0).getString("userPoint"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };





    }

}

/*<?php
    $con = mysqli_connect("localhost", "smg6135", "iamagod1026", "smg6135");
    $userID = "smg6135";
    $result = mysqli_query($con, "SELECT * FROM USER WHERE userID like '$userID';");
    $response = array();
    
    while($row = mysqli_fetch_array($result)){
        array_push($response, array("userPoint" => $row[4]));
    }
    
    echo json_encode(array("response" => $response));
    mysqli_close($con);
?>  
------This PHP returns the userPoint value from the table in an array call response like this
 {
	"response": [{
		"userPoint": "0"
	}]
}
-----Than why won't the final pointview point read out the userPoint?
