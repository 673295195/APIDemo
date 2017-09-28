package com.example.skycheng.apidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.skycheng.apidemo.LocationAndPacket;
import com.example.skycheng.apidemo.R;

import java.io.Serializable;

public class TwoButton extends AppCompatActivity {

    private Serializable mSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_button);

        Button two= (Button) findViewById(R.id.two);
        Button three= (Button) findViewById(R.id.three);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent1=new Intent(TwoButton.this,LocationAndPacket.class);
//                startActivity(intent1);
                Intent intent=new Intent(TwoButton.this,LocationAndPacket.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Seller",mSeller);
                startActivity(intent);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(TwoButton.this,Main2Activity.class);
                startActivity(intent2);

            }
        });
    }
}
