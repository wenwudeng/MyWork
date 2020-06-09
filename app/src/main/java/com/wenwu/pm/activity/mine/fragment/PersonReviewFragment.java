package com.wenwu.pm.activity.mine.fragment;


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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.mine.adapter.PersonReviewAdapter;
import com.wenwu.pm.activity.mine.adapter.ReviewRecyclerAdapter;
import com.wenwu.pm.activity.mine.bean.ReviewCardViewItem;
import com.wenwu.pm.goson.MyCommentJson;
import com.wenwu.pm.goson.OneArticleJson;
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

public class PersonReviewFragment extends Fragment {
    private static List<ReviewCardViewItem> reviewList;
    private PersonReviewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static int id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.my_review, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_my_review);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PersonReviewAdapter(reviewList, this);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FF5a60"));        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_my_review);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getMyCommentData(id);
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });
    }


    /*获取我的主页评论数据*/
    public static void getMyCommentData(int userId) {
        id = userId;
        reviewList = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("userid", userId);
        OkHttpUtil.sendPostRequest("comment/getCommentsArt", param, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data1 = response.body().string();
                MyCommentJson json = new Gson().fromJson(data1, MyCommentJson.class);
                List<MyCommentJson.Data> dataList = json.getData();
                for (MyCommentJson.Data data : dataList) {
                    ReviewCardViewItem item = new ReviewCardViewItem(data.getAuthorName(),data.getAuthorPhoto(),data.getUserid(),JsonUtil.loginJson.getData().getPhoto(),JsonUtil.loginJson.getData().getUserName(),data.getCTime(),
                            data.getCcontent(),data.getTitle(),data.getAid(),data.getClike(),data.getAlike(),data.getContent(),data.getLocation(),data.getImg());
                    reviewList.add(item);
                }

            }
        });

    }
}
