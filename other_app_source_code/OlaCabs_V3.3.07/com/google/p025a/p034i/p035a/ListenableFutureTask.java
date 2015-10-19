package com.google.p025a.p034i.p035a;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import javax.annotation.Nullable;

/* renamed from: com.google.a.i.a.h */
public class ListenableFutureTask<V> extends FutureTask<V> implements ListenableFuture<V> {
    private final ExecutionList f1960a;

    public static <V> ListenableFutureTask<V> m3136a(Callable<V> callable) {
        return new ListenableFutureTask(callable);
    }

    public static <V> ListenableFutureTask<V> m3135a(Runnable runnable, @Nullable V v) {
        return new ListenableFutureTask(runnable, v);
    }

    ListenableFutureTask(Callable<V> callable) {
        super(callable);
        this.f1960a = new ExecutionList();
    }

    ListenableFutureTask(Runnable runnable, @Nullable V v) {
        super(runnable, v);
        this.f1960a = new ExecutionList();
    }

    public void m3137a(Runnable runnable, Executor executor) {
        this.f1960a.m3130a(runnable, executor);
    }

    protected void done() {
        this.f1960a.m3129a();
    }
}
