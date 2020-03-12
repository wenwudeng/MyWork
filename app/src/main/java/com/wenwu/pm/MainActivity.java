package com.wenwu.pm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.view.EasyNavigationBar;
import com.wenwu.pm.find.fragment.FindFragment;
import com.wenwu.pm.home.fragment.HomeFragment;
import com.wenwu.pm.message.fragment.MessageFragment;
import com.wenwu.pm.mine.activity.LoginActivity;
import com.wenwu.pm.mine.fragment.MyFragment;
import com.wenwu.pm.publish.activity.LongArticleActivity;
import com.wenwu.pm.publish.activity.QuestionActivity;


import java.util.ArrayList;
import java.util.List;

import lrq.com.addpopmenu.PopMenu;
import lrq.com.addpopmenu.PopMenuItem;
import lrq.com.addpopmenu.PopMenuItemListener;

public class MainActivity extends AppCompatActivity {

    private EasyNavigationBar navigationBar;


    private String[] tabText = {"首页", "发现", "", "消息", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.tab_home_normal, R.mipmap.tab_pic_normal, R.mipmap.btn_pai, R.mipmap.tab_shop_normal, R.mipmap.tab_mine_normal};
    //选中时icon
    private int[] selectIcon = {R.mipmap.tab_home_pressed, R.mipmap.tab_pic_pressed, R.mipmap.btn_pai, R.mipmap.tab_shop_pressed, R.mipmap.tab_mine_pressed};
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initNavigationBar().setAddViewLayout(createNaviBarView());
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
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            //return true则拦截事件、不进行页面切换
                            return false;
                        } else if (position == 2) {
                            //跳转页面
                            // showMunu();
                            PopMenu mPopMenu = new PopMenu.Builder().attachToActivity(MainActivity.this)
                                    .addMenuItem(new PopMenuItem("发布日志", getResources().getDrawable(R.mipmap.publish_post)))
                                    .addMenuItem(new PopMenuItem("发长图文", getResources().getDrawable(R.mipmap.post_content)))
                                    .addMenuItem(new PopMenuItem("提出问题", getResources().getDrawable(R.mipmap.publish_ask)))

                                    .setOnItemClickListener(new PopMenuItemListener() {
                                        @Override
                                        public void onItemClick(PopMenu popMenu, int position) {
                                            if (position == 0) {
                                                Toast.makeText(MainActivity.this, "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                                            } else if (position == 1) {
                                                Toast.makeText(MainActivity.this, "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MainActivity.this, LongArticleActivity.class));
                                            }
                                            else {
                                                Toast.makeText(MainActivity.this, "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MainActivity.this, QuestionActivity.class));
                                            }
                                        }
                                    })
                                    .build();

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

    public EasyNavigationBar getNavigationBar() {
        return navigationBar;
    }

   /* private void gotoAddArticleActivity(){
        startActivity(new Intent(this,AddArticleActivity.class));
    }
    public static void openIMart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity)context).finish();
    }*/
}
