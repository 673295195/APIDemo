package com.example.skycheng.apidemo;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.amap.api.mapcore.util.db.v;
import static com.example.skycheng.apidemo.R.id.map;


public class LocationAndPacket extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener
        , AMap.OnMyLocationChangeListener, RadioGroup.OnCheckedChangeListener, AMap.OnMarkerClickListener,
        AMap.OnMapClickListener, AMap.OnMapLongClickListener {


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
    private final float R1 = (float) 200.0;
    private SellerBean mSeller;
    private double mLat;
    private double mLon;
    private double mPacket;
    private LatLng mLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapView = (MapView) findViewById(map);
        mMapView.onCreate(savedInstanceState);
        //初始化地图
        init();

        initIntent();

        //加入红包功能
        addHongBao();

    }

    //传过来的商家数据
    private void initIntent() {
        if (getIntent() != null) {
            Intent intent = getIntent();
            intent.getStringExtra("name");
            intent.getStringExtra("ID");
            mLat = intent.getDoubleExtra("lat", 22.948299);
            mLon = intent.getDoubleExtra("lon", 113.891437);
            // mLon = intent.getStringExtra("lon");
            mPacket = intent.getDoubleExtra("Packet", 1.0);
            // Bundle bundle = intent.getExtras();
            //mSeller = (SellerBean) bundle.getSerializable("Seller");


        }
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
                .snippet("松山湖").draggable(true));
        // LatLng latLng=new LatLng(lat,lon);
        //lat: 22.947299 lon: 113.890437 定位坐标


        //随机地点
        Random random = new Random(1);
        double r = random.nextDouble() * 0.001;
        double lat = 22.947299 + r;
        double lon = 113.890437 + r;
        LatLng latLng1 = new LatLng(lat, lon);
        Log.e(TAG, "addHongBao: " + r);

        markerOption = new MarkerOptions();
        markerOption.position(latLng1);
        markerOption.title("沙县小吃").snippet("剩余5个红包");

        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory
                .fromResource(R.drawable.redred));
        marker2 = aMap.addMarker(markerOption);

        //  Log.e(TAG, "addHongBao: len"+list.size() );

        //3西安
        markerOption = new MarkerOptions();
        markerOption.position(Constants.XIAN);
        markerOption.title("西安市").snippet("坐标：34.341568, 108.940174");
        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory
                .fromResource(R.drawable.redred));
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
                .fromResource(R.drawable.redred));
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
                .fromResource(R.drawable.redred));
        marker2 = aMap.addMarker(markerOption);

        //商家

        markerOption = new MarkerOptions();
        markerOption.position(new LatLng(mLat, mLon));
        markerOption.title("沙县小吃").snippet("剩余5个红包");

        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory
                .fromResource(R.drawable.redred));
        marker2 = aMap.addMarker(markerOption);


        //添加地图上红包的点击事件
        aMap.setOnMarkerClickListener(this);

        aMap.setOnMapClickListener(this);// 对amap添加单击地图事件监听器
        // aMap.setOnMapLongClickListener(this);// 对amap添加长按地图事件监听器,暂时不用



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
    /*    mColorBar = (SeekBar) findViewById(R.id.hueSeekBar);
        mColorBar.setMax(HUE_MAX);
        mColorBar.setProgress(50);

        mAlphaBar = (SeekBar) findViewById(R.id.alphaSeekBar);
        mAlphaBar.setMax(ALPHA_MAX);
        mAlphaBar.setProgress(50);

        mWidthBar = (SeekBar) findViewById(R.id.widthSeekBar);
        mWidthBar.setMax(WIDTH_MAX);
        mWidthBar.setProgress(25);*/
        if (aMap == null) {
            aMap = mMapView.getMap();
            setUpMap();
        }
      /*  mGPSModeGroup = (RadioGroup) findViewById(R.id.gps_radio_group);
        mGPSModeGroup.setOnCheckedChangeListener(this);*/

        //设置SDK 自带定位消息监听
        aMap.setOnMyLocationChangeListener(this);


    }

    /**
     * 方法必须重写
     */
    private void setUpMap() {

        // 如果要设置定位的默认状态，可以在此处进行设置
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.location_marker));
        aMap.setMyLocationStyle(myLocationStyle);
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色  。
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
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
    public void onMyLocationChange(Location location) {  //位置改变是调用
        mLocation = location;

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
    public boolean onMarkerClick(Marker marker) {   //marker点击事件

        //计算2个经纬度之间的距离,精准度更高
        double longitude = marker.getPosition().longitude;
        double latitude = marker.getPosition().latitude;

        LatLng latlng2 = new LatLng(22.9471703230, 113.8910579681);
        LatLng latlng3 = new LatLng(latitude, longitude);
        LatLng latlng1 = new LatLng(mWEI, mJING);

        LogUtils.log("onMarkerClick: 定位" + mWEI + "==" + mJING);
        //Log.e(TAG, "onMarkerClick: 定位" + mWEI + "==" + mJING);
        float calculateLineDistance = AMapUtils.calculateLineDistance(latlng1, latlng3);

        LogUtils.log("2点距离marker:= " + calculateLineDistance + "米");
        //Log.e(TAG, "2点距离marker:= " + calculateLineDistance + "米");

        //判断是否在范围内,在则弹出红包
        if (calculateLineDistance <= R1 && calculateLineDistance > 0) {
            // Log.d(TAG, "onMarkerClick: " + marker);

            //弹出比例红包
            LuckeyDialog.Builder builder = new LuckeyDialog.Builder(LocationAndPacket.this, R.style.Dialog);//调用style中的Diaog
            builder.setName("系统");
            builder.setOpenButton("", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    //TODO  需要判断 红包是否领取成功,每个账号一天只能另一个
                    Intent intent = new Intent(LocationAndPacket.this, OpenSuccess.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Seller", mPacket);
                    intent.putExtras(bundle);
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

            //true表示消费该事件,可以点击
            //TODO
            return true;
            //表示超出范围200米
        } else if (calculateLineDistance > 200) {
            Toast.makeText(this, "距离商家太远,请靠近试试", Toast.LENGTH_SHORT).show();
            return false;
            //距离等于0
        } else {
            return false;
        }

    }

    //TODO 点击地图,显示点击坐标
    @Override
    public void onMapClick(LatLng latLng) {

        mLatLng = latLng;
        double latitude = mLatLng.latitude;
        double longitude = mLatLng.longitude;

        LogUtils.log("onMapClick: 点击坐标" + latitude + "==" + longitude);
       // Log.e(TAG, "onMapClick: 点击坐标" + latitude + "==" + longitude);

        LatLng latlng1 = new LatLng(mWEI, mJING);

        //LogUtils.log("onMarkerClick: 定位" + mWEI + "==" + mJING);
        //Log.e(TAG, "onMarkerClick: 定位" + mWEI + "==" + mJING);
        float calculateLineDistance = AMapUtils.calculateLineDistance(latlng1, mLatLng);

        LogUtils.log("2个marker间距离="+calculateLineDistance+"米");
        //Log.e(TAG, "2点距离marker:= " + calculateLineDistance + "米");
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }
}

