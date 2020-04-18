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
import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.bean.MsgAddNewConcern;
import com.wenwu.pm.activity.mine.activity.FansActivity;
import com.wenwu.pm.activity.mine.activity.PersonHomeActivity;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author:wenwudeng
 * @date:9:47 PM 3/18/2020
 */
public class FansAdapter extends RecyclerView.Adapter<FansAdapter.ViewHolder> {
    private List<MsgAddNewConcern> list;
    private FansActivity activity;
    static class ViewHolder extends RecyclerView.ViewHolder {
        View ConcernItemView;
        CircleImageView photo;
        TextView userId;
        Button fans;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ConcernItemView = itemView;
            photo = itemView.findViewById(R.id.my_fans_user_photo);
            userId = itemView.findViewById(R.id.my_fans_user_id);
            fans = itemView.findViewById(R.id.my_fans_follow);
        }
    }
    public FansAdapter(List<MsgAddNewConcern> list,FansActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FansAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_fans_item, parent, false);
        final FansAdapter.ViewHolder holder = new FansAdapter.ViewHolder(view);
        holder.ConcernItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MsgAddNewConcern fans = list.get(position);
                Intent intent = new Intent(v.getContext(), PersonHomeActivity.class);
                intent.putExtra("id", fans.getId());
                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "you click view" + fans.getUserId(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.fans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MsgAddNewConcern fans = list.get(position);
                if (holder.fans.getText().equals("相互关注")) {
                    holder.fans.setText("回粉");
                    holder.fans.setTextColor(v.getResources().getColor(R.color.red));
                    holder.fans.setBackgroundResource(R.drawable.btn_round_unfollow);
                    FollowAdapter.unFollow(fans.getId());
                }else {
                    holder.fans.setText("相互关注");
                    holder.fans.setTextColor(v.getResources().getColor(R.color.gray));
                    holder.fans.setBackgroundResource(R.drawable.btn_round_followed);
                    FollowAdapter.follow(fans.getId());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FansAdapter.ViewHolder holder, int position) {
        MsgAddNewConcern fans = list.get(position);
        Glide.with(activity).load(fans.getUserImage()).into(holder.photo);
        holder.userId.setText(fans.getUserId());
        System.out.println("======id"+fans.getId());
        isFans(fans.getId(),holder);
    }

    /*查询粉丝状态*/
    public  void isFans(int id,FansAdapter.ViewHolder holder) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",id );
        map.put("fId", JsonUtil.loginJson.getData().getId());
        OkHttpUtil.sendPostRequest("followAndFans/isFollow", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               String data = response.body().string();
                 LRReturnJson json = new Gson().fromJson(data, LRReturnJson.class);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getData() == 1) {
                            holder.fans.setText("相互关注");
                            holder.fans.setTextColor(activity.getResources().getColor(R.color.gray));
                            holder.fans.setBackgroundResource(R.drawable.btn_round_followed);
                        }else {
                            holder.fans.setText("回粉");
                            holder.fans.setTextColor(activity.getResources().getColor(R.color.red));
                            holder.fans.setBackgroundResource(R.drawable.btn_round_unfollow);
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
