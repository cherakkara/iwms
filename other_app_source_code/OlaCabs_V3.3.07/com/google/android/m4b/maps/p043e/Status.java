package com.google.android.m4b.maps.p043e;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.m4b.maps.p037h.C0591c;
import com.google.android.m4b.maps.p047g.Objects;
import com.olacabs.customer.p076d.ao;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Arrays;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpRequestExecutor;

/* renamed from: com.google.android.m4b.maps.e.j */
public final class Status implements Result, C0591c {
    public static final StatusCreator CREATOR;
    public static final Status f7358a;
    public static final Status f7359b;
    public static final Status f7360c;
    private final int f7361d;
    private final int f7362e;
    private final String f7363f;
    private final PendingIntent f7364g;

    static {
        f7358a = new Status(0);
        Status status = new Status(14);
        status = new Status(8);
        f7359b = new Status(15);
        f7360c = new Status(16);
        CREATOR = new StatusCreator();
    }

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.f7361d = i;
        this.f7362e = i2;
        this.f7363f = str;
        this.f7364g = pendingIntent;
    }

    public Status(int i) {
        this(1, i, null, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, 8, str, null);
    }

    final PendingIntent m10298a() {
        return this.f7364g;
    }

    public final String m10299b() {
        return this.f7363f;
    }

    final int m10300c() {
        return this.f7361d;
    }

    public final boolean m10301d() {
        return this.f7362e <= 0;
    }

    public final int m10302e() {
        return this.f7362e;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f7361d), Integer.valueOf(this.f7362e), this.f7363f, this.f7364g});
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        if (this.f7361d == status.f7361d && this.f7362e == status.f7362e && Objects.m10457a(this.f7363f, status.f7363f) && Objects.m10457a(this.f7364g, status.f7364g)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        Object obj;
        Objects.Objects a = Objects.m10456a(this);
        String str = "statusCode";
        if (this.f7363f == null) {
            int i = this.f7362e;
            switch (i) {
                case ContentLengthStrategy.IDENTITY /*-1*/:
                    obj = "SUCCESS_CACHE";
                    break;
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
                case HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE /*3000*/:
                    obj = "AUTH_API_INVALID_CREDENTIALS";
                    break;
                case 3001:
                    obj = "AUTH_API_ACCESS_FORBIDDEN";
                    break;
                case 3002:
                    obj = "AUTH_API_CLIENT_ERROR";
                    break;
                case 3003:
                    obj = "AUTH_API_SERVER_ERROR";
                    break;
                case 3004:
                    obj = "AUTH_TOKEN_ERROR";
                    break;
                case 3005:
                    obj = "AUTH_URL_RESOLUTION";
                    break;
                default:
                    obj = "unknown status code: " + i;
                    break;
            }
        }
        obj = this.f7363f;
        return a.m10455a(str, obj).m10455a(ao.DEVICE_RESOLUTION_KEY, this.f7364g).toString();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        StatusCreator.m10303a(this, parcel, i);
    }
}
