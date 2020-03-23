package com.wenwu.pm.activity.find.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:10:08 PM 2/14/2020
 * 此类用于实现Find模块中tabLayout+viewpager+fragment功能
 */
public class FindPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;


    public FindPagerAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = list.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "每日贴士";
            case 1:
                return "宠友互助";
            case 2:
                return "热议话题";
        }
        return null;
    }
}
