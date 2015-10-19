package com.leanplum;

import java.io.OutputStream;

final class aY implements Runnable {
    private /* synthetic */ aV f8718a;
    private final /* synthetic */ byte[] f8719b;

    aY(aV aVVar, byte[] bArr) {
        this.f8718a = aVVar;
        this.f8719b = bArr;
    }

    public final void run() {
        try {
            synchronized (this.f8718a.f8715i) {
                if (this.f8718a.f8709c != null) {
                    OutputStream outputStream = this.f8718a.f8709c.getOutputStream();
                    outputStream.write(this.f8719b);
                    outputStream.flush();
                }
            }
        } catch (Exception e) {
            this.f8718a.f8708b.m12642a(e);
        }
    }
}
