package com.olacabs.customer.app;

import com.olacabs.customer.p076d.dw;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.olacabs.customer.app.m */
public class ObservableData extends Observable {
    private Map<String, dw> f9392a;

    public ObservableData() {
        this.f9392a = new ConcurrentHashMap();
    }

    public dw m13320a(String str) {
        return (dw) this.f9392a.get(str);
    }

    public void m13324b(String str) {
        this.f9392a.remove(str);
    }

    public void m13321a() {
        this.f9392a.clear();
    }

    public void m13322a(String str, dw dwVar) {
        this.f9392a.put(str, dwVar);
        setChanged();
        notifyObservers();
    }

    public void m13323b() {
        setChanged();
        notifyObservers();
    }
}
