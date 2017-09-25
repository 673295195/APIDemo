package com.example.skycheng.apidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TwoButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_button);

        Button two= (Button) findViewById(R.id.two);
        Button three= (Button) findViewById(R.id.three);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(TwoButton.this,MainActivity.class);
                startActivity(intent1);
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
