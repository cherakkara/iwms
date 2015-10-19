package com.olacabs.customer.p076d;

/* compiled from: Route */
/* renamed from: com.olacabs.customer.d.cw */
public class cw {
    private static final String JSON_ARRAY_TAG = "";
    private static final String JSON_OBJECT_TAG = "route";
    public static final String TAG;
    private ap distance;
    private as duration;
    private dx wait_time;

    static {
        TAG = cw.class.getSimpleName();
    }

    public ap getDistance() {
        if (this.distance == null) {
            this.distance = new ap();
        }
        return this.distance;
    }

    public dx getWait_time() {
        if (this.wait_time == null) {
            this.wait_time = new dx();
        }
        return this.wait_time;
    }

    public as getDuration() {
        if (this.duration == null) {
            this.duration = new as();
        }
        return this.duration;
    }

    public String getJsonObjectTag() {
        return null;
    }

    public String getJsonArrayTag() {
        return null;
    }
}
