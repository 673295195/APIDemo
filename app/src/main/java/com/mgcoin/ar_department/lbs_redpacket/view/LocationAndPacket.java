package com.mgcoin.ar_department.lbs_redpacket.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.mgcoin.ar_department.apidemo.R;
import com.mgcoin.ar_department.lbs_redpacket.OpenFragmentSuccess;
import com.mgcoin.ar_department.lbs_redpacket.bean.BuyerBean;
import com.mgcoin.ar_department.lbs_redpacket.bean.CouponBean;
import com.mgcoin.ar_department.lbs_redpacket.bean.PacketFragmentBean;
import com.mgcoin.ar_department.lbs_redpacket.bean.ReturnSellerPacketBean;
import com.mgcoin.ar_department.lbs_redpacket.bean.SellerBean;
import com.mgcoin.ar_department.lbs_redpacket.util.LogUtils;
import com.mgcoin.ar_department.lbs_redpacket.util.MyYAnimation;
import com.mgcoin.ar_department.lbs_redpacket.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.HashMap;

import static com.mgcoin.ar_department.apidemo.R.id.map;


public class LocationAndPacket extends AppCompatActivity implements AMap.OnMyLocationChangeListener,
        RadioGroup.OnCheckedChangeListener, AMap.OnMarkerClickListener,
        AMap.OnMapClickListener, AMap.OnMapLongClickListener {

    private static final String TAG = "LocationAndPacket";
    private MapView mMapView;
    private AMap aMap;
    private Circle circle;
    private RadioGroup mGPSModeGroup;
    private MyLocationStyle myLocationStyle;
    private double mWEI;
    private double mJING;
    private Location mLocation;
    private MarkerOptions markerOption;
    private LatLng latlng = new LatLng(mWEI, mJING);
    private Marker marker2;
    private final float R1 = (float) 200.0;
    private double mLat;
    private double mLon;
    private LatLng mLatLng;
    private OkHttpUtil mOkHttpUtil = new OkHttpUtil(this);
    private ArrayList<SellerBean> mSellerBean;
    private SellerBean mString;
    private double mLat1;
    private double mLon1;
    private String mN_shops;
    private HashMap<String, Integer> mHashMap;
    private ArrayList<BuyerBean> mList;
    private ReturnSellerPacketBean mReturnSellerPacketBean;
    private CouponBean mCouponBean;
    private PacketFragmentBean mPacketFragmentBean;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapView = (MapView) findViewById(map);
        mMapView.onCreate(savedInstanceState);
        //初始化地图
        init();
        //加入红包功能
        // addHongBao();
    }

    private void addHongBao() {
        // for循环添加marker
//
        mHashMap = new HashMap<>();
        markerOption = new MarkerOptions();
        for (int i = 0; i < mSellerBean.size(); i++) {
            mLat1 = mSellerBean.get(i).getN_lat();
            mLon1 = mSellerBean.get(i).getN_location();
            String name = mSellerBean.get(i).getN_shops();
            // Log.e(TAG, "显示=" + name);
            mHashMap.put(name, i);
            String n_envelope = mSellerBean.get(i).getN_envelope();
            markerOption.position(new LatLng(mLat1, mLon1));
            markerOption.title(name).snippet("剩余" + n_envelope + "个红包");

            markerOption.draggable(true);
            markerOption.icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.redred));
            marker2 = aMap.addMarker(markerOption);
        }
        Log.e(TAG, "集合" + mHashMap);

        //添加地图上红包的点击事件
        aMap.setOnMarkerClickListener(this);

        aMap.setOnMapClickListener(this);// 对amap添加单击地图事件监听器
        // aMap.setOnMapLongClickListener(this);// 对amap添加长按地图事件监听器,暂时不用

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
        if (aMap == null) {
            aMap = mMapView.getMap();
            setUpMap();
        }
        //circleAndLocation();
        //模式切换
        mGPSModeGroup = (RadioGroup) findViewById(R.id.gps_radio_group);
        mGPSModeGroup.setOnCheckedChangeListener(this);

        //设置SDK 自带定位消息监听
        aMap.setOnMyLocationChangeListener(this);
        loadNetData();
    }
    // 加载商家bean数据
    private void loadNetData() {
        mOkHttpUtil.getSellerBean();
        mOkHttpUtil.getBuyerBean();

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
    public void onMyLocationChange(Location location) {  //位置改变是调用
        mLocation = location;
        //long realtimeNanos = mLocation.getElapsedRealtimeNanos();

        LogUtils.log(TAG, "onMyLocationChange: wei:" + mWEI + "+++" + mJING);

        // 定位回调监听
        if (location != null) {
            Log.e(TAG, "onMyLocationChange: " + "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
            LogUtils.log("amap", "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
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
                LogUtils.log("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
                // circleAndLocation();
                mJING = mLocation.getLongitude();
                mWEI = mLocation.getLatitude();

                //限定周围范围
                circleAndLocation();
            } else {
                LogUtils.log("amap", "定位信息， bundle is null ");
            }
        } else {
            LogUtils.log("amap", "定位失败");
        }
    }

    //圆圈范围
    //// : 2017/10/10 小问题
    private void circleAndLocation() {
        aMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(mWEI, mJING), 17));// 设置指定的可视区域地图
        circle = aMap.addCircle(new CircleOptions().center(new LatLng(mWEI, mJING))
                .radius(200).strokeColor(Color.RED)
                .fillColor(Color.argb(10, 5, 5, 5)).strokeWidth(0));

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
    public boolean onMarkerClick(final Marker marker) {   //marker点击事件

        //计算2个经纬度之间的距离,精准度更高
        LatLng position = marker.getPosition();
        String title = marker.getTitle();
        Log.e(TAG, "title=" + title);

        double longitude = position.longitude;
        double latitude = position.latitude;

        LogUtils.log("onMarkerClick: 定位" + mWEI + "==" + mJING);

        LatLng latlng3 = new LatLng(latitude, longitude);
        LatLng latlng1 = new LatLng(mWEI, mJING);
        float calculateLineDistance = AMapUtils.calculateLineDistance(latlng1, latlng3);
        LogUtils.log("2点距离marker:= " + calculateLineDistance + "米");

        //判断是否在范围内,在则弹出红包
        // if (title.equals(name)) {
        if (calculateLineDistance <= R1 && calculateLineDistance > 0) {
            final int a = mHashMap.get(title);
            Log.e(TAG, "点击的位置=" + mHashMap.get(title));
            // final int a = mHashMap.get(title);
            //弹出比例红包
            final LuckeyDialog.Builder builder = new LuckeyDialog.Builder(LocationAndPacket.this, R.style.Dialog);//调用style中的Diaog

            //商家名字
            final String n_shops = mSellerBean.get(a).getN_shops();
            //商家头像
            String n_picture = mSellerBean.get(a).getN_picture();
            //设置商家名字
            builder.setName(n_shops);
            //商家头像
            builder.setHeadImage(R.drawable.seller);


            builder.setOpenButton("", new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialog, int which) {
                    //  需要判断 红包是否领取成功,每个账号一天只能另领个

                    //网络请求,获取红包金额
                    mOkHttpUtil.getPacketMoney(mSellerBean.get(a).getId(), mSellerBean.get(a).getN_redstate());


                    //动画
                    final MyYAnimation myYAnimation = new MyYAnimation();
                    //   myYAnimation.setRepeatCount(Animation.INFINITE); //旋转的次数（无数次）
                    builder.red_page.setBackgroundResource(R.drawable.weixin);
                    builder.red_page.startAnimation(myYAnimation);

                    myYAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }
                        //动画结束跳转
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (mSellerBean.get(a).getN_redstate().equals("0")) {  //现金红包
                               /* Context application = mContext.getApplicationContext();
                                ConnectivityManager connectivity =
                                        (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
                                NetworkInfo[] allNetworks = connectivity.getAllNetworkInfo();

                                for (int i = 0; i < allNetworks.length; i++) {
                                    if (allNetworks[i].isConnected()) {*/

                                String mRedPacket = mReturnSellerPacketBean.getCa_amount();
                                //状态为0,并且不为空
                                if (mReturnSellerPacketBean.getCa_switchstate().equals("0")&&mRedPacket!=null) {
                                    Intent intent = new Intent(LocationAndPacket.this, OpenSuccess.class);
                                    Log.e(TAG, "钱的大小=: " + mRedPacket);
                                    intent.putExtra("money", mRedPacket);  //红包金额
                                    intent.putExtra("name", n_shops);  //商家名字
                                   // intent.putExtra("headImage", n_shops);
                                    startActivity(intent);
                                    dialog.dismiss();
                                    marker.destroy();
                                } else {
                                    Intent intent = new Intent(LocationAndPacket.this, OpenFail.class);
                                    intent.putExtra("name", n_shops);  //商家名字
                                    startActivity(intent);
                                    dialog.dismiss();
                                    marker.destroy();
                                    Toast.makeText(LocationAndPacket.this, "网络差", Toast.LENGTH_SHORT).show();
                                }

                            } else if (mSellerBean.get(a).getN_redstate().equals("1")) {  //优惠券
                                String p_amount = mCouponBean.getP_amount();
                               // Log.e(TAG, "是优惠券");
                                //状态为0,并且不为空
                                if (mCouponBean.getP_switchstate().equals("0")&&p_amount!=null) {
                                    Intent intent = new Intent(LocationAndPacket.this, OpenCouponSuccess.class);
                                    //double redPacket = mString.getRedPacket();

                                    Log.e(TAG, "优惠券大小=: " + p_amount);
                                    intent.putExtra("coupon", p_amount);
                                    intent.putExtra("name", n_shops);
                                    startActivity(intent);
                                    dialog.dismiss();
                                    marker.destroy();
                                } else {
                                    Intent intent = new Intent(LocationAndPacket.this, OpenFail.class);
                                    intent.putExtra("name", n_shops);  //商家名字
                                    startActivity(intent);
                                    dialog.dismiss();
                                    marker.destroy();
                                   // myYAnimation.setRepeatCount(Animation.INFINITE); //旋转的次数（无数次）
                                }
                            } else {  //活动碎片

                                String chipcol = mPacketFragmentBean.getVi_chipcol();
                               // Log.e(TAG, "是活动碎片");
                                //状态为0,并且不为空
                                if (mPacketFragmentBean.getVi_switchstate().equals("0")&&chipcol!=null) {
                                    Intent intent = new Intent(LocationAndPacket.this, OpenFragmentSuccess.class);
                                    //double redPacket = mString.getRedPacket();
                                    Log.e(TAG, "活动碎片为: " + chipcol);
                                    intent.putExtra("fragment", chipcol);
                                    intent.putExtra("name", n_shops);
                                    startActivity(intent);
                                    dialog.dismiss();
                                    marker.destroy();
                                } else {
                                    Intent intent = new Intent(LocationAndPacket.this, OpenFail.class);
                                    intent.putExtra("name", n_shops);  //商家名字
                                    startActivity(intent);
                                    dialog.dismiss();
                                    marker.destroy();
                                    //myYAnimation.setRepeatCount(Animation.INFINITE); //旋转的次数（无数次）
                                }
                            }

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
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

    // 点击地图,显示点击坐标
    @Override
    public void onMapClick(LatLng latLng) {

        mLatLng = latLng;
        double latitude = mLatLng.latitude;
        double longitude = mLatLng.longitude;

        LogUtils.log("onMapClick: 点击坐标" + latitude + "==" + longitude);

        LatLng latlng1 = new LatLng(mWEI, mJING);
        //高德自带的util工具类
        float calculateLineDistance = AMapUtils.calculateLineDistance(latlng1, mLatLng);
        LogUtils.log("2个marker间距离=" + calculateLineDistance + "米");
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    //网络获取的商家数据
    public void setData(ArrayList<SellerBean> sellerBean) {
            mSellerBean = sellerBean;
            Log.e(TAG, "setData: " + mSellerBean.size());
            addHongBao();
    }


    //获取红包金额
    public void getPacketData(ReturnSellerPacketBean sellerPacketBean) {
        mReturnSellerPacketBean = sellerPacketBean;

    }

    //获取优惠券
    public void getCouponData(CouponBean couponBean) {

        mCouponBean = couponBean;
    }

    //获取活动碎片
    public void getFragmentData(PacketFragmentBean packetFragmentBean) {

        mPacketFragmentBean = packetFragmentBean;
    }

    //会员信息
    public void setBuyerData(ArrayList<BuyerBean> list) {
        mList = list;
    }

    //刷新地图
    public void refreshMap(View view) {

        loadNetData();
        Log.e(TAG, "刷新");
    }
}


