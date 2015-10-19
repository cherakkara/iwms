package com.google.android.m4b.maps.ay;

import com.google.android.m4b.maps.an.Point;
import com.google.android.m4b.maps.az.IndexBufferInterface;
import com.google.android.m4b.maps.az.TexCoordBufferInterface;
import com.google.android.m4b.maps.az.VertexBufferInterface;
import com.google.p025a.p028c.ar;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.ay.g */
public final class GmmVertexDataBuilder implements IndexBufferInterface, TexCoordBufferInterface, VertexBufferInterface {
    private final List<Float> f4898a;
    private List<Float> f4899b;
    private List<Float> f4900c;
    private List<Short> f4901d;
    private List<Byte> f4902e;
    private final boolean f4903f;
    private final boolean f4904g;
    private final boolean f4905h;
    private int f4906i;
    private int f4907j;
    private int f4908k;
    private int f4909l;
    private final int f4910m;
    private ByteBuffer f4911n;

    public GmmVertexDataBuilder(int i, int i2, boolean z) {
        this.f4898a = ar.m2515a();
        this.f4907j = 0;
        this.f4908k = 0;
        this.f4909l = 0;
        this.f4910m = 9;
        this.f4903f = true;
        this.f4904g = false;
        this.f4905h = false;
        this.f4906i = 12;
        if (this.f4904g) {
            this.f4906i += 16;
            this.f4900c = ar.m2527c(i);
        }
        if (this.f4903f) {
            this.f4906i += 8;
            this.f4899b = ar.m2527c(i);
        }
        if (this.f4905h) {
            this.f4902e = ar.m2527c(i);
            this.f4906i++;
        }
        this.f4911n = ByteBuffer.allocateDirect(this.f4906i * i).order(ByteOrder.nativeOrder());
    }

    public final void m7571a(float f, float f2) {
        this.f4909l++;
        if (this.f4903f) {
            this.f4899b.add(Float.valueOf(f));
            this.f4899b.add(Float.valueOf(f2));
            return;
        }
        throw new IllegalStateException("Texture coordinate 0 not enabled in this VBO");
    }

    public final void m7577a(Point point, int i) {
        this.f4898a.add(Float.valueOf(((float) point.m5958f()) / ((float) i)));
        this.f4898a.add(Float.valueOf(((float) point.m5960g()) / ((float) i)));
        this.f4898a.add(Float.valueOf(((float) point.m5962h()) / ((float) i)));
        if (this.f4902e != null) {
            throw new RuntimeException("Expecting styleIndex");
        }
        this.f4907j++;
    }

    public final void m7578a(Point point, int i, byte b) {
        this.f4898a.add(Float.valueOf(((float) point.m5958f()) / ((float) i)));
        this.f4898a.add(Float.valueOf(((float) point.m5960g()) / ((float) i)));
        this.f4898a.add(Float.valueOf(((float) point.m5962h()) / ((float) i)));
        if (this.f4902e != null) {
            this.f4902e.add(Byte.valueOf(b));
        }
        this.f4907j++;
    }

    public final void m7572a(float f, float f2, float f3) {
        this.f4898a.add(Float.valueOf(f));
        this.f4898a.add(Float.valueOf(f2));
        this.f4898a.add(Float.valueOf(f3));
        this.f4907j++;
    }

    public final void m7573a(int i) {
        if (this.f4911n == null) {
            this.f4911n = ByteBuffer.allocateDirect(this.f4906i * i).order(ByteOrder.nativeOrder());
        } else if (this.f4906i * i > this.f4911n.capacity()) {
            ByteBuffer order = ByteBuffer.allocateDirect(this.f4906i * i).order(ByteOrder.nativeOrder());
            if (this.f4911n.position() != 0) {
                this.f4911n.rewind();
                order.put(this.f4911n);
            }
            this.f4911n = order;
        }
    }

    public final int m7570a() {
        m7583c();
        return this.f4907j;
    }

    public final int m7581b() {
        return this.f4908k;
    }

    public final void m7582b(int i) {
    }

    public final void m7583c() {
        if (this.f4898a.size() != 0) {
            int size = this.f4898a.size() / 3;
            if (this.f4903f && size != this.f4899b.size() / 2) {
                throw new RuntimeException("Buffer mismatch verts = " + size + "  tex coords = " + this.f4899b.size());
            } else if (this.f4904g && size != this.f4900c.size() / 4) {
                throw new RuntimeException("Buffer mismatch");
            } else if (!this.f4905h || size == this.f4902e.size()) {
                int i = 0;
                while (i < size) {
                    try {
                        this.f4911n.putFloat(((Float) this.f4898a.get(i * 3)).floatValue());
                        this.f4911n.putFloat(((Float) this.f4898a.get((i * 3) + 1)).floatValue());
                        this.f4911n.putFloat(((Float) this.f4898a.get((i * 3) + 2)).floatValue());
                        if (this.f4904g) {
                            this.f4911n.putFloat(((Float) this.f4900c.get(i * 4)).floatValue());
                            this.f4911n.putFloat(((Float) this.f4900c.get((i * 4) + 1)).floatValue());
                            this.f4911n.putFloat(((Float) this.f4900c.get((i * 4) + 2)).floatValue());
                            this.f4911n.putFloat(((Float) this.f4900c.get((i * 4) + 3)).floatValue());
                        }
                        if (this.f4903f) {
                            this.f4911n.putFloat(((Float) this.f4899b.get(i * 2)).floatValue());
                            this.f4911n.putFloat(((Float) this.f4899b.get((i * 2) + 1)).floatValue());
                        }
                        if (this.f4905h) {
                            this.f4911n.put(((Byte) this.f4902e.get(i)).byteValue());
                        }
                        i++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
                this.f4898a.clear();
                if (this.f4899b != null) {
                    this.f4899b.clear();
                }
                if (this.f4900c != null) {
                    this.f4900c.clear();
                }
                if (this.f4902e != null) {
                    this.f4902e.clear();
                }
            } else {
                throw new RuntimeException("Buffer mismatch");
            }
        }
    }

    public final int m7585d() {
        return this.f4910m;
    }

    public final ByteBuffer m7587e() {
        m7583c();
        ByteBuffer byteBuffer = this.f4911n;
        byteBuffer.rewind();
        this.f4911n = null;
        return byteBuffer;
    }

    public final void m7588f() {
        if (this.f4901d != null) {
            this.f4901d.clear();
        }
        this.f4908k = 0;
        this.f4907j = 0;
        this.f4909l = 0;
        this.f4898a.clear();
        if (this.f4900c != null) {
            this.f4900c.clear();
        }
        if (this.f4899b != null) {
            this.f4899b.clear();
        }
        if (this.f4911n != null) {
            this.f4911n.clear();
        }
    }

    public final void m7579a(int[] iArr) {
        this.f4909l += iArr.length / 2;
        m7580a(iArr, 0, iArr.length);
    }

    public final void m7580a(int[] iArr, int i, int i2) {
        this.f4909l += i2 / 2;
        for (int i3 = i; i3 < i + i2; i3++) {
            this.f4899b.add(Float.valueOf(((float) iArr[i3]) / 65536.0f));
        }
    }

    public final void m7574a(int i, int i2) {
        this.f4909l++;
        this.f4899b.add(Float.valueOf(((float) i) / 65536.0f));
        this.f4899b.add(Float.valueOf(((float) i2) / 65536.0f));
    }

    public final int m7589g() {
        return this.f4909l;
    }

    public final void m7584c(int i) {
    }

    public final void m7586d(int i) {
        this.f4901d.add(Short.valueOf((short) i));
        this.f4908k++;
    }

    public final void m7576a(int i, int i2, int i3, int i4) {
        this.f4901d.add(Short.valueOf((short) i));
        this.f4901d.add(Short.valueOf((short) i2));
        this.f4901d.add(Short.valueOf((short) i3));
        this.f4901d.add(Short.valueOf((short) i3));
        this.f4901d.add(Short.valueOf((short) i2));
        this.f4901d.add(Short.valueOf((short) i4));
        this.f4908k += 6;
    }

    public final void m7575a(int i, int i2, int i3) {
        this.f4901d.add(Short.valueOf((short) i));
        this.f4901d.add(Short.valueOf((short) i2));
        this.f4901d.add(Short.valueOf((short) i3));
        this.f4908k += 3;
    }
}
