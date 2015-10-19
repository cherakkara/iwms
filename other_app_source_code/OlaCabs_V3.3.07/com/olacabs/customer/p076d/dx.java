package com.olacabs.customer.p076d;

import com.olacabs.customer.utils.Utils;

/* compiled from: WaitTime */
/* renamed from: com.olacabs.customer.d.dx */
public class dx implements dw {
    private static final String JSON_ARRAY_TAG = "";
    private static final String JSON_OBJECT_TAG = "wait_time";
    public static final String TAG;
    private String unit;
    private String value;

    static {
        TAG = ap.class.getSimpleName();
    }

    public String getValue() {
        return this.value;
    }

    public String getUnit() {
        return this.unit;
    }

    public String getJsonObjectTag() {
        return JSON_OBJECT_TAG;
    }

    public String getJsonArrayTag() {
        return JSON_ARRAY_TAG;
    }

    public boolean isValid() {
        return Utils.m14924g(this.value) && Utils.m14924g(this.unit);
    }
}
