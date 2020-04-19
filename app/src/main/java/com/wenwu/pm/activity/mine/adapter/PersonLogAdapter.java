package com.wenwu.pm.activity.mine.adapter;

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
import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.home.adapter.DynamicRecyclerAdapter;
import com.wenwu.pm.activity.home.bean.CardViewItemBean;
import com.wenwu.pm.activity.mine.fragment.MyLogFragment;
import com.wenwu.pm.activity.mine.fragment.PersonLogFragment;
import com.wenwu.pm.activity.publish.activity.ArticleReviewActivity;
import com.wenwu.pm.goson.MyLogJson;
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
 * @date:9:41 AM 3/19/2020
 * 数据在HomeFragment中加载
 */
public class PersonLogAdapter extends RecyclerView.Adapter<PersonLogAdapter.ViewHolder>{

    private List<CardViewItemBean> cardViewItemBeanList;
    private CardViewItemBean cardViewItemBean;

    private PersonLogFragment personLogFragment;

    private boolean flag = false;

    public PersonLogAdapter(List<CardViewItemBean> cardViewItemBean, PersonLogFragment personLogFragment) {
        this.cardViewItemBeanList = cardViewItemBean;
        this.personLogFragment = personLogFragment;
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
    public PersonLogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //获得R.layout.concern_item视图view实例
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_log_item, parent, false);
        //将获得的concern_item视图实例作为ViewHolder获取实例的参数
        final PersonLogAdapter.ViewHolder holder = new PersonLogAdapter.ViewHolder(view);

/*        int position = holder.getAdapterPosition();
        System.out.println(cardViewItemBeanList.get(position).getUserId());
        personLogFragment.getMyLogData(cardViewItemBeanList.get(position).getUserId());*/

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                CardViewItemBean item = cardViewItemBeanList.get(position);
                /*传参*/
                JsonUtil.bean =  new CardViewItemBean(item.getUserId(),item.getArticleId(), item.getTitle(),
                        item.getImgUrl(), item.getContent(), item.getUserName(), item.getUserPhoto(), item.getCollect());

                /*加载评论数据*/
                DynamicRecyclerAdapter.initCommentData();

                v.getContext().startActivity(new Intent(v.getContext(), ArticleReviewActivity.class));
                Toast.makeText(v.getContext(), "you click view" + cardViewItemBean.getContent(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.favourButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                cardViewItemBean = cardViewItemBeanList.get(position);
                if (v.getId() == R.id.user_favourButton) {
                    int count =  cardViewItemBean.getAcceptFavourCount()+1;
                    int count1 = count-1;
                    if (!flag) {
                        holder.favourButton.setBackgroundResource(R.mipmap.icon_upvoted);
                        holder.favourCount.setText(Integer.toString(count));
                        flag = true;
                    }else {
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
    public void onBindViewHolder(@NonNull PersonLogAdapter.ViewHolder holder, int position) {
        CardViewItemBean cardViewItemBean = cardViewItemBeanList.get(position);
        int count = cardViewItemBean.getAcceptFavourCount();
        Glide.with(personLogFragment).load(cardViewItemBean.getImgUrl()).into(holder.showImg);
        Glide.with(personLogFragment).load(cardViewItemBean.getUserPhoto()).into(holder.photo);
        holder.title_content.setText(cardViewItemBean.getContent());
        holder.userName.setText(cardViewItemBean.getUserName());
        holder.favourCount.setText(Integer.toString(count));
    }

    @Override
    public int getItemCount() {
        return cardViewItemBeanList.size();
    }

}
