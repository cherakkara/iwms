package com.olacabs.customer.p076d;

/* compiled from: ServiceType */
/* renamed from: com.olacabs.customer.d.cz */
public class cz {
    private static final String JSON_ARRAY_TAG = "";
    private static final String JSON_OBJECT_TAG = "service_type";
    public static final String TAG;
    private String display_name;
    private String id;

    static {
        TAG = cz.class.getSimpleName();
    }

    public String getId() {
        return this.id;
    }

    public String getDisplay_name() {
        return this.display_name;
    }

    public String getJsonObjectTag() {
        return JSON_OBJECT_TAG;
    }

    public String getJsonArrayTag() {
        return JSON_ARRAY_TAG;
    }
}
