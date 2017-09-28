package com.example.skycheng.apidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by SkyCheng on 2017/9/25.
 */

public class OpenSuccess extends AppCompatActivity{

    private Button mClose;
    private ImageButton mHeadImage;
    private TextView mName;
    private TextView mMoney;
    private TextView mSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open);
        initView();
        onListener();
    }

    private void onListener() {
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OpenSuccess.this,MGCoinRecord.class);
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

    public void close(View view) {
        finish();
    }

}
