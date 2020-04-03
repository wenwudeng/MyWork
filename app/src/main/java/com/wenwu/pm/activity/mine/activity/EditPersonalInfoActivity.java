package com.wenwu.pm.activity.mine.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.mine.fragment.MyFragment;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.goson.ShowReturnJson;
import com.wenwu.pm.presenter.EditInfoPresenter;
import com.wenwu.pm.utils.GetPhotoFromPhotoAlbumPathUtil;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.utils.OkHttpUtil;
import com.wenwu.pm.view.IEditInfoView;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    private String photoUrl;//返回头像url地址
    private TextView city;
    private TextView pet;
    private TextView profile;
    private TextView time;

    private EditInfoPresenter presenter;
    private LRReturnJson json;

    // 调用相册的requestCode
    private static final int ALBUM_REQUEST_CODE = 2;
    // 申请相册权限的requestCode
    private static final int PERMISSION_ALBUM_REQUEST_CODE = 2;
    private static MyFragment myFragment;
    //private Activity context;

    public static void openEdit(Context context, MyFragment myFragment1) {
        Intent intent = new Intent(context, EditPersonalInfoActivity.class);
        ((Activity) context).startActivity(intent);
        myFragment = myFragment1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_personal_info_edit);
       // context = this;
        initView();
        showResponse(JsonUtil.showJson);

        //修改个人信息
        setData();
        setEditView();
    }

    private void setEditView() {
        save = findViewById(R.id.my_edit_save_info);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.save();
                finish();
            }
        });
    }

    private void setData() {
        presenter = new EditInfoPresenter(this, myFragment);
    }

    /*控件初始化*/
    private void initView() {
        userName = findViewById(R.id.my_edit_id);
        userPhoto = findViewById(R.id.my_edit_photo);
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
        userPhoto.setOnClickListener(this);
    }


    //显示数据
    public void showResponse(final ShowReturnJson json) {
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.with(EditPersonalInfoActivity.this).load(json.getData().getPhoto()).into(userPhoto);
                userName.setText(json.getData().getUserName());
                if (json.getData().getGender().equals("男")) {
                    boyIcon.setImageResource(R.mipmap.sex_boy_selected);
                } else {
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
            case R.id.my_edit_photo:
                changePhoto();
                break;
        }
    }

    public void changePhoto() {
        checkPermissionAndAlbum();
    }

    private void checkPermissionAndAlbum() {
        int hasAlbumPermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasAlbumPermission == PackageManager.PERMISSION_GRANTED) {
            //有权限，获取相册
            openAlbum();
        } else {
            //没有权限，申请权限。
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_ALBUM_REQUEST_CODE);
        }
    }

    /*调用相册*/
    //激活相册操作
    private void openAlbum() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, ALBUM_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALBUM_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    Glide.with(this).load(uri).into(userPhoto);
                    String path = GetPhotoFromPhotoAlbumPathUtil.getRealPathFromUri(this, uri);
                    //头像上传服务器
                    OkHttpUtil.uploadImage(getUserName(), path, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            photoUrl = response.body().string();
                        }
                    });
                }
            } else {
                Toast.makeText(this, "请选择照片", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
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
