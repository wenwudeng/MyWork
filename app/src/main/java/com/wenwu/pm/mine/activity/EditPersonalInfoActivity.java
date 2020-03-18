package com.wenwu.pm.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wenwu.pm.R;

public class EditPersonalInfoActivity extends AppCompatActivity {
    private RadioGroup gender;
    private RadioButton boy;
    private RadioButton girl;
    private ImageView boyIcon;
    private ImageView girlIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_personal_info_edit);

        boy = findViewById(R.id.my_edit_boy);
        girl = findViewById(R.id.my_edit_girl);

        boyIcon = findViewById(R.id.my_edit_boy_icon);
        boyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boyIcon.setImageResource(R.mipmap.sex_boy_selected);
                boy.setChecked(true);
                girl.setChecked(false);
                girlIcon.setImageResource(R.mipmap.sex_girl_nomal);

            }
        });

        girlIcon = findViewById(R.id.my_edit_girl_icon);
        girlIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girlIcon.setImageResource(R.mipmap.sex_girl_selected);
                girl.setChecked(true);
                boy.setChecked(false);
                boyIcon.setImageResource(R.mipmap.sex_boy_nomal);
            }
        });

    }


}
