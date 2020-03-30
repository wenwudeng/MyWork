package com.wenwu.pm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.view.EasyNavigationBar;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.find.fragment.FindFragment;
import com.wenwu.pm.activity.publish.activity.PublishLogActivity;
import com.wenwu.pm.goson.ShowReturnJson;
import com.wenwu.pm.activity.home.fragment.HomeFragment;
import com.wenwu.pm.activity.message.fragment.MessageFragment;
import com.wenwu.pm.activity.mine.fragment.MyFragment;
import com.wenwu.pm.activity.publish.activity.LongArticleActivity;
import com.wenwu.pm.activity.publish.activity.QuestionActivity;
import com.wenwu.pm.presenter.ShowInfoPresenter;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.view.IShowView;



import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import lrq.com.addpopmenu.PopMenu;
import lrq.com.addpopmenu.PopMenuItem;
import lrq.com.addpopmenu.PopMenuItemListener;


public class MainActivity extends AppCompatActivity implements IShowView {

    private EasyNavigationBar navigationBar;

    //个人信息数据对接
    private ShowReturnJson json;
    private ShowInfoPresenter presenter;
    private  int userId;


    private String[] tabText = {"首页", "发现", "", "消息", "我的"};
    private int[] normalIcon = {R.mipmap.tab_home_normal, R.mipmap.tab_pic_normal, R.mipmap.btn_pai, R.mipmap.tab_shop_normal, R.mipmap.tab_mine_normal};
    private int[] selectIcon = {R.mipmap.tab_home_pressed, R.mipmap.tab_pic_pressed, R.mipmap.btn_pai, R.mipmap.tab_shop_pressed, R.mipmap.tab_mine_pressed};
    private List<Fragment> fragments = new ArrayList<>();

    private TextView userName;
    private CircleImageView userPhoto;
    private TextView concernCount;
    private TextView fansCount;
    private TextView collectCount;
    private ImageView gender;
    private TextView city;
    private TextView pet;
    private TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationBar = findViewById(R.id.navigationBar);

        fragments.add(new HomeFragment());
        fragments.add(new FindFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MyFragment());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .addLayoutRule(EasyNavigationBar.RULE_BOTTOM)
                .addLayoutBottom(100)
                .onTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        if (position == 4) {
                            //显示个人信息
                            Intent intent = getIntent();
                            userId = intent.getIntExtra("user_id",0);
                            Log.d("===",""+ userId);
                            setData();
                            setView(userId);


                           // showInfoWithOkHttp("showInfo", intent.getStringExtra("account"));

                        } else if (position == 2) {
                            PopMenu mPopMenu = new PopMenu.Builder().attachToActivity(MainActivity.this)
                                    .addMenuItem(new PopMenuItem("发布日志", ResourcesCompat.getDrawable(getResources(), R.mipmap.publish_post, null)))
                                    .addMenuItem(new PopMenuItem("发长图文", ResourcesCompat.getDrawable(getResources(), R.mipmap.post_content, null)))
                                    .addMenuItem(new PopMenuItem("提出问题", ResourcesCompat.getDrawable(getResources(), R.mipmap.publish_ask, null)))
                                    .setOnItemClickListener(new PopMenuItemListener() {
                                        @Override
                                        public void onItemClick(PopMenu popMenu, int position) {
                                            if (position == 0) {
                                                Toast.makeText(MainActivity.this, "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MainActivity.this, PublishLogActivity.class));
                                            } else if (position == 1) {
                                                Toast.makeText(MainActivity.this, "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MainActivity.this, LongArticleActivity.class));
                                            } else {
                                                Toast.makeText(MainActivity.this, "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MainActivity.this, QuestionActivity.class));
                                            }
                                        }
                                    })
                                    .build();

                            mPopMenu.setmBackGroundTrasnparent();
                            mPopMenu.setmMarginTopRemainSpace(1.2f);
                            mPopMenu.setmIsmalpositionAnimatOut(true);
                            mPopMenu.setmBackGroundColor(Color.parseColor("#ffffff"));
                            mPopMenu.show();

                        }
                        return false;
                    }
                })
                .mode(EasyNavigationBar.MODE_ADD)
                .anim(Anim.ZoomIn)
                .build();
    }

    private void setView(int  userId) {
        userName = findViewById(R.id.my_user_name);
        userPhoto = findViewById(R.id.my_user_photo);
        concernCount = findViewById(R.id.my_concern_count);
        collectCount = findViewById(R.id.my_collect_count);
        fansCount = findViewById(R.id.my_fans_count);
        gender = findViewById(R.id.my_sex);
        city = findViewById(R.id.my_city);
        pet = findViewById(R.id.my_pet);
        profile = findViewById(R.id.my_profile);
        presenter.showInfo(userId);
    }

    private void setData() {
        this.presenter = new ShowInfoPresenter(this);
    }


    @Override
    public void onViewSuccess(Object object) {
        json = (ShowReturnJson) object;
        JsonUtil.showJson = json;
        Looper.prepare();
        Toast.makeText(getApplicationContext(),json.getMessage(), Toast.LENGTH_LONG).show();
        showResponse(json);
        Looper.loop();
    }

    @Override
    public void onViewFail(Object object) {
        json = (ShowReturnJson) object;
        Looper.prepare();
        Toast.makeText(getApplicationContext(),json.getMessage(), Toast.LENGTH_LONG).show();
        Looper.loop();
    }

    //线程更新
    public void showResponse(final ShowReturnJson json) {
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.with(getBaseContext()).load(json.getData().getPhoto()).into(userPhoto);
                userName.setText(json.getData().getUserName());
                fansCount.setText(Integer.toString(json.getData().getFollow()));
                concernCount.setText(Integer.toString(json.getData().getFollow()));
                collectCount.setText(Integer.toString(json.getData().getCollect()));
                if (json.getData().getGender().equals("男")) {
                    gender.setImageResource(R.drawable.sex_boy_p);
                }else {
                    gender.setImageResource(R.drawable.sex_girl_p);
                }
                city.setText(json.getData().getCity());
                pet.setText(json.getData().getPet());
                profile.setText(json.getData().getProfile());
            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得在销毁的时候断掉引用链，养成良好的习惯
        this.presenter = null;
    }


}



