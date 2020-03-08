package com.wenwu.pm.mine.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenwu.pm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPInfoEditFragment extends Fragment {


    public MyPInfoEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_personal_info_edit, container, false);
    }

}
