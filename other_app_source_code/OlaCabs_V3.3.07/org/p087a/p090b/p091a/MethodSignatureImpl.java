package org.p087a.p090b.p091a;

import org.p087a.p088a.p089a.MethodSignature;

/* renamed from: org.a.b.a.e */
class MethodSignatureImpl extends CodeSignatureImpl implements MethodSignature {
    Class f11681d;

    MethodSignatureImpl(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        super(i, str, cls, clsArr, strArr, clsArr2);
        this.f11681d = cls2;
    }

    public Class m15079e() {
        if (this.f11681d == null) {
            this.f11681d = m15059c(6);
        }
        return this.f11681d;
    }

    protected String m15078a(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.m15088a(m15061f()));
        if (stringMaker.f11690b) {
            stringBuffer.append(stringMaker.m15089a(m15079e()));
        }
        if (stringMaker.f11690b) {
            stringBuffer.append(" ");
        }
        stringBuffer.append(stringMaker.m15090a(m15057b(), m15062g()));
        stringBuffer.append(".");
        stringBuffer.append(m15053a());
        stringMaker.m15095b(stringBuffer, m15063c());
        stringMaker.m15096c(stringBuffer, m15064d());
        return stringBuffer.toString();
    }
}
