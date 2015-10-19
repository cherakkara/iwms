package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* renamed from: com.olacabs.customer.d.b */
public class AddEditFavouriteResponse {
    private String name;
    private String reason;
    @SerializedName(a = "request_type")
    private String requestType;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public String getName() {
        return this.name;
    }

    public String getReason() {
        return this.reason;
    }
}
