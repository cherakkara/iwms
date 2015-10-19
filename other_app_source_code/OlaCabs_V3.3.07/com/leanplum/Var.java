package com.leanplum;

import android.util.Log;
import com.leanplum.FileManager.DownloadFileResult;
import com.leanplum.callbacks.VariableCallback;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var<T> {
    private static boolean f8636t;
    String f8637a;
    boolean f8638b;
    int f8639c;
    String f8640d;
    private String f8641e;
    private String[] f8642f;
    private Double f8643g;
    private T f8644h;
    private T f8645i;
    private String f8646j;
    private List<VariableCallback<T>> f8647k;
    private List<VariableCallback<T>> f8648l;
    private boolean f8649m;
    private boolean f8650n;
    private boolean f8651o;
    private byte[] f8652p;
    private boolean f8653q;
    private boolean f8654r;
    private int f8655s;

    private void m12586b() {
        if (!this.f8654r && !Leanplum.hasStarted() && !f8636t) {
            Log.w("Leanplum", "Leanplum hasn't finished retrieving values from the server. You should use a callback to make sure the value for '" + this.f8641e + "' is ready. Otherwise, your app may not use the most up-to-date value.");
            f8636t = true;
        }
    }

    private static <T> Var<T> m12581a(String str, T t, String str2, aS<T> aSVar) {
        if (Leanplum.f8554a && !str.startsWith("__Android Resources")) {
            Log.w("Leanplum", "You should not create new variables after calling start (name=" + str + ")");
        }
        Var<T> b = aT.m12672b(str);
        if (b != null) {
            return b;
        }
        Var<T> var = new Var();
        var.f8641e = str;
        var.f8642f = aT.m12671a(str);
        var.f8644h = t;
        var.f8645i = t;
        var.f8646j = str2;
        if (str.startsWith("__Android Resources")) {
            var.f8654r = true;
        }
        if (aSVar != null) {
            aSVar.m12654a(var);
        }
        var.m12587c();
        aT.m12663a((Var) var);
        if (var.f8646j.equals("file")) {
            aT.m12670a(var.f8637a, var.defaultValue().toString(), var.defaultStream(), var.f8638b, var.f8640d, var.f8639c);
        }
        var.m12590a();
        return var;
    }

    public static <T> Var<T> define(String str, T t) {
        return m12581a(str, t, aT.m12661a((Object) t), null);
    }

    public static <T> Var<T> define(String str, T t, String str2) {
        return m12581a(str, t, str2, null);
    }

    public static Var<Integer> defineColor(String str, int i) {
        return m12581a(str, Integer.valueOf(i), "color", null);
    }

    public static Var<String> defineFile(String str, String str2) {
        return m12581a(str, str2, "file", null);
    }

    public static Var<String> defineAsset(String str, String str2) {
        return m12581a(str, str2, "file", new aP());
    }

    static Var<String> m12582a(String str, String str2, int i, String str3, byte[] bArr) {
        return m12581a(str, str2, "file", new aQ(i, str3, bArr));
    }

    protected Var() {
        this.f8647k = new ArrayList();
        this.f8648l = new ArrayList();
        this.f8653q = false;
    }

    public String name() {
        return this.f8641e;
    }

    public String[] nameComponents() {
        return this.f8642f;
    }

    public String kind() {
        return this.f8646j;
    }

    public T defaultValue() {
        return this.f8644h;
    }

    public T value() {
        m12586b();
        return this.f8645i;
    }

    public int overrideResId() {
        return this.f8655s;
    }

    public void setOverrideResId(int i) {
        this.f8655s = i;
    }

    private void m12587c() {
        if (this.f8645i instanceof String) {
            this.f8637a = (String) this.f8645i;
            try {
                this.f8643g = Double.valueOf(this.f8637a);
            } catch (NumberFormatException e) {
                this.f8643g = null;
            }
        } else if (this.f8645i instanceof Number) {
            this.f8637a = this.f8645i;
            this.f8643g = Double.valueOf(((Number) this.f8645i).doubleValue());
            if (this.f8644h instanceof Byte) {
                this.f8645i = Byte.valueOf(((Number) this.f8645i).byteValue());
            } else if (this.f8644h instanceof Short) {
                this.f8645i = Short.valueOf(((Number) this.f8645i).shortValue());
            } else if (this.f8644h instanceof Integer) {
                this.f8645i = Integer.valueOf(((Number) this.f8645i).intValue());
            } else if (this.f8644h instanceof Long) {
                this.f8645i = Long.valueOf(((Number) this.f8645i).longValue());
            } else if (this.f8644h instanceof Float) {
                this.f8645i = Float.valueOf(((Number) this.f8645i).floatValue());
            } else if (this.f8644h instanceof Double) {
                this.f8645i = Double.valueOf(((Number) this.f8645i).doubleValue());
            } else if (this.f8644h instanceof Character) {
                this.f8645i = Character.valueOf((char) ((Number) this.f8645i).intValue());
            }
        } else if (this.f8645i == null || (this.f8645i instanceof Iterable) || (this.f8645i instanceof Map)) {
            this.f8637a = null;
            this.f8643g = null;
        } else {
            this.f8637a = this.f8645i.toString();
            this.f8643g = null;
        }
    }

    final void m12590a() {
        Object obj = this.f8645i;
        this.f8645i = aT.m12659a(this.f8642f);
        if (this.f8645i != null || obj != null) {
            if (this.f8645i == null || obj == null || !this.f8645i.equals(obj) || !this.f8650n) {
                m12587c();
                if (aT.m12681i() && this.f8641e.startsWith("__Android Resources") && this.f8646j.equals("file") && !this.f8649m) {
                    m12589e();
                }
                if (!aT.m12681i()) {
                    if (Leanplum.hasStarted()) {
                        m12588d();
                    }
                    if (this.f8646j.equals("file")) {
                        if (!C0633g.m12775a()) {
                            DownloadFileResult a = FileManager.m12415a(this.f8638b, this.f8637a, (String) this.f8644h, new aR(this));
                            this.f8653q = false;
                            if (a == DownloadFileResult.DOWNLOADING) {
                                this.f8649m = true;
                            } else if (a == DownloadFileResult.EXISTS_IN_ASSETS) {
                                this.f8653q = true;
                            }
                        }
                        if (Leanplum.hasStarted() && !this.f8649m) {
                            m12589e();
                        }
                    }
                    if (Leanplum.hasStarted()) {
                        this.f8650n = true;
                    }
                }
            }
        }
    }

    private void m12588d() {
        for (VariableCallback variableCallback : this.f8648l) {
            variableCallback.setVariable(this);
            C0612M.m12512a().m12513a(variableCallback);
        }
    }

    public void addValueChangedHandler(VariableCallback<T> variableCallback) {
        this.f8648l.add(variableCallback);
        if (Leanplum.hasStarted()) {
            m12588d();
        }
    }

    public void removeValueChangedHandler(VariableCallback<T> variableCallback) {
        this.f8648l.remove(variableCallback);
    }

    private void m12589e() {
        this.f8649m = false;
        for (VariableCallback variableCallback : this.f8647k) {
            variableCallback.setVariable(this);
            C0612M.m12512a().m12513a(variableCallback);
        }
    }

    public void addFileReadyHandler(VariableCallback<T> variableCallback) {
        this.f8647k.add(variableCallback);
        if (Leanplum.hasStarted() && !this.f8649m) {
            m12589e();
        }
    }

    public void removeFileReadyHandler(VariableCallback<T> variableCallback) {
        this.f8647k.remove(variableCallback);
    }

    public String fileValue() {
        m12586b();
        if (this.f8646j.equals("file")) {
            return FileManager.m12419a(this.f8637a, (String) this.f8644h, Boolean.valueOf(this.f8653q));
        }
        return null;
    }

    public Object objectForKeyPath(Object... objArr) {
        m12586b();
        List arrayList = new ArrayList();
        for (Object add : this.f8642f) {
            arrayList.add(add);
        }
        for (Object add2 : objArr) {
            arrayList.add(add2);
        }
        return aT.m12659a(arrayList.toArray(new Object[0]));
    }

    public int count() {
        m12586b();
        Object a = aT.m12659a(this.f8642f);
        if (a instanceof List) {
            return ((List) a).size();
        }
        Leanplum.m12437a(new UnsupportedOperationException("This variable is not a list."));
        return 0;
    }

    public Number numberValue() {
        m12586b();
        return this.f8643g;
    }

    public String stringValue() {
        m12586b();
        return this.f8637a;
    }

    public InputStream stream() {
        if (!this.f8646j.equals("file")) {
            return null;
        }
        m12586b();
        InputStream a = FileManager.m12417a(this.f8638b, Boolean.valueOf(this.f8651o), Boolean.valueOf(this.f8653q), fileValue(), (String) this.f8644h, this.f8652p);
        if (a == null) {
            return defaultStream();
        }
        return a;
    }

    public InputStream defaultStream() {
        if (this.f8646j.equals("file")) {
            return FileManager.m12417a(this.f8638b, Boolean.valueOf(this.f8651o), Boolean.valueOf(this.f8653q), (String) this.f8644h, (String) this.f8644h, this.f8652p);
        }
        return null;
    }

    public final String toString() {
        return "Var(" + this.f8641e + ")=" + this.f8645i;
    }
}
