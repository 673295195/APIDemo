package com.example.skycheng.apidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.skycheng.apidemo.view.LocationAndPacket;
import com.example.skycheng.apidemo.R;
import com.example.skycheng.apidemo.bean.SellerBean;

public class TwoButton extends AppCompatActivity {

    private SellerBean mSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_button);

        Button two= (Button) findViewById(R.id.two);
        Button three= (Button) findViewById(R.id.three);
//        mSeller.setID("01");
//        mSeller.setName("小明");
//        mSeller.setRedPacket(1.0);
//        mSeller.setLat(22.948299);
//        mSeller.setLon(113.891437);


        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent1=new Intent(TwoButton.this,LocationAndPacket.class);
//                startActivity(intent1);
                Intent intent=new Intent(TwoButton.this,LocationAndPacket.class);
                Bundle bundle = new Bundle();
                //bundle.putSerializable("Seller",mSeller);
                bundle.putString("name","小明");
                bundle.putString("ID","01");
                bundle.putDouble("Packet",1.0);
                bundle.putDouble("lat",22.948299);
                bundle.putDouble("lon",113.891437);
                startActivity(intent);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(TwoButton.this,ThreeDMap.class);
                startActivity(intent2);

            }
        });
    }
}
