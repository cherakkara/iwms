package com.google.android.m4b.maps.be;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.FeatureInfo;
import android.content.res.Resources;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.widget.Toast;
import com.google.android.m4b.maps.R.R;
import com.google.android.m4b.maps.aa.ApiParameters;
import com.google.android.m4b.maps.aa.ServerControlledParametersManager;
import com.google.android.m4b.maps.au.ParameterManager;
import com.google.android.m4b.maps.be.bt.ConnectionManager;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p059w.AndroidBuilds;
import com.google.android.m4b.maps.p061y.CredentialsHelper;
import com.google.p025a.p026a.Preconditions;

/* renamed from: com.google.android.m4b.maps.be.h */
public final class Initializer {
    private static Context f6020a;

    /* renamed from: com.google.android.m4b.maps.be.h.1 */
    static class Initializer implements Runnable {
        final /* synthetic */ bt f6016a;
        final /* synthetic */ Context f6017b;
        private /* synthetic */ boolean f6018c;

        /* renamed from: com.google.android.m4b.maps.be.h.1.1 */
        class Initializer implements ConnectionManager {
            private /* synthetic */ Initializer f6015a;

            Initializer(Initializer initializer) {
                this.f6015a = initializer;
            }

            public final void m9388a() {
                Initializer.m9391b(this.f6015a.f6016a, this.f6015a.f6017b, DataRequestDispatcher.m11393a(), false);
            }
        }

        Initializer(boolean z, bt btVar, Context context) {
            this.f6018c = z;
            this.f6016a = btVar;
            this.f6017b = context;
        }

        public final void run() {
            if (this.f6018c) {
                this.f6016a.m9045a(new Initializer(this));
                az.m8757a(4, "Failed to contact Google servers. Another attempt will be made when connectivity is established.");
                return;
            }
            az.m8757a(6, "Failed to load map. Error contacting Google servers. This is probably an authentication issue (but could be due to network errors).");
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.h.2 */
    static class Initializer extends ContextWrapper {
        private /* synthetic */ Context f6019a;

        Initializer(Context context, Context context2) {
            this.f6019a = context2;
            super(context);
        }

        public final Resources getResources() {
            return InternalResourceLoader.m9392a();
        }

        public final Context getApplicationContext() {
            return this;
        }

        public final void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
            this.f6019a.registerComponentCallbacks(componentCallbacks);
        }

        public final void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
            this.f6019a.unregisterComponentCallbacks(componentCallbacks);
        }
    }

    public static synchronized Context m9389a(Context context, bt btVar, boolean z) {
        Context context2;
        Object obj = null;
        synchronized (Initializer.class) {
            if (f6020a != null) {
                context2 = f6020a;
            } else {
                Object obj2;
                String b;
                Preconditions.m1822a(context.equals(context.getApplicationContext()));
                f6020a = new Initializer(context, context);
                InternalResourceLoader.m9395b(context.getResources());
                bg.m8850a(f6020a);
                DataRequestDispatcher a = btVar.m9044a();
                a.m11464k();
                ServerControlledParametersManager.m4798a(a, Config.m11320a().m11339o());
                Context context3 = f6020a;
                FeatureInfo[] systemAvailableFeatures = context3.getPackageManager().getSystemAvailableFeatures();
                if (systemAvailableFeatures != null && systemAvailableFeatures.length > 0) {
                    for (FeatureInfo featureInfo : systemAvailableFeatures) {
                        if (featureInfo.name == null && featureInfo.reqGlEsVersion >= AccessibilityNodeInfoCompat.ACTION_SET_SELECTION) {
                            obj2 = 1;
                            break;
                        }
                    }
                }
                obj2 = null;
                if (obj2 == null && !AndroidBuilds.m11562b()) {
                    az.m8757a(6, "Google Maps Android API v2 only supports devices with OpenGL ES 2.0 and above");
                } else if (az.m8759a(context3) || AndroidBuilds.m11562b()) {
                    obj = 1;
                } else {
                    az.m8757a(6, "Google Play services is missing.");
                }
                if (obj != null) {
                    Initializer.m9391b(btVar, f6020a, a, true);
                } else {
                    a.m11463j();
                }
                if (z) {
                    b = CredentialsHelper.m11645b(f6020a);
                } else {
                    b = f6020a.getPackageName();
                }
                bc.m8779a(f6020a, CredentialsHelper.m11642a(f6020a, b), a).m8798c();
                context2 = f6020a;
                ApiParameters d = ServerControlledParametersManager.m4810d();
                if (d != null && d.m4741a()) {
                    Toast.makeText(context2, R.API_OUTDATED_WARNING, 1).show();
                }
                context2 = f6020a;
            }
        }
        return context2;
    }

    private static void m9391b(bt btVar, Context context, DataRequestDispatcher dataRequestDispatcher, boolean z) {
        ParameterManager.m6652e();
        ParameterManager.m6645a(context, dataRequestDispatcher, new Initializer(z, btVar, context), false);
    }
}
