package com.mgcoin.ar_department.lbs_redpacket.util;

import android.util.Log;
import android.widget.Toast;

import com.mgcoin.ar_department.lbs_redpacket.bean.BuyerBean;
import com.mgcoin.ar_department.lbs_redpacket.bean.CouponBean;
import com.mgcoin.ar_department.lbs_redpacket.bean.PacketFragmentBean;
import com.mgcoin.ar_department.lbs_redpacket.bean.ReturnSellerPacketBean;
import com.mgcoin.ar_department.lbs_redpacket.bean.SellerBean;
import com.mgcoin.ar_department.lbs_redpacket.view.CouponRecord;
import com.mgcoin.ar_department.lbs_redpacket.view.LocationAndPacket;
import com.mgcoin.ar_department.lbs_redpacket.view.MGCoinRecord;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SkyCheng on 2017/9/30.
 */

public class OkHttpUtil {

    private OkHttpClient mOkHttpClient;
    private MGCoinRecord mMGCoinRecord;
    private CouponRecord mCouponRecord;
    private static final String TAG = "OkHttpUtil";
    private LocationAndPacket mLocationAndPacket;
    private PostToServer mPostToServer=new PostToServer();
    private int mId;
    private String mStringvip;
    private String mV_name;
    private String mV_amount;

    public OkHttpUtil(LocationAndPacket locationAndPacket) {
        mLocationAndPacket = locationAndPacket;
    }

    public void getSellerBean() {
        //http://localhost:8080/versionupdate.json
        //获取所有商家信息地址 模拟器
       // String url = "http://192.168.15.16:8080/lbsbonustext/shopm.action";
        //真机
        String url = "http://192.168.23.1:8080/lbsbonustext/shopm.action";
        mOkHttpClient = new OkHttpClient();
        //url可能不同
        Request mRequest = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mLocationAndPacket.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mLocationAndPacket, "服务器繁忙", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e(TAG, "1次请求服务器异常: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                Log.e(TAG, "获得所以商家数据" + string);
                // Toast.makeText(locationAndPacket, "请求成功", Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();

                ArrayList<SellerBean> list = new ArrayList<SellerBean>();
                Type type = new TypeToken<List<SellerBean>>() {
                }.getType();
                list = gson.fromJson(string, type);
               // Log.e(TAG, "当前线程为" + Thread.currentThread());
                Log.e(TAG, "商家个数 " + list.size());
                mLocationAndPacket.setData(list);
            }
        });
    }

    public void getPacketMoney(int pid, final String n_redstate) {

        //发送商家和消费者ID,获取红包
        String url = "http://192.168.23.1:8080/lbsbonustext/redhbaoqq.action?pid=" + pid + "&vid=" + mId;
        mOkHttpClient = new OkHttpClient();
        //url可能不同
        Request mRequest = new Request.Builder().url(url).build();
        Log.e(TAG, "返回的请求头: " + mRequest);

        //synchronized (LocationAndPacket.class){
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                mLocationAndPacket.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mLocationAndPacket, "服务器繁忙", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e(TAG, "2次请求异常: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e(TAG, "返回商家数据 " + string);
                Gson gson = new Gson();
                if (n_redstate.equals("0")) {  //现金红包
                    ArrayList<ReturnSellerPacketBean> list = new ArrayList<ReturnSellerPacketBean>();
                    Type type = new TypeToken<List<ReturnSellerPacketBean>>() {
                    }.getType();
                    list = gson.fromJson(string, type);
                    Log.e(TAG, "现金为" + list.get(0).getCa_amount());
                    mLocationAndPacket.getPacketData(list.get(0));
                }else if (n_redstate.equals("1")){ //优惠券
                    ArrayList<CouponBean> list = new ArrayList<CouponBean>();
                    Type type = new TypeToken<List<CouponBean>>() {
                    }.getType();
                    list = gson.fromJson(string, type);
                    Log.e(TAG, "现金为" + list.get(0).getP_amount());
                    mLocationAndPacket.getCouponData(list.get(0));
                }else {  //活动碎片
                    ArrayList<PacketFragmentBean> list = new ArrayList<PacketFragmentBean>();
                    Type type = new TypeToken<List<PacketFragmentBean>>() {
                    }.getType();
                    list = gson.fromJson(string, type);
                    Log.e(TAG, "现金为" + list.get(0).getVi_chipcol());
                    mLocationAndPacket.getFragmentData(list.get(0));
                }
            }
        });
    }
    //获取红包记录,传入会员ID,哪种红包
    public void loadBuyerPacketRecord(String s, final String id) {
        String url = "";
        Request mRequest = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mLocationAndPacket.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mLocationAndPacket, "服务器繁忙", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            //返回红包历史记录
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                ArrayList<ReturnSellerPacketBean> list = new ArrayList<ReturnSellerPacketBean>();
                Type type = new TypeToken<List<ReturnSellerPacketBean>>() {
                }.getType();
                list = gson.fromJson(string, type);
                if (id.equals("0")){
                    mMGCoinRecord.getData(list,mV_name,mV_amount);
                }else if (id.equals("1")){
                    mCouponRecord.getdata(list,mV_name,mV_amount);
                }
            }
        });
    }

    public void getBuyerBean() {
        //获取会员信息地址
        String url = "http://192.168.23.1:8080/lbsbonustext/member.action";
       // String url = "http://192.168.23.1:8080/lbsbonustext/logintest.action";
        mOkHttpClient = new OkHttpClient();
        //url可能不同
        Request mRequest = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {



            @Override
            public void onFailure(Call call, IOException e) {
                mLocationAndPacket.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mLocationAndPacket, "服务器繁忙", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e(TAG, "1次请求服务器异常: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mStringvip = response.body().string();

                Log.e(TAG, "获得会员数据" + mStringvip);
                Gson gson = new Gson();

                ArrayList<BuyerBean> list = new ArrayList<BuyerBean>();
                Type type = new TypeToken<List<BuyerBean>>() {
                }.getType();
                list = gson.fromJson(mStringvip, type);
                //Log.e(TAG, "当前线程为" + Thread.currentThread());
               // Log.e(TAG, "会员个数 " + list.size());
                mId = list.get(0).getId();
                mV_name = list.get(0).getV_name();
                mV_amount = list.get(0).getV_amount();
                mLocationAndPacket.setBuyerData(list);


                //返回会员信息
                mPostToServer.loginByPost(mStringvip);

            }
        });
    }
}
