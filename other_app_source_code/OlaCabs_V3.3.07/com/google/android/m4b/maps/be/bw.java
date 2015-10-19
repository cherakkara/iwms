package com.google.android.m4b.maps.be;

import com.google.android.m4b.maps.model.LatLng;
import com.google.p025a.p028c.ar;
import java.util.LinkedList;
import java.util.List;

/* compiled from: GeometryUtil */
public final class bw {
    public static double m9062a(double d, double d2) {
        double abs = Math.abs(d - d2);
        return Math.min(abs, 360.0d - abs);
    }

    public static List<LatLng> m9065a(List<LatLng> list) {
        List<LatLng> a = ar.m2515a();
        LinkedList b = ar.m2525b((Iterable) list);
        if (b.isEmpty()) {
            return a;
        }
        LatLng latLng = (LatLng) b.removeFirst();
        while (!b.isEmpty()) {
            LatLng latLng2 = (LatLng) b.getFirst();
            if (Math.max(Math.abs(latLng.f7554a - latLng2.f7554a), m9062a(latLng.f7555b, latLng2.f7555b)) < 4.0d) {
                a.add(latLng);
                latLng = (LatLng) b.removeFirst();
            } else {
                Object latLng3;
                Object obj = (latLng.f7554a == (-latLng2.f7554a) && Math.abs(latLng.f7555b - latLng2.f7555b) == 180.0d) ? 1 : null;
                if (obj != null) {
                    latLng3 = new LatLng(0.0d, (latLng.f7555b + latLng2.f7555b) / 2.0d);
                } else {
                    ao a2 = ao.m8578a(latLng);
                    ao a3 = ao.m8578a(latLng2);
                    ao aoVar = new ao((a2.f5659a + a3.f5659a) / 2.0d, (a2.f5660b + a3.f5660b) / 2.0d, (a2.f5661c + a3.f5661c) / 2.0d);
                    if (aoVar.f5659a == 0.0d && aoVar.f5660b == 0.0d && aoVar.f5661c == 0.0d) {
                        throw new ArithmeticException();
                    }
                    double atan2 = Math.atan2(aoVar.f5661c, Math.sqrt((aoVar.f5659a * aoVar.f5659a) + (aoVar.f5660b * aoVar.f5660b)));
                    double atan22 = (aoVar.f5660b == 0.0d && aoVar.f5659a == 0.0d) ? 0.0d : Math.atan2(aoVar.f5660b, aoVar.f5659a);
                    latLng2 = new LatLng(Math.toDegrees(atan2), Math.toDegrees(atan22));
                }
                b.addFirst(latLng3);
            }
        }
        a.add(latLng);
        return a;
    }

    public static double m9063a(LatLng latLng, double d) {
        return Math.toDegrees(d / (Math.cos(Math.toRadians(latLng.f7554a)) * 6371009.0d));
    }

    public static double m9061a(double d) {
        return Math.toDegrees(d / 6371009.0d);
    }

    public static List<LatLng> m9064a(LatLng latLng, double d, int i) {
        List<LatLng> a = ar.m2515a();
        double toRadians = Math.toRadians(latLng.f7554a);
        double toRadians2 = Math.toRadians(latLng.f7555b);
        double d2 = d / 6371009.0d;
        double cos = Math.cos(d2);
        d2 = Math.sin(d2);
        double cos2 = Math.cos(toRadians);
        toRadians = Math.sin(toRadians);
        for (int i2 = 0; i2 < 100; i2++) {
            double d3 = (6.283185307179586d * ((double) i2)) / 99.0d;
            double cos3 = Math.cos(d3);
            d3 = Math.sin(d3);
            cos3 = (cos3 * (cos2 * d2)) + (toRadians * cos);
            d3 = Math.atan2((d3 * d2) * cos2, cos - (cos3 * toRadians)) + toRadians2;
            a.add(new LatLng(Math.toDegrees(Math.asin(cos3)), Math.toDegrees(d3)));
        }
        return a;
    }
}
