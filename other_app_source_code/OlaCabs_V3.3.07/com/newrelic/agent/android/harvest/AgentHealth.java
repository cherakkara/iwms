package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.com.google.gson.JsonArray;
import java.text.MessageFormat;

public class AgentHealth extends HarvestableArray {
    public static final String DEFAULT_KEY = "Exception";
    private static final AgentLog log;
    protected final AgentHealthExceptions agentHealthExceptions;

    public AgentHealth() {
        this.agentHealthExceptions = new AgentHealthExceptions();
    }

    static {
        log = AgentLogManager.getAgentLog();
    }

    public static void noticeException(Exception exception) {
        AgentHealthException agentHealthException = null;
        if (exception != null) {
            agentHealthException = new AgentHealthException(exception);
        }
        noticeException(agentHealthException);
    }

    public static void noticeException(AgentHealthException agentHealthException) {
        noticeException(agentHealthException, DEFAULT_KEY);
    }

    public static void noticeException(AgentHealthException agentHealthException, String str) {
        if (agentHealthException != null) {
            StatsEngine statsEngine = StatsEngine.get();
            if (statsEngine != null) {
                if (str == null) {
                    log.warning("Passed metric key is null. Defaulting to Exception");
                }
                String str2 = "Supportability/AgentHealth/{0}/{1}/{2}/{3}";
                Object[] objArr = new Object[4];
                if (str == null) {
                    str = DEFAULT_KEY;
                }
                objArr[0] = str;
                objArr[1] = agentHealthException.getSourceClass();
                objArr[2] = agentHealthException.getSourceMethod();
                objArr[3] = agentHealthException.getExceptionClass();
                statsEngine.inc(MessageFormat.format(str2, objArr));
                TaskQueue.queue(agentHealthException);
                return;
            }
            log.error("StatsEngine is null. Exception not recorded.");
            return;
        }
        log.error("AgentHealthException is null. StatsEngine not updated");
    }

    public void addException(AgentHealthException agentHealthException) {
        this.agentHealthExceptions.add(agentHealthException);
    }

    public void clear() {
        this.agentHealthExceptions.clear();
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        if (!this.agentHealthExceptions.isEmpty()) {
            jsonArray.add(this.agentHealthExceptions.asJsonObject());
        }
        return jsonArray;
    }
}
