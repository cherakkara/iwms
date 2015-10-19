package com.google.android.m4b.maps.p049i;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.newrelic.agent.android.instrumentation.Trace;
import java.io.DataInput;
import java.io.EOFException;
import java.io.UTFDataFormatException;

/* renamed from: com.google.android.m4b.maps.i.a */
public final class ByteArrayDataInput implements DataInput {
    private byte[] f7429a;
    private int f7430b;
    private int f7431c;
    private char[] f7432d;

    public ByteArrayDataInput(byte[] bArr) {
        this.f7429a = bArr;
        this.f7430b = this.f7429a.length;
        this.f7431c = 0;
        this.f7432d = new char[AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS];
    }

    public final int m10512a() {
        return this.f7431c;
    }

    public final boolean readBoolean() {
        try {
            byte[] bArr = this.f7429a;
            int i = this.f7431c;
            this.f7431c = i + 1;
            return bArr[i] != null;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EOFException();
        }
    }

    public final byte readByte() {
        try {
            byte[] bArr = this.f7429a;
            int i = this.f7431c;
            this.f7431c = i + 1;
            return bArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EOFException();
        }
    }

    public final char readChar() {
        try {
            byte[] bArr = this.f7429a;
            int i = this.f7431c;
            this.f7431c = i + 1;
            byte b = bArr[i];
            byte[] bArr2 = this.f7429a;
            int i2 = this.f7431c;
            this.f7431c = i2 + 1;
            return (char) ((b << 8) | (bArr2[i2] & MotionEventCompat.ACTION_MASK));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EOFException();
        }
    }

    public final double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public final float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public final void readFully(byte[] bArr) {
        readFully(bArr, 0, bArr.length);
    }

    public final void readFully(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            if (i + i2 > bArr.length) {
                throw new IndexOutOfBoundsException();
            } else if (i2 > this.f7430b - this.f7431c) {
                this.f7431c = this.f7430b;
                throw new EOFException();
            } else {
                System.arraycopy(this.f7429a, this.f7431c, bArr, i, i2);
                this.f7431c += i2;
            }
        }
    }

    public final int readInt() {
        try {
            byte[] bArr = this.f7429a;
            int i = this.f7431c;
            this.f7431c = i + 1;
            int i2 = bArr[i] & MotionEventCompat.ACTION_MASK;
            byte[] bArr2 = this.f7429a;
            int i3 = this.f7431c;
            this.f7431c = i3 + 1;
            i = bArr2[i3] & MotionEventCompat.ACTION_MASK;
            byte[] bArr3 = this.f7429a;
            int i4 = this.f7431c;
            this.f7431c = i4 + 1;
            i3 = bArr3[i4] & MotionEventCompat.ACTION_MASK;
            byte[] bArr4 = this.f7429a;
            int i5 = this.f7431c;
            this.f7431c = i5 + 1;
            return (((i2 << 24) | (i << 16)) | (i3 << 8)) | (bArr4[i5] & MotionEventCompat.ACTION_MASK);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EOFException();
        }
    }

    public final String readLine() {
        if (this.f7431c >= this.f7430b) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        do {
            byte[] bArr = this.f7429a;
            int i = this.f7431c;
            this.f7431c = i + 1;
            char c = (char) bArr[i];
            if (c == '\n') {
                return stringBuilder.toString();
            }
            if (c == '\r') {
                if (this.f7431c < this.f7430b && this.f7429a[this.f7431c] == (byte) 10) {
                    this.f7431c++;
                }
                return stringBuilder.toString();
            }
            stringBuilder.append(c);
        } while (this.f7431c != this.f7430b);
        return stringBuilder.toString();
    }

    public final long readLong() {
        try {
            byte[] bArr = this.f7429a;
            int i = this.f7431c;
            this.f7431c = i + 1;
            long j = (long) (bArr[i] & MotionEventCompat.ACTION_MASK);
            byte[] bArr2 = this.f7429a;
            int i2 = this.f7431c;
            this.f7431c = i2 + 1;
            long j2 = (long) (bArr2[i2] & MotionEventCompat.ACTION_MASK);
            byte[] bArr3 = this.f7429a;
            int i3 = this.f7431c;
            this.f7431c = i3 + 1;
            long j3 = (long) (bArr3[i3] & MotionEventCompat.ACTION_MASK);
            byte[] bArr4 = this.f7429a;
            int i4 = this.f7431c;
            this.f7431c = i4 + 1;
            long j4 = (long) (bArr4[i4] & MotionEventCompat.ACTION_MASK);
            byte[] bArr5 = this.f7429a;
            int i5 = this.f7431c;
            this.f7431c = i5 + 1;
            long j5 = (long) (bArr5[i5] & MotionEventCompat.ACTION_MASK);
            byte[] bArr6 = this.f7429a;
            int i6 = this.f7431c;
            this.f7431c = i6 + 1;
            long j6 = (long) (bArr6[i6] & MotionEventCompat.ACTION_MASK);
            byte[] bArr7 = this.f7429a;
            int i7 = this.f7431c;
            this.f7431c = i7 + 1;
            long j7 = (long) (bArr7[i7] & MotionEventCompat.ACTION_MASK);
            byte[] bArr8 = this.f7429a;
            int i8 = this.f7431c;
            this.f7431c = i8 + 1;
            return (((((((j << 56) | (j2 << 48)) | (j3 << 40)) | (j4 << 32)) | (j5 << 24)) | (j6 << 16)) | (j7 << 8)) | ((long) (bArr8[i8] & MotionEventCompat.ACTION_MASK));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EOFException();
        }
    }

    public final short readShort() {
        try {
            byte[] bArr = this.f7429a;
            int i = this.f7431c;
            this.f7431c = i + 1;
            byte b = bArr[i];
            byte[] bArr2 = this.f7429a;
            int i2 = this.f7431c;
            this.f7431c = i2 + 1;
            return (short) ((b << 8) | (bArr2[i2] & MotionEventCompat.ACTION_MASK));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EOFException();
        }
    }

    public final String readUTF() {
        int readUnsignedShort = readUnsignedShort();
        if (readUnsignedShort == 0) {
            return Trace.NULL;
        }
        if (readUnsignedShort > this.f7430b - this.f7431c) {
            this.f7431c = this.f7430b;
            throw new EOFException();
        }
        if (readUnsignedShort > this.f7432d.length) {
            this.f7432d = new char[readUnsignedShort];
        }
        String a = ByteArrayDataInput.m10511a(this.f7429a, this.f7432d, this.f7431c, readUnsignedShort);
        this.f7431c = readUnsignedShort + this.f7431c;
        return a;
    }

    public final int readUnsignedByte() {
        try {
            byte[] bArr = this.f7429a;
            int i = this.f7431c;
            this.f7431c = i + 1;
            return bArr[i] & MotionEventCompat.ACTION_MASK;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EOFException();
        }
    }

    public final int readUnsignedShort() {
        try {
            byte[] bArr = this.f7429a;
            int i = this.f7431c;
            this.f7431c = i + 1;
            int i2 = bArr[i] & MotionEventCompat.ACTION_MASK;
            byte[] bArr2 = this.f7429a;
            int i3 = this.f7431c;
            this.f7431c = i3 + 1;
            return (i2 << 8) | (bArr2[i3] & MotionEventCompat.ACTION_MASK);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EOFException();
        }
    }

    public final int skipBytes(int i) {
        if (i > this.f7430b - this.f7431c) {
            i = this.f7430b - this.f7431c;
        }
        this.f7431c += i;
        return i;
    }

    private static String m10511a(byte[] bArr, char[] cArr, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = i4 + 1;
            char c = (char) bArr[i4 + i];
            cArr[i3] = c;
            if (c < '\u0080') {
                i3++;
                i4 = i5;
            } else {
                char c2 = cArr[i3];
                if ((c2 & 224) == 192) {
                    if (i5 >= i2) {
                        throw new UTFDataFormatException("Second byte at " + i5 + " does not match UTF8 Specification");
                    }
                    int i6 = i5 + 1;
                    byte b = bArr[i + i5];
                    if ((b & 192) != AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                        throw new UTFDataFormatException("Second byte at " + (i6 - 1) + " does not match UTF8 Specification");
                    }
                    i4 = i3 + 1;
                    cArr[i3] = (char) ((b & 63) | ((c2 & 31) << 6));
                    i3 = i4;
                    i4 = i6;
                } else if ((c2 & 240) != 224) {
                    throw new UTFDataFormatException("Input at " + (i5 - 1) + " does not match UTF8 Specification");
                } else if (i5 + 1 >= i2) {
                    throw new UTFDataFormatException("Third byte at " + (i5 + 1) + " does not match UTF8 Specification");
                } else {
                    i4 = i5 + 1;
                    byte b2 = bArr[i5 + i];
                    i5 = i4 + 1;
                    byte b3 = bArr[i4 + i];
                    if ((b2 & 192) == AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS && (b3 & 192) == AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) {
                        i4 = i3 + 1;
                        cArr[i3] = (char) ((((b2 & 63) << 6) | ((c2 & 15) << 12)) | (b3 & 63));
                        i3 = i4;
                        i4 = i5;
                    } else {
                        throw new UTFDataFormatException("Second or third byte at " + (i5 - 2) + " does not match UTF8 Specification");
                    }
                }
            }
        }
        return new String(cArr, 0, i3);
    }
}
