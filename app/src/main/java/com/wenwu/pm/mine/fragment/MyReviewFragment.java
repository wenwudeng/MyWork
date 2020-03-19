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
import com.wenwu.pm.mine.adapter.ReviewRecyclerAdapter;
import com.wenwu.pm.mine.bean.ReviewCardViewItem;

import java.util.ArrayList;
import java.util.List;

public class MyReviewFragment extends Fragment {
    private List<ReviewCardViewItem> reviewList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_review,container,false);
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_my_review);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        ReviewRecyclerAdapter adapter = new ReviewRecyclerAdapter(reviewList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_my_review);
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
    /**
     * 初始化数据
     */
    public void init() {
        for (int i = 0; i < 3; i++) {
            ReviewCardViewItem item = new ReviewCardViewItem(R.drawable.chen,"陈瑶","1小时前","建议去看兽医","狗狗不吃饭怎么办？","0");
            reviewList.add(item);
        }

    }

}
