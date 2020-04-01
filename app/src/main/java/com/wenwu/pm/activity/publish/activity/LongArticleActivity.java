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
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.MainActivity;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.presenter.ArticlePresenter;
import com.wenwu.pm.utils.GetPhotoFromPhotoAlbumPathUtil;
import com.wenwu.pm.utils.OkHttpUtil;
import com.wenwu.pm.view.IArticleView;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LongArticleActivity extends AppCompatActivity implements IArticleView,View.OnClickListener {
    // 调用相册的requestCode
    private static final int ALBUM_REQUEST_CODE = 2;
    // 申请相册权限的requestCode
    private static final int PERMISSION_ALBUM_REQUEST_CODE = 2;

    private TextView title;
    private TextView content;
    private Button btn_addImg;
   // private Button publish;
    private ImageView showImg;

    private String imgUrl;
    private String location = null;

    private ArticlePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish_long_aitcle);
        initView();
    }

    private void initView() {
        presenter = new ArticlePresenter(this);
        Button publish = findViewById(R.id.publish_article);
        title = findViewById(R.id.article_title);
        content = findViewById(R.id.article_content);
        btn_addImg = findViewById(R.id.article_addImg);
        showImg = findViewById(R.id.article_imgShow);
        btn_addImg.setOnClickListener(this);
        publish.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.article_addImg:
                checkPermissionAndAlbum();
                break;
            case R.id.publish_article:
                presenter.publish();
                break;
        }
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
                if (data.getData() != null) {
                    Uri uri = data.getData();
                    String imgPath = GetPhotoFromPhotoAlbumPathUtil.getRealPathFromUri(this, uri);
                    Glide.with(this).load(uri).into(showImg);
                    OkHttpUtil.uploadImage("长图文", imgPath, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println("长图文：上传图片失败");
                            e.printStackTrace();
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            imgUrl = response.body().string();
                        }
                    });
                }

            }else {
                Toast.makeText(this,"请选择照片",Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    public String getArtTitle() {
        return title.getText().toString();
    }

    @Override
    public String getArtContent() {
        return content.getText().toString();
    }

    @Override
    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public String getArtLocation() {
        return location;
    }

    @Override
    public void onViewSuccess(Object object) {

      LRReturnJson  json = (LRReturnJson)object;
        Looper.prepare();
        Toast.makeText(LongArticleActivity.this,json.getMsg(), Toast.LENGTH_LONG).show();
        startActivity(new Intent(LongArticleActivity.this, MainActivity.class));
        Looper.loop();
    }

    @Override
    public void onViewFail(Object object) {
        LRReturnJson json = (LRReturnJson)object;
        Looper.prepare();
        Toast.makeText(getApplicationContext(),json.getMsg(), Toast.LENGTH_LONG).show();
        Looper.loop();
    }


}
