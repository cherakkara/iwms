package com.olacabs.customer.p079e.p080a;

/* renamed from: com.olacabs.customer.e.a.f */
public class SpecialCharsPolicy implements PasswordPolicy {
    public int m13412a(String str) {
        return m13411b(str) ? 4 : 0;
    }

    private boolean m13411b(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if ((Character.isLetterOrDigit(str.charAt(i)) & Character.isSpaceChar(str.charAt(i))) == 0) {
                return true;
            }
        }
        return false;
    }
}
