package com.google.android.m4b.maps.p040u;

import com.google.android.m4b.maps.bx.UserEvents;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p039o.Task;
import com.google.android.m4b.maps.p039o.TaskRunner;
import com.google.android.m4b.maps.p040u.TaskRunnerManager.TaskRunnerManager;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.u.r */
public class UserEventReporter {
    private static final HashSet<Short> f7962a;
    private static Clock f7963b;
    private static boolean f7964c;
    private static boolean f7965d;
    private static UserEventReporter f7966e;
    private static UserEventReporter f7967f;
    private static final Object f7968g;
    private volatile int f7969h;
    private int f7970i;

    /* renamed from: com.google.android.m4b.maps.u.r.1 */
    static class UserEventReporter extends Task {
        private /* synthetic */ int f7954b;
        private /* synthetic */ String f7955c;
        private /* synthetic */ String f7956d;
        private /* synthetic */ ProtoBuf f7957e;
        private /* synthetic */ long f7958f;
        private /* synthetic */ boolean f7959g;

        UserEventReporter(TaskRunner taskRunner, int i, String str, String str2, ProtoBuf protoBuf, long j, boolean z) {
            this.f7954b = i;
            this.f7955c = str;
            this.f7956d = str2;
            this.f7957e = protoBuf;
            this.f7958f = j;
            this.f7959g = z;
            super(taskRunner);
        }

        public final void m11490f() {
            UserEventReporter.m11511b(this.f7954b, this.f7955c, this.f7956d, this.f7957e, this.f7958f, this.f7959g);
        }
    }

    /* renamed from: com.google.android.m4b.maps.u.r.2 */
    class UserEventReporter extends Task {
        private /* synthetic */ UserEventReporter f7960b;

        UserEventReporter(UserEventReporter userEventReporter, TaskRunner taskRunner) {
            this.f7960b = userEventReporter;
            super(taskRunner);
        }

        public final void m11491f() {
            this.f7960b.m11507a(false);
        }
    }

    /* renamed from: com.google.android.m4b.maps.u.r.3 */
    class UserEventReporter extends SimpleDataRequest {
        private /* synthetic */ UserEventReporter f7961a;

        UserEventReporter(UserEventReporter userEventReporter, int i, byte[] bArr, boolean z, boolean z2, boolean z3, Object obj) {
            this.f7961a = userEventReporter;
            super(i, bArr, z, false, z3, null);
        }

        public final void m11493h() {
            this.f7961a.m11519c();
        }

        public final void m11492g() {
            synchronized (UserEventReporter.class) {
                if (!false) {
                    if (UserEventReporter.m11516g()) {
                        if (UserEventReporter.f7966e.m11494a() != null) {
                            UserEventReporter.f7966e.m11495a(UserEventReporter.m11498a(UserEventReporter.f7966e.m11494a(), this.f7961a.f7969h));
                        } else {
                            ExceptionReporter.m11467a("USER_EVENTSUserEventReporter", new NullPointerException(UserEventReporter.class.toString() + ".uploadEventLog"));
                        }
                    }
                    this.f7961a.m11519c();
                }
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.u.r.a */
    public interface UserEventReporter {
        ProtoBuf m11494a();

        void m11495a(ProtoBuf protoBuf);

        void m11496b();
    }

    public UserEventReporter() {
        this.f7970i = 0;
    }

    static {
        f7963b = new Clock();
        HashSet hashSet = new HashSet();
        f7962a = hashSet;
        hashSet.add(Short.valueOf((short) 18));
        f7962a.add(Short.valueOf((short) 81));
        f7962a.add(Short.valueOf((short) 91));
        f7962a.add(Short.valueOf((short) 92));
        f7962a.add(Short.valueOf((short) 1));
        f7962a.add(Short.valueOf((short) 4));
        f7962a.add(Short.valueOf((short) 61));
        f7962a.add(Short.valueOf((short) 104));
        f7962a.add(Short.valueOf((short) 67));
        f7962a.add(Short.valueOf((short) 16));
        f7962a.add(Short.valueOf((short) 69));
        f7962a.add(Short.valueOf((short) 101));
        f7962a.add(Short.valueOf((short) 84));
        f7962a.add(Short.valueOf((short) 87));
        f7962a.add(Short.valueOf((short) 55));
        f7962a.add(Short.valueOf((short) 85));
        f7962a.add(Short.valueOf((short) 97));
        f7962a.add(Short.valueOf((short) 5));
        f7962a.add(Short.valueOf((short) 79));
        f7962a.add(Short.valueOf((short) 57));
        f7962a.add(Short.valueOf((short) 112));
        f7962a.add(Short.valueOf((short) 114));
        f7962a.add(Short.valueOf((short) 118));
        f7962a.add(Short.valueOf((short) 115));
        f7964c = false;
        f7965d = false;
        f7966e = null;
        f7968g = new Object();
    }

    private static boolean m11516g() {
        if (f7965d && f7966e != null) {
            return true;
        }
        return false;
    }

    public static UserEventReporter m11499a() {
        synchronized (f7968g) {
            if (f7967f == null) {
                f7967f = new UserEventReporter();
            }
        }
        return f7967f;
    }

    public static synchronized void m11504a(UserEventReporter userEventReporter) {
        synchronized (UserEventReporter.class) {
            f7966e = userEventReporter;
            f7965d = userEventReporter != null;
        }
    }

    public static void m11501a(int i, String str) {
        UserEventReporter.m11502a(99, str, Trace.NULL);
    }

    public static void m11502a(int i, String str, String str2) {
        if (Config.m11320a() != null) {
            long a = f7963b.m10151a();
            if (UserEventReporter.m11516g()) {
                new UserEventReporter(TaskRunnerManager.f7953a, i, str, str2, null, a, false).m4754d();
            }
        }
    }

    public static void m11506a(String str, String str2) {
        UserEventReporter.m11502a(78, str, str2);
    }

    private void m11517h() {
        synchronized (UserEventReporter.class) {
            if (UserEventReporter.m11516g()) {
                ProtoBuf b = UserEventReporter.m11509b(f7966e.m11494a());
                int k = b.m10215k(2);
                b = UserEventReporter.m11512c(b);
                this.f7969h -= k - b.m10215k(2);
                if (this.f7969h < 0) {
                    this.f7969h = 0;
                }
                f7966e.m11495a(b);
            }
        }
    }

    private static synchronized void m11511b(int i, String str, String str2, ProtoBuf protoBuf, long j, boolean z) {
        synchronized (UserEventReporter.class) {
            new StringBuilder("Event: type=").append(i).append(", status: ").append(str).append(", data: ").append(str2);
            ProtoBuf a = f7966e.m11494a();
            if (a == null) {
                UserEventReporter.m11518i();
                a = f7966e.m11494a();
            }
            ProtoBuf protoBuf2 = new ProtoBuf(UserEvents.f6925b);
            protoBuf2.m10210f(1, i);
            protoBuf2.m10184a(7, j);
            protoBuf2.m10197b(3, str);
            protoBuf2.m10197b(4, str2);
            protoBuf2.m10185a(6, z);
            if (protoBuf != null) {
                protoBuf2.m10196b(5, protoBuf);
            }
            a.m10190a(2, protoBuf2);
            f7966e.m11495a(a);
            if (a.m10201c() > 1200) {
                UserEventReporter a2 = UserEventReporter.m11499a();
                synchronized (UserEventReporter.class) {
                    if (UserEventReporter.m11516g()) {
                        a2.f7970i++;
                        if (a2.f7970i == 1) {
                            a2.m11507a(false);
                        }
                    }
                }
                if (UserEventReporter.m11499a().f7970i > 1) {
                    UserEventReporter.m11499a().m11517h();
                }
            }
        }
    }

    public static String m11500a(String[] strArr) {
        if (strArr.length == 0) {
            return Trace.NULL;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|");
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] != null) {
                stringBuilder.append(new StringBuilder(strArr[i].replace("|", Trace.NULL)));
                stringBuilder.append("|");
            }
        }
        return stringBuilder.toString();
    }

    public static synchronized void m11510b() {
        synchronized (UserEventReporter.class) {
            if (UserEventReporter.m11516g()) {
                f7966e.m11496b();
            }
        }
    }

    private void m11507a(boolean z) {
        ProtoBuf a = f7966e.m11494a();
        if (a != null) {
            synchronized (UserEventReporter.class) {
                if (!UserEventReporter.m11516g() || a.m10215k(2) == 0) {
                    this.f7970i = 0;
                    return;
                }
                int i;
                ProtoBuf c;
                boolean z2;
                long a2 = f7963b.m10151a();
                Set set = f7962a;
                a.m10215k(2);
                for (i = 0; i < a.m10215k(2); i++) {
                    c = a.m10202c(2, i);
                    if (c != null && set.contains(Short.valueOf((short) c.m10204d(1)))) {
                        z2 = true;
                        break;
                    }
                }
                z2 = false;
                a.m10184a(3, a2);
                c = new ProtoBuf(UserEvents.f6924a);
                for (i = 0; i < a.m10215k(2); i++) {
                    ProtoBuf c2 = a.m10202c(2, i);
                    if (c2.m10214j(7)) {
                        if ((f7963b.m10151a() - c2.m10207e(7) > 2592000000L ? 1 : null) == null) {
                            c.m10190a(2, c2);
                        }
                    }
                }
                if (c.m10214j(2) && a.m10214j(3)) {
                    c.m10184a(3, a.m10207e(3));
                }
                byte[] a3 = UserEventReporter.m11508a(c);
                ProtoBuf b = UserEventReporter.m11509b(c);
                if (b.m10214j(2)) {
                    f7966e.m11495a(UserEventReporter.m11512c(b));
                } else {
                    UserEventReporter.m11518i();
                }
                this.f7969h = b.m10215k(2);
                DataRequest userEventReporter = new UserEventReporter(this, 125, a3, z, false, z2, null);
                DataRequestDispatcher a4 = DataRequestDispatcher.m11393a();
                if (a4 != null) {
                    a4.m11451c(userEventReporter);
                }
            }
        }
    }

    public final void m11519c() {
        synchronized (UserEventReporter.class) {
            if (this.f7970i > 1) {
                this.f7970i = 1;
                new UserEventReporter(this, TaskRunnerManager.f7953a).m4754d();
            } else {
                this.f7970i = 0;
            }
        }
    }

    private static byte[] m11508a(ProtoBuf protoBuf) {
        int k = protoBuf.m10215k(2);
        ProtoBuf protoBuf2 = new ProtoBuf(UserEvents.f6924a);
        if (protoBuf.m10214j(3)) {
            protoBuf2.m10184a(3, protoBuf.m10207e(3));
        }
        long j = 0;
        int i = 0;
        while (i < k) {
            long j2;
            ProtoBuf c = protoBuf.m10202c(2, i);
            if (c.m10213i(7)) {
                long e = c.m10207e(7);
                j = e - j;
                if (i > 0 && j >= 0 && j <= 6553500) {
                    c = c.m10181a();
                    c.m10208e(7, 0);
                    c.m10184a(2, j / 100);
                }
                protoBuf2.m10190a(2, c);
                j2 = e;
            } else {
                j2 = j;
            }
            i++;
            j = j2;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            byte[] d = protoBuf2.m10206d();
            dataOutputStream.writeInt(d.length);
            dataOutputStream.write(d);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            return new byte[]{(byte) 0};
        }
    }

    static ProtoBuf m11498a(ProtoBuf protoBuf, int i) {
        int k = protoBuf.m10215k(2);
        ProtoBuf protoBuf2 = new ProtoBuf(UserEvents.f6924a);
        for (int min = Math.min(i, k); min < k; min++) {
            protoBuf2.m10190a(2, protoBuf.m10202c(2, min));
        }
        if (protoBuf.m10214j(3) && protoBuf2.m10214j(2)) {
            protoBuf2.m10184a(3, protoBuf.m10207e(3));
        }
        return protoBuf2;
    }

    private static ProtoBuf m11509b(ProtoBuf protoBuf) {
        ProtoBuf protoBuf2 = new ProtoBuf(UserEvents.f6924a);
        for (int i = 0; i < protoBuf.m10215k(2); i++) {
            ProtoBuf c = protoBuf.m10202c(2, i);
            if (c.m10200b(6)) {
                protoBuf2.m10190a(2, c);
            }
        }
        if (protoBuf2.m10214j(2) && protoBuf.m10214j(3)) {
            protoBuf2.m10184a(3, protoBuf.m10207e(3));
        }
        return protoBuf2;
    }

    private static ProtoBuf m11512c(ProtoBuf protoBuf) {
        int c = protoBuf.m10201c() - 800;
        if (c <= 0) {
            return protoBuf;
        }
        for (int i = 0; i < protoBuf.m10215k(2); i++) {
            c -= protoBuf.m10202c(2, i).m10201c() + 2;
            if (c <= 0) {
                return UserEventReporter.m11498a(protoBuf, i + 1);
            }
        }
        return protoBuf;
    }

    private static synchronized void m11518i() {
        synchronized (UserEventReporter.class) {
            if (f7966e != null) {
                f7966e.m11495a(new ProtoBuf(UserEvents.f6924a));
            }
        }
    }
}
