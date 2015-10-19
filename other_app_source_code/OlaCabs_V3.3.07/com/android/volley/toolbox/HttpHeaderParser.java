package com.android.volley.toolbox;

import com.android.volley.Cache.Cache;
import com.android.volley.NetworkResponse;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.protocol.HTTP;

/* renamed from: com.android.volley.toolbox.e */
public class HttpHeaderParser {
    public static Cache m646a(NetworkResponse networkResponse) {
        long a;
        long j;
        long a2;
        Object obj = null;
        long j2 = 0;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = networkResponse.f499c;
        String str = (String) map.get(HTTP.DATE_HEADER);
        if (str != null) {
            a = HttpHeaderParser.m645a(str);
        } else {
            a = 0;
        }
        str = (String) map.get(HttpHeaders.CACHE_CONTROL);
        if (str != null) {
            String[] split = str.split(",");
            long j3 = 0;
            for (String trim : split) {
                String trim2 = trim2.trim();
                if (trim2.equals("no-cache") || trim2.equals("no-store")) {
                    return null;
                }
                if (trim2.startsWith("max-age=")) {
                    try {
                        j3 = Long.parseLong(trim2.substring(8));
                    } catch (Exception e) {
                    }
                } else if (trim2.equals("must-revalidate") || trim2.equals("proxy-revalidate")) {
                    j3 = 0;
                }
            }
            j = j3;
            obj = 1;
        } else {
            j = 0;
        }
        str = (String) map.get(HttpHeaders.EXPIRES);
        if (str != null) {
            a2 = HttpHeaderParser.m645a(str);
        } else {
            a2 = 0;
        }
        str = (String) map.get(HttpHeaders.ETAG);
        if (obj != null) {
            j2 = (1000 * j) + currentTimeMillis;
        } else if (a > 0 && a2 >= a) {
            j2 = (a2 - a) + currentTimeMillis;
        }
        Cache cache = new Cache();
        cache.f467a = networkResponse.f498b;
        cache.f468b = str;
        cache.f471e = j2;
        cache.f470d = cache.f471e;
        cache.f469c = a;
        cache.f472f = map;
        return cache;
    }

    public static long m645a(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException e) {
            return 0;
        }
    }

    public static String m647a(Map<String, String> map) {
        String str = (String) map.get(HTTP.CONTENT_TYPE);
        if (str != null) {
            String[] split = str.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return HTTP.ISO_8859_1;
    }
}
