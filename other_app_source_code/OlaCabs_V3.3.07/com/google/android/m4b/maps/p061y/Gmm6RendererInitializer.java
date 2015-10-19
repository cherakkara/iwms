package com.google.android.m4b.maps.p061y;

import android.content.Context;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.aa.ServerControlledParametersManager;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.be.InternalResourceLoader;
import com.google.android.m4b.maps.be.bn;
import com.google.android.m4b.maps.be.bn.CacheExpirationManager;
import com.google.android.m4b.maps.be.bt;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.DataRequestListener;
import com.google.android.m4b.maps.p040u.TaskRunnerManager;
import com.google.android.m4b.maps.p040u.UserEventReporter.UserEventReporter;
import com.google.android.m4b.maps.p059w.FileLogEventStore;

/* renamed from: com.google.android.m4b.maps.y.f */
public final class Gmm6RendererInitializer {
    private static boolean f8081a;

    /* renamed from: com.google.android.m4b.maps.y.f.1 */
    static class Gmm6RendererInitializer implements CacheExpirationManager {
        Gmm6RendererInitializer() {
        }

        public final void m11678a() {
            VectorGlobalState.m7289e();
        }
    }

    public static synchronized void m11679a(Context context, bt btVar) {
        synchronized (Gmm6RendererInitializer.class) {
            if (!f8081a) {
                f8081a = true;
                UserEventReporter fileLogEventStore = new FileLogEventStore(context);
                ai[] aiVarArr = new ai[]{ai.f3423a, ai.f3426d, ai.f3428f, ai.f3427e, ai.f3437o, ai.f3436n};
                DataRequestDispatcher a = btVar.m9044a();
                VectorGlobalState.m7283a(context, InternalResourceLoader.m9392a(), aiVarArr, context.getPackageName(), R.dav_k2, fileLogEventStore, a);
                TaskRunnerManager.m11489a().m11061b();
                DataRequestListener bnVar = new bn(btVar, context.getSharedPreferences("MapviewInitializerPreferences", 0), new Gmm6RendererInitializer());
                a.m11444a(bnVar);
                bnVar.m8920a(ServerControlledParametersManager.m4804b().m4747a());
            }
        }
    }
}
