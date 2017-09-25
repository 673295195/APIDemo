package com.example.skycheng.apidemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by SkyCheng on 2017/9/22.
 */

public class HongActivity extends AppCompatActivity{

    private LinearLayout mRed1;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hong_activity);

        mRed1 = (LinearLayout) findViewById(R.id.red1);
        onListener();
    }

    private void onListener() {
        mRed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LuckeyDialog.Builder builder = new LuckeyDialog.Builder(mContext,R.style.Dialog);//调用style中的Diaog
                builder.setName("系统");
                builder.setOpenButton("", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext,Open.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                builder.setCloseButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });


//                <span style="color:#ff0000;">Dialog dialog = builder.create();
//                Window dialogWindow = dialog.getWindow();
//
//
//                WindowManager m = getWindowManager();
//                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
//                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//                p.height = (int) (d.getHeight() * 0.7); // 高度设置为屏幕的0.6
//                p.width = (int) (d.getWidth() * 0.75); // 宽度设置为屏幕的0.65
//                dialogWindow.setAttributes(p);
//</span>
                   //     dialog.show();
            }
        });
    }
}
