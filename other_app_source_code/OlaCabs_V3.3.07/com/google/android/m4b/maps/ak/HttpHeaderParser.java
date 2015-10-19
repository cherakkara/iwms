package com.google.android.m4b.maps.ak;

import com.google.android.m4b.maps.p038a.Cache.Cache;
import com.google.android.m4b.maps.p038a.NetworkResponse;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.ak.d */
public final class HttpHeaderParser {
    public static Cache m5240a(NetworkResponse networkResponse) {
        long a;
        long j;
        long a2;
        Object obj = null;
        long j2 = 0;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = networkResponse.f2895b;
        String str = (String) map.get(HTTP.DATE_HEADER);
        if (str != null) {
            a = HttpHeaderParser.m5239a(str);
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
            a2 = HttpHeaderParser.m5239a(str);
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
        cache.f2870a = networkResponse.f2894a;
        cache.f2871b = str;
        cache.f2874e = j2;
        cache.f2873d = cache.f2874e;
        cache.f2872c = a;
        cache.f2875f = map;
        return cache;
    }

    private static long m5239a(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException e) {
            return 0;
        }
    }

    public static String m5241a(Map<String, String> map) {
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
