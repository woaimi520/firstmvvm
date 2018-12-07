package com.renyu.administrator.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.TimeZoneFormat;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.net.ContentHandler;
import java.util.List;

import retrofit.http.GET;

/**
 * Created by Administrator on 2018/12/6.
 */

public class GetProvider {


    LocationManager locationManager;
    double latitude;
    double longitude;
    Location location;

    public GetProvider() {
        try {
            this.locationManager = (LocationManager) MyApplication.getAppContext().getSystemService(Context.LOCATION_SERVICE);
        }catch (Exception e)
        {
            Log.i("exception", "erroe");
        }
    }


    public String getLocalCity() {
        String provider;
        List<String> list = locationManager.getProviders(true);

        if (list.contains(LocationManager.GPS_PROVIDER)) {
            provider = getGpsProvide();
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = getNetProvide();
        } else {
           // Toast.makeText(MyApplication.getAppContext(), "请检查网络或GPS是否打开",
           //         Toast.LENGTH_LONG).show();
            return "chongqing";
        }
        if (ActivityCompat.checkSelfPermission(MyApplication.getAppContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyApplication.getAppContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "chongqing";
        }
         location = locationManager.getLastKnownLocation(provider);
        if (location != null) {

            latitude = location.getLatitude();
            longitude = location.getLongitude();


            return "经度:" + latitude + " 纬度:" + longitude;

        }else {
            while(location  == null)
            {
                locationManager.requestLocationUpdates("gps", 1000, 1, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location locationf) {
                     
                            latitude = locationf.getLatitude();
                            longitude = locationf.getLongitude();

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {


                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });
            }
        }
        return "经度:" + latitude + " 纬度:" + longitude;  }

    private String getGpsProvide() {
        return  LocationManager.GPS_PROVIDER;
    }

    private String getNetProvide() {
        return LocationManager.NETWORK_PROVIDER;
    }

}
