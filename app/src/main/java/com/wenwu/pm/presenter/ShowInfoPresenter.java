package com.wenwu.pm.presenter;

import com.wenwu.pm.modle.ShowInfoModel;
import com.wenwu.pm.modle.listener.Listener;
import com.wenwu.pm.view.IShowView;
import com.wenwu.pm.view.IView;

import java.lang.ref.WeakReference;

/**
 * @author:wenwudeng
 * @date:10:09 PM 3/23/2020
 */
public class ShowInfoPresenter extends PresenterBase {

    public ShowInfoPresenter(IShowView iShowView) {
        this.mIModel = new ShowInfoModel();
        this.mViewReference = new WeakReference<IView>(iShowView);
    }

    public void showInfo(int userId) {
        if (mViewReference != null && mIModel != null && mViewReference.get() != null) {

            ((ShowInfoModel)mIModel).showInfo(userId, new Listener() {
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
