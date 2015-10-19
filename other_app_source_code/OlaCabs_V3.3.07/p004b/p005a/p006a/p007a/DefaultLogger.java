package p004b.p005a.p006a.p007a;

import android.util.Log;

/* renamed from: b.a.a.a.b */
public class DefaultLogger implements Logger {
    private int f398a;

    public DefaultLogger(int i) {
        this.f398a = i;
    }

    public DefaultLogger() {
        this.f398a = 4;
    }

    public boolean m487a(String str, int i) {
        return this.f398a <= i;
    }

    public void m486a(String str, String str2, Throwable th) {
        if (m487a(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    public void m489b(String str, String str2, Throwable th) {
        if (m487a(str, 2)) {
            Log.v(str, str2, th);
        }
    }

    public void m491c(String str, String str2, Throwable th) {
        if (m487a(str, 4)) {
            Log.i(str, str2, th);
        }
    }

    public void m493d(String str, String str2, Throwable th) {
        if (m487a(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    public void m495e(String str, String str2, Throwable th) {
        if (m487a(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    public void m485a(String str, String str2) {
        m486a(str, str2, null);
    }

    public void m488b(String str, String str2) {
        m489b(str, str2, null);
    }

    public void m490c(String str, String str2) {
        m491c(str, str2, null);
    }

    public void m492d(String str, String str2) {
        m493d(str, str2, null);
    }

    public void m494e(String str, String str2) {
        m495e(str, str2, null);
    }

    public void m483a(int i, String str, String str2) {
        m484a(i, str, str2, false);
    }

    public void m484a(int i, String str, String str2, boolean z) {
        if (z || m487a(str, i)) {
            Log.println(i, str, str2);
        }
    }
}
