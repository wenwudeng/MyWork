package com.wenwu.pm.mine.fragment;

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

import com.google.android.material.tabs.TabLayout;
import com.wenwu.pm.R;
import com.wenwu.pm.mine.adapter.MyPagerAdapter;


import java.util.ArrayList;
import java.util.List;

public class MyFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;


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

        Button button = view.findViewById(R.id.edit_myInfo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"编辑资料",Toast.LENGTH_SHORT).show();
              /*  replaceFragment(new MyPInfoEditFragment());*/
            }
        });
    }


    /*public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.my_fragment_container, fragment);
        transaction.commit();
    }*/


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
