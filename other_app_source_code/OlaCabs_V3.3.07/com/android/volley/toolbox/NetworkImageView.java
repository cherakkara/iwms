package com.android.volley.toolbox;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader.ImageLoader;

public class NetworkImageView extends ImageView {
    private String f538a;
    private int f539b;
    private int f540c;
    private ImageLoader f541d;
    private ImageLoader f542e;

    /* renamed from: com.android.volley.toolbox.NetworkImageView.1 */
    class C01131 implements ImageLoader {
        final /* synthetic */ boolean f536a;
        final /* synthetic */ NetworkImageView f537b;

        /* renamed from: com.android.volley.toolbox.NetworkImageView.1.1 */
        class C01121 implements Runnable {
            final /* synthetic */ ImageLoader f534a;
            final /* synthetic */ C01131 f535b;

            C01121(C01131 c01131, ImageLoader imageLoader) {
                this.f535b = c01131;
                this.f534a = imageLoader;
            }

            public void run() {
                this.f535b.m600a(this.f534a, false);
            }
        }

        C01131(NetworkImageView networkImageView, boolean z) {
            this.f537b = networkImageView;
            this.f536a = z;
        }

        public void m599a(VolleyError volleyError) {
            if (this.f537b.f540c != 0) {
                this.f537b.setImageResource(this.f537b.f540c);
            }
        }

        public void m600a(ImageLoader imageLoader, boolean z) {
            if (z && this.f536a) {
                this.f537b.post(new C01121(this, imageLoader));
            } else if (imageLoader.m668b() != null) {
                this.f537b.setImageBitmap(imageLoader.m668b());
            } else if (this.f537b.f539b != 0) {
                this.f537b.setImageResource(this.f537b.f539b);
            }
        }
    }

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void m604a(String str, ImageLoader imageLoader) {
        this.f538a = str;
        this.f541d = imageLoader;
        m605a(false);
    }

    public String getImageURL() {
        return this.f538a;
    }

    public void setDefaultImageResId(int i) {
        this.f539b = i;
    }

    public void setErrorImageResId(int i) {
        this.f540c = i;
    }

    void m605a(boolean z) {
        int i;
        int i2;
        int i3 = 1;
        int i4 = 0;
        int width = getWidth();
        int height = getHeight();
        if (getLayoutParams() != null) {
            i = getLayoutParams().width == -2 ? 1 : 0;
            if (getLayoutParams().height == -2) {
                i2 = 1;
            } else {
                i2 = 0;
            }
        } else {
            i2 = 0;
            i = 0;
        }
        if (i == 0 || i2 == 0) {
            i3 = 0;
        }
        if (width != 0 || height != 0 || r1 != 0) {
            if (TextUtils.isEmpty(this.f538a)) {
                if (this.f542e != null) {
                    this.f542e.m667a();
                    this.f542e = null;
                }
                m602a();
                return;
            }
            if (!(this.f542e == null || this.f542e.m669c() == null)) {
                if (!this.f542e.m669c().equals(this.f538a)) {
                    this.f542e.m667a();
                    m602a();
                } else {
                    return;
                }
            }
            i = i != 0 ? 0 : width;
            if (i2 == 0) {
                i4 = height;
            }
            this.f542e = this.f541d.m677a(this.f538a, new C01131(this, z), i, i4);
        }
    }

    private void m602a() {
        if (this.f539b != 0) {
            setImageResource(this.f539b);
        } else {
            setImageBitmap(null);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        m605a(true);
    }

    protected void onDetachedFromWindow() {
        if (this.f542e != null) {
            this.f542e.m667a();
            setImageBitmap(null);
            this.f542e = null;
        }
        super.onDetachedFromWindow();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
