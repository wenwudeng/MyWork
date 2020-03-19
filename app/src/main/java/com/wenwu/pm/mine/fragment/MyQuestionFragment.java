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
import com.wenwu.pm.mine.adapter.QuesRecyclerAdapter;
import com.wenwu.pm.mine.bean.QuestionCardViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wenwudeng
 * @date:9:41 AM 3/19/2020
 */
public class MyQuestionFragment extends Fragment {

    private List<QuestionCardViewItem> cardViewItemBeanList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_question,container,false);
    }

    /**
     * 初始化数据
     */
    public void init() {
        for (int i = 0; i < 3; i++) {
            QuestionCardViewItem cardViewItemBean = new QuestionCardViewItem("养猫好还是养狗好？", "1小时前", "12");
            cardViewItemBeanList.add(cardViewItemBean);
            QuestionCardViewItem cardViewItemBean1 = new QuestionCardViewItem("狗狗不吃东西怎么办？", "1小时前", "0");
            cardViewItemBeanList.add(cardViewItemBean1);
        }

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_my_ques);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        QuesRecyclerAdapter adapter = new QuesRecyclerAdapter(cardViewItemBeanList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_my_ques);
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


