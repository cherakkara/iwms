package com.google.android.m4b.maps.be;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import com.google.android.m4b.maps.cc.IObjectWrapper;
import com.google.android.m4b.maps.cc.ObjectWrapper;
import com.google.android.m4b.maps.model.BitmapDescriptor;
import com.google.android.m4b.maps.p047g.Objects;
import com.google.android.m4b.maps.p047g.Preconditions;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* compiled from: BitmapDescriptorImpl */
public abstract class bk {
    private static final BitmapDescriptorImpl f5818a;

    /* renamed from: com.google.android.m4b.maps.be.bk.a */
    public static class BitmapDescriptorImpl extends bk {
        private final String f5819a;

        private BitmapDescriptorImpl(String str) {
            super((byte) 0);
            this.f5819a = (String) Preconditions.m10460a((Object) str, (Object) "null asset name");
        }

        public final Bitmap m8894a(Context context) {
            try {
                InputStream open = context.getAssets().open(this.f5819a);
                try {
                    Bitmap decodeStream = BitmapFactoryInstrumentation.decodeStream(open);
                    return decodeStream;
                } finally {
                    try {
                        open.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                return null;
            }
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.f5819a});
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BitmapDescriptorImpl)) {
                return false;
            }
            return this.f5819a.equals(((BitmapDescriptorImpl) obj).f5819a);
        }

        public final String toString() {
            return Objects.m10456a(this).m10455a("asset", this.f5819a).toString();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bk.b */
    public static class BitmapDescriptorImpl extends bk {
        private final bk f5820a;
        private final float f5821b;

        public BitmapDescriptorImpl(bk bkVar, float f) {
            boolean z = true;
            super((byte) 0);
            if (f < 0.0f || f >= 360.0f) {
                z = false;
            }
            Preconditions.m10466b(z, "hue outside range [0.0,360.0)");
            this.f5820a = (bk) Preconditions.m10459a((Object) bkVar);
            this.f5821b = f;
        }

        public final Bitmap m8895a(Context context) {
            Bitmap a = this.f5820a.m8893a(context);
            float f = this.f5821b;
            int height = a.getHeight();
            int width = a.getWidth();
            int[] iArr = new int[(height * width)];
            a.getPixels(iArr, 0, width, 0, 0, width, height);
            int[] iArr2 = new int[(height * width)];
            float[] fArr = new float[3];
            for (int i = 0; i < iArr.length; i++) {
                Color.colorToHSV(iArr[i], fArr);
                fArr[0] = f;
                iArr2[i] = Color.HSVToColor(Color.alpha(iArr[i]), fArr);
            }
            return Bitmap.createBitmap(iArr2, 0, width, width, height, Config.ARGB_8888);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.f5820a, Float.valueOf(this.f5821b)});
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BitmapDescriptorImpl)) {
                return false;
            }
            BitmapDescriptorImpl bitmapDescriptorImpl = (BitmapDescriptorImpl) obj;
            if (this.f5820a.equals(bitmapDescriptorImpl.f5820a) && Float.floatToIntBits(this.f5821b) == Float.floatToIntBits(bitmapDescriptorImpl.f5821b)) {
                return true;
            }
            return false;
        }

        public final String toString() {
            return Objects.m10456a(this).m10455a("base", this.f5820a).m10455a("hue", Float.valueOf(this.f5821b)).toString();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bk.c */
    public static class BitmapDescriptorImpl extends bk {
        private final String f5822a;

        private BitmapDescriptorImpl(String str) {
            super((byte) 0);
            this.f5822a = (String) Preconditions.m10460a((Object) str, (Object) "null file name");
        }

        public final Bitmap m8896a(Context context) {
            try {
                InputStream openFileInput = context.openFileInput(this.f5822a);
                try {
                    Bitmap decodeStream = BitmapFactoryInstrumentation.decodeStream(openFileInput);
                    return decodeStream;
                } finally {
                    try {
                        openFileInput.close();
                    } catch (IOException e) {
                    }
                }
            } catch (FileNotFoundException e2) {
                return null;
            }
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.f5822a});
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BitmapDescriptorImpl)) {
                return false;
            }
            return this.f5822a.equals(((BitmapDescriptorImpl) obj).f5822a);
        }

        public final String toString() {
            return Objects.m10456a(this).m10455a("file", this.f5822a).toString();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bk.d */
    public static class BitmapDescriptorImpl extends bk {
        private final Bitmap f5823a;

        private BitmapDescriptorImpl(Bitmap bitmap) {
            super((byte) 0);
            this.f5823a = bitmap;
        }

        public final Bitmap m8897a(Context context) {
            return this.f5823a;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.f5823a});
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BitmapDescriptorImpl)) {
                return false;
            }
            return this.f5823a.equals(((BitmapDescriptorImpl) obj).f5823a);
        }

        public final String toString() {
            return Objects.m10456a(this).toString();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bk.e */
    public static class BitmapDescriptorImpl extends bk {
        private final int f5824a;

        private BitmapDescriptorImpl(int i) {
            boolean z;
            super((byte) 0);
            if (i >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.m10464a(z, "invalid resource id: %s", Integer.valueOf(i));
            this.f5824a = i;
        }

        public final Bitmap m8898a(Context context) {
            return BitmapFactoryInstrumentation.decodeResource(InternalResourceLoader.m9392a(), this.f5824a);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{Integer.valueOf(this.f5824a)});
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BitmapDescriptorImpl)) {
                return false;
            }
            if (this.f5824a != ((BitmapDescriptorImpl) obj).f5824a) {
                return false;
            }
            return true;
        }

        public final String toString() {
            return Objects.m10456a(this).m10455a("resource ", Integer.valueOf(this.f5824a)).toString();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bk.f */
    public static class BitmapDescriptorImpl extends bk {
        private final String f5825a;

        private BitmapDescriptorImpl(String str) {
            super((byte) 0);
            this.f5825a = (String) Preconditions.m10460a((Object) str, (Object) "null file name");
        }

        public final Bitmap m8899a(Context context) {
            return BitmapFactoryInstrumentation.decodeFile(this.f5825a);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.f5825a});
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BitmapDescriptorImpl)) {
                return false;
            }
            return this.f5825a.equals(((BitmapDescriptorImpl) obj).f5825a);
        }

        public final String toString() {
            return Objects.m10456a(this).m10455a("path", this.f5825a).toString();
        }
    }

    /* renamed from: com.google.android.m4b.maps.be.bk.g */
    public static class BitmapDescriptorImpl extends bk {
        private final int f5826a;

        private BitmapDescriptorImpl(int i) {
            boolean z;
            super((byte) 0);
            if (i >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.m10464a(z, "invalid resource id: %s", Integer.valueOf(i));
            this.f5826a = i;
        }

        public final Bitmap m8900a(Context context) {
            return BitmapFactoryInstrumentation.decodeResource(InternalResourceLoader.m9394b(), this.f5826a);
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{Integer.valueOf(this.f5826a)});
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BitmapDescriptorImpl)) {
                return false;
            }
            if (this.f5826a != ((BitmapDescriptorImpl) obj).f5826a) {
                return false;
            }
            return true;
        }

        public final String toString() {
            return Objects.m10456a(this).m10455a("resource ", Integer.valueOf(this.f5826a)).toString();
        }
    }

    public abstract Bitmap m8893a(Context context);

    static {
        f5818a = new BitmapDescriptorImpl((byte) 0);
    }

    private bk(byte b) {
    }

    public static BitmapDescriptorImpl m8886a() {
        return f5818a;
    }

    public static BitmapDescriptorImpl m8884a(float f) {
        return new BitmapDescriptorImpl(f5818a, f);
    }

    public static BitmapDescriptorImpl m8887a(int i) {
        return new BitmapDescriptorImpl((byte) 0);
    }

    public static BitmapDescriptorImpl m8891b(int i) {
        return new BitmapDescriptorImpl((byte) 0);
    }

    public static BitmapDescriptorImpl m8883a(String str) {
        return new BitmapDescriptorImpl((byte) 0);
    }

    public static BitmapDescriptorImpl m8890b(String str) {
        return new BitmapDescriptorImpl((byte) 0);
    }

    public static BitmapDescriptorImpl m8885a(Bitmap bitmap) {
        return new BitmapDescriptorImpl((byte) 0);
    }

    public static BitmapDescriptorImpl m8892c(String str) {
        return new BitmapDescriptorImpl((byte) 0);
    }

    static bk m8889a(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            return f5818a;
        }
        return m8888a(bitmapDescriptor.m10810a());
    }

    static bk m8888a(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper == null) {
            return f5818a;
        }
        return (bk) ObjectWrapper.m10131a(iObjectWrapper);
    }
}
