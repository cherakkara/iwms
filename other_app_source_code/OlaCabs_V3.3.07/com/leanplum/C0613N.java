package com.leanplum;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

/* renamed from: com.leanplum.N */
final class C0613N implements ActivityLifecycleCallbacks {
    C0613N() {
    }

    public final void onActivityStopped(Activity activity) {
        LeanplumActivityHelper.m12486e(activity);
    }

    public final void onActivityResumed(Activity activity) {
        LeanplumActivityHelper.m12485d(activity);
    }

    public final void onActivityPaused(Activity activity) {
        LeanplumActivityHelper.f8576a = true;
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }
}
