package org.p087a.p090b.p091a;

import com.newrelic.agent.android.instrumentation.Trace;
import java.lang.reflect.Modifier;

/* renamed from: org.a.b.a.h */
class StringMaker {
    static StringMaker f11686j;
    static StringMaker f11687k;
    static StringMaker f11688l;
    boolean f11689a;
    boolean f11690b;
    boolean f11691c;
    boolean f11692d;
    boolean f11693e;
    boolean f11694f;
    boolean f11695g;
    boolean f11696h;
    int f11697i;

    StringMaker() {
        this.f11689a = true;
        this.f11690b = true;
        this.f11691c = false;
        this.f11692d = false;
        this.f11693e = false;
        this.f11694f = true;
        this.f11695g = true;
        this.f11696h = true;
    }

    static {
        f11686j = new StringMaker();
        f11686j.f11689a = true;
        f11686j.f11690b = false;
        f11686j.f11691c = false;
        f11686j.f11692d = false;
        f11686j.f11693e = true;
        f11686j.f11694f = false;
        f11686j.f11695g = false;
        f11686j.f11697i = 0;
        f11687k = new StringMaker();
        f11687k.f11689a = true;
        f11687k.f11690b = true;
        f11687k.f11691c = false;
        f11687k.f11692d = false;
        f11687k.f11693e = false;
        f11686j.f11697i = 1;
        f11688l = new StringMaker();
        f11688l.f11689a = false;
        f11688l.f11690b = true;
        f11688l.f11691c = false;
        f11688l.f11692d = true;
        f11688l.f11693e = false;
        f11688l.f11696h = false;
        f11688l.f11697i = 2;
    }

    String m15092a(String str) {
        int lastIndexOf = str.lastIndexOf(45);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    String m15088a(int i) {
        if (!this.f11692d) {
            return Trace.NULL;
        }
        String modifier = Modifier.toString(i);
        if (modifier.length() == 0) {
            return Trace.NULL;
        }
        return new StringBuffer().append(modifier).append(" ").toString();
    }

    String m15094b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    String m15091a(Class cls, String str, boolean z) {
        if (cls == null) {
            return "ANONYMOUS";
        }
        if (cls.isArray()) {
            Class componentType = cls.getComponentType();
            return new StringBuffer().append(m15091a(componentType, componentType.getName(), z)).append("[]").toString();
        } else if (z) {
            return m15094b(str).replace('$', '.');
        } else {
            return str.replace('$', '.');
        }
    }

    public String m15089a(Class cls) {
        return m15091a(cls, cls.getName(), this.f11689a);
    }

    public String m15090a(Class cls, String str) {
        return m15091a(cls, str, this.f11693e);
    }

    public void m15093a(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i = 0; i < clsArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(m15089a(clsArr[i]));
        }
    }

    public void m15095b(StringBuffer stringBuffer, Class[] clsArr) {
        if (clsArr != null) {
            if (this.f11690b) {
                stringBuffer.append("(");
                m15093a(stringBuffer, clsArr);
                stringBuffer.append(")");
            } else if (clsArr.length == 0) {
                stringBuffer.append("()");
            } else {
                stringBuffer.append("(..)");
            }
        }
    }

    public void m15096c(StringBuffer stringBuffer, Class[] clsArr) {
        if (this.f11691c && clsArr != null && clsArr.length != 0) {
            stringBuffer.append(" throws ");
            m15093a(stringBuffer, clsArr);
        }
    }
}
