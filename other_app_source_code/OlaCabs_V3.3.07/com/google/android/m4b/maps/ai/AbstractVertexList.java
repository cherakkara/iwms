package com.google.android.m4b.maps.ai;

import java.util.Comparator;

/* renamed from: com.google.android.m4b.maps.ai.a */
abstract class AbstractVertexList {
    protected int f3111a;

    /* renamed from: com.google.android.m4b.maps.ai.a.a */
    public static class AbstractVertexList implements Comparator<int[]> {
        private final AbstractVertexList f3100a;
        private final double f3101b;
        private final double f3102c;

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return m4913a((int[]) obj, (int[]) obj2);
        }

        public AbstractVertexList(AbstractVertexList abstractVertexList, int i) {
            this.f3100a = abstractVertexList;
            this.f3101b = abstractVertexList.m4915a(i);
            this.f3102c = abstractVertexList.m4921b(i);
        }

        public final int m4913a(int[] iArr, int[] iArr2) {
            double a = this.f3100a.m4915a(iArr[1]);
            double b = this.f3100a.m4921b(iArr[1]);
            double a2 = this.f3100a.m4915a(iArr2[1]);
            double b2 = this.f3100a.m4921b(iArr2[1]);
            if (this.f3101b == a && this.f3102c == b) {
                return -1;
            }
            if (this.f3101b == a2 && this.f3102c == b2) {
                return 1;
            }
            Object obj;
            double a3;
            Object obj2 = (Vertex2d.m5076a(a, b, this.f3101b, this.f3102c) < 0 || (Vertex2d.m5076a(a, b, this.f3101b, this.f3102c) == 0 && b < this.f3102c)) ? 1 : null;
            if (Vertex2d.m5076a(a2, b2, this.f3101b, this.f3102c) >= 0) {
                if (Vertex2d.m5076a(a2, b2, this.f3101b, this.f3102c) >= 0 || b2 >= this.f3102c) {
                    obj = null;
                    if (obj2 != obj) {
                        return obj2 == null ? -1 : 1;
                    } else {
                        a3 = Vertex2d.m5075a(this.f3101b, this.f3102c, a2, b2, a, b);
                        if (a3 == 0.0d) {
                            return a3 <= 0.0d ? 1 : -1;
                        } else {
                            if (Vertex2d.m5076a(a, b, a2, b2) <= 0) {
                                return 1;
                            }
                            return -1;
                        }
                    }
                }
            }
            obj = 1;
            if (obj2 != obj) {
                a3 = Vertex2d.m5075a(this.f3101b, this.f3102c, a2, b2, a, b);
                if (a3 == 0.0d) {
                    if (a3 <= 0.0d) {
                    }
                } else if (Vertex2d.m5076a(a, b, a2, b2) <= 0) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (obj2 == null) {
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ai.a.b */
    public enum AbstractVertexList {
        SPLIT_VERTEX,
        MERGE_VERTEX,
        RIGHT_VERTEX,
        LEFT_VERTEX,
        START_VERTEX,
        END_VERTEX,
        INTERSECTION_VERTEX
    }

    public abstract double m4915a(int i);

    public abstract int m4917a();

    abstract void m4919a(int i, double[] dArr, int i2, int i3);

    public abstract double m4921b(int i);

    public abstract int m4922c(int i);

    public abstract int m4923d(int i);

    public abstract AbstractVertexList m4924e(int i);

    AbstractVertexList() {
    }

    public final int m4918a(int i, int i2) {
        return Vertex2d.m5076a(m4915a(i), m4921b(i), m4915a(i2), m4921b(i2));
    }

    public final double m4916a(int i, int i2, int i3) {
        return Vertex2d.m5075a(m4915a(i), m4921b(i), m4915a(i2), m4921b(i2), m4915a(i3), m4921b(i3));
    }

    public final boolean m4920a(int i, int i2, int i3, int i4, int i5) {
        if (m4918a(i, i2) == 0 || m4918a(i, i3) == 0 || m4918a(i, i4) == 0 || m4918a(i, i5) == 0) {
            return false;
        }
        if ((m4914b(i2, i, i3) == 0 && m4914b(i4, i, i5) == 0) || (m4914b(i2, i, i5) == 0 && m4914b(i4, i, i3) == 0)) {
            return false;
        }
        if (m4914b(i2, i, i4) == 0 && m4914b(i3, i, i5) == 0) {
            return (m4918a(i2, i) == m4918a(i4, i) || m4918a(i3, i) == m4918a(i5, i)) ? false : true;
        } else {
            if (m4914b(i2, i, i4) == 0) {
                return (m4918a(i2, i) == m4918a(i4, i) || m4914b(i2, i, i3) == m4914b(i2, i, i5)) ? false : true;
            } else {
                if (m4914b(i3, i, i5) == 0) {
                    return (m4918a(i3, i) == m4918a(i5, i) || m4914b(i3, i, i2) == m4914b(i3, i, i4)) ? false : true;
                } else {
                    int i6;
                    int i7;
                    int i8;
                    int i9;
                    if (m4914b(i2, i, i3) == 0) {
                        i6 = i5;
                        i7 = i4;
                        i8 = i3;
                        i9 = i2;
                    } else {
                        i6 = -1;
                        i7 = -1;
                        i8 = -1;
                        i9 = -1;
                    }
                    if (m4914b(i2, i, i5) == 0) {
                        i6 = i3;
                        i7 = i4;
                        i8 = i5;
                        i9 = i2;
                    }
                    if (m4914b(i3, i, i4) == 0) {
                        i6 = i2;
                        i7 = i5;
                        i8 = i4;
                        i9 = i3;
                    }
                    if (m4914b(i4, i, i5) == 0) {
                        i6 = i3;
                        i7 = i2;
                        i8 = i5;
                        i9 = i4;
                    }
                    if (i9 != -1) {
                        return m4918a(i, i9) != m4918a(i, i8) && m4914b(i9, i, i7) == m4914b(i9, i, i6) && m4914b(i7, i, i9) == m4914b(i7, i, i6);
                    } else {
                        if (m4914b(i2, i, i3) == m4914b(i2, i, i5)) {
                            return m4914b(i2, i, i3) == m4914b(i2, i, i4) && m4914b(i4, i, i3) != m4914b(i4, i, i5);
                        } else {
                            if (m4914b(i2, i, i4) != m4914b(i2, i, i3)) {
                                i3 = i5;
                            }
                            if (m4914b(i3, i, i2) != m4914b(i3, i, i4)) {
                                return true;
                            }
                            return false;
                        }
                    }
                }
            }
        }
    }

    private int m4914b(int i, int i2, int i3) {
        double a = m4916a(i, i2, i3);
        if (a > 0.0d) {
            return 1;
        }
        return a == 0.0d ? 0 : -1;
    }

    public int m4925f(int i) {
        if (i >= 0 && i < this.f3111a) {
            return 0;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int m4926g(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return this.f3111a;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    protected final int m4927h(int i) {
        if (this.f3111a == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i2 = i % this.f3111a;
        return i2 < 0 ? i2 + this.f3111a : i2;
    }
}
