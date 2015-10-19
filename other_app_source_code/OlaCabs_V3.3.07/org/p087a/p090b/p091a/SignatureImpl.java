package org.p087a.p090b.p091a;

import java.lang.ref.SoftReference;
import java.util.StringTokenizer;
import org.p087a.p088a.Signature;

/* renamed from: org.a.b.a.f */
abstract class SignatureImpl implements Signature {
    private static boolean f11653a;
    static String[] f11654k;
    static Class[] f11655l;
    private String f11656b;
    int f11657e;
    String f11658f;
    String f11659g;
    Class f11660h;
    SignatureImpl f11661i;
    ClassLoader f11662j;

    /* renamed from: org.a.b.a.f.a */
    private interface SignatureImpl {
        String m15080a(int i);

        void m15081a(int i, String str);
    }

    /* renamed from: org.a.b.a.f.b */
    private static final class SignatureImpl implements SignatureImpl {
        private SoftReference f11682a;

        public SignatureImpl() {
            m15083b();
        }

        public String m15084a(int i) {
            String[] a = m15082a();
            if (a == null) {
                return null;
            }
            return a[i];
        }

        public void m15085a(int i, String str) {
            String[] a = m15082a();
            if (a == null) {
                a = m15083b();
            }
            a[i] = str;
        }

        private String[] m15082a() {
            return (String[]) this.f11682a.get();
        }

        private String[] m15083b() {
            Object obj = new String[3];
            this.f11682a = new SoftReference(obj);
            return obj;
        }
    }

    protected abstract String m15055a(StringMaker stringMaker);

    static {
        f11653a = true;
        f11654k = new String[0];
        f11655l = new Class[0];
    }

    SignatureImpl(int i, String str, Class cls) {
        this.f11657e = -1;
        this.f11662j = null;
        this.f11657e = i;
        this.f11658f = str;
        this.f11660h = cls;
    }

    String m15058b(StringMaker stringMaker) {
        String str = null;
        if (f11653a) {
            if (this.f11661i == null) {
                try {
                    this.f11661i = new SignatureImpl();
                } catch (Throwable th) {
                    f11653a = false;
                }
            } else {
                str = this.f11661i.m15080a(stringMaker.f11697i);
            }
        }
        if (str == null) {
            str = m15055a(stringMaker);
        }
        if (f11653a) {
            this.f11661i.m15081a(stringMaker.f11697i, str);
        }
        return str;
    }

    public final String toString() {
        return m15058b(StringMaker.f11687k);
    }

    public int m15061f() {
        if (this.f11657e == -1) {
            this.f11657e = m15056b(0);
        }
        return this.f11657e;
    }

    public String m15053a() {
        if (this.f11658f == null) {
            this.f11658f = m15054a(1);
        }
        return this.f11658f;
    }

    public Class m15057b() {
        if (this.f11660h == null) {
            this.f11660h = m15059c(2);
        }
        return this.f11660h;
    }

    public String m15062g() {
        if (this.f11659g == null) {
            this.f11659g = m15057b().getName();
        }
        return this.f11659g;
    }

    private ClassLoader m15052c() {
        if (this.f11662j == null) {
            this.f11662j = getClass().getClassLoader();
        }
        return this.f11662j;
    }

    String m15054a(int i) {
        int i2 = 0;
        int indexOf = this.f11656b.indexOf(45);
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                break;
            }
            i2 = indexOf + 1;
            indexOf = this.f11656b.indexOf(45, i2);
            i = i3;
        }
        if (indexOf == -1) {
            indexOf = this.f11656b.length();
        }
        return this.f11656b.substring(i2, indexOf);
    }

    int m15056b(int i) {
        return Integer.parseInt(m15054a(i), 16);
    }

    Class m15059c(int i) {
        return Factory.m15066a(m15054a(i), m15052c());
    }

    Class[] m15060d(int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(m15054a(i), ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            clsArr[i2] = Factory.m15066a(stringTokenizer.nextToken(), m15052c());
        }
        return clsArr;
    }
}
