package com.wenwu.pm.activity.home.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
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


import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.home.adapter.DynamicRecyclerAdapter;
import com.wenwu.pm.activity.home.bean.CardViewItemBean;
import com.wenwu.pm.goson.ShowArticlesJson;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class HomeDynamicFragment extends Fragment {
    private List<CardViewItemBean> cardViewItemBeanList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private  DynamicRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        init();
        return inflater.inflate(R.layout.home_dynamic,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view2);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DynamicRecyclerAdapter(cardViewItemBeanList,this);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_dynamic);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FF5a60"));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        init();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(),"刷新完成", Toast.LENGTH_SHORT).show();
                    }
                },200);
            }
        });
    }

    /*加载所有文章数据*/
    public void init() {
        cardViewItemBeanList = new ArrayList<>();
        OkHttpUtil.sendPostRequest("article/getArticles", null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                ShowArticlesJson showArticlesJson = new Gson().fromJson(responseData, ShowArticlesJson.class);
                List<ShowArticlesJson.Data> dataList = showArticlesJson.getData();
                for (ShowArticlesJson.Data data : dataList) {
                    CardViewItemBean cardViewItemBean = new CardViewItemBean(data.getUserId(),data.getArticleId(),data.getTitle(),
                            data.getImg(),data.getContent(),data.getUserName(),data.getUserPhoto(),data.getLike());

                    Log.d("MainActivity", data.getUserName()+data.getUserPhoto()+" ");
                    cardViewItemBeanList.add(cardViewItemBean);
                }

            }
        });
    }

}
