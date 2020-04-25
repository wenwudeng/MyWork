package com.wenwu.pm.activity.message.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cxd.chatview.moudle.ChatView;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.activity.MsgActivity;
import com.wenwu.pm.activity.message.bean.Msg;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:wenwudeng
 * @date:15:42 2020/2/18
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;
    private MsgActivity activity;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ChatView lefLayout;

        ChatView rightLayout;

        TextView leftMsg;

        TextView rightMsg;

        CircleImageView leftPhoto;

        CircleImageView rightPhoto;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lefLayout = itemView.findViewById(R.id.left_layout);
            rightLayout = itemView.findViewById(R.id.right_layout);
            leftMsg = itemView.findViewById(R.id.left_msg);
            rightMsg = itemView.findViewById(R.id.right_msg);
            leftPhoto = itemView.findViewById(R.id.left_photo);
            rightPhoto = itemView.findViewById(R.id.right_photo);
        }
    }

    public MsgAdapter(List<Msg> mMsgList,MsgActivity activity) {
        this.mMsgList = mMsgList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_chatui_inner_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        System.out.println(msg);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            //如果收到的消息,则显示左边消息布局,将右边布局隐藏
            holder.lefLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
            System.out.println("===holder======"+msg.getImage());
            Glide.with(activity).load(msg.getImage()).into(holder.leftPhoto);
            holder.rightPhoto.setVisibility(View.GONE);
           /* holder.leftPhoto.setImageResource(msg.getImage());*/
        } else if (msg.getType() == Msg.TYPE_SEND) {
            //如果是发出消息则显示右边消息布局,隐藏左边消息布局
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.lefLayout.setVisibility(View.GONE);
            holder.leftPhoto.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
            Glide.with(activity).load(msg.getImage()).into(holder.rightPhoto);
           /* holder.rightPhoto.setImageResource(msg.getImage());*/
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
