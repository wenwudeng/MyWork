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
import com.wenwu.pm.find.bean.FindDailyTipsShow;

import java.util.List;


/**
 * @author:wenwudeng
 * @date:15:01 2020/2/17
 * 此类为发现模块中的每日贴士信息展示RecycleView列表的适配器
 */
public class FDailyRecyclerAdapter extends RecyclerView.Adapter<FDailyRecyclerAdapter.ViewHolder>{

    private List<FindDailyTipsShow> dailyTipsContentShowList;

    public FDailyRecyclerAdapter(List<FindDailyTipsShow> dailyTipsContentShows) {
        this.dailyTipsContentShowList = dailyTipsContentShows;
    }

    // ViewHolder对控件实例进行缓存,避免每次都去每次都去通过id去获得控件实例句柄.*/
    static  class ViewHolder extends RecyclerView.ViewHolder {
        View dailyTipsShowView;//recycle列表中每一个item视图对象
        ImageView daily_img_show;
        TextView daily_title_show;
        TextView daily_content_show;
        TextView daily_learner_count;

        private ViewHolder( View itemView) {
            super(itemView);
            dailyTipsShowView = itemView;//recycle列表中每一个item视图对象,用于设置监听器
            daily_img_show = itemView.findViewById(R.id.daily_img_show);
            daily_title_show = itemView.findViewById(R.id.daily_title_show);
            daily_content_show = itemView.findViewById(R.id.daily_content_show);
            daily_learner_count = itemView.findViewById(R.id.daily_learner_count);
        }
    }

    /**
     * 创建viewHolder实例,并把加载出来的find_dailytips_item.xml布局视图对象传递到ViewHolder构造函数中
     * @param parent 父布局
     * @param viewType 视图
     * @return  返回一个ViewHolder对象
     */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获得R.layout.find_dailyTips_item.xml视图view实例
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_dailytips_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.dailyTipsShowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                FindDailyTipsShow contentShow = dailyTipsContentShowList.get(position);
                Toast.makeText(v.getContext(), "you click view" + contentShow.getContent(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    /**
     * 对RecylerView的item子项进行赋值,会在每个子项滚动到屏幕内的时候执行
     * @param holder 实例对象
     * @param position 根据position获取各个实例
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FindDailyTipsShow contentShow = dailyTipsContentShowList.get(position);
        holder.daily_title_show.setText(contentShow.getTitle());
        holder.daily_content_show.setText(contentShow.getContent());
        holder.daily_img_show.setImageResource(contentShow.getImage());
        holder.daily_learner_count.setText(Integer.toString(contentShow.getLearnCount()));

    }

    @Override
    public int getItemCount() {
        return dailyTipsContentShowList.size();
    }




}
