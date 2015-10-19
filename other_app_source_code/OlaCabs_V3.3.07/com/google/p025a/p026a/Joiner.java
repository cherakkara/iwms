package com.google.p025a.p026a;

import com.sothree.slidinguppanel.p086a.R.R;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* renamed from: com.google.a.a.f */
public class Joiner {
    private final String f1344a;

    /* renamed from: com.google.a.a.f.1 */
    class Joiner extends Joiner {
        final /* synthetic */ String f1345a;
        final /* synthetic */ Joiner f1346b;

        Joiner(Joiner joiner, Joiner joiner2, String str) {
            this.f1346b = joiner;
            this.f1345a = str;
            super(null);
        }

        CharSequence m1793a(@Nullable Object obj) {
            return obj == null ? this.f1345a : this.f1346b.m1784a(obj);
        }

        public Joiner m1794b(String str) {
            Preconditions.m1817a((Object) str);
            throw new UnsupportedOperationException("already specified useForNull");
        }
    }

    /* renamed from: com.google.a.a.f.2 */
    static class Joiner extends AbstractList<Object> {
        final /* synthetic */ Object[] f1347a;
        final /* synthetic */ Object f1348b;
        final /* synthetic */ Object f1349c;

        Joiner(Object[] objArr, Object obj, Object obj2) {
            this.f1347a = objArr;
            this.f1348b = obj;
            this.f1349c = obj2;
        }

        public int size() {
            return this.f1347a.length + 2;
        }

        public Object get(int i) {
            switch (i) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    return this.f1348b;
                case R.SlidingUpPanelLayout_umanoShadowHeight /*1*/:
                    return this.f1349c;
                default:
                    return this.f1347a[i - 2];
            }
        }
    }

    /* renamed from: com.google.a.a.f.a */
    public static final class Joiner {
        private final Joiner f1350a;
        private final String f1351b;

        private Joiner(Joiner joiner, String str) {
            this.f1350a = joiner;
            this.f1351b = (String) Preconditions.m1817a((Object) str);
        }

        public StringBuilder m1798a(StringBuilder stringBuilder, Map<?, ?> map) {
            return m1796a(stringBuilder, map.entrySet());
        }

        public <A extends Appendable> A m1795a(A a, Iterator<? extends Entry<?, ?>> it) throws IOException {
            Preconditions.m1817a((Object) a);
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                a.append(this.f1350a.m1784a(entry.getKey()));
                a.append(this.f1351b);
                a.append(this.f1350a.m1784a(entry.getValue()));
                while (it.hasNext()) {
                    a.append(this.f1350a.f1344a);
                    entry = (Entry) it.next();
                    a.append(this.f1350a.m1784a(entry.getKey()));
                    a.append(this.f1351b);
                    a.append(this.f1350a.m1784a(entry.getValue()));
                }
            }
            return a;
        }

        public StringBuilder m1796a(StringBuilder stringBuilder, Iterable<? extends Entry<?, ?>> iterable) {
            return m1797a(stringBuilder, iterable.iterator());
        }

        public StringBuilder m1797a(StringBuilder stringBuilder, Iterator<? extends Entry<?, ?>> it) {
            try {
                m1795a((Appendable) stringBuilder, (Iterator) it);
                return stringBuilder;
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    public static Joiner m1780a(String str) {
        return new Joiner(str);
    }

    public static Joiner m1779a(char c) {
        return new Joiner(String.valueOf(c));
    }

    private Joiner(String str) {
        this.f1344a = (String) Preconditions.m1817a((Object) str);
    }

    private Joiner(Joiner joiner) {
        this.f1344a = joiner.f1344a;
    }

    public <A extends Appendable> A m1783a(A a, Iterator<?> it) throws IOException {
        Preconditions.m1817a((Object) a);
        if (it.hasNext()) {
            a.append(m1784a(it.next()));
            while (it.hasNext()) {
                a.append(this.f1344a);
                a.append(m1784a(it.next()));
            }
        }
        return a;
    }

    public final StringBuilder m1789a(StringBuilder stringBuilder, Iterable<?> iterable) {
        return m1790a(stringBuilder, iterable.iterator());
    }

    public final StringBuilder m1790a(StringBuilder stringBuilder, Iterator<?> it) {
        try {
            m1783a((Appendable) stringBuilder, (Iterator) it);
            return stringBuilder;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final String m1785a(Iterable<?> iterable) {
        return m1787a(iterable.iterator());
    }

    public final String m1787a(Iterator<?> it) {
        return m1790a(new StringBuilder(), (Iterator) it).toString();
    }

    public final String m1788a(Object[] objArr) {
        return m1785a(Arrays.asList(objArr));
    }

    public final String m1786a(@Nullable Object obj, @Nullable Object obj2, Object... objArr) {
        return m1785a(Joiner.m1782b(obj, obj2, objArr));
    }

    @CheckReturnValue
    public Joiner m1791b(String str) {
        Preconditions.m1817a((Object) str);
        return new Joiner(this, this, str);
    }

    @CheckReturnValue
    public Joiner m1792c(String str) {
        return new Joiner(str, null);
    }

    CharSequence m1784a(Object obj) {
        Preconditions.m1817a(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    private static Iterable<Object> m1782b(Object obj, Object obj2, Object[] objArr) {
        Preconditions.m1817a((Object) objArr);
        return new Joiner(objArr, obj, obj2);
    }
}
