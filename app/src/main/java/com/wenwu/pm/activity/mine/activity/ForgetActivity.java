package com.wenwu.pm.activity.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wenwu.pm.R;
import com.wenwu.pm.activity.BaseActivity;

/**
 * 忘记密码
 */
public class ForgetActivity extends BaseActivity {
    private Button nextButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_forget_password);

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToNext = new Intent(ForgetActivity.this, SetNewPasswordActivity.class);
                startActivity(intentToNext);
            }
        });

       /* backButton = findViewById(R.id.back2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

    }
}
