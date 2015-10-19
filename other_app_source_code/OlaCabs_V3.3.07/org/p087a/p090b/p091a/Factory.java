package org.p087a.p090b.p091a;

import java.util.Hashtable;
import java.util.StringTokenizer;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p088a.Signature;
import org.p087a.p088a.p089a.MethodSignature;
import org.p087a.p088a.p089a.SourceLocation;
import org.p087a.p090b.p091a.JoinPointImpl.JoinPointImpl;

/* renamed from: org.a.b.a.b */
public final class Factory {
    static Hashtable f11666e;
    static Class f11667f;
    private static Object[] f11668g;
    Class f11669a;
    ClassLoader f11670b;
    String f11671c;
    int f11672d;

    static {
        f11666e = new Hashtable();
        f11666e.put("void", Void.TYPE);
        f11666e.put("boolean", Boolean.TYPE);
        f11666e.put("byte", Byte.TYPE);
        f11666e.put("char", Character.TYPE);
        f11666e.put("short", Short.TYPE);
        f11666e.put("int", Integer.TYPE);
        f11666e.put("long", Long.TYPE);
        f11666e.put("float", Float.TYPE);
        f11666e.put("double", Double.TYPE);
        f11668g = new Object[0];
    }

    static Class m15066a(String str, ClassLoader classLoader) {
        if (str.equals("*")) {
            return null;
        }
        Class cls = (Class) f11666e.get(str);
        if (cls != null) {
            return cls;
        }
        if (classLoader != null) {
            return Class.forName(str, false, classLoader);
        }
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            if (f11667f != null) {
                return f11667f;
            }
            cls = Factory.m15065a("java.lang.ClassNotFoundException");
            f11667f = cls;
            return cls;
        }
    }

    static Class m15065a(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Factory(String str, Class cls) {
        this.f11671c = str;
        this.f11669a = cls;
        this.f11672d = 0;
        this.f11670b = cls.getClassLoader();
    }

    public JoinPoint m15070a(String str, Signature signature, int i) {
        int i2 = this.f11672d;
        this.f11672d = i2 + 1;
        return new JoinPointImpl(i2, str, signature, m15072a(i, -1));
    }

    public static org.p087a.p088a.JoinPoint m15067a(JoinPoint joinPoint, Object obj, Object obj2) {
        return new JoinPointImpl(joinPoint, obj, obj2, f11668g);
    }

    public static org.p087a.p088a.JoinPoint m15068a(JoinPoint joinPoint, Object obj, Object obj2, Object obj3) {
        return new JoinPointImpl(joinPoint, obj, obj2, new Object[]{obj3});
    }

    public static org.p087a.p088a.JoinPoint m15069a(JoinPoint joinPoint, Object obj, Object obj2, Object[] objArr) {
        return new JoinPointImpl(joinPoint, obj, obj2, objArr);
    }

    public MethodSignature m15071a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        int i;
        int parseInt = Integer.parseInt(str, 16);
        Class a = Factory.m15066a(str3, this.f11670b);
        StringTokenizer stringTokenizer = new StringTokenizer(str4, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (i = 0; i < countTokens; i++) {
            clsArr[i] = Factory.m15066a(stringTokenizer.nextToken(), this.f11670b);
        }
        stringTokenizer = new StringTokenizer(str5, ":");
        int countTokens2 = stringTokenizer.countTokens();
        String[] strArr = new String[countTokens2];
        for (i = 0; i < countTokens2; i++) {
            strArr[i] = stringTokenizer.nextToken();
        }
        stringTokenizer = new StringTokenizer(str6, ":");
        int countTokens3 = stringTokenizer.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (i = 0; i < countTokens3; i++) {
            clsArr2[i] = Factory.m15066a(stringTokenizer.nextToken(), this.f11670b);
        }
        return new MethodSignatureImpl(parseInt, str2, a, clsArr, strArr, clsArr2, Factory.m15066a(str7, this.f11670b));
    }

    public SourceLocation m15072a(int i, int i2) {
        return new SourceLocationImpl(this.f11669a, this.f11671c, i);
    }
}
