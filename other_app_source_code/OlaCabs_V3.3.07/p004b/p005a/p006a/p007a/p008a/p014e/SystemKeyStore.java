package p004b.p005a.p006a.p007a.p008a.p014e;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;

/* renamed from: b.a.a.a.a.e.i */
class SystemKeyStore {
    final KeyStore f304a;
    private final HashMap<Principal, X509Certificate> f305b;

    public SystemKeyStore(InputStream inputStream, String str) {
        KeyStore a = m409a(inputStream, str);
        this.f305b = m410a(a);
        this.f304a = a;
    }

    public boolean m411a(X509Certificate x509Certificate) {
        X509Certificate x509Certificate2 = (X509Certificate) this.f305b.get(x509Certificate.getSubjectX500Principal());
        return x509Certificate2 != null && x509Certificate2.getPublicKey().equals(x509Certificate.getPublicKey());
    }

    public X509Certificate m412b(X509Certificate x509Certificate) {
        X509Certificate x509Certificate2 = (X509Certificate) this.f305b.get(x509Certificate.getIssuerX500Principal());
        if (x509Certificate2 == null) {
            return null;
        }
        if (x509Certificate2.getSubjectX500Principal().equals(x509Certificate.getSubjectX500Principal())) {
            return null;
        }
        try {
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return x509Certificate2;
        } catch (GeneralSecurityException e) {
            return null;
        }
    }

    private HashMap<Principal, X509Certificate> m410a(KeyStore keyStore) {
        try {
            HashMap<Principal, X509Certificate> hashMap = new HashMap();
            Enumeration aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate((String) aliases.nextElement());
                if (x509Certificate != null) {
                    hashMap.put(x509Certificate.getSubjectX500Principal(), x509Certificate);
                }
            }
            return hashMap;
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        }
    }

    private KeyStore m409a(InputStream inputStream, String str) {
        InputStream bufferedInputStream;
        try {
            KeyStore instance = KeyStore.getInstance("BKS");
            bufferedInputStream = new BufferedInputStream(inputStream);
            instance.load(bufferedInputStream, str.toCharArray());
            bufferedInputStream.close();
            return instance;
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        } catch (CertificateException e3) {
            throw new AssertionError(e3);
        } catch (IOException e4) {
            throw new AssertionError(e4);
        } catch (Throwable th) {
            bufferedInputStream.close();
        }
    }
}
