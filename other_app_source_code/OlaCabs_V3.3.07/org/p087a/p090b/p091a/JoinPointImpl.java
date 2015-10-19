package org.p087a.p090b.p091a;

import org.p087a.p088a.C0952c;
import org.p087a.p088a.JoinPoint.JoinPoint;
import org.p087a.p088a.Signature;
import org.p087a.p088a.p089a.SourceLocation;

/* renamed from: org.a.b.a.c */
class JoinPointImpl implements C0952c {
    Object f11677a;
    Object f11678b;
    Object[] f11679c;
    JoinPoint f11680d;

    /* renamed from: org.a.b.a.c.a */
    static class JoinPointImpl implements JoinPoint {
        String f11673a;
        Signature f11674b;
        SourceLocation f11675c;
        private int f11676d;

        public JoinPointImpl(int i, String str, Signature signature, SourceLocation sourceLocation) {
            this.f11673a = str;
            this.f11674b = signature;
            this.f11675c = sourceLocation;
            this.f11676d = i;
        }

        public String m15075b() {
            return this.f11673a;
        }

        public Signature m15074a() {
            return this.f11674b;
        }

        String m15073a(StringMaker stringMaker) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(stringMaker.m15092a(m15075b()));
            stringBuffer.append("(");
            stringBuffer.append(((SignatureImpl) m15074a()).m15058b(stringMaker));
            stringBuffer.append(")");
            return stringBuffer.toString();
        }

        public final String toString() {
            return m15073a(StringMaker.f11687k);
        }
    }

    public JoinPointImpl(JoinPoint joinPoint, Object obj, Object obj2, Object[] objArr) {
        this.f11680d = joinPoint;
        this.f11677a = obj;
        this.f11678b = obj2;
        this.f11679c = objArr;
    }

    public Object m15076a() {
        return this.f11677a;
    }

    public Signature m15077b() {
        return this.f11680d.m15047a();
    }

    public final String toString() {
        return this.f11680d.toString();
    }
}
