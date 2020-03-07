package com.wenwu.pm.message.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenwu.pm.R;

/**
 * A simple {@link Fragment} subclass.
 * 消息-评论和@列表
 */
public class MsgReviewFragment extends Fragment {


    public MsgReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.msg_review, container, false);
    }

}
