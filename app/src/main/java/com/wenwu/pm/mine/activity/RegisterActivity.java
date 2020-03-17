package com.wenwu.pm.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wenwu.pm.MainActivity;
import com.wenwu.pm.R;
import com.wenwu.pm.goson.Msg;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button register;
    private Button sendCode;
    private Button existButton;

    private EditText phoneInput;
    private EditText passwordInput;
    private EditText codeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_register);

        register = findViewById(R.id.registerButton);
        existButton = findViewById(R.id.existAccount);
        sendCode = findViewById(R.id.sendCode);


        phoneInput = findViewById(R.id.registerName);
        passwordInput = findViewById(R.id.RegisterPassword);
        codeInput = findViewById(R.id.RegisterCode);


        existButton.setOnClickListener(this);
        register.setOnClickListener(this);
        sendCode.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.existAccount:
                Intent intentToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentToLogin);
                break;
            case R.id.registerButton:
                String account = phoneInput.getText().toString();
                String password = passwordInput.getText().toString();
                String code = codeInput.getText().toString();
                registerWithOkHttp("register",code,account,password);
                break;
        }
    }

    public void registerWithOkHttp(String mapping,String code,String account,String password) {
        OkHttpUtil.registerWithOkHttp(mapping, code, account, password, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                Msg msg = new Gson().fromJson(responseData, Msg.class);
                if (msg.getCode().equals("200")) {
                    Looper.prepare();
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    Looper.loop();
                }else {
                    Looper.prepare();
                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });
    }
}
