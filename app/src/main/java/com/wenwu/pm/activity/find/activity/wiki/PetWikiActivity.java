package com.wenwu.pm.activity.find.activity.wiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.PermissionInterceptor;
import com.just.agentweb.WebChromeClient;
import com.wenwu.pm.R;

public class PetWikiActivity extends AppCompatActivity {
    private  AgentWeb agentWeb;
    private LinearLayout layout;
    private String[] permission = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_wiki);

        layout = findViewById(R.id.linear);

        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(layout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(Color.parseColor("#00000000"))
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(getUrl());
    }

    public void initView() {

    }

    private com.just.agentweb.WebViewClient mWebViewClient = new com.just.agentweb.WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Log.i("Info", "BaseWebActivity onPageStarted");
        }
    };
    private com.just.agentweb.WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

    };


    public String getUrl() {
        return "http://lingdang.m.goumin.com/category-all/1";
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (agentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("Info", "Info:" + requestCode + " onResult:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        agentWeb.getWebLifeCycle().onDestroy();
    }

    protected PermissionInterceptor mPermissionInterceptor = new PermissionInterceptor() {

        @Override
        public boolean intercept(String url, String[] permissions, String action) {
            return false;
        }
    };

}
