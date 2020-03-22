package com.wenwu.pm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.view.EasyNavigationBar;
import com.wenwu.pm.find.fragment.FindFragment;
import com.wenwu.pm.goson.ShowReturnJson;
import com.wenwu.pm.home.fragment.HomeFragment;
import com.wenwu.pm.message.fragment.MessageFragment;
import com.wenwu.pm.mine.fragment.MyFragment;
import com.wenwu.pm.publish.activity.LongArticleActivity;
import com.wenwu.pm.publish.activity.QuestionActivity;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.utils.OkHttpUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;
import lrq.com.addpopmenu.PopMenu;
import lrq.com.addpopmenu.PopMenuItem;
import lrq.com.addpopmenu.PopMenuItemListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EasyNavigationBar navigationBar;
    private ShowReturnJson json;
    
    private View view;

    private String[] tabText = {"首页", "发现", "", "消息", "我的"};
    private int[] normalIcon = {R.mipmap.tab_home_normal, R.mipmap.tab_pic_normal, R.mipmap.btn_pai, R.mipmap.tab_shop_normal, R.mipmap.tab_mine_normal};
    private int[] selectIcon = {R.mipmap.tab_home_pressed, R.mipmap.tab_pic_pressed, R.mipmap.btn_pai, R.mipmap.tab_shop_pressed, R.mipmap.tab_mine_pressed};
    private List<Fragment> fragments = new ArrayList<>();

    private TextView userName;
    private CircleImageView userPhoto;
    private TextView concernCount;
    private TextView fansCount;
    private TextView collectCount;
    private ImageView gender;
    private TextView city;
    private TextView pet;
    private TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationBar = findViewById(R.id.navigationBar);

        fragments.add(new HomeFragment());
        fragments.add(new FindFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MyFragment());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .addLayoutRule(EasyNavigationBar.RULE_BOTTOM)
                .addLayoutBottom(100)
                .onTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        if (position == 4) {
                            userName = findViewById(R.id.my_user_name);
                            userPhoto = findViewById(R.id.my_user_photo);
                            concernCount = findViewById(R.id.my_concern_count);
                            collectCount = findViewById(R.id.my_collect_count);
                            fansCount = findViewById(R.id.my_fans_count);
                            gender = findViewById(R.id.my_sex);
                            city = findViewById(R.id.my_city);
                            pet = findViewById(R.id.my_pet);
                            profile = findViewById(R.id.my_profile);
                            //显示个人信息
                            Intent intent = getIntent();
                           // showInfoWithOkHttp("showInfo", intent.getStringExtra("account"));

                        } else if (position == 2) {
                            PopMenu mPopMenu = new PopMenu.Builder().attachToActivity(MainActivity.this)
                                    .addMenuItem(new PopMenuItem("发布日志", getResources().getDrawable(R.mipmap.publish_post)))
                                    .addMenuItem(new PopMenuItem("发长图文", getResources().getDrawable(R.mipmap.post_content)))
                                    .addMenuItem(new PopMenuItem("提出问题", getResources().getDrawable(R.mipmap.publish_ask)))

                                    .setOnItemClickListener(new PopMenuItemListener() {
                                        @Override
                                        public void onItemClick(PopMenu popMenu, int position) {
                                            if (position == 0) {
                                                Toast.makeText(MainActivity.this, "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                                            } else if (position == 1) {
                                                Toast.makeText(MainActivity.this, "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MainActivity.this, LongArticleActivity.class));
                                            } else {
                                                Toast.makeText(MainActivity.this, "你点击了第" + position + "个位置", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MainActivity.this, QuestionActivity.class));
                                            }
                                        }
                                    })
                                    .build();

                            mPopMenu.setmMarginTopRemainSpace(1.2f);
                            mPopMenu.setmIsmalpositionAnimatOut(true);
                            mPopMenu.setmBackGroundColor(Color.parseColor("#ffffff"));
                            mPopMenu.show();

                        }
                        return false;
                    }
                })
                .mode(EasyNavigationBar.MODE_ADD)
                .anim(Anim.ZoomIn)
                .build();
    }


    /**
     * 根据account显示个人信息
     *
     * @param mapping 访问地址
     * @param account 账号
     */
    private void showInfoWithOkHttp(String mapping, final String account) {
        OkHttpUtil.showInfoWithOkHttp(mapping, account, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                json = new Gson().fromJson(responseData, ShowReturnJson.class);
                JsonUtil.showJson = json;
                //子线程更新
                showResponse(json);

            }
        });
    }

    //线程更新
    public void showResponse(final ShowReturnJson json) {
        runOnUiThread(new Thread(new Runnable() {
            @Override
            public void run() {
                userName.setText(json.getData().getUserName());
                fansCount.setText(Integer.toString(json.getData().getFollow()));
                concernCount.setText(Integer.toString(json.getData().getFollow()));
                collectCount.setText(Integer.toString(json.getData().getCollectLike()));
                if (json.getData().getGender().equals("男")) {
                    gender.setImageResource(R.drawable.sex_boy_p);
                }
                city.setText(json.getData().getCity());
                pet.setText(json.getData().getPet());
                profile.setText(json.getData().getProfile());
            }
        }));
    }
}



