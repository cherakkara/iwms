package com.facebook;

/* renamed from: com.facebook.g */
public class FacebookException extends RuntimeException {
    public FacebookException(String str) {
        super(str);
    }

    public FacebookException(String str, Object... objArr) {
        this(String.format(str, objArr));
    }

    public FacebookException(String str, Throwable th) {
        super(str, th);
    }

    public FacebookException(Throwable th) {
        super(th);
    }

    public String toString() {
        return getMessage();
    }
}
