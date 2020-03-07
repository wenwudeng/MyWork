package com.wenwu.pm.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.wenwu.pm.R;
import com.wenwu.pm.home.bean.UserEditInfo;

import java.util.List;

/**
 * RecyclerView适配器
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    private List<UserEditInfo> userEditInfoList;


    public HomeRecyclerAdapter(List<UserEditInfo> userEditInfo) {
        this.userEditInfoList = userEditInfo;
    }


   // ViewHolder对控件实例进行缓存,避免每次都去每次都去通过id去获得控件实例句柄.*/
    static  class ViewHolder extends RecyclerView.ViewHolder {
        View userEditView;
        ImageView userUploadImg;
        TextView userUploadText;
        ImageView userPhoto;
        TextView userId;
        Button userFavourButton;
        TextView userFavourCount;

        private ViewHolder( View itemView) {
            super(itemView);
            userEditView = itemView;
            userUploadImg = itemView.findViewById(R.id.userUploadImg);
            userUploadText = itemView.findViewById(R.id.userUpLoadText);
            userPhoto = itemView.findViewById(R.id.user_Photo);
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
        holder.userEditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UserEditInfo userEditInfo = userEditInfoList.get(position);
                Toast.makeText(v.getContext(), "you click view" + userEditInfo.getContent(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.userFavourButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UserEditInfo userEditInfo = userEditInfoList.get(position);
                if (v.getId() == R.id.user_favourButton) {
                   int count =  userEditInfo.getAcceptFavourCount();
                    userEditInfo.setAcceptFavourCount(count+1);
                    holder.userFavourButton.setText("♥");
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserEditInfo userEditInfo = userEditInfoList.get(position);
        holder.userUploadImg.setImageResource(userEditInfo.getSendImageId());
        holder.userUploadText.setText(userEditInfo.getContent());
        holder.userPhoto.setImageResource(userEditInfo.getUserPhoto());
        holder.userId.setText(userEditInfo.getUserId());
        holder.userFavourButton.setText("♡");
        holder.userFavourCount.setText(Integer.toString(userEditInfo.getAcceptFavourCount()));

    }

    @Override
    public int getItemCount() {
        return userEditInfoList.size();
    }


}
