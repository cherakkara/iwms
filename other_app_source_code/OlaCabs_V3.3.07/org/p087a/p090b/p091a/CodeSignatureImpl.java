package org.p087a.p090b.p091a;

import org.p087a.p088a.p089a.C0951a;

/* renamed from: org.a.b.a.a */
abstract class CodeSignatureImpl extends MemberSignatureImpl implements C0951a {
    Class[] f11663a;
    String[] f11664b;
    Class[] f11665c;

    CodeSignatureImpl(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2) {
        super(i, str, cls);
        this.f11663a = clsArr;
        this.f11664b = strArr;
        this.f11665c = clsArr2;
    }

    public Class[] m15063c() {
        if (this.f11663a == null) {
            this.f11663a = m15060d(3);
        }
        return this.f11663a;
    }

    public Class[] m15064d() {
        if (this.f11665c == null) {
            this.f11665c = m15060d(5);
        }
        return this.f11665c;
    }
}
