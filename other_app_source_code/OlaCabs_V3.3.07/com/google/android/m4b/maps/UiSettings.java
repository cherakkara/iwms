package com.google.android.m4b.maps;

import android.os.RemoteException;
import com.google.android.m4b.maps.model.RuntimeRemoteException;
import com.google.android.m4b.maps.p042r.IUiSettingsDelegate;

/* renamed from: com.google.android.m4b.maps.k */
public final class UiSettings {
    private final IUiSettingsDelegate f7464a;

    UiSettings(IUiSettingsDelegate iUiSettingsDelegate) {
        this.f7464a = iUiSettingsDelegate;
    }

    public final void m10562a(boolean z) {
        try {
            this.f7464a.m9076f(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10563b(boolean z) {
        try {
            this.f7464a.m9078h(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10564c(boolean z) {
        try {
            this.f7464a.m9079i(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10565d(boolean z) {
        try {
            this.f7464a.m9080j(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10566e(boolean z) {
        try {
            this.f7464a.m9081k(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10567f(boolean z) {
        try {
            this.f7464a.m9082l(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10568g(boolean z) {
        try {
            this.f7464a.m9084m(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void m10569h(boolean z) {
        try {
            this.f7464a.m9085n(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
