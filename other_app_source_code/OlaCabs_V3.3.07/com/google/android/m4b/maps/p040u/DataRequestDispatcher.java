package com.google.android.m4b.maps.p040u;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Pair;
import com.google.android.m4b.maps.bx.ClientProperties;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p041b.ConnectionFactory;
import com.google.android.m4b.maps.p041b.PersistentStore;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.p025a.p028c.ar;
import com.newrelic.agent.android.instrumentation.Trace;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import org.apache.http.HttpStatus;

/* renamed from: com.google.android.m4b.maps.u.h */
public class DataRequestDispatcher implements DataRequestDispatcherInterface {
    private static int f7902H;
    private static volatile DataRequestDispatcher f7903I;
    private volatile int f7904A;
    private volatile int f7905B;
    private volatile String f7906C;
    private ShortbreadToken f7907D;
    private final Clock f7908E;
    private volatile int f7909F;
    private int f7910G;
    protected final String f7911a;
    protected final String f7912b;
    protected final boolean f7913c;
    protected final DataRequestDispatcher f7914d;
    protected DataRequestDispatcher f7915e;
    protected int f7916f;
    protected int f7917g;
    protected ConnectionFactory f7918h;
    protected ConnectionWarmUpManager f7919i;
    private volatile String f7920j;
    private volatile boolean f7921k;
    private boolean f7922l;
    private final List<DataRequestDispatcher> f7923m;
    private final String f7924n;
    private Long f7925o;
    private final List<DataRequestListener> f7926p;
    private final Random f7927q;
    private long f7928r;
    private volatile boolean f7929s;
    private volatile int f7930t;
    private volatile long f7931u;
    private volatile long f7932v;
    private volatile boolean f7933w;
    private long f7934x;
    private long f7935y;
    private volatile int f7936z;

    /* renamed from: com.google.android.m4b.maps.u.h.a */
    public static class DataRequestDispatcher {
        private static /* synthetic */ boolean f7878l;
        private String f7879a;
        private String f7880b;
        private String f7881c;
        private boolean f7882d;
        private int f7883e;
        private String f7884f;
        private String f7885g;
        private Boolean f7886h;
        private Boolean f7887i;
        private int f7888j;
        private DataRequestDispatcher f7889k;

        static {
            f7878l = !DataRequestDispatcher.class.desiredAssertionStatus();
        }

        public DataRequestDispatcher() {
            this.f7882d = false;
            this.f7888j = -1;
        }

        public final DataRequestDispatcher m11345a(String str) {
            if (f7878l || this.f7889k == null) {
                this.f7879a = str;
                return this;
            }
            throw new AssertionError();
        }

        public final DataRequestDispatcher m11348b(String str) {
            if (f7878l || this.f7889k == null) {
                this.f7880b = str;
                return this;
            }
            throw new AssertionError();
        }

        public final DataRequestDispatcher m11350c(String str) {
            if (f7878l || this.f7889k == null) {
                this.f7881c = str;
                return this;
            }
            throw new AssertionError();
        }

        public final DataRequestDispatcher m11346a(boolean z) {
            if (f7878l || this.f7889k == null) {
                this.f7882d = z;
                return this;
            }
            throw new AssertionError();
        }

        public final DataRequestDispatcher m11352d(String str) {
            if (f7878l || this.f7889k == null) {
                this.f7884f = str;
                return this;
            }
            throw new AssertionError();
        }

        public final DataRequestDispatcher m11344a(int i) {
            if (f7878l || this.f7889k == null) {
                this.f7883e = i;
                return this;
            }
            throw new AssertionError();
        }

        public final DataRequestDispatcher m11349b(boolean z) {
            if (f7878l || this.f7889k == null) {
                this.f7886h = Boolean.valueOf(true);
                return this;
            }
            throw new AssertionError();
        }

        public final DataRequestDispatcher m11351c(boolean z) {
            if (f7878l || this.f7889k == null) {
                this.f7887i = Boolean.valueOf(z);
                return this;
            }
            throw new AssertionError();
        }

        public final DataRequestDispatcher m11353e(String str) {
            this.f7885g = str;
            return this;
        }

        public final DataRequestDispatcher m11347a() {
            if (f7878l || this.f7889k == null) {
                this.f7889k = DataRequestDispatcher.m11410b(this.f7879a, this.f7880b, this.f7881c, this.f7882d);
                this.f7889k.m11396a(27, VERSION.SDK);
                this.f7889k.m11449b("SYSTEM");
                this.f7889k.m11457e(this.f7884f);
                DataRequestDispatcher.m11399a(this.f7889k, this.f7883e);
                if (this.f7886h != null) {
                    this.f7889k.m11397a(4, this.f7886h.booleanValue());
                }
                if (this.f7887i != null) {
                    this.f7889k.m11397a(29, this.f7887i.booleanValue());
                }
                DataRequestDispatcher dataRequestDispatcher = this.f7889k.f7915e;
                if (this.f7885g != null) {
                    dataRequestDispatcher.f7900c.m10197b(5, this.f7885g);
                }
                if (this.f7888j != -1) {
                    dataRequestDispatcher.m11384a(this.f7888j);
                }
                return this.f7889k;
            }
            throw new AssertionError();
        }
    }

    /* renamed from: com.google.android.m4b.maps.u.h.b */
    class DataRequestDispatcher extends BaseDataRequest {
        private /* synthetic */ DataRequestDispatcher f7890a;

        private DataRequestDispatcher(DataRequestDispatcher dataRequestDispatcher) {
            this.f7890a = dataRequestDispatcher;
        }

        public final int m11357i() {
            return 15;
        }

        public final boolean m11355a() {
            return false;
        }

        public final void m11354a(DataOutput dataOutput) {
        }

        public final synchronized boolean m11356a(DataInput dataInput) {
            this.f7890a.f7925o = Long.valueOf(dataInput.readLong());
            DataRequestDispatcher.m11398a(this.f7890a.f7925o.longValue());
            return true;
        }
    }

    /* renamed from: com.google.android.m4b.maps.u.h.c */
    public class DataRequestDispatcher {
        private volatile String f7891a;
        private /* synthetic */ DataRequestDispatcher f7892b;

        static /* synthetic */ void m11360a(DataRequestDispatcher dataRequestDispatcher, Exception exception) {
            ExceptionReporter.m11467a("REQUEST", exception);
            dataRequestDispatcher.f7892b.m11463j();
            dataRequestDispatcher.f7892b.m11438a(0);
        }

        private DataRequestDispatcher(DataRequestDispatcher dataRequestDispatcher, String str) {
            this.f7892b = dataRequestDispatcher;
            this.f7891a = str;
        }

        private synchronized void m11358a() {
            if (this.f7892b.m11462i()) {
                for (DataRequestDispatcher a : this.f7892b.f7923m) {
                    DataRequestDispatcher a2 = a.m11380a(true);
                    if (a2 != null) {
                        a2.m11370a();
                        break;
                    }
                }
            }
        }

        private void m11362b() {
            Object obj;
            if (!this.f7892b.m11453c()) {
                for (DataRequestDispatcher b : this.f7892b.f7923m) {
                    if (b.f7899b) {
                        obj = 1;
                        break;
                    }
                }
            }
            obj = null;
            if (obj != null) {
                m11358a();
            }
        }

        private synchronized long m11363c() {
            long m;
            switch (this.f7892b.f7910G) {
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    m = (this.f7892b.f7934x + 800) + (this.f7892b.f7927q.nextLong() % this.f7892b.f7934x);
                    break;
                default:
                    m = this.f7892b.f7934x;
                    break;
            }
            return m;
        }
    }

    /* renamed from: com.google.android.m4b.maps.u.h.d */
    class DataRequestDispatcher implements Runnable {
        private Vector<DataRequest> f7893a;
        private final ProtoBuf f7894b;
        private final boolean f7895c;
        private final boolean f7896d;
        private /* synthetic */ DataRequestDispatcher f7897e;

        DataRequestDispatcher(DataRequestDispatcher dataRequestDispatcher, Vector<DataRequest> vector, ProtoBuf protoBuf) {
            this.f7897e = dataRequestDispatcher;
            this.f7893a = vector;
            this.f7894b = protoBuf;
            this.f7895c = DataRequestDispatcher.m11406a((Vector) vector);
            this.f7896d = DataRequestDispatcher.m11415b((Vector) vector);
        }

        public final void m11370a() {
            this.f7897e.m11404a(this.f7895c, this.f7896d);
            this.f7897e.f7931u = this.f7897e.f7908E.m10152b();
            new Thread(this, "DataRequestDispatcher").start();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
            r19 = this;
        L_0x0000:
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x00f7 }
            r2 = r2.f7929s;	 Catch:{ all -> 0x00f7 }
            if (r2 == 0) goto L_0x0549;
        L_0x000a:
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ all -> 0x00f7 }
            r2 = r2.size();	 Catch:{ all -> 0x00f7 }
            if (r2 <= 0) goto L_0x0549;
        L_0x0014:
            monitor-enter(r19);	 Catch:{ all -> 0x00f7 }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x0111 }
            r2 = r2.f7914d;	 Catch:{ all -> 0x0111 }
            r2 = r2.m11363c();	 Catch:{ all -> 0x0111 }
            r4 = 0;
            r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r4 <= 0) goto L_0x002a;
        L_0x0025:
            r0 = r19;
            r0.wait(r2);	 Catch:{ InterruptedException -> 0x0562 }
        L_0x002a:
            monitor-exit(r19);	 Catch:{ all -> 0x0111 }
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r11 = r2.size();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r8 = 0;
            r6 = 0;
            r3 = 0;
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.f7919i;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2.m11342a(r0);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r5 = new java.io.ByteArrayOutputStream;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r5.<init>();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7 = new java.io.DataOutputStream;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7.<init>(r5);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = 0;
            r0 = r19;
            r4 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = com.google.android.m4b.maps.p040u.DataRequestDispatcher.m11417c(r4);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            if (r4 == 0) goto L_0x056e;
        L_0x0056:
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.m11437t();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.m11478a();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = r2;
        L_0x0063:
            if (r4 == 0) goto L_0x0114;
        L_0x0065:
            r0 = r19;
            r2 = r0.f7894b;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.m10181a();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r9 = 31;
            r2.m10190a(r9, r4);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x0072:
            r4 = new com.google.android.m4b.maps.u.d;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4.<init>(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.size();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            if (r2 <= 0) goto L_0x012f;
        L_0x0081:
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r9 = 0;
            r2 = r2.elementAt(r9);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = (com.google.android.m4b.maps.p040u.DataRequest) r2;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2 instanceof com.google.android.m4b.maps.p040u.ClientPropertiesRequest;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            if (r2 == 0) goto L_0x011a;
        L_0x0090:
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r9 = 0;
            r2.setElementAt(r4, r9);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x0098:
            r2 = 23;
            r7.writeShort(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r12 = r2.m11465l();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7.writeLong(r12);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = com.google.android.m4b.maps.p040u.Config.m11324b();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7.writeUTF(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.f7911a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7.writeUTF(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.f7912b;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7.writeUTF(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.f7924n;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7.writeUTF(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = r2.iterator();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x00d4:
            r2 = r4.hasNext();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            if (r2 == 0) goto L_0x0144;
        L_0x00da:
            r2 = r4.next();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = (com.google.android.m4b.maps.p040u.DataRequest) r2;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r9 = r2.m4778i();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7.writeByte(r9);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2.m4768a(r7);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x00d4;
        L_0x00eb:
            r2 = move-exception;
            r0 = r19;
            r3 = r0.f7897e;	 Catch:{ all -> 0x00f7 }
            r3 = r3.f7914d;	 Catch:{ all -> 0x00f7 }
            com.google.android.m4b.maps.p040u.DataRequestDispatcher.DataRequestDispatcher.m11360a(r3, r2);	 Catch:{ all -> 0x00f7 }
            goto L_0x0000;
        L_0x00f7:
            r2 = move-exception;
            r0 = r19;
            r3 = r0.f7897e;
            r0 = r19;
            r4 = r0.f7895c;
            r0 = r19;
            r5 = r0.f7896d;
            r3.m11414b(r4, r5);
            r0 = r19;
            r3 = r0.f7897e;
            r3 = r3.f7914d;
            r3.m11362b();
            throw r2;
        L_0x0111:
            r2 = move-exception;
            monitor-exit(r19);	 Catch:{ all -> 0x00f7 }
            throw r2;	 Catch:{ all -> 0x00f7 }
        L_0x0114:
            r0 = r19;
            r2 = r0.f7894b;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x0072;
        L_0x011a:
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r9 = 0;
            r2.insertElementAt(r4, r9);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x0098;
        L_0x0124:
            r2 = move-exception;
            r0 = r19;
            r3 = r0.f7897e;	 Catch:{ all -> 0x00f7 }
            r4 = 4;
            com.google.android.m4b.maps.p040u.DataRequestDispatcher.m11400a(r3, r4, r2);	 Catch:{ all -> 0x00f7 }
            goto L_0x0000;
        L_0x012f:
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r9 = 0;
            r2.insertElementAt(r4, r9);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x0098;
        L_0x0139:
            r2 = move-exception;
            r0 = r19;
            r3 = r0.f7897e;	 Catch:{ all -> 0x00f7 }
            r4 = 3;
            com.google.android.m4b.maps.p040u.DataRequestDispatcher.m11400a(r3, r4, r2);	 Catch:{ all -> 0x00f7 }
            goto L_0x0000;
        L_0x0144:
            r7.flush();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r12 = r5.toByteArray();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r13 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = "DRD";
            r13.<init>(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = "(";
            r2 = r13.append(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = com.google.android.m4b.maps.p040u.DataRequestDispatcher.m11433p();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.append(r4);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = "): ";
            r2.append(r4);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = "";
            r0 = r19;
            r4 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r5 = r4.iterator();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = r2;
        L_0x0170:
            r2 = r5.hasNext();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            if (r2 == 0) goto L_0x0199;
        L_0x0176:
            r2 = r5.next();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = (com.google.android.m4b.maps.p040u.DataRequest) r2;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r13.append(r4);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = "|";
            r2 = r2.m4778i();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r13.append(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x0170;
        L_0x0189:
            r2 = move-exception;
            r0 = r19;
            r3 = r0.f7897e;	 Catch:{ all -> 0x00f7 }
            r4 = 5;
            com.google.android.m4b.maps.p040u.DataRequestDispatcher.m11400a(r3, r4, r2);	 Catch:{ all -> 0x00f7 }
            r3 = "REQUEST";
            com.google.android.m4b.maps.p040u.ExceptionReporter.m11467a(r3, r2);	 Catch:{ all -> 0x00f7 }
            goto L_0x0000;
        L_0x0199:
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x0565 }
            r2 = r2.f7908E;	 Catch:{ all -> 0x0565 }
            r4 = r2.m10152b();	 Catch:{ all -> 0x0565 }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x0565 }
            r2 = r2.f7914d;	 Catch:{ all -> 0x0565 }
            r7 = r2.f7891a;	 Catch:{ all -> 0x0565 }
            r2 = new java.net.URL;	 Catch:{ all -> 0x0565 }
            r2.<init>(r7);	 Catch:{ all -> 0x0565 }
            r2 = r2.openConnection();	 Catch:{ all -> 0x0565 }
            r2 = com.newrelic.agent.android.instrumentation.HttpInstrumentation.openConnection(r2);	 Catch:{ all -> 0x0565 }
            r0 = r2;
            r0 = (java.net.HttpURLConnection) r0;	 Catch:{ all -> 0x0565 }
            r8 = r0;
            r2 = 1;
            r8.setDoOutput(r2);	 Catch:{ all -> 0x0565 }
            r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0565 }
            r9 = "Connection opened to:";
            r2.<init>(r9);	 Catch:{ all -> 0x0565 }
            r2.append(r7);	 Catch:{ all -> 0x0565 }
            r2 = "Content-Type";
            r7 = "application/binary";
            r8.addRequestProperty(r2, r7);	 Catch:{ all -> 0x0565 }
            r2 = "Content-Length";
            r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0565 }
            r7.<init>();	 Catch:{ all -> 0x0565 }
            r9 = r12.length;	 Catch:{ all -> 0x0565 }
            r7 = r7.append(r9);	 Catch:{ all -> 0x0565 }
            r7 = r7.toString();	 Catch:{ all -> 0x0565 }
            r8.addRequestProperty(r2, r7);	 Catch:{ all -> 0x0565 }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x0565 }
            r2 = r2.f7906C;	 Catch:{ all -> 0x0565 }
            r2 = com.google.p025a.p026a.Strings.m1866b(r2);	 Catch:{ all -> 0x0565 }
            if (r2 != 0) goto L_0x0212;
        L_0x01f6:
            r2 = "Authorization";
            r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0565 }
            r9 = "Bearer ";
            r7.<init>(r9);	 Catch:{ all -> 0x0565 }
            r0 = r19;
            r9 = r0.f7897e;	 Catch:{ all -> 0x0565 }
            r9 = r9.f7906C;	 Catch:{ all -> 0x0565 }
            r7 = r7.append(r9);	 Catch:{ all -> 0x0565 }
            r7 = r7.toString();	 Catch:{ all -> 0x0565 }
            r8.addRequestProperty(r2, r7);	 Catch:{ all -> 0x0565 }
        L_0x0212:
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x0565 }
            r2 = r2.f7921k;	 Catch:{ all -> 0x0565 }
            if (r2 == 0) goto L_0x027d;
        L_0x021c:
            r7 = "X-Google-Maps-Mobile-API";
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x0565 }
            r9 = r2.m11466m();	 Catch:{ all -> 0x0565 }
            r0 = r19;
            r2 = r0.f7894b;	 Catch:{ all -> 0x0565 }
            r10 = 39;
            r10 = r2.m10212h(r10);	 Catch:{ all -> 0x0565 }
            r0 = r19;
            r2 = r0.f7894b;	 Catch:{ all -> 0x0565 }
            r14 = 40;
            r14 = r2.m10212h(r14);	 Catch:{ all -> 0x0565 }
            if (r10 == 0) goto L_0x0358;
        L_0x023c:
            r2 = 1;
        L_0x023d:
            r15 = "app version not set";
            com.google.p025a.p026a.Preconditions.m1829b(r2, r15);	 Catch:{ all -> 0x0565 }
            if (r14 == 0) goto L_0x035b;
        L_0x0244:
            r2 = 1;
        L_0x0245:
            r15 = "gmm version not set";
            com.google.p025a.p026a.Preconditions.m1829b(r2, r15);	 Catch:{ all -> 0x0565 }
            r2 = 44;
            r2 = com.google.p025a.p026a.Joiner.m1779a(r2);	 Catch:{ all -> 0x0565 }
            r15 = 3;
            r15 = new java.lang.Object[r15];	 Catch:{ all -> 0x0565 }
            r16 = 0;
            r0 = r19;
            r0 = r0.f7897e;	 Catch:{ all -> 0x0565 }
            r17 = r0;
            r0 = r17;
            r0 = r0.f7912b;	 Catch:{ all -> 0x0565 }
            r17 = r0;
            r15[r16] = r17;	 Catch:{ all -> 0x0565 }
            r16 = 1;
            r15[r16] = r14;	 Catch:{ all -> 0x0565 }
            r14 = 2;
            r0 = r19;
            r0 = r0.f7897e;	 Catch:{ all -> 0x0565 }
            r16 = r0;
            r0 = r16;
            r0 = r0.f7911a;	 Catch:{ all -> 0x0565 }
            r16 = r0;
            r15[r14] = r16;	 Catch:{ all -> 0x0565 }
            r2 = r2.m1786a(r9, r10, r15);	 Catch:{ all -> 0x0565 }
            r8.addRequestProperty(r7, r2);	 Catch:{ all -> 0x0565 }
        L_0x027d:
            r10 = new java.io.DataOutputStream;	 Catch:{ all -> 0x0565 }
            r2 = r8.getOutputStream();	 Catch:{ all -> 0x0565 }
            r10.<init>(r2);	 Catch:{ all -> 0x0565 }
            r10.write(r12);	 Catch:{ all -> 0x036f }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x036f }
            r6 = r2.f7916f;	 Catch:{ all -> 0x036f }
            r7 = r12.length;	 Catch:{ all -> 0x036f }
            r6 = r6 + r7;
            r2.f7916f = r6;	 Catch:{ all -> 0x036f }
            r2 = r8.getResponseCode();	 Catch:{ all -> 0x036f }
            r6 = r8.getContentType();	 Catch:{ all -> 0x036f }
            r0 = r19;
            r7 = r0.f7897e;	 Catch:{ all -> 0x036f }
            r7 = r7.f7908E;	 Catch:{ all -> 0x036f }
            r14 = r7.m10152b();	 Catch:{ all -> 0x036f }
            r14 = r14 - r4;
            r7 = ", ";
            r13.append(r7);	 Catch:{ all -> 0x036f }
            r16 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            r7 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
            if (r7 >= 0) goto L_0x035e;
        L_0x02b3:
            r7 = "<1s";
            r13.append(r7);	 Catch:{ all -> 0x036f }
        L_0x02b8:
            r0 = r19;
            r0.m11367a(r2, r6);	 Catch:{ all -> 0x036f }
            r16 = r8.getContentLength();	 Catch:{ all -> 0x036f }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x036f }
            r6 = r2.f7917g;	 Catch:{ all -> 0x036f }
            r6 = r6 + r16;
            r2.f7917g = r6;	 Catch:{ all -> 0x036f }
            r9 = new java.io.DataInputStream;	 Catch:{ all -> 0x036f }
            r2 = r8.getInputStream();	 Catch:{ all -> 0x036f }
            r9.<init>(r2);	 Catch:{ all -> 0x036f }
            r2 = r9.readUnsignedShort();	 Catch:{ all -> 0x0300 }
            r3 = 23;
            if (r2 == r3) goto L_0x0377;
        L_0x02dc:
            r0 = r19;
            r3 = r0.f7897e;	 Catch:{ all -> 0x0300 }
            r4 = 1;
            r3.m11438a(r4);	 Catch:{ all -> 0x0300 }
            r3 = "DRD";
            r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0300 }
            r5 = "Protocol version mismatch. Client = 23 Server = ";
            r4.<init>(r5);	 Catch:{ all -> 0x0300 }
            r2 = r4.append(r2);	 Catch:{ all -> 0x0300 }
            r2 = r2.toString();	 Catch:{ all -> 0x0300 }
            com.google.android.m4b.maps.p040u.UserEventReporter.m11506a(r3, r2);	 Catch:{ all -> 0x0300 }
            r2 = new java.io.IOException;	 Catch:{ all -> 0x0300 }
            r3 = "Protocol version mismatch with the server";
            r2.<init>(r3);	 Catch:{ all -> 0x0300 }
            throw r2;	 Catch:{ all -> 0x0300 }
        L_0x0300:
            r2 = move-exception;
            r3 = r2;
            r4 = r10;
            r2 = r9;
        L_0x0304:
            if (r2 == 0) goto L_0x0309;
        L_0x0306:
            r2.close();	 Catch:{ IOException -> 0x0511, SecurityException -> 0x00eb, e -> 0x0124, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x0309:
            if (r4 == 0) goto L_0x030e;
        L_0x030b:
            r4.close();	 Catch:{ IOException -> 0x051e, SecurityException -> 0x00eb, e -> 0x0124, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x030e:
            if (r8 == 0) goto L_0x0313;
        L_0x0310:
            r8.disconnect();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x0313:
            r4 = new java.util.ArrayList;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4.<init>();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r5 = r2.iterator();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x0320:
            r2 = r5.hasNext();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            if (r2 == 0) goto L_0x053a;
        L_0x0326:
            r2 = r5.next();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = (com.google.android.m4b.maps.p040u.DataRequest) r2;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r6 = r2.m4773d();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            if (r6 != 0) goto L_0x052b;
        L_0x0332:
            r6 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7 = "Error processing: ";
            r6.<init>(r7);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r6 = r6.append(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7 = " not retrying";
            r6.append(r7);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r6 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r6.m11447b(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x0320;
        L_0x034a:
            r2 = move-exception;
            com.google.android.m4b.maps.p040u.StaticUtil.m11486a();	 Catch:{ all -> 0x00f7 }
            r0 = r19;
            r3 = r0.f7897e;	 Catch:{ all -> 0x00f7 }
            r4 = 5;
            com.google.android.m4b.maps.p040u.DataRequestDispatcher.m11400a(r3, r4, r2);	 Catch:{ all -> 0x00f7 }
            goto L_0x0000;
        L_0x0358:
            r2 = 0;
            goto L_0x023d;
        L_0x035b:
            r2 = 0;
            goto L_0x0245;
        L_0x035e:
            r16 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            r16 = r14 / r16;
            r0 = r16;
            r7 = r13.append(r0);	 Catch:{ all -> 0x036f }
            r9 = "s";
            r7.append(r9);	 Catch:{ all -> 0x036f }
            goto L_0x02b8;
        L_0x036f:
            r2 = move-exception;
            r4 = r10;
            r18 = r3;
            r3 = r2;
            r2 = r18;
            goto L_0x0304;
        L_0x0377:
            r0 = r19;
            r0.m11368a(r9);	 Catch:{ all -> 0x0300 }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x0300 }
            r2 = r2.f7908E;	 Catch:{ all -> 0x0300 }
            r2 = r2.m10152b();	 Catch:{ all -> 0x0300 }
            r2 = r2 - r4;
            r7 = (int) r2;	 Catch:{ all -> 0x0300 }
            r2 = 22;
            r3 = "fb";
            r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0300 }
            r6.<init>();	 Catch:{ all -> 0x0300 }
            r6 = r6.append(r14);	 Catch:{ all -> 0x0300 }
            r6 = r6.toString();	 Catch:{ all -> 0x0300 }
            com.google.android.m4b.maps.p040u.UserEventReporter.m11502a(r2, r3, r6);	 Catch:{ all -> 0x0300 }
            r2 = 22;
            r3 = "lb";
            r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0300 }
            r6.<init>();	 Catch:{ all -> 0x0300 }
            r6 = r6.append(r7);	 Catch:{ all -> 0x0300 }
            r6 = r6.toString();	 Catch:{ all -> 0x0300 }
            com.google.android.m4b.maps.p040u.UserEventReporter.m11502a(r2, r3, r6);	 Catch:{ all -> 0x0300 }
            r2 = 22;
            r3 = "flbs";
            r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0300 }
            r17 = "fb=";
            r0 = r17;
            r6.<init>(r0);	 Catch:{ all -> 0x0300 }
            r6 = r6.append(r14);	 Catch:{ all -> 0x0300 }
            r17 = "|lb=";
            r0 = r17;
            r6 = r6.append(r0);	 Catch:{ all -> 0x0300 }
            r6 = r6.append(r7);	 Catch:{ all -> 0x0300 }
            r17 = "|s=";
            r0 = r17;
            r6 = r6.append(r0);	 Catch:{ all -> 0x0300 }
            r0 = r16;
            r6 = r6.append(r0);	 Catch:{ all -> 0x0300 }
            r6 = r6.toString();	 Catch:{ all -> 0x0300 }
            com.google.android.m4b.maps.p040u.UserEventReporter.m11502a(r2, r3, r6);	 Catch:{ all -> 0x0300 }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x0300 }
            r2 = r2.f7919i;	 Catch:{ all -> 0x0300 }
            r6 = (int) r14;	 Catch:{ all -> 0x0300 }
            r3 = r19;
            r2.m11343a(r3, r4, r6, r7);	 Catch:{ all -> 0x0300 }
            r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
            r0 = r16;
            if (r0 < r2) goto L_0x0433;
        L_0x03f6:
            r2 = (long) r7;	 Catch:{ all -> 0x0300 }
            r4 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 > 0) goto L_0x0433;
        L_0x03fe:
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ all -> 0x0300 }
            r0 = r16;
            r3 = r0 * 1000;
            r3 = r3 / r7;
            r2.f7909F = r3;	 Catch:{ all -> 0x0300 }
            r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0300 }
            r3 = "Sent ";
            r2.<init>(r3);	 Catch:{ all -> 0x0300 }
            r3 = r12.length;	 Catch:{ all -> 0x0300 }
            r2 = r2.append(r3);	 Catch:{ all -> 0x0300 }
            r3 = ", Loaded ";
            r2 = r2.append(r3);	 Catch:{ all -> 0x0300 }
            r0 = r16;
            r2 = r2.append(r0);	 Catch:{ all -> 0x0300 }
            r3 = " bytes.  Byte/Sec = ";
            r2 = r2.append(r3);	 Catch:{ all -> 0x0300 }
            r0 = r19;
            r3 = r0.f7897e;	 Catch:{ all -> 0x0300 }
            r3 = r3.f7909F;	 Catch:{ all -> 0x0300 }
            r2.append(r3);	 Catch:{ all -> 0x0300 }
        L_0x0433:
            r2 = ", ";
            r13.append(r2);	 Catch:{ all -> 0x0300 }
            r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            r0 = r16;
            if (r0 >= r2) goto L_0x0485;
        L_0x043e:
            r2 = "<1kb";
            r13.append(r2);	 Catch:{ all -> 0x0300 }
        L_0x0443:
            r9.close();	 Catch:{ IOException -> 0x0493, SecurityException -> 0x00eb, e -> 0x0124, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x0446:
            r10.close();	 Catch:{ IOException -> 0x049f, SecurityException -> 0x00eb, e -> 0x0124, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x0449:
            if (r8 == 0) goto L_0x044e;
        L_0x044b:
            r8.disconnect();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x044e:
            r3 = new java.util.ArrayList;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r3.<init>();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = r2.iterator();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x045b:
            r2 = r4.hasNext();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            if (r2 == 0) goto L_0x04b9;
        L_0x0461:
            r2 = r4.next();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = (com.google.android.m4b.maps.p040u.DataRequest) r2;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r5 = r2.m4773d();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            if (r5 != 0) goto L_0x04ab;
        L_0x046d:
            r5 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r6 = "Error processing: ";
            r5.<init>(r6);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r5 = r5.append(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r6 = " not retrying";
            r5.append(r6);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r5 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r5.m11447b(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x045b;
        L_0x0485:
            r0 = r16;
            r2 = r0 / 1000;
            r2 = r13.append(r2);	 Catch:{ all -> 0x0300 }
            r3 = "kb";
            r2.append(r3);	 Catch:{ all -> 0x0300 }
            goto L_0x0443;
        L_0x0493:
            r2 = move-exception;
            r3 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = "Closing is: ";
            r3.<init>(r4);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r3.append(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x0446;
        L_0x049f:
            r2 = move-exception;
            r3 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = "Closing os: ";
            r3.<init>(r4);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r3.append(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x0449;
        L_0x04ab:
            r5 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r6 = "Retrying: ";
            r5.<init>(r6);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r5.append(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r3.add(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x045b;
        L_0x04b9:
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2.removeAllElements();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2.addAll(r3);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.f7918h;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r3 = 0;
            r2.m7747a(r3);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2.m11436s();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r3 = r0.f7897e;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r3 = r3.f7908E;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = r3.m10152b();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2.f7932v = r4;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = r2.size();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            if (r11 != r2) goto L_0x0000;
        L_0x04f5:
            r2 = "DRD";
            r3 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4 = "No requests are processed. Request count = ";
            r3.<init>(r4);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r3 = r3.append(r11);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r3 = r3.toString();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            com.google.android.m4b.maps.p040u.UserEventReporter.m11506a(r2, r3);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2 = new java.io.IOException;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r3 = "No requests are processed";
            r2.<init>(r3);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            throw r2;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x0511:
            r2 = move-exception;
            r5 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r6 = "Closing is: ";
            r5.<init>(r6);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r5.append(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x0309;
        L_0x051e:
            r2 = move-exception;
            r4 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r5 = "Closing os: ";
            r4.<init>(r5);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4.append(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x030e;
        L_0x052b:
            r6 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r7 = "Retrying: ";
            r6.<init>(r7);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r6.append(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r4.add(r2);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            goto L_0x0320;
        L_0x053a:
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2.removeAllElements();	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r0 = r19;
            r2 = r0.f7893a;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            r2.addAll(r4);	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
            throw r3;	 Catch:{ SecurityException -> 0x00eb, e -> 0x0124, IOException -> 0x0139, Exception -> 0x0189, OutOfMemoryError -> 0x034a }
        L_0x0549:
            r0 = r19;
            r2 = r0.f7897e;
            r0 = r19;
            r3 = r0.f7895c;
            r0 = r19;
            r4 = r0.f7896d;
            r2.m11414b(r3, r4);
            r0 = r19;
            r2 = r0.f7897e;
            r2 = r2.f7914d;
            r2.m11362b();
            return;
        L_0x0562:
            r2 = move-exception;
            goto L_0x002a;
        L_0x0565:
            r2 = move-exception;
            r4 = r6;
            r18 = r3;
            r3 = r2;
            r2 = r18;
            goto L_0x0304;
        L_0x056e:
            r4 = r2;
            goto L_0x0063;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.u.h.d.run():void");
        }

        private void m11367a(int i, String str) {
            if (i != HttpStatus.SC_OK) {
                new StringBuilder("Bad HTTP response code: ").append(i);
                if (i == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                    Iterator it = this.f7893a.iterator();
                    while (it.hasNext()) {
                        ((DataRequest) it.next()).m4775f();
                    }
                    if (this.f7897e.f7913c) {
                        this.f7897e.m11439a(7, this.f7897e.f7918h.m7746a(), "Server 500 for request types: " + m11369b());
                    }
                    throw new DataRequestDispatcher("Serverside failure (HTTP" + i + ") for " + m11369b());
                }
                if (i == HttpStatus.SC_FORBIDDEN && this.f7897e.f7921k) {
                    this.f7897e.m11458f();
                } else if (i == HttpStatus.SC_NOT_IMPLEMENTED) {
                    this.f7897e.m11438a(2);
                    UserEventReporter.m11506a("DRD", "Server side HTTP not implemented");
                    throw new IOException("Server side HTTP not implemented");
                } else if (i == HttpStatus.SC_BAD_REQUEST && this.f7897e.f7921k) {
                    this.f7897e.m11460g();
                }
                throw new IOException("Bad HTTP response code: " + i + " for " + m11369b());
            } else if (!"application/binary".equals(str)) {
                new StringBuilder("Bad HTTP content type: ").append(str);
                throw new IOException("Bad HTTP content type: " + str + " for " + m11369b());
            }
        }

        private String m11369b() {
            StringBuilder stringBuilder = new StringBuilder();
            String str = Trace.NULL;
            Iterator it = this.f7893a.iterator();
            String str2 = str;
            while (it.hasNext()) {
                DataRequest dataRequest = (DataRequest) it.next();
                stringBuilder.append(str2);
                str2 = ",";
                stringBuilder.append(dataRequest.m4778i());
            }
            return stringBuilder.toString();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void m11368a(java.io.DataInputStream r9) {
            /*
            r8 = this;
            r3 = 0;
            r4 = new java.util.ArrayList;
            r4.<init>();
            r1 = 0;
            r2 = r3;
        L_0x0008:
            r0 = r8.f7893a;	 Catch:{ IOException -> 0x0127, RuntimeException -> 0x0125 }
            r0 = r0.size();	 Catch:{ IOException -> 0x0127, RuntimeException -> 0x0125 }
            if (r2 >= r0) goto L_0x00f0;
        L_0x0010:
            r0 = r8.f7893a;	 Catch:{ IOException -> 0x0127, RuntimeException -> 0x0125 }
            r0 = r0.get(r2);	 Catch:{ IOException -> 0x0127, RuntimeException -> 0x0125 }
            r0 = (com.google.android.m4b.maps.p040u.DataRequest) r0;	 Catch:{ IOException -> 0x0127, RuntimeException -> 0x0125 }
            r1 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r5 = "Processing DataRequest: ";
            r1.<init>(r5);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r1.append(r0);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r1 = r9.readUnsignedByte();	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r5 = r0.m4778i();	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            if (r1 == r5) goto L_0x00d1;
        L_0x002c:
            r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r5 = "Expecting request type: ";
            r3.<init>(r5);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r5 = r0.m4778i();	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r3 = r3.append(r5);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r5 = " got: ";
            r3 = r3.append(r5);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r3 = r3.append(r1);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r5 = ".  ABORTING!";
            r3.append(r5);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r3 = new java.io.IOException;	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r6 = "RT: ";
            r5.<init>(r6);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r1 = r5.append(r1);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r5 = " != ";
            r1 = r1.append(r5);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r5 = r0.m4778i();	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r1 = r1.append(r5);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r1 = r1.toString();	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r3.<init>(r1);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            throw r3;	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
        L_0x006d:
            r1 = move-exception;
            r7 = r1;
            r1 = r0;
            r0 = r7;
        L_0x0071:
            r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ae }
            r5 = "IOException: ";
            r3.<init>(r5);	 Catch:{ all -> 0x00ae }
            r5 = r1.m4778i();	 Catch:{ all -> 0x00ae }
            r3.append(r5);	 Catch:{ all -> 0x00ae }
            r3 = r0 instanceof java.io.EOFException;	 Catch:{ all -> 0x00ae }
            if (r3 == 0) goto L_0x00ad;
        L_0x0083:
            r1.m4775f();	 Catch:{ all -> 0x00ae }
            r3 = r8.f7897e;	 Catch:{ all -> 0x00ae }
            r3 = r3.f7913c;	 Catch:{ all -> 0x00ae }
            if (r3 == 0) goto L_0x00ad;
        L_0x008c:
            r1 = r1.m4778i();	 Catch:{ all -> 0x00ae }
            r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ae }
            r5 = "No server support for data request: ";
            r3.<init>(r5);	 Catch:{ all -> 0x00ae }
            r1 = r3.append(r1);	 Catch:{ all -> 0x00ae }
            r1 = r1.toString();	 Catch:{ all -> 0x00ae }
            r3 = r8.f7897e;	 Catch:{ all -> 0x00ae }
            r3 = r3.f7918h;	 Catch:{ all -> 0x00ae }
            r3 = r3.m7746a();	 Catch:{ all -> 0x00ae }
            r5 = r8.f7897e;	 Catch:{ all -> 0x00ae }
            r6 = 7;
            r5.m11439a(r6, r3, r1);	 Catch:{ all -> 0x00ae }
        L_0x00ad:
            throw r0;	 Catch:{ all -> 0x00ae }
        L_0x00ae:
            r0 = move-exception;
            r1 = r8.f7893a;
            r1 = r1.size();
            if (r2 >= r1) goto L_0x00c6;
        L_0x00b7:
            r1 = r8.f7893a;
            r3 = r8.f7893a;
            r3 = r3.size();
            r1 = r1.subList(r2, r3);
            r4.addAll(r1);
        L_0x00c6:
            r1 = r8.f7893a;
            r1.clear();
            r1 = r8.f7893a;
            r1.addAll(r4);
            throw r0;
        L_0x00d1:
            r1 = r0.m4770a(r9);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            if (r1 == 0) goto L_0x00ee;
        L_0x00d7:
            r1 = r0.m4772c();	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            if (r1 != 0) goto L_0x00e2;
        L_0x00dd:
            r1 = r8.f7897e;	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
            r1.m11443a(r0);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
        L_0x00e2:
            r1 = 1;
        L_0x00e3:
            if (r1 != 0) goto L_0x00e8;
        L_0x00e5:
            r4.add(r0);	 Catch:{ IOException -> 0x006d, RuntimeException -> 0x0112 }
        L_0x00e8:
            r1 = r2 + 1;
            r2 = r1;
            r1 = r0;
            goto L_0x0008;
        L_0x00ee:
            r1 = r3;
            goto L_0x00e3;
        L_0x00f0:
            r0 = r8.f7893a;
            r0 = r0.size();
            if (r2 >= r0) goto L_0x0107;
        L_0x00f8:
            r0 = r8.f7893a;
            r1 = r8.f7893a;
            r1 = r1.size();
            r0 = r0.subList(r2, r1);
            r4.addAll(r0);
        L_0x0107:
            r0 = r8.f7893a;
            r0.clear();
            r0 = r8.f7893a;
            r0.addAll(r4);
            return;
        L_0x0112:
            r1 = move-exception;
            r7 = r1;
            r1 = r0;
            r0 = r7;
        L_0x0116:
            r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ae }
            r5 = "RunTimeException: ";
            r3.<init>(r5);	 Catch:{ all -> 0x00ae }
            r1 = r1.m4778i();	 Catch:{ all -> 0x00ae }
            r3.append(r1);	 Catch:{ all -> 0x00ae }
            throw r0;	 Catch:{ all -> 0x00ae }
        L_0x0125:
            r0 = move-exception;
            goto L_0x0116;
        L_0x0127:
            r0 = move-exception;
            goto L_0x0071;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.u.h.d.a(java.io.DataInputStream):void");
        }
    }

    /* renamed from: com.google.android.m4b.maps.u.h.e */
    static class DataRequestDispatcher extends Exception {
        DataRequestDispatcher(String str) {
            super(str);
        }
    }

    /* renamed from: com.google.android.m4b.maps.u.h.f */
    public class DataRequestDispatcher implements DataRequestDispatcherInterface {
        private Vector<DataRequest> f7898a;
        private boolean f7899b;
        private final ProtoBuf f7900c;
        private /* synthetic */ DataRequestDispatcher f7901d;

        private DataRequestDispatcher(DataRequestDispatcher dataRequestDispatcher) {
            this.f7901d = dataRequestDispatcher;
            this.f7898a = new Vector();
            this.f7899b = false;
            this.f7900c = new ProtoBuf(ClientProperties.f7002a);
        }

        public final String m11391m() {
            return this.f7900c.m10212h(5);
        }

        public final void m11384a(int i) {
            this.f7900c.m10210f(25, i);
        }

        public final void m11388c(DataRequest dataRequest) {
            if (false) {
                new StringBuilder("Offline - Dropped Request: ").append(dataRequest.m4778i());
                return;
            }
            new StringBuilder("Add Data Request: ").append(dataRequest.m4778i());
            DataRequestDispatcher dataRequestDispatcher = this.f7901d;
            synchronized (this) {
                if (dataRequest.m4769a()) {
                    this.f7899b = true;
                }
                this.f7898a.add(dataRequest);
            }
            if (dataRequest.m4769a() && !m11382a()) {
                this.f7901d.f7914d.m11358a();
            }
        }

        public final void m11385a(int i, byte[] bArr, boolean z, boolean z2) {
            m11386a(i, bArr, z, z2, false);
        }

        public final void m11386a(int i, byte[] bArr, boolean z, boolean z2, boolean z3) {
            m11388c(new SimpleDataRequest(i, bArr, z, z2, z3, null));
        }

        private DataRequestDispatcher m11380a(boolean z) {
            List<Pair> a = ar.m2515a();
            synchronized (this) {
                if (this.f7898a.size() == 0) {
                    return null;
                } else if (!z || this.f7899b) {
                    DataRequestDispatcher dataRequestDispatcher = new DataRequestDispatcher(this.f7901d, this.f7898a, this.f7900c);
                    this.f7898a = new Vector();
                    this.f7899b = false;
                    for (Pair pair : a) {
                        UserEventReporter.m11506a((String) pair.first, (String) pair.second);
                    }
                    return dataRequestDispatcher;
                } else {
                    return null;
                }
            }
        }

        public final void m11387a(DataRequestListener dataRequestListener) {
            DataRequestDispatcher.f7903I.m11444a(dataRequestListener);
        }

        public final long m11390l() {
            return DataRequestDispatcher.f7903I.m11465l();
        }

        private synchronized boolean m11382a() {
            return DataRequestDispatcher.f7903I.m11453c();
        }

        public final void m11389j() {
            DataRequestDispatcher.f7903I.m11463j();
        }
    }

    static /* synthetic */ void m11399a(DataRequestDispatcher dataRequestDispatcher, int i) {
        int i2 = i > HttpStatus.SC_OK ? 3 : 1;
        for (DataRequestDispatcher a : dataRequestDispatcher.f7923m) {
            a.f7900c.m10210f(22, i2);
        }
    }

    static /* synthetic */ void m11400a(DataRequestDispatcher dataRequestDispatcher, int i, Throwable th) {
        Object obj = null;
        synchronized (dataRequestDispatcher) {
            ConnectionFactory connectionFactory = dataRequestDispatcher.f7918h;
            dataRequestDispatcher.f7910G = i;
            if (i == 4) {
                if (dataRequestDispatcher.f7934x == 0 || dataRequestDispatcher.f7933w) {
                    dataRequestDispatcher.m11436s();
                    dataRequestDispatcher.f7910G = i;
                    dataRequestDispatcher.f7934x = 200;
                } else if (dataRequestDispatcher.f7934x < dataRequestDispatcher.f7928r) {
                    dataRequestDispatcher.f7934x *= 2;
                }
            } else if (dataRequestDispatcher.f7933w) {
                if (dataRequestDispatcher.f7934x < 2000) {
                    dataRequestDispatcher.f7934x = 2000;
                } else {
                    dataRequestDispatcher.f7934x = (dataRequestDispatcher.f7934x * 5) / 4;
                }
                if (dataRequestDispatcher.f7934x > dataRequestDispatcher.f7928r) {
                    dataRequestDispatcher.f7934x = dataRequestDispatcher.f7928r;
                }
            } else {
                dataRequestDispatcher.f7934x = 200;
                if (dataRequestDispatcher.f7935y == Long.MIN_VALUE) {
                    dataRequestDispatcher.f7935y = dataRequestDispatcher.f7908E.m10152b();
                } else if (dataRequestDispatcher.f7935y + 15000 < dataRequestDispatcher.f7908E.m10152b()) {
                    obj = 1;
                }
            }
        }
        if (obj != null) {
            dataRequestDispatcher.m11438a(i);
        }
    }

    static /* synthetic */ int m11433p() {
        int i = f7902H;
        f7902H = i + 1;
        return i;
    }

    static {
        f7902H = 0;
    }

    private synchronized void m11404a(boolean z, boolean z2) {
        this.f7936z++;
        if (z) {
            this.f7904A++;
        }
        if (z2) {
            this.f7905B++;
        }
    }

    private synchronized void m11414b(boolean z, boolean z2) {
        this.f7936z--;
        if (z) {
            this.f7904A--;
        }
        if (z2) {
            this.f7905B--;
        }
    }

    private static synchronized DataRequestDispatcher m11410b(String str, String str2, String str3, boolean z) {
        DataRequestDispatcher dataRequestDispatcher;
        synchronized (DataRequestDispatcher.class) {
            if (f7903I != null) {
                throw new RuntimeException("Attempting to create multiple DataRequestDispatchers");
            }
            dataRequestDispatcher = new DataRequestDispatcher(DataRequestDispatcher.m11422g(str), str2, str3, z);
            f7903I = dataRequestDispatcher;
        }
        return dataRequestDispatcher;
    }

    private static String m11422g(String str) {
        if (str == null || !str.startsWith("http:")) {
            return str;
        }
        return "https:" + str.substring(5);
    }

    public static DataRequestDispatcher m11393a() {
        return f7903I;
    }

    public static DataRequestDispatcherInterface m11411b() {
        DataRequestDispatcher dataRequestDispatcher = f7903I;
        return null;
    }

    private DataRequestDispatcher(String str, String str2, String str3, boolean z) {
        this.f7922l = false;
        this.f7926p = ar.m2515a();
        this.f7927q = new Random();
        this.f7928r = 30000;
        this.f7929s = false;
        this.f7931u = Long.MIN_VALUE;
        this.f7932v = Long.MIN_VALUE;
        this.f7934x = 0;
        this.f7935y = Long.MIN_VALUE;
        this.f7936z = 0;
        this.f7904A = 0;
        this.f7905B = 0;
        this.f7909F = -1;
        this.f7910G = -1;
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException();
        }
        this.f7920j = DataRequestDispatcher.m11422g(str);
        this.f7912b = str3;
        this.f7911a = str2;
        this.f7924n = DataRequestDispatcher.m11430n();
        this.f7913c = z;
        this.f7918h = Config.m11320a().m10150r();
        this.f7908E = new Clock();
        this.f7919i = new ConnectionWarmUpManager(this, this.f7908E);
        this.f7916f = 0;
        this.f7917g = 0;
        this.f7914d = new DataRequestDispatcher(this.f7920j, (byte) 0);
        this.f7923m = new ArrayList(2);
        this.f7915e = new DataRequestDispatcher();
        this.f7923m.add(this.f7915e);
        new StringBuilder("Using server: ").append(this.f7920j);
    }

    private void m11397a(int i, boolean z) {
        for (DataRequestDispatcher a : this.f7923m) {
            a.f7900c.m10185a(i, z);
        }
    }

    private void m11396a(int i, String str) {
        for (DataRequestDispatcher a : this.f7923m) {
            a.f7900c.m10197b(i, str);
        }
    }

    public final synchronized boolean m11453c() {
        return this.f7930t > 0;
    }

    public final synchronized void m11454d() {
        this.f7930t++;
    }

    public final void m11456e() {
        synchronized (this) {
            this.f7930t--;
            if (m11453c()) {
                return;
            }
            this.f7914d.m11362b();
            this.f7919i.m11341a();
        }
    }

    private static long m11434q() {
        Config.m11327n();
        DataInput b = Config.m11320a().m11330d().m10157b("SessionID");
        if (b != null) {
            try {
                return b.readLong();
            } catch (IOException e) {
                Config.m11320a().m10149q().m7754a("SessionID", null);
            }
        }
        return 0;
    }

    public final synchronized void m11444a(DataRequestListener dataRequestListener) {
        if (!this.f7926p.contains(dataRequestListener)) {
            this.f7926p.add(dataRequestListener);
        }
    }

    public final synchronized void m11448b(DataRequestListener dataRequestListener) {
        this.f7926p.remove(dataRequestListener);
    }

    private synchronized DataRequestListener[] m11435r() {
        DataRequestListener[] dataRequestListenerArr;
        dataRequestListenerArr = new DataRequestListener[this.f7926p.size()];
        this.f7926p.toArray(dataRequestListenerArr);
        return dataRequestListenerArr;
    }

    protected final void m11443a(DataRequest dataRequest) {
        for (DataRequestListener a : m11435r()) {
            a.m6181a(dataRequest);
        }
    }

    protected final void m11439a(int i, boolean z, String str) {
        for (DataRequestListener a : m11435r()) {
            a.m6180a(i, z, str);
        }
    }

    protected final void m11447b(DataRequest dataRequest) {
        for (DataRequestListener b : m11435r()) {
            b.m6183b(dataRequest);
        }
    }

    protected final void m11458f() {
        for (DataRequestListener a : m11435r()) {
            a.m6179a();
        }
    }

    protected final void m11460g() {
        for (DataRequestListener b : m11435r()) {
            b.m6182b();
        }
    }

    protected final void m11438a(int i) {
        Object obj = 1;
        synchronized (this) {
            if (this.f7933w) {
                obj = null;
            } else {
                this.f7933w = true;
                this.f7935y = Long.MIN_VALUE;
            }
        }
        boolean a = this.f7918h.m7746a();
        if (obj != null) {
            m11439a(i, a, null);
        }
    }

    public final void m11451c(DataRequest dataRequest) {
        this.f7915e.m11388c(dataRequest);
    }

    public final synchronized boolean m11461h() {
        return this.f7933w;
    }

    public final synchronized boolean m11462i() {
        boolean z;
        z = this.f7929s && this.f7936z < 10 && (this.f7918h.m7748b() || this.f7936z == 0);
        return z;
    }

    public final void m11463j() {
        this.f7929s = false;
    }

    public final void m11464k() {
        this.f7929s = true;
        this.f7914d.m11362b();
    }

    private synchronized void m11436s() {
        this.f7935y = Long.MIN_VALUE;
        this.f7933w = false;
        this.f7934x = 0;
        this.f7910G = -1;
    }

    public final void m11440a(int i, byte[] bArr, boolean z, boolean z2) {
        m11441a(i, bArr, z, z2, false);
    }

    public final void m11441a(int i, byte[] bArr, boolean z, boolean z2, boolean z3) {
        this.f7915e.m11386a(i, bArr, z, z2, z3);
    }

    protected static boolean m11406a(Vector<DataRequest> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (((DataRequest) vector.elementAt(i)).m4771b()) {
                return true;
            }
        }
        return false;
    }

    protected static boolean m11415b(Vector<DataRequest> vector) {
        for (int i = 0; i < vector.size(); i++) {
            vector.elementAt(i);
        }
        return false;
    }

    protected static boolean m11417c(Vector<DataRequest> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (((DataRequest) vector.elementAt(i)).m4774e()) {
                return true;
            }
        }
        return false;
    }

    static void m11398a(long j) {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new DataOutputStream(byteArrayOutputStream).writeLong(j);
            PersistentStore q = Config.m11320a().m10149q();
            q.m7754a("SessionID", byteArrayOutputStream.toByteArray());
            q.m7753a();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public final synchronized long m11465l() {
        if (this.f7925o == null) {
            long q = DataRequestDispatcher.m11434q();
            if (q == 0) {
                this.f7915e.m11388c(new DataRequestDispatcher());
            }
            this.f7925o = Long.valueOf(q);
        }
        return this.f7925o.longValue();
    }

    public final void m11446a(boolean z) {
        this.f7921k = true;
    }

    public final void m11445a(String str) {
        this.f7906C = str;
    }

    public final void m11449b(String str) {
        m11396a(18, str);
    }

    public final void m11452c(String str) {
        m11396a(40, str);
    }

    public final void m11455d(String str) {
        m11396a(39, str);
    }

    public final void m11457e(String str) {
        m11396a(19, str);
    }

    public final void m11459f(String str) {
        m11396a(38, str);
    }

    public final void m11450b(boolean z) {
        m11397a(45, true);
    }

    public final String m11466m() {
        return this.f7915e.f7900c.m10212h(5);
    }

    private ShortbreadToken m11437t() {
        if (this.f7907D == null) {
            this.f7907D = new ShortbreadToken();
        }
        return this.f7907D;
    }

    public final void m11442a(ProtoBuf protoBuf) {
        m11437t().m11479a(protoBuf);
    }

    public static String m11430n() {
        String h = DataRequestDispatcher.m11424h("maps_client_id");
        StringBuilder stringBuilder = new StringBuilder();
        if (h == null || h.length() == 0) {
            stringBuilder.append("Web");
        } else {
            stringBuilder.append(h);
        }
        return stringBuilder.toString();
    }

    private static String m11424h(String str) {
        Cursor query;
        Object th;
        Throwable th2;
        Cursor cursor = null;
        try {
            String string;
            query = Config.m11320a().m11338l().getContentResolver().query(Uri.parse("content://com.google.settings/partner"), new String[]{"value"}, "name='" + str + "'", null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        string = query.getString(query.getColumnIndexOrThrow("value"));
                        if (query != null) {
                            return string;
                        }
                        query.close();
                        return string;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        new StringBuilder("Error getting distribution channel for key ").append(str).append(": ").append(th);
                        if (query != null) {
                            return null;
                        }
                        query.close();
                        return null;
                    } catch (Throwable th4) {
                        th2 = th4;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th2;
                    }
                }
            }
            string = null;
            if (query != null) {
                return string;
            }
            query.close();
            return string;
        } catch (Throwable th5) {
            th2 = th5;
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
    }
}
