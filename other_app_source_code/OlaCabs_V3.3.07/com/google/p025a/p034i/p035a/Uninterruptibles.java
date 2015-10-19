package com.google.p025a.p034i.p035a;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: com.google.a.i.a.m */
public final class Uninterruptibles {
    public static <V> V m3146a(Future<V> future) throws ExecutionException {
        V v;
        Object obj = null;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException e) {
                obj = 1;
            } catch (Throwable th) {
                if (obj != null) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (obj != null) {
            Thread.currentThread().interrupt();
        }
        return v;
    }
}
