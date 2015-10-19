package com.leanplum;

import android.content.SharedPreferences.Editor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.leanplum.c */
final class C0629c {
    static String f8784a;
    private static C0629c f8785e;
    private Map<String, Map<String, Number>> f8786b;
    private Map<String, Number> f8787c;
    private Map<String, Number> f8788d;

    static {
        f8784a = "__Push Notification";
    }

    public static synchronized C0629c m12764a() {
        C0629c c0629c;
        synchronized (C0629c.class) {
            if (f8785e == null) {
                f8785e = new C0629c();
            }
            c0629c = f8785e;
        }
        return c0629c;
    }

    public static af m12768b() {
        if (Util.m12578i()) {
            try {
                if (Class.forName("com.google.android.gms.location.LocationClient") != null) {
                    return (af) Class.forName("com.leanplum.LocationManagerImpl").getMethod("instance", new Class[0]).invoke(null, new Object[0]);
                }
            } catch (Throwable th) {
            }
        }
        return null;
    }

    private C0629c() {
        Leanplum.onAction(f8784a, new C0630d(this));
        Leanplum.onAction("__Cancel" + f8784a, new C0631e(this));
        this.f8788d = new HashMap();
        this.f8786b = new HashMap();
        this.f8787c = new HashMap();
    }

    private Map<String, Number> m12770d(String str) {
        Map<String, Number> map = (Map) this.f8786b.get(str);
        if (map != null) {
            return map;
        }
        map = C0625a.m12599a(Leanplum.m12431a().getSharedPreferences("__leanplum_messaging__", 0).getString(String.format("__leanplum_message_occurrences_%s", new Object[]{str}), "{}"));
        this.f8786b.put(str, map);
        return map;
    }

    private int m12771e(String str) {
        Number number = (Number) this.f8787c.get(str);
        if (number != null) {
            return number.intValue();
        }
        int i = Leanplum.m12431a().getSharedPreferences("__leanplum_messaging__", 0).getInt(String.format("__leanplum_message_trigger_occurrences_%s", new Object[]{str}), 0);
        this.f8787c.put(str, Integer.valueOf(i));
        return i;
    }

    public final C0632f m12772a(String str, Map<String, Object> map, String str2, String str3, C0628b c0628b) {
        C0632f c0632f = new C0632f();
        if (Leanplum.m12431a().getSharedPreferences("__leanplum_messaging__", 0).getBoolean(String.format("__leanplum_message_muted_%s", new Object[]{str}), false)) {
            return c0632f;
        }
        c0632f.f8791a = C0629c.m12767a(map.get("whenTriggers"), str2, str3, c0628b);
        c0632f.f8792b = C0629c.m12767a(map.get("unlessTriggers"), str2, str3, c0628b);
        if (!c0632f.f8791a && !c0632f.f8792b) {
            return c0632f;
        }
        Map map2;
        boolean z;
        Object obj = map.get("whenLimits");
        if (obj instanceof Map) {
            map2 = (Map) obj;
        } else {
            map2 = null;
        }
        if (map2 == null) {
            z = true;
        } else {
            List<Map> list = (List) map2.get("children");
            if (list.isEmpty()) {
                z = true;
            } else {
                Map d = m12770d(str);
                int e = m12771e(str) + 1;
                for (Map map22 : list) {
                    String obj2 = map22.get("subject").toString();
                    String obj3 = map22.get("noun").toString();
                    String obj4 = map22.get("verb").toString();
                    if (obj2.equals("times")) {
                        Number number;
                        List list2 = (List) map22.get("objects");
                        int parseInt = list2.size() > 0 ? Integer.parseInt(list2.get(0).toString()) : 0;
                        int parseInt2 = Integer.parseInt(obj3);
                        Number valueOf = Long.valueOf(0);
                        if (obj4.equals("limitSession")) {
                            number = (Number) this.f8788d.get(str);
                            if (number == null) {
                                number = Long.valueOf(0);
                            }
                        } else if (d == null || d.isEmpty()) {
                            obj = 1;
                            if (obj != null) {
                                z = false;
                                break;
                            }
                        } else {
                            number = (Number) d.get("min");
                            Number number2 = (Number) d.get("max");
                            Number valueOf2 = number == null ? Long.valueOf(0) : number;
                            if (number2 == null) {
                                number2 = Long.valueOf(0);
                            }
                            if (obj4.equals("limitUser")) {
                                number = Long.valueOf((number2.longValue() - valueOf2.longValue()) + 1);
                            } else {
                                if (obj4.equals("limitMinute")) {
                                    parseInt = 60;
                                } else if (obj4.equals("limitHour")) {
                                    parseInt = 3600;
                                } else if (obj4.equals("limitDay")) {
                                    parseInt = 86400;
                                } else if (obj4.equals("limitWeek")) {
                                    parseInt = 604800;
                                } else if (obj4.equals("limitMonth")) {
                                    parseInt = 2592000;
                                }
                                long currentTimeMillis = System.currentTimeMillis();
                                long longValue = number2.longValue();
                                int i = 0;
                                while (longValue >= valueOf2.longValue()) {
                                    int i2;
                                    if (d.containsKey(longValue)) {
                                        if ((currentTimeMillis - ((Number) d.get(longValue)).longValue()) / 1000 > ((long) parseInt)) {
                                            break;
                                        }
                                        i2 = i + 1;
                                        if (i2 >= parseInt2) {
                                            break;
                                        }
                                    } else {
                                        i2 = i;
                                    }
                                    longValue--;
                                    i = i2;
                                }
                                number = valueOf;
                            }
                        }
                        if (number.longValue() < ((long) parseInt2)) {
                            obj = 1;
                            if (obj != null) {
                                z = false;
                                break;
                            }
                        }
                        obj = null;
                        if (obj != null) {
                            z = false;
                            break;
                        }
                    } else if (obj2.equals("onNthOccurrence")) {
                        if (e != Integer.parseInt(obj3)) {
                            z = false;
                            break;
                        }
                    } else if (obj2.equals("everyNthOccurrence")) {
                        int parseInt3 = Integer.parseInt(obj3);
                        if (parseInt3 == 0 || e % parseInt3 != 0) {
                            z = false;
                            break;
                        }
                    } else {
                        continue;
                    }
                }
                z = true;
            }
        }
        c0632f.f8793c = z;
        return c0632f;
    }

    private static boolean m12767a(Object obj, String str, String str2, C0628b c0628b) {
        if (obj instanceof Map) {
            for (Map map : (List) ((Map) obj).get("children")) {
                if (((String) map.get("subject")).equals(str)) {
                    String str3 = (String) map.get("noun");
                    if ((str3 == null && str2 == null) || str3.equals(str2)) {
                        str3 = (String) map.get("verb");
                        List list = (List) map.get("objects");
                        if ("changesTo".equals(str3)) {
                            if (!(c0628b == null || list == null)) {
                                for (Object next : list) {
                                    if ((next == null && c0628b.f8783c == null) || (next != null && next.toString().equalsIgnoreCase(c0628b.f8783c.toString()))) {
                                        return true;
                                    }
                                }
                            }
                            return false;
                        } else if ("changesFromTo".equals(str3)) {
                            return c0628b != null && list.size() == 2 && list.get(0) != null && list.get(1) != null && list.get(0).toString().equalsIgnoreCase(c0628b.f8782b.toString()) && list.get(1).toString().equalsIgnoreCase(c0628b.f8783c.toString());
                        } else {
                            if (!"triggersWithParameter".equals(str3)) {
                                return true;
                            }
                            if (c0628b == null || list.size() != 2 || list.get(0) == null || list.get(1) == null || c0628b.f8781a == null) {
                                return false;
                            }
                            Object obj2 = c0628b.f8781a.get(list.get(0));
                            if (obj2 == null || !obj2.toString().equalsIgnoreCase(list.get(1).toString())) {
                                return false;
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final void m12773a(String str) {
        int e = m12771e(str) + 1;
        Editor edit = Leanplum.m12431a().getSharedPreferences("__leanplum_messaging__", 0).edit();
        edit.putInt(String.format("__leanplum_message_trigger_occurrences_%s", new Object[]{str}), e);
        this.f8787c.put(str, Integer.valueOf(e));
        try {
            edit.apply();
        } catch (NoSuchMethodError e2) {
            edit.commit();
        }
    }

    public final void m12774b(String str) {
        Map hashMap;
        Map hashMap2 = new HashMap();
        hashMap2.put("messageId", str);
        Leanplum.m12439a(null, 0.0d, null, null, hashMap2);
        Number number = (Number) this.f8788d.get(str);
        if (number == null) {
            number = Long.valueOf(0);
        }
        this.f8788d.put(str, Long.valueOf(number.longValue() + 1));
        Map d = m12770d(str);
        if (d == null || d.isEmpty()) {
            hashMap = new HashMap();
            hashMap.put("min", Long.valueOf(0));
            hashMap.put("max", Long.valueOf(0));
            hashMap.put("0", Long.valueOf(System.currentTimeMillis()));
        } else {
            number = (Number) d.get("min");
            Number number2 = (Number) d.get("max");
            if (number == null) {
                number = Long.valueOf(0);
            }
            if (number2 == null) {
                number2 = Long.valueOf(0);
            }
            number2 = Long.valueOf(number2.longValue() + 1);
            d.put(number2, Long.valueOf(System.currentTimeMillis()));
            if ((number2.longValue() - number.longValue()) + 1 > 100) {
                d.remove(number);
                d.put("min", Long.valueOf(number.longValue() + 1));
            }
            d.put("max", number2);
            hashMap = d;
        }
        Editor edit = Leanplum.m12431a().getSharedPreferences("__leanplum_messaging__", 0).edit();
        edit.putString(String.format("__leanplum_message_occurrences_%s", new Object[]{str}), C0625a.m12597a(hashMap));
        this.f8786b.put(str, hashMap);
        try {
            edit.apply();
        } catch (NoSuchMethodError e) {
            edit.commit();
        }
    }

    public static void m12769c(String str) {
        if (str != null) {
            Editor edit = Leanplum.m12431a().getSharedPreferences("__leanplum_messaging__", 0).edit();
            edit.putBoolean(String.format("__leanplum_message_muted_%s", new Object[]{str}), true);
            try {
                edit.apply();
            } catch (NoSuchMethodError e) {
                edit.commit();
            }
        }
    }

    public static void m12766a(Set<String> set, Set<String> set2) {
        Map l = aT.m12684l();
        for (String str : l.keySet()) {
            Map map = (Map) l.get(str);
            Object obj = map.get("action");
            if (obj instanceof String) {
                Set set3;
                if (obj.equals(f8784a)) {
                    set3 = set2;
                } else {
                    Set<String> set4 = set;
                }
                C0629c.m12765a((Map) map.get("whenTriggers"), set3);
                C0629c.m12765a((Map) map.get("unlessTriggers"), set3);
            }
        }
    }

    private static void m12765a(Map<String, Object> map, Set<String> set) {
        if (map != null) {
            for (Map map2 : (List) map.get("children")) {
                String str = (String) map2.get("subject");
                if (str.equals("enterRegion") || str.equals("exitRegion")) {
                    set.add((String) map2.get("noun"));
                }
            }
        }
    }
}
