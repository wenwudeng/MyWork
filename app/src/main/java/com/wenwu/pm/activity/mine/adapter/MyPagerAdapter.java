package com.wenwu.pm.activity.mine.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:15:21 2020/2/21
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public MyPagerAdapter(@NonNull FragmentManager fm,  List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "日志";
            case 1:
                return "提问";
            case 2:
                return "评论";
            case 3:
                return "收藏";
        }
        return null;
    }
}
