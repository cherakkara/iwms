package com.google.android.m4b.maps.bj;

import com.google.android.m4b.maps.bx.Geometry;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import java.util.Formatter;
import java.util.regex.Pattern;

/* renamed from: com.google.android.m4b.maps.bj.x */
public final class MapPoint {
    private final double f6665a;
    private final double f6666b;
    private final int f6667c;

    static {
        Pattern.compile(",");
    }

    public MapPoint(double d, double d2) {
        this.f6665a = d;
        this.f6666b = d2;
        long doubleToRawLongBits = ((17 + Double.doubleToRawLongBits(d)) * 37) + Double.doubleToRawLongBits(d2);
        this.f6667c = (int) (doubleToRawLongBits ^ (doubleToRawLongBits >>> 32));
    }

    public final ProtoBuf m10028a() {
        ProtoBuf protoBuf = new ProtoBuf(Geometry.f7064a);
        protoBuf.m10210f(1, (int) Math.round(this.f6665a * 1000000.0d));
        protoBuf.m10210f(2, (int) Math.round(this.f6666b * 1000000.0d));
        return protoBuf;
    }

    public final int hashCode() {
        return this.f6667c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MapPoint mapPoint = (MapPoint) obj;
        if (Double.doubleToRawLongBits(this.f6665a) == Double.doubleToRawLongBits(mapPoint.f6665a) && Double.doubleToRawLongBits(this.f6666b) == Double.doubleToRawLongBits(mapPoint.f6666b)) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return new Formatter(null).format("%f,%f", new Object[]{Double.valueOf(this.f6665a), Double.valueOf(this.f6666b)}).toString();
    }
}
