package com.wenwu.pm.activity.publish.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wenwu.pm.R;
import com.wenwu.pm.activity.publish.bean.ArticleReview;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author:wenwudeng
 * @date:10:39 PM 3/26/2020
 */
public class ArticleReviewAdapter  extends RecyclerView.Adapter<ArticleReviewAdapter.ViewHolder> {

   private List<ArticleReview> reviewList;
    private boolean flag =false;

    public ArticleReviewAdapter(List<ArticleReview> reviewList) {
        this.reviewList = reviewList;
    }

    static  class ViewHolder extends RecyclerView.ViewHolder {
        View reviewItem;
        CircleImageView userPhoto;
        TextView userName;
        TextView reviewTime;
        Button favour;
        TextView reviewContent;
        TextView favourCount;


        private ViewHolder(View itemView) {
            super(itemView);
            reviewItem = itemView;
            userPhoto = itemView.findViewById(R.id.article_reviewer_photo);
            userName = itemView.findViewById(R.id.article_reviewer_user_name);
            reviewTime = itemView.findViewById(R.id.article_reviewer_time);
            favour = itemView.findViewById(R.id.article_reviewer_like);
            favourCount = itemView.findViewById(R.id.article_reviewer_like_count);
            reviewContent = itemView.findViewById(R.id.article_reviewer_content);
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
    public ArticleReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获得R.layout.concern_item视图view实例
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_article_show_item, parent, false);
        //将获得的concern_item视图实例作为ViewHolder获取实例的参数
        final ArticleReviewAdapter.ViewHolder holder = new ArticleReviewAdapter.ViewHolder(view);
        holder.reviewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                ArticleReview articleReviewItem = reviewList.get(position);
                Toast.makeText(v.getContext(), "you click view" + articleReviewItem.getReviewContent(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.favour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.article_reviewer_like) {
                    if (!flag) {
                        holder.favour.setBackgroundResource(R.mipmap.icon_upvoted);
                        flag = true;
                    }else{
                        holder.favour.setBackgroundResource(R.mipmap.icon_upvote);
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
    public void onBindViewHolder(@NonNull ArticleReviewAdapter.ViewHolder holder, int position) {
        ArticleReview articleReview = reviewList.get(position);
        holder.userPhoto.setImageResource(articleReview.getPhoto());
        holder.userName.setText(articleReview.getUserId());
        holder.reviewTime.setText(articleReview.getTime());
      //  holder.favourCount.setText(articleReview.getLikeCount());
        holder.reviewContent.setText(articleReview.getReviewContent());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }
}


