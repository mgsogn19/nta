package com.example.user.nta;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView regButton = findViewById(R.id.registerButton);
        regButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent regInt = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(regInt);
            }
        });
        Intent intent = getIntent();

        final EditText idText = findViewById(R.id.idText);
        final EditText PassText = findViewById(R.id.passwordText);
        final Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString();
                final String userPass = PassText.getText().toString();

                Response.Listener<String> reslis = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean success = jsonresponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder build = new AlertDialog.Builder(LoginActivity.this);
                                dialog = build.setMessage("로그인 성공")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID", userID);

                                LoginActivity.this.startActivity(intent);
                                finish();
                            }else{
                                AlertDialog.Builder build = new AlertDialog.Builder(LoginActivity.this);
                                dialog = build.setMessage("로그인 실패")
                                        .setNegativeButton("다시시도", null)
                                        .create();
                                dialog.show();
                            }

                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPass, reslis);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }

}
