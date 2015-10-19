package com.leanplum.utils;

import android.app.Activity;
import android.content.Context;
import com.newrelic.agent.android.api.v1.Defaults;
import org.apache.http.HttpStatus;
import p004b.p005a.p006a.p007a.p008a.p010b.AbstractSpiCall;

public class SizeUtil {
    private static boolean f8875a = false;
    public static int dp10 = 0;
    public static int dp100 = 0;
    public static int dp14 = 0;
    public static int dp16 = 0;
    public static int dp18 = 0;
    public static int dp2 = 0;
    public static int dp20 = 0;
    public static int dp200 = 0;
    public static int dp250 = 0;
    public static int dp30 = 0;
    public static int dp5 = 0;
    public static int dp50 = 0;
    public static int dp7 = 0;
    public static final int textSize0 = 20;
    public static final int textSize0_1 = 18;
    public static final int textSize0_2 = 16;
    public static final int textSize1 = 22;
    public static final int textSize2 = 24;

    static {
        f8875a = false;
    }

    public static void init(Context context) {
        if (!f8875a) {
            f8875a = true;
            dp30 = dpToPx(context, 30);
            dp5 = dpToPx(context, 5);
            dp20 = dpToPx(context, textSize0);
            dp10 = dpToPx(context, 10);
            dp7 = dpToPx(context, 7);
            dp18 = dpToPx(context, textSize0_1);
            dp16 = dpToPx(context, textSize0_2);
            dp14 = dpToPx(context, 14);
            dp100 = dpToPx(context, 100);
            dp200 = dpToPx(context, HttpStatus.SC_OK);
            dp250 = dpToPx(context, 250);
            dp2 = dpToPx(context, 2);
            dp50 = dpToPx(context, 50);
        }
    }

    public static int dpToPx(Context context, int i) {
        init(context);
        return Math.round((context.getResources().getDisplayMetrics().xdpi / 160.0f) * ((float) i));
    }

    public static int pxToDp(Context context, int i) {
        init(context);
        return Math.round(((float) i) / (context.getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    public static int spToPx(Context context, int i) {
        init(context);
        return (int) (context.getResources().getDisplayMetrics().scaledDensity * ((float) i));
    }

    public static int pxToSp(Context context, int i) {
        init(context);
        return (int) (((float) i) / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int getStatusBarHeight(Activity activity) {
        init(activity);
        if (((activity.getWindow().getAttributes().flags & Defaults.RESPONSE_BODY_LIMIT) == Defaults.RESPONSE_BODY_LIMIT ? 1 : 0) != 0) {
            return 0;
        }
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        if (identifier > 0) {
            return activity.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
