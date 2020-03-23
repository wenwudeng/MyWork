package com.wenwu.pm.activity.message.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.activity.MsgActivity;
import com.wenwu.pm.activity.message.bean.MessageListItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author:wenwudeng
 * @date:21:32 2020/2/18
 * 聊天通知界面item适配器
 */
public class MsgListItemAdapter extends RecyclerView.Adapter<MsgListItemAdapter.ViewHolder> {

    private List<MessageListItem> messageListItemList;


    public MsgListItemAdapter(List<MessageListItem> messageListItemList) {
        this.messageListItemList = messageListItemList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        View listItemView;
        CircleImageView ms_receive_user_photo;
        TextView ms_receive_time;
        TextView ms_receive_userId;
        TextView ms_receive_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemView = itemView;
            ms_receive_user_photo = itemView.findViewById(R.id.ms_receive_user_photo);
            ms_receive_time = itemView.findViewById(R.id.ms_receive_time);
            ms_receive_userId = itemView.findViewById(R.id.ms_receive_userId);
            ms_receive_content = itemView.findViewById(R.id.ms_receive_content);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_chat_list_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*int position = holder.getAdapterPosition();
                MessageListItem msgItem = messageListItemList.get(position);*/
               v.getContext().startActivity(new Intent(v.getContext(), MsgActivity.class));
                //Toast.makeText(v.getContext(), "you click view" + msgItem.getChatMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MessageListItem listItem = messageListItemList.get(position);
        holder.ms_receive_user_photo.setImageResource(listItem.getImage());
        holder.ms_receive_userId.setText(listItem.getUserId());
        holder.ms_receive_content.setText(listItem.getChatMessage());
        holder.ms_receive_time.setText(listItem.getTime());
    }

    @Override
    public int getItemCount() {
        return messageListItemList.size();
    }


}
