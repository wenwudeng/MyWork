package com.wenwu.pm.activity.find.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.wenwu.pm.R;
import com.wenwu.pm.activity.find.adapter.FHelpRecyclerAdapter;
import com.wenwu.pm.activity.find.bean.FindHelpPetShow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wenwudeng
 * @date:10:20 PM 2/14/2020
 */
public class FindHelpPetFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<FindHelpPetShow> list = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_helppet,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_helPet);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(layoutManager);

        FHelpRecyclerAdapter helpRecyclerAdapter = new FHelpRecyclerAdapter(list);

        recyclerView.setAdapter(helpRecyclerAdapter);


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_helpPet);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(),"刷新完成", Toast.LENGTH_SHORT).show();
                    }
                },200);
            }
        });
    }

    private void init() {
        for (int i = 0; i < 20; i++) {
            FindHelpPetShow petShow = new FindHelpPetShow(R.drawable.img, "中国加油,武汉加油!", "中国人民万众一心,我们一定会战胜此次疫情", "1周前", 15);
            list.add(petShow);
        }
    }
}
