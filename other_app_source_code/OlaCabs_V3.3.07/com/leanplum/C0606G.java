package com.leanplum;

import com.leanplum.callbacks.RegisterDeviceCallback.EmailCallback;
import com.leanplum.callbacks.StartCallback;
import com.olacabs.customer.utils.Constants;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.leanplum.G */
final class C0606G extends EmailCallback {
    C0606G(C0605F c0605f) {
    }

    public final void onResponse(String str) {
        if (str != null) {
            StartCallback c0607h = new C0607H(this);
            Map hashMap = new HashMap();
            hashMap.put(Constants.EMAIL, str);
            C0618S b = C0618S.m12530b("registerDevice", hashMap);
            b.m12551a(new ag(c0607h));
            b.m12550a(new ai(c0607h));
            b.m12557h();
        }
    }
}
