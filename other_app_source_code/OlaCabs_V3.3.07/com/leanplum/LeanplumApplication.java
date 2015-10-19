package com.leanplum;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.leanplum.annotations.Parser;

public class LeanplumApplication extends Application {
    private static LeanplumApplication f8583a;

    public static LeanplumApplication getInstance() {
        return f8583a;
    }

    public static Context getContext() {
        return f8583a;
    }

    public void onCreate() {
        f8583a = this;
        LeanplumActivityHelper.enableLifecycleCallbacks(this);
        super.onCreate();
        Parser.parseVariables(this);
    }

    public Resources getResources() {
        if (C0633g.m12775a()) {
            return super.getResources();
        }
        return new LeanplumResources(super.getResources());
    }
}
