package p000a.p001a.p002a;

import android.os.Looper;
import android.util.Log;
import com.sothree.slidinguppanel.p086a.R.R;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

/* renamed from: a.a.a.c */
public class EventBus {
    public static String f13a;
    static volatile EventBus f14b;
    private static final EventBusBuilder f15c;
    private static final Map<Class<?>, List<Class<?>>> f16d;
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> f17e;
    private final Map<Object, List<Class<?>>> f18f;
    private final Map<Class<?>, Object> f19g;
    private final ThreadLocal<EventBus> f20h;
    private final HandlerPoster f21i;
    private final BackgroundPoster f22j;
    private final AsyncPoster f23k;
    private final SubscriberMethodFinder f24l;
    private final ExecutorService f25m;
    private final boolean f26n;
    private final boolean f27o;
    private final boolean f28p;
    private final boolean f29q;
    private final boolean f30r;
    private final boolean f31s;

    /* renamed from: a.a.a.c.1 */
    class EventBus extends ThreadLocal<EventBus> {
        final /* synthetic */ EventBus f5a;

        EventBus(EventBus eventBus) {
            this.f5a = eventBus;
        }

        protected /* synthetic */ Object initialValue() {
            return m2a();
        }

        protected EventBus m2a() {
            return new EventBus();
        }
    }

    /* renamed from: a.a.a.c.2 */
    static /* synthetic */ class EventBus {
        static final /* synthetic */ int[] f6a;

        static {
            f6a = new int[ThreadMode.values().length];
            try {
                f6a[ThreadMode.PostThread.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f6a[ThreadMode.MainThread.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f6a[ThreadMode.BackgroundThread.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f6a[ThreadMode.Async.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* renamed from: a.a.a.c.a */
    static final class EventBus {
        final List<Object> f7a;
        boolean f8b;
        boolean f9c;
        Subscription f10d;
        Object f11e;
        boolean f12f;

        EventBus() {
            this.f7a = new ArrayList();
        }
    }

    static {
        f13a = "Event";
        f15c = new EventBusBuilder();
        f16d = new HashMap();
    }

    public static EventBus m3a() {
        if (f14b == null) {
            synchronized (EventBus.class) {
                if (f14b == null) {
                    f14b = new EventBus();
                }
            }
        }
        return f14b;
    }

    public EventBus() {
        this(f15c);
    }

    EventBus(EventBusBuilder eventBusBuilder) {
        this.f20h = new EventBus(this);
        this.f17e = new HashMap();
        this.f18f = new HashMap();
        this.f19g = new ConcurrentHashMap();
        this.f21i = new HandlerPoster(this, Looper.getMainLooper(), 10);
        this.f22j = new BackgroundPoster(this);
        this.f23k = new AsyncPoster(this);
        this.f24l = new SubscriberMethodFinder(eventBusBuilder.f40h);
        this.f27o = eventBusBuilder.f33a;
        this.f28p = eventBusBuilder.f34b;
        this.f29q = eventBusBuilder.f35c;
        this.f30r = eventBusBuilder.f36d;
        this.f26n = eventBusBuilder.f37e;
        this.f31s = eventBusBuilder.f38f;
        this.f25m = eventBusBuilder.f39g;
    }

    public void m15a(Object obj) {
        m10a(obj, false, 0);
    }

    private synchronized void m10a(Object obj, boolean z, int i) {
        for (SubscriberMethod a : this.f24l.m26a(obj.getClass())) {
            m8a(obj, a, z, i);
        }
    }

    private void m8a(Object obj, SubscriberMethod subscriberMethod, boolean z, int i) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        Class cls = subscriberMethod.f59c;
        CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.f17e.get(cls);
        Subscription subscription = new Subscription(obj, subscriberMethod, i);
        if (copyOnWriteArrayList2 == null) {
            copyOnWriteArrayList2 = new CopyOnWriteArrayList();
            this.f17e.put(cls, copyOnWriteArrayList2);
            copyOnWriteArrayList = copyOnWriteArrayList2;
        } else if (copyOnWriteArrayList2.contains(subscription)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        } else {
            copyOnWriteArrayList = copyOnWriteArrayList2;
        }
        int size = copyOnWriteArrayList.size();
        int i2 = 0;
        while (i2 <= size) {
            if (i2 == size || subscription.f65c > ((Subscription) copyOnWriteArrayList.get(i2)).f65c) {
                copyOnWriteArrayList.add(i2, subscription);
                break;
            }
            i2++;
        }
        List list = (List) this.f18f.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.f18f.put(obj, list);
        }
        list.add(cls);
        if (z) {
            Object obj2;
            synchronized (this.f19g) {
                obj2 = this.f19g.get(cls);
            }
            if (obj2 != null) {
                boolean z2;
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                m6a(subscription, obj2, z2);
            }
        }
    }

    private void m9a(Object obj, Class<?> cls) {
        List list = (List) this.f17e.get(cls);
        if (list != null) {
            int size = list.size();
            int i = 0;
            while (i < size) {
                int i2;
                Subscription subscription = (Subscription) list.get(i);
                if (subscription.f63a == obj) {
                    subscription.f66d = false;
                    list.remove(i);
                    i2 = i - 1;
                    i = size - 1;
                } else {
                    i2 = i;
                    i = size;
                }
                size = i;
                i = i2 + 1;
            }
        }
    }

    public synchronized void m17b(Object obj) {
        List<Class> list = (List) this.f18f.get(obj);
        if (list != null) {
            for (Class a : list) {
                m9a(obj, a);
            }
            this.f18f.remove(obj);
        } else {
            Log.w(f13a, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void m18c(Object obj) {
        EventBus eventBus = (EventBus) this.f20h.get();
        List list = eventBus.f7a;
        list.add(obj);
        if (!eventBus.f8b) {
            boolean z;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                z = true;
            } else {
                z = false;
            }
            eventBus.f9c = z;
            eventBus.f8b = true;
            if (eventBus.f12f) {
                throw new EventBusException("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                try {
                    m7a(list.remove(0), eventBus);
                } finally {
                    eventBus.f8b = false;
                    eventBus.f9c = false;
                }
            }
        }
    }

    private void m7a(Object obj, EventBus eventBus) throws Error {
        boolean z;
        Class cls = obj.getClass();
        if (this.f31s) {
            List a = m4a(cls);
            boolean z2 = false;
            for (int i = 0; i < a.size(); i++) {
                z2 |= m12a(obj, eventBus, (Class) a.get(i));
            }
            z = z2;
        } else {
            z = m12a(obj, eventBus, cls);
        }
        if (!z) {
            if (this.f28p) {
                Log.d(f13a, "No subscribers registered for event " + cls);
            }
            if (this.f30r && cls != NoSubscriberEvent.class && cls != SubscriberExceptionEvent.class) {
                m18c(new NoSubscriberEvent(this, obj));
            }
        }
    }

    private boolean m12a(Object obj, EventBus eventBus, Class<?> cls) {
        synchronized (this) {
            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.f17e.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator it = copyOnWriteArrayList.iterator();
        loop0:
        while (it.hasNext()) {
            Subscription subscription = (Subscription) it.next();
            eventBus.f11e = obj;
            eventBus.f10d = subscription;
            try {
                m6a(subscription, obj, eventBus.f9c);
                Object obj2 = eventBus.f12f;
                continue;
            } finally {
                eventBus.f11e = null;
                eventBus.f10d = null;
                eventBus.f12f = false;
            }
            if (obj2 != null) {
                break loop0;
            }
        }
        return true;
    }

    private void m6a(Subscription subscription, Object obj, boolean z) {
        switch (EventBus.f6a[subscription.f64b.f58b.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                m14a(subscription, obj);
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                if (z) {
                    m14a(subscription, obj);
                } else {
                    this.f21i.m19a(subscription, obj);
                }
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                if (z) {
                    this.f22j.m1a(subscription, obj);
                } else {
                    m14a(subscription, obj);
                }
            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                this.f23k.m0a(subscription, obj);
            default:
                throw new IllegalStateException("Unknown thread mode: " + subscription.f64b.f58b);
        }
    }

    private List<Class<?>> m4a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (f16d) {
            list = (List) f16d.get(cls);
            if (list == null) {
                list = new ArrayList();
                for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    EventBus.m11a((List) list, cls2.getInterfaces());
                }
                f16d.put(cls, list);
            }
        }
        return list;
    }

    static void m11a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                EventBus.m11a((List) list, cls.getInterfaces());
            }
        }
    }

    void m13a(PendingPost pendingPost) {
        Object obj = pendingPost.f48a;
        Subscription subscription = pendingPost.f49b;
        PendingPost.m21a(pendingPost);
        if (subscription.f66d) {
            m14a(subscription, obj);
        }
    }

    void m14a(Subscription subscription, Object obj) {
        try {
            subscription.f64b.f57a.invoke(subscription.f63a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            m5a(subscription, obj, e.getCause());
        } catch (Throwable e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    private void m5a(Subscription subscription, Object obj, Throwable th) {
        if (obj instanceof SubscriberExceptionEvent) {
            if (this.f27o) {
                Log.e(f13a, "SubscriberExceptionEvent subscriber " + subscription.f63a.getClass() + " threw an exception", th);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                Log.e(f13a, "Initial event " + subscriberExceptionEvent.f55c + " caused exception in " + subscriberExceptionEvent.f56d, subscriberExceptionEvent.f54b);
            }
        } else if (this.f26n) {
            throw new EventBusException("Invoking subscriber failed", th);
        } else {
            if (this.f27o) {
                Log.e(f13a, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + subscription.f63a.getClass(), th);
            }
            if (this.f29q) {
                m18c(new SubscriberExceptionEvent(this, th, obj, subscription.f63a));
            }
        }
    }

    ExecutorService m16b() {
        return this.f25m;
    }
}
