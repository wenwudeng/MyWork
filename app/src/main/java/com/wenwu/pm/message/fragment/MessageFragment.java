package com.wenwu.pm.message.fragment;

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

import com.wenwu.pm.R;
import com.wenwu.pm.message.adapter.MsgListItemAdapter;
import com.wenwu.pm.message.bean.MessageListItem;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<MessageListItem> listItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        RecyclerView recyclerView = view.findViewById(R.id.message_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        MsgListItemAdapter itemAdapter = new MsgListItemAdapter(listItems);
        recyclerView.setAdapter(itemAdapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_message);
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

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    private void init() {
        for (int i = 0; i < 2; i++) {
            MessageListItem listItem = new MessageListItem(R.drawable.ic_invite_more, "系统消息", "喜大普奔TT,您的用户等级从Lv1升级到Lv2!我的世界世界", "15分钟前");
            listItems.add(listItem);
        }
    }
}
