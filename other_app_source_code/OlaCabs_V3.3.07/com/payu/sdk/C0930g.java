package com.payu.sdk;

import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.utils.Constants;
import com.payu.p084a.Bank;
import com.payu.sdk.C0927e.C0926a;
import com.payu.sdk.p085a.C0921b;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import org.apache.http.protocol.HTTP;

/* renamed from: com.payu.sdk.g */
public class C0930g extends HashMap<String, String> {

    /* renamed from: com.payu.sdk.g.a */
    public class C0929a {
        final /* synthetic */ C0930g f11566a;
        private C0930g f11567b;

        public C0929a(C0930g c0930g) {
            this.f11566a = c0930g;
            this.f11567b = new C0930g();
        }

        public C0929a m14952a(String str, String str2) {
            this.f11567b.put(str, str2);
            return this;
        }

        public C0930g m14953a() throws C0921b {
            int i = 0;
            String[] strArr = new String[]{Constants.ARG_AMOUNT, Bank.TXN_ID, Constants.PRODUCT_INFO, "surl", "mode"};
            int length = strArr.length;
            while (i < length) {
                String str = strArr[i];
                if (!this.f11567b.containsKey(str) || this.f11567b.m14956a(str) == null || this.f11567b.m14956a(str).equals(Trace.NULL)) {
                    throw new C0921b("Missing required parameter" + str);
                }
                i++;
            }
            if (Double.valueOf(this.f11567b.m14956a(Constants.ARG_AMOUNT)).doubleValue() > 0.0d) {
                return this.f11567b;
            }
            throw new C0921b("Amount cannot be less than zero");
        }

        public String m14954a(String str) {
            return this.f11567b.m14956a(str);
        }
    }

    public C0926a m14955a() {
        return C0926a.valueOf(m14956a("mode"));
    }

    public String m14956a(String str) {
        String str2 = (String) super.get(str);
        if (str2 == null) {
            str2 = Trace.NULL;
        }
        try {
            return URLEncoder.encode(str2, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Trace.NULL;
        }
    }
}
