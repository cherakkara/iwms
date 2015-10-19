package com.leanplum.annotations;

import android.util.Log;
import com.leanplum.Var;
import com.leanplum.callbacks.VariableCallback;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/* renamed from: com.leanplum.annotations.a */
final class C0626a extends VariableCallback<T> {
    private final /* synthetic */ WeakReference f8773a;
    private final /* synthetic */ boolean f8774b;
    private final /* synthetic */ Field f8775c;
    private final /* synthetic */ Var f8776d;

    C0626a(WeakReference weakReference, boolean z, Field field, Var var) {
        this.f8773a = weakReference;
        this.f8774b = z;
        this.f8775c = field;
        this.f8776d = var;
    }

    public final void handle(Var<T> var) {
        Object obj = this.f8773a.get();
        if ((this.f8774b && obj == null) || this.f8775c == null) {
            this.f8776d.removeValueChangedHandler(this);
            return;
        }
        try {
            boolean isAccessible = this.f8775c.isAccessible();
            if (!isAccessible) {
                this.f8775c.setAccessible(true);
            }
            this.f8775c.set(obj, this.f8776d.value());
            if (!isAccessible) {
                this.f8775c.setAccessible(false);
            }
        } catch (Throwable e) {
            Log.e("Leanplum", "Invalid value " + this.f8776d.value() + " for field " + this.f8776d.name(), e);
        } catch (Throwable e2) {
            Log.e("Leanplum", "Error setting value for field " + this.f8776d.name(), e2);
        }
    }
}
