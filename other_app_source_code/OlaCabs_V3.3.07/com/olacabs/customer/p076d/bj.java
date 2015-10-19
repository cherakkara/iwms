package com.olacabs.customer.p076d;

import java.util.Map;

/* compiled from: HeadResponse */
/* renamed from: com.olacabs.customer.d.bj */
public class bj {
    public static final String CONFIG_LAST_MODIFIED_TIME_KEY = "Last-Modified-Config";
    private Map<String, String> headers;

    public bj(Map<String, String> map) {
        this.headers = map;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }
}
