package com.leanplum;

import com.leanplum.callbacks.StartCallback;
import java.util.HashMap;

/* renamed from: com.leanplum.x */
final class C0674x extends StartCallback {
    private final /* synthetic */ HashMap f8892a;

    C0674x(HashMap hashMap) {
        this.f8892a = hashMap;
    }

    public final void onResponse(boolean z) {
        Leanplum.m12453b(this.f8892a);
    }
}
