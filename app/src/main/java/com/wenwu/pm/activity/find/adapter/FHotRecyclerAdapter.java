package com.wenwu.pm.activity.find.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.wenwu.pm.R;
import com.wenwu.pm.activity.find.bean.FindHotTopicShow;
import com.wenwu.pm.activity.find.activity.topic.TopicActivity;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:12:32 2020/2/18
 */
public class FHotRecyclerAdapter extends RecyclerView.Adapter<FHotRecyclerAdapter.ViewHolder> {

    private List<FindHotTopicShow> topicShowList;

    public FHotRecyclerAdapter(List<FindHotTopicShow> topicShowList) {
        this.topicShowList = topicShowList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View hotTopicViewItem;
        TextView hot_topic_show;
        TextView hot_description_show;
        TextView hot_taker_count;
        ImageView hot_image_show;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotTopicViewItem = itemView;//设置监监听器
            hot_topic_show = itemView.findViewById(R.id.hot_topic_show);
            hot_description_show = itemView.findViewById(R.id.hot__description_show);
            hot_taker_count = itemView.findViewById(R.id.hot_taker_count);
            hot_image_show = itemView.findViewById(R.id.hot_image_show);
        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_hottopic_item, parent, false);
       final  ViewHolder holder = new ViewHolder(view);

       //监听每一个item
        holder.hotTopicViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                FindHotTopicShow topicShow = topicShowList.get(position);
                v.getContext().startActivity(new Intent(v.getContext(), TopicActivity.class));
                Toast.makeText(v.getContext(), "you click view" + topicShow.getTopic(), Toast.LENGTH_SHORT).show();
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FindHotTopicShow topicShow = topicShowList.get(position);
        holder.hot_topic_show.setText(topicShow.getTopic());
        holder.hot_description_show.setText(topicShow.getTopicDescription());
        holder.hot_taker_count.setText(topicShow.getTakeCount());
        holder.hot_image_show.setImageResource(topicShow.getImage());
    }

    @Override
    public int getItemCount() {
        return topicShowList.size();
    }

}
