package com.olacabs.customer.p079e.p080a;

import android.content.Context;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/* renamed from: com.olacabs.customer.e.a.b */
public class CommonPasswordsPolicy implements PasswordPolicy {
    private ArrayList<String> f9445a;

    public CommonPasswordsPolicy(Context context) {
        this.f9445a = new ArrayList(49);
        m13406a(context);
    }

    private void m13406a(Context context) {
        try {
            Scanner scanner = new Scanner(context.getApplicationContext().getAssets().open("common_passwords.txt"));
            scanner.useDelimiter(Pattern.compile("[\\r\\n;]+"));
            while (scanner.hasNext()) {
                this.f9445a.add(scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int m13407a(String str) {
        return !this.f9445a.contains(str) ? 9 : 0;
    }
}
