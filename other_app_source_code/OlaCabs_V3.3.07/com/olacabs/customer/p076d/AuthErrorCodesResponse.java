package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Utils;

/* renamed from: com.olacabs.customer.d.g */
public class AuthErrorCodesResponse implements dw {
    @SerializedName(a = "code")
    private String code;
    @SerializedName(a = "header")
    private String header;
    @SerializedName(a = "message")
    private String text;

    public AuthErrorCodesResponse(String str, String str2) {
        this.text = str;
        this.code = str2;
    }

    public String getText() {
        return this.text;
    }

    public String getCode() {
        return this.code;
    }

    public String getHeader() {
        return this.header;
    }

    public boolean isValid() {
        return Utils.m14924g(getCode());
    }
}
