package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response.Response;
import com.android.volley.VolleyLog;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;
import com.olacabs.customer.utils.Constants;

/* renamed from: com.android.volley.toolbox.i */
public class ImageRequest extends Request<Bitmap> {
    private static final Object f590e;
    private final Response<Bitmap> f591a;
    private final Config f592b;
    private final int f593c;
    private final int f594d;

    protected /* synthetic */ void deliverResponse(Object obj) {
        m683a((Bitmap) obj);
    }

    static {
        f590e = new Object();
    }

    public ImageRequest(String str, Response<Bitmap> response, int i, int i2, Config config, Response response2) {
        super(0, str, response2);
        setRetryPolicy(new DefaultRetryPolicy(Constants.MILLIS_IN_A_SECOND, 2, dm.DEFAULT_BACKOFF_MULT));
        this.f591a = response;
        this.f592b = config;
        this.f593c = i;
        this.f594d = i2;
    }

    public Request.Request getPriority() {
        return Request.Request.LOW;
    }

    private static int m682b(int i, int i2, int i3, int i4) {
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

    protected com.android.volley.Response<Bitmap> parseNetworkResponse(NetworkResponse networkResponse) {
        com.android.volley.Response<Bitmap> a;
        synchronized (f590e) {
            try {
                a = m681a(networkResponse);
            } catch (Throwable e) {
                VolleyLog.m595c("Caught OOM for %d byte image, url=%s", Integer.valueOf(networkResponse.f498b.length), getUrl());
                a = com.android.volley.Response.m586a(new ParseError(e));
            }
        }
        return a;
    }

    private com.android.volley.Response<Bitmap> m681a(NetworkResponse networkResponse) {
        Object decodeByteArray;
        byte[] bArr = networkResponse.f498b;
        Options options = new Options();
        if (this.f593c == 0 && this.f594d == 0) {
            options.inPreferredConfig = this.f592b;
            decodeByteArray = BitmapFactoryInstrumentation.decodeByteArray(bArr, 0, bArr.length, options);
        } else {
            options.inJustDecodeBounds = true;
            BitmapFactoryInstrumentation.decodeByteArray(bArr, 0, bArr.length, options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            int b = ImageRequest.m682b(this.f593c, this.f594d, i, i2);
            int b2 = ImageRequest.m682b(this.f594d, this.f593c, i2, i);
            options.inJustDecodeBounds = false;
            options.inSampleSize = ImageRequest.m680a(i, i2, b, b2);
            Bitmap decodeByteArray2 = BitmapFactoryInstrumentation.decodeByteArray(bArr, 0, bArr.length, options);
            if (decodeByteArray2 == null || (decodeByteArray2.getWidth() <= b && decodeByteArray2.getHeight() <= b2)) {
                Bitmap bitmap = decodeByteArray2;
            } else {
                decodeByteArray = Bitmap.createScaledBitmap(decodeByteArray2, b, b2, true);
                decodeByteArray2.recycle();
            }
        }
        if (decodeByteArray == null) {
            return com.android.volley.Response.m586a(new ParseError(networkResponse));
        }
        return com.android.volley.Response.m587a(decodeByteArray, HttpHeaderParser.m646a(networkResponse));
    }

    protected void m683a(Bitmap bitmap) {
        this.f591a.m585a(bitmap);
    }

    static int m680a(int i, int i2, int i3, int i4) {
        double min = Math.min(((double) i) / ((double) i3), ((double) i2) / ((double) i4));
        float f = br.DEFAULT_BACKOFF_MULT;
        while (((double) (f * dm.DEFAULT_BACKOFF_MULT)) <= min) {
            f *= dm.DEFAULT_BACKOFF_MULT;
        }
        return (int) f;
    }
}
