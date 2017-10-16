package com.mgcoin.ar_department.lbs_redpacket.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by SkyCheng on 2017/9/30. */

public class ToastUtil {
    public static final String TOAST = "TOAST";
    public static boolean TOAST_TOOL = true;

    public ToastUtil() {
    }

    public static final void setToastTool(Context context, String log) {
        if (TOAST_TOOL)
           // Log.d(LOG_TAG, log);
        Toast.makeText(context,log,Toast.LENGTH_SHORT).show();
    }
}
