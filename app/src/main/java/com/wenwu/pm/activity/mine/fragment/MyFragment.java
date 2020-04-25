package com.wenwu.pm.activity.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.activity.MsgCollectPraiseActivity;
import com.wenwu.pm.activity.mine.activity.FollowActivity;
import com.wenwu.pm.activity.mine.activity.EditPersonalInfoActivity;
import com.wenwu.pm.activity.mine.activity.FansActivity;
import com.wenwu.pm.activity.mine.activity.SettingActivity;
import com.wenwu.pm.activity.mine.adapter.MyPagerAdapter;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.goson.LoginReturnJson;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyFragment extends Fragment implements View.OnClickListener{

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList;

    private Button btn_setting;

    private TextView tex_concern;
    private TextView tex_fans;
    private TextView tex_collect;

    private TextView userName;
    private CircleImageView userPhoto;
    private TextView concernCount;
    private TextView fansCount;
    private TextView collectCount;
    private ImageView gender;
    private TextView city;
    private TextView pet;
    private TextView profile;

    private String follow;


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
        viewPager.setOffscreenPageLimit(3);
        initView(view);
    }

    private void initView(View view) {
        userName = view.findViewById(R.id.my_user_name);
        userPhoto = view.findViewById(R.id.my_user_photo);
        concernCount = view.findViewById(R.id.my_concern_count);
        collectCount = view.findViewById(R.id.my_collect_count);
        fansCount = view.findViewById(R.id.my_fans_count);
        gender = view.findViewById(R.id.my_sex);
        city = view.findViewById(R.id.my_city);
        pet = view.findViewById(R.id.my_pet);
        profile = view.findViewById(R.id.my_profile);

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

        getFollow();
        showResponse(JsonUtil.loginJson.getData());

    }


    /**
     * 初始化ListFragment,将三个Fragment加入list
     * @return List<Fragment>集合
     */

    public List<Fragment> initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new MyLogFragment());
        fragmentList.add(new MyQuestionFragment());
        fragmentList.add(new MyReviewFragment());
        fragmentList.add(new MyCollectFragment());
        return fragmentList;
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
                startActivity(new Intent(v.getContext(), FollowActivity.class));
                break;

            case R.id.my_fans:
                Toast.makeText(v.getContext(),"粉丝",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(), FansActivity.class));
                break;

            case R.id.my_collect:
                Toast.makeText(v.getContext(),"获赞",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(v.getContext(), MsgCollectPraiseActivity.class));
                break;
        }
    }

    public void updateInfo(String userPhoto,String userName,String gender,String city,String profile,String pet) {
        JsonUtil.loginJson.getData().setPhoto(userPhoto);
        JsonUtil.loginJson.getData().setCity(city);
        JsonUtil.loginJson.getData().setUserName(userName);
        JsonUtil.loginJson.getData().setProfile(profile);
        JsonUtil.loginJson.getData().setPet(pet);
        JsonUtil.loginJson.getData().setGender(gender);
        showResponse(JsonUtil.loginJson.getData());
    }

    //线程更新
    public void showResponse(final LoginReturnJson.Data json) {
        getActivity().runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.with(getActivity()).load(json.getPhoto()).into(userPhoto);
                userName.setText(json.getUserName());
                getFollow();
                getFans();
                getSupport();
                collectCount.setText(Integer.toString(json.getCollect()));
                if (json.getGender().equals("男")) {
                    gender.setImageResource(R.drawable.sex_boy_p);
                }else {
                    gender.setImageResource(R.drawable.sex_girl_p);
                }
                city.setText(json.getCity());
                pet.setText(json.getPet());
                profile.setText(json.getProfile());
            }
        }));
    }

    /*关注*/
    public void getFollow() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", JsonUtil.loginJson.getData().getId());
        OkHttpUtil.sendPostRequest("followAndFans/getFollows", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                LRReturnJson json = new Gson().fromJson(data, LRReturnJson.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        concernCount.setText(Integer.toString(json.getData()));
                    }
                });

            }
        });
    }


    /*fans*/
    public void getFans() {
        Map<String, Object> map = new HashMap<>();
        map.put("fId", JsonUtil.loginJson.getData().getId());
        OkHttpUtil.sendPostRequest("followAndFans/getFans", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                LRReturnJson json = new Gson().fromJson(data, LRReturnJson.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fansCount.setText(Integer.toString(json.getData()));
                    }
                });

            }
        });
    }


    /*fans*/
    public void getSupport() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", JsonUtil.loginJson.getData().getId());
        OkHttpUtil.sendPostRequest("cLike/getSupport", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                LRReturnJson json = new Gson().fromJson(data, LRReturnJson.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        collectCount.setText(Integer.toString(json.getData()));
                    }
                });

            }
        });
    }
}
