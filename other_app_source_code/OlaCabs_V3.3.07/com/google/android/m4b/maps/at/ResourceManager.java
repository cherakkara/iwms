package com.google.android.m4b.maps.at;

import com.google.android.m4b.maps.ah.EventLog;
import com.google.android.m4b.maps.ar.DiskResourceCache;
import com.google.android.m4b.maps.au.LRUCache;
import com.google.android.m4b.maps.bx.ae;
import com.google.android.m4b.maps.cm.Clock;
import com.google.android.m4b.maps.p039o.Task;
import com.google.android.m4b.maps.p039o.TaskRunner;
import com.google.android.m4b.maps.p040u.BaseDataRequest;
import com.google.android.m4b.maps.p040u.Config;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.DataRequestListener;
import com.google.android.m4b.maps.p040u.TaskRunnerManager;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.google.android.m4b.maps.at.c */
public final class ResourceManager implements DataRequestListener {
    private static ResourceManager f4065a;
    private final DataRequestDispatcher f4066b;
    private final Clock f4067c;
    private final LRUCache<String, Resource> f4068d;
    private final LRUCache<String, SoftReference<Resource>> f4069e;
    private volatile DiskResourceCache f4070f;
    private final CountDownLatch f4071g;

    /* renamed from: com.google.android.m4b.maps.at.c.1 */
    static class ResourceManager extends Task {
        private /* synthetic */ File f4061b;

        ResourceManager(TaskRunner taskRunner, File file) {
            this.f4061b = file;
            super(taskRunner);
        }

        public final void m6592f() {
            ResourceManager.m6598a(ResourceManager.m6599c(), this.f4061b);
        }
    }

    /* renamed from: com.google.android.m4b.maps.at.c.a */
    class ResourceManager extends BaseDataRequest {
        Resource f4062a;
        private ProtoBuf f4063b;
        private /* synthetic */ ResourceManager f4064c;

        private ResourceManager(ResourceManager resourceManager, ProtoBuf protoBuf, Resource resource) {
            this.f4064c = resourceManager;
            this.f4063b = protoBuf;
            this.f4062a = resource;
        }

        public final int m6595i() {
            return 39;
        }

        public final boolean m6594a(DataInput dataInput) {
            ProtoBuf a = ProtoBufUtil.m10226a(ae.f6818b, dataInput);
            if (a.m10215k(1) == 0) {
                return false;
            }
            ProtoBuf c = a.m10202c(1, 0);
            boolean a2 = this.f4062a.m6585a(c);
            if (this.f4064c.f4070f != null && a2 && this.f4062a.m6584a()) {
                this.f4064c.f4070f.m6347a(c);
            }
            return true;
        }

        public final void m6593a(DataOutput dataOutput) {
            byte[] d = this.f4063b.m10206d();
            dataOutput.writeInt(d.length);
            dataOutput.write(d);
        }
    }

    static /* synthetic */ void m6598a(ResourceManager resourceManager, File file) {
        resourceManager.f4070f = DiskResourceCache.m6344a(file);
        resourceManager.f4071g.countDown();
    }

    private ResourceManager(DataRequestDispatcher dataRequestDispatcher) {
        this.f4066b = dataRequestDispatcher;
        if (this.f4066b != null) {
            this.f4066b.m11444a((DataRequestListener) this);
        }
        this.f4067c = Config.m11320a().m11334h();
        this.f4068d = new LRUCache(64);
        this.f4069e = new LRUCache(32);
        this.f4070f = null;
        this.f4071g = new CountDownLatch(1);
    }

    ResourceManager() {
        this.f4066b = null;
        this.f4067c = null;
        this.f4068d = null;
        this.f4069e = null;
        this.f4070f = null;
        this.f4071g = null;
    }

    public final Resource m6600a(String str, ResourceListener resourceListener, boolean z) {
        Resource resource;
        synchronized (this.f4068d) {
            Resource resource2 = (Resource) this.f4068d.m6235b((Object) str);
            if (resource2 == null && this.f4070f != null) {
                resource2 = this.f4070f.m6345a(str);
            }
            if (resource2 == null) {
                resource2 = new Resource();
                resource2.m6583a(true);
                resource = resource2;
            } else {
                resource = resource2;
            }
            this.f4068d.m6239c(str, resource);
        }
        synchronized (resource) {
            long a = this.f4067c.m10151a();
            if (resource.m6590f() + 86400000 < a) {
                ProtoBuf protoBuf = new ProtoBuf(ae.f6817a);
                protoBuf.m10197b(4, str);
                if (resource.m6586b()) {
                    protoBuf.m10184a(2, resource.m6589e());
                }
                DataRequest resourceManager = new ResourceManager(protoBuf, resource, (byte) 0);
                EventLog.m4911b(new EventLog.EventLog("addRequest", resourceManager));
                this.f4066b.m11451c(resourceManager);
                resource.m6581a(a);
            }
        }
        if (!(resourceListener == null || resource.m6586b())) {
            resource.m6582a(resourceListener);
        }
        return resource;
    }

    public final void m6604a(boolean z) {
        synchronized (this.f4069e) {
            this.f4069e.m6231a();
        }
        synchronized (this.f4068d) {
            this.f4068d.m6231a();
        }
        if (z) {
            while (this.f4070f == null) {
                try {
                    this.f4071g.await();
                } catch (InterruptedException e) {
                }
            }
            this.f4070f.m6346a();
        }
    }

    public final void m6603a(DataRequest dataRequest) {
        if (dataRequest instanceof ResourceManager) {
            ((ResourceManager) dataRequest).f4062a.m6591g();
        }
    }

    public final void m6602a(int i, boolean z, String str) {
    }

    public final void m6601a() {
    }

    public final void m6605b() {
    }

    public final void m6606b(DataRequest dataRequest) {
    }

    public static ResourceManager m6597a(DataRequestDispatcher dataRequestDispatcher, File file) {
        if (f4065a == null) {
            f4065a = new ResourceManager(dataRequestDispatcher);
        }
        new ResourceManager(TaskRunnerManager.m11489a(), file).m4754d();
        return f4065a;
    }

    public static ResourceManager m6599c() {
        return f4065a;
    }
}
