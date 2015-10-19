package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import java.io.File;

/* renamed from: com.android.volley.toolbox.m */
public class Volley {
    public static RequestQueue m687a(Context context, HttpStack httpStack, int i) {
        RequestQueue requestQueue;
        File file = new File(context.getCacheDir(), "volley");
        String str = "volley/0";
        try {
            String packageName = context.getPackageName();
            str = packageName + "/" + context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (NameNotFoundException e) {
        }
        if (httpStack == null) {
            if (VERSION.SDK_INT >= 9) {
                httpStack = new HurlStack();
            } else {
                httpStack = new HttpClientStack(AndroidHttpClient.newInstance(str));
            }
        }
        Network basicNetwork = new BasicNetwork(httpStack);
        if (i <= -1) {
            requestQueue = new RequestQueue(new DiskBasedCache(file), basicNetwork);
        } else {
            requestQueue = new RequestQueue(new DiskBasedCache(file, i), basicNetwork);
        }
        requestQueue.m578a();
        return requestQueue;
    }

    public static RequestQueue m686a(Context context, HttpStack httpStack) {
        return Volley.m687a(context, httpStack, -1);
    }

    public static RequestQueue m685a(Context context) {
        return Volley.m686a(context, null);
    }
}
