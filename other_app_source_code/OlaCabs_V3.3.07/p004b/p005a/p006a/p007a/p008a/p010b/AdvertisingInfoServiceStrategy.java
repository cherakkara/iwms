package p004b.p005a.p006a.p007a.p008a.p010b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import com.crashlytics.android.core.CrashlyticsCore;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import p004b.p005a.p006a.p007a.Fabric;

/* renamed from: b.a.a.a.a.b.e */
class AdvertisingInfoServiceStrategy implements AdvertisingInfoStrategy {
    private final Context f125a;

    /* renamed from: b.a.a.a.a.b.e.a */
    private static final class AdvertisingInfoServiceStrategy implements ServiceConnection {
        private boolean f122a;
        private final LinkedBlockingQueue<IBinder> f123b;

        private AdvertisingInfoServiceStrategy() {
            this.f122a = false;
            this.f123b = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f123b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.f123b.clear();
        }

        public IBinder m136a() {
            if (this.f122a) {
                Fabric.m512h().m481e(CrashlyticsCore.TAG, "getBinder already called");
            }
            this.f122a = true;
            try {
                return (IBinder) this.f123b.poll(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                return null;
            }
        }
    }

    /* renamed from: b.a.a.a.a.b.e.b */
    private static final class AdvertisingInfoServiceStrategy implements IInterface {
        private final IBinder f124a;

        public boolean m138b() throws android.os.RemoteException {
            /* JADX: method processing error */
/*
            Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r7 = this;
            r0 = 1;
            r1 = 0;
            r2 = android.os.Parcel.obtain();
            r3 = android.os.Parcel.obtain();
            r4 = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r2.writeInterfaceToken(r4);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = 1;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r2.writeInt(r4);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = r7.f124a;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r5 = 2;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r6 = 0;	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4.transact(r5, r2, r3, r6);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r3.readException();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = r3.readInt();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            if (r4 == 0) goto L_0x002a;
        L_0x0023:
            r3.recycle();
            r2.recycle();
        L_0x0029:
            return r0;
        L_0x002a:
            r0 = r1;
            goto L_0x0023;
        L_0x002c:
            r0 = move-exception;
            r0 = p004b.p005a.p006a.p007a.Fabric.m512h();	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r4 = "Fabric";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r5 = "Could not get parcel from Google Play Service to capture Advertising limitAdTracking";	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r0.m474a(r4, r5);	 Catch:{ Exception -> 0x002c, all -> 0x0040 }
            r3.recycle();
            r2.recycle();
            r0 = r1;
            goto L_0x0029;
        L_0x0040:
            r0 = move-exception;
            r3.recycle();
            r2.recycle();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: b.a.a.a.a.b.e.b.b():boolean");
        }

        public AdvertisingInfoServiceStrategy(IBinder iBinder) {
            this.f124a = iBinder;
        }

        public IBinder asBinder() {
            return this.f124a;
        }

        public String m137a() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            String str = null;
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f124a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                str = obtain2.readString();
            } catch (Exception e) {
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Could not get parcel from Google Play Service to capture AdvertisingId");
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return str;
        }
    }

    public AdvertisingInfoServiceStrategy(Context context) {
        this.f125a = context.getApplicationContext();
    }

    public AdvertisingInfo m139a() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Fabric.m512h().m474a(CrashlyticsCore.TAG, "AdvertisingInfoServiceStrategy cannot be called on the main thread");
            return null;
        }
        try {
            this.f125a.getPackageManager().getPackageInfo("com.android.vending", 0);
            ServiceConnection advertisingInfoServiceStrategy = new AdvertisingInfoServiceStrategy();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                if (this.f125a.bindService(intent, advertisingInfoServiceStrategy, 1)) {
                    AdvertisingInfoServiceStrategy advertisingInfoServiceStrategy2 = new AdvertisingInfoServiceStrategy(advertisingInfoServiceStrategy.m136a());
                    AdvertisingInfo advertisingInfo = new AdvertisingInfo(advertisingInfoServiceStrategy2.m137a(), advertisingInfoServiceStrategy2.m138b());
                    this.f125a.unbindService(advertisingInfoServiceStrategy);
                    return advertisingInfo;
                }
                Fabric.m512h().m474a(CrashlyticsCore.TAG, "Could not bind to Google Play Service to capture AdvertisingId");
                return null;
            } catch (Throwable e) {
                Fabric.m512h().m480d(CrashlyticsCore.TAG, "Exception in binding to Google Play Service to capture AdvertisingId", e);
                this.f125a.unbindService(advertisingInfoServiceStrategy);
                return null;
            } catch (Throwable e2) {
                Fabric.m512h().m475a(CrashlyticsCore.TAG, "Could not bind to Google Play Service to capture AdvertisingId", e2);
                return null;
            }
        } catch (Throwable e22) {
            Fabric.m512h().m475a(CrashlyticsCore.TAG, "Unable to determine if Google Play Services is available", e22);
            return null;
        }
    }
}
