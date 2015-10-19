package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: OFDErrorCodes */
/* renamed from: com.olacabs.customer.d.bt */
public class bt {
    @SerializedName(a = "header")
    private String header;
    @SerializedName(a = "message")
    private String text;

    public bt(String str, String str2) {
        this.text = str2;
        this.header = str;
    }

    public String getText() {
        return this.text;
    }

    public String getHeader() {
        return this.header;
    }
}
