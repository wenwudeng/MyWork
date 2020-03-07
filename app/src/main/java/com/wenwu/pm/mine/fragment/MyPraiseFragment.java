package com.wenwu.pm.mine.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenwu.pm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPraiseFragment extends Fragment {


    public MyPraiseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.my_praise, container, false);
    }

}
