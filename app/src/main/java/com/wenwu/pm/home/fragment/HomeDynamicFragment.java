package com.wenwu.pm.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.wenwu.pm.R;
import com.wenwu.pm.home.adapter.HomeRecyclerAdapter;
import com.wenwu.pm.home.bean.UserEditInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeDynamicFragment extends Fragment {
    //用于用户头像
    private List<UserEditInfo> userEditInfoList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_dynamic,container,false);
    }



    public void init() {
        for (int i = 0; i < 8; i++) {
            UserEditInfo userEditInfo = new UserEditInfo("抗战疫情", R.mipmap.pic3, "中国加油,武汉加油!", "梦想", R.mipmap.pic4,100);
            userEditInfoList.add(userEditInfo);
            UserEditInfo userEditInfo1 = new UserEditInfo("抗战疫情", R.mipmap.pic1, "中国加油,武汉加油!", "梦想", R.mipmap.pic2,100);
            userEditInfoList.add(userEditInfo1);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        HomeRecyclerAdapter userEditIRecyclerAdapter = new HomeRecyclerAdapter(userEditInfoList);
        recyclerView.setAdapter(userEditIRecyclerAdapter);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_dynamic);
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

}
