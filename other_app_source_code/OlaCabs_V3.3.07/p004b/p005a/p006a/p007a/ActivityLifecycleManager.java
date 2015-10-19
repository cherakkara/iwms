package p004b.p005a.p006a.p007a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Set;

/* renamed from: b.a.a.a.a */
public class ActivityLifecycleManager {
    private final Application f396a;
    private ActivityLifecycleManager f397b;

    /* renamed from: b.a.a.a.a.a */
    private static class ActivityLifecycleManager {
        private final Set<ActivityLifecycleCallbacks> f111a;
        private final Application f112b;

        /* renamed from: b.a.a.a.a.a.1 */
        class ActivityLifecycleManager implements ActivityLifecycleCallbacks {
            final /* synthetic */ ActivityLifecycleManager f109a;
            final /* synthetic */ ActivityLifecycleManager f110b;

            ActivityLifecycleManager(ActivityLifecycleManager activityLifecycleManager, ActivityLifecycleManager activityLifecycleManager2) {
                this.f110b = activityLifecycleManager;
                this.f109a = activityLifecycleManager2;
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                this.f109a.onActivityCreated(activity, bundle);
            }

            public void onActivityStarted(Activity activity) {
                this.f109a.onActivityStarted(activity);
            }

            public void onActivityResumed(Activity activity) {
                this.f109a.onActivityResumed(activity);
            }

            public void onActivityPaused(Activity activity) {
                this.f109a.onActivityPaused(activity);
            }

            public void onActivityStopped(Activity activity) {
                this.f109a.onActivityStopped(activity);
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                this.f109a.onActivitySaveInstanceState(activity, bundle);
            }

            public void onActivityDestroyed(Activity activity) {
                this.f109a.onActivityDestroyed(activity);
            }
        }

        ActivityLifecycleManager(Application application) {
            this.f111a = new HashSet();
            this.f112b = application;
        }

        @TargetApi(14)
        private boolean m112a(ActivityLifecycleManager activityLifecycleManager) {
            if (this.f112b == null) {
                return false;
            }
            ActivityLifecycleCallbacks activityLifecycleManager2 = new ActivityLifecycleManager(this, activityLifecycleManager);
            this.f112b.registerActivityLifecycleCallbacks(activityLifecycleManager2);
            this.f111a.add(activityLifecycleManager2);
            return true;
        }
    }

    /* renamed from: b.a.a.a.a.b */
    public static abstract class ActivityLifecycleManager {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }

    public ActivityLifecycleManager(Context context) {
        this.f396a = (Application) context.getApplicationContext();
        if (VERSION.SDK_INT >= 14) {
            this.f397b = new ActivityLifecycleManager(this.f396a);
        }
    }

    public boolean m471a(ActivityLifecycleManager activityLifecycleManager) {
        return this.f397b != null && this.f397b.m112a(activityLifecycleManager);
    }
}
