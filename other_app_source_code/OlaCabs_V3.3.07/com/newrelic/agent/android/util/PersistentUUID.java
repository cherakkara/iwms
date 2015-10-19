package com.newrelic.agent.android.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.newrelic.agent.android.instrumentation.Trace;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.olacabs.customer.utils.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class PersistentUUID {
    private static File UUID_FILE = null;
    private static final String UUID_FILENAME = "nr_installation";
    private static final String UUID_KEY = "nr_uuid";
    private static AgentLog log;

    static {
        UUID_FILE = new File(Environment.getDataDirectory(), UUID_FILENAME);
        log = AgentLogManager.getAgentLog();
    }

    public PersistentUUID(Context context) {
        UUID_FILE = new File(context.getFilesDir(), UUID_FILENAME);
    }

    public String getDeviceId(Context context) {
        String generateUniqueID = generateUniqueID(context);
        if (TextUtils.isEmpty(generateUniqueID)) {
            return UUID.randomUUID().toString();
        }
        return generateUniqueID;
    }

    private String generateUniqueID(Context context) {
        String str = Build.SERIAL;
        String str2 = Build.ID;
        try {
            Object string = Secure.getString(context.getContentResolver(), "android_id");
            if (TextUtils.isEmpty(string)) {
                return UUID.randomUUID().toString();
            }
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Constants.PHONE);
                str2 = telephonyManager != null ? telephonyManager.getDeviceId() : str;
            } catch (Exception e) {
                str2 = "badf00d";
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = Build.HARDWARE + Build.DEVICE + Build.BOARD + Build.BRAND;
            }
            intToHexString(string.hashCode(), 8) + "-" + intToHexString(str2.hashCode(), 4) + "-" + intToHexString(VERSION.SDK_INT, 4) + "-" + intToHexString(VERSION.RELEASE.hashCode(), 12);
            throw new RuntimeException("Not supported (TODO)");
        } catch (Exception e2) {
            return UUID.randomUUID().toString();
        }
    }

    private String intToHexString(int i, int i2) {
        String str = Trace.NULL;
        String toHexString = Integer.toHexString(i);
        char[] cArr = new char[(i2 - toHexString.length())];
        Arrays.fill(cArr, '0');
        String str2 = new String(cArr) + toHexString;
        toHexString = str;
        int i3 = 0;
        for (int length = str2.length() - 1; length >= 0; length--) {
            i3++;
            toHexString = str2.substring(length, length + 1) + toHexString;
            if (i3 % i2 == 0) {
                toHexString = "-" + toHexString;
            }
        }
        if (toHexString.startsWith("-")) {
            return toHexString.substring(1, toHexString.length());
        }
        return toHexString;
    }

    private void noticeUUIDMetric(String str) {
        StatsEngine statsEngine = StatsEngine.get();
        if (statsEngine != null) {
            statsEngine.inc("Supportability/AgentHealth/" + str);
        } else {
            log.error("StatsEngine is null. " + str + "  not recorded.");
        }
    }

    public String getPersistentUUID() {
        Object uUIDFromFileStore = getUUIDFromFileStore();
        if (TextUtils.isEmpty(uUIDFromFileStore)) {
            String uuid = UUID.randomUUID().toString();
            log.info("Created random UUID: " + uuid);
            setPersistedUUID(uuid);
            return uuid;
        }
        noticeUUIDMetric("UUIDRecovered");
        return uUIDFromFileStore;
    }

    protected void setPersistedUUID(String str) {
        putUUIDToFileStore(str);
    }

    protected String getUUIDFromFileStore() {
        String str = Trace.NULL;
        if (UUID_FILE.exists()) {
            try {
                str = new JSONObject(new BufferedReader(new FileReader(UUID_FILE)).readLine()).getString(UUID_KEY);
            } catch (FileNotFoundException e) {
                log.error(e.getMessage());
            } catch (IOException e2) {
                log.error(e2.getMessage());
            } catch (JSONException e3) {
                log.error(e3.getMessage());
            }
        }
        return str;
    }

    protected void putUUIDToFileStore(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(UUID_KEY, str);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(UUID_FILE));
            bufferedWriter.write(jSONObject.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (JSONException e2) {
            log.error(e2.getMessage());
        }
    }
}
