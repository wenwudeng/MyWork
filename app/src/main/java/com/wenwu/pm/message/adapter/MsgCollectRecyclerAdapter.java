package com.wenwu.pm.message.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wenwu.pm.R;
import com.wenwu.pm.message.bean.MsgAddNewConcern;
import com.wenwu.pm.message.bean.MsgCollectPraise;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:wenwudeng
 * @date:12:15 AM 3/10/2020
 * 收藏和赞模块recycler列表适配器
 */
public class MsgCollectRecyclerAdapter extends RecyclerView.Adapter<MsgCollectRecyclerAdapter.ViewHolder> {
    private List<MsgCollectPraise> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View collectPraiseItemView;
        CircleImageView photo;
        TextView userId;
        TextView time;
        ImageView sendImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            collectPraiseItemView = itemView;
            photo = itemView.findViewById(R.id.msg_collect_user_photo);
            userId = itemView.findViewById(R.id.msg_collect_user_id);
            time = itemView.findViewById(R.id.msg_collect_user_time);
            sendImage = itemView.findViewById(R.id.msg_collect_content_img);
        }
    }


    public MsgCollectRecyclerAdapter(List<MsgCollectPraise> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MsgCollectRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_collect_praise_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.collectPraiseItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MsgCollectPraise collect = list.get(position);
                Toast.makeText(v.getContext(), "you click view" + collect.getUserId(),Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MsgCollectRecyclerAdapter.ViewHolder holder, int position) {
        MsgCollectPraise collectPraise = list.get(position);
        holder.photo.setImageResource(collectPraise.getUserImage());
        holder.userId.setText(collectPraise.getUserId());
        holder.time.setText(collectPraise.getUserTime());
        holder.sendImage.setImageResource(collectPraise.getContentImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
