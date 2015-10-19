package com.leanplum.annotations;

import android.util.Log;
import com.leanplum.Var;
import com.leanplum.callbacks.VariableCallback;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/* renamed from: com.leanplum.annotations.b */
final class C0627b extends VariableCallback<String> {
    private final /* synthetic */ WeakReference f8777a;
    private final /* synthetic */ boolean f8778b;
    private final /* synthetic */ Field f8779c;
    private final /* synthetic */ Var f8780d;

    C0627b(WeakReference weakReference, boolean z, Field field, Var var) {
        this.f8777a = weakReference;
        this.f8778b = z;
        this.f8779c = field;
        this.f8780d = var;
    }

    public final void handle(Var<String> var) {
        Object obj = this.f8777a.get();
        if ((this.f8778b && obj == null) || this.f8779c == null) {
            this.f8780d.removeFileReadyHandler(this);
            return;
        }
        try {
            boolean isAccessible = this.f8779c.isAccessible();
            if (!isAccessible) {
                this.f8779c.setAccessible(true);
            }
            this.f8779c.set(obj, this.f8780d.fileValue());
            if (!isAccessible) {
                this.f8779c.setAccessible(false);
            }
        } catch (Throwable e) {
            Log.e("Leanplum", "Invalid value " + ((String) this.f8780d.value()) + " for field " + this.f8780d.name(), e);
        } catch (Throwable e2) {
            Log.e("Leanplum", "Error setting value for field " + this.f8780d.name(), e2);
        }
    }
}
