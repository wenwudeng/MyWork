package com.wenwu.pm.presenter;

import com.wenwu.pm.modle.ArticleModel;
import com.wenwu.pm.modle.listener.Listener;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.view.IArticleView;
import com.wenwu.pm.view.IView;

import java.lang.ref.WeakReference;

/**
 * @author:wenwudeng
 * @date:17:18 2020/4/1
 */
public class ArticlePresenter extends PresenterBase {
    public ArticlePresenter(IArticleView articleView) {
        this.mIModel = new ArticleModel();
        this.mViewReference = new WeakReference<IView>(articleView);
    }

    public void publish() {
        if (mViewReference != null && mIModel != null && mViewReference.get() != null) {
            IArticleView articleView  = (IArticleView) mViewReference.get();
            int userId = JsonUtil.loginJson.getData().getId();
            String title = articleView.getArtTitle();
            String content = articleView.getArtContent();
            String imgUrl = articleView.getImgUrl();
            String location = articleView.getArtLocation();

            ((ArticleModel)mIModel).publishArticle(userId, title, content, imgUrl, location, new Listener() {

                @Override
                public void onSuccess(Object json) {
                    System.out.println("=========成功========");
                    if (mViewReference.get() != null) {
                        mViewReference.get().onViewSuccess(json);
                    }
                }

                @Override
                public void onFail(Object json) {
                    System.out.println("=========失败=====");
                    if (mViewReference.get() != null) {
                        mViewReference.get().onViewFail(json);
                    }
                }
            });
        }
    }
}
