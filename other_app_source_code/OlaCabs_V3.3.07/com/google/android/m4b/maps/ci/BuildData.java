package com.google.android.m4b.maps.ci;

import com.newrelic.agent.android.instrumentation.Trace;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/* renamed from: com.google.android.m4b.maps.ci.a */
public final class BuildData {
    private static Properties f7275a;
    private static volatile boolean f7276b;
    private static Map<String, String> f7277c;

    private BuildData() {
    }

    static {
        f7277c = null;
    }

    private static synchronized Properties m10146a(String str) {
        InputStream resourceAsStream;
        Properties properties;
        synchronized (BuildData.class) {
            if (str == null) {
                throw new NullPointerException();
            }
            if (f7275a == null) {
                f7275a = new Properties();
                try {
                    resourceAsStream = BuildData.class.getResourceAsStream(str);
                    if (resourceAsStream != null) {
                        f7275a.load(resourceAsStream);
                        resourceAsStream.close();
                    }
                } catch (IOException e) {
                } catch (Throwable th) {
                    resourceAsStream.close();
                }
            }
            properties = f7275a;
        }
        return properties;
    }

    public static int m10145a() {
        try {
            Map map;
            Properties a = BuildData.m10146a("/build-data.properties");
            if (a.isEmpty()) {
                map = null;
            } else {
                map = new LinkedHashMap();
                map.put("Built on", a.getProperty("build.time", Trace.NULL));
                map.put("Build timestamp", a.getProperty("build.timestamp", Trace.NULL));
                map.put("Build timestamp as int", a.getProperty("build.timestamp.as.int", Trace.NULL));
                map.put("Built at", a.getProperty("build.location", Trace.NULL));
                map.put("Build target", a.getProperty("build.target", Trace.NULL));
                map.put("Build ID", a.getProperty("build.build_id", "<unknown>"));
                map.put("Build changelist", a.getProperty("build.changelist", Trace.NULL));
                map.put("Build changelist as int", a.getProperty("build.changelist.as.int", Trace.NULL));
                map.put("Build version map", a.getProperty("build.versionmap", Trace.NULL));
                map.put("Build client", a.getProperty("build.client", Trace.NULL));
                map.put("Build client mint status", a.getProperty("build.client_mint_status", Trace.NULL));
                map.put("Build depot path", a.getProperty("build.depot.path", Trace.NULL));
                map.put("Build label", a.getProperty("build.label", Trace.NULL));
                map.put("Build tool", a.getProperty("build.tool", Trace.NULL));
                map.put("Build gplatform", a.getProperty("build.gplatform", Trace.NULL));
                map.put("Mpm version", a.getProperty("build.mpm.version", Trace.NULL));
                map.put("Dependencies", a.getProperty("build.dependencies", Trace.NULL));
                map.put("Citc snapshot", a.getProperty("build.citc.snapshot", Integer.toString(-1)));
                map.put("Verifiable", a.getProperty("build.verifiable", "0"));
            }
            if (map == null) {
                if (!f7276b) {
                    f7276b = true;
                    Logger.getLogger(BuildData.class.getName()).info("No build data available; consider building this application as a deploy jar");
                }
                map = Collections.emptyMap();
            }
            return Integer.parseInt((String) map.get("Build changelist"));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
