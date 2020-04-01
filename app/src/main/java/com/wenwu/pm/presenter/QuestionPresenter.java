package com.wenwu.pm.presenter;

import com.wenwu.pm.modle.QuestionModel;
import com.wenwu.pm.modle.listener.Listener;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.view.IQuestionView;
import com.wenwu.pm.view.IView;

import java.lang.ref.WeakReference;

/**
 * @author:wenwudeng
 * @date:16:17 2020/4/1
 */
public class QuestionPresenter extends PresenterBase {
    public QuestionPresenter(IQuestionView questionView) {
        this.mIModel = new QuestionModel();
        this.mViewReference = new WeakReference<IView>(questionView);
    }

    public void publish() {
        if (mViewReference != null && mIModel != null && mViewReference.get() != null) {
            IQuestionView questionView = (IQuestionView) mViewReference.get();
            int userId = JsonUtil.userId;
            String title = questionView.getQuesTitle();
            String content = questionView.getContent();
            String imgUrl = questionView.getImageUrl();
            String location = questionView.getLocation();
            ((QuestionModel)mIModel).question(userId, title, content, imgUrl, location, new Listener() {
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
