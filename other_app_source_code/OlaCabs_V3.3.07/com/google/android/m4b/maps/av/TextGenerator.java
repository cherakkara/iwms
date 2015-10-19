package com.google.android.m4b.maps.av;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Typeface;
import com.google.android.m4b.maps.an.TextStyle;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.ay.Texture.Texture;
import com.google.android.m4b.maps.p040u.UserEventReporter;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.dm;

/* renamed from: com.google.android.m4b.maps.av.l */
public final class TextGenerator {
    public static final TextGenerator f4630a;
    public static final TextGenerator f4631b;
    public static final TextGenerator f4632c;
    private final Paint f4633d;
    private final Paint f4634e;
    private final Path f4635f;
    private Texture<TextGenerator> f4636g;
    private final float f4637h;

    /* renamed from: com.google.android.m4b.maps.av.l.a */
    public static class TextGenerator {
        private TextGenerator() {
        }
    }

    /* renamed from: com.google.android.m4b.maps.av.l.b */
    static class TextGenerator {
        private final String f4623a;
        private final TextGenerator f4624b;
        private final TextStyle f4625c;
        private final float f4626d;
        private final int f4627e;
        private final int f4628f;
        private final int f4629g;

        public TextGenerator(String str, TextGenerator textGenerator, TextStyle textStyle, float f, int i, int i2, int i3) {
            this.f4623a = str;
            this.f4624b = textGenerator;
            this.f4625c = textStyle;
            this.f4626d = f;
            this.f4627e = i;
            this.f4628f = i2;
            this.f4629g = i3;
        }

        public final int hashCode() {
            int hashCode = ((this.f4623a.hashCode() + 31) * 31) + this.f4624b.hashCode();
            if (this.f4625c != null) {
                hashCode = (hashCode * 31) + this.f4625c.hashCode();
            }
            return (((((((hashCode * 31) + Float.floatToIntBits(this.f4626d)) * 31) + this.f4627e) * 31) + this.f4628f) * 31) + this.f4629g;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof TextGenerator)) {
                return false;
            }
            TextGenerator textGenerator = (TextGenerator) obj;
            if (textGenerator.f4626d == this.f4626d && textGenerator.f4627e == this.f4627e && textGenerator.f4628f == this.f4628f && textGenerator.f4629g == this.f4629g && textGenerator.f4624b == this.f4624b && ((textGenerator.f4625c == this.f4625c || (textGenerator.f4625c != null && textGenerator.f4625c.equals(this.f4625c))) && textGenerator.f4623a.equals(this.f4623a))) {
                return true;
            }
            return false;
        }
    }

    static {
        f4630a = new TextGenerator();
        f4631b = new TextGenerator();
        f4632c = new TextGenerator();
    }

    public TextGenerator(float f) {
        this.f4633d = new Paint();
        this.f4633d.setAntiAlias(true);
        this.f4633d.setStyle(Style.FILL);
        this.f4634e = new Paint();
        this.f4634e.setAntiAlias(true);
        this.f4634e.setStyle(Style.STROKE);
        this.f4635f = new Path();
        this.f4636g = new Texture(64);
        this.f4637h = 2.1f * f;
    }

    public final void m7266a(int i) {
        if (i != this.f4636g.m6234b()) {
            this.f4636g.m6231a();
            this.f4636g = new Texture(i);
        }
    }

    public final float m7262a(String str, TextGenerator textGenerator, TextStyle textStyle, float f) {
        m7261a(textGenerator, textStyle);
        this.f4633d.setTextSize(f);
        return this.f4633d.measureText(str);
    }

    public final float[] m7268a(String str, TextGenerator textGenerator, TextStyle textStyle, float f, boolean z, float f2) {
        m7261a(textGenerator, textStyle);
        this.f4633d.setTextSize(f);
        float measureText = this.f4633d.measureText(str);
        FontMetrics fontMetrics = this.f4633d.getFontMetrics();
        float ceil = (float) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
        float f3 = fontMetrics.ascent - fontMetrics.top;
        float f4 = fontMetrics.bottom - fontMetrics.descent;
        float f5 = (f2 - br.DEFAULT_BACKOFF_MULT) * ceil;
        if (z && measureText > 0.0f) {
            measureText += this.f4637h * dm.DEFAULT_BACKOFF_MULT;
            f3 += this.f4637h;
            f4 += this.f4637h;
        }
        ceil += f3 + f4;
        f3 -= f5 / dm.DEFAULT_BACKOFF_MULT;
        f4 -= f5 / dm.DEFAULT_BACKOFF_MULT;
        return new float[]{measureText, ceil, f3, f4};
    }

    public final float[] m7267a(String str, TextGenerator textGenerator, TextStyle textStyle, float f, boolean z) {
        float[] fArr = new float[(str.length() + 1)];
        if (fArr.length == 0) {
            return fArr;
        }
        m7261a(textGenerator, textStyle);
        this.f4633d.setTextSize(f);
        this.f4633d.getTextWidths(str, fArr);
        float f2 = this.f4637h;
        int i = 0;
        while (i < fArr.length) {
            float f3 = fArr[i] + f2;
            fArr[i] = f2;
            i++;
            f2 = f3;
        }
        fArr[0] = fArr[0] - this.f4637h;
        i = fArr.length - 1;
        fArr[i] = fArr[i] + this.f4637h;
        return fArr;
    }

    public final com.google.android.m4b.maps.ay.Texture m7263a(GLState gLState, String str, TextGenerator textGenerator, TextStyle textStyle, float f, int i, int i2, int i3) {
        TextGenerator textGenerator2 = new TextGenerator(str, textGenerator, textStyle, f, i, i2, i3);
        com.google.android.m4b.maps.ay.Texture texture = (com.google.android.m4b.maps.ay.Texture) this.f4636g.m6235b((Object) textGenerator2);
        if (texture == null) {
            int I;
            boolean z = (i2 == 0 && i3 == 0) ? false : true;
            m7261a(textGenerator, textStyle);
            float f2 = (float) ((int) (1.5f * f));
            this.f4633d.setTextSize(f2);
            float f3 = z ? this.f4637h : 0.0f;
            float[] a = m7268a(str, textGenerator, textStyle, f2, z, br.DEFAULT_BACKOFF_MULT);
            int ceil = ((int) Math.ceil((double) (a[0] * 1.016f))) + 1;
            int ceil2 = (int) Math.ceil((double) a[1]);
            int a2 = com.google.android.m4b.maps.ay.Texture.m7603a(ceil, 8);
            int a3 = com.google.android.m4b.maps.ay.Texture.m7603a(ceil2, 8);
            if (a2 > gLState.m7511I() || a3 > gLState.m7511I()) {
                UserEventReporter.m11506a("TextGenerator texture too large", a2 + ", " + a3 + " because of string " + str + " with size " + f2);
                I = gLState.m7511I();
                a2 = gLState.m7511I();
            } else {
                I = a2;
                a2 = a3;
            }
            Config config = (i == -16777216 || i == -1) ? Config.ARGB_4444 : Config.ARGB_8888;
            Bitmap a4 = gLState.m7529l().m7591a(I, a2, config);
            a4.eraseColor(i3);
            Canvas canvas = new Canvas();
            canvas.setBitmap(a4);
            a2 = (int) Math.ceil((double) ((-this.f4633d.getFontMetrics().top) + f3));
            this.f4634e.setColor(i2);
            this.f4634e.setStrokeWidth(dm.DEFAULT_BACKOFF_MULT * f3);
            this.f4633d.setColor(i);
            Object obj = (i2 == 0 || f3 <= 0.0f) ? null : 1;
            Object obj2 = i != 0 ? 1 : null;
            this.f4633d.getTextPath(str, 0, str.length(), (float) ((int) ((float) Math.ceil((double) f3))), (float) a2, this.f4635f);
            if (obj != null) {
                canvas.drawPath(this.f4635f, this.f4634e);
            }
            if (obj2 != null) {
                canvas.drawPath(this.f4635f, this.f4633d);
            }
            texture = new com.google.android.m4b.maps.ay.Texture(gLState, false);
            texture.m7621c(true);
            texture.m7612a(a4, ceil, ceil2);
            a4.recycle();
            this.f4636g.m6239c(textGenerator2, texture);
        }
        texture.m7625e();
        return texture;
    }

    public final com.google.android.m4b.maps.ay.Texture m7264a(String str, TextGenerator textGenerator, TextStyle textStyle, float f, int i, int i2, int i3) {
        com.google.android.m4b.maps.ay.Texture texture = (com.google.android.m4b.maps.ay.Texture) this.f4636g.m6235b((Object) new TextGenerator(str, textGenerator, textStyle, f, i, i2, i3));
        if (texture != null) {
            texture.m7625e();
        }
        return texture;
    }

    public final void m7265a() {
        this.f4636g.m6231a();
    }

    public final void m7269b() {
        this.f4636g.m6232a(Math.max(this.f4636g.m6234b() / 2, 8));
    }

    private void m7261a(TextGenerator textGenerator, TextStyle textStyle) {
        int i = 1;
        int i2 = 0;
        if (textGenerator == f4632c) {
            i2 = 1;
        }
        if (textStyle != null) {
            if (!textStyle.m6122b()) {
                i = i2;
            }
            if (textStyle.m6123c()) {
                i |= 2;
            }
        } else {
            i = i2;
        }
        this.f4633d.setTypeface(Typeface.defaultFromStyle(i));
    }
}
