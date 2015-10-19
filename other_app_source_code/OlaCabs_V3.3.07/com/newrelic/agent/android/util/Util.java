package com.newrelic.agent.android.util;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Util {
    private static final Random random;

    static {
        random = new Random();
    }

    public static String slurp(InputStream inputStream) throws IOException {
        char[] cArr = new char[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read < 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append(cArr, 0, read);
        }
    }

    public static String sanitizeUrl(String str) {
        if (str == null) {
            return null;
        }
        try {
            URL url = new URL(str);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(url.getProtocol());
            stringBuffer.append("://");
            stringBuffer.append(url.getHost());
            if (url.getPort() != -1) {
                stringBuffer.append(":");
                stringBuffer.append(url.getPort());
            }
            stringBuffer.append(url.getPath());
            return stringBuffer.toString();
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public static Random getRandom() {
        return random;
    }
}
