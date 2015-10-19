package com.google.android.m4b.maps.p041b;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.newrelic.agent.android.api.v1.Defaults;

/* renamed from: com.google.android.m4b.maps.b.k */
public final class Utf8Encoder {
    public static byte[] m7790a(String str) {
        byte[] bArr = new byte[Utf8Encoder.m7788a(str, null, 0)];
        Utf8Encoder.m7788a(str, bArr, 0);
        return bArr;
    }

    public static int m7788a(String str, byte[] bArr, int i) {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            int charAt;
            int charAt2 = str.charAt(i2);
            if (charAt2 >= 55296 && charAt2 <= 57343 && i2 + 1 < length) {
                charAt = str.charAt(i2 + 1);
                if (((charAt & 64512) ^ (charAt2 & 64512)) == Defaults.RESPONSE_BODY_LIMIT) {
                    i2++;
                    if ((charAt & 64512) != 55296) {
                        int i3 = charAt2;
                        charAt2 = charAt;
                        charAt = i3;
                    }
                    charAt2 = AccessibilityNodeInfoCompat.ACTION_CUT + (((charAt & 1023) << 10) | (charAt2 & 1023));
                }
            }
            if (charAt2 <= TransportMediator.KEYCODE_MEDIA_PAUSE) {
                if (bArr != null) {
                    bArr[i] = (byte) charAt2;
                }
                charAt = i + 1;
            } else if (charAt2 <= 2047) {
                if (bArr != null) {
                    bArr[i] = (byte) ((charAt2 >> 6) | 192);
                    bArr[i + 1] = (byte) ((charAt2 & 63) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                }
                charAt = i + 2;
            } else if (charAt2 <= SupportMenu.USER_MASK) {
                if (bArr != null) {
                    bArr[i] = (byte) ((charAt2 >> 12) | 224);
                    bArr[i + 1] = (byte) (((charAt2 >> 6) & 63) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                    bArr[i + 2] = (byte) ((charAt2 & 63) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                }
                charAt = i + 3;
            } else {
                if (bArr != null) {
                    bArr[i] = (byte) ((charAt2 >> 18) | 240);
                    bArr[i + 1] = (byte) (((charAt2 >> 12) & 63) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                    bArr[i + 2] = (byte) (((charAt2 >> 6) & 63) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                    bArr[i + 3] = (byte) ((charAt2 & 63) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                }
                charAt = i + 4;
            }
            i2++;
            i = charAt;
        }
        return i;
    }

    public static String m7789a(byte[] bArr, int i, int i2, boolean z) {
        StringBuilder stringBuilder = new StringBuilder(i2 + 0);
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 + 1;
            int i5 = bArr[i3] & MotionEventCompat.ACTION_MASK;
            if (i5 <= TransportMediator.KEYCODE_MEDIA_PAUSE) {
                stringBuilder.append((char) i5);
                i3 = i4;
            } else if (i5 >= 245) {
                stringBuilder.append((char) i5);
                i3 = i4;
            } else {
                int i6;
                i3 = 31;
                int i7 = 1;
                for (i6 = 224; i5 >= i6; i6 = (i6 >> 1) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                    i3 >>= 1;
                    i7++;
                }
                i3 &= i5;
                int i8 = 0;
                while (i8 < i7) {
                    i3 <<= 6;
                    if (i4 < i2) {
                        i6 = i4 + 1;
                        i3 |= bArr[i4] & 63;
                    } else {
                        i6 = i4;
                    }
                    i8++;
                    i4 = i6;
                }
                if (i3 >= 55296 && i3 <= 57343) {
                    throw new IllegalArgumentException("Invalid UTF8");
                } else if (i3 <= SupportMenu.USER_MASK) {
                    stringBuilder.append((char) i3);
                    i3 = i4;
                } else {
                    i3 -= AccessibilityNodeInfoCompat.ACTION_CUT;
                    stringBuilder.append((char) ((i3 >> 10) | 55296));
                    stringBuilder.append((char) ((i3 & 1023) | 56320));
                    i3 = i4;
                }
            }
        }
        return stringBuilder.toString();
    }
}
