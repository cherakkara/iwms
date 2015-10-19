package p004b.p005a.p006a.p007a.p008a.p013d;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;

/* renamed from: b.a.a.a.a.d.b */
public abstract class EnabledEventsStrategy<T> implements EventsStrategy<T> {
    static final int UNDEFINED_ROLLOVER_INTERVAL_SECONDS = -1;
    protected final Context context;
    final ScheduledExecutorService executorService;
    protected final EventsFilesManager<T> filesManager;
    volatile int rolloverIntervalSeconds;
    final AtomicReference<ScheduledFuture<?>> scheduledRolloverFutureRef;

    public EnabledEventsStrategy(Context context, ScheduledExecutorService scheduledExecutorService, EventsFilesManager<T> eventsFilesManager) {
        this.rolloverIntervalSeconds = UNDEFINED_ROLLOVER_INTERVAL_SECONDS;
        this.context = context;
        this.executorService = scheduledExecutorService;
        this.filesManager = eventsFilesManager;
        this.scheduledRolloverFutureRef = new AtomicReference();
    }

    public void scheduleTimeBasedRollOverIfNeeded() {
        if ((this.rolloverIntervalSeconds != UNDEFINED_ROLLOVER_INTERVAL_SECONDS ? 1 : null) != null) {
            scheduleTimeBasedFileRollOver((long) this.rolloverIntervalSeconds, (long) this.rolloverIntervalSeconds);
        }
    }

    public void sendEvents() {
        sendAndCleanUpIfSuccess();
    }

    public void cancelTimeBasedFileRollOver() {
        if (this.scheduledRolloverFutureRef.get() != null) {
            CommonUtils.m164a(this.context, "Cancelling time-based rollover because no events are currently being generated.");
            ((ScheduledFuture) this.scheduledRolloverFutureRef.get()).cancel(false);
            this.scheduledRolloverFutureRef.set(null);
        }
    }

    public void deleteAllEvents() {
        this.filesManager.deleteAllEventsFiles();
    }

    public void recordEvent(T t) {
        CommonUtils.m164a(this.context, t.toString());
        try {
            this.filesManager.writeEvent(t);
        } catch (Throwable e) {
            CommonUtils.m165a(this.context, "Failed to write event.", e);
        }
        scheduleTimeBasedRollOverIfNeeded();
    }

    public boolean rollFileOver() {
        try {
            return this.filesManager.rollFileOver();
        } catch (Throwable e) {
            CommonUtils.m165a(this.context, "Failed to roll file over.", e);
            return false;
        }
    }

    protected void configureRollover(int i) {
        this.rolloverIntervalSeconds = i;
        scheduleTimeBasedFileRollOver(0, (long) this.rolloverIntervalSeconds);
    }

    void scheduleTimeBasedFileRollOver(long j, long j2) {
        if ((this.scheduledRolloverFutureRef.get() == null ? 1 : null) != null) {
            Runnable timeBasedFileRollOverRunnable = new TimeBasedFileRollOverRunnable(this.context, this);
            CommonUtils.m164a(this.context, "Scheduling time based file roll over every " + j2 + " seconds");
            try {
                this.scheduledRolloverFutureRef.set(this.executorService.scheduleAtFixedRate(timeBasedFileRollOverRunnable, j, j2, TimeUnit.SECONDS));
            } catch (Throwable e) {
                CommonUtils.m165a(this.context, "Failed to schedule time based file roll over", e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void sendAndCleanUpIfSuccess() {
        /*
        r10 = this;
        r1 = 0;
        r3 = r10.getFilesSender();
        if (r3 != 0) goto L_0x000f;
    L_0x0007:
        r0 = r10.context;
        r1 = "skipping files send because we don't yet know the target endpoint";
        p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils.m164a(r0, r1);
    L_0x000e:
        return;
    L_0x000f:
        r0 = r10.context;
        r2 = "Sending all files";
        p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils.m164a(r0, r2);
        r0 = r10.filesManager;
        r0 = r0.getBatchOfFilesToSend();
        r2 = r0;
        r0 = r1;
    L_0x001e:
        r1 = r2.size();	 Catch:{ Exception -> 0x0062 }
        if (r1 <= 0) goto L_0x0052;
    L_0x0024:
        r1 = r10.context;	 Catch:{ Exception -> 0x0062 }
        r4 = java.util.Locale.US;	 Catch:{ Exception -> 0x0062 }
        r5 = "attempt to send batch of %d files";
        r6 = 1;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x0062 }
        r7 = 0;
        r8 = r2.size();	 Catch:{ Exception -> 0x0062 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x0062 }
        r6[r7] = r8;	 Catch:{ Exception -> 0x0062 }
        r4 = java.lang.String.format(r4, r5, r6);	 Catch:{ Exception -> 0x0062 }
        p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils.m164a(r1, r4);	 Catch:{ Exception -> 0x0062 }
        r4 = r3.send(r2);	 Catch:{ Exception -> 0x0062 }
        if (r4 == 0) goto L_0x0050;
    L_0x0045:
        r1 = r2.size();	 Catch:{ Exception -> 0x0062 }
        r1 = r1 + r0;
        r0 = r10.filesManager;	 Catch:{ Exception -> 0x0084 }
        r0.deleteSentFiles(r2);	 Catch:{ Exception -> 0x0084 }
        r0 = r1;
    L_0x0050:
        if (r4 != 0) goto L_0x005a;
    L_0x0052:
        if (r0 != 0) goto L_0x000e;
    L_0x0054:
        r0 = r10.filesManager;
        r0.deleteOldestInRollOverIfOverMax();
        goto L_0x000e;
    L_0x005a:
        r1 = r10.filesManager;	 Catch:{ Exception -> 0x0062 }
        r1 = r1.getBatchOfFilesToSend();	 Catch:{ Exception -> 0x0062 }
        r2 = r1;
        goto L_0x001e;
    L_0x0062:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
    L_0x0066:
        r2 = r10.context;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Failed to send batch of analytics files to server: ";
        r3 = r3.append(r4);
        r4 = r0.getMessage();
        r3 = r3.append(r4);
        r3 = r3.toString();
        p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils.m165a(r2, r3, r0);
        r0 = r1;
        goto L_0x0052;
    L_0x0084:
        r0 = move-exception;
        goto L_0x0066;
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.a.a.d.b.sendAndCleanUpIfSuccess():void");
    }
}
