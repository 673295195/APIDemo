package com.example.skycheng.apidemo.ui;

import android.widget.Toast;

import com.example.skycheng.apidemo.BuyerBean;
import com.example.skycheng.apidemo.LocationAndPacket;
import com.example.skycheng.apidemo.MGCoinRecord;
import com.example.skycheng.apidemo.SellerBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by SkyCheng on 2017/9/30.
 */

public class OkHttpUtil {

    private LocationAndPacket locationAndPacket;
    private OkHttpClient mOkHttpClient;
    private MGCoinRecord mMGCoinRecord;

    public void getSellerBean() {
        mOkHttpClient = new OkHttpClient();
        //url可能不同
        HttpUrl url = null;
        Request mRequest = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(locationAndPacket, "服务器繁忙", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Gson gson = new Gson();
                SellerBean sellerBean = gson.fromJson(string, SellerBean.class);
                locationAndPacket.setdata(sellerBean);
            }
        });
    }

    public void getPacketMoney() {

        HttpUrl url = null;
        Request mRequest = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(locationAndPacket, "服务器繁忙", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                locationAndPacket.getMoney(string);
            }
        });
    }

    public void loadBuyerPacketRecord() {
        HttpUrl url = null;
        Request mRequest = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(locationAndPacket, "服务器繁忙", Toast.LENGTH_SHORT).show();

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
