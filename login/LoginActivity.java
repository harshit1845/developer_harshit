package com.example.makesurest.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.makesurest.ApiClinet;
import com.example.makesurest.MainActivity;
import com.example.makesurest.R;
import com.example.makesurest.companyselection.CompanySelection;
import com.example.makesurest.model.LoginResponce;
import com.example.makesurest.model.SendLoginRequestModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
 SendLoginRequestModel loginRequest;

    String username,password;
    EditText userName,Password;
    Button btn;
    int flag =1;
    private LoginResponce responsee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.user_id);
        Password = findViewById(R.id.password);
        btn = findViewById(R.id.login);

        username = userName.getText().toString();
        password = Password.getText().toString();
//         flag = loginRequest.getFlag(1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(userName.getText().toString()) || TextUtils.isEmpty(Password.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                }else{
                 login();
                }

            }
        });

    }

    public void login() {
        SendLoginRequestModel loginRequest = new SendLoginRequestModel();
        loginRequest.setUsername(userName.getText().toString());
        loginRequest.setPassword(Password.getText().toString());
        loginRequest.setFlag(flag);

            Call<LoginResponce> loginResponseCall = ApiClinet.getUserService().userLogin(loginRequest);

            loginResponseCall.enqueue(new Callback<LoginResponce>() {

                @Override
                public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {

                    String responseMessage = response.message();

                    Log.d("LoginApi", responseMessage);

                    if (response.isSuccessful()) {
                        LoginResponce loginResponse = response.body();
//                        loginResponse.getMessage().equals("user i")

                      if (loginResponse.isUserActive()) {
                          Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                          Intent i = new Intent(LoginActivity.this, CompanySelection.class);
                          startActivity(i);

                      }

                      else  {
                          Toast.makeText(LoginActivity.this, "User not active.", Toast.LENGTH_LONG).show();
                      }

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<LoginResponce> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }

            });



    }

}




