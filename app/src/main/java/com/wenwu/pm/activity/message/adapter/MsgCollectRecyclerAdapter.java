package com.wenwu.pm.activity.message.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autonavi.ae.pos.LocGSVData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.LogTime;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.home.adapter.DynamicRecyclerAdapter;
import com.wenwu.pm.activity.home.bean.CardViewItemBean;
import com.wenwu.pm.activity.message.activity.MsgCollectPraiseActivity;
import com.wenwu.pm.activity.message.bean.Msg;
import com.wenwu.pm.activity.message.bean.MsgCollectPraise;
import com.wenwu.pm.activity.publish.activity.ArticleReviewActivity;
import com.wenwu.pm.utils.JsonUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:wenwudeng
 * @date:12:15 AM 3/10/2020
 * 收藏和赞模块recycler列表适配器
 */
public class MsgCollectRecyclerAdapter extends RecyclerView.Adapter<MsgCollectRecyclerAdapter.ViewHolder> {
    private List<MsgCollectPraise> list;
    private MsgCollectPraiseActivity activity;

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


    public MsgCollectRecyclerAdapter(List<MsgCollectPraise> list,MsgCollectPraiseActivity activity) {
        this.list = list;
        this.activity =activity;
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

                JsonUtil.bean = new CardViewItemBean(collect.getAuId(),collect.getaId(),collect.getTitle(),collect.getImg()
                ,collect.getContent(),JsonUtil.loginJson.getData().getUserName(),JsonUtil.loginJson.getData().getPhoto(),0);
                /*加载评论数据*/
                DynamicRecyclerAdapter.initCommentData();
                v.getContext().startActivity(new Intent(v.getContext(), ArticleReviewActivity.class));
                Toast.makeText(v.getContext(), "you click view" + collect.getsName(),Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MsgCollectRecyclerAdapter.ViewHolder holder, int position) {
        MsgCollectPraise item = list.get(position);
        Glide.with(activity).load(item.getsPhoto()).into(holder.photo);
       // Log.d("tag",item.getsPhoto());
        holder.userId.setText(item.getsName());
       // Log.d("tag",item.getsName());
        holder.time.setText(item.getTime());
        Glide.with(activity).load(item.getImg()).into(holder.sendImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
