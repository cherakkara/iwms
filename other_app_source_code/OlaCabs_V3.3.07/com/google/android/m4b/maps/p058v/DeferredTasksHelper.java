package com.google.android.m4b.maps.p058v;

import android.os.Looper;
import com.google.android.m4b.maps.p039o.Task;
import com.google.android.m4b.maps.p039o.TaskRunner;
import com.google.p025a.p028c.au;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.m4b.maps.v.a */
public final class DeferredTasksHelper {
    private static volatile boolean f7990a;
    private static final Map<DeferredTasksHelper, LinkedList<DeferredTasksHelper>> f7991b;

    /* renamed from: com.google.android.m4b.maps.v.a.1 */
    static class DeferredTasksHelper implements Runnable {
        private /* synthetic */ List f7972a;
        private /* synthetic */ DeferredTasksHelper f7973b;

        DeferredTasksHelper(List list, DeferredTasksHelper deferredTasksHelper) {
            this.f7972a = list;
            this.f7973b = deferredTasksHelper;
        }

        public final void run() {
            for (DeferredTasksHelper deferredTasksHelper : this.f7972a) {
                DeferredTasksHelper.m11524a(this.f7973b, deferredTasksHelper);
                deferredTasksHelper.run();
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.v.a.a */
    public static abstract class DeferredTasksHelper implements Runnable {
        private final boolean f7974a;
        private final boolean f7975b;
        private final DeferredTasksHelper f7976c;

        public DeferredTasksHelper() {
            this(false, false);
        }

        private DeferredTasksHelper(boolean z, boolean z2) {
            this(false, false, null);
        }

        private DeferredTasksHelper(boolean z, boolean z2, DeferredTasksHelper deferredTasksHelper) {
            this.f7974a = z;
            this.f7975b = z2;
            this.f7976c = null;
        }

        public final void run() {
            if ((!this.f7975b || !DeferredTasksHelper.f7990a) && this.f7976c != null) {
                synchronized (this.f7976c) {
                }
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.v.a.b */
    public enum DeferredTasksHelper {
        ORIENTATION_PROVIDER_ACTIVITY_RESUME,
        START_MOTION_RECOGNIZER
    }

    /* renamed from: com.google.android.m4b.maps.v.a.c */
    public enum DeferredTasksHelper {
        STARTUP_GMM("GMM startup", true),
        STARTUP_DRIVEABOUT("Driveabout startup", true),
        STARTUP_COMMON("GMM or Driveabout startup", true),
        TERMS_AND_CONDITIONS("Term and conditions", true),
        ON_RESUME("On resume", false),
        GENERAL("General", false),
        GENERAL_ONE_TIME("General one time", true);
        
        private String f7988h;
        private final boolean f7989i;

        private DeferredTasksHelper(String str, boolean z) {
            this.f7988h = str;
            this.f7989i = z;
        }

        public final String toString() {
            return this.f7988h + "[oneTime = " + this.f7989i + "]";
        }
    }

    static /* synthetic */ void m11524a(DeferredTasksHelper deferredTasksHelper, DeferredTasksHelper deferredTasksHelper2) {
        Looper.getMainLooper().getThread();
        Thread.currentThread();
    }

    static {
        int i = 0;
        f7990a = false;
        f7991b = Collections.synchronizedMap(au.m2807a());
        DeferredTasksHelper[] values = DeferredTasksHelper.values();
        int length = values.length;
        while (i < length) {
            f7991b.put(values[i], new LinkedList());
            i++;
        }
    }

    public static void m11522a() {
        f7990a = false;
    }

    public static void m11525b() {
        f7990a = true;
    }

    public static void m11523a(DeferredTasksHelper deferredTasksHelper, TaskRunner taskRunner) {
        synchronized (deferredTasksHelper) {
            List list = (List) f7991b.get(deferredTasksHelper);
            if (list == null) {
                return;
            }
            List linkedList = new LinkedList(list);
            synchronized (deferredTasksHelper) {
                if (deferredTasksHelper.f7989i) {
                    f7991b.remove(deferredTasksHelper);
                } else {
                    list = (List) f7991b.get(deferredTasksHelper);
                    if (list != null) {
                        list.clear();
                    }
                }
            }
            if (!linkedList.isEmpty()) {
                new Task(taskRunner, new DeferredTasksHelper(linkedList, deferredTasksHelper)).m4754d();
            }
        }
    }
}
