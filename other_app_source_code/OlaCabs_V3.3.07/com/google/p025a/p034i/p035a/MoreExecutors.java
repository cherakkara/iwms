package com.google.p025a.p034i.p035a;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.google.a.i.a.j */
public final class MoreExecutors {

    /* renamed from: com.google.a.i.a.j.1 */
    static class MoreExecutors implements Runnable {
        final /* synthetic */ BlockingQueue f1961a;
        final /* synthetic */ ListenableFuture f1962b;

        MoreExecutors(BlockingQueue blockingQueue, ListenableFuture listenableFuture) {
            this.f1961a = blockingQueue;
            this.f1962b = listenableFuture;
        }

        public void run() {
            this.f1961a.add(this.f1962b);
        }
    }

    /* renamed from: com.google.a.i.a.j.a */
    private static class MoreExecutors extends AbstractListeningExecutorService {
        private final Lock f1963a;
        private final Condition f1964b;
        private int f1965c;
        private boolean f1966d;

        private MoreExecutors() {
            this.f1963a = new ReentrantLock();
            this.f1964b = this.f1963a.newCondition();
            this.f1965c = 0;
            this.f1966d = false;
        }

        public void execute(Runnable runnable) {
            m3138a();
            try {
                runnable.run();
            } finally {
                m3139b();
            }
        }

        public boolean isShutdown() {
            this.f1963a.lock();
            try {
                boolean z = this.f1966d;
                return z;
            } finally {
                this.f1963a.unlock();
            }
        }

        public void shutdown() {
            this.f1963a.lock();
            try {
                this.f1966d = true;
            } finally {
                this.f1963a.unlock();
            }
        }

        public List<Runnable> shutdownNow() {
            shutdown();
            return Collections.emptyList();
        }

        public boolean isTerminated() {
            this.f1963a.lock();
            try {
                boolean z = this.f1966d && this.f1965c == 0;
                this.f1963a.unlock();
                return z;
            } catch (Throwable th) {
                this.f1963a.unlock();
            }
        }

        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            long toNanos = timeUnit.toNanos(j);
            this.f1963a.lock();
            while (!isTerminated()) {
                if (toNanos <= 0) {
                    return false;
                }
                try {
                    toNanos = this.f1964b.awaitNanos(toNanos);
                } finally {
                    this.f1963a.unlock();
                }
            }
            this.f1963a.unlock();
            return true;
        }

        private void m3138a() {
            this.f1963a.lock();
            try {
                if (isShutdown()) {
                    throw new RejectedExecutionException("Executor already shutdown");
                }
                this.f1965c++;
            } finally {
                this.f1963a.unlock();
            }
        }

        private void m3139b() {
            this.f1963a.lock();
            try {
                this.f1965c--;
                if (isTerminated()) {
                    this.f1964b.signalAll();
                }
                this.f1963a.unlock();
            } catch (Throwable th) {
                this.f1963a.unlock();
            }
        }
    }

    public static ListeningExecutorService m3141a() {
        return new MoreExecutors();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> T m3142a(com.google.p025a.p034i.p035a.ListeningExecutorService r19, java.util.Collection<? extends java.util.concurrent.Callable<T>> r20, boolean r21, long r22) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {
        /*
        com.google.p025a.p026a.Preconditions.m1817a(r19);
        r3 = r20.size();
        if (r3 <= 0) goto L_0x0073;
    L_0x0009:
        r2 = 1;
    L_0x000a:
        com.google.p025a.p026a.Preconditions.m1822a(r2);
        r12 = com.google.p025a.p028c.ar.m2523b(r3);
        r13 = com.google.p025a.p028c.bb.m2837a();
        r4 = 0;
        if (r21 == 0) goto L_0x0075;
    L_0x0018:
        r6 = java.lang.System.nanoTime();	 Catch:{ all -> 0x0083 }
    L_0x001c:
        r14 = r20.iterator();	 Catch:{ all -> 0x0083 }
        r2 = r14.next();	 Catch:{ all -> 0x0083 }
        r2 = (java.util.concurrent.Callable) r2;	 Catch:{ all -> 0x0083 }
        r0 = r19;
        r2 = com.google.p025a.p034i.p035a.MoreExecutors.m3140a(r0, r2, r13);	 Catch:{ all -> 0x0083 }
        r12.add(r2);	 Catch:{ all -> 0x0083 }
        r3 = r3 + -1;
        r5 = 1;
        r10 = r22;
    L_0x0034:
        r2 = r13.poll();	 Catch:{ all -> 0x0083 }
        r2 = (java.util.concurrent.Future) r2;	 Catch:{ all -> 0x0083 }
        if (r2 != 0) goto L_0x00dc;
    L_0x003c:
        if (r3 <= 0) goto L_0x0078;
    L_0x003e:
        r8 = r3 + -1;
        r3 = r14.next();	 Catch:{ all -> 0x0083 }
        r3 = (java.util.concurrent.Callable) r3;	 Catch:{ all -> 0x0083 }
        r0 = r19;
        r3 = com.google.p025a.p034i.p035a.MoreExecutors.m3140a(r0, r3, r13);	 Catch:{ all -> 0x0083 }
        r12.add(r3);	 Catch:{ all -> 0x0083 }
        r3 = r5 + 1;
        r5 = r8;
        r8 = r10;
        r15 = r2;
        r2 = r3;
        r3 = r15;
    L_0x0056:
        if (r3 == 0) goto L_0x00da;
    L_0x0058:
        r2 = r2 + -1;
        r3 = r3.get();	 Catch:{ ExecutionException -> 0x00d7, RuntimeException -> 0x00ca }
        r4 = r12.iterator();
    L_0x0062:
        r2 = r4.hasNext();
        if (r2 == 0) goto L_0x00d9;
    L_0x0068:
        r2 = r4.next();
        r2 = (java.util.concurrent.Future) r2;
        r5 = 1;
        r2.cancel(r5);
        goto L_0x0062;
    L_0x0073:
        r2 = 0;
        goto L_0x000a;
    L_0x0075:
        r6 = 0;
        goto L_0x001c;
    L_0x0078:
        if (r5 != 0) goto L_0x009a;
    L_0x007a:
        if (r4 != 0) goto L_0x0082;
    L_0x007c:
        r4 = new java.util.concurrent.ExecutionException;	 Catch:{ all -> 0x0083 }
        r2 = 0;
        r4.<init>(r2);	 Catch:{ all -> 0x0083 }
    L_0x0082:
        throw r4;	 Catch:{ all -> 0x0083 }
    L_0x0083:
        r2 = move-exception;
        r3 = r2;
        r4 = r12.iterator();
    L_0x0089:
        r2 = r4.hasNext();
        if (r2 == 0) goto L_0x00d6;
    L_0x008f:
        r2 = r4.next();
        r2 = (java.util.concurrent.Future) r2;
        r5 = 1;
        r2.cancel(r5);
        goto L_0x0089;
    L_0x009a:
        if (r21 == 0) goto L_0x00be;
    L_0x009c:
        r2 = java.util.concurrent.TimeUnit.NANOSECONDS;	 Catch:{ all -> 0x0083 }
        r2 = r13.poll(r10, r2);	 Catch:{ all -> 0x0083 }
        r2 = (java.util.concurrent.Future) r2;	 Catch:{ all -> 0x0083 }
        if (r2 != 0) goto L_0x00ac;
    L_0x00a6:
        r2 = new java.util.concurrent.TimeoutException;	 Catch:{ all -> 0x0083 }
        r2.<init>();	 Catch:{ all -> 0x0083 }
        throw r2;	 Catch:{ all -> 0x0083 }
    L_0x00ac:
        r8 = java.lang.System.nanoTime();	 Catch:{ all -> 0x0083 }
        r6 = r8 - r6;
        r6 = r10 - r6;
        r15 = r2;
        r2 = r5;
        r5 = r3;
        r3 = r15;
        r16 = r8;
        r8 = r6;
        r6 = r16;
        goto L_0x0056;
    L_0x00be:
        r2 = r13.take();	 Catch:{ all -> 0x0083 }
        r2 = (java.util.concurrent.Future) r2;	 Catch:{ all -> 0x0083 }
        r8 = r10;
        r15 = r5;
        r5 = r3;
        r3 = r2;
        r2 = r15;
        goto L_0x0056;
    L_0x00ca:
        r4 = move-exception;
        r3 = new java.util.concurrent.ExecutionException;	 Catch:{ all -> 0x0083 }
        r3.<init>(r4);	 Catch:{ all -> 0x0083 }
    L_0x00d0:
        r4 = r3;
        r10 = r8;
        r3 = r5;
        r5 = r2;
        goto L_0x0034;
    L_0x00d6:
        throw r3;
    L_0x00d7:
        r3 = move-exception;
        goto L_0x00d0;
    L_0x00d9:
        return r3;
    L_0x00da:
        r3 = r4;
        goto L_0x00d0;
    L_0x00dc:
        r8 = r10;
        r15 = r5;
        r5 = r3;
        r3 = r2;
        r2 = r15;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.a.i.a.j.a(com.google.a.i.a.i, java.util.Collection, boolean, long):T");
    }

    private static <T> ListenableFuture<T> m3140a(ListeningExecutorService listeningExecutorService, Callable<T> callable, BlockingQueue<Future<T>> blockingQueue) {
        ListenableFuture<T> a = listeningExecutorService.m3124a(callable);
        a.m3118a(new MoreExecutors(blockingQueue, a), MoreExecutors.m3141a());
        return a;
    }
}
