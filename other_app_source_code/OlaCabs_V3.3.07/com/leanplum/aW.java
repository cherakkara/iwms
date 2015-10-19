package com.leanplum;

import android.text.TextUtils;
import android.util.Log;
import com.apsalar.sdk.Constants;
import java.io.PrintWriter;
import java.net.URI;
import javax.net.SocketFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.message.BasicLineParser;

final class aW implements Runnable {
    private /* synthetic */ aV f8716a;

    aW(aV aVVar) {
        this.f8716a = aVVar;
    }

    public final void run() {
        try {
            String str;
            int port = this.f8716a.f8707a.getPort() != -1 ? this.f8716a.f8707a.getPort() : this.f8716a.f8707a.getScheme().equals("wss") ? 443 : 80;
            String path = TextUtils.isEmpty(this.f8716a.f8707a.getPath()) ? "/" : this.f8716a.f8707a.getPath();
            if (TextUtils.isEmpty(this.f8716a.f8707a.getQuery())) {
                str = path;
            } else {
                str = new StringBuilder(String.valueOf(path)).append("?").append(this.f8716a.f8707a.getQuery()).toString();
            }
            URI uri = new URI(this.f8716a.f8707a.getScheme().equals("wss") ? Constants.API_PROTOCOL : HttpHost.DEFAULT_SCHEME_NAME, "//" + this.f8716a.f8707a.getHost(), null);
            this.f8716a.f8709c = (this.f8716a.f8707a.getScheme().equals("wss") ? aV.m12693b(this.f8716a) : SocketFactory.getDefault()).createSocket(this.f8716a.f8707a.getHost(), port);
            PrintWriter printWriter = new PrintWriter(this.f8716a.f8709c.getOutputStream());
            printWriter.print("GET " + str + " HTTP/1.1\r\n");
            printWriter.print("Upgrade: websocket\r\n");
            printWriter.print("Connection: Upgrade\r\n");
            printWriter.print("Host: " + this.f8716a.f8707a.getHost() + "\r\n");
            printWriter.print("Origin: " + uri.toString() + "\r\n");
            printWriter.print("Sec-WebSocket-Key: " + aV.m12696d(this.f8716a) + "\r\n");
            printWriter.print("Sec-WebSocket-Version: 13\r\n");
            if (this.f8716a.f8713g != null) {
                for (NameValuePair nameValuePair : this.f8716a.f8713g) {
                    printWriter.print(String.format("%s: %s\r\n", new Object[]{nameValuePair.getName(), nameValuePair.getValue()}));
                }
            }
            printWriter.print("\r\n");
            printWriter.flush();
            C0667q c0667q = new C0667q(this.f8716a.f8709c.getInputStream());
            StatusLine a = aV.m12691a(this.f8716a, aV.m12689a(this.f8716a, c0667q));
            if (a == null) {
                throw new HttpException("Received no reply from server.");
            } else if (a.getStatusCode() != HttpStatus.SC_SWITCHING_PROTOCOLS) {
                throw new HttpResponseException(a.getStatusCode(), a.getReasonPhrase());
            } else {
                while (true) {
                    Object a2 = aV.m12689a(this.f8716a, c0667q);
                    if (TextUtils.isEmpty(a2)) {
                        this.f8716a.f8708b.m12644b();
                        this.f8716a.f8714h.m12788a(c0667q);
                        return;
                    }
                    BasicLineParser.parseHeader(a2, new BasicLineParser()).getName();
                }
            }
        } catch (Throwable e) {
            Log.d("WebSocketClient", "WebSocket EOF!", e);
            this.f8716a.f8708b.m12641a(0, "EOF");
        } catch (Throwable e2) {
            Log.d("WebSocketClient", "Websocket SSL error!", e2);
            this.f8716a.f8708b.m12641a(0, "SSL");
        } catch (Exception e3) {
            this.f8716a.f8708b.m12642a(e3);
        }
    }
}
