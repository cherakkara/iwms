package com.olacabs.p067a.p068a.p069a.p070a;

import com.google.gson.p063a.SerializedName;
import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.newrelic.agent.android.instrumentation.Trace;

/* renamed from: com.olacabs.a.a.a.a.p */
public class ResponseInfo {
    @SerializedName(a = "errorMessage")
    private String mErrorMessage;
    @SerializedName(a = "payloadSize")
    private Long mPayloadSize;
    private transient byte[] mResponsePayload;
    @SerializedName(a = "status")
    private String mStatus;

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public byte[] getResponsePayload() {
        return this.mResponsePayload;
    }

    public void setResponsePayload(byte[] bArr) {
        this.mResponsePayload = bArr;
        this.mPayloadSize = new Long((long) bArr.length);
    }

    public void setStatus(int i) {
        if (HarvestErrorCodes.NSURLErrorBadURL == i) {
            this.mStatus = Trace.NULL;
        } else {
            this.mStatus = Integer.toString(i);
        }
    }

    public void setErrorMessage(String str) {
        this.mErrorMessage = str;
    }

    public String getStatus() {
        return this.mStatus;
    }
}
