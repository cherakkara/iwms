package com.google.android.m4b.maps.ca;

import android.app.PendingIntent;
import com.google.android.m4b.maps.p047g.Objects.Objects;
import com.olacabs.customer.p076d.ao;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.ca.a */
public final class ConnectionResult {
    private final PendingIntent f7228a;
    private final int f7229b;

    static {
        ConnectionResult connectionResult = new ConnectionResult(0, null);
    }

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this.f7229b = i;
        this.f7228a = pendingIntent;
    }

    public final String toString() {
        Object obj;
        Objects a = com.google.android.m4b.maps.p047g.Objects.m10456a(this);
        String str = "statusCode";
        switch (this.f7229b) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                obj = "SUCCESS";
                break;
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                obj = "SERVICE_MISSING";
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                obj = "SERVICE_VERSION_UPDATE_REQUIRED";
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                obj = "SERVICE_DISABLED";
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                obj = "SIGN_IN_REQUIRED";
                break;
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                obj = "INVALID_ACCOUNT";
                break;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                obj = "RESOLUTION_REQUIRED";
                break;
            case R.SlidingUpPanelLayout_umanoClipPanel /*7*/:
                obj = "NETWORK_ERROR";
                break;
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                obj = "INTERNAL_ERROR";
                break;
            case HTTP.HT /*9*/:
                obj = "SERVICE_INVALID";
                break;
            case HTTP.LF /*10*/:
                obj = "DEVELOPER_ERROR";
                break;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                obj = "LICENSE_CHECK_FAILED";
                break;
            case HTTP.CR /*13*/:
                obj = "CANCELED";
                break;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                obj = "TIMEOUT";
                break;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                obj = "INTERRUPTED";
                break;
            default:
                obj = "unknown status code " + this.f7229b;
                break;
        }
        return a.m10455a(str, obj).m10455a(ao.DEVICE_RESOLUTION_KEY, this.f7228a).toString();
    }
}
