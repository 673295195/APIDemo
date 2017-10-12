package com.example.skycheng.apidemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.skycheng.apidemo.R;
import com.example.skycheng.apidemo.bean.SellerBean;
import com.example.skycheng.apidemo.util.OkHttpUtil;

/**
 * Created by SkyCheng on 2017/9/25.
 */

public class OpenSuccess extends AppCompatActivity {

    private Button mClose;
    private ImageButton mHeadImage;
    private TextView mName;
    private TextView mMoney;
    private TextView mSearch;
    private SellerBean mSeller;
    //private double mADouble;
    private String mADouble;
    private OkHttpUtil mOkHttpUtil;
    private String nameSeller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_success);
        //初始化
        initView();
        //获取传递数据
        initIntent();
        //红包明细
        onListener();
        //显示数据
        initData();
    }

    private void initData() {
        mMoney.setText(mADouble);
        mName.setText(nameSeller);
    }

    private void initIntent() {
        if (getIntent() != null) {
            mADouble = getIntent().getStringExtra("money");
            nameSeller = getIntent().getStringExtra("name");
        }
/*            Bundle bundle = intent.getExtras();
            mADouble = bundle.getDouble("Seller");
             mSeller = (SellerBean) bundle.getSerializable("Seller");
        }*/
    }

    private void onListener() {
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 获取消费者红包记录
                // mOkHttpUtil.loadBuyerPacketRecord();
                Intent intent = new Intent(OpenSuccess.this, MGCoinRecord.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    private void initView() {
        mClose = (Button) findViewById(R.id.close);
        mHeadImage = (ImageButton) findViewById(R.id.head_img);
        mName = (TextView) findViewById(R.id.name);
        mMoney = (TextView) findViewById(R.id.money);
        mSearch = (TextView) findViewById(R.id.search);
    }

    //关闭
    public void close(View view) {
        finish();
    }

}
