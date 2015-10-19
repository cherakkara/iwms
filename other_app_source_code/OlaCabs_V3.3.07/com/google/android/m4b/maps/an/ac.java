package com.google.android.m4b.maps.an;

import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;

/* compiled from: TileCoords */
public class ac implements Comparable<ac> {
    final int f3381a;
    final int f3382b;
    final int f3383c;
    private final int f3384d;
    private final int f3385e;
    private final int f3386f;
    private final ag f3387g;
    private ac f3388h;
    private Rectangle2D f3389i;

    public /* synthetic */ int compareTo(Object obj) {
        ac acVar = (ac) obj;
        if (this.f3384d != acVar.f3384d) {
            return this.f3384d - acVar.f3384d;
        }
        if (this.f3385e == acVar.f3385e) {
            return this.f3386f == acVar.f3386f ? this.f3387g.m5469a(acVar.f3387g) : this.f3386f - acVar.f3386f;
        } else {
            return this.f3385e - acVar.f3385e;
        }
    }

    public ac(int i, int i2, int i3, ag agVar) {
        this.f3388h = null;
        this.f3384d = i;
        this.f3385e = i2;
        this.f3386f = i3;
        if (agVar == null) {
            agVar = new ag();
        }
        this.f3387g = agVar;
        this.f3383c = 18 - i;
        int i4 = 1073741824 >> i;
        this.f3381a = (this.f3385e * i4) - 536870912;
        this.f3382b = -((i4 * (this.f3386f + 1)) - 536870912);
    }

    public ac(int i, int i2, int i3) {
        this(i, i2, i3, null);
    }

    public final ac m5433a(int i, int i2, int i3) {
        return new ac(i, i2, i3, this.f3387g);
    }

    public final ac m5431a() {
        if (this.f3388h == null) {
            this.f3388h = new ac(this.f3384d, this.f3385e, this.f3386f, null);
        }
        return this.f3388h;
    }

    public static ac m5424a(int i, Point point) {
        int i2 = point.f3646a;
        int i3 = point.f3647b;
        if (i3 <= -536870912 || i3 > 536870912) {
            return null;
        }
        i2 = (i2 + 536870912) >> 0;
        int i4 = ((-i3) + 536870912) >> 0;
        if (i2 < 0) {
            i2 += 1073741824;
        } else if (i2 >= 1073741824) {
            i2 -= 1073741824;
        }
        return new ac(30, i2, i4);
    }

    private static ac m5423a(int i, int i2, int i3, ag agVar) {
        int i4 = 0;
        if (i <= 0) {
            return new ac(0, 0, 0);
        }
        if (i > 30) {
            i = 30;
        }
        int i5 = 30 - i;
        int i6 = (i2 + 536870912) >> i5;
        i5 = ((-i3) + 536870912) >> i5;
        int i7 = 1 << i;
        if (i6 < 0) {
            i6 += i7;
        } else if (i6 >= i7) {
            i6 -= i7;
        }
        if (i5 >= 0) {
            i4 = i5 >= i7 ? i7 - 1 : i5;
        }
        return new ac(i, i6, i4, agVar);
    }

    public static ac m5429b(int i, Point point) {
        return m5423a(15, point.f3646a, point.f3647b, null);
    }

    private static ac m5428b(int i, int i2, int i3, ag agVar) {
        if (i <= 0) {
            return new ac(0, 0, 0);
        }
        if (i > 30) {
            i = 30;
        }
        int i4 = 30 - i;
        return new ac(i, (i2 + 536870912) >> i4, ((-i3) + 536870912) >> i4, agVar);
    }

    public final int m5439b() {
        return this.f3384d;
    }

    public final int m5440c() {
        return this.f3385e;
    }

    public final int m5441d() {
        return this.f3386f;
    }

    public final int m5442e() {
        return this.f3381a;
    }

    public final int m5443f() {
        return this.f3382b;
    }

    public final Point m5444g() {
        return new Point(this.f3381a, this.f3382b);
    }

    public final Point m5445h() {
        int i = 1073741824 >> this.f3384d;
        return new Point(this.f3381a + i, i + this.f3382b);
    }

    public final Rectangle2D m5446i() {
        if (this.f3389i == null) {
            int i = 1073741824 >> this.f3384d;
            this.f3389i = new Rectangle2D(new Point(this.f3381a, this.f3382b), new Point(this.f3381a + i, i + this.f3382b));
        }
        return this.f3389i;
    }

    public final ac m5432a(int i) {
        int i2 = this.f3384d - i;
        return i2 <= 0 ? this : m5433a(i, this.f3385e >> i2, this.f3386f >> i2);
    }

    public final void m5438a(DataOutput dataOutput) {
        an.m5579a(dataOutput, this.f3384d);
        an.m5579a(dataOutput, this.f3385e);
        an.m5579a(dataOutput, this.f3386f);
    }

    public static ac m5425a(DataInput dataInput) {
        return new ac(an.m5578a(dataInput), an.m5578a(dataInput), an.m5578a(dataInput));
    }

    public static ArrayList<ac> m5426a(as asVar, int i) {
        return m5427a(asVar, 15, null);
    }

    public static ArrayList<ac> m5427a(as asVar, int i, ag agVar) {
        int i2 = 0;
        if (i < 0) {
            return new ArrayList(0);
        }
        int i3;
        ac a = m5423a(i, asVar.m5685f().f3646a, asVar.m5686g().f3647b, agVar);
        ac a2 = m5423a(i, asVar.m5686g().f3646a - 1, asVar.m5685f().f3647b + 1, agVar);
        int i4 = a.f3385e;
        int i5 = a.f3386f;
        int i6 = a2.f3385e;
        int i7 = a2.f3386f;
        int i8 = 1 << i;
        if (i4 > i6) {
            i3 = (((i8 - i4) + i6) + 1) * ((i7 - i5) + 1);
        } else {
            i3 = ((i6 - i4) + 1) * ((i7 - i5) + 1);
        }
        if (i3 < 0) {
            return new ArrayList();
        }
        ArrayList<ac> arrayList = new ArrayList(i3);
        if (i3 <= 2) {
            arrayList.add(a);
            if (i3 != 2) {
                return arrayList;
            }
            arrayList.add(a2);
            return arrayList;
        } else if (i4 > i6) {
            while (i4 < i8) {
                for (i3 = i5; i3 <= i7; i3++) {
                    arrayList.add(new ac(i, i4, i3, agVar));
                }
                i4++;
            }
            while (i2 <= i6) {
                for (i4 = i5; i4 <= i7; i4++) {
                    arrayList.add(new ac(i, i2, i4, agVar));
                }
                i2++;
            }
            return arrayList;
        } else {
            while (i4 <= i6) {
                for (i2 = i5; i2 <= i7; i2++) {
                    arrayList.add(new ac(i, i4, i2, agVar));
                }
                i4++;
            }
            return arrayList;
        }
    }

    public static ArrayList<ac> m5430b(as asVar, int i) {
        ac b = m5428b(i, asVar.m5685f().f3646a, asVar.m5686g().f3647b, null);
        ac b2 = m5428b(i, asVar.m5686g().f3646a - 1, asVar.m5685f().f3647b + 1, null);
        int i2 = b.f3385e;
        int i3 = b.f3386f;
        int i4 = b2.f3385e;
        int i5 = b2.f3386f;
        if (!Point.m5940e(b.f3381a, b.f3382b) && !Point.m5940e(b2.f3381a, b2.f3382b)) {
            return new ArrayList();
        }
        int i6 = 1 << i;
        ArrayList<ac> arrayList = new ArrayList();
        int i7;
        if (i2 > i4) {
            while (i2 < i6) {
                for (i7 = i3; i7 < 0; i7++) {
                    arrayList.add(new ac(i, i2, i7, null));
                }
                for (i7 = i6; i7 <= i5; i7++) {
                    arrayList.add(new ac(i, i2, i7, null));
                }
                i2++;
            }
            for (i7 = 0; i7 <= i4; i7++) {
                for (i2 = i3; i2 < 0; i2++) {
                    arrayList.add(new ac(i, i7, i2, null));
                }
                for (i2 = i6; i2 <= i5; i2++) {
                    arrayList.add(new ac(i, i7, i2, null));
                }
            }
            return arrayList;
        }
        while (i2 <= i4) {
            for (i7 = i3; i7 < 0; i7++) {
                arrayList.add(new ac(i, i2, i7, null));
            }
            for (i7 = i6; i7 <= i5; i7++) {
                arrayList.add(new ac(i, i2, i7, null));
            }
            i2++;
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ac)) {
            return false;
        }
        ac acVar = (ac) obj;
        if (this.f3385e == acVar.f3385e && this.f3386f == acVar.f3386f && this.f3384d == acVar.f3384d) {
            return this.f3387g.equals(acVar.f3387g);
        }
        return false;
    }

    public int hashCode() {
        int i = (((this.f3384d * 31) + this.f3385e) * 31) + this.f3386f;
        if (this.f3387g.m5475b()) {
            return i;
        }
        return (i * 31) + this.f3387g.hashCode();
    }

    public final af m5436a(TileParameters tileParameters) {
        return this.f3387g.m5470a(tileParameters);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        stringBuilder.append(this.f3384d).append(",").append(this.f3385e).append(",").append(this.f3386f).append(",").append(this.f3387g).append("]");
        return stringBuilder.toString();
    }

    public ac m5434a(ag agVar) {
        return new ac(this.f3384d, this.f3385e, this.f3386f, agVar);
    }

    public final ac m5435a(ai aiVar) {
        return m5434a(this.f3387g.m5471a(aiVar));
    }

    public final void m5437a(ai aiVar, ProtoBuf protoBuf) {
        this.f3387g.m5474a(aiVar, protoBuf);
    }

    public final ag m5447j() {
        return this.f3387g;
    }
}
