package com.google.android.m4b.maps.ap;

import com.google.android.m4b.maps.an.aa;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.ai;
import com.google.android.m4b.maps.an.an;
import com.google.android.m4b.maps.an.bg;
import com.google.android.m4b.maps.ap.DashServerMapTileStore.DashServerMapTileStore;
import com.google.android.m4b.maps.ap.DashServerTileStore.DashServerTileStore;
import com.google.android.m4b.maps.ar.DiskTileCacheListener;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* renamed from: com.google.android.m4b.maps.ap.c */
public final class ImageTileStore extends DashServerMapTileStore {
    private static final List<Integer> f3808e;

    /* renamed from: com.google.android.m4b.maps.ap.c.1 */
    class ImageTileStore extends DashServerMapTileStore {
        private /* synthetic */ ImageTileStore f3807c;

        ImageTileStore(ImageTileStore imageTileStore) {
            this.f3807c = imageTileStore;
            super(imageTileStore);
        }

        protected final aa m6252b(int i) {
            if (this.b[i] == null) {
                return null;
            }
            return new bg(m6151a(i).f3796a, m6167j(), AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, this.b[i], this.f3807c.c);
        }

        protected final byte[] m6253c(int i) {
            byte[] bArr = null;
            if (this.b[i] != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream(this.b[i].length + 32);
                try {
                    ac acVar = m6151a(i).f3796a;
                    int j = m6167j();
                    byte[] bArr2 = this.b[i];
                    DataOutput dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    dataOutputStream.writeInt(1146241364);
                    an.m5579a(dataOutputStream, 8);
                    acVar.m5438a(dataOutputStream);
                    an.m5579a(dataOutputStream, j);
                    an.m5579a(dataOutputStream, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
                    an.m5579a(dataOutputStream, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH);
                    an.m5579a(dataOutputStream, bArr2.length);
                    dataOutputStream.write(bArr2);
                    bArr = byteArrayOutputStream.toByteArray();
                } catch (IOException e) {
                }
            }
            return bArr;
        }
    }

    static {
        f3808e = Arrays.asList(new Integer[]{Integer.valueOf(0), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(5)});
    }

    public ImageTileStore(DataRequestDispatcherInterface dataRequestDispatcherInterface, ai aiVar, int i, int i2, float f, Locale locale, File file, DiskTileCacheListener diskTileCacheListener) {
        super(dataRequestDispatcherInterface, "its" + aiVar.f3449C, aiVar, AnalyticAttribute.ATTRIBUTE_NAME_MAX_LENGTH, f3808e, i, i2, f, false, locale, false, file, null);
    }

    protected final DashServerTileStore m6254g() {
        return new ImageTileStore(this);
    }
}
