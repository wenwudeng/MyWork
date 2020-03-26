package com.wenwu.pm.activity.publish.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.wenwu.pm.R;
import com.wenwu.pm.activity.publish.adapter.ArticleReviewAdapter;
import com.wenwu.pm.activity.publish.bean.ArticleReview;

import java.util.ArrayList;
import java.util.List;

public class ArticleShowActivity extends AppCompatActivity {
    private List<ArticleReview> reviewList = new ArrayList<>();;
    private RecyclerView recycler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_show);
        initView();
    }

    private void initView() {
        initData();
        recycler = findViewById(R.id.article_show_review);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recycler.setLayoutManager(layout);
        ArticleReviewAdapter adapter = new ArticleReviewAdapter(reviewList);
        recycler.setAdapter(adapter);
    }

    private void initData() {
        String content = "写得很详细，受教了。没有变动，乖乖呆在家里的就不用说明了。\n" +
                "再次强调下，疫情结束前暂停所有实习任务，请务必听从学校的安排，安全第一。";
        String content1 = "热爱生命，就是热爱自己，热爱他人。";
        for (int i = 0; i < 5; i++) {
            ArticleReview review1 = new ArticleReview(R.drawable.li, "留白", "一周前", 2, content);
            reviewList.add(review1);
            ArticleReview review2 = new ArticleReview(R.drawable.li, "留白", "一周前", 2, content1);
            reviewList.add(review2);
        }

    }
}
