package com.wenwu.pm.home.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.utils.NavigationUtil;
import com.next.easynavigation.view.EasyNavigationBar;
import com.wenwu.pm.R;
import com.wenwu.pm.find.fragment.FindFragment;
import com.wenwu.pm.home.fragment.HomeFragment;
import com.wenwu.pm.message.fragment.MessageFragment;
import com.wenwu.pm.mine.fragment.MyInfoFragment;
import com.wenwu.pm.widget.KickBackAnimator;


import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity {

    private EasyNavigationBar navigationBar;

    private String[] tabText = {"首页", "发现", "", "消息", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.tab_home_normal, R.mipmap.tab_pic_normal, R.mipmap.tab_add, R.mipmap.tab_shop_normal, R.mipmap.tab_mine_normal};
    //选中时icon
    private int[] selectIcon = {R.mipmap.tab_home_pressed, R.mipmap.tab_pic_pressed, R.mipmap.tab_add, R.mipmap.tab_shop_pressed, R.mipmap.tab_mine_pressed};
    private List<Fragment> fragments = new ArrayList<>();

    //图片和文字集合
    private int[] menuIconItems = {R.mipmap.publish_post, R.mipmap.post_content, R.mipmap.publish_ask};
    private String[] menuTextItems = {"发布日志", "发图长文", "提问求助"};

    private LinearLayout menuLayout;
    private View cancelImageView;
    private Handler mHandler = new Handler();






    protected EasyNavigationBar initNavigationBar() {
        navigationBar = findViewById(R.id.navigationBar);

        fragments.add(new HomeFragment());
        fragments.add(new FindFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MyInfoFragment());

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
                            /*Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                            startActivity(intent);*/
                            Toast.makeText(NavigationActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            //return true则拦截事件、不进行页面切换
                            return false;
                        } else if (position == 2) {
                            //跳转页面
                            showMunu();
                        }
                        return false;
                    }
                })
                .mode(EasyNavigationBar.MODE_ADD)
                .anim(Anim.ZoomIn)
                .build();
        return navigationBar;
    }

    //弹出菜单
    protected View createNaviBarView() {
        ViewGroup view = (ViewGroup) View.inflate(NavigationActivity.this, R.layout.layout_add_view, null);
        menuLayout = view.findViewById(R.id.icon_group);
        cancelImageView = view.findViewById(R.id.cancel_iv);
        cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAnimation();
            }
        });

        for (int i = 0; i < 3; i++) {
            View itemView = View.inflate(NavigationActivity.this, R.layout.add_item_icon, null);
            ImageView menuImage = itemView.findViewById(R.id.menu_icon_iv);
            TextView menuText = itemView.findViewById(R.id.menu_text_tv);

            menuImage.setImageResource(menuIconItems[i]);
            menuText.setText(menuTextItems[i]);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            itemView.setLayoutParams(params);
            itemView.setVisibility(View.GONE);
            menuLayout.addView(itemView);
        }
        return view;
    }

    //
    private void showMunu() {
        startAnimation();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //＋ 旋转动画
                cancelImageView.animate().rotation(90).setDuration(400);
            }
        });
        //菜单项弹出动画
        for (int i = 0; i < menuLayout.getChildCount(); i++) {
            final View child = menuLayout.getChildAt(i);
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(500);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(500);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 50 + 100);
        }
    }


    private void startAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //圆形扩展的动画
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        int x = NavigationUtil.getScreenWidth(NavigationActivity.this) / 2;
                        int y = (int) (NavigationUtil.getScreenHeith(NavigationActivity.this) - NavigationUtil.dip2px(NavigationActivity.this, 25));
                        Animator animator = ViewAnimationUtils.createCircularReveal(navigationBar.getAddViewLayout(), x,
                                y, 0, navigationBar.getAddViewLayout().getHeight());
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                navigationBar.getAddViewLayout().setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                				//			layout.setVisibility(View.VISIBLE);
                            }
                        });
                        animator.setDuration(300);
                        animator.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 关闭window动画
     */
    private void closeAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                cancelImageView.animate().rotation(0).setDuration(400);
            }
        });

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                int x = NavigationUtil.getScreenWidth(this) / 2;
                int y = (NavigationUtil.getScreenHeith(this) - NavigationUtil.dip2px(this, 25));
                Animator animator = ViewAnimationUtils.createCircularReveal(navigationBar.getAddViewLayout(), x,
                        y, navigationBar.getAddViewLayout().getHeight(), 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //							layout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        navigationBar.getAddViewLayout().setVisibility(View.GONE);
                        //dismiss();
                    }
                });
                animator.setDuration(300);
                animator.start();
            }
        } catch (Exception e) {
        }
    }


    public EasyNavigationBar getNavigationBar() {
        return navigationBar;
    }
}
