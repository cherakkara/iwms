package p004b.p005a.p006a.p007a.p008a.p014e;

import com.apsalar.sdk.Constants;
import com.crashlytics.android.core.CrashlyticsCore;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import p004b.p005a.p006a.p007a.DefaultLogger;
import p004b.p005a.p006a.p007a.Logger;

/* renamed from: b.a.a.a.a.e.b */
public class DefaultHttpRequestFactory implements HttpRequestFactory {
    private final Logger f271a;
    private PinningInfoProvider f272b;
    private SSLSocketFactory f273c;
    private boolean f274d;

    /* renamed from: b.a.a.a.a.e.b.1 */
    static /* synthetic */ class DefaultHttpRequestFactory {
        static final /* synthetic */ int[] f270a;

        static {
            f270a = new int[HttpMethod.values().length];
            try {
                f270a[HttpMethod.GET.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f270a[HttpMethod.POST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f270a[HttpMethod.PUT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f270a[HttpMethod.DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public DefaultHttpRequestFactory() {
        this(new DefaultLogger());
    }

    public DefaultHttpRequestFactory(Logger logger) {
        this.f271a = logger;
    }

    public void m337a(PinningInfoProvider pinningInfoProvider) {
        if (this.f272b != pinningInfoProvider) {
            this.f272b = pinningInfoProvider;
            m331a();
        }
    }

    private synchronized void m331a() {
        this.f274d = false;
        this.f273c = null;
    }

    public HttpRequest m335a(HttpMethod httpMethod, String str) {
        return m336a(httpMethod, str, Collections.emptyMap());
    }

    public HttpRequest m336a(HttpMethod httpMethod, String str, Map<String, String> map) {
        HttpRequest a;
        switch (DefaultHttpRequestFactory.f270a[httpMethod.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                a = HttpRequest.m350a((CharSequence) str, (Map) map, true);
                break;
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                a = HttpRequest.m355b((CharSequence) str, (Map) map, true);
                break;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                a = HttpRequest.m358d((CharSequence) str);
                break;
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                a = HttpRequest.m359e((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (m332a(str) && this.f272b != null) {
            SSLSocketFactory b = m333b();
            if (b != null) {
                ((HttpsURLConnection) a.m377a()).setSSLSocketFactory(b);
            }
        }
        return a;
    }

    private boolean m332a(String str) {
        return str != null && str.toLowerCase(Locale.US).startsWith(Constants.API_PROTOCOL);
    }

    private synchronized SSLSocketFactory m333b() {
        if (this.f273c == null && !this.f274d) {
            this.f273c = m334c();
        }
        return this.f273c;
    }

    private synchronized SSLSocketFactory m334c() {
        SSLSocketFactory a;
        this.f274d = true;
        try {
            a = NetworkUtils.m403a(this.f272b);
            this.f271a.m474a(CrashlyticsCore.TAG, "Custom SSL pinning enabled");
        } catch (Throwable e) {
            this.f271a.m482e(CrashlyticsCore.TAG, "Exception while validating pinned certs", e);
            a = null;
        }
        return a;
    }
}
