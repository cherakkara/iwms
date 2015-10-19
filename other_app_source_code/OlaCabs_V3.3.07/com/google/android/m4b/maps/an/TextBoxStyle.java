package com.google.android.m4b.maps.an;

import java.io.DataInput;

/* renamed from: com.google.android.m4b.maps.an.y */
public final class TextBoxStyle {
    private static TextBoxStyle f3718c;
    private final int f3719a;
    private final Stroke f3720b;

    static {
        f3718c = new TextBoxStyle(0, Stroke.m6081a());
    }

    public TextBoxStyle(int i, Stroke stroke) {
        this.f3719a = i;
        this.f3720b = stroke;
    }

    public static TextBoxStyle m6118a(DataInput dataInput, int i) {
        return new TextBoxStyle(dataInput.readInt(), Stroke.m6082a(dataInput));
    }

    public static TextBoxStyle m6117a() {
        return f3718c;
    }

    public final int m6119b() {
        return this.f3719a;
    }

    public final int hashCode() {
        return (this.f3720b == null ? 0 : this.f3720b.hashCode()) + ((this.f3719a + 31) * 31);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TextBoxStyle textBoxStyle = (TextBoxStyle) obj;
        if (this.f3719a != textBoxStyle.f3719a) {
            return false;
        }
        if (this.f3720b == null) {
            if (textBoxStyle.f3720b != null) {
                return false;
            }
            return true;
        } else if (this.f3720b.equals(textBoxStyle.f3720b)) {
            return true;
        } else {
            return false;
        }
    }
}
