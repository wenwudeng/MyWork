package com.wenwu.pm.activity.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.activity.MsgCollectPraiseActivity;
import com.wenwu.pm.activity.mine.adapter.MyPagerAdapter;
import com.wenwu.pm.activity.mine.fragment.MyCollectFragment;
import com.wenwu.pm.activity.mine.fragment.MyLogFragment;
import com.wenwu.pm.activity.mine.fragment.MyQuestionFragment;
import com.wenwu.pm.activity.mine.fragment.MyReviewFragment;

import java.util.ArrayList;
import java.util.List;

public class PersonHomeActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;

    private TextView follow;
    private TextView fans;
    private TextView collect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_show);
        initView();
    }

    private void initView() {
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), initViewPager());
        Toolbar toolbar = findViewById(R.id.person_home_toolbar);
        toolbar.setTitle("程序员");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = findViewById(R.id.view_pager_person);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout = findViewById(R.id.tab_layout_person);
        tabLayout.setupWithViewPager(viewPager);
        follow = findViewById(R.id.person_concern);
        follow.setOnClickListener(this);

        fans = findViewById(R.id.person_fans);
        fans.setOnClickListener(this);

        collect = findViewById(R.id.person_collect);
        collect.setOnClickListener(this);


    }

    public List<Fragment> initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new MyLogFragment());
        fragmentList.add(new MyQuestionFragment());
        fragmentList.add(new MyCollectFragment());
        fragmentList.add(new MyReviewFragment());
        return fragmentList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.person_concern:
                Toast.makeText(v.getContext(),"关注",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(), FollowActivity.class));
                break;

            case R.id.person_fans:
                Toast.makeText(v.getContext(),"粉丝",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(), FansActivity.class));
                break;

            case R.id.person_collect:
                Toast.makeText(v.getContext(),"收藏",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(), MsgCollectPraiseActivity.class));
                break;
        }
    }
}
