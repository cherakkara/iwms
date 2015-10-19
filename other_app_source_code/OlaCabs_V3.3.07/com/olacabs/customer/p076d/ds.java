package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Constants;
import java.util.List;
import java.util.Map;

/* compiled from: UserFavourites */
/* renamed from: com.olacabs.customer.d.ds */
public class ds implements bv {
    private List<aw> favourites;
    Map<String, String> origRequestParams;
    long origTimeStamp;
    @SerializedName(a = "request_type")
    private String requestType;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public List<aw> getFavourites() {
        return this.favourites;
    }

    public void setOrigTimeStamp(long j) {
        this.origTimeStamp = j;
    }

    public long getOrigTimeStamp() {
        return this.origTimeStamp;
    }

    public void setOrigParams(Map<String, String> map) {
        this.origRequestParams = map;
    }

    public boolean isValid(Map<String, String> map) {
        return ((String) map.get(Constants.USER_ID)).equals(this.origRequestParams.get(Constants.USER_ID));
    }

    public boolean markAsInvalid() {
        return false;
    }
}
