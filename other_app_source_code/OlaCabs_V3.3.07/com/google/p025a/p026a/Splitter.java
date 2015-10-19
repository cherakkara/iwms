package com.google.p025a.p026a;

import java.util.Iterator;

/* renamed from: com.google.a.a.l */
public final class Splitter {
    private final CharMatcher f1377a;
    private final boolean f1378b;
    private final Splitter f1379c;
    private final int f1380d;

    /* renamed from: com.google.a.a.l.a */
    private static abstract class Splitter extends AbstractIterator<String> {
        final CharSequence f1368b;
        final CharMatcher f1369c;
        final boolean f1370d;
        int f1371e;
        int f1372f;

        abstract int m1843a(int i);

        abstract int m1845b(int i);

        protected /* synthetic */ Object m1844a() {
            return m1846c();
        }

        protected Splitter(Splitter splitter, CharSequence charSequence) {
            this.f1371e = 0;
            this.f1369c = splitter.f1377a;
            this.f1370d = splitter.f1378b;
            this.f1372f = splitter.f1380d;
            this.f1368b = charSequence;
        }

        protected String m1846c() {
            int i = this.f1371e;
            while (this.f1371e != -1) {
                int a = m1843a(this.f1371e);
                if (a == -1) {
                    a = this.f1368b.length();
                    this.f1371e = -1;
                } else {
                    this.f1371e = m1845b(a);
                }
                if (this.f1371e == i) {
                    this.f1371e++;
                    if (this.f1371e >= this.f1368b.length()) {
                        this.f1371e = -1;
                    }
                } else {
                    int i2 = i;
                    while (i2 < a && this.f1369c.m1741a(this.f1368b.charAt(i2))) {
                        i2++;
                    }
                    i = a;
                    while (i > i2 && this.f1369c.m1741a(this.f1368b.charAt(i - 1))) {
                        i--;
                    }
                    if (this.f1370d && i2 == i) {
                        i = this.f1371e;
                    } else {
                        if (this.f1372f == 1) {
                            i = this.f1368b.length();
                            this.f1371e = -1;
                            while (i > i2 && this.f1369c.m1741a(this.f1368b.charAt(i - 1))) {
                                i--;
                            }
                        } else {
                            this.f1372f--;
                        }
                        return this.f1368b.subSequence(i2, i).toString();
                    }
                }
            }
            return (String) m1730b();
        }
    }

    /* renamed from: com.google.a.a.l.b */
    private interface Splitter {
        Iterator<String> m1849b(Splitter splitter, CharSequence charSequence);
    }

    /* renamed from: com.google.a.a.l.1 */
    static class Splitter implements Splitter {
        final /* synthetic */ int f1374a;

        /* renamed from: com.google.a.a.l.1.1 */
        class Splitter extends Splitter {
            final /* synthetic */ Splitter f1373a;

            Splitter(Splitter splitter, Splitter splitter2, CharSequence charSequence) {
                this.f1373a = splitter;
                super(splitter2, charSequence);
            }

            public int m1847a(int i) {
                int i2 = this.f1373a.f1374a + i;
                return i2 < this.b.length() ? i2 : -1;
            }

            public int m1848b(int i) {
                return i;
            }
        }

        Splitter(int i) {
            this.f1374a = i;
        }

        public /* synthetic */ Iterator m1851b(Splitter splitter, CharSequence charSequence) {
            return m1850a(splitter, charSequence);
        }

        public Splitter m1850a(Splitter splitter, CharSequence charSequence) {
            return new Splitter(this, splitter, charSequence);
        }
    }

    /* renamed from: com.google.a.a.l.2 */
    class Splitter implements Iterable<String> {
        final /* synthetic */ CharSequence f1375a;
        final /* synthetic */ Splitter f1376b;

        Splitter(Splitter splitter, CharSequence charSequence) {
            this.f1376b = splitter;
            this.f1375a = charSequence;
        }

        public Iterator<String> iterator() {
            return this.f1376b.m1855b(this.f1375a);
        }

        public String toString() {
            return Joiner.m1780a(", ").m1789a(new StringBuilder().append('['), (Iterable) this).append(']').toString();
        }
    }

    private Splitter(Splitter splitter) {
        this(splitter, false, CharMatcher.f1332m, Integer.MAX_VALUE);
    }

    private Splitter(Splitter splitter, boolean z, CharMatcher charMatcher, int i) {
        this.f1379c = splitter;
        this.f1378b = z;
        this.f1377a = charMatcher;
        this.f1380d = i;
    }

    public static Splitter m1853a(int i) {
        Preconditions.m1823a(i > 0, (Object) "The length may not be less than 1");
        return new Splitter(new Splitter(i));
    }

    public Iterable<String> m1858a(CharSequence charSequence) {
        Preconditions.m1817a((Object) charSequence);
        return new Splitter(this, charSequence);
    }

    private Iterator<String> m1855b(CharSequence charSequence) {
        return this.f1379c.m1849b(this, charSequence);
    }
}
