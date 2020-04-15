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
import com.wenwu.pm.activity.find.adapter.FHelpRecyclerAdapter;
import com.wenwu.pm.activity.find.bean.FindHelpPetShow;
import com.wenwu.pm.goson.FindHelpJson;
import com.wenwu.pm.goson.MyQuestionJson;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author:wenwudeng
 * @date:10:20 PM 2/14/2020
 */
public class FindHelpPetFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<FindHelpPetShow> list = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_helppet,container,false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getFindHelpData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       // init();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_helPet);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        FHelpRecyclerAdapter helpRecyclerAdapter = new FHelpRecyclerAdapter(list,this);
        recyclerView.setAdapter(helpRecyclerAdapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_helpPet);
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

    /*获取find模块宠物互助数据*/
    public void getFindHelpData() {
        OkHttpUtil.sendPostRequest("question/getQuestions", null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String reData = response.body().string();
                FindHelpJson json = new Gson().fromJson(reData, FindHelpJson.class);
                List<FindHelpJson.Data> dataList = json.getData();
                for (FindHelpJson.Data data : dataList) {
                    FindHelpPetShow item = new FindHelpPetShow(data.getqId(), data.getuId(), data.getTitle(), data.getContent(),
                            data.getImg(), data.getLocation(), data.getLike(), data.getAnswer(), data.getTime(), data.getPhoto(), data.getUserNam());
                    list.add(item);
                }
            }
        });
    }
}
