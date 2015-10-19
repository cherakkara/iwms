package com.google.android.m4b.maps.ba;

import com.google.android.m4b.maps.av.RankMergingFeatureIterator;
import com.google.android.m4b.maps.av.ac;
import com.google.android.m4b.maps.av.ad;
import com.google.android.m4b.maps.ax.Camera;
import com.google.android.m4b.maps.ay.GLState;
import com.google.android.m4b.maps.bc.LabelSource;
import com.google.android.m4b.maps.cm.Clock;
import java.util.Collection;

/* renamed from: com.google.android.m4b.maps.ba.q */
public interface GLTile extends GLDrawable {
    int m7836a(Camera camera, ac acVar);

    void m7837a(int i, Collection<String> collection);

    void m7838a(long j);

    void m7839a(LabelSource labelSource);

    void m7840a(Collection<String> collection);

    void m7841a(boolean z);

    boolean m7842a();

    boolean m7843a(RankMergingFeatureIterator rankMergingFeatureIterator);

    boolean m7844a(Clock clock);

    com.google.android.m4b.maps.an.ac m7845b();

    void m7846b(GLState gLState, Camera camera, ad adVar);

    boolean m7847b(Clock clock);

    boolean m7848c();

    void m7849d();

    int m7850e();

    int m7851f();

    int m7852g();
}
