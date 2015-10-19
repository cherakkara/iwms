package com.newrelic.agent.android.logging;

public class AgentLogManager {
    private static DefaultAgentLog instance;

    static {
        instance = new DefaultAgentLog();
    }

    public static AgentLog getAgentLog() {
        return instance;
    }

    public static void setAgentLog(AgentLog agentLog) {
        instance.setImpl(agentLog);
    }
}
