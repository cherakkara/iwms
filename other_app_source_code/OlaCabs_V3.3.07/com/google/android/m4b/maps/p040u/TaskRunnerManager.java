package com.google.android.m4b.maps.p040u;

import com.google.android.m4b.maps.p039o.TaskRunner;
import com.google.android.m4b.maps.p050j.BaseThreadFactory;

/* renamed from: com.google.android.m4b.maps.u.q */
public final class TaskRunnerManager {

    /* renamed from: com.google.android.m4b.maps.u.q.a */
    static class TaskRunnerManager {
        private static TaskRunner f7953a;

        static {
            f7953a = new TaskRunner(new BackgroundThreadFactory(new BaseThreadFactory()));
        }
    }

    public static TaskRunner m11489a() {
        return TaskRunnerManager.f7953a;
    }
}
