package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.C0270e;
import com.google.android.gms.common.internal.C0411p.C0412a;

/* renamed from: com.google.android.gms.common.internal.b */
public class C0413b extends C0412a {
    int f2231a;
    private Account f2232b;
    private Context f2233c;

    public static Account m3558a(C0411p c0411p) {
        Account account = null;
        if (c0411p != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                account = c0411p.m3556a();
            } catch (RemoteException e) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
        return account;
    }

    public Account m3559a() {
        int callingUid = Binder.getCallingUid();
        if (callingUid == this.f2231a) {
            return this.f2232b;
        }
        if (C0270e.m3385a(this.f2233c, callingUid)) {
            this.f2231a = callingUid;
            return this.f2232b;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !(obj instanceof C0413b) ? false : this.f2232b.equals(((C0413b) obj).f2232b);
    }
}
