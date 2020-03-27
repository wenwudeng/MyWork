package com.wenwu.pm.activity.mine.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import android.widget.TextView;
import android.widget.Toast;

import com.wenwu.pm.R;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.goson.ShowReturnJson;
import com.wenwu.pm.presenter.EditInfoPresenter;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.view.IEditInfoView;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditPersonalInfoActivity extends AppCompatActivity implements View.OnClickListener, IEditInfoView {
    private RadioButton boy;
    private RadioButton girl;
    private Button save;

    private ImageView boyIcon;
    private ImageView girlIcon;

    private TextView userName;
    private CircleImageView userPhoto;
    //private ImageView gender;
    private String gender;
    private TextView city;
    private TextView pet;
    private TextView profile;
    private TextView time;

    private EditInfoPresenter presenter;
    private LRReturnJson json;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_personal_info_edit);


        //显示个人信息
        ShowInfoView();

        //修改个人信息
        setData();
        setShowAfterEditView();
    }

    private void setShowAfterEditView() {
        save = findViewById(R.id.my_edit_save_info);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.save();
            }
        });
    }

    private void setData() {
        presenter = new EditInfoPresenter(this);
    }

    /*显示个人信息*/
    private void ShowInfoView() {
        //显示数据
        userName = findViewById(R.id.my_edit_id);
        userPhoto = findViewById(R.id.my_edit_photo);
        //gender = findViewById(R.id.my_sex);
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


    //显示数据
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
                gender = "男";
                girl.setChecked(false);
                girlIcon.setImageResource(R.mipmap.sex_girl_nomal);
                break;
            case R.id.my_edit_girl_icon:
                girlIcon.setImageResource(R.mipmap.sex_girl_selected);
                girl.setChecked(true);
                gender = "女";
                boy.setChecked(false);
                boyIcon.setImageResource(R.mipmap.sex_boy_nomal);
                break;
        }
    }

    @Override
    public String getPhotoUrl() {
        return "abc";
    }

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getGenders() {
        return gender;
    }

    @Override
    public String getCity() {
        return city.getText().toString();
    }

    @Override
    public String getProfile() {
        return profile.getText().toString();
    }

    @Override
    public String getPet() {
        return pet.getText().toString();
    }

    @Override
    public void onViewSuccess(Object object) {
        json = (LRReturnJson) object;
        Looper.prepare();
        Toast.makeText(getApplicationContext(), json.getMsg(), Toast.LENGTH_SHORT).show();
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
