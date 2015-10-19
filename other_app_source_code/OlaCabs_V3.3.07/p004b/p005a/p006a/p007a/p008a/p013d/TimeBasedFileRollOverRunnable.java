package p004b.p005a.p006a.p007a.p008a.p013d;

import android.content.Context;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;

/* renamed from: b.a.a.a.a.d.n */
public class TimeBasedFileRollOverRunnable implements Runnable {
    private final Context f268a;
    private final FileRollOverManager f269b;

    public TimeBasedFileRollOverRunnable(Context context, FileRollOverManager fileRollOverManager) {
        this.f268a = context;
        this.f269b = fileRollOverManager;
    }

    public void run() {
        try {
            CommonUtils.m164a(this.f268a, "Performing time based file roll over.");
            if (!this.f269b.rollFileOver()) {
                this.f269b.cancelTimeBasedFileRollOver();
            }
        } catch (Throwable e) {
            CommonUtils.m165a(this.f268a, "Failed to roll over file", e);
        }
    }
}
