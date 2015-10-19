package com.google.p025a.p034i.p035a;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* renamed from: com.google.a.i.a.i */
public interface ListeningExecutorService extends ExecutorService {
    <T> ListenableFuture<T> m3124a(Callable<T> callable);
}
