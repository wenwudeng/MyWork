package com.wenwu.pm.activity.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.MainActivity;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.presenter.RegisterPresenter;
import com.wenwu.pm.utils.OkHttpUtil;
import com.wenwu.pm.view.IRegisterView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IRegisterView {
    private Button register;
    private Button sendCode;
    private Button existButton;

    private EditText account;
    private EditText password;
    private EditText verifyCode;

    private RegisterPresenter presenter;

    private LRReturnJson json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_register);

        setData();
        setView();

        existButton = findViewById(R.id.existAccount);
        sendCode = findViewById(R.id.sendCode);

        existButton.setOnClickListener(this);
        sendCode.setOnClickListener(this);


    }

    private void setView() {
        account = findViewById(R.id.registerName);
        password = findViewById(R.id.RegisterPassword);
        verifyCode = findViewById(R.id.RegisterCode);
        register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register();
            }
        });
    }

    private void setData() {
        presenter = new RegisterPresenter(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.existAccount) {
            Intent intentToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intentToLogin);
        }
    }



    @Override
    public String getAccount() {
        return account.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public String getVerifyCode() {
        return verifyCode.getText().toString();
    }

    @Override
    public void onViewSuccess(Object object) {
        json = (LRReturnJson) object;
        Looper.prepare();
        Toast.makeText(getApplicationContext(), json.getMsg()+",请完善你的个人信息", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra("userId", json.getData());
        startActivity(intent);
        Looper.loop();
    }

    @Override
    public void onViewFail(Object object) {
        json = (LRReturnJson) object;
        Looper.prepare();
        Toast.makeText(getApplicationContext(), json.getMsg(), Toast.LENGTH_SHORT).show();
        Looper.loop();
    }
}
