package com.google.android.gms.common.api;

import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpRequestExecutor;

/* renamed from: com.google.android.gms.common.api.c */
public class C0219c {
    public static String m3205a(int i) {
        switch (i) {
            case ContentLengthStrategy.IDENTITY /*-1*/:
                return "SUCCESS_CACHE";
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return "SUCCESS";
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return "SERVICE_MISSING";
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return "SERVICE_DISABLED";
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                return "SIGN_IN_REQUIRED";
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                return "INVALID_ACCOUNT";
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                return "RESOLUTION_REQUIRED";
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                return "NETWORK_ERROR";
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                return "INTERNAL_ERROR";
            case HTTP.HT /*9*/:
                return "SERVICE_INVALID";
            case HTTP.LF /*10*/:
                return "DEVELOPER_ERROR";
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                return "LICENSE_CHECK_FAILED";
            case HTTP.CR /*13*/:
                return "ERROR_OPERATION_FAILED";
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                return "INTERRUPTED";
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                return "TIMEOUT";
            case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                return "CANCELED";
            case HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE /*3000*/:
                return "AUTH_API_INVALID_CREDENTIALS";
            case 3001:
                return "AUTH_API_ACCESS_FORBIDDEN";
            case 3002:
                return "AUTH_API_CLIENT_ERROR";
            case 3003:
                return "AUTH_API_SERVER_ERROR";
            case 3004:
                return "AUTH_TOKEN_ERROR";
            case 3005:
                return "AUTH_URL_RESOLUTION";
            default:
                return "unknown status code: " + i;
        }
    }
}
