package com.wenwu.pm.activity.find.activity.hospital;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.ajguan.library.EasyRefreshLayout;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;


import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.wenwu.pm.R;

import com.wenwu.pm.utils.OkHttpUtil;
import com.wenwu.pm.utils.Utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HospitalActivity extends NavMapActivity {
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption option = null;

    private EasyRefreshLayout refresh;
    private Toolbar toolbar;
    private  RecyclerView  recycler;
    private HospitalAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        initView();
        initLocation();
    }

    public void initView() {
        recycler = findViewById(R.id.recycler_location);
        refresh = findViewById(R.id.swipe_refresh_location);
        refresh.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final List<String> list = new ArrayList<>();
                        for (int j = 0; j < 5; j++) {
                            list.add("this is  new load data >>>>" + new Date().toLocaleString());
                        }

                        //adapter.addData(list);

                        refresh.loadMoreComplete(new EasyRefreshLayout.Event() {
                            @Override
                            public void complete() {
                             //   adapter.getData().addAll(list);
                                adapter.notifyDataSetChanged();

                            }
                        }, 500);

                    }
                }, 2000);


            }

            @Override
            public void onRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            list.add("this is refresh data >>>" + new Date().toLocaleString());
                        }
                        adapter.setNewData(null);
                        refresh.refreshComplete();
                        Toast.makeText(getApplicationContext(), "refresh success", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }
        });

        toolbar = findViewById(R.id.location_toolbar);
        toolbar.setTitle("附近宠物医院");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void getPoi(String location) {

        OkHttpUtil.sendGetRequest(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<PetHospitalModel> models = new ArrayList<>();
                String responseData = response.body().string();
                System.out.println(responseData);

                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray data =  jsonObject.getJSONArray("pois");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject object = data.getJSONObject(i);
                        PetHospitalModel model = new PetHospitalModel();
                        model.setStoreName(object.getString("name"));
                        model.setAddress(Utils.addressFilter(object.getString("address")));
                        model.setDistance(Utils.distanceParse(object.getString("distance")));
                        model.setLocation(object.getString("location"));
                        model.settel(Utils.phone(object.getString("tel")));
                        models.add(model);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LinearLayoutManager manager = new LinearLayoutManager(HospitalActivity.this);
                        recycler.setLayoutManager(manager);
                        adapter = new HospitalAdapter(R.layout.activity_hospital_item,models,new HospitalActivity());
                        recycler.setAdapter(adapter);
                    }
                });

                while (adapter ==null);

                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        PetHospitalModel model = (PetHospitalModel) adapter.getData().get(position);
                        switch (view.getId()) {
                            case R.id.location_call:
                                Toast.makeText(HospitalActivity.this,model.gettel(),Toast.LENGTH_SHORT).show();
                                call(model.gettel());
                                break;

                            case R.id.location_route:
                                route(model.getLocation(),model.getStoreName());
                                break;
                        }

                    }
                });

            }
        });
    }

    /*导航*/
    public void route(String StoreLocation,String storeName) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(HospitalActivity.this);
        dialog.setMessage("即将为你导航至："+storeName);
        dialog.setCancelable(true);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String la = StoreLocation.substring(0, StoreLocation.indexOf(","));//经度
                String lo = StoreLocation.substring(StoreLocation.indexOf(",")+1);//纬度
                double a = Double.parseDouble(la);
                double b =  Double.parseDouble(lo);
                LatLng end = new LatLng(b,a);
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), new AmapNaviParams(null, null, new Poi(storeName, end, ""),
                        AmapNaviType.DRIVER), HospitalActivity.this);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();

    }


    public  void call(String phone) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(HospitalActivity.this);
        dialog.setMessage("你确定要拨打：" + phone + "吗?");
        dialog.setCancelable(true);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }

    public void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(init());

        //初始化AMapLocationClientOption对象
        option = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //获取最近3s内精度最高的一次定位结果：
        option.setOnceLocationLatest(true);
        option.setHttpTimeOut(20000);//超时

        //设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if(null !=  mLocationClient){
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(option);
        //启动定位
        mLocationClient.startLocation();

    }

    public AMapLocationListener init() {
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {

                if (aMapLocation != null
                        && aMapLocation.getErrorCode() == 0) {
                    //  mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    final double lo = aMapLocation.getLongitude();//获取经度
                    final double la = aMapLocation.getLatitude();//获取纬度
                    aMapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    getPoi(lo+","+la);

                    Log.d("位置", "经度" + lo + " 纬度" + la);
                    df.format(date);//定位时间
                } else {
                    String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                    Toast.makeText(HospitalActivity.this,errText,Toast.LENGTH_SHORT).show();
                    Log.e("AmapErr", errText);
                }

                if (mLocationClient.isStarted()) {
                    mLocationClient.stopLocation();
                }
            }
        };
        return mLocationListener;
    }


}
