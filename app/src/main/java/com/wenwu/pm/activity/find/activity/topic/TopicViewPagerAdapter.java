package com.wenwu.pm.activity.find.activity.topic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:8:58 2020/4/8
 */
public class TopicViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list ;
    public TopicViewPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        list = fragmentList;
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
                return "最热";
            case 1:
                return "最新";
        }
        return null;
    }
}
