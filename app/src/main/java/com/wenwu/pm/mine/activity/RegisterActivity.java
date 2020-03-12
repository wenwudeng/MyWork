package com.wenwu.pm.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wenwu.pm.R;

public class RegisterActivity extends AppCompatActivity {
    private Button existButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_register);
        existButton = findViewById(R.id.existAccount);
        existButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentToLogin);
            }
        });

/*        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }
}
