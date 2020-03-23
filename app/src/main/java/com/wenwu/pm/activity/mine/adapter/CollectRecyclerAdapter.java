package com.wenwu.pm.activity.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.wenwu.pm.R;
import com.wenwu.pm.activity.mine.bean.CollectCardViewItem;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:wenwudeng
 * @date:12:11 PM 3/19/2020
 */
public class CollectRecyclerAdapter extends RecyclerView.Adapter<CollectRecyclerAdapter.ViewHolder> {
    private List<CollectCardViewItem> CollectCardViewItemList;
    private CollectCardViewItem CollectCardViewItem;
    private boolean flag =false;


    public CollectRecyclerAdapter(List<CollectCardViewItem> CollectCardViewItem) {
        this.CollectCardViewItemList = CollectCardViewItem;
    }


    // ViewHolder对控件实例进行缓存,避免每次都去每次都去通过id去获得控件实例句柄.*/
    static  class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title_content;
        CircleImageView userPhoto;
        TextView userName;
        Button recommendButton;
        TextView recommendCount;
        Button favourButton;
        TextView favourCount;


        private ViewHolder( View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            title_content = itemView.findViewById(R.id.my_collect_title);
            userPhoto = itemView.findViewById(R.id.my_collect_user_photo);
            userName = itemView.findViewById(R.id.my_collect_user_name);

            recommendButton = itemView.findViewById(R.id.my_collect_recommend_button);
            recommendCount =  itemView.findViewById(R.id.my_collect_review_count);

            favourButton = itemView.findViewById(R.id.my_collect_favour_button);
            favourCount =  itemView.findViewById(R.id.my_collect_favour_count);
        }
    }

    /**
     * 创建viewHolder实例,并把加载出来的布局传递到构造函数中
     * @param parent 父布局
     * @param viewType 视图
     * @return  返回一个ViewHolder对象
     */
    @NonNull
    @Override
    public CollectRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获得R.layout.concern_item视图view实例
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_collect_item, parent, false);
        //将获得的concern_item视图实例作为ViewHolder获取实例的参数
        final CollectRecyclerAdapter.ViewHolder holder = new CollectRecyclerAdapter.ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                CollectCardViewItem CollectCardViewItem = CollectCardViewItemList.get(position);
                Toast.makeText(v.getContext(), "you click view" + CollectCardViewItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.favourButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                CollectCardViewItem = CollectCardViewItemList.get(position);
                Integer count = Integer.parseInt(CollectCardViewItem.getLikeCount())+1;
                Integer count1 = count-1;
                if (v.getId() == R.id.my_collect_favour_button) {
                    if (!flag) {
                        holder.favourButton.setBackgroundResource(R.mipmap.icon_upvoted);
                        holder.favourCount.setText(Integer.toString(count));
                        flag = true;
                    }else{
                        holder.favourButton.setBackgroundResource(R.mipmap.icon_upvote);
                        holder.favourCount.setText(Integer.toString(count1));
                        flag =false;
                    }

                }
            }
        });

        return holder;
    }


    /**
     * 对RecylerView子项进行赋值,会在每个子项滚动到屏幕内的时候执行
     * @param holder 实例对象
     * @param position 根据position获取各个实例
     */
    @Override
    public void onBindViewHolder(@NonNull CollectRecyclerAdapter.ViewHolder holder, int position) {
        CollectCardViewItem collectCardViewItem = CollectCardViewItemList.get(position);
        holder.title_content.setText(collectCardViewItem.getTitle());
        holder.userPhoto.setImageResource(collectCardViewItem.getPhoto());
    }

    @Override
    public int getItemCount() {
        return CollectCardViewItemList.size();
    }
}
