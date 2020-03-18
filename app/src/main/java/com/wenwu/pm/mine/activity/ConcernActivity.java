package com.wenwu.pm.mine.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.wenwu.pm.R;
import com.wenwu.pm.message.adapter.MsgANConcernRecyclerAdapter;
import com.wenwu.pm.message.bean.MsgAddNewConcern;
import com.wenwu.pm.mine.adapter.ConcernRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConcernActivity extends AppCompatActivity {
    private List<MsgAddNewConcern> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_concern);

        init();
        recyclerView = findViewById(R.id.recycler_view_my_concern);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        ConcernRecyclerAdapter adapter = new ConcernRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        refreshLayout = findViewById(R.id.swipe_refresh_my_concern);
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
        MsgAddNewConcern concern = new MsgAddNewConcern(R.drawable.img, "蒙眼丽莎");
        list.add(concern);

        MsgAddNewConcern concern1 = new MsgAddNewConcern(R.drawable.li, "李易峰");
        list.add(concern1);

        MsgAddNewConcern concern2 = new MsgAddNewConcern(R.drawable.chen, "陈瑶");
        list.add(concern2);
    }
}
