package com.wenwu.pm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

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

import com.wenwu.pm.goson.LoginReturnJson;
import com.wenwu.pm.activity.home.fragment.HomeFragment;
import com.wenwu.pm.activity.message.fragment.MessageFragment;
import com.wenwu.pm.activity.mine.fragment.MyFragment;
import com.wenwu.pm.activity.publish.activity.LongArticleActivity;
import com.wenwu.pm.activity.publish.activity.QuestionActivity;

import com.wenwu.pm.utils.JsonUtil;




import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import lrq.com.addpopmenu.PopMenu;
import lrq.com.addpopmenu.PopMenuItem;
import lrq.com.addpopmenu.PopMenuItemListener;


public class MainActivity extends AppCompatActivity  {

    private EasyNavigationBar navigationBar;

    //个人信息数据对接



    private String[] tabText = {"首页", "发现", "", "消息", "我的"};
    private int[] normalIcon = {R.mipmap.tab_home_normal, R.mipmap.tab_pic_normal, R.mipmap.btn_pai, R.mipmap.tab_shop_normal, R.mipmap.tab_mine_normal};
    private int[] selectIcon = {R.mipmap.tab_home_pressed, R.mipmap.tab_pic_pressed, R.mipmap.btn_pai, R.mipmap.tab_shop_pressed, R.mipmap.tab_mine_pressed};
    private List<Fragment> fragments = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("=======MainActivity=====");

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





}



