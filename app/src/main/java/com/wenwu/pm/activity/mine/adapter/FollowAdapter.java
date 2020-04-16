package com.wenwu.pm.activity.mine.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.LogTime;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.message.bean.MsgAddNewConcern;
import com.wenwu.pm.activity.mine.activity.FollowActivity;
import com.wenwu.pm.activity.mine.activity.PersonHomeActivity;
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
 * @date:9:20 PM 3/18/2020
 */
public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ViewHolder>{
    private static final String TAG = "FollowAdapter";

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

    public FollowAdapter(List<MsgAddNewConcern> list, FollowActivity followActivity) {
        this.list = list;
        this.followActivity = followActivity;
    }

    @NonNull
    @Override
    public FollowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_concern_item, parent, false);
        final FollowAdapter.ViewHolder holder = new FollowAdapter.ViewHolder(view);
        holder.ConcernItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MsgAddNewConcern concern = list.get(position);
                Toast.makeText(v.getContext(), "you click view" + concern.getUserId(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), PersonHomeActivity.class);
                intent.putExtra("id", concern.getId());
                v.getContext().startActivity(intent);
            }
        });

        holder.unFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MsgAddNewConcern concern = list.get(position);
                if (holder.unFollow.getText().equals("已关注")) {
                    holder.unFollow.setText("关注");
                    holder.unFollow.setTextColor(v.getResources().getColor(R.color.red));
                    holder.unFollow.setBackgroundResource(R.drawable.btn_round_unfollow);
                    unFollow(concern.getId());
                }else {
                    holder.unFollow.setText("已关注");
                    holder.unFollow.setTextColor(v.getResources().getColor(R.color.gray));
                    holder.unFollow.setBackgroundResource(R.drawable.btn_round_followed);
                    follow(concern.getId());
                }
            }
        });
        return holder;
    }

    public static void follow(int fId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", JsonUtil.loginJson.getData().getId());
        map.put("fId", fId);
        OkHttpUtil.sendPostRequest("followAndFans/follow", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.body().string());
            }
        });
    }

    public static void unFollow(int fId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", JsonUtil.loginJson.getData().getId());
        map.put("fId", fId);
        OkHttpUtil.sendPostRequest("followAndFans/unFollow", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.body().string());
            }
        });
    }


    @Override
    public void onBindViewHolder(@NonNull FollowAdapter.ViewHolder holder, int position) {
        MsgAddNewConcern concern = list.get(position);
        Glide.with(followActivity).load(concern.getUserImage()).into(holder.photo);
        holder.userId.setText(concern.getUserId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
