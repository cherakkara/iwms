package com.olacabs.customer.p076d;

/* compiled from: Duration */
/* renamed from: com.olacabs.customer.d.as */
public class as {
    private static final String JSON_ARRAY_TAG = "durations";
    private static final String JSON_OBJECT_TAG = "duration";
    public static final String TAG;
    private String unit;
    private String value;

    static {
        TAG = as.class.getSimpleName();
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
}
