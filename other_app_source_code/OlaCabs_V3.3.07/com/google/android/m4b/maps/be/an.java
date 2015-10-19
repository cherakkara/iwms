package com.google.android.m4b.maps.be;

import android.content.Context;
import com.google.android.m4b.maps.bx.Api;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.google.android.m4b.maps.p041b.IoUtil;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufType;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: QuotaEventReporter */
final class an {
    private static an f5654a;
    private static final Object f5655b;
    private final DataRequestDispatcherInterface f5656c;
    private final QuotaEventReporter f5657d;
    private boolean f5658e;

    /* renamed from: com.google.android.m4b.maps.be.an.a */
    class QuotaEventReporter extends BaseDataRequest {
        private int f5650a;
        private /* synthetic */ an f5651b;

        QuotaEventReporter(an anVar) {
            this.f5651b = anVar;
        }

        public final int m8568i() {
            return 147;
        }

        public final void m8564a(DataOutput dataOutput) {
            synchronized (this.f5651b) {
                ProtoBuf a = this.f5651b.f5657d.m8569a(Api.f6934a);
                this.f5650a = a.m10215k(1);
                ProtoBufUtil.m10228a(dataOutput, a);
            }
        }

        public final boolean m8565a(DataInput dataInput) {
            switch (ProtoBufUtil.m10226a(Api.f6936c, dataInput).m10204d(1)) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    az.m8757a(6, "This application has been blocked by the Google Maps API. This might be because of an incorrectly registered key.");
                    this.f5651b.f5656c.m11375j();
                    break;
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    az.m8757a(6, "This application has exceeded its quota for the Google Maps API.");
                    this.f5651b.f5656c.m11375j();
                    break;
            }
            return true;
        }

        public final void m8566g() {
            synchronized (this.f5651b) {
                ProtoBuf a = this.f5651b.f5657d.m8569a(Api.f6934a);
                an.m8574a(a, this.f5650a);
                this.f5651b.f5657d.m8570a(a);
                this.f5650a = 0;
                this.f5651b.f5658e = false;
            }
        }

        public final void m8567h() {
            synchronized (this.f5651b) {
                this.f5650a = 0;
                this.f5651b.f5658e = false;
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.an.b */
    public interface QuotaEventReporter {
        private final String f5652a;
        private final Context f5653b;

        QuotaEventReporter(Context context, String str) {
            this.f5653b = context;
            this.f5652a = str;
        }

        synchronized ProtoBuf m8569a(ProtoBufType protoBufType) {
            InputStream openFileInput;
            ProtoBuf protoBuf;
            Throwable th;
            ProtoBuf protoBuf2 = null;
            synchronized (this) {
                try {
                    openFileInput = this.f5653b.openFileInput(this.f5652a);
                    if (openFileInput != null) {
                        try {
                            protoBuf = new ProtoBuf(protoBufType);
                        } catch (IOException e) {
                            try {
                                this.f5653b.deleteFile(this.f5652a);
                                IoUtil.m7773b(openFileInput);
                                return protoBuf2;
                            } catch (Throwable th2) {
                                th = th2;
                                IoUtil.m7773b(openFileInput);
                                throw th;
                            }
                        }
                        try {
                            protoBuf.m10188a(IoUtil.m7771a(openFileInput));
                            protoBuf2 = protoBuf;
                        } catch (IOException e2) {
                            protoBuf2 = protoBuf;
                            this.f5653b.deleteFile(this.f5652a);
                            IoUtil.m7773b(openFileInput);
                            return protoBuf2;
                        }
                    }
                    IoUtil.m7773b(openFileInput);
                } catch (IOException e3) {
                    openFileInput = null;
                    this.f5653b.deleteFile(this.f5652a);
                    IoUtil.m7773b(openFileInput);
                    return protoBuf2;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    openFileInput = null;
                    th = th4;
                    IoUtil.m7773b(openFileInput);
                    throw th;
                }
            }
            return protoBuf2;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        synchronized void m8570a(com.google.android.m4b.maps.p046d.ProtoBuf r6) {
            /*
            r5 = this;
            monitor-enter(r5);
            if (r6 != 0) goto L_0x000c;
        L_0x0003:
            r0 = r5.f5653b;	 Catch:{ all -> 0x0021 }
            r1 = r5.f5652a;	 Catch:{ all -> 0x0021 }
            r0.deleteFile(r1);	 Catch:{ all -> 0x0021 }
        L_0x000a:
            monitor-exit(r5);
            return;
        L_0x000c:
            r0 = 0;
            r1 = r5.f5653b;	 Catch:{ IOException -> 0x0024, all -> 0x0030 }
            r2 = r5.f5652a;	 Catch:{ IOException -> 0x0024, all -> 0x0030 }
            r3 = 0;
            r0 = r1.openFileOutput(r2, r3);	 Catch:{ IOException -> 0x0024, all -> 0x0030 }
            r1 = r6.m10206d();	 Catch:{ IOException -> 0x0024 }
            r0.write(r1);	 Catch:{ IOException -> 0x0024 }
            com.google.android.m4b.maps.p041b.IoUtil.m7770a(r0);	 Catch:{ all -> 0x0021 }
            goto L_0x000a;
        L_0x0021:
            r0 = move-exception;
            monitor-exit(r5);
            throw r0;
        L_0x0024:
            r1 = move-exception;
            r1 = r5.f5653b;	 Catch:{ all -> 0x0038 }
            r2 = r5.f5652a;	 Catch:{ all -> 0x0038 }
            r1.deleteFile(r2);	 Catch:{ all -> 0x0038 }
            com.google.android.m4b.maps.p041b.IoUtil.m7770a(r0);	 Catch:{ all -> 0x0021 }
            goto L_0x000a;
        L_0x0030:
            r1 = move-exception;
            r4 = r1;
            r1 = r0;
            r0 = r4;
        L_0x0034:
            com.google.android.m4b.maps.p041b.IoUtil.m7770a(r1);	 Catch:{ all -> 0x0021 }
            throw r0;	 Catch:{ all -> 0x0021 }
        L_0x0038:
            r1 = move-exception;
            r4 = r1;
            r1 = r0;
            r0 = r4;
            goto L_0x0034;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.be.an.b.a(com.google.android.m4b.maps.d.a):void");
        }
    }

    static {
        f5655b = new Object();
    }

    static an m8572a(Context context) {
        synchronized (f5655b) {
            if (f5654a == null) {
                f5654a = new an(DataRequestDispatcher.m11393a(), new QuotaEventReporter(context.getApplicationContext(), "com.google.android.m4b.maps._m_u"));
            }
        }
        return f5654a;
    }

    private an(DataRequestDispatcherInterface dataRequestDispatcherInterface, QuotaEventReporter quotaEventReporter) {
        this.f5656c = dataRequestDispatcherInterface;
        this.f5657d = quotaEventReporter;
    }

    final synchronized void m8577a(int i) {
        ProtoBuf a = this.f5657d.m8569a(Api.f6934a);
        if (a == null) {
            a = new ProtoBuf(Api.f6934a);
        }
        ProtoBuf protoBuf = new ProtoBuf(Api.f6935b);
        protoBuf.m10189a(1, 1);
        a.m10190a(1, protoBuf);
        this.f5657d.m8570a(a);
        m8573a();
    }

    private synchronized void m8573a() {
        if (!this.f5658e) {
            this.f5658e = true;
            this.f5656c.m11374c(new QuotaEventReporter(this));
        }
    }

    static void m8574a(ProtoBuf protoBuf, int i) {
        int min = Math.min(i, protoBuf.m10215k(1));
        for (int i2 = 0; i2 < min; i2++) {
            protoBuf.m10208e(1, 0);
        }
    }
}
