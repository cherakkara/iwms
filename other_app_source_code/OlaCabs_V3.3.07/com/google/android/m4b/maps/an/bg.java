package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.ac.JpegUtil;
import com.google.android.m4b.maps.am.MapTileInfo;
import com.google.android.m4b.maps.av.VectorGlobalState;
import com.google.android.m4b.maps.cm.Clock;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* compiled from: ImageTile */
public final class bg implements aa {
    private final ac f3568a;
    private final ai f3569b;
    private final int f3570c;
    private final byte[] f3571d;
    private String[] f3572e;
    private String[] f3573f;
    private List<bp> f3574g;
    private int f3575h;

    public bg(ac acVar, int i, int i2, int i3, byte[] bArr, ai aiVar) {
        this.f3575h = -1;
        this.f3568a = acVar;
        this.f3569b = aiVar;
        this.f3570c = i;
        if (!(bArr == null || bArr.length == 0)) {
            MapTileInfo mapTileInfo = new MapTileInfo();
            bArr = mapTileInfo.m5397a(bArr);
            if (VectorGlobalState.m7285a()) {
                this.f3572e = mapTileInfo.m5398a();
                this.f3573f = mapTileInfo.m5399b();
                this.f3575h = mapTileInfo.m5400c();
            }
            if (aiVar == ai.f3446x) {
                this.f3574g = mapTileInfo.m5401d();
            }
            if (bArr[0] == 67) {
                try {
                    bArr = JpegUtil.m4856a(bArr);
                } catch (UnsupportedOperationException e) {
                    throw new IOException("Input image is not Compact JPEG");
                }
            }
        }
        if (this.f3572e == null) {
            this.f3572e = new String[0];
        }
        if (this.f3573f == null) {
            this.f3573f = new String[0];
        }
        if (this.f3574g == null) {
            this.f3574g = Collections.emptyList();
        }
        this.f3571d = bArr;
    }

    public final String[] m5778f() {
        return this.f3572e;
    }

    public final String[] m5779g() {
        return this.f3573f;
    }

    public final int m5780h() {
        return this.f3575h;
    }

    public final ac m5770a() {
        return this.f3568a;
    }

    public final int m5774c() {
        return this.f3570c;
    }

    public final List<bp> m5781i() {
        return this.f3574g;
    }

    public final byte[] m5782j() {
        return this.f3571d;
    }

    public final ai m5772b() {
        return this.f3569b;
    }

    public final boolean m5771a(Clock clock) {
        return false;
    }

    public final int m5776d() {
        return -1;
    }

    public final boolean m5773b(Clock clock) {
        return false;
    }

    public final void m5775c(Clock clock) {
    }

    public final boolean m5777e() {
        return false;
    }
}
