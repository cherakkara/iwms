package p004b.p005a.p006a.p007a.p008a.p011c;

import com.sothree.slidinguppanel.p086a.R.R;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: b.a.a.a.a.c.c */
public class DependencyPriorityBlockingQueue<E extends Dependency & Task & PriorityProvider> extends PriorityBlockingQueue<E> {
    final Queue<E> f236a;
    private final ReentrantLock f237b;

    public /* synthetic */ Object peek() {
        return m286b();
    }

    public /* synthetic */ Object poll() {
        return m288c();
    }

    public /* synthetic */ Object poll(long j, TimeUnit timeUnit) throws InterruptedException {
        return m282a(j, timeUnit);
    }

    public /* synthetic */ Object take() throws InterruptedException {
        return m280a();
    }

    public DependencyPriorityBlockingQueue() {
        this.f236a = new LinkedList();
        this.f237b = new ReentrantLock();
    }

    public E m280a() throws InterruptedException {
        return m287b(0, null, null);
    }

    public E m286b() {
        E e = null;
        try {
            e = m287b(1, null, null);
        } catch (InterruptedException e2) {
        }
        return e;
    }

    public E m282a(long j, TimeUnit timeUnit) throws InterruptedException {
        return m287b(3, Long.valueOf(j), timeUnit);
    }

    public E m288c() {
        E e = null;
        try {
            e = m287b(2, null, null);
        } catch (InterruptedException e2) {
        }
        return e;
    }

    public int size() {
        try {
            this.f237b.lock();
            int size = this.f236a.size() + super.size();
            return size;
        } finally {
            this.f237b.unlock();
        }
    }

    public <T> T[] toArray(T[] tArr) {
        try {
            this.f237b.lock();
            T[] a = m285a(super.toArray(tArr), this.f236a.toArray(tArr));
            return a;
        } finally {
            this.f237b.unlock();
        }
    }

    public Object[] toArray() {
        try {
            this.f237b.lock();
            Object[] a = m285a(super.toArray(), this.f236a.toArray());
            return a;
        } finally {
            this.f237b.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection) {
        try {
            this.f237b.lock();
            int drainTo = super.drainTo(collection) + this.f236a.size();
            while (!this.f236a.isEmpty()) {
                collection.add(this.f236a.poll());
            }
            return drainTo;
        } finally {
            this.f237b.unlock();
        }
    }

    public int drainTo(Collection<? super E> collection, int i) {
        try {
            this.f237b.lock();
            int drainTo = super.drainTo(collection, i);
            while (!this.f236a.isEmpty() && drainTo <= i) {
                collection.add(this.f236a.poll());
                drainTo++;
            }
            this.f237b.unlock();
            return drainTo;
        } catch (Throwable th) {
            this.f237b.unlock();
        }
    }

    public boolean contains(Object obj) {
        try {
            this.f237b.lock();
            boolean z = super.contains(obj) || this.f236a.contains(obj);
            this.f237b.unlock();
            return z;
        } catch (Throwable th) {
            this.f237b.unlock();
        }
    }

    public void clear() {
        try {
            this.f237b.lock();
            this.f236a.clear();
            super.clear();
        } finally {
            this.f237b.unlock();
        }
    }

    public boolean remove(Object obj) {
        try {
            this.f237b.lock();
            boolean z = super.remove(obj) || this.f236a.remove(obj);
            this.f237b.unlock();
            return z;
        } catch (Throwable th) {
            this.f237b.unlock();
        }
    }

    public boolean removeAll(Collection<?> collection) {
        try {
            this.f237b.lock();
            boolean removeAll = super.removeAll(collection) | this.f236a.removeAll(collection);
            return removeAll;
        } finally {
            this.f237b.unlock();
        }
    }

    E m281a(int i, Long l, TimeUnit timeUnit) throws InterruptedException {
        switch (i) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                return (Dependency) super.take();
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return (Dependency) super.peek();
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                return (Dependency) super.poll();
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                return (Dependency) super.poll(l.longValue(), timeUnit);
            default:
                return null;
        }
    }

    boolean m283a(int i, E e) {
        try {
            this.f237b.lock();
            if (i == 1) {
                super.remove(e);
            }
            boolean offer = this.f236a.offer(e);
            return offer;
        } finally {
            this.f237b.unlock();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    E m287b(int r3, java.lang.Long r4, java.util.concurrent.TimeUnit r5) throws java.lang.InterruptedException {
        /*
        r2 = this;
    L_0x0000:
        r0 = r2.m281a(r3, r4, r5);
        if (r0 == 0) goto L_0x000c;
    L_0x0006:
        r1 = r2.m284a(r0);
        if (r1 == 0) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r2.m283a(r3, r0);
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.a.a.c.c.b(int, java.lang.Long, java.util.concurrent.TimeUnit):E");
    }

    boolean m284a(E e) {
        return e.areDependenciesMet();
    }

    public void m289d() {
        try {
            this.f237b.lock();
            Iterator it = this.f236a.iterator();
            while (it.hasNext()) {
                Dependency dependency = (Dependency) it.next();
                if (m284a(dependency)) {
                    super.offer(dependency);
                    it.remove();
                }
            }
        } finally {
            this.f237b.unlock();
        }
    }

    <T> T[] m285a(T[] tArr, T[] tArr2) {
        int length = tArr.length;
        int length2 = tArr2.length;
        Object[] objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), length + length2);
        System.arraycopy(tArr, 0, objArr, 0, length);
        System.arraycopy(tArr2, 0, objArr, length, length2);
        return objArr;
    }
}
