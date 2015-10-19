package com.olacabs.customer.p076d;

import com.google.gson.p063a.SerializedName;
import com.olacabs.customer.utils.Constants;
import java.util.Map;

/* compiled from: RidesCategoryResponse */
/* renamed from: com.olacabs.customer.d.cv */
public class cv implements bv {
    private String header;
    Map<String, String> origRequestParams;
    long origTimeStamp;
    @SerializedName(a = "request_type")
    private String requestType;
    @SerializedName(a = "rides")
    private cu ridesCategoryList;
    private String status;
    private String text;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getRequestType() {
        return this.requestType;
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

    public void setRequestType(String str) {
        this.requestType = str;
    }

    public cu getRidesCategoryList() {
        return this.ridesCategoryList;
    }

    public void setRidesCategoryList(cu cuVar) {
        this.ridesCategoryList = cuVar;
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
