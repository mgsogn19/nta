package com.example.user.nta;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private String userID;
    private String userPassword;
    private String userEmail;
    private String userGender;
    private String userPoint;
    private AlertDialog dialog;
    private boolean validate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText idText = (EditText) findViewById(R.id.idReg);
        final EditText passText = (EditText) findViewById(R.id.passReg);
        final EditText emailText = (EditText) findViewById(R.id.emailReg);

        RadioGroup gendergroup = (RadioGroup) findViewById(R.id.genderGroup);
        int genderGroupid = gendergroup.getCheckedRadioButtonId();
        userGender = ((RadioButton) findViewById(genderGroupid)).getText().toString();

        gendergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton genderbutton = findViewById(checkedId);
                userGender = genderbutton.getText().toString();
            }
        });

        final Button validatecheck = findViewById(R.id.valibutton);
        validatecheck.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                if(validate){
                    return;
                }if(userID.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디는 빈칸일수 없습니다")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;

                }
                Response.Listener<String> responseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할수 있는 아이디입니다")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                idText.setEnabled(false);
                                validate = true;
                                idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                validatecheck.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("중복된 아이디 입니다")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                                return;
                            }

                        }catch(Exception e){
                            e.printStackTrace();

                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(userID, responseLister);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });

        Button registerButton = findViewById(R.id.finalregbutton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPassword = passText.getText().toString();
                String userEmail = emailText.getText().toString();

                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("먼저 중복체크를 해주세요")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }

                if (userID.equals("") || userPassword.equals("") || userEmail.equals("") || userGender.equals("") ){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("빈칸없이 입력해주세요")
                            .setNegativeButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원등록에 성공했습니다")
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .create();
                                dialog.show();

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원등록에 실패했습니다")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }

                        }catch(Exception e){
                            e.printStackTrace();

                        }
                    }
                };
                userPoint = "0";
                RegisterRequest registerRequest = new RegisterRequest(userID, userPassword, userGender, userEmail, userPoint, responseLister);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
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
