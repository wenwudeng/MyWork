package com.wenwu.pm.activity.home.adapter;

import android.content.Intent;
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
import com.wenwu.pm.activity.home.fragment.HomeConcernFragment;
import com.wenwu.pm.activity.publish.activity.ArticleReviewActivity;
import com.wenwu.pm.utils.JsonUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.wenwu.pm.activity.home.adapter.DynamicRecyclerAdapter.initCommentData;

/**
 * RecyclerView适配器
 */
public class ConcernRecyclerAdapter extends RecyclerView.Adapter<ConcernRecyclerAdapter.ViewHolder> {

    private List<CardViewItemBean> cardViewItemBeanList;
    private CardViewItemBean cardViewItemBean ;

    private HomeConcernFragment concernFragment;

    public ConcernRecyclerAdapter(List<CardViewItemBean> cardViewItemBean, HomeConcernFragment concernFragment) {
        this.cardViewItemBeanList = cardViewItemBean;
        this.concernFragment = concernFragment;
    }


   // ViewHolder对控件实例进行缓存,避免每次都去每次都去通过id去获得控件实例句柄.*/
    static  class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userUploadImg;
        TextView userUploadText;
        CircleImageView userPhoto;
        TextView userId;
        CardView cardView;
        Button userFavourButton;
        TextView userFavourCount;

        private ViewHolder( View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            userUploadImg = itemView.findViewById(R.id.userUploadImg);
            userUploadText = itemView.findViewById(R.id.userUpLoadText);
            userPhoto = itemView.findViewById(R.id.user_photo);
            userId = itemView.findViewById(R.id.user_id);
            userFavourButton = itemView.findViewById(R.id.user_favourButton);
            userFavourCount =  itemView.findViewById(R.id.user_favourCount);
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获得R.layout.concern_item视图view实例
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        //将获得的concern_item视图实例作为ViewHolder获取实例的参数
        final ViewHolder holder = new ViewHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                CardViewItemBean item = cardViewItemBeanList.get(position);
                /*传参*/
                JsonUtil.bean = item;

                /*提前加载文章评论数据*/
                initCommentData();
                v.getContext().startActivity(new Intent(v.getContext(), ArticleReviewActivity.class));
                Toast.makeText(v.getContext(), "you click view" + item.getContent(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.userFavourButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                cardViewItemBean = cardViewItemBeanList.get(position);
                if (v.getId() == R.id.user_favourButton) {
                   int count =  cardViewItemBean.getAcceptFavourCount();
                    holder.userFavourButton.setBackgroundResource(R.mipmap.icon_upvoted);
                }
            }
        });

        holder.userFavourCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.userFavourCount.setText(Integer.toString(cardViewItemBean.getAcceptFavourCount()+1));
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardViewItemBean cardViewItemBean = cardViewItemBeanList.get(position);
        int count = cardViewItemBean.getAcceptFavourCount();
        Glide.with(concernFragment).load(cardViewItemBean.getImgUrl()).into(holder.userUploadImg);
        Glide.with(concernFragment).load(cardViewItemBean.getUserPhoto()).into(holder.userPhoto);
        holder.userUploadText.setText(cardViewItemBean.getContent());
        holder.userId.setText(cardViewItemBean.getUserName());
        holder.userFavourCount.setText(Integer.toString(count));


    }

    @Override
    public int getItemCount() {
        return cardViewItemBeanList.size();
    }


}
