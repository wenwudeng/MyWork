package com.wenwu.pm.message.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenwu.pm.R;

/**
 * A simple {@link Fragment} subclass.
 * 消息功能-赞和收藏列表
 */
public class MsgCollectPraiseFragment extends Fragment {


    public MsgCollectPraiseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.msg_collect_praise, container, false);
    }

}
