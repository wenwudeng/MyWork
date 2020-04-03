package com.wenwu.pm.activity.home.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 此适配器类用于实现home页面的viewpager+fragment+tabLayout功能
 */
public class HomePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list ;
    public HomePagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
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
                return "关注";
            case 1:
                return "推荐";
            case 2:
                return "动态";
        }
        return null;
    }

  /*  @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }*/
}
