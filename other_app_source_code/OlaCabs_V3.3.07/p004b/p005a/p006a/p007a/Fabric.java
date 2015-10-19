package p004b.p005a.p006a.p007a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.crashlytics.android.core.CrashlyticsCore;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import p004b.p005a.p006a.p007a.ActivityLifecycleManager.ActivityLifecycleManager;
import p004b.p005a.p006a.p007a.p008a.p010b.IdManager;
import p004b.p005a.p006a.p007a.p008a.p011c.DependsOn;
import p004b.p005a.p006a.p007a.p008a.p011c.PriorityThreadPoolExecutor;
import p004b.p005a.p006a.p007a.p008a.p011c.UnmetDependencyException;

/* renamed from: b.a.a.a.c */
public class Fabric {
    static volatile Fabric f413a;
    static final Logger f414b;
    final Logger f415c;
    final boolean f416d;
    private final Context f417e;
    private final Map<Class<? extends Kit>, Kit> f418f;
    private final ExecutorService f419g;
    private final Handler f420h;
    private final InitializationCallback<Fabric> f421i;
    private final InitializationCallback<?> f422j;
    private final IdManager f423k;
    private ActivityLifecycleManager f424l;
    private WeakReference<Activity> f425m;
    private AtomicBoolean f426n;

    /* renamed from: b.a.a.a.c.1 */
    class Fabric extends ActivityLifecycleManager {
        final /* synthetic */ Fabric f399a;

        Fabric(Fabric fabric) {
            this.f399a = fabric;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            this.f399a.m515a(activity);
        }

        public void onActivityStarted(Activity activity) {
            this.f399a.m515a(activity);
        }

        public void onActivityResumed(Activity activity) {
            this.f399a.m515a(activity);
        }
    }

    /* renamed from: b.a.a.a.c.2 */
    class Fabric implements InitializationCallback {
        final CountDownLatch f401a;
        final /* synthetic */ int f402b;
        final /* synthetic */ Fabric f403c;

        Fabric(Fabric fabric, int i) {
            this.f403c = fabric;
            this.f402b = i;
            this.f401a = new CountDownLatch(this.f402b);
        }

        public void m499a(Object obj) {
            this.f401a.countDown();
            if (this.f401a.getCount() == 0) {
                this.f403c.f426n.set(true);
                this.f403c.f421i.m497a(this.f403c);
            }
        }

        public void m498a(Exception exception) {
            this.f403c.f421i.m496a(exception);
        }
    }

    /* renamed from: b.a.a.a.c.a */
    public static class Fabric {
        private final Context f404a;
        private Kit[] f405b;
        private PriorityThreadPoolExecutor f406c;
        private Handler f407d;
        private Logger f408e;
        private boolean f409f;
        private String f410g;
        private String f411h;
        private InitializationCallback<Fabric> f412i;

        public Fabric(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.f404a = context.getApplicationContext();
        }

        public Fabric m500a(Kit... kitArr) {
            if (this.f405b != null) {
                throw new IllegalStateException("Kits already set.");
            }
            this.f405b = kitArr;
            return this;
        }

        public Fabric m501a() {
            Map hashMap;
            if (this.f406c == null) {
                this.f406c = PriorityThreadPoolExecutor.m300a();
            }
            if (this.f407d == null) {
                this.f407d = new Handler(Looper.getMainLooper());
            }
            if (this.f408e == null) {
                if (this.f409f) {
                    this.f408e = new DefaultLogger(3);
                } else {
                    this.f408e = new DefaultLogger();
                }
            }
            if (this.f411h == null) {
                this.f411h = this.f404a.getPackageName();
            }
            if (this.f412i == null) {
                this.f412i = InitializationCallback.f400d;
            }
            if (this.f405b == null) {
                hashMap = new HashMap();
            } else {
                hashMap = Fabric.m509b(Arrays.asList(this.f405b));
            }
            return new Fabric(this.f404a, hashMap, this.f406c, this.f407d, this.f408e, this.f409f, this.f412i, new IdManager(this.f404a, this.f411h, this.f410g, hashMap.values()));
        }
    }

    static {
        f414b = new DefaultLogger();
    }

    static Fabric m502a() {
        if (f413a != null) {
            return f413a;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    Fabric(Context context, Map<Class<? extends Kit>, Kit> map, PriorityThreadPoolExecutor priorityThreadPoolExecutor, Handler handler, Logger logger, boolean z, InitializationCallback initializationCallback, IdManager idManager) {
        this.f417e = context;
        this.f418f = map;
        this.f419g = priorityThreadPoolExecutor;
        this.f420h = handler;
        this.f415c = logger;
        this.f416d = z;
        this.f421i = initializationCallback;
        this.f426n = new AtomicBoolean(false);
        this.f422j = m516a(map.size());
        this.f423k = idManager;
    }

    public static Fabric m503a(Context context, Kit... kitArr) {
        if (f413a == null) {
            synchronized (Fabric.class) {
                if (f413a == null) {
                    Fabric.m511c(new Fabric(context).m500a(kitArr).m501a());
                }
            }
        }
        return f413a;
    }

    private static void m511c(Fabric fabric) {
        f413a = fabric;
        fabric.m514j();
    }

    public Fabric m515a(Activity activity) {
        this.f425m = new WeakReference(activity);
        return this;
    }

    public Activity m519b() {
        if (this.f425m != null) {
            return (Activity) this.f425m.get();
        }
        return null;
    }

    private void m514j() {
        m515a(m510c(this.f417e));
        this.f424l = new ActivityLifecycleManager(this.f417e);
        this.f424l.m471a(new Fabric(this));
        m517a(this.f417e);
    }

    public String m521c() {
        return "1.3.4.60";
    }

    public String m522d() {
        return "io.fabric.sdk.android:fabric";
    }

    void m517a(Context context) {
        StringBuilder append;
        Future b = m520b(context);
        Collection g = m525g();
        Onboarding onboarding = new Onboarding(b, g);
        List<Kit> arrayList = new ArrayList(g);
        Collections.sort(arrayList);
        onboarding.injectParameters(context, this, InitializationCallback.f400d, this.f423k);
        for (Kit injectParameters : arrayList) {
            injectParameters.injectParameters(context, this, this.f422j, this.f423k);
        }
        onboarding.initialize();
        if (Fabric.m512h().m476a(CrashlyticsCore.TAG, 3)) {
            append = new StringBuilder("Initializing ").append(m522d()).append(" [Version: ").append(m521c()).append("], with the following kits:\n");
        } else {
            append = null;
        }
        for (Kit injectParameters2 : arrayList) {
            injectParameters2.initializationTask.m297a(onboarding.initializationTask);
            m518a(this.f418f, injectParameters2);
            injectParameters2.initialize();
            if (append != null) {
                append.append(injectParameters2.getIdentifier()).append(" [Version: ").append(injectParameters2.getVersion()).append("]\n");
            }
        }
        if (append != null) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, append.toString());
        }
    }

    void m518a(Map<Class<? extends Kit>, Kit> map, Kit kit) {
        DependsOn dependsOn = (DependsOn) kit.getClass().getAnnotation(DependsOn.class);
        if (dependsOn != null) {
            for (Class cls : dependsOn.m290a()) {
                if (cls.isInterface()) {
                    for (Kit kit2 : map.values()) {
                        if (cls.isAssignableFrom(kit2.getClass())) {
                            kit.initializationTask.m297a(kit2.initializationTask);
                        }
                    }
                } else if (((Kit) map.get(cls)) == null) {
                    throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
                } else {
                    kit.initializationTask.m297a(((Kit) map.get(cls)).initializationTask);
                }
            }
        }
    }

    private Activity m510c(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    public ActivityLifecycleManager m523e() {
        return this.f424l;
    }

    public ExecutorService m524f() {
        return this.f419g;
    }

    public Collection<Kit> m525g() {
        return this.f418f.values();
    }

    public static <T extends Kit> T m504a(Class<T> cls) {
        return (Kit) Fabric.m502a().f418f.get(cls);
    }

    public static Logger m512h() {
        if (f413a == null) {
            return f414b;
        }
        return f413a.f415c;
    }

    public static boolean m513i() {
        if (f413a == null) {
            return false;
        }
        return f413a.f416d;
    }

    private static Map<Class<? extends Kit>, Kit> m509b(Collection<? extends Kit> collection) {
        Map hashMap = new HashMap(collection.size());
        Fabric.m507a(hashMap, (Collection) collection);
        return hashMap;
    }

    private static void m507a(Map<Class<? extends Kit>, Kit> map, Collection<? extends Kit> collection) {
        for (Kit kit : collection) {
            map.put(kit.getClass(), kit);
            if (kit instanceof KitGroup) {
                Fabric.m507a((Map) map, ((KitGroup) kit).getKits());
            }
        }
    }

    InitializationCallback<?> m516a(int i) {
        return new Fabric(this, i);
    }

    Future<Map<String, KitInfo>> m520b(Context context) {
        return m524f().submit(new FabricKitsFinder(context.getPackageCodePath()));
    }
}
