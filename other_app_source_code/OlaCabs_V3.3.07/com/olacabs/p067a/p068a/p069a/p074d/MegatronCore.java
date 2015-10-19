package com.olacabs.p067a.p068a.p069a.p074d;

import android.content.Context;
import android.os.Handler;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.olacabs.customer.p076d.br;
import com.olacabs.p067a.p068a.p069a.p070a.BatteryInfo;
import com.olacabs.p067a.p068a.p069a.p070a.SessionInfo;
import com.olacabs.p067a.p068a.p069a.p071b.FileSessionStorage;
import com.olacabs.p067a.p068a.p069a.p071b.MegatronRequest;
import com.olacabs.p067a.p068a.p069a.p073c.MegatronHurlStack;
import java.io.File;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONArray;

/* renamed from: com.olacabs.a.a.a.d.b */
public class MegatronCore {
    private static MegatronCore f8946a;
    private static String f8947e;
    private SessionInfo f8948b;
    private Handler f8949c;
    private Context f8950d;
    private MegatronHurlStack f8951f;
    private RequestQueue f8952g;
    private FileSessionStorage f8953h;
    private String f8954i;
    private String f8955j;

    /* renamed from: com.olacabs.a.a.a.d.b.1 */
    class MegatronCore implements Runnable {
        final /* synthetic */ MegatronCore f8919a;

        MegatronCore(MegatronCore megatronCore) {
            this.f8919a = megatronCore;
        }

        public void run() {
            this.f8919a.f8953h.m12819d();
        }
    }

    /* renamed from: com.olacabs.a.a.a.d.b.2 */
    class MegatronCore implements Response {
        final /* synthetic */ MegatronCore f8920a;

        MegatronCore(MegatronCore megatronCore) {
            this.f8920a = megatronCore;
        }

        public void m12835a(VolleyError volleyError) {
            volleyError.printStackTrace();
        }
    }

    /* renamed from: com.olacabs.a.a.a.d.b.3 */
    class MegatronCore implements Runnable {
        final /* synthetic */ File f8921a;
        final /* synthetic */ MegatronCore f8922b;

        MegatronCore(MegatronCore megatronCore, File file) {
            this.f8922b = megatronCore;
            this.f8921a = file;
        }

        public void run() {
            this.f8922b.f8953h.m12815a(this.f8921a);
        }
    }

    /* renamed from: com.olacabs.a.a.a.d.b.4 */
    class MegatronCore implements Runnable {
        final /* synthetic */ String f8923a;
        final /* synthetic */ long f8924b;
        final /* synthetic */ MegatronCore f8925c;

        MegatronCore(MegatronCore megatronCore, String str, long j) {
            this.f8925c = megatronCore;
            this.f8923a = str;
            this.f8924b = j;
        }

        public void run() {
            if (this.f8923a != null) {
                this.f8925c.m12849a(this.f8923a, this.f8924b);
            }
        }
    }

    /* renamed from: com.olacabs.a.a.a.d.b.5 */
    class MegatronCore implements Runnable {
        final /* synthetic */ MegatronCore f8926a;

        MegatronCore(MegatronCore megatronCore) {
            this.f8926a = megatronCore;
        }

        public void run() {
            this.f8926a.m12856f();
        }
    }

    /* renamed from: com.olacabs.a.a.a.d.b.6 */
    class MegatronCore implements Runnable {
        final /* synthetic */ boolean f8927a;
        final /* synthetic */ long f8928b;
        final /* synthetic */ String f8929c;
        final /* synthetic */ String f8930d;
        final /* synthetic */ boolean f8931e;
        final /* synthetic */ long f8932f;
        final /* synthetic */ MegatronCore f8933g;

        MegatronCore(MegatronCore megatronCore, boolean z, long j, String str, String str2, boolean z2, long j2) {
            this.f8933g = megatronCore;
            this.f8927a = z;
            this.f8928b = j;
            this.f8929c = str;
            this.f8930d = str2;
            this.f8931e = z2;
            this.f8932f = j2;
        }

        public void run() {
            this.f8933g.f8948b.add(this.f8927a, this.f8928b, this.f8929c, this.f8930d, this.f8931e, this.f8932f);
        }
    }

    /* renamed from: com.olacabs.a.a.a.d.b.7 */
    class MegatronCore implements Runnable {
        final /* synthetic */ String f8934a;
        final /* synthetic */ int f8935b;
        final /* synthetic */ Map f8936c;
        final /* synthetic */ byte[] f8937d;
        final /* synthetic */ int f8938e;
        final /* synthetic */ String f8939f;
        final /* synthetic */ long f8940g;
        final /* synthetic */ long f8941h;
        final /* synthetic */ MegatronCore f8942i;

        MegatronCore(MegatronCore megatronCore, String str, int i, Map map, byte[] bArr, int i2, String str2, long j, long j2) {
            this.f8942i = megatronCore;
            this.f8934a = str;
            this.f8935b = i;
            this.f8936c = map;
            this.f8937d = bArr;
            this.f8938e = i2;
            this.f8939f = str2;
            this.f8940g = j;
            this.f8941h = j2;
        }

        public void run() {
            this.f8942i.f8948b.add(this.f8942i.f8950d, this.f8934a, this.f8935b, this.f8936c, this.f8937d, this.f8938e, this.f8939f, this.f8940g, this.f8941h);
        }
    }

    /* renamed from: com.olacabs.a.a.a.d.b.8 */
    class MegatronCore implements Runnable {
        final /* synthetic */ BatteryInfo f8943a;
        final /* synthetic */ MegatronCore f8944b;

        MegatronCore(MegatronCore megatronCore, BatteryInfo batteryInfo) {
            this.f8944b = megatronCore;
            this.f8943a = batteryInfo;
        }

        public void run() {
            this.f8944b.f8948b.addBatteryInfo(this.f8943a);
        }
    }

    /* renamed from: com.olacabs.a.a.a.d.b.9 */
    class MegatronCore implements Runnable {
        final /* synthetic */ MegatronCore f8945a;

        MegatronCore(MegatronCore megatronCore) {
            this.f8945a = megatronCore;
        }

        public void run() {
            this.f8945a.f8948b.endSession();
        }
    }

    public static String m12837a() {
        return f8947e;
    }

    public void m12847a(String str) {
        this.f8954i = str;
    }

    public String m12851b() {
        return this.f8954i;
    }

    private MegatronCore() {
    }

    public void m12845a(Context context, String str) {
        this.f8950d = context;
        this.f8949c = new Handler(BackgroundLooper.m12832a());
        this.f8953h = new FileSessionStorage(this.f8950d);
        this.f8952g = Volley.m685a(this.f8950d);
        this.f8955j = str;
        m12838a(new File(this.f8950d.getCacheDir(), "megatron"));
        m12843h();
    }

    public void m12853c() {
        this.f8949c.post(new MegatronCore(this));
    }

    private void m12838a(File file) {
        this.f8949c.post(new MegatronCore(this, file));
    }

    private void m12840b(String str, long j) {
        this.f8949c.post(new MegatronCore(this, str, j));
    }

    public MegatronHurlStack m12844a(SSLSocketFactory sSLSocketFactory) {
        this.f8951f = new MegatronHurlStack(sSLSocketFactory);
        return this.f8951f;
    }

    public MegatronHurlStack m12855d() {
        if (this.f8950d == null) {
            throw new IllegalStateException("init is not called");
        }
        this.f8951f = new MegatronHurlStack();
        return this.f8951f;
    }

    public static synchronized MegatronCore m12842e() {
        MegatronCore megatronCore;
        synchronized (MegatronCore.class) {
            if (f8946a == null) {
                synchronized (MegatronCore.class) {
                    if (f8946a == null) {
                        f8946a = new MegatronCore();
                    }
                }
            }
            megatronCore = f8946a;
        }
        return megatronCore;
    }

    private void m12843h() {
        this.f8949c.postDelayed(new MegatronCore(this), 5000);
        this.f8948b = new SessionInfo(this.f8950d);
    }

    public void m12852b(String str) {
        if (this.f8950d == null) {
            throw new IllegalStateException("init is not called");
        }
        f8947e = str;
        this.f8948b.setSessionId(str);
    }

    public void m12856f() {
        if (this.f8953h.f8906a.m12808a()) {
            m12840b(this.f8953h.m12818c(), System.currentTimeMillis());
        }
    }

    public void m12850a(boolean z, long j, String str, String str2, boolean z2, long j2) {
        if (this.f8948b == null) {
            m12843h();
        }
        this.f8949c.post(new MegatronCore(this, z, j, str, str2, z2, j2));
    }

    public void m12848a(String str, int i, Map<String, String> map, byte[] bArr, int i2, String str2, long j, long j2) {
        if (this.f8948b == null) {
            m12843h();
        }
        this.f8949c.post(new MegatronCore(this, str, i, map, bArr, i2, str2, j, j2));
    }

    public void m12846a(BatteryInfo batteryInfo) {
        if (this.f8948b == null) {
            m12843h();
        }
        this.f8949c.post(new MegatronCore(this, batteryInfo));
    }

    public void m12857g() {
        this.f8949c.postDelayed(new MegatronCore(this), 1000);
    }

    public void m12854c(String str) {
        if (str.length() > 3) {
            this.f8953h.m12816a(str);
        }
    }

    public void m12849a(String str, long j) {
        Request megatronRequest = new MegatronRequest(1, this.f8955j, str, new Response<JSONArray>() {
            final /* synthetic */ com.olacabs.p067a.p068a.p069a.p074d.MegatronCore f8918a;

            {
                this.f8918a = r1;
            }

            public void m12834a(JSONArray jSONArray) {
                this.f8918a.m12853c();
            }
        }, new MegatronCore(this));
        megatronRequest.setRetryPolicy(new DefaultRetryPolicy(50000, 0, br.DEFAULT_BACKOFF_MULT));
        this.f8952g.m577a(megatronRequest);
    }
}
