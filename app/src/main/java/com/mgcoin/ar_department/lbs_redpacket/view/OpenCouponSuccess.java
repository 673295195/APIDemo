package com.mgcoin.ar_department.lbs_redpacket.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mgcoin.ar_department.apidemo.R;
import com.mgcoin.ar_department.lbs_redpacket.util.OkHttpUtil;
import com.orzangleli.coupon.view.CouponView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenCouponSuccess extends AppCompatActivity {

    @BindView(R.id.close)
    Button close;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.head_img)
    ImageButton headImg;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.money)
    CouponView money;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.coupon)
    TextView coupon;
    private String mName;
    private String mCoupon;
    private String mDate;
    private TextView mData;
    private TextView mLimit;
    private String mId;
    private OkHttpUtil mOkHttpUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_coupon_success);
        ButterKnife.bind(this);
        init();
        initIntent();
        initData();
    }

    private void init() {
        mLimit = (TextView) findViewById(R.id.limittime);
        mData = (TextView) findViewById(R.id.gettime);
    }

    private void initIntent() {
        if (getIntent() != null) {
            mCoupon = getIntent().getStringExtra("coupon");
            mName = getIntent().getStringExtra("name");
            mDate = getIntent().getStringExtra("date");
            mId= getIntent().getStringExtra("buyer");
        }
    }

    private void initData() {
        name.setText(mName);
        coupon.setText(mCoupon);
        mData.setText("领取时间:"+mDate);
    }

    @OnClick({R.id.close, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.search:
                mOkHttpUtil.loadBuyerPacketRecord(mId,"1");
                Intent intent=new Intent(OpenCouponSuccess.this,CouponRecord.class);
                startActivity(intent);
                break;
        }
    }
}
