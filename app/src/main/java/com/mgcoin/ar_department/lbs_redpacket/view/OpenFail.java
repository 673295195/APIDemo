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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenFail extends AppCompatActivity {

    @BindView(R.id.head_img)
    ImageButton headImg;
    @BindView(R.id.name_fail)
    TextView nameFail;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.fail_close)
    Button failClose;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.fail_search)
    TextView failSearch;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_fail);
        ButterKnife.bind(this);
        initIntent();
        setData();
    }

    private void setData() {
        nameFail.setText(mName);
    }

    private void initIntent() {
        if (getIntent() != null) {
            mName = getIntent().getStringExtra("name");
        }
    }


    @OnClick({R.id.fail_close, R.id.fail_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fail_close:
                finish();
                break;
            case R.id.fail_search:
                //todo 获取消费者红包记录
                // mOkHttpUtil.loadBuyerPacketRecord();
                Intent intent = new Intent(OpenFail.this, MGCoinRecord.class);
                startActivity(intent);
                //finish();
                break;
        }
    }
}
