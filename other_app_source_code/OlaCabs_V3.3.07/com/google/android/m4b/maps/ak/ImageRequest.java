package com.google.android.m4b.maps.ak;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import com.google.android.m4b.maps.p038a.NetworkResponse;
import com.google.android.m4b.maps.p038a.ParseError;
import com.google.android.m4b.maps.p038a.Request;
import com.google.android.m4b.maps.p038a.Response.Response;
import com.google.android.m4b.maps.p038a.RetryPolicy;
import com.google.android.m4b.maps.p038a.VolleyLog;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.olacabs.customer.utils.Constants;

/* renamed from: com.google.android.m4b.maps.ak.g */
public final class ImageRequest extends Request<Bitmap> {
    private static final Object f3266e;
    private final Response<Bitmap> f3267a;
    private final Config f3268b;
    private final int f3269c;
    private final int f3270d;

    static {
        f3266e = new Object();
    }

    public ImageRequest(String str, Response<Bitmap> response, int i, int i2, Config config, Response response2) {
        super(0, str, response2);
        m4701a(new RetryPolicy(Constants.MILLIS_IN_A_SECOND, 2, dm.DEFAULT_BACKOFF_MULT));
        this.f3267a = response;
        this.f3268b = config;
        this.f3269c = 0;
        this.f3270d = 0;
    }

    public final Request.Request m5251m() {
        return Request.Request.LOW;
    }

    private static int m5247a(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0) {
            return i3;
        }
        if (i == 0) {
            return (int) ((((double) i2) / ((double) i4)) * ((double) i3));
        }
        if (i2 == 0) {
            return i;
        }
        double d = ((double) i4) / ((double) i3);
        if (((double) i) * d > ((double) i2)) {
            return (int) (((double) i2) / d);
        }
        return i;
    }

    protected final com.google.android.m4b.maps.p038a.Response<Bitmap> m5249a(NetworkResponse networkResponse) {
        com.google.android.m4b.maps.p038a.Response<Bitmap> a;
        synchronized (f3266e) {
            try {
                Object decodeByteArray;
                byte[] bArr = networkResponse.f2894a;
                Options options = new Options();
                if (this.f3269c == 0 && this.f3270d == 0) {
                    options.inPreferredConfig = this.f3268b;
                    decodeByteArray = BitmapFactoryInstrumentation.decodeByteArray(bArr, 0, bArr.length, options);
                } else {
                    options.inJustDecodeBounds = true;
                    BitmapFactoryInstrumentation.decodeByteArray(bArr, 0, bArr.length, options);
                    int i = options.outWidth;
                    int i2 = options.outHeight;
                    int a2 = ImageRequest.m5247a(this.f3269c, this.f3270d, i, i2);
                    int a3 = ImageRequest.m5247a(this.f3270d, this.f3269c, i2, i);
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = ImageRequest.m5248b(i, i2, a2, a3);
                    Bitmap decodeByteArray2 = BitmapFactoryInstrumentation.decodeByteArray(bArr, 0, bArr.length, options);
                    if (decodeByteArray2 == null || (decodeByteArray2.getWidth() <= a2 && decodeByteArray2.getHeight() <= a3)) {
                        Bitmap bitmap = decodeByteArray2;
                    } else {
                        decodeByteArray = Bitmap.createScaledBitmap(decodeByteArray2, a2, a3, true);
                        decodeByteArray2.recycle();
                    }
                }
                a = decodeByteArray == null ? com.google.android.m4b.maps.p038a.Response.m4727a(new ParseError(networkResponse)) : com.google.android.m4b.maps.p038a.Response.m4728a(decodeByteArray, HttpHeaderParser.m5240a(networkResponse));
            } catch (Throwable e) {
                VolleyLog.m4738c("Caught OOM for %d byte image, url=%s", Integer.valueOf(networkResponse.f2894a.length), m4707c());
                a = com.google.android.m4b.maps.p038a.Response.m4727a(new ParseError(e));
            }
        }
        return a;
    }

    private static int m5248b(int i, int i2, int i3, int i4) {
        double min = Math.min(((double) i) / ((double) i3), ((double) i2) / ((double) i4));
        float f = br.DEFAULT_BACKOFF_MULT;
        while (((double) (f * dm.DEFAULT_BACKOFF_MULT)) <= min) {
            f *= dm.DEFAULT_BACKOFF_MULT;
        }
        return (int) f;
    }
}
