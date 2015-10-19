package com.payu.sdk;

import com.newrelic.agent.android.instrumentation.Trace;
import java.util.HashMap;

/* renamed from: com.payu.sdk.d */
public class C0925d extends HashMap<String, String> {
    public String m14947a(String str) {
        String str2 = (String) super.get(str);
        return str2 == null ? Trace.NULL : str2;
    }
}
