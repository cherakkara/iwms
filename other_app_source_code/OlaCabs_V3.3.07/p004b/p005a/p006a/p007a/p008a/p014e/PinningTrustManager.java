package p004b.p005a.p006a.p007a.p008a.p014e;

import com.crashlytics.android.core.CrashlyticsCore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import p004b.p005a.p006a.p007a.Fabric;

/* renamed from: b.a.a.a.a.e.h */
class PinningTrustManager implements X509TrustManager {
    private final TrustManager[] f299a;
    private final SystemKeyStore f300b;
    private final long f301c;
    private final List<byte[]> f302d;
    private final Set<X509Certificate> f303e;

    public PinningTrustManager(SystemKeyStore systemKeyStore, PinningInfoProvider pinningInfoProvider) {
        this.f302d = new LinkedList();
        this.f303e = Collections.synchronizedSet(new HashSet());
        this.f299a = m408a(systemKeyStore);
        this.f300b = systemKeyStore;
        this.f301c = pinningInfoProvider.getPinCreationTimeInMillis();
        for (String a : pinningInfoProvider.getPins()) {
            this.f302d.add(m407a(a));
        }
    }

    private TrustManager[] m408a(SystemKeyStore systemKeyStore) {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            instance.init(systemKeyStore.f304a);
            return instance.getTrustManagers();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e2) {
            throw new AssertionError(e2);
        }
    }

    private boolean m406a(X509Certificate x509Certificate) throws CertificateException {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(x509Certificate.getPublicKey().getEncoded());
            for (byte[] equals : this.f302d) {
                if (Arrays.equals(equals, digest)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            throw new CertificateException(e);
        }
    }

    private void m405a(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        for (TrustManager trustManager : this.f299a) {
            ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
        }
    }

    private void m404a(X509Certificate[] x509CertificateArr) throws CertificateException {
        if (this.f301c == -1 || System.currentTimeMillis() - this.f301c <= 15552000000L) {
            X509Certificate[] a = CertificateChainCleaner.m327a(x509CertificateArr, this.f300b);
            int length = a.length;
            int i = 0;
            while (i < length) {
                if (!m406a(a[i])) {
                    i++;
                } else {
                    return;
                }
            }
            throw new CertificateException("No valid pins found in chain!");
        }
        Fabric.m512h().m479d(CrashlyticsCore.TAG, "Certificate pins are stale, (" + (System.currentTimeMillis() - this.f301c) + " millis vs " + 15552000000L + " millis) " + "falling back to system trust.");
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (!this.f303e.contains(x509CertificateArr[0])) {
            m405a(x509CertificateArr, str);
            m404a(x509CertificateArr);
            this.f303e.add(x509CertificateArr[0]);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    private byte[] m407a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
