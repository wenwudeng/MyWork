package com.wenwu.pm.activity.message.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.activity.MsgAddNewConcernActivity;
import com.wenwu.pm.activity.message.activity.MsgCollectPraiseActivity;
import com.wenwu.pm.activity.message.activity.MsgReViewActivity;
import com.wenwu.pm.activity.message.adapter.MsgListItemAdapter;
import com.wenwu.pm.activity.message.bean.MessageListItem;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<MessageListItem> listItems = new ArrayList<>();
    private Button btn_follow;
    private Button btn_like;
    private Button btn_review;

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

        //赞和收藏button
        btn_like = view.findViewById(R.id.btn_like);
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), MsgCollectPraiseActivity.class));
            }
        });

        //新增关注button
        btn_like = view.findViewById(R.id.btn_follow);
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), MsgAddNewConcernActivity.class));
            }
        });

        //评论button
        btn_review = view.findViewById(R.id.btn_comment);
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), MsgReViewActivity.class));
            }
        });



    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    public void init() {
            MessageListItem listItem = new MessageListItem(R.drawable.ic_invite_more, "系统消息", "喜大普奔TT,您的用户等级从Lv1升级到Lv2!我的世界世界", "15分钟前");
            listItems.add(listItem);
    }
}
