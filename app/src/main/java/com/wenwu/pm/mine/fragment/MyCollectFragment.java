package com.wenwu.pm.mine.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wenwu.pm.R;
import com.wenwu.pm.mine.adapter.CollectRecyclerAdapter;
import com.wenwu.pm.mine.adapter.QuesRecyclerAdapter;
import com.wenwu.pm.mine.bean.CollectCardViewItem;
import com.wenwu.pm.mine.bean.QuestionCardViewItem;

import java.util.ArrayList;
import java.util.List;


public class MyCollectFragment extends Fragment {

    private List<CollectCardViewItem> collectsList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_collect,container,false);
    }

    /**
     * 初始化数据
     */
    public void init() {
        for (int i = 0; i < 3; i++) {
            CollectCardViewItem collectCardViewItem = new CollectCardViewItem("狗狗不吃东西怎么办？", R.mipmap.btn_comment, "丫头", "0", "0");
            collectsList.add(collectCardViewItem);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_my_collect);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        CollectRecyclerAdapter adapter = new CollectRecyclerAdapter(collectsList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_my_collect);
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
