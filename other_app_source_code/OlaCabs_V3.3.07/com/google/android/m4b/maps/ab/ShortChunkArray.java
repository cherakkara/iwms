package com.google.android.m4b.maps.ab;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.nio.ShortBuffer;

/* renamed from: com.google.android.m4b.maps.ab.e */
public final class ShortChunkArray extends BaseChunkArray<short[]> {
    private static final ChunkArrayManager<short[]> f3048e;

    /* renamed from: com.google.android.m4b.maps.ab.e.1 */
    static class ShortChunkArray extends ChunkArrayManager<short[]> {
        ShortChunkArray(int i, String str) {
            super(100, str);
        }

        protected final /* bridge */ /* synthetic */ Object m4844a() {
            return new short[2058];
        }
    }

    static {
        f3048e = new ShortChunkArray(100, "ShortChunkArrayManager");
    }

    public ShortChunkArray(int i) {
        super(i, 11, f3048e);
    }

    public final void m4845a(ShortBuffer shortBuffer) {
        for (int i = 0; i < this.b; i++) {
            shortBuffer.put((short[]) this.a.get(i), 0, AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT);
        }
        if (this.b != this.a.size()) {
            shortBuffer.put((short[]) this.c, 0, this.d);
        }
    }
}
