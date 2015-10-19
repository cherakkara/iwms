package com.google.android.m4b.maps.p057t;

import com.google.p025a.p032g.UnsignedLongs;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.DataInput;
import java.util.regex.Pattern;

/* renamed from: com.google.android.m4b.maps.t.a */
public abstract class FeatureId {
    public static final FeatureId f7848a;

    /* renamed from: com.google.android.m4b.maps.t.a.a */
    public static class FeatureId extends FeatureId {
        protected final long f7849b;
        protected final long f7850c;
        protected final long f7851d;

        FeatureId(long j) {
            this(0, j);
        }

        FeatureId(long j, long j2) {
            this.f7849b = FeatureId.m11289a(j, j2);
            this.f7850c = j;
            this.f7851d = j2;
        }

        public final String m11296a() {
            return "0x" + Long.toHexString(this.f7850c) + ":0x" + Long.toHexString(this.f7851d);
        }

        public String toString() {
            return m11296a();
        }

        public int hashCode() {
            return (int) (this.f7849b ^ (this.f7849b >>> 32));
        }

        public boolean equals(Object obj) {
            if (obj instanceof FeatureId) {
                return ((FeatureId) obj).f7852b == this.f7849b;
            } else {
                return m11297a(obj);
            }
        }

        public final boolean m11297a(Object obj) {
            if (!(obj instanceof FeatureId)) {
                return false;
            }
            FeatureId featureId = (FeatureId) obj;
            if (featureId.f7850c == this.f7850c && featureId.f7851d == this.f7851d && featureId.f7849b == this.f7849b) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.google.android.m4b.maps.t.a.b */
    public static class FeatureId extends FeatureId {
        protected final long f7852b;

        FeatureId(long j) {
            this.f7852b = j;
        }

        public final String m11298a() {
            return Trace.NULL;
        }

        public final String toString() {
            return "[hash:" + this.f7852b + "]";
        }

        public final int hashCode() {
            return (int) (this.f7852b ^ (this.f7852b >>> 32));
        }

        public final boolean equals(Object obj) {
            if (obj instanceof FeatureId) {
                return ((FeatureId) obj).f7849b == this.f7852b;
            } else {
                return m11299a(obj);
            }
        }

        public final boolean m11299a(Object obj) {
            if ((obj instanceof FeatureId) && ((FeatureId) obj).f7852b == this.f7852b) {
                return true;
            }
            return false;
        }
    }

    /* renamed from: com.google.android.m4b.maps.t.a.c */
    public static final class FeatureId extends FeatureId {
        public FeatureId(long j, long j2) {
            super(j, j2);
        }

        public static FeatureId m11300b(String str) {
            try {
                FeatureId a = FeatureId.m11291a(str);
                if (a instanceof FeatureId) {
                    return new FeatureId(((FeatureId) a).f7850c, ((FeatureId) a).f7851d);
                }
            } catch (IllegalArgumentException e) {
            }
            return null;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof FeatureId) {
                if (this.d != ((FeatureId) obj).d) {
                    return false;
                }
                return true;
            } else if (!(obj instanceof IndoorLevelReference)) {
                return false;
            } else {
                if (this.d != ((IndoorLevelReference) obj).m11306a().d) {
                    return false;
                }
                return true;
            }
        }

        public final int hashCode() {
            return (int) (this.d ^ (this.d >>> 32));
        }

        public final String m11301c() {
            return Long.toHexString(this.d);
        }
    }

    /* renamed from: com.google.android.m4b.maps.t.a.d */
    public static class FeatureId extends FeatureId {
        private GeoPoint f7853b;
        private int f7854c;

        FeatureId(GeoPoint geoPoint, int i) {
            this.f7853b = geoPoint;
            this.f7854c = i;
        }

        public final String m11302a() {
            return this.f7853b.m11304a() + "|" + this.f7853b.m11305b() + "|" + Integer.toString(this.f7854c);
        }

        public final int hashCode() {
            return m11302a().hashCode();
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof FeatureId)) {
                return false;
            }
            FeatureId featureId = (FeatureId) obj;
            if (featureId.f7853b.equals(this.f7853b) && featureId.f7854c == this.f7854c) {
                return true;
            }
            return false;
        }

        public final boolean m11303a(Object obj) {
            return equals(obj);
        }
    }

    public abstract String m11294a();

    static /* synthetic */ long m11289a(long j, long j2) {
        long j3 = j >>> 16;
        j3 = (((j3 >>> 44) ^ (j3 << 4)) & 281474976710655L) ^ (((j << 32) & 281474976710655L) | (j2 >>> 32));
        return (((j3 >>> 44) ^ (j3 << 4)) & 281474976710655L) ^ (4294967295L & j2);
    }

    static {
        f7848a = new FeatureId(0, 0);
    }

    public static FeatureId m11290a(DataInput dataInput) {
        return new FeatureId(dataInput.readLong(), dataInput.readLong());
    }

    public static FeatureId m11293b(DataInput dataInput) {
        return new FeatureId(((((long) dataInput.readShort()) & 65535) << 32) | (((long) dataInput.readInt()) & 4294967295L));
    }

    public static FeatureId m11291a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("null feature id");
        }
        String[] split = str.split(":");
        String[] split2 = str.split("\\|");
        if (split.length == 2) {
            if (split[0].startsWith("0x") && split[1].startsWith("0x")) {
                return new FeatureId(ParseHexUtil.m11308a(split[0].substring(2)), ParseHexUtil.m11308a(split[1].substring(2)));
            }
            throw new IllegalArgumentException("malformed feature id " + str);
        } else if (split2.length == 3) {
            int parseInt = Integer.parseInt(split2[0]);
            int parseInt2 = Integer.parseInt(split2[1]);
            return new FeatureId(new GeoPoint(parseInt, parseInt2), Integer.parseInt(split2[2]));
        } else if (str.startsWith("0x")) {
            return new FeatureId(ParseHexUtil.m11308a(str.substring(2)));
        } else {
            if (Pattern.matches("[0-9]{1,20}", str)) {
                return new FeatureId(UnsignedLongs.m3013a(str));
            }
            throw new IllegalArgumentException("malformed feature id " + str);
        }
    }

    boolean m11295a(Object obj) {
        return false;
    }

    public static int m11292b() {
        return 40;
    }
}
