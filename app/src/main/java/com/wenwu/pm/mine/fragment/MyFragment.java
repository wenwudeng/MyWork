package com.wenwu.pm.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.next.easynavigation.utils.NavigationUtil;
import com.next.easynavigation.view.EasyNavigationBar;
import com.wenwu.pm.R;
import com.wenwu.pm.mine.activity.EditPersonalInfoActivity;
import com.wenwu.pm.mine.activity.SettingActivity;
import com.wenwu.pm.mine.adapter.MyPagerAdapter;


import java.util.ArrayList;
import java.util.List;

public class MyFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private Button btn_edit;
    private Button btn_setting;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), initViewPager());
        viewPager = view.findViewById(R.id.view_pagerMy);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout = view.findViewById(R.id.tab_layoutMy);
        tabLayout.setupWithViewPager(viewPager);

        btn_edit = view.findViewById(R.id.edit_myInfo);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"编辑资料",Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(new Intent(v.getContext(), EditPersonalInfoActivity.class));

            }
        });

        btn_setting = view.findViewById(R.id.setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"编辑资料",Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(new Intent(v.getContext(), SettingActivity.class));

            }
        });


    }


    /**
     * 初始化ListFragment,将三个Fragment加入list
     * @return List<Fragment>集合
     */
    public List<Fragment> initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new MyLogFragment());
        fragmentList.add(new MyQuestionFragment());
        fragmentList.add(new MyCollectFragment());
        fragmentList.add(new MyReviewFragment());
        fragmentList.add(new MyPraiseFragment());
        return fragmentList;
    }
}
