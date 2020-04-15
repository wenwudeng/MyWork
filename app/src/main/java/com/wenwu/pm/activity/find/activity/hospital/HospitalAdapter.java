package com.wenwu.pm.activity.find.activity.hospital;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenwu.pm.R;
import org.jetbrains.annotations.NotNull;
import java.util.List;


/**
 * @author:wenwudeng
 * @date:18:03 2020/4/11
 */
public class HospitalAdapter extends BaseQuickAdapter<PetHospitalModel, BaseViewHolder> {
    private HospitalActivity hospital;

    public HospitalAdapter(int layoutResId, @Nullable List<PetHospitalModel> data, HospitalActivity hospital) {
        super(layoutResId, data);
        this.hospital = hospital;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, PetHospitalModel model) {
        holder.setText(R.id.location_store_name, model.getStoreName())
                .setText(R.id.location_distance, model.getDistance())
                .setText(R.id.location_address, model.getAddress())
                .addOnClickListener(R.id.location_call)
                .addOnClickListener(R.id.location_route);
    }
}
