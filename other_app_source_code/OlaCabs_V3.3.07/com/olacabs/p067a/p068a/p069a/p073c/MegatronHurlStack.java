package com.olacabs.p067a.p068a.p069a.p073c;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HurlStack;
import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import com.olacabs.p067a.p068a.p069a.p074d.MegatronCore;
import java.io.IOException;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

/* renamed from: com.olacabs.a.a.a.c.c */
public class MegatronHurlStack extends HurlStack {
    public MegatronHurlStack(SSLSocketFactory sSLSocketFactory) {
        super(null, sSLSocketFactory);
    }

    public HttpResponse m12831a(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        HttpResponse a;
        Throwable th;
        String str = null;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            a = super.m654a((Request) request, (Map) map);
            int statusCode;
            try {
                statusCode = a.getStatusLine().getStatusCode();
                if (statusCode < HttpStatus.SC_OK || statusCode > 299) {
                    try {
                        str = a.getStatusLine().getReasonPhrase();
                    } catch (Throwable th2) {
                        th = th2;
                        MegatronCore.m12842e().m12848a(request.getUrl(), request.getMethod(), request.getHeaders(), request.getBody(), statusCode, str, currentTimeMillis, System.currentTimeMillis());
                        throw th;
                    }
                }
                str = a.getEntity().toString();
                MegatronCore.m12842e().m12848a(request.getUrl(), request.getMethod(), request.getHeaders(), request.getBody(), statusCode, str, currentTimeMillis, System.currentTimeMillis());
            } catch (AuthFailureError e) {
                try {
                    str = "Network Failure";
                    MegatronCore.m12842e().m12848a(request.getUrl(), request.getMethod(), request.getHeaders(), request.getBody(), HarvestErrorCodes.NSURLErrorBadURL, str, currentTimeMillis, System.currentTimeMillis());
                    return a;
                } catch (Throwable th3) {
                    th = th3;
                    statusCode = HarvestErrorCodes.NSURLErrorBadURL;
                    MegatronCore.m12842e().m12848a(request.getUrl(), request.getMethod(), request.getHeaders(), request.getBody(), statusCode, str, currentTimeMillis, System.currentTimeMillis());
                    throw th;
                }
            } catch (Exception e2) {
                str = "Network Failure";
                MegatronCore.m12842e().m12848a(request.getUrl(), request.getMethod(), request.getHeaders(), request.getBody(), HarvestErrorCodes.NSURLErrorBadURL, str, currentTimeMillis, System.currentTimeMillis());
                return a;
            }
        } catch (AuthFailureError e3) {
            a = str;
            str = "Network Failure";
            MegatronCore.m12842e().m12848a(request.getUrl(), request.getMethod(), request.getHeaders(), request.getBody(), HarvestErrorCodes.NSURLErrorBadURL, str, currentTimeMillis, System.currentTimeMillis());
            return a;
        } catch (Exception e4) {
            a = str;
            str = "Network Failure";
            MegatronCore.m12842e().m12848a(request.getUrl(), request.getMethod(), request.getHeaders(), request.getBody(), HarvestErrorCodes.NSURLErrorBadURL, str, currentTimeMillis, System.currentTimeMillis());
            return a;
        }
        return a;
    }
}
