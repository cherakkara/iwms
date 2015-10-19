package p004b.p005a.p006a.p007a.p008a.p014e;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* renamed from: b.a.a.a.a.e.f */
public final class NetworkUtils {
    public static final SSLSocketFactory m403a(PinningInfoProvider pinningInfoProvider) throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext instance = SSLContext.getInstance("TLS");
        PinningTrustManager pinningTrustManager = new PinningTrustManager(new SystemKeyStore(pinningInfoProvider.getKeyStoreStream(), pinningInfoProvider.getKeyStorePassword()), pinningInfoProvider);
        instance.init(null, new TrustManager[]{pinningTrustManager}, null);
        return instance.getSocketFactory();
    }
}
