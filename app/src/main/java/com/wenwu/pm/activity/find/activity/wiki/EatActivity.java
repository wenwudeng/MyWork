package com.wenwu.pm.activity.find.activity.wiki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wenwu.pm.activity.find.activity.wiki.PetWikiActivity;


public class EatActivity extends PetWikiActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public String getUrl() {
        return "http://m.goumin.com/food/home.html";
    }

}
