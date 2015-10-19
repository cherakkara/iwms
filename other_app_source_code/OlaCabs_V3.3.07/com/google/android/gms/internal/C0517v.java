package com.google.android.gms.internal;

import android.text.TextUtils;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.gms.internal.v */
public final class C0517v {
    private static final Pattern f2440a;
    private static final Pattern f2441b;

    static {
        f2440a = Pattern.compile("\\\\.");
        f2441b = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }

    public static String m4169a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = f2441b.matcher(str);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            switch (matcher.group().charAt(0)) {
                case R.SlidingUpPanelLayout_umanoAnchorPoint /*8*/:
                    matcher.appendReplacement(stringBuffer, "\\\\b");
                    break;
                case HTTP.HT /*9*/:
                    matcher.appendReplacement(stringBuffer, "\\\\t");
                    break;
                case HTTP.LF /*10*/:
                    matcher.appendReplacement(stringBuffer, "\\\\n");
                    break;
                case com.olacabs.customer.R.R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                    matcher.appendReplacement(stringBuffer, "\\\\f");
                    break;
                case HTTP.CR /*13*/:
                    matcher.appendReplacement(stringBuffer, "\\\\r");
                    break;
                case '\"':
                    matcher.appendReplacement(stringBuffer, "\\\\\\\"");
                    break;
                case '/':
                    matcher.appendReplacement(stringBuffer, "\\\\/");
                    break;
                case '\\':
                    matcher.appendReplacement(stringBuffer, "\\\\\\\\");
                    break;
                default:
                    break;
            }
        }
        if (stringBuffer == null) {
            return str;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
