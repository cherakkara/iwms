package com.olacabs.customer.p079e.p080a;

/* renamed from: com.olacabs.customer.e.a.c */
public class LengthPolicy implements PasswordPolicy {
    public int m13408a(String str) {
        return str.length() >= 6 ? 10 : 0;
    }
}
