package com.google.android.m4b.maps.p061y;

import android.os.Handler;
import android.os.Looper;
import com.google.android.m4b.maps.an.ac;
import com.google.android.m4b.maps.an.af.TileParameters;
import com.google.android.m4b.maps.an.ag;
import com.google.android.m4b.maps.an.am;
import com.google.android.m4b.maps.an.br;
import com.google.android.m4b.maps.bx.Tile;
import com.google.android.m4b.maps.p040u.ByteArrayDataOutput;
import com.google.android.m4b.maps.p040u.DataRequest;
import com.google.android.m4b.maps.p040u.DataRequestDispatcher;
import com.google.android.m4b.maps.p040u.DataRequestDispatcherInterface;
import com.google.android.m4b.maps.p040u.DataRequestListener;
import com.google.android.m4b.maps.p046d.ProtoBuf;
import com.google.android.m4b.maps.p046d.ProtoBufUtil;
import com.google.android.m4b.maps.p047g.Preconditions;
import com.google.p025a.p026a.Strings;
import com.google.p025a.p028c.ar;
import com.google.p025a.p030e.ByteStreams;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import com.olacabs.customer.utils.Constants;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

/* renamed from: com.google.android.m4b.maps.y.j */
public final class MapsEngineDataRequestDispatcher implements DataRequestDispatcherInterface {
    private static final ProtoBuf f8117a;
    private final Collection<DataRequestListener> f8118b;
    private final ScheduledExecutorService f8119c;
    private final DataRequestDispatcher f8120d;
    private final Handler f8121e;

    /* renamed from: com.google.android.m4b.maps.y.j.1 */
    class MapsEngineDataRequestDispatcher implements Runnable {
        private /* synthetic */ DataRequest f8111a;
        private /* synthetic */ MapsEngineDataRequestDispatcher f8112b;

        MapsEngineDataRequestDispatcher(MapsEngineDataRequestDispatcher mapsEngineDataRequestDispatcher, DataRequest dataRequest) {
            this.f8112b = mapsEngineDataRequestDispatcher;
            this.f8111a = dataRequest;
        }

        public final void run() {
            for (DataRequestListener a : this.f8112b.m11748b()) {
                a.m6181a(this.f8111a);
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.y.j.a */
    class MapsEngineDataRequestDispatcher implements Runnable {
        private DataRequest f8113a;
        private ProtoBuf f8114b;
        private int f8115c;
        private /* synthetic */ MapsEngineDataRequestDispatcher f8116d;

        public MapsEngineDataRequestDispatcher(MapsEngineDataRequestDispatcher mapsEngineDataRequestDispatcher, DataRequest dataRequest) {
            this.f8116d = mapsEngineDataRequestDispatcher;
            this.f8115c = 0;
            this.f8113a = dataRequest;
            this.f8114b = MapsEngineDataRequestDispatcher.m11750d(dataRequest);
        }

        public final void run() {
            ProtoBuf protoBuf = new ProtoBuf(Tile.f6890f);
            for (int i = 0; i < this.f8114b.m10215k(9); i++) {
                ProtoBuf c = this.f8114b.m10202c(9, i);
                try {
                    HttpResponse execute;
                    ag agVar = new ag();
                    agVar.m5473a(TileParameters.MAPS_ENGINE.m5454a(c));
                    ProtoBuf a = am.m5577a(c.m10207e(30));
                    ProtoBuf b = ((br) new ac(a.m10204d(4) - 1, a.m10204d(2), a.m10204d(3), agVar).m5436a(TileParameters.MAPS_ENGINE)).m5879b();
                    HttpUriRequest httpGet = new HttpGet(String.format("https://mapsengine.google.com/%s/maptile/maps?v=%s&authToken=%s&x=%d&y=%d&z=%d&s=", new Object[]{b.m10212h(1), Strings.m1865a(MapsEngineDataRequestDispatcher.m11740a(b, "v")), Strings.m1865a(MapsEngineDataRequestDispatcher.m11740a(b, "authToken")), Integer.valueOf(r5.m5440c()), Integer.valueOf(r5.m5441d()), Integer.valueOf(r5.m5439b())}));
                    HttpClient defaultHttpClient = new DefaultHttpClient();
                    if (defaultHttpClient instanceof HttpClient) {
                        execute = HttpInstrumentation.execute(defaultHttpClient, httpGet);
                    } else {
                        execute = defaultHttpClient.execute(httpGet);
                    }
                    if (execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        byte[] a2 = ByteStreams.m2994a(execute.getEntity().getContent());
                        a = new ProtoBuf(Tile.f6889e);
                        a.m10190a(1, c);
                        a.m10192a(2, a2);
                        protoBuf.m10190a(9, a);
                    } else {
                        MapsEngineDataRequestDispatcher.m11741a(protoBuf, c);
                    }
                } catch (IOException e) {
                    r4 = (long) (200.0d * Math.pow(2.0d, (double) this.f8115c));
                    long pow;
                    if (pow < Constants.MILLIS_IN_A_MINUTE) {
                        this.f8116d.f8119c.schedule(this, pow, TimeUnit.MILLISECONDS);
                        this.f8115c++;
                        return;
                    }
                    MapsEngineDataRequestDispatcher.m11741a(protoBuf, c);
                }
            }
            protoBuf.m10190a(1, MapsEngineDataRequestDispatcher.f8117a);
            try {
                this.f8113a.m4770a(ProtoBufUtil.m10230b(protoBuf));
            } catch (IOException e2) {
                this.f8116d.m11746b(this.f8113a);
            }
            this.f8116d.f8121e.post(new MapsEngineDataRequestDispatcher(this.f8116d, this.f8113a));
        }

        private static void m11741a(ProtoBuf protoBuf, ProtoBuf protoBuf2) {
            ProtoBuf protoBuf3 = new ProtoBuf(Tile.f6889e);
            protoBuf3.m10190a(1, protoBuf2);
            protoBuf.m10190a(9, protoBuf3);
        }

        private static String m11740a(ProtoBuf protoBuf, String str) {
            int k = protoBuf.m10215k(2);
            for (int i = 0; i < k; i++) {
                ProtoBuf c = protoBuf.m10202c(2, i);
                if (c.m10212h(1).equals(str)) {
                    return c.m10212h(2);
                }
            }
            return null;
        }
    }

    static {
        ProtoBuf protoBuf = new ProtoBuf(Tile.f6888d);
        f8117a = protoBuf;
        protoBuf.m10189a(1, 0);
        f8117a.m10189a(2, 0);
    }

    public MapsEngineDataRequestDispatcher(DataRequestDispatcher dataRequestDispatcher, ScheduledExecutorService scheduledExecutorService) {
        this.f8118b = ar.m2515a();
        this.f8120d = (DataRequestDispatcher) Preconditions.m10459a((Object) dataRequestDispatcher);
        this.f8119c = (ScheduledExecutorService) Preconditions.m10459a((Object) scheduledExecutorService);
        this.f8121e = new Handler(Looper.getMainLooper());
    }

    private synchronized DataRequestListener[] m11748b() {
        DataRequestListener[] dataRequestListenerArr;
        dataRequestListenerArr = new DataRequestListener[this.f8118b.size()];
        this.f8118b.toArray(dataRequestListenerArr);
        return dataRequestListenerArr;
    }

    private void m11746b(DataRequest dataRequest) {
        for (DataRequestListener b : m11748b()) {
            b.m6183b(dataRequest);
        }
    }

    public final void m11754c(DataRequest dataRequest) {
        Object obj = 1;
        if (dataRequest.m4778i() != 108) {
            m11746b(dataRequest);
            return;
        }
        try {
            String h = MapsEngineDataRequestDispatcher.m11750d(dataRequest).m10202c(9, 0).m10211g(29).m10212h(1);
            if (h == null || !(h.startsWith("ft:cw:") || h.startsWith("vdb:"))) {
                obj = null;
            }
            if (obj != null) {
                this.f8120d.m11451c(dataRequest);
            } else {
                this.f8119c.execute(new MapsEngineDataRequestDispatcher(this, dataRequest));
            }
        } catch (IOException e) {
            m11746b(dataRequest);
        }
    }

    private static ProtoBuf m11750d(DataRequest dataRequest) {
        DataOutput byteArrayDataOutput = new ByteArrayDataOutput();
        dataRequest.m4768a(byteArrayDataOutput);
        return ProtoBufUtil.m10226a(Tile.f6887c, new DataInputStream(new ByteArrayInputStream(byteArrayDataOutput.m11313a())));
    }

    public final void m11753a(DataRequestListener dataRequestListener) {
        this.f8118b.add(dataRequestListener);
        this.f8120d.m11444a(dataRequestListener);
    }

    public final void m11751a(int i, byte[] bArr, boolean z, boolean z2) {
        this.f8120d.m11440a(i, bArr, z, z2);
    }

    public final void m11752a(int i, byte[] bArr, boolean z, boolean z2, boolean z3) {
        this.f8120d.m11441a(i, bArr, z, z2, z3);
    }

    public final String m11757m() {
        return this.f8120d.m11466m();
    }

    public final long m11756l() {
        return this.f8120d.m11465l();
    }

    public final void m11755j() {
        this.f8120d.m11463j();
    }
}
