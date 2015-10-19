package com.facebook;

/* renamed from: com.facebook.f */
public class FacebookDialogException extends FacebookException {
    private int f907a;
    private String f908b;

    public FacebookDialogException(String str, int i, String str2) {
        super(str);
        this.f907a = i;
        this.f908b = str2;
    }

    public int m1185a() {
        return this.f907a;
    }

    public String m1186b() {
        return this.f908b;
    }

    public final String toString() {
        return "{FacebookDialogException: " + "errorCode: " + m1185a() + ", message: " + getMessage() + ", url: " + m1186b() + "}";
    }
}
