package com.google.android.m4b.maps.p054p;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: com.google.android.m4b.maps.p.d */
public final class IntMap {
    private Object[] f7685a;
    private HashMap<Integer, Object> f7686b;
    private int f7687c;
    private int f7688d;
    private int f7689e;

    /* renamed from: com.google.android.m4b.maps.p.d.a */
    public class IntMap {
        private int f7681a;
        private int f7682b;
        private Iterator<Integer> f7683c;
        private /* synthetic */ IntMap f7684d;

        public IntMap(IntMap intMap) {
            this.f7684d = intMap;
            this.f7681a = 0;
            this.f7682b = ExploreByTouchHelper.INVALID_ID;
            this.f7683c = null;
        }

        public final boolean m11066a() {
            if (this.f7682b != ExploreByTouchHelper.INVALID_ID) {
                return true;
            }
            if (this.f7681a <= this.f7684d.f7687c) {
                while (this.f7681a <= this.f7684d.f7687c) {
                    if (this.f7684d.f7685a[this.f7681a] != null) {
                        int i = this.f7681a;
                        this.f7681a = i + 1;
                        this.f7682b = i;
                        return true;
                    }
                    this.f7681a++;
                }
            }
            if (this.f7684d.f7686b != null) {
                if (this.f7683c == null) {
                    this.f7683c = this.f7684d.f7686b.keySet().iterator();
                }
                if (this.f7683c.hasNext()) {
                    this.f7682b = ((Integer) this.f7683c.next()).intValue();
                    return true;
                }
            }
            return false;
        }

        public final int m11067b() {
            if (m11066a()) {
                int i = this.f7682b;
                this.f7682b = ExploreByTouchHelper.INVALID_ID;
                return i;
            }
            throw new NoSuchElementException();
        }
    }

    public IntMap() {
        this(16);
    }

    private IntMap(int i) {
        this.f7685a = new Object[Math.min(16, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS)];
        this.f7689e = 0;
        this.f7688d = ExploreByTouchHelper.INVALID_ID;
        this.f7687c = ExploreByTouchHelper.INVALID_ID;
    }

    public final IntMap m11071a() {
        return new IntMap(this);
    }

    public final int m11074b() {
        return this.f7688d;
    }

    public final int m11076c() {
        return this.f7686b == null ? this.f7689e : this.f7689e + this.f7686b.size();
    }

    public final void m11077d() {
        for (int i = 0; i < this.f7685a.length; i++) {
            this.f7685a[i] = null;
        }
        if (this.f7686b != null) {
            this.f7686b.clear();
        }
        this.f7688d = ExploreByTouchHelper.INVALID_ID;
        this.f7687c = ExploreByTouchHelper.INVALID_ID;
        this.f7689e = 0;
    }

    public final Object m11072a(int i) {
        if (i <= this.f7687c && i >= 0) {
            return this.f7685a[i];
        }
        if (i > this.f7688d || this.f7686b == null) {
            return null;
        }
        return this.f7686b.get(Primitives.m11084a(i));
    }

    public final void m11073a(int i, Object obj) {
        int i2 = 0;
        if (obj == null) {
            m11075b(i);
            return;
        }
        if (i > this.f7688d) {
            this.f7688d = i;
        }
        if (i < 0 || i >= this.f7685a.length) {
            if (i < AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS && i >= this.f7685a.length && i > 0) {
                int length = this.f7685a.length;
                do {
                    length <<= 1;
                } while (length <= i);
                Object obj2 = new Object[Math.min(length, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS)];
                System.arraycopy(this.f7685a, 0, obj2, 0, this.f7685a.length);
                this.f7685a = obj2;
                i2 = 1;
            }
            if (i2 == 0) {
                if (this.f7686b == null) {
                    this.f7686b = new HashMap();
                }
                this.f7686b.put(Primitives.m11084a(i), obj);
                return;
            }
        }
        if (i > this.f7687c) {
            this.f7687c = i;
            this.f7689e++;
        } else if (this.f7685a[i] == null) {
            this.f7689e++;
        }
        this.f7685a[i] = obj;
    }

    public final Object m11075b(int i) {
        if (i < 0 || i >= this.f7685a.length) {
            return this.f7686b != null ? this.f7686b.remove(Primitives.m11084a(i)) : null;
        } else {
            Object obj = this.f7685a[i];
            if (obj != null) {
                this.f7689e--;
            }
            this.f7685a[i] = null;
            return obj;
        }
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.f7685a.length; i2++) {
            Object obj = this.f7685a[i2];
            if (obj != null) {
                i = ((i * 31) + obj.hashCode()) + i2;
            }
        }
        return this.f7686b == null ? i : i + this.f7686b.hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof IntMap)) {
            return false;
        }
        IntMap intMap = (IntMap) obj;
        if (m11076c() != intMap.m11076c()) {
            return false;
        }
        Object obj2;
        Object[] objArr = this.f7685a;
        Object[] objArr2 = intMap.f7685a;
        int min = Math.min(objArr.length, objArr2.length);
        int i = 0;
        while (i < min) {
            if ((objArr[i] == null && objArr2[i] != null) || (objArr[i] != null && !objArr[i].equals(objArr2[i]))) {
                obj2 = null;
                break;
            }
            i++;
        }
        if (objArr.length > objArr2.length) {
            while (min < objArr.length) {
                if (objArr[min] != null) {
                    obj2 = null;
                    break;
                }
                min++;
            }
        } else if (objArr.length < objArr2.length) {
            while (min < objArr2.length) {
                if (objArr2[min] != null) {
                    obj2 = null;
                    break;
                }
                min++;
            }
        }
        min = 1;
        return obj2 != null && this.f7686b.equals(intMap.f7686b);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("IntMap{lower:");
        for (int i = 0; i < this.f7685a.length; i++) {
            if (this.f7685a[i] != null) {
                stringBuilder.append(i);
                stringBuilder.append("=>");
                stringBuilder.append(this.f7685a[i]);
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(", higher:" + this.f7686b + "}");
        return stringBuilder.toString();
    }
}
