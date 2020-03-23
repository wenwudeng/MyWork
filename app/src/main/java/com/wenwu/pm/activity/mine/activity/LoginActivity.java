package com.wenwu.pm.activity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wenwu.pm.R;
import com.wenwu.pm.activity.MainActivity;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.presenter.LoginPresenter;
import com.wenwu.pm.view.ILoginView;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements ILoginView,View.OnClickListener{
    private Button register;
    private Button forgetPass;
    private Button quickLoginByMsg;


    private Button login;
    private EditText account;
    private EditText password;

    private LoginPresenter mPresenter;
    private LRReturnJson json;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_login);
        setData();
        setView();

        register = findViewById(R.id.register);
        register.setOnClickListener(this);
        forgetPass = findViewById(R.id.forgotPassword);
        forgetPass.setOnClickListener(this);
        quickLoginByMsg = findViewById(R.id.mobileLogin);
        quickLoginByMsg.setOnClickListener(this);
        }

    private void setData() {
        mPresenter = new LoginPresenter((ILoginView) this);
    }
    private void setView() {
        this.account = findViewById(R.id.loginPhone);
        this.password = findViewById(R.id.loginPassword);
        this.login =  findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mPresenter.login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
    public void onViewSuccess(Object json1) {
        json = (LRReturnJson)json1;
        Looper.prepare();
        Toast.makeText(getApplicationContext(),json.getMsg(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("user_id", json.getData());
        startActivity(intent);
        Looper.loop();
    }

    @Override
    public void onViewFail(Object json1) {
        json = (LRReturnJson)json1;
        Toast.makeText(getApplicationContext(),json.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
            case R.id.mobileLogin:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
        }
    }
}
