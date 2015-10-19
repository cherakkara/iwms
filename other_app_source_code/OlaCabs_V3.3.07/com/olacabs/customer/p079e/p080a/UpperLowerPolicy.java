package com.olacabs.customer.p079e.p080a;

/* renamed from: com.olacabs.customer.e.a.g */
public class UpperLowerPolicy implements PasswordPolicy {
    public int m13414a(String str) {
        return m13413b(str) ? 4 : 0;
    }

    private boolean m13413b(String str) {
        int length = str.length();
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < length; i++) {
            if (!z2) {
                z2 = Character.isUpperCase(str.charAt(i));
            }
            if (!z) {
                z = Character.isLowerCase(str.charAt(i));
            }
            if (z && r2) {
                return true;
            }
        }
        return false;
    }
}
