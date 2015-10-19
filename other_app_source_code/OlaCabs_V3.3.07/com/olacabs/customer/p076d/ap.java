package com.olacabs.customer.p076d;

import com.olacabs.customer.utils.Utils;

/* compiled from: Distance */
/* renamed from: com.olacabs.customer.d.ap */
public class ap implements dw {
    private static final String JSON_ARRAY_TAG = "distances";
    private static final String JSON_OBJECT_TAG = "distance";
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
