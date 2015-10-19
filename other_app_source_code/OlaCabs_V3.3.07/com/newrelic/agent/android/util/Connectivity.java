package com.newrelic.agent.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.text.MessageFormat;
import org.apache.http.protocol.HTTP;

public final class Connectivity {
    private static final String ANDROID = "Android";
    private static AgentLog log;

    static {
        log = AgentLogManager.getAgentLog();
    }

    public static String carrierNameFromContext(Context context) {
        try {
            NetworkInfo networkInfo = getNetworkInfo(context);
            if (!isConnected(networkInfo)) {
                return WanType.NONE;
            }
            if (isWan(networkInfo)) {
                return carrierNameFromTelephonyManager(context);
            }
            if (isWifi(networkInfo)) {
                return WanType.WIFI;
            }
            log.warning(MessageFormat.format("Unknown network type: {0} [{1}]", new Object[]{networkInfo.getTypeName(), Integer.valueOf(networkInfo.getType())}));
            return WanType.UNKNOWN;
        } catch (SecurityException e) {
            return WanType.UNKNOWN;
        }
    }

    public static String wanType(Context context) {
        try {
            NetworkInfo networkInfo = getNetworkInfo(context);
            if (!isConnected(networkInfo)) {
                return WanType.NONE;
            }
            if (isWifi(networkInfo)) {
                return WanType.WIFI;
            }
            if (isWan(networkInfo)) {
                return connectionNameFromNetworkSubtype(networkInfo.getSubtype());
            }
            return WanType.UNKNOWN;
        } catch (SecurityException e) {
            return WanType.UNKNOWN;
        }
    }

    private static boolean isConnected(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isConnected();
    }

    private static boolean isWan(NetworkInfo networkInfo) {
        switch (networkInfo.getType()) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return true;
            default:
                return false;
        }
    }

    private static boolean isWifi(NetworkInfo networkInfo) {
        switch (networkInfo.getType()) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
            case HTTP.HT /*9*/:
                return true;
            default:
                return false;
        }
    }

    private static NetworkInfo getNetworkInfo(Context context) throws SecurityException {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            log.warning("Cannot determine network state. Enable android.permission.ACCESS_NETWORK_STATE in your manifest.");
            throw e;
        }
    }

    private static String carrierNameFromTelephonyManager(Context context) {
        String networkOperatorName = ((TelephonyManager) context.getSystemService(Constants.PHONE)).getNetworkOperatorName();
        Object obj = (Build.PRODUCT.equals("google_sdk") || Build.PRODUCT.equals("sdk") || Build.PRODUCT.equals("sdk_x86") || Build.FINGERPRINT.startsWith("generic")) ? 1 : null;
        if (!networkOperatorName.equals(ANDROID) || obj == null) {
            return networkOperatorName;
        }
        return WanType.WIFI;
    }

    private static String connectionNameFromNetworkSubtype(int i) {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return WanType.GPRS;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return WanType.EDGE;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return WanType.UMTS;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return WanType.CDMA;
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return WanType.EVDO_REV_0;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return WanType.EVDO_REV_A;
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                return WanType.RTT;
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                return WanType.HSDPA;
            case HTTP.HT /*9*/:
                return WanType.HSUPA;
            case HTTP.LF /*10*/:
                return WanType.HSPA;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                return WanType.IDEN;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                return WanType.EVDO_REV_B;
            case HTTP.CR /*13*/:
                return WanType.LTE;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                return WanType.HRPD;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                return WanType.HSPAP;
            default:
                return WanType.UNKNOWN;
        }
    }
}
