package com.wenwu.pm.activity.message.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.bean.MsgAddNewConcern;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/*
 * @author:wenwudeng
 * @date:12:58 AM 3/10/2020
 * 新增关注Recycler列表适配器
 */

public class MsgANConcernRecyclerAdapter extends RecyclerView.Adapter<MsgANConcernRecyclerAdapter.ViewHolder> {
    private List<MsgAddNewConcern> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View aNConcernItemView;
        CircleImageView photo;
        TextView userId;
        TextView time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            aNConcernItemView = itemView;
            photo = itemView.findViewById(R.id.msg_concern_user_photo);
            userId = itemView.findViewById(R.id.msg_concern_user_id);
            time = itemView.findViewById(R.id.msg_concern_user_time);
        }
    }

    public MsgANConcernRecyclerAdapter(List<MsgAddNewConcern> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_add_new_concern_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.aNConcernItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MsgAddNewConcern concern = list.get(position);
                Toast.makeText(v.getContext(), "you click view" + concern.getUserId(),Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MsgAddNewConcern concern = list.get(position);
       // holder.photo.setImageResource(concern.getUserImage());
        holder.userId.setText(concern.getUserId());
        holder.time.setText(concern.getUserTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}



