package com.localytics.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class JsonHelper {
    JsonHelper() {
    }

    static Object toJSON(Object obj) throws JSONException {
        if (obj instanceof Map) {
            JSONObject jSONObject = new JSONObject();
            Map map = (Map) obj;
            for (Object next : map.keySet()) {
                jSONObject.put(next.toString(), toJSON(map.get(next)));
            }
            return jSONObject;
        } else if (!(obj instanceof Iterable)) {
            return obj;
        } else {
            JSONArray jSONArray = new JSONArray();
            for (Object next2 : (Iterable) obj) {
                jSONArray.put(toJSON(next2));
            }
            return jSONArray;
        }
    }

    static boolean isEmptyObject(JSONObject jSONObject) {
        return jSONObject.names() == null;
    }

    static Map<String, Object> getMap(JSONObject jSONObject, String str) throws JSONException {
        return toMap(jSONObject.getJSONObject(str));
    }

    static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        Map<String, Object> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, fromJson(jSONObject.get(str)));
        }
        return hashMap;
    }

    static List toList(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(fromJson(jSONArray.get(i)));
        }
        return arrayList;
    }

    private static Object fromJson(Object obj) throws JSONException {
        if (obj == JSONObject.NULL) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return toMap((JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            return toList((JSONArray) obj);
        }
        return obj;
    }

    static String getSafeStringFromValue(Object obj) {
        if (obj == null) {
            return null;
        }
        String num = obj instanceof Integer ? Integer.toString(((Integer) obj).intValue()) : obj instanceof String ? (String) obj : null;
        return num;
    }

    static int getSafeIntegerFromMap(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj == null) {
            return 0;
        }
        int intValue = obj instanceof Integer ? ((Integer) obj).intValue() : obj instanceof String ? Integer.parseInt((String) obj) : 0;
        return intValue;
    }

    static String getSafeStringFromMap(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        String num = obj instanceof Integer ? Integer.toString(((Integer) obj).intValue()) : obj instanceof String ? (String) obj : null;
        return num;
    }

    static Map<String, Object> getSafeMapFromMap(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        Map<String, Object> map2;
        if (obj instanceof Map) {
            map2 = (Map) obj;
        } else {
            map2 = null;
        }
        return map2;
    }

    static List<Object> getSafeListFromMap(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        List<Object> list;
        if (obj instanceof List) {
            list = (List) obj;
        } else {
            list = null;
        }
        return list;
    }

    static boolean getSafeBooleanFromMap(Map<String, Object> map, String str) {
        Object obj = map.get(str);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return obj instanceof Integer ? ((Integer) obj).intValue() != 0 : obj instanceof String ? ((String) obj).equalsIgnoreCase("true") : false;
    }
}
