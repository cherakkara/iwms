package com.localytics.android;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.util.TreeMap;

final class MarketingDownloader extends BaseUploadThread {
    private static final String MARKETING_URL_HTTP = "http://%s/api/v2/applications/%s/amp";
    private static final String MARKETING_URL_HTTPS = "https://%s/api/v2/applications/%s/amp";

    MarketingDownloader(BaseHandler baseHandler, TreeMap<Integer, Object> treeMap, String str, LocalyticsDao localyticsDao) {
        super(baseHandler, treeMap, str, localyticsDao);
    }

    static String downloadFile(String str, String str2, boolean z, Proxy proxy) {
        File file = new File(str2);
        if (!file.exists() || z) {
            File parentFile = file.getParentFile();
            if (parentFile.mkdirs() || parentFile.isDirectory()) {
                try {
                    parentFile = new File(String.format("%s_temp", new Object[]{str2}));
                    parentFile.createNewFile();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(BaseUploadThread.createURLConnection(new URL(str), proxy).getInputStream(), AccessibilityNodeInfoCompat.ACTION_COPY);
                    FileOutputStream fileOutputStream = new FileOutputStream(parentFile.getPath());
                    byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.close();
                    if (parentFile.renameTo(file)) {
                        return str2;
                    }
                    parentFile.delete();
                    return null;
                } catch (IOException e) {
                    Log.m12801w("In-app campaign not found. Creating a new one.");
                    return null;
                }
            }
            Log.m12801w(String.format("Could not create the directory %s for saving file.", new Object[]{parentFile.getAbsolutePath()}));
            return null;
        }
        Log.m12801w(String.format("The file %s does exist and overwrite is turned off.", new Object[]{file.getAbsolutePath()}));
        return str2;
    }

    static String getRemoteFileURL(MarketingMessage marketingMessage) {
        String safeStringFromMap = JsonHelper.getSafeStringFromMap(marketingMessage, "phone_location");
        String safeStringFromMap2 = JsonHelper.getSafeStringFromMap(marketingMessage, "devices");
        if (safeStringFromMap2 == null) {
            return safeStringFromMap;
        }
        if (safeStringFromMap2.equals("tablet")) {
            return JsonHelper.getSafeStringFromMap(marketingMessage, "tablet_location");
        }
        if (safeStringFromMap2.equals("both")) {
            return JsonHelper.getSafeStringFromMap(marketingMessage, "phone_location");
        }
        return safeStringFromMap;
    }

    static String getLocalFileURL(long j, boolean z, Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getMarketingDataDirectory(context, str));
        stringBuilder.append(File.separator);
        if (z) {
            stringBuilder.append(String.format("amp_rule_%d.zip", new Object[]{Long.valueOf(j)}));
        } else {
            stringBuilder.append(String.format("marketing_rule_%d", new Object[]{Long.valueOf(j)}));
            File file = new File(stringBuilder.toString());
            if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
                stringBuilder.append(File.separator);
                stringBuilder.append("index.html");
            } else {
                Log.m12801w(String.format("Could not create the directory %s for saving the HTML file.", new Object[]{file.getAbsolutePath()}));
                return null;
            }
        }
        return stringBuilder.toString();
    }

    static String getMarketingDataDirectory(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getFilesDir().getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(".localytics");
        stringBuilder.append(File.separator);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    int uploadData() {
        String apiKey = getApiKey();
        upload(UploadType.MARKETING, String.format(Constants.USE_HTTPS ? MARKETING_URL_HTTPS : MARKETING_URL_HTTP, new Object[]{Constants.MARKETING_HOST, apiKey}), Trace.NULL, 0);
        return 1;
    }
}
