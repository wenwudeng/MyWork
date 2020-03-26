package com.wenwu.pm.activity.publish.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wenwu.pm.R;

/**
 *提问求助activity
 */

public class QuestionActivity extends AppCompatActivity {
    private ImageButton imageButton;
    private TextView title;

    // 调用相册的requestCode
    private static final int ALBUM_REQUEST_CODE = 2;
    // 申请相册权限的requestCode
    private static final int PERMISSION_ALBUM_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_question);
        initView();
    }

    private void initView() {
        imageButton = findViewById(R.id.upload_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndAlbum();
            }
        });
    }

    private void checkPermissionAndAlbum() {
        int hasAlbumPermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasAlbumPermission == PackageManager.PERMISSION_GRANTED) {
            //有权限，获取相册
            openAlbum();
        } else {
            //没有权限，申请权限。
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_ALBUM_REQUEST_CODE);
        }
    }

    /*调用相册*/
    //激活相册操作
    private void openAlbum() {
        Intent intent=new Intent();
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
                    Glide.with(this).load(uri).into(imageButton);

                    //方法2
                     /* try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    ivPhoto.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
                }else {
                    Toast.makeText(this,"请选择照片",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

