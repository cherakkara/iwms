package com.olacabs.customer.p079e.p080a;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.olacabs.customer.e.a.e */
public class PasswordStrength {
    private ArrayList<PasswordPolicy> f9451a;
    private CommonPasswordsPolicy f9452b;

    /* renamed from: com.olacabs.customer.e.a.e.a */
    public enum PasswordStrength {
        WEAK,
        GOOD,
        STRONG;
        
        public boolean f9450d;
    }

    public PasswordStrength(Context context) {
        this.f9452b = new CommonPasswordsPolicy(context);
        this.f9451a = new ArrayList();
        this.f9451a.add(new LengthPolicy());
        this.f9451a.add(new AlpahNumericPolicy());
        this.f9451a.add(new SpecialCharsPolicy());
        this.f9451a.add(new UpperLowerPolicy());
    }

    public PasswordStrength m13410a(String str) {
        boolean z = false;
        Iterator it = this.f9451a.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((PasswordPolicy) it.next()).m13403a(str) + i;
        }
        i += this.f9452b.m13407a(str);
        if (this.f9452b.m13407a(str) == 0) {
            z = true;
        }
        return m13409a(i, z);
    }

    private PasswordStrength m13409a(int i, boolean z) {
        if (i == 36) {
            return PasswordStrength.STRONG;
        }
        if (i >= 28 && i < 36) {
            return PasswordStrength.GOOD;
        }
        PasswordStrength passwordStrength = PasswordStrength.WEAK;
        passwordStrength.f9450d = z;
        return passwordStrength;
    }
}
