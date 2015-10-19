package com.olacabs.customer.p076d;

import com.olacabs.customer.utils.Utils;

/* renamed from: com.olacabs.customer.d.v */
public class CheckoutResponse implements dw {
    private long id;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public boolean isValid() {
        return this.id != 0 && Utils.m14924g(this.status) && (this.status.equals("approved") || this.status.equals("created"));
    }
}
