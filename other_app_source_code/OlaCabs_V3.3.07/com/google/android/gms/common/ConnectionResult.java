package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import com.google.android.gms.common.internal.C0452t;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.olacabs.customer.p076d.ao;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

public final class ConnectionResult implements SafeParcelable {
    public static final C0205a CREATOR;
    public static final ConnectionResult f1981a;
    final int f1982b;
    private final int f1983c;
    private final PendingIntent f1984d;

    static {
        f1981a = new ConnectionResult(0, null);
        CREATOR = new C0205a();
    }

    ConnectionResult(int i, int i2, PendingIntent pendingIntent) {
        this.f1982b = i;
        this.f1983c = i2;
        this.f1984d = pendingIntent;
    }

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this(1, i, pendingIntent);
    }

    private String m3157e() {
        switch (this.f1983c) {
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
                return "CANCELED";
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_zOrderOnTop /*14*/:
                return "TIMEOUT";
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiMapToolbar /*15*/:
                return "INTERRUPTED";
            case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                return "API_UNAVAILABLE";
            case LangUtils.HASH_SEED /*17*/:
                return "SIGN_IN_FAILED";
            default:
                return "unknown status code " + this.f1983c;
        }
    }

    public void m3158a(Activity activity, int i) throws SendIntentException {
        if (m3159a()) {
            activity.startIntentSenderForResult(this.f1984d.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public boolean m3159a() {
        return (this.f1983c == 0 || this.f1984d == null) ? false : true;
    }

    public boolean m3160b() {
        return this.f1983c == 0;
    }

    public int m3161c() {
        return this.f1983c;
    }

    public PendingIntent m3162d() {
        return this.f1984d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionResult)) {
            return false;
        }
        ConnectionResult connectionResult = (ConnectionResult) obj;
        return this.f1983c == connectionResult.f1983c && C0452t.m3845a(this.f1984d, connectionResult.f1984d);
    }

    public int hashCode() {
        return C0452t.m3843a(Integer.valueOf(this.f1983c), this.f1984d);
    }

    public String toString() {
        return C0452t.m3844a((Object) this).m3842a("statusCode", m3157e()).m3842a(ao.DEVICE_RESOLUTION_KEY, this.f1984d).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        C0205a.m3163a(this, parcel, i);
    }
}
