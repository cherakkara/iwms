package com.leanplum;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Base64;
import com.newrelic.agent.android.instrumentation.Trace;
import java.net.Socket;
import java.net.URI;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.BasicNameValuePair;

final class aV {
    private URI f8707a;
    private aZ f8708b;
    private Socket f8709c;
    private Thread f8710d;
    private HandlerThread f8711e;
    private Handler f8712f;
    private List<BasicNameValuePair> f8713g;
    private C0666p f8714h;
    private final Object f8715i;

    public aV(URI uri, aZ aZVar, List<BasicNameValuePair> list) {
        this.f8715i = new Object();
        this.f8707a = uri;
        this.f8708b = aZVar;
        this.f8713g = null;
        this.f8714h = new C0666p(this);
        this.f8711e = new HandlerThread("websocket-thread");
        this.f8711e.start();
        this.f8712f = new Handler(this.f8711e.getLooper());
    }

    public final aZ m12701a() {
        return this.f8708b;
    }

    public final void m12704b() {
        if (this.f8710d == null || !this.f8710d.isAlive()) {
            this.f8710d = new Thread(new aW(this));
            this.f8710d.start();
        }
    }

    public final void m12705c() {
        if (this.f8709c != null) {
            this.f8712f.post(new aX(this));
        }
    }

    public final void m12702a(String str) {
        m12703a(this.f8714h.m12789a(str));
    }

    static /* synthetic */ StatusLine m12691a(aV aVVar, String str) {
        return TextUtils.isEmpty(str) ? null : BasicLineParser.parseStatusLine(str, new BasicLineParser());
    }

    static /* synthetic */ String m12689a(aV aVVar, C0667q c0667q) {
        int read = c0667q.read();
        if (read == -1) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(Trace.NULL);
        while (read != 10) {
            if (read != 13) {
                stringBuilder.append((char) read);
            }
            read = c0667q.read();
            if (read == -1) {
                return null;
            }
        }
        return stringBuilder.toString();
    }

    static /* synthetic */ String m12696d(aV aVVar) {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) ((int) (Math.random() * 256.0d));
        }
        return Base64.encodeToString(bArr, 0).trim();
    }

    final void m12703a(byte[] bArr) {
        this.f8712f.post(new aY(this, bArr));
    }

    static /* synthetic */ SSLSocketFactory m12693b(aV aVVar) {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, null, null);
        return instance.getSocketFactory();
    }
}
