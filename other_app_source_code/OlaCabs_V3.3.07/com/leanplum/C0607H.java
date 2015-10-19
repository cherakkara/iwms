package com.leanplum;

import com.leanplum.callbacks.StartCallback;

/* renamed from: com.leanplum.H */
final class C0607H extends StartCallback {
    C0607H(C0606G c0606g) {
    }

    public final void onResponse(boolean z) {
        if (Leanplum.f8563j != null) {
            Leanplum.f8563j.setSuccess(z);
            C0612M.m12512a().m12513a(Leanplum.f8563j);
        }
        if (z) {
            Leanplum.m12458d();
        }
    }
}
