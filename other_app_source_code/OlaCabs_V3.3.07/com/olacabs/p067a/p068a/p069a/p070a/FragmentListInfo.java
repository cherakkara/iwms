package com.olacabs.p067a.p068a.p069a.p070a;

import com.google.gson.p063a.SerializedName;
import java.util.UUID;

/* renamed from: com.olacabs.a.a.a.a.j */
public class FragmentListInfo {
    @SerializedName(a = "data")
    private FragmentInfo fragmentInfo;
    @SerializedName(a = "id")
    private UUID id;
    @SerializedName(a = "timestamp")
    private long timestamp;

    public FragmentInfo getFragmentInfo() {
        return this.fragmentInfo;
    }

    public void setFragmentInfo(FragmentInfo fragmentInfo) {
        this.fragmentInfo = fragmentInfo;
    }

    public FragmentListInfo(FragmentInfo fragmentInfo) {
        this.id = UUID.randomUUID();
        setFragmentInfo(fragmentInfo);
        this.timestamp = fragmentInfo.getTimeStamp();
    }
}
