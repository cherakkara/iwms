package com.newrelic.agent.android;

import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.util.Encoder;
import java.util.List;

public class Agent {
    private static final AgentImpl NULL_AGENT_IMPL;
    public static final String VERSION = "5.1.2";
    private static AgentImpl impl;
    private static Object implLock;

    static {
        NULL_AGENT_IMPL = new NullAgentImpl();
        implLock = new Object();
        impl = NULL_AGENT_IMPL;
    }

    public static void setImpl(AgentImpl agentImpl) {
        synchronized (implLock) {
            if (agentImpl == null) {
                impl = NULL_AGENT_IMPL;
            } else {
                impl = agentImpl;
            }
        }
    }

    public static AgentImpl getImpl() {
        AgentImpl agentImpl;
        synchronized (implLock) {
            agentImpl = impl;
        }
        return agentImpl;
    }

    public static String getVersion() {
        return VERSION;
    }

    public static String getCrossProcessId() {
        return getImpl().getCrossProcessId();
    }

    public static int getStackTraceLimit() {
        return getImpl().getStackTraceLimit();
    }

    public static int getResponseBodyLimit() {
        return getImpl().getResponseBodyLimit();
    }

    public static void addTransactionData(TransactionData transactionData) {
        getImpl().addTransactionData(transactionData);
    }

    public static List<TransactionData> getAndClearTransactionData() {
        return getImpl().getAndClearTransactionData();
    }

    public static void mergeTransactionData(List<TransactionData> list) {
        getImpl().mergeTransactionData(list);
    }

    public static String getActiveNetworkCarrier() {
        return getImpl().getNetworkCarrier();
    }

    public static String getActiveNetworkWanType() {
        return getImpl().getNetworkWanType();
    }

    public static void disable() {
        getImpl().disable();
    }

    public static boolean isDisabled() {
        return getImpl().isDisabled();
    }

    public static void start() {
        getImpl().start();
    }

    public static void stop() {
        getImpl().stop();
    }

    public static void setLocation(String str, String str2) {
        getImpl().setLocation(str, str2);
    }

    public static Encoder getEncoder() {
        return getImpl().getEncoder();
    }

    public static DeviceInformation getDeviceInformation() {
        return getImpl().getDeviceInformation();
    }

    public static ApplicationInformation getApplicationInformation() {
        return getImpl().getApplicationInformation();
    }
}
