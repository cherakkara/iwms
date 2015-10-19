package com.google.android.m4b.maps.ai;

import com.google.android.m4b.maps.ai.AbstractVertexList.AbstractVertexList;
import com.google.p025a.p028c.ar;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.google.android.m4b.maps.ai.e */
public final class Polygon2dTessellator {

    /* renamed from: com.google.android.m4b.maps.ai.e.1 */
    static /* synthetic */ class Polygon2dTessellator {
        static final /* synthetic */ int[] f3122a;
        static final /* synthetic */ int[] f3123b;

        static {
            f3123b = new int[Polygon2dTessellator.values().length];
            try {
                f3123b[Polygon2dTessellator.EMPTY_MESH.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3123b[Polygon2dTessellator.TESSELLATE_MONOTONE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3123b[Polygon2dTessellator.CUT_AND_TESSELLATE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            f3122a = new int[AbstractVertexList.values().length];
            try {
                f3122a[AbstractVertexList.LEFT_VERTEX.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3122a[AbstractVertexList.RIGHT_VERTEX.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3122a[AbstractVertexList.SPLIT_VERTEX.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f3122a[AbstractVertexList.MERGE_VERTEX.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f3122a[AbstractVertexList.START_VERTEX.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f3122a[AbstractVertexList.END_VERTEX.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.ai.e.b */
    public static class Polygon2dTessellator extends Exception {
        public Polygon2dTessellator(String str, Throwable th) {
            super(str, th);
        }
    }

    /* renamed from: com.google.android.m4b.maps.ai.e.a */
    public static class Polygon2dTessellator extends Polygon2dTessellator {
        public Polygon2dTessellator(String str) {
            super(str, null);
        }
    }

    /* renamed from: com.google.android.m4b.maps.ai.e.c */
    static class Polygon2dTessellator {
        public Polygon2dTessellator f3128a;
        public SortedVertexMapping f3129b;
        public EdgeList f3130c;
        public int f3131d;
        public int f3132e;
        public AbstractVertexList f3133f;
        public List<Object> f3134g;
        public int[] f3135h;
        public int f3136i;

        /* renamed from: com.google.android.m4b.maps.ai.e.c.a */
        public enum Polygon2dTessellator {
            EMPTY_MESH,
            TESSELLATE_MONOTONE,
            CUT_AND_TESSELLATE
        }

        private Polygon2dTessellator() {
        }

        public final Polygon2dTessellator m5008a(Polygon2dTessellator polygon2dTessellator, SortedVertexMapping sortedVertexMapping, EdgeList edgeList, int i, int i2, AbstractVertexList abstractVertexList, List<Object> list) {
            this.f3128a = polygon2dTessellator;
            this.f3129b = sortedVertexMapping;
            this.f3130c = edgeList;
            this.f3131d = i;
            this.f3132e = i2;
            this.f3133f = abstractVertexList;
            this.f3134g = list;
            return this;
        }
    }

    /* renamed from: com.google.android.m4b.maps.ai.e.d */
    public enum Polygon2dTessellator {
        CCW_OUTLINE,
        CW_HOLES,
        NON_SELF_INTERSECTING,
        NO_LINES_STICKING_OUT
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.m4b.maps.ai.TriangleMesh2d m5010a(com.google.android.m4b.maps.ai.Polygon2d r12) {
        /*
        r3 = 3;
        r0 = 0;
        r2 = r12.m5007f();	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r1 = r2.m5025a();	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        if (r1 != 0) goto L_0x0034;
    L_0x000c:
        r0 = r2.a;	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        if (r0 >= r3) goto L_0x0015;
    L_0x0010:
        r0 = com.google.android.m4b.maps.ai.TriangleMesh2d.m5060a();	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
    L_0x0014:
        return r0;
    L_0x0015:
        r0 = r2.a;	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        if (r0 != r3) goto L_0x002a;
    L_0x0019:
        r0 = r2.m5029b();	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r0 = com.google.android.m4b.maps.ai.TriangleMesh2d.m5061a(r0);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r1 = 0;
        r2 = 1;
        r3 = 2;
        r0.m5064a(r1, r2, r3);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        goto L_0x0014;
    L_0x0028:
        r0 = move-exception;
        throw r0;
    L_0x002a:
        r0 = r2.m5032c();	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r1 = 0;
        r0 = com.google.android.m4b.maps.ai.Polygon2dTessellator.m5011a(r0, r1);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        goto L_0x0014;
    L_0x0034:
        r1 = new com.google.android.m4b.maps.ai.c;	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r1.<init>(r2);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r3 = r1.m4966d();	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        if (r3 != 0) goto L_0x0046;
    L_0x003f:
        r0 = r1;
    L_0x0040:
        r1 = 0;
        r0 = com.google.android.m4b.maps.ai.Polygon2dTessellator.m5011a(r0, r1);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        goto L_0x0014;
    L_0x0046:
        r4 = new com.google.android.m4b.maps.ai.b;	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r4.<init>(r1);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r2 = r3 * 2;
        r5 = new int[r2];	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r2 = r0;
    L_0x0050:
        r6 = r1.c;	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        if (r2 >= r6) goto L_0x00cb;
    L_0x0054:
        r6 = r1.m4980j(r2);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r7 = r1.m4967d(r6);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r8 = r1.m4969e(r6);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r9 = r1.m4972f(r6);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r10 = com.google.android.m4b.maps.ai.Polygon2dTessellator.Polygon2dTessellator.f3122a;	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r11 = r9.ordinal();	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r10 = r10[r11];	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        switch(r10) {
            case 1: goto L_0x0072;
            case 2: goto L_0x0093;
            case 3: goto L_0x009a;
            case 4: goto L_0x00b9;
            case 5: goto L_0x00c0;
            case 6: goto L_0x00c4;
            default: goto L_0x006f;
        };	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
    L_0x006f:
        r2 = r2 + 1;
        goto L_0x0050;
    L_0x0072:
        r4.m4941a(r7, r6, r9);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r4.m4946b(r6, r8, r9);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        goto L_0x006f;
    L_0x0079:
        r0 = move-exception;
        r1 = new com.google.android.m4b.maps.ai.e$b;
        r2 = new java.lang.StringBuilder;
        r3 = "Error when tessellating polygon: ";
        r2.<init>(r3);
        r3 = r0.getMessage();
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x0093:
        r4.m4941a(r6, r8, r9);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r4.m4946b(r7, r6, r9);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        goto L_0x006f;
    L_0x009a:
        r9 = r4.m4940a(r6);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r10 = r1.m5000a(r6, r9);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        if (r10 == 0) goto L_0x00b5;
    L_0x00a4:
        r5[r0] = r6;	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r10 = r0 + 1;
        r5[r10] = r9;	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r0 = r0 + 2;
        r9 = r3 * 2;
        if (r0 != r9) goto L_0x00b5;
    L_0x00b0:
        r0 = r1.m4999a(r5);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        goto L_0x0040;
    L_0x00b5:
        r4.m4947b(r7, r6, r8);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        goto L_0x006f;
    L_0x00b9:
        r4.m4946b(r6, r8, r9);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r4.m4946b(r7, r6, r9);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        goto L_0x006f;
    L_0x00c0:
        r4.m4943a(r7, r6, r8);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        goto L_0x006f;
    L_0x00c4:
        r4.m4946b(r7, r6, r9);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r4.m4946b(r6, r8, r9);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        goto L_0x006f;
    L_0x00cb:
        r0 = new java.lang.NullPointerException;	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        r1 = "Tesselation could not cut all holes open.";
        r0.<init>(r1);	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        throw r0;	 Catch:{ b -> 0x0028, Exception -> 0x0079 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.m4b.maps.ai.e.a(com.google.android.m4b.maps.ai.d):com.google.android.m4b.maps.ai.k");
    }

    public static Polygon2dTessellator m5014b(Polygon2d polygon2d) {
        if (polygon2d.m5003b()) {
            return Polygon2dTessellator.CCW_OUTLINE;
        }
        if (polygon2d.m5004c()) {
            return Polygon2dTessellator.CW_HOLES;
        }
        if (polygon2d.m5005d()) {
            return Polygon2dTessellator.NO_LINES_STICKING_OUT;
        }
        if (polygon2d.m5006e()) {
            return Polygon2dTessellator.NON_SELF_INTERSECTING;
        }
        return null;
    }

    private static TriangleMesh2d m5011a(SortedVertexMapping sortedVertexMapping, List<Object> list) {
        Polygon2dTessellator a = Polygon2dTessellator.m5009a(sortedVertexMapping, new EdgeList((VertexMapping) sortedVertexMapping), 0, null, null);
        switch (Polygon2dTessellator.f3123b[a.f3128a.ordinal()]) {
            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                return TriangleMesh2d.m5060a();
            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                TriangleMesh2d a2 = TriangleMesh2d.m5061a(a.f3129b);
                Polygon2dTessellator.m5012a(a.f3129b, a2, null, 0);
                return a2;
            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                VertexMapping vertexMapping = a.f3129b;
                EdgeList edgeList = a.f3130c;
                int i = a.f3131d;
                int i2 = a.f3132e;
                AbstractVertexList abstractVertexList = a.f3133f;
                List list2 = a.f3134g;
                TriangleMesh2d a3 = TriangleMesh2d.m5061a(vertexMapping);
                LinkedList b = ar.m2524b();
                Object obj = null;
                int i3 = 0;
                int[] iArr = null;
                Polygon2dTessellator polygon2dTessellator = null;
                Polygon2dTessellator polygon2dTessellator2 = null;
                List list3 = list2;
                AbstractVertexList abstractVertexList2 = abstractVertexList;
                EdgeList edgeList2 = edgeList;
                VertexMapping vertexMapping2 = vertexMapping;
                int i4 = i2;
                int i5 = i;
                while (obj == null) {
                    int d;
                    int i6;
                    int e;
                    int i7;
                    int i8;
                    SortedVertexMapping sortedVertexMapping2;
                    EdgeList edgeList3;
                    EdgeList edgeList4;
                    SortedVertexMapping sortedVertexMapping3;
                    Polygon2dTessellator polygon2dTessellator3;
                    VertexMapping vertexMapping3;
                    EdgeList edgeList5;
                    AbstractVertexList abstractVertexList3;
                    List list4;
                    int[] iArr2;
                    if (list3 != null) {
                        list3.add(new double[]{vertexMapping2.m4959b(i5), vertexMapping2.m4963c(i5), vertexMapping2.m4959b(i4), vertexMapping2.m4963c(i4)});
                    }
                    int i9 = i5 > i4 ? i4 : i5;
                    int i10 = i5 > i4 ? i5 : i4;
                    i2 = 0;
                    int i11 = 0;
                    Object obj2 = null;
                    if (vertexMapping2.m4975g(i9, i10) == 0) {
                        obj2 = 1;
                        d = vertexMapping2.m4967d(i10);
                        i6 = i9;
                        e = vertexMapping2.m4969e(i10);
                        i7 = i9;
                        i8 = d;
                    } else {
                        if (vertexMapping2.m4975g(i9, vertexMapping2.m4967d(i10)) == 0) {
                            obj2 = 1;
                            i8 = vertexMapping2.m4967d(i10);
                            i6 = i9;
                            e = i10;
                            i7 = i9;
                        } else {
                            if (vertexMapping2.m4975g(i9, vertexMapping2.m4969e(i10)) == 0) {
                                obj2 = 1;
                                i6 = i9;
                                e = vertexMapping2.m4969e(i10);
                                i7 = i9;
                                i8 = i10;
                            } else {
                                if (vertexMapping2.m4975g(vertexMapping2.m4967d(i9), i10) == 0) {
                                    obj2 = 1;
                                    i6 = vertexMapping2.m4967d(i9);
                                    e = i10;
                                    i7 = i9;
                                    i8 = i10;
                                } else {
                                    if (vertexMapping2.m4975g(vertexMapping2.m4969e(i9), i10) == 0) {
                                        obj2 = 1;
                                        i6 = i9;
                                        e = i10;
                                        i7 = vertexMapping2.m4969e(i9);
                                        i8 = i10;
                                    } else {
                                        i6 = i9;
                                        e = i10;
                                        i8 = i10;
                                        i7 = i9;
                                    }
                                }
                            }
                        }
                    }
                    if (obj2 == null) {
                        SortedVertexMapping b2 = vertexMapping2.m4990b(i9, i10);
                        EdgeList a4 = edgeList2.m4942a(b2, i9, i10);
                        int h = b2.m4977h(i5 - i9, 0);
                        SortedVertexMapping b3 = vertexMapping2.m4990b(i10, i9);
                        EdgeList a5 = edgeList2.m4942a(b3, i10, i9);
                        int i12 = (i10 - i9) - 1;
                        int i13 = i4 <= i9 ? i4 : i4 - i12;
                        int i14 = i5 <= i9 ? i5 : i5 - i12;
                        int h2 = b3.m4977h(i14, 0);
                        int d2 = vertexMapping2.m4967d(i5);
                        i8 = vertexMapping2.m4969e(i5);
                        SortedVertexMapping sortedVertexMapping4;
                        switch (Polygon2dTessellator.f3122a[abstractVertexList2.ordinal()]) {
                            case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                                if (d2 < i9 || d2 > i10) {
                                    if (b2.m4972f(i5 - i9) == AbstractVertexList.END_VERTEX) {
                                        sortedVertexMapping2 = b3;
                                        i2 = h + 1;
                                        edgeList3 = a4;
                                        i11 = h2 + 1;
                                        edgeList4 = a5;
                                        sortedVertexMapping3 = b2;
                                        break;
                                    }
                                    throw new NullPointerException("Impossible case in cutAndTessellate.");
                                }
                                i8 = h + 1;
                                if (b3.m4972f(i14) == AbstractVertexList.END_VERTEX) {
                                    edgeList3 = a4;
                                    i11 = h2 + 1;
                                    edgeList4 = a5;
                                    sortedVertexMapping3 = b2;
                                    sortedVertexMapping4 = b3;
                                    i2 = i8;
                                    sortedVertexMapping2 = sortedVertexMapping4;
                                    break;
                                }
                                throw new NullPointerException("Impossible case in cutAndTessellate.");
                            case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                                if (d2 >= i9 && d2 <= i10) {
                                    if (b2.m4972f(i5 - i9) == AbstractVertexList.END_VERTEX) {
                                        sortedVertexMapping2 = b3;
                                        i2 = h + 1;
                                        edgeList3 = a4;
                                        i11 = h2 + 1;
                                        edgeList4 = a5;
                                        sortedVertexMapping3 = b2;
                                        break;
                                    }
                                    throw new NullPointerException("Impossible case in cutAndTessellate.");
                                }
                                i8 = h + 1;
                                if (b3.m4972f(i14) == AbstractVertexList.END_VERTEX) {
                                    edgeList3 = a4;
                                    i11 = h2 + 1;
                                    edgeList4 = a5;
                                    sortedVertexMapping3 = b2;
                                    sortedVertexMapping4 = b3;
                                    i2 = i8;
                                    sortedVertexMapping2 = sortedVertexMapping4;
                                    break;
                                }
                                throw new NullPointerException("Impossible case in cutAndTessellate.");
                            case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                                a4.m4950d(i4 - i9, i5 - i9);
                                a5.m4950d(i13, i14);
                                edgeList4 = a5;
                                edgeList3 = a4;
                                sortedVertexMapping2 = b3;
                                sortedVertexMapping3 = b2;
                                i11 = h2;
                                i2 = h;
                                break;
                            case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                                if (d2 >= i9 && d2 <= i10) {
                                    if (!edgeList2.m4949c(d2, i5)) {
                                        if (b2.m4972f(i5 - i9) == AbstractVertexList.END_VERTEX) {
                                            sortedVertexMapping2 = b3;
                                            i2 = h + 1;
                                            edgeList3 = a4;
                                            i11 = h2 + 1;
                                            edgeList4 = a5;
                                            sortedVertexMapping3 = b2;
                                            break;
                                        }
                                        throw new NullPointerException("Impossible case in cutAndTessellate.");
                                    }
                                    a4.m4950d(i4 - i9, i5 - i9);
                                    if (b3.m4972f(i14) == AbstractVertexList.END_VERTEX) {
                                        edgeList3 = a4;
                                        sortedVertexMapping2 = b3;
                                        i11 = h2 + 1;
                                        i2 = h;
                                        edgeList4 = a5;
                                        sortedVertexMapping3 = b2;
                                        break;
                                    }
                                    throw new NullPointerException("Impossible case in cutAndTessellate.");
                                } else if (!edgeList2.m4949c(d2, i5)) {
                                    if (b3.m4972f(i14) == AbstractVertexList.END_VERTEX) {
                                        sortedVertexMapping2 = b3;
                                        i2 = h + 1;
                                        edgeList3 = a4;
                                        i11 = h2 + 1;
                                        edgeList4 = a5;
                                        sortedVertexMapping3 = b2;
                                        break;
                                    }
                                    throw new NullPointerException("Impossible case in cutAndTessellate.");
                                } else if (b2.m4972f(i5 - i9) == AbstractVertexList.END_VERTEX) {
                                    i10 = h + 1;
                                    a5.m4950d(i13, i14);
                                    edgeList3 = a4;
                                    sortedVertexMapping2 = b3;
                                    i11 = h2;
                                    i2 = i10;
                                    edgeList4 = a5;
                                    sortedVertexMapping3 = b2;
                                    break;
                                } else {
                                    throw new NullPointerException("Impossible case in cutAndTessellate.");
                                }
                                break;
                            case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                                throw new NullPointerException("Impossible case in cutAndTessellate.");
                            case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                                if (i8 < i9 || i8 > i10) {
                                    if (a5.m4949c(i8 <= i9 ? i8 : i8 - i12, i14)) {
                                        if (i8 > i9) {
                                            i8 -= i12;
                                        }
                                        if (a5.m4946b(i8, i14, AbstractVertexList.END_VERTEX) != -1) {
                                            throw new NullPointerException("Impossible case in cutAndTessellate.");
                                        }
                                    }
                                    throw new NullPointerException("Impossible case in cutAndTessellate.");
                                } else if (!a4.m4949c(i8 - i9, i5 - i9)) {
                                    throw new NullPointerException("Impossible case in cutAndTessellate.");
                                } else if (a4.m4946b(i8 - i9, i5 - i9, AbstractVertexList.END_VERTEX) != -1) {
                                    throw new NullPointerException("Impossible case in cutAndTessellate.");
                                }
                                sortedVertexMapping2 = b3;
                                i2 = h + 1;
                                edgeList3 = a4;
                                i11 = h2 + 1;
                                edgeList4 = a5;
                                sortedVertexMapping3 = b2;
                                break;
                            default:
                                edgeList4 = a5;
                                edgeList3 = a4;
                                sortedVertexMapping2 = b3;
                                sortedVertexMapping3 = b2;
                                i11 = h2;
                                i2 = h;
                                break;
                        }
                    }
                    sortedVertexMapping3 = vertexMapping2.m4990b(i7, i8);
                    sortedVertexMapping2 = vertexMapping2.m4990b(e, i6);
                    edgeList3 = new EdgeList((VertexMapping) sortedVertexMapping3);
                    edgeList4 = new EdgeList((VertexMapping) sortedVertexMapping2);
                    a3.m5067b();
                    Polygon2dTessellator a6 = Polygon2dTessellator.m5009a(sortedVertexMapping3, edgeList3, i2, list3, polygon2dTessellator2);
                    Polygon2dTessellator a7 = Polygon2dTessellator.m5009a(sortedVertexMapping2, edgeList4, i11, list3, polygon2dTessellator);
                    if (iArr != null) {
                        i11 = sortedVertexMapping3.m4968e();
                        if (a6.f3135h == null || a6.f3135h.length < i11) {
                            a6.f3135h = new int[i11];
                        }
                        for (d = 0; d < i11; d++) {
                            a6.f3135h[d] = iArr[i7 + d];
                        }
                    } else {
                        a6.f3135h = null;
                        a6.f3136i = i3 + i7;
                    }
                    d = sortedVertexMapping2.m4968e();
                    if (a7.f3135h == null || a7.f3135h.length < d) {
                        a7.f3135h = new int[d];
                    }
                    if (iArr != null) {
                        System.arraycopy(iArr, 0, a7.f3135h, 0, i6 + 1);
                        if (e < iArr.length) {
                            System.arraycopy(iArr, e, a7.f3135h, i6 + 1, (d - i6) - 1);
                        }
                    } else {
                        for (i8 = 0; i8 < i6 + 1; i8++) {
                            a7.f3135h[i8] = i8 + i3;
                        }
                        i11 = ((e - i6) - 1) + i3;
                        for (i8 = i6 + 1; i8 < d; i8++) {
                            a7.f3135h[i8] = i8 + i11;
                        }
                    }
                    if (a6.f3128a != Polygon2dTessellator.CUT_AND_TESSELLATE || a7.f3128a == Polygon2dTessellator.CUT_AND_TESSELLATE) {
                        Polygon2dTessellator polygon2dTessellator4 = a7;
                        a7 = a6;
                        a6 = polygon2dTessellator4;
                    }
                    switch (Polygon2dTessellator.f3123b[a7.f3128a.ordinal()]) {
                        case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                            polygon2dTessellator3 = a7;
                            break;
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                            Polygon2dTessellator.m5012a(a7.f3129b, a3, a7.f3135h, a7.f3136i);
                            polygon2dTessellator3 = a7;
                            break;
                        case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                            b.add(a7);
                            polygon2dTessellator3 = null;
                            break;
                        default:
                            throw new Polygon2dTessellator("Unknown subdivide result in tessellation");
                    }
                    switch (Polygon2dTessellator.f3123b[a6.f3128a.ordinal()]) {
                        case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                            break;
                        case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                            Polygon2dTessellator.m5012a(a6.f3129b, a3, a6.f3135h, 0);
                            break;
                        case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                            vertexMapping3 = a6.f3129b;
                            edgeList5 = a6.f3130c;
                            i2 = a6.f3131d;
                            i11 = a6.f3132e;
                            abstractVertexList3 = a6.f3133f;
                            list4 = a6.f3134g;
                            iArr2 = a6.f3135h;
                            i3 = a6.f3136i;
                            iArr = iArr2;
                            polygon2dTessellator = a6;
                            polygon2dTessellator2 = polygon2dTessellator3;
                            list3 = list4;
                            abstractVertexList2 = abstractVertexList3;
                            edgeList2 = edgeList5;
                            vertexMapping2 = vertexMapping3;
                            i4 = i11;
                            i5 = i2;
                            continue;
                        default:
                            throw new Polygon2dTessellator("Unknown subdivide result in tessellation");
                    }
                    if (b.isEmpty()) {
                        obj = 1;
                        polygon2dTessellator = a6;
                        polygon2dTessellator2 = polygon2dTessellator3;
                    } else {
                        a7 = (Polygon2dTessellator) b.poll();
                        vertexMapping3 = a7.f3129b;
                        edgeList5 = a7.f3130c;
                        i2 = a7.f3131d;
                        i11 = a7.f3132e;
                        abstractVertexList3 = a7.f3133f;
                        list4 = a7.f3134g;
                        iArr2 = a7.f3135h;
                        i3 = a7.f3136i;
                        iArr = iArr2;
                        polygon2dTessellator = a6;
                        polygon2dTessellator2 = polygon2dTessellator3;
                        list3 = list4;
                        abstractVertexList2 = abstractVertexList3;
                        edgeList2 = edgeList5;
                        vertexMapping2 = vertexMapping3;
                        i4 = i11;
                        i5 = i2;
                    }
                }
                return a3;
            default:
                throw new Polygon2dTessellator("Unknown subdivide result in tessellation");
        }
    }

    private static Polygon2dTessellator m5009a(SortedVertexMapping sortedVertexMapping, EdgeList edgeList, int i, List<Object> list, Polygon2dTessellator polygon2dTessellator) {
        Polygon2dTessellator polygon2dTessellator2;
        if (polygon2dTessellator == null) {
            polygon2dTessellator2 = new Polygon2dTessellator();
        } else {
            polygon2dTessellator2 = polygon2dTessellator;
        }
        if (sortedVertexMapping.m4968e() < 3) {
            return polygon2dTessellator2.m5008a(Polygon2dTessellator.EMPTY_MESH, null, null, 0, 0, null, null);
        }
        while (i < sortedVertexMapping.c) {
            int j = sortedVertexMapping.m4980j(i);
            int d = sortedVertexMapping.m4967d(j);
            int e = sortedVertexMapping.m4969e(j);
            AbstractVertexList f = sortedVertexMapping.m4972f(j);
            int a;
            switch (Polygon2dTessellator.f3122a[f.ordinal()]) {
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    a = edgeList.m4941a(d, j, f);
                    if (a == -1) {
                        a = edgeList.m4946b(j, e, f);
                        if (a == -1) {
                            break;
                        }
                        return polygon2dTessellator2.m5008a(Polygon2dTessellator.CUT_AND_TESSELLATE, sortedVertexMapping, edgeList, j, a, f, list);
                    }
                    return polygon2dTessellator2.m5008a(Polygon2dTessellator.CUT_AND_TESSELLATE, sortedVertexMapping, edgeList, j, a, f, list);
                case R.SlidingUpPanelLayout_umanoParalaxOffset /*2*/:
                    a = edgeList.m4941a(j, e, f);
                    if (a == -1) {
                        a = edgeList.m4946b(d, j, f);
                        if (a == -1) {
                            break;
                        }
                        return polygon2dTessellator2.m5008a(Polygon2dTessellator.CUT_AND_TESSELLATE, sortedVertexMapping, edgeList, j, a, f, list);
                    }
                    return polygon2dTessellator2.m5008a(Polygon2dTessellator.CUT_AND_TESSELLATE, sortedVertexMapping, edgeList, j, a, f, list);
                case R.SlidingUpPanelLayout_umanoFadeColor /*3*/:
                    return polygon2dTessellator2.m5008a(Polygon2dTessellator.CUT_AND_TESSELLATE, sortedVertexMapping, edgeList, j, edgeList.m4945b(j), f, list);
                case R.SlidingUpPanelLayout_umanoFlingVelocity /*4*/:
                    a = edgeList.m4946b(j, e, f);
                    if (a == -1) {
                        a = edgeList.m4946b(d, j, f);
                        if (a == -1) {
                            break;
                        }
                        return polygon2dTessellator2.m5008a(Polygon2dTessellator.CUT_AND_TESSELLATE, sortedVertexMapping, edgeList, j, a, f, list);
                    }
                    return polygon2dTessellator2.m5008a(Polygon2dTessellator.CUT_AND_TESSELLATE, sortedVertexMapping, edgeList, j, a, f, list);
                case R.SlidingUpPanelLayout_umanoDragView /*5*/:
                    edgeList.m4943a(d, j, e);
                    break;
                case R.SlidingUpPanelLayout_umanoOverlay /*6*/:
                    a = edgeList.m4946b(d, j, f);
                    if (a == -1) {
                        a = edgeList.m4946b(j, e, f);
                        if (a == -1) {
                            break;
                        }
                        return polygon2dTessellator2.m5008a(Polygon2dTessellator.CUT_AND_TESSELLATE, sortedVertexMapping, edgeList, j, a, f, list);
                    }
                    return polygon2dTessellator2.m5008a(Polygon2dTessellator.CUT_AND_TESSELLATE, sortedVertexMapping, edgeList, j, a, f, list);
                default:
                    break;
            }
            i++;
        }
        return polygon2dTessellator2.m5008a(Polygon2dTessellator.TESSELLATE_MONOTONE, sortedVertexMapping, null, 0, 0, null, null);
    }

    private static void m5012a(SortedVertexMapping sortedVertexMapping, TriangleMesh2d triangleMesh2d, int[] iArr, int i) {
        int e = sortedVertexMapping.m4968e();
        if (e >= 3) {
            VertexMapping c = sortedVertexMapping.m4965c();
            int j = sortedVertexMapping.m4980j(0);
            int j2 = sortedVertexMapping.m4980j(1);
            c.m4962b(j);
            c.m4962b(j2);
            int i2 = 2;
            while (i2 < e) {
                int j3 = sortedVertexMapping.m4980j(i2);
                int f;
                if (sortedVertexMapping.m4981k(i2)) {
                    f = c.m4971f();
                    while (c.f3119c > 1 && sortedVertexMapping.m4955a(j3, j2, f) > 0.0d) {
                        Polygon2dTessellator.m5013a(triangleMesh2d, j3, j2, f, iArr, i);
                        c.m4961b();
                        j2 = f;
                        f = c.m4971f();
                    }
                    c.m4962b(j3);
                } else if (sortedVertexMapping.m4982l(i2)) {
                    f = c.m4971f();
                    int i3 = j2;
                    while (c.f3119c > 1 && sortedVertexMapping.m4955a(f, i3, j3) > 0.0d) {
                        Polygon2dTessellator.m5013a(triangleMesh2d, f, i3, j3, iArr, i);
                        c.m4961b();
                        i3 = f;
                        f = c.m4971f();
                    }
                    c.m4962b(j3);
                } else {
                    f = c.m4980j(0);
                    int i4 = 1;
                    while (i4 < c.f3119c) {
                        j2 = c.m4980j(i4);
                        if (sortedVertexMapping.m4955a(f, j2, j3) < 0.0d) {
                            Polygon2dTessellator.m5013a(triangleMesh2d, j3, j2, f, iArr, i);
                        } else {
                            Polygon2dTessellator.m5013a(triangleMesh2d, f, j2, j3, iArr, i);
                        }
                        i4++;
                        f = j2;
                    }
                    c.m4956a();
                    c.m4962b(f);
                    c.m4962b(j3);
                }
                i2++;
                j2 = j3;
            }
        }
    }

    private static void m5013a(TriangleMesh2d triangleMesh2d, int i, int i2, int i3, int[] iArr, int i4) {
        if (iArr != null) {
            triangleMesh2d.m5064a(iArr[i], iArr[i2], iArr[i3]);
        } else {
            triangleMesh2d.m5064a(i + i4, i2 + i4, i3 + i4);
        }
    }
}
