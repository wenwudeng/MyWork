package com.wenwu.pm.message.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wenwu.pm.R;
import com.wenwu.pm.message.adapter.MsgANConcernRecyclerAdapter;
import com.wenwu.pm.message.adapter.MsgCollectRecyclerAdapter;
import com.wenwu.pm.message.bean.MsgAddNewConcern;

import java.util.ArrayList;
import java.util.List;

public class MsgAddNewConcernActivity extends AppCompatActivity {
    private List<MsgAddNewConcern> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private Button btn_follow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.msg_add_new_concern);

        init();
        recyclerView = findViewById(R.id.recycler_view_msg_concern);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        MsgANConcernRecyclerAdapter adapter = new MsgANConcernRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        refreshLayout = findViewById(R.id.swipe_refresh_msg_concern);
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

       /* btn_follow = findViewById(R.id.msg_concern_follow);

        btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_follow.setText("相互关注");
            }
        });*/

    }

    private void init() {
        for (int i = 0; i < 14; i++) {
            MsgAddNewConcern concern = new MsgAddNewConcern(R.drawable.img, "令狐冲", "3天前");
            list.add(concern);
        }
    }
}
