package com.google.android.m4b.maps.ab;

import com.newrelic.agent.android.api.v1.Defaults;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/* renamed from: com.google.android.m4b.maps.ab.d */
public final class IntChunkArray extends BaseChunkArray<int[]> {
    private static final ChunkArrayManager<int[]> f3047e;

    /* renamed from: com.google.android.m4b.maps.ab.d.1 */
    static class IntChunkArray extends ChunkArrayManager<int[]> {
        IntChunkArray(int i, String str) {
            super(100, str);
        }

        protected final /* bridge */ /* synthetic */ Object m4840a() {
            return new int[1034];
        }
    }

    static {
        f3047e = new IntChunkArray(100, "IntChunkArrayManager");
    }

    public IntChunkArray(int i) {
        super(i, 10, f3047e);
    }

    public final void m4842a(IntBuffer intBuffer) {
        for (int i = 0; i < this.b; i++) {
            intBuffer.put((int[]) this.a.get(i), 0, Defaults.RESPONSE_BODY_LIMIT);
        }
        if (this.b != this.a.size()) {
            intBuffer.put((int[]) this.c, 0, this.d);
        }
    }

    public final void m4843a(ShortBuffer shortBuffer, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.b; i3++) {
            int[] iArr = (int[]) this.a.get(i3);
            for (int i4 = 0; i4 < Defaults.RESPONSE_BODY_LIMIT; i4++) {
                shortBuffer.put((short) (iArr[i4] / i));
            }
        }
        if (this.b != this.a.size()) {
            while (i2 < this.d) {
                shortBuffer.put((short) (((int[]) this.c)[i2] / i));
                i2++;
            }
        }
    }

    public final void m4841a(ByteBuffer byteBuffer, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.b; i3++) {
            int[] iArr = (int[]) this.a.get(i3);
            for (int i4 = 0; i4 < Defaults.RESPONSE_BODY_LIMIT; i4++) {
                byteBuffer.put((byte) (iArr[i4] / i));
            }
        }
        if (this.b != this.a.size()) {
            while (i2 < this.d) {
                byteBuffer.put((byte) (((int[]) this.c)[i2] / i));
                i2++;
            }
        }
    }
}
