package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

public class AgentHealthException extends HarvestableArray {
    private final AtomicLong count;
    private String exceptionClass;
    private Map<String, String> extras;
    private String message;
    private StackTraceElement[] stackTrace;
    private String threadName;

    public AgentHealthException(Exception exception) {
        this(exception, Thread.currentThread().getName());
    }

    public AgentHealthException(Exception exception, String str) {
        this(exception.getClass().getName(), exception.getMessage(), str, exception.getStackTrace());
    }

    public AgentHealthException(String str, String str2, String str3, StackTraceElement[] stackTraceElementArr) {
        this(str, str2, str3, stackTraceElementArr, null);
    }

    public AgentHealthException(String str, String str2, String str3, StackTraceElement[] stackTraceElementArr, Map<String, String> map) {
        this.count = new AtomicLong(1);
        this.exceptionClass = str;
        this.message = str2;
        this.threadName = str3;
        this.stackTrace = stackTraceElementArr;
        this.extras = map;
    }

    public void increment() {
        this.count.getAndIncrement();
    }

    public void increment(long j) {
        this.count.getAndAdd(j);
    }

    public String getExceptionClass() {
        return this.exceptionClass;
    }

    public String getMessage() {
        return this.message;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public StackTraceElement[] getStackTrace() {
        return this.stackTrace;
    }

    public long getCount() {
        return this.count.get();
    }

    public Map<String, String> getExtras() {
        return this.extras;
    }

    public String getSourceClass() {
        return this.stackTrace[0].getClassName();
    }

    public String getSourceMethod() {
        return this.stackTrace[0].getMethodName();
    }

    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(this.exceptionClass));
        jsonArray.add(new JsonPrimitive(this.message == null ? Trace.NULL : this.message));
        jsonArray.add(new JsonPrimitive(this.threadName));
        jsonArray.add(stackTraceToJson());
        jsonArray.add(new JsonPrimitive(Long.valueOf(this.count.get())));
        jsonArray.add(extrasToJson());
        return jsonArray;
    }

    private JsonArray stackTraceToJson() {
        JsonArray jsonArray = new JsonArray();
        for (StackTraceElement stackTraceElement : this.stackTrace) {
            jsonArray.add(new JsonPrimitive(stackTraceElement.toString()));
        }
        return jsonArray;
    }

    private JsonObject extrasToJson() {
        JsonObject jsonObject = new JsonObject();
        if (this.extras != null) {
            for (Entry entry : this.extras.entrySet()) {
                jsonObject.add((String) entry.getKey(), new JsonPrimitive((String) entry.getValue()));
            }
        }
        return jsonObject;
    }
}
