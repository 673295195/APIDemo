package com.mgcoin.ar_department.lbs_redpacket.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mgcoin.ar_department.apidemo.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_coupon_success);
        ButterKnife.bind(this);

        initIntent();
        initData();
    }

    private void initIntent() {
        if (getIntent() != null) {
            mCoupon = getIntent().getStringExtra("coupon");
            mName = getIntent().getStringExtra("name");
        }
    }

    private void initData() {
        name.setText(mName);
        coupon.setText(mCoupon);
    }

    @OnClick({R.id.close, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.search:

                break;
        }
    }
}
