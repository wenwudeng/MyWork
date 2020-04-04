package com.wenwu.pm.activity.mine.fragment;

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
import com.wenwu.pm.activity.home.bean.CardViewItemBean;
import com.wenwu.pm.activity.mine.adapter.LogRecyclerAdapter;
import com.wenwu.pm.goson.MyLogJson;
import com.wenwu.pm.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wenwudeng
 * @date:1:20 PM 3/12/2020
 */
public class MyLogFragment extends Fragment {

    private List<CardViewItemBean> cardViewItemBeanList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_log,container,false);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        System.out.println(JsonUtil.myLogJson);
        List<MyLogJson.Data> dataList = JsonUtil.myLogJson.getData();

        for (MyLogJson.Data data : dataList) {
            System.out.println(data);
            CardViewItemBean cardViewItemBean = new CardViewItemBean(data.getId(),data.getTitle(),
                    data.getImg(),data.getContent(),JsonUtil.loginJson.getData().getUserName(),JsonUtil.loginJson.getData().getPhoto(),data.getLike());
            cardViewItemBeanList.add(cardViewItemBean);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initData();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_my_log);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        LogRecyclerAdapter adapter = new LogRecyclerAdapter(cardViewItemBeanList,this);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_my_log);
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
