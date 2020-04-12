package com.wenwu.pm.activity.find.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.find.activity.EatActivity;
import com.wenwu.pm.activity.find.activity.hospital.HospitalActivity;
import com.wenwu.pm.activity.find.activity.wiki.PetWikiActivity;
import com.wenwu.pm.activity.find.adapter.FindPagerAdapter;


import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment implements View.OnClickListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;

    private Button eat;
    private Button wiki;
    private Button hospital;

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
        viewPager.setOffscreenPageLimit(2);
        tabLayout = view.findViewById(R.id.tab_layoutFind);
        tabLayout.setupWithViewPager(viewPager);

        eat = view.findViewById(R.id.isEnableEat);
        eat.setOnClickListener(this);

        wiki = view.findViewById(R.id.wiki);
        wiki.setOnClickListener(this);

        hospital = view.findViewById(R.id.hospital);
        hospital.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.isEnableEat:
                startActivity(new Intent(v.getContext(), EatActivity.class));
                break;
            case R.id.wiki:
                startActivity(new Intent(v.getContext(), PetWikiActivity.class));
                break;
            case R.id.hospital:
                startActivity(new Intent(v.getContext(), HospitalActivity.class));
                break;
        }
    }
}
