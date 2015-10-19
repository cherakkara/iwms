package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p028c.bk;
import com.newrelic.agent.android.instrumentation.Trace;
import java.util.Arrays;
import java.util.Set;

/* compiled from: TransitParameters */
public final class ak implements af {
    private final FeatureId f3470a;
    private final Integer[] f3471b;

    /* renamed from: com.google.android.m4b.maps.an.ak.a */
    public static class TransitParameters {
        private static final Integer[] f3467a;
        private FeatureId f3468b;
        private Set<Integer> f3469c;

        public TransitParameters() {
            this.f3469c = bk.m2870a();
        }

        static {
            f3467a = new Integer[0];
        }

        public final TransitParameters m5563a(FeatureId featureId) {
            this.f3468b = featureId;
            return this;
        }

        public final TransitParameters m5562a(int i) {
            this.f3469c.add(Integer.valueOf(i));
            return this;
        }

        public final ak m5564a() {
            if (this.f3469c.contains(Integer.valueOf(-1))) {
                this.f3469c.clear();
            }
            return new ak(this.f3468b, (Integer[]) this.f3469c.toArray(f3467a));
        }
    }

    public final /* synthetic */ int compareTo(Object obj) {
        af afVar = (af) obj;
        return afVar == null ? 1 : toString().compareTo(afVar.toString());
    }

    protected ak(FeatureId featureId, Integer[] numArr) {
        this.f3470a = featureId;
        Arrays.sort(numArr);
        this.f3471b = numArr;
    }

    public final TileParameters m5565a() {
        return TileParameters.TRANSIT;
    }

    public final boolean m5567a(af afVar) {
        return equals(afVar);
    }

    public final boolean m5568a(ai aiVar) {
        return aiVar == ai.f3435m && !(this.f3470a == null && this.f3471b.length == 0);
    }

    public final void m5566a(ProtoBuf protoBuf) {
        if (this.f3470a != null) {
            protoBuf.m10197b(9, this.f3470a.m11294a());
        }
        for (Integer intValue : this.f3471b) {
            protoBuf.m10189a(12, intValue.intValue());
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f3470a == null ? Trace.NULL : this.f3470a.toString());
        stringBuilder.append("|");
        for (Integer intValue : this.f3471b) {
            stringBuilder.append(intValue.intValue());
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    public final int hashCode() {
        int hashCode = (this.f3470a == null ? 0 : this.f3470a.hashCode()) + 31;
        if (this.f3471b.length > 0) {
            return (hashCode * 31) + Arrays.hashCode(this.f3471b);
        }
        return hashCode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            if (this.f3470a == null && this.f3471b.length == 0) {
                return true;
            }
            return false;
        } else if (!(obj instanceof ak)) {
            return false;
        } else {
            ak akVar = (ak) obj;
            if (Objects.m1811a(this.f3470a, akVar.f3470a) && Arrays.equals(this.f3471b, akVar.f3471b)) {
                return true;
            }
            return false;
        }
    }
}
