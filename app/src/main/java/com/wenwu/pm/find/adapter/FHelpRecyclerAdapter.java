package com.wenwu.pm.find.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.wenwu.pm.R;
import com.wenwu.pm.find.bean.FindHelpPetShow;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:23:49 2020/2/17
 * 宠友互助信息展示Recycler配器
 */
public class FHelpRecyclerAdapter extends RecyclerView.Adapter<FHelpRecyclerAdapter.ViewHolder> {
    private List<FindHelpPetShow> helpPetShowList;

    public FHelpRecyclerAdapter(List<FindHelpPetShow> helpPetShowList) {
        this.helpPetShowList = helpPetShowList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        View helpPetShowView;//recycle列表中每一个item视图对象
        ImageView help_img_show;
        TextView help_title_show;
        TextView help_content_show;
        TextView helip_time_show;
        TextView help_reply_count_show;

        private ViewHolder( View itemView) {
            super(itemView);
            helpPetShowView = itemView;//recycle列表中每一个item视图对象,用于设置监听器
            help_img_show = itemView.findViewById(R.id.help_image_show);
            help_title_show = itemView.findViewById(R.id.help_title_show);
            help_content_show = itemView.findViewById(R.id.help_content_show);
            helip_time_show = itemView.findViewById(R.id.help_time_show);
            help_reply_count_show = itemView.findViewById(R.id.help_reply_Count_show);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获得R.layout.find_helppet_item.xml视图view实例
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_helppet_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.helpPetShowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                FindHelpPetShow contentShow = helpPetShowList.get(position);
                Toast.makeText(v.getContext(), "you click view" + contentShow.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FindHelpPetShow petShow = helpPetShowList.get(position);
        holder.help_img_show.setImageResource(R.drawable.img);
        holder.help_title_show.setText(petShow.getTitle());
        holder.help_content_show.setText(petShow.getContent());
        holder.helip_time_show.setText(petShow.getDate());
        holder.help_reply_count_show.setText(Integer.toString(petShow.getAnswerCount()));
    }

    @Override
    public int getItemCount() {
        return helpPetShowList.size();
    }
}
