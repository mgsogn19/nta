package com.example.user.nta;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 1/18/2018.
 */

public class PointRequest extends StringRequest {
    final static private String URL = "http://smg6135.cafe24.com/userregisteration.php";
    private Map<String, String> parameters;

    public PointRequest(String userID, String userPassword, String userGender, String userEmail, String userPoint, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userGender", userGender);
        parameters.put("userEmail", userEmail);
        parameters.put("userPoint", userPoint);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
