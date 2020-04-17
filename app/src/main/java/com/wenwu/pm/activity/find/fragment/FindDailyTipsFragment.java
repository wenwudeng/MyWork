package com.wenwu.pm.activity.find.fragment;

import android.content.Context;
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


import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.find.adapter.FDailyRecyclerAdapter;
import com.wenwu.pm.activity.find.bean.FindDailyTipsShow;
import com.wenwu.pm.goson.ShowArticlesJson;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author:wenwudeng
 * @date:10:18 PM 2/14/2020
 */
public class FindDailyTipsFragment extends Fragment {

    private List<FindDailyTipsShow> contentShowList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FDailyRecyclerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_dailytips,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initData();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_dailyTips);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FDailyRecyclerAdapter(contentShowList,this);
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
     * 获取媒体贴士数据
     */
    public void initData() {
        contentShowList = new ArrayList<>();
        OkHttpUtil.sendPostRequest("article/getDailyTips",null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data1 = response.body().string();
                ShowArticlesJson json = new Gson().fromJson(data1, ShowArticlesJson.class);
                List<ShowArticlesJson.Data> dataList = json.getData();
                for (ShowArticlesJson.Data data : dataList) {
                    FindDailyTipsShow contentShow = new FindDailyTipsShow(data.getArticleId(),data.getUserId(),data.getUserName(),data.getUserPhoto(),data.getTitle()
                    ,data.getContent(),data.getImg(),data.getLike());
                    contentShowList.add(contentShow);
                }
            }
        });

    }
}
