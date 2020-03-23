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
import com.wenwu.pm.activity.mine.bean.ReviewCardViewItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:wenwudeng
 * @date:3:50 PM 3/19/2020
 */
public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.ViewHolder>{
    private List<ReviewCardViewItem> ReviewCardViewItemList;
    private ReviewCardViewItem ReviewCardViewItem;
    private boolean flag = false;


    public ReviewRecyclerAdapter(List<ReviewCardViewItem> cardViewItemBeanList) {
        this.ReviewCardViewItemList = cardViewItemBeanList;
    }

    // ViewHolder对控件实例进行缓存,避免每次都去每次都去通过id去获得控件实例句柄.*/
    static  class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        CircleImageView userPhoto;
        TextView userName;
        TextView time;
        TextView reviewContent;
        TextView quesTitle;
        TextView favourCount;
        Button share;
        Button review;
        Button like;


        private ViewHolder( View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            userPhoto = itemView.findViewById(R.id.my_review_user_photo);
            userName = itemView.findViewById(R.id.my_review_user_name);
            time = itemView.findViewById(R.id.my_review_time);
            reviewContent = itemView.findViewById(R.id.my_review_content);
            quesTitle = itemView.findViewById(R.id.my_review_ques_title);
            favourCount = itemView.findViewById(R.id.my_review_favour_count);

            share = itemView.findViewById(R.id.my_review_share_button);
            review = itemView.findViewById(R.id.my_review_comment_button);
            like = itemView.findViewById(R.id.my_review_favour_button);
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
    public ReviewRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获得R.layout.concern_item视图view实例
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_review_item, parent, false);
        //将获得的concern_item视图实例作为ViewHolder获取实例的参数
        final ReviewRecyclerAdapter.ViewHolder holder = new ReviewRecyclerAdapter.ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ReviewCardViewItem ReviewCardViewItem = ReviewCardViewItemList.get(position);
                Toast.makeText(v.getContext(), "you click view" + ReviewCardViewItem.getQuesTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        holder.like.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ReviewCardViewItem = ReviewCardViewItemList.get(position);
                Integer count = Integer.parseInt(ReviewCardViewItem.getFavourCount())+1;
                Integer count1 = count-1;
                if (v.getId() == R.id.my_review_favour_button) {
                    if (!flag) {
                        holder.like.setBackgroundResource(R.mipmap.icon_upvoted);
                        holder.favourCount.setText(Integer.toString(count));
                        flag = true;
                    }else{
                        holder.like.setBackgroundResource(R.mipmap.icon_upvote);
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
    public void onBindViewHolder(@NonNull ReviewRecyclerAdapter.ViewHolder holder, int position) {
        ReviewCardViewItem ReviewCardViewItem = ReviewCardViewItemList.get(position);
        holder.userPhoto.setImageResource(ReviewCardViewItem.getUserPhoto());
        holder.userName.setText(ReviewCardViewItem.getUserNam());
        holder.time .setText(ReviewCardViewItem.getTime());
        holder.reviewContent.setText(ReviewCardViewItem.getReviewContent());
        holder.quesTitle.setText(ReviewCardViewItem.getQuesTitle());
        holder.favourCount.setText(ReviewCardViewItem.getFavourCount());
    }

    @Override
    public int getItemCount() {
        return ReviewCardViewItemList.size();
    }
}

