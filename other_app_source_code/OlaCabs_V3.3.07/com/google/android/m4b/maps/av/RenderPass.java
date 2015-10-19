package com.google.android.m4b.maps.av;

import com.google.android.m4b.maps.p057t.FeatureId;
import com.google.p025a.p026a.Objects;
import com.google.p025a.p028c.au;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/* renamed from: com.google.android.m4b.maps.av.h */
public final class RenderPass implements Comparable<RenderPass> {
    private final al f4554a;
    private final RenderPass f4555b;
    private final Map<FeatureId, RenderTweak> f4556c;
    private final Map<FeatureId, RenderTweak> f4557d;
    private boolean f4558e;
    private boolean f4559f;

    /* renamed from: com.google.android.m4b.maps.av.h.a */
    public enum RenderPass {
        BASE,
        DROP_SHADOWS_OUTER,
        ELEVATED_COLOR,
        UNDERGROUND_MODE_MASK,
        UNDERGROUND_STENCIL,
        UNDERGROUND_COLOR,
        DROP_SHADOWS_INNER,
        ANIMATED_ELEVATED_COLOR,
        DEFAULT
    }

    public final /* synthetic */ int compareTo(Object obj) {
        RenderPass renderPass = (RenderPass) obj;
        int compareTo = this.f4555b.compareTo(renderPass.f4555b);
        if (compareTo != 0) {
            return compareTo;
        }
        al alVar = this.f4554a;
        al alVar2 = renderPass.f4554a;
        if (!(alVar == null || alVar2 == null)) {
            compareTo = alVar.m6680d().ordinal() - alVar2.m6680d().ordinal();
        }
        return (compareTo != 0 || this.f4556c.isEmpty() || renderPass.f4556c.isEmpty()) ? compareTo : ((RenderTweak) Collections.max(this.f4556c.values())).compareTo((RenderTweak) Collections.max(renderPass.f4556c.values()));
    }

    public RenderPass(al alVar, RenderPass renderPass, RenderTweak... renderTweakArr) {
        this.f4556c = au.m2807a();
        this.f4557d = au.m2807a();
        this.f4558e = false;
        this.f4559f = false;
        this.f4554a = alVar;
        this.f4555b = renderPass;
        for (RenderTweak renderTweak : renderTweakArr) {
            for (FeatureId put : renderTweak.m5293a()) {
                this.f4556c.put(put, renderTweak);
            }
        }
    }

    public RenderPass(al alVar, RenderPass renderPass, Collection<RenderTweak> collection, Collection<RenderTweak> collection2) {
        this.f4556c = au.m2807a();
        this.f4557d = au.m2807a();
        this.f4558e = false;
        this.f4559f = false;
        this.f4554a = alVar;
        this.f4555b = renderPass;
        for (RenderTweak renderTweak : collection) {
            for (FeatureId put : renderTweak.m5293a()) {
                this.f4556c.put(put, renderTweak);
            }
        }
        for (RenderTweak renderTweak2 : collection2) {
            for (FeatureId put2 : renderTweak2.m5293a()) {
                this.f4557d.put(put2, renderTweak2);
            }
        }
    }

    public final al m7206a() {
        return this.f4554a;
    }

    public final RenderPass m7209b() {
        return this.f4555b;
    }

    public final boolean m7212c() {
        return this.f4555b == RenderPass.BASE || this.f4555b == RenderPass.ELEVATED_COLOR || this.f4555b == RenderPass.ANIMATED_ELEVATED_COLOR || this.f4555b == RenderPass.UNDERGROUND_COLOR || this.f4555b == RenderPass.DEFAULT;
    }

    public final boolean m7213d() {
        return this.f4555b == RenderPass.DROP_SHADOWS_INNER || this.f4555b == RenderPass.DROP_SHADOWS_OUTER;
    }

    public final boolean m7214e() {
        return this.f4555b == RenderPass.UNDERGROUND_STENCIL;
    }

    public final RenderTweak m7207a(FeatureId featureId) {
        return (RenderTweak) this.f4556c.get(featureId);
    }

    public final RenderTweak m7210b(FeatureId featureId) {
        return (RenderTweak) this.f4557d.get(featureId);
    }

    public final void m7208a(boolean z) {
        this.f4558e = z;
    }

    public final void m7211b(boolean z) {
        this.f4559f = z;
    }

    public final boolean m7215f() {
        return this.f4558e;
    }

    public final boolean m7216g() {
        return this.f4559f;
    }

    public final String toString() {
        return Objects.m1809a((Object) this).m1806a("overlay", this.f4554a).m1806a("order", this.f4555b).m1807a("isFirstPassForOverlay", this.f4558e).m1807a("isLastPassForOverlay", this.f4559f).m1806a("overlayRenderTweaks", this.f4556c).m1806a("featureRenderTweaks", this.f4557d).toString();
    }
}
