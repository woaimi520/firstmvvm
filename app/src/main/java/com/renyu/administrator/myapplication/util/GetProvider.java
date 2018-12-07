package com.renyu.administrator.myapplication.util;


import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.renyu.administrator.myapplication.MyApplication;

public class GetProvider implements AMapLocationListener {
    public AMapLocationClient mMapLocationClient;
    public AMapLocationClientOption mMapLocationClientOption;
    Context context = MyApplication.getAppContext();

    public GetProvider() {
        mMapLocationClient = new AMapLocationClient(context);
        //初始化定位参数
        mMapLocationClientOption = new AMapLocationClientOption();

    }


    public String getLocalCity() {
        //高精度模式
        mMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置单次获取
        mMapLocationClientOption.setOnceLocationLatest(true);
        //设置返回三次中精度最高的一次
        mMapLocationClientOption.setOnceLocationLatest(true);
        //设置定位超时时间 30S
        mMapLocationClientOption.setHttpTimeOut(30000);

        //设置定位回调监听
        mMapLocationClient.setLocationListener(this);

        //启动定位
        mMapLocationClient.setLocationOption(mMapLocationClientOption);
        mMapLocationClient.startLocation();

        return "wait";


    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        //接收返回的定位结果
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
             //可在其中解析amapLocation获取相应内容。


                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
                aMapLocation.getAoiName();//获取当前定位点的AOI信息
                aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                aMapLocation.getFloor();//获取当前室内定位的楼层
                aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态编码

                mMapLocationClient.stopLocation();
            }else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError","location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }

    }
}