package com.newrelic.agent.android;

import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.EnvironmentInformation;
import com.newrelic.agent.android.util.Encoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class NullAgentImpl implements AgentImpl {
    public static final NullAgentImpl instance;
    private final AgentConfiguration agentConfiguration;
    private final ReentrantLock lock;
    private int responseBodyLimit;
    private long sessionStartTimeMillis;

    /* renamed from: com.newrelic.agent.android.NullAgentImpl.1 */
    class C07291 implements Encoder {
        C07291() {
        }

        public String encode(byte[] bArr) {
            return new String(bArr);
        }
    }

    static {
        instance = new NullAgentImpl();
    }

    public NullAgentImpl() {
        this.lock = new ReentrantLock();
        this.agentConfiguration = new AgentConfiguration();
        this.sessionStartTimeMillis = 0;
    }

    public void addTransactionData(TransactionData transactionData) {
    }

    public List<TransactionData> getAndClearTransactionData() {
        return new ArrayList();
    }

    public void mergeTransactionData(List<TransactionData> list) {
    }

    public String getCrossProcessId() {
        return null;
    }

    public int getStackTraceLimit() {
        return 0;
    }

    public int getResponseBodyLimit() {
        return this.responseBodyLimit;
    }

    public void setResponseBodyLimit(int i) {
        this.responseBodyLimit = i;
    }

    public void start() {
    }

    public void stop() {
    }

    public void disable() {
    }

    public boolean isDisabled() {
        return true;
    }

    public String getNetworkCarrier() {
        return WanType.UNKNOWN;
    }

    public String getNetworkWanType() {
        return WanType.UNKNOWN;
    }

    public void setLocation(String str, String str2) {
    }

    public Encoder getEncoder() {
        return new C07291();
    }

    public DeviceInformation getDeviceInformation() {
        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setOsName("Android");
        deviceInformation.setOsVersion("2.3");
        deviceInformation.setOsBuild("a.b.c");
        deviceInformation.setManufacturer("Fake");
        deviceInformation.setModel("NullAgent");
        deviceInformation.setAgentName("AndroidAgent");
        deviceInformation.setAgentVersion("2.123");
        deviceInformation.setDeviceId("389C9738-A761-44DE-8A66-1668CFD67DA1");
        deviceInformation.setArchitecture("Fake Arch");
        deviceInformation.setRunTime("1.7.0");
        deviceInformation.setSize("Fake Size");
        return deviceInformation;
    }

    public ApplicationInformation getApplicationInformation() {
        return new ApplicationInformation("null", "0.0", "null", "0");
    }

    public EnvironmentInformation getEnvironmentInformation() {
        return new EnvironmentInformation(0, 1, WanType.NONE, WanType.NONE, new long[]{0, 0});
    }

    public boolean updateSavedConnectInformation() {
        return false;
    }

    public long getSessionDurationMillis() {
        return this.sessionStartTimeMillis;
    }
}
