package com.olacabs.customer.p079e.p080a;

/* renamed from: com.olacabs.customer.e.a.a */
public class AlpahNumericPolicy implements PasswordPolicy {
    public int m13405a(String str) {
        return m13404b(str) ? 9 : 0;
    }

    private boolean m13404b(String str) {
        int length = str.length();
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < length; i++) {
            if (!z2) {
                z2 = Character.isLetter(str.charAt(i));
            }
            if (!z) {
                z = Character.isDigit(str.charAt(i));
            }
            if (z2 && r1) {
                return true;
            }
        }
        return false;
    }
}
