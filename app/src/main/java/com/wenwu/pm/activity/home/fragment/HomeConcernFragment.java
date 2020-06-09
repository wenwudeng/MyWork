package com.wenwu.pm.activity.home.fragment;

import android.graphics.Color;
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


import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.home.adapter.ConcernRecyclerAdapter;
import com.wenwu.pm.activity.home.adapter.DynamicRecyclerAdapter;
import com.wenwu.pm.activity.home.bean.CardViewItemBean;
import com.wenwu.pm.goson.LoginReturnJson;
import com.wenwu.pm.goson.ShowArticlesJson;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 首页模块中的关注碎片页
 */
public class HomeConcernFragment extends Fragment {
    //用于用户头像
    private List<CardViewItemBean> cardViewItemBeanList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ConcernRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_concern,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initData();
        RecyclerView recyclerView =  view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
       // while (cardViewItemBeanList==null);
        adapter = new ConcernRecyclerAdapter(cardViewItemBeanList, this);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_concern);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FF5a60"));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(),"刷新完成", Toast.LENGTH_SHORT).show();
                    }
                },200);
            }
        });
    }

    public void initData() {
        cardViewItemBeanList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", JsonUtil.loginJson.getData().getId());
        OkHttpUtil.sendPostRequest("article/getFollowedArticle", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data1 = response.body().string();
                ShowArticlesJson showArticlesJson = new Gson().fromJson(data1, ShowArticlesJson.class);
                List<ShowArticlesJson.Data> dataList = showArticlesJson.getData();
                for (ShowArticlesJson.Data data : dataList) {
                    CardViewItemBean cardViewItemBean = new CardViewItemBean(data.getUserId(), data.getArticleId(), data.getTitle(),
                            data.getImg(), data.getContent(), data.getUserName(), data.getUserPhoto(), data.getLike());
                    cardViewItemBeanList.add(cardViewItemBean);
                }
            }
        });
        }
}
