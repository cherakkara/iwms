package com.newrelic.agent.android.harvest.crash;

import com.newrelic.agent.android.api.common.WanType;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class ThreadInfo extends HarvestableObject {
    private boolean crashed;
    private StackTraceElement[] stackTrace;
    private String state;
    private long threadId;
    private String threadName;
    private int threadPriority;

    private ThreadInfo() {
    }

    public ThreadInfo(Throwable th) {
        this.crashed = true;
        this.threadId = Thread.currentThread().getId();
        this.threadName = Thread.currentThread().getName();
        this.threadPriority = Thread.currentThread().getPriority();
        this.stackTrace = th.getStackTrace();
        this.state = Thread.currentThread().getState().toString();
    }

    public ThreadInfo(Thread thread, StackTraceElement[] stackTraceElementArr) {
        this.crashed = false;
        this.threadId = thread.getId();
        this.threadName = thread.getName();
        this.threadPriority = thread.getPriority();
        this.stackTrace = stackTraceElementArr;
        this.state = thread.getState().toString();
    }

    public long getThreadId() {
        return this.threadId;
    }

    public static List<ThreadInfo> extractThreads(Throwable th) {
        List<ThreadInfo> arrayList = new ArrayList();
        ThreadInfo threadInfo = new ThreadInfo(th);
        long threadId = threadInfo.getThreadId();
        arrayList.add(threadInfo);
        for (Entry entry : Thread.getAllStackTraces().entrySet()) {
            Thread thread = (Thread) entry.getKey();
            StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) entry.getValue();
            if (thread.getId() != threadId) {
                arrayList.add(new ThreadInfo(thread, stackTraceElementArr));
            }
        }
        return arrayList;
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("crashed", new JsonPrimitive(Boolean.valueOf(this.crashed)));
        jsonObject.add("state", new JsonPrimitive(this.state));
        jsonObject.add("threadNumber", new JsonPrimitive(Long.valueOf(this.threadId)));
        jsonObject.add("threadId", new JsonPrimitive(this.threadName));
        jsonObject.add("priority", new JsonPrimitive(Integer.valueOf(this.threadPriority)));
        jsonObject.add("stack", getStackAsJson());
        return jsonObject;
    }

    public static ThreadInfo newFromJson(JsonObject jsonObject) {
        ThreadInfo threadInfo = new ThreadInfo();
        threadInfo.crashed = jsonObject.get("crashed").getAsBoolean();
        threadInfo.state = jsonObject.get("state").getAsString();
        threadInfo.threadId = jsonObject.get("threadNumber").getAsLong();
        threadInfo.threadName = jsonObject.get("threadId").getAsString();
        threadInfo.threadPriority = jsonObject.get("priority").getAsInt();
        threadInfo.stackTrace = stackTraceFromJson(jsonObject.get("stack").getAsJsonArray());
        return threadInfo;
    }

    public static StackTraceElement[] stackTraceFromJson(JsonArray jsonArray) {
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[jsonArray.size()];
        Iterator it = jsonArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            JsonElement jsonElement = (JsonElement) it.next();
            String str = WanType.UNKNOWN;
            if (jsonElement.getAsJsonObject().get("fileName") != null) {
                str = jsonElement.getAsJsonObject().get("fileName").getAsString();
            }
            StackTraceElement stackTraceElement = new StackTraceElement(jsonElement.getAsJsonObject().get("className").getAsString(), jsonElement.getAsJsonObject().get("methodName").getAsString(), str, jsonElement.getAsJsonObject().get("lineNumber").getAsInt());
            int i2 = i + 1;
            stackTraceElementArr[i] = stackTraceElement;
            i = i2;
        }
        return stackTraceElementArr;
    }

    public static List<ThreadInfo> newListFromJson(JsonArray jsonArray) {
        List<ThreadInfo> arrayList = new ArrayList();
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            arrayList.add(newFromJson(((JsonElement) it.next()).getAsJsonObject()));
        }
        return arrayList;
    }

    private JsonArray getStackAsJson() {
        JsonArray jsonArray = new JsonArray();
        for (StackTraceElement stackTraceElement : this.stackTrace) {
            JsonElement jsonObject = new JsonObject();
            if (stackTraceElement.getFileName() != null) {
                jsonObject.add("fileName", new JsonPrimitive(stackTraceElement.getFileName()));
            }
            jsonObject.add("className", new JsonPrimitive(stackTraceElement.getClassName()));
            jsonObject.add("methodName", new JsonPrimitive(stackTraceElement.getMethodName()));
            jsonObject.add("lineNumber", new JsonPrimitive(Integer.valueOf(stackTraceElement.getLineNumber())));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
