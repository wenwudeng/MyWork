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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.wenwu.pm.R;
import com.wenwu.pm.activity.home.adapter.ConcernRecyclerAdapter;
import com.wenwu.pm.activity.home.adapter.DynamicRecyclerAdapter;
import com.wenwu.pm.activity.home.bean.CardViewItemBean;

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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView =  view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
        ConcernRecyclerAdapter adapter = new ConcernRecyclerAdapter(cardViewItemBeanList, this);
        recyclerView.setAdapter(adapter);
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
