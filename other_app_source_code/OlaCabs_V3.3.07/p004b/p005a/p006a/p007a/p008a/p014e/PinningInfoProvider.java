package p004b.p005a.p006a.p007a.p008a.p014e;

import java.io.InputStream;

/* renamed from: b.a.a.a.a.e.g */
public interface PinningInfoProvider {
    String getKeyStorePassword();

    InputStream getKeyStoreStream();

    long getPinCreationTimeInMillis();

    String[] getPins();
}
