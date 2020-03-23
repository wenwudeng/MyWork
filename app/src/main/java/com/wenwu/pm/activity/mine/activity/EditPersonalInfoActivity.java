package com.wenwu.pm.activity.mine.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wenwu.pm.R;
import com.wenwu.pm.goson.ShowReturnJson;
import com.wenwu.pm.utils.JsonUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditPersonalInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private RadioButton boy;
    private RadioButton girl;
    private Button save;

    private ImageView boyIcon;
    private ImageView girlIcon;

    private TextView userName;
    private CircleImageView userPhoto;
    private ImageView gender;
    private TextView city;
    private TextView pet;
    private TextView profile;
    private TextView time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_personal_info_edit);

        save = findViewById(R.id.my_edit_save_info);
        //显示数据
        userName = findViewById(R.id.my_edit_id);
        userPhoto = findViewById(R.id.my_edit_photo);
        gender = findViewById(R.id.my_sex);
        city = findViewById(R.id.my_edit_province);
        pet = findViewById(R.id.my_edit_pet);
        profile = findViewById(R.id.my_edit_introduce);
        time = findViewById(R.id.my_edit_register_time);

        boy = findViewById(R.id.my_edit_boy);
        girl = findViewById(R.id.my_edit_girl);
        boyIcon = findViewById(R.id.my_edit_boy_icon);
        girlIcon = findViewById(R.id.my_edit_girl_icon);
        boyIcon.setOnClickListener(this);
        girlIcon.setOnClickListener(this);
        showResponse(JsonUtil.showJson);

    }

    //线程更新
    public void showResponse(final ShowReturnJson json) {
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                userName.setText(json.getData().getUserName());
                if (json.getData().getGender().equals("男")) {
                    boyIcon.setImageResource(R.mipmap.sex_boy_selected);
                }else {
                    girlIcon.setImageResource(R.mipmap.sex_girl_selected);
                }
                city.setText(json.getData().getCity());
                pet.setText(json.getData().getPet());
                profile.setText(json.getData().getProfile());
                time.setText(json.getData().getTime());
            }
        }));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_edit_boy_icon:
                boyIcon.setImageResource(R.mipmap.sex_boy_selected);
                boy.setChecked(true);
                girl.setChecked(false);
                girlIcon.setImageResource(R.mipmap.sex_girl_nomal);
                break;
            case R.id.my_edit_girl_icon:
                girlIcon.setImageResource(R.mipmap.sex_girl_selected);
                girl.setChecked(true);
                boy.setChecked(false);
                boyIcon.setImageResource(R.mipmap.sex_boy_nomal);
                break;
            case R.id.my_edit_save_info:
                //updateWithOkHttp(String id, phone, String name, String sex, String live, String introduce, String pt);

        }
    }

    public void updateWithOkHttp(String id, String name, String sex, String live, String introduce, String pt){
        //OkHttpUtil.updateInfo("editInfo",id);
    }

}
