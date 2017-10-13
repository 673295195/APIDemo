package com.example.skycheng.apidemo.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.skycheng.apidemo.R;
import com.example.skycheng.apidemo.util.MyYAnimation;

/**
 * Created by SkyCheng on 2017/9/25.
 */


public class LuckeyDialog extends Dialog {

    private Context mContext;


    public LuckeyDialog(Context context) {
        super(context);
        mContext = context;
    }

    public LuckeyDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String name;//发红包者的名称

        public Button red_page;

        //拆红包按钮
        private String openButtonText;
        private OnClickListener openButtonClickListener;

        //关闭按钮
        private String closeButtonText;
        private OnClickListener closeButtonClickListener;
        //private ImageButton mImage;
        private int mBao;
        private int mImageHead;
        private ImageButton mImage;
        private LocationAndPacket location;

        public Builder(Context context, int dialog) {
            this.context = context;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param name
         * @return
         */
        public Builder setName(int name) {
            this.name = (String) context.getText(name);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param name
         * @return
         */

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param closeButtonText
         * @return
         */
        public Builder setCloseButton(int closeButtonText,
                                      OnClickListener listener) {
            this.closeButtonText = (String) context
                    .getText(closeButtonText);
            this.closeButtonClickListener = listener;
            return this;
        }

        public Builder setCloseButton(String closeButtonText,
                                      OnClickListener listener) {
            this.closeButtonText = closeButtonText;
            this.closeButtonClickListener = listener;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param openButtonText
         * @return
         */
        public Builder setOpenButton(int openButtonText,
                                     OnClickListener listener) {
            this.openButtonText = (String) context
                    .getText(openButtonText);
            this.openButtonClickListener = listener;
            return this;
        }

        public Builder setOpenButton(String openButtonText,
                                     OnClickListener listener) {
            this.openButtonText = openButtonText;
            this.openButtonClickListener = listener;
            return this;
        }


        public void setHeadImage(int image) {

            mImageHead = image;
        }
        public void setAnim(final int a, final String n_shops){
            MyYAnimation myYAnimation = new MyYAnimation();
            myYAnimation.setRepeatCount(Animation.INFINITE); //旋转的次数（无数次）
            red_page.startAnimation(myYAnimation);
            myYAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }

        public LuckeyDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //加载布局
            final LuckeyDialog dialog = new LuckeyDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.luckey_dialog, null);

            red_page = (Button) layout.findViewById(R.id.l_open_btn);
            mImage = (ImageButton) layout.findViewById(R.id.head_img);
            mImage.setImageResource(mImageHead);
            //red_page.setText("开");
//            <span style = "color:#ff0000;" >//red指的是需要播放动画的ImageView控件
//                    AnimationDrawable
//            animationDrawable = (AnimationDrawable) red_page.getBackground();
//            animationDrawable.start();//启动动画</span>

            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            //设置发红包者姓名
            ((TextView) layout.findViewById(R.id.name)).setText(name);

            //设置拆红包的按钮
            if (openButtonText != null) {
                ((Button) layout.findViewById(R.id.l_open_btn))
                        .setText("");
                if (openButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.l_open_btn))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    openButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                   /* AnimationDrawable animation= (AnimationDrawable) red_page.getBackground();
                                    animation.start();*/
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.l_open_btn).setVisibility(
                        View.GONE);
            }

            //设置关闭按钮
            if (closeButtonText != null) {
                ((Button) layout.findViewById(R.id.close))
                        .setText(closeButtonText);
                if (closeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.close))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    closeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.close).setVisibility(
                        View.GONE);
            }

            dialog.setContentView(layout);
            return dialog;
        }

    }
}

