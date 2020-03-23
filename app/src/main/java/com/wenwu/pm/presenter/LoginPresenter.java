package com.wenwu.pm.presenter;

import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.modle.LoginModel;
import com.wenwu.pm.modle.listener.Listener;
import com.wenwu.pm.view.ILoginView;
import com.wenwu.pm.view.IView;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * @author:wenwudeng
 * @date:4:04 PM 3/23/2020
 * 登录
 */
public class LoginPresenter extends PresenterBase {

    public LoginPresenter(ILoginView loginView) {
        this.mIModel = new LoginModel();
        this.mViewReference = new WeakReference<IView>(loginView);
    }

    public void login() throws IOException {
        if (mViewReference != null && mIModel != null && mViewReference.get() != null) {
            ILoginView loginView = (ILoginView) mViewReference.get();
            String account = loginView.getAccount();
            String password = loginView.getPassword();
            ((LoginModel)mIModel).login(account, password, new Listener() {


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
