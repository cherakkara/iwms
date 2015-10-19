package com.olacabs.customer.utils;

import android.util.Base64;
import java.security.MessageDigest;

/* renamed from: com.olacabs.customer.utils.b */
public class CheckMate {
    public static String m14897a(byte[] bArr) throws Exception {
        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        instance.reset();
        instance.update(bArr);
        return Base64.encodeToString(instance.digest(), 0);
    }
}
