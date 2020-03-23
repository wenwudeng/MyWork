package com.wenwu.pm.presenter;

import com.wenwu.pm.modle.RegisterModel;
import com.wenwu.pm.modle.listener.Listener;
import com.wenwu.pm.view.IRegisterView;
import com.wenwu.pm.view.IView;

import java.lang.ref.WeakReference;

/**
 * @author:wenwudeng
 * @date:11:31 PM 3/23/2020
 * 注册
 */
public class RegisterPresenter extends PresenterBase {
    public RegisterPresenter(IRegisterView registerView) {
        this.mIModel = new RegisterModel();
        this.mViewReference = new WeakReference<IView>(registerView);
    }

    public void register() {
        if (mIModel != null && mViewReference != null && mViewReference.get() != null) {
            IRegisterView registerView = (IRegisterView) mViewReference.get();
            String account = registerView.getAccount();
            String password = registerView.getPassword();
            String verifyCode = registerView.getVerifyCode();
            ((RegisterModel)mIModel).register(account, password, verifyCode, new Listener() {
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
