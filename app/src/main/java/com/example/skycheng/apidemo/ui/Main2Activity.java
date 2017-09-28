package com.example.skycheng.apidemo.ui;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.skycheng.apidemo.R;

import static com.example.skycheng.apidemo.R.id.map;

public class Main2Activity extends AppCompatActivity implements AMap.OnMyLocationChangeListener,RadioGroup.OnCheckedChangeListener{

    private MapView mMapView;
    private SeekBar mColorBar;
    private SeekBar mAlphaBar;
    private SeekBar mWidthBar;
    private AMap aMap;
    private static final int WIDTH_MAX = 50;
    private static final int HUE_MAX = 255;
    private static final int ALPHA_MAX = 255;
    private Circle circle;
    public double mWEI;
    public double mJING;
    private MyLocationStyle myLocationStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mMapView = (MapView) findViewById(map);
        mMapView.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mColorBar = (SeekBar) findViewById(R.id.hueSeekBar);
        mColorBar.setMax(HUE_MAX);
        mColorBar.setProgress(50);

        mAlphaBar = (SeekBar) findViewById(R.id.alphaSeekBar);
        mAlphaBar.setMax(ALPHA_MAX);
        mAlphaBar.setProgress(50);

        mWidthBar = (SeekBar) findViewById(R.id.widthSeekBar);
        mWidthBar.setMax(WIDTH_MAX);
        mWidthBar.setProgress(25);
        if (aMap == null) {
            aMap = mMapView.getMap();
            setUpMap();
        }
        //设置SDK 自带定位消息监听
        aMap.setOnMyLocationChangeListener(this);
    }

    private void setUpMap() {

//
//        aMap.moveCamera(CameraUpdateFactory
//                .newLatLngZoom(Constants.DONGGUAN, 18));// 设置指定的可视区域地图
//        // 绘制一个圆形
//        //Color.argb(50, 1, 1, 1)
//        //Log.d(TAG, "setUpMap: 2");
//        circle = aMap.addCircle(new CircleOptions().center(Constants.DONGGUAN)
//                .radius(100).strokeColor(Color.GREEN)
//                .fillColor(Color.argb(50, 1, 1, 1)).strokeWidth(0));
//        //Log.d(TAG, "setUpMap: 3");


        // 如果要设置定位的默认状态，可以在此处进行设置
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.location_marker));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色  。
        //myLocationStyle.isMyLocationShowing();

        aMap.setMyLocationStyle(myLocationStyle);

        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.gps_show_button:
                // 只定位，不进行其他操作
                aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW));
            case R.id.gps_locate_button:
                aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE));
                break;
            case R.id.gps_follow_button:
                // 设置定位的类型为 跟随模式
                aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW));
                break;
            case R.id.gps_follow_button_nocenter:
                // 设置定位的类型为 持续定位不移动到中心点
                aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER));
                break;
        }
    }

    @Override
    public void onMyLocationChange(Location location) {
        mWEI = location.getLatitude();
        mJING = location.getLongitude();
        LatLng latLng = new LatLng(mWEI, mJING);
        //Log.d(TAG, "onMyLocationChange: wei:" + mWEI + "+++" + mJING);
        // 定位回调监听
        if (location != null) {

            Log.e("amap", "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
            Bundle bundle = location.getExtras();
            if (bundle != null) {
                int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
                String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);



                aMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(latLng, 18));// 设置指定的可视区域地图
                // 绘制一个圆形
                //Color.argb(50, 1, 1, 1)
                //Log.d(TAG, "setUpMap: 2");
                circle = aMap.addCircle(new CircleOptions().center(latLng)
                        .radius(100).strokeColor(Color.GREEN)
                        .fillColor(Color.argb(50, 1, 1, 1)).strokeWidth(0));
                //Log.d(TAG, "setUpMap: 3");


                /*
                errorCode
                errorInfo
                locationType
                */
                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);

            } else {
                Log.e("amap", "定位信息， bundle is null ");

            }

        } else {
            Log.e("amap", "定位失败");
        }
    }
}
