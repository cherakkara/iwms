package com.leanplum;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class LeanplumInflater {
    private Context f8585a;
    private LeanplumResources f8586b;

    public static LeanplumInflater from(Context context) {
        return new LeanplumInflater(context);
    }

    private LeanplumInflater(Context context) {
        this.f8585a = context;
    }

    public LeanplumResources getLeanplumResources() {
        return getLeanplumResources(null);
    }

    public LeanplumResources getLeanplumResources(Resources resources) {
        if (this.f8586b != null) {
            return this.f8586b;
        }
        Resources resources2;
        if (resources == null) {
            resources2 = this.f8585a.getResources();
        } else {
            resources2 = resources;
        }
        if (resources2 instanceof LeanplumResources) {
            return (LeanplumResources) resources2;
        }
        this.f8586b = new LeanplumResources(resources2);
        return this.f8586b;
    }

    public View inflate(int i) {
        return inflate(i, null, false);
    }

    public View inflate(int i, ViewGroup viewGroup) {
        return inflate(i, viewGroup, viewGroup != null);
    }

    public View inflate(int i, ViewGroup viewGroup, boolean z) {
        Throwable th;
        Throwable th2;
        Var a = getLeanplumResources(this.f8585a.getResources()).m12501a(i);
        if (a == null || a.f8637a.equals(a.defaultValue())) {
            return LayoutInflater.from(this.f8585a).inflate(i, viewGroup, z);
        }
        int overrideResId = a.overrideResId();
        if (overrideResId != 0) {
            return LayoutInflater.from(this.f8585a).inflate(overrideResId, viewGroup, z);
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream stream = a.stream();
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD];
            while (true) {
                int read = stream.read(bArr);
                if (read < 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            stream.close();
            Object newInstance = Class.forName("android.content.res.XmlBlock").getConstructor(new Class[]{byte[].class}).newInstance(new Object[]{byteArrayOutputStream.toByteArray()});
            XmlResourceParser xmlResourceParser = null;
            try {
                XmlResourceParser xmlResourceParser2 = (XmlResourceParser) newInstance.getClass().getMethod("newParser", new Class[0]).invoke(newInstance, new Object[0]);
                try {
                    View inflate = LayoutInflater.from(this.f8585a).inflate(xmlResourceParser2, viewGroup, z);
                    if (xmlResourceParser2 != null) {
                        xmlResourceParser2.close();
                    }
                    return inflate;
                } catch (Throwable e) {
                    th = e;
                    xmlResourceParser = xmlResourceParser2;
                    th2 = th;
                    try {
                        throw new RuntimeException(th2);
                    } catch (Throwable th3) {
                        th2 = th3;
                        if (xmlResourceParser != null) {
                            xmlResourceParser.close();
                        }
                        throw th2;
                    }
                } catch (Throwable e2) {
                    th = e2;
                    xmlResourceParser = xmlResourceParser2;
                    th2 = th;
                    if (xmlResourceParser != null) {
                        xmlResourceParser.close();
                    }
                    throw th2;
                }
            } catch (Exception e3) {
                th2 = e3;
                throw new RuntimeException(th2);
            }
        } catch (Throwable th22) {
            Log.e("Leanplum", "Could not inflate resource " + i + ":" + a.stringValue(), th22);
            return LayoutInflater.from(this.f8585a).inflate(i, viewGroup, z);
        }
    }
}
