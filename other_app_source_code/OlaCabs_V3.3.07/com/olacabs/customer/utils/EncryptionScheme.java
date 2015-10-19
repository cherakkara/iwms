package com.olacabs.customer.utils;

import android.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.olacabs.customer.utils.c */
public class EncryptionScheme {
    public static String m14898a(String str) {
        String str2 = str + "|";
        Key secretKeySpec = new SecretKeySpec("PRODKEYPRODKEY12".getBytes(), "AES");
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, secretKeySpec);
            return new String(Base64.encode(instance.doFinal(str2.getBytes()), 0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String m14899a(byte[] bArr, String str) {
        byte[] bArr2 = null;
        try {
            Key secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, secretKeySpec);
            bArr2 = instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(bArr2);
    }
}
