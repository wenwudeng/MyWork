package com.wenwu.pm.activity.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.wenwu.pm.R;
import com.wenwu.pm.activity.home.adapter.DynamicRecyclerAdapter;
import com.wenwu.pm.activity.home.adapter.RecommendRecyclerAdapter;
import com.wenwu.pm.activity.home.bean.CardViewItemBean;

import java.util.ArrayList;
import java.util.List;

public class HomeRecommendFragment extends Fragment {
    //用于用户头像
    private List<CardViewItemBean> cardViewItemBeanList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_recommend,container,false);
    }

    public void init() {
        for (int i = 0; i < 1; i++) {
/*            CardViewItemBean cardViewItemBean = new CardViewItemBean("写得很详细，受教了", R.drawable.dog1, "写得很详细，受教了。", "梦想", R.mipmap.pic4,1);
            cardViewItemBeanList.add(cardViewItemBean);
            CardViewItemBean cardViewItemBean1 = new CardViewItemBean("抗战疫情",R.drawable.dog , "中国加油,武汉加油!", "Jack", R.mipmap.pic2,12);
            cardViewItemBeanList.add(cardViewItemBean1);*/
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view1);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecommendRecyclerAdapter adapter = new RecommendRecyclerAdapter(cardViewItemBeanList, this);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_recommend);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });
    }
}
