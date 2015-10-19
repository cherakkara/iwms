package com.google.android.m4b.maps.p040u;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;

/* renamed from: com.google.android.m4b.maps.u.c */
public final class ByteArrayDataOutput extends DataOutputStream implements DataOutput {
    private final ByteArrayOutputStream f7860a;

    public ByteArrayDataOutput() {
        this(new ByteArrayOutputStream());
    }

    private ByteArrayDataOutput(ByteArrayOutputStream byteArrayOutputStream) {
        super(byteArrayOutputStream);
        this.f7860a = byteArrayOutputStream;
    }

    public final byte[] m11313a() {
        return this.f7860a.toByteArray();
    }
}
