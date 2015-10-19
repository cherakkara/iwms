package com.olacabs.customer.p076d;

import java.util.Map;

/* compiled from: OlaCacheEntry */
/* renamed from: com.olacabs.customer.d.bv */
public interface bv {
    boolean isValid(Map<String, String> map);

    boolean markAsInvalid();

    void setOrigParams(Map<String, String> map);

    void setOrigTimeStamp(long j);
}
