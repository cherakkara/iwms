package com.google.android.m4b.maps.an;

import android.support.v4.media.TransportMediator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.DataInput;
import java.io.DataOutput;

/* compiled from: VarInt */
public final class an {
    public static int m5578a(DataInput dataInput) {
        int readUnsignedByte = dataInput.readUnsignedByte();
        if (readUnsignedByte >= AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
            readUnsignedByte &= TransportMediator.KEYCODE_MEDIA_PAUSE;
            int readUnsignedByte2 = dataInput.readUnsignedByte();
            readUnsignedByte |= (readUnsignedByte2 & TransportMediator.KEYCODE_MEDIA_PAUSE) << 7;
            if (readUnsignedByte2 >= AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                readUnsignedByte2 = dataInput.readUnsignedByte();
                readUnsignedByte |= (readUnsignedByte2 & TransportMediator.KEYCODE_MEDIA_PAUSE) << 14;
                if (readUnsignedByte2 >= AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                    readUnsignedByte2 = dataInput.readUnsignedByte();
                    readUnsignedByte |= (readUnsignedByte2 & TransportMediator.KEYCODE_MEDIA_PAUSE) << 21;
                    if (readUnsignedByte2 >= AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                        readUnsignedByte2 = dataInput.readUnsignedByte();
                        readUnsignedByte |= (readUnsignedByte2 & TransportMediator.KEYCODE_MEDIA_PAUSE) << 28;
                        if (readUnsignedByte2 >= AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                            while (readUnsignedByte2 >= AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                                readUnsignedByte2 = dataInput.readUnsignedByte();
                            }
                        }
                    }
                }
            }
        }
        return readUnsignedByte;
    }

    public static void m5579a(DataOutput dataOutput, int i) {
        if (i < 0) {
            long j = 4294967296L + ((long) i);
            dataOutput.write((int) (j | 128));
            dataOutput.write((int) ((j >> 7) | 128));
            dataOutput.write((int) ((j >> 14) | 128));
            dataOutput.write((int) ((j >> 21) | 128));
            dataOutput.write((int) (j >> 28));
        } else if (i < AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
            dataOutput.write(i);
        } else if (i < AccessibilityNodeInfoCompat.ACTION_COPY) {
            dataOutput.write(i | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            dataOutput.write(i >> 7);
        } else if (i < AccessibilityNodeInfoCompat.ACTION_SET_TEXT) {
            dataOutput.write(i | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            dataOutput.write((i >> 7) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            dataOutput.write(i >> 14);
        } else if (i < 268435456) {
            dataOutput.write(i | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            dataOutput.write((i >> 7) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            dataOutput.write((i >> 14) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            dataOutput.write(i >> 21);
        } else {
            dataOutput.write(i | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            dataOutput.write((i >> 7) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            dataOutput.write((i >> 14) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            dataOutput.write((i >> 21) | AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            dataOutput.write(i >> 28);
        }
    }

    public static int m5580b(DataInput dataInput) {
        int a = m5578a(dataInput);
        return (-(a & 1)) ^ (a >>> 1);
    }
}
