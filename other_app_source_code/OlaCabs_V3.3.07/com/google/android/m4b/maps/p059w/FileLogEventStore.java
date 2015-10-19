package com.google.android.m4b.maps.p059w;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import com.google.android.m4b.maps.bx.UserEvents;
import com.google.android.m4b.maps.p040u.UserEventReporter.UserEventReporter;
import com.google.android.m4b.maps.p041b.IoUtil;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import p004b.p005a.p006a.p007a.p008a.p013d.EventsFilesManager;

/* renamed from: com.google.android.m4b.maps.w.c */
public final class FileLogEventStore implements UserEventReporter {
    private ProtoBuf f8022a;
    private boolean f8023b;
    private String f8024c;
    private Context f8025d;

    public FileLogEventStore(Context context) {
        this.f8025d = context;
        String a = FileLogEventStore.m11566a(context);
        if (a != null) {
            int indexOf = a.indexOf(58);
            a = (indexOf == -1 || indexOf >= a.length()) ? Trace.NULL : new StringBuilder(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR).append(a.substring(indexOf + 1)).toString();
        } else {
            a = Trace.NULL;
        }
        this.f8024c = "event_store_v2" + a;
        File fileStreamPath = context.getFileStreamPath("event_store" + a);
        if (fileStreamPath != null && fileStreamPath.exists()) {
            fileStreamPath.delete();
        }
    }

    public final synchronized ProtoBuf m11568a() {
        if (!this.f8023b) {
            m11567c();
        }
        return this.f8022a;
    }

    public final synchronized void m11569a(ProtoBuf protoBuf) {
        this.f8022a = protoBuf;
        this.f8023b = true;
    }

    private synchronized void m11567c() {
        InputStream inputStream = null;
        synchronized (this) {
            if (this.f8024c != null) {
                try {
                    inputStream = this.f8025d.openFileInput(this.f8024c);
                    if (inputStream != null) {
                        this.f8022a = new ProtoBuf(UserEvents.f6924a);
                        this.f8022a.m10188a(IoUtil.m7771a(inputStream));
                        inputStream.close();
                    } else {
                        this.f8022a = null;
                    }
                } catch (IOException e) {
                    this.f8022a = null;
                    IoUtil.m7773b(inputStream);
                    this.f8025d.deleteFile(this.f8024c);
                }
            }
            this.f8023b = true;
        }
    }

    public final synchronized void m11570b() {
        OutputStream outputStream = null;
        synchronized (this) {
            if (this.f8024c != null) {
                if (this.f8022a == null) {
                    this.f8025d.deleteFile(this.f8024c);
                } else {
                    try {
                        outputStream = this.f8025d.openFileOutput(this.f8024c, 0);
                        outputStream.write(this.f8022a.m10206d());
                        outputStream.close();
                    } catch (IOException e) {
                        IoUtil.m7770a(outputStream);
                        this.f8025d.deleteFile(this.f8024c);
                    }
                }
            }
            this.f8022a = null;
            this.f8023b = false;
        }
    }

    private static String m11566a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }
}
