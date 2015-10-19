package com.google.android.m4b.maps.bj;

import android.support.v4.view.MotionEventCompat;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;

/* renamed from: com.google.android.m4b.maps.bj.s */
final class LEDataInputStream extends FilterInputStream implements DataInput {
    private DataInputStream f6620a;

    public LEDataInputStream(InputStream inputStream) {
        super(inputStream);
        this.f6620a = new DataInputStream(inputStream);
    }

    public final boolean readBoolean() {
        return read() != 0;
    }

    public final byte readByte() {
        return (byte) read();
    }

    public final char readChar() {
        throw new UnsupportedOperationException();
    }

    public final double readDouble() {
        throw new UnsupportedOperationException();
    }

    public final float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public final void readFully(byte[] bArr) {
        this.f6620a.readFully(bArr);
    }

    public final void readFully(byte[] bArr, int i, int i2) {
        this.f6620a.readFully(bArr, i, i2);
    }

    public final int readInt() {
        return ((readUnsignedByte() | (readUnsignedByte() << 8)) | (readUnsignedByte() << 16)) | (readUnsignedByte() << 24);
    }

    public final String readLine() {
        throw new UnsupportedOperationException();
    }

    public final long readLong() {
        throw new UnsupportedOperationException();
    }

    public final short readShort() {
        throw new UnsupportedOperationException();
    }

    public final String readUTF() {
        throw new UnsupportedOperationException();
    }

    public final int readUnsignedByte() {
        return read() & MotionEventCompat.ACTION_MASK;
    }

    public final int readUnsignedShort() {
        return readUnsignedByte() | (readUnsignedByte() << 8);
    }

    public final int skipBytes(int i) {
        throw new UnsupportedOperationException();
    }
}
