package com.wenwu.pm.activity.mine.fragment;


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

import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.mine.adapter.CollectRecyclerAdapter;
import com.wenwu.pm.activity.mine.bean.CollectCardViewItem;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.goson.UserCollections;
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


public class MyCollectFragment extends Fragment {

    private List<CollectCardViewItem> collectsList;

    private SwipeRefreshLayout swipeRefreshLayout;
    private CollectRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_collect,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initData();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_my_collect);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CollectRecyclerAdapter(collectsList,this);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_my_collect);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        initData();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(),"刷新完成", Toast.LENGTH_SHORT).show();
                    }
                },200);
            }
        });
    }

    /**
     * 初始化数据
     */
    public void initData() {
        collectsList = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("userId", JsonUtil.loginJson.getData().getId());
            OkHttpUtil.sendPostRequest("cLike/getCollection", map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {}

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String data1 = response.body().string();
                    UserCollections json = new Gson().fromJson(data1, UserCollections.class);
                    List<UserCollections.Data> dataList = json.getData();
                    for (UserCollections.Data data : dataList) {
                        CollectCardViewItem item = new CollectCardViewItem(data.getAuthorId(), data.getAuthorName(), data.getAuthorPhoto(), data.getaId()
                                , data.getTitle(), data.getContent(), data.getImg(), data.getLike(), data.getComment());
                        collectsList.add(item);
                    }

                }
            });
        }
}

