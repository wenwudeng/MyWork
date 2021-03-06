package com.wenwu.pm.presenter;


import com.wenwu.pm.activity.mine.fragment.MyFragment;
import com.wenwu.pm.modle.EditInfoModel;
import com.wenwu.pm.modle.listener.Listener;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.view.IEditInfoView;
import com.wenwu.pm.view.IView;

import java.lang.ref.WeakReference;

/**
 * @author:wenwudeng
 * @date:3:09 PM 3/24/2020
 */
public class EditInfoPresenter extends PresenterBase {

    private MyFragment myFragment;

    public EditInfoPresenter(IEditInfoView editInfoView, MyFragment myFragment) {
        this.mIModel = new EditInfoModel();
        this.mViewReference = new WeakReference<IView>(editInfoView);
        this.myFragment = myFragment;
    }

    public void save() {

        if (mIModel != null && mViewReference != null && mViewReference.get() != null) {
            int userId = JsonUtil.loginJson.getData().getId();
            IEditInfoView editInfoView = (IEditInfoView) mViewReference.get();
            String userPhoto = editInfoView.getPhotoUrl();
            String userName = editInfoView.getUserName();
            String gender = editInfoView.getGenders();
            String city = editInfoView.getCity();
            String profile = editInfoView.getProfile();
            String pet = editInfoView.getPet();

            myFragment.updateInfo(userPhoto,userName,gender,city,profile,pet);

            ((EditInfoModel)mIModel).save(userId, userPhoto, userName, gender, city, profile, pet, new Listener() {
                @Override
                public void onSuccess(Object json) {
                    if (mViewReference.get() != null) {
                        mViewReference.get().onViewSuccess(json);
                    }
                }

                @Override
                public void onFail(Object json) {
                    if (mViewReference.get() != null) {
                        mViewReference.get().onViewFail(json);
                    }
                }
            });
        }
    }
}
