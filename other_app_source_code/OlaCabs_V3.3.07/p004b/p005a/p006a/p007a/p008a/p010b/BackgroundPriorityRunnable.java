package p004b.p005a.p006a.p007a.p008a.p010b;

import android.os.Process;

/* renamed from: b.a.a.a.a.b.h */
public abstract class BackgroundPriorityRunnable implements Runnable {
    protected abstract void onRun();

    public final void run() {
        Process.setThreadPriority(10);
        onRun();
    }
}
