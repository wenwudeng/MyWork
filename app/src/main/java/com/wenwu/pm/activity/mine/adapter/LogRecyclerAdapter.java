package com.wenwu.pm.activity.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.home.bean.CardViewItemBean;
import com.wenwu.pm.activity.mine.fragment.MyLogFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:wenwudeng
 * @date:9:41 AM 3/19/2020
 */
public class LogRecyclerAdapter extends RecyclerView.Adapter<LogRecyclerAdapter.ViewHolder>{

    private List<CardViewItemBean> cardViewItemBeanList;
    private CardViewItemBean cardViewItemBean;

    private MyLogFragment myLogFragment;

    public LogRecyclerAdapter(List<CardViewItemBean> cardViewItemBean, MyLogFragment myLogFragment) {
        this.cardViewItemBeanList = cardViewItemBean;
        this.myLogFragment = myLogFragment;
    }


    // ViewHolder对控件实例进行缓存,避免每次都去每次都去通过id去获得控件实例句柄.*/
    static  class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView showImg;
        TextView title_content;
        CircleImageView photo;
        TextView userName;
        Button favourButton;
        TextView favourCount;

        private ViewHolder( View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            showImg = itemView.findViewById(R.id.my_log_show_img);
            title_content = itemView.findViewById(R.id.my_log_title_content);
            photo = itemView.findViewById(R.id.my_logo_user_photo);
            userName = itemView.findViewById(R.id.my_log_user_id);
            favourButton = itemView.findViewById(R.id.my_log_favour);
            favourCount =  itemView.findViewById(R.id.my_log_favour_count);
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
    public LogRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获得R.layout.concern_item视图view实例
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_log_item, parent, false);
        //将获得的concern_item视图实例作为ViewHolder获取实例的参数
        final LogRecyclerAdapter.ViewHolder holder = new LogRecyclerAdapter.ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                CardViewItemBean cardViewItemBean = cardViewItemBeanList.get(position);
                Toast.makeText(v.getContext(), "you click view" + cardViewItemBean.getContent(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.favourButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                cardViewItemBean = cardViewItemBeanList.get(position);
                if (v.getId() == R.id.user_favourButton) {
                    int count =  cardViewItemBean.getAcceptFavourCount();
                    holder.favourButton.setBackgroundResource(R.mipmap.icon_upvoted);
                }
            }
        });

        holder.favourCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.favourCount.setText("23");
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
    public void onBindViewHolder(@NonNull LogRecyclerAdapter.ViewHolder holder, int position) {
        CardViewItemBean cardViewItemBean = cardViewItemBeanList.get(position);
        int count = cardViewItemBean.getAcceptFavourCount();
        Glide.with(myLogFragment).load(cardViewItemBean.getImgUrl()).into(holder.showImg);
        Glide.with(myLogFragment).load(cardViewItemBean.getUserPhoto()).into(holder.photo);
        holder.title_content.setText(cardViewItemBean.getContent());
        holder.userName.setText(cardViewItemBean.getUserName());
        holder.favourCount.setText(Integer.toString(count));
    }

    @Override
    public int getItemCount() {
        return cardViewItemBeanList.size();
    }

}
