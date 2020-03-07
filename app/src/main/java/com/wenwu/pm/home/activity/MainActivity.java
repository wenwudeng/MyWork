package com.wenwu.pm.home.activity;

import android.os.Bundle;

import com.wenwu.pm.R;


public class MainActivity extends NavigationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavigationBar().setAddViewLayout(createNaviBarView());

    }


}
