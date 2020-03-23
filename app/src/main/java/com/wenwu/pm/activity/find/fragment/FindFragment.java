package com.wenwu.pm.activity.find.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.find.adapter.FindPagerAdapter;


import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FindPagerAdapter findPagerAdapter = new FindPagerAdapter(getChildFragmentManager(), initViewPager());
        viewPager = view.findViewById(R.id.view_pagerFind);
        viewPager.setAdapter(findPagerAdapter);

        tabLayout = view.findViewById(R.id.tab_layoutFind);

        tabLayout.setupWithViewPager(viewPager);


    }


    /**
     * 初始化ListFragment,将三个Fragment加入list
     * @return List<Fragment>集合
     */
    public List<Fragment> initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new FindDailyTipsFragment());
        fragmentList.add(new FindHelpPetFragment());
        fragmentList.add(new FindHotTopicFragment());
        return fragmentList;
    }


}
