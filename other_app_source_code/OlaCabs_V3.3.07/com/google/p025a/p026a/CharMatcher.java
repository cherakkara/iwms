package com.google.p025a.p026a;

import com.google.a.a.c.AnonymousClass10;
import com.google.p025a.p026a.CharMatcher.CharMatcher;
import com.google.p025a.p026a.Preconditions;
import com.olacabs.customer.R.R;
import java.util.Arrays;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.a.a.c */
public abstract class CharMatcher implements Predicate<Character> {
    public static final CharMatcher f1320a;
    public static final CharMatcher f1321b;
    public static final CharMatcher f1322c;
    public static final CharMatcher f1323d;
    public static final CharMatcher f1324e;
    public static final CharMatcher f1325f;
    public static final CharMatcher f1326g;
    public static final CharMatcher f1327h;
    public static final CharMatcher f1328i;
    public static final CharMatcher f1329j;
    public static final CharMatcher f1330k;
    public static final CharMatcher f1331l;
    public static final CharMatcher f1332m;
    public static final CharMatcher f1333o;
    private static final String f1334p;
    final String f1335n;

    /* renamed from: com.google.a.a.c.a */
    static abstract class CharMatcher extends CharMatcher {
        public /* bridge */ /* synthetic */ boolean m1744a(Object obj) {
            return super.m1742a((Character) obj);
        }

        CharMatcher(String str) {
            super(str);
        }
    }

    /* compiled from: CharMatcher */
    /* renamed from: com.google.a.a.c.10 */
    static class AnonymousClass10 extends CharMatcher {
        AnonymousClass10(String str) {
            super(str);
        }

        public boolean m1746a(char c) {
            return false;
        }

        public com.google.p025a.p026a.CharMatcher m1745a(com.google.p025a.p026a.CharMatcher charMatcher) {
            return (com.google.p025a.p026a.CharMatcher) Preconditions.m1817a((Object) charMatcher);
        }
    }

    /* renamed from: com.google.a.a.c.1 */
    static class CharMatcher extends CharMatcher {
        CharMatcher() {
        }

        public boolean m1747a(char c) {
            switch (c) {
                case HTTP.HT /*9*/:
                case HTTP.LF /*10*/:
                case R.MapM4bAttrs_m4b_uiZoomControls /*11*/:
                case R.MapM4bAttrs_m4b_uiZoomGestures /*12*/:
                case HTTP.CR /*13*/:
                case HTTP.SP /*32*/:
                case '\u0085':
                case '\u1680':
                case '\u2028':
                case '\u2029':
                case '\u205f':
                case '\u3000':
                    return true;
                case '\u2007':
                    return false;
                default:
                    if (c < '\u2000' || c > '\u200a') {
                        return false;
                    }
                    return true;
            }
        }

        public String toString() {
            return "CharMatcher.BREAKING_WHITESPACE";
        }
    }

    /* renamed from: com.google.a.a.c.2 */
    static class CharMatcher extends CharMatcher {
        final /* synthetic */ char f1336p;
        final /* synthetic */ char f1337q;

        CharMatcher(String str, char c, char c2) {
            this.f1336p = c;
            this.f1337q = c2;
            super(str);
        }

        public boolean m1749a(char c) {
            return this.f1336p <= c && c <= this.f1337q;
        }
    }

    /* renamed from: com.google.a.a.c.3 */
    static class CharMatcher extends CharMatcher {
        CharMatcher(String str) {
            super(str);
        }

        public boolean m1750a(char c) {
            return "\u0001\u0000\u00a0\u0000\u0000\u0000\u0000\u0000\u0000\t\n\u000b\f\r\u0000\u0000\u2028\u2029\u0000\u0000\u0000\u0000\u0000\u202f\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000 \u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u3000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0085\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u0000\u0000\u0000\u0000\u0000\u205f\u1680\u0000\u0000\u180e\u0000\u0000\u0000".charAt(c % 79) == c;
        }
    }

    /* renamed from: com.google.a.a.c.4 */
    static class CharMatcher extends CharMatcher {
        CharMatcher(String str) {
            super(str);
        }

        public boolean m1751a(char c) {
            return Character.isDigit(c);
        }
    }

    /* renamed from: com.google.a.a.c.5 */
    static class CharMatcher extends CharMatcher {
        CharMatcher(String str) {
            super(str);
        }

        public boolean m1753a(char c) {
            return Character.isLetter(c);
        }
    }

    /* renamed from: com.google.a.a.c.6 */
    static class CharMatcher extends CharMatcher {
        CharMatcher(String str) {
            super(str);
        }

        public boolean m1755a(char c) {
            return Character.isLetterOrDigit(c);
        }
    }

    /* renamed from: com.google.a.a.c.7 */
    static class CharMatcher extends CharMatcher {
        CharMatcher(String str) {
            super(str);
        }

        public boolean m1757a(char c) {
            return Character.isUpperCase(c);
        }
    }

    /* renamed from: com.google.a.a.c.8 */
    static class CharMatcher extends CharMatcher {
        CharMatcher(String str) {
            super(str);
        }

        public boolean m1759a(char c) {
            return Character.isLowerCase(c);
        }
    }

    /* renamed from: com.google.a.a.c.9 */
    static class CharMatcher extends CharMatcher {
        CharMatcher(String str) {
            super(str);
        }

        public boolean m1762a(char c) {
            return true;
        }

        public CharMatcher m1761a(CharMatcher charMatcher) {
            Preconditions.m1817a((Object) charMatcher);
            return this;
        }
    }

    /* renamed from: com.google.a.a.c.b */
    private static class CharMatcher extends CharMatcher {
        final CharMatcher f1338p;
        final CharMatcher f1339q;

        CharMatcher(CharMatcher charMatcher, CharMatcher charMatcher2, String str) {
            super(str);
            this.f1338p = (CharMatcher) Preconditions.m1817a((Object) charMatcher);
            this.f1339q = (CharMatcher) Preconditions.m1817a((Object) charMatcher2);
        }

        CharMatcher(CharMatcher charMatcher, CharMatcher charMatcher2) {
            this(charMatcher, charMatcher2, "CharMatcher.or(" + charMatcher + ", " + charMatcher2 + ")");
        }

        public boolean m1764a(char c) {
            return this.f1338p.m1741a(c) || this.f1339q.m1741a(c);
        }

        CharMatcher m1763a(String str) {
            return new CharMatcher(this.f1338p, this.f1339q, str);
        }
    }

    /* renamed from: com.google.a.a.c.c */
    private static class CharMatcher extends CharMatcher {
        private final char[] f1340p;
        private final char[] f1341q;

        CharMatcher(String str, char[] cArr, char[] cArr2) {
            boolean z;
            super(str);
            this.f1340p = cArr;
            this.f1341q = cArr2;
            if (cArr.length == cArr2.length) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.m1822a(z);
            for (int i = 0; i < cArr.length; i++) {
                boolean z2;
                if (cArr[i] <= cArr2[i]) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                Preconditions.m1822a(z2);
                if (i + 1 < cArr.length) {
                    if (cArr2[i] < cArr[i + 1]) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    Preconditions.m1822a(z2);
                }
            }
        }

        public boolean m1766a(char c) {
            int binarySearch = Arrays.binarySearch(this.f1340p, c);
            if (binarySearch >= 0) {
                return true;
            }
            binarySearch = (binarySearch ^ -1) - 1;
            if (binarySearch < 0 || c > this.f1341q[binarySearch]) {
                return false;
            }
            return true;
        }
    }

    public abstract boolean m1741a(char c);

    static {
        f1320a = new CharMatcher();
        f1321b = CharMatcher.m1737a('\u0000', '\u007f', "CharMatcher.ASCII");
        StringBuilder stringBuilder = new StringBuilder("0\u0660\u06f0\u07c0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be6\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1090\u17e0\u1810\u1946\u19d0\u1b50\u1bb0\u1c40\u1c50\ua620\ua8d0\ua900\uaa50\uff10".length());
        for (int i = 0; i < "0\u0660\u06f0\u07c0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be6\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1090\u17e0\u1810\u1946\u19d0\u1b50\u1bb0\u1c40\u1c50\ua620\ua8d0\ua900\uaa50\uff10".length(); i++) {
            stringBuilder.append((char) ("0\u0660\u06f0\u07c0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be6\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1090\u17e0\u1810\u1946\u19d0\u1b50\u1bb0\u1c40\u1c50\ua620\ua8d0\ua900\uaa50\uff10".charAt(i) + 9));
        }
        f1334p = stringBuilder.toString();
        f1322c = new CharMatcher("CharMatcher.DIGIT", "0\u0660\u06f0\u07c0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be6\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1090\u17e0\u1810\u1946\u19d0\u1b50\u1bb0\u1c40\u1c50\ua620\ua8d0\ua900\uaa50\uff10".toCharArray(), f1334p.toCharArray());
        f1323d = new CharMatcher("CharMatcher.JAVA_DIGIT");
        f1324e = new CharMatcher("CharMatcher.JAVA_LETTER");
        f1325f = new CharMatcher("CharMatcher.JAVA_LETTER_OR_DIGIT");
        f1326g = new CharMatcher("CharMatcher.JAVA_UPPER_CASE");
        f1327h = new CharMatcher("CharMatcher.JAVA_LOWER_CASE");
        f1328i = CharMatcher.m1736a('\u0000', '\u001f').m1739a(CharMatcher.m1736a('\u007f', '\u009f')).m1740a("CharMatcher.JAVA_ISO_CONTROL");
        f1329j = new CharMatcher("CharMatcher.INVISIBLE", "\u0000\u007f\u00ad\u0600\u06dd\u070f\u1680\u180e\u2000\u2028\u205f\u206a\u3000\ud800\ufeff\ufff9\ufffa".toCharArray(), " \u00a0\u00ad\u0604\u06dd\u070f\u1680\u180e\u200f\u202f\u2064\u206f\u3000\uf8ff\ufeff\ufff9\ufffb".toCharArray());
        f1330k = new CharMatcher("CharMatcher.SINGLE_WIDTH", "\u0000\u05be\u05d0\u05f3\u0600\u0750\u0e00\u1e00\u2100\ufb50\ufe70\uff61".toCharArray(), "\u04f9\u05be\u05ea\u05f4\u06ff\u077f\u0e7f\u20af\u213a\ufdff\ufeff\uffdc".toCharArray());
        f1331l = new CharMatcher("CharMatcher.ANY");
        f1332m = new AnonymousClass10("CharMatcher.NONE");
        f1333o = new CharMatcher("CharMatcher.WHITESPACE");
    }

    private static String m1738b(char c) {
        String str = "0123456789ABCDEF";
        char[] cArr = new char[]{'\\', 'u', '\u0000', '\u0000', '\u0000', '\u0000'};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = str.charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public static CharMatcher m1736a(char c, char c2) {
        Preconditions.m1822a(c2 >= c);
        return CharMatcher.m1737a(c, c2, "CharMatcher.inRange('" + CharMatcher.m1738b(c) + "', '" + CharMatcher.m1738b(c2) + "')");
    }

    static CharMatcher m1737a(char c, char c2, String str) {
        return new CharMatcher(str, c, c2);
    }

    CharMatcher(String str) {
        this.f1335n = str;
    }

    protected CharMatcher() {
        this.f1335n = super.toString();
    }

    public CharMatcher m1739a(CharMatcher charMatcher) {
        return new CharMatcher(this, (CharMatcher) Preconditions.m1817a((Object) charMatcher));
    }

    CharMatcher m1740a(String str) {
        throw new UnsupportedOperationException();
    }

    public boolean m1742a(Character ch) {
        return m1741a(ch.charValue());
    }

    public String toString() {
        return this.f1335n;
    }
}
