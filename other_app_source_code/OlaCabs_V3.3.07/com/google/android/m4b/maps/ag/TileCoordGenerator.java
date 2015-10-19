package com.google.android.m4b.maps.ag;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.ax.Camera;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.ag.f */
public interface TileCoordGenerator {
    float m4872a(Point point);

    long m4873a();

    ac m4874a(ac acVar, Point point);

    List<ac> m4875a(int i, Point point);

    List<ac> m4876a(Camera camera);
}
