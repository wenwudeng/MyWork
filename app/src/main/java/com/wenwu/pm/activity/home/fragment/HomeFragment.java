package com.wenwu.pm.activity.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.home.adapter.HomePagerAdapter;
import com.wenwu.pm.goson.FindHelpJson;
import com.wenwu.pm.goson.MyCommentJson;
import com.wenwu.pm.goson.MyLogJson;
import com.wenwu.pm.goson.MyQuestionJson;
import com.wenwu.pm.goson.OneArticleJson;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 预加载数据
 */
public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;
    private HomePagerAdapter myViewPagerAdapter;

    private MyCommentJson json;


    /**
     * 返回试图对象
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }


    /**
     * 初始化
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        myViewPagerAdapter = new HomePagerAdapter(getChildFragmentManager(),initViewPager());
        viewPager = view.findViewById(R.id.view_pager);
//        viewPager添加适配器
        viewPager.setAdapter(myViewPagerAdapter);

        tabLayout = view.findViewById(R.id.tab_layout);
//        tabLayout绑定ViewPager
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);


    }


    /**
     * 初始化ListFragment,将三个Fragment加入list
     * @return List<Fragment>集合
     */
    public List<Fragment> initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeConcernFragment());
        fragmentList.add(new HomeRecommendFragment());
        fragmentList.add(new HomeDynamicFragment());
        return fragmentList;
    }



}
