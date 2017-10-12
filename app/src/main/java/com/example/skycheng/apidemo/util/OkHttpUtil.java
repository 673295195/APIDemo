package com.example.skycheng.apidemo.util;

import android.util.Log;
import android.widget.Toast;

import com.example.skycheng.apidemo.bean.BuyerBean;
import com.example.skycheng.apidemo.bean.SellerBean;
import com.example.skycheng.apidemo.bean.ReturnSellerPacket;
import com.example.skycheng.apidemo.view.LocationAndPacket;
import com.example.skycheng.apidemo.view.MGCoinRecord;
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
    private static final String TAG = "OkHttpUtil";
    private LocationAndPacket mLocationAndPacket;

    public OkHttpUtil(LocationAndPacket locationAndPacket) {
        mLocationAndPacket = locationAndPacket;
    }


    public void getSellerBean() {
        //http://localhost:8080/versionupdate.json
        //获取所有商家信息地址
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
                Log.e(TAG, "商家个数 " + list.size());
                mLocationAndPacket.setData(list);
            }
        });
    }

    public void getPacketMoney(int pid) {

       //发送商家和消费者ID,获取红包
        String url = "http://192.168.23.1:8080/lbsbonustext/redhbaoqq.action?pid="+pid+"&vid=3";
        mOkHttpClient = new OkHttpClient();
        //url可能不同
        Request mRequest = new Request.Builder().url(url).build();
        Log.e(TAG, "返回的请求头: " + mRequest);
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


                ArrayList<ReturnSellerPacket> list = new ArrayList<ReturnSellerPacket>();
                Type type = new TypeToken<List<ReturnSellerPacket>>() {
                }.getType();
                list = gson.fromJson(string, type);
                Log.e(TAG, "现金为"+list.get(0).getCa_amount());
                mLocationAndPacket.getMoney(list.get(0));}
        });
    }

    public void loadBuyerPacketRecord() {
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
               // Toast.makeText(mLocationAndPacket, "服务器繁忙", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                BuyerBean buyerBean = gson.fromJson(string, BuyerBean.class);
                mMGCoinRecord.getData(buyerBean);
            }
        });
    }
}
