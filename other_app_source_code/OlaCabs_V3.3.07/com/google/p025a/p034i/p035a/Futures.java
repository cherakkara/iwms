package com.google.p025a.p034i.p035a;

import com.google.p025a.p026a.Function;
import com.google.p025a.p026a.Preconditions;
import com.google.p025a.p028c.az;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* renamed from: com.google.a.i.a.f */
public final class Futures {
    private static final AsyncFunction<ListenableFuture<Object>, Object> f1958a;
    private static final az<Constructor<?>> f1959b;

    /* renamed from: com.google.a.i.a.f.1 */
    static class Futures implements AsyncFunction<I, O> {
    }

    /* renamed from: com.google.a.i.a.f.2 */
    static class Futures implements AsyncFunction<ListenableFuture<Object>, Object> {
        Futures() {
        }
    }

    /* renamed from: com.google.a.i.a.f.3 */
    static class Futures implements Function<Constructor<?>, Boolean> {
        Futures() {
        }

        public Boolean m3131a(Constructor<?> constructor) {
            return Boolean.valueOf(Arrays.asList(constructor.getParameterTypes()).contains(String.class));
        }
    }

    /* renamed from: com.google.a.i.a.f.a */
    private static abstract class Futures<V> implements ListenableFuture<V> {
        private static final Logger f1956a;

        public abstract V get() throws ExecutionException;

        private Futures() {
        }

        static {
            f1956a = Logger.getLogger(Futures.class.getName());
        }

        public void m3133a(Runnable runnable, Executor executor) {
            Preconditions.m1818a((Object) runnable, (Object) "Runnable was null.");
            Preconditions.m1818a((Object) executor, (Object) "Executor was null.");
            try {
                executor.execute(runnable);
            } catch (Throwable e) {
                f1956a.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
            }
        }

        public boolean cancel(boolean z) {
            return false;
        }

        public V get(long j, TimeUnit timeUnit) throws ExecutionException {
            Preconditions.m1817a((Object) timeUnit);
            return get();
        }

        public boolean isCancelled() {
            return false;
        }

        public boolean isDone() {
            return true;
        }
    }

    /* renamed from: com.google.a.i.a.f.b */
    private static class Futures<V> extends Futures<V> {
        @Nullable
        private final V f1957a;

        Futures(@Nullable V v) {
            super();
            this.f1957a = v;
        }

        public V get() {
            return this.f1957a;
        }
    }

    public static <V> ListenableFuture<V> m3134a(@Nullable V v) {
        return new Futures(v);
    }

    static {
        f1958a = new Futures();
        f1959b = az.m2824b().m2827a(new Futures()).m2826a();
    }
}
