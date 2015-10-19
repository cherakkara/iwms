package com.crashlytics.android.beta;

import android.annotation.TargetApi;
import android.app.Activity;
import java.util.concurrent.ExecutorService;
import p004b.p005a.p006a.p007a.ActivityLifecycleManager.ActivityLifecycleManager;

@TargetApi(14)
class ActivityLifecycleCheckForUpdatesController extends AbstractCheckForUpdatesController {
    private final ActivityLifecycleManager callbacks;
    private final ExecutorService executorService;

    /* renamed from: com.crashlytics.android.beta.ActivityLifecycleCheckForUpdatesController.1 */
    class C01181 extends ActivityLifecycleManager {

        /* renamed from: com.crashlytics.android.beta.ActivityLifecycleCheckForUpdatesController.1.1 */
        class C01171 implements Runnable {
            C01171() {
            }

            public void run() {
                ActivityLifecycleCheckForUpdatesController.this.checkForUpdates();
            }
        }

        C01181() {
        }

        public void onActivityStarted(Activity activity) {
            if (ActivityLifecycleCheckForUpdatesController.this.signalExternallyReady()) {
                ActivityLifecycleCheckForUpdatesController.this.executorService.submit(new C01171());
            }
        }
    }

    public ActivityLifecycleCheckForUpdatesController(p004b.p005a.p006a.p007a.ActivityLifecycleManager activityLifecycleManager, ExecutorService executorService) {
        this.callbacks = new C01181();
        this.executorService = executorService;
        activityLifecycleManager.m471a(this.callbacks);
    }

    public boolean isActivityLifecycleTriggered() {
        return true;
    }
}
