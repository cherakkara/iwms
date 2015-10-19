package com.leanplum;

import android.util.Log;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionContext {
    private String f8528a;
    private String f8529b;
    private Map<String, Object> f8530c;
    private ActionContext f8531d;
    private int f8532e;
    private String f8533f;
    private boolean f8534g;
    private boolean f8535h;
    private boolean f8536i;
    private C0628b f8537j;

    ActionContext(String str, Map<String, Object> map, String str2) {
        this.f8534g = false;
        this.f8535h = true;
        this.f8536i = false;
        this.f8528a = str;
        this.f8530c = map;
        this.f8529b = str2;
        this.f8532e = aT.m12678f();
    }

    final void m12402a() {
        this.f8534g = true;
    }

    final void m12404a(boolean z) {
        this.f8535h = z;
    }

    final void m12403a(C0628b c0628b) {
        this.f8537j = c0628b;
    }

    private static Map<String, Object> m12399a(String str) {
        Map<String, Object> map = (Map) aT.m12683k().get(str);
        if (map == null) {
            return new HashMap();
        }
        return map;
    }

    private Map<String, Object> m12401e() {
        Map<String, Object> map = (Map) m12399a(this.f8528a).get("values");
        if (map == null) {
            return new HashMap();
        }
        return map;
    }

    final void m12405b() {
        Map map = (Map) m12399a(this.f8528a).get("kinds");
        Map hashMap = map == null ? new HashMap() : map;
        Map e = m12401e();
        for (String str : this.f8530c.keySet()) {
            String str2 = (String) hashMap.get(str);
            if (str2 != null && str2.equals("file")) {
                FileManager.m12415a(false, this.f8530c.get(str).toString(), e.get(str).toString(), null);
            } else if (str2 == null || str2.equals("action")) {
                Object objectNamed = objectNamed(str);
                if (objectNamed instanceof Map) {
                    map = (Map) objectNamed;
                    new ActionContext((String) map.get("__name__"), map, this.f8529b).m12405b();
                }
            }
        }
    }

    public String actionName() {
        return this.f8528a;
    }

    public <T> T objectNamed(String str) {
        if (!this.f8534g && aT.m12678f() > this.f8532e) {
            ActionContext actionContext = this.f8531d;
            if (actionContext != null) {
                this.f8530c = actionContext.m12400b(this.f8533f);
            } else if (this.f8529b != null) {
                this.f8530c = (Map) ((Map) aT.m12684l().get(this.f8529b)).get("vars");
            }
        }
        return aT.m12660a(aT.m12671a(str), this.f8530c);
    }

    public String stringNamed(String str) {
        Object objectNamed = objectNamed(str);
        if (objectNamed == null) {
            return null;
        }
        String obj = objectNamed.toString();
        if (this.f8537j == null || obj == null || !obj.contains("##")) {
            return obj;
        }
        String str2;
        if (this.f8537j.f8781a != null) {
            Map map = this.f8537j.f8781a;
            str2 = obj;
            for (String obj2 : map.keySet()) {
                str2 = str2.replace("##Parameter " + obj2 + "##", map.get(obj2));
            }
        } else {
            str2 = obj2;
        }
        if (this.f8537j.f8782b != null) {
            str2 = str2.replace("##Previous Value##", this.f8537j.f8782b.toString());
        }
        return this.f8537j.f8783c != null ? str2.replace("##Value##", this.f8537j.f8783c.toString()) : str2;
    }

    public InputStream streamNamed(String str) {
        InputStream inputStream = null;
        String stringNamed = stringNamed(str);
        String obj = m12401e().get(str).toString();
        if (!((stringNamed == null || stringNamed.length() == 0) && (obj == null || obj.length() == 0))) {
            inputStream = FileManager.m12417a(false, null, null, FileManager.m12419a(stringNamed, obj, null), obj, null);
            if (inputStream == null) {
                Log.e("Leanplum", "Could not open stream named " + str);
            }
        }
        return inputStream;
    }

    public boolean booleanNamed(String str) {
        Object objectNamed = objectNamed(str);
        if (objectNamed instanceof Boolean) {
            return ((Boolean) objectNamed).booleanValue();
        }
        return Boolean.valueOf(objectNamed.toString()).booleanValue();
    }

    public Number numberNamed(String str) {
        Object objectNamed = objectNamed(str);
        if (objectNamed instanceof Number) {
            return (Number) objectNamed;
        }
        return Double.valueOf(objectNamed.toString());
    }

    private Map<String, Object> m12400b(String str) {
        Object objectNamed = objectNamed(str);
        if (!(objectNamed instanceof Map)) {
            return null;
        }
        objectNamed = (Map) objectNamed;
        return (Map) aT.m12657a((Map) m12399a((String) objectNamed.get("__name__")).get("values"), objectNamed);
    }

    public void runActionNamed(String str) {
        Map b = m12400b(str);
        if (b != null) {
            ActionContext actionContext = new ActionContext(b.get("__name__").toString(), b, this.f8529b);
            actionContext.f8537j = this.f8537j;
            actionContext.f8534g = this.f8534g;
            actionContext.f8535h = this.f8535h;
            actionContext.f8531d = this;
            actionContext.f8533f = str;
            Leanplum.m12435a(actionContext);
        }
    }

    public void runTrackedActionNamed(String str) {
        if (!(C0633g.m12775a() || this.f8529b == null || !this.f8535h)) {
            Object obj;
            List arrayList = new ArrayList();
            for (ActionContext actionContext = this; actionContext.f8531d != null; actionContext = actionContext.f8531d) {
                arrayList.add(actionContext);
            }
            StringBuilder stringBuilder = new StringBuilder();
            int size = arrayList.size() - 1;
            while (size >= -1) {
                String str2;
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(' ');
                }
                if (size >= 0) {
                    str2 = ((ActionContext) arrayList.get(size)).f8533f;
                } else {
                    str2 = str;
                }
                if (str2 == null) {
                    obj = null;
                    break;
                } else {
                    stringBuilder.append(str2.replace(" action", Trace.NULL));
                    size--;
                }
            }
            obj = 1;
            if (obj != null) {
                Map hashMap = new HashMap();
                hashMap.put("messageId", this.f8529b);
                Leanplum.m12439a(stringBuilder.toString(), 0.0d, null, null, hashMap);
            }
        }
        runActionNamed(str);
    }

    public void track(String str, double d, Map<String, Object> map) {
        if (!C0633g.m12775a() && this.f8529b != null) {
            Map hashMap = new HashMap();
            hashMap.put("messageId", this.f8529b);
            Leanplum.m12439a(str, 0.0d, null, (Map) map, hashMap);
        }
    }

    public void muteFutureMessagesOfSameKind() {
        C0629c.m12764a();
        C0629c.m12769c(this.f8529b);
    }

    final String m12407c() {
        return this.f8529b;
    }

    final void m12406b(boolean z) {
        this.f8536i = true;
    }

    final boolean m12408d() {
        return this.f8536i;
    }
}
