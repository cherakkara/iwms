package com.newrelic.agent.android.instrumentation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class GsonInstrumentation {
    private static final ArrayList<String> categoryParams;

    static {
        categoryParams = new ArrayList(Arrays.asList(new String[]{AnalyticAttribute.EVENT_CATEGORY_ATTRIBUTE, MetricCategory.class.getName(), "JSON"}));
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static String toJson(Gson gson, Object obj) {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        String a = gson.m12346a(obj);
        TraceMachine.exitMethod();
        return a;
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static String toJson(Gson gson, Object obj, Type type) {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        String a = gson.m12347a(obj, type);
        TraceMachine.exitMethod();
        return a;
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static void toJson(Gson gson, Object obj, Appendable appendable) throws JsonIOException {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        gson.m12350a(obj, appendable);
        TraceMachine.exitMethod();
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static void toJson(Gson gson, Object obj, Type type, Appendable appendable) throws JsonIOException {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        gson.m12352a(obj, type, appendable);
        TraceMachine.exitMethod();
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static void toJson(Gson gson, Object obj, Type type, JsonWriter jsonWriter) throws JsonIOException {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        gson.m12351a(obj, type, jsonWriter);
        TraceMachine.exitMethod();
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static String toJson(Gson gson, JsonElement jsonElement) {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        String a = gson.m12345a(jsonElement);
        TraceMachine.exitMethod();
        return a;
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static void toJson(Gson gson, JsonElement jsonElement, Appendable appendable) throws JsonIOException {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        gson.m12349a(jsonElement, appendable);
        TraceMachine.exitMethod();
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static void toJson(Gson gson, JsonElement jsonElement, JsonWriter jsonWriter) throws JsonIOException {
        TraceMachine.enterMethod("Gson#toJson", categoryParams);
        gson.m12348a(jsonElement, jsonWriter);
        TraceMachine.exitMethod();
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, String str, Class<T> cls) throws JsonSyntaxException {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        T a = gson.m12343a(str, (Class) cls);
        TraceMachine.exitMethod();
        return a;
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, String str, Type type) throws JsonSyntaxException {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        T a = gson.m12344a(str, type);
        TraceMachine.exitMethod();
        return a;
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, Reader reader, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        T a = gson.m12341a(reader, (Class) cls);
        TraceMachine.exitMethod();
        return a;
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, Reader reader, Type type) throws JsonIOException, JsonSyntaxException {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        T a = gson.m12342a(reader, type);
        TraceMachine.exitMethod();
        return a;
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, JsonReader jsonReader, Type type) throws JsonIOException, JsonSyntaxException {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        T a = gson.m12340a(jsonReader, type);
        TraceMachine.exitMethod();
        return a;
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, JsonElement jsonElement, Class<T> cls) throws JsonSyntaxException {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        T a = gson.m12338a(jsonElement, (Class) cls);
        TraceMachine.exitMethod();
        return a;
    }

    @ReplaceCallSite(scope = "com.google.gson.Gson")
    public static <T> T fromJson(Gson gson, JsonElement jsonElement, Type type) throws JsonSyntaxException {
        TraceMachine.enterMethod("Gson#fromJson", categoryParams);
        T a = gson.m12339a(jsonElement, type);
        TraceMachine.exitMethod();
        return a;
    }
}
