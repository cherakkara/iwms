package com.google.android.m4b.maps.p047g;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.m4b.maps.ca.ConnectionResult;
import com.google.android.m4b.maps.ca.GooglePlayServicesClient.GooglePlayServicesClient;
import com.google.android.m4b.maps.ca.GooglePlayServicesUtil;
import com.google.android.m4b.maps.p043e.Api.Api;
import com.google.android.m4b.maps.p043e.GoogleApiClient.GoogleApiClient;
import com.google.android.m4b.maps.p047g.GmsClientEventManager.GmsClientEventManager;
import com.google.android.m4b.maps.p047g.IGmsCallbacks.IGmsCallbacks;
import com.google.android.m4b.maps.p047g.IGmsServiceBroker.IGmsServiceBroker;
import com.sothree.slidinguppanel.p086a.R.R;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;

/* renamed from: com.google.android.m4b.maps.g.c */
public abstract class GmsClient<T extends IInterface> implements Api, GmsClientEventManager {
    final Handler f7393a;
    private final Context f7394b;
    private final Looper f7395c;
    private T f7396d;
    private final ArrayList<GmsClient<?>> f7397e;
    private GmsClient f7398f;
    private volatile int f7399g;
    private boolean f7400h;
    private final GmsClientEventManager f7401i;

    /* renamed from: com.google.android.m4b.maps.g.c.a */
    final class GmsClient extends Handler {
        private /* synthetic */ GmsClient f7383a;

        public GmsClient(GmsClient gmsClient, Looper looper) {
            this.f7383a = gmsClient;
            super(looper);
        }

        public final void handleMessage(Message message) {
            if (message.what == 1 && !this.f7383a.m10349g()) {
                ((GmsClient) message.obj).m10325b();
            } else if (message.what == 3) {
                this.f7383a.f7401i.m10358a(new ConnectionResult(((Integer) message.obj).intValue(), null));
            } else if (message.what == 4) {
                this.f7383a.m10334a(1);
                this.f7383a.f7396d = null;
                this.f7383a.f7401i.m10356a(((Integer) message.obj).intValue());
            } else if (message.what == 2 && !this.f7383a.m10345c()) {
                ((GmsClient) message.obj).m10325b();
            } else if (message.what == 2 || message.what == 1) {
                ((GmsClient) message.obj).m10323a();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle this message.");
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.g.c.b */
    public abstract class GmsClient<TListener> {
        private TListener f7384a;
        private boolean f7385b;
        private /* synthetic */ GmsClient f7386c;

        protected abstract void m10324a(TListener tListener);

        public GmsClient(GmsClient gmsClient, TListener tListener) {
            this.f7386c = gmsClient;
            this.f7384a = tListener;
            this.f7385b = false;
        }

        public final void m10323a() {
            synchronized (this) {
                Object obj = this.f7384a;
                if (this.f7385b) {
                    new StringBuilder("Callback proxy ").append(this).append(" being reused. This is not safe.");
                }
            }
            if (obj != null) {
                try {
                    m10324a(obj);
                } catch (RuntimeException e) {
                    throw e;
                }
            }
            synchronized (this) {
                this.f7385b = true;
            }
            m10325b();
        }

        public final void m10325b() {
            m10326c();
            synchronized (this.f7386c.f7397e) {
                this.f7386c.f7397e.remove(this);
            }
        }

        public final void m10326c() {
            synchronized (this) {
                this.f7384a = null;
            }
        }
    }

    /* renamed from: com.google.android.m4b.maps.g.c.c */
    public static final class GmsClient extends IGmsCallbacks {
        private GmsClient f7387a;

        public GmsClient(GmsClient gmsClient) {
            this.f7387a = gmsClient;
        }

        public final void m10329a(int i, IBinder iBinder, Bundle bundle) {
            Preconditions.m10460a((Object) "onPostInitComplete can be called only once per call to getServiceFromBroker", this.f7387a);
            GmsClient gmsClient = this.f7387a;
            gmsClient.f7393a.sendMessage(gmsClient.f7393a.obtainMessage(1, new GmsClient(gmsClient, i, iBinder, bundle)));
            this.f7387a = null;
        }
    }

    /* renamed from: com.google.android.m4b.maps.g.c.d */
    final class GmsClient implements ServiceConnection {
        private /* synthetic */ GmsClient f7388a;

        GmsClient(GmsClient gmsClient) {
            this.f7388a = gmsClient;
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.f7388a.m10344b(iBinder);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            this.f7388a.f7393a.sendMessage(this.f7388a.f7393a.obtainMessage(4, Integer.valueOf(1)));
        }
    }

    /* renamed from: com.google.android.m4b.maps.g.c.e */
    public final class GmsClient extends GmsClient<Boolean> {
        private int f7389a;
        private Bundle f7390b;
        private IBinder f7391c;
        private /* synthetic */ GmsClient f7392d;

        protected final /* synthetic */ void m10330a(Object obj) {
            if (((Boolean) obj) == null) {
                this.f7392d.m10334a(1);
                return;
            }
            switch (this.f7389a) {
                case R.SlidingUpPanelLayout_umanoPanelHeight /*0*/:
                    try {
                        if (this.f7392d.m10348f().equals(this.f7391c.getInterfaceDescriptor())) {
                            this.f7392d.f7396d = this.f7392d.m10340a(this.f7391c);
                            if (this.f7392d.f7396d != null) {
                                this.f7392d.m10334a(3);
                                this.f7392d.f7401i.m10355a();
                                return;
                            }
                        }
                    } catch (RemoteException e) {
                    }
                    GmsClientSupervisor.m10376a(this.f7392d.f7394b).m10379b(this.f7392d.m10347e(), this.f7392d.f7398f);
                    this.f7392d.f7398f = null;
                    this.f7392d.m10334a(1);
                    this.f7392d.f7396d = null;
                    this.f7392d.f7401i.m10358a(new ConnectionResult(8, null));
                case HTTP.LF /*10*/:
                    this.f7392d.m10334a(1);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    PendingIntent pendingIntent = this.f7390b != null ? (PendingIntent) this.f7390b.getParcelable("pendingIntent") : null;
                    if (this.f7392d.f7398f != null) {
                        GmsClientSupervisor.m10376a(this.f7392d.f7394b).m10379b(this.f7392d.m10347e(), this.f7392d.f7398f);
                        this.f7392d.f7398f = null;
                    }
                    this.f7392d.m10334a(1);
                    this.f7392d.f7396d = null;
                    this.f7392d.f7401i.m10358a(new ConnectionResult(this.f7389a, pendingIntent));
            }
        }

        public GmsClient(GmsClient gmsClient, int i, IBinder iBinder, Bundle bundle) {
            this.f7392d = gmsClient;
            super(gmsClient, Boolean.valueOf(true));
            this.f7389a = i;
            this.f7391c = iBinder;
            this.f7390b = bundle;
        }
    }

    protected abstract T m10340a(IBinder iBinder);

    protected abstract void m10342a(IGmsServiceBroker iGmsServiceBroker, GmsClient gmsClient);

    protected abstract String m10347e();

    protected abstract String m10348f();

    static {
        String[] strArr = new String[]{"service_esmobile", "service_googleme"};
    }

    protected GmsClient(Context context, Looper looper, GoogleApiClient googleApiClient, GooglePlayServicesClient googlePlayServicesClient, String... strArr) {
        this.f7397e = new ArrayList();
        this.f7399g = 1;
        this.f7400h = false;
        this.f7394b = (Context) Preconditions.m10459a((Object) context);
        this.f7395c = (Looper) Preconditions.m10460a((Object) looper, (Object) "Looper must not be null");
        this.f7401i = new GmsClientEventManager(looper, this);
        this.f7393a = new GmsClient(this, looper);
        this.f7401i.m10360a((GoogleApiClient) Preconditions.m10459a((Object) googleApiClient));
        this.f7401i.m10359a((GooglePlayServicesClient) Preconditions.m10459a((Object) googlePlayServicesClient));
    }

    private void m10334a(int i) {
        int i2 = this.f7399g;
        this.f7399g = i;
    }

    public final void m10341a() {
        this.f7400h = true;
        m10334a(2);
        int a = GooglePlayServicesUtil.m10078a(this.f7394b);
        if (a != 0) {
            m10334a(1);
            this.f7393a.sendMessage(this.f7393a.obtainMessage(3, Integer.valueOf(a)));
            return;
        }
        if (this.f7398f != null) {
            this.f7396d = null;
            GmsClientSupervisor.m10376a(this.f7394b).m10379b(m10347e(), this.f7398f);
        }
        this.f7398f = new GmsClient(this);
        if (!GmsClientSupervisor.m10376a(this.f7394b).m10378a(m10347e(), this.f7398f)) {
            new StringBuilder("unable to connect to service: ").append(m10347e());
            this.f7393a.sendMessage(this.f7393a.obtainMessage(3, Integer.valueOf(9)));
        }
    }

    public final boolean m10345c() {
        return this.f7399g == 3;
    }

    public final boolean m10349g() {
        return this.f7399g == 2;
    }

    public void m10343b() {
        this.f7400h = false;
        synchronized (this.f7397e) {
            int size = this.f7397e.size();
            for (int i = 0; i < size; i++) {
                ((GmsClient) this.f7397e.get(i)).m10326c();
            }
            this.f7397e.clear();
        }
        m10334a(1);
        this.f7396d = null;
        if (this.f7398f != null) {
            GmsClientSupervisor.m10376a(this.f7394b).m10379b(m10347e(), this.f7398f);
            this.f7398f = null;
        }
    }

    public final Context m10350h() {
        return this.f7394b;
    }

    public final Looper m10346d() {
        return this.f7395c;
    }

    protected final void m10344b(IBinder iBinder) {
        try {
            m10342a(IGmsServiceBroker.m10454a(iBinder), new GmsClient(this));
        } catch (RemoteException e) {
        }
    }

    protected final void m10351i() {
        if (!m10345c()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T m10352j() {
        m10351i();
        return this.f7396d;
    }

    public final boolean k_() {
        return this.f7400h;
    }
}
