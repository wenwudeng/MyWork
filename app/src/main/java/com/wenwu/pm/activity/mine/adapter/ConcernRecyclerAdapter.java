package com.wenwu.pm.activity.mine.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.bean.MsgAddNewConcern;
import com.wenwu.pm.activity.mine.activity.FollowActivity;
import com.wenwu.pm.activity.mine.activity.PersonHomeActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:wenwudeng
 * @date:9:20 PM 3/18/2020
 */
public class ConcernRecyclerAdapter extends RecyclerView.Adapter<ConcernRecyclerAdapter.ViewHolder>{
    private List<MsgAddNewConcern> list;
    private FollowActivity followActivity;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View ConcernItemView;
        CircleImageView photo;
        TextView userId;
        Button unFollow;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ConcernItemView = itemView;
            photo = itemView.findViewById(R.id.my_concern_user_photo);
            userId = itemView.findViewById(R.id.my_concern_user_id);
            unFollow = itemView.findViewById(R.id.my_concern_follow);
        }

    }

    public ConcernRecyclerAdapter(List<MsgAddNewConcern> list, FollowActivity followActivity) {
        this.list = list;
        this.followActivity = followActivity;
    }

    @NonNull
    @Override
    public ConcernRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_concern_item, parent, false);
        final ConcernRecyclerAdapter.ViewHolder holder = new ConcernRecyclerAdapter.ViewHolder(view);

        holder.ConcernItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MsgAddNewConcern concern = list.get(position);
                Toast.makeText(v.getContext(), "you click view" + concern.getUserId(),Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(new Intent(v.getContext(), PersonHomeActivity.class));
            }
        });

        holder.unFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.unFollow.setText("关注");
                holder.unFollow.setTextColor(v.getResources().getColor(R.color.red));
               holder.unFollow.setBackgroundResource(R.drawable.btn_round_unfollow);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConcernRecyclerAdapter.ViewHolder holder, int position) {
        MsgAddNewConcern concern = list.get(position);
        Glide.with(followActivity).load(concern.getUserImage()).into(holder.photo);
        holder.userId.setText(concern.getUserId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
