package com.wenwu.pm.message.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.wenwu.pm.R;
import com.wenwu.pm.message.adapter.MsgCollectRecyclerAdapter;
import com.wenwu.pm.message.bean.MsgCollectPraise;

import java.util.ArrayList;
import java.util.List;

public class MsgCollectPraiseActivity extends AppCompatActivity {
    private List<MsgCollectPraise> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msg_collect_praise);
        init();
        recyclerView = findViewById(R.id.recycler_view_msg_collect);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        MsgCollectRecyclerAdapter adapter = new MsgCollectRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        refreshLayout = findViewById(R.id.swipe_refresh_msg_collect);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getBaseContext(),"刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 200);
            }
        });
    }

    private void init() {
        MsgCollectPraise collect = new MsgCollectPraise(R.drawable.li, "李易峰", "一周前", R.drawable.timg);
        list.add(collect);

        MsgCollectPraise collect1 = new MsgCollectPraise(R.drawable.chen, "陈瑶", "一周前", R.drawable.timg);
        list.add(collect1);


    }
}
