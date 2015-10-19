package com.newrelic.agent.android.instrumentation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.util.TypedValue;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class BitmapFactoryInstrumentation {
    private static final ArrayList<String> categoryParams;

    static {
        categoryParams = new ArrayList(Arrays.asList(new String[]{AnalyticAttribute.EVENT_CATEGORY_ATTRIBUTE, MetricCategory.class.getName(), "IMAGE"}));
    }

    private BitmapFactoryInstrumentation() {
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFile(String str, Options options) {
        TraceMachine.enterMethod("BitmapFactory#decodeFile", categoryParams);
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        TraceMachine.exitMethod();
        return decodeFile;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFile(String str) {
        TraceMachine.enterMethod("BitmapFactory#decodeFile", categoryParams);
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        TraceMachine.exitMethod();
        return decodeFile;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeResourceStream(Resources resources, TypedValue typedValue, InputStream inputStream, Rect rect, Options options) {
        TraceMachine.enterMethod("BitmapFactory#decodeResourceStream", categoryParams);
        Bitmap decodeResourceStream = BitmapFactory.decodeResourceStream(resources, typedValue, inputStream, rect, options);
        TraceMachine.exitMethod();
        return decodeResourceStream;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeResource(Resources resources, int i, Options options) {
        TraceMachine.enterMethod("BitmapFactory#decodeResource", categoryParams);
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, i, options);
        TraceMachine.exitMethod();
        return decodeResource;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeResource(Resources resources, int i) {
        TraceMachine.enterMethod("BitmapFactory#decodeResource", categoryParams);
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, i);
        TraceMachine.exitMethod();
        return decodeResource;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeByteArray(byte[] bArr, int i, int i2, Options options) {
        TraceMachine.enterMethod("BitmapFactory#decodeByteArray", categoryParams);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, i, i2, options);
        TraceMachine.exitMethod();
        return decodeByteArray;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeByteArray(byte[] bArr, int i, int i2) {
        TraceMachine.enterMethod("BitmapFactory#decodeByteArray", categoryParams);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, i, i2);
        TraceMachine.exitMethod();
        return decodeByteArray;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeStream(InputStream inputStream, Rect rect, Options options) {
        TraceMachine.enterMethod("BitmapFactory#decodeStream", categoryParams);
        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, rect, options);
        TraceMachine.exitMethod();
        return decodeStream;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeStream(InputStream inputStream) {
        TraceMachine.enterMethod("BitmapFactory#decodeStream", categoryParams);
        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
        TraceMachine.exitMethod();
        return decodeStream;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor, Rect rect, Options options) {
        TraceMachine.enterMethod("BitmapFactory#decodeFileDescriptor", categoryParams);
        Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
        TraceMachine.exitMethod();
        return decodeFileDescriptor;
    }

    @ReplaceCallSite(isStatic = true, scope = "android.graphics.BitmapFactory")
    public static Bitmap decodeFileDescriptor(FileDescriptor fileDescriptor) {
        TraceMachine.enterMethod("BitmapFactory#decodeFileDescriptor", categoryParams);
        Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        TraceMachine.exitMethod();
        return decodeFileDescriptor;
    }
}
