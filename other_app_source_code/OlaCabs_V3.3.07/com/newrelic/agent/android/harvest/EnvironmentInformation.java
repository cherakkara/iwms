package com.newrelic.agent.android.harvest;

public class EnvironmentInformation {
    private long[] diskAvailable;
    private long memoryUsage;
    private String networkStatus;
    private String networkWanType;
    private int orientation;

    public EnvironmentInformation(long j, int i, String str, String str2, long[] jArr) {
        this.memoryUsage = j;
        this.orientation = i;
        this.networkStatus = str;
        this.networkWanType = str2;
        this.diskAvailable = jArr;
    }

    public void setMemoryUsage(long j) {
        this.memoryUsage = j;
    }

    public void setOrientation(int i) {
        this.orientation = i;
    }

    public void setNetworkStatus(String str) {
        this.networkStatus = str;
    }

    public void setNetworkWanType(String str) {
        this.networkWanType = str;
    }

    public void setDiskAvailable(long[] jArr) {
        this.diskAvailable = jArr;
    }

    public long getMemoryUsage() {
        return this.memoryUsage;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public String getNetworkStatus() {
        return this.networkStatus;
    }

    public String getNetworkWanType() {
        return this.networkWanType;
    }

    public long[] getDiskAvailable() {
        return this.diskAvailable;
    }
}
