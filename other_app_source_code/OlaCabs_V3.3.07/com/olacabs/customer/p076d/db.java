package com.olacabs.customer.p076d;

import com.android.volley.VolleyError;
import java.util.HashMap;

/* compiled from: SherlockEvent */
/* renamed from: com.olacabs.customer.d.db */
public class db {
    private int arguments;
    private long endTime;
    private VolleyError error;
    private String errorReason;
    private String eventName;
    private HashMap<String, String> instrumentationDetails;
    private boolean isPopupShown;

    public db(String str, VolleyError volleyError, String str2, boolean z) {
        this.eventName = str;
        this.error = volleyError;
        this.errorReason = str2;
        this.isPopupShown = z;
        this.endTime = System.currentTimeMillis();
    }

    public db(String str, HashMap<String, String> hashMap, VolleyError volleyError) {
        this.eventName = str;
        this.instrumentationDetails = hashMap;
        this.error = volleyError;
        this.errorReason = "NA";
        this.endTime = System.currentTimeMillis();
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String str) {
        this.eventName = str;
    }

    public VolleyError getError() {
        return this.error;
    }

    public void setError(VolleyError volleyError) {
        this.error = volleyError;
    }

    public String getErrorReason() {
        return this.errorReason;
    }

    public void setErrorReason(String str) {
        this.errorReason = str;
    }

    public boolean isPopupShown() {
        return this.isPopupShown;
    }

    public void setIsPopupShown(boolean z) {
        this.isPopupShown = z;
    }

    public HashMap<String, String> getInstrumentationDetails() {
        return this.instrumentationDetails;
    }

    public void setInstrumentationDetails(HashMap<String, String> hashMap) {
        this.instrumentationDetails = hashMap;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public int getArguments() {
        return this.arguments;
    }

    public void setArguments(int i) {
        this.arguments = i;
    }
}
