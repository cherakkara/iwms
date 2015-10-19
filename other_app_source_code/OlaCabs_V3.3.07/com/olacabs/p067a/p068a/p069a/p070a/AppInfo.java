package com.olacabs.p067a.p068a.p069a.p070a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.gson.p063a.SerializedName;
import java.io.File;

/* renamed from: com.olacabs.a.a.a.a.c */
public class AppInfo {
    @SerializedName(a = "size")
    private long mAPKSize;
    @SerializedName(a = "name")
    private String mAppName;
    private transient Context mContext;
    private transient String mPackageName;
    @SerializedName(a = "version")
    private String sVersionName;

    public AppInfo(Context context) {
        this.mContext = context;
        init();
    }

    public void init() {
        try {
            this.mPackageName = this.mContext.getPackageName();
            this.sVersionName = this.mContext.getPackageManager().getPackageInfo(this.mPackageName, 0).versionName;
            this.mAPKSize = getAppSize();
            this.mAppName = getApplicationName();
        } catch (NameNotFoundException e) {
        }
    }

    private String getApplicationName() {
        return this.mContext.getString(this.mContext.getApplicationInfo().labelRes);
    }

    private long getAppSize() {
        long j = 0;
        try {
            j = new File(this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 0).publicSourceDir).length();
        } catch (NameNotFoundException e) {
        } catch (Throwable th) {
        }
        return j;
    }
}
