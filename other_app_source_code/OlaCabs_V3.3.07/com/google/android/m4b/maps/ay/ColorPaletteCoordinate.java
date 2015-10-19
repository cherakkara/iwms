package com.google.android.m4b.maps.ay;

import com.google.android.m4b.maps.ay.ColorPalette.ColorPalette;
import com.google.android.m4b.maps.az.TexCoordBuffer;
import com.google.android.m4b.maps.az.TexCoordVBO;

/* renamed from: com.google.android.m4b.maps.ay.b */
public final class ColorPaletteCoordinate {
    private Texture f4822a;
    private final TexCoordBuffer f4823b;
    private final ColorPalette f4824c;
    private final ColorPalette f4825d;

    public ColorPaletteCoordinate(int i, ColorPalette colorPalette) {
        this.f4823b = TexCoordVBO.m7709b(i, 1);
        this.f4824c = new ColorPalette();
        this.f4825d = colorPalette;
    }

    public final void m7477a(int i, int i2) {
        if (i2 > 0) {
            this.f4825d.m7473a(i, this.f4824c);
            this.f4823b.m7694a(this.f4824c.f4816a, this.f4824c.f4817b, i2);
        }
    }

    public final void m7478a(GLState gLState) {
        if (!(this.f4822a == null || this.f4822a.m7609a() == gLState.f4847a)) {
            m7475c();
        }
        this.f4825d.m7474b(gLState);
        if (this.f4822a == null) {
            this.f4822a = this.f4825d.m7471a(gLState);
            this.f4822a.m7625e();
        }
        this.f4822a.m7613a(gLState.f4847a);
        this.f4823b.m7706d(gLState);
    }

    public final void m7480b(GLState gLState) {
        this.f4823b.m7702b(gLState);
        m7475c();
    }

    public final void m7481c(GLState gLState) {
        this.f4823b.m7705c(gLState);
        m7475c();
    }

    public final int m7476a() {
        return this.f4823b.m7701b();
    }

    public final int m7479b() {
        return this.f4823b.m7703c() + 24;
    }

    private void m7475c() {
        if (this.f4822a != null) {
            this.f4822a.m7626f();
            this.f4822a = null;
        }
    }
}
