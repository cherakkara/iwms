package p004b.p005a.p006a.p007a.p008a.p013d;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import p004b.p005a.p006a.p007a.p008a.p010b.CommonUtils;

/* renamed from: b.a.a.a.a.d.e */
public abstract class EventsHandler<T> implements EventsStorageListener {
    protected final Context context;
    protected final ScheduledExecutorService executor;
    protected EventsStrategy<T> strategy;

    /* renamed from: b.a.a.a.a.d.e.1 */
    class EventsHandler implements Runnable {
        final /* synthetic */ Object f255a;
        final /* synthetic */ boolean f256b;
        final /* synthetic */ EventsHandler f257c;

        EventsHandler(EventsHandler eventsHandler, Object obj, boolean z) {
            this.f257c = eventsHandler;
            this.f255a = obj;
            this.f256b = z;
        }

        public void run() {
            try {
                this.f257c.strategy.recordEvent(this.f255a);
                if (this.f256b) {
                    this.f257c.strategy.rollFileOver();
                }
            } catch (Throwable e) {
                CommonUtils.m165a(this.f257c.context, "Failed to record event.", e);
            }
        }
    }

    /* renamed from: b.a.a.a.a.d.e.2 */
    class EventsHandler implements Runnable {
        final /* synthetic */ Object f258a;
        final /* synthetic */ EventsHandler f259b;

        EventsHandler(EventsHandler eventsHandler, Object obj) {
            this.f259b = eventsHandler;
            this.f258a = obj;
        }

        public void run() {
            try {
                this.f259b.strategy.recordEvent(this.f258a);
            } catch (Throwable e) {
                CommonUtils.m165a(this.f259b.context, "Crashlytics failed to record event", e);
            }
        }
    }

    /* renamed from: b.a.a.a.a.d.e.3 */
    class EventsHandler implements Runnable {
        final /* synthetic */ EventsHandler f260a;

        EventsHandler(EventsHandler eventsHandler) {
            this.f260a = eventsHandler;
        }

        public void run() {
            try {
                this.f260a.strategy.sendEvents();
            } catch (Throwable e) {
                CommonUtils.m165a(this.f260a.context, "Failed to send events files.", e);
            }
        }
    }

    /* renamed from: b.a.a.a.a.d.e.4 */
    class EventsHandler implements Runnable {
        final /* synthetic */ EventsHandler f261a;

        EventsHandler(EventsHandler eventsHandler) {
            this.f261a = eventsHandler;
        }

        public void run() {
            try {
                EventsStrategy eventsStrategy = this.f261a.strategy;
                this.f261a.strategy = this.f261a.getDisabledEventsStrategy();
                eventsStrategy.deleteAllEvents();
            } catch (Throwable e) {
                CommonUtils.m165a(this.f261a.context, "Failed to disable events.", e);
            }
        }
    }

    protected abstract EventsStrategy<T> getDisabledEventsStrategy();

    public EventsHandler(Context context, EventsStrategy<T> eventsStrategy, EventsFilesManager eventsFilesManager, ScheduledExecutorService scheduledExecutorService) {
        this.context = context.getApplicationContext();
        this.executor = scheduledExecutorService;
        this.strategy = eventsStrategy;
        eventsFilesManager.registerRollOverListener(this);
    }

    public void recordEventAsync(T t, boolean z) {
        executeAsync(new EventsHandler(this, t, z));
    }

    public void recordEventSync(T t) {
        executeSync(new EventsHandler(this, t));
    }

    public void onRollOver(String str) {
        executeAsync(new EventsHandler(this));
    }

    public void disable() {
        executeAsync(new EventsHandler(this));
    }

    protected void executeSync(Runnable runnable) {
        try {
            this.executor.submit(runnable).get();
        } catch (Throwable e) {
            CommonUtils.m165a(this.context, "Failed to run events task", e);
        }
    }

    protected void executeAsync(Runnable runnable) {
        try {
            this.executor.submit(runnable);
        } catch (Throwable e) {
            CommonUtils.m165a(this.context, "Failed to submit events task", e);
        }
    }
}
