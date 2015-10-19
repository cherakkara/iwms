package com.leanplum;

import android.text.TextUtils;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeanplumCompatibility {
    public static final String CAMPAIGN_CONTENT = "&cc";
    public static final String CAMPAIGN_MEDUIM = "&cm";
    public static final String CAMPAIGN_NAME = "&cn";
    public static final String CAMPAIGN_SOURCE = "&cs";
    public static final String EVENT_ACTION = "&ea";
    public static final String EVENT_CATEGORY = "&ec";
    public static final String EVENT_LABEL = "&el";
    public static final String EVENT_VALUE = "&ev";
    public static final String EXCEPTION_DESCRIPTION = "&exd";
    public static final String ITEM_CATEGORY = "&iv";
    public static final String ITEM_NAME = "&in";
    public static final String SOCIAL_ACTION = "&sa";
    public static final String SOCIAL_NETWORK = "&sn";
    public static final String SOCIAL_TARGET = "&st";
    public static final String TIMING_CATEGORY = "&utc";
    public static final String TIMING_LABEL = "&utl";
    public static final String TIMING_NAME = "&utv";
    public static final String TIMING_VALUE = "&utt";
    public static final String TRANSACTION_AFFILIATION = "&ta";
    public static final String TYPE = "&t";

    public static void fTrack(String str) {
        Leanplum.track(str);
    }

    public static void fTrack(String str, boolean z) {
        if (z) {
            Leanplum.track(new StringBuilder(String.valueOf(str)).append(" begin").toString(), 0.0d, Trace.NULL, new HashMap());
        } else {
            Leanplum.track(str, 0.0d, Trace.NULL, new HashMap());
        }
    }

    public static void fTrack(String str, HashMap<String, ?> hashMap) {
        Leanplum.track(str, (Map) hashMap);
    }

    public static void fTrack(String str, HashMap<String, ?> hashMap, boolean z) {
        if (z) {
            Leanplum.track(new StringBuilder(String.valueOf(str)).append(" begin").toString(), 0.0d, Trace.NULL, hashMap);
        } else {
            Leanplum.track(str, 0.0d, Trace.NULL, hashMap);
        }
    }

    public static void fTrack(String str, String str2, String str3) {
        Map hashMap = new HashMap();
        hashMap.put("errorMessage", str2);
        hashMap.put("errorClass", str3);
        Leanplum.track(new StringBuilder(String.valueOf(str)).append(" error").toString(), 0.0d, Trace.NULL, hashMap);
    }

    public static void gaTrack(Map<String, String> map) {
        String a;
        String str;
        String str2;
        Map hashMap = new HashMap(map);
        if (hashMap.get(EVENT_CATEGORY) != null || hashMap.get(EVENT_ACTION) != null || hashMap.get(EVENT_LABEL) != null) {
            a = m12487a(hashMap, Arrays.asList(new String[]{EVENT_CATEGORY, EVENT_ACTION, EVENT_LABEL}));
            str = (String) hashMap.get(EVENT_VALUE);
            if (str != null) {
                hashMap.remove(EVENT_VALUE);
                str2 = a;
                a = str;
            }
            str2 = a;
            a = str;
        } else if (hashMap.get(EXCEPTION_DESCRIPTION) != null) {
            str2 = m12487a(hashMap, Arrays.asList(new String[]{EXCEPTION_DESCRIPTION, TYPE}));
            a = null;
        } else if (hashMap.get(TRANSACTION_AFFILIATION) != null) {
            str2 = m12487a(hashMap, Arrays.asList(new String[]{TRANSACTION_AFFILIATION, TYPE}));
            a = null;
        } else if (hashMap.get(ITEM_CATEGORY) != null || hashMap.get(ITEM_NAME) != null) {
            str2 = m12487a(hashMap, Arrays.asList(new String[]{ITEM_CATEGORY, ITEM_NAME, TYPE}));
            a = null;
        } else if (hashMap.get(SOCIAL_NETWORK) != null || hashMap.get(SOCIAL_ACTION) != null || hashMap.get(SOCIAL_TARGET) != null) {
            str2 = m12487a(hashMap, Arrays.asList(new String[]{SOCIAL_NETWORK, SOCIAL_ACTION, SOCIAL_TARGET}));
            a = null;
        } else if (hashMap.get(TIMING_CATEGORY) != null || hashMap.get(TIMING_NAME) != null || hashMap.get(TIMING_LABEL) != null) {
            a = m12487a(hashMap, Arrays.asList(new String[]{TIMING_CATEGORY, TIMING_NAME, TIMING_LABEL, TYPE}));
            str = (String) hashMap.get(TIMING_VALUE);
            if (str != null) {
                hashMap.remove(TIMING_VALUE);
                str2 = a;
                a = str;
            }
            str2 = a;
            a = str;
        } else if (hashMap.get(CAMPAIGN_MEDUIM) != null || hashMap.get(CAMPAIGN_CONTENT) != null || hashMap.get(CAMPAIGN_NAME) != null || hashMap.get(CAMPAIGN_SOURCE) == null) {
            return;
        } else {
            return;
        }
        for (String str3 : hashMap.keySet()) {
            if (hashMap.get(str3) == null) {
                hashMap.remove(str3);
            }
        }
        if (a != null) {
            Leanplum.track(str2, Double.parseDouble(a), hashMap);
        } else {
            Leanplum.track(str2, hashMap);
        }
    }

    private static String m12487a(HashMap<String, String> hashMap, List<String> list) {
        Iterable linkedList = new LinkedList();
        for (String str : list) {
            if (hashMap.get(str) != null) {
                linkedList.add((String) hashMap.get(str));
                hashMap.remove(str);
            }
        }
        return TextUtils.join(" ", linkedList);
    }
}
