package com.example.skycheng.apidemo.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.example.skycheng.apidemo.Constants;
import com.example.skycheng.apidemo.LuckeyDialog;
import com.example.skycheng.apidemo.OpenSuccess;
import com.example.skycheng.apidemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.amap.api.mapcore.util.db.v;
import static com.example.skycheng.apidemo.R.id.map;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener
        , AMap.OnMyLocationChangeListener, RadioGroup.OnCheckedChangeListener, AMap.OnMarkerClickListener {


    private static final String TAG = "MainActivity";
    private static final double EARTH_RADIUS = 6378137.0;
    private MapView mMapView;
    private SeekBar mColorBar;
    private SeekBar mAlphaBar;
    private SeekBar mWidthBar;

    private static final int WIDTH_MAX = 50;
    private static final int HUE_MAX = 255;
    private static final int ALPHA_MAX = 255;
    private AMap aMap;
    private Circle circle;
    private RadioGroup mGPSModeGroup;
    private MyLocationStyle myLocationStyle;
    private double mWEI;
    private double mJING;
    private Location mLocation;
    private HashMap positionEneityList;
    private MarkerOptions markerOption;
    private LatLng latlng = new LatLng(mWEI, mJING);
    private Marker marker2;
    private TextView markerText;
    private Intent mContext;
    private final float R1= (float) 200.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mMapView = (MapView) findViewById(map);
        mMapView.onCreate(savedInstanceState);
        init();

        //AMap map = mMapView.getMap();
        // map.setTrafficEnabled(true);
        //加入红包功能
        addHongBao();

    }

    private void addHongBao() {
        //1北京
        //文字显示标注，可以设置显示内容，位置，字体大小颜色，背景色旋转角度,Z值等
       /* TextOptions textOptions = new TextOptions().position(Constants.BEIJING)
                .text("Text").fontColor(Color.BLACK)
                .backgroundColor(Color.BLUE).fontSize(30).rotate(20).align(Text.ALIGN_CENTER_HORIZONTAL,
                        Text.ALIGN_CENTER_VERTICAL)
                .zIndex(1.f).typeface(Typeface.DEFAULT_BOLD);
        aMap.addText(textOptions);*/

        //2成都
       /* aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(Constants.CHENGDU).title("成都市")
                .snippet("成都市:30.679879, 104.064855").draggable(true));*/
        //东莞
        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(new LatLng(22.9468146495, 113.8909184933)).title("东莞市")
                .snippet("东莞市:松山湖").draggable(true));
       // LatLng latLng=new LatLng(lat,lon);
        //lat: 22.947299 lon: 113.890437 定位坐标


            //随机地点
            Random random = new Random(1);
            double r = random.nextDouble()*0.001;
            double lat = 22.947299 + r;
            double lon = 113.890437 + r;
            LatLng latLng1=new LatLng(lat,lon);
            Log.e(TAG, "addHongBao: "+r );

            markerOption = new MarkerOptions();
            markerOption.position(latLng1);
            markerOption.title("沙县小吃").snippet("剩余5个红包");

            markerOption.draggable(true);
            markerOption.icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.xiao));
            marker2 = aMap.addMarker(markerOption);

      //  Log.e(TAG, "addHongBao: len"+list.size() );

        //3西安
        markerOption = new MarkerOptions();
        markerOption.position(Constants.XIAN);
        markerOption.title("西安市").snippet("西安市：34.341568, 108.940174");
        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory
                .fromResource(R.drawable.xiao));
        marker2 = aMap.addMarker(markerOption);
        marker2.showInfoWindow();
        // marker旋转90度
        marker2.setRotateAngle(90);

        //3沙县
        markerOption = new MarkerOptions();
        markerOption.position(Constants.SHAXIAN);
        markerOption.title("兰州拉面").snippet("剩余10个红包");
        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory
                .fromResource(R.drawable.xiao));
        marker2 = aMap.addMarker(markerOption);
        //marker2.showInfoWindow();
        //marker2.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bao));
        // marker旋转90度
        //marker2.setRotateAngle(90);
        //33沙县
        markerOption = new MarkerOptions();
        markerOption.position(Constants.SHAXIAN1);
        markerOption.title("沙县小吃").snippet("剩余5个红包");

        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory
                .fromResource(R.drawable.xiao));
        marker2 = aMap.addMarker(markerOption);

        //添加地图上红包的点击事件
        aMap.setOnMarkerClickListener(this);
        /*mMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,HongActivity.class);
                startActivity(intent);
            }
        });*/
        //marker2.showInfoWindow();
        //marker2.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bao));
        // marker旋转90度
        //marker2.setRotateAngle(90);

        // 动画效果
        //4郑州
        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_RED));
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(Constants.ZHENGZHOU).title("郑州市").icons(giflist)
                .draggable(true).period(10));

        //drawMarkers();// 添加10个带有系统默认icon的marker
    }

    public void drawMarkers() {
        Marker marker = aMap.addMarker(new MarkerOptions()
                .position(latlng)
                .title("好好学习")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .draggable(true));
        marker.showInfoWindow();// 设置默认显示一个infowinfow
    }


    /**
     * 初始化AMap对象
     */
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
        mGPSModeGroup = (RadioGroup) findViewById(R.id.gps_radio_group);
        mGPSModeGroup.setOnCheckedChangeListener(this);

        //设置SDK 自带定位消息监听
        aMap.setOnMyLocationChangeListener(this);
    }


    /**
     * 方法必须重写
     */

    private void setUpMap() {

//
        // 如果要设置定位的默认状态，可以在此处进行设置
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.location_marker));
        aMap.setMyLocationStyle(myLocationStyle);
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色  。
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

        //circleAndLocation();
//        Log.d(TAG, "setUpMap: 1");
//        aMap.moveCamera(CameraUpdateFactory
//                .newLatLngZoom(new LatLng(mWEI, mJING), 18));// 设置指定的可视区域地图
//        // 绘制一个圆形
//        //Color.argb(50, 1, 1, 1)
//        Log.d(TAG, "setUpMap: 2");
//        circle = aMap.addCircle(new CircleOptions().center(new LatLng(mWEI, mJING))
//                .radius(4000).strokeColor(Color.GREEN)
//                .fillColor(Color.argb(50, 1, 1, 1)).strokeWidth(0));
//        Log.d(TAG, "setUpMap: 3");
//        aMap.invalidate();
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

    /**
     * Circle中对填充颜色，透明度，画笔宽度设置响应事件
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (circle == null) {
            return;
        }
        if (seekBar == mColorBar) {
            circle.setFillColor(Color.argb(progress, 1, 1, 1));
        } else if (seekBar == mAlphaBar) {
            circle.setStrokeColor(Color.argb(progress, 1, 1, 1));
        } else if (seekBar == mWidthBar) {
            circle.setStrokeWidth(progress);
        }
        aMap.invalidate();// 刷新地图
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onMyLocationChange(Location location) {
        mLocation = location;
        // mWEI = location.getLatitude();
        // mJING = location.getLongitude();

        //LatLng latLng = new LatLng(mWEI, mJING);
        Log.d(TAG, "onMyLocationChange: wei:" + mWEI + "+++" + mJING);
        // 定位回调监听
        if (location != null) {

            Log.e("amap", "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
            Bundle bundle = location.getExtras();
            if (bundle != null) {
                int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
                String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);

                /*
                errorCode
                errorInfo
                locationType
                */
                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
                // circleAndLocation();
                mJING = mLocation.getLongitude();
                mWEI = mLocation.getLatitude();

                //限定周围范围
                circleAndLocation();
                //float v = mLocation.distanceTo(location);

                Log.e(TAG, "onMyLocationChange1: " + v);

            } else {
                Log.e("amap", "定位信息， bundle is null ");

            }

        } else {
            Log.e("amap", "定位失败");
        }
    }

    //
    private void circleAndLocation() {
        aMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(mWEI, mJING), 17));// 设置指定的可视区域地图
        circle = aMap.addCircle(new CircleOptions().center(new LatLng(mWEI, mJING))
                .radius(200).strokeColor(Color.RED)
                .fillColor(Color.argb(50, 1, 1, 1)).strokeWidth(0));

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
    public boolean onMarkerClick(Marker marker) {
//        Location location=new Location("沙县小吃");
//        location.setLatitude(22.9471703230);
//        location.setLongitude(113.8910579681);
//        float v1 = mLocation.distanceTo(location);
//        Log.e(TAG, "onMarkerClick: 经纬度距离"+ v1);
//SHAXIAN1 = new LatLng(22.9491703230,113.8910579681)


//        double Lat1 = rad(22.9471703230);
//        double Lat2 = rad(mWEI);
//        double a = Lat1 - Lat2;
//        double b = rad(113.8910579681) - rad(mJING);
//        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
//                + Math.cos(Lat1) * Math.cos(Lat2)
//                * Math.pow(Math.sin(b / 2), 2)));
//        s = s * EARTH_RADIUS;
//        //s为2个点之间的距离
//        s = Math.round(s * 10000) / 10000;
//        Log.e(TAG, "onMarkerClick: 距离s=" + s + "米");

        //计算2个经纬度之间的距离,精准度更高
        LatLng latlng2 = new LatLng(22.9471703230, 113.8910579681);
        LatLng latlng1 = new LatLng(mWEI, mJING);
        float calculateLineDistance = AMapUtils.calculateLineDistance(latlng1, latlng2);

        Log.e(TAG, "22.9471703230,113.8910579681:= " + calculateLineDistance + "米");

        //判断是否在范围内,在则弹出红包
        if (calculateLineDistance <= R1) {
            // Log.d(TAG, "onMarkerClick: " + marker);


            //弹出比例红包
            LuckeyDialog.Builder builder = new LuckeyDialog.Builder(MainActivity.this, R.style.Dialog);//调用style中的Diaog
            builder.setName("系统");
            builder.setOpenButton("", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    //TODO  需要判断 红包是否领取成功,每个账号一天只能另一个
                    Intent intent = new Intent(MainActivity.this, OpenSuccess.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });

            builder.setCloseButton("", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });
            Dialog dialog = builder.create();
            Window dialogWindow = dialog.getWindow();


            WindowManager m = getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            p.height = (int) (d.getHeight() * 0.7); // 高度设置为屏幕的0.6
            p.width = (int) (d.getWidth() * 0.75); // 宽度设置为屏幕的0.65
            dialogWindow.setAttributes(p);

            dialog.show();

//            Intent intent = new Intent(this, HongActivity.class);
//            startActivity(intent);

           // markerText.setText("你点击的是" + marker.getTitle());

            //true表示消费该事件,可以点击
            return true;
            //表示超出范围
        } else {
            Toast.makeText(this, "距离商家太远,请靠近试试", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

}

