package com.google.android.m4b.maps.p046d;

import android.support.v4.media.TransportMediator;
import android.support.v4.util.TimeUtils;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import com.google.android.m4b.maps.p041b.MarkedOutputStream;
import com.google.android.m4b.maps.p041b.Utf8Encoder;
import com.google.android.m4b.maps.p054p.Base64;
import com.google.android.m4b.maps.p054p.IntMap;
import com.google.android.m4b.maps.p054p.Primitives;
import com.leanplum.utils.SizeUtil;
import com.olacabs.customer.utils.Constants;
import com.sothree.slidinguppanel.p086a.R.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Vector;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;

/* renamed from: com.google.android.m4b.maps.d.a */
public final class ProtoBuf {
    public static final Boolean f7297a;
    public static final Boolean f7298b;
    private static byte[] f7299c;
    private static final ProtoBuf f7300h;
    private ProtoBufType f7301d;
    private final IntMap f7302e;
    private IntMap f7303f;
    private int f7304g;

    /* renamed from: com.google.android.m4b.maps.d.a.a */
    static class ProtoBuf {
        public int f7296a;

        private ProtoBuf() {
            this.f7296a = 0;
        }
    }

    public final /* synthetic */ Object clone() {
        return m10181a();
    }

    static {
        f7297a = new Boolean(false);
        f7298b = new Boolean(true);
        f7299c = new byte[0];
        f7300h = new ProtoBuf();
    }

    public ProtoBuf(ProtoBufType protoBufType) {
        this.f7304g = ExploreByTouchHelper.INVALID_ID;
        this.f7301d = protoBufType;
        this.f7302e = new IntMap();
    }

    public final ProtoBuf m10181a() {
        try {
            ProtoBuf protoBuf = new ProtoBuf(this.f7301d);
            protoBuf.m10188a(m10206d());
            return protoBuf;
        } catch (IOException e) {
            throw new RuntimeException("Could not serialize and parse ProtoBuf.");
        }
    }

    public final ProtoBuf m10182a(int i) {
        return new ProtoBuf((ProtoBufType) this.f7301d.m10220b(i));
    }

    public final void m10192a(int i, byte[] bArr) {
        m10169a(2, (Object) bArr);
    }

    public final void m10189a(int i, int i2) {
        m10169a(i, Primitives.m11085a((long) i2));
    }

    public final void m10190a(int i, ProtoBuf protoBuf) {
        m10169a(i, (Object) protoBuf);
    }

    public final void m10191a(int i, String str) {
        m10169a(i, (Object) str);
    }

    public final boolean m10200b(int i) {
        return ((Boolean) m10175h(i, 24)).booleanValue();
    }

    public final byte[] m10203c(int i) {
        return (byte[]) m10175h(i, 25);
    }

    public final int m10204d(int i) {
        return (int) ((Long) m10175h(i, 21)).longValue();
    }

    public final int m10195b(int i, int i2) {
        return (int) ((Long) m10166a(i, i2, 21)).longValue();
    }

    public final long m10207e(int i) {
        return ((Long) m10175h(i, 19)).longValue();
    }

    private long m10174g(int i, int i2) {
        return ((Long) m10166a(i, i2, 19)).longValue();
    }

    public final float m10209f(int i) {
        return Float.intBitsToFloat(m10204d(5));
    }

    public final ProtoBuf m10211g(int i) {
        return (ProtoBuf) m10175h(i, 26);
    }

    public final ProtoBuf m10202c(int i, int i2) {
        return (ProtoBuf) m10166a(i, i2, 26);
    }

    public final String m10212h(int i) {
        return (String) m10175h(i, 28);
    }

    public final String m10205d(int i, int i2) {
        return (String) m10166a(i, i2, 28);
    }

    public final ProtoBufType m10198b() {
        return this.f7301d;
    }

    final ProtoBuf m10186a(ProtoBufType protoBufType) {
        if ((this.f7302e.m11076c() == 0 ? 1 : null) == null || !(this.f7301d == null || protoBufType == null || protoBufType == this.f7301d)) {
            throw new IllegalArgumentException();
        }
        this.f7301d = protoBufType;
        this.f7303f = null;
        return this;
    }

    public final boolean m10213i(int i) {
        return m10215k(i) > 0;
    }

    public final boolean m10214j(int i) {
        return m10213i(i) || m10178n(i) != null;
    }

    public final ProtoBuf m10188a(byte[] bArr) {
        m10180a(new ByteArrayInputStream(bArr), bArr.length);
        return this;
    }

    public final ProtoBuf m10187a(InputStream inputStream) {
        m10180a(inputStream, Integer.MAX_VALUE);
        return this;
    }

    public final int m10180a(InputStream inputStream, int i) {
        return m10160a(inputStream, i, true, new ProtoBuf());
    }

    private int m10160a(InputStream inputStream, int i, boolean z, ProtoBuf protoBuf) {
        if (z) {
            this.f7302e.m11077d();
            this.f7303f = null;
        }
        int i2 = i;
        while (i2 > 0) {
            long a = ProtoBuf.m10165a(inputStream, true, protoBuf);
            if (a != -1) {
                int i3 = i2 - protoBuf.f7296a;
                i2 = ((int) a) & 7;
                if (i2 != 4) {
                    Object a2;
                    int i4;
                    int i5 = (int) (a >>> 3);
                    if (m10176l(i5) == 16) {
                        if (this.f7303f == null) {
                            this.f7303f = new IntMap();
                        }
                        this.f7303f.m11073a(i5, Primitives.m11084a(i2));
                    }
                    long a3;
                    int i6;
                    switch (i2) {
                        case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                            a3 = ProtoBuf.m10165a(inputStream, false, protoBuf);
                            i3 -= protoBuf.f7296a;
                            if (m10177m(i5)) {
                                a3 = (-(a3 & 1)) ^ (a3 >>> 1);
                            }
                            a2 = Primitives.m11085a(a3);
                            i4 = i3;
                            break;
                        case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                            i2 = i2 == 5 ? 4 : 8;
                            i4 = i3 - i2;
                            i6 = i2;
                            a3 = null;
                            long j = 0;
                            while (true) {
                                int i7 = i6 - 1;
                                if (i6 <= 0) {
                                    a2 = Primitives.m11085a(j);
                                    break;
                                }
                                j |= ((long) inputStream.read()) << a3;
                                a3 += 8;
                                i6 = i7;
                            }
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                            int a4 = (int) ProtoBuf.m10165a(inputStream, false, protoBuf);
                            i4 = (i3 - protoBuf.f7296a) - a4;
                            a2 = a4 == 0 ? f7299c : new byte[a4];
                            i3 = 0;
                            while (i3 < a4) {
                                i6 = inputStream.read(a2, i3, a4 - i3);
                                if (i6 <= 0) {
                                    throw new IOException("Unexp.EOF");
                                }
                                i3 += i6;
                            }
                            continue;
                        case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                            ProtoBufType protoBufType;
                            if (this.f7301d == null) {
                                protoBufType = null;
                            } else {
                                protoBufType = (ProtoBufType) this.f7301d.m10220b(i5);
                            }
                            ProtoBuf protoBuf2 = new ProtoBuf(protoBufType);
                            ProtoBuf protoBuf3 = protoBuf2;
                            i4 = protoBuf2.m10160a(inputStream, i3, false, protoBuf);
                            ProtoBuf protoBuf4 = protoBuf3;
                            break;
                        default:
                            throw new IOException("Unknown wire type " + i2 + ", reading garbage data?");
                    }
                    m10169a(i5, a2);
                    i2 = i4;
                } else {
                    i2 = i3;
                }
            }
            if (i2 < 0) {
                return i2;
            }
            throw new IOException();
        }
        if (i2 < 0) {
            return i2;
        }
        throw new IOException();
    }

    public final void m10208e(int i, int i2) {
        int k = m10215k(i);
        if (i2 >= k) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (k == 1) {
            this.f7302e.m11075b(i);
        } else {
            ((Vector) this.f7302e.m11072a(i)).removeElementAt(i2);
        }
    }

    private static int m10162a(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj instanceof Vector ? ((Vector) obj).size() : 1;
    }

    public final int m10215k(int i) {
        return ProtoBuf.m10162a(this.f7302e.m11072a(i));
    }

    private int m10176l(int i) {
        int a;
        int intValue;
        if (this.f7301d != null) {
            a = this.f7301d.m10218a(i);
        } else {
            a = 16;
        }
        if (a == 16) {
            Integer num = this.f7303f != null ? (Integer) this.f7303f.m11072a(i) : null;
            if (num != null) {
                intValue = num.intValue();
                if (intValue != 16 && m10215k(i) > 0) {
                    Object a2 = m10166a(i, 0, 16);
                    return ((a2 instanceof Long) || (a2 instanceof Boolean)) ? 0 : 2;
                }
            }
        }
        intValue = a;
        return intValue != 16 ? intValue : intValue;
    }

    public final int m10201c() {
        return m10163a(false);
    }

    private int m10163a(boolean z) {
        if (this.f7304g != ExploreByTouchHelper.INVALID_ID && z) {
            return this.f7304g;
        }
        IntMap.IntMap a = this.f7302e.m11071a();
        int i = 0;
        while (a.m11066a()) {
            int b = a.m11067b();
            int k = m10215k(b);
            int i2 = i;
            for (int i3 = 0; i3 < k; i3++) {
                int a2 = ProtoBuf.m10158a((long) (b << 3));
                switch (m10179o(b)) {
                    case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                        long g = m10174g(b, i3);
                        if (m10177m(b)) {
                            g = ProtoBuf.m10172b(g);
                        }
                        i = ProtoBuf.m10158a(g) + a2;
                        break;
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        i = a2 + 8;
                        break;
                    case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                        i = (m10202c(b, i3).m10163a(z) + a2) + a2;
                        break;
                    case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                        i = a2 + 4;
                        break;
                    default:
                        Object a3 = m10166a(b, i3, 16);
                        i = a3 instanceof byte[] ? ((byte[]) a3).length : a3 instanceof String ? Utf8Encoder.m7788a((String) a3, null, 0) : ((ProtoBuf) a3).m10163a(z);
                        i += ProtoBuf.m10158a((long) i) + a2;
                        break;
                }
                i2 += i;
            }
            i = i2;
        }
        this.f7304g = i;
        return this.f7304g;
    }

    private static int m10158a(long j) {
        if (j < 0) {
            return 10;
        }
        int i = 1;
        while (j >= 128) {
            i++;
            j >>= 7;
        }
        return i;
    }

    public final void m10193a(OutputStream outputStream) {
        m10170a(outputStream, true);
    }

    public final void m10199b(OutputStream outputStream) {
        m10170a(outputStream, false);
    }

    private void m10170a(OutputStream outputStream, boolean z) {
        int i = 0;
        MarkedOutputStream markedOutputStream = new MarkedOutputStream();
        int a = m10159a(markedOutputStream);
        if (z) {
            ((DataOutput) outputStream).writeInt(a);
        }
        int a2 = markedOutputStream.m7776a();
        int i2 = 0;
        while (i2 < a2) {
            a = markedOutputStream.m7777a(i2);
            markedOutputStream.m7779a(outputStream, i, a - i);
            ProtoBuf.m10161a(outputStream, (long) markedOutputStream.m7777a(i2 + 1));
            i2 += 2;
            i = a;
        }
        if (i < markedOutputStream.m7780b()) {
            markedOutputStream.m7779a(outputStream, i, markedOutputStream.m7780b() - i);
        }
    }

    private int m10159a(MarkedOutputStream markedOutputStream) {
        IntMap.IntMap a = this.f7302e.m11071a();
        int i = 0;
        while (a.m11066a()) {
            int b = a.m11067b();
            int k = m10215k(b);
            int o = m10179o(b);
            int i2 = (b << 3) | o;
            int i3 = 0;
            for (int i4 = 0; i4 < k; i4++) {
                Object obj;
                int a2 = i3 + ProtoBuf.m10161a((OutputStream) markedOutputStream, (long) i2);
                int b2 = markedOutputStream.m7780b();
                long longValue;
                int a3;
                switch (o) {
                    case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                        longValue = ((Long) m10166a(b, i4, 19)).longValue();
                        if (m10177m(b)) {
                            longValue = ProtoBuf.m10172b(longValue);
                        }
                        ProtoBuf.m10161a((OutputStream) markedOutputStream, longValue);
                        obj = null;
                        i3 = a2;
                        break;
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                        longValue = ((Long) m10166a(b, i4, 19)).longValue();
                        i3 = o == 5 ? 4 : 8;
                        for (int i5 = 0; i5 < i3; i5++) {
                            markedOutputStream.write((int) (255 & longValue));
                            longValue >>= 8;
                        }
                        obj = null;
                        i3 = a2;
                        break;
                    case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                        Object a4 = m10166a(b, i4, m10176l(b) == 27 ? 16 : 25);
                        if (!(a4 instanceof byte[])) {
                            markedOutputStream.m7781b(markedOutputStream.m7780b());
                            a3 = markedOutputStream.m7776a();
                            markedOutputStream.m7781b(-1);
                            i3 = ((ProtoBuf) a4).m10159a(markedOutputStream);
                            markedOutputStream.m7778a(a3, i3);
                            i3 = a2 + (i3 + ProtoBuf.m10158a((long) i3));
                            a3 = 1;
                            break;
                        }
                        byte[] bArr = (byte[]) a4;
                        ProtoBuf.m10161a((OutputStream) markedOutputStream, (long) bArr.length);
                        markedOutputStream.write(bArr);
                        obj = null;
                        i3 = a2;
                        break;
                    case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                        i3 = ProtoBuf.m10161a((OutputStream) markedOutputStream, (long) ((b << 3) | 4)) + (((ProtoBuf) m10166a(b, i4, 26)).m10159a(markedOutputStream) + a2);
                        a3 = 1;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                if (obj == null) {
                    i3 += markedOutputStream.m7780b() - b2;
                }
            }
            i = i3 + i;
        }
        return i;
    }

    private boolean m10177m(int i) {
        int l = m10176l(i);
        return l == 33 || l == 34;
    }

    private static long m10172b(long j) {
        return (j << 1) ^ (-(j >>> 63));
    }

    public final byte[] m10206d() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m10199b(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public final void m10194a(Writer writer) {
        m10171a(writer, 0);
    }

    private void m10171a(Writer writer, int i) {
        int i2;
        int i3 = i * 2;
        StringBuffer stringBuffer = new StringBuffer();
        for (i2 = 0; i2 < i3; i2++) {
            stringBuffer.append(' ');
        }
        CharSequence stringBuffer2 = stringBuffer.toString();
        for (int i4 = 0; i4 <= this.f7302e.m11074b(); i4++) {
            for (i3 = 0; i3 < m10215k(i4); i3++) {
                writer.append(stringBuffer2).append(Integer.toString(i4));
                i2 = m10176l(i4);
                if (i2 == 26) {
                    writer.append(' ');
                } else {
                    writer.append(':');
                }
                switch (i2) {
                    case LangUtils.HASH_SEED /*17*/:
                        writer.append(Double.toString(Double.longBitsToDouble(m10174g(i4, i3))));
                        break;
                    case SizeUtil.textSize0_1 /*18*/:
                        writer.append(Float.toString(Float.intBitsToFloat(m10195b(i4, i3))));
                        break;
                    case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
                    case SizeUtil.textSize0 /*20*/:
                    case 21:
                    case SizeUtil.textSize1 /*22*/:
                    case 23:
                    case SizeUtil.textSize2 /*24*/:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case HTTP.SP /*32*/:
                    case 33:
                    case 34:
                    case 36:
                        writer.append(m10166a(i4, i3, i2).toString());
                        break;
                    case 25:
                    case 35:
                        writer.append(Base64.m11065a((byte[]) m10166a(i4, i3, 25)));
                        break;
                    case 26:
                    case 27:
                        writer.append("{\n");
                        m10202c(i4, i3).m10171a(writer, i + 1);
                        writer.append(stringBuffer2).append('}');
                        break;
                    default:
                        writer.append("UNSUPPORTED TYPE: ").append(Integer.toString(i2));
                        break;
                }
                writer.append('\n');
            }
        }
    }

    public final ProtoBuf m10185a(int i, boolean z) {
        return m10173b(i, z ? f7298b : f7297a);
    }

    public final ProtoBuf m10210f(int i, int i2) {
        return m10184a(i, (long) i2);
    }

    public final ProtoBuf m10184a(int i, long j) {
        return m10173b(i, Primitives.m11085a(j));
    }

    public final ProtoBuf m10183a(int i, float f) {
        return m10184a(i, (long) Float.floatToIntBits(f));
    }

    public final ProtoBuf m10196b(int i, ProtoBuf protoBuf) {
        return m10173b(i, (Object) protoBuf);
    }

    public final ProtoBuf m10197b(int i, String str) {
        return m10173b(i, (Object) str);
    }

    private Object m10178n(int i) {
        switch (m10176l(i)) {
            case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
            case 26:
            case 27:
                return null;
            default:
                return this.f7301d == null ? null : this.f7301d.m10220b(i);
        }
    }

    private Object m10175h(int i, int i2) {
        Object a = this.f7302e.m11072a(i);
        int a2 = ProtoBuf.m10162a(a);
        if (a2 == 0) {
            return m10178n(i);
        }
        if (a2 <= 1) {
            return m10167a(i, 0, i2, a);
        }
        throw new IllegalArgumentException();
    }

    private Object m10166a(int i, int i2, int i3) {
        Object a = this.f7302e.m11072a(i);
        if (i2 < ProtoBuf.m10162a(a)) {
            return m10167a(i, i2, i3, a);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private Object m10167a(int i, int i2, int i3, Object obj) {
        Object elementAt;
        if (obj instanceof Vector) {
            obj = (Vector) obj;
            elementAt = obj.elementAt(i2);
        } else {
            elementAt = obj;
            obj = null;
        }
        Object a = m10168a(elementAt, i3, i);
        if (!(a == elementAt || elementAt == null)) {
            if (obj == null) {
                m10173b(i, a);
            } else {
                obj.setElementAt(a, i2);
            }
        }
        return a;
    }

    private final int m10179o(int i) {
        int l = m10176l(i);
        switch (l) {
            case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
            case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                return l;
            case LangUtils.HASH_SEED /*17*/:
            case SizeUtil.textSize1 /*22*/:
            case HTTP.SP /*32*/:
                return 1;
            case SizeUtil.textSize0_1 /*18*/:
            case 23:
            case 31:
                return 5;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
            case SizeUtil.textSize0 /*20*/:
            case 21:
            case SizeUtil.textSize2 /*24*/:
            case 29:
            case 30:
            case 33:
            case 34:
                return 0;
            case 25:
            case 27:
            case 28:
            case 35:
            case 36:
                return 2;
            case 26:
                return 3;
            default:
                throw new RuntimeException("Unsupp.Type:" + this.f7301d + '/' + i + '/' + l);
        }
    }

    private void m10169a(int i, Object obj) {
        Object a = this.f7302e.m11072a(i);
        Vector vector = null;
        if (a instanceof Vector) {
            vector = (Vector) a;
        }
        if (a == null || (vector != null && vector.size() == 0)) {
            m10173b(i, obj);
            return;
        }
        if (vector == null) {
            vector = new Vector();
            vector.addElement(a);
            this.f7302e.m11073a(i, vector);
        }
        vector.addElement(obj);
    }

    private Object m10168a(Object obj, int i, int i2) {
        switch (i) {
            case Constants.DEFAULT_MAP_ZOOM_LEVEL /*16*/:
                return obj;
            case TimeUtils.HUNDRED_DAY_FIELD_LEN /*19*/:
            case SizeUtil.textSize0 /*20*/:
            case 21:
            case SizeUtil.textSize1 /*22*/:
            case 23:
            case 29:
            case 30:
            case 31:
            case HTTP.SP /*32*/:
            case 33:
            case 34:
                if (!(obj instanceof Boolean)) {
                    return obj;
                }
                return Primitives.m11085a(((Boolean) obj).booleanValue() ? 1 : 0);
            case SizeUtil.textSize2 /*24*/:
                if (obj instanceof Boolean) {
                    return obj;
                }
                switch ((int) ((Long) obj).longValue()) {
                    case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                        return f7297a;
                    case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                        return f7298b;
                    default:
                        throw new IllegalArgumentException("Type mismatch");
                }
            case 25:
            case 35:
                if (obj instanceof String) {
                    return Utf8Encoder.m7790a((String) obj);
                }
                if (!(obj instanceof ProtoBuf)) {
                    return obj;
                }
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    ((ProtoBuf) obj).m10199b(byteArrayOutputStream);
                    return byteArrayOutputStream.toByteArray();
                } catch (IOException e) {
                    throw new RuntimeException(e.toString());
                }
            case 26:
            case 27:
                if (!(obj instanceof byte[])) {
                    return obj;
                }
                ProtoBuf protoBuf;
                if (i2 > 0) {
                    try {
                        if (this.f7301d != null) {
                            protoBuf = new ProtoBuf((ProtoBufType) this.f7301d.m10220b(i2));
                            return protoBuf.m10188a((byte[]) obj);
                        }
                    } catch (IOException e2) {
                        throw new RuntimeException(e2.toString());
                    }
                }
                protoBuf = new ProtoBuf(null);
                return protoBuf.m10188a((byte[]) obj);
            case 28:
            case 36:
                if (!(obj instanceof byte[])) {
                    return obj;
                }
                byte[] bArr = (byte[]) obj;
                return Utf8Encoder.m7789a(bArr, 0, bArr.length, true);
            default:
                throw new RuntimeException("Unsupp.Type");
        }
    }

    static long m10164a(InputStream inputStream, boolean z) {
        return ProtoBuf.m10165a(inputStream, z, f7300h);
    }

    private static long m10165a(InputStream inputStream, boolean z, ProtoBuf protoBuf) {
        protoBuf.f7296a = 0;
        long j = 0;
        int i = 0;
        long j2 = 0;
        while (i < 10) {
            int read = inputStream.read();
            if (read != -1) {
                j |= ((long) (read & TransportMediator.KEYCODE_MEDIA_PAUSE)) << j2;
                if ((read & AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) == 0) {
                    break;
                }
                j2 += 7;
                i++;
            } else if (i == 0 && z) {
                return -1;
            } else {
                throw new IOException("EOF");
            }
        }
        protoBuf.f7296a = i + 1;
        return j;
    }

    private ProtoBuf m10173b(int i, Object obj) {
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.f7302e.m11073a(i, obj);
        return this;
    }

    public final String toString() {
        Writer stringWriter = new StringWriter();
        try {
            m10194a(stringWriter);
        } catch (IOException e) {
            stringWriter.write(e.toString());
        }
        return stringWriter.toString();
    }

    private static int m10161a(OutputStream outputStream, long j) {
        int i = 0;
        while (i < 10) {
            int i2 = (int) (127 & j);
            j >>>= 7;
            if (j == 0) {
                outputStream.write(i2);
                return i + 1;
            }
            outputStream.write(i2 | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            i++;
        }
        return i;
    }
}
