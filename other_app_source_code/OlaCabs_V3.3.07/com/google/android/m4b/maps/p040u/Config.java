package com.google.android.m4b.maps.p040u;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.google.android.m4b.maps.bh.DummyPaintParameterRequest;
import com.google.android.m4b.maps.ci.BuildData;
import com.google.android.m4b.maps.cm.BaseConfig;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.cm.PreferencesUtil;
import com.google.android.m4b.maps.p041b.PersistentStore;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p054p.Assert;
import com.google.android.m4b.maps.p054p.MathUtil;
import com.olacabs.customer.p076d.br;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.http.HttpStatus;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsFilesManager;

/* renamed from: com.google.android.m4b.maps.u.e */
public final class Config extends BaseConfig {
    private static String[] f7862d;
    private static volatile Boolean f7863e;
    private Context f7864f;
    private int f7865g;
    private float f7866h;
    private final float f7867i;
    private final float f7868j;
    private final ProtoBuf f7869k;
    private I18n f7870l;

    protected Config() {
        throw new IllegalStateException("not in a unit or feature test");
    }

    private Config(Context context) {
        this(context, null);
    }

    private Config(Context context, PersistentStore persistentStore) {
        String d;
        super(context, null);
        this.f7864f = context;
        m11328s();
        if (context != null) {
            this.f7865g = context.getResources().getDisplayMetrics().densityDpi;
            this.f7866h = context.getResources().getDisplayMetrics().density;
        } else {
            this.f7865g = 160;
            this.f7866h = br.DEFAULT_BACKOFF_MULT;
        }
        float f = (float) this.f7865g;
        if (context != null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (((double) (Math.abs(displayMetrics.xdpi - f) / f)) > 0.25d || ((double) (Math.abs(displayMetrics.ydpi - f) / f)) > 0.25d) {
                this.f7867i = f;
                this.f7868j = f;
            } else {
                this.f7867i = displayMetrics.xdpi;
                this.f7868j = displayMetrics.ydpi;
            }
        } else {
            this.f7867i = f;
            this.f7868j = f;
        }
        String locale = Locale.getDefault().toString();
        this.f7870l.m11474b(locale);
        I18n i18n = this.f7870l;
        if (f7862d == null) {
            int i;
            String[] split = "en ar bg ca cs da de el en_GB es es_MX et fi fr hr hu it iw ja ko lt lv nl no pl pt_BR pt_PT ro ru sk sl sr sv tl tr uk vi zh zh_CN".split(" ");
            if (VERSION.SDK_INT >= 14) {
                i = 1;
            } else {
                i = 0;
            }
            if (i == 0) {
                ArrayList arrayList = new ArrayList(split.length);
                for (String str : split) {
                    d = I18n.m11470d(I18n.m11468a(str));
                    if (d.equals("ar") || d.equals("fa") || d.equals("iw")) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    if (i == 0) {
                        arrayList.add(str);
                    }
                }
                split = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            f7862d = split;
        }
        String a = I18n.m11469a(locale, f7862d);
        d = I18n.m11471e(locale);
        if (Config.m11322a(I18n.m11471e(a)) && !Config.m11322a(d)) {
            a = a + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + d;
        }
        i18n.m11475c(a);
        ProtoBuf protoBuf = new ProtoBuf(DummyPaintParameterRequest.f6229a);
        protoBuf.m10197b(1, Config.m11324b());
        protoBuf.m10197b(2, Config.m11326m());
        protoBuf.m10184a(3, (long) BuildData.m10145a());
        protoBuf.m10197b(4, DataRequestDispatcher.m11430n());
        protoBuf.m10197b(5, String.valueOf(VERSION.SDK_INT));
        protoBuf.m10197b(6, context.getPackageName());
        protoBuf.m10197b(7, String.valueOf(Config.m11323b(context).versionCode));
        protoBuf.m10197b(8, I18n.m11471e(Config.m11324b()));
        protoBuf.m10183a(9, this.f7866h);
        this.f7869k = protoBuf;
    }

    public static Config m11321a(Context context) {
        synchronized (b) {
            if (a == null) {
                a = new Config(context);
            }
        }
        Assert.m11064a(a instanceof Config);
        return (Config) a;
    }

    public static Config m11320a() {
        return (Config) a;
    }

    private void m11328s() {
        if (this.f7870l == null) {
            this.f7870l = new I18n(null);
        }
    }

    public static synchronized String m11324b() {
        String a;
        synchronized (Config.class) {
            a = ((Config) a).f7870l.m11473a();
        }
        return a;
    }

    public static String m11325c() {
        return "6.18.0";
    }

    public final PreferencesUtil m11330d() {
        return new PreferencesUtil(m10149q());
    }

    public final int m11331e() {
        return this.f7865g;
    }

    public final float m11332f() {
        return this.f7867i;
    }

    public final float m11333g() {
        return this.f7868j;
    }

    public final Clock m11334h() {
        return this.c;
    }

    public final double m11335i() {
        return (double) this.f7866h;
    }

    public final boolean m11336j() {
        return this.f7865g > HttpStatus.SC_OK;
    }

    public final int m11329a(int i) {
        return MathUtil.m11078a(20.0d * ((double) this.f7866h));
    }

    public final boolean m11337k() {
        if (f7863e == null) {
            f7863e = Boolean.valueOf(this.f7864f.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct"));
        }
        return f7863e.booleanValue();
    }

    private static boolean m11322a(String str) {
        return str == null || str.length() == 0;
    }

    public final Context m11338l() {
        return this.f7864f;
    }

    public static String m11326m() {
        return "android:" + Build.MANUFACTURER.replace('-', '_') + "-" + Build.DEVICE.replace('-', '_') + "-" + Build.MODEL.replace('-', '_');
    }

    public static void m11327n() {
    }

    private static PackageInfo m11323b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    public final ProtoBuf m11339o() {
        return this.f7869k;
    }
}
