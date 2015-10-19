package com.google.android.m4b.maps.p058v;

import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.ExceptionReporter;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import java.io.DataInput;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.v.c */
public final class Stats {
    private static final String[] f7993a;
    private static List<Stats> f7994b;
    private int f7995c;
    private int f7996d;
    private int f7997e;
    private int f7998f;
    private int f7999g;
    private int f8000h;
    private final String f8001i;

    static {
        f7993a = new String[]{"c", "v"};
    }

    private Stats(String str) {
        this.f7995c = 0;
        this.f7996d = 0;
        this.f7998f = 0;
        this.f7999g = 0;
        this.f7997e = 0;
        this.f8000h = 0;
        this.f8001i = str;
    }

    public static synchronized Stats m11537a(byte b) {
        Stats stats;
        synchronized (Stats.class) {
            if (f7994b == null) {
                f7994b = Stats.m11539d();
            }
            if (1 >= f7994b.size()) {
                stats = null;
            } else {
                stats = (Stats) f7994b.get(1);
            }
        }
        return stats;
    }

    public final void m11540a() {
        synchronized (this) {
            this.f7997e++;
            this.f8000h++;
        }
    }

    public final void m11541b() {
        synchronized (this) {
            this.f7995c++;
            this.f7998f++;
        }
        m11538a(false);
    }

    public final void m11542c() {
        synchronized (this) {
            this.f7996d++;
            this.f7999g++;
        }
        m11538a(false);
    }

    private static List<Stats> m11539d() {
        int i;
        int i2 = 0;
        DataInput b = Config.m11320a().m11330d().m10157b("CacheHitStats");
        List<Stats> arrayList = new ArrayList();
        if (b != null) {
            try {
                if (b.readInt() == 2) {
                    i = 0;
                    while (true) {
                        String[] strArr = f7993a;
                        if (i >= 2) {
                            break;
                        }
                        Stats stats = new Stats(f7993a[i]);
                        stats.f7995c = b.readInt();
                        stats.f7996d = b.readInt();
                        b.readInt();
                        b.readInt();
                        stats.f7997e = b.readInt();
                        arrayList.add(stats);
                        i++;
                    }
                }
            } catch (Throwable e) {
                ExceptionReporter.m11467a("STATS", e);
                Config.m11320a().m10149q().m7756b("CacheHitStats");
            }
        }
        i = arrayList.size();
        String[] strArr2 = f7993a;
        if (i != 2) {
            arrayList.clear();
            while (true) {
                String[] strArr3 = f7993a;
                if (i2 >= 2) {
                    break;
                }
                arrayList.add(new Stats(f7993a[i2]));
                i2++;
            }
        }
        return arrayList;
    }

    private void m11538a(boolean z) {
        synchronized (this) {
            int i = this.f7998f;
            int i2 = this.f7999g;
            int i3 = this.f8000h;
            if (i + i2 <= 50) {
                return;
            }
            this.f7998f = 0;
            this.f7999g = 0;
            this.f8000h = 0;
            StringBuilder stringBuilder = new StringBuilder();
            if (i > 0) {
                stringBuilder.append("|");
                stringBuilder.append("f");
                stringBuilder.append("=");
                stringBuilder.append(i);
            }
            if (i2 > 0) {
                stringBuilder.append("|");
                stringBuilder.append("m");
                stringBuilder.append("=");
                stringBuilder.append(i2);
            }
            if (i3 > 0) {
                stringBuilder.append("|");
                stringBuilder.append("r");
                stringBuilder.append("=");
                stringBuilder.append(i3);
            }
            stringBuilder.append("|");
            UserEventReporter.m11502a(22, this.f8001i, stringBuilder.toString());
        }
    }
}
