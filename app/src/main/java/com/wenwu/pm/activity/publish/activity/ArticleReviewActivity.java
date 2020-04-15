package com.wenwu.pm.activity.publish.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.LogTime;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.wenwu.pm.R;
import com.wenwu.pm.activity.publish.adapter.CommentExpandAdapter;
import com.wenwu.pm.activity.publish.bean.CommentBean;
import com.wenwu.pm.activity.publish.bean.CommentDetailBean;
import com.wenwu.pm.activity.publish.bean.ReplyDetailBean;
import com.wenwu.pm.activity.publish.view.CommentExpandableListView;
import com.wenwu.pm.goson.LRReturnJson;
import com.wenwu.pm.utils.JsonUtil;
import com.wenwu.pm.utils.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedTransferQueue;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ArticleReviewActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private TextView bt_comment;

    private ImageView article_page_img;
    private CircleImageView article_page_user_photo;
    private TextView article_user_name;
   private TextView article_comment_count;
    private TextView article_page_content;
    private ImageButton article_page_share;
    private Button article_page_concern;

    private CommentExpandableListView expandableListView;
    private CommentExpandAdapter adapter;
    private CommentBean commentBean;
    private List<CommentDetailBean> commentsList;
    private BottomSheetDialog dialog;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_review);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        expandableListView = findViewById(R.id.detail_page_lv_comment);
        bt_comment = findViewById(R.id.detail_page_do_comment);
        bt_comment.setOnClickListener(this);
        article_page_img = findViewById(R.id.article_page_image);
        article_page_user_photo = findViewById(R.id.article_page_user_photo);
        article_user_name = findViewById(R.id.article_page_user_name);
        article_page_content = findViewById(R.id.article_page_content);
        article_page_share = findViewById(R.id.article_pager_share);
        article_page_concern = findViewById(R.id.article_page_concern);
        article_comment_count = findViewById(R.id.article_page_comment_count);//评论数
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       CollapsingToolbarLayout collapsingToolbar =
               findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(JsonUtil.bean.getTitle());//文章标题

        commentsList = generateTestData();

        initExpandableListView(commentsList);

        initArticle();
    }
    /*初始化文章内容*/
   public void initArticle() {
       Glide.with(this).load(JsonUtil.bean.getImgUrl()).into(article_page_img);
       Glide.with(this).load(JsonUtil.bean.getUserPhoto()).into(article_page_user_photo);
       if (JsonUtil.loginJson.getData().getUserName().equals(JsonUtil.bean.getUserName())) {
           article_page_concern.setVisibility(View.INVISIBLE);
       }
       article_comment_count.setText(Integer.toString(total));
       article_user_name.setText(JsonUtil.bean.getUserName());
       article_page_content.setText(JsonUtil.bean.getContent());
    }

    /**
     * by moos on 2018/04/20
     * func:生成数据
     * @return 评论数据
     */
    private List<CommentDetailBean> generateTestData(){
        while (JsonUtil.commentJson==null);
        commentBean = new Gson().fromJson(JsonUtil.commentJson, CommentBean.class);
        List<CommentDetailBean> commentList = commentBean.getData().getList();
        total = commentBean.getData().getTotal();
        return commentList;
    }

    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<CommentDetailBean> commentList){
        expandableListView.setGroupIndicator(null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter(this, commentList);
        expandableListView.setAdapter(adapter);
        for(int i = 0; i<commentList.size(); i++){
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
                Log.e(TAG, "onGroupClick: 当前的评论id>>>"+commentList.get(groupPosition).getId());

                showReplyDialog(groupPosition);
                return true;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(ArticleReviewActivity.this,"点击了回复",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //toast("展开第"+groupPosition+"个分组");

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.detail_page_do_comment){

            showCommentDialog();
        }
    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.article_review_comment_dialog_layout,null);
        final EditText commentText = commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){

                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    CommentDetailBean detailBean = new CommentDetailBean(JsonUtil.loginJson.getData().getPhoto(),JsonUtil.loginJson.getData().getUserName(), commentContent,"刚刚");
                    adapter.addTheCommentData(detailBean);

                    //评论
                    Map<String, Object> params = new HashMap<>();
                    params.put("userid", JsonUtil.loginJson.getData().getId());
                    System.out.println(JsonUtil.bean.getArticleId());
                    params.put("articleId", JsonUtil.bean.getArticleId());
                    params.put("content", commentContent);
                    OkHttpUtil.sendPostRequest("comment/add", params, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String data = response.body().string();
                            LRReturnJson json = new Gson().fromJson(data, LRReturnJson.class);
                            if (json.getCode().equals("3009")) {
                                Looper.prepare();
                                Toast.makeText(ArticleReviewActivity.this,json.getMsg(),Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }else {
                                Looper.prepare();
                                Toast.makeText(ArticleReviewActivity.this,"评论失败",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    });

                }else {
                    Toast.makeText(ArticleReviewActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                   bt_comment.setBackgroundColor(getColor(R.color.red));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private void showReplyDialog(final int position){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.article_review_comment_dialog_layout,null);
        final EditText commentText =  commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + commentsList.get(position).getNickName() + " 的评论:");
        dialog.setContentView(commentView);
        //回复框
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(replyContent)){

                    dialog.dismiss();
                    ReplyDetailBean detailBean = new ReplyDetailBean(JsonUtil.loginJson.getData().getUserName(),replyContent);
                    adapter.addTheReplyData(detailBean, position);
                    expandableListView.expandGroup(position);

                    //回复评论
                    Map<String, Object> params = new HashMap<>();
                    params.put("userid", JsonUtil.loginJson.getData().getId());
                    params.put("commentId", JsonUtil.commentId);
                    params.put("content", replyContent);
                    OkHttpUtil.sendPostRequest("comment/reply", params, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String data = response.body().string();
                            LRReturnJson json = new Gson().fromJson(data, LRReturnJson.class);
                            if (json.getMsg().equals("3009")) {
                                Looper.prepare();
                                Toast.makeText(ArticleReviewActivity.this, json.getMsg(), Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }

                        }
                    });


                }else {
                    Toast.makeText(ArticleReviewActivity.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(getColor(R.color.red));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

}
