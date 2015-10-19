package com.leanplum;

import android.net.http.AndroidHttpClient;
import android.os.Handler;
import android.os.Looper;
import com.newrelic.agent.android.api.v1.Defaults;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

final class aI {
    String f8667a;
    aO f8668b;
    String f8669c;
    int f8670d;
    aV f8671e;
    Handler f8672f;
    Looper f8673g;

    public aI(URI uri, aO aOVar) {
        this.f8667a = new StringBuilder(String.valueOf(uri.toString().replaceAll("/$", Trace.NULL))).append("/socket.io/1/").toString();
        this.f8668b = aOVar;
    }

    private static String m12638b(HttpUriRequest httpUriRequest) {
        AndroidHttpClient newInstance = AndroidHttpClient.newInstance("android-websockets");
        try {
            HttpResponse execute;
            if (newInstance instanceof HttpClient) {
                execute = HttpInstrumentation.execute(newInstance, httpUriRequest);
            } else {
                execute = newInstance.execute(httpUriRequest);
            }
            String str = new String(m12637a(execute.getEntity().getContent()));
            return str;
        } finally {
            newInstance.close();
        }
    }

    private static byte[] m12637a(InputStream inputStream) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] bArr = new byte[Defaults.RESPONSE_BODY_LIMIT];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = dataInputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    static /* synthetic */ void m12639b(aI aIVar) {
        aIVar.f8671e = new aV(new URI(aIVar.f8667a + "websocket/" + aIVar.f8669c), new aK(aIVar), null);
        aIVar.f8671e.m12704b();
    }

    static /* synthetic */ void m12636a(aI aIVar) {
        if (aIVar.f8671e != null) {
            aIVar.f8671e.m12705c();
            aIVar.f8671e = null;
        }
        if (aIVar.f8673g != null) {
            aIVar.f8673g.quit();
        }
        aIVar.f8673g = null;
        aIVar.f8672f = null;
    }
}
