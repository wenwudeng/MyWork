package com.wenwu.pm.activity.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wenwu.pm.ActivityController;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.BaseActivity;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_setting);

        Button loginOut = findViewById(R.id.login_off);
        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityController.finishAll();
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));

            }
        });
    }
}
