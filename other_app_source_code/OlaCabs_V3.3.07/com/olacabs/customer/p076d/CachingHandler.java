package com.olacabs.customer.p076d;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.util.LruCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.olacabs.customer.app.OLog;
import com.olacabs.customer.utils.BackgroundLooper;
import com.olacabs.customer.utils.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/* renamed from: com.olacabs.customer.d.t */
public class CachingHandler {
    private static final int CACHE_MAX_ENTRIES = 100;
    private static final String LOGTAG;
    private static CachingHandler sInstance;
    private Handler mBkgHandler;
    private Context mContext;
    private LruCache<String, bv> mDataCache;

    /* renamed from: com.olacabs.customer.d.t.1 */
    class CachingHandler implements Runnable {
        final /* synthetic */ String f9442a;
        final /* synthetic */ bv f9443b;
        final /* synthetic */ CachingHandler f9444c;

        CachingHandler(CachingHandler cachingHandler, String str, bv bvVar) {
            this.f9444c = cachingHandler;
            this.f9442a = str;
            this.f9443b = bvVar;
        }

        public void run() {
            File file = new File(this.f9444c.mContext.getCacheDir().getAbsolutePath());
            File file2 = new File(file, Uri.encode(this.f9442a));
            if (file != null) {
                try {
                    if (file.exists()) {
                        String a = new Gson().m12346a(this.f9443b);
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        fileOutputStream.write(a.getBytes());
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    OLog.m13318e("Failed to save into json " + e.getStackTrace(), new Object[0]);
                }
            }
        }
    }

    static {
        LOGTAG = CachingHandler.class.getSimpleName();
    }

    private CachingHandler(Context context) {
        this.mBkgHandler = new Handler(BackgroundLooper.m14896a());
        this.mContext = context;
    }

    public static CachingHandler getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CachingHandler(context);
        }
        return sInstance;
    }

    public void init() {
        this.mDataCache = new LruCache(CACHE_MAX_ENTRIES);
    }

    public void addToCache(String str, bv bvVar) {
        OLog.m13311a("Caching data for " + str, new Object[0]);
        this.mDataCache.put(str, bvVar);
        this.mBkgHandler.post(new CachingHandler(this, str, bvVar));
    }

    public bv readFromCache(String str, Class<?> cls, Map<String, String> map) {
        Utils.m14907a();
        bv bvVar = (bv) this.mDataCache.get(str);
        if (bvVar != null && bvVar.isValid(map)) {
            return bvVar;
        }
        r2 = new File(new File(this.mContext.getCacheDir().getAbsolutePath()), Uri.encode(str));
        if (r2 != null) {
            try {
                if (r2.exists()) {
                    bvVar = (bv) new GsonBuilder().m12355b().m12341a(new BufferedReader(new FileReader(r2)), (Class) cls);
                    if (bvVar != null && bvVar.isValid(map)) {
                        this.mDataCache.put(str, bvVar);
                        return bvVar;
                    }
                    return null;
                }
            } catch (IOException e) {
                OLog.m13315c("Failed to read from json", new Object[0]);
                File file;
                if (file != null && file.exists()) {
                    file.delete();
                }
            }
        }
        return null;
    }

    public void purgeAll() {
        OLog.m13311a("Clearing cache folder", new Object[0]);
        this.mDataCache.evictAll();
        File file = new File(this.mContext.getCacheDir().getAbsolutePath());
        if (file != null && file.exists()) {
            for (String file2 : file.list()) {
                File file3 = new File(file, file2);
                if (file3 != null) {
                    OLog.m13311a("Deleted " + file3.getAbsolutePath() + "? - " + file3.delete(), new Object[0]);
                }
            }
        }
    }
}
