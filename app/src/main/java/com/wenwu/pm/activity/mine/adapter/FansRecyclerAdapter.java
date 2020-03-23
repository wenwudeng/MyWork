package com.wenwu.pm.activity.mine.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.bean.MsgAddNewConcern;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:wenwudeng
 * @date:9:47 PM 3/18/2020
 */
public class FansRecyclerAdapter extends RecyclerView.Adapter<FansRecyclerAdapter.ViewHolder> {
    {
    }

    private List<MsgAddNewConcern> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View ConcernItemView;
        CircleImageView photo;
        TextView userId;
        Button follow;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ConcernItemView = itemView;
            photo = itemView.findViewById(R.id.my_fans_user_photo);
            userId = itemView.findViewById(R.id.my_fans_user_id);
            follow = itemView.findViewById(R.id.my_fans_follow);
        }
    }

    public FansRecyclerAdapter(List<MsgAddNewConcern> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FansRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_fans_item, parent, false);
        final FansRecyclerAdapter.ViewHolder holder = new FansRecyclerAdapter.ViewHolder(view);

        holder.ConcernItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MsgAddNewConcern concern = list.get(position);
                Toast.makeText(v.getContext(), "you click view" + concern.getUserId(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.follow.setText("互相关注");
                holder.follow.setTextColor(Color.parseColor("#DEE1E6"));
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FansRecyclerAdapter.ViewHolder holder, int position) {
        MsgAddNewConcern concern = list.get(position);
        holder.photo.setImageResource(concern.getUserImage());
        holder.userId.setText(concern.getUserId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
