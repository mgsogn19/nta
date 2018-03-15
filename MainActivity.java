package com.example.user.nta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView idview = findViewById(R.id.userID);
        final TextView userpoint = findViewById(R.id.point);
        int point = 0;

        Intent intent = getIntent();
        String user = intent.getStringExtra("userID");

        idview.setText(user);

        try{
            HttpClient client = new DefaultHttpClient();
            final String URL = "http://smg6135.cafe24.com/ogi.php";
            HttpPost post = new HttpPost(URL + "?" + "userID=" + user);

            HttpResponse response = client.execute(post);

            String arr[] = new String[1];

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            String page = "";
            while((line = bufferedReader.readLine()) != null){
                page += line;
            }

            JSONObject jsonObject = new JSONObject(page);
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);

                arr[0] = jsonObject.getString("userPoint");
            }
            String data = arr[0];

            userpoint.setText(data);



        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}

