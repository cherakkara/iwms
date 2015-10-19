package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Utils;

/* compiled from: CityBaseFareBreakUpResponse */
/* renamed from: com.olacabs.customer.d.aa */
public class aa implements dw {
    @SerializedName(a = "display_text")
    private String displayText;
    @SerializedName(a = "request_type")
    public String requestType;
    public String status;
    private String value;

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public String getValue() {
        return this.value;
    }

    public boolean isValid() {
        return Utils.m14924g(this.displayText) && Utils.m14924g(this.value);
    }
}
