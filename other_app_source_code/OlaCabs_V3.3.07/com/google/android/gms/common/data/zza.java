package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class zza implements SafeParcelable {
    public static final Creator<zza> CREATOR;
    final int f2120a;
    ParcelFileDescriptor f2121b;
    final int f2122c;
    private Bitmap f2123d;
    private boolean f2124e;
    private File f2125f;

    static {
        CREATOR = new C0268a();
    }

    zza(int i, ParcelFileDescriptor parcelFileDescriptor, int i2) {
        this.f2120a = i;
        this.f2121b = parcelFileDescriptor;
        this.f2122c = i2;
        this.f2123d = null;
        this.f2124e = false;
    }

    private FileOutputStream m3377a() {
        if (this.f2125f == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File createTempFile = File.createTempFile("teleporter", ".tmp", this.f2125f);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                this.f2121b = ParcelFileDescriptor.open(createTempFile, 268435456);
                createTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (Throwable e2) {
            throw new IllegalStateException("Could not create temporary file", e2);
        }
    }

    private void m3378a(Closeable closeable) {
        try {
            closeable.close();
        } catch (Throwable e) {
            Log.w("BitmapTeleporter", "Could not close stream", e);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.f2121b == null) {
            Bitmap bitmap = this.f2123d;
            Buffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            Closeable dataOutputStream = new DataOutputStream(m3377a());
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(bitmap.getWidth());
                dataOutputStream.writeInt(bitmap.getHeight());
                dataOutputStream.writeUTF(bitmap.getConfig().toString());
                dataOutputStream.write(array);
                m3378a(dataOutputStream);
            } catch (Throwable e) {
                throw new IllegalStateException("Could not write into unlinked file", e);
            } catch (Throwable th) {
                m3378a(dataOutputStream);
            }
        }
        C0268a.m3371a(this, parcel, i | 1);
        this.f2121b = null;
    }
}
