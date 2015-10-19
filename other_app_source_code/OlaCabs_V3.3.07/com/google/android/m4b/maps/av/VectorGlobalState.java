package com.google.android.m4b.maps.av;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import com.google.android.m4b.maps.ae.PerformanceProfile;
import com.google.android.m4b.maps.al.IndoorState;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.ap.IndoorBuildingStore;
import com.google.android.m4b.maps.ap.TileCrypt;
import com.google.android.m4b.maps.ap.TileStore;
import com.google.android.m4b.maps.ap.TileStoreFactory;
import com.google.android.m4b.maps.at.ResourceManager;
import com.google.android.m4b.maps.be.az;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.google.android.m4b.maps.p040u.UserEventReporter.UserEventReporter;
import com.google.android.m4b.maps.p058v.GmmSettings;
import com.google.android.m4b.maps.p058v.Util;
import java.io.File;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.av.p */
public final class VectorGlobalState {
    private static ai[] f4643a;
    private static ai[] f4644b;
    private static boolean f4645c;
    private static boolean f4646d;
    private static volatile int f4647e;
    private static volatile int f4648f;

    static {
        f4643a = new ai[]{ai.f3424b, ai.f3426d, ai.f3429g, ai.f3430h, ai.f3431i};
        f4644b = new ai[]{ai.f3423a, ai.f3425c, ai.f3426d, ai.f3428f, ai.f3427e, ai.f3432j, ai.f3434l, ai.f3433k, ai.f3435m, ai.f3436n, ai.f3437o, ai.f3438p};
        f4645c = false;
        f4647e = 10;
        f4648f = -1;
    }

    public static synchronized void m7283a(Context context, Resources resources, ai[] aiVarArr, String str, int i, UserEventReporter userEventReporter, DataRequestDispatcher dataRequestDispatcher) {
        synchronized (VectorGlobalState.class) {
            if (!f4645c) {
                SystemClock.uptimeMillis();
                PerformanceProfile.m4867a();
                Config.m11321a(context);
                Util.m11549a();
                com.google.android.m4b.maps.p040u.UserEventReporter.m11504a(userEventReporter);
                f4648f = ((ActivityManager) context.getSystemService("activity")).getMemoryClass();
                Util.m11548a(context).mkdirs();
                Util.m11554b(context).mkdir();
                Util.m11558c(context).mkdir();
                File c = Util.m11558c(context);
                ResourceManager.m6597a(dataRequestDispatcher, c);
                Locale locale = Locale.getDefault();
                if (i != -1) {
                    try {
                        TileCrypt.m6301a(resources.openRawResource(i));
                    } catch (Throwable e) {
                        Util.m11552a("Could not load encryption key", e);
                    }
                }
                boolean equals = "DriveAbout".equals(str);
                boolean equals2 = "GMM".equals(str);
                if (equals || equals2) {
                    VectorGlobalState.m7284a(f4644b, dataRequestDispatcher, locale, c, context, resources);
                    VectorGlobalState.m7284a(f4643a, DataRequestDispatcher.m11411b(), locale, c, context, resources);
                } else {
                    VectorGlobalState.m7284a(aiVarArr, dataRequestDispatcher, locale, c, context, resources);
                }
                if (GmmSettings.m11533g()) {
                    IndoorBuildingStore a = IndoorBuildingStore.m6255a(dataRequestDispatcher, c, locale, new Clock());
                    if (a != null) {
                        a.m6271d();
                        IndoorState.m5335a(a);
                    }
                }
                f4646d = az.m8761b(context);
                SystemClock.uptimeMillis();
                f4645c = true;
                PerformanceProfile.m4868b();
            }
        }
    }

    public static boolean m7285a() {
        return f4646d;
    }

    public static boolean m7286b() {
        return GmmSettings.m11533g();
    }

    public static synchronized TileStore m7281a(ai aiVar, Context context, Resources resources, DataRequestDispatcher dataRequestDispatcher) {
        TileStore a;
        synchronized (VectorGlobalState.class) {
            a = VectorGlobalState.m7282a(aiVar, (DataRequestDispatcherInterface) dataRequestDispatcher, context, resources);
        }
        return a;
    }

    public static synchronized TileStore m7282a(ai aiVar, DataRequestDispatcherInterface dataRequestDispatcherInterface, Context context, Resources resources) {
        TileStore b;
        synchronized (VectorGlobalState.class) {
            if (f4645c) {
                VectorGlobalState.m7284a(new ai[]{aiVar}, dataRequestDispatcherInterface, Locale.getDefault(), Util.m11558c(context), context, resources);
                b = TileStoreFactory.m6313b(aiVar);
            } else {
                throw new IllegalStateException("VectorGlobalState.initialize() must be called first");
            }
        }
        return b;
    }

    private static synchronized void m7284a(ai[] aiVarArr, DataRequestDispatcherInterface dataRequestDispatcherInterface, Locale locale, File file, Context context, Resources resources) {
        synchronized (VectorGlobalState.class) {
            boolean equals = "GMM".equals(dataRequestDispatcherInterface.m11377m());
            if (equals) {
                f4647e = 1;
            }
            for (ai aiVar : aiVarArr) {
                if (!TileStoreFactory.m6312a(aiVar)) {
                    TileStore a = aiVar.m5509a(dataRequestDispatcherInterface, resources, locale, file, equals, equals);
                    if (a != null) {
                        a.m6176d();
                        TileStoreFactory.m6311a(aiVar, a);
                    }
                }
            }
        }
    }

    public static synchronized void m7287c() {
        synchronized (VectorGlobalState.class) {
            if (f4645c) {
                for (ai aiVar : ai.m5504c()) {
                    if (TileStoreFactory.m6312a(aiVar)) {
                        TileStoreFactory.m6313b(aiVar).m6177e();
                    }
                }
                if (ResourceManager.m6599c() != null) {
                    ResourceManager.m6599c().m6604a(false);
                }
                if (IndoorBuildingStore.m6260c() != null) {
                    IndoorBuildingStore.m6260c().m6272e();
                }
            }
        }
    }

    public static int m7288d() {
        return f4647e;
    }

    public static synchronized void m7289e() {
        synchronized (VectorGlobalState.class) {
            if (f4645c) {
                for (ai aiVar : ai.m5504c()) {
                    if (TileStoreFactory.m6312a(aiVar)) {
                        TileStoreFactory.m6313b(aiVar).m6175c();
                    }
                }
                ResourceManager.m6599c().m6604a(true);
                if (IndoorBuildingStore.m6260c() != null) {
                    IndoorBuildingStore.m6260c().m6274g();
                }
            }
        }
    }

    public static int m7290f() {
        return f4648f;
    }
}
