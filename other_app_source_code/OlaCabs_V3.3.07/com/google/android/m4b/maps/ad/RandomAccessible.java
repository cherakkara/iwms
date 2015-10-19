package com.google.android.m4b.maps.ad;

import java.io.File;
import java.io.RandomAccessFile;

/* renamed from: com.google.android.m4b.maps.ad.a */
public class RandomAccessible {
    private RandomAccessFile f3059a;

    /* renamed from: com.google.android.m4b.maps.ad.a.a */
    public interface RandomAccessible {
        private File f3058a;

        RandomAccessible(File file) {
            this.f3058a = file;
        }

        RandomAccessible m4858a(String str, boolean z) {
            return new RandomAccessible(new File(this.f3058a, "cache_" + str), "rw");
        }

        void m4859a(String str) {
            File file = new File(this.f3058a, "cache_" + str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public RandomAccessible(File file, String str) {
        this.f3059a = new RandomAccessFile(file, str);
    }

    public void m4860a() {
        this.f3059a.close();
    }

    public void m4864b() {
        this.f3059a.getFD().sync();
    }

    public void m4861a(long j) {
        this.f3059a.seek(j);
    }

    public void m4865b(byte[] bArr) {
        this.f3059a.readFully(bArr);
    }

    public void m4863a(byte[] bArr, int i, int i2) {
        this.f3059a.read(bArr, 0, i2);
    }

    public void m4862a(byte[] bArr) {
        this.f3059a.write(bArr);
    }

    public void m4866b(byte[] bArr, int i, int i2) {
        this.f3059a.write(bArr, i, i2);
    }
}
