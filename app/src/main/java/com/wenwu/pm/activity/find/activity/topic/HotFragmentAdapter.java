package com.wenwu.pm.activity.find.activity.topic;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wenwu.pm.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author:wenwudeng
 * @date:11:28 2020/4/8
 */
public class HotFragmentAdapter extends BaseQuickAdapter<Model, BaseViewHolder> {
    public HotFragmentAdapter(int layoutResId, @Nullable List<Model> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, Model model) {
        Glide.with(getContext()).load(model.getImage()).into((ImageView) holder.findView(R.id.userUploadImg));
        holder.setText(R.id.userUpLoadText, model.getTitle());
        Glide.with(getContext()).load(model.getImage()).into((ImageView) holder.findView(R.id.user_photo));
        holder.setText(R.id.user_id, model.getUserName());
    }
}
