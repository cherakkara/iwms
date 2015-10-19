package com.google.android.m4b.maps.aa;

import com.google.android.m4b.maps.bh.DummyPaintParameterRequest;
import com.google.android.m4b.maps.bx.ClientParameters;
import com.google.android.m4b.maps.p039o.TaskRunner;
import com.google.android.m4b.maps.p039o.TimerTask;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.TaskRunnerManager;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.google.android.m4b.maps.p058v.GmmSettings;
import com.google.p025a.p028c.ad;
import com.google.p025a.p028c.ar;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.m4b.maps.aa.g */
public final class ServerControlledParametersManager {
    private static volatile boolean f3004a;
    private static volatile ProtoBuf f3005b;
    private static volatile EnableFeatureParameters f3006c;
    private static volatile ServerSettingParameters f3007d;
    private static volatile PrefetcherSettingsParameters f3008e;
    private static volatile VectorMapsParameters f3009f;
    private static volatile OffersParameters f3010g;
    private static volatile AdsParameters f3011h;
    private static volatile ApiParameters f3012i;
    private static volatile NearbyTransitParameters f3013j;
    private static volatile ProtoBuf f3014k;
    private static volatile TimerTask f3015l;
    private static volatile boolean f3016m;
    private static volatile boolean f3017n;
    private static Object f3018o;
    private static boolean f3019p;
    private static boolean f3020q;
    private static Map<Integer, Integer> f3021r;
    private static List<ServerControlledParametersManager> f3022s;

    /* renamed from: com.google.android.m4b.maps.aa.g.a */
    public interface ServerControlledParametersManager {
        void m4748a();
    }

    /* renamed from: com.google.android.m4b.maps.aa.g.b */
    static class ServerControlledParametersManager extends BaseDataRequest {
        private final String f3002a;
        private final ProtoBuf f3003b;

        /* renamed from: com.google.android.m4b.maps.aa.g.b.1 */
        class ServerControlledParametersManager extends TimerTask {
            private /* synthetic */ ServerControlledParametersManager f2998b;

            ServerControlledParametersManager(ServerControlledParametersManager serverControlledParametersManager, TaskRunner taskRunner) {
                this.f2998b = serverControlledParametersManager;
                super(taskRunner);
            }

            protected final void m4767f() {
                ServerControlledParametersManager.m4806b(DataRequestDispatcher.m11393a(), this.f2998b.f3002a, true, this.f2998b.f3003b);
            }
        }

        public ServerControlledParametersManager(String str, boolean z, ProtoBuf protoBuf) {
            this.f3002a = str;
            this.f3003b = protoBuf;
        }

        public final int m4793i() {
            return 75;
        }

        public final boolean m4790a() {
            return false;
        }

        public final boolean m4792d() {
            return false;
        }

        public final boolean m4791a(DataInput dataInput) {
            boolean z = false;
            ProtoBuf a = ProtoBufUtil.m10226a(ClientParameters.f6978c, dataInput);
            int k = a.m10215k(1);
            synchronized (ServerControlledParametersManager.class) {
                for (int i = 0; i < k; i++) {
                    ProtoBuf c = a.m10202c(1, i);
                    if (c.m10214j(2) && ServerControlledParametersManager.m4809c(c)) {
                        ServerControlledParametersManager.m4811d(c);
                        z = true;
                    }
                }
                if (z) {
                    ServerControlledParametersManager.m4802a(ServerControlledParametersManager.f3005b, this.f3002a);
                }
                ServerControlledParametersManager.f3019p = true;
            }
            ServerControlledParametersManager.m4819l();
            if (ServerControlledParametersManager.f3004a) {
                synchronized (ServerControlledParametersManager.f3018o) {
                    ServerControlledParametersManager.f3017n = false;
                    if (ServerControlledParametersManager.f3016m) {
                        ServerControlledParametersManager.m4806b(DataRequestDispatcher.m11393a(), this.f3002a, false, this.f3003b);
                    } else {
                        ServerControlledParametersManager.f3015l = new ServerControlledParametersManager(this, TaskRunnerManager.m11489a());
                        ServerControlledParametersManager.f3015l.m4761a(10800000);
                        ServerControlledParametersManager.f3015l.m4763d();
                    }
                }
            }
            return true;
        }

        public final void m4789a(DataOutput dataOutput) {
            ProtoBuf protoBuf = new ProtoBuf(ClientParameters.f6976a);
            synchronized (ServerControlledParametersManager.class) {
                int k = ServerControlledParametersManager.f3005b.m10215k(1);
                for (int i = 0; i < k; i++) {
                    ProtoBuf c = ServerControlledParametersManager.f3005b.m10202c(1, i);
                    ProtoBuf protoBuf2 = new ProtoBuf(ClientParameters.f6979d);
                    int a = ProtoBufUtil.m10222a(c, 1, -1);
                    if (a != -1) {
                        protoBuf2.m10210f(1, a);
                    }
                    if (c.m10214j(2)) {
                        protoBuf2.m10184a(2, c.m10207e(2));
                    }
                    protoBuf.m10190a(1, protoBuf2);
                }
            }
            ServerControlledParametersManager.m4797a(protoBuf, this.f3003b);
            protoBuf.m10193a((OutputStream) dataOutput);
        }
    }

    static {
        f3004a = true;
        f3015l = null;
        f3016m = false;
        f3017n = false;
        f3018o = new Object();
        f3019p = false;
        f3020q = false;
        f3021r = ad.m2311j().m2362a(Integer.valueOf(2), Integer.valueOf(4)).m2362a(Integer.valueOf(3), Integer.valueOf(5)).m2362a(Integer.valueOf(6), Integer.valueOf(8)).m2362a(Integer.valueOf(8), Integer.valueOf(10)).m2362a(Integer.valueOf(9), Integer.valueOf(11)).m2362a(Integer.valueOf(12), Integer.valueOf(14)).m2362a(Integer.valueOf(13), Integer.valueOf(15)).m2362a(Integer.valueOf(37), Integer.valueOf(39)).m2364a();
        f3022s = ar.m2515a();
    }

    private ServerControlledParametersManager() {
    }

    public static synchronized EnableFeatureParameters m4794a() {
        EnableFeatureParameters enableFeatureParameters;
        synchronized (ServerControlledParametersManager.class) {
            enableFeatureParameters = f3006c;
        }
        return enableFeatureParameters;
    }

    public static synchronized PrefetcherSettingsParameters m4804b() {
        PrefetcherSettingsParameters prefetcherSettingsParameters;
        synchronized (ServerControlledParametersManager.class) {
            prefetcherSettingsParameters = f3008e;
        }
        return prefetcherSettingsParameters;
    }

    public static synchronized VectorMapsParameters m4808c() {
        VectorMapsParameters vectorMapsParameters;
        synchronized (ServerControlledParametersManager.class) {
            vectorMapsParameters = f3009f;
        }
        return vectorMapsParameters;
    }

    public static synchronized ApiParameters m4810d() {
        ApiParameters apiParameters;
        synchronized (ServerControlledParametersManager.class) {
            apiParameters = f3012i;
        }
        return apiParameters;
    }

    public static synchronized ProtoBuf m4812e() {
        ProtoBuf protoBuf;
        synchronized (ServerControlledParametersManager.class) {
            protoBuf = f3014k;
        }
        return protoBuf;
    }

    public static synchronized void m4798a(DataRequestDispatcher dataRequestDispatcher, ProtoBuf protoBuf) {
        synchronized (ServerControlledParametersManager.class) {
            String str = "ServerControlledParametersManager.data";
            if (f3005b == null) {
                ServerControlledParametersManager.m4800a(str);
                int k = f3005b.m10215k(1);
                for (int i = 0; i < k; i++) {
                    ServerControlledParametersManager.m4809c(f3005b.m10202c(1, i));
                }
                ServerControlledParametersManager.m4806b(dataRequestDispatcher, str, true, protoBuf);
            }
        }
    }

    public static void m4796a(ServerControlledParametersManager serverControlledParametersManager) {
        synchronized (ServerControlledParametersManager.class) {
            if (!(f3019p || f3020q)) {
                f3022s.add(serverControlledParametersManager);
                serverControlledParametersManager = null;
            }
        }
        if (serverControlledParametersManager != null) {
            serverControlledParametersManager.m4748a();
        }
    }

    private static synchronized void m4806b(DataRequestDispatcher dataRequestDispatcher, String str, boolean z, ProtoBuf protoBuf) {
        synchronized (ServerControlledParametersManager.class) {
            if (dataRequestDispatcher != null) {
                synchronized (f3018o) {
                    if (f3015l != null) {
                        f3015l.m4750a();
                        f3015l = null;
                    }
                    if (f3017n) {
                        f3016m = true;
                    } else {
                        f3017n = true;
                        f3016m = false;
                        dataRequestDispatcher.m11451c(new ServerControlledParametersManager(str, z, protoBuf));
                    }
                }
            }
        }
    }

    private static boolean m4809c(ProtoBuf protoBuf) {
        int a = ProtoBufUtil.m10222a(protoBuf, 1, -1);
        if (!f3021r.containsKey(Integer.valueOf(a))) {
            return false;
        }
        int intValue = ((Integer) f3021r.get(Integer.valueOf(a))).intValue();
        if (!protoBuf.m10214j(intValue)) {
            return false;
        }
        ProtoBuf g = protoBuf.m10211g(intValue);
        switch (a) {
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (f3006c != null) {
                    f3006c.m4744a(g);
                } else {
                    f3006c = new EnableFeatureParameters(g);
                }
                return true;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                f3007d = new ServerSettingParameters(g);
                GmmSettings.m11527a();
                GmmSettings.m11534h();
                return true;
            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                f3008e = new PrefetcherSettingsParameters(g);
                return true;
            case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                f3009f = new VectorMapsParameters(g);
                return true;
            case HTTP.HT /*9*/:
                f3010g = new OffersParameters(g);
                return true;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                f3011h = new AdsParameters(g);
                return true;
            case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                f3012i = new ApiParameters(g);
                return true;
            case HTTP.CR /*13*/:
                f3013j = new NearbyTransitParameters(g);
                return true;
            case LangUtils.HASH_OFFSET /*37*/:
                try {
                    f3014k = ProtoBufUtil.m10225a(g);
                    return true;
                } catch (IOException e) {
                    return false;
                }
            default:
                return false;
        }
    }

    private static void m4811d(ProtoBuf protoBuf) {
        int d = protoBuf.m10204d(1);
        if (f3021r.containsKey(Integer.valueOf(d))) {
            int k = f3005b.m10215k(1);
            for (int i = 0; i < k; i++) {
                if (d == f3005b.m10202c(1, i).m10204d(1)) {
                    f3005b.m10208e(1, i);
                    break;
                }
            }
            f3005b.m10190a(1, protoBuf);
        }
    }

    private static void m4800a(String str) {
        ProtoBuf protoBuf = new ProtoBuf(ClientParameters.f6978c);
        for (Entry entry : f3021r.entrySet()) {
            ProtoBuf protoBuf2;
            ProtoBuf protoBuf3 = new ProtoBuf(ClientParameters.f6979d);
            int intValue = ((Integer) entry.getKey()).intValue();
            int intValue2 = ((Integer) entry.getValue()).intValue();
            protoBuf3.m10210f(1, intValue);
            switch (intValue) {
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    protoBuf3.m10196b(intValue2, new ProtoBuf(ClientParameters.f6980e));
                    protoBuf2 = protoBuf3;
                    break;
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    protoBuf3.m10196b(intValue2, new ProtoBuf(ClientParameters.f6986k));
                    protoBuf2 = protoBuf3;
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    protoBuf3.m10196b(intValue2, PrefetcherSettingsParameters.m4746b());
                    protoBuf2 = protoBuf3;
                    break;
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    protoBuf3.m10196b(intValue2, new ProtoBuf(ClientParameters.f6988m));
                    protoBuf2 = protoBuf3;
                    break;
                case HTTP.HT /*9*/:
                    protoBuf3.m10196b(intValue2, new ProtoBuf(ClientParameters.f6989n));
                    protoBuf2 = protoBuf3;
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                    protoBuf3.m10196b(intValue2, new ProtoBuf(ClientParameters.f6981f));
                    protoBuf2 = protoBuf3;
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    protoBuf3.m10196b(intValue2, new ProtoBuf(ClientParameters.f6982g));
                    protoBuf2 = protoBuf3;
                    break;
                case HTTP.CR /*13*/:
                    protoBuf3.m10196b(intValue2, new ProtoBuf(ClientParameters.f6983h));
                    protoBuf2 = protoBuf3;
                    break;
                case LangUtils.HASH_OFFSET /*37*/:
                    protoBuf3.m10196b(intValue2, new ProtoBuf(DummyPaintParameterRequest.f6230b));
                    protoBuf2 = protoBuf3;
                    break;
                default:
                    protoBuf2 = null;
                    break;
            }
            if (protoBuf2 != null) {
                protoBuf.m10190a(1, protoBuf2);
            }
        }
        f3005b = protoBuf;
        try {
            byte[] c = Config.m11320a().m10149q().m7757c(str);
            if (c != null) {
                ProtoBuf protoBuf4 = new ProtoBuf(ClientParameters.f6978c);
                protoBuf4.m10188a(c);
                int k = protoBuf4.m10215k(1);
                synchronized (ServerControlledParametersManager.class) {
                    for (intValue2 = 0; intValue2 < k; intValue2++) {
                        ServerControlledParametersManager.m4811d(protoBuf4.m10202c(1, intValue2));
                    }
                    f3020q = true;
                }
                ServerControlledParametersManager.m4819l();
            }
        } catch (IOException e) {
        }
    }

    static boolean m4802a(ProtoBuf protoBuf, String str) {
        try {
            Config.m11320a().m10149q().m7752a(protoBuf.m10206d(), str);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void m4819l() {
        ar.m2515a();
        synchronized (ServerControlledParametersManager.class) {
            List<ServerControlledParametersManager> a = ar.m2516a(f3022s);
            f3022s.clear();
        }
        for (ServerControlledParametersManager a2 : a) {
            a2.m4748a();
        }
    }

    public static void m4797a(ProtoBuf protoBuf, ProtoBuf protoBuf2) {
        ProtoBuf protoBuf3 = new ProtoBuf(ClientParameters.f6977b);
        boolean d = GmmSettings.m11527a().m11536d();
        protoBuf3.m10185a(1, true);
        protoBuf3.m10185a(2, d);
        protoBuf3.m10196b(4, protoBuf2);
        protoBuf.m10190a(2, protoBuf3);
    }
}
