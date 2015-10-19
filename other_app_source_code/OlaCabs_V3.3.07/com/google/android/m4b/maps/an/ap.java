package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.p057t.FeatureId;
import java.io.DataInput;

/* compiled from: VectorModifier */
public class ap {
    protected final FeatureId f3476a;
    protected final bc f3477b;
    protected final int f3478c;
    protected final int f3479d;

    /* renamed from: com.google.android.m4b.maps.an.ap.a */
    public static class VectorModifier extends ap {
        public VectorModifier(bc bcVar, int i, int i2) {
            super(null, bcVar, i | 1, i2);
        }

        public final bc m5595a() {
            return this.b;
        }

        public final boolean m5596b() {
            return ModelUtil.m5887a(this.c, 2);
        }

        public final int m5597c() {
            return this.d;
        }

        public final boolean m5598d() {
            return ModelUtil.m5887a(this.c, 4);
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ap.b */
    public static class VectorModifier extends ap {
        public VectorModifier(int i, bc bcVar) {
            super(null, bcVar, 0, 0);
        }

        public final bc m5599a() {
            return this.b;
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ap.c */
    public static class VectorModifier extends ap {
        public VectorModifier(FeatureId featureId) {
            super(featureId, null, featureId instanceof FeatureId.FeatureId ? 2 : 0, -1);
        }

        public final FeatureId m5600a() {
            return this.a;
        }
    }

    /* renamed from: com.google.android.m4b.maps.an.ap.d */
    public static class VectorModifier extends ap {
        public VectorModifier(int i) {
            super(null, null, 0, 0);
        }
    }

    protected ap(FeatureId featureId, bc bcVar, int i, int i2) {
        this.f3476a = featureId;
        this.f3477b = bcVar;
        this.f3478c = i;
        this.f3479d = i2;
    }

    public static ap m5593a(DataInput dataInput, ae aeVar) {
        int readUnsignedByte = dataInput.readUnsignedByte();
        if (aeVar.m5449a() == 11) {
            int a = an.m5578a(dataInput);
            if (ModelUtil.m5887a(readUnsignedByte, 1)) {
                return new VectorModifier(a, aq.m5623a(dataInput, aeVar));
            }
            return new VectorModifier(a);
        }
        int i = -1;
        if (ModelUtil.m5887a(readUnsignedByte, 1)) {
            bc a2 = aq.m5623a(dataInput, aeVar);
            if (ModelUtil.m5887a(readUnsignedByte, 2)) {
                i = an.m5578a(dataInput);
            }
            return new VectorModifier(a2, readUnsignedByte, i);
        }
        FeatureId b;
        if (ModelUtil.m5887a(readUnsignedByte, 2)) {
            b = FeatureId.m11293b(dataInput);
        } else {
            b = FeatureId.m11290a(dataInput);
        }
        return new VectorModifier(b);
    }
}
