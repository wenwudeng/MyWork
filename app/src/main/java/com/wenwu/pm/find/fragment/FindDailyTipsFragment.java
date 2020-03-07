package com.wenwu.pm.find.fragment;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.wenwu.pm.R;
import com.wenwu.pm.find.adapter.FDailyRecyclerAdapter;
import com.wenwu.pm.find.bean.FindDailyTipsShow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wenwudeng
 * @date:10:18 PM 2/14/2020
 */
public class FindDailyTipsFragment extends Fragment {

    private List<FindDailyTipsShow> contentShowList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_dailytips,container,false);
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_dailyTips);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(layoutManager);

        FDailyRecyclerAdapter adapter = new FDailyRecyclerAdapter(contentShowList);

        recyclerView.setAdapter(adapter);





        //下拉刷新
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_dailyTip);
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
        for (int i = 0; i < 10; i++) {
            FindDailyTipsShow contentShow = new FindDailyTipsShow("狗狗零食到底怎么选？？", "1、肉干类特点：以鸡肉干为主,呵呵水水水水水水水水", R.drawable.img, 343);
            contentShowList.add(contentShow);
        }
    }
}
