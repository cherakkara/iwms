package com.google.android.m4b.maps.ab;

import com.newrelic.agent.android.analytics.AnalyticAttribute;

/* renamed from: com.google.android.m4b.maps.ab.b */
public final class ByteChunkArray extends BaseChunkArray<byte[]> {
    private static final ChunkArrayManager<byte[]> f3046e;

    /* renamed from: com.google.android.m4b.maps.ab.b.1 */
    static class ByteChunkArray extends ChunkArrayManager<byte[]> {
        ByteChunkArray(int i, String str) {
            super(100, str);
        }

        protected final /* bridge */ /* synthetic */ Object m4837a() {
            return new byte[4106];
        }
    }

    /* renamed from: com.google.android.m4b.maps.ab.b.a */
    public interface ByteChunkArray {
        void m4838a(byte[] bArr, int i);
    }

    static {
        f3046e = new ByteChunkArray(100, "ByteChunkArrayManager");
    }

    public ByteChunkArray(int i) {
        super(i, 12, f3046e);
    }

    public final void m4839a(ByteChunkArray byteChunkArray) {
        for (int i = 0; i < this.b; i++) {
            byteChunkArray.m4838a((byte[]) this.a.get(i), AnalyticAttribute.ATTRIBUTE_VALUE_MAX_LENGTH);
        }
        if (this.b != this.a.size()) {
            byteChunkArray.m4838a((byte[]) this.c, this.d);
        }
    }
}
