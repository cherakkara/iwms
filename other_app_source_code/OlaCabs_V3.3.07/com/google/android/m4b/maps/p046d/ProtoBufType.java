package com.google.android.m4b.maps.p046d;

import android.support.v4.view.MotionEventCompat;
import com.google.android.m4b.maps.p054p.IntMap;

/* renamed from: com.google.android.m4b.maps.d.b */
public final class ProtoBufType {
    private static final ProtoBufType[] f7307b;
    private final IntMap f7308a;
    private final String f7309c;

    /* renamed from: com.google.android.m4b.maps.d.b.a */
    static class ProtoBufType {
        private int f7305a;
        private Object f7306b;

        ProtoBufType(int i, Object obj) {
            this.f7305a = i;
            this.f7306b = obj;
        }

        public final int hashCode() {
            return this.f7305a;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof ProtoBufType)) {
                return false;
            }
            ProtoBufType protoBufType = (ProtoBufType) obj;
            if (this.f7305a == protoBufType.f7305a) {
                if (this.f7306b == protoBufType.f7306b) {
                    return true;
                }
                if (this.f7306b != null && this.f7306b.equals(protoBufType.f7306b)) {
                    return true;
                }
            }
            return false;
        }

        public final String toString() {
            return "TypeInfo{type=" + this.f7305a + ", data=" + this.f7306b + "}";
        }
    }

    static {
        f7307b = new ProtoBufType[168];
        int i = 0;
        for (int i2 = 0; i2 <= 7; i2++) {
            int i3 = 16;
            while (i3 < 37) {
                int i4 = i + 1;
                f7307b[i] = new ProtoBufType((i2 << 8) + i3, null);
                i3++;
                i = i4;
            }
        }
    }

    public ProtoBufType() {
        this.f7308a = new IntMap();
        this.f7309c = null;
    }

    public final ProtoBufType m10219a(int i, int i2, Object obj) {
        Object obj2;
        IntMap intMap = this.f7308a;
        if (obj == null) {
            obj2 = f7307b[(((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8) * 21) + ((i & MotionEventCompat.ACTION_MASK) - 16)];
        } else {
            obj2 = new ProtoBufType(i, obj);
        }
        intMap.m11073a(i2, obj2);
        return this;
    }

    public final int m10218a(int i) {
        ProtoBufType protoBufType = (ProtoBufType) this.f7308a.m11072a(i);
        return protoBufType == null ? 16 : protoBufType.f7305a & MotionEventCompat.ACTION_MASK;
    }

    public final Object m10220b(int i) {
        ProtoBufType protoBufType = (ProtoBufType) this.f7308a.m11072a(i);
        return protoBufType == null ? protoBufType : protoBufType.f7306b;
    }

    public final String toString() {
        return "ProtoBufType Name: " + null;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.f7308a.equals(((ProtoBufType) obj).f7308a);
    }

    public final int hashCode() {
        IntMap intMap = this.f7308a;
        return this.f7308a.hashCode();
    }
}
