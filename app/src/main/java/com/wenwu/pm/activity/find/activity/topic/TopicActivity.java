package com.wenwu.pm.activity.find.activity.topic;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.wenwu.pm.ActivityController;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.BaseActivity;
import com.wenwu.pm.activity.publish.activity.PublishLogActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 热议话题
 */
public class TopicActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private TopicViewPagerAdapter adapter;
    private FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        ActivityController.addActivity(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    public void init() {
        fb = findViewById(R.id.fb_take);
        fb.setOnClickListener(this::onClick);

        Toolbar toolbar = findViewById(R.id.topic_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new TopicViewPagerAdapter(getSupportFragmentManager(),initViewPager());
        viewPager = findViewById(R.id.topic_viewPager);
//        viewPager添加适配器
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.topic_tab);
//        tabLayout绑定ViewPager
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化ListFragment,将三个Fragment加入list
     * @return List<Fragment>集合
     */
    public List<Fragment> initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HotFragment());
        fragmentList.add(new LatestFragment());
        return fragmentList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb_take:
                startActivity(new Intent(this, PublishLogActivity.class));
        }
    }
}
