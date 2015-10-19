package com.google.p025a.p031f;

/* renamed from: com.google.a.f.a */
public final class DoubleMath {
    static final double[] f1894a;
    private static final double f1895b;

    static {
        f1895b = Math.log(2.0d);
        f1894a = new double[]{1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};
    }

    public static boolean m3000a(double d) {
        return DoubleUtils.m3003c(d) && (d == 0.0d || 52 - Long.numberOfTrailingZeros(DoubleUtils.m3002b(d)) <= DoubleUtils.m3001a(d));
    }
}
