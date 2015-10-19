package com.leanplum;

import com.leanplum.callbacks.StartCallback;
import java.util.HashMap;

/* renamed from: com.leanplum.v */
final class C0672v extends StartCallback {
    private final /* synthetic */ String f8876a;
    private final /* synthetic */ HashMap f8877b;

    C0672v(String str, HashMap hashMap) {
        this.f8876a = str;
        this.f8877b = hashMap;
    }

    public final void onResponse(boolean z) {
        Leanplum.m12451b(this.f8876a, this.f8877b);
    }
}
