package com.google.android.m4b.maps.au;

import android.content.Context;
import com.google.android.m4b.maps.aa.ServerControlledParametersManager;
import com.google.android.m4b.maps.ae.PerformanceProfile;
import com.google.android.m4b.maps.av.ZoomTableQuadTree;
import com.google.android.m4b.maps.bx.ClientParameters;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.DataRequestListener;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.google.android.m4b.maps.p058v.Util;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.OutputStream;

/* renamed from: com.google.android.m4b.maps.au.i */
public final class ParameterManager {
    private static boolean f4103a;
    private static volatile boolean f4104b;
    private static volatile boolean f4105c;
    private static volatile NavigationParameters f4106d;
    private static volatile ZoomTableQuadTree f4107e;
    private static volatile boolean f4108f;

    /* renamed from: com.google.android.m4b.maps.au.i.1 */
    static class ParameterManager extends DriveAboutThread {
        private /* synthetic */ Context f4091a;
        private /* synthetic */ DataRequestDispatcher f4092b;
        private /* synthetic */ Runnable f4093c;
        private /* synthetic */ boolean f4094d;

        ParameterManager(String str, Context context, DataRequestDispatcher dataRequestDispatcher, Runnable runnable, boolean z) {
            this.f4091a = context;
            this.f4092b = dataRequestDispatcher;
            this.f4093c = runnable;
            this.f4094d = z;
            super(str);
        }

        public final void m6630f() {
            ParameterManager.m6647b(this.f4091a, this.f4092b, this.f4093c, "NavigationParameters.data", this.f4094d ? "NavZoomTables.data" : "ZoomTables.data");
        }
    }

    /* renamed from: com.google.android.m4b.maps.au.i.a */
    static class ParameterManager implements DataRequestListener {
        private final DataRequestDispatcher f4095a;
        private final Runnable f4096b;
        private final boolean f4097c;

        public ParameterManager(DataRequestDispatcher dataRequestDispatcher, Runnable runnable) {
            this.f4095a = dataRequestDispatcher;
            this.f4096b = runnable;
            this.f4097c = dataRequestDispatcher.m11453c();
            if (this.f4097c) {
                dataRequestDispatcher.m11456e();
            }
        }

        public final void m6634a(DataRequest dataRequest) {
            if (dataRequest instanceof ParameterManager) {
                this.f4095a.m11448b((DataRequestListener) this);
                if (this.f4097c) {
                    this.f4095a.m11454d();
                }
            }
        }

        public final void m6633a(int i, boolean z, String str) {
            Object obj = (i == 3 && z) ? 1 : null;
            if (obj == null) {
                m6631c();
            }
        }

        public final void m6636b(DataRequest dataRequest) {
            if (dataRequest.m4778i() == 75) {
                m6631c();
            }
        }

        private void m6631c() {
            if (ParameterManager.f4107e == null) {
                this.f4095a.m11448b((DataRequestListener) this);
                if (this.f4096b != null) {
                    this.f4096b.run();
                }
                synchronized (ParameterManager.class) {
                    ParameterManager.f4108f = true;
                    ParameterManager.class.notifyAll();
                }
            }
        }

        public final void m6632a() {
        }

        public final void m6635b() {
        }
    }

    /* renamed from: com.google.android.m4b.maps.au.i.b */
    static class ParameterManager extends BaseDataRequest {
        private final Context f4098a;
        private final Long f4099b;
        private final Long f4100c;
        private final String f4101d;
        private final String f4102e;

        private ParameterManager(Context context, Long l, String str, Long l2, String str2) {
            this.f4098a = context;
            this.f4099b = l;
            this.f4101d = str;
            this.f4100c = l2;
            this.f4102e = str2;
        }

        public final int m6640i() {
            return 75;
        }

        public final boolean m6639d() {
            return false;
        }

        public final boolean m6638a(DataInput dataInput) {
            ProtoBuf a = ProtoBufUtil.m10226a(ClientParameters.f6978c, dataInput);
            int k = a.m10215k(1);
            for (int i = 0; i < k; i++) {
                ProtoBuf c = a.m10202c(1, i);
                if (c.m10204d(1) == 1 && c.m10214j(2) && c.m10214j(3)) {
                    Util.m11553a(this.f4098a, c, this.f4101d);
                    ParameterManager.f4106d = new NavigationParameters(c.m10211g(3));
                } else if (c.m10204d(1) == 5 && c.m10214j(2) && c.m10214j(7)) {
                    Util.m11553a(this.f4098a, c, this.f4102e);
                    ParameterManager.f4107e = ZoomTableQuadTree.m7364a(c.m10211g(7));
                }
            }
            ParameterManager.f4104b = true;
            synchronized (ParameterManager.class) {
                ParameterManager.class.notifyAll();
            }
            return true;
        }

        public final void m6637a(DataOutput dataOutput) {
            ProtoBuf protoBuf = new ProtoBuf(ClientParameters.f6979d);
            protoBuf.m10210f(1, 1);
            if (this.f4099b != null) {
                protoBuf.m10184a(2, this.f4099b.longValue());
            }
            ProtoBuf protoBuf2 = new ProtoBuf(ClientParameters.f6976a);
            protoBuf2.m10190a(1, protoBuf);
            protoBuf = new ProtoBuf(ClientParameters.f6979d);
            protoBuf.m10210f(1, 5);
            if (this.f4100c != null) {
                protoBuf.m10184a(2, this.f4100c.longValue());
            }
            protoBuf2.m10190a(1, protoBuf);
            ServerControlledParametersManager.m4797a(protoBuf2, Config.m11320a().m11339o());
            dataOutput.writeInt(protoBuf2.m10201c());
            protoBuf2.m10199b((OutputStream) dataOutput);
        }
    }

    static {
        f4106d = new NavigationParameters(new ProtoBuf(ClientParameters.f6984i));
    }

    public static synchronized NavigationParameters m6641a() {
        NavigationParameters navigationParameters;
        synchronized (ParameterManager.class) {
            navigationParameters = f4106d;
        }
        return navigationParameters;
    }

    public static boolean m6648b() {
        return f4104b || f4105c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.google.android.m4b.maps.av.ZoomTableQuadTree m6650c() {
        /*
        r1 = com.google.android.m4b.maps.au.ParameterManager.class;
        monitor-enter(r1);
    L_0x0003:
        r0 = f4107e;	 Catch:{ all -> 0x0017 }
        if (r0 != 0) goto L_0x0013;
    L_0x0007:
        r0 = f4108f;	 Catch:{ all -> 0x0017 }
        if (r0 != 0) goto L_0x0013;
    L_0x000b:
        r0 = com.google.android.m4b.maps.au.ParameterManager.class;
        r0.wait();	 Catch:{ InterruptedException -> 0x0011 }
        goto L_0x0003;
    L_0x0011:
        r0 = move-exception;
        goto L_0x0003;
    L_0x0013:
        r0 = f4107e;	 Catch:{ all -> 0x0017 }
        monitor-exit(r1);
        return r0;
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.au.i.c():com.google.android.m4b.maps.av.u");
    }

    public static boolean m6651d() {
        return f4107e != null;
    }

    public static void m6652e() {
        f4108f = false;
        f4103a = false;
        f4104b = false;
        f4105c = false;
    }

    public static void m6645a(Context context, DataRequestDispatcher dataRequestDispatcher, Runnable runnable, boolean z) {
        if (!f4103a) {
            f4103a = true;
            new ParameterManager("ParameterManagerLoad", context, dataRequestDispatcher, runnable, false).start();
        }
    }

    private static synchronized void m6647b(Context context, DataRequestDispatcher dataRequestDispatcher, Runnable runnable, String str, String str2) {
        Long l = null;
        synchronized (ParameterManager.class) {
            ProtoBuf protoBuf;
            PerformanceProfile.m4867a();
            ProtoBuf a = Util.m11547a(context, str, ClientParameters.f6979d);
            if (a == null || !a.m10214j(3)) {
                a = new ProtoBuf(ClientParameters.f6979d);
                protoBuf = new ProtoBuf(ClientParameters.f6984i);
            } else {
                protoBuf = a.m10211g(3);
                f4105c = true;
            }
            f4106d = new NavigationParameters(protoBuf);
            Long valueOf = a.m10214j(2) ? Long.valueOf(a.m10207e(2)) : null;
            protoBuf = Util.m11547a(context, str2, ClientParameters.f6979d);
            if (protoBuf != null && protoBuf.m10214j(7)) {
                f4107e = ZoomTableQuadTree.m7364a(protoBuf.m10211g(7));
                if (protoBuf.m10214j(2)) {
                    l = Long.valueOf(protoBuf.m10207e(2));
                }
                ParameterManager.class.notifyAll();
            }
            if (dataRequestDispatcher != null) {
                if (f4107e == null && dataRequestDispatcher.m11461h()) {
                    if (runnable != null) {
                        runnable.run();
                    }
                    f4108f = true;
                    ParameterManager.class.notifyAll();
                }
                dataRequestDispatcher.m11444a(new ParameterManager(dataRequestDispatcher, runnable));
                dataRequestDispatcher.m11451c(new ParameterManager(valueOf, str, l, str2, (byte) 0));
            }
            PerformanceProfile.m4868b();
        }
    }

    private ParameterManager() {
    }
}
