package com.wenwu.pm.activity.message.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.BaseActivity;
import com.wenwu.pm.activity.message.adapter.MsgCollectRecyclerAdapter;
import com.wenwu.pm.activity.message.bean.MsgAddNewConcern;
import com.wenwu.pm.activity.message.bean.MsgCollectPraise;
import com.wenwu.pm.goson.BeSupportInfo;
import com.wenwu.pm.utils.GsonUtil;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.utils.OkHttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MsgCollectPraiseActivity extends BaseActivity {
    private List<MsgCollectPraise> list ;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private  MsgCollectRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msg_collect_praise);
        initData();
        recyclerView = findViewById(R.id.recycler_view_msg_collect);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MsgCollectRecyclerAdapter(list,this);
        recyclerView.setAdapter(adapter);

        refreshLayout = findViewById(R.id.swipe_refresh_msg_collect);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getBaseContext(),"刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });
    }

    private void initData() {
            list= new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("oId", JsonUtil.loginJson.getData().getId());
            OkHttpUtil.sendPostRequest("cLike/getSupportInfo",map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {}
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String returnData = response.body().string();
                    BeSupportInfo json = new Gson().fromJson(returnData, BeSupportInfo.class);
                    List<BeSupportInfo.Data> dataList = json.getData();
                    for (BeSupportInfo.Data data : dataList) {
                        MsgCollectPraise collect = new MsgCollectPraise(data.getSid(), data.getSphoto(), data.getSname(), data.getAid(), data.getAuId(), data.getTitle(), data.getContent(), data.getImg(),"一周前");
                        list.add(collect);
                        Log.d("tag",""+collect);
                    }

                }
            });
        }





}
