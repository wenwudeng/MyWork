package com.wenwu.pm.activity.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wenwu.pm.R;
import com.wenwu.pm.goson.ShowReturnJson;
import com.wenwu.pm.activity.message.activity.MsgCollectPraiseActivity;
import com.wenwu.pm.activity.mine.activity.ConcernActivity;
import com.wenwu.pm.activity.mine.activity.EditPersonalInfoActivity;
import com.wenwu.pm.activity.mine.activity.FansActivity;
import com.wenwu.pm.activity.mine.activity.SettingActivity;
import com.wenwu.pm.activity.mine.adapter.MyPagerAdapter;
import com.wenwu.pm.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment implements View.OnClickListener{

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;

    private Button btn_setting;

    private TextView tex_concern;
    private TextView tex_fans;
    private TextView tex_collect;



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

        tex_concern = view.findViewById(R.id.my_concern);
        tex_concern.setOnClickListener(this);

        tex_fans = view.findViewById(R.id.my_fans);
        tex_fans.setOnClickListener(this);

        tex_collect = view.findViewById(R.id.my_collect);
        tex_collect.setOnClickListener(this);

        btn_setting = view.findViewById(R.id.setting);
        btn_setting.setOnClickListener(this);

        CircleImageView imageView = view.findViewById(R.id.my_user_photo);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting:
                startActivity(new Intent(v.getContext(), SettingActivity.class));
                break;

            case R.id.my_user_photo:

                EditPersonalInfoActivity.openEdit(v.getContext(),this);
                break;

            case R.id.my_concern:
                Toast.makeText(v.getContext(),"关注",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(), ConcernActivity.class));
                break;

            case R.id.my_fans:
                Toast.makeText(v.getContext(),"粉丝",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(), FansActivity.class));
                break;

            case R.id.my_collect:
                Toast.makeText(v.getContext(),"收藏",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(), MsgCollectPraiseActivity.class));
                break;
        }
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
        return fragmentList;
    }

    public void updateInfo(String userPhoto,String userName,String gender,String city,String profile,String pet) {
        TextView textView_city = getView().findViewById(R.id.my_city);
        textView_city.setText(city);//可以了6啊是挺多的，你这个还有一个问题，刚切换的时候，数据加载慢，可以单独开一个线程，慢慢查，登录之后，或者，我先看看
        JsonUtil.showJson.getData().setCity(city);
        JsonUtil.showJson.getData().setUserName(userName);
        JsonUtil.showJson.getData().setProfile(profile);
        JsonUtil.showJson.getData().setPhoto(userPhoto);
    }
}
