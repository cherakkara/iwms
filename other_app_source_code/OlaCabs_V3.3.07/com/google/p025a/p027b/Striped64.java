package com.google.p025a.p027b;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import sun.misc.Unsafe;

/* renamed from: com.google.a.b.m */
abstract class Striped64 extends Number {
    static final Striped64 f1558a;
    static final int f1559b;
    private static final Unsafe f1560f;
    private static final long f1561g;
    private static final long f1562h;
    volatile transient Striped64[] f1563c;
    volatile transient long f1564d;
    volatile transient int f1565e;

    /* renamed from: com.google.a.b.m.1 */
    static class Striped64 implements PrivilegedExceptionAction<Unsafe> {
        Striped64() {
        }

        public /* synthetic */ Object run() throws Exception {
            return m2278a();
        }

        public Unsafe m2278a() throws Exception {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return (Unsafe) declaredField.get(null);
        }
    }

    /* renamed from: com.google.a.b.m.a */
    static final class Striped64 {
        private static final Unsafe f1575b;
        private static final long f1576c;
        volatile long f1577a;

        /* renamed from: com.google.a.b.m.a.1 */
        static class Striped64 implements PrivilegedExceptionAction<Unsafe> {
            Striped64() {
            }

            public /* synthetic */ Object run() throws Exception {
                return m2279a();
            }

            public Unsafe m2279a() throws Exception {
                Class cls = Unsafe.class;
                for (Field field : cls.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object obj = field.get(null);
                    if (cls.isInstance(obj)) {
                        return (Unsafe) cls.cast(obj);
                    }
                }
                throw new NoSuchFieldError("the Unsafe");
            }
        }

        Striped64(long j) {
            this.f1577a = j;
        }

        final boolean m2281a(long j, long j2) {
            return f1575b.compareAndSwapLong(this, f1576c, j, j2);
        }

        static {
            try {
                f1575b = Striped64.m2280a();
                f1576c = f1575b.objectFieldOffset(Striped64.class.getDeclaredField("value"));
            } catch (Throwable e) {
                throw new Error(e);
            }
        }

        private static Unsafe m2280a() {
            Unsafe unsafe;
            try {
                unsafe = Unsafe.getUnsafe();
            } catch (SecurityException e) {
                try {
                    unsafe = (Unsafe) AccessController.doPrivileged(new Striped64());
                } catch (PrivilegedActionException e2) {
                    throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                }
            }
            return unsafe;
        }
    }

    /* renamed from: com.google.a.b.m.b */
    static final class Striped64 {
        static final Random f1578a;
        int f1579b;

        static {
            f1578a = new Random();
        }

        Striped64() {
            int nextInt = f1578a.nextInt();
            if (nextInt == 0) {
                nextInt = 1;
            }
            this.f1579b = nextInt;
        }
    }

    /* renamed from: com.google.a.b.m.c */
    static final class Striped64 extends ThreadLocal<Striped64> {
        Striped64() {
        }

        public /* synthetic */ Object initialValue() {
            return m2282a();
        }

        public Striped64 m2282a() {
            return new Striped64();
        }
    }

    abstract long m2264a(long j, long j2);

    static {
        f1558a = new Striped64();
        f1559b = Runtime.getRuntime().availableProcessors();
        try {
            f1560f = Striped64.m2263a();
            Class cls = Striped64.class;
            f1561g = f1560f.objectFieldOffset(cls.getDeclaredField("base"));
            f1562h = f1560f.objectFieldOffset(cls.getDeclaredField("busy"));
        } catch (Throwable e) {
            throw new Error(e);
        }
    }

    Striped64() {
    }

    final boolean m2266b(long j, long j2) {
        return f1560f.compareAndSwapLong(this, f1561g, j, j2);
    }

    final boolean m2267c() {
        return f1560f.compareAndSwapInt(this, f1562h, 0, 1);
    }

    final void m2265a(long j, Striped64 striped64, boolean z) {
        int i = striped64.f1579b;
        Object obj = null;
        while (true) {
            Object obj2;
            Striped64[] striped64Arr = this.f1563c;
            if (striped64Arr != null) {
                int length = striped64Arr.length;
                if (length > 0) {
                    Striped64 striped642 = striped64Arr[(length - 1) & i];
                    Striped64[] striped64Arr2;
                    if (striped642 != null) {
                        if (z) {
                            long j2 = striped642.f1577a;
                            if (striped642.m2281a(j2, m2264a(j2, j))) {
                                break;
                            } else if (length >= f1559b || this.f1563c != striped64Arr) {
                                obj = null;
                            } else if (obj == null) {
                                obj = 1;
                            } else if (this.f1565e == 0 && m2267c()) {
                                try {
                                    if (this.f1563c == striped64Arr) {
                                        striped64Arr2 = new Striped64[(length << 1)];
                                        for (int i2 = 0; i2 < length; i2++) {
                                            striped64Arr2[i2] = striped64Arr[i2];
                                        }
                                        this.f1563c = striped64Arr2;
                                    }
                                    this.f1565e = 0;
                                    obj = null;
                                } catch (Throwable th) {
                                    this.f1565e = 0;
                                }
                            }
                        } else {
                            z = true;
                        }
                    } else {
                        if (this.f1565e == 0) {
                            Striped64 striped643 = new Striped64(j);
                            if (this.f1565e == 0 && m2267c()) {
                                obj2 = null;
                                try {
                                    striped64Arr2 = this.f1563c;
                                    if (striped64Arr2 != null) {
                                        int length2 = striped64Arr2.length;
                                        if (length2 > 0) {
                                            length2 = (length2 - 1) & i;
                                            if (striped64Arr2[length2] == null) {
                                                striped64Arr2[length2] = striped643;
                                                obj2 = 1;
                                            }
                                        }
                                    }
                                    this.f1565e = 0;
                                    if (obj2 != null) {
                                        break;
                                    }
                                } catch (Throwable th2) {
                                    this.f1565e = 0;
                                }
                            }
                        }
                        obj = null;
                    }
                    i ^= i << 13;
                    i ^= i >>> 17;
                    i ^= i << 5;
                }
            }
            if (this.f1565e == 0 && this.f1563c == striped64Arr && m2267c()) {
                obj2 = null;
                try {
                    if (this.f1563c == striped64Arr) {
                        Striped64[] striped64Arr3 = new Striped64[2];
                        striped64Arr3[i & 1] = new Striped64(j);
                        this.f1563c = striped64Arr3;
                        obj2 = 1;
                    }
                    this.f1565e = 0;
                    if (obj2 != null) {
                        break;
                    }
                } catch (Throwable th3) {
                    this.f1565e = 0;
                }
            } else {
                long j3 = this.f1564d;
                if (m2266b(j3, m2264a(j3, j))) {
                    break;
                }
            }
        }
        striped64.f1579b = i;
    }

    private static Unsafe m2263a() {
        Unsafe unsafe;
        try {
            unsafe = Unsafe.getUnsafe();
        } catch (SecurityException e) {
            try {
                unsafe = (Unsafe) AccessController.doPrivileged(new Striped64());
            } catch (PrivilegedActionException e2) {
                throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
            }
        }
        return unsafe;
    }
}
