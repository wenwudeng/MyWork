package com.wenwu.pm.activity.find.fragment;

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
import com.wenwu.pm.activity.find.adapter.FHotRecyclerAdapter;
import com.wenwu.pm.activity.find.bean.FindHotTopicShow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wenwudeng
 * @date:10:20 PM 2/14/2020
 */
public class FindHotTopicFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<FindHotTopicShow> topicShowsList = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_hottopic,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_hotTopic);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        FHotRecyclerAdapter hotRecyclerAdapter = new FHotRecyclerAdapter(topicShowsList);
        recyclerView.setAdapter(hotRecyclerAdapter);



        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_hotTopic);
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

    private void init() {
        for (int i = 0; i < 4; i++) {
            FindHotTopicShow topicShow = new FindHotTopicShow(R.drawable.img, "2345", "#你的城市,如何办理狗证?#", "分享你办理狗证的经历吧");
            topicShowsList.add(topicShow);

            FindHotTopicShow topicShow1 = new FindHotTopicShow(R.drawable.timg, "2345", "#宠物表情包大赛#", "把你家宠物的表情包丢过来吧~");
            topicShowsList.add(topicShow1);
        }

    }


}
