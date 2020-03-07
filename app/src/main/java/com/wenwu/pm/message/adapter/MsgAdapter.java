package com.wenwu.pm.message.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cxd.chatview.moudle.ChatView;
import com.wenwu.pm.R;
import com.wenwu.pm.message.bean.Msg;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:wenwudeng
 * @date:15:42 2020/2/18
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;

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

    public MsgAdapter(List<Msg> mMsgList) {
        this.mMsgList = mMsgList;
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
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            //如果收到的消息,则显示左边消息布局,将右边布局隐藏
            holder.lefLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
            holder.rightPhoto.setVisibility(View.GONE);
           /* holder.leftPhoto.setImageResource(msg.getImage());*/
        } else if (msg.getType() == Msg.TYPE_SEND) {
            //如果是发出消息则显示右边消息布局,隐藏左边消息布局
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.lefLayout.setVisibility(View.GONE);
            holder.leftPhoto.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
           /* holder.rightPhoto.setImageResource(msg.getImage());*/
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
