package com.google.android.m4b.maps.ab;

import android.support.v4.media.TransportMediator;
import java.lang.Character.UnicodeBlock;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.google.android.m4b.maps.ab.f */
public final class SupportedCharacterChecker {
    private static final Set<UnicodeBlock> f3049a;

    static {
        Set hashSet = new HashSet();
        hashSet.add(UnicodeBlock.ARABIC);
        hashSet.add(UnicodeBlock.BASIC_LATIN);
        hashSet.add(UnicodeBlock.COMBINING_DIACRITICAL_MARKS);
        hashSet.add(UnicodeBlock.CYRILLIC);
        hashSet.add(UnicodeBlock.ENCLOSED_ALPHANUMERICS);
        hashSet.add(UnicodeBlock.GENERAL_PUNCTUATION);
        hashSet.add(UnicodeBlock.GEOMETRIC_SHAPES);
        hashSet.add(UnicodeBlock.GREEK);
        hashSet.add(UnicodeBlock.HEBREW);
        hashSet.add(UnicodeBlock.IPA_EXTENSIONS);
        hashSet.add(UnicodeBlock.LATIN_EXTENDED_A);
        hashSet.add(UnicodeBlock.LATIN_EXTENDED_ADDITIONAL);
        hashSet.add(UnicodeBlock.LATIN_EXTENDED_B);
        hashSet.add(UnicodeBlock.LATIN_1_SUPPLEMENT);
        hashSet.add(UnicodeBlock.LETTERLIKE_SYMBOLS);
        hashSet.add(UnicodeBlock.MISCELLANEOUS_SYMBOLS);
        hashSet.add(UnicodeBlock.NUMBER_FORMS);
        hashSet.add(UnicodeBlock.SPACING_MODIFIER_LETTERS);
        hashSet.add(UnicodeBlock.THAI);
        hashSet.add(UnicodeBlock.HANGUL_JAMO);
        hashSet.add(UnicodeBlock.CJK_RADICALS_SUPPLEMENT);
        hashSet.add(UnicodeBlock.KANGXI_RADICALS);
        hashSet.add(UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION);
        hashSet.add(UnicodeBlock.HIRAGANA);
        hashSet.add(UnicodeBlock.KATAKANA);
        hashSet.add(UnicodeBlock.BOPOMOFO);
        hashSet.add(UnicodeBlock.HANGUL_COMPATIBILITY_JAMO);
        hashSet.add(UnicodeBlock.KANBUN);
        hashSet.add(UnicodeBlock.BOPOMOFO_EXTENDED);
        hashSet.add(UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS);
        hashSet.add(UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS);
        hashSet.add(UnicodeBlock.CJK_COMPATIBILITY);
        hashSet.add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
        hashSet.add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
        hashSet.add(UnicodeBlock.HANGUL_SYLLABLES);
        hashSet.add(UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS);
        hashSet.add(UnicodeBlock.CJK_COMPATIBILITY_FORMS);
        hashSet.add(UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
        hashSet.add(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B);
        hashSet.add(UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT);
        f3049a = Collections.unmodifiableSet(hashSet);
    }

    public static String m4846a(String str) {
        StringBuilder stringBuilder = null;
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i = 0;
        while (i < length) {
            int i2;
            int i3;
            StringBuilder stringBuilder2;
            int i4;
            int codePointAt = str.codePointAt(i);
            if (codePointAt < 0 || codePointAt > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if (i2 != 0) {
                if (stringBuilder != null) {
                    stringBuilder.append((char) codePointAt);
                    i3 = i;
                    stringBuilder2 = stringBuilder;
                    i4 = i3;
                }
                i3 = i;
                stringBuilder2 = stringBuilder;
                i4 = i3;
            } else {
                if (!f3049a.contains(UnicodeBlock.of(codePointAt))) {
                    if (stringBuilder == null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(str.substring(0, i));
                    }
                    stringBuilder.append(' ');
                } else if (stringBuilder != null) {
                    stringBuilder.append(Character.toChars(codePointAt));
                }
                if (Character.isSupplementaryCodePoint(codePointAt)) {
                    i3 = i + 1;
                    stringBuilder2 = stringBuilder;
                    i4 = i3;
                }
                i3 = i;
                stringBuilder2 = stringBuilder;
                i4 = i3;
            }
            i3 = i4 + 1;
            stringBuilder = stringBuilder2;
            i = i3;
        }
        return stringBuilder != null ? stringBuilder.toString() : str;
    }
}
