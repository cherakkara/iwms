package com.google.android.m4b.maps.p040u;

import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p039o.Task;
import com.google.android.m4b.maps.p039o.TaskRunner;
import com.google.android.m4b.maps.p040u.TaskRunnerManager.TaskRunnerManager;

/* renamed from: com.google.android.m4b.maps.u.m */
public final class PersistentStoreUtil {

    /* renamed from: com.google.android.m4b.maps.u.m.1 */
    static class PersistentStoreUtil extends Task {
        PersistentStoreUtil(TaskRunner taskRunner) {
            super(taskRunner);
        }

        protected final void m11476f() {
            Config.m11320a().m10149q().m7753a();
        }
    }

    static {
        Clock clock = new Clock();
    }

    public static void m11477a() {
        new PersistentStoreUtil(TaskRunnerManager.f7953a).m4754d();
    }
}
