package com.olacabs.customer.p076d;

import com.olacabs.customer.utils.Utils;

/* compiled from: RideTrackResponse */
/* renamed from: com.olacabs.customer.d.ct */
public class ct {
    private String header;
    private String reason;
    private String status;
    private String text;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public boolean isValid() {
        return Utils.m14924g(this.status);
    }
}
