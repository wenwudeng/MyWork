package com.wenwu.pm.activity.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.bean.MsgAddNewConcern;
import com.wenwu.pm.activity.mine.adapter.ConcernRecyclerAdapter;
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

public class FollowActivity extends AppCompatActivity{
    private List<MsgAddNewConcern> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private Toolbar toolbar;
    private Button unFollow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_concern);
        initView();
    }

    public void initView() {
        initData();
        toolbar = findViewById(R.id.follow_toolbar);
        toolbar.setTitle("我关注的");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        recyclerView = findViewById(R.id.recycler_view_my_concern);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

     //   while (list==null);
        ConcernRecyclerAdapter adapter = new ConcernRecyclerAdapter(list,this);
        recyclerView.setAdapter(adapter);

        refreshLayout = findViewById(R.id.swipe_refresh_my_concern);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.red));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                     //   initData();
                        Toast.makeText(getBaseContext(),"刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });
    }

    private void initData() {
        OkHttpUtil.sendPostRequest("followAndFans/getAllFollowsInfo",null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String returnData = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(returnData);
                    JSONArray array =  jsonObject.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject  arrayObject = array.getJSONObject(i);
                        MsgAddNewConcern concern = new MsgAddNewConcern(arrayObject.getString("photo"),
                                arrayObject.getString("userName"),arrayObject.getBoolean("status"));
                        list.add(concern);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
