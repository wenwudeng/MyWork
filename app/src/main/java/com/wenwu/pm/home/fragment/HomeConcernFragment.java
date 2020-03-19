package com.wenwu.pm.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.wenwu.pm.R;
import com.wenwu.pm.home.adapter.HomeRecyclerAdapter;
import com.wenwu.pm.home.bean.CardViewItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页模块中的关注碎片页
 */
public class HomeConcernFragment extends Fragment {

    //用于用户头像
    private List<CardViewItemBean> cardViewItemBeanList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_concern,container,false);
    }

    /**
     * 初始化数据
     */
    public void init() {
        for (int i = 0; i < 8; i++) {
            CardViewItemBean cardViewItemBean = new CardViewItemBean("抗战疫情", R.drawable.li, "中国加油,武汉加油!", "梦想", R.mipmap.pic2,100);
            cardViewItemBeanList.add(cardViewItemBean);
            CardViewItemBean cardViewItemBean1 = new CardViewItemBean("抗战疫情", R.drawable.chen, "中国加油,武汉加油!", "梦想", R.mipmap.pic4,100);
            cardViewItemBeanList.add(cardViewItemBean1);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        HomeRecyclerAdapter userEditIRecyclerAdapter = new HomeRecyclerAdapter(cardViewItemBeanList);
        recyclerView.setAdapter(userEditIRecyclerAdapter);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_concern);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(),"刷新完成", Toast.LENGTH_SHORT).show();
                    }
                },200);
            }
        });
    }
}
