package com.olacabs.p067a.p068a.p069a.p071b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.olacabs.a.a.a.b.b */
public class GenericUtilities {
    public static String m12821a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        if (networkInfo.isConnected()) {
            return "WIFI";
        }
        if (!networkInfo2.isConnected()) {
            return null;
        }
        switch (((TelephonyManager) context.getSystemService(Constants.PHONE)).getNetworkType()) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                return "2G";
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
            case HTTP.HT /*9*/:
            case HTTP.LF /*10*/:
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                return "3G";
            case HTTP.CR /*13*/:
                return "4G";
            default:
                return "NA";
        }
    }

    public static double m12820a() {
        try {
            Runtime runtime = Runtime.getRuntime();
            return (double) ((runtime.totalMemory() - runtime.freeMemory()) / 1048576);
        } catch (Exception e) {
            e.printStackTrace();
            return (double) (-1 / 1048576);
        } catch (Throwable th) {
            return (double) (-1 / 1048576);
        }
    }
}
