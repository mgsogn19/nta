package com.example.user.nta;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2/3/2018.
 */
public class GameRequest extends StringRequest {
    final static private String URL = "http://smg6135.cafe24.com/ogi.php";
    private Map<String, String> parameters;

    public GameRequest(String userID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override 
    public Map<String, String> getParams(){
        return parameters;
    }
}
