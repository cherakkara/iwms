package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;

/* compiled from: FavouriteDeleteResponse */
/* renamed from: com.olacabs.customer.d.ay */
public class ay {
    @SerializedName(a = "fav_id")
    private int favId;
    private String reason;
    @SerializedName(a = "request_type")
    private String requestType;
    private String status;
    private String text;

    public int getFavId() {
        return this.favId;
    }

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public String getText() {
        return this.text;
    }

    public String getReason() {
        return this.reason;
    }
}
