package com.wenwu.pm.home.activity;

import android.os.Bundle;

import com.wenwu.pm.R;


public class MainActivity extends NavigationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavigationBar().setAddViewLayout(createNaviBarView());

    }


}
