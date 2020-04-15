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
import com.wenwu.pm.activity.mine.adapter.QuesRecyclerAdapter;
import com.wenwu.pm.activity.mine.bean.QuestionCardViewItem;
import com.wenwu.pm.goson.MyQuestionJson;
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
        while ( JsonUtil.myQuestionJson.getData()==null);
        List<MyQuestionJson.Data> dataList = JsonUtil.myQuestionJson.getData();
        for (MyQuestionJson.Data data : dataList) {
            QuestionCardViewItem item = new QuestionCardViewItem(data.getTitle(),data.getTime(),data.getAnswer(),
                    data.getId(),data.getUserid(),data.getContent(),data.getImg(),data.getLocation(),data.getLike()
            ,data.getCollect());
            cardViewItemBeanList.add(item);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getMyQuestionData();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_my_ques);
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

    /*获取我的主页提问页面数据*/
    public void getMyQuestionData() {
        Map<String, Object> param = new HashMap<>();
        param.put("userid", JsonUtil.loginJson.getData().getId());
        OkHttpUtil.sendPostRequest("question/getAll", param, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data1 = response.body().string();
                MyQuestionJson json = new Gson().fromJson(data1, MyQuestionJson.class);
                List<MyQuestionJson.Data> dataList = json.getData();
                for (MyQuestionJson.Data data : dataList) {
                    QuestionCardViewItem item = new QuestionCardViewItem(data.getTitle(),data.getTime(),data.getAnswer(),
                            data.getId(),data.getUserid(),data.getContent(),data.getImg(),data.getLocation(),data.getLike()
                            ,data.getCollect());
                    cardViewItemBeanList.add(item);
                }
            }
        });
    }
}


