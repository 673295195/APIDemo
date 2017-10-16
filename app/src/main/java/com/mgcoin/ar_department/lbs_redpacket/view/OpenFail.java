package com.mgcoin.ar_department.lbs_redpacket.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mgcoin.ar_department.apidemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        if (getIntent()!=null){
            mName = getIntent().getStringExtra("name");
        }
    }
}
