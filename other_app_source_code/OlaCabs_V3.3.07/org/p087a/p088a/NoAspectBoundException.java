package org.p087a.p088a;

/* renamed from: org.a.a.b */
public class NoAspectBoundException extends RuntimeException {
    Throwable f11652a;

    public NoAspectBoundException(String str, Throwable th) {
        if (th != null) {
            str = new StringBuffer().append("Exception while initializing ").append(str).append(": ").append(th).toString();
        }
        super(str);
        this.f11652a = th;
    }

    public Throwable getCause() {
        return this.f11652a;
    }
}
