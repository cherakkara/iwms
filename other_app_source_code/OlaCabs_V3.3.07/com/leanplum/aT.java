package com.leanplum;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

final class aT {
    static final Map<String, Object> f8686a;
    private static Map<String, Var<?>> f8687b;
    private static Map<String, Object> f8688c;
    private static Map<String, InputStream> f8689d;
    private static Map<String, String> f8690e;
    private static Map<String, Object> f8691f;
    private static Map<String, Object> f8692g;
    private static Map<String, Object> f8693h;
    private static Map<String, Object> f8694i;
    private static Map<String, Object> f8695j;
    private static Map<String, Object> f8696k;
    private static Map<String, Object> f8697l;
    private static List<Map<String, Object>> f8698m;
    private static aU f8699n;
    private static boolean f8700o;
    private static Map<String, Object> f8701p;
    private static Object f8702q;
    private static boolean f8703r;
    private static int f8704s;
    private static Map<String, Object> f8705t;
    private static final Pattern f8706u;

    static {
        f8687b = new ConcurrentHashMap();
        f8688c = new HashMap();
        f8689d = new HashMap();
        f8686a = new HashMap();
        f8690e = new HashMap();
        f8691f = new HashMap();
        f8692g = new HashMap();
        f8693h = new HashMap();
        f8700o = false;
        f8706u = Pattern.compile("(?:[^\\.\\[.(\\\\]+|\\\\.)+");
    }

    public static String[] m12671a(String str) {
        Matcher matcher = f8706u.matcher(str);
        List arrayList = new ArrayList();
        while (matcher.find()) {
            arrayList.add(str.substring(matcher.start(), matcher.end()));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private static Object m12658a(Object obj, Object obj2, boolean z) {
        if (obj == null) {
            return null;
        }
        Object obj3;
        if (obj instanceof Map) {
            obj3 = ((Map) obj).get(obj2);
            if (!z || obj3 != null || !(obj2 instanceof String)) {
                return obj3;
            }
            obj3 = new HashMap();
            ((Map) obj).put((String) obj2, obj3);
            return obj3;
        } else if (!(obj instanceof List)) {
            return null;
        } else {
            obj3 = ((List) obj).get(((Integer) obj2).intValue());
            if (!z || obj3 != null || !(obj2 instanceof String)) {
                return obj3;
            }
            obj3 = new HashMap();
            ((List) obj).set(((Integer) obj2).intValue(), obj3);
            return obj3;
        }
    }

    public static boolean m12670a(String str, String str2, InputStream inputStream, boolean z, String str3, int i) {
        if (!C0633g.f8804k) {
            return false;
        }
        if (!C0633g.m12775a()) {
            if (inputStream == null) {
                return false;
            }
            Map hashMap = new HashMap();
            Map hashMap2 = new HashMap();
            if (z) {
                hashMap2.put("hash", str3);
                hashMap2.put("size", Integer.valueOf(i));
            } else if (C0633g.f8803j && Util.m12574e()) {
                C0637k a = FileManager.m12416a(inputStream);
                hashMap2.put("hash", a.f8815a);
                hashMap2.put("size", Integer.valueOf(a.f8816b));
            } else {
                hashMap2.put("size", Integer.valueOf(FileManager.m12414a(FileManager.m12419a(str, str2, null))));
            }
            hashMap.put(Trace.NULL, hashMap2);
            f8688c.put(str, hashMap);
            f8689d.put(str, inputStream);
            m12680h();
        }
        return true;
    }

    private static void m12666a(String str, String[] strArr, Object obj, String str2, Map<String, Object> map, Map<String, String> map2) {
        int i = 0;
        Object obj2 = map;
        while (i < strArr.length - 1) {
            map = m12658a(obj2, strArr[i], true);
            i++;
            Map<String, Object> map3 = map;
        }
        ((Map) obj2).put(strArr[strArr.length - 1], obj);
        map2.put(str, str2);
    }

    public static void m12663a(Var<?> var) {
        f8687b.put(var.name(), var);
        synchronized (f8686a) {
            m12666a(var.name(), var.nameComponents(), var.defaultValue(), var.kind(), f8686a, f8690e);
        }
        if (!C0633g.m12775a() && Leanplum.hasStartedAndRegisteredAsDeveloper() && !FileManager.m12423a()) {
            m12679g();
        }
    }

    public static <T> Var<T> m12672b(String str) {
        return (Var) f8687b.get(str);
    }

    static Object m12657a(Object obj, Object obj2) {
        if (obj2 == null) {
            return obj;
        }
        if ((obj2 instanceof Number) || (obj2 instanceof Boolean) || (obj2 instanceof String) || (obj2 instanceof Character) || (obj instanceof Number) || (obj instanceof Boolean) || (obj instanceof String) || (obj instanceof Character)) {
            return obj2;
        }
        Map map;
        Map map2;
        Object obj3;
        String str;
        Iterable<String> keySet = obj2 instanceof Map ? ((Map) obj2).keySet() : (Iterable) obj2;
        Iterable<Object> keySet2 = obj instanceof Map ? ((Map) obj).keySet() : (Iterable) obj;
        if (obj2 instanceof Map) {
            map = (Map) obj2;
        } else {
            map = null;
        }
        if (obj instanceof Map) {
            map2 = (Map) obj;
        } else {
            map2 = null;
        }
        int i;
        if (obj == null && (obj2 instanceof Map) && ((Map) obj2).size() > 0) {
            for (Object obj32 : keySet) {
                if (!(obj32 instanceof String)) {
                    i = 0;
                    break;
                }
                str = (String) obj32;
                if (str.length() < 3 || str.charAt(0) != '[' || str.charAt(str.length() - 1) != ']') {
                    i = 0;
                    break;
                }
                str = str.substring(1, str.length() - 1);
                if (!(Integer.getInteger(str)).equals(str)) {
                    i = 0;
                    break;
                }
            }
            i = 1;
        } else {
            i = 0;
        }
        if ((obj instanceof List) || r0 != 0) {
            obj = new ArrayList();
            for (Object add : keySet2) {
                obj.add(add);
            }
            for (String str2 : keySet) {
                int parseInt = Integer.parseInt(str2.substring(1, str2.length() - 1));
                obj32 = map.get(str2);
                while (parseInt >= obj.size()) {
                    obj.add(null);
                }
                obj.set(parseInt, m12657a(obj.get(parseInt), obj32));
            }
            return obj;
        } else if (!(obj instanceof Map) && !(obj2 instanceof Map)) {
            return null;
        } else {
            obj = new HashMap();
            if (keySet2 != null) {
                for (Object add2 : keySet2) {
                    if (map.get(add2) == null) {
                        obj.put(add2, map2.get(add2));
                    }
                }
            }
            for (Object add22 : keySet) {
                if (map2 != null) {
                    obj32 = map2.get(add22);
                } else {
                    obj32 = null;
                }
                obj.put(add22, m12657a(obj32, map.get(add22)));
            }
            return obj;
        }
    }

    public static <T> T m12660a(Object[] objArr, Object obj) {
        for (Object a : objArr) {
            obj = m12658a(obj, a, false);
        }
        return obj;
    }

    public static <T> T m12659a(Object[] objArr) {
        return m12660a(objArr, f8702q != null ? f8702q : f8686a);
    }

    public static Map<String, Object> m12662a() {
        return f8692g;
    }

    public static Map<String, Object> m12673b() {
        return f8694i;
    }

    public static boolean m12675c() {
        return f8700o;
    }

    public static void m12676d() {
        if (!C0633g.m12775a()) {
            SharedPreferences sharedPreferences = Leanplum.m12431a().getSharedPreferences("__leanplum__", 0);
            String string = sharedPreferences.getString("__leanplum_token", null);
            if (string == null) {
                m12668a(new HashMap(), new HashMap(), new HashMap(), new ArrayList());
                return;
            }
            C0618S.m12538c(string);
            try {
                m12668a(C0625a.m12599a(C0625a.m12604b(C0618S.m12519a(), sharedPreferences.getString("__leanplum_variables", "{}"))), C0625a.m12599a(C0625a.m12604b(C0618S.m12519a(), sharedPreferences.getString("__leanplum_messages", "{}"))), C0625a.m12599a(C0625a.m12604b(C0618S.m12519a(), sharedPreferences.getString("regions", "{}"))), C0625a.m12598a(JSONArrayInstrumentation.init(C0625a.m12604b(C0618S.m12519a(), sharedPreferences.getString("variants", "[]")))));
                string = sharedPreferences.getString("deviceId", null);
                if (string != null) {
                    string = C0625a.m12604b(C0618S.m12519a(), string);
                    if (string != null) {
                        C0618S.m12525a(string);
                    }
                }
                String string2 = sharedPreferences.getString(Constants.PREF_USER_ID, null);
                if (string2 != null) {
                    string2 = C0625a.m12604b(C0618S.m12519a(), string2);
                    if (string2 != null) {
                        C0618S.m12534b(string2);
                    }
                }
            } catch (Throwable e) {
                Log.e("Leanplum", "Could not load variable diffs", e);
            }
            m12685m();
        }
    }

    public static void m12677e() {
        if (!C0633g.m12775a() && C0618S.m12519a() != null) {
            Editor edit = Leanplum.m12431a().getSharedPreferences("__leanplum__", 0).edit();
            edit.putString("__leanplum_variables", C0625a.m12596a(C0618S.m12519a(), C0625a.m12597a(f8692g)));
            edit.putString("__leanplum_messages", C0625a.m12596a(C0618S.m12519a(), C0625a.m12597a(f8701p)));
            edit.putString("regions", C0625a.m12596a(C0618S.m12519a(), C0625a.m12597a(f8693h)));
            try {
                JSONArray a = C0625a.m12601a(f8698m);
                edit.putString("variants", C0625a.m12596a(C0618S.m12519a(), !(a instanceof JSONArray) ? a.toString() : JSONArrayInstrumentation.toString(a)));
            } catch (Throwable e) {
                Log.e("Leanplum", "Error converting " + f8698m + " to JSON", e);
            }
            edit.putString("deviceId", C0625a.m12596a(C0618S.m12519a(), C0618S.m12537c()));
            edit.putString(Constants.PREF_USER_ID, C0625a.m12596a(C0618S.m12519a(), C0618S.m12540d()));
            edit.putString("__leanplum_token", C0618S.m12519a());
            try {
                edit.apply();
            } catch (NoSuchMethodError e2) {
                edit.commit();
            }
        }
    }

    public static int m12674c(String str) {
        int i = 0;
        try {
            String replace = str.replace("res/", Trace.NULL);
            replace = replace.substring(0, replace.lastIndexOf(46));
            String substring = replace.substring(replace.lastIndexOf(47) + 1);
            replace = replace.substring(0, replace.lastIndexOf(47)).replace('/', '.');
            Context a = Leanplum.m12431a();
            i = a.getResources().getIdentifier(substring, replace, a.getPackageName());
        } catch (Exception e) {
        }
        return i;
    }

    private static void m12687o() {
        for (String str : new HashMap(f8687b).keySet()) {
            Var var = (Var) f8687b.get(str);
            String str2 = var.f8637a;
            if (var.f8638b && var.kind().equals("file") && str2 != null && !var.defaultValue().equals(str2)) {
                Map map = (Map) f8688c.get(str2);
                InputStream inputStream = (InputStream) f8689d.get(str2);
                if (!(map == null || inputStream == null)) {
                    var.setOverrideResId(m12674c(var.stringValue()));
                }
            }
        }
    }

    public static void m12668a(Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3, List<Map<String, Object>> list) {
        if (map != null) {
            f8692g = map;
            synchronized (f8686a) {
                f8702q = m12657a(f8686a, f8692g);
            }
            for (String str : new HashMap(f8687b).keySet()) {
                ((Var) f8687b.get(str)).m12590a();
            }
            m12687o();
        }
        if (map2 != null) {
            f8694i = map2;
            Map hashMap = new HashMap();
            for (String str2 : map2.keySet()) {
                Map map4 = (Map) map2.get(str2);
                Map hashMap2 = new HashMap(map4);
                Object obj = (Map) map4.get("vars");
                Map map5 = (Map) m12657a((Map) Util.m12560a(f8691f, hashMap2.get("action"), "values"), obj);
                hashMap.put(str2, hashMap2);
                hashMap2.put("vars", map5);
                if (map4.get("action") != null) {
                    new ActionContext(map4.get("action").toString(), obj, str2).m12405b();
                }
            }
            f8701p = hashMap;
        }
        if (map3 != null) {
            f8693h = map3;
        }
        if (!(map2 == null && map3 == null)) {
            Set hashSet = new HashSet();
            Set hashSet2 = new HashSet();
            C0629c.m12766a(hashSet, hashSet2);
            af b = C0629c.m12768b();
            if (b != null) {
                b.m12503a(map3, hashSet, hashSet2);
            }
        }
        f8698m = list;
        f8704s++;
        if (!f8703r) {
            m12677e();
            f8700o = true;
            if (f8699n != null) {
                f8699n.m12688a();
            }
        }
    }

    public static int m12678f() {
        return f8704s;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void m12679g() {
        /*
        r3 = 1;
        r4 = 0;
        r0 = com.leanplum.C0633g.m12775a();
        if (r0 == 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r0 = f8695j;
        if (r0 == 0) goto L_0x0008;
    L_0x000d:
        r0 = f8686a;
        r1 = f8695j;
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0027;
    L_0x0017:
        r6 = f8691f;
        r7 = f8697l;
        r0 = r6.size();
        r1 = r7.size();
        if (r0 == r1) goto L_0x0062;
    L_0x0025:
        if (r4 != 0) goto L_0x0008;
    L_0x0027:
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = "variables";
        r2 = f8686a;
        r2 = com.leanplum.C0625a.m12597a(r2);
        r0.put(r1, r2);
        r1 = "kinds";
        r2 = f8690e;
        r2 = com.leanplum.C0625a.m12597a(r2);
        r0.put(r1, r2);
        r1 = "fileAttributes";
        r2 = f8688c;
        r2 = com.leanplum.C0625a.m12597a(r2);
        r0.put(r1, r2);
        r1 = "actionDefinitions";
        r2 = f8691f;
        r2 = com.leanplum.C0625a.m12597a(r2);
        r0.put(r1, r2);
        r1 = "setVars";
        r0 = com.leanplum.C0618S.m12530b(r1, r0);
        r0.m12557h();
        goto L_0x0008;
    L_0x0062:
        r0 = r6.keySet();
        r8 = r0.iterator();
    L_0x006a:
        r0 = r8.hasNext();
        if (r0 != 0) goto L_0x0072;
    L_0x0070:
        r4 = r3;
        goto L_0x0025;
    L_0x0072:
        r0 = r8.next();
        r0 = (java.lang.String) r0;
        r1 = r6.get(r0);
        r1 = (java.util.Map) r1;
        r0 = r7.get(r0);
        r0 = (java.util.Map) r0;
        if (r0 == 0) goto L_0x0025;
    L_0x0086:
        r2 = "kind";
        r2 = r1.get(r2);
        r5 = "kind";
        r5 = r0.get(r5);
        r2 = r2.equals(r5);
        if (r2 == 0) goto L_0x0025;
    L_0x0098:
        r2 = "values";
        r2 = r1.get(r2);
        r5 = "values";
        r5 = r0.get(r5);
        r2 = r2.equals(r5);
        if (r2 == 0) goto L_0x0025;
    L_0x00aa:
        r2 = "kinds";
        r2 = r1.get(r2);
        r5 = "kinds";
        r5 = r0.get(r5);
        r2 = r2.equals(r5);
        if (r2 == 0) goto L_0x0025;
    L_0x00bc:
        r2 = "options";
        r2 = r1.get(r2);
        if (r2 != 0) goto L_0x00e4;
    L_0x00c4:
        r2 = r3;
    L_0x00c5:
        r5 = "options";
        r5 = r0.get(r5);
        if (r5 != 0) goto L_0x00e6;
    L_0x00cd:
        r5 = r3;
    L_0x00ce:
        if (r2 != r5) goto L_0x0025;
    L_0x00d0:
        r2 = "options";
        r1 = r1.get(r2);
        r2 = "options";
        r0 = r0.get(r2);
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x006a;
    L_0x00e2:
        goto L_0x0025;
    L_0x00e4:
        r2 = r4;
        goto L_0x00c5;
    L_0x00e6:
        r5 = r4;
        goto L_0x00ce;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.leanplum.aT.g():void");
    }

    static void m12680h() {
        if (!C0633g.m12775a() && f8696k != null && Leanplum.hasStartedAndRegisteredAsDeveloper() && C0633g.f8807n) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            int i = 0;
            List list = arrayList3;
            Object obj = arrayList2;
            List list2 = arrayList;
            for (String str : f8688c.keySet()) {
                Map map = (Map) f8696k.get(str);
                Map map2 = (Map) ((Map) f8688c.get(str)).get(Trace.NULL);
                map = (Map) (map != null ? map.get(Trace.NULL) : null);
                if (FileManager.m12424a(map2, map)) {
                    Object obj2;
                    List list3;
                    List list4;
                    int i2;
                    if (C0633g.f8806m) {
                        Log.d("Leanplum", "Will upload file " + str + ". Local attributes: " + map2 + "; server attributes: " + map);
                    }
                    String str2 = (String) map2.get("hash");
                    if (str2 == null) {
                        obj2 = Trace.NULL;
                    } else {
                        String str3 = str2;
                    }
                    String b = FileManager.m12425b(str);
                    if ((i <= 26214400 || list2.size() <= 0) && list2.size() < 16) {
                        int i3 = i;
                        list3 = list;
                        list = obj;
                        list4 = list2;
                        i2 = i3;
                    } else {
                        map = new HashMap();
                        map.put("data", obj.toString());
                        C0618S.m12530b("uploadFile", map).m12552a(list2, list);
                        list4 = new ArrayList();
                        list = new ArrayList();
                        list3 = new ArrayList();
                        i2 = 0;
                    }
                    int intValue = ((Integer) map2.get("size")).intValue() + i2;
                    list4.add(b);
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("hash", obj2);
                        jSONObject.put("size", map2.get("size"));
                        jSONObject.put("filename", str);
                        list.add(jSONObject);
                    } catch (Throwable e) {
                        Log.e("Leanplum", "Unable to upload files", e);
                        list.add(new JSONObject());
                    }
                    list3.add((InputStream) f8689d.get(str));
                    list2 = list4;
                    obj = list;
                    list = list3;
                    i = intValue;
                }
            }
            if (list2.size() > 0) {
                Map hashMap = new HashMap();
                hashMap.put("data", obj.toString());
                C0618S.m12530b("uploadFile", hashMap).m12552a(list2, list);
            }
        }
    }

    public static void m12669a(boolean z) {
        f8703r = z;
    }

    public static boolean m12681i() {
        return f8703r;
    }

    public static void m12667a(Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        f8695j = map;
        f8697l = map3;
        f8696k = map2;
    }

    public static void m12664a(aU aUVar) {
        f8699n = aUVar;
    }

    public static List<Map<String, Object>> m12682j() {
        return f8698m;
    }

    public static Map<String, Object> m12683k() {
        return f8691f;
    }

    public static Map<String, Object> m12684l() {
        return f8701p;
    }

    public static void m12665a(String str, int i, List<C0625a<?>> list, Map<String, Object> map) {
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        List arrayList = new ArrayList();
        for (C0625a c0625a : list) {
            m12666a(c0625a.m12610a(), m12671a(c0625a.m12610a()), c0625a.m12612c(), c0625a.m12611b(), hashMap, hashMap2);
            arrayList.add(c0625a.m12610a());
        }
        Map hashMap3 = new HashMap();
        hashMap3.put("kind", Integer.valueOf(i));
        hashMap3.put("values", hashMap);
        hashMap3.put("kinds", hashMap2);
        hashMap3.put("order", arrayList);
        hashMap3.put("options", map);
        f8691f.put(str, hashMap3);
    }

    public static <T> String m12661a(T t) {
        if ((t instanceof Integer) || (t instanceof Long) || (t instanceof Short) || (t instanceof Character) || (t instanceof Byte) || (t instanceof BigInteger)) {
            return "integer";
        }
        if ((t instanceof Float) || (t instanceof Double) || (t instanceof BigDecimal)) {
            return "float";
        }
        if (t instanceof String) {
            return "string";
        }
        if ((t instanceof List) || (t instanceof Array)) {
            return "list";
        }
        if (t instanceof Map) {
            return "group";
        }
        if (t instanceof Boolean) {
            return "bool";
        }
        return null;
    }

    public static Map<String, Object> m12685m() {
        if (f8705t == null) {
            SharedPreferences sharedPreferences = Leanplum.m12431a().getSharedPreferences("__leanplum__", 0);
            if (C0618S.m12519a() != null) {
                try {
                    String string = sharedPreferences.getString("__leanplum_attributes", null);
                    if (string == null) {
                        string = "{}";
                    } else {
                        string = C0625a.m12604b(C0618S.m12519a(), string);
                    }
                    f8705t = C0625a.m12599a(string);
                } catch (Throwable e) {
                    Log.e("Leanplum", "Could not load user attributes", e);
                }
            }
        }
        if (f8705t == null) {
            f8705t = new HashMap();
        }
        return f8705t;
    }

    public static void m12686n() {
        if (!C0633g.m12775a() && C0618S.m12519a() != null) {
            Editor edit = Leanplum.m12431a().getSharedPreferences("__leanplum__", 0).edit();
            edit.putString("__leanplum_attributes", C0625a.m12596a(C0618S.m12519a(), C0625a.m12597a(f8705t)));
            try {
                edit.apply();
            } catch (NoSuchMethodError e) {
                edit.commit();
            }
        }
    }
}
