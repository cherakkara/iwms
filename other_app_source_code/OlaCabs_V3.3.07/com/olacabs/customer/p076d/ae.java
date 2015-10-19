package com.olacabs.customer.p076d;

/* compiled from: CouponCodeApplyResponse */
/* renamed from: com.olacabs.customer.d.ae */
public class ae {
    private String code;
    private String header;
    private String reason;
    private String status;
    private String text;
    private boolean valid;

    public boolean getValid() {
        return this.valid;
    }

    public String getCode() {
        return this.code;
    }

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
}
