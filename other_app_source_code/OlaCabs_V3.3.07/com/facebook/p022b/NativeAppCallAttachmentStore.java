package com.facebook.p022b;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.util.Log;
import com.facebook.FacebookContentProvider;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.apache.http.protocol.HTTP;

/* renamed from: com.facebook.b.n */
public final class NativeAppCallAttachmentStore {
    private static final String f818a;
    private static File f819b;

    /* renamed from: com.facebook.b.n.a */
    public static final class NativeAppCallAttachmentStore {
        private final UUID f811a;
        private final String f812b;
        private final String f813c;
        private Bitmap f814d;
        private Uri f815e;
        private boolean f816f;
        private boolean f817g;

        private NativeAppCallAttachmentStore(UUID uuid, Bitmap bitmap, Uri uri) {
            this.f811a = uuid;
            this.f814d = bitmap;
            this.f815e = uri;
            if (uri != null) {
                String scheme = uri.getScheme();
                if ("content".equalsIgnoreCase(scheme)) {
                    this.f816f = true;
                    this.f817g = true;
                } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                    this.f817g = true;
                } else if (!Utility.m1135b(uri)) {
                    throw new FacebookException("Unsupported scheme for image Uri : " + scheme);
                }
            } else if (bitmap != null) {
                this.f817g = true;
            } else {
                throw new FacebookException("Cannot share a photo without a bitmap or Uri set");
            }
            this.f813c = !this.f817g ? null : UUID.randomUUID().toString();
            this.f812b = !this.f817g ? this.f815e.toString() : FacebookContentProvider.m707a(FacebookSdk.m1210h(), uuid, this.f813c);
        }

        public String m1016a() {
            return this.f812b;
        }
    }

    static {
        f818a = NativeAppCallAttachmentStore.class.getName();
    }

    private NativeAppCallAttachmentStore() {
    }

    public static NativeAppCallAttachmentStore m1017a(UUID uuid, Bitmap bitmap) {
        Validate.m1146a((Object) uuid, "callId");
        Validate.m1146a((Object) bitmap, "attachmentBitmap");
        return new NativeAppCallAttachmentStore(bitmap, null, null);
    }

    public static NativeAppCallAttachmentStore m1018a(UUID uuid, Uri uri) {
        Validate.m1146a((Object) uuid, "callId");
        Validate.m1146a((Object) uri, "attachmentUri");
        return new NativeAppCallAttachmentStore(null, uri, null);
    }

    private static void m1023a(Bitmap bitmap, File file) throws IOException {
        Closeable fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
        } finally {
            Utility.m1116a(fileOutputStream);
        }
    }

    private static void m1024a(Uri uri, boolean z, File file) throws IOException {
        InputStream openInputStream;
        Closeable fileOutputStream = new FileOutputStream(file);
        if (z) {
            openInputStream = FacebookSdk.m1208f().getContentResolver().openInputStream(uri);
        } else {
            try {
                openInputStream = new FileInputStream(uri.getPath());
            } catch (Throwable th) {
                Utility.m1116a(fileOutputStream);
            }
        }
        Utility.m1088a(openInputStream, (OutputStream) fileOutputStream);
        Utility.m1116a(fileOutputStream);
    }

    public static void m1025a(Collection<NativeAppCallAttachmentStore> collection) {
        if (collection != null && collection.size() != 0) {
            if (f819b == null) {
                NativeAppCallAttachmentStore.m1027c();
            }
            NativeAppCallAttachmentStore.m1026b();
            List<File> arrayList = new ArrayList();
            try {
                for (NativeAppCallAttachmentStore nativeAppCallAttachmentStore : collection) {
                    if (nativeAppCallAttachmentStore.f817g) {
                        File a = NativeAppCallAttachmentStore.m1021a(nativeAppCallAttachmentStore.f811a, nativeAppCallAttachmentStore.f813c, true);
                        arrayList.add(a);
                        if (nativeAppCallAttachmentStore.f814d != null) {
                            NativeAppCallAttachmentStore.m1023a(nativeAppCallAttachmentStore.f814d, a);
                        } else if (nativeAppCallAttachmentStore.f815e != null) {
                            NativeAppCallAttachmentStore.m1024a(nativeAppCallAttachmentStore.f815e, nativeAppCallAttachmentStore.f816f, a);
                        }
                    }
                }
            } catch (Throwable e) {
                Throwable th = e;
                Log.e(f818a, "Got unexpected exception:" + th);
                for (File delete : arrayList) {
                    try {
                        delete.delete();
                    } catch (Exception e2) {
                    }
                }
                throw new FacebookException(th);
            }
        }
    }

    public static File m1020a(UUID uuid, String str) throws FileNotFoundException {
        if (Utility.m1126a(str) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return NativeAppCallAttachmentStore.m1021a(uuid, str, false);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    static synchronized File m1019a() {
        File file;
        synchronized (NativeAppCallAttachmentStore.class) {
            if (f819b == null) {
                f819b = new File(FacebookSdk.m1208f().getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
            }
            file = f819b;
        }
        return file;
    }

    static File m1026b() {
        File a = NativeAppCallAttachmentStore.m1019a();
        a.mkdirs();
        return a;
    }

    static File m1022a(UUID uuid, boolean z) {
        if (f819b == null) {
            return null;
        }
        File file = new File(f819b, uuid.toString());
        if (!z || file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    static File m1021a(UUID uuid, String str, boolean z) throws IOException {
        File a = NativeAppCallAttachmentStore.m1022a(uuid, z);
        if (a == null) {
            return null;
        }
        try {
            return new File(a, URLEncoder.encode(str, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void m1027c() {
        Utility.m1117a(NativeAppCallAttachmentStore.m1019a());
    }
}
