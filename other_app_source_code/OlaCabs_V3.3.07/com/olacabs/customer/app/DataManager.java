package com.olacabs.customer.app;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.C0226d;
import com.google.android.gms.common.api.C0226d.C0220a;
import com.google.android.gms.common.api.C0226d.C0221b;
import com.google.android.gms.common.api.C0226d.C0222c;
import com.google.android.gms.location.C0530f;
import com.google.android.gms.location.C0532g;
import com.google.android.gms.location.LocationRequest;
import com.google.android.m4b.maps.model.LatLng;
import com.google.gson.Gson;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.Trace;
import com.olacabs.customer.R;
import com.olacabs.customer.app.f.AnonymousClass10;
import com.olacabs.customer.app.f.AnonymousClass100;
import com.olacabs.customer.app.f.AnonymousClass101;
import com.olacabs.customer.app.f.AnonymousClass102;
import com.olacabs.customer.app.f.AnonymousClass103;
import com.olacabs.customer.app.f.AnonymousClass104;
import com.olacabs.customer.app.f.AnonymousClass105;
import com.olacabs.customer.app.f.AnonymousClass106;
import com.olacabs.customer.app.f.AnonymousClass107;
import com.olacabs.customer.app.f.AnonymousClass108;
import com.olacabs.customer.app.f.AnonymousClass11;
import com.olacabs.customer.app.f.AnonymousClass110;
import com.olacabs.customer.app.f.AnonymousClass111;
import com.olacabs.customer.app.f.AnonymousClass112;
import com.olacabs.customer.app.f.AnonymousClass113;
import com.olacabs.customer.app.f.AnonymousClass114;
import com.olacabs.customer.app.f.AnonymousClass115;
import com.olacabs.customer.app.f.AnonymousClass116;
import com.olacabs.customer.app.f.AnonymousClass117;
import com.olacabs.customer.app.f.AnonymousClass118;
import com.olacabs.customer.app.f.AnonymousClass119;
import com.olacabs.customer.app.f.AnonymousClass12;
import com.olacabs.customer.app.f.AnonymousClass121;
import com.olacabs.customer.app.f.AnonymousClass122;
import com.olacabs.customer.app.f.AnonymousClass123;
import com.olacabs.customer.app.f.AnonymousClass124;
import com.olacabs.customer.app.f.AnonymousClass125;
import com.olacabs.customer.app.f.AnonymousClass126;
import com.olacabs.customer.app.f.AnonymousClass127;
import com.olacabs.customer.app.f.AnonymousClass128;
import com.olacabs.customer.app.f.AnonymousClass129;
import com.olacabs.customer.app.f.AnonymousClass13;
import com.olacabs.customer.app.f.AnonymousClass130;
import com.olacabs.customer.app.f.AnonymousClass131;
import com.olacabs.customer.app.f.AnonymousClass132;
import com.olacabs.customer.app.f.AnonymousClass133;
import com.olacabs.customer.app.f.AnonymousClass134;
import com.olacabs.customer.app.f.AnonymousClass135;
import com.olacabs.customer.app.f.AnonymousClass136;
import com.olacabs.customer.app.f.AnonymousClass137;
import com.olacabs.customer.app.f.AnonymousClass138;
import com.olacabs.customer.app.f.AnonymousClass139;
import com.olacabs.customer.app.f.AnonymousClass14;
import com.olacabs.customer.app.f.AnonymousClass140;
import com.olacabs.customer.app.f.AnonymousClass141;
import com.olacabs.customer.app.f.AnonymousClass142;
import com.olacabs.customer.app.f.AnonymousClass143;
import com.olacabs.customer.app.f.AnonymousClass144;
import com.olacabs.customer.app.f.AnonymousClass145;
import com.olacabs.customer.app.f.AnonymousClass146;
import com.olacabs.customer.app.f.AnonymousClass147;
import com.olacabs.customer.app.f.AnonymousClass148;
import com.olacabs.customer.app.f.AnonymousClass149;
import com.olacabs.customer.app.f.AnonymousClass15;
import com.olacabs.customer.app.f.AnonymousClass150;
import com.olacabs.customer.app.f.AnonymousClass151;
import com.olacabs.customer.app.f.AnonymousClass152;
import com.olacabs.customer.app.f.AnonymousClass153;
import com.olacabs.customer.app.f.AnonymousClass154;
import com.olacabs.customer.app.f.AnonymousClass155;
import com.olacabs.customer.app.f.AnonymousClass156;
import com.olacabs.customer.app.f.AnonymousClass157;
import com.olacabs.customer.app.f.AnonymousClass158;
import com.olacabs.customer.app.f.AnonymousClass159;
import com.olacabs.customer.app.f.AnonymousClass16;
import com.olacabs.customer.app.f.AnonymousClass160;
import com.olacabs.customer.app.f.AnonymousClass161;
import com.olacabs.customer.app.f.AnonymousClass17;
import com.olacabs.customer.app.f.AnonymousClass18;
import com.olacabs.customer.app.f.AnonymousClass19;
import com.olacabs.customer.app.f.AnonymousClass20;
import com.olacabs.customer.app.f.AnonymousClass21;
import com.olacabs.customer.app.f.AnonymousClass22;
import com.olacabs.customer.app.f.AnonymousClass23;
import com.olacabs.customer.app.f.AnonymousClass24;
import com.olacabs.customer.app.f.AnonymousClass25;
import com.olacabs.customer.app.f.AnonymousClass26;
import com.olacabs.customer.app.f.AnonymousClass27;
import com.olacabs.customer.app.f.AnonymousClass28;
import com.olacabs.customer.app.f.AnonymousClass29;
import com.olacabs.customer.app.f.AnonymousClass30;
import com.olacabs.customer.app.f.AnonymousClass31;
import com.olacabs.customer.app.f.AnonymousClass32;
import com.olacabs.customer.app.f.AnonymousClass33;
import com.olacabs.customer.app.f.AnonymousClass34;
import com.olacabs.customer.app.f.AnonymousClass35;
import com.olacabs.customer.app.f.AnonymousClass36;
import com.olacabs.customer.app.f.AnonymousClass37;
import com.olacabs.customer.app.f.AnonymousClass38;
import com.olacabs.customer.app.f.AnonymousClass39;
import com.olacabs.customer.app.f.AnonymousClass40;
import com.olacabs.customer.app.f.AnonymousClass41;
import com.olacabs.customer.app.f.AnonymousClass42;
import com.olacabs.customer.app.f.AnonymousClass43;
import com.olacabs.customer.app.f.AnonymousClass45;
import com.olacabs.customer.app.f.AnonymousClass46;
import com.olacabs.customer.app.f.AnonymousClass47;
import com.olacabs.customer.app.f.AnonymousClass49;
import com.olacabs.customer.app.f.AnonymousClass50;
import com.olacabs.customer.app.f.AnonymousClass51;
import com.olacabs.customer.app.f.AnonymousClass52;
import com.olacabs.customer.app.f.AnonymousClass53;
import com.olacabs.customer.app.f.AnonymousClass54;
import com.olacabs.customer.app.f.AnonymousClass55;
import com.olacabs.customer.app.f.AnonymousClass56;
import com.olacabs.customer.app.f.AnonymousClass57;
import com.olacabs.customer.app.f.AnonymousClass58;
import com.olacabs.customer.app.f.AnonymousClass59;
import com.olacabs.customer.app.f.AnonymousClass60;
import com.olacabs.customer.app.f.AnonymousClass61;
import com.olacabs.customer.app.f.AnonymousClass62;
import com.olacabs.customer.app.f.AnonymousClass63;
import com.olacabs.customer.app.f.AnonymousClass64;
import com.olacabs.customer.app.f.AnonymousClass65;
import com.olacabs.customer.app.f.AnonymousClass66;
import com.olacabs.customer.app.f.AnonymousClass67;
import com.olacabs.customer.app.f.AnonymousClass68;
import com.olacabs.customer.app.f.AnonymousClass69;
import com.olacabs.customer.app.f.AnonymousClass70;
import com.olacabs.customer.app.f.AnonymousClass71;
import com.olacabs.customer.app.f.AnonymousClass72;
import com.olacabs.customer.app.f.AnonymousClass73;
import com.olacabs.customer.app.f.AnonymousClass74;
import com.olacabs.customer.app.f.AnonymousClass75;
import com.olacabs.customer.app.f.AnonymousClass77;
import com.olacabs.customer.app.f.AnonymousClass78;
import com.olacabs.customer.app.f.AnonymousClass79;
import com.olacabs.customer.app.f.AnonymousClass80;
import com.olacabs.customer.app.f.AnonymousClass81;
import com.olacabs.customer.app.f.AnonymousClass82;
import com.olacabs.customer.app.f.AnonymousClass83;
import com.olacabs.customer.app.f.AnonymousClass84;
import com.olacabs.customer.app.f.AnonymousClass85;
import com.olacabs.customer.app.f.AnonymousClass86;
import com.olacabs.customer.app.f.AnonymousClass87;
import com.olacabs.customer.app.f.AnonymousClass88;
import com.olacabs.customer.app.f.AnonymousClass89;
import com.olacabs.customer.app.f.AnonymousClass90;
import com.olacabs.customer.app.f.AnonymousClass91;
import com.olacabs.customer.app.f.AnonymousClass92;
import com.olacabs.customer.app.f.AnonymousClass93;
import com.olacabs.customer.app.f.AnonymousClass94;
import com.olacabs.customer.app.f.AnonymousClass95;
import com.olacabs.customer.app.f.AnonymousClass96;
import com.olacabs.customer.app.f.AnonymousClass97;
import com.olacabs.customer.app.f.AnonymousClass98;
import com.olacabs.customer.app.f.AnonymousClass99;
import com.olacabs.customer.p076d.AccountBalanceResponse;
import com.olacabs.customer.p076d.AddEditFavouriteResponse;
import com.olacabs.customer.p076d.AnalyticsInstallResponse;
import com.olacabs.customer.p076d.AppInfo;
import com.olacabs.customer.p076d.AuthRefreshResponse;
import com.olacabs.customer.p076d.AuthSession;
import com.olacabs.customer.p076d.AuthSessionResponse;
import com.olacabs.customer.p076d.BasicResponse;
import com.olacabs.customer.p076d.BookingCabInfoResponse;
import com.olacabs.customer.p076d.CabInfoRideSummaryResponse;
import com.olacabs.customer.p076d.CachingHandler;
import com.olacabs.customer.p076d.ChangePasswordResponse;
import com.olacabs.customer.p076d.CheckoutResponse;
import com.olacabs.customer.p076d.CitiesLocalitiesResponse;
import com.olacabs.customer.p076d.UpdateECResponse;
import com.olacabs.customer.p076d.ab;
import com.olacabs.customer.p076d.ad;
import com.olacabs.customer.p076d.ae;
import com.olacabs.customer.p076d.af;
import com.olacabs.customer.p076d.ag;
import com.olacabs.customer.p076d.aj;
import com.olacabs.customer.p076d.ak;
import com.olacabs.customer.p076d.ak.DeepLinkInfo;
import com.olacabs.customer.p076d.am;
import com.olacabs.customer.p076d.ao;
import com.olacabs.customer.p076d.ax;
import com.olacabs.customer.p076d.ay;
import com.olacabs.customer.p076d.az;
import com.olacabs.customer.p076d.bb;
import com.olacabs.customer.p076d.be;
import com.olacabs.customer.p076d.bf;
import com.olacabs.customer.p076d.bi;
import com.olacabs.customer.p076d.bj;
import com.olacabs.customer.p076d.bm;
import com.olacabs.customer.p076d.bp;
import com.olacabs.customer.p076d.br;
import com.olacabs.customer.p076d.bt;
import com.olacabs.customer.p076d.bu;
import com.olacabs.customer.p076d.bv;
import com.olacabs.customer.p076d.bw;
import com.olacabs.customer.p076d.by;
import com.olacabs.customer.p076d.ca;
import com.olacabs.customer.p076d.cb;
import com.olacabs.customer.p076d.cd;
import com.olacabs.customer.p076d.ci;
import com.olacabs.customer.p076d.cj;
import com.olacabs.customer.p076d.ck;
import com.olacabs.customer.p076d.cl;
import com.olacabs.customer.p076d.cm;
import com.olacabs.customer.p076d.cn;
import com.olacabs.customer.p076d.cq;
import com.olacabs.customer.p076d.cs;
import com.olacabs.customer.p076d.ct;
import com.olacabs.customer.p076d.cv;
import com.olacabs.customer.p076d.cy;
import com.olacabs.customer.p076d.da;
import com.olacabs.customer.p076d.dc;
import com.olacabs.customer.p076d.de;
import com.olacabs.customer.p076d.df;
import com.olacabs.customer.p076d.dh;
import com.olacabs.customer.p076d.di;
import com.olacabs.customer.p076d.dj;
import com.olacabs.customer.p076d.dm;
import com.olacabs.customer.p076d.dn;
import com.olacabs.customer.p076d.dp;
import com.olacabs.customer.p076d.dq;
import com.olacabs.customer.p076d.ds;
import com.olacabs.customer.p076d.dt;
import com.olacabs.customer.p076d.du;
import com.olacabs.customer.p076d.dv;
import com.olacabs.customer.tfs.p081a.TFSBookingCreateResponse;
import com.olacabs.customer.tfs.p081a.TFSCabInfoResponse;
import com.olacabs.customer.tfs.p081a.TFSCallDriverResponse;
import com.olacabs.customer.tfs.p081a.TFSFareResponse;
import com.olacabs.customer.tfs.p081a.TFSHasBookingResponse;
import com.olacabs.customer.tfs.p081a.TFSShareRideResponse;
import com.olacabs.customer.tfs.p081a.TFSTrackTaxiResponse;
import com.olacabs.customer.utils.BackgroundLooper;
import com.olacabs.customer.utils.Constants;
import com.olacabs.customer.utils.EncryptionScheme;
import com.olacabs.customer.utils.Utils;
import com.olacabs.customer.utils.p083a.ApplicationMode;
import com.olacabs.p067a.p068a.p069a.p074d.MegatronCore;
import com.payu.p084a.Bank;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.json.JSONException;
import org.json.JSONObject;
import p000a.p001a.p002a.EventBus;

/* renamed from: com.olacabs.customer.app.f */
public class DataManager implements C0221b, C0222c, C0530f {
    public static final Object f9351a;
    private static final String f9352b;
    private static DataManager f9353c;
    private final Object f9354d;
    private Context f9355e;
    private volatile boolean f9356f;
    private AppInfo f9357g;
    private dt f9358h;
    private da f9359i;
    private ao f9360j;
    private ak f9361k;
    private ImageCacheManager f9362l;
    private C0226d f9363m;
    private RequestQueue f9364n;
    private CachingHandler f9365o;
    private HashSet<String> f9366p;
    private ag f9367q;
    private Handler f9368r;

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.100 */
    class AnonymousClass100 implements Response {
        final /* synthetic */ WeakReference f9008a;
        final /* synthetic */ DataManager f9009b;

        AnonymousClass100(DataManager dataManager, WeakReference weakReference) {
            this.f9009b = dataManager;
            this.f9008a = weakReference;
        }

        public void m12897a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9008a != null) {
                ajVar = (aj) this.f9008a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.101 */
    class AnonymousClass101 implements Response<cs> {
        final /* synthetic */ WeakReference f9010a;
        final /* synthetic */ DataManager f9011b;

        AnonymousClass101(DataManager dataManager, WeakReference weakReference) {
            this.f9011b = dataManager;
            this.f9010a = weakReference;
        }

        public void m12898a(cs csVar) {
            aj ajVar = null;
            if (this.f9010a != null) {
                ajVar = (aj) this.f9010a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(csVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.102 */
    class AnonymousClass102 implements Response {
        final /* synthetic */ WeakReference f9012a;
        final /* synthetic */ DataManager f9013b;

        AnonymousClass102(DataManager dataManager, WeakReference weakReference) {
            this.f9013b = dataManager;
            this.f9012a = weakReference;
        }

        public void m12900a(VolleyError volleyError) {
            OLog.m13311a("Error in ride estimate", volleyError);
            aj ajVar = null;
            if (this.f9012a != null) {
                ajVar = (aj) this.f9012a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.103 */
    class AnonymousClass103 implements Response<cv> {
        final /* synthetic */ Map f9014a;
        final /* synthetic */ WeakReference f9015b;
        final /* synthetic */ DataManager f9016c;

        AnonymousClass103(DataManager dataManager, Map map, WeakReference weakReference) {
            this.f9016c = dataManager;
            this.f9014a = map;
            this.f9015b = weakReference;
        }

        public void m12901a(cv cvVar) {
            if (cvVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                cvVar.setOrigTimeStamp(System.currentTimeMillis());
                cvVar.setOrigParams(this.f9014a);
                this.f9016c.f9365o.addToCache("v3/rides", cvVar);
            }
            aj ajVar = null;
            if (this.f9015b != null) {
                ajVar = (aj) this.f9015b.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(cvVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.104 */
    class AnonymousClass104 implements Response {
        final /* synthetic */ WeakReference f9017a;
        final /* synthetic */ DataManager f9018b;

        AnonymousClass104(DataManager dataManager, WeakReference weakReference) {
            this.f9018b = dataManager;
            this.f9017a = weakReference;
        }

        public void m12903a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9017a != null) {
                ajVar = (aj) this.f9017a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.105 */
    class AnonymousClass105 implements Runnable {
        final /* synthetic */ Map f9021a;
        final /* synthetic */ WeakReference f9022b;
        final /* synthetic */ String f9023c;
        final /* synthetic */ OlaApp f9024d;
        final /* synthetic */ Response f9025e;
        final /* synthetic */ Response f9026f;
        final /* synthetic */ DataManager f9027g;

        /* renamed from: com.olacabs.customer.app.f.105.1 */
        class DataManager implements Runnable {
            final /* synthetic */ bv f9019a;
            final /* synthetic */ AnonymousClass105 f9020b;

            DataManager(AnonymousClass105 anonymousClass105, bv bvVar) {
                this.f9020b = anonymousClass105;
                this.f9019a = bvVar;
            }

            public void run() {
                aj ajVar = null;
                if (this.f9020b.f9022b != null) {
                    ajVar = (aj) this.f9020b.f9022b.get();
                }
                if (ajVar != null && this.f9020b.f9027g.f9366p.contains(this.f9020b.f9023c)) {
                    ajVar.onSuccess(this.f9019a);
                }
            }
        }

        AnonymousClass105(DataManager dataManager, Map map, WeakReference weakReference, String str, OlaApp olaApp, Response response, Response response2) {
            this.f9027g = dataManager;
            this.f9021a = map;
            this.f9022b = weakReference;
            this.f9023c = str;
            this.f9024d = olaApp;
            this.f9025e = response;
            this.f9026f = response2;
        }

        public void run() {
            bv readFromCache = this.f9027g.f9365o.readFromCache("v3/rides", cv.class, this.f9021a);
            if (readFromCache == null || !readFromCache.isValid(this.f9021a)) {
                OLog.m13311a("Did not find cache data for v3/rides", new Object[0]);
            } else {
                OLog.m13311a("Found cache data for v3/rides", new Object[0]);
                new Handler(Looper.getMainLooper()).post(new com.olacabs.customer.app.DataManager.105.DataManager(this, readFromCache));
            }
            Request instance = bw.getInstance(this.f9024d, 0, "https://apps.olacabs.com/v3/rides", Request.Request.IMMEDIATE, this.f9021a, this.f9025e, this.f9026f, cv.class);
            this.f9027g.f9366p.add(this.f9023c);
            instance.setTag(this.f9023c);
            this.f9027g.m13168a(instance);
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.106 */
    class AnonymousClass106 implements Response<dn> {
        final /* synthetic */ WeakReference f9028a;
        final /* synthetic */ DataManager f9029b;

        AnonymousClass106(DataManager dataManager, WeakReference weakReference) {
            this.f9029b = dataManager;
            this.f9028a = weakReference;
        }

        public void m12904a(dn dnVar) {
            aj ajVar = null;
            if (this.f9028a != null) {
                ajVar = (aj) this.f9028a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(dnVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.107 */
    class AnonymousClass107 implements Response {
        final /* synthetic */ WeakReference f9030a;
        final /* synthetic */ DataManager f9031b;

        AnonymousClass107(DataManager dataManager, WeakReference weakReference) {
            this.f9031b = dataManager;
            this.f9030a = weakReference;
        }

        public void m12906a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9030a != null) {
                ajVar = (aj) this.f9030a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.108 */
    class AnonymousClass108 implements Response<bj> {
        final /* synthetic */ WeakReference f9032a;
        final /* synthetic */ DataManager f9033b;

        AnonymousClass108(DataManager dataManager, WeakReference weakReference) {
            this.f9033b = dataManager;
            this.f9032a = weakReference;
        }

        public void m12907a(bj bjVar) {
            aj ajVar = null;
            if (this.f9032a != null) {
                ajVar = (aj) this.f9032a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(bjVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.10 */
    class AnonymousClass10 implements Response {
        final /* synthetic */ WeakReference f9035a;
        final /* synthetic */ DataManager f9036b;

        AnonymousClass10(DataManager dataManager, WeakReference weakReference) {
            this.f9036b = dataManager;
            this.f9035a = weakReference;
        }

        public void m12911a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9035a != null) {
                ajVar = (aj) this.f9035a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.110 */
    class AnonymousClass110 implements Response {
        final /* synthetic */ WeakReference f9037a;
        final /* synthetic */ DataManager f9038b;

        AnonymousClass110(DataManager dataManager, WeakReference weakReference) {
            this.f9038b = dataManager;
            this.f9037a = weakReference;
        }

        public void m12912a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9037a != null) {
                ajVar = (aj) this.f9037a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.111 */
    class AnonymousClass111 implements Response<ad> {
        final /* synthetic */ Map f9039a;
        final /* synthetic */ WeakReference f9040b;
        final /* synthetic */ DataManager f9041c;

        AnonymousClass111(DataManager dataManager, Map map, WeakReference weakReference) {
            this.f9041c = dataManager;
            this.f9039a = map;
            this.f9040b = weakReference;
        }

        public void m12913a(ad adVar) {
            if (adVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                OLog.m13311a("Updating cache ", new Object[0]);
                adVar.setOrigTimeStamp(System.currentTimeMillis());
                adVar.setOrigParams(this.f9039a);
                this.f9041c.f9365o.addToCache("v3/config/new", adVar);
                Editor edit = PreferenceManager.getDefaultSharedPreferences(this.f9041c.f9355e).edit();
                OLog.m13311a("Modified Time : " + adVar.getLastModifiedTime(), new Object[0]);
                edit.putString(bj.CONFIG_LAST_MODIFIED_TIME_KEY, adVar.getLastModifiedTime());
                edit.apply();
            }
            aj ajVar = null;
            if (this.f9040b != null) {
                ajVar = (aj) this.f9040b.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(adVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.112 */
    class AnonymousClass112 implements Response {
        final /* synthetic */ WeakReference f9042a;
        final /* synthetic */ DataManager f9043b;

        AnonymousClass112(DataManager dataManager, WeakReference weakReference) {
            this.f9043b = dataManager;
            this.f9042a = weakReference;
        }

        public void m12915a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9042a != null) {
                ajVar = (aj) this.f9042a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.113 */
    class AnonymousClass113 implements Response<cq> {
        final /* synthetic */ WeakReference f9044a;
        final /* synthetic */ DataManager f9045b;

        AnonymousClass113(DataManager dataManager, WeakReference weakReference) {
            this.f9045b = dataManager;
            this.f9044a = weakReference;
        }

        public void m12916a(cq cqVar) {
            aj ajVar = null;
            if (this.f9044a != null) {
                ajVar = (aj) this.f9044a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(cqVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.114 */
    class AnonymousClass114 implements Response {
        final /* synthetic */ WeakReference f9046a;
        final /* synthetic */ DataManager f9047b;

        AnonymousClass114(DataManager dataManager, WeakReference weakReference) {
            this.f9047b = dataManager;
            this.f9046a = weakReference;
        }

        public void m12918a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9046a != null) {
                ajVar = (aj) this.f9046a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.115 */
    class AnonymousClass115 implements Response<ct> {
        final /* synthetic */ WeakReference f9048a;
        final /* synthetic */ DataManager f9049b;

        AnonymousClass115(DataManager dataManager, WeakReference weakReference) {
            this.f9049b = dataManager;
            this.f9048a = weakReference;
        }

        public void m12919a(ct ctVar) {
            aj ajVar = null;
            if (this.f9048a != null) {
                ajVar = (aj) this.f9048a.get();
            }
            if (ajVar == null) {
                return;
            }
            if (ctVar == null || !ctVar.isValid()) {
                ajVar.onFailure(new VolleyError());
            } else {
                ajVar.onSuccess(ctVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.116 */
    class AnonymousClass116 implements Response {
        final /* synthetic */ WeakReference f9050a;
        final /* synthetic */ DataManager f9051b;

        AnonymousClass116(DataManager dataManager, WeakReference weakReference) {
            this.f9051b = dataManager;
            this.f9050a = weakReference;
        }

        public void m12921a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9050a != null) {
                ajVar = (aj) this.f9050a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.117 */
    class AnonymousClass117 implements Response<ax> {
        final /* synthetic */ WeakReference f9052a;
        final /* synthetic */ DataManager f9053b;

        AnonymousClass117(DataManager dataManager, WeakReference weakReference) {
            this.f9053b = dataManager;
            this.f9052a = weakReference;
        }

        public void m12922a(ax axVar) {
            aj ajVar = null;
            if (this.f9052a != null) {
                ajVar = (aj) this.f9052a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(axVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.118 */
    class AnonymousClass118 implements Response {
        final /* synthetic */ WeakReference f9054a;
        final /* synthetic */ DataManager f9055b;

        AnonymousClass118(DataManager dataManager, WeakReference weakReference) {
            this.f9055b = dataManager;
            this.f9054a = weakReference;
        }

        public void m12924a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9054a != null) {
                ajVar = (aj) this.f9054a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.119 */
    class AnonymousClass119 implements Response<ae> {
        final /* synthetic */ WeakReference f9056a;
        final /* synthetic */ DataManager f9057b;

        AnonymousClass119(DataManager dataManager, WeakReference weakReference) {
            this.f9057b = dataManager;
            this.f9056a = weakReference;
        }

        public void m12925a(ae aeVar) {
            aj ajVar = null;
            if (this.f9056a != null) {
                ajVar = (aj) this.f9056a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(aeVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.11 */
    class AnonymousClass11 implements Response<bi> {
        final /* synthetic */ WeakReference f9058a;
        final /* synthetic */ DataManager f9059b;

        AnonymousClass11(DataManager dataManager, WeakReference weakReference) {
            this.f9059b = dataManager;
            this.f9058a = weakReference;
        }

        public void m12927a(bi biVar) {
            aj ajVar = null;
            if (this.f9058a != null) {
                ajVar = (aj) this.f9058a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(biVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.121 */
    class AnonymousClass121 implements Response {
        final /* synthetic */ WeakReference f9061a;
        final /* synthetic */ DataManager f9062b;

        AnonymousClass121(DataManager dataManager, WeakReference weakReference) {
            this.f9062b = dataManager;
            this.f9061a = weakReference;
        }

        public void m12930a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9061a != null) {
                ajVar = (aj) this.f9061a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.122 */
    class AnonymousClass122 implements Response<ae> {
        final /* synthetic */ WeakReference f9063a;
        final /* synthetic */ DataManager f9064b;

        AnonymousClass122(DataManager dataManager, WeakReference weakReference) {
            this.f9064b = dataManager;
            this.f9063a = weakReference;
        }

        public void m12931a(ae aeVar) {
            aj ajVar = null;
            if (this.f9063a != null) {
                ajVar = (aj) this.f9063a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(aeVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.123 */
    class AnonymousClass123 implements Response {
        final /* synthetic */ WeakReference f9065a;
        final /* synthetic */ DataManager f9066b;

        AnonymousClass123(DataManager dataManager, WeakReference weakReference) {
            this.f9066b = dataManager;
            this.f9065a = weakReference;
        }

        public void m12933a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9065a != null) {
                ajVar = (aj) this.f9065a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.124 */
    class AnonymousClass124 implements Response<cn> {
        final /* synthetic */ WeakReference f9067a;
        final /* synthetic */ DataManager f9068b;

        AnonymousClass124(DataManager dataManager, WeakReference weakReference) {
            this.f9068b = dataManager;
            this.f9067a = weakReference;
        }

        public void m12934a(cn cnVar) {
            aj ajVar = null;
            if (this.f9067a != null) {
                ajVar = (aj) this.f9067a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(cnVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.125 */
    class AnonymousClass125 implements Response {
        final /* synthetic */ WeakReference f9069a;
        final /* synthetic */ DataManager f9070b;

        AnonymousClass125(DataManager dataManager, WeakReference weakReference) {
            this.f9070b = dataManager;
            this.f9069a = weakReference;
        }

        public void m12936a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9069a != null) {
                ajVar = (aj) this.f9069a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.126 */
    class AnonymousClass126 implements Response<cn> {
        final /* synthetic */ WeakReference f9071a;
        final /* synthetic */ DataManager f9072b;

        AnonymousClass126(DataManager dataManager, WeakReference weakReference) {
            this.f9072b = dataManager;
            this.f9071a = weakReference;
        }

        public void m12937a(cn cnVar) {
            aj ajVar = null;
            if (this.f9071a != null) {
                ajVar = (aj) this.f9071a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(cnVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.127 */
    class AnonymousClass127 implements Response {
        final /* synthetic */ WeakReference f9073a;
        final /* synthetic */ DataManager f9074b;

        AnonymousClass127(DataManager dataManager, WeakReference weakReference) {
            this.f9074b = dataManager;
            this.f9073a = weakReference;
        }

        public void m12939a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9073a != null) {
                ajVar = (aj) this.f9073a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.128 */
    class AnonymousClass128 implements Response<ab> {
        final /* synthetic */ WeakReference f9075a;
        final /* synthetic */ DataManager f9076b;

        AnonymousClass128(DataManager dataManager, WeakReference weakReference) {
            this.f9076b = dataManager;
            this.f9075a = weakReference;
        }

        public void m12940a(ab abVar) {
            aj ajVar = null;
            if (this.f9075a != null) {
                ajVar = (aj) this.f9075a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(abVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.129 */
    class AnonymousClass129 implements Response {
        final /* synthetic */ WeakReference f9077a;
        final /* synthetic */ DataManager f9078b;

        AnonymousClass129(DataManager dataManager, WeakReference weakReference) {
            this.f9078b = dataManager;
            this.f9077a = weakReference;
        }

        public void m12942a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9077a != null) {
                ajVar = (aj) this.f9077a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.12 */
    class AnonymousClass12 implements Response {
        final /* synthetic */ WeakReference f9079a;
        final /* synthetic */ DataManager f9080b;

        AnonymousClass12(DataManager dataManager, WeakReference weakReference) {
            this.f9080b = dataManager;
            this.f9079a = weakReference;
        }

        public void m12943a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9079a != null) {
                ajVar = (aj) this.f9079a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.130 */
    class AnonymousClass130 implements Response<dj> {
        final /* synthetic */ WeakReference f9081a;
        final /* synthetic */ DataManager f9082b;

        AnonymousClass130(DataManager dataManager, WeakReference weakReference) {
            this.f9082b = dataManager;
            this.f9081a = weakReference;
        }

        public void m12944a(dj djVar) {
            aj ajVar = null;
            if (this.f9081a != null) {
                ajVar = (aj) this.f9081a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(djVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.131 */
    class AnonymousClass131 implements Response<bp> {
        final /* synthetic */ WeakReference f9083a;
        final /* synthetic */ DataManager f9084b;

        AnonymousClass131(DataManager dataManager, WeakReference weakReference) {
            this.f9084b = dataManager;
            this.f9083a = weakReference;
        }

        public void m12946a(bp bpVar) {
            aj ajVar = null;
            if (this.f9083a != null) {
                ajVar = (aj) this.f9083a.get();
            }
            if (ajVar == null) {
                return;
            }
            if (bpVar != null) {
                ajVar.onSuccess(bpVar);
            } else {
                ajVar.onFailure(new VolleyError("User login failed or Invalid auth token"));
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.132 */
    class AnonymousClass132 implements Response {
        final /* synthetic */ WeakReference f9085a;
        final /* synthetic */ DataManager f9086b;

        AnonymousClass132(DataManager dataManager, WeakReference weakReference) {
            this.f9086b = dataManager;
            this.f9085a = weakReference;
        }

        public void m12948a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9085a != null) {
                ajVar = (aj) this.f9085a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.133 */
    class AnonymousClass133 implements Response<ds> {
        final /* synthetic */ Map f9087a;
        final /* synthetic */ WeakReference f9088b;
        final /* synthetic */ DataManager f9089c;

        AnonymousClass133(DataManager dataManager, Map map, WeakReference weakReference) {
            this.f9089c = dataManager;
            this.f9087a = map;
            this.f9088b = weakReference;
        }

        public void m12949a(ds dsVar) {
            if (dsVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                dsVar.setOrigTimeStamp(System.currentTimeMillis());
                dsVar.setOrigParams(this.f9087a);
                this.f9089c.f9365o.addToCache("v3/favourites", dsVar);
            }
            aj ajVar = null;
            if (this.f9088b != null) {
                ajVar = (aj) this.f9088b.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(dsVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.134 */
    class AnonymousClass134 implements Response {
        final /* synthetic */ WeakReference f9090a;
        final /* synthetic */ DataManager f9091b;

        AnonymousClass134(DataManager dataManager, WeakReference weakReference) {
            this.f9091b = dataManager;
            this.f9090a = weakReference;
        }

        public void m12951a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9090a != null) {
                ajVar = (aj) this.f9090a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.135 */
    class AnonymousClass135 implements Runnable {
        final /* synthetic */ Map f9094a;
        final /* synthetic */ WeakReference f9095b;
        final /* synthetic */ String f9096c;
        final /* synthetic */ OlaApp f9097d;
        final /* synthetic */ Response f9098e;
        final /* synthetic */ Response f9099f;
        final /* synthetic */ DataManager f9100g;

        /* renamed from: com.olacabs.customer.app.f.135.1 */
        class DataManager implements Runnable {
            final /* synthetic */ bv f9092a;
            final /* synthetic */ AnonymousClass135 f9093b;

            DataManager(AnonymousClass135 anonymousClass135, bv bvVar) {
                this.f9093b = anonymousClass135;
                this.f9092a = bvVar;
            }

            public void run() {
                aj ajVar = null;
                if (this.f9093b.f9095b != null) {
                    ajVar = (aj) this.f9093b.f9095b.get();
                }
                if (ajVar != null && this.f9093b.f9100g.f9366p.contains(this.f9093b.f9096c)) {
                    ajVar.onSuccess(this.f9092a);
                }
            }
        }

        AnonymousClass135(DataManager dataManager, Map map, WeakReference weakReference, String str, OlaApp olaApp, Response response, Response response2) {
            this.f9100g = dataManager;
            this.f9094a = map;
            this.f9095b = weakReference;
            this.f9096c = str;
            this.f9097d = olaApp;
            this.f9098e = response;
            this.f9099f = response2;
        }

        public void run() {
            bv readFromCache = this.f9100g.f9365o.readFromCache("v3/favourites", ds.class, this.f9094a);
            if (readFromCache == null || !readFromCache.isValid(this.f9094a)) {
                OLog.m13311a("Did not find cache data for v3/favourites", new Object[0]);
            } else {
                OLog.m13311a("Found cache data for v3/favourites", new Object[0]);
                new Handler(Looper.getMainLooper()).post(new com.olacabs.customer.app.DataManager.135.DataManager(this, readFromCache));
            }
            Request instance = bw.getInstance(this.f9097d, 0, "https://apps.olacabs.com/v3/favourites", Request.Request.IMMEDIATE, this.f9094a, this.f9098e, this.f9099f, ds.class);
            instance.setTag(this.f9096c);
            this.f9100g.f9366p.add(this.f9096c);
            instance.setTag(this.f9096c);
            this.f9100g.m13168a(instance);
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.136 */
    class AnonymousClass136 implements Response<ay> {
        final /* synthetic */ WeakReference f9101a;
        final /* synthetic */ DataManager f9102b;

        AnonymousClass136(DataManager dataManager, WeakReference weakReference) {
            this.f9102b = dataManager;
            this.f9101a = weakReference;
        }

        public void m12952a(ay ayVar) {
            aj ajVar = null;
            if (this.f9101a != null) {
                ajVar = (aj) this.f9101a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(ayVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.137 */
    class AnonymousClass137 implements Response {
        final /* synthetic */ WeakReference f9103a;
        final /* synthetic */ DataManager f9104b;

        AnonymousClass137(DataManager dataManager, WeakReference weakReference) {
            this.f9104b = dataManager;
            this.f9103a = weakReference;
        }

        public void m12954a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9103a != null) {
                ajVar = (aj) this.f9103a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.138 */
    class AnonymousClass138 implements Response<AddEditFavouriteResponse> {
        final /* synthetic */ WeakReference f9105a;
        final /* synthetic */ DataManager f9106b;

        AnonymousClass138(DataManager dataManager, WeakReference weakReference) {
            this.f9106b = dataManager;
            this.f9105a = weakReference;
        }

        public void m12955a(AddEditFavouriteResponse addEditFavouriteResponse) {
            aj ajVar = null;
            if (this.f9105a != null) {
                ajVar = (aj) this.f9105a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(addEditFavouriteResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.139 */
    class AnonymousClass139 implements Response {
        final /* synthetic */ WeakReference f9107a;
        final /* synthetic */ DataManager f9108b;

        AnonymousClass139(DataManager dataManager, WeakReference weakReference) {
            this.f9108b = dataManager;
            this.f9107a = weakReference;
        }

        public void m12957a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9107a != null) {
                ajVar = (aj) this.f9107a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.13 */
    class AnonymousClass13 implements Response<Bitmap> {
        final /* synthetic */ WeakReference f9109a;
        final /* synthetic */ DataManager f9110b;

        AnonymousClass13(DataManager dataManager, WeakReference weakReference) {
            this.f9110b = dataManager;
            this.f9109a = weakReference;
        }

        public void m12958a(Bitmap bitmap) {
            aj ajVar = null;
            if (this.f9109a != null) {
                ajVar = (aj) this.f9109a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(bitmap);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.140 */
    class AnonymousClass140 implements Response<AddEditFavouriteResponse> {
        final /* synthetic */ WeakReference f9111a;
        final /* synthetic */ DataManager f9112b;

        AnonymousClass140(DataManager dataManager, WeakReference weakReference) {
            this.f9112b = dataManager;
            this.f9111a = weakReference;
        }

        public void m12960a(AddEditFavouriteResponse addEditFavouriteResponse) {
            aj ajVar = null;
            if (this.f9111a != null) {
                ajVar = (aj) this.f9111a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(addEditFavouriteResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.141 */
    class AnonymousClass141 implements Response {
        final /* synthetic */ WeakReference f9113a;
        final /* synthetic */ DataManager f9114b;

        AnonymousClass141(DataManager dataManager, WeakReference weakReference) {
            this.f9114b = dataManager;
            this.f9113a = weakReference;
        }

        public void m12962a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9113a != null) {
                ajVar = (aj) this.f9113a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.142 */
    class AnonymousClass142 implements Response {
        final /* synthetic */ WeakReference f9115a;
        final /* synthetic */ DataManager f9116b;

        AnonymousClass142(DataManager dataManager, WeakReference weakReference) {
            this.f9116b = dataManager;
            this.f9115a = weakReference;
        }

        public void m12963a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9115a != null) {
                ajVar = (aj) this.f9115a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.143 */
    class AnonymousClass143 implements Response<af> {
        final /* synthetic */ WeakReference f9117a;
        final /* synthetic */ DataManager f9118b;

        AnonymousClass143(DataManager dataManager, WeakReference weakReference) {
            this.f9118b = dataManager;
            this.f9117a = weakReference;
        }

        public void m12964a(af afVar) {
            aj ajVar = null;
            if (this.f9117a != null) {
                ajVar = (aj) this.f9117a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(afVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.144 */
    class AnonymousClass144 implements Response {
        final /* synthetic */ WeakReference f9119a;
        final /* synthetic */ DataManager f9120b;

        AnonymousClass144(DataManager dataManager, WeakReference weakReference) {
            this.f9120b = dataManager;
            this.f9119a = weakReference;
        }

        public void m12966a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9119a != null) {
                ajVar = (aj) this.f9119a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.145 */
    class AnonymousClass145 implements Response<cl> {
        final /* synthetic */ WeakReference f9121a;
        final /* synthetic */ DataManager f9122b;

        AnonymousClass145(DataManager dataManager, WeakReference weakReference) {
            this.f9122b = dataManager;
            this.f9121a = weakReference;
        }

        public void m12967a(cl clVar) {
            aj ajVar = null;
            if (this.f9121a != null) {
                ajVar = (aj) this.f9121a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(clVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.146 */
    class AnonymousClass146 implements Response {
        final /* synthetic */ WeakReference f9123a;
        final /* synthetic */ DataManager f9124b;

        AnonymousClass146(DataManager dataManager, WeakReference weakReference) {
            this.f9124b = dataManager;
            this.f9123a = weakReference;
        }

        public void m12969a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9123a != null) {
                ajVar = (aj) this.f9123a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.147 */
    class AnonymousClass147 implements Response<di> {
        final /* synthetic */ WeakReference f9125a;
        final /* synthetic */ DataManager f9126b;

        AnonymousClass147(DataManager dataManager, WeakReference weakReference) {
            this.f9126b = dataManager;
            this.f9125a = weakReference;
        }

        public void m12970a(di diVar) {
            aj ajVar = null;
            if (this.f9125a != null) {
                ajVar = (aj) this.f9125a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(diVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.148 */
    class AnonymousClass148 implements Response {
        final /* synthetic */ WeakReference f9127a;
        final /* synthetic */ DataManager f9128b;

        AnonymousClass148(DataManager dataManager, WeakReference weakReference) {
            this.f9128b = dataManager;
            this.f9127a = weakReference;
        }

        public void m12972a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9127a != null) {
                ajVar = (aj) this.f9127a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.149 */
    class AnonymousClass149 implements Response<cd> {
        final /* synthetic */ WeakReference f9129a;
        final /* synthetic */ DataManager f9130b;

        AnonymousClass149(DataManager dataManager, WeakReference weakReference) {
            this.f9130b = dataManager;
            this.f9129a = weakReference;
        }

        public void m12973a(cd cdVar) {
            OLog.m13311a("Hash generated successfully ", new Object[0]);
            aj ajVar = null;
            if (this.f9129a != null) {
                ajVar = (aj) this.f9129a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(cdVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.14 */
    class AnonymousClass14 implements Response<BasicResponse> {
        final /* synthetic */ WeakReference f9131a;
        final /* synthetic */ DataManager f9132b;

        AnonymousClass14(DataManager dataManager, WeakReference weakReference) {
            this.f9132b = dataManager;
            this.f9131a = weakReference;
        }

        public void m12975a(BasicResponse basicResponse) {
            aj ajVar = null;
            if (this.f9131a != null) {
                ajVar = (aj) this.f9131a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(basicResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.150 */
    class AnonymousClass150 implements Response {
        final /* synthetic */ WeakReference f9133a;
        final /* synthetic */ DataManager f9134b;

        AnonymousClass150(DataManager dataManager, WeakReference weakReference) {
            this.f9134b = dataManager;
            this.f9133a = weakReference;
        }

        public void m12977a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9133a != null) {
                ajVar = (aj) this.f9133a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.151 */
    class AnonymousClass151 implements Response<ca> {
        final /* synthetic */ WeakReference f9135a;
        final /* synthetic */ DataManager f9136b;

        AnonymousClass151(DataManager dataManager, WeakReference weakReference) {
            this.f9136b = dataManager;
            this.f9135a = weakReference;
        }

        public void m12978a(ca caVar) {
            aj ajVar = null;
            if (this.f9135a != null) {
                ajVar = (aj) this.f9135a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(caVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.152 */
    class AnonymousClass152 implements Response<cb> {
        final /* synthetic */ WeakReference f9137a;
        final /* synthetic */ DataManager f9138b;

        AnonymousClass152(DataManager dataManager, WeakReference weakReference) {
            this.f9138b = dataManager;
            this.f9137a = weakReference;
        }

        public void m12980a(cb cbVar) {
            aj ajVar = null;
            if (this.f9137a != null) {
                ajVar = (aj) this.f9137a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(cbVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.153 */
    class AnonymousClass153 implements Response {
        final /* synthetic */ WeakReference f9139a;
        final /* synthetic */ DataManager f9140b;

        AnonymousClass153(DataManager dataManager, WeakReference weakReference) {
            this.f9140b = dataManager;
            this.f9139a = weakReference;
        }

        public void m12982a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9139a != null) {
                ajVar = (aj) this.f9139a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.154 */
    class AnonymousClass154 implements Response<ChangePasswordResponse> {
        final /* synthetic */ WeakReference f9141a;
        final /* synthetic */ DataManager f9142b;

        AnonymousClass154(DataManager dataManager, WeakReference weakReference) {
            this.f9142b = dataManager;
            this.f9141a = weakReference;
        }

        public void m12983a(ChangePasswordResponse changePasswordResponse) {
            aj ajVar = null;
            if (this.f9141a != null) {
                ajVar = (aj) this.f9141a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(changePasswordResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.155 */
    class AnonymousClass155 implements Response {
        final /* synthetic */ WeakReference f9143a;
        final /* synthetic */ DataManager f9144b;

        AnonymousClass155(DataManager dataManager, WeakReference weakReference) {
            this.f9144b = dataManager;
            this.f9143a = weakReference;
        }

        public void m12985a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9143a != null) {
                ajVar = (aj) this.f9143a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.156 */
    class AnonymousClass156 implements Response<cj> {
        final /* synthetic */ WeakReference f9145a;
        final /* synthetic */ DataManager f9146b;

        AnonymousClass156(DataManager dataManager, WeakReference weakReference) {
            this.f9146b = dataManager;
            this.f9145a = weakReference;
        }

        public void m12986a(cj cjVar) {
            aj ajVar = null;
            if (this.f9145a != null) {
                ajVar = (aj) this.f9145a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(cjVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.157 */
    class AnonymousClass157 implements Response {
        final /* synthetic */ WeakReference f9147a;
        final /* synthetic */ DataManager f9148b;

        AnonymousClass157(DataManager dataManager, WeakReference weakReference) {
            this.f9148b = dataManager;
            this.f9147a = weakReference;
        }

        public void m12988a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9147a != null) {
                ajVar = (aj) this.f9147a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.158 */
    class AnonymousClass158 implements Response<ae> {
        final /* synthetic */ WeakReference f9149a;
        final /* synthetic */ DataManager f9150b;

        AnonymousClass158(DataManager dataManager, WeakReference weakReference) {
            this.f9150b = dataManager;
            this.f9149a = weakReference;
        }

        public void m12989a(ae aeVar) {
            aj ajVar = null;
            if (this.f9149a != null) {
                ajVar = (aj) this.f9149a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(aeVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.159 */
    class AnonymousClass159 implements Response {
        final /* synthetic */ WeakReference f9151a;
        final /* synthetic */ DataManager f9152b;

        AnonymousClass159(DataManager dataManager, WeakReference weakReference) {
            this.f9152b = dataManager;
            this.f9151a = weakReference;
        }

        public void m12991a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9151a != null) {
                ajVar = (aj) this.f9151a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.15 */
    class AnonymousClass15 implements Response {
        final /* synthetic */ WeakReference f9153a;
        final /* synthetic */ DataManager f9154b;

        AnonymousClass15(DataManager dataManager, WeakReference weakReference) {
            this.f9154b = dataManager;
            this.f9153a = weakReference;
        }

        public void m12992a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9153a != null) {
                ajVar = (aj) this.f9153a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.160 */
    class AnonymousClass160 implements Response<dh> {
        final /* synthetic */ WeakReference f9155a;
        final /* synthetic */ DataManager f9156b;

        AnonymousClass160(DataManager dataManager, WeakReference weakReference) {
            this.f9156b = dataManager;
            this.f9155a = weakReference;
        }

        public void m12993a(dh dhVar) {
            aj ajVar = null;
            if (this.f9155a != null) {
                ajVar = (aj) this.f9155a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(dhVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.161 */
    class AnonymousClass161 implements Response {
        final /* synthetic */ WeakReference f9157a;
        final /* synthetic */ DataManager f9158b;

        AnonymousClass161(DataManager dataManager, WeakReference weakReference) {
            this.f9158b = dataManager;
            this.f9157a = weakReference;
        }

        public void m12995a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9157a != null) {
                ajVar = (aj) this.f9157a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.16 */
    class AnonymousClass16 implements Response<BookingCabInfoResponse> {
        final /* synthetic */ WeakReference f9159a;
        final /* synthetic */ Boolean f9160b;
        final /* synthetic */ DataManager f9161c;

        AnonymousClass16(DataManager dataManager, WeakReference weakReference, Boolean bool) {
            this.f9161c = dataManager;
            this.f9159a = weakReference;
            this.f9160b = bool;
        }

        public void m12996a(BookingCabInfoResponse bookingCabInfoResponse) {
            aj ajVar = null;
            if (this.f9159a != null) {
                ajVar = (aj) this.f9159a.get();
            }
            if (this.f9160b.booleanValue()) {
                DataUpdaterManager.m13261a(this.f9161c.f9355e).m13263a().m13322a("cab_info", bookingCabInfoResponse);
            } else if (ajVar != null) {
                ajVar.onSuccess(bookingCabInfoResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.17 */
    class AnonymousClass17 implements Response {
        final /* synthetic */ WeakReference f9162a;
        final /* synthetic */ Boolean f9163b;
        final /* synthetic */ DataManager f9164c;

        AnonymousClass17(DataManager dataManager, WeakReference weakReference, Boolean bool) {
            this.f9164c = dataManager;
            this.f9162a = weakReference;
            this.f9163b = bool;
        }

        public void m12998a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9162a != null) {
                ajVar = (aj) this.f9162a.get();
            }
            if (this.f9163b.booleanValue()) {
                DataUpdaterManager.m13261a(this.f9164c.f9355e).m13263a().m13323b();
            } else if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.18 */
    class AnonymousClass18 implements Response<ct> {
        final /* synthetic */ WeakReference f9165a;
        final /* synthetic */ DataManager f9166b;

        AnonymousClass18(DataManager dataManager, WeakReference weakReference) {
            this.f9166b = dataManager;
            this.f9165a = weakReference;
        }

        public void m12999a(ct ctVar) {
            aj ajVar = null;
            if (this.f9165a != null) {
                ajVar = (aj) this.f9165a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(ctVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.19 */
    class AnonymousClass19 implements Response {
        final /* synthetic */ WeakReference f9167a;
        final /* synthetic */ DataManager f9168b;

        AnonymousClass19(DataManager dataManager, WeakReference weakReference) {
            this.f9168b = dataManager;
            this.f9167a = weakReference;
        }

        public void m13001a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9167a != null) {
                ajVar = (aj) this.f9167a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* renamed from: com.olacabs.customer.app.f.1 */
    class DataManager implements Runnable {
        final /* synthetic */ DataManager f9169a;

        DataManager(DataManager dataManager) {
            this.f9169a = dataManager;
        }

        public void run() {
            synchronized (this.f9169a.f9354d) {
                this.f9169a.f9357g = AppInfo.getInstance(this.f9169a.f9355e);
                this.f9169a.f9357g.init();
                this.f9169a.f9358h = dt.getInstance(this.f9169a.f9355e);
                this.f9169a.f9358h.init();
                this.f9169a.f9360j = ao.getInstance(this.f9169a.f9355e);
                this.f9169a.f9360j.init();
                this.f9169a.f9359i = da.getInstance(this.f9169a.f9355e);
                this.f9169a.f9359i.init();
                this.f9169a.f9367q = new ag(Utils.m14909a(this.f9169a.f9355e));
                this.f9169a.f9355e.registerReceiver(this.f9169a.f9367q, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                this.f9169a.f9363m = new C0220a(this.f9169a.f9355e).m3208a(this.f9169a).m3209a(this.f9169a).m3207a(C0532g.f2650a).m3211b();
                this.f9169a.f9363m.m3227b();
                this.f9169a.f9364n = this.f9169a.m13162r();
                this.f9169a.f9365o = CachingHandler.getInstance(this.f9169a.f9355e);
                this.f9169a.f9365o.init();
                this.f9169a.f9366p = new HashSet();
                this.f9169a.f9362l = ImageCacheManager.m13274a();
                this.f9169a.f9362l.m13275a(this.f9169a.f9355e, 10485760, this.f9169a.f9364n);
                this.f9169a.f9356f = true;
                this.f9169a.f9354d.notifyAll();
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.20 */
    class AnonymousClass20 implements Response<BasicResponse> {
        final /* synthetic */ WeakReference f9170a;
        final /* synthetic */ DataManager f9171b;

        AnonymousClass20(DataManager dataManager, WeakReference weakReference) {
            this.f9171b = dataManager;
            this.f9170a = weakReference;
        }

        public void m13002a(BasicResponse basicResponse) {
            aj ajVar = null;
            if (this.f9170a != null) {
                ajVar = (aj) this.f9170a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(basicResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.21 */
    class AnonymousClass21 implements Response {
        final /* synthetic */ WeakReference f9172a;
        final /* synthetic */ DataManager f9173b;

        AnonymousClass21(DataManager dataManager, WeakReference weakReference) {
            this.f9173b = dataManager;
            this.f9172a = weakReference;
        }

        public void m13004a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9172a != null) {
                ajVar = (aj) this.f9172a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.22 */
    class AnonymousClass22 implements Response<BookingCabInfoResponse> {
        final /* synthetic */ WeakReference f9174a;
        final /* synthetic */ DataManager f9175b;

        AnonymousClass22(DataManager dataManager, WeakReference weakReference) {
            this.f9175b = dataManager;
            this.f9174a = weakReference;
        }

        public void m13005a(BookingCabInfoResponse bookingCabInfoResponse) {
            aj ajVar = null;
            if (this.f9174a != null) {
                ajVar = (aj) this.f9174a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(bookingCabInfoResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.23 */
    class AnonymousClass23 implements Response {
        final /* synthetic */ WeakReference f9176a;
        final /* synthetic */ DataManager f9177b;

        AnonymousClass23(DataManager dataManager, WeakReference weakReference) {
            this.f9177b = dataManager;
            this.f9176a = weakReference;
        }

        public void m13007a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9176a != null) {
                ajVar = (aj) this.f9176a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.24 */
    class AnonymousClass24 implements Response {
        final /* synthetic */ WeakReference f9178a;
        final /* synthetic */ DataManager f9179b;

        AnonymousClass24(DataManager dataManager, WeakReference weakReference) {
            this.f9179b = dataManager;
            this.f9178a = weakReference;
        }

        public void m13008a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9178a != null) {
                ajVar = (aj) this.f9178a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.25 */
    class AnonymousClass25 implements Response<bf[]> {
        final /* synthetic */ WeakReference f9180a;
        final /* synthetic */ DataManager f9181b;

        AnonymousClass25(DataManager dataManager, WeakReference weakReference) {
            this.f9181b = dataManager;
            this.f9180a = weakReference;
        }

        public void m13010a(bf[] bfVarArr) {
            aj ajVar = null;
            if (this.f9180a != null) {
                ajVar = (aj) this.f9180a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(bfVarArr);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.26 */
    class AnonymousClass26 implements Response {
        final /* synthetic */ WeakReference f9182a;
        final /* synthetic */ DataManager f9183b;

        AnonymousClass26(DataManager dataManager, WeakReference weakReference) {
            this.f9183b = dataManager;
            this.f9182a = weakReference;
        }

        public void m13011a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9182a != null) {
                ajVar = (aj) this.f9182a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.27 */
    class AnonymousClass27 implements Response<be> {
        final /* synthetic */ WeakReference f9184a;
        final /* synthetic */ DataManager f9185b;

        AnonymousClass27(DataManager dataManager, WeakReference weakReference) {
            this.f9185b = dataManager;
            this.f9184a = weakReference;
        }

        public void m13012a(be beVar) {
            aj ajVar = null;
            if (this.f9184a != null) {
                ajVar = (aj) this.f9184a.get();
            }
            if (ajVar == null) {
                return;
            }
            if (beVar == null || !beVar.isValid()) {
                ajVar.onFailure(this.f9185b.m13134a((int) R.string.error_generic_ofd_title, (int) R.string.error_unable_fetch_food_message));
            } else {
                ajVar.onSuccess(beVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.28 */
    class AnonymousClass28 implements Response {
        final /* synthetic */ WeakReference f9186a;
        final /* synthetic */ DataManager f9187b;

        AnonymousClass28(DataManager dataManager, WeakReference weakReference) {
            this.f9187b = dataManager;
            this.f9186a = weakReference;
        }

        public void m13014a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9186a != null) {
                ajVar = (aj) this.f9186a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.29 */
    class AnonymousClass29 implements Response<JSONObject> {
        final /* synthetic */ WeakReference f9188a;
        final /* synthetic */ DataManager f9189b;

        AnonymousClass29(DataManager dataManager, WeakReference weakReference) {
            this.f9189b = dataManager;
            this.f9188a = weakReference;
        }

        public void m13016a(JSONObject jSONObject) {
            aj ajVar;
            CheckoutResponse checkoutResponse;
            OLog.m13311a("Success checkout returned a response", new Object[0]);
            if (this.f9188a != null) {
                ajVar = (aj) this.f9188a.get();
            } else {
                ajVar = null;
            }
            if (jSONObject != null) {
                if (Utils.m14924g(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject))) {
                    checkoutResponse = (CheckoutResponse) new Gson().m12343a(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject), CheckoutResponse.class);
                    if (ajVar == null) {
                    }
                    if (checkoutResponse == null && checkoutResponse.isValid()) {
                        ajVar.onSuccess(checkoutResponse);
                        return;
                    } else {
                        ajVar.onFailure(this.f9189b.m13134a((int) R.string.error_generic_ofd_title, (int) R.string.error_unable_to_connect_message));
                    }
                }
            }
            checkoutResponse = null;
            if (ajVar == null) {
                if (checkoutResponse == null) {
                }
                ajVar.onFailure(this.f9189b.m13134a((int) R.string.error_generic_ofd_title, (int) R.string.error_unable_to_connect_message));
            }
        }
    }

    /* renamed from: com.olacabs.customer.app.f.2 */
    class DataManager implements Response {
        final /* synthetic */ WeakReference f9190a;
        final /* synthetic */ DataManager f9191b;

        DataManager(DataManager dataManager, WeakReference weakReference) {
            this.f9191b = dataManager;
            this.f9190a = weakReference;
        }

        public void m13017a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9190a != null) {
                ajVar = (aj) this.f9190a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.30 */
    class AnonymousClass30 implements Response {
        final /* synthetic */ WeakReference f9192a;
        final /* synthetic */ DataManager f9193b;

        AnonymousClass30(DataManager dataManager, WeakReference weakReference) {
            this.f9193b = dataManager;
            this.f9192a = weakReference;
        }

        public void m13018a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9192a != null) {
                ajVar = (aj) this.f9192a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.31 */
    class AnonymousClass31 implements Response<du> {
        final /* synthetic */ WeakReference f9194a;
        final /* synthetic */ DataManager f9195b;

        AnonymousClass31(DataManager dataManager, WeakReference weakReference) {
            this.f9195b = dataManager;
            this.f9194a = weakReference;
        }

        public void m13019a(du duVar) {
            aj ajVar = null;
            if (this.f9194a != null) {
                ajVar = (aj) this.f9194a.get();
            }
            if (ajVar == null) {
                return;
            }
            if (duVar == null || !duVar.isValid()) {
                ajVar.onFailure(this.f9195b.m13134a((int) R.string.error_generic_ofd_title, (int) R.string.error_unable_to_connect_message));
            } else {
                ajVar.onSuccess(duVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.32 */
    class AnonymousClass32 implements Response {
        final /* synthetic */ WeakReference f9196a;
        final /* synthetic */ DataManager f9197b;

        AnonymousClass32(DataManager dataManager, WeakReference weakReference) {
            this.f9197b = dataManager;
            this.f9196a = weakReference;
        }

        public void m13021a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9196a != null) {
                ajVar = (aj) this.f9196a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.33 */
    class AnonymousClass33 implements Response<du> {
        final /* synthetic */ WeakReference f9198a;
        final /* synthetic */ DataManager f9199b;

        AnonymousClass33(DataManager dataManager, WeakReference weakReference) {
            this.f9199b = dataManager;
            this.f9198a = weakReference;
        }

        public void m13022a(du duVar) {
            aj ajVar = null;
            if (this.f9198a != null) {
                ajVar = (aj) this.f9198a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(duVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.34 */
    class AnonymousClass34 implements Response {
        final /* synthetic */ WeakReference f9200a;
        final /* synthetic */ DataManager f9201b;

        AnonymousClass34(DataManager dataManager, WeakReference weakReference) {
            this.f9201b = dataManager;
            this.f9200a = weakReference;
        }

        public void m13024a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9200a != null) {
                ajVar = (aj) this.f9200a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.35 */
    class AnonymousClass35 implements Response<Bitmap> {
        final /* synthetic */ WeakReference f9202a;
        final /* synthetic */ DataManager f9203b;

        AnonymousClass35(DataManager dataManager, WeakReference weakReference) {
            this.f9203b = dataManager;
            this.f9202a = weakReference;
        }

        public void m13025a(Bitmap bitmap) {
            aj ajVar = null;
            if (this.f9202a != null) {
                ajVar = (aj) this.f9202a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(bitmap);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.36 */
    class AnonymousClass36 implements Response<bb> {
        final /* synthetic */ WeakReference f9204a;
        final /* synthetic */ DataManager f9205b;

        AnonymousClass36(DataManager dataManager, WeakReference weakReference) {
            this.f9205b = dataManager;
            this.f9204a = weakReference;
        }

        public void m13027a(bb bbVar) {
            aj ajVar = null;
            if (this.f9204a != null) {
                ajVar = (aj) this.f9204a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(bbVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.37 */
    class AnonymousClass37 implements Response {
        final /* synthetic */ WeakReference f9206a;
        final /* synthetic */ DataManager f9207b;

        AnonymousClass37(DataManager dataManager, WeakReference weakReference) {
            this.f9207b = dataManager;
            this.f9206a = weakReference;
        }

        public void m13029a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9206a != null) {
                ajVar = (aj) this.f9206a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.38 */
    class AnonymousClass38 implements Response<bu> {
        final /* synthetic */ WeakReference f9208a;
        final /* synthetic */ DataManager f9209b;

        AnonymousClass38(DataManager dataManager, WeakReference weakReference) {
            this.f9209b = dataManager;
            this.f9208a = weakReference;
        }

        public void m13030a(bu buVar) {
            aj ajVar = null;
            if (this.f9208a != null) {
                ajVar = (aj) this.f9208a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(buVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.39 */
    class AnonymousClass39 implements Response {
        final /* synthetic */ WeakReference f9210a;
        final /* synthetic */ DataManager f9211b;

        AnonymousClass39(DataManager dataManager, WeakReference weakReference) {
            this.f9211b = dataManager;
            this.f9210a = weakReference;
        }

        public void m13032a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9210a != null) {
                ajVar = (aj) this.f9210a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* renamed from: com.olacabs.customer.app.f.3 */
    class DataManager implements Response<CitiesLocalitiesResponse> {
        final /* synthetic */ WeakReference f9212a;
        final /* synthetic */ DataManager f9213b;

        DataManager(DataManager dataManager, WeakReference weakReference) {
            this.f9213b = dataManager;
            this.f9212a = weakReference;
        }

        public void m13033a(CitiesLocalitiesResponse citiesLocalitiesResponse) {
            aj ajVar = null;
            if (this.f9212a != null) {
                ajVar = (aj) this.f9212a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(citiesLocalitiesResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.40 */
    class AnonymousClass40 implements Response<BasicResponse> {
        final /* synthetic */ WeakReference f9214a;
        final /* synthetic */ DataManager f9215b;

        AnonymousClass40(DataManager dataManager, WeakReference weakReference) {
            this.f9215b = dataManager;
            this.f9214a = weakReference;
        }

        public void m13035a(BasicResponse basicResponse) {
            aj ajVar = null;
            if (this.f9214a != null) {
                ajVar = (aj) this.f9214a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(basicResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.41 */
    class AnonymousClass41 implements Response {
        final /* synthetic */ WeakReference f9216a;
        final /* synthetic */ DataManager f9217b;

        AnonymousClass41(DataManager dataManager, WeakReference weakReference) {
            this.f9217b = dataManager;
            this.f9216a = weakReference;
        }

        public void m13037a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9216a != null) {
                ajVar = (aj) this.f9216a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.42 */
    class AnonymousClass42 implements Response<BasicResponse> {
        final /* synthetic */ WeakReference f9218a;
        final /* synthetic */ DataManager f9219b;

        AnonymousClass42(DataManager dataManager, WeakReference weakReference) {
            this.f9219b = dataManager;
            this.f9218a = weakReference;
        }

        public void m13038a(BasicResponse basicResponse) {
            aj ajVar = null;
            if (this.f9218a != null) {
                ajVar = (aj) this.f9218a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(basicResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.43 */
    class AnonymousClass43 implements Response {
        final /* synthetic */ WeakReference f9220a;
        final /* synthetic */ DataManager f9221b;

        AnonymousClass43(DataManager dataManager, WeakReference weakReference) {
            this.f9221b = dataManager;
            this.f9220a = weakReference;
        }

        public void m13040a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9220a != null) {
                ajVar = (aj) this.f9220a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.45 */
    class AnonymousClass45 implements Response<AuthSessionResponse> {
        final /* synthetic */ WeakReference f9223a;
        final /* synthetic */ DataManager f9224b;

        AnonymousClass45(DataManager dataManager, WeakReference weakReference) {
            this.f9224b = dataManager;
            this.f9223a = weakReference;
        }

        public void m13043a(AuthSessionResponse authSessionResponse) {
            aj ajVar = null;
            if (this.f9223a != null) {
                ajVar = (aj) this.f9223a.get();
            }
            if (ajVar == null) {
                return;
            }
            if (authSessionResponse == null || !authSessionResponse.isValid()) {
                OLog.m13317d("Unable to create auth token for logged in user", new Object[0]);
                ajVar.onFailure(new VolleyError("Unable to create auth token"));
                return;
            }
            ajVar.onSuccess(authSessionResponse);
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.46 */
    class AnonymousClass46 implements Response {
        final /* synthetic */ WeakReference f9225a;
        final /* synthetic */ DataManager f9226b;

        AnonymousClass46(DataManager dataManager, WeakReference weakReference) {
            this.f9226b = dataManager;
            this.f9225a = weakReference;
        }

        public void m13045a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9225a != null) {
                ajVar = (aj) this.f9225a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.47 */
    class AnonymousClass47 implements Response {
        final /* synthetic */ WeakReference f9227a;
        final /* synthetic */ DataManager f9228b;

        AnonymousClass47(DataManager dataManager, WeakReference weakReference) {
            this.f9228b = dataManager;
            this.f9227a = weakReference;
        }

        public void m13046a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9227a != null) {
                ajVar = (aj) this.f9227a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.49 */
    class AnonymousClass49 implements Response<JSONObject> {
        final /* synthetic */ WeakReference f9230a;
        final /* synthetic */ DataManager f9231b;

        AnonymousClass49(DataManager dataManager, WeakReference weakReference) {
            this.f9231b = dataManager;
            this.f9230a = weakReference;
        }

        public void m13050a(JSONObject jSONObject) {
            aj ajVar;
            if (this.f9230a != null) {
                ajVar = (aj) this.f9230a.get();
            } else {
                ajVar = null;
            }
            if (ajVar == null) {
                return;
            }
            if (jSONObject != null) {
                AuthRefreshResponse authRefreshResponse = (AuthRefreshResponse) new Gson().m12343a(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject), AuthRefreshResponse.class);
                if (authRefreshResponse.isValid()) {
                    ajVar.onSuccess(authRefreshResponse);
                    return;
                }
                OLog.m13317d("Refresh token is in valid or expiry threshold time 0", new Object[0]);
                ajVar.onFailure(new VolleyError("Refresh token is in valid or expiry threshold time 0"));
                return;
            }
            OLog.m13317d("Refresh token json object is null", new Object[0]);
            ajVar.onFailure(new VolleyError("Refresh token json object is null"));
        }
    }

    /* renamed from: com.olacabs.customer.app.f.4 */
    class DataManager implements Response {
        final /* synthetic */ WeakReference f9232a;
        final /* synthetic */ DataManager f9233b;

        DataManager(DataManager dataManager, WeakReference weakReference) {
            this.f9233b = dataManager;
            this.f9232a = weakReference;
        }

        public void m13051a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9232a != null) {
                ajVar = (aj) this.f9232a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.50 */
    class AnonymousClass50 implements Response {
        final /* synthetic */ WeakReference f9234a;
        final /* synthetic */ DataManager f9235b;

        AnonymousClass50(DataManager dataManager, WeakReference weakReference) {
            this.f9235b = dataManager;
            this.f9234a = weakReference;
        }

        public void m13052a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9234a != null) {
                ajVar = (aj) this.f9234a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.51 */
    class AnonymousClass51 implements Response<TFSFareResponse> {
        final /* synthetic */ WeakReference f9236a;
        final /* synthetic */ DataManager f9237b;

        AnonymousClass51(DataManager dataManager, WeakReference weakReference) {
            this.f9237b = dataManager;
            this.f9236a = weakReference;
        }

        public void m13053a(TFSFareResponse tFSFareResponse) {
            aj ajVar = null;
            if (this.f9236a != null) {
                ajVar = (aj) this.f9236a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(tFSFareResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.52 */
    class AnonymousClass52 implements Response {
        final /* synthetic */ WeakReference f9238a;
        final /* synthetic */ DataManager f9239b;

        AnonymousClass52(DataManager dataManager, WeakReference weakReference) {
            this.f9239b = dataManager;
            this.f9238a = weakReference;
        }

        public void m13055a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9238a != null) {
                ajVar = (aj) this.f9238a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.53 */
    class AnonymousClass53 implements Response<TFSCabInfoResponse> {
        final /* synthetic */ WeakReference f9240a;
        final /* synthetic */ DataManager f9241b;

        AnonymousClass53(DataManager dataManager, WeakReference weakReference) {
            this.f9241b = dataManager;
            this.f9240a = weakReference;
        }

        public void m13056a(TFSCabInfoResponse tFSCabInfoResponse) {
            aj ajVar = null;
            if (this.f9240a != null) {
                ajVar = (aj) this.f9240a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(tFSCabInfoResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.54 */
    class AnonymousClass54 implements Response {
        final /* synthetic */ WeakReference f9242a;
        final /* synthetic */ DataManager f9243b;

        AnonymousClass54(DataManager dataManager, WeakReference weakReference) {
            this.f9243b = dataManager;
            this.f9242a = weakReference;
        }

        public void m13058a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9242a != null) {
                ajVar = (aj) this.f9242a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.55 */
    class AnonymousClass55 implements Response<TFSBookingCreateResponse> {
        final /* synthetic */ WeakReference f9244a;
        final /* synthetic */ DataManager f9245b;

        AnonymousClass55(DataManager dataManager, WeakReference weakReference) {
            this.f9245b = dataManager;
            this.f9244a = weakReference;
        }

        public void m13059a(TFSBookingCreateResponse tFSBookingCreateResponse) {
            aj ajVar = null;
            if (this.f9244a != null) {
                ajVar = (aj) this.f9244a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(tFSBookingCreateResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.56 */
    class AnonymousClass56 implements Response {
        final /* synthetic */ WeakReference f9246a;
        final /* synthetic */ DataManager f9247b;

        AnonymousClass56(DataManager dataManager, WeakReference weakReference) {
            this.f9247b = dataManager;
            this.f9246a = weakReference;
        }

        public void m13061a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9246a != null) {
                ajVar = (aj) this.f9246a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.57 */
    class AnonymousClass57 implements Response<cm> {
        final /* synthetic */ WeakReference f9248a;
        final /* synthetic */ DataManager f9249b;

        AnonymousClass57(DataManager dataManager, WeakReference weakReference) {
            this.f9249b = dataManager;
            this.f9248a = weakReference;
        }

        public void m13062a(cm cmVar) {
            aj ajVar = null;
            if (this.f9248a != null) {
                ajVar = (aj) this.f9248a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(cmVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.58 */
    class AnonymousClass58 implements Response<BasicResponse> {
        final /* synthetic */ WeakReference f9250a;
        final /* synthetic */ DataManager f9251b;

        AnonymousClass58(DataManager dataManager, WeakReference weakReference) {
            this.f9251b = dataManager;
            this.f9250a = weakReference;
        }

        public void m13064a(BasicResponse basicResponse) {
            aj ajVar = null;
            if (this.f9250a != null) {
                ajVar = (aj) this.f9250a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(basicResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.59 */
    class AnonymousClass59 implements Response {
        final /* synthetic */ WeakReference f9252a;
        final /* synthetic */ DataManager f9253b;

        AnonymousClass59(DataManager dataManager, WeakReference weakReference) {
            this.f9253b = dataManager;
            this.f9252a = weakReference;
        }

        public void m13066a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9252a != null) {
                ajVar = (aj) this.f9252a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* renamed from: com.olacabs.customer.app.f.5 */
    class DataManager implements Response<dh> {
        final /* synthetic */ WeakReference f9254a;
        final /* synthetic */ DataManager f9255b;

        DataManager(DataManager dataManager, WeakReference weakReference) {
            this.f9255b = dataManager;
            this.f9254a = weakReference;
        }

        public void m13067a(dh dhVar) {
            aj ajVar = null;
            if (this.f9254a != null) {
                ajVar = (aj) this.f9254a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(dhVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.60 */
    class AnonymousClass60 implements Response<TFSTrackTaxiResponse> {
        final /* synthetic */ WeakReference f9256a;
        final /* synthetic */ DataManager f9257b;

        AnonymousClass60(DataManager dataManager, WeakReference weakReference) {
            this.f9257b = dataManager;
            this.f9256a = weakReference;
        }

        public void m13069a(TFSTrackTaxiResponse tFSTrackTaxiResponse) {
            aj ajVar = null;
            if (this.f9256a != null) {
                ajVar = (aj) this.f9256a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(tFSTrackTaxiResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.61 */
    class AnonymousClass61 implements Response {
        final /* synthetic */ WeakReference f9258a;
        final /* synthetic */ DataManager f9259b;

        AnonymousClass61(DataManager dataManager, WeakReference weakReference) {
            this.f9259b = dataManager;
            this.f9258a = weakReference;
        }

        public void m13071a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9258a != null) {
                ajVar = (aj) this.f9258a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.62 */
    class AnonymousClass62 implements Response<TFSCallDriverResponse> {
        final /* synthetic */ WeakReference f9260a;
        final /* synthetic */ DataManager f9261b;

        AnonymousClass62(DataManager dataManager, WeakReference weakReference) {
            this.f9261b = dataManager;
            this.f9260a = weakReference;
        }

        public void m13072a(TFSCallDriverResponse tFSCallDriverResponse) {
            aj ajVar = null;
            if (this.f9260a != null) {
                ajVar = (aj) this.f9260a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(tFSCallDriverResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.63 */
    class AnonymousClass63 implements Response {
        final /* synthetic */ WeakReference f9262a;
        final /* synthetic */ DataManager f9263b;

        AnonymousClass63(DataManager dataManager, WeakReference weakReference) {
            this.f9263b = dataManager;
            this.f9262a = weakReference;
        }

        public void m13074a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9262a != null) {
                ajVar = (aj) this.f9262a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.64 */
    class AnonymousClass64 implements Response<TFSShareRideResponse> {
        final /* synthetic */ WeakReference f9264a;
        final /* synthetic */ DataManager f9265b;

        AnonymousClass64(DataManager dataManager, WeakReference weakReference) {
            this.f9265b = dataManager;
            this.f9264a = weakReference;
        }

        public void m13075a(TFSShareRideResponse tFSShareRideResponse) {
            aj ajVar = null;
            if (this.f9264a != null) {
                ajVar = (aj) this.f9264a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(tFSShareRideResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.65 */
    class AnonymousClass65 implements Response {
        final /* synthetic */ WeakReference f9266a;
        final /* synthetic */ DataManager f9267b;

        AnonymousClass65(DataManager dataManager, WeakReference weakReference) {
            this.f9267b = dataManager;
            this.f9266a = weakReference;
        }

        public void m13077a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9266a != null) {
                ajVar = (aj) this.f9266a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.66 */
    class AnonymousClass66 implements Response<TFSHasBookingResponse> {
        final /* synthetic */ WeakReference f9268a;
        final /* synthetic */ DataManager f9269b;

        AnonymousClass66(DataManager dataManager, WeakReference weakReference) {
            this.f9269b = dataManager;
            this.f9268a = weakReference;
        }

        public void m13078a(TFSHasBookingResponse tFSHasBookingResponse) {
            aj ajVar = null;
            if (this.f9268a != null) {
                ajVar = (aj) this.f9268a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(tFSHasBookingResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.67 */
    class AnonymousClass67 implements Response {
        final /* synthetic */ WeakReference f9270a;
        final /* synthetic */ DataManager f9271b;

        AnonymousClass67(DataManager dataManager, WeakReference weakReference) {
            this.f9271b = dataManager;
            this.f9270a = weakReference;
        }

        public void m13080a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9270a != null) {
                ajVar = (aj) this.f9270a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.68 */
    class AnonymousClass68 implements Response {
        final /* synthetic */ WeakReference f9272a;
        final /* synthetic */ DataManager f9273b;

        AnonymousClass68(DataManager dataManager, WeakReference weakReference) {
            this.f9273b = dataManager;
            this.f9272a = weakReference;
        }

        public void m13081a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9272a != null) {
                ajVar = (aj) this.f9272a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.69 */
    class AnonymousClass69 implements Response<ab> {
        final /* synthetic */ WeakReference f9274a;
        final /* synthetic */ DataManager f9275b;

        AnonymousClass69(DataManager dataManager, WeakReference weakReference) {
            this.f9275b = dataManager;
            this.f9274a = weakReference;
        }

        public void m13082a(ab abVar) {
            aj ajVar = null;
            if (this.f9274a != null) {
                ajVar = (aj) this.f9274a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(abVar);
            }
        }
    }

    /* renamed from: com.olacabs.customer.app.f.6 */
    class DataManager implements Response {
        final /* synthetic */ WeakReference f9276a;
        final /* synthetic */ DataManager f9277b;

        DataManager(DataManager dataManager, WeakReference weakReference) {
            this.f9277b = dataManager;
            this.f9276a = weakReference;
        }

        public void m13084a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9276a != null) {
                ajVar = (aj) this.f9276a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.70 */
    class AnonymousClass70 implements Response {
        final /* synthetic */ WeakReference f9278a;
        final /* synthetic */ DataManager f9279b;

        AnonymousClass70(DataManager dataManager, WeakReference weakReference) {
            this.f9279b = dataManager;
            this.f9278a = weakReference;
        }

        public void m13085a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9278a != null) {
                ajVar = (aj) this.f9278a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.71 */
    class AnonymousClass71 implements Response<ck> {
        final /* synthetic */ WeakReference f9280a;
        final /* synthetic */ DataManager f9281b;

        AnonymousClass71(DataManager dataManager, WeakReference weakReference) {
            this.f9281b = dataManager;
            this.f9280a = weakReference;
        }

        public void m13086a(ck ckVar) {
            aj ajVar = null;
            if (this.f9280a != null) {
                ajVar = (aj) this.f9280a.get();
            }
            if (ajVar == null) {
                return;
            }
            if (ckVar != null) {
                ajVar.onSuccess(ckVar);
            } else {
                ajVar.onFailure(new VolleyError("Error in push ack"));
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.72 */
    class AnonymousClass72 implements Response {
        final /* synthetic */ WeakReference f9282a;
        final /* synthetic */ DataManager f9283b;

        AnonymousClass72(DataManager dataManager, WeakReference weakReference) {
            this.f9283b = dataManager;
            this.f9282a = weakReference;
        }

        public void m13088a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9282a != null) {
                ajVar = (aj) this.f9282a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.73 */
    class AnonymousClass73 implements Response<UpdateECResponse> {
        final /* synthetic */ WeakReference f9284a;
        final /* synthetic */ DataManager f9285b;

        AnonymousClass73(DataManager dataManager, WeakReference weakReference) {
            this.f9285b = dataManager;
            this.f9284a = weakReference;
        }

        public void m13089a(UpdateECResponse updateECResponse) {
            aj ajVar = null;
            if (this.f9284a != null) {
                ajVar = (aj) this.f9284a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(updateECResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.74 */
    class AnonymousClass74 implements Response {
        final /* synthetic */ WeakReference f9286a;
        final /* synthetic */ DataManager f9287b;

        AnonymousClass74(DataManager dataManager, WeakReference weakReference) {
            this.f9287b = dataManager;
            this.f9286a = weakReference;
        }

        public void m13091a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9286a != null) {
                ajVar = (aj) this.f9286a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.75 */
    class AnonymousClass75 implements Response<am> {
        final /* synthetic */ WeakReference f9288a;
        final /* synthetic */ DataManager f9289b;

        AnonymousClass75(DataManager dataManager, WeakReference weakReference) {
            this.f9289b = dataManager;
            this.f9288a = weakReference;
        }

        public void m13092a(am amVar) {
            aj ajVar = null;
            if (this.f9288a != null) {
                ajVar = (aj) this.f9288a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(amVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.77 */
    class AnonymousClass77 implements Response {
        final /* synthetic */ WeakReference f9291a;
        final /* synthetic */ DataManager f9292b;

        AnonymousClass77(DataManager dataManager, WeakReference weakReference) {
            this.f9292b = dataManager;
            this.f9291a = weakReference;
        }

        public void m13095a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9291a != null) {
                ajVar = (aj) this.f9291a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.78 */
    class AnonymousClass78 implements Response<ci> {
        final /* synthetic */ Map f9293a;
        final /* synthetic */ WeakReference f9294b;
        final /* synthetic */ DataManager f9295c;

        AnonymousClass78(DataManager dataManager, Map map, WeakReference weakReference) {
            this.f9295c = dataManager;
            this.f9293a = map;
            this.f9294b = weakReference;
        }

        public void m13096a(ci ciVar) {
            if (ciVar.getStatus().equalsIgnoreCase("SUCCESS")) {
                ciVar.setOrigTimeStamp(System.currentTimeMillis());
                ciVar.setOrigParams(this.f9293a);
                this.f9295c.f9365o.addToCache("v3/user/getProfile", ciVar);
            }
            aj ajVar = null;
            if (this.f9294b != null) {
                ajVar = (aj) this.f9294b.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(ciVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.79 */
    class AnonymousClass79 implements Response {
        final /* synthetic */ WeakReference f9296a;
        final /* synthetic */ DataManager f9297b;

        AnonymousClass79(DataManager dataManager, WeakReference weakReference) {
            this.f9297b = dataManager;
            this.f9296a = weakReference;
        }

        public void m13098a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9296a != null) {
                ajVar = (aj) this.f9296a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* renamed from: com.olacabs.customer.app.f.7 */
    class DataManager implements Response<dh> {
        final /* synthetic */ WeakReference f9298a;
        final /* synthetic */ DataManager f9299b;

        DataManager(DataManager dataManager, WeakReference weakReference) {
            this.f9299b = dataManager;
            this.f9298a = weakReference;
        }

        public void m13099a(dh dhVar) {
            aj ajVar = null;
            if (this.f9298a != null) {
                ajVar = (aj) this.f9298a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(dhVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.80 */
    class AnonymousClass80 implements Runnable {
        final /* synthetic */ Map f9302a;
        final /* synthetic */ WeakReference f9303b;
        final /* synthetic */ String f9304c;
        final /* synthetic */ OlaApp f9305d;
        final /* synthetic */ Response f9306e;
        final /* synthetic */ Response f9307f;
        final /* synthetic */ DataManager f9308g;

        /* renamed from: com.olacabs.customer.app.f.80.1 */
        class DataManager implements Runnable {
            final /* synthetic */ bv f9300a;
            final /* synthetic */ AnonymousClass80 f9301b;

            DataManager(AnonymousClass80 anonymousClass80, bv bvVar) {
                this.f9301b = anonymousClass80;
                this.f9300a = bvVar;
            }

            public void run() {
                aj ajVar = null;
                if (this.f9301b.f9303b != null) {
                    ajVar = (aj) this.f9301b.f9303b.get();
                }
                if (ajVar != null && this.f9301b.f9308g.f9366p.contains(this.f9301b.f9304c)) {
                    ajVar.onSuccess(this.f9300a);
                }
            }
        }

        AnonymousClass80(DataManager dataManager, Map map, WeakReference weakReference, String str, OlaApp olaApp, Response response, Response response2) {
            this.f9308g = dataManager;
            this.f9302a = map;
            this.f9303b = weakReference;
            this.f9304c = str;
            this.f9305d = olaApp;
            this.f9306e = response;
            this.f9307f = response2;
        }

        public void run() {
            bv readFromCache = this.f9308g.f9365o.readFromCache("v3/user/getProfile", ci.class, this.f9302a);
            if (readFromCache == null || !readFromCache.isValid(this.f9302a)) {
                OLog.m13311a("Did not find cache data for v3/user/getProfile", new Object[0]);
            } else {
                OLog.m13311a("Found cache data for v3/user/getProfile", new Object[0]);
                new Handler(Looper.getMainLooper()).post(new com.olacabs.customer.app.DataManager.80.DataManager(this, readFromCache));
            }
            Request instance = bw.getInstance(this.f9305d, 0, "https://apps.olacabs.com/v3/user/getProfile", Request.Request.IMMEDIATE, this.f9302a, this.f9306e, this.f9307f, ci.class);
            instance.setRetryPolicy(new dm());
            this.f9308g.f9366p.add(this.f9304c);
            instance.setTag(this.f9304c);
            this.f9308g.m13168a(instance);
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.81 */
    class AnonymousClass81 implements Response<dp> {
        final /* synthetic */ WeakReference f9309a;
        final /* synthetic */ DataManager f9310b;

        AnonymousClass81(DataManager dataManager, WeakReference weakReference) {
            this.f9310b = dataManager;
            this.f9309a = weakReference;
        }

        public void m13101a(dp dpVar) {
            aj ajVar = null;
            if (this.f9309a != null) {
                ajVar = (aj) this.f9309a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(dpVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.82 */
    class AnonymousClass82 implements Response {
        final /* synthetic */ WeakReference f9311a;
        final /* synthetic */ DataManager f9312b;

        AnonymousClass82(DataManager dataManager, WeakReference weakReference) {
            this.f9312b = dataManager;
            this.f9311a = weakReference;
        }

        public void m13103a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9311a != null) {
                ajVar = (aj) this.f9311a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.83 */
    class AnonymousClass83 implements Response<df> {
        final /* synthetic */ WeakReference f9313a;
        final /* synthetic */ DataManager f9314b;

        AnonymousClass83(DataManager dataManager, WeakReference weakReference) {
            this.f9314b = dataManager;
            this.f9313a = weakReference;
        }

        public void m13104a(df dfVar) {
            aj ajVar = null;
            if (this.f9313a != null) {
                ajVar = (aj) this.f9313a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(dfVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.84 */
    class AnonymousClass84 implements Response {
        final /* synthetic */ WeakReference f9315a;
        final /* synthetic */ DataManager f9316b;

        AnonymousClass84(DataManager dataManager, WeakReference weakReference) {
            this.f9316b = dataManager;
            this.f9315a = weakReference;
        }

        public void m13106a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9315a != null) {
                ajVar = (aj) this.f9315a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.85 */
    class AnonymousClass85 implements Response<de> {
        final /* synthetic */ WeakReference f9317a;
        final /* synthetic */ DataManager f9318b;

        AnonymousClass85(DataManager dataManager, WeakReference weakReference) {
            this.f9318b = dataManager;
            this.f9317a = weakReference;
        }

        public void m13107a(de deVar) {
            aj ajVar = null;
            if (this.f9317a != null) {
                ajVar = (aj) this.f9317a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(deVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.86 */
    class AnonymousClass86 implements Response {
        final /* synthetic */ WeakReference f9319a;
        final /* synthetic */ DataManager f9320b;

        AnonymousClass86(DataManager dataManager, WeakReference weakReference) {
            this.f9320b = dataManager;
            this.f9319a = weakReference;
        }

        public void m13109a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9319a != null) {
                ajVar = (aj) this.f9319a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.87 */
    class AnonymousClass87 implements Response<AnalyticsInstallResponse> {
        final /* synthetic */ WeakReference f9321a;
        final /* synthetic */ DataManager f9322b;

        AnonymousClass87(DataManager dataManager, WeakReference weakReference) {
            this.f9322b = dataManager;
            this.f9321a = weakReference;
        }

        public void m13110a(AnalyticsInstallResponse analyticsInstallResponse) {
            aj ajVar = null;
            if (this.f9321a != null) {
                ajVar = (aj) this.f9321a.get();
            }
            if (analyticsInstallResponse.getStatus().equals("SUCCESS")) {
                OLog.m13315c("/v3/analytics/install returned success", new Object[0]);
                if (ajVar != null) {
                    ajVar.onSuccess(analyticsInstallResponse);
                    return;
                }
                return;
            }
            OLog.m13315c("/v3/analytics/install returned failure", new Object[0]);
            if (ajVar != null) {
                ajVar.onSuccess(analyticsInstallResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.88 */
    class AnonymousClass88 implements Response<dc> {
        final /* synthetic */ WeakReference f9323a;
        final /* synthetic */ DataManager f9324b;

        AnonymousClass88(DataManager dataManager, WeakReference weakReference) {
            this.f9324b = dataManager;
            this.f9323a = weakReference;
        }

        public void m13112a(dc dcVar) {
            OLog.m13311a("Signout request status - " + dcVar.getStatus(), new Object[0]);
            aj ajVar = null;
            if (this.f9323a != null) {
                ajVar = (aj) this.f9323a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(dcVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.89 */
    class AnonymousClass89 implements Response {
        final /* synthetic */ WeakReference f9325a;
        final /* synthetic */ DataManager f9326b;

        AnonymousClass89(DataManager dataManager, WeakReference weakReference) {
            this.f9326b = dataManager;
            this.f9325a = weakReference;
        }

        public void m13114a(VolleyError volleyError) {
            OLog.m13311a("Signout request failed", volleyError);
            aj ajVar = null;
            if (this.f9325a != null) {
                ajVar = (aj) this.f9325a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* renamed from: com.olacabs.customer.app.f.8 */
    class DataManager implements Response {
        final /* synthetic */ WeakReference f9327a;
        final /* synthetic */ DataManager f9328b;

        DataManager(DataManager dataManager, WeakReference weakReference) {
            this.f9328b = dataManager;
            this.f9327a = weakReference;
        }

        public void m13115a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9327a != null) {
                ajVar = (aj) this.f9327a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.90 */
    class AnonymousClass90 implements Response<dq> {
        final /* synthetic */ WeakReference f9329a;
        final /* synthetic */ DataManager f9330b;

        AnonymousClass90(DataManager dataManager, WeakReference weakReference) {
            this.f9330b = dataManager;
            this.f9329a = weakReference;
        }

        public void m13116a(dq dqVar) {
            OLog.m13311a("User call back for verification status - " + dqVar.getStatus(), new Object[0]);
            aj ajVar = null;
            if (this.f9329a != null) {
                ajVar = (aj) this.f9329a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(dqVar);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.91 */
    class AnonymousClass91 implements Response {
        final /* synthetic */ WeakReference f9331a;
        final /* synthetic */ DataManager f9332b;

        AnonymousClass91(DataManager dataManager, WeakReference weakReference) {
            this.f9332b = dataManager;
            this.f9331a = weakReference;
        }

        public void m13118a(VolleyError volleyError) {
            OLog.m13311a("User call back for verification request failed", volleyError);
            aj ajVar = null;
            if (this.f9331a != null) {
                ajVar = (aj) this.f9331a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.92 */
    class AnonymousClass92 implements Response<dv> {
        final /* synthetic */ WeakReference f9333a;
        final /* synthetic */ DataManager f9334b;

        AnonymousClass92(DataManager dataManager, WeakReference weakReference) {
            this.f9334b = dataManager;
            this.f9333a = weakReference;
        }

        public void m13119a(dv dvVar) {
            OLog.m13311a("User call back for verification status - " + dvVar.getStatus(), new Object[0]);
            aj ajVar = null;
            if (this.f9333a != null) {
                ajVar = (aj) this.f9333a.get();
            }
            if (ajVar == null) {
                return;
            }
            if (dvVar != null) {
                ajVar.onSuccess(dvVar);
            } else {
                ajVar.onFailure(new VolleyError("User login failed or Invalid auth token"));
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.93 */
    class AnonymousClass93 implements Response {
        final /* synthetic */ WeakReference f9335a;
        final /* synthetic */ DataManager f9336b;

        AnonymousClass93(DataManager dataManager, WeakReference weakReference) {
            this.f9336b = dataManager;
            this.f9335a = weakReference;
        }

        public void m13121a(VolleyError volleyError) {
            OLog.m13311a("User verification failed", volleyError);
            aj ajVar = null;
            if (this.f9335a != null) {
                ajVar = (aj) this.f9335a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.94 */
    class AnonymousClass94 implements Response<AccountBalanceResponse> {
        final /* synthetic */ WeakReference f9337a;
        final /* synthetic */ DataManager f9338b;

        AnonymousClass94(DataManager dataManager, WeakReference weakReference) {
            this.f9338b = dataManager;
            this.f9337a = weakReference;
        }

        public void m13122a(AccountBalanceResponse accountBalanceResponse) {
            OLog.m13311a("request status - " + accountBalanceResponse.getStatus(), new Object[0]);
            aj ajVar = null;
            if (this.f9337a != null) {
                ajVar = (aj) this.f9337a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(accountBalanceResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.95 */
    class AnonymousClass95 implements Response {
        final /* synthetic */ WeakReference f9339a;
        final /* synthetic */ DataManager f9340b;

        AnonymousClass95(DataManager dataManager, WeakReference weakReference) {
            this.f9340b = dataManager;
            this.f9339a = weakReference;
        }

        public void m13124a(VolleyError volleyError) {
            OLog.m13311a("request failed", volleyError);
            aj ajVar = null;
            if (this.f9339a != null) {
                ajVar = (aj) this.f9339a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.96 */
    class AnonymousClass96 implements Response<CabInfoRideSummaryResponse> {
        final /* synthetic */ WeakReference f9341a;
        final /* synthetic */ DataManager f9342b;

        AnonymousClass96(DataManager dataManager, WeakReference weakReference) {
            this.f9342b = dataManager;
            this.f9341a = weakReference;
        }

        public void m13125a(CabInfoRideSummaryResponse cabInfoRideSummaryResponse) {
            aj ajVar = null;
            if (this.f9341a != null) {
                ajVar = (aj) this.f9341a.get();
            }
            if (ajVar == null) {
                return;
            }
            if (cabInfoRideSummaryResponse == null || !cabInfoRideSummaryResponse.isValid()) {
                ajVar.onFailure(this.f9342b.m13134a((int) R.string.error_generic_ofd_title, (int) R.string.generic_failure_desc));
            } else {
                ajVar.onSuccess(cabInfoRideSummaryResponse);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.97 */
    class AnonymousClass97 implements Response {
        final /* synthetic */ WeakReference f9343a;
        final /* synthetic */ DataManager f9344b;

        AnonymousClass97(DataManager dataManager, WeakReference weakReference) {
            this.f9344b = dataManager;
            this.f9343a = weakReference;
        }

        public void m13127a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9343a != null) {
                ajVar = (aj) this.f9343a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.98 */
    class AnonymousClass98 implements Response {
        final /* synthetic */ WeakReference f9345a;
        final /* synthetic */ DataManager f9346b;

        AnonymousClass98(DataManager dataManager, WeakReference weakReference) {
            this.f9346b = dataManager;
            this.f9345a = weakReference;
        }

        public void m13128a(VolleyError volleyError) {
            aj ajVar = null;
            if (this.f9345a != null) {
                ajVar = (aj) this.f9345a.get();
            }
            if (ajVar != null) {
                ajVar.onFailure(volleyError);
            }
        }
    }

    /* compiled from: DataManager */
    /* renamed from: com.olacabs.customer.app.f.99 */
    class AnonymousClass99 implements Response<az> {
        final /* synthetic */ WeakReference f9347a;
        final /* synthetic */ DataManager f9348b;

        AnonymousClass99(DataManager dataManager, WeakReference weakReference) {
            this.f9348b = dataManager;
            this.f9347a = weakReference;
        }

        public void m13129a(az azVar) {
            aj ajVar = null;
            if (this.f9347a != null) {
                ajVar = (aj) this.f9347a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(azVar);
            }
        }
    }

    /* renamed from: com.olacabs.customer.app.f.9 */
    class DataManager implements Response<cy> {
        final /* synthetic */ WeakReference f9349a;
        final /* synthetic */ DataManager f9350b;

        DataManager(DataManager dataManager, WeakReference weakReference) {
            this.f9350b = dataManager;
            this.f9349a = weakReference;
        }

        public void m13131a(cy cyVar) {
            aj ajVar = null;
            if (this.f9349a != null) {
                ajVar = (aj) this.f9349a.get();
            }
            if (ajVar != null) {
                ajVar.onSuccess(cyVar);
            }
        }
    }

    static {
        f9352b = DataManager.class.getSimpleName();
        f9351a = new Object();
    }

    private DataManager(Context context) {
        this.f9354d = new Object();
        this.f9356f = false;
        this.f9361k = new DeepLinkInfo().build();
        this.f9368r = new Handler(BackgroundLooper.m14896a());
        this.f9355e = context;
    }

    public static DataManager m13137a(Context context) {
        if (f9353c == null) {
            OLog.m13311a("Creating data manager instance", new Object[0]);
            f9353c = new DataManager(context);
        }
        return f9353c;
    }

    public void m13163a() {
        this.f9368r.post(new DataManager(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m13160p() {
        /*
        r2 = this;
        r1 = r2.f9354d;
        monitor-enter(r1);
    L_0x0003:
        r0 = r2.f9356f;	 Catch:{ all -> 0x0012 }
        if (r0 != 0) goto L_0x0015;
    L_0x0007:
        r0 = r2.f9354d;	 Catch:{ InterruptedException -> 0x000d }
        r0.wait();	 Catch:{ InterruptedException -> 0x000d }
        goto L_0x0003;
    L_0x000d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0012 }
        goto L_0x0003;
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0012 }
        throw r0;
    L_0x0015:
        monitor-exit(r1);	 Catch:{ all -> 0x0012 }
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.olacabs.customer.app.f.p():void");
    }

    public AppInfo m13197b() {
        AppInfo appInfo;
        m13160p();
        synchronized (this.f9354d) {
            appInfo = this.f9357g;
        }
        return appInfo;
    }

    public dt m13209c() {
        dt dtVar;
        m13160p();
        synchronized (this.f9354d) {
            dtVar = this.f9358h;
        }
        return dtVar;
    }

    public da m13218d() {
        da daVar;
        m13160p();
        synchronized (this.f9354d) {
            daVar = this.f9359i;
        }
        return daVar;
    }

    public ao m13224e() {
        ao aoVar;
        m13160p();
        synchronized (this.f9354d) {
            aoVar = this.f9360j;
        }
        return aoVar;
    }

    public void m13230f() {
        this.f9364n.m581b();
        this.f9355e.unregisterReceiver(this.f9367q);
        C0532g.f2651b.m3920a(this.f9363m, this);
        this.f9363m.m3230c();
    }

    public void m13234g() {
        this.f9359i = null;
        this.f9356f = false;
        this.f9358h = null;
        this.f9365o.purgeAll();
        this.f9368r.removeCallbacksAndMessages(null);
    }

    public void m13169a(String str) {
        m13160p();
        this.f9366p.remove(str);
        this.f9364n.m580a((Object) str);
    }

    public void m13238h() {
        this.f9364n.m579a(new RequestQueue.RequestQueue() {
            final /* synthetic */ DataManager f9290a;

            {
                this.f9290a = r1;
            }

            public boolean m13094a(Request<?> request) {
                return true;
            }
        });
    }

    public <T> void m13168a(Request<T> request) {
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 0, br.DEFAULT_BACKOFF_MULT));
        m13160p();
        this.f9364n.m577a((Request) request);
    }

    public void m13167a(Bundle bundle) {
        if (this.f9363m.m3231d()) {
            Location a = C0532g.f2651b.m3918a(this.f9363m);
            if (!(a == null || this.f9358h == null)) {
                OLog.m13311a("Read last known location -" + a.toString(), new Object[0]);
                this.f9358h.setUserLocation(a);
                EventBus.m3a().m18c(new bm(a));
            }
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.m4325a(5000);
            locationRequest.m4327b(2500);
            locationRequest.m4324a(105);
            locationRequest.m4323a(50.0f);
            C0532g.f2651b.m3919a(this.f9363m, locationRequest, this);
        }
    }

    public C0226d m13242i() {
        return this.f9363m;
    }

    public void m13164a(int i) {
        OLog.m13311a("Connection suspended", new Object[0]);
        this.f9363m.m3227b();
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        OLog.m13317d("Failed to connect to GoogleApiClient object", new Object[0]);
    }

    public void m13165a(Location location) {
        if (location != null && this.f9358h != null) {
            OLog.m13311a("Obtained new location - " + location.toString(), new Object[0]);
            this.f9358h.setUserLocation(location);
            EventBus.m3a().m18c(new bm(location));
        }
    }

    public void m13246j() {
        this.f9365o.purgeAll();
    }

    public void m13175a(WeakReference<aj> weakReference, String str) {
        m13160p();
        OLog.m13311a("notifyServerOfInstall", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        CharSequence installationId = this.f9359i.getInstallationId();
        CharSequence deviceId = this.f9360j.getDeviceId();
        String str2 = AppInfo.sVersionName;
        String osType = this.f9360j.getOsType();
        String osVersion = this.f9360j.getOsVersion();
        String phoneModel = this.f9360j.getPhoneModel();
        boolean isAnUpdate = this.f9359i.isAnUpdate();
        String userLoginEmail = this.f9358h.getUserLoginEmail();
        Map hashMap = new HashMap();
        if (!TextUtils.isEmpty(installationId)) {
            hashMap.put(da.APP_INSTALLATION_ID_KEY, installationId);
        }
        if (!TextUtils.isEmpty(deviceId)) {
            hashMap.put(ao.DEVICE_ID_KEY, deviceId);
        }
        hashMap.put(AppInfo.APP_VERSION_KEY, str2);
        hashMap.put(ao.OS_TYPE_KEY, osType);
        hashMap.put(ao.OS_VERSION_KEY, osVersion);
        hashMap.put(ao.PHONE_MODEL_KEY, phoneModel);
        hashMap.put(da.IS_UPDATE_KEY, String.valueOf(isAnUpdate));
        hashMap.put(Constants.EMAIL, userLoginEmail);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/analytics/install", Request.Request.LOW, hashMap, new AnonymousClass87(this, weakReference), new AnonymousClass98(this, weakReference), AnalyticsInstallResponse.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13249k() {
        OLog.m13311a("reportGcmRegIdToBackend", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.USER_ID, this.f9358h.getUserId());
            jSONObject.put(da.GCM_REG_ID_KEY, this.f9359i.getGcmRegId());
            jSONObject.put(ao.DEVICE_ID_KEY, this.f9360j.getDeviceId());
            jSONObject.put(AppInfo.APP_VERSION_KEY, AppInfo.sVersionName);
        } catch (Throwable e) {
            OLog.m13310a("Failed to create params - ", e);
        }
        Request byVar = new by(olaApp, 2, "https://apps.olacabs.com/v3/user/update_registration?", jSONObject, Request.Request.LOW, new Response<JSONObject>() {
            final /* synthetic */ DataManager f9034a;

            {
                this.f9034a = r1;
            }

            public void m12910a(JSONObject jSONObject) {
                String str = "FAILURE";
                try {
                    str = jSONObject.getString(NotificationCompatApi21.CATEGORY_STATUS);
                } catch (JSONException e) {
                }
                if (str.equals("SUCCESS")) {
                    OLog.m13311a("v3/user/update_registration success", new Object[0]);
                    this.f9034a.f9359i.setGcmRegWithBackend(true);
                    return;
                }
                OLog.m13311a("v3/user/update_registration failed", new Object[0]);
                this.f9034a.f9359i.setGcmRegWithBackend(false);
            }
        }, new Response() {
            final /* synthetic */ DataManager f9060a;

            {
                this.f9060a = r1;
            }

            public void m12929a(VolleyError volleyError) {
                OLog.m13311a("v3/user/update_registration returned an error", volleyError);
                this.f9060a.f9359i.setGcmRegWithBackend(false);
            }
        });
        byVar.setTag(f9352b);
        m13168a(byVar);
    }

    public void m13181a(WeakReference<aj> weakReference, String str, String str2, String str3) {
        OLog.m13311a("performLogin", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.EMAIL, str);
        hashMap.put(dt.USER_PASSWORD_KEY, EncryptionScheme.m14898a(str2));
        String str4 = Constants.DEVICE_MODEL;
        ao aoVar = this.f9360j;
        hashMap.put(str4, ao.DEVICE_MODEL);
        hashMap.put(ao.DEVICE_ID_KEY, this.f9360j.getDeviceId());
        hashMap.put(da.APP_INSTALLATION_ID_KEY, this.f9359i.getInstallationId());
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/user/login", Request.Request.IMMEDIATE, hashMap, new AnonymousClass131(this, weakReference), new AnonymousClass142(this, weakReference), bp.class);
        instance.setRetryPolicy(new dm());
        instance.setTag(str3);
        m13168a(instance);
    }

    public void m13180a(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("resetPassword", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.EMAIL, str);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/user/resetPassword", Request.Request.IMMEDIATE, hashMap, new AnonymousClass151(this, weakReference), new DataManager(this, weakReference), ca.class);
        instance.setTag(str2);
        m13168a(instance);
    }

    public void m13176a(WeakReference<aj> weakReference, String str, int i, int i2, Config config, String str2) {
        Request imageRequest = new ImageRequest(str, new AnonymousClass13(this, weakReference), i2, i, config, new AnonymousClass24(this, weakReference));
        imageRequest.setTag(str2);
        m13168a(imageRequest);
    }

    public void m13178a(WeakReference<aj> weakReference, String str, Config config, int i, int i2, String str2, String str3, String str4, String str5, String str6, String str7) {
        Map hashMap = new HashMap();
        String str8 = str2 + "," + str3;
        hashMap.put("center", str8);
        hashMap.put("zoom", str4);
        hashMap.put("size", str5);
        hashMap.put("sensor", str6);
        hashMap.put("markers", "size:mid|color:red|" + str8);
        Response anonymousClass35 = new AnonymousClass35(this, weakReference);
        Response anonymousClass46 = new AnonymousClass46(this, weakReference);
        Request imageRequest = new ImageRequest(bw.getInstance((OlaApp) this.f9355e.getApplicationContext(), 0, str, Request.Request.IMMEDIATE, hashMap, anonymousClass35, anonymousClass46, cm.class).getOriginUrl(), anonymousClass35, i, i2, config, anonymousClass46);
        imageRequest.setTag(str7);
        m13168a(imageRequest);
    }

    public void m13202b(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("resendVerification", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/user/resend_sos_verification", Request.Request.IMMEDIATE, hashMap, new AnonymousClass57(this, weakReference), new AnonymousClass68(this, weakReference), cm.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13188a(WeakReference<aj> weakReference, String str, String str2, String str3, boolean z, String str4) {
        OLog.m13311a("submitECDetails", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.BUNDLE_NAME, str);
        hashMap.put(Constants.PHONE, str2);
        hashMap.put(Constants.EMAIL, str3);
        hashMap.put(dt.USER_EC_AUTO_SHARE_KEY, String.valueOf(z));
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/user/update_emergency_info", Request.Request.NORMAL, hashMap, new AnonymousClass73(this, weakReference), new AnonymousClass74(this, weakReference), UpdateECResponse.class);
        instance.setTag(str4);
        m13168a(instance);
    }

    public void m13213c(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("deleteECDetails", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 2, "https://apps.olacabs.com/v3/user/remove_ec", Request.Request.NORMAL, hashMap, new AnonymousClass75(this, weakReference), new AnonymousClass77(this, weakReference), am.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13220d(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("getProfileDetails", new Object[0]);
        String str2 = "v3/user/getProfile";
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(ao.DEVICE_ID_KEY, this.f9360j.getDeviceId());
        hashMap.put(da.APP_INSTALLATION_ID_KEY, this.f9359i.getInstallationId());
        this.f9368r.post(new AnonymousClass80(this, hashMap, weakReference, str, olaApp, new AnonymousClass78(this, hashMap, weakReference), new AnonymousClass79(this, weakReference)));
    }

    public void m13204b(WeakReference<aj> weakReference, String str, String str2, String str3) {
        OLog.m13311a("updateProfileDetails", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.BUNDLE_NAME, str);
        hashMap.put(dt.USER_PHONE_KEY, str2);
        hashMap.put("source", "app");
        hashMap.put(dt.VERIFY_USER_ON_SIGNUP_KEY, String.valueOf(true));
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/user/updateProfile", Request.Request.IMMEDIATE, hashMap, new AnonymousClass81(this, weakReference), new AnonymousClass82(this, weakReference), dp.class);
        instance.setTag(str3);
        m13168a(instance);
    }

    public void m13226e(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("sendSosRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        Location userLocation = this.f9358h.getUserLocation();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        if (userLocation != null) {
            hashMap.put(Constants.LAT, String.valueOf(userLocation.getLatitude()));
            hashMap.put(Constants.LNG, String.valueOf(userLocation.getLongitude()));
            hashMap.put(dt.USER_LOC_ACCURACY_KEY, String.valueOf(userLocation.getAccuracy()));
            hashMap.put(dt.USER_LOC_FIX_TIME_KEY, String.valueOf(userLocation.getTime()));
        }
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/user/send_sos_signal", Request.Request.IMMEDIATE, hashMap, new AnonymousClass83(this, weakReference), new AnonymousClass84(this, weakReference), df.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13186a(WeakReference<aj> weakReference, String str, String str2, String str3, String str4, String str5, String str6) {
        OLog.m13311a("performSignUp", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Location userLocation = this.f9358h.getUserLocation();
        Map hashMap = new HashMap();
        hashMap.put(Constants.EMAIL, str);
        hashMap.put(dt.USER_PASSWORD_KEY, str2);
        hashMap.put(Constants.BUNDLE_NAME, str3);
        hashMap.put(dt.USER_PHONE_KEY, str4);
        hashMap.put(Constants.PREF_REFERRAL_CODE, str5);
        hashMap.put(ao.DEVICE_ID_KEY, this.f9360j.getDeviceId());
        String str7 = Constants.DEVICE_MODEL;
        ao aoVar = this.f9360j;
        hashMap.put(str7, ao.DEVICE_MODEL);
        hashMap.put(da.APP_INSTALLATION_ID_KEY, this.f9359i.getInstallationId());
        if (userLocation != null) {
            hashMap.put(Constants.LAT, String.valueOf(userLocation.getLatitude()));
            hashMap.put(Constants.LNG, String.valueOf(userLocation.getLongitude()));
        }
        hashMap.put(dt.VERIFY_USER_ON_SIGNUP_KEY, String.valueOf(true));
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/user/signup", Request.Request.IMMEDIATE, hashMap, new AnonymousClass85(this, weakReference), new AnonymousClass86(this, weakReference), de.class);
        instance.setTag(str6);
        instance.setRetryPolicy(new br());
        m13168a(instance);
    }

    public void m13231f(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("performSignOut", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        CharSequence deviceId = m13224e().getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            hashMap.put(ao.DEVICE_ID_KEY, deviceId);
        }
        deviceId = m13218d().getGcmRegId();
        if (!TextUtils.isEmpty(deviceId)) {
            hashMap.put(da.GCM_REG_ID_KEY, deviceId);
        }
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/user/signout", Request.Request.LOW, hashMap, new AnonymousClass88(this, weakReference), new AnonymousClass89(this, weakReference), dc.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13185a(WeakReference<aj> weakReference, String str, String str2, String str3, String str4, String str5) {
        OLog.m13311a("requestUserCallBack for verification", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.BUNDLE_TYPE, str);
        hashMap.put("source", "app");
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("verification_id", str2);
        }
        hashMap.put(Constants.BUNDLE_NAME, str3);
        hashMap.put(dt.USER_PHONE_KEY, str4);
        if (TextUtils.isEmpty(m13218d().getSignUpAttemptDetails().mEnteredEmailId)) {
            hashMap.put(Constants.EMAIL, m13209c().getUserLoginEmail());
        } else {
            hashMap.put(Constants.EMAIL, m13218d().getSignUpAttemptDetails().mEnteredEmailId);
        }
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/user/call_user", Request.Request.IMMEDIATE, hashMap, new AnonymousClass90(this, weakReference), new AnonymousClass91(this, weakReference), dq.class);
        instance.setTag(str5);
        m13168a(instance);
    }

    public void m13207b(WeakReference<aj> weakReference, String str, String str2, String str3, String str4, String str5, String str6) {
        OLog.m13311a("verifyUser", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("verification_id", str2);
        }
        hashMap.put(Constants.BUNDLE_TYPE, str);
        hashMap.put("source", "app");
        hashMap.put("code", str3);
        String str7 = Constants.DEVICE_MODEL;
        ao aoVar = this.f9360j;
        hashMap.put(str7, ao.DEVICE_MODEL);
        if (str.equals("signup")) {
            if (!TextUtils.isEmpty(m13218d().getSignUpAttemptDetails().mEnteredEmailId)) {
                hashMap.put(Constants.EMAIL, m13218d().getSignUpAttemptDetails().mEnteredEmailId);
            }
            hashMap.put(Constants.BUNDLE_NAME, m13218d().getSignUpAttemptDetails().mName);
            hashMap.put(dt.USER_PHONE_KEY, m13218d().getSignUpAttemptDetails().mPhoneNumber);
            hashMap.put(dt.USER_PASSWORD_KEY, m13218d().getSignUpAttemptDetails().mPassword);
            hashMap.put(da.APP_INSTALLATION_ID_KEY, this.f9359i.getInstallationId());
            hashMap.put(ao.DEVICE_ID_KEY, this.f9360j.getDeviceId());
            Location userLocation = this.f9358h.getUserLocation();
            if (userLocation != null) {
                hashMap.put(Constants.LAT, String.valueOf(userLocation.getLatitude()));
                hashMap.put(Constants.LNG, String.valueOf(userLocation.getLongitude()));
            }
            hashMap.put(Constants.PREF_REFERRAL_CODE, m13218d().getSignUpAttemptDetails().mReferrerCode);
        } else if (str.equals("update")) {
            hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
            hashMap.put(Constants.BUNDLE_NAME, str4);
            hashMap.put(dt.USER_PHONE_KEY, str5);
        }
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/user/verify", Request.Request.IMMEDIATE, hashMap, new AnonymousClass92(this, weakReference), new AnonymousClass93(this, weakReference), dv.class);
        instance.setTag(str6);
        m13168a(instance);
    }

    public void m13203b(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("getAccountBalance", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        da d = m13218d();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("info", str);
        hashMap.put("offer_id", String.valueOf(d.getFirstTimeOfferId()));
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/account/balance", Request.Request.IMMEDIATE, hashMap, new AnonymousClass94(this, weakReference), new AnonymousClass95(this, weakReference), AccountBalanceResponse.class);
        instance.setTag(str2);
        m13168a(instance);
    }

    public void m13235g(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("Get CabInfo", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(ao.NETWORK_TYPE_KEY, Utils.m14912b(this.f9355e) ? "fast" : "slow");
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/cab/info", Request.Request.IMMEDIATE, hashMap, new AnonymousClass96(this, weakReference), new AnonymousClass97(this, weakReference), CabInfoRideSummaryResponse.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13217c(WeakReference<aj> weakReference, String str, String str2, String str3, String str4, String str5, String str6) {
        OLog.m13311a("postRideFeedBack", new Object[0]);
        String str7 = ("kp".equalsIgnoreCase(str) || "auto".equalsIgnoreCase(str) || "cool_cab".equalsIgnoreCase(str)) ? "v3/booking/kp/feedback" : "delivery".equalsIgnoreCase(str) ? "v3/booking/delivery/feedback" : "v3/booking/feedback";
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.ARG_BOOKING_ID, str2);
        hashMap.put("action", "submit");
        hashMap.put("rating", str3);
        hashMap.put("comments", str4);
        hashMap.put("more", str5);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/" + str7, Request.Request.NORMAL, hashMap, new AnonymousClass99(this, weakReference), new AnonymousClass100(this, weakReference), az.class);
        instance.setTag(str6);
        m13168a(instance);
    }

    public void m13187a(WeakReference<aj> weakReference, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        String str10;
        OLog.m13311a("getRideEstimate", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("pickup_lat", str);
        hashMap.put("pickup_lng", str2);
        hashMap.put("drop_lat", str3);
        hashMap.put("drop_lng", str4);
        hashMap.put(Constants.ARG_CAR_CATEGORY_ID, str5);
        hashMap.put(Constants.COUPON_CODE, str6);
        hashMap.put("pickup_mode", str7);
        hashMap.put("pickup_time", str8);
        Response anonymousClass101 = new AnonymousClass101(this, weakReference);
        Response anonymousClass102 = new AnonymousClass102(this, weakReference);
        if (this.f9359i.isB()) {
            str10 = "v3/booking/estimate_with_surcharge";
        } else {
            str10 = "v3/booking/estimate";
        }
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/" + str10, Request.Request.IMMEDIATE, hashMap, anonymousClass101, anonymousClass102, cs.class);
        instance.setTag(str9);
        m13168a(instance);
    }

    public void m13239h(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("getRidesData", new Object[0]);
        String str2 = "v3/rides";
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put("enable_new_state", "true");
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        this.f9368r.post(new AnonymousClass105(this, hashMap, weakReference, str, olaApp, new AnonymousClass103(this, hashMap, weakReference), new AnonymousClass104(this, weakReference)));
    }

    public void m13243i(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("Untrack ride", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/user/ride/untrack?", Request.Request.IMMEDIATE, hashMap, new AnonymousClass106(this, weakReference), new AnonymousClass107(this, weakReference), dn.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13170a(WeakReference<aj> weakReference) {
        OLog.m13311a("Fetching app configuration from server", new Object[0]);
        String str = "v3/config/new";
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Location userLocation = olaApp.m12878a().m13209c().getUserLocation();
        if (userLocation != null) {
            hashMap.put(Constants.LAT, String.valueOf(userLocation.getLatitude()));
            hashMap.put(Constants.LNG, String.valueOf(userLocation.getLongitude()));
        }
        hashMap.put(ao.DEVICE_RESOLUTION_KEY, olaApp.m12878a().m13224e().getScreenSize());
        m13168a(bw.getInstance(olaApp, 4, "https://apps.olacabs.com/v3/config/new", Request.Request.NORMAL, hashMap, new AnonymousClass108(this, weakReference), new AnonymousClass110(this, weakReference), bj.class));
    }

    public void m13198b(WeakReference<aj> weakReference) {
        OLog.m13311a("Fetching app configuration from server", new Object[0]);
        String str = "v3/config/new";
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, m13209c().getUserId());
        Location userLocation = olaApp.m12878a().m13209c().getUserLocation();
        if (userLocation != null) {
            hashMap.put(Constants.LAT, String.valueOf(userLocation.getLatitude()));
            hashMap.put(Constants.LNG, String.valueOf(userLocation.getLongitude()));
        }
        hashMap.put(ao.DEVICE_RESOLUTION_KEY, olaApp.m12878a().m13224e().getScreenSize());
        m13168a(bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/config/new", Request.Request.NORMAL, hashMap, new AnonymousClass111(this, hashMap, weakReference), new AnonymousClass112(this, weakReference), ad.class));
    }

    public void m13196a(WeakReference<aj> weakReference, boolean z, String str, String str2, String str3, String str4) {
        OLog.m13311a("rideDetailsRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        if (z) {
            hashMap.put(Constants.ARG_KRN, str);
        } else {
            hashMap.put(Constants.ARG_BOOKING_ID, str2);
        }
        hashMap.put(Constants.ARG_CAR_CATEGORY_ID, str3);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/rides/details", Request.Request.IMMEDIATE, hashMap, new AnonymousClass113(this, weakReference), new AnonymousClass114(this, weakReference), cq.class);
        instance.setTag(str4);
        m13168a(instance);
    }

    public void m13195a(WeakReference<aj> weakReference, boolean z, String str, String str2, String str3) {
        OLog.m13311a("rideDetailsRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        if (z) {
            hashMap.put(Constants.ARG_KRN, str);
        } else {
            hashMap.put(Constants.ARG_BOOKING_ID, str2);
        }
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/user/ride/track", Request.Request.IMMEDIATE, hashMap, new AnonymousClass115(this, weakReference), new AnonymousClass116(this, weakReference), ct.class);
        instance.setTag(str3);
        m13168a(instance);
    }

    public void m13214c(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("addFavouriteRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put("favourite", str);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/favourites/create", Request.Request.IMMEDIATE, hashMap, new AnonymousClass117(this, weakReference), new AnonymousClass118(this, weakReference), ax.class);
        instance.setTag(str2);
        m13168a(instance);
    }

    public void m13215c(WeakReference<aj> weakReference, String str, String str2, String str3) {
        OLog.m13311a("codeApplyRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.ARG_BOOKING_ID, str);
        hashMap.put(Constants.COUPON_CODE, str2);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        String str4 = Constants.DEVICE_MODEL;
        ao aoVar = this.f9360j;
        hashMap.put(str4, ao.DEVICE_MODEL);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/booking/coupon/apply", Request.Request.IMMEDIATE, hashMap, new AnonymousClass119(this, weakReference), new AnonymousClass121(this, weakReference), ae.class);
        instance.setTag(str3);
        m13168a(instance);
    }

    public void m13222d(WeakReference<aj> weakReference, String str, String str2, String str3) {
        OLog.m13311a("codeApplyRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.ARG_BOOKING_ID, str);
        hashMap.put(Constants.EMAIL, str2);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/booking/invoice/send", Request.Request.IMMEDIATE, hashMap, new AnonymousClass122(this, weakReference), new AnonymousClass123(this, weakReference), ae.class);
        instance.setTag(str3);
        m13168a(instance);
    }

    public void m13228e(WeakReference<aj> weakReference, String str, String str2, String str3) {
        OLog.m13311a("CancelRideRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.ARG_BOOKING_ID, str);
        hashMap.put("reason", str2);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/booking/cancel", Request.Request.IMMEDIATE, hashMap, new AnonymousClass124(this, weakReference), new AnonymousClass125(this, weakReference), cn.class);
        instance.setTag(str3);
        m13168a(instance);
    }

    public void m13184a(WeakReference<aj> weakReference, String str, String str2, String str3, String str4) {
        String str5;
        OLog.m13311a("CancelKPRideRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        String str6 = "v3/booking/kp/cancel";
        if ("delivery".equalsIgnoreCase(str3)) {
            str5 = "v3/booking/delivery/cancel";
        } else {
            str5 = str6;
        }
        Map hashMap = new HashMap();
        hashMap.put(Constants.ARG_BOOKING_ID, str);
        hashMap.put("reason", str2);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 2, "https://apps.olacabs.com/" + str5, Request.Request.IMMEDIATE, hashMap, new AnonymousClass126(this, weakReference), new AnonymousClass127(this, weakReference), cn.class);
        instance.setTag(str4);
        m13168a(instance);
    }

    public void m13174a(WeakReference<aj> weakReference, Double d, Double d2, String str) {
        OLog.m13311a("RequestForCityBasedCarModel", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.LAT, d.toString());
        hashMap.put(Constants.LNG, d2.toString());
        hashMap.put("ab_variant", this.f9359i.getCampaignName());
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/cab/city_wise_fare_breakup_with_surcharge", Request.Request.IMMEDIATE, hashMap, new AnonymousClass128(this, weakReference), new AnonymousClass129(this, weakReference), ab.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13205b(WeakReference<aj> weakReference, String str, String str2, String str3, String str4) {
        OLog.m13311a("getTransactions", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.EMAIL, str);
        hashMap.put("page_no", str2);
        hashMap.put("txn_type", str3);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v1_1/ola_money/report", Request.Request.IMMEDIATE, hashMap, new AnonymousClass130(this, weakReference), new AnonymousClass132(this, weakReference), dj.class);
        instance.setTag(str4);
        m13168a(instance);
    }

    public void m13247j(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("getUserFavourites", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        String str2 = "v3/favourites";
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        this.f9368r.post(new AnonymousClass135(this, hashMap, weakReference, str, olaApp, new AnonymousClass133(this, hashMap, weakReference), new AnonymousClass134(this, weakReference)));
    }

    public void m13171a(WeakReference<aj> weakReference, long j, String str) {
        OLog.m13311a("deleteFavourite", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("fav_id", String.valueOf(j));
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/favourites/delete", Request.Request.IMMEDIATE, hashMap, new AnonymousClass136(this, weakReference), new AnonymousClass137(this, weakReference), ay.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13221d(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("addFavourite", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("favourite", str);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/favourites/create", Request.Request.IMMEDIATE, hashMap, new AnonymousClass138(this, weakReference), new AnonymousClass139(this, weakReference), AddEditFavouriteResponse.class);
        instance.setTag(str2);
        m13168a(instance);
    }

    public void m13177a(WeakReference<aj> weakReference, String str, long j, String str2) {
        OLog.m13311a("editFavourite", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("fav_id", String.valueOf(j));
        hashMap.put("favourite", str);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/favourites/edit", Request.Request.IMMEDIATE, hashMap, new AnonymousClass140(this, weakReference), new AnonymousClass141(this, weakReference), AddEditFavouriteResponse.class);
        instance.setTag(str2);
        m13168a(instance);
    }

    public void m13183a(WeakReference<aj> weakReference, String str, String str2, String str3, LatLng latLng, boolean z, String str4) {
        if (latLng != null) {
            OLog.m13311a("getCurrentSurcharge", new Object[0]);
            OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
            Map hashMap = new HashMap();
            hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
            hashMap.put(Constants.CITY, str);
            hashMap.put(Constants.ARG_CAR_CATEGORY_ID, str2);
            if (!z) {
                hashMap.put("pickup_date", str3);
            }
            hashMap.put(Constants.LAT, String.valueOf(latLng.f7554a));
            hashMap.put(Constants.LNG, String.valueOf(latLng.f7555b));
            hashMap.put("ab_variant", this.f9359i.getCampaignName());
            Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/pricing/dynamic_surcharge_amount", Request.Request.IMMEDIATE, hashMap, new AnonymousClass143(this, weakReference), new AnonymousClass144(this, weakReference), af.class);
            instance.setTag(str4);
            m13168a(instance);
        } else if (weakReference != null) {
            aj ajVar = (aj) weakReference.get();
            if (ajVar != null) {
                ajVar.onFailure(m13134a((int) R.string.connection_time_out_error_title, (int) R.string.connection_time_out_error_desc));
            }
        }
    }

    public void m13227e(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("verifyRechargeCode", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("recharge_code", str);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/account/recharge", Request.Request.IMMEDIATE, hashMap, new AnonymousClass145(this, weakReference), new AnonymousClass146(this, weakReference), cl.class);
        instance.setTag(str2);
        m13168a(instance);
    }

    public void m13216c(WeakReference<aj> weakReference, String str, String str2, String str3, String str4) {
        OLog.m13311a("get order Id for payment", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.ARG_AMOUNT, str);
        hashMap.put(Constants.OLA_RIDE_OFFER_ID, str2);
        hashMap.put(Constants.COUPON_CODE, str3);
        String str5 = Constants.DEVICE_MODEL;
        ao aoVar = this.f9360j;
        hashMap.put(str5, ao.DEVICE_MODEL);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v1_1/ola_money/user_information_for_transaction_payu?", Request.Request.IMMEDIATE, hashMap, new AnonymousClass147(this, weakReference), new AnonymousClass148(this, weakReference), di.class);
        instance.setTag(str4);
        m13168a(instance);
    }

    public void m13223d(WeakReference<aj> weakReference, String str, String str2, String str3, String str4) {
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Bank.TXN_ID, str);
        hashMap.put("key", str2);
        hashMap.put(Constants.ARG_AMOUNT, Float.valueOf(str3) + Trace.NULL);
        hashMap.put(Constants.PRODUCT_INFO, "olaproduct");
        hashMap.put(Constants.FIRST_NAME, this.f9358h.getFirstName());
        hashMap.put(Constants.EMAIL, this.f9358h.getUserLoginEmail());
        hashMap.put(Constants.PHONE, this.f9358h.getPhoneNumber());
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v1_1/ola_money/checksum_payu", Request.Request.IMMEDIATE, hashMap, new AnonymousClass149(this, weakReference), new AnonymousClass150(this, weakReference), cd.class);
        instance.setTag(str4);
        m13168a(instance);
    }

    public void m13232f(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("get pay u transaction response", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.ORDER_ID, str);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v1_1/ola_money/get_transaction_balance_payu", Request.Request.IMMEDIATE, hashMap, new AnonymousClass152(this, weakReference), new AnonymousClass153(this, weakReference), cb.class);
        instance.setTag(str2);
        m13168a(instance);
    }

    public void m13233f(WeakReference<aj> weakReference, String str, String str2, String str3) {
        OLog.m13311a("requestPasswordChange", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("current_password", EncryptionScheme.m14898a(str));
        hashMap.put("new_password", EncryptionScheme.m14898a(str2));
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/user/changePassword", Request.Request.IMMEDIATE, hashMap, new AnonymousClass154(this, weakReference), new AnonymousClass155(this, weakReference), ChangePasswordResponse.class);
        instance.setTag(str3);
        m13168a(instance);
    }

    public void m13237g(WeakReference<aj> weakReference, String str, String str2, String str3) {
        OLog.m13311a("verifyPromoCode", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.COUPON_CODE, str);
        hashMap.put(Constants.ARG_AMOUNT, str2);
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v1_1/ola_money/verify_coupon", Request.Request.IMMEDIATE, hashMap, new AnonymousClass156(this, weakReference), new AnonymousClass157(this, weakReference), cj.class);
        instance.setTag(str3);
        m13168a(instance);
    }

    public void m13182a(WeakReference<aj> weakReference, String str, String str2, String str3, LatLng latLng, String str4) {
        OLog.m13311a("applyCouponCode", new Object[0]);
        if (latLng != null) {
            OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
            Map hashMap = new HashMap();
            hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
            hashMap.put(Constants.COUPON_CODE, str);
            hashMap.put(Constants.LAT, String.valueOf(latLng.f7554a));
            hashMap.put(Constants.LNG, String.valueOf(latLng.f7555b));
            hashMap.put(Constants.ARG_CAR_CATEGORY_ID, str2);
            hashMap.put("pickup_date", str3);
            String str5 = Constants.DEVICE_MODEL;
            ao aoVar = this.f9360j;
            hashMap.put(str5, ao.DEVICE_MODEL);
            Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/booking/coupon/verify", Request.Request.IMMEDIATE, hashMap, new AnonymousClass158(this, weakReference), new AnonymousClass159(this, weakReference), ae.class);
            instance.setTag(str4);
            m13168a(instance);
        } else if (weakReference != null) {
            aj ajVar = (aj) weakReference.get();
            if (ajVar != null) {
                ajVar.onFailure(m13134a((int) R.string.connection_time_out_error_title, (int) R.string.connection_time_out_error_desc));
            }
        }
    }

    public void m13179a(WeakReference<aj> weakReference, String str, LatLng latLng, boolean z, String str2, String str3, String str4, String str5, boolean z2, String str6, String str7, String str8) {
        OLog.m13311a("createBooking", new Object[0]);
        if (latLng != null) {
            OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
            Map hashMap = new HashMap();
            hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
            hashMap.put("category_id", str);
            hashMap.put(Constants.LAT, String.valueOf(latLng.f7554a));
            hashMap.put(Constants.LNG, String.valueOf(latLng.f7555b));
            Location userLocation = this.f9358h.getUserLocation();
            if (userLocation != null) {
                hashMap.put(dt.USER_LOC_ACCURACY_KEY, String.valueOf(userLocation.getAccuracy()));
                hashMap.put("speed", String.valueOf(userLocation.getSpeed()));
                hashMap.put("altitude", String.valueOf(userLocation.getAltitude()));
                hashMap.put(dt.USER_LOC_FIX_TIME_KEY, String.valueOf(userLocation.getTime()));
                hashMap.put("user_lat", String.valueOf(userLocation.getLatitude()));
                hashMap.put("user_lng", String.valueOf(userLocation.getLongitude()));
            } else {
                hashMap.put("user_lat", "0.0");
                hashMap.put("user_lng", "0.0");
            }
            if (z) {
                hashMap.put("pickup_mode", "NOW");
            } else {
                hashMap.put("pickup_mode", "LATER");
                hashMap.put("pickup_time", str2);
            }
            hashMap.put("location_type", str3);
            hashMap.put(Constants.BUNDLE_ADDRESS, str4);
            hashMap.put("surcharge_type", str6);
            hashMap.put("surcharge_value", str7);
            hashMap.put("ab_variant", this.f9359i.getCampaignName());
            String str9 = Constants.DEVICE_MODEL;
            ao aoVar = this.f9360j;
            hashMap.put(str9, ao.DEVICE_MODEL);
            hashMap.put(Constants.DEVICE_LOCATION_MODE, Utils.m14923g(this.f9355e));
            if (str5 != null) {
                hashMap.put("custom_code", str5);
            }
            ak o = m13259o();
            if (z2 && o.getUtmSource() != null) {
                hashMap.put(Constants.UTM_SOURCE, o.getUtmSource());
            }
            Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/booking/create", Request.Request.IMMEDIATE, hashMap, new AnonymousClass160(this, weakReference), new AnonymousClass161(this, weakReference), dh.class);
            instance.setRetryPolicy(new br());
            instance.setTag(str8);
            m13168a(instance);
        } else if (weakReference != null) {
            aj ajVar = (aj) weakReference.get();
            if (ajVar != null) {
                ajVar.onFailure(m13134a((int) R.string.connection_time_out_error_title, (int) R.string.connection_time_out_error_desc));
            }
        }
    }

    public void m13241h(WeakReference<aj> weakReference, String str, String str2, String str3) {
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.LAT, str);
        hashMap.put(Constants.LNG, str2);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/cities/localities", Request.Request.IMMEDIATE, hashMap, new DataManager(this, weakReference), new DataManager(this, weakReference), CitiesLocalitiesResponse.class);
        instance.setTag(str3);
        m13168a(instance);
    }

    public void m13189a(WeakReference<aj> weakReference, String str, String str2, String str3, boolean z, String str4, String str5, String str6, String str7, String str8) {
        OLog.m13311a("confirmDeliveryRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("category_id", str);
        hashMap.put("drop_lat", str2);
        hashMap.put("drop_lng", str3);
        if (z) {
            hashMap.put("pickup_mode", "NOW");
        } else {
            hashMap.put("pickup_mode", "LATER");
            hashMap.put("pickup_date", str4);
        }
        hashMap.put("receiver_name", str6);
        hashMap.put("receiver_number", str7);
        hashMap.put(Constants.BUNDLE_ADDRESS, str5);
        if (this.f9358h.getUserLocation() != null) {
            hashMap.put("user_lat", String.valueOf(this.f9358h.getUserLocation().getLatitude()));
            hashMap.put("user_lng", String.valueOf(this.f9358h.getUserLocation().getLongitude()));
            hashMap.put(dt.USER_LOC_ACCURACY_KEY, String.valueOf(this.f9358h.getUserLocation().getAccuracy()));
        }
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/booking/delivery/create", Request.Request.IMMEDIATE, hashMap, new DataManager(this, weakReference), new DataManager(this, weakReference), dh.class);
        instance.setTag(str8);
        m13168a(instance);
    }

    public void m13250k(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("Get CabInfo", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("enable_new_state", "true");
        hashMap.put(ao.NETWORK_TYPE_KEY, Utils.m14915c(this.f9355e));
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/cab/info", Request.Request.IMMEDIATE, hashMap, new DataManager(this, weakReference), new DataManager(this, weakReference), dh.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13206b(WeakReference<aj> weakReference, String str, String str2, String str3, String str4, String str5) {
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.LAT, str);
        hashMap.put(Constants.LNG, str2);
        hashMap.put("tag", str3);
        hashMap.put("query", str4);
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/location/search", Request.Request.IMMEDIATE, hashMap, new DataManager(this, weakReference), new AnonymousClass10(this, weakReference), cy.class);
        instance.setTag(str5);
        m13168a(instance);
    }

    public void m13236g(WeakReference<aj> weakReference, String str, String str2) {
        Request instance = bw.getInstance((OlaApp) this.f9355e.getApplicationContext(), 0, String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&components=country:IN", new Object[]{URLEncoder.encode(str)}), Request.Request.IMMEDIATE, new HashMap(), new AnonymousClass11(this, weakReference), new AnonymousClass12(this, weakReference), bi.class);
        OLog.m13315c("Google server request " + instance.getOriginUrl(), new Object[0]);
        instance.setTag(str2);
        Volley volley = new Volley();
        Volley.m685a(this.f9355e.getApplicationContext()).m577a(instance);
    }

    public void m13208b(WeakReference<aj> weakReference, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        OLog.m13311a("confirmDeliveryRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("category_id", str);
        hashMap.put(Constants.LAT, str2);
        hashMap.put(Constants.LNG, str3);
        hashMap.put("drop_locality_id", str4);
        hashMap.put("drop_locality", str5);
        hashMap.put("drop_landmark", str6);
        hashMap.put("pickup_landmark", str7);
        hashMap.put(Constants.BUNDLE_ADDRESS, str8);
        if (this.f9358h.getUserLocation() != null) {
            hashMap.put("user_lat", String.valueOf(this.f9358h.getUserLocation().getLatitude()));
            hashMap.put("user_lng", String.valueOf(this.f9358h.getUserLocation().getLongitude()));
            hashMap.put(dt.USER_LOC_ACCURACY_KEY, String.valueOf(this.f9358h.getUserLocation().getAccuracy()));
        }
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/booking/kp/create", Request.Request.IMMEDIATE, hashMap, new AnonymousClass14(this, weakReference), new AnonymousClass15(this, weakReference), BasicResponse.class);
        instance.setTag(str9);
        m13168a(instance);
    }

    public void m13192a(WeakReference<aj> weakReference, boolean z, LatLng latLng, Location location, boolean z2, String str, String str2) {
        m13193a(weakReference, z, latLng, location, z2, str, str2, Boolean.valueOf(false));
    }

    public void m13193a(WeakReference<aj> weakReference, boolean z, LatLng latLng, Location location, boolean z2, String str, String str2, Boolean bool) {
        OLog.m13311a("requestBookingCabInfo", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("enable_new_state", "true");
        hashMap.put("enable_auto", "true");
        hashMap.put("enable_marketing", "true");
        if (z) {
            hashMap.put("custom_lat", String.valueOf(latLng.f7554a));
            hashMap.put("custom_lng", String.valueOf(latLng.f7555b));
        } else {
            hashMap.put(Constants.LAT, String.valueOf(latLng.f7554a));
            hashMap.put(Constants.LNG, String.valueOf(latLng.f7555b));
        }
        if (location != null) {
            hashMap.put(dt.USER_LOC_ACCURACY_KEY, String.valueOf(location.getAccuracy()));
            hashMap.put("speed", String.valueOf(location.getSpeed()));
            hashMap.put("altitude", String.valueOf(location.getAltitude()));
            hashMap.put(dt.USER_LOC_FIX_TIME_KEY, String.valueOf(location.getTime()));
        }
        if (z) {
            hashMap.put("location_type", "CUSTOM");
            hashMap.put("selected_by", "USER");
        } else {
            hashMap.put("location_type", "CURRENT");
            hashMap.put("selected_by", z2 ? "USER" : "AUTO");
        }
        if (str != null) {
            hashMap.put("cab_category", str);
        }
        hashMap.put(ao.NETWORK_TYPE_KEY, Utils.m14912b(this.f9355e) ? "fast" : "slow");
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/cab/info", Request.Request.IMMEDIATE, hashMap, new AnonymousClass16(this, weakReference, bool), new AnonymousClass17(this, weakReference, bool), BookingCabInfoResponse.class);
        instance.setTag(str2);
        m13168a(instance);
    }

    public void m13240h(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("requestTrackRide", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.ARG_BOOKING_ID, str);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/user/ride/track", Request.Request.IMMEDIATE, hashMap, new AnonymousClass18(this, weakReference), new AnonymousClass19(this, weakReference), ct.class);
        instance.setTag(str2);
        m13168a(instance);
    }

    public void m13245i(WeakReference<aj> weakReference, String str, String str2, String str3) {
        OLog.m13311a("submitLocalTaxiResponse", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        if (str != null) {
            hashMap.put(Constants.ARG_KRN, str);
        }
        hashMap.put("text", str2);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/booking/kp/submit_response", Request.Request.IMMEDIATE, hashMap, new AnonymousClass20(this, weakReference), new AnonymousClass21(this, weakReference), BasicResponse.class);
        instance.setTag(str3);
        m13168a(instance);
    }

    public void m13173a(WeakReference<aj> weakReference, LatLng latLng, String str) {
        OLog.m13311a("submitLocalTaxiResponse", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.LAT, String.valueOf(latLng.f7554a));
        hashMap.put(Constants.LNG, String.valueOf(latLng.f7555b));
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(ao.NETWORK_TYPE_KEY, Utils.m14912b(this.f9355e) ? "fast" : "slow");
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/cab/info", Request.Request.IMMEDIATE, hashMap, new AnonymousClass22(this, weakReference), new AnonymousClass23(this, weakReference), BookingCabInfoResponse.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13210c(WeakReference<aj> weakReference) {
        OLog.m13311a("Checking if the user has pending food orders", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(NotificationCompatApi21.CATEGORY_STATUS, "pending");
        Request instance = bw.getInstance(olaApp, 0, "https://oms.ofd.olacabs.com/v1/orders", Request.Request.IMMEDIATE, hashMap, new AnonymousClass25(this, weakReference), new AnonymousClass26(this, weakReference), bf[].class);
        instance.setTag(f9352b);
        m13168a(instance);
    }

    public void m13253l(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("queryFoodMenu", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        if (m13209c().getUserLocation() != null) {
            hashMap.put(Constants.LAT, String.valueOf(m13209c().getUserLocation().getLatitude()));
            hashMap.put(Constants.LNG, String.valueOf(m13209c().getUserLocation().getLongitude()));
            Request instance = bw.getInstance(olaApp, 0, "https://discovery.ofd.olacabs.com/products", Request.Request.IMMEDIATE, hashMap, new AnonymousClass27(this, weakReference), new AnonymousClass28(this, weakReference), be.class);
            instance.setTag(str);
            m13168a(instance);
        } else if (weakReference != null && weakReference.get() != null) {
            ((aj) weakReference.get()).onFailure(m13134a((int) R.string.error_generic_ofd_title, (int) R.string.error_unable_fetch_food_message));
        }
    }

    public void m13191a(WeakReference<aj> weakReference, JSONObject jSONObject, String str) {
        OLog.m13311a("requestCheckout", new Object[0]);
        JSONObject jSONObject2 = jSONObject;
        Request byVar = new by((OlaApp) this.f9355e.getApplicationContext(), 1, "https://oms.ofd.olacabs.com/v1/orders", jSONObject2, Request.Request.IMMEDIATE, new AnonymousClass29(this, weakReference), new AnonymousClass30(this, weakReference));
        byVar.setTag(f9352b);
        m13168a(byVar);
    }

    public void m13199b(WeakReference<aj> weakReference, long j, String str) {
        OLog.m13311a("Fetching details for Order # " + j, new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://oms.ofd.olacabs.com/v1/orders/" + j, Request.Request.IMMEDIATE, hashMap, new AnonymousClass31(this, weakReference), new AnonymousClass32(this, weakReference), du.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13172a(WeakReference<aj> weakReference, long j, String str, String str2) {
        OLog.m13311a("cancelOrder", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("reason", str);
        hashMap.put(Constants.BUNDLE_TYPE, "customer_cancelled");
        Request instance = bw.getInstance(olaApp, 1, "https://oms.ofd.olacabs.com/v1/orders/" + j + "/cancel", Request.Request.IMMEDIATE, hashMap, new AnonymousClass33(this, weakReference), new AnonymousClass34(this, weakReference), du.class);
        instance.setTag(str2);
        m13168a(instance);
    }

    public ImageLoader m13252l() {
        m13160p();
        return this.f9362l.m13276b();
    }

    public void m13219d(WeakReference<aj> weakReference) {
        OLog.m13311a("Fetch food delivery configurations from server", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        m13168a(bw.getInstance(olaApp, 0, "https://discovery.ofd.olacabs.com/configs/consumer_app", Request.Request.IMMEDIATE, hashMap, new AnonymousClass36(this, weakReference), new AnonymousClass37(this, weakReference), bb.class));
    }

    public void m13211c(WeakReference<aj> weakReference, long j, String str) {
        OLog.m13311a("Fetch track order details", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://oms.ofd.olacabs.com/v1/orders/" + j + "/track", Request.Request.IMMEDIATE, hashMap, new AnonymousClass38(this, weakReference), new AnonymousClass39(this, weakReference), bu.class);
        instance.setTag(str);
        m13168a(instance);
    }

    public void m13190a(WeakReference<aj> weakReference, String str, boolean z) {
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put("rooted", String.valueOf(z));
        hashMap.put("proxy_address", str);
        m13168a(bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/user/device_state_data/", Request.Request.LOW, hashMap, new AnonymousClass40(this, weakReference), new AnonymousClass41(this, weakReference), BasicResponse.class));
    }

    public void m13225e(WeakReference<aj> weakReference) {
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        m13168a(bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v3/cab/driver_cancel_change_state", Request.Request.IMMEDIATE, hashMap, new AnonymousClass42(this, weakReference), new AnonymousClass43(this, weakReference), BasicResponse.class));
    }

    public void m13255m() {
        OLog.m13311a("initialiseAuthSession asynchronously", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        String str = Constants.DEVICE_MODEL;
        ao aoVar = this.f9360j;
        hashMap.put(str, ao.DEVICE_MODEL);
        hashMap.put(ao.DEVICE_ID_KEY, this.f9360j.getDeviceId());
        hashMap.put(da.APP_INSTALLATION_ID_KEY, this.f9359i.getInstallationId());
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v1/tokens/initialize", Request.Request.NORMAL, hashMap, new Response<AuthSessionResponse>() {
            final /* synthetic */ DataManager f9222a;

            {
                this.f9222a = r1;
            }

            public void m13041a(AuthSessionResponse authSessionResponse) {
                if (authSessionResponse != null && authSessionResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                    this.f9222a.f9359i.setAuthSessionId(authSessionResponse.getAuthSessionToken().getAccessToken());
                    OLog.m13313b("Initial OAuth Session token" + authSessionResponse.getAuthSessionToken().getAccessToken(), new Object[0]);
                }
            }
        }, null, AuthSessionResponse.class);
        instance.setRetryPolicy(new DefaultRetryPolicy(1500, 3, br.DEFAULT_BACKOFF_MULT));
        m13168a(instance);
    }

    public void m13256m(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("initialiseAuthSession synchronously", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        String str2 = Constants.DEVICE_MODEL;
        ao aoVar = this.f9360j;
        hashMap.put(str2, ao.DEVICE_MODEL);
        hashMap.put(ao.DEVICE_ID_KEY, this.f9360j.getDeviceId());
        hashMap.put(da.APP_INSTALLATION_ID_KEY, this.f9359i.getInstallationId());
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/v1/tokens/initialize", Request.Request.IMMEDIATE, hashMap, new AnonymousClass45(this, weakReference), new AnonymousClass47(this, weakReference), AuthSessionResponse.class);
        instance.setTag(str);
        instance.setRetryPolicy(new DefaultRetryPolicy(1500, 3, br.DEFAULT_BACKOFF_MULT));
        this.f9364n.m580a((Object) str);
        m13168a(instance);
    }

    public void m13257n() {
        OLog.m13313b("refreshAuthSession asynchronously", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.AUTH_REFRESH_TOKEN, this.f9359i.getAuthRefreshToken());
            jSONObject.put(da.APP_INSTALLATION_ID_KEY, this.f9359i.getInstallationId());
            m13168a(new by(olaApp, 1, "https://apps.olacabs.com/v1/tokens/refresh", jSONObject, Request.Request.IMMEDIATE, new Response<JSONObject>() {
                final /* synthetic */ DataManager f9229a;

                {
                    this.f9229a = r1;
                }

                public void m13048a(JSONObject jSONObject) {
                    this.f9229a.f9359i.setTokenAuthorizedStatus(true);
                    if (jSONObject != null) {
                        if (Utils.m14924g(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject))) {
                            AuthRefreshResponse authRefreshResponse = (AuthRefreshResponse) new Gson().m12343a(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject), AuthRefreshResponse.class);
                            if (authRefreshResponse == null || !authRefreshResponse.getStatus().equalsIgnoreCase("SUCCESS")) {
                                OLog.m13317d("refresh auth token is null", new Object[0]);
                                return;
                            }
                            AuthSession authSession = authRefreshResponse.getAuthSession();
                            this.f9229a.f9359i.setAuthSessionId(authSession.getOlaAuthToken());
                            this.f9229a.f9359i.setAuthSessionExpiry(authSession.getAccessTokenExpiryFromNow() + System.currentTimeMillis());
                            OLog.m13313b(" Refreshed oauth token" + authSession.getOlaAuthToken(), new Object[0]);
                        }
                    }
                }
            }, null));
        } catch (Throwable e) {
            OLog.m13310a("Failed to form refresh auth JSON", e);
        }
    }

    public void m13258n(WeakReference<aj> weakReference, String str) {
        OLog.m13313b("refreshAuthSession Synchronously", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.AUTH_REFRESH_TOKEN, this.f9359i.getAuthRefreshToken());
            jSONObject.put(da.APP_INSTALLATION_ID_KEY, this.f9359i.getInstallationId());
            Request byVar = new by(olaApp, 1, "https://apps.olacabs.com/v1/tokens/refresh", jSONObject, Request.Request.IMMEDIATE, new AnonymousClass49(this, weakReference), new AnonymousClass50(this, weakReference));
            this.f9364n.m580a((Object) str);
            m13168a(byVar);
        } catch (Throwable e) {
            OLog.m13310a("Failed to form refresh auth JSON", e);
        }
    }

    public void m13201b(WeakReference<aj> weakReference, Double d, Double d2, String str) {
        OLog.m13311a("requestRfsFare", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.LAT, d.toString());
        hashMap.put(Constants.LNG, d2.toString());
        Request instance = bw.getInstance(olaApp, 0, "https://tfs-apps.olacabs.com/v3/tfs/fares", Request.Request.IMMEDIATE, hashMap, new AnonymousClass51(this, weakReference), new AnonymousClass52(this, weakReference), TFSFareResponse.class);
        instance.setTag(str);
        instance.setRetryPolicy(new DefaultRetryPolicy(30000, 2, br.DEFAULT_BACKOFF_MULT));
        m13168a(instance);
    }

    public void m13200b(WeakReference<aj> weakReference, LatLng latLng, String str) {
        OLog.m13311a("Fetch tfs cab info", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.LAT, String.valueOf(latLng.f7554a));
        hashMap.put(Constants.LNG, String.valueOf(latLng.f7555b));
        Request instance = bw.getInstance(olaApp, 0, "https://tfs-apps.olacabs.com/v3/tfs/cab_info", Request.Request.NORMAL, hashMap, new AnonymousClass53(this, weakReference), new AnonymousClass54(this, weakReference), TFSCabInfoResponse.class);
        instance.setTag(str);
        instance.setRetryPolicy(new DefaultRetryPolicy(30000, 2, br.DEFAULT_BACKOFF_MULT));
        m13168a(instance);
    }

    public void m13194a(WeakReference<aj> weakReference, boolean z, LatLng latLng, String str) {
        OLog.m13311a("Fetch tfs cab info", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.LAT, String.valueOf(latLng.f7554a));
        hashMap.put(Constants.LNG, String.valueOf(latLng.f7555b));
        hashMap.put("pickup_address", this.f9358h.getUserAddress());
        hashMap.put(ao.DEVICE_ID_KEY, this.f9360j.getDeviceId());
        hashMap.put("is_airportdrop", String.valueOf(z));
        Request instance = bw.getInstance(olaApp, 1, "https://tfs-apps.olacabs.com/v3/tfs/booking_create", Request.Request.IMMEDIATE, hashMap, new AnonymousClass55(this, weakReference), new AnonymousClass56(this, weakReference), TFSBookingCreateResponse.class);
        instance.setTag(str);
        instance.setRetryPolicy(new br());
        instance.setRetryPolicy(new DefaultRetryPolicy(30000, 2, br.DEFAULT_BACKOFF_MULT));
        m13168a(instance);
    }

    public void m13244i(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("CancelTFSRideRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.ARG_BOOKING_ID, str);
        hashMap.put("reason", str2);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 1, "https://tfs-apps.olacabs.com/v3/tfs/booking_cancel", Request.Request.IMMEDIATE, hashMap, new AnonymousClass58(this, weakReference), new AnonymousClass59(this, weakReference), BasicResponse.class);
        instance.setRetryPolicy(new DefaultRetryPolicy(30000, 2, br.DEFAULT_BACKOFF_MULT));
        m13168a(instance);
    }

    public void m13248j(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("trackTFSTaxiRequest", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.ARG_BOOKING_ID, str);
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://tfs-apps.olacabs.com/v3/tfs/track_taxi", Request.Request.IMMEDIATE, hashMap, new AnonymousClass60(this, weakReference), new AnonymousClass61(this, weakReference), TFSTrackTaxiResponse.class);
        instance.setTag(str2);
        instance.setRetryPolicy(new DefaultRetryPolicy(30000, 2, br.DEFAULT_BACKOFF_MULT));
        m13168a(instance);
    }

    public void m13251k(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("Fetch tfs Call Driver info", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.ARG_BOOKING_ID, str);
        Request instance = bw.getInstance(olaApp, 0, "https://tfs-apps.olacabs.com/v3/tfs/call_driver", Request.Request.IMMEDIATE, hashMap, new AnonymousClass62(this, weakReference), new AnonymousClass63(this, weakReference), TFSCallDriverResponse.class);
        instance.setTag(str2);
        instance.setRetryPolicy(new DefaultRetryPolicy(30000, 2, br.DEFAULT_BACKOFF_MULT));
        m13168a(instance);
    }

    public void m13254l(WeakReference<aj> weakReference, String str, String str2) {
        OLog.m13311a("Fetch share ride", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.ARG_BOOKING_ID, str);
        Request instance = bw.getInstance(olaApp, 0, "http://www.radiotaxiforsure.in/api/shorten-url", Request.Request.IMMEDIATE, hashMap, new AnonymousClass64(this, weakReference), new AnonymousClass65(this, weakReference), TFSShareRideResponse.class);
        instance.setTag(str2);
        instance.setRetryPolicy(new DefaultRetryPolicy(30000, 2, br.DEFAULT_BACKOFF_MULT));
        m13168a(instance);
    }

    public void m13260o(WeakReference<aj> weakReference, String str) {
        OLog.m13311a("check TFS booking", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        Request instance = bw.getInstance(olaApp, 0, "https://tfs-apps.olacabs.com/v3/tfs/has_booking", Request.Request.IMMEDIATE, hashMap, new AnonymousClass66(this, weakReference), new AnonymousClass67(this, weakReference), TFSHasBookingResponse.class);
        instance.setTag(str);
        instance.setRetryPolicy(new DefaultRetryPolicy(30000, 2, br.DEFAULT_BACKOFF_MULT));
        m13168a(instance);
    }

    public void m13212c(WeakReference<aj> weakReference, Double d, Double d2, String str) {
        OLog.m13311a("RequestForCityBasedCarModel", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.USER_ID, this.f9358h.getUserId());
        hashMap.put(Constants.LAT, d.toString());
        hashMap.put(Constants.LNG, d2.toString());
        hashMap.put("ab_variant", this.f9359i.getCampaignName());
        Request instance = bw.getInstance(olaApp, 0, "https://apps.olacabs.com/v3/cab/city_wise_car_model_fare_breakup", Request.Request.IMMEDIATE, hashMap, new AnonymousClass69(this, weakReference), new AnonymousClass70(this, weakReference), ab.class);
        instance.setTag(str);
        m13168a(instance);
    }

    private VolleyError m13134a(int i, int i2) {
        return new VolleyError(new NetworkResponse(new Gson().m12346a(new bt(this.f9355e.getString(i), this.f9355e.getString(i2))).getBytes()));
    }

    public void m13166a(Uri uri) {
        this.f9361k = new DeepLinkInfo().latitude(uri.getQueryParameter(Constants.LAT)).longitude(uri.getQueryParameter(Constants.LNG)).cabCategory(uri.getQueryParameter(AnalyticAttribute.EVENT_CATEGORY_ATTRIBUTE)).landingPage(uri.getQueryParameter("landing_page")).utmSource(uri.getQueryParameter(Constants.UTM_SOURCE)).address(uri.getQueryParameter(Constants.BUNDLE_ADDRESS)).deepLinked(true).confPanelPending(true).build();
    }

    public ak m13259o() {
        if (this.f9361k == null) {
            this.f9361k = new DeepLinkInfo().build();
        }
        return this.f9361k;
    }

    public void m13229e(WeakReference<aj> weakReference, String str, String str2, String str3, String str4) {
        OLog.m13311a("sendPushAck", new Object[0]);
        OlaApp olaApp = (OlaApp) this.f9355e.getApplicationContext();
        Map hashMap = new HashMap();
        hashMap.put(Constants.PUSH_REQUEST_ID, str);
        hashMap.put(Constants.PUSH_ACK_TYPE, str2);
        hashMap.put(Constants.PUSH_ACK_TIME, str3);
        Request instance = bw.getInstance(olaApp, 1, "https://apps.olacabs.com/marketing/v1/feedback/push", Request.Request.IMMEDIATE, hashMap, new AnonymousClass71(this, weakReference), new AnonymousClass72(this, weakReference), ck.class);
        instance.setRetryPolicy(new DefaultRetryPolicy(15000, 2, br.DEFAULT_BACKOFF_MULT));
        instance.setTag(str4);
        m13168a(instance);
    }

    private RequestQueue m13161q() {
        Throwable e;
        SSLContext sSLContext = null;
        TrustManager[] trustManagerArr = new TrustManager[]{new PublicKeyManager()};
        try {
            sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagerArr, null);
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            e.printStackTrace();
            if (AppInfo.sRunningMode != ApplicationMode.DEV) {
                Crashlytics.log("NoSuchAlgorithmException or KeyManagementException");
                Crashlytics.logException(e);
            }
            if (sSLContext != null) {
                return Volley.m686a(this.f9355e, MegatronCore.m12842e().m12855d());
            }
            return Volley.m686a(this.f9355e.getApplicationContext(), MegatronCore.m12842e().m12844a(sSLContext.getSocketFactory()));
        } catch (KeyManagementException e3) {
            e = e3;
            e.printStackTrace();
            if (AppInfo.sRunningMode != ApplicationMode.DEV) {
                Crashlytics.log("NoSuchAlgorithmException or KeyManagementException");
                Crashlytics.logException(e);
            }
            if (sSLContext != null) {
                return Volley.m686a(this.f9355e, MegatronCore.m12842e().m12855d());
            }
            return Volley.m686a(this.f9355e.getApplicationContext(), MegatronCore.m12842e().m12844a(sSLContext.getSocketFactory()));
        }
        if (sSLContext != null) {
            return Volley.m686a(this.f9355e, MegatronCore.m12842e().m12855d());
        }
        return Volley.m686a(this.f9355e.getApplicationContext(), MegatronCore.m12842e().m12844a(sSLContext.getSocketFactory()));
    }

    private RequestQueue m13162r() {
        if (this.f9355e.getResources().getBoolean(R.bool.ssl_pinning_flag)) {
            return m13161q();
        }
        return Volley.m686a(this.f9355e, MegatronCore.m12842e().m12855d());
    }
}
