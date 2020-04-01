package com.wenwu.pm.presenter;

import com.wenwu.pm.modle.IModel;
import com.wenwu.pm.view.IView;

import java.lang.ref.WeakReference;

/**
 * @author:wenwudeng
 * @date:3:30 PM 3/23/2020
 */
public class PresenterBase {
    protected IModel mIModel;
//引用
    protected WeakReference<IView> mViewReference;
}
