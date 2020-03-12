package com.wenwu.pm.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wenwu.pm.R;

public class LoginActivity extends AppCompatActivity {
    private Button registerButton;
    private Button mobileToLogin;
    private Button cancelToLogin;
    private Button forgetPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_login);

        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentToRegister);
            }
        });


        mobileToLogin = findViewById(R.id.mobileLogin);
        mobileToLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intentToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentToRegister);
            }
        });

        /*cancelToLogin = findViewById(R.id.cancel);
        cancelToLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               finish();
            }
        });*/

        forgetPassword = findViewById(R.id.forgotPassword);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToForgetPassword = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intentToForgetPassword);
            }
        });

    }

}
